package com.softserveinc.ita.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket/{appointmentId}", encoders = { MessageEncoder.class },
                                       decoders = { MessageDecoder.class })
public class ChatEndpoint {

    private static Set<Session> connections = java.util.Collections.synchronizedSet(new HashSet<Session>());
    private static Map<Session, String> clients = new ConcurrentHashMap<>();
    private Session currentSession;
    private String currentNickName;
    private boolean online = false;

	@OnMessage
	public void onMessage(Message message, Session session) throws IOException, EncodeException {

        currentNickName = message.getNickname();

        if (!clients.containsValue(currentNickName)) {

            clients.put(currentSession, message.getNickname());

            online = true;

            sentToAllExceptThis(session, joinUser(currentNickName));

        } else {

            Message response = new Message();
            response.setNickname(message.getNickname());
            response.setText(message.getText());
            response.setTime(message.getTime());

            System.out.println(currentNickName + currentSession.getId());

            if (clients.get(currentSession).equals(message.getNickname())) {
                response.setSent(false);
            } else response.setSent(true);

            sentToAll(session, response);
        }

    }
	@OnOpen
	public void onOpen(Session session, @PathParam("appointmentId") final String appointment) {
	    connections.add(session);
        this.currentSession = session;
        System.out.println(appointment);
        session.getUserProperties().put("appointmentId", appointment);
    }

	@OnClose
	public void onClose(Session session) {

        String nick = clients.get(session);

        connections.remove(session);

        clients.remove(session);

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

        String appointment = (String) session.getUserProperties().get("appointmentId");

        for (Session s : session.getOpenSessions()) {
            if (s.isOpen()&& appointment.equals(s.getUserProperties().get("appointmentId"))) {
                s.getAsyncRemote().sendObject(response);
            }
        }
   }

    private void sentToAllExceptThis(Session session, Message response){

        String appointment = (String) session.getUserProperties().get("appointmentId");

        Set<Session> openSessions = session.getOpenSessions();
        openSessions.remove(currentSession);

        for (Session s : openSessions) {
            if (s.isOpen() && appointment.equals(s.getUserProperties().get("appointmentId"))) {
                s.getAsyncRemote().sendObject(response);
            }
        }
    }
}
