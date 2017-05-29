<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit</title>
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
        input[type="submit"]{

        }
    </style>
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
