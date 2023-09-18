package kr.or.ddit.api.chat;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoWebSocketHandler extends TextWebSocketHandler {
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		session.sendMessage(message);
	}
}
