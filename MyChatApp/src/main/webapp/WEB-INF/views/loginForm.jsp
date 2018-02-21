<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<form:form commandName="model" action="" method="POST">
	<table>
		<tr>	
			<td>Username: </td>
			<td><form:input path="username" /></td>
		</tr>
			<tr>	
			<td>Password: </td>
			<td><form:password path="password" /></td>
		</tr>
		<tr><td></td><td><button type="submit">Sign in</button></td></tr>
	</table>	
</form:form>
</body> 
</html>