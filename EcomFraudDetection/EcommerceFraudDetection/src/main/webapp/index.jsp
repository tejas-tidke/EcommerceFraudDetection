<%@page import="JavaBeans.*"%>
<%@page import="JavaBeans.States"%>
<%@page import="java.util.List"%>
<%@page import="JavaBeans.GetStateNCities"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="soham"%>
 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 

<!DOCTYPE html>
<html lang="zxx">

<head>
    <title>Shopping Hub </title>
   
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

function makeGetRequest1(st) { 
	//alert('in ajax');
    http.open('get', 'Cities.jsp?state='+st );
    http.onreadystatechange = processResponse11;
    http.send(null);
}

function processResponse11() {
    if(http.readyState == 4){
        var response = http.responseText;
        document.getElementById('cities').innerHTML = response;
    }
}
 
</script>     
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

function makeGetRequest() { 
	//alert('in ajax');
    http.open('get', 'getProducts' );
    http.onreadystatechange = processResponse;
    http.send(null);
}

function processResponse() {
    if(http.readyState == 4){
        var response = http.responseText;
        document.getElementById('products').innerHTML = response;
    }
}
 
</script>
<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function() {
    var elements = document.getElementsByName("pass");
    for (var i = 0; i < elements.length; i++) {
        elements[i].oninvalid = function(e) {
            e.target.setCustomValidity("");
            if (!e.target.validity.valid) {
                e.target.setCustomValidity("password must be of minimum eight characters, at least one letter, one number and one special character!!");
            }
        };
        elements[i].oninput = function(e) {
            e.target.setCustomValidity("");
        };
    }
})

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
   
   
   <!-- Start WOWSlider.com HEAD section --> <!-- add to the <head> of your page -->
	<link rel="stylesheet" type="text/css" href="engine1/style.css" />
	<script type="text/javascript" src="engine1/jquery.js"></script>
	<!-- End WOWSlider.com HEAD section -->
    
</head>

<body onload="makeGetRequest()">
 

    <!-- header -->
    <header>
        <div class="container headcss">
            <!-- top nav -->
            <nav class="top_nav d-flex pt-3 pb-1">
                <!-- logo -->
                <h1>
                    <a class="navbar-brand" href="home">Secured Ecommerce System using ML
                    </a>
                </h1>
                <!-- //logo -->
                <div class="w3ls_right_nav ml-auto d-flex">
                    <!-- search form -->
                     
                    <!-- search form -->
                    <div class="nav-icon d-flex">
                        <!-- sigin and sign up -->
                        <a class="text-dark login_btn align-self-center mx-3" href="#myModal_btn" data-toggle="modal" data-target="#myModal_btn">
                            Registration 
                        </a>
                         <!-- sigin and sign up -->
                        <a data-toggle="modal" data-target="#exampleModal1" class="text-dark login_btn align-self-center mx-3">
                           Login 
                        </a>
                       
                        <!-- sigin and sign up -->
                        <!-- shopping cart -->
                        <div class="cart-mainf">
                            <div class="hubcart hubcart2 cart cart box_1">
                                       <a data-toggle="modal" data-target="#exampleModal1" class="text-dark login_btn align-self-center mx-3">
                       <button class="btn top_hub_cart mt-1" type="submit" name="submit" value="" title="Cart">
                                        <i class="fas fa-shopping-bag"></i>
                                    </button>
                                </a>
                            </div>
                        </div>
                        <!-- //shopping cart ends here -->
                    </div>
                </div>
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
                            <a class="nav-link  active" href="home">Home
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
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
                                            <a class="dropdown-item" href="showProd?category=<%=lstcate.get(i).trim() %>&subcategory=<%=lstsubcate.get(j).trim() %>"><%=lstsubcate.get(j).trim() %></a>
                            </div>                 <%} %> 
                                       
                                      
                                    </div>
                                </div>
                            </div>
                        </li><%} %>
                         
                       <!--   <li class="nav-item">
                            <a class="nav-link" href="about.html">About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="blog.html">Blog</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="contact.html">Contact</a>
                        </li>-->
                    </ul>
                </div>
            </nav>
            <!-- //bottom nav -->
        </div>
        <!-- //header container -->
    </header>
    <!-- //header -->
    
    
    <div class="container-fluid bannerhome">
    
    
    
	<!-- Start WOWSlider.com BODY section --> <!-- add to the <body> of your page -->
	<div id="wowslider-container1">
	<div class="ws_images"><ul>
		<li><img src="data1/images/1.jpg" alt="1" title="1" id="wows1_0"/></li>
		<li><a href="http://wowslider.net"><img src="data1/images/2.jpg" alt="css slider" title="2" id="wows1_1"/></a></li>
		<li><img src="data1/images/31.jpg" alt="31" title="31" id="wows1_2"/></li>
	</ul></div>
	<div class="ws_bullets"><div>
		<a href="#" title="1"><span><img src="data1/tooltips/1.jpg" alt="1"/>1</span></a>
		<a href="#" title="2"><span><img src="data1/tooltips/2.jpg" alt="2"/>2</span></a>
		<a href="#" title="31"><span><img src="data1/tooltips/31.jpg" alt="31"/>3</span></a>
	</div></div> 
	<div class="ws_shadow"></div>
	</div>	
	<script type="text/javascript" src="engine1/wowslider.js"></script>
	<script type="text/javascript" src="engine1/script.js"></script>
	<!-- End WOWSlider.com BODY section -->
    
    </div>
    
    <!-- banner -->
     
     
    <!-- product tabs -->
    <section class="tabs_pro py-md-5 pt-sm-3 pb-5" id="products">
        <h5 class="head_agileinfo text-center text-capitalize pb-5">
          
          <%
          String subcate="Products";
          try{
        	   subcate=request.getAttribute("subcategory").toString();
          }catch(Exception exx){}
          %>
              <%=subcate %></h5>
        <!--  <div id="products"></div>-->
        <%try{ %>
 
<div class="container-fluid">
<div class="row">
<soham:forEach items="${stf}" var="rec">
<div class="col-md-3">
           
          
                            <!-- card -->
                            <div class="card product-men p-3">
                                <div class="men-thumb-item">
                                <div class="card-div" style="background-image:url('${rec.imgUrl}')">
                                   <!--  <img src="${rec.imgUrl}" alt="img" class="card-img-top img-responsive img-thumbnail">
                                     --></div>
                                    <div class="men-cart-pro">
                                        <div class="inner-men-cart-pro">
                                            <a   data-toggle="modal" data-target="#exampleModal1" class="link-product-add-cart">Quick View</a>
                                             
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
                                <!-- card footer -->
                                <div class="card-footer d-flex justify-content-end">
                                     <div class="hubcart hubcart2 cart cart box_1">
                                       <a data-toggle="modal" data-target="#exampleModal1" class="text-dark login_btn align-self-center mx-3">
                       <button class="btn top_hub_cart mt-1" type="submit" name="submit" value="" title="Cart">
                                        <i class="fas fa-shopping-bag"></i>
                                    </button>
                                </a>
                            </div>
                                </div>
                            </div>
                            <!-- //card -->
                       
                    </div>
                
       
</soham:forEach>
 
<%}catch(Exception ex){
	System.out.println("err in session="+ex.getMessage());
	
} %>
            
          </div>
    </section>
    <!-- //product tabs -->
    
    <!-- footer -->
    <footer>
        <div class="footerv2-w3ls">
           
            <div class="container-fluid py-5 footer-copy_w3ls">
                <div class="d-lg-flex justify-content-between">
                     
                    <div class="cpy-right align-self-center">
                        
                        <p>� 2024 Shopping Hub. All rights reserved  
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- //footer -->
    <!-- sign up Modal -->
    <div class="modal fade" id="myModal_btn" tabindex="-1" role="dialog" aria-labelledby="myModal_btn" aria-hidden="true">
        <div class="agilemodal-dialog modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Register Now</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">Close</span>
                    </button>
                </div>
                <div class="modal-body pt-3 pb-5 px-sm-5">
                    <div class="row">
                        
                        <div class="col-md-12">
                         <form id="frm" action="RegUser" method="post">
			 	  
			 <table class="tblform"><tr><td width="50%" style="vertical-align: top">
									 
									  <table  >
									 <tr><td>UserName</td>
                <td><input type="text" class="form-control"  name="name" pattern="^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$" required></td></tr>
            <tr><td>UserID</td>
                <td><input type="text"  class="form-control"  name="userid" required></td></tr>
                       
             <tr><td>Password
              
             </td>
                <td><input type="password" oninvalid="ths.setCustomValidity('password must be of minimum eight characters, at least one letter, one number and one special character')"
       oninput="setCustomValidity('')"  class="form-control"  name="pass" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$" required></td></tr>
              <tr><td>Profile Password
              
             </td>
                <td><input type="password" oninvalid="ths.setCustomValidity1('password must be of minimum eight characters, at least one letter, one number and one special character')"
       oninput="setCustomValidity1('')"  class="form-control"  name="profpass" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$" required></td></tr>
            
			<tr>
				<td>Mobile Number</td>
				<td>
				<input type="text" required  class="form-control"  name="mobile" pattern="^\d{10}$"  >
				</td>
			</tr>
			
                <tr><td>Email ID:</td>
                    <td><input type="text" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]{2,9}+\.[a-z]{2,4}$"  name="email" required></td>
                </tr>
                <tr><td>Culture Preference</td>
                <td>
                
                Indian <input type="radio" name="culture" value="Indian"   checked="true" required /> 
Western <input type="radio" name="culture" value="Western"  required/> 
                
                </td>
                </tr>
                  <%
									 GetStateNCities obj=new GetStateNCities();
									 obj.getStates();
									 List<States> lst=obj.getLststate();
									 %>
									      <tr>
									 <td>State
									 </td>
									 <td> 
									 <select required name="state" class="form-control" onchange="makeGetRequest1(this.value)">
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
									   
            
            </table></td><td><table  width="50%" style="vertical-align: top">
       <tr>
	            <td>Address</td>
		            <td>
		            <textarea rows="5" required cols="25" name="address" class="form-control" ></textarea>          
		            </td>
            </tr>  								  
<tr>
<td>DOB</td>
<td>
<input type="date" min='1923-01-01' max='2013-01-01' name="dob" required class="form-control"/>
</td>
</tr>
<tr>
<td>Gender</td>
<td>
Male <input type="radio" name="gender" value="Male"   checked="true" required /> 
Female <input type="radio" name="gender" value="Female"  required/> 
</td>
</tr>
 <tr><td>Security Question</td>
                <td>
                <select name="sques" class="form-control"> 
                <option value="In what city were you born?">In what city were you born?</option>
                <option value="What is your mothers maiden name?">What is your mother's maiden name?</option>
                <option value="What is the name of your favorite pet?">What is the name of your favorite pet?</option>
                <option value="What is the name of your first school?">What high school did you attend?</option>
                <option value="What was your favorite food as a child?">What was your favorite food as a child?</option>
                <option value="Where did you meet your spouse?">Where did you meet your spouse?</option>
                </select>
                </td>
                </tr>
                 <tr><td>Answer</td>
                <td><input type="text" class="form-control"  name="ans" required></td></tr>
            <tr>
 <tr><td>Profession</td>
                <td><input type="text"  class="form-control"  name="prof" required></td></tr>
    <tr><td>Approximate Maximum Shopping Amount(For Security Purpose)</td>
                <td><input type="text"  class="form-control"  name="shopAmt" required></td></tr>
                 
<tr><tr>
<td colspan="2"><input type="submit" value="Submit" class="btn btn-primary"/>
</td>
</tr>
            </table></td></tr></table>
             
       </form> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //signup modal -->
    <!-- signin Modal -->
    <div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModal1" aria-hidden="true">
        <div class="agilemodal-dialog modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Login</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">�</span>
                    </button>
                </div>
                <div class="modal-body  pt-3 pb-5 px-sm-5">
                    <div class="row">
                        <div class="col-md-6 align-self-center">
                            <img src="images/p3.png" class="img-fluid" alt="login_image" />
                        </div>
                        <div class="col-md-6">
                             <form name="frm" method="post" action="login">
                                <div class="form-group">
                                    <label for="recipient-name" class="col-form-label">Your Userid</label>
                                    <input type="text" class="form-control" placeholder=" " name="txtuserid" id="recipient-name" required="">
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Password</label>
                                    <input type="password" class="form-control" placeholder=" " name="txtpass" required="">
                                     <%String token=RandomString.getAlphaNumericString(10).trim();
                                    		session.setAttribute("token", token); %>
                                    <input type="hidden" value="<%=token %>" class="form-control" placeholder=" " name="token" required="">
                                
                                </div>
                                <div class="right-w3l">
                                    <input type="submit" class="form-control" value="Login">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- signin Modal -->
    <!-- js -->
    <script src="js/jquery-2.2.3.min.js"></script>
    <!-- //js -->
    <!-- script for show signin and signup modal -->
    <script>
        $(document).ready(function () {
            //$("#myModal_btn").modal();
        });
    </script>
    <!-- //script for show signin and signup modal -->
    <!-- smooth dropdown -->
    <script>
        $(document).ready(function () {
            $('ul li.dropdown').hover(function () {
                $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(200);
            }, function () {
                $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(200);
            });
        });
    </script>
    <!-- //smooth dropdown -->
    <!-- script for password match -->
    <script>
        window.onload = function () {
            document.getElementById("password1").onchange = validatePassword;
            document.getElementById("password2").onchange = validatePassword;
        }

        function validatePassword() {
            var pass2 = document.getElementById("password2").value;
            var pass1 = document.getElementById("password1").value;
            if (pass1 != pass2)
                document.getElementById("password2").setCustomValidity("Passwords Don't Match");
            else
                document.getElementById("password2").setCustomValidity('');
            //empty string means no validation error
        }
    </script>
    <!-- script for password match -->
    <!-- Banner Responsiveslides -->
    <script src="js/responsiveslides.min.js"></script>
    <script>
        // You can also use "$(window).load(function() {"
        $(function () {
            // Slideshow 4
            $("#slider3").responsiveSlides({
                auto: false,
                pager: true,
                nav: false,
                speed: 500,
                namespace: "callbacks",
                before: function () {
                    $('.events').append("<li>before event fired.</li>");
                },
                after: function () {
                    $('.events').append("<li>after event fired.</li>");
                }
            });

        });
    </script>
    <!-- // Banner Responsiveslides -->
    <!-- Product slider Owl-Carousel-JavaScript -->
    <script src="js/owl.carousel.js"></script>
    <script>
        var owl = $('.owl-carousel');
        owl.owlCarousel({
            items: 4,
            loop: false,
            margin: 10,
            autoplay: false,
            autoplayTimeout: 5000,
            autoplayHoverPause: false,
            responsive: {
                320: {
                    items: 1,
                },
                568: {
                    items: 2,
                },
                991: {
                    items: 3,
                },
                1050: {
                    items: 4
                }
            }
        });
    </script>
    <!-- //Product slider Owl-Carousel-JavaScript -->
    <!-- cart-js -->
    <script src="js/minicart.js">
    </script>
    <script>
        hub.render();

        hub.cart.on('new_checkout', function (evt) {
            var items, len, i;

            if (this.subtotal() > 0) {
                items = this.items();

                for (i = 0, len = items.length; i < len; i++) {}
            }
        });
    </script>
    <!-- //cart-js -->
    <!-- start-smooth-scrolling -->
    <script src="js/move-top.js"></script>
    <script src="js/easing.js"></script>
    <script>
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();

                $('html,body').animate({
                    scrollTop: $(this.hash).offset().top
                }, 1000);
            });
        });
    </script>
    <!-- //end-smooth-scrolling -->
    <!-- smooth-scrolling-of-move-up -->
    <script>
        $(document).ready(function () {
            /*
            var defaults = {
                containerID: 'toTop', // fading element id
                containerHoverID: 'toTopHover', // fading element hover id
                scrollSpeed: 1200,
                easingType: 'linear' 
            };
            */

            $().UItoTop({
                easingType: 'easeOutQuart'
            });

        });
    </script>
    <script src="js/SmoothScroll.min.js"></script>
    <!-- //smooth-scrolling-of-move-up -->
    <!-- Bootstrap core JavaScript
================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/bootstrap.js"></script>
</body>

</html>