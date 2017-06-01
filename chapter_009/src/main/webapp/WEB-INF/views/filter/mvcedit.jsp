<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Edit</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <script type="text/javascript">

        function upd() {
            $.ajax( '/json', {
                method: 'get',
                data: {
                    id: ${user.id}
                },
                complete: function(data){
                    var u = JSON.parse(data.responseText);
                    var login = document.getElementsByClassName("loginclass")[0];
                    login.innerHTML = u.login;
                    var pwd = document.getElementsByClassName("pwd")[0];
                    pwd.innerHTML = u.password;
                    var email = document.getElementsByClassName("emailclass")[0];
                    email.innerHTML = u.email;
                    var country = document.getElementsByClassName("countryclass")[0];
                    country.innerHTML = u.country;
                    var city = document.getElementsByClassName("cityclass")[0];
                    city.innerHTML = u.city;
                    return false;
                }
            });

            return false;
        }

        function edit() {
            $.ajax('/json', {
                method: 'post',
                data: {
                    id: document.getElementsByName("id")[0].value,
                    newlogin: document.getElementById("newlogin").value,
                    newpassword: document.getElementsByName("newpassword")[0].value,
                    email: document.getElementsByName("email")[0].value,
                    country: document.getElementsByName("country")[0].value,
                    city: document.getElementsByName("city")[0].value
                },
                complete: upd()
            });
            return false;
        }

        cities = new Array();
        cities['Russia'] = new Array('Moscow', 'Saint-Petersburg', 'Omsk');
        cities['India'] = new Array('Banglapoor', 'Mumbai');
        cities['China'] = new Array('Beijing', 'Hong Kong');

        function changeSelect(fieldID, newOptions, newValues) {
            var selectField = document.getElementById(fieldID);
            selectField.length = 0;
            for (var i = 0; i < newOptions.length; i++) {
                selectField.options[selectField.length] = new Option(newOptions[i], newValues[i]);
            }
        }

        function setCities() {
            var cntrySel = document.getElementById("country").value;
            var citiesList = cities[cntrySel];
            changeSelect('city', citiesList, citiesList);
        }

    </script>

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
        table{
            width: 100%;
        }
    </style>

</head>
<body>
<h2 align="center">Edit user</h2>
<form action='${pageContext.servletContext.contextPath}/mvcedit' method='post'>
    Login: <input type='text' id="newlogin" name='newlogin' value='${user.login}'/><br>
    Password: <input type='text' name='newpassword' value='${user.password}'/><br>
    Email: <input type='text' name='email' value='${user.email}'/><br>
    Role: <select name="role" size="1">
        <option value="admin">admin</option>
        <option value="user">user</option>
    </select><br>

    Country: <select name="country" size="1" id="country" onchange="setCities()">
    <option selected="selected">${user.country}</option>
    <option value="Russia">Russia</option>
    <option value="India">India</option>
    <option value="China">China</option>
</select><br>

    City: <select name="city" size="1" id="city">
    <option value="">${user.city}</option>
</select><br>
    <input type='hidden' name='id' value='${user.id}'>
    <input type='hidden' name='oldlogin' value='${user.login}'>
    <input type='hidden' name='oldpassword' value='${user.password}'>
    <input type='submit' value='edit' onclick="return user();">
    <input type='button' value='ajax' onclick="return edit();">

</form>

<table id="usertable" border="1">
    <TR style="background-color: gray">
        <TD>ID</TD>
        <TD>LOGIN</TD>
        <TD>PASSWORD</TD>
        <TD>EMAIL</TD>
        <TD>DATE OF CREATE</TD>
        <TD>COUNTRY</TD>
        <TD>CITY</TD>
    </TR>
    <TR>
        <TD class="idclass"><c:out value="${user.id}"></c:out></TD>
        <TD class="loginclass"><c:out value="${user.login}"></c:out></TD>
        <TD class="pwd"><c:out value="${user.password}"></c:out></TD>
        <TD class="emailclass"><c:out value="${user.email}"></c:out></TD>
        <TD><fmt:formatDate type="both" value="${user.createDate.time}"/>
        <TD class="countryclass"><c:out value="${user.country}"></c:out></TD>
        <TD class="cityclass"><c:out value="${user.city}"></c:out></TD>
    </TR>

</table>

</body>
</html>
