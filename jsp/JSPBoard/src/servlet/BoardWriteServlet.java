package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.BoardDAO;
import dto.BoardDTO;

@WebServlet("/write")
public class BoardWriteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,HttpServletResponse response)
    throws ServletException, IOException{

        try{

            request.setCharacterEncoding("UTF-8");

            HttpSession session=request.getSession();
            String writer=(String)session.getAttribute("loginId");

            if(writer==null){
                response.sendRedirect("login.jsp");
                return;
            }

            String title=request.getParameter("title");
            String content=request.getParameter("content");

            BoardDTO dto=new BoardDTO();
            dto.setTitle(title);
            dto.setContent(content);
            dto.setWriter(writer);

            BoardDAO dao=new BoardDAO();
            dao.write(dto);

            response.sendRedirect("list");

        }catch(Exception e){e.printStackTrace();}
    }
}
