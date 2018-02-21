<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chat Room</title>
</head>
<body style="margin-left: 30px;">
	<h3>${model.username }
		| <a href="chat/logout" style="text-decoration: none">Logout</a>
	</h3>
	<i class="glyphicon glyphicon-user"></i>
	<span id="countUser"></span>
	<br>
	<textarea rows="10" cols="45" disabled="disabled" id="frameChat"></textarea>
	<textarea rows="10" cols="10" disabled="disabled" id="usersChat"></textarea>
	<br/>
	<input type="text" id="messageText" />
	<button onclick="sendMessage();">Send</button>
</body>
<script>
	var websocket = new WebSocket("ws://localhost:8080/MyChatApp/mychatendpoint");
	websocket.onmessage = function processMessage(message) {
		var jsonData = JSON.parse(message.data);
		console.log(jsonData);
		if(jsonData.users!=null){
			usersChat.value = "";
			var i =0;
			while(i<jsonData.users.length) usersChat.value += jsonData.users[i++] +"\n";
		}
		if (jsonData.count != null) {
			$('#countUser').html(jsonData.count);
		}
		if (jsonData.system != null) {
			frameChat.value += jsonData.system + "\n";
		} else if (jsonData.message != null)
			frameChat.value += jsonData.message + "\n";
	}

	function sendMessage() {
		var message = $("#messageText").val();
		websocket.send(message);
		$("#messageText").val("");
	}
</script>
</html>