package dao;

import util.DBConnection;
import java.sql.*;

public class MemberDAO {

    public void join(String id,String pw,String name,String email) throws Exception {

        Connection conn = DBConnection.getConnection();

        String sql="insert into member values(?,?,?,?,now())";

        PreparedStatement ps=conn.prepareStatement(sql);

        ps.setString(1,id);
        ps.setString(2,pw);
        ps.setString(3,name);
        ps.setString(4,email);

        ps.executeUpdate();
    }

    public boolean login(String id,String pw)throws Exception{

        Connection conn=DBConnection.getConnection();

        String sql="select * from member where id=? and password=?";

        PreparedStatement ps=conn.prepareStatement(sql);

        ps.setString(1,id);
        ps.setString(2,pw);

        ResultSet rs=ps.executeQuery();

        return rs.next();
    }
}
