<%@page import="JavaBeans.States"%>
<%@page import="services.Base64Decoder"%>
<%@page import="models.Hobbies"%>
<%@page import="models.Food"%>
<%@page import="models.Animals"%>
 
<%@page import="java.util.List"%>
<%@page import="models.GetStateNCities"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

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
   
    http.open('get', 'Cities.jsp?state=' + st);
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
<body><jsp:include page="DefaultTop.jsp"></jsp:include>
<div class="container jumbotron">
     
 <div class="row"> <br/>

<div class="col-md-6"><center><h2>Update Your Profile</h2></center>
 <form id="frm" action="updateProfile" method="post">
									 <table class="tblform"><tr>
									 <td>
									  <table class="tblform">
									 <tr><td>UserName</td>
                <td><input type="text" class="form-control"  name="username" value="<%=request.getAttribute("username").toString().trim() %>" required></td></tr>
            <tr><td>UserID</td>
                <td><input type="text"  class="form-control"  name="userid" required value="<%=request.getAttribute("userid").toString().trim() %>">
               
                </td></tr>
                       
                
                   
									 <%
									 GetStateNCities obj=new GetStateNCities();
									 obj.getStates();
									 List<States> lst=obj.getLststate();
									 %>
									  <tr>
									 <td>State
									 </td>
									 <td> 
									 <select required name="state" class="form-control" onchange="makeGetRequest(this.value)">
									 <option value=""><--select--></option>
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
									 <td> 
									<div id="cities"></div>
									 </td>
									 </tr>
							<%
									 Animals obj1=new Animals();
									 obj1.getAnimalList1();
									 List<Animals> lstanimal=obj1.getLstanimal();
									 %>		  
	           <tr> <td>  Animals</td>
		            <td>
		           <select required name="animal" class="form-control" >
									 <option value=""><--select--></option>
										<%for(int i=0;i<lstanimal.size();i++)
											{%>
									 <option value="<%=lstanimal.get(i).getAnimalId() %>"><%=lstanimal.get(i).getAnimalName() %></option>											
											<%}%>															  
									 </select>
									 
									   </td>
            </tr>
								 
									 
<%
									 Food objfood=new Food();
									 objfood.getFoodList1();
									 List<Food> lstfood=objfood.getLstfood();
									 System.out.println("food="+lstfood.size());
									 %>		  
	           <tr> <td>  Food</td>
		            <td>
		           <select required name="food" class="form-control" >
									 <option value=""><--select--></option>
										<%for(int i=0;i<lstfood.size();i++)
											{%>
									 <option value="<%=lstfood.get(i).getFoodId() %>"><%=lstfood.get(i).getFoodItems() %></option>											
											<%}%>															  
									 </select>
									 
									   </td>
            </tr>
            								 
<%
 Hobbies objhobby=new Hobbies();
objhobby.getHobbiesList1();
List<Hobbies> lsthobby=objhobby.getLsthobby();
System.out.println("hobby="+lsthobby.size());
									 %>		  
<tr> <td>  Hobby</td>
 <td>
 <select required name="hobby" class="form-control" >
<option value=""><--select--></option>
<%for(int i=0;i<lsthobby.size();i++)
{%>
<option value="<%=lsthobby.get(i).getHobbyId() %>"><%=lsthobby.get(i).getHobbyText() %></option>											
<%}%>															  
</select></td>
            </tr>
</table></td></tr>
 

									 <tr>
									 <td colspan="2"><input type="submit" value="Submit" class="btn btn-primary"/>
									 </td></tr>
									 </table>
									 </form></div>
									 
									 <div class="col-md-3">
<br/><img src="images/updateProfile.png" width="100%"/>
</div></div>
</body>
</html>