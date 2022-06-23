package com.guestbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnectionMaker implements ConnectionMaker {
    @Override
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "hr", "hr");
    }
}
