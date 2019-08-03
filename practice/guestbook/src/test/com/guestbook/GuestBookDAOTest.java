package com.guestbook;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class GuestBookDAOTest {
    private GuestBookDAO guestBookDAO;
    private DAOFactory daoFactory;

    @Before
    public void setup() {
        daoFactory = new DAOFactory();
        guestBookDAO = DAOFactory.getGuestBookDAO();
    }

    @Test
    public void get() throws SQLException {
        Integer commentNo = 3;
        String content = "새로운 글";
        String createDate = "2019-08-03 05:08:26.0";

        GuestBookVO guestBookVO = guestBookDAO.get(commentNo);
        assertEquals(commentNo, guestBookVO.getCommentNo());
        assertEquals(content, guestBookVO.getContent());
        assertEquals(createDate, guestBookVO.getUpdateDate());
    }

    @Test
    public void insert() throws SQLException {
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

    @Test
    public void delete() throws SQLException {
        GuestBookVO guestBookVO = new GuestBookVO();
        guestBookVO.setContent("새로운 글");

        Integer commentNo = guestBookDAO.insert(guestBookVO);

        guestBookDAO.delete(commentNo);

        GuestBookVO deletedProduct = guestBookDAO.get(commentNo);
        assertNull(deletedProduct);
    }

    @Test
    public void findAll() throws SQLException{
        ArrayList<GuestBookVO> list = (ArrayList<GuestBookVO>) guestBookDAO.findAll();
        for(GuestBookVO arrayList : list){
            System.out.println(arrayList.getContent());
        }
    }

}
