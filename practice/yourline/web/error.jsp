<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	Exception e = (Exception) session.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<body>
	<%
		StackTraceElement[] st = e.getStackTrace();
		for (int i = 0; i < st.length; i++) {
	%>
	<%=st[i]%>
	<%
		}
	%>
</body>
</html>