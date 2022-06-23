<%@ page import="com.urline.dao.UserDAO" %>
<%@ page contentType="text/html; charset=utf-8"%>
<title>ID 중복확인</title>
<%
    //한글 깨짐을 방지하기 위한 인코딩 처리
    request.setCharacterEncoding("euc-kr");

    String id = request.getParameter("id");
    UserDAO dao = UserDAO.getInstance();
    int check = dao.confrimId(id);

    if (check == -1) {
%>
<b><font color="red"><%=id%>
</font>는 이미 사용중인 아이디입니다.</b>
<form name="checkForm" method="POST" action="ConfirmId.jsp">
    <b>다른 아이디를 입력해주세요.</b><br/><br/>
    <input type="text" name="id"/>
    <input type="submit" value="ID중복확인"/>
</form>
<%
} else {
%>
<center>
    <b>입력하신 <font color="red"><%=id%>
    </font>는<br/>사용하실 수 있는 ID입니다. </b><br/><br/>
    <input type="button" value="선택하기" onclick="setId()">
</center>
<%
    }
%>
<script language="javascript">
    function setId() {

        opener.document.userInfo.id.value = "<%=id%>";
        opener.document.userInfo.confirm_id.disabled = 'disabled';
        opener.document.userInfo.confirm_id.value = "사용가능";
        opener.document.userInfo.id.disabled = 'disabled';
        self.close();
        //1. 중복확인 버튼 비활성화 시키기 , id입력란 비활성화
        //2. 그리고 입력값들 다 입력이 들어가간 상태 + 중복검사버튼 비활성화되면 가입하기 버튼 활성화되기..!
        //3. 이름 입력란에는 한글이름 3글자만 받도록, 닉네임은 자유, 핸드폰 번호는 딱 11자이면서 앞에는 010만 받도록, 이메일으로 입력되도록
        //		아이디, 비밀번호는 8자리 이상 , 아이디는 영어또는숫자로만(첫글자는 영어로시작)

    }
</script>