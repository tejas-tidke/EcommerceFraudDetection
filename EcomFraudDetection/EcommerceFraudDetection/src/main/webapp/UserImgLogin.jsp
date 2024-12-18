<%@page import="services.Base64Encoder"%>

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

function makeGetRequest(txt) { 
	//alert('in ajax');
    http.open('get', 'getProductsSearch?txt='+txt );
    http.onreadystatechange = processResponse;
    http.send(null);
}

function processResponse() {
    if(http.readyState == 4){
        var response = http.responseText;
        document.getElementById('search').innerHTML = response;
    }
}
 
</script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8" />
    <meta name="keywords" content="Shopping Hub Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
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
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <!-- Owl-Carousel-CSS -->
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
<jsp:include page="Top.jsp"></jsp:include>
<% try{ response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("home");
}
%> 
<div class="container">
  <main id="main">

    
    <!-- ======= About Us Section ======= -->
    <section id="login" class="about">
      <div class="container" data-aos="fade-up">

        <div class="section-title">
          <h2>Image Based Login</h2>
          <h5>Userid : <%=request.getAttribute("userid").toString().trim() %></h5>
        </div>
 
        <div class="row content">
         
            <c:forEach var="userdsc" items="${lst}">
              <div class="col-md-4">
           <a href="ImgAuth?id=${userdsc.id }"> <img src="${userdsc.imgPath }" class="img-thumbnail" style="max-height: 200px;height:200px" width="80%"/></a>
          </div>
		 
		</c:forEach>
        
        </div>

      </div>
    </section><!-- End About Us Section -->

     <%}

catch(Exception ex)
{
	
} 
 %> 

</body>

</html>