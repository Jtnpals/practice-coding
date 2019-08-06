package com.urline.vo;

public class CommentVO {
	private Integer userNo = null;
	private Integer postNo = null;
	private Integer commentNo = null;
	private String wDate = null;
	private String contents = null;
	private Integer selected = null;
	private Integer h_hopefulPoint = null;

	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	public Integer getPostNo() {
		return postNo;
	}

	public void setPostNo(Integer postNo) {
		this.postNo = postNo;
	}

	public Integer getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(Integer commentNo) {
		this.commentNo = commentNo;
	}

	public String getwDate() {
		return wDate;
	}

	public void setwDate(String wDate) {
		this.wDate = wDate;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public Integer getH_hopefulPoint() {
		return h_hopefulPoint;
	}

	public void setH_hopefulPoint(Integer h_hopefulPoint) {
		this.h_hopefulPoint = h_hopefulPoint;
	}
}
