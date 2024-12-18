<%@page import="JavaBeans.*"%>
<%@page import="java.util.List"%>
<%@page import="JavaBeans.Categories"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="zxx">

<!DOCTYPE html>
<html lang="zxx">

<head>
    <title>Fashion Hub  </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8" />
    <meta name="keywords" content="Fashion Hub Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
	SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
    <script>
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <!-- Custom Theme files -->
    <link href="css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
    <!-- shop css -->
    <link href="css/shop.css" type="text/css" rel="stylesheet" media="all">
    <!-- shop css -->
    <link href="css/style.css" type="text/css" rel="stylesheet" media="all">
    <!-- font-awesome icons -->
    <link href="css/fontawesome-all.min.css" rel="stylesheet">
    <!-- //Custom Theme files -->
    <!-- online-fonts -->
    <link href="//fonts.googleapis.com/css?family=Elsie+Swash+Caps:400,900" rel="stylesheet">
    <link href="//fonts.googleapis.com/css?family=Source+Sans+Pro:200,200i,300,300i,400,400i,600,600i,700,700i,900,900i" rel="stylesheet">
    <!-- //online-fonts -->
</head>

<body>
    <%
try
{  response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("home");
}
%>	 <!-- header -->
    <header>
        <div class="container">
            <!-- top nav -->
            <nav class="top_nav d-flex pt-3 pb-1">
                <!-- logo -->
              <h1>
                    <a class="navbar-brand" href="home">Secured Ecommerce System using ML
                    </a>
                </h1>
                <!-- //logo -->
                
            </nav>
            <!-- //top nav -->
            <!-- bottom nav -->
            <nav class="navbar navbar-expand-lg navbar-light justify-content-center">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mx-auto text-center">
                        <li class="nav-item">
                            <a class="nav-link" href="<%=session.getAttribute("utype").toString().trim() %>Home">Home
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                         <%if(session.getAttribute("utype").toString().trim().equals("admin"))
                                	{
                                	%>
                                	
                                	<li class="nav-item">
                            <a class="nav-link" href="PendingOrders">Pending Orders</a>
                        </li><li class="nav-item">
                            <a class="nav-link" href="OrdersAdmin">Orders</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="addNewProd.jsp">Add New Product</a>
                        </li>
                        <li class="nav-item"><a href="/uploadCityImg">Upload City Images</a></li>
              <li class="nav-item"><a href="/uploadAnimalImg">Upload Animal Images</a></li>
              <li class="nav-item"><a href="/uploadHobbyImg">Upload Hobby Images</a></li>
               <li class="nav-item"><a href="/uploadFoodImg">Upload Food Images</a></li>
                     <!--   <li class="nav-item">
                            <a class="nav-link" href="ReviewsReport.jsp">Reviews Statistics</a>
                        </li>
                          <li class="nav-item">
                            <a class="nav-link" href="PreferredCategoriesReport.jsp">Preferred Categories Report</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="MonthlyProdStats.jsp">Monthly Product Statistical Report</a>
                        </li>
                         <li class="nav-item">
                            <a class="nav-link" href="MonthlyCategoryStats.jsp">Monthly Product Category Statistical Report</a>
                        </li>
                         <li class="nav-item">
                            <a class="nav-link" href="http://localhost:88/PredictiveAnalytics/AssociationMining.py">Apply Association Mining</a>
                        </li>-->
                                	<%}else if(session.getAttribute("utype").toString().trim().equals("user"))
                                	{
                                	%>
                       <%
                        Categories category=new Categories();
                        List<String> lstcate=category.getCategories();
                        for(int i=0;i<lstcate.size();i++)
                        {
                        	 List<String> lstsubcate=category.getSubCategories(lstcate.get(i).trim());
                        %>
                        <li class="nav-item dropdown has-mega-menu" style="position:static;">
                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"><%=lstcate.get(i).toString().trim() %></a>
                            <div class="dropdown-menu" style="width:100%">
                                <div class="px-0 container">
                                    <div class="row">
                                        
                                          <%for(int j=0;j<lstsubcate.size();j++)
                                        	  {%><div class="col-md-4">
                                            <a class="dropdown-item" href="showProdSess?category=<%=lstcate.get(i).trim() %>&subcategory=<%=lstsubcate.get(j).trim() %>"><%=lstsubcate.get(j).trim() %></a>
                            </div>                 <%} %> 
                                       
                                      
                                    </div>
                                </div>
                            </div>
                        </li><%} %>
                        <li class="nav-item">
                            <a class="nav-link" href="MyOrders">My Orders</a>
                        </li>
                         <li class="nav-item">
                            <a class="nav-link" href="editProfileAuth">Edit Profile</a>
                        </li>
                         
                        <li class="nav-item">
                            <a class="nav-link" href="RevIdKeyAuth">Recover Profile Password</a>
                        </li>
                        <%
                        }%>
                        
                        <li class="nav-item">
                            <a class="nav-link" href="changePass">Change Password</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="logout">Logout</a>
                        </li>
                         
                    </ul>
                </div>
            </nav>
            <!-- //bottom nav -->
        </div>
        <!-- //header container -->
    </header>
    <!-- //header -->
	<!-- inner banner -->
	<div class="ibanner_w3 pt-sm-5 pt-3">
		<h4 class="head_agileinfo text-center text-capitalize text-center pt-5">
			<!--  <span>f</span>ashion
			<span>h</span>ub</h4>-->
	</div>
	<!-- //inner banner -->
    <!-- breadcrumbs -->
  
    <!-- //breadcrumbs -->
    <div class="container-fluid">
    <div class="CartPanel">
    <div class="row">
    <div class="col-md-9">
      Logged in as <%=session.getAttribute("username").toString() %>( <%=session.getAttribute("utype").toString() %>)
  <%=session.getAttribute("sessionid").toString() %>
  <%if(session.getAttribute("utype").toString().trim().equals("user")){ %>
  
   <%} %>
    
    </div>
    <div class="col-md-3">
   <%if(session.getAttribute("utype").toString().trim().equals("user"))
                                	{
                                	%>
    <% int totalItems=0;
    Cart cart=new Cart();
    totalItems=cart.totalItemsInCart(session.getAttribute("userid").toString().trim());
    %>
   <a href="ShowCart"> Total Items in your Cart : <i class="fa fa-cart"></i> <span><%=totalItems %></span> </a>
   <%} %>
   </div>
  </div>  </div>
    </div>
    <div class="container ebox"> 
</body>

</html>
 
    <%}catch(Exception ex)
{
    	System.out.println("err="+ex.getMessage());
    	 
}%>
 
</body>

</html>