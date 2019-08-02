<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="l"%>
<html>
<head>
    <title>Title</title>

</head>
<body>
<div>
    <table border="1">
        <l:forEach var="vo" varStatus="vs" items="${rl}">
            <tr bgcolor="${(vs.count%2 != 0)?'#aabbcc':'#bbccdd'}">
                <td>${vs.count}</td>
                <td>${vo.commentNo}</td>
                <td>${vo.content}</td>
                <td>${vo.updateDate}</td>
                <td><a href="guestbook_del.do?comment_no=${vo.commentNo}">삭제</a></td>
            </tr>
        </l:forEach>
    </table>
    <form method="post" action="guestbook_add.do">
        <input type="text" name="content"/>
        <input type="submit"/>
    </form>
</div>
</body>
</html>
