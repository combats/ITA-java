angular.module('chatMod').factory('ChatService', function() {

    var service = {};


    service.connect = function(appointmentId) {

        if(service.websocket) { return; }

        console.log("connect" + appointmentId)

        console.log("ws://localhost:8085/ChatSocket/websocket" + appointmentId);

        var websocket = new WebSocket("ws://localhost:8085/ChatSocket/websocket/" + appointmentId);

        var ONLINE;

//        window.onbeforeunload = function() {
//            websocket.close();
//        }

        websocket.onopen = function(event) {
            console.log("Connection succesful function from Controller");
            service.callback(websocket.readyState);
        };

        websocket.onclose = function(event){

            console.log("CLOSE!!!!!!!!!!!!!!!!!!!!!!");
            service.callback('Close');
        }
        websocket.onerror = function(event) {
            service.callback('Error');
        }

        websocket.onmessage = function(event) {

            console.log("OnMessage function from Service" + event.data);

            service.callback(JSON.parse(event.data));
        };

        service.websocket = websocket;
    }

    service.send = function(message) {
        console.log("Send() function from Service");
        console.log("Nickname is:"+ message.nickname + "Content is:"+message.text);
        service.websocket.send(message);
    }

    service.subscribe = function(callback) {
        console.log("Hi from Subscribe - Service" + callback.nickname + callback.text
            + callback.time );
        console.log("Send() function from Service");
        service.callback = callback;
    }

    return service;
})
