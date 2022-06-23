package com.urline.vo;

public class PostingVO {
	// 이 VO는 USER 테이블과 POST테이블을 JOIN한 결과 테이블에 대한 것이다.
	// 만든 이유는 게시판에 보여주기위한 데이터들이 USER와 POST에 산재되어 있기때문에 JOIN을
	// 통해 합치고 게시글에서 보여줄 필요한 데이터만 추출하기 위해서다.
	private Integer userNo = null;
	private Integer postNo = null;
	private String nickName = null;
	private Integer grade = null;
	private String title = null;
	private String region = null;
	private String postingDate = null;
	private String contents = null;
	private Integer b_hopefulPoint = null;//buyer가 희망하는 포인트
	private Integer state = null;
	private String requestTime = null;
	private String howMuch = null;

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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Integer getB_hopefulPoint() {
		return b_hopefulPoint;
	}

	public void setB_hopefulPoint(Integer b_hopefulPoint) {
		this.b_hopefulPoint = b_hopefulPoint;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getHowMuch() {
		return howMuch;
	}

	public void setHowMuch(String howMuch) {
		this.howMuch = howMuch;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	private String validTime = null;

}
