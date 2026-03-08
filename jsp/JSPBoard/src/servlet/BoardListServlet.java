package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
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
