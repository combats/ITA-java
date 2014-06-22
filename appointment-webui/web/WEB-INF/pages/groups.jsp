<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/jqueryFunctions.js"/>"></script>
    <link href="<c:url value="/resources/css/mycss2.css" />" rel="stylesheet">

    <script>
        var list;
        function doSomething(form) {
            var selectedValue = form.options[form.selectedIndex].value;
            alert("form = " + selectedValue);
            var statusUrl = "/appointment-webui/groups/" + selectedValue
            $.ajax({
                url: statusUrl,
                dataType: "json",
                type: "GET",
                success: function (data) {
                    var index;
                    list = data;
                    for (index = 0; index < data.length; index++) {
                        data[index].groupID;
                        data[index].groupStatus;
                        data[index].course;
                    }
                }
            });
        }
    </script>

    <title>
        Groups
    </title>

</head>

<body>

<select name="select1" id="drop" onchange="doSomething(this)">
    <option value="Status1">Status1</option>
    <option value="Status2">Status2</option>
</select>
<br>

<div id="images">
    <div class="img">
        <a target="_blank" href="#"><img src="<c:url value="/resources/images/team2.jpg"/>"/></a>

        <div class="desc">Add a description of the image here</div>
    </div>
    <div class="img">
        <a target="_blank" href="#"><img src="<c:url value="/resources/images/team2.jpg"/>"/></a>

        <div class="desc">Add a description of the image here</div>
    </div>
    <div class="img">
        <a target="_blank" href="#"><img src="<c:url value="/resources/images/team2.jpg"/>"/></a>

        <div class="desc">Add a description of the image here</div>
    </div>
    <div class="img">
        <a target="_blank" href="#"><img src="<c:url value="/resources/images/team2.jpg"/>"/></a>

        <div class="desc">Add a description of the image here</div>
    </div>
    <div class="img">
        <a target="_blank" href="#"><img src="<c:url value="/resources/images/team2.jpg"/>"/></a>

        <div class="desc">Add a description of the image here</div>
    </div>
    <div class="img">
        <a target="_blank" href="#"><img src="<c:url value="/resources/images/team2.jpg"/>"/></a>

        <div class="desc">Add a description of the image here</div>
    </div>
    <div class="img">
        <a target="_blank" href="#"><img src="<c:url value="/resources/images/team2.jpg"/>"/></a>

        <div class="desc">Add a description of the image here</div>
    </div>


    <div class="img">
        <a target="_blank" href="#"><img src="<c:url value="/resources/images/team2.jpg"/>"/></a>

        <div class="desc">Add a description of the image here</div>
    </div>
    <div class="img">
        <a target="_blank" href="#"><img src="<c:url value="/resources/images/team2.jpg"/>"/></a>

        <div class="desc">Add a description of the image here</div>
    </div>
    <div class="img">
        <a target="_blank" href="#"><img src="<c:url value="/resources/images/team2.jpg"/>"/></a>

        <div class="desc">Add a description of the image here</div>
    </div>
</div>

</body>
</html>
