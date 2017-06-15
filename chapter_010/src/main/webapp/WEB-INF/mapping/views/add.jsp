<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Add</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script type="text/javascript">

        $(document).ready(function (){
                $('#manufac').on('change', function () {
                    var manufId = $('#manufac').val();
                    var modelsList = [];
                    <c:forEach items="${models}" var="model">
                        var manuf = ${model.manuf.id};
                        if(manuf == manufId){
                            modelsList.push("${model}");
                        }
                    </c:forEach>

                    var selectField = document.getElementById("model");
                    selectField.length = 0;
                    for (var i = 0; i < modelsList.length; i++) {
                        selectField.options[selectField.length] =
                            new Option(${modelsList[i].model}, ${modelsList[i].id});
                    }
                    var modelsList = [];
                });
            });

    </script>
</head>
<body>

    <h1>ADD</h1>
    <form action='${pageContext.servletContext.contextPath}/add' method='post'>
        Title: <input type="text" name="title" /><br>

        Manufactor:
        <select name="manuf" id="manufac" size="1" >
            <c:forEach items="${manufacturers}" var="manufactor">
                    <option value="${manufactor.id}">${manufactor.manuf}</option>
            </c:forEach>
        </select><br>

        Model:
        <select name="model" id="model" size="1">
            <option value="model">Choose manufactor</option>
        </select><br>

        Transmisson:
        <select name="transmission" id="transmission" size="1" >
            <c:forEach items="${transmissions}" var="body">
                <option value="${transmission.id}">${transmission.name}</option>
            </c:forEach>
        </select><br>

        Body:
        <select name="body" id="body" size="1" >
            <c:forEach items="${bodies}" var="body">
                    <option value="${body.id}">${body.body}</option>
            </c:forEach>
        </select><br>

        Color:
        <select name="color" id="color" size="1" >
            <c:forEach items="${colours}" var="color">
                <option value="${color.id}">${color.color}</option>
            </c:forEach>
        </select><br>

        Price: <input type="text" name="price" /><br>
        Upload images:
        <div class="files">
            <input type="file" multiple/>
        </div>

        <input type="submit" value="add"/>

    </form>

    <form action='${pageContext.servletContext.contextPath}/ads' method='get'>
        <input type="submit" value="cancel"/>
        <input type="hidden" name="id" value="${userId}">
    </form>
</body>
</html>
