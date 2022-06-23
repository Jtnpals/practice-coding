package com.guestbook.mvc;

import com.guestbook.DAOFactory;
import com.guestbook.GuestBookDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/guestbook_del.do")
public class CtrlDel implements Controller{
    private GuestBookDAO guestBookDAO;
    private DAOFactory daoFactory;

    public CtrlDel() {
        this.guestBookDAO = DAOFactory.getGuestBookDAO();
        this.daoFactory = new DAOFactory();
    }

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Integer commentNo = Integer.valueOf(req.getParameter("comment_no"));
        guestBookDAO.delete(commentNo);
        return "redirect:/guestbook_list.do";
    }
}
