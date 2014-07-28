angular.module('finalComMod').factory('Comment',['$http', function($http) {
    return {
        add: function(){
            return $http({method: 'POST', url: "/inteviews/?????/" ,data:null});

        }

    }}])
