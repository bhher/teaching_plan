package kculture;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.util.List;

/**
 * K-컬쳐 플랫폼 Front Controller (*.do)
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
            MemberDAO memberDAO = new MemberDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            PostDAO postDAO = new PostDAO();
            CommentDAO commentDAO = new CommentDAO();

            List<CategoryDTO> categories = categoryDAO.list();
            request.setAttribute("categories", categories);

            switch (action) {
                case "index":
                case "list":
                    int catId = parseInt(request.getParameter("categoryId"), 0);
                    int page = parseInt(request.getParameter("page"), 1);
                    int pageSize = 10;
                    int start = (page - 1) * pageSize;

                    List<PostDTO> list = postDAO.list(catId, start, pageSize);
                    int total = postDAO.count(catId);
                    int totalPages = (int) Math.ceil((double) total / pageSize);

                    request.setAttribute("list", list);
                    request.setAttribute("categoryId", catId);
                    request.setAttribute("currentPage", page);
                    request.setAttribute("totalPages", totalPages);
                    request.setAttribute("total", total);
                    view = "/list.jsp";
                    break;

                case "view":
                    int postId = parseInt(request.getParameter("id"), 0);
                    if (postId <= 0) {
                        response.sendRedirect(request.getContextPath() + "/list.do");
                        return;
                    }
                    PostDTO post = postDAO.findById(postId);
                    if (post == null) {
                        response.sendRedirect(request.getContextPath() + "/list.do");
                        return;
                    }
                    postDAO.increaseView(postId);
                    post.setViewCount(post.getViewCount() + 1);
                    List<CommentDTO> comments = commentDAO.listByPostId(postId);
                    request.setAttribute("post", post);
                    request.setAttribute("comments", comments);
                    view = "/view.jsp";
                    break;

                case "write":
                    if (getLoginMember(request) == null) {
                        response.sendRedirect(request.getContextPath() + "/login.do");
                        return;
                    }
                    view = "/write.jsp";
                    break;

                case "writeProcess":
                    MemberDTO login = getLoginMember(request);
                    if (login == null) {
                        response.sendRedirect(request.getContextPath() + "/login.do");
                        return;
                    }
                    PostDTO wDto = new PostDTO();
                    wDto.setCategoryId(parseInt(request.getParameter("categoryId"), 1));
                    wDto.setMemberId(login.getId());
                    wDto.setTitle(request.getParameter("title"));
                    wDto.setContent(request.getParameter("content"));
                    int newId = postDAO.insert(wDto);
                    response.sendRedirect(request.getContextPath() + "/view.do?id=" + newId);
                    return;

                case "edit":
                    if (getLoginMember(request) == null) {
                        response.sendRedirect(request.getContextPath() + "/login.do");
                        return;
                    }
                    int editId = parseInt(request.getParameter("id"), 0);
                    PostDTO editPost = postDAO.findById(editId);
                    if (editPost == null || editPost.getMemberId() != getLoginMember(request).getId()) {
                        response.sendRedirect(request.getContextPath() + "/list.do");
                        return;
                    }
                    request.setAttribute("post", editPost);
                    view = "/edit.jsp";
                    break;

                case "editProcess":
                    login = getLoginMember(request);
                    if (login == null) {
                        response.sendRedirect(request.getContextPath() + "/login.do");
                        return;
                    }
                    PostDTO upDto = new PostDTO();
                    upDto.setId(parseInt(request.getParameter("id"), 0));
                    upDto.setMemberId(login.getId());
                    upDto.setTitle(request.getParameter("title"));
                    upDto.setContent(request.getParameter("content"));
                    postDAO.update(upDto);
                    response.sendRedirect(request.getContextPath() + "/view.do?id=" + upDto.getId());
                    return;

                case "delete":
                    login = getLoginMember(request);
                    if (login == null) {
                        response.sendRedirect(request.getContextPath() + "/login.do");
                        return;
                    }
                    int delId = parseInt(request.getParameter("id"), 0);
                    postDAO.delete(delId, login.getId());
                    response.sendRedirect(request.getContextPath() + "/list.do");
                    return;

                case "commentProcess":
                    login = getLoginMember(request);
                    if (login == null) {
                        response.sendRedirect(request.getContextPath() + "/login.do");
                        return;
                    }
                    CommentDTO cDto = new CommentDTO();
                    cDto.setPostId(parseInt(request.getParameter("postId"), 0));
                    cDto.setMemberId(login.getId());
                    cDto.setContent(request.getParameter("content"));
                    commentDAO.insert(cDto);
                    response.sendRedirect(request.getContextPath() + "/view.do?id=" + cDto.getPostId());
                    return;

                case "login":
                    view = "/login.jsp";
                    break;

                case "loginProcess":
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    MemberDTO m = memberDAO.login(email, password);
                    if (m != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("loginMember", m);
                        response.sendRedirect(request.getContextPath() + "/list.do");
                    } else {
                        request.setAttribute("error", "Invalid email or password.");
                        view = "/login.jsp";
                    }
                    break;

                case "logout":
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/list.do");
                    return;

                case "join":
                    view = "/join.jsp";
                    break;

                case "joinProcess":
                    MemberDTO jDto = new MemberDTO();
                    jDto.setEmail(request.getParameter("email"));
                    jDto.setPassword(request.getParameter("password"));
                    jDto.setName(request.getParameter("name"));
                    jDto.setNationality(request.getParameter("nationality"));
                    jDto.setLanguage(request.getParameter("language"));
                    if (memberDAO.existsEmail(jDto.getEmail())) {
                        request.setAttribute("error", "Email already exists.");
                        view = "/join.jsp";
                    } else {
                        memberDAO.join(jDto);
                        response.sendRedirect(request.getContextPath() + "/login.do");
                        return;
                    }
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

    private MemberDTO getLoginMember(HttpServletRequest request) {
        return (MemberDTO) request.getSession().getAttribute("loginMember");
    }

    private int parseInt(String s, int def) {
        if (s == null || s.isEmpty()) return def;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return def;
        }
    }
}
