package com.softserveinc.ita.chat;

import com.google.gson.*;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {

    @Override
    public String encode(Message message) throws EncodeException {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("nickname", message.getNickname());
        jsonObject.addProperty("text", message.getText());
        jsonObject.addProperty("time", message.getTime());
        jsonObject.addProperty("sent", message.isSent());

        return jsonObject.toString();

    }

    @Override
    public void init(EndpointConfig ec) {
        System.out.println("MessageEncoder - init method called");
    }

    @Override
    public void destroy() {
        System.out.println("MessageEncoder - destroy method called");
    }

}
