<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset=\"UTF-8\">
    <title>Edit</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style>
        table {
            width: 100%;
        }
        .red {
            background-color: #CC0000;
            background-image: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#330000), to(#CC0000));
            background-image: -webkit-linear-gradient(left, #330000, #CC0000);
            background-image:    -moz-linear-gradient(left, #330000, #CC0000);
            background-image:     -ms-linear-gradient(left, #330000, #CC0000);
            background-image:      -o-linear-gradient(left, #330000, #CC0000);
        }
    </style>
    <script type="text/javascript">
        function cancel() {
            document.location.href = "${pageContext.servletContext.contextPath}/ads";
            return false;
        }

        function deleteImg(id) {
            $.ajax('/imgdel', {
                method: 'post',
                data: {
                    id: id
                },
                complete: function () {
                    document.location.href = "${pageContext.servletContext.contextPath}/ads";
                }
            });
            return false;
        }

        $(document).ready(function(){
            $('input').focus(function(){
                $(this).css("outline-color", "#FF0000");
            });
        });

        $(document).ready(function (){
            $('#manufac').on('change', function () {
                var manufId = $('#manufac').val();
                var modelsList = [];
                var modelListId = []
                <c:forEach items="${models}" var="model">
                var manuf = ${model.manuf.id};
                if(manuf == manufId){
                    modelsList.push("${model.model}");
                    modelListId.push(${model.id});
                }
                </c:forEach>

                var selectField = document.getElementById("model");
                selectField.length = 0;
                for (var i = 0; i < modelsList.length; i++) {
                    selectField.options[selectField.length] =
                        new Option(modelsList[i], modelListId[i]);
                }
                var modelsList = [];
            });
        });
    </script>

</head>
<body>

<h1>EDIT</h1>
<form enctype="multipart/form-data"
      action='${pageContext.servletContext.contextPath}/add' method='post'>
    Title: <input type="text" name="title" value="${ad.tittle}"/><br>

    Manufactor:
    <select name="manuf" id="manufac" size="1" >
        <option selected="selected">${ad.car.model.manuf.manuf}</option>
        <c:forEach items="${manufacturers}" var="manufactor">
            <option value="${manufactor.id}">${manufactor.manuf}</option>
        </c:forEach>
    </select><br>

    Model:
    <select name="model" id="model" size="1">
        <option selected="selected">${ad.car.model.model}</option>
        <option value="model">Choose manufactor</option>
    </select><br>

    Transmisson:
    <select name="transmission" id="transmission" size="1" >
        <option selected="selected">${ad.car.transmission.name}</option>
        <c:forEach items="${transmissions}" var="transmission">
            <option value="${transmission.id}">${transmission.name}</option>
        </c:forEach>
    </select><br>

    Body:
    <select name="body" id="body" size="1" >
        <option selected="selected">${ad.car.transmission.name}</option>
        <c:forEach items="${bodies}" var="body">
            <option value="${body.id}">${body.body}</option>
        </c:forEach>
    </select><br>

    Color:
    <select name="color" id="color" size="1" >
        <option selected="selected">${ad.car.color.color}</option>
        <c:forEach items="${colours}" var="color">
            <option value="${color.id}">${color.color}</option>
        </c:forEach>
    </select><br>

    Price: <input type="text" name="price" value="${ad.price}"/><br>

    Current images:
    <table>
        <c:forEach items="${images}" var="image">
            <tr>
                <td><img src="${image.url}"></td>
                <td><input type="button" value="delete" onclick="deleteImg(${image.id})"></td>
            </tr>
        </c:forEach>
    </table>
    File to upload: <input type="file" name="upfiles" multiple="multiple"><br/>
    <br/>
    <input type="submit" value="add">
</form>

    <table>
        <TD>
            <form action='${pageContext.servletContext.contextPath}/edit' method='get'>
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
