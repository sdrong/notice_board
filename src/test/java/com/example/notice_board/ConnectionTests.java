package com.example.notice_board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
//JDBC를 사용하여 MariaDB에 접속하는 것을 테스트
public class ConnectionTests {
    @Test
    public void testConnection() throws Exception{
        Class.forName("org.mariadb.jdbc.Driver");

        Connection connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/notice_board",
                "post_user",
                "6030"
        );
        Assertions.assertNotNull(connection);

        connection.close();
    }
}