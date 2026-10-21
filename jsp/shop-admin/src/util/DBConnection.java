package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * MySQL 연결 유틸
 * - shop_db 데이터베이스에 접속
 * - 비밀번호는 본인 MySQL 환경에 맞게 수정
 */
public class DBConnection {

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/shop_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul&characterEncoding=UTF-8",
                "root",
                "1234");
    }
}
