<html>
<head>
    <meta charset="utf-8"/>
    <title>THIS IS FILE UPLOADDDDDDDD!!!!!!!!!!!11</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
</head>
<body>
	<h1>${message}</h1>

    <form method="POST" enctype="multipart/form-data" action="http://localhost:8080/repository/imgfile/applicant/testController100">
        File to upload: <input type="file" name="file"><br /> Name: <input
            type="text" name="name"><br /> <br /> <input type="submit" value="Upload"> Press here to upload the file!
    </form>
    <%--<script type="text/javascript">--%>
        <%--$.get("http://localhost:8080/repo/img/applicant/testController100?height=200&width=200", onAjaxSuccess--%>
        <%--);--%>

        <%--function onAjaxSuccess(data)--%>
        <%--{--%>
            <%--alert(data);--%>
        <%--}--%>
    <%--</script>--%>
</body>
</html>