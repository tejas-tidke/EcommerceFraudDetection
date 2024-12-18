<%@page import="java.util.Vector"%>
<%@page import="JavaBeans.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <title>Shopping Application</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8" />
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
    <!-- Owl-Carousel-CSS -->
    <link rel="stylesheet" href="css/owl.carousel.css" type="text/css" media="all">
    <!-- flexslider-css -->
    <link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />

    <link href="css/style.css" type="text/css" rel="stylesheet" media="all">
    <!-- font-awesome icons -->
    <link href="css/fontawesome-all.min.css" rel="stylesheet">
    <!-- //Custom Theme files -->
    <!-- online-fonts -->
    <link href="//fonts.googleapis.com/css?family=Elsie+Swash+Caps:400,900" rel="stylesheet">
    <link href="//fonts.googleapis.com/css?family=Source+Sans+Pro:200,200i,300,300i,400,400i,600,600i,700,700i,900,900i" rel="stylesheet">
    <!-- //online-fonts -->
    <script src='rating/jquery.js' type="text/javascript"></script>
	<script src='rating/jquery.MetaData.js' type="text/javascript" language="javascript"></script>
 <script src='rating/jquery.rating.js' type="text/javascript" language="javascript"></script>
 <link href='rating/jquery.rating.css' type="text/css" rel="stylesheet"/>
 <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.1/jquery-ui.min.js" type="text/javascript"></script>
 
</head>
<body>
<%
List<Products> lst=(List<Products>)request.getAttribute("stf");

%> 
     <jsp:include page="Top.jsp"></jsp:include>
      <%
          String subcate="Products",category="NA";
          try{
        	   subcate=request.getAttribute("subcategory").toString();
        	   category=request.getAttribute("category").toString();
          }catch(Exception exx){}
          %>
<!-- Single -->
<div class="innerf-pages section py-5">
    <div class="container">
        <div class="row my-sm-5">
            <div class="col-lg-4 single-right-left">
                <div class="grid images_3_of_2">
                    <div class="flexslider1">
                        <ul class="slides">
                            <li data-thumb="images/mff1.jpg">
                                <div class="thumb-image">
                                 
                                    <img src="<%=lst.get(0).getImgUrl() %>" data-imagezoom="true" alt=" " class="img-fluid"> 
                                    <br/>
                                    <form method="post" action="AssociatedItems">
                                    <input type="text" style="visibility: hidden;" name="prodName" value="<%=lst.get(0).getTitle() %>"/>
                                   <!--  <input type="submit" value="View Associated Products" class="btn btn-primary"/> -->
                                    </form> 
  									 
                                    
                                    </div>
                                    
                            </li>
                             
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8 mt-lg-0 mt-5 single-right-left simpleCart_shelfItem">
                <h3> <%=lst.get(0).getTitle() %>
                
                </h3>
                <div class="caption">

                   
                    <div class="clearfix"> </div>
                    <h6>
                       Rs. <%=lst.get(0).getPrice() %>/-</h6>
                </div>
                <div class="desc_single">
                    <h5>Description</h5>
                    <p><%=lst.get(0).getProddesc() %></p>
                    <h5>Category</h5>
                    <p><%=lst.get(0).getCategories() %></p>
                    <br/>
                    <h5>Brand</h5>
                    <p><%=lst.get(0).getBrand()%></p>
                    
                    <form method="post" action="GetSimilarProd">
                                    <input type="text" style="visibility: hidden;" name="prodName" value="<%=lst.get(0).getTitle() %>"/>
                                    <input type="text" style="visibility: hidden;" name="category" value="<%=lst.get(0).getCategories() %>"/>
                                    <input type="text" style="visibility: hidden;" name="brand" value="<%=lst.get(0).getBrand() %>"/>
                                  <!--   <input type="submit" value="View Other Brand Products in Same Category" class="btn btn-primary"/> -->
                                    </form>
                </div>
                <div class="d-sm-flex justify-content-between">
                    <div class="occasional">
                         
                   <h5 class="sp_title mb-3">Submit Review</h5>
                    <form name="frm" method="post" action="regReview"><table class="tblform">
	<tr><td>Review</td>
	<td>
	<input type="hidden" name="userid" value="<%=session.getAttribute("userid").toString().trim() %>"/>
	<input type="hidden" name="username" value="<%=session.getAttribute("username").toString().trim() %>"/>
	
	<input type="hidden" name="prodId" value="<%=lst.get(0).getProdId() %>"/>
	<textarea name="reviewText" class="form-control" required></textarea></td>
	</tr>
	 
	  
	<tr>
	<td><input type="submit" value="Submit" class="btn btn-primary" ></td>
	</tr>
	</table>
</form>
                 
                        <h5 class="sp_title mb-3">Submit Ratings</h5>
                        
						<form action="regRating" method="post">
                        <div class="Clear">
    <input class="star" type="radio" name="prating" value="20" checked="checked"/>
    <input class="star" type="radio" name="prating" value="40"/>
    <input class="star" type="radio" name="prating" value="60" />
    <input class="star" type="radio" name="prating" value="80"/>
    <input class="star" type="radio" name="prating" value="100"/>
     <input type="hidden" name="prodId" value="<%=lst.get(0).getProdId() %>"/>
     </div>
                        <input type="submit" value="Submit" class="btn btn-primary">
                    </form>
                    </div>
                     
                </div>
                 <div class="card-footer d-flex justify-content-end">
                                
                                    <form action="AddToCart" method="post">
                                     <input type="text" style="visibility: hidden;"  name="prodId" value="<%=lst.get(0).getProdId() %>"> 
                                        <input type="text" style="visibility: hidden;" name="title" value="<%=lst.get(0).getTitle() %>">
                                         <input type="text" style="visibility: hidden;"  name="price" value="<%=lst.get(0).getPrice() %>">
                                         <input type="text" style="visibility: hidden;"  name="page" value="prodDetails?prodId=<%=lst.get(0).getProdId() %>&subcategory=<%=subcate %>&category=<%=category %>">
                                         <input type="text" style="visibility: hidden;"  name="category" value="<%=category%>">
                                         <input type="text" style="visibility: hidden;"  name="subcategory" value="<%=subcate%>">
                                        <button type="submit" class="hub-cart phub-cart btn">
                                            <i class="fa fa-cart-plus" aria-hidden="true"></i>
                                        </button>
                                        <a href="#" data-toggle="modal" data-target="#myModal1"></a>
                                    </form>
                                     
                                </div>
                                <center><h2>Reviews</h2></center>
                <%
                JavaFuns jf=new JavaFuns();
               Vector v=jf.getValue("select username,reviewText,dt from custreviews where prodId="+lst.get(0).getProdId(), 3);
               for(int i=0;i<v.size();i=i+3){
                %>
                
                <table class="table table-bordered">
                 <tr>
                <th colspan="2"><%=v.elementAt(i+2).toString().trim() %></th>
                </tr>
                <tr>
                <th colspan="2">User Name: <%=v.elementAt(i).toString().trim() %></td>
                </tr>
                 <tr>
                <td colspan="2"><%=v.elementAt(i+1).toString().trim() %></td>
                </tr>
                </table>
                <%} %>
                
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /new_arrivals -->
 
<!--// Single -->
 <footer>
        <div class="footerv2-w3ls">
           
            <div class="container-fluid py-5 footer-copy_w3ls">
                <div class="d-lg-flex justify-content-between">
                     
                    <div class="cpy-right align-self-center">
                        <h2 class="agile_btxt">
                            <a href="index.html">
                                <span>f</span>ashion
                                <span>h</span>ub</a>
                        </h2>
                        <p>© 2021 Shopping Hub. All rights reserved  
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
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body pt-3 pb-5 px-sm-5">
                    <div class="row">
                        <div class="col-md-6 align-self-center">
                            <img src="images/p3.png" class="img-fluid" alt="login_image" />
                        </div>
                        <div class="col-md-6">
                            <form action="#" method="post">
                                <div class="form-group">
                                    <label for="recipient-name1" class="col-form-label">Your Name</label>
                                    <input type="text" class="form-control" placeholder=" " name="Name" id="recipient-name1" required="">
                                </div>
                                <div class="form-group">
                                    <label for="recipient-email" class="col-form-label">Email</label>
                                    <input type="email" class="form-control" placeholder=" " name="Email" id="recipient-email" required="">
                                </div>
                                <div class="form-group">
                                    <label for="password1" class="col-form-label">Password</label>
                                    <input type="password" class="form-control" placeholder=" " name="Password" id="password1" required="">
                                </div>
                                <div class="form-group">
                                    <label for="password2" class="col-form-label">Confirm Password</label>
                                    <input type="password" class="form-control" placeholder=" " name="Confirm Password" id="password2" required="">
                                </div>
                                <div class="sub-w3l">
                                    <div class="sub-agile">
                                        <input type="checkbox" id="brand2" value="">
                                        <label for="brand2" class="mb-3">
                                            <span></span>I Accept to the Terms & Conditions</label>
                                    </div>
                                </div>
                                <div class="right-w3l">
                                    <input type="submit" class="form-control" value="Register">
                                </div>
                            </form>
                            <p class="text-center mt-3">Already a member?
                                <a href="#" data-toggle="modal" data-target="#exampleModal1" class="text-dark login_btn">
                                    Login Now</a>
                            </p>
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
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body  pt-3 pb-5 px-sm-5">
                    <div class="row">
                        <div class="col-md-6 align-self-center">
                            <img src="images/p3.png" class="img-fluid" alt="login_image" />
                        </div>
                        <div class="col-md-6">
                            <form action="#" method="post">
                                <div class="form-group">
                                    <label for="recipient-name" class="col-form-label">Your Name</label>
                                    <input type="text" class="form-control" placeholder=" " name="Name" id="recipient-name" required="">
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Password</label>
                                    <input type="password" class="form-control" placeholder=" " name="Password" required="">
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

<!-- FlexSlider -->
<script src="js/jquery.flexslider.js"></script>
<script>
    // Can also be used with $(document).ready()
    $(window).load(function () {
        $('.flexslider1').flexslider({
            animation: "slide",
            controlNav: "thumbnails"
        });
    });
</script>
<!-- //FlexSlider-->
<!-- Responsiveslides -->
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
<!-- // Responsiveslides -->
<!-- cart-js -->
<script src="js/minicart.js"></script>
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
<!-- zoom -->
<script src="js/imagezoom.js"></script>
<!-- zoom-->

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