(function () {
    var questionModule = angular.module('questionMod', []);

    questionModule.controller('QuestionsController', ['$scope','$rootScope','Interview','User','Applicant','Appointment', function ($rootScope,$scope, Interview,User, Applicant, Appointment) {

        var userId = User.id;
        question.userId = userId;
        $scope.activeIndex = 0;

        Interview.get(Appointment.appointmentId).then(function (response) {
//                if(response.data.finalComment){
//                    window.location = "/sorry?code=6";
//                }
                $scope.questions = response.data.questions;
                if(!$scope.questions){
                    $scope.questions = [];
                }
                if($scope.questions.length === 0){
                    $scope.questions.push(angular.copy(question));
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

        var getQuestionById = function(id){
            var arr = $scope.questions;
            for(var j=0;j<arr.length;j++){
                if(arr[j].id === id){
                    return arr[j];
                }
            }
            return undefined;
        }
        var getQuestionByIdFromArr = function(id,arr){
            for(var j=0;j<arr.length;j++){
                if(arr[j].id === id){
                    return arr[j];
                }
            }
            return undefined;
        }

        $scope.questionIsMine = function(q){
            return userId === q.userId;
        }

        $scope.updateInterview = function(){


            Interview.get(Appointment.appointmentId).then(function (response) {

                var qs = response.data.questions;
                for(var i=0;i<qs.length;i++){
                    if(qs[i].userId === userId){
                        qs[i] = getQuestionById(qs[i].id);
                    }
                }
                var maybeNewQuestion = $scope.questions[$scope.questions.length-1];
                //check if question is present
                var q = getQuestionByIdFromArr(maybeNewQuestion.id,qs);
                if(q) {
                    if (q.id !== maybeNewQuestion.id) {
                        qs.push(maybeNewQuestion);
                        $rootScope.$broadcast('New question was added', 'User ' + User.name + ' asked: "' + maybeNewQuestion.question + '" mark: ' + maybeNewQuestion.mark);

                    }
                }
                else{
                    qs.push(maybeNewQuestion);
                    $rootScope.$broadcast('New question was added', 'User ' + User.name + ' asked: "' + maybeNewQuestion.question + '" mark: ' + maybeNewQuestion.mark);
                }

                var len = $scope.questions.length;
                for(var i=0;i<len;i++){
                    if(!$scope.questions[i].question){
                        var index = array.indexOf(i);
                        if (index > -1) {
                            array.splice(index, 1);
                            i--;
                        }
                    }
                }
                response.data.questions = qs;
                Interview.edit(response.data).then(function(response){
                    $scope.questions = response.data.questions;
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
        mark: 0,
        id: null,
        weight: 2
    };
})();
