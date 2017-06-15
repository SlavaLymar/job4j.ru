<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset=\"UTF-8\">
    <title>Ads</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style>
        table {
            width: 100%;
        }
    </style>

    <script type="text/javascript">
        function inAd(id) {
            $.ajax( '/ad', {
                method: 'get',
                data: {
                    id: id
                }
            });
            return true;
        }
    </script>

</head>
<body>
<table border="1">
    <h2 align="center">Ads</h2>
    <TR style="background-color: gray">
        <TD>IMAGE</TD>
        <TD>TITLE</TD>
        <TD>MODEL</TD>
        <TD>PRICE</TD>
        <TD>DATE</TD>
        <TD>ACTUAL</TD>
    </TR>
    <c:forEach items="${ads}" var="ad" varStatus="i">
        <TR id="mainrow" onclick="return inAd(${ad.id});">
            <TD><img src="/../warehouse/myCar.jpg" alt=""></TD>
            <TD><c:out value="${ad.tittle}"></c:out></TD>

            <TD><c:out value="${ad.car}"></c:out></TD>

            <TD><c:out value="${ad.price}"></c:out></TD>
            <TD><fmt:formatDate type="both" value="${ad.create}"/></TD>
            <TD><c:out value="${ad.done}"></c:out></TD>

        </TR>
    </c:forEach>
</table>


<table border="1">
    <TD>
        <form action='${pageContext.servletContext.contextPath}/add' method='get'>
            <input type='submit' value='ADD'>
        </form>
    </TD>
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
