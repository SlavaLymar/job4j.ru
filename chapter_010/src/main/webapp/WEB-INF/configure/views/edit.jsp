<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Edit</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script type="text/javascript">

        function edit() {
            $.ajax('/edit', {
                method: 'post',
                data: {
                    id: document.getElementsByName("id")[0].value,
                    tabledesc: document.getElementsByName("desc")[0].value,
                    tabledone: document.getElementsByName("done")[0].value
                },
                complete: function (data) {
                    var u = JSON.parse(data.responseText);
                    var desc = document.getElementsByClassName("descclass")[0];
                    desc.innerHTML = u.description;
                    var done = document.getElementsByClassName("doneclass")[0];
                    done.innerHTML = u.done;
                    return false;
                }
            });
            return false;
        }

    </script>

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

        table {
            width: 100%;
        }
    </style>

</head>
<body>
<h2 align="center">Edit</h2>
<form action='${pageContext.servletContext.contextPath}/edit' method='post'>
    Description: <input type='text' id='desc' name='desc' value='${item.description}'/><br>
    Done: <input type='checkbox' name='done' value='${item.done}'/><br>

    <input type='hidden' name='id' value='${item.id}'>
    <input type='button' value='ajax' onclick="return edit();">

</form>

<table id="usertable" border="1">
    <TR style="background-color: gray">
        <TD>ID</TD>
        <TD>DESCRIPTION</TD>
        <TD>DATE</TD>
        <TD>DONE</TD>
    </TR>
    <TR>
        <TD class="idclass"><c:out value="${item.id}"></c:out></TD>
        <TD class="descclass"><c:out value="${item.description}"></c:out></TD>
        <TD><fmt:formatDate type="both" value="${item.created}"/>
        <TD class="doneclass"><c:out value="${item.done}"></c:out></TD>
    </TR>

</table>

</body>
</html>
