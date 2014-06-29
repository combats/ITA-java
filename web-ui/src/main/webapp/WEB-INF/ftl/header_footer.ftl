<#macro main_page>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<title>interviewer-home</title>
		<link rel="stylesheet" href="css/icons/entypo/css/entypo.css" />
		<link rel="stylesheet" href="css/flexslider.css" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>

	<body class=" ">
		<div id="boxedWrapper">

			<div class="navbar navbar-static-top">
				<div class="navbar-inner">
					<div class="container">

						<a class="brand" href="#">IT Interviewer</a>

						<div class="nav-collapse">
							<ul class="nav pull-left">
								<li class="active dropdown">
									<a href="#" class="dropdown-toggle">Courses</a>
								</li>
								<li class="dropdown">
									<a href="#" class="dropdown-toggle">Question pool</a>
								</li>
								<li class="dropdown">
									<a href="#" class="dropdown-toggle">Candidates</a>
								</li>
								<li class="dropdown">
									<a href="#" class="dropdown-toggle">Schedule</a>
								</li>
								<li class="dropdown">
									<a href="#" class="dropdown-toggle">Users</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<!--use this as an anchor for your specific page content-->
			<#nested/>

			<div class="push80"></div>

			<a href="#" id="toTop" class="entypo up-open"><i></i> Back to top</a>
			</div>

			<div id="footer">
				<div class="footNotes">
					<div class="container">
						<div class="row-fluid">
							<div class="span4">
								<p><a href="#">FAQ</a></p>
							</div>
							<div class="span4">
								<p>03057 Dehtyarivska Street 21G, KIEV</p>
							</div>
							<div class="span4 doRight">
								<p>2014 ITA Java group  All Rights Reserved</p>
							</div>
						</div>
					</div>
				</div>
			</div>

		<script src="js/jquery.min.js"></script>
		<script src="js/jquery.flexslider-min.js"></script>
		<script src="js/jquery.contenthover.min.js"></script>
		<script src="js/main.js"></script>
	</body>
</html>
</#macro>