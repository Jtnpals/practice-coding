package com.guestbook;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.sql.SQLException;

public class GuestBookDAOTest {
    private GuestBookDAO guestBookDAO;
    @Before
    public void setup() {
        guestBookDAO = new GuestBookDAO();
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Integer commentNo = 2;
        String content = "가나다라";
        String createDate = "2019-08-01 20:52:43.0";

        GuestBookVO guestBookVO = guestBookDAO.get(commentNo);
        assertEquals(commentNo, guestBookVO.getCommentNo());
        assertEquals(content, guestBookVO.getContent());
        assertEquals(createDate, guestBookVO.getCreateDate());
    }

}
