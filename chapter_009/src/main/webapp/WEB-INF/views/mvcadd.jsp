<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
    <form action='${pageContext.servletContext.contextPath}/mvcadd' method='post'>
    Name: <input type='text' name='name'/>
    Login: <input type='text' name='login'/>
    Email: <input type='text' name='email'/>
    <input type='submit' value='add'>
    </form>

</body>
</html>
