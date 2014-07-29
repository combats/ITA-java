var  mediapp = angular.module('finalComMod',[]);
mediapp.controller('finalComCtrl',['$scope','Comment', 'User', '$rootScope', function($scope, Comment, User, $rootScope){

        $scope.user = User;
        $scope.finalComment = '';



    $scope.submitComment = function(){
        var finalComment = {};
        finalComment.finalComment = $scope.finalComment;

        $rootScope.$broadcast('New question was added');

//        Comment.update(finalComment).then(function(response){
//                //yoohoo, you completed interview!
                alert("You're about to leave interview. Thanks!");
                window.location = "/";
//        },function(err){
//                alert("Can't send final comment. Service is unreachable");
//            }
//        );
    };
}]);
