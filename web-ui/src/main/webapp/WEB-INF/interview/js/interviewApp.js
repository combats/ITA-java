(function () {

    var app = angular.module('interview', ['ui.bootstrap', 'questionMod', 'chatMod', 'applicantPopupModule','timerModule','userHeadeMod', 'finalComMod']);

    //be sure to inject $scope and $location
    var changeLocation = function(url, forceReload) {
        if(forceReload) {
            window.location = url;
        }
        else {
            //only use this if you want to replace the history stack
            //$location.path(url).replace();

            //this this if you want to change the URL and add it to the history stack
            $location.path(url);
            $scope.$apply();
        }
    };

    angular.element(document).ready(
        function () {
            var baseUrl = "http://176.36.11.25:8080/"
            var initInjector = angular.injector(['ng']);
            var $http = initInjector.get('$http');
            var $q = initInjector.get('$q');
            var $cookies = angular.injector(['ngCookies']).get('$cookies');

            // Retrieving a cookie
            var userId = $cookies.userId;
            var appointmentId = $cookies.appointmentId;
            console.log("user id = " + userId);
            console.log("appointment id = " + appointmentId);
            //if one of the cookie is absent
            if(!userId || !appointmentId){
                console.log("going to redirect you, %username%");
                changeLocation("http://cat-bounce.com/",true);
            }

            var Appointment = {};
            $http.get("http://176.36.11.25:8080/appointments/"+appointmentId)
            .then(function (response) {
                app.value('Appointment', response.data);
                Appointment = response.data;
                //TODO: this is where we find applicant id and stuff.
                // ....
                //we should find these to calculate applicant we need to get and user
            })
            .then(function(){
                var initRequests = [
                    $http.get('http://176.36.11.25:8080/users/'+userId)
                    //$http.get('/users/'+userId)
                    .then(function (response) {
                        app.value('User', response.data);
                    }),
                    $http.get('http://176.36.11.25:8080/applicants/'+ Appointment.applicantId)
                    .then(function (response) {
                        app.value('Applicant', response.data);
                    })
                ];
                $q.all(initRequests)
                    .then(function() {
                    //Afterall, we are ready to initialize Ng-app.
                    angular.bootstrap(document, ['interview']);
                });
            });
        }
    );

    app.controller('ModalDemoCtrl',['$scope', '$modal', '$log','Applicant', function ($scope, $modal, $log,Applicant) {


        $scope.applicant = Applicant;

        $scope.openAppPop = function () {

             $modal.open({templateUrl: 'interview/components/header-menu/applicant/applicantPopup.html',
                          controller: 'applicantPopupCtrl'});

        };
        $scope.openFCPop = function () {

            $modal.open({templateUrl: 'interview/components/header-menu/final-comment/finalCommentPopup.html',
                controller: 'finalComCtrl'});

        };
    }]);

})();
