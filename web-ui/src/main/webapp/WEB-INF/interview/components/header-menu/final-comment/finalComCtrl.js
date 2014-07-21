var  mediapp = angular.module('finalComMod',[]);
mediapp.controller('finalComCtrl',['$scope', 'User', function($scope, User){

        $scope.user = User;
        $scope.finalComment = '';



    $scope.submitComment = function(){
        alert($scope.finalComment);
//        &http.put("/someURL")
    };


}]);
