<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add</title>
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
    <h2 align="center">Add user</h2>
    <form action='${pageContext.servletContext.contextPath}/filteradd' method='post'>
    Login: <input type='text' name='login'/><br>
    Password: <input type='text' name='password'/><br>
    Email: <input type='text' name='email'/><br>
    <input type='submit' value='add'>
    </form>

</body>
</html>
