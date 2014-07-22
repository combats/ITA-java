(function () {
    var app = angular.module('chatMod', []);

    app.controller('ChatController', function ($scope) {
        $scope.messages = messages;
    });

    var messages = [{
        from: "Syperia",
        message: "Lorem ipsum dolor sit amet, consectgue",
        time: "1288323623006",
        avatar:"https://pbs.twimg.com/profile_images/2077664484/avatar234_normal.png",
        sent: true
    },
                    {
        from: "UserHR",
        message: "Lorem ipsum dolor sit amet, consectgue",
        time: "1288323623006",
        avatar: "https://pbs.twimg.com/profile_images/2373016559/image_normal.jpg"
    },
                    {
        from: "Syperia",
        message: "Lorem ipsum dolor sit amet, consectgue",
        time: "1288323623006",
        avatar:"https://pbs.twimg.com/profile_images/2077664484/avatar234_normal.png",
        sent: true
    },
                    {
        from: "UserHR",
        message: "Lorem ipsum dolor sit amet, consectgue",
        time: "1288323623006",
        avatar: "https://pbs.twimg.com/profile_images/2373016559/image_normal.jpg"
    }
    ];
})();
