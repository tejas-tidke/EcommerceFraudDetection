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
<div class="container"><br/><br/>
<%
if(request.getParameter("type").toString().trim().equals("Reg"))
{
	%><h2 class="h2">Your Registration Done Successfully....</h2>
	<br/>
	<a href="home">Home</a>
<%}
if(request.getParameter("type").toString().trim().equals("UserReg"))
{
	%><h2 class="h2">Your Registration Done Successfully....</h2>
	<br/>
	<a href="home">Home</a>
<%}

else if(request.getParameter("type").toString().trim().equals("ImgRegHobby"))
{
	%><h2 class="h2">Hobby Image Uploaded Successfully....</h2>
	<br/>
	<a href="/uploadHobbyImg">continue...</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ImgRegCity"))
{
	%><h2 class="h2">City Image Uploaded Successfully....</h2>
	<br/>
	<a href="/uploadCityImg">continue...</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ImgRegFood"))
{
	%><h2 class="h2">Food Image Uploaded Successfully....</h2>
	<br/>
	<a href="/uploadFoodImg">continue...</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ImgAnimalHobby"))
{
	%><h2 class="h2">Hobby Image Uploaded Successfully....</h2>
	<br/>
	<a href="/uploadAnimalImg">continue...</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ImgRegAnimal"))
{
	%><h2 class="h2">Animal Image Uploaded Successfully....</h2>
	<br/>
	<a href="/uploadAnimalImg">continue...</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ImgRegFood"))
{
	%><h2 class="h2">Food Item Image Uploaded Successfully....</h2>
	<br/>
	<a href="/uploadFoodImg">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("idkeyRev"))
{
	%><h2 class="h2">Profile Password sent on email Successfully....</h2>
	<br/>
	<a href="userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("updateProfile"))
{
	%><h2 class="h2">Profile Updated Successfully....</h2>
	<br/>
	<a href="userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("PathAdmin"))
{
	%><h2 class="h2">Pathology Admin Registration Done Successfully....</h2>
	<br/>
	<a href="adminHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("fraudNotDetected"))
{
	%><h2 class="h2">Fraud Not Detected....</h2>
	<br/>
	<a href="userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ReviewReg"))
{
	%><h2 class="h2">Review Submitted Successfully....</h2>
	<br/>
	<a href="userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("AssociationMining"))
{
	%><h2 class="h2">Association mining on Orders Done Successfully....</h2>
	<br/>
	<a href="adminHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("OrderProcess"))
{
	%><h2 class="h2">Order Processed Successfully....</h2>
	<br/>
	<a href="adminHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ProdRating"))
{
	%><h2 class="h2">Rating Submitted Successfully....</h2>
	<br/>
	<a href="userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("placeOrder"))
{
	%><h2 class="h2">Order Placed Successfully....</h2>
	<br/>
	<a href="userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("Doctor"))
{
	%><h2 class="h2">Doctor Registration Done Successfully....</h2>
	<br/>
	<a href="adminHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("habit"))
{
	%><h2 class="h2">Eating Habit Registration Done Successfully....</h2>
	<br/>
	<a href="adminHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("RegUserDisease"))
{
	%><h2 class="h2">Disease Registered Successfully....</h2>
	<br/>
	<a href="/userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("RegUserReading"))
{
	%><h2 class="h2">Readings Registered Successfully....</h2>
	<br/>
	<a href="/userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("DiseaseDataSet"))
{
	%><h2 class="h2">New Disease Registered Successfully....</h2>
	<br/>
	<a href="ViewJobs.jsp">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ChangePass"))
{
	%><h2 class="h2">Password Changed Successfully....</h2>
	<br/>
	<a href="<%=session.getAttribute("utype").toString().trim() %>Home">continue...</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("passEmail"))
{
	%><h2 class="h2">New Password has been sent on your registered email id  Successfully....</h2>
	<br/>
	<a href="home">continue...</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("Prev"))
{
	%><h2 class="h2">Preventive Measures Registered  Successfully....</h2>
	<br/>
	<a href="adminHome">continue...</a>
<%
}
%>
</div>
</body>
</html>