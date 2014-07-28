(function () {
    var questionModule = angular.module('questionMod', []);

    questionModule.controller('QuestionsController', ['$scope','Question','User', function ($scope, Question, User) {
        $scope.question = angular.copy(question);
        $scope.questions = User.questions;

        $scope.isActive = function(q){
            return q.question === $scope.question.question;
        };
        $scope.isAnswered = function(q){
            return (q.comment || q.mark) && !$scope.isActive(q);
        };
        $scope.selectQuestion = function(q){
            $scope.submitQuestion($scope.question);
            $scope.question = angular.copy(q);
        };
        $scope.submitQuestion = function(q){
            if($scope.isNewQuestion(q)){
                Question.add(q).then(function (response) {
                    q.id = response;
                    $scope.questions.push(angular.copy(q));
                },function(err){
                    alert("Error during question submit. Check the connection");
                });
            }
            else if($scope.isAnyChangesQuestion(q)){
                var i=0, len=$scope.questions.length;
                for (; i<len; i++) {
                    if (q.question === $scope.questions[i].question) {
                        $scope.questions[i].comment = angular.copy(q.comment);
                        $scope.questions[i].mark = q.mark;
                        $scope.questions[i].weight = q.weight;
                        Question.update(q).then(function (response) {

                        }, function (err) {
                            alert("Error during question update. Check the connection");
                        });
                    }
                }

            }
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
            var i=0, len=$scope.questions.length;
            for (; i<len; i++) {
                if(q.question === $scope.questions[i].question){
                    return q !== $scope.questions[i].question;
                }
            }
            return false;
        }
        $scope.newQuestion = function(){
            $scope.question = angular.copy(question);
        }
    }]);

    var question = {
        weight: 2
    };
})();
