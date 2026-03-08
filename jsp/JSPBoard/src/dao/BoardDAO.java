package dao;

import util.DBConnection;
import dto.BoardDTO;

import java.sql.*;
import java.util.*;

public class BoardDAO {

    public void write(BoardDTO dto) throws Exception {

        Connection conn = DBConnection.getConnection();

        String sql="insert into board(title,content,writer) values(?,?,?)";

        PreparedStatement ps=conn.prepareStatement(sql);

        ps.setString(1,dto.getTitle());
        ps.setString(2,dto.getContent());
        ps.setString(3,dto.getWriter());

        ps.executeUpdate();
    }

    public List<BoardDTO> list() throws Exception {

        List<BoardDTO> list=new ArrayList<>();

        Connection conn=DBConnection.getConnection();

        String sql="select * from board order by bno desc";

        PreparedStatement ps=conn.prepareStatement(sql);

        ResultSet rs=ps.executeQuery();

        while(rs.next()){

            BoardDTO dto=new BoardDTO();

            dto.setBno(rs.getInt("bno"));
            dto.setTitle(rs.getString("title"));
            dto.setWriter(rs.getString("writer"));
            dto.setRegdate(rs.getString("regdate"));

            list.add(dto);
        }

        return list;
    }

    public BoardDTO view(int bno) throws Exception {

        Connection conn=DBConnection.getConnection();

        String sql="select * from board where bno=?";

        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1,bno);

        ResultSet rs=ps.executeQuery();

        BoardDTO dto=null;
        if(rs.next()){
            dto=new BoardDTO();
            dto.setBno(rs.getInt("bno"));
            dto.setTitle(rs.getString("title"));
            dto.setContent(rs.getString("content"));
            dto.setWriter(rs.getString("writer"));
            dto.setRegdate(rs.getString("regdate"));
        }

        return dto;
    }

    public void update(BoardDTO dto) throws Exception {

        Connection conn=DBConnection.getConnection();

        String sql="update board set title=?, content=? where bno=?";

        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1,dto.getTitle());
        ps.setString(2,dto.getContent());
        ps.setInt(3,dto.getBno());

        ps.executeUpdate();
    }

    public void delete(int bno) throws Exception {

        Connection conn=DBConnection.getConnection();

        String sql="delete from board where bno=?";

        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1,bno);

        ps.executeUpdate();
    }
}
