(function () {
    var questionModule = angular.module('questionMod', []);

    questionModule.controller('QuestionsController', ['$scope','Interview','User','Applicant','Appointment', function ($scope, Interview,Applicant, User, Appointment) {

        $scope.activeIndex = 0;
        $scope.question = angular.copy(question);

        Interview.get(Appointment.appointmentId).then(function (response) {
                $scope.questions = response.questions;
                if(!$scope.questions){
                    $scope.questions = [];
                    $scope.questions.push(question);
                }
            },
            function (err) {
                var interview = {};
                interview.id = Appointment.appointmentId;
                interview.applicantId = Applicant.id;
                interview.usersId = Appointment.userIdList;
                var qs = User.questions;
                if(qs) {
                    for (var i = 0; i < qs.length; i++) {
                        qs[i].question = qs[i].questionBody;
                        delete qs[i]['questionBody'];
                        qs[i].comment = "";
                        qs[i].mark = 1;
                    }
                    interview.questions = qs;
                }
                interview.finalComment = "";

                Interview.add(interview).then(function(response){
                    $scope.questions = response.questions;
                    if(!$scope.questions){
                        $scope.questions = [];
                        $scope.questions.push(question);
                    }
                });
            }
        );


        $scope.updateInterview = function(){
            Interview.get(Appointment.appointmentId).then(function (response) {
                response.data.questions = $scope.questions;

                Interview.edit(response.data).then(function(){
                    console.log("Interview was updated");

                });
            });
        }


        $scope.isActive = function(q){
            return q.question === $scope.question.question;
        };
        $scope.isAnswered = function(q){
            return (q.comment) && !$scope.isActive(q);
        };
        $scope.selectQuestion = function(q){
            $scope.submitQuestion($scope.question);
            $scope.question = angular.copy(q);
        };

        var addQuestion = function(q){
//            Question.add(q).then(function (response) {
//                q.id = response;
                $scope.questions.push(angular.copy(q));
//            },function(err){
//                alert("Error during question submit. Check the connection");
//            });
        };

        $scope.submitQuestion = function(q){
            if($scope.isNewQuestion(q)){

                $rootScope.$broadcast('New question was added');

                addQuestion(q);
            }
            $scope.updateInterview();

        };
        $scope.intToWeight = function(num){
            var weights = ["low", "normal", "high", "critical"]
            return weights[num-1];
        };
        $scope.isNewQuestion = function(q){
            if(!q.question || !$scope.questions){
                return false;
            }
            var i=0, len=$scope.questions.length;
            for (; i<len; i++) {
                if(q.question === $scope.questions[i].question)
                    return false;
            }
            return true;
        }
        $scope.hoveringOver = function(value) {
            $scope.hoverMark = value;
        };

        $scope.isAnyChangesQuestion = function(q){
            if(!$scope.questions){
                return false;
            }
            var len=$scope.questions.length;
            for (var i=0; i<len; i++) {
                if(q.question === $scope.questions[i].question){
                    return q !== $scope.questions[i].question;
                }
            }
            return false;
        }
        $scope.newQuestion = function(){
            $scope.questions.push(question);
            $scope.activeIndex = $scope.questions.length-1;
            $scope.question = angular.copy(question);
        }
    }]);

    var question = {
        weight: 2
    };
})();
