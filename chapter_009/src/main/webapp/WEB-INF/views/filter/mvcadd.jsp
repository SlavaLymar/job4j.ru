<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
    <form action='${pageContext.servletContext.contextPath}/filteradd' method='post'>
    Login: <input type='text' name='login'/><br>
    Password: <input type='text' name='password'/><br>
    Email: <input type='text' name='email'/><br>
    <input type='submit' value='add'>
    </form>

</body>
</html>
