<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset=\"UTF-8\">
    <title>Users</title>
</head>
<body>
<table>
    <caption ALIGN=center>USERS</caption>
    <TR>
        <TD>ID</TD>
        <TD>NAME</TD>
        <TD>LOGIN</TD>
        <TD>EMAIL</TD>
        <TD>DATE OF CREATE</TD>
    </TR>
        <c:forEach items="${users}" var="user">
    <TR>
        <TD><c:out value="${user.id}"></c:out></TD>
        <TD><c:out value="${user.name}"></c:out></TD>
        <TD><c:out value="${user.login}"></c:out></TD>
        <TD><c:out value="${user.email}"></c:out></TD>
        <TD><fmt:formatDate type="both" value="${user.createDate.time}"/><TD>
            <form action='${pageContext.servletContext.contextPath}/mvcedit' method='get'>
                <input type='submit' value='edit'>
                <input type='hidden' name='id' value='${user.id}'>
            </form>
        </TD>
        <TD>
            <form action='${pageContext.servletContext.contextPath}/mvcdelete' method='post'>
                <input type='submit' value='delete'>
                <input type='hidden' name='id' value='${user.id}'>
            </form>
        </TD>
    </TR>
        </c:forEach>

    <TD>
    <form action='${pageContext.servletContext.contextPath}/mvcadd' method='get'>
            <input type='submit' value='+'>
    </form>
    </TD>
    </table>
</body>
</html>
