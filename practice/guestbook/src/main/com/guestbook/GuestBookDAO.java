package com.guestbook;

import java.sql.*;

public class GuestBookDAO {

    public GuestBookVO get(Integer commentNo) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","hr", "hr");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GUESTBOOK WHERE COMMENT_NO = ?");
        preparedStatement.setInt(1, commentNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        GuestBookVO guestBookVO = new GuestBookVO();
        guestBookVO.setCommentNo(resultSet.getInt("COMMENT_NO"));
        guestBookVO.setContent(resultSet.getString("CONTENT"));
        guestBookVO.setCreateDate(resultSet.getString("CREATE_DATE"));
        return guestBookVO;
    }

}
