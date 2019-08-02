package com.guestbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcContext {
    final ConnectionMaker connectionMaker;

    public JdbcContext(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    GuestBookVO queryForObject(String sql, Object[] params) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        };
        return jdbcContextForGet(statementStrategy);
    }

    Integer insert(String sql, Object[] params) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"COMMENT_NO"});
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        };
        return jdbcContextInsert(statementStrategy);
    }

    void update(String sql, Object[] params) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        };
        jdbcContextForUpdate(statementStrategy);
    }



    GuestBookVO jdbcContextForGet(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        GuestBookVO guestBookVO = null;
        try {
            connection = connectionMaker.getConnection();
            preparedStatement = statementStrategy.makeConnection(connection);

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

    Integer jdbcContextInsert(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer commentNo = null;
        ResultSet resultSet = null;
        try {
            connection = connectionMaker.getConnection();
            preparedStatement = statementStrategy.makeConnection(connection);

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


    void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionMaker.getConnection();
            preparedStatement = statementStrategy.makeConnection(connection);

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
