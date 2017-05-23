<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<form action='${pageContext.servletContext.contextPath}/edituser' method='post'>
    Login: <input type='text' name='newlogin' value='${user.login}'/><br>
    Password: <input type='text' name='newpassword' value='${user.password}'/><br>
    Email: <input type='text' name='email' value='${user.email}'/><br>
    <input type='submit' value='edit'>
    <input type='hidden' name='id' value='${user.id}'>
    <input type='hidden' name='login' value='${user.login}'>
    <input type='hidden' name='password' value='${user.password}'>
</form>
</body>
</html>
