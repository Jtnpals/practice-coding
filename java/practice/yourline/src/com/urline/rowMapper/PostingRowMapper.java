package com.urline.rowMapper;

import com.urline.template.RowMapper;
import com.urline.vo.PostingVO;

import java.sql.ResultSet;

public class PostingRowMapper implements RowMapper {

	@Override
	public PostingVO mapRow(ResultSet rs) throws Exception {
		PostingVO vo = new PostingVO();
		vo.setUserNo(rs.getInt(1));
		vo.setPostNo(rs.getInt(2));
		vo.setNickName(rs.getString(3));
		vo.setGrade(rs.getInt(4));
		vo.setTitle(rs.getString(5));
		vo.setRegion(rs.getString(6));
		vo.setPostingDate(rs.getString(7));
		vo.setContents(rs.getString(8));
		vo.setB_hopefulPoint(rs.getInt(9));
		vo.setState(rs.getInt(10));
		vo.setRequestTime(rs.getString(11));
		vo.setHowMuch(rs.getString(12));
		return vo;
	}

}
