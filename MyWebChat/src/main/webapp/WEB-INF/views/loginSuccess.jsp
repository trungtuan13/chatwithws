<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<textarea rows="10" cols="45" id="messagesTextArea" readonly="readonly"></textarea>
<input type="text" id="messageText" size="50" /><button onclick="sendMessage();">Send</button>
</body>

<script type="text/javascript">
	var websocket = new WebSocket("ws://localhost:8080/MyWebChat/mychat");
	websocket.onmessage = function processMessage(message){
		var jsonData = JSON.parse(message.data);
		if(jsonData.message != null) messagesTextArea.value += jsonData.message +"\n";
	}
	function sendMessage(){
		websocket.send(messageText.value);
		messageText.value ="";
	}
</script>
</html>

