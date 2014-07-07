<#import "header_footer.ftl" as header_footer/>

<@header_footer.main_page>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="/group_webui/resources/js/mustache.js"></script>
<script src="/group_webui/resources/js/jqueryFunctions.js"></script>

<script>
    $(document).ready(function(){
        viewGroups();
    });
</script>
    <br>
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

</@header_footer.main_page>