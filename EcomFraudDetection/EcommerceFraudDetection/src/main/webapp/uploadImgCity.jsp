
 
<%@page import="JavaBeans.States"%>
<%@page import="models.GetStateNCities"%>
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
 <script lang="javascript/text">
 var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];    
 function ValidateSingleInput(oInput) {
     if (oInput.type == "file") {
         var sFileName = oInput.value;
          if (sFileName.length > 0) {
             var blnValid = false;
             for (var j = 0; j < _validFileExtensions.length; j++) {
                 var sCurExtension = _validFileExtensions[j];
                 if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                     blnValid = true;
                     break;
                 }
             }
              
             if (!blnValid) {
                 alert("Sorry, Allowed extensions are: " + _validFileExtensions.join(", "));
                 oInput.value = "";
                 return false;
             }
         }
     }
     return true;
 }
 </script>
</head>
<body>
<jsp:include page="Top.jsp"></jsp:include>
<% try{ response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("/");
}
%>
<div class="container jumbotron">
  <div class="jumbotron"> 

     
<div class="row">

<div class="col-md-5"> <h2>Upload City Images</h2>
   <div class="form-group"> 
<form method="post" name="frm" action="RegCityImg" enctype="multipart/form-data">
<table class="tblform">
  
                
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
              
 <tr><td>File</td><td>
    <input type="file" name="file"  class="form-control" required onchange="ValidateSingleInput(this);" ></input>
 </td></tr> 
 <tr>
 <td colspan="2">
  
 <input type="submit" value="Submit" class="btn btn-primary"/>
 </td>
 </tr>
</table></form>
 
</div></div>
 <div class="col-md-6">
 <img src="images/hobbyupload.png" width="70%" class="img-responsive"/>
 </div>
</div>
</div>
<%}
catch(Exception ex)
{
	
} %>

</div>
</body>
</html>