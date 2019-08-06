package com.urline.dao;


import com.urline.rowMapper.PostingRowMapper;
import com.urline.template.JdbcTemplate;
import com.urline.vo.PostingVO;

import java.util.List;


public class PostingDAO {
    private static PostingDAO instance;
    private JdbcTemplate jdbcTemplate = null;


    public PostingDAO() {
        this.jdbcTemplate = new JdbcTemplate();
    }

    public static PostingDAO getInstance() {
        if (instance == null)
            instance = new PostingDAO();
        return instance;
    }

    public void insertPosting(String sql, Object... args) throws Exception {
        jdbcTemplate.update(sql, args);
    }

    public void deletePosting(String sql, Object... args) throws Exception {
        jdbcTemplate.update(sql, args);
    }

    public void updatePosting(String sql, Object... args) throws Exception {
        jdbcTemplate.update(sql, args);
    }

    public PostingVO selectOnePosting(String sql, Object... args) throws Exception {
        PostingRowMapper urm = new PostingRowMapper();
        return (PostingVO) jdbcTemplate.queryForObject(sql, urm, args);
    }

    public List selectAllPosting(String sql, Object... args) throws Exception {
        PostingRowMapper urm = new PostingRowMapper();
        return jdbcTemplate.query(sql, urm, args);
    }
}
