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

    public Integer insert(GuestBookVO guestBookVO) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","hr", "hr");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO GUESTBOOK (COMMENT_NO, CONTENT, CREATE_DATE) VALUES (SEQ_GUESTBOOK.nextval, ?, SYSDATE)", new String[] {"COMMENT_NO"});
        preparedStatement.setString(1, guestBookVO.getContent());

        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        System.out.println();
        Integer commentNo = Math.toIntExact(resultSet.getLong(1));
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return commentNo;
    }

}
