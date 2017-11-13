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
        .active {
            background-color: #2fffee;
        }

    </style>

    <script type="text/javascript">
        function inAd(id) {
            document.location.href = "${pageContext.servletContext.contextPath}/ad.do?id=" + id;
            return true;
        }

        $(document).ready(function () {
            $('tr').hover(
                function () {
                $(this).addClass('active');
            },
                function () {
                $(this).removeClass('active');
            })
        });

        $(document).ready(function (){
            $('#fmanufac').on('change', function () {
                var manufId = $('#fmanufac').val();
                if(manufId == 'Choose manufactor'){
                    var selectField = document.getElementById("fmodel");
                    selectField.length = 0;
                    selectField.options[selectField.length] =
                        new Option("Choose manufactor", "Choose manufactor");
                    return;
                }
                var modelsList = [];
                var modelListId = [];
                <c:forEach items="${models}" var="model">
                    var manuf = '${model.manuf.manuf}';
                    if(manuf == manufId){
                        modelsList.push("${model.model}");
                        modelListId.push(${model.model});
                    }
                </c:forEach>

                var selectField = document.getElementById("fmodel");
                selectField.length = 0;
                var el = document.createElement("option");
                el.textContent = "Choose manufactor";
                el.value = "";
                el.selected = true;
                selectField.appendChild(el);

                for (var i = 0; i < modelsList.length; i++) {
                    selectField.options[selectField.length] =
                        new Option(modelsList[i], modelListId[i]);
                }

                var modelsList = [];
            });
        });


        $(document).ready(function () {
            $('select').on('change', function () {
                $.ajax('/adfilter', {
                    method: 'post',
                    data: {
                        fmanuf: $('#fmanufac').val(),
                        fmodel: $('#fmodel').val(),
                        ftransmission: $('#ftransmission').val(),
                        fbody: $('#fbody').val(),
                        fcolor: $('#fcolor').val(),
                        from: $('#from').val(),
                        to: $('#to').val()
                    },
                    complete: function (data) {
                        var j = JSON.parse(data.responseText);
                        if(j.id.length === 0){
                            alert("Didn`t find anything! Try again!");
                            return;
                        }
                        <c:forEach items="${ads}" var="ad">
                            for(var i = 0; i<j.id.length; i++){
                                if(j.id[i] === ${ad.id}){
                                    $('#mainrow${ad.id}').show('fast');
                                    break;
                                }
                                else {
                                    $('#mainrow${ad.id}').hide();
                                }
                            }
                        </c:forEach>
                    }
                })
            });
        });
    </script>

</head>
<body>
<h2 align="center">Ads</h2>

<h3>Filters</h3>

    Manufactor:
    <select name="fmanuf" id="fmanufac" size="1" >
        <option selected="selected" value="">Choose manufactor</option>
        <c:forEach items="${manufacturers}" var="manufactor">
            <option value="${manufactor.manuf}">${manufactor.manuf}</option>
        </c:forEach>
    </select><br>

    Model:
    <select name="fmodel" id="fmodel" size="1">
        <option value="">Choose manufactor</option>
    </select><br>

    Transmisson:
    <select name="ftransmission" id="ftransmission" size="1" >
        <option value="" selected="selected">Choose transmission</option>
        <c:forEach items="${transmissions}" var="transmission">
            <option value="${transmission.name}">${transmission.name}</option>
        </c:forEach>
    </select><br>

    Body:
    <select name="fbody" id="fbody" size="1" >
        <option value="" selected="selected">Choose Body</option>
        <c:forEach items="${bodies}" var="body">
            <option value="${body.body}">${body.body}</option>
        </c:forEach>
    </select><br>

    Color:
    <select name="fcolor" id="fcolor" size="1" >
        <option value="" selected="selected">Choose Color</option>
        <c:forEach items="${colours}" var="color">
            <option value="${color.color}">${color.color}</option>
        </c:forEach>
    </select><br>

    Price: from <input type="text" id="from" name="from" value=""/> - to
                    <input type="text" id="to" name="to" value=""/><br>
    <br/>


<table border="1">
    <TR style="background-color: gray">
        <TD>IMAGE</TD>
        <TD>TITLE</TD>
        <TD>MODEL</TD>
        <TD>PRICE</TD>
        <TD>DATE</TD>
        <TD>ACTUAL</TD>
    </TR>
    <div id="rows">
    <c:forEach items="${ads}" var="ad">
        <TR id="mainrow${ad.id}" onclick="return inAd(${ad.id});">

            <c:forEach items="${ad.images}" var="image" varStatus="loop">
                <c:if test="${loop.index == 0}">
                    <TD>
                        <img src="${image.url}" alt="1" style="width:150px;height:150px;">
                    </TD>
                </c:if>
            </c:forEach>

            <TD><c:out value="${ad.tittle}"></c:out></TD>

            <c:forEach items="${desc}" var="d">
                <c:if test="${d.key == ad.id}">
                    <TD><c:out value="${d.value.get(0)} ${d.value.get(1)}"></c:out></TD>
                </c:if>
            </c:forEach>


            <TD><c:out value="${ad.price}"></c:out></TD>
            <TD><fmt:formatDate type="both" value="${ad.create}"/></TD>
            <TD><c:out value="${ad.done}"></c:out></TD>

        </TR>
    </c:forEach>
    </div>
</table>


<table border="1">
    <TD>
        <form action='${pageContext.servletContext.contextPath}/add.do' method='get'>
            <input type='submit' value='ADD'>
        </form>
    </TD>
</table>
<table border="1">
    <TD>
        <form action='${pageContext.servletContext.contextPath}/exit.do' method='post'>
            <input type='submit' value='EXIT'>
        </form>
    </TD>
</table>
    <c:out value="You was signed in as ${role}"></c:out>
</body>
</html>
