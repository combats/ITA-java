package com.softserveinc.ita.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket", encoders = { MessageEncoder.class },
                                 decoders = { MessageDecoder.class })
public class ChatEndpoint {

    private static Set<Session> connections = java.util.Collections.synchronizedSet(new HashSet<Session>());
    private static Map<Session, String> clients = new ConcurrentHashMap<>();
    private Session currentSession;
    private static String CurrentNickName;
    private boolean online = false;

	@OnMessage
	public void onMessage(Message message, Session session) throws IOException, EncodeException {

        if(online) {

            connections.add(session);
            this.currentSession = session;

            if (!clients.containsKey(currentSession)) {

                clients.put(currentSession, message.getNickname());

            }

            Message response = new Message();
            response.setNickname(message.getNickname());
            response.setText(message.getText());
            response.setTime(message.getTime());

            if (clients.get(currentSession).equals(message.getNickname())) {
                response.setSent(false);
            } else response.setSent(true);

            sentToAll(session, response);
        } else {
               String CurrentNickName = message.getNickname();
               online = true;
               sentToAll(session, joinUser(CurrentNickName));
               }

    }

	@OnOpen
	public void onOpen(Session session) {
        String nick = clients.get(session);

        System.out.println("Client connected" + session.getId());
	    connections.add(session);
        this.currentSession = session;

    }

	@OnClose
	public void onClose(Session session) {
        String nick = clients.get(session);

        connections.remove(session);
        clients.remove(session);
         if (nick!= null) {
         quitUser(nick);
        }
        quitUser(nick);
        System.out.println("Connection closed");
	}

    public Message joinUser(String nick){

        Message infoMessage = new Message();
        infoMessage.setNickname("Server");
        infoMessage.setText("User " + nick + " join to chat");
        infoMessage.setTime(new Date().getTime());
        return infoMessage;
    }

    public Message quitUser(String nick){
        Message infoMessage = new Message();
        infoMessage.setNickname("Server");
        infoMessage.setText("User " + nick + " quit from chat");
        infoMessage.setTime(new Date().getTime());
        return infoMessage;
    }

    private void sentToAll(Session session, Message response){
        for (Session s : session.getOpenSessions()) {
            if (s.isOpen()) {
                s.getAsyncRemote().sendObject(response);
            }
        }
   }
}
