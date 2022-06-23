package com.urline.rowMapper;


import com.urline.template.RowMapper;
import com.urline.vo.UserVO;

import java.sql.ResultSet;

public class UserRowMapper implements RowMapper {

	@Override
	public UserVO mapRow(ResultSet rs) throws Exception {
		UserVO vo = new UserVO();
		vo.setUserNo(rs.getInt(1));
		vo.setId(rs.getString(2));
		vo.setPw(rs.getString(3));
		vo.setPhone(rs.getString(4));
		vo.setNickname(rs.getString(5));
		vo.setPoint(rs.getInt(6));
		vo.setGrade(rs.getInt(7));
		vo.setSex(rs.getInt(8));
		vo.setName(rs.getString(9));
		return vo;
	}

}
