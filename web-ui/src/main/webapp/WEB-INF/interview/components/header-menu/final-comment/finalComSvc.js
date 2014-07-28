angular.module('finalComMod').factory('Comment',['$http', function($http) {
    return {
        update: function(fc){
            q.interviewId = Appointment.appointmentId;
            return $http({method: 'PUT', url: "http://176.36.11.25:8080/interviewing/final_comment/", data : fc});
        }
    }}])
