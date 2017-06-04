<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SIGN IN</title>
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

    <script>
        function validate(){
            var result = true;
            var login = document.getElementById("login").value;
            var password = document.getElementById("password").value;
            if(login == '' || password == ''){
                alert("You must fill login and password!\nTry again.");
                result = false;
            }
            return result;
        }

    </script>
</head>
<body>
<h2 align="center">SIGN IN</h2>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"></c:out>
    </div>
</c:if>
<form id="sn" action='${pageContext.servletContext.contextPath}/testtask' method='post' onsubmit="return validate()">

    Login: <input type='text' name='slogin' id="login"/><br>
    Password: <input type='password' name='spassword' id="password"/><br>
    <input type='submit' value="SIGN IN">

</form>
</body>
</html>
