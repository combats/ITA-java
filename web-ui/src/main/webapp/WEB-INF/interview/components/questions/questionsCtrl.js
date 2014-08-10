(function () {
    var questionModule = angular.module('questionMod', []);

    questionModule.controller('QuestionsController', ['$scope','$rootScope','Interview','User','Applicant','Appointment', function ($rootScope,$scope, Interview,User, Applicant, Appointment) {

        var userId = User.id;
        question.userId = userId;
        $scope.activeIndex = 0;

        var getMyQuestions = function (arr) {
            var res_arr = [];
            if (arr) {
                for (var i = 0; i < arr.length; i++) {
                    if (arr[i].userId === userId) {
                        res_arr.push(arr[i]);
                    }
                }
            }
            if(res_arr.length === 0 && hardcodedQuestions.length !== 0){
                res_arr = hardcodedQuestions;
            }
            else if(res_arr.length === 0){
                res_arr.push(angular.copy(question));
            }
            return res_arr;
        }
        var getHardcodedQuestions = function(){
            var hardcodedQuestions = User.questions;
            if(hardcodedQuestions) {
                for (var i = 0; i < hardcodedQuestions.length; i++) {
                    hardcodedQuestions[i].question = hardcodedQuestions[i].questionBody;
                    delete hardcodedQuestions[i]['questionBody'];
                    hardcodedQuestions[i].comment = "";
                    hardcodedQuestions[i].mark = 1;
                    if(!hardcodedQuestions[i].weight){
                        hardcodedQuestions[i].weight = 1;
                    }
                    hardcodedQuestions[i].id = null;
                    hardcodedQuestions[i].userId = userId;
                }
            }
            else{
                hardcodedQuestions = [];
            }
            return hardcodedQuestions;
        }
        var hardcodedQuestions = getHardcodedQuestions();

        Interview.get(Appointment.appointmentId).then(function (response) {
                $scope.questions = getMyQuestions(response.data.questions);
            },
            function (err) {
                var interview = {};
                interview.id = Appointment.appointmentId;
                interview.applicantId = Applicant.id;
                interview.usersId = Appointment.userIdList;

                interview.finalComment = "";
                interview.questions = hardcodedQuestions;

                Interview.add(interview).then(function(response){
                    $scope.questions = getMyQuestions(response.data.questions);
                },function(err){
                    window.location = "/sorry?code=4";
                });
            }
        );

        $scope.updateInterview = function() {
            //remove all questions with no question definition
            for(var i=0;i<$scope.questions.length;i++){
                if(!$scope.questions[i].question){
                    $scope.questions.splice(i, 1);
                    i--;
                }
            }
            //
            if($scope.questions.length <= $scope.activeIndex){
                $scope.activeIndex = $scope.questions.length-1;
            }
            if($scope.activeIndex === -1){
                $scope.activeIndex = 0;
            }
            Interview.get(Appointment.appointmentId).then(function (response) {
                var qs = response.data.questions;
                //remove user questions
                for(var i=0; i<qs.length; i++){
                    if (qs[i].userId === userId){
                        qs.splice(i, 1);
                        i--;
                    }
                }

                response.data.questions = $scope.questions.concat(qs);
                Interview.edit(response.data).then(function(response){
                    $scope.questions = getMyQuestions(response.data.questions);
                    console.log("Interview was updated");
                });
            });
        }

        $scope.isActive = function(q){
            return q.question === $scope.questions[$scope.activeIndex].question;
        };
        $scope.isAnswered = function(q){
            return (q.comment) && !$scope.isActive(q);
        };
        $scope.selectQuestion = function(index){
            $scope.activeIndex = index;
            $scope.updateInterview();
        };

        $scope.intToWeight = function(num){
            var weights = ["low", "normal", "high", "critical"]
            return weights[num-1];
        };
        $scope.hoveringOver = function(value) {
            $scope.hoverMark = value;
        };
        $scope.newQuestion = function(){
            var last = $scope.questions.length-1;
            //if there is no questions yet
            if(last===-1){
                $scope.questions.push(angular.copy(question));
            }
            //only if the last question has question fomulation
            else if($scope.questions[last].question) {

                $scope.questions.push(angular.copy(question));
                $scope.activeIndex = $scope.questions.length - 1;
            }
        }
    }]);

    var question = {
        question: "",
        comment: "",
        mark: 0,
        id: null,
        weight: 2
    };
})();
