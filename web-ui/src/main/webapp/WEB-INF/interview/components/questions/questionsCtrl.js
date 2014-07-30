(function () {
    var questionModule = angular.module('questionMod', []);

    questionModule.controller('QuestionsController', ['$scope','Interview','User','Applicant','Appointment', function ($scope, Interview,Applicant, User, Appointment) {

        $scope.activeIndex = 0;

        Interview.get(Appointment.appointmentId).then(function (response) {
                if(response.data.finalComment){
                    window.location = "/sorry?code=6";
                }
                $scope.questions = response.data.questions;
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
                    $scope.questions = response.data.questions;
                    if(!$scope.questions){
                        $scope.questions = [];
                        $scope.questions.push(question);
                    }
                },function(err){
                    window.location = "/sorry?code=4";
                });
            }
        );


        $scope.updateInterview = function(){
            //TODO:
            //$rootScope.$broadcast('New question was added');
            Interview.get(Appointment.appointmentId).then(function (response) {
                response.data.questions = $scope.questions;
                var len = $scope.questions.length;
                for(var i=0;i<len;i++){
                    if(!$scope.questions[i].question){
                        return;
                    }
                }
                Interview.edit(response.data).then(function(){
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
            //only if the last question has question fomulation
            if($scope.questions[last].question) {
                $scope.questions.push(angular.copy(question));
                $scope.activeIndex = $scope.questions.length-1;
            }
        }
    }]);

    var question = {
        question: "",
        comment: "",
        mark: 1,
        id: "",
        weight: 2
    };
})();
