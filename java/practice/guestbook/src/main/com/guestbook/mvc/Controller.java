package com.guestbook.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    public String handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
