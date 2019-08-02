package com.guestbook;

import java.sql.*;

public class GuestBookDAO {
    private final JdbcContext jdbcContext;

    public GuestBookDAO(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public GuestBookVO get(Integer commentNo) throws SQLException {
        StatementStrategy statementStrategy = new GetGuestVOStatementStrategy(commentNo);
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public Integer insert(GuestBookVO guestBookVO) throws SQLException {
        StatementStrategy statementStrategy = new InsertGuestVOStatementStrategy(guestBookVO);
        return jdbcContext.jdbcContextInsert(statementStrategy);
    }
    public void update(GuestBookVO guestBookVO) throws SQLException {
        StatementStrategy statementStrategy = new UpdateGuestbookVOStatmentStrategy(guestBookVO);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Integer commentNo) throws SQLException {
        StatementStrategy statementStrategy = new DeleteGuestbookVOStatementStrategy(commentNo);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}