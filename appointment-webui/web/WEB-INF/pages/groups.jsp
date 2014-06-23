<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/mustache.js"/>"></script>
    <script src="<c:url value="/resources/js/jqueryFunctions.js"/>"></script>
    <link href="<c:url value="/resources/css/mycss2.css" />" rel="stylesheet">


    <script>
        function redirect() {
            $.ajax({
                url: "/appointment-webui/list",
                dataType: "json",
                type: "GET",
                success: function () {
                    alert("wow");
                }
            });
        }
    </script>


    <script>
        var javaImage = "<c:url value="/resources/images/pen-java.png"/>";
        var javaScriptImage = "<c:url value="/resources/images/pen-jsui.png"/>";
        var sharpImage = "<c:url value="/resources/images/pen-net.png"/>";
        var devopsImage = "<c:url value="/resources/images/pen-devops.png"/>";
        var plusImage = "<c:url value="/resources/images/green-plus.png"/>";
        var template = "<div class='img'>" +
                " <img id = 'image' src={{{imageSrc}}} onclick = 'redirect()'> " +
                "    <div class='desc'>{{{descr}}}</div>" +
                "<p id = 'groupId' hidden = 'true' >+ {{groupId}} </p>" +
                " </div>";
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
                            descr: "Group : " + data[index].groupName + "<br>"
                                    + "Course : " + data[index].course.name,
                            groupId: data[index].groupID
                        }
                        output += Mustache.render(template, view);
                    }
                    var view = {
                        imageSrc: plusImage,
                        descr: "<br>" + "Add new group"
                    }
                    output += Mustache.render(template, view);
                    $("#testdiv").html(output);
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
    <option value="In progress">In progress</option>
    <option value="Recruitment">Recruitment</option>
</select>
<br>

<div id="testdiv">

</div>

</body>
</html>
