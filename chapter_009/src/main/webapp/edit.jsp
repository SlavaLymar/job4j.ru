<%@ page import="ru.ru.yalymar.jsp.model.UserManager" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="ru.ru.yalymar.jsp.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
    <% UserManager userManager = new UserManager(); %>
    <% SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss"); %>
    <% User user = userManager.get(request.getParameter("id")); %>


    <form action='<%= request.getContextPath() %>/edit' method='post'>
    Name: <input type='text' name='name' value='<%= user.getName() %>'/>
    Login: <input type='text' name='login' value='<%= user.getLogin() %>'/>
    Email: <input type='text' name='email' value='<%= user.getEmail() %>'/>
    <input type='submit' value='edit'>
    <input type='hidden' name='id' value='<%= request.getParameter("id") %>'>
    </form>
</body>
</html>
