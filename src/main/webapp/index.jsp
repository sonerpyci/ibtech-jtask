<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World Java EE</title>
</head>
<body>
    <h1>Enter Your Xml</h1>
  
    
    <textarea id="xmlText" class="text" cols="86" rows ="20" name="xmlText" form="xmlText"></textarea>

	<form action="MainServlet" id="xmlForm" name="xmlForm" method="post">
	   <input type="submit" value="Submit" class="submitButton">
	</form>
</form>
</body>
</html>