var app = angular.module('chatMod', ['ngAnimate', 'luegg.directives'])

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

app.controller('ChatController', ['$scope',  'ChatService','User', '$rootScope', function ($scope, ChatService, User, $rootScope) {

    $scope.message = {};
    $scope.message.nickname = User.name + ' ' + User.surname;
    $scope.messages = [];
    $scope.glued = true;

    var $cookies = angular.injector(['ngCookies']).get('$cookies');
    var appointmentId = $cookies.appointmentId;
    var message = {};
    var messageInfo = {};
    var error = 0;

    $rootScope.$on('New question was added', function(event, msg) {
        message.nickname = 'Server';
        message.text = msg;
        $scope.messages.push(message);
    });

    ChatService.subscribe(function(message) {

        if (message == 3) {

            $scope.ONLINE = false;

            if (error == 0) {
                messageInfo.nickname = "Server";
                messageInfo.text = "Oops, something wrong, connection are not established. " +
                    "Check your internet connection";

                console.log("Push message" + messageInfo);
                $scope.messages.push(messageInfo);
                error++;
            }
        }
        console.log("Hi from Subscribe - controller" + message);

        if (!$scope.ONLINE) {
            console.log("Current status...." + message);
            if (message == 1) $scope.ONLINE = true;
            ChatService.send(JSON.stringify($scope.message));
        }
        else {
            console.log("Push message" + message.nickname + message.text);
            if($scope.message.text){
                $scope.messages.push(message);
            }
            $scope.$apply();
        }
    });

    $scope.connect = function() {

        console.log("Connect() function from Controller" + appointmentId);

        ChatService.connect(appointmentId);

        console.log("Connect() function from Controller" + $scope.message.nickname);
    }

    $scope.send = function(){

        console.log("Send() function from Controller" );
//        console.log($scope.message);
        if($scope.message.text){
            ChatService.send(JSON.stringify($scope.message));}

        console.log("I'M HERE" + $scope.message.text);
    }

}]);