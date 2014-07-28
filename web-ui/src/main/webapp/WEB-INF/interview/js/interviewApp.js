(function () {

    var app = angular.module('interview', ['ui.bootstrap', 'questionMod', 'applicantPopupModule', 'finalComMod','chatMod']);

    //be sure to inject $scope and $location
    var changeLocation = function (url, forceReload) {
        if (forceReload) {
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
            if (!userId || !appointmentId) {
                changeLocation("/sorry?code=" + 1, true);
            }

            var Appointment = {};
            $http.get("http://176.36.11.25:8080/appointments/"+appointmentId)
            .success(function (response) {
                app.value('Appointment', response);
                Appointment = response;
            }).error(function (error) {
                    //if request failed
                    changeLocation("/sorry?code="+2,true);
            })
            .then(function(){
                var initRequests = [
                    $http.get('http://176.36.11.25:8080/users/'+userId)
                    .then(function (response) {
                        app.value('User', response.data);
                    }),
                    $http.get('http://176.36.11.25:8080/applicants/'+ Appointment.applicantId)
                    .then(function (response) {
                        app.value('Applicant', response.data);
                    })
                ];
                $q.all(initRequests).then(function(){
                    //Afterall, we are ready to initialize Ng-app.
                    angular.bootstrap(document, ['interview']);
                    },
                    function() {
                            changeLocation("/sorry?code="+3,true);
                    });
            });
//            app.value('User', {name: "John", surname: "Smith"});
//            app.value('Applicant', {name: "Anton", surname: "Kravchuk"});
//            app.value('Appointment', {date: 1401866602, durationTime: 3000, actualStartTime: 0});
//            angular.bootstrap(document, ['interview']);
        }
    );

    app.controller('ModalDemoCtrl', ['$timeout', '$scope', '$modal', '$log', 'Applicant', 'Appointment', 'User', function ($timeout, $scope, $modal, $log, Applicant, Appointment, User) {

        $scope.curentTime = Date.now();

        if (Appointment.actualStartTime) {
            var timeTmp = $scope.curentTime - Appointment.actualStartTime;
            $scope.duration = Appointment.durationTime - timeTmp;
        }
        else {
            Appointment.actualStartTime = $scope.curentTime;
            $scope.duration = Appointment.durationTime;
            Appointment.actualStartTime = $scope.curentTime
        }

        $scope.isForward = false;

        $scope.onTimeout = function () {
            if ($scope.duration <= 0) {
                $scope.isForward = true;
            }
            ;

            $scope.duration += $scope.isForward ? +1000 : -1000;
            mytimeout = $timeout($scope.onTimeout, 1000);
        };
        var mytimeout = $timeout($scope.onTimeout, 1000);

        $scope.user = User;
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
