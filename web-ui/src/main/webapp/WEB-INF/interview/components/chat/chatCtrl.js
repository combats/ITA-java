var app = angular.module('chatMod', ['ngAnimate', 'userHeadeMod'])

app.directive('ngEnter', function () {
    return {
        restrict: 'A',
        link: function (scope, elem, attrs) {

            elem.bind('keydown', function(event) {
                var code = event.keyCode || event.which;

                if (code === 13) {
                    if (!event.shiftKey) {
                        event.preventDefault();
                        scope.$apply(attrs.ngEnter);
                    }
                }
            });
        }
    }
})

    app.controller('ChatController', ['$scope', '$rootScope','ChatService','User', function ($scope,$rootScope,ChatService,User) {

    $scope.message = {};
    $scope.message.nickname = User.name + ' ' + User.lastname;
    $scope.messages = [];

        var $cookies = angular.injector(['ngCookies']).get('$cookies');
        var appointmentId = $cookies.appointmentId;

    ChatService.subscribe(function(message) {

        if (!$scope.ONLINE) {
            if (message.data = 1) $scope.ONLINE = true;
            ChatService.send(JSON.stringify($scope.message));
        }
        else {

            if($scope.message.text){
                 $scope.messages.push(message);
            }
             $scope.$apply();
    }
    });

    $scope.connect = function() {

        ChatService.connect(appointmentId);
           }

    $scope.send = function() {
        if($scope.message.text){
            ChatService.send(JSON.stringify($scope.message));}
    }

}]);

