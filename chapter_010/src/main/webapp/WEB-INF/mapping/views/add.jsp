<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script type="text/javascript">


    </script>
</head>
<body>
    <h1>ADD</h1>
    <form action='${pageContext.servletContext.contextPath}/add' method='post'>
        Title: <input type="text" name="title" /><<br>
        Manufactor: <input type="text" name="manuf" /><br>
        Model: <input type="text" name="model" /><br>
        Body: <input type="text" name="body" /><br>
        Color: <input type="text" name="color" /><br>
        Price: <input type="text" name="price" /><br>
        Upload images:
        <div id="upload">
            <input type="file" multiple name="file[]"/>
            <input type='submit' value='ADD'>
        </div>
        <div id="cancel">
            <input type="button" value="cancel"/>
        </div>
    </form>
</body>
</html>
