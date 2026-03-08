package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
