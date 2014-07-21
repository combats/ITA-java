angular.module('timerModule').factory('StartTimeUpdate',['$http','Appointment', function($http, Appointment) {
    return {
        update: function(){
            return $http.put("TODO:insert URL",Appointment).then(function(response){
                return response.data;
            });
        }
    }
}]);
