angular.module('applicantPopupModule').factory('Photo',['$http','Applicant', function($http, Applicant) {
    return {
        add: function(photo){
           return $http({method: 'POST', url: "http://176.36.11.25:8080/repository/img/applicant/" + Applicant.id, data : photo});
        },
        get: function(){
            return $http({method: 'GET', url: "http://176.36.11.25:8080/repository/img/applicant/" + Applicant.id})
        }
    }
}])
