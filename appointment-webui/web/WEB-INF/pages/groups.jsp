<%--
  Created by IntelliJ IDEA.
  User: abrastc
  Date: 20.06.2014
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type=src="jqueryFunctions.jss"></script>
    <%--<script>--%>
        <%--$(document).ready(function () {--%>
            <%--getGroup();--%>
        <%--})--%>
    <%--</script>--%>
    <%----%>
    <%--<script>--%>
        <%--$(document).ready(function () {--%>
            <%--hide();--%>
        <%--});--%>
    <%--</script>--%>
    <%----%>
    <%--<script>--%>
        <%--$(document).ready(function () {--%>
            <%--sayHello();--%>
        <%--});--%>
    <%--</script>--%>
    <%----%>
    <script>
        $(document).ready(function(){
            readyFunc();
        });
    </script>

    <%--<script>--%>
        <%--$(document).ready(function(){--%>
            <%--alert('aaaa');--%>
        <%--});--%>
    <%--</script>--%>

    <title></title>
</head>

<body>
ID:
<p id="groupID"></p>
Status:
<p id="status"></p>
Course:
<p id="course"></p>
Object:
<p id="object"></p>

<p>If you click on the "Hide" button, I will disappear.</p>
<button id="hide">Hide</button>
<button id="show">Show</button>


</body>
</html>
