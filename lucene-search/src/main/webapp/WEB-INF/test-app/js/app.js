angular.module('app', [])
    .factory('Applicant',['$http', function($http){
        return {
            getByName: function(wildcard){
                return $http({
                    url: "/search/applicants/name",
                    method: "GET",
                    params: {wildcard: wildcard}
                });
            },
            getByLastName: function(wildcard){
                return $http({
                    url: "/search/applicants/lastName",
                    method: "GET",
                    params: {wildcard: wildcard}
                });
            }
        }
    }])
    .controller('appCtrl', ['$scope','Applicant', function($scope,Applicant) {
        $scope.wildcard = "";
        $scope.applicants = {};
        $scope.byName = true;

        $scope.textChanged = function(){
            if($scope.byName){
                Applicant.getByName($scope.wildcard).then(function(response){
                    $scope.applicants = response.data;
                });
            }
            else{
                Applicant.getByLastName($scope.wildcard).then(function(response){
                    $scope.applicants = response.data;
                });
            }
        }
    }]);