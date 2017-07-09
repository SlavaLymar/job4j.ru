<%@ page import="ru.ru.yalymar.jsp.model.UserManager" %>
<%@ page import="ru.ru.yalymar.jsp.model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset=\"UTF-8\">
    <title>Users</title>
</head>
<body>
<table>
    <caption ALIGN=center>USERS</caption>
    <TR>
        <TD>ID</TD>
        <TD>NAME</TD>
        <TD>LOGIN</TD>
        <TD>EMAIL</TD>
        <TD>DATE OF CREATE</TD>
    </TR>
        <% UserManager userManager = new UserManager(); %>
        <% SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss"); %>
        <% for(User user : userManager.getAll()){ %>
    <TR>
        <TD><%= user.getId() %>
        </TD>
        <TD><%= user.getName() %>
        </TD>
        <TD><%= user.getLogin() %>
        </TD>
        <TD><%= user.getEmail() %>
        </TD>
        <TD><%= sdf.format(user.getCreateDate().getTime()) %>
        </TD>
        <TD>
            <form action='<%= request.getContextPath() %>/edit.jsp' method='get'>
                <input type='submit' value='edit'>
                <input type='hidden' name='id' value='<%= user.getId() %>'>
            </form>
        </TD>
        <TD>
            <form action='<%= request.getContextPath() %>/delete' method='post'>
                <input type='submit' value='delete'>
                <input type='hidden' name='id' value='<%= user.getId() %>'>
            </form>
        </TD>
    </TR>
        <% } %>

    <TD>
    <form action='<%= request.getContextPath() %>/add.jsp' method='get'>
            <input type='submit' value='+'>
    </form>
    </TD>
    </table>
</body>
</html>
