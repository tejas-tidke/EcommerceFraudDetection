<%@page import="JavaBeans.States"%>
<%@page import="JavaBeans.GetStateNCities"%>
<%@page import="JavaBeans.UserReg"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
 
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.min.css">
 <link rel="stylesheet" href="css/cust.css">

<title> </title>

<script language="Javascript" type="text/javascript">
 

function createRequestObject() {
    var tmpXmlHttpObject;
    if (window.XMLHttpRequest) {
            tmpXmlHttpObject = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        tmpXmlHttpObject = new ActiveXObject("Microsoft.XMLHTTP");
    }

    return tmpXmlHttpObject;
}


var http = createRequestObject();

function makeGetRequest(st) {
   // st=document.frm.state.value;
   
    http.open('get', 'Cities?state=' + st);
    http.onreadystatechange = processResponse;
    http.send(null);
}

function processResponse() {
    if(http.readyState == 4){
        var response = http.responseText;
        document.getElementById('cities').innerHTML = response;
    }
}
 
</script>
 
</head>
<% 
	List<UserReg> lstst=(ArrayList)request.getAttribute("std");
%>
<body><jsp:include page="Top.jsp"></jsp:include>
<% try{ response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("index.jsp");
}
 
String userid=String.valueOf(session.getAttribute("userid"));

if(!userid.equalsIgnoreCase("null")){	
	
session.setMaxInactiveInterval(10*60);

%>
  



 <div class="container">
<h2>My Profile</h2>
 
<form name="frm" method="post" action="updateUser"  >
 <div class="row">
 <div class="col-md-6">
<table>
	<tr><td>Userid</td>
	<td><input type="text" name="userid" class="form-control"  value='<%=lstst.get(0).getUserid() %>' disabled="disabled" required></td>
	</tr>
	<tr><td>User Name</td>
	<td><input type="text" name="name" class="form-control" value='<%=lstst.get(0).getName() %>' required></td>
	</tr>
		<tr><td>Profession</td>
	<td><input type="text" name="prof" class="form-control" value='<%=lstst.get(0).getProf() %>' required></td>
	</tr>
	  <tr><td>Maximum Purchase Amount</td>
	<td><input type="text" name="shopAmt" class="form-control" value='<%=lstst.get(0).getShopAmt()%>' required>
	<input type="hidden" name="prevshopAmt" class="form-control" value='<%=lstst.get(0).getShopAmt()%>' required>
	</td>
	</tr>
       <tr><td>Mobile Number</td>
       	<td><input type="text" name="mobile"  pattern="^\d{10}$" value='<%=lstst.get(0).getMobile()%>' class="form-control" required>
       	<input type="hidden" name="prevmobile"  pattern="^\d{10}$" value='<%=lstst.get(0).getMobile()%>' class="form-control" required>
       	</td></tr>
       <tr>
		<td>Email Address</td>       
       <td><input type="text" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"  value='<%=lstst.get(0).getEmail() %>' class="form-control" required>
       <input type="hidden" name="prevemail" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"  value='<%=lstst.get(0).getEmail() %>' class="form-control" required>
       </td>
       </tr>
       <tr>
	            <td>Address</td>
		            <td>
		            <textarea rows="5" cols="25"  value='<%=lstst.get(0).getAddress() %>' name="address" class="form-control" ><%=lstst.get(0).getAddress() %></textarea>
		            <input type="hidden" name="prevaddr"   value='<%=lstst.get(0).getAddress()%>' class="form-control" required>          
		            </td>
            </tr>
          
             
</table></div>
<div class="col-md-6">
<table>
             
		 
          
									 <%
									 GetStateNCities obj=new GetStateNCities();
									 obj.getStates();
									 List<States> lst=obj.getLststate();
									 %>
									<!--   <tr><td>Security Question</td>
                <td>
                <select name="sques" class="form-control"> 
                <option value='<%=lstst.get(0).getSques() %>'><%=lstst.get(0).getSques() %></option>
                <option value="In what city were you born?">In what city were you born?</option>
                <option value="What is your mother's maiden name?">What is your mother's maiden name?</option>
                <option value="What is the name of your favorite pet?">What is the name of your favorite pet?</option>
                <option value="What is the name of your first school?">What high school did you attend?</option>
                <option value="What was your favorite food as a child?">What was your favorite food as a child?</option>
                <option value="Where did you meet your spouse?">Where did you meet your spouse?</option>
                </select>
                </td>
                </tr>
                 <tr><td>Answer</td>
                <td>
                  <tr>-->
									  <tr>
									 <td>State
									 </td>
									 <td>    <input type="hidden" name="sques" value="<%=lstst.get(0).getSques() %>"/>
                <input type="hidden" class="form-control"  name="ans" value='<%=lstst.get(0).getAns() %>' required> 
       
									 <select required name="state" class="form-control" onchange="makeGetRequest(this.value)">
									 <option value="<%=lstst.get(0).getState() %>"><%=lstst.get(0).getState() %></option>
										<%for(int i=0;i<lst.size();i++)
											{%>
									 <option value="<%=lst.get(i).getState() %>"><%=lst.get(i).getState() %></option>											
											<%}%>															  
									 </select>
									 </td>
									 </tr>
									   <tr>
									 <td>City
									 </td>
									 <td> <input type="hidden" name="city1" value="<%=lstst.get(0).getCity() %>"/>
									<div id="cities"><%=lstst.get(0).getCity() %></div>
									 </td></tr>							 
       <tr>
       	<td>Date Of Birth</td>
       	<td><input type="date" name="dob" value='<%=lstst.get(0).getDob() %>'   class="form-control"></td>
       </tr>
      
         
          
            <tr>
	<td colspan="2"><input type="submit" value="Submit" class="btn btn-primary" ></td>
	</tr>
	
	</table> </div>
	</div> 
</form>
</div> 
 
</div>
<%
}
else{
	%>
	<h2>Invalid Session...Login again</h2>
	<br>
	<a href="index.jsp">Login</a>
	
	<%
}}
catch(Exception ex)
{
System.out.println("Err="+ex.getMessage());
}
%>
 
</body>
</html>