package com.urline.dao;

import com.urline.rowMapper.PayRecordRowMapper;
import com.urline.template.JdbcTemplate;
import com.urline.vo.PayRecordVO;

import java.util.List;

public class PayRecordDAO {
    private static PayRecordDAO instance;
    private JdbcTemplate jdbcTemplate = null;

    public PayRecordDAO() {
        this.jdbcTemplate = new JdbcTemplate();
    }

    public static PayRecordDAO getInstance() {
        if (instance == null)
            instance = new PayRecordDAO();
        return instance;
    }

    public void insertPayRecord(String sql, Object... args) throws Exception {
        jdbcTemplate.update(sql, args);
    }

    public void deletePayRecord(String sql, Object... args) throws Exception {
        jdbcTemplate.update(sql, args);
    }

    public void updatePayRecord(String sql, Object... args) throws Exception {
        jdbcTemplate.update(sql, args);
    }

    public PayRecordVO selectOnePayRecord(String sql, Object... args) throws Exception {
        PayRecordRowMapper urm = new PayRecordRowMapper();
        return (PayRecordVO) jdbcTemplate.queryForObject(sql, urm, args);
    }

    public List<PayRecordVO> selectAllPayRecord(String sql, Object... args) throws Exception {
        PayRecordRowMapper urm = new PayRecordRowMapper();
        return jdbcTemplate.query(sql, urm, args);
    }
}
