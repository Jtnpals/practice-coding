package com.guestbook;

public class GuestBookVO {
    private Integer commentNo = null;
    private String content = null;
    private String createDate = null;

    public Integer getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(Integer commentNo) {
        this.commentNo = commentNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
