 
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="soham"%>
 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<title>Products</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
 
</head>
<body>
<%System.out.println("session page") ; %>
  
 
<%try{ %>
 


<soham:forEach items="${stf}" var="rec">
<div class="tabs tabs-style-line pt-md-5">
           
            <div role="tabpanel" class="tab-pane fade show active" id="women" aria-labelledby="women-tab">
                    <div id="owl-demo" class="owl-carousel text-center">
                        <div class="item">
                            <!-- card -->
                            <div class="card product-men p-3">
                                <div class="men-thumb-item">
                                    <img src="${rec.imgUrl}" alt="img" class="card-img-top">
                                    <div class="men-cart-pro">
                                        <div class="inner-men-cart-pro">
                                            <a href="prodDetails?${rec.prodId}" class="link-product-add-cart">Quick View</a>
                                        </div>
                                    </div>
                                </div>
                                <!-- card body -->
                                <div class="card-body  py-3 px-2">
                                    <h5 class="card-title text-capitalize">${rec.title}</h5>
                                    <div class="card-text d-flex justify-content-between">
                                        <p class="text-dark font-weight-bold">Rs. ${rec.price}</p>
                                         
                                    </div>
                                </div>
                                <!-- card footer -->
                                <div class="card-footer d-flex justify-content-end">
                                    <form action="#" method="post">
                                        <input type="hidden" name="cmd" value="_cart">
                                        <input type="hidden" name="add" value="1">
                                        <input type="hidden" name="hub_item" value="Self Design Women's Tunic">
                                        <input type="hidden" name="amount" value="28.00">
                                        <button type="submit" class="hub-cart phub-cart btn">
                                            <i class="fa fa-cart-plus" aria-hidden="true"></i>
                                        </button>
                                        <a href="#" data-toggle="modal" data-target="#myModal1"></a>
                                    </form>
                                </div>
                            </div>
                            <!-- //card -->
                        </div>
                      
                            <div class="product-men p-3 text-center">
                                <img src="images/p2.png" class="img-responsive" alt="" />
                                <a href="women.html" class="btn btn-lg bg-info text-white">view more</a>
                            </div>
                        </div>
                    </div>
                </div>
       
</soham:forEach>
 
<%}catch(Exception ex){
	System.out.println("err in session="+ex.getMessage());
	
} %>
</body>
</html>