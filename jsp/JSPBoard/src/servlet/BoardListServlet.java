package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import dao.BoardDAO;
import dto.BoardDTO;

@WebServlet("/list")
public class BoardListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,HttpServletResponse response)
    throws ServletException, IOException{

        try{

            BoardDAO dao=new BoardDAO();

            List<BoardDTO> list=dao.list();

            request.setAttribute("list",list);

            RequestDispatcher rd=request.getRequestDispatcher("boardList.jsp");

            rd.forward(request,response);

        }catch(Exception e){e.printStackTrace();}
    }
}
