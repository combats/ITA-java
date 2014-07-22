<html>
<head>
    <meta charset="utf-8"/>
    <title>THIS IS FILE UPLOADDDDDDDD!!!!!!!!!!!11</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>

</head>
<body>
	<h1>${message}</h1>
    <div>
        <form method="POST" enctype="multipart/form-data" action="...">
            File to upload: <input type="file" name="file"><br /> ID: <input type="text" id="text_field1"><br /> <br /> <input id="button1" type="submit" value="Upload"> Press here to upload the file!
        </form>
        <script >
            $('#button1').click(function(){
                var bla = $('#text_field1').val();
                $('form').get(0).setAttribute('action', 'http://localhost:8080/imgfile/applicant/' + bla);
            });

//            $(document).ready(function(){
//                 /* your code */
//            });
        </script>
    </div>

    <%--<script type="text/javascript">--%>
        <%--$.get("http://localhost:8080/repo/img/applicant/testController100?height=200&width=200", onAjaxSuccess--%>
        <%--);--%>

        <%--function onAjaxSuccess(data)--%>
        <%--{--%>
            <%--alert(data);--%>
        <%--}--%>
    <%--</script>--%>

    <div>
        ID: <input type="text" id="text_field2">
        <input id="button2" type="submit" value="GET"> Press here to get the image!
        <script>
            $('#button2').click(function(){
                var bla = $('#text_field2').val();

                var img = $("<img />").attr('src', 'http://localhost:8080/imgfile/applicant/' + bla + '?height=200&width=200')
                        .load(function() {
                            if (!this.complete || typeof this.naturalWidth == "undefined" || this.naturalWidth == 0) {
                                alert('broken image!');
                            } else {
                                $("#something").append(img);
                            }
                        });

            });
        </script>
    </div>
<div id="something">

</div>
<%--<script>--%>
    <%--var img = $("<img />").attr('src', 'http://localhost:8080/imgfile/applicant/testController100')--%>
            <%--.load(function() {--%>
                <%--if (!this.complete || typeof this.naturalWidth == "undefined" || this.naturalWidth == 0) {--%>
                    <%--alert('broken image!');--%>
                <%--} else {--%>
                    <%--$("#something").append(img);--%>
                <%--}--%>
            <%--});--%>
<%--</script>--%>
</body>
</html>