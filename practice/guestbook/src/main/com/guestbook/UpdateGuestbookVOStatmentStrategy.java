package com.guestbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateGuestbookVOStatmentStrategy implements StatementStrategy {
    GuestBookVO guestBookVO;
    public UpdateGuestbookVOStatmentStrategy(GuestBookVO guestBookVO) {
        this.guestBookVO = guestBookVO;
    }

    @Override
    public PreparedStatement makeConnection(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE GUESTBOOK SET CONTENT = ?, UPDATE_DATE = SYSDATE where COMMENT_NO= ?");
        preparedStatement.setString(1, guestBookVO.getContent());
        preparedStatement.setInt(2, guestBookVO.getCommentNo());
        return preparedStatement;
    }
}
