package com.guestbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertGuestVOStatementStrategy implements StatementStrategy {
    GuestBookVO guestBookVO;

    public InsertGuestVOStatementStrategy(GuestBookVO guestBookVO) {
        this.guestBookVO = guestBookVO;
    }

    @Override
    public PreparedStatement makeConnection(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO GUESTBOOK (COMMENT_NO, CONTENT, UPDATE_DATE) VALUES (SEQ_GUESTBOOK.NEXTVAL, ?, SYSDATE)", new String[]{"COMMENT_NO"});
        preparedStatement.setString(1, guestBookVO.getContent());
        return preparedStatement;
    }
}
