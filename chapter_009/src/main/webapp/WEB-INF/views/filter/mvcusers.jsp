<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset=\"UTF-8\">
    <title>Users</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        table{
            width: 100%;
        }
    </style>
</head>
<body>
<table border="1">
    <h2 align="center">Users</h2>
    <TR style="background-color: gray">
        <TD>ID</TD>
        <TD>LOGIN</TD>
        <TD>PASSWORD</TD>
        <TD>EMAIL</TD>
        <TD>DATE OF CREATE</TD>
        <TD>COUNTRY</TD>
        <TD>CITY</TD>
        <TD>ROLE</TD>
    </TR>
        <c:forEach items="${users}" var="user">
    <TR>
        <TD><c:out value="${user.id}"></c:out></TD>
        <TD><c:out value="${user.login}"></c:out></TD>
        <TD><c:out value="${user.password}"></c:out></TD>
        <TD><c:out value="${user.email}"></c:out></TD>
        <TD><fmt:formatDate type="both" value="${user.createDate.time}"/></TD>
        <TD><c:out value="${user.country}"></c:out></TD>
        <TD><c:out value="${user.city}"></c:out></TD>
        <TD><c:out value="${user.role}"></c:out></TD>
        <TD>
            <form action='${pageContext.servletContext.contextPath}/filteredit' method='get'>
                <input type='submit' value='edit'>
                <input type='hidden' name='id' value='${user.id}'>
            </form>
        </TD>
        <TD>
            <form action='${pageContext.servletContext.contextPath}/filterdelete' method='post'>
                <input type='submit' value='delete'>
                <input type='hidden' name='id' value='${user.id}'>
            </form>
        </TD>
    </TR>
        </c:forEach>

    <TD>
    <form action='${pageContext.servletContext.contextPath}/filteradd' method='get'>
            <input type='submit' value='+'>
    </form>
    </TD>
    </table>
    <table border="1">
        <TD>
            <form action='${pageContext.servletContext.contextPath}/exit' method='post'>
                <input type='submit' value='EXIT'>
            </form>
        </TD>
    </table>
    <c:out value="You was signed in as ${role}"></c:out>
</body>
</html>
