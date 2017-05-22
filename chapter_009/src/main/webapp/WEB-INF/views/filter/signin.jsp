<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SIGN IN</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"></c:out>
    </div>
</c:if>
<form action='${pageContext.servletContext.contextPath}/signin' method='post'>
    Login: <input type='text' name='login'/><br>
    Password: <input type='password' name='password'/><br>
    <input type='submit' value="SIGN IN">
</form>
</body>
</html>
