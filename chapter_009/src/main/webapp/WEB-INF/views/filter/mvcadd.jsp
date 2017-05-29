<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f9fff9;
            margin: 0;
            padding: 0;
        }

        h1 {
            color: #000000;
            text-align: center;
        }

        form {
            width: 300px;
            margin: 0 auto;
        }
    </style>

    <script type="text/javascript">
        function validate() {
            var result = true;
            var login = document.getElementById("login").value;
            var password = document.getElementById("password").value;
            var email = document.getElementById("email").value;
            if (login === '') {
                alert("You must fill login!\nTry again.");
                result = false;
            }
            else if (password === '') {
                alert("You must fill password!\nTry again.");
                result = false;
            }
            else if (email === '') {
                alert("You must fill email!\nTry again.");
                result = false;
            }
            return result;
        }

        cities = new Array();
        cities['Russia'] = new Array('Moscow', 'Saint-Petersburg', 'Omsk');
        cities['India'] = new Array('Banglapoor', 'Mumbai');
        cities['China'] = new Array('Beijing', 'Hong Kong');

        function changeSelect(fieldID, newOptions, newValues) {
            var selectField = document.getElementById(fieldID);
            selectField.length = 0;
            for(var i = 0; i<newOptions.length; i++){
                selectField.options[selectField.length] = new Option(newOptions[i], newValues[i]);
            }
        }

        function setCities() {
            var cntrySel = document.getElementById("country").value;
            var citiesList = cities[cntrySel];
            changeSelect('city', citiesList, citiesList);
        }

    </script>
</head>
<body>
<h2 align="center">Add user</h2>
<form action='${pageContext.servletContext.contextPath}/filteradd' method='post' onsubmit="return validate()">
    Login: <input type='text' name='login' id="login"/><br>
    Password: <input type='text' name='password' id="password"/><br>
    Email: <input type='text' name='email' id="email"/><br>
    Country: <select name="country" size="1" id="country" onchange="setCities()">
    <option selected="selected">Please select a country</option>
    <option value="Russia">Russia</option>
    <option value="India">India</option>
    <option value="China">China</option>
</select><br>

    City: <select name="city" size="1" id="city">
    <option value="">Please select a country</option>
</select><br>

    <input type='submit' value='add'>
</form>

</body>
</html>
