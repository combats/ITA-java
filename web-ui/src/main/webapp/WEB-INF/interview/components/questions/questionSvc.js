angular.module('questionMod').factory('Question',['$http','Appointment', function($http, Appointment) {
    return {
        update: function(id, q){
            q.interviewId = Appointment.appointmentId;
            return $http.put("TODO:",q).then(function(response){
               return response.data;
            });
        },
        add: function(q){
            q.interviewId = Appointment.appointmentId;
            $http.post("TODO:",q).then(function(response){
               return response.data;
            });
        }
    }
}])
