package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
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
