<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log IN</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
            var login = $('#login').val();
            var password = $('#password').val();
            if(login == '' || password == ''){
                alert("You must fill login and password!\nTry again.");
                result = false;
            }
            return result;
        }

        $(document).ready(function(){
            $('input').focus(function(){
                $(this).css("outline-color", "#FF0000");
            });
        });
    </script>

</head>
<body>
<h2 align="center">SIGN IN</h2>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"></c:out>
    </div>
</c:if>
<form id="ln" action='${pageContext.servletContext.contextPath}/ads.do' method='get' onsubmit="return validate()">

    Login: <input type='text' name='slogin' id="login"/><br>
    Password: <input type='password' name='spassword' id="password"/><br>
    <input type='submit' value="SIGN IN">

</form>
</body>
</html>
