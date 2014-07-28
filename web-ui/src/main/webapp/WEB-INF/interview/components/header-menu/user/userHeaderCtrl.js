var  mediapp = angular.module('userHeaderMod',[]);
mediapp.controller('userHeaderCtrl',['$scope',"User" , function($scope, User){

    $scope.user = User;

}]);
