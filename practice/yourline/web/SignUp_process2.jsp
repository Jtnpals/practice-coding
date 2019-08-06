<%@ page contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@ page import="vo.UserVO"%>
<%@ page import="dao.UserDAO"%>
<%@ page import="dao.Util"%>
<%
	// 한글 깨짐을 방지하기 위한 인코딩 처리
	request.setCharacterEncoding("euc-kr");
	String ctxPath = request.getContextPath();

	// 회원 정보를 DB에 넣어주기 위해 입력 값들을 가져옴
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String nickName = request.getParameter("nickname");
	Integer sex = Util.parseInt(request.getParameter("sex"));
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");

	UserDAO dao = UserDAO.getInstance();
	//db에 회원정보 commit
	dao.insertUser("insert into usert values (seq_userno.nextval,?,?,?,?,?,?,?,?,?)", id, pw, email, phone,
			nickName, 0, 1000, sex, name);
	response.sendRedirect(ctxPath + "/Login.jsp");
%>

