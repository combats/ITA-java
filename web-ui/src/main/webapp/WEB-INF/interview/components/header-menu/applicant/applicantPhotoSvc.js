angular.module('applicantPopupModule').factory('Photo',['$http','Applicant', function($http, Applicant) {
    return {
        add: function(photo){
            $http({method: 'POST', url: "/repository/img/applicant/" + Applicant.id, headers: {'Content-Type': 'image/png'}},photo).then(function(response){
                return response.data;
            });
        }
    }
}])
