<!DOCTYPE html>
<html>

<head>
    <title>SoftServe |</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Interview</title>
    <link rel="stylesheet" href="interview/css/reset.css">
    <link rel="stylesheet" href="interview/css/bootstrap-3.2.0-dist/css/bootstrap.css">
    <link rel="stylesheet" href="interview/css/common.css">
    <link rel="stylesheet" href="interview/css/interview.css">
    <link rel="stylesheet" href="interview/css/chat.css">
    <link rel="stylesheet" href="interview/css/applicantPopup.css">
    <link rel="stylesheet" href="interview/css/timer.css">


    <link rel="icon" href="common/img/favicon.ico">
</head>
<body>
   <div id="startData" userId="${userId}" appointmentId="${appointmentId}"></div>
    <header>
        <div id="header-left-group">

            <div id="header-logo"></div>
            <div id="page-title">Interview</div>
        </div>

        <div ng-controller="ModalDemoCtrl">

             <a  id="applicant-btn" ng-click="openAppPop()"  href="#"><span class="glyphicon glyphicon-user"></span> {{applicant.name}} {{applicant.surname}}</a>

        </div>

        <div id="header-right-group"  ng-controller="ModalDemoCtrl">
            <a id="finish-btn"   ng-click="openFCPop()" href="#"><span class="glyphicon glyphicon-ok"></span></a>

            <div class="timeOn" ng-controller="timerCtrl" ng-class="{'timeUp': isForward}">
                {{ duration  | date:"mm:ss" }}

            </div>


            <a id="header-user-profile-btn" ng-controller="userHeaderCtrl" ><span> <img class="img-rounded" src="interview/img/userpic-small.png" alt="user-pic">  {{user.name}} {{user.surname}}</span> </a>
        </div>
    </header>
    <div ng-controller="QuestionsController" id="main-panel">
    <div id="left-panel" >
        <div class="panel-title">Questions</div>
        <div id="questions-list">
<div class="list-group">
    <a ng-repeat="question in questions" class="list-group-item" ng-class="{'answered': isAnswered(question) ,'active':isActive(question) }" href="#" data-toggle="tab" ng-click="selectQuestion(question)">{{ question.question }}</a>
    </div>
        </div>
        <a id="new-question-btn" class="panel-btn" href="#" ng-click="newQuestion()">
            <span class="glyphicon glyphicon-edit"></span> New Question
        </a>
    </div>
    <div id="central-panel">
        <div class="panel-title">Question Manager</div>
        <div id="question-manager">
            <div id="question-container">
                <textarea id="question-area" placeholder="Enter your question over here ..." required ng-model="question.question"></textarea>
            </div>
            <div id="comment-container">
                <textarea id="comment-area" placeholder="Enter your comment over here ..." required ng-model="question.comment"></textarea>
            </div>
            <div class="rating-container">
                <div id="question-mark">
                <b>Mark:</b>
                   <rating ng-model="question.mark" max="10" state-on="'glyphicon-star'" state-off="'glyphicon-star-empty'" on-hover="hoveringOver(value)" on-leave="hoverMark = null"></rating><b>

                <span id="mark">{{hoverMark || question.mark}}</span>/10</b>
                </div>
                <div id="question-weight">
                <b>Weight:</b>
                   <rating ng-model="question.weight" max="4" state-on="'glyphicon-stop'" state-off="'glyphicon-unchecked'"></rating><b>
                <span id="weight">{{intToWeight(question.weight)}}</span></b>
                </div>
            </div>
        </div>
        <a id="submit-question-btn" class="panel-btn" ng-click="submitQuestion(question)" href="#">
            <span class="glyphicon glyphicon-ok"></span> Submit
        </a>
    </div>
    </div>
    <div id="right-panel" ng-controller="ChatController">
        <div class="panel-title">Chat/Notifications</div>
        <div id="panel-notifications">
            <div class="dm-chat">
                <div ng-repeat="message in messages" class="dm" ng-class="{'sent':message.sent}">
                    <img class="avatar" ng-src="{{message.avatar}}">
                    <div class="dm-content">
                        <small class="time">
                            <span class="_timestamp">
                                {{message.time | date:'HH:mm:ss'}}
                            </span>
                        </small>
                        <div class="dm-message" ng-class="{'sent':message.sent}">
                            <p class="js-tweet-text tweet-text">{{message.message}}</p>
                            <div class="dm-caret">
                                <div class="dm-caret-outer"></div>
                                <div class="dm-caret-inner"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="panel-chat">
            <div id="message-content">
                <textarea placeholder="What's up?" required></textarea>
            </div>
            <a class="panel-btn" id="send-msg-btn">
                <span class="glyphicon glyphicon-envelope"></span> Send message
            </a>
        </div>
    </div>

    <script type="text/javascript" src="interview/js/angular.js"></script>
   <script type="text/javascript" src="https://code.angularjs.org/1.2.18/angular-cookies.js"></script>
    <script type="text/javascript" src="interview/js/interviewApp.js"></script>
    <script type="text/javascript" src="interview/js/ngActivityIndicator.js"></script>
    <script type="text/javascript" src="interview/js/ui-bootstrap-tpls-0.11.0.js"></script>
    <script type="text/javascript" src="interview/components/header-menu/applicant/applicantHeaderCtrl.js" ></script>
    <script type="text/javascript" src="interview/components/questions/questionsCtrl.js"></script>
    <script type="text/javascript" src="interview/components/chat/chatCtrl.js"></script>
    <script type="text/javascript" src="interview/components/questions/questionSvc.js"></script>
    <script type="text/javascript" src="interview/components/header-menu/timer/timerCtrl.js" ></script>
    <script type="text/javascript" src="interview/components/header-menu/user/userHeaderCtrl.js" ></script>
   <script type="text/javascript" src="interview/components/header-menu/applicant/applicantPhotoSvc.js" ></script>
    <script type="text/javascript" src="interview/components/header-menu/final-comment/finalComCtrl.js"></script>
</body>
</html>
