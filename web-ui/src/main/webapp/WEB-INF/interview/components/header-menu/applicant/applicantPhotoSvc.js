angular.module('applicantPopupModule').factory('Photo',['$http','Applicant', function($http, Applicant) {
    return {
        add: function(photo){
           return $http({method: 'POST', url: "/repository/img/applicant/" + Applicant.id, data : photo});
//                var fd = new FormData();
//                fd.append('file', photo);
//                return $http.post("/repository/img/applicant/" + Applicant.id, fd, {
//                    transformRequest: angular.identity,
//                    headers: {'Content-Type': 'image/png'}
//                }).then(function(response){
//                    return response.data;
//                });
        },
        get: function(){
            return $http({method: 'GET', url: "/repository/img/applicant/" + Applicant.id})
        }


    }
}])
