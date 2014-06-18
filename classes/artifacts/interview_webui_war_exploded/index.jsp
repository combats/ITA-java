<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 18.06.2014
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Groups</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
    </script>
    <script src="jqueryFunctions.js"></script>

    <script>
        $(document).ready(function () {
            hide();
        })
    </script>
</head>

<body>

<p>If you click on the "Hide" button, I will disappear.</p>
<button id="hide">Hide</button>
<button id="show">Show</button>

</body>
</html>
