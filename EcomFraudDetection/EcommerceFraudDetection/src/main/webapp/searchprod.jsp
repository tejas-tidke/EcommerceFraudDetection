
<%@page import="JavaBeans.Cart"%>
<%@page import="JavaBeans.Categories"%>
<%@page import="JavaBeans.States"%>
<%@page import="JavaBeans.GetStateNCities"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
  
</head>
<body>
     <%
          String subcate="Products",category="NA";
          try{
        	   subcate=request.getAttribute("subcategory").toString();
        	   category=request.getAttribute("category").toString();
          }catch(Exception exx){}
          %>
 <%try{ %>
<div class="container-fluid">
 
<div class="row">
<c:forEach items="${stf}" var="rec">
<div class="col-md-3">
           
          
                            <!-- card -->
                            <div  class="card product-men p-3">
                                <div class="men-thumb-item">
                                <div class="card-div" style="background-image:url('${rec.imgUrl}')">
                                   <!--  <img src="${rec.imgUrl}" alt="img" class="card-img-top img-responsive img-thumbnail">
                                     --></div>
                                    <div class="men-cart-pro">
                                        <div class="inner-men-cart-pro">
                                            <a href="prodDetails?prodnm=${rec.title}&brand=${rec.brand}&prodId=${rec.prodId}&subcategory=<%=subcate %>&category=<%=category %>" class="link-product-add-cart">Quick View</a>
                                        </div>
                                    </div>
                                </div>
                                <!-- card body -->
                                <div class="card-body  py-3 px-2">
                                    <h5 class="card-title text-capitalize">${rec.title}</h5>
                                    <div class="card-text d-flex justify-content-between">
                                        <p class="text-dark font-weight-bold price-style">Rs. ${rec.price}</p>
                                         
                                    </div>
                                </div>
                                <div class="card-footer d-flex justify-content-end">
                                
                                    <form action="AddToCart" method="post">
                                     <input type="hidden" name="prodId" value="${rec.prodId}"> 
                                        <input type="hidden" name="title" value="${rec.title}">
                                         <input type="hidden" name="price" value="${rec.price}">
                                         <input type="hidden" name="page" value="user.jsp">
                                         <input type="hidden" name="category" value="<%=category%>">
                                         <input type="hidden" name="subcategory" value="<%=subcate%>">
                                       <table><tr><td>   Select Size</td> <td> <select name="size" class="form-control">
                                         <option>S</option>
                                         <option>M</option>
                                         <option>L</option>
                                         <option>XL</option>
                                         <option>XXL</option>
                                         </select></td><td>
                                        <button type="submit" class="hub-cart phub-cart btn">
                                            <i class="fa fa-cart-plus" aria-hidden="true"></i>
                                        </button>
                                        <a href="#" data-toggle="modal" data-target="#myModal1"></a>
                                 </td></tr> </table>  
                                    </form>
                                     
                                </div>
                            </div>
                            <!-- //card -->
                       
                    </div>
                
       
</c:forEach>
 
<%}catch(Exception ex){
	System.out.println("err in session="+ex.getMessage());
	
} %>
            
          </div></div>
    
 
</body>
</html>