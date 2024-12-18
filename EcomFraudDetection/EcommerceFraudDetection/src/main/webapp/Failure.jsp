<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="DefaultTop.jsp"></jsp:include>
<div class="container">
<%
if(request.getParameter("type").toString().trim().equals("Reg"))
{
	%><h2>Registration Failed!!</h2>
	<br/>
	<a href="index.jsp">Home</a>
<%}else if(request.getParameter("type").toString().trim().equals("UserReg"))
{
	%><h2>Registration Failed!!</h2>
	<br/>
	<a href="home">Home</a>
<%}
else if(request.getParameter("type").toString().trim().equals("Auth"))
{
	%><h2>Authentication Failed!!</h2>
	<br/>
	<a href="index.jsp">Home</a>
<%
}else if(request.getParameter("type").toString().trim().equals("IdkeyAuth"))
{
	%><h2>Authentication Failed!!</h2>
	<br/>
	<a href="userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("DeactiveAuth"))
{
	%><h2>Your account has been deactivated for security purpose. Please login with correct password to activate your account!!</h2>
	<br/>
	<a href="index.jsp">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("fraudDetected"))
{
	%><h2>Fraud Detected!!</h2>
	<br/>
	<a href="logout">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("limitextended"))
{
	%><h2>Amount is greater than transaction limit!!</h2>
	<br/>
	<a href="userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("RegUserReading"))
{
	%><h2>Readings Registration Failed!!</h2>
	<br/>
	<a href="userHome">Home</a>
<%
}else if(request.getParameter("type").toString().trim().equals("ChangePass"))
{
	%><h2 class="h2">Password Changing Failed!!</h2>
	<br/>
	<a href="userHome">continue...</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("passEmail"))
{
	%><h2 class="h2">Password Recovery Failed!!</h2>
	<br/>
	<a href="home">continue...</a>
<%
}else if(request.getParameter("type").toString().trim().equals("placeOrderamt"))
{
	%><h2 class="h2">Amount is greater than maximum purchase amt!!</h2>
	<br/>
	<a href="userHome">continue...</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("AddCard"))
{
	%><h2 class="h2">Card Verification Failed!! Please check all details and try again!!</h2>
	<br/>
	<a href="userHome">continue...</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("placeOrder"))
{
	%><h2 class="h2">Something went wrong!!</h2>
	<br/>
	<a href="userHome">continue...</a>
<%
}
%>
</div>
</body>
</html>