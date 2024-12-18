<%@page import="JavaBeans.JavaFuns"%>
<%@page import="java.util.Vector"%>
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
	response.sendRedirect("index.jsp");
}
JavaFuns jf=new JavaFuns();
  Vector v=jf.getValue("select distinct(mainCategory) from categories", 1);
  Vector v1=jf.getValue("select distinct(category) from categories", 1);
  Vector v2=jf.getValue("select distinct(brand) from products", 1);
%>
<div class="container jumbotron"> 
<h2>New Product Registration</h2>
<form method="post" action="newProd" enctype="multipart/form-data">
<table>
<tr><td>Title</td>
<td>
<input type="text" class="form-control" name="title" required/>
</td>
</tr>
<tr><td>Description</td>
<td>
<textarea  name="proddesc" class="form-control" required></textarea>
</td>
</tr>
 <tr><td>Culture  </td>
                <td>
                
                Indian <input type="radio" name="culture" value="Indian"   checked="true" required /> 
Western <input type="radio" name="culture" value="Western"  required/> 
                
                </td>
                </tr>
<tr><td>Category</td>
<td>
<select name="category" class="form-control" required> 
<%for(int i=0;i<v.size();i++){ %>
<option value="<%=v.elementAt(i).toString().trim() %>"><%=v.elementAt(i).toString().trim() %></option>
<%} %></select>
 
</td>
</tr>
<tr><td>Product Category</td>
<td>
<select name="subcategory" class="form-control" required> 
<%for(int i=0;i<v1.size();i++){ %>
<option value="<%=v1.elementAt(i).toString().trim() %>"><%=v1.elementAt(i).toString().trim() %></option>
<%} %></select>
 
</td>
</tr>
<tr><td>Brand</td>
<td>
<select name="brand" class="form-control" required> 
<%for(int i=0;i<v2.size();i++){ %>
<option value="<%=v2.elementAt(i).toString().trim() %>"><%=v2.elementAt(i).toString().trim() %></option>
<%} %></select>
 
</td>
</tr>
<tr><td>Price</td>
<td>
<input type="text" class="form-control" name="price" required/> 
</td>
</tr>
<tr><td>product image</td>
<td>
<input type="file" class="form-control" name="file" required/> 
</td>
</tr>
<tr><td colspan="2"><input type="submit" value="Submit"/>
</td>
</tr>
</table>

</form></div>
<%}
catch(Exception ex)
{
	
}
%>
</body>
</html>