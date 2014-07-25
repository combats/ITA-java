package com.softserveinc.ita.chat;

import com.google.gson.*;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message> {

    @Override
    public Message decode(String jsonMessage) throws DecodeException {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        Message message = gson.fromJson(jsonMessage, Message.class);

//		JsonObject jsonObject = Json
//				.createReader(new StringReader(jsonMessage)).readObject();
//
//        JsonObject jsonObject1 = new JsonObject().getAsJsonObject(jsonMessage);
//
//        Message message = new Message();
//        Message message = new Message();
//		message.setNickname(jsonObject.getString("nickname"));
//		message.setText(jsonObject.getString("text"));

        return message;

    }

    @Override
    public boolean willDecode(String jsonMessage) {
        try {
            // Check if incoming message is valid JSON

            JsonObject object1 = new JsonParser().parse(jsonMessage).getAsJsonObject().getAsJsonObject("object");

//            Json.createReader(new StringReader(jsonMessage)).readObject();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void init(EndpointConfig ec) {
        System.out.println("MessageDecoder -init method called");
    }

    @Override
    public void destroy() {
        System.out.println("MessageDecoder - destroy method called");
    }

}
