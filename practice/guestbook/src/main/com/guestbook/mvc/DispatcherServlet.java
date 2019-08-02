package com.guestbook.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class DispatcherServlet extends HttpServlet{

    private Map<String, Controller> mapp = null;

    @Override
    public void init(ServletConfig config) throws ServletException {

        mapp = new Hashtable<String, Controller>();

        String cs = config.getInitParameter("controllers");
        String[] cs2 = cs.split(",");
        for(int i = 0 ; i<cs2.length;i++) {
            try {
                Class<?> cls= Class.forName(cs2[i].trim());

                RequestMapping an = cls.getAnnotation(RequestMapping.class);
                Controller value = (Controller)cls.newInstance(); //얘네들이 Controller 상속받아서 가능
                String key = an.value(); //어노테이션에서 지정한 밸류값

                mapp.put(key, value);

            }
            catch(Exception e) {

            }
            System.out.println(mapp.toString());
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ctxPath = request.getContextPath();  //  /study
        String uri = request.getRequestURI();  //  /study/something.do

        uri = uri.substring(ctxPath.length());//  /something.do
        System.out.println(uri);

        Controller ctrl = mapp.get(uri);
        if(ctrl == null){
            System.out.println("해당 요청은 미등록입니다.");
            return;
        }

        try {
            String l = ctrl.handleRequest(request, response);
            if(l ==null){

            }else if(l.startsWith("redirect:")){//리다이렉트로 들어올 시 리다이렉트
                response.sendRedirect(ctxPath + l.substring(9)); //redirect: 해서 9
            }else{// 리다이렉트 아닐 시 포워드
                RequestDispatcher rd = request.getRequestDispatcher(l);
                rd.forward(request, response);
            }
        } catch (Exception e) {
            HttpSession session = request.getSession();
            session.setAttribute("err", e);
            e.printStackTrace();
            response.sendRedirect(ctxPath + "/error.jsp");
        }
    }

}