package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.BoardDAO;

@WebServlet("/delete")
public class BoardDeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,HttpServletResponse response)
    throws ServletException, IOException{

        try{

            HttpSession session=request.getSession();
            String loginId=(String)session.getAttribute("loginId");

            if(loginId==null){
                response.sendRedirect("login.jsp");
                return;
            }

            int bno=Integer.parseInt(request.getParameter("bno"));

            BoardDAO dao=new BoardDAO();
            dao.delete(bno);

            response.sendRedirect("list");

        }catch(Exception e){e.printStackTrace();}
    }
}
