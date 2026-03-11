package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.MemberDAO;

@WebServlet("/join")
public class JoinServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,HttpServletResponse response)
    throws ServletException, IOException{

        try{

            request.setCharacterEncoding("UTF-8");

            String id=request.getParameter("id");
            String pw=request.getParameter("password");
            String name=request.getParameter("name");
            String email=request.getParameter("email");

            MemberDAO dao=new MemberDAO();

            dao.join(id,pw,name,email);

            response.sendRedirect("login.jsp");

        }catch(Exception e){e.printStackTrace();}
    }
}
