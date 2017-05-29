<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset=\"UTF-8\">
    <title>Users</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <style>
        body {
            background-color : #f9fff9;
            margin: 0;
            padding: 0;
        }
        h1 {
            color : #000000;
            text-align : center;
        }
        form {
            width: 300px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<table border="1">
    <h2 align="center">User</h2>
    <TR style="background-color: gray">
        <TD>ID</TD>
        <TD>LOGIN</TD>
        <TD>PASSWORD</TD>
        <TD>EMAIL</TD>
        <TD>DATE OF CREATE</TD>
    </TR>
        <TR>
            <TD><c:out value="${user.id}"></c:out></TD>
            <TD><c:out value="${user.login}"></c:out></TD>
            <TD><c:out value="${user.password}"></c:out></TD>
            <TD><c:out value="${user.email}"></c:out></TD>
            <TD><fmt:formatDate type="both" value="${user.createDate.time}"/><TD>
            <form action='${pageContext.servletContext.contextPath}/edituser' method='get'>
                <input type='submit' value='edit'>
                <input type='hidden' name='id' value='${user.id}'>
            </form>
        </TD>
        </TR>

</table>
<table border="1">
    <TD>
        <form action='${pageContext.servletContext.contextPath}/exit' method='post'>
            <input type='submit' value='EXIT'>
        </form>
    </TD>
</table>
<c:out value="You was signed in as ${user.role}"></c:out>
</body>
</html>
