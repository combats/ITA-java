package com.softserveinc.ita.chat;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket", encoders = { MessageEncoder.class },
                                 decoders = { MessageDecoder.class })
public class ChatEndpoint {

    private static Set<Session> connections = java.util.Collections.synchronizedSet(new HashSet<Session>());
    private static Map<Session, String> nickNames = new ConcurrentHashMap<>();
    private Session currentSession;

	@OnMessage
	public void onMessage(Message message, Session session) throws IOException, EncodeException {

        connections.add(session);
        this.currentSession = session;

        if (!nickNames.containsKey(currentSession)) {

            nickNames.put(currentSession, message.getNickname());

        }

		Message response = new Message();
		response.setNickname(message.getNickname());
		response.setText(message.getText());
        response.setTime(message.getTime());

        if (nickNames.get(currentSession).equals(message.getNickname())){
          response.setSent(false);
        } else response.setSent(true);

        for (Session s : session.getOpenSessions()) {
            if (s.isOpen()) {s.getAsyncRemote().sendObject(response);}
        }
    }

	@OnOpen
	public void onOpen() {
		System.out.println("Client connected");
	}

	@OnClose
	public void onClose(Session session) {
        connections.remove(session);
        System.out.println("Connection closed");
	}

}
