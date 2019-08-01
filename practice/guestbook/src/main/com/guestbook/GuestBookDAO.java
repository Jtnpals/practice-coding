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
            // 없을시 null값 반환 되도록
            if(resultSet.next()) {
                guestBookVO = new GuestBookVO();
                guestBookVO.setCommentNo(resultSet.getInt("COMMENT_NO"));
                guestBookVO.setContent(resultSet.getString("CONTENT"));
                guestBookVO.setUpdateDate(resultSet.getString("UPDATE_DATE"));
            }
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
            preparedStatement = connection.prepareStatement("UPDATE GUESTBOOK SET CONTENT = ?, UPDATE_DATE = SYSDATE where COMMENT_NO= ?");
            preparedStatement.setString(1, guestBookVO.getContent());
            preparedStatement.setInt(2, guestBookVO.getCommentNo());

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

    public void delete(Integer commentNo) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionMaker.getConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM GUESTBOOK WHERE COMMENT_NO = ?");
            preparedStatement.setInt(1, commentNo);

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