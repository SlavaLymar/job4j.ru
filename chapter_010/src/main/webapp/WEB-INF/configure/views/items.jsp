<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>CREATE ITEM</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
        form[name=f1] {
            width: 300px;
            margin: auto;
        }
        table[name=t1]{
            width: 500px;
            text-align: center;
        }
        input[type=checkbox]{
            margin: 50px;
        }
    </style>

    <script type="text/javascript">

        function add() {
            if(validate()) {
                $.ajax('/items', {
                    method: 'post',
                    data: {
                        description: document.getElementsByName("description")[0].value
                    },
                    complete: function (data) {
                        var v = JSON.parse(data.responseText);
                    }
                });
                return false;
            }
            return false;
        }

        function validate() {
            var result = true;
            var v = document.getElementsByName("description")[0].value;
            if(v === ''){
                return result;
            }
            if(!result){
                alert("Please, enter description!");
            }
            return result;
        }

    </script>
</head>
<body>
<h2 align="center">CREATE ITEM</h2>

<table align="center">
    <tr>
        <td>Description: <input type='text' name='description'><br>
    </tr>
    <tr>
        <td><input type='button' value='create' onclick="return add();"></td>
    </tr>
</table>

<table align="center">
    <tr>
        <td>Show completed</td>
        <td><input type="checkbox" id="done" name="done" checked></td>
    </tr>
</table>


<table name="t1" id="t1" border="1" align="center">
    <h2 align="center">ITEMS</h2>
    <TR style="background-color: gray">
        <TD>ID</TD>
        <TD>DESCRIPTION</TD>
        <TD>DATE</TD>
        <TD>DONE</TD>
        <TD></TD>
        <TD></TD>
    </TR>
    <c:forEach items="${items}" var="item">
    <TR>
        <TD><c:out value="${item.id}"></c:out></TD>
        <TD><c:out value="${item.description}"></c:out></TD>
        <TD><fmt:formatDate type="both" value="${item.created}"/></TD>
        <TD>

        </TD>
        <TD>
            <form action='${pageContext.servletContext.contextPath}/edit' method='get'>
                <input type='submit' value='edit'>
                <input type='hidden' name='id' value='${item.id}'>
            </form>
        </TD>
        <TD>
            <form action='${pageContext.servletContext.contextPath}/delete' method='post'>
                <input type='submit' value='delete'>
                <input type='hidden' name='id' value='${item.id}'>
            </form>
        </TD>
    </TR>
    </c:forEach>

</body>
</html>
