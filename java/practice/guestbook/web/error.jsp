<%@ page contentType="text/html;charset=UTF-8" language="java" %><%
    Exception err = (Exception)session.getAttribute("err");
    StackTraceElement[] errs = err.getStackTrace();
%>
<html>
<head>
    <title>Title</title>
</head>
<body><%
    for( int i = 0; i<errs.length ; i++){
  %><%=errs[i].toString()%><br/><%
}
%>

</body>
</html><%
    //session.removeAttribute("err");  //리로드시 다시 에러생길수 있으니.일단 주석
%>
