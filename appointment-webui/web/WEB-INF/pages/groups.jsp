<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/mustache.js"/>"></script>
    <script src="<c:url value="/resources/js/jqueryFunctions.js"/>"></script>
    <link href="<c:url value="/resources/css/mycss2.css" />" rel="stylesheet">

    <script>
        var javaImage = "<c:url value="/resources/images/pen-java.png"/>";
        var javaScriptImage = "<c:url value="/resources/images/pen-jsui.png"/>"
        var sharpImage = "<c:url value="/resources/images/pen-net.png"/>"
        var devopsImage = "<c:url value="/resources/images/pen-devops.png"/>"

        function doSomething(form) {
            var selectedValue = form.options[form.selectedIndex].value;
            var statusUrl = "/appointment-webui/groups/" + selectedValue
            $.ajax({
                url: statusUrl,
                dataType: "json",
                type: "GET",
                success: function (data) {
                    var output = "";
                    var image;
                    for (index = 0; index < data.length; index++) {
                        switch (data[index].course.name) {
                            case "Java":
                                image = javaImage;
                                break;
                            case "DevOps":
                                image = devopsImage;
                                break;
                            case "Sharp":
                                image = sharpImage;
                                break;
                            case "JavaScript":
                                image = javaScriptImage;
                                break;
                        }
                        var view = {
                            imageSrc: image,
                            descContent: data[index].groupID + " " + data[index].course.name
                        }
                        var template = "<div class='img'>" +
                                "  <a href='#'><img src={{{imageSrc}}}></a> " +
                                "    <div class='desc'>{{descContent}}</div>" +
                                " </div>";
                        output += Mustache.render(template, view);
                    }
                    $("#testdiv").html(output);
                }
            });
        }
    </script>

    <script>
        $(document).ready(function () {
            var userName = "Dron";
            var center = 'center';
            var userOccupation = "WebDeveloper"
            var view = {
                name: userName,
                occupation: userOccupation,
                center: center
            };
            var template = "<h1  align={{center}}>{{name}} is a  {{occupation}}</h1>";
            var output = Mustache.render(template, view);
            $("#testdiv").html(output);
        });
    </script>

    <title>
        Groups
    </title>

</head>

<body>

<p id="person" hidden="true">text</p>

<select name="select1" id="drop" onchange="doSomething(this)">
    <option value="Status1">Status1</option>
    <option value="Status2">Status2</option>
</select>
<br>

<div id="testdiv">

</div>

</body>
</html>
