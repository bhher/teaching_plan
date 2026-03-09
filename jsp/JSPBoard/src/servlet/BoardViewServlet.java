package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import dao.BoardDAO;
import dto.BoardDTO;

@WebServlet("/view")
public class BoardViewServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,HttpServletResponse response)
    throws ServletException, IOException{

        try{

            int bno=Integer.parseInt(request.getParameter("bno"));

            BoardDAO dao=new BoardDAO();
            BoardDTO dto=dao.view(bno);

            request.setAttribute("board",dto);

            RequestDispatcher rd=request.getRequestDispatcher("boardView.jsp");
            rd.forward(request,response);

        }catch(Exception e){e.printStackTrace();}
    }
}
