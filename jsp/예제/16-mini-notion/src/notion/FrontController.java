package notion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.util.List;

/**
 * *.do URL 패턴 처리 - 미니 노션 Front Controller
 * list.do, write.do, update.do, delete.do
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String uri = request.getRequestURI();
        String path = uri.substring(uri.lastIndexOf("/") + 1);
        String action = path.replace(".do", "");

        String view = null;

        try {
            NoteDAO dao = new NoteDAO();

            switch (action) {
                case "list":
                    List<NoteDTO> list = dao.list();
                    request.setAttribute("list", list);
                    view = "/list.jsp";
                    break;

                case "write":
                    String parentIdStr = request.getParameter("parentId");
                    if (parentIdStr != null && !parentIdStr.isEmpty()) {
                        try {
                            request.setAttribute("parentId", Integer.valueOf(parentIdStr));
                        } catch (NumberFormatException ignored) {}
                    }
                    view = "/write.jsp";
                    break;

                case "writeProcess":
                    String wTitle = request.getParameter("title");
                    String wContent = request.getParameter("content");
                    String wWriter = request.getParameter("writer");
                    String pId = request.getParameter("parentId");

                    NoteDTO wDto = new NoteDTO();
                    wDto.setTitle(wTitle != null ? wTitle : "");
                    wDto.setContent(wContent != null ? wContent : "");
                    wDto.setWriter(wWriter);

                    int newId;
                    if (pId != null && !pId.isEmpty()) {
                        newId = dao.insertReply(Integer.parseInt(pId), wDto);
                    } else {
                        newId = dao.insertRoot(wDto);
                    }
                    response.sendRedirect(request.getContextPath() + "/list.do");
                    return;

                case "update":
                    String uId = request.getParameter("id");
                    if (uId != null && !uId.isEmpty()) {
                        NoteDTO uDto = dao.findById(Integer.parseInt(uId));
                        request.setAttribute("note", uDto);
                    }
                    view = "/update.jsp";
                    break;

                case "updateProcess":
                    String upId = request.getParameter("id");
                    String upTitle = request.getParameter("title");
                    String upContent = request.getParameter("content");
                    String upWriter = request.getParameter("writer");

                    NoteDTO upDto = new NoteDTO();
                    upDto.setId(Integer.parseInt(upId));
                    upDto.setTitle(upTitle != null ? upTitle : "");
                    upDto.setContent(upContent != null ? upContent : "");
                    upDto.setWriter(upWriter);
                    dao.update(upDto);
                    response.sendRedirect(request.getContextPath() + "/list.do");
                    return;

                case "delete":
                    String dId = request.getParameter("id");
                    if (dId != null && !dId.isEmpty()) {
                        dao.moveToTrash(Integer.parseInt(dId));
                    }
                    response.sendRedirect(request.getContextPath() + "/list.do");
                    return;

                case "trash":
                    List<NoteDTO> trashList = dao.listTrash();
                    request.setAttribute("trashList", trashList);
                    view = "/trash.jsp";
                    break;

                default:
                    response.sendRedirect(request.getContextPath() + "/list.do");
                    return;
            }

            if (view != null) {
                RequestDispatcher rd = request.getRequestDispatcher(view);
                rd.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
