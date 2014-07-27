'use strict';
angular.module('timerModule', []).controller('timerCtrl',['$scope','$timeout','Appointment','StartTimeUpdate', function($scope,$timeout,Appointment,StartTimeUpdate) {


    $scope.curentTime = Date.now();



    if (Appointment.actualStartTime){
        var timeTmp =$scope.curentTime - Appointment.actualStartTime;
        $scope.duration = Appointment.durationTime - timeTmp;

    }
    else{
        Appointment.actualStartTime = $scope.curentTime;
      $scope.duration = Appointment.durationTime;
      Appointment.actualStartTime = $scope.curentTime
        StartTimeUpdate.update(Appointment);

    }

    $scope.isForward = false;

    $scope.onTimeout = function(){
        if($scope.duration<=0){ $scope.isForward=true;};

        $scope.duration += $scope.isForward? +1000: -1000;
        /*console.log("duration is: ", $scope.duration );*/
        mytimeout = $timeout($scope.onTimeout,1000);
    };
    var mytimeout = $timeout($scope.onTimeout,1000);





}]);


