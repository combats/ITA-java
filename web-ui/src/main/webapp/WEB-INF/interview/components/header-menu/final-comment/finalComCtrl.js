var  mediapp = angular.module('finalComMod',[]);
mediapp.controller('finalComCtrl',['$scope','Comment', 'User', 'Appointment', '$rootScope','$http', function($scope, Comment, User, Appointment, $rootScope, $http){

        $scope.user = User;
        $scope.finalComment = '';

    var applicantId = Appointment.applicantId;
    var baseUrl = "http://176.36.11.25:8080/";
    var $cookies = angular.injector(['ngCookies']).get('$cookies');
    var groupId = $cookies.groupID;
    var list = {applicantId:{"status":"NOT_PASSED", "rank":-1}};

    $scope.submitComment = function(){
        var finalComment = {};
        finalComment.finalComment = $scope.finalComment;

        $rootScope.$broadcast('Final Comment was submit');

        $http({method: 'PUT',
               url: baseUrl+'/groups/' + groupId + '/applicants',
               data: JSON.stringify(list)
                 });

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
