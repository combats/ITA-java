<%--
  Created by IntelliJ IDEA.
  User: abrastc
  Date: 20.06.2014
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/jqueryFunctions.js"/>"></script>

    <script>
        $(document).ready(function(){
            readyFunc();
        });
    </script>

    <script>
        $(document).ready(function () {
            alert('ALERT')
        });
    </script>

    <script>
        $(document).ready(
                readyFunc()
        )
    </script>

    <title></title>
</head>

<body>

<script>
    readyFunc();
</script>

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
