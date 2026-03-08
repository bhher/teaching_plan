package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import dao.BoardDAO;
import dto.BoardDTO;

@WebServlet("/update")
public class BoardUpdateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,HttpServletResponse response)
    throws ServletException, IOException{

        try{

            int bno=Integer.parseInt(request.getParameter("bno"));

            BoardDAO dao=new BoardDAO();
            BoardDTO dto=dao.view(bno);

            request.setAttribute("board",dto);

            RequestDispatcher rd=request.getRequestDispatcher("boardUpdate.jsp");
            rd.forward(request,response);

        }catch(Exception e){e.printStackTrace();}
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response)
    throws ServletException, IOException{

        try{

            request.setCharacterEncoding("UTF-8");

            HttpSession session=request.getSession();
            String loginId=(String)session.getAttribute("loginId");

            if(loginId==null){
                response.sendRedirect("login.jsp");
                return;
            }

            int bno=Integer.parseInt(request.getParameter("bno"));
            String title=request.getParameter("title");
            String content=request.getParameter("content");

            BoardDTO dto=new BoardDTO();
            dto.setBno(bno);
            dto.setTitle(title);
            dto.setContent(content);

            BoardDAO dao=new BoardDAO();
            dao.update(dto);

            response.sendRedirect("view?bno="+bno);

        }catch(Exception e){e.printStackTrace();}
    }
}
