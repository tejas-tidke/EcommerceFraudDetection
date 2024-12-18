<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> </title>
</head>
<body>
<jsp:include page="Top.jsp"></jsp:include>
<% try{ response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
 
  %><br/>
  <div class="container jumbotron ebox">
  <center><h2>Profile Password Authentication</h2></center>
  <div class="row">
  <div class="col-md-6">
  <img src="images/otp.png" width="70%"/>
  </div>
  <div class="col-md-6">
  <form action="IdkeyAuthEdit1" method="post">
  <table class="tblform">
  <tr>
  <td>Userid</td>
  <td>
  <input type="text" value="<%=session.getAttribute("userid").toString().trim() %>" name="userid" class="form-control" required/>
    
  </td>
  </tr>
   <tr>
  <td>Profile Password</td>
  <td>
  <input type="password" name="idkey" class="form-control" required/>
    </td>
  </tr>
   <tr>
  <td colspan="2"><input type="submit" class="btn btn-primary" value="Submit"/>  </td>   
  </tr>
  </table>
  </form>
  </div></div></div>
  <%
}
catch(Exception ex)
{
	System.out.println("errin jsp="+ex.getMessage());
}
%>
</body>
</html>