package com.urline.rowMapper;

import com.urline.template.RowMapper;
import com.urline.vo.PayRecordVO;

import java.sql.ResultSet;

public class PayRecordRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs) throws Exception {
        PayRecordVO vo = new PayRecordVO();
        vo.setPayNo(rs.getInt(1));
        vo.setPayAmount(rs.getInt(2));
        vo.setPayDate(rs.getString(3));
        vo.setUserNo(rs.getInt(4));
        return null;
    }
}
