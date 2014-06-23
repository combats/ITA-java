<#import "header_footer.ftl" as header_footer/>

<@header_footer.main_page>

    <div class="flexslider mainslider">
        <ul class="slides">
            <li>
                <div style="background: transparent url(images/content-demo/full-slider1.jpg);">
                    <div class="container leftDown">
                        <div class="container">
                            <div class="box-mainslider">
                                <h3>Welcome!</h3>
                                <p>
                                    Our app makes process of interviewing students in IT-Academy simple, easy and convenient.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div style="background-image: url(images/content-demo/full-slider4.jpg);">
                    <div class="container rightUp">
                        <div class="box-mainslider">
                            <h3>About project</h3>
                            <p>
                                ...
                            </p>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>

    <div class="bgnavmainslider">
        <div class="container">
            <div class="flexslider navmainslider">
                <ul class="slides">
                    <li>
                        Welcome to IT Interviewer
                    </li>
                    <li>
                        About project
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row-fluid">
            <div class="span4">
                <div class="iconBox doCenter">
                    <span class="entypo cog"><i></i></span>
                    <h3 class="light">Schedule</h3>
                    <p>
                        your appointments with candidates.
                    </p>
                </div>
            </div>
            <div class="span4">
                <div class="iconBox doCenter">
                    <span class="entypo lamp"><i></i></span>
                    <h3 class="light">Estimate</h3>
                    <p>
                        and comment candidate answers through the interview.
                    </p>
                </div>
            </div>
            <div class="span4">
                <div class="iconBox doCenter">
                    <span class="entypo chat"><i></i></span>
                    <h3 class="light">Invite</h3>
                    <p>
                        your colleagues to interview candidate.
                    </p>
                </div>
            </div>
        </div>
    </div>

</@header_footer.main_page>
