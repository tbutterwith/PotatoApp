<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Potato Database</title>
</head>
<body>
Potato Login
<form name="loginForm" action="Login" method="post">
<% if(request.getParameter("r") != null) {
%>
<b>Incorrect username or password</b><br />
<%
	}
	%>
<input type="text" name="username" /><br />
<input type="password" name="password" /><br />
<input type="submit" />
</form>
</body>
</html>