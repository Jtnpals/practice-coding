package com.guestbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestBookDAO {
    private final JdbcContext jdbcContext;

    public GuestBookDAO(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public GuestBookVO get(Integer commentNo) throws SQLException {
        String sql = "SELECT * FROM GUESTBOOK WHERE COMMENT_NO = ?";
        Object[] params = new Object[]{commentNo};
        return jdbcContext.queryForObject(sql, params);
    }

    public Integer insert(GuestBookVO guestBookVO) throws SQLException {
        String sql = "INSERT INTO GUESTBOOK (COMMENT_NO, CONTENT, UPDATE_DATE) VALUES (SEQ_GUESTBOOK.NEXTVAL, ?, SYSDATE)";
        Object[] params = new Object[]{guestBookVO.getContent()};
        return jdbcContext.insert(sql, params);
    }

    public void update(GuestBookVO guestBookVO) throws SQLException {
        String sql = "UPDATE GUESTBOOK SET CONTENT = ?, UPDATE_DATE = SYSDATE where COMMENT_NO= ?";
        Object[] params = new Object[]{guestBookVO.getContent(), guestBookVO.getCommentNo()};
        jdbcContext.update(sql, params);
    }

    public void delete(Integer commentNo) throws SQLException {
        String sql = "DELETE FROM GUESTBOOK WHERE COMMENT_NO = ?";
        Object[] params = new Object[]{commentNo};
        jdbcContext.update(sql, params);
    }

    public List<GuestBookVO> findAll() throws SQLException{
        String sql = "SELECT COMMENT_NO, CONTENT, UPDATE_DATE FROM GUESTBOOK ORDER BY COMMENT_NO DESC";
        return jdbcContext.findAll(sql);
    }
}