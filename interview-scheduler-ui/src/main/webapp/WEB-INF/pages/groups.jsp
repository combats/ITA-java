<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/mustache.js"/>"></script>
    <script src="<c:url value="/resources/js/jqueryFunctions.js"/>"></script>
    <link href="<c:url value="/resources/css/style2.css" />" rel="stylesheet">

    <script>
        $(document).ready(function(){
            viewGroups();
        });
    </script>
    <title>
        Groups
    </title>
</head>
<body>


<div id="boxedWrapper">
<div class="navbar navbar-static-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="index-2.html">It Academy</a>

            <div class="nav-collapse">
                <ul class="nav pull-left">
                    <li class="dropdown">
                        <a href="index-2.html" class="dropdown-toggle">Menu1</a>
                    </li>
                    <li class="dropdown">
                        <a href="invia-aboutus.html" class="dropdown-toggle">Menu2</a>
                    </li>
                    <li class="dropdown">
                        <a href="invia-site-elements.html" class="dropdown-toggle">Menu3</a>
                    </li>
                    <li class="dropdown">
                        <a href="invia-portfolio.html" class="dropdown-toggle">Menu4</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>


<br>

<!-- main container -->
<div class="container">

    <div class="row-fluid">
        <div class="span12">
            <div class="titleBox">
                <h1 class="huge">Groups
            <span>
                <select name="select1" id="drop" onchange="viewGroups()">
                    <option value="Boarding">Boarding</option>
                    <option value="In process">In progress</option>
                    <option value="Planned">Planned</option>
                    <option value="Offering">Offering</option>
                </select>
            </span>
                </h1>
            </div>
        </div>
    </div>

    <div class="row-fluid">
        <div class="span12">
            <ul class="portfolioList col4" id="portfolio1">

            </ul>
        </div>
    </div>
</div>
<!-- /  container -->
<div id="footer">
    <div class="footNotes">
        <div class="container">
            <div class="row-fluid">
                <div class="span4">
                    <p><a href="#">Text</a></p>
                </div>
                <div class="span4">
                    <p>Text</p>
                </div>
                <div class="span4 doRight">
                    <p>Text</p>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
