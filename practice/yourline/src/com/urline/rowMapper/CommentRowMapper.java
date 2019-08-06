package com.urline.rowMapper;

import com.urline.template.RowMapper;
import com.urline.vo.CommentVO;

import java.sql.ResultSet;

public class CommentRowMapper implements RowMapper {

	@Override
	public CommentVO mapRow(ResultSet rs) throws Exception {
		CommentVO vo = new CommentVO();
		vo.setUserNo(rs.getInt(1));
		vo.setPostNo(rs.getInt(2));
		vo.setCommentNo(rs.getInt(3));
		vo.setwDate(rs.getString(4));
		vo.setContents(rs.getString(5));
		vo.setSelected(rs.getInt(6));
		vo.setH_hopefulPoint(rs.getInt(7));
		return vo;
	}

}
