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
    private DAOFactory daoFactory;
    @Before
    public void setup() {
        daoFactory = new DAOFactory();
        guestBookDAO = DAOFactory.getGuestBookDAO();
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Integer commentNo = 2;
        String content = "가나다라";
        String createDate = "2019-08-01 20:52:43.0";

        GuestBookVO guestBookVO = guestBookDAO.get(commentNo);
        assertEquals(commentNo, guestBookVO.getCommentNo());
        assertEquals(content, guestBookVO.getContent());
        assertEquals(createDate, guestBookVO.getUpdateDate());
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        GuestBookVO guestBookVO = new GuestBookVO();
        guestBookVO.setContent("새로운 글");

        Integer commentNo = guestBookDAO.insert(guestBookVO);
        GuestBookVO insertedGuestBookVO = guestBookDAO.get(commentNo);

        assertEquals(commentNo, insertedGuestBookVO.getCommentNo());
        assertEquals(guestBookVO.getContent(), insertedGuestBookVO.getContent());
    }

    @Test
    public void update() throws SQLException {
        GuestBookVO guestBookVO = new GuestBookVO();
        guestBookVO.setContent("새로운 글");

        Integer commentNo = guestBookDAO.insert(guestBookVO);

        guestBookVO.setCommentNo(commentNo);
        guestBookVO.setContent("수정된 글");
        guestBookDAO.update(guestBookVO);

        GuestBookVO updatedGuestBookVO = guestBookDAO.get(commentNo);
        assertEquals(guestBookVO.getCommentNo(), updatedGuestBookVO.getCommentNo());
        assertEquals(guestBookVO.getContent(), updatedGuestBookVO.getContent());
    }

}
