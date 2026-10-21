package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * MySQL 연결 — db_setup.sql 의 crud_board 와 계정/비번을 맞추세요.
 */
public class DBConnection {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL =
            "jdbc:mysql://localhost:3306/crud_board?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws Exception {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
