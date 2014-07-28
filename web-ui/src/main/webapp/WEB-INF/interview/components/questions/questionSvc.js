angular.module('questionMod').factory('Question',['$http','Appointment', function($http, Appointment) {
    return {
        update: function(q){
            return $http({method: 'PUT', url: "http://176.36.11.25:8080/interviews/interviewing/answer/", data : q});
        },
        add: function(q){
            q.interviewId = Appointment.appointmentId;
            return $http({method: 'POST', url: "http://176.36.11.25:8080/interviews/interviewing/answer/", data : q});
        }
    }
}])
