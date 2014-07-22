var  mediapp = angular.module('userHeadeMod',[]);
mediapp.controller('userHeaderCtrl',['$scope',"User" , function($scope, User){

    $scope.user = User;

}]);
