package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.MemberDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,HttpServletResponse response)
    throws ServletException, IOException{

        try{

            String id=request.getParameter("id");
            String pw=request.getParameter("password");

            MemberDAO dao=new MemberDAO();

            boolean result=dao.login(id,pw);

            if(result){

                HttpSession session=request.getSession();
                session.setAttribute("loginId",id);

                response.sendRedirect("list");

            }else{

                response.sendRedirect("login.jsp");
            }

        }catch(Exception e){e.printStackTrace();}
    }
}
