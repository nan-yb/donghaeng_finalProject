package kr.or.ddit.api.chat;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

public class ChattingHandler extends TextWebSocketHandler {
	Set<ChattingUser> userSet = new LinkedHashSet<>();
	
	private String getNickName(WebSocketSession session) throws UnsupportedEncodingException {
		String uri = session.getUri().toString();
		int lastIndex = uri.lastIndexOf("/");
		return URLDecoder.decode(uri.substring(lastIndex+1), "UTF-8");
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String name = getNickName(session);
		TextMessage sendMsg = new TextMessage(name+"님 입장");
		for(ChattingUser user : userSet){
			user.getSession().sendMessage(sendMsg);
		}
		userSet.add(new ChattingUser(name, session));
	}
	

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		ChattingUser sender = new ChattingUser(getNickName(session), session);
		ChattingMessage msg = new ChattingMessage(sender, message.getPayload());
		ObjectMapper mapper = new ObjectMapper();
		String jsonMsg = mapper.writeValueAsString(msg);
		TextMessage sendMsg = new TextMessage(jsonMsg);
		for(ChattingUser user : userSet){
			user.getSession().sendMessage(sendMsg);
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String name = getNickName(session);
		userSet.remove(new ChattingUser(name, session));
		
		TextMessage sendMsg = new TextMessage(name+"님 퇴장");
		for(ChattingUser user : userSet){
			user.getSession().sendMessage(sendMsg);
		}
	}
	
	@Getter
	@AllArgsConstructor
	@EqualsAndHashCode
	public static class ChattingUser{
		private String name;
		@JsonIgnore
		private WebSocketSession session;
	}
	
	@Getter
	@AllArgsConstructor
	public static class ChattingMessage{
		private ChattingUser sender;
		private String message;
	}
}





























