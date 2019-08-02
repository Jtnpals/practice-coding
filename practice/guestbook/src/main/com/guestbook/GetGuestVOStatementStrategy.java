package com.guestbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetGuestVOStatementStrategy implements StatementStrategy {
    private Integer commentNo;

    public GetGuestVOStatementStrategy(Integer commentNo) {
        this.commentNo = commentNo;
    }

    @Override
    public PreparedStatement makeConnection(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GUESTBOOK WHERE COMMENT_NO = ?");
        preparedStatement.setInt(1, commentNo);
        return preparedStatement;
    }
}
