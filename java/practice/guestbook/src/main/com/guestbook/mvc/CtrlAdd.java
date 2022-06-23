package com.guestbook.mvc;


import com.guestbook.DAOFactory;
import com.guestbook.GuestBookDAO;
import com.guestbook.GuestBookVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/guestbook_add.do")
public class CtrlAdd implements Controller {

    private GuestBookDAO guestBookDAO;
    private DAOFactory daoFactory;

    public CtrlAdd() {
        daoFactory = new DAOFactory();
        guestBookDAO = DAOFactory.getGuestBookDAO();
    }

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String content = Util.h(req.getParameter("content"));
        GuestBookVO guestBookVO = new GuestBookVO();
        guestBookVO.setContent(content);
        guestBookDAO.insert(guestBookVO);
    return "redirect:/guestbook_list.do";
    }
}
