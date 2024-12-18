<%@page import="java.util.List"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="Top.jsp"></jsp:include>
<% try{ response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("home");
}
%>
<div class="container-fluid">
  <div class="jumbotron"> 

     
<div class="row">

<div class="col-md-12"> <h2>Order Details<input type="button" value="Back" class="btn btn-primary" onclick="window.history.back();"/></h2>
    
 <table class="table table-bordered table-responsive">
 <tr>
  
 <th>Order No</th>
 <th>Product Name</th>
 <th>Size</th>
 <th>Quantity</th>
 <th>Price in Rs.</th>
 <th>Total Price in Rs.</th>
 
 </tr>
 <c:forEach var="userdsc" items="${lst}">
		<tr>
		 
		<td>${userdsc.getOrderno() }</td> 
		<td>${userdsc.getTitle() }</td> 
		<td>${userdsc.getSize() }</td> 
			<td>${userdsc.getQuantity()}</td>
			<td>Rs.${userdsc.getPrice()}/-</td>
			<td>Rs.${userdsc.getTotalprice()}/-</td>
			 
		</tr>
		</c:forEach></table>
 </div>
</div>
</div>
<%}
catch(Exception ex)
{
	System.out.println("err="+ex.getMessage());
} %>

</div>
</body>
</html>