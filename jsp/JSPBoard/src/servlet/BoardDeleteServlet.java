package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
