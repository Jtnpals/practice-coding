package com.guestbook;

import java.sql.*;

public class GuestBookDAO {
    private final ConnectionMaker connectionMaker;

    public GuestBookDAO(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public GuestBookVO get(Integer commentNo) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        GuestBookVO guestBookVO = null;
        try {
            connection = connectionMaker.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM GUESTBOOK WHERE COMMENT_NO = ?");
            preparedStatement.setInt(1, commentNo);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            guestBookVO = new GuestBookVO();
            guestBookVO.setCommentNo(resultSet.getInt("COMMENT_NO"));
            guestBookVO.setContent(resultSet.getString("CONTENT"));
            guestBookVO.setCreateDate(resultSet.getString("CREATE_DATE"));
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return guestBookVO;
    }

    public Integer insert(GuestBookVO guestBookVO) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer commentNo = null;
        ResultSet resultSet = null;
        try {
            connection = connectionMaker.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO GUESTBOOK (COMMENT_NO, CONTENT, UPDATE_DATE) VALUES (SEQ_GUESTBOOK.NEXTVAL, ?, SYSDATE)", new String[]{"COMMENT_NO"});
            preparedStatement.setString(1, guestBookVO.getContent());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            commentNo = Math.toIntExact(resultSet.getLong(1));
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return commentNo;
    }

    public void update(GuestBookVO guestBookVO) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionMaker.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE GUESTBOOK SET CONTENT = ? UPDATE_DATE = SYSDATE where COMMNET_NO= ?");
            preparedStatement.setString(1, guestBookVO.getContent());
            preparedStatement.setLong(2, guestBookVO.getCommentNo());

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }


}