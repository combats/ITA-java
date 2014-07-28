angular.module('applicantPopupModule').factory('Photo',['$http','Applicant', function($http, Applicant) {
    return {
        add: function(photo){
           return $http({method: 'POST', url: "/repository/img/applicant/" + Applicant.id, data : photo});
        },
        get: function(){
            return $http({method: 'GET', url: "/repository/img/applicant/" + Applicant.id})
        }
    }
}])
