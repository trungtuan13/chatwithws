package com.chat.config;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/mychat")
public class MyChatService {
	//seen tutorial by: https://www.youtube.com/watch?v=BikL52HYaZg
	static Set<Session> chatroomUsers = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void handleOpen(Session userSession) {
		chatroomUsers.add(userSession);
	}
	@OnMessage
	public void handleMessage(String message, Session userSession,HttpSession session) throws IOException {
//		String username = (String) userSession.getUserProperties().get("username");
		String username = "Trung Tuan";
		if(username==null) {
			userSession.getUserProperties().put("username", message);
			userSession.getBasicRemote().sendText(buildJsonData("System","you are now connected as: " + message));
		}else {
			Iterator<Session> iterator = chatroomUsers.iterator();
			while(iterator.hasNext()) iterator.next().getBasicRemote().sendText(buildJsonData(username, message));
		}
	}
	@OnClose
	public void handleClose(Session userSession) {
		chatroomUsers.remove(userSession);
	}
	private String buildJsonData(String username, String message) {
		JsonObject jsonObejct = Json.createObjectBuilder().add("message", username+": "+message).build();
		StringWriter stringWriter = new StringWriter();
		try(JsonWriter jsonWriter = Json.createWriter(stringWriter)){
			jsonWriter.write(jsonObejct);
		}
		System.out.println(stringWriter.toString());
		return stringWriter.toString();
	}
}
