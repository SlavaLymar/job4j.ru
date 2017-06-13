<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset=\"UTF-8\">
    <title>Ads</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        table{
            width: 100%;
        }
    </style>
</head>
<body>
<table border="1">
    <h2 align="center">Ads</h2>
    <TR style="background-color: gray">
        <TD>IMAGE</TD>
        <TD>TITLE</TD>
        <TD>MODEL</TD>
        <TD>DATE</TD>
        <TD>ACTUAL</TD>
    </TR>
        <c:forEach items="${ads}" var="ad" varStatus="i">
    <TR>
        <TD><c:out value="${ad.images[0]}"></c:out></TD>
        <TD><c:out value="${ad.title}"></c:out></TD>
        <TD><c:out value="${ad.model}"></c:out></TD>
        <TD><fmt:formatDate type="both" value="${ad.create}"/></TD>
        <TD><c:out value="${ad.done}"></c:out></TD>
        <TD>
            <form action='${pageContext.servletContext.contextPath}/edit' method='get'>
                <input type='submit' value='edit'>
                <input type='hidden' name='id' value='${ad.id}'>
            </form>
        </TD>
        <TD>
            <form action='${pageContext.servletContext.contextPath}/delete' method='post'>
                <input type='submit' value='delete'>
                <input type='hidden' name='id' value='${ad.id}'>
            </form>
        </TD>
    </TR>
        </c:forEach>

    </table>
    <table border="1">
        <TD>
            <form action='${pageContext.servletContext.contextPath}/exit' method='post'>
                <input type='submit' value='EXIT'>
            </form>
        </TD>
    </table>
    <c:out value="You was signed in as ${role}"></c:out>
</body>
</html>
