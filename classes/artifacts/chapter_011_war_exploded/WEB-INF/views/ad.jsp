<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset=\"UTF-8\">
    <title>Ad</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style>
        table {
            width: 100%;
        }
    </style>
    <script type="text/javascript">

        $(document).ready(function () {
            if((!${isAdmin}) && (${user.id != ad.user.id})){
                $('.privatetable').hide();
            }
        });

        function del(id){
            $.ajax('/delete', {
                method: 'post',
                data: {
                    id: id
                },
                complete: function () {
                    document.location.href = "${pageContext.servletContext.contextPath}/ads.do";
                }
            });
            return false;
        }

        function cancel() {
            document.location.href = "${pageContext.servletContext.contextPath}/ads.do";
            return false;
        }
    </script>

</head>
<body>
    <h1><c:out value="${ad.tittle}"></c:out></h1>
    <table id="imgs" border="1">
        <c:forEach items="${ad.images}" var="image">
            <img src="${image.url}" alt="">
        </c:forEach>
    </table>

<table id="desc" border="1">
    <TR><TD>MODEL: <c:out value="${m} ${model}"></c:out></TD></TR>
    <TR><TD>TRANSMISSION: <c:out value="${transmission}"></c:out></TD></TR>
    <TR><TD>BODY: <c:out value="${body}"></c:out></TD></TR>
    <TR><TD>COLOR: <c:out value="${color}"></c:out></TD></TR>
    <TR><TD>PRICE: <c:out value="${ad.price}"></c:out></TD></TR>
    <TR><TD>STATUS: <c:out value="${ad.done}"></c:out></TD></TR>
</table>

    <table class="privatetable">
        <TD>
            <form action='${pageContext.servletContext.contextPath}/editad.do' method='get'>
                <input type='submit' value='edit'>
                <input type="hidden" name="id" value="${ad.id}">
            </form>
        </TD>
        <TD>
            <input type='button' id="delete" value='delete' onclick="del(${ad.id})">
        </TD>
    </table>

<table border="1">
    <div id="cancel">
        <input type="button" value="cancel" onclick="cancel()"/>
    </div>
</table>

    <p><c:out value="You was signed in as ${role}"></c:out></p>
</body>
</html>
