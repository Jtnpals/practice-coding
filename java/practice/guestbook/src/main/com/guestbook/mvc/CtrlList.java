package com.guestbook.mvc;

import com.guestbook.DAOFactory;
import com.guestbook.GuestBookDAO;
import com.guestbook.GuestBookVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/guestbook_list.do")
public class CtrlList implements Controller {
    private GuestBookDAO guestBookDAO;
    private DAOFactory daoFactory;

    public CtrlList() {
        this.guestBookDAO = DAOFactory.getGuestBookDAO();
        this.daoFactory = new DAOFactory();
    }

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<GuestBookVO> rl = guestBookDAO.findAll();
        req.setAttribute("rl", rl);
        return "/guestbook_list.jsp";
    }
}
