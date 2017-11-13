<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#manufac').on('change', function () {
                var manufId = $('#manufac').val();
                var modelsList = [];
                var modelListId = [];
                <c:forEach items="${models}" var="model">
                var manuf = ${model.manuf.id};
                if (manuf == manufId) {
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
        $(document).ready(function () {
            $('input').focus(function () {
                $(this).css("outline-color", "#FF0000");
            });
        });
    </script>
</head>
<body>

<h1>ADD</h1>
<spring:form method='post' action='${pageContext.servletContext.contextPath}/add.do' enctype="multipart/form-data">
    Title:
    <input type="text" name="title"/><br>

    Manufactor:
    <select name="manuf" id="manufac" size="1">
        <option selected="selected">Choose manufactor</option>
        <c:forEach items="${manufacturers}" var="manufactor">
            <option value="${manufactor.id}">${manufactor.manuf}</option>
        </c:forEach>
    </select><br>

    Model:
    <select name="model" id="model" size="1">
        <option selected="selected" value="">Choose manufactor</option>
    </select><br>

    Transmisson:
    <select name="transmission" id="transmission" size="1">
        <c:forEach items="${transmissions}" var="transmission">
            <option value="${transmission.id}">${transmission.name}</option>
        </c:forEach>
    </select><br>

    Body:
    <select name="body" id="body" size="1">
        <c:forEach items="${bodies}" var="body">
            <option value="${body.id}">${body.body}</option>
        </c:forEach>
    </select><br>

    Color:
    <select name="color" id="color" size="1">
        <c:forEach items="${colours}" var="color">
            <option value="${color.id}">${color.color}</option>
        </c:forEach>
    </select><br>

    Price: <input type="text" name="price"/><br>

    File to upload: <input type="file" name="upfiles"><br/>
    <br/>
    <input type="submit" value="add">
</spring:form>

<form action='${pageContext.servletContext.contextPath}/ads.do' method='get'>
    <input type="submit" value="cancel"/>
    <input type="hidden" name="id" value="${userId}">
</form>
</body>
</html>