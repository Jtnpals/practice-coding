package com.guestbook;

import java.sql.*;

public class GuestBookDAO {
    private final JdbcContext jdbcContext;

    public GuestBookDAO(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public GuestBookVO get(Integer commentNo) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GUESTBOOK WHERE COMMENT_NO = ?");
            preparedStatement.setInt(1, commentNo);
            return preparedStatement;
        };
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public Integer insert(GuestBookVO guestBookVO) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO GUESTBOOK (COMMENT_NO, CONTENT, UPDATE_DATE) VALUES (SEQ_GUESTBOOK.NEXTVAL, ?, SYSDATE)", new String[]{"COMMENT_NO"});
            preparedStatement.setString(1, guestBookVO.getContent());
            return preparedStatement;
        };
        return jdbcContext.jdbcContextInsert(statementStrategy);
    }

    public void update(GuestBookVO guestBookVO) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE GUESTBOOK SET CONTENT = ?, UPDATE_DATE = SYSDATE where COMMENT_NO= ?");
            preparedStatement.setString(1, guestBookVO.getContent());
            preparedStatement.setInt(2, guestBookVO.getCommentNo());
            return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Integer commentNo) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM GUESTBOOK WHERE COMMENT_NO = ?");
            preparedStatement.setInt(1, commentNo);
            return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}