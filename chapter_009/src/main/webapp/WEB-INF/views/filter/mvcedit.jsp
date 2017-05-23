<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<form action='${pageContext.servletContext.contextPath}/mvcedit' method='post'>
    Login: <input type='text' name='newlogin' value='${user.login}'/><br>
    Password: <input type='text' name='newpassword' value='${user.password}'/><br>
    Email: <input type='text' name='email' value='${user.email}'/><br>
    Role: <select name="role" size="1">
        <option value="admin">admin</option>
        <option value="user">user</option>
    </select><br>
    <input type='submit' value='edit'>
    <input type='hidden' name='id' value='${user.id}'>
    <input type='hidden' name='oldlogin' value='${user.login}'>
    <input type='hidden' name='oldpassword' value='${user.password}'>
</form>
</body>
</html>
