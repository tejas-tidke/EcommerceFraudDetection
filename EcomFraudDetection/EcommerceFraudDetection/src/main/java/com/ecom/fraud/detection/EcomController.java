package com.ecom.fraud.detection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import jakarta.servlet.ServletRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import models.AnimalImages;
import models.Animals;
import models.CityImages;
import models.Food;
import models.FoodImages;
import models.GetLoginImages;
import models.Hobbies;
import models.HobbyImages;
import models.UserReg1;
import services.Base64Decoder;
import services.Base64Encoder;
import services.IPAddressGenrator;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import JavaBeans.Cart;
import JavaBeans.ProfilePassword;
import JavaBeans.JavaFuns;
import JavaBeans.Login;
import JavaBeans.Mail;
import JavaBeans.MyOrders;
import JavaBeans.Orders;
import JavaBeans.Pass;
import JavaBeans.Products;
import JavaBeans.RandomString;
import JavaBeans.Rating;
import JavaBeans.Reviews;
import JavaBeans.UserReg;

@Controller
public class EcomController   implements ErrorController{
	 @RequestMapping("/error")
	    public String handleError(HttpServletRequest request) {
	        	return "home";
	    }
	 
	 @RequestMapping("newProd")
		public String RegProduct(Products obj,HttpServletRequest request,HttpSession ses)
		{
			boolean flag=true;
			double p=0;
			 try
			 {
				 JavaFuns jf=new JavaFuns();
				  
				 
				 MultipartFile file=obj.getFile();
			  
			 String filepath=request.getServletContext().getRealPath("/")+"/Products/";
			  
			 System.out.println("path="+filepath);
			 File f=new File(filepath);
			 f.mkdir();
			 filepath+="/"+ses.getAttribute("userid").toString().trim();
			 f=new File(filepath);
			 f.mkdir();
			  
			 int mx=jf.FetchMax("id", "products");
			 String fileName=mx+"."+ file.getOriginalFilename().split("\\.")[1];
			 file.transferTo(new File(filepath+"/"+fileName));
			  
			 String cate="Clothing/"+obj.getCategory()+"/"+obj.getSubcategory()+"/"+obj.getBrand()+"/More by "+obj.getBrand();
			 fileName="http://localhost:8080/Products/admin/"+fileName;
			String qr="insert into products values("+mx+",'"+obj.getTitle()+"','"+cate+"','"+obj.getProddesc()+"',"+obj.getPrice()+",'"+fileName+"','"+obj.getBrand()+"','"+obj.getCulture()+"','0',0)";
			if(jf.execute(qr)) {}
			 return "Success.jsp?type=RegProd";
				 
			 }
			 catch (Exception e) {
				// TODO: handle exception
				 System.out.println("err="+e.getMessage());
				 return("Failure.jsp?type=RegProd");
			}
		}
		@RequestMapping("/viewOrderDetails")
		public ModelAndView viewOrderDetails(HttpServletRequest request,HttpSession ses)
		{
	    	ModelAndView mv=new ModelAndView();
	    	
	    	try {
	    	  Orders order=new  Orders();
	    	  String ord=request.getParameter("orderNo").trim();
	    	  
	    	  order.getOrderDetails(Integer.parseInt(ord));
	    	 List<Orders> lstorder=new ArrayList<Orders>();
	    	 lstorder=order.getLstorders();
	    	 mv.setViewName("OrderDetails.jsp");
	    	 mv.addObject("lst",lstorder);
	    	}
	    	catch (Exception e) {
	    		System.out.println("err in place order="+e.getMessage());
				// TODO: handle exception
			}  
	    	return mv;
		}
	   
	    @RequestMapping("/MyOrders")
		public ModelAndView MyOrders(HttpServletRequest request,HttpSession ses)
		{
	    	ModelAndView mv=new ModelAndView();
	    	
	    	try {
	    	 JavaBeans.MyOrders order=new JavaBeans.MyOrders();
	    	 order.setUserid(ses.getAttribute("userid").toString().trim());
	    	 order.getMyOrders("all");
	    	 List<MyOrders> lstorder=new ArrayList<MyOrders>();
	    	 lstorder=order.getLstorders();
	    	 mv.setViewName("orders.jsp");
	    	 mv.addObject("lst",lstorder);
	    	}
	    	catch (Exception e) {
	    		System.out.println("err in place order="+e.getMessage());
				// TODO: handle exception
			}
	    	return mv;
		}
		 @RequestMapping("/FromPython")
		public String FromPython(HttpServletRequest request )
		{
	    	 
	    	 
	    	return "Success.jsp?type=AssociationMining";
		}
	    @RequestMapping("/ProcessOrder")
		public String ProcessOrder(HttpServletRequest request,HttpSession ses)
		{
	    	 
	    	try {
	    	  JavaFuns jf=new JavaFuns();
	    	  if(jf.execute("update orders set paymentsts='paid', orderstatus='processed' where orderno="+request.getParameter("orderNo").trim()))
	    	  {
	    		  
	    	  }
	    	  
	    	}
	    	catch (Exception e) {
	    		System.out.println("err in place order="+e.getMessage());
				// TODO: handle exception
			}
	    	return "Success.jsp?type=OrderProcess";
		}
	    @RequestMapping("/PendingOrders")
		public ModelAndView PendingOrders(HttpServletRequest request,HttpSession ses)
		{
	    	ModelAndView mv=new ModelAndView();
	    	
	    	try {
	    	 JavaBeans.MyOrders order=new JavaBeans.MyOrders();
	    	  order.getPendingOrders();
	    	 List<MyOrders> lstorder=new ArrayList<MyOrders>();
	    	 lstorder=order.getLstorders();
	    	 mv.setViewName("PendingOrders.jsp");
	    	 mv.addObject("lst",lstorder);
	    	}
	    	catch (Exception e) {
	    		System.out.println("err in place order="+e.getMessage());
				// TODO: handle exception
			}
	    	return mv;
		}
	    @RequestMapping("/OrdersAdmin")
		public ModelAndView OrdersAdmin(HttpServletRequest request,HttpSession ses)
		{
	    	ModelAndView mv=new ModelAndView();
	    	
	    	try {
	    	 JavaBeans.MyOrders order=new JavaBeans.MyOrders();
	    	  order.getPendingOrders1();
	    	 List<MyOrders> lstorder=new ArrayList<MyOrders>();
	    	 lstorder=order.getLstorders();
	    	 mv.setViewName("PendingOrders1.jsp");
	    	 mv.addObject("lst",lstorder);
	    	}
	    	catch (Exception e) {
	    		System.out.println("err in place order="+e.getMessage());
				// TODO: handle exception
			}
	    	return mv;
		}
	    @RequestMapping("/PlaceOrder")
		public String PlaceOrder(HttpServletRequest request,HttpSession ses)
		{
	    	try {
	    		JavaFuns jf=new JavaFuns();
	    		Vector v=jf.getValue("select maxPurchaseAmt from userdetails where userid='"+ses.getAttribute("userid").toString().trim()+"'", 1);
	    		Vector v1=jf.getValue("select sum(totalprice) as bill from cart where userid='"+ses.getAttribute("userid").toString().trim()+"'", 1);
	    		if(Double.parseDouble(v1.elementAt(0).toString().trim())>Double.parseDouble(v.elementAt(0).toString().trim()))
	    		{
	    			return "Failure.jsp?type=placeOrderamt";
	    		}
	    		else
	    		{
	    			if(Double.parseDouble(v1.elementAt(0).toString().trim())>10000)
		    		{
	    				
	    				return("userImgLogin");
		    		}
	    			else {
	    		Orders order=new Orders();
	    	 order.setUserid(ses.getAttribute("userid").toString().trim());
	    	if(order.placeOrder()) {}
	    	return "Success.jsp?type=placeOrder";
	    		}}
	    	}
	    	catch (Exception e) {
	    		System.out.println("err in place order="+e.getMessage());
				// TODO: handle exception
	    		return "Failure.jsp?type=placeOrder";
			}
	    	
		}
	 
	    @RequestMapping("/PlaceOrder1")
		public String PlaceOrder1(HttpServletRequest request,HttpSession ses)
		{
	    	try {
	    		JavaFuns jf=new JavaFuns();
	    		Vector v=jf.getValue("select maxPurchaseAmt from userdetails where userid='"+ses.getAttribute("userid").toString().trim()+"'", 1);
	    		Vector v1=jf.getValue("select sum(totalprice) as bill from cart where userid='"+ses.getAttribute("userid").toString().trim()+"'", 1);
	    		if(Double.parseDouble(v1.elementAt(0).toString().trim())>Double.parseDouble(v.elementAt(0).toString().trim()))
	    		{
	    			return "Failure.jsp?type=placeOrderamt";
	    		}
	    		else
	    		{
	    			 
	    		Orders order=new Orders();
	    	 order.setUserid(ses.getAttribute("userid").toString().trim());
	    	if(order.placeOrder()) {}
	    	return "Success.jsp?type=placeOrder";
	    		}  
	    	}
	    	catch (Exception e) {
	    		System.out.println("err in place order="+e.getMessage());
				// TODO: handle exception
	    		return "Failure.jsp?type=placeOrder";
			}
	    	
		}
	@RequestMapping("/getProducts")
	public ModelAndView getProducts(HttpServletRequest request,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
		List<Products> lst=new ArrayList<Products>();
		Products vs = new Products();
		 vs.getProducts();
		lst=vs.getLstproducts();
		 System.out.println("list size="+lst.size());
		mv.addObject("stf",lst);
		mv.setViewName("showProducts.jsp");
		//mv.addObject("branch",request.getParameter("branch").toString().trim());
		return mv;
	}
	
	@RequestMapping("/home")
	public ModelAndView myspring()
	{
		ModelAndView mv=new ModelAndView();
	List<Products> lst=new ArrayList<Products>();
	Products vs = new Products();
	 vs.getProducts();
	lst=vs.getLstproducts();
	 System.out.println("list size="+lst.size());
	mv.addObject("stf",lst);
	mv.setViewName("index.jsp");
	//mv.addObject("branch",request.getParameter("branch").toString().trim());
	return mv;
		 
	}
	
	@RequestMapping("/prodDetails")
	public ModelAndView prodDetails(Products vs,HttpSession ses,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
	List<Products> lst=new ArrayList<Products>();
	try {
	 vs.setProdId(Integer.parseInt(request.getParameter("prodId").toString().trim()));
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	 vs.getProduct();
	lst=vs.getLstproducts();
	JavaFuns jf=new JavaFuns();
	  if(jf.execute("update products set searchCount=searchCount+1 where id="+Integer.parseInt(request.getParameter("prodId").trim())))
	  {
		  
	  }
	  Date d=new Date();
	  String dt=d.getDate()+"/"+(d.getMonth()+1)+(d.getYear()+1900);
	  int mx=jf.FetchMax("sid", "searchtrack");
	  String qr="insert into searchtrack values("+mx+",'"+ses.getAttribute("userid").toString().trim()+"','"+request.getParameter("subcategory").toString().trim()+"','"+request.getParameter("prodnm").toString().trim()+"','"+dt+"','"+request.getParameter("prodId").trim()+"','"+request.getParameter("brand").trim()+"')";
	  System.out.println("qr="+qr);
	  if(jf.execute(qr)) {}
	 System.out.println("list size="+lst.size());
	mv.addObject("stf",lst);
	mv.addObject("subcategory",request.getParameter("subcategory").trim());
	mv.addObject("category",request.getParameter("category").trim());
	mv.setViewName("prodDetails.jsp");
	//mv.addObject("branch",request.getParameter("branch").toString().trim());
	return mv;
		 
	}
	@RequestMapping("/showProd")
	public ModelAndView showProd(Products vs)
	{
		ModelAndView mv=new ModelAndView();
	List<Products> lst=new ArrayList<Products>();
	 
	 vs.getCategoryWiseProducts();
	lst=vs.getLstproducts();
	 System.out.println("list size="+lst.size());
	mv.addObject("stf",lst);
	mv.addObject("subcategory",vs.getSubcategory());
	mv.addObject("category",vs.getCategory());
	mv.setViewName("index.jsp#products");
	//mv.addObject("branch",request.getParameter("branch").toString().trim());
	return mv;
		 
	}
	@RequestMapping("/updateCart")
	public ModelAndView updateCart(HttpServletRequest request, HttpSession ses)
	{
		Cart cart=new Cart();
		int cartid=Integer.parseInt(request.getParameter("cartid").toString());
		int quant=Integer.parseInt(request.getParameter("quan").toString());
		cart.setCartid(cartid);
		cart.setQuantity(quant);
		ModelAndView mv=new ModelAndView(); 
		if(cart.updateItems())
		{
			
		}
		cart.setUserid(ses.getAttribute("userid").toString().trim());
		 cart.getCartDetails();
			List<Cart> lstcart=cart.getLstcart();
			mv.addObject("lst",lstcart);
			mv.setViewName("cartAjax.jsp");
		 System.out.println("in cart ajax");
	//mv.addObject("branch",request.getParameter("branch").toString().trim());
			return mv;
		 
	}
	@RequestMapping("/RemoveItem")
	public ModelAndView RemoveItem(Cart cart,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView(); 
		if(cart.removeItems())
		{
			
		}
		cart.setUserid(ses.getAttribute("userid").toString().trim());
		 cart.getCartDetails();
			List<Cart> lstcart=cart.getLstcart();
			mv.addObject("lst",lstcart);
			mv.setViewName("Cart.jsp");
		 
	//mv.addObject("branch",request.getParameter("branch").toString().trim());
			return mv;
		 
	}
	@RequestMapping("/ShowCart")
	public ModelAndView ShowCart(Cart cart,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView(); 
		cart.setUserid(ses.getAttribute("userid").toString().trim());
		 cart.getCartDetails();
			List<Cart> lstcart=cart.getLstcart();
			mv.addObject("lst",lstcart);
			mv.setViewName("Cart.jsp");
		 
	//mv.addObject("branch",request.getParameter("branch").toString().trim());
			return mv;
		 
	}
	@RequestMapping("/AddToCart")
	public ModelAndView addToCart(Cart cart,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView(); 
		cart.setUserid(ses.getAttribute("userid").toString().trim());
		
		if(!cart.checkProductInCartORNOT(cart.getProdId(), ses.getAttribute("userid").toString().trim()))
		{
			if(cart.addToCart())
			{
				 JavaFuns jf=new JavaFuns();
		    	  if(jf.execute("update products set searchCount=searchCount+1 where id="+cart.getProdId()))
		    	  {
		    		  
		    	  }
				
			}	
				List<Products> lst=new ArrayList<Products>();
				 Products vs=new Products();
				 vs.setCategory(cart.getCategory());
				 vs.setSubcategory(cart.getSubcategory());
				 if(cart.getCategory().trim().equals("NA"))
					 vs.getProducts();
				 else
					 vs.getCategoryWiseProducts();
				lst=vs.getLstproducts();
				 System.out.println("list size="+lst.size());
				mv.addObject("stf",lst);
				mv.addObject("subcategory",vs.getSubcategory());
				mv.addObject("category",vs.getCategory());
				mv.setViewName(cart.getPage());
		}
		else
		{
			
			cart.getCartDetails();
			List<Cart> lstcart=cart.getLstcart();
			mv.addObject("lst",lstcart);
			mv.setViewName("Cart.jsp");
		}
	//mv.addObject("branch",request.getParameter("branch").toString().trim());
			return mv;
		 
	}
	 
	@RequestMapping("/showProdSess")
	public ModelAndView showProdSess(Products vs)
	{
		ModelAndView mv=new ModelAndView();
	List<Products> lst=new ArrayList<Products>();
	 
	 vs.getCategoryWiseProducts();
	lst=vs.getLstproducts();
	 System.out.println("list size="+lst.size());
	mv.addObject("stf",lst);
	mv.addObject("subcategory",vs.getSubcategory());
	mv.addObject("category",vs.getCategory());
	mv.setViewName("user.jsp");
	//mv.addObject("branch",request.getParameter("branch").toString().trim());
	return mv;
		 
	}
	@RequestMapping("/forgot")
	public String forgot()
	{
		return "Forgot.jsp";
	}
	@RequestMapping("/changePass")
	public String ChangePass()
	{
		return "ChangePass.jsp";
	}
	@RequestMapping("/adminHome")
	public String adminHome()
	{
		return "PendingOrders";
	}
	 
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
     session.invalidate();
		return "home";
	}
	@RequestMapping("/regRating")
	@SessionScope
	public ModelAndView regRating(Rating rev,HttpServletRequest request,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
		rev.setUserid(ses.getAttribute("userid").toString().trim());
		rev.setUsername(ses.getAttribute("username").toString().trim());
		rev.registerRating(); 	
		mv.setViewName("Success.jsp?type=ProdRating");
	    return mv;
	}
	@RequestMapping("/regReview")
	@SessionScope
	public ModelAndView regReview(Reviews rev,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		String filepath=request.getServletContext().getRealPath("/");
		rev.registerReview(filepath); 	
		mv.setViewName("Success.jsp?type=ReviewReg"); 
		return mv;
	}
	
	
	@RequestMapping("getProductsSearch")
	public ModelAndView getProductsSearch(HttpSession ses,HttpServletRequest request)
	{
		System.out.println("in userhome");
		ModelAndView mv=new ModelAndView();
		List<Products> lst=new ArrayList<Products>();
		Products vs = new Products();
		 vs.getProductsSearch(request.getParameter("txt").toString().trim());
		lst=vs.getLstproducts();
		 System.out.println("list size="+lst.size());
		mv.addObject("stf",lst);
		mv.setViewName("searchprod.jsp");
		//mv.addObject("branch",request.getParameter("branch").toString().trim());
		return mv;
	}
	@RequestMapping("getPreferredProds1")
	public ModelAndView getPreferredProds1(HttpSession ses)
	{
		System.out.println("in userhome");
		ModelAndView mv=new ModelAndView();
		List<Products> lst=new ArrayList<Products>();
		Products vs = new Products();
		 vs.getProductsPref1(ses.getAttribute("userid").toString().trim());
		lst=vs.getLstproducts();
		 System.out.println("list size="+lst.size());
		mv.addObject("stf",lst);
		mv.setViewName("user.jsp");
		//mv.addObject("branch",request.getParameter("branch").toString().trim());
		return mv;
	}
	@RequestMapping("getPreferredProds")
	public ModelAndView getPreferredProds(HttpSession ses)
	{
		System.out.println("in userhome");
		ModelAndView mv=new ModelAndView();
		List<Products> lst=new ArrayList<Products>();
		Products vs = new Products();
		 vs.getProductsPref(ses.getAttribute("userid").toString().trim());
		lst=vs.getLstproducts();
		 System.out.println("list size="+lst.size());
		mv.addObject("stf",lst);
		mv.setViewName("user.jsp");
		//mv.addObject("branch",request.getParameter("branch").toString().trim());
		return mv;
	}
	@RequestMapping("userHome")
	public ModelAndView userHome(HttpSession ses)
	{
		System.out.println("in userhome");
		ModelAndView mv=new ModelAndView();
		List<Products> lst=new ArrayList<Products>();
		Products vs = new Products();
		 vs.getProducts1(ses.getAttribute("userid").toString().trim());
		lst=vs.getLstproducts();
		 System.out.println("list size="+lst.size());
		mv.addObject("stf",lst);
		mv.setViewName("user.jsp");
		//mv.addObject("branch",request.getParameter("branch").toString().trim());
		return mv;
	}
	@RequestMapping("PurchaseHistRecomm")
	public ModelAndView PurchaseHistRecomm(HttpSession ses)
	{
		System.out.println("in userhome");
		ModelAndView mv=new ModelAndView();
		List<Products> lst=new ArrayList<Products>();
		Products vs = new Products();
		 vs.getProductsPurchaseHist(ses.getAttribute("userid").toString().trim());
		lst=vs.getLstproducts();
		 System.out.println("list size="+lst.size());
		mv.addObject("stf",lst);
		mv.setViewName("purchaseHistRecomm.jsp");
		//mv.addObject("branch",request.getParameter("branch").toString().trim());
		return mv;
	}
	@RequestMapping("GetSimilarProd")
	public ModelAndView GetSimilarProd(HttpServletRequest request, HttpSession ses)
	{
		System.out.println("in userhome");
		ModelAndView mv=new ModelAndView();
		List<Products> lst=new ArrayList<Products>();
		Products vs = new Products();
		System.out.println(request.getParameter("prodName").toString().trim());
		 vs.getSimilarProducts(request.getParameter("prodName").toString().trim(),request.getParameter("category").toString().trim(),request.getParameter("brand").toString().trim());
		 
		lst=vs.getLstproducts();
		 System.out.println("list size="+lst.size());
		mv.addObject("stf",lst);
		mv.setViewName("similarProds.jsp");
		//mv.addObject("branch",request.getParameter("branch").toString().trim());
		return mv;
	}
	@RequestMapping("AssociatedItems")
	public ModelAndView AssociatedItems(HttpServletRequest request, HttpSession ses)
	{
		System.out.println("in userhome");
		ModelAndView mv=new ModelAndView();
		List<Products> lst=new ArrayList<Products>();
		Products vs = new Products();
		System.out.println(request.getParameter("prodName").toString().trim());
		 vs.getProductsAssProd(request.getParameter("prodName").toString().trim());
		 
		lst=vs.getLstproducts();
		 System.out.println("list size="+lst.size());
		mv.addObject("stf",lst);
		mv.setViewName("associatedProds.jsp");
		//mv.addObject("branch",request.getParameter("branch").toString().trim());
		return mv;
	}
	@RequestMapping("/passRecoveryOTPAuth")
	public ModelAndView passRecoveryOTPAuth(UserReg user)
	{
		ModelAndView mv=new ModelAndView();
		try {
			if(user.getSentOTP().equals(user.getOtp()))
			{
				String pass=RandomString.getAlphaNumericString(8);
				user.setPass(pass);
				if(user.updatePass())
				{
					
				}
				
				
			    mv.setViewName("Success.jsp?type=passEmail");
			    
			   // Mail mail=new Mail();
			    String msg="Dear "+user.getName()+" \n Your password has been reset to "+pass;
			    System.out.println("pass="+pass);
			    try
			    {
			    //	if(mail.sendMail(msg,user.getEmail(), "New password"))
			    	//{
			    		
			    	//}
			    }
			    catch (Exception e) {
					// TODO: handle exception
				}
			}
			else
			{
				mv.setViewName("Failure.jsp?type=passEmail");
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	    return mv;
	}
	
	@RequestMapping("/passRecovery")
	public ModelAndView passRecovery(UserReg user)
	{
		ModelAndView mv=new ModelAndView();
		try {
			if(user.useridAuth())
			{
				String otp=RandomString.getAlphaNumericString(4);
				
			    mv.setViewName("ForgotOTP.jsp");
			    mv.addObject("userid",user.getUserid());
			    mv.addObject("otp",otp);
			    mv.addObject("email",user.getEmail());
			//   JavaBeans.Mail mail=new Mail();
			    String msg="Dear "+user.getName()+" \n Your one time password is "+otp;
			    System.out.println("otp="+otp);
			    try
			    {
			    	//if(mail.sendMail(msg,user.getEmail(), "One Time Password"))
			    	//{
			    		
			    //	}
			    }
			    catch (Exception e) {
					// TODO: handle exception
				}
			}
			else
			{
				mv.setViewName("Failure.jsp?type=Auth");
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	    return mv;
	}
	 
	@RequestMapping("/ChangePassService")
	public String ChangePassService(Pass eobj,HttpSession ses)
	{
		 
		 try
		 {
			 
			 eobj.setUserid(ses.getAttribute("userid").toString().trim());
			 if(eobj.changePassword())
			 {
				 
				 
				 return "Success.jsp?type=ChangePass";
			 }
			 else
			 { 
				 return "Failure.jsp?type=ChangePass";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=Auth");
		}
		 
	}
	@RequestMapping("/CheckFraud")
	public ModelAndView checkFraud(HttpSession ses,HttpServletRequest reqeust)
	{
		ModelAndView mv=new ModelAndView();
		JavaFuns jf=new JavaFuns();
		//System.out.println("in check fraud");
		 jf.insertInputAttributes(ses.getAttribute("userid").toString().trim(), ses.getAttribute("sessionid").toString().trim());
		 if(jf.checkBehavior(ses.getAttribute("userid").toString().trim(), ses.getAttribute("sessionid").toString().trim()).trim().equals("suspicious"))
		 {
			
			 mv.setViewName("userImgLogin");
		 }
		 else
		 {
			 
			 mv.setViewName("PlaceOrder");
		 }
		 return mv;
	}
	
	
	
	
	
	
	
	@RequestMapping("/uploadHobbyImg")
	public ModelAndView uploadImg()
	{
		ModelAndView mv=new ModelAndView();
		Hobbies hobby=new Hobbies();
		hobby.getHobbiesList();
		List<Hobbies> lst=new ArrayList<Hobbies>();
		lst=hobby.getLsthobby();
		mv.setViewName("uploadImgHobby.jsp");
		mv.addObject("lst", lst);
		return mv;
		 
	}
	@SessionScope
	@RequestMapping("/RegCityImg")
	public ModelAndView RegCityImg(CityImages eobj,ServletRequest request,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
		 
		 try
		 {MultipartFile file=eobj.getFile();
		 String filepath=request.getServletContext().getRealPath("/")+"/UploadCityImg/";
		  
		 System.out.println("path="+filepath);
		 File f=new File(filepath);
		 f.mkdir();
		 f=new File(filepath);
		 f.mkdir();
		 try {
			 
			 int mx=eobj.getId(); 
			 System.out.println("cityidmm="+mx);
			 String fileName=mx+"."+ file.getOriginalFilename().split("\\.")[1];
			 file.transferTo(new File(filepath+"/"+fileName));
			  
			 eobj.setcityimgpath(fileName);
			  
			 if(eobj.registration() )
			 { 
				mv.setViewName("Success.jsp?type=ImgRegCity");
			 }
			 else
			 { 
				 mv.setViewName("Failure.jsp?type=ImgRegCity");
			 }
			 } catch (IOException e) {
				 
			 }
		 
			 
		 mv.setViewName("Success.jsp?type=ImgRegCity");
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 mv.setViewName("Failure.jsp?type=ImgCity");
		}
		 return mv;
	}
	@SessionScope
	@RequestMapping("/RegHobbyImg")
	public ModelAndView RegHobbyImg(HobbyImages eobj,ServletRequest request,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
		 
		 try
		 {MultipartFile file=eobj.getFile();
		 String filepath=request.getServletContext().getRealPath("/")+"/UploadHobbyImg/";
		  
		 System.out.println("path="+filepath);
		 File f=new File(filepath);
		 f.mkdir();
		 f=new File(filepath);
		 f.mkdir();
		 try {
			 eobj.getId();
			 int mx=eobj.getHobbyImgId();
			 String fileName=mx+"."+ file.getOriginalFilename().split("\\.")[1];
			 file.transferTo(new File(filepath+"/"+fileName));
			  
			 eobj.setHobbyimgpath(fileName);
			  
			 if(eobj.registration() )
			 { 
				mv.setViewName("Success.jsp?type=ImgRegHobby");
			 }
			 else
			 { 
				 mv.setViewName("Failure.jsp?type=ImgRegHobby");
			 }
			 } catch (IOException e) {
				 
			 }
		 
			 
		 mv.setViewName("Success.jsp?type=ImgRegHobby");
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 mv.setViewName("Failure.jsp?type=ImgReg");
		}
		 return mv;
	}
	@RequestMapping("/uploadAnimalImg")
	public ModelAndView uploadAnimalImg()
	{
		ModelAndView mv=new ModelAndView();
		Animals hobby=new Animals();
		hobby.getAnimalList();
		List<Animals> lst=new ArrayList<Animals>();
		lst=hobby.getLstanimal();
		mv.setViewName("uploadImgAnimals.jsp");
		mv.addObject("lst", lst);
		return mv;
		 
	}
	@RequestMapping("/uploadCityImg")
	public ModelAndView uploadCityImg()
	{
		ModelAndView mv=new ModelAndView();
		 
		mv.setViewName("uploadImgCity.jsp"); 
		return mv;
		 
	}
	@RequestMapping("/ImgAuth")
	public ModelAndView ImgAuth(HttpServletRequest request,HttpSession ses)
	{
		GetLoginImages login=new GetLoginImages();
		Base64Decoder decoder=new Base64Decoder();
		ModelAndView mv=new ModelAndView();
		String str=new String(decoder.decode(request.getParameter("id").toString().trim()));
		String id=str.split("\\|")[0].trim();
		String userid=str.split("\\|")[1].trim();
		if(id.trim().equals(ses.getAttribute("loginImgId").toString().trim()))
		{
			login.loginLog(userid, str.split("\\|")[2].trim(), "success");
			mv.setViewName("forwardUserHome.jsp");
			//mv.addObject("userid",request.getParameter("userid").toString().trim());
			Base64Encoder encoder=new Base64Encoder();
			String uidenc=encoder.encode(userid.trim().getBytes());
			ses.setAttribute("gauth", "true");
			mv.setViewName("PlaceOrder1");
		}
		else
		{
			JavaFuns jf=new JavaFuns();
			if(jf.execute("update users set loginsts='deactive' where userid='"+ses.getAttribute("userid").toString().trim()+"'")) {}
			String qr="select ifnull(max(fid),1000)+1 from fraudDetails;";
    		Mail mail=new Mail();
    		String msg="Dear User, suspicious behavior found in your account on EcommerceSystem.com. Your account has been locked for security purpose!! Please unlock with your correct credentials!!";
    		try {
    			Vector v11=jf.getValue("select email from userdetails where userid='"+ses.getAttribute("userid").toString().trim()+"'", 1);
    			
    		if(mail.sendMail(msg, v11.elementAt(0).toString().trim(), "Fraud Detected")) {}
    		}
    		catch (Exception e) {
				// TODO: handle exception
			}
    		try {   
    			Vector  v=jf.getValue(qr, 1);
    		int mxid=Integer.parseInt(v.elementAt(0).toString().trim());
    		String ipaddr="na",macaddr="na";
    		Date d=new Date();
			String dt=d.getDate()+"/"+(d.getMonth()+1)+"/"+(d.getYear()+1900);
			String tm=d.getHours()+":"+d.getMinutes();
			try {
				ipaddr=IPAddressGenrator.getIPAddr();
	    		macaddr=IPAddressGenrator.getMacAddr();
			}
			catch (Exception e) {
				// TODO: handle exception
			} 
    		String qr1="insert into fraudDetails values("+mxid+",'"+ses.getAttribute("userid").toString().trim()+"','"+ipaddr.trim()+"','"+macaddr.trim()+"','"+dt+"');";
    		if(jf.execute(qr1)) {}
    		}
    		catch (Exception e) {
				// TODO: handle exception
			}
				mv.setViewName("Failure.jsp?type=fraudDetected");
				mv.addObject("activity","fraudDetected");
		}
		return mv;
		 
	}
	@RequestMapping("changeProfile")
	public ModelAndView changeProfile(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		 try
		 { Base64Decoder decoder=new Base64Decoder();
		 String userid=new String(decoder.decode(request.getParameter("param")));
		 UserReg1 obj=new UserReg1();
		 System.out.println("userid1="+userid);
		 String[] str=userid.split("\\|");
		 
		 obj.setUserid(str[0].trim());
			 if(obj.deleteProfile() )
			 {
				 System.out.println("in del profile");
				 mv.setViewName("profile.jsp?param="+request.getParameter("param").trim());
					//mv.addObject("userid",request.getParameter("userid").toString().trim());
				 
			 }
			 else
			 { System.out.println("in del profile else");
				 mv.setViewName("Failure.jsp?type=RegUser");
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 mv.setViewName("Failure.jsp?type=RegUser");
		}
		 return mv;
	}
	@RequestMapping("/userImgLogin")
	public ModelAndView userImgLogin(HttpServletRequest request,HttpSession ses)
	{
		//String param=request.getParameter("param").toString().trim();
		// Base64Decoder decoder=new Base64Decoder();
		   String param1= "[0]"+"|favAnimal+hobby+favCity+favFood|"+ses.getAttribute("userid").toString().trim();
		   
		    System.out.println("param="+param1);
		    String[] str=param1.split("\\|");
		    String userid=str[2].trim();
		    String authNames=str[1].trim();
		    String lst=str[0].trim();
		    lst=lst.replace("[", "");
		    lst=lst.replace("]", "");
		    String[] lstarr=lst.split("\\ ");
		    String[] lstarrNames=authNames.split("\\+");
		    System.out.println("userid="+userid+" "+authNames+" "+lst);
		ModelAndView mv=new ModelAndView();
		List lst1=new ArrayList<String>();
		for(int i=0;i<lstarr.length;i++)
		{
			if(lstarr[i].trim().equals("1"))
			{
			lst1.add(lstarrNames[i].trim());
			}
			 System.out.println(lstarr[i].trim());
		}
		 Collections.shuffle(lst1);
		System.out.println("authnm="+lst1);
		GetLoginImages img=new GetLoginImages();
		
		List<GetLoginImages> lstimgs=new ArrayList<GetLoginImages>();
		String authcate="";
		if(lst1.size()>0)
		{
			String loginimgid=img.getLoginImgId(userid, lst1.get(0).toString().trim());
		
			 ses.setAttribute("loginImgId",loginimgid );
			authcate=lst1.get(0).toString().trim();
			System.out.println("loginImgid="+loginimgid);
		}
		else
		{
			String loginimgid=img.getLoginImgId(userid, lstarrNames[0].toString().trim());
			ses.setAttribute("loginImgId",loginimgid );
			System.out.println("loginImgid="+loginimgid);
			authcate=lstarrNames[0].toString().trim();
		}
		img.getImagesList(userid, authcate);
		lstimgs=img.getLstloginImg();
		mv.addObject("lst",lstimgs);
		mv.addObject("userid",userid);
		 mv.setViewName("UserImgLogin.jsp#login"); 
		return mv;
		 
	}
	@RequestMapping("/uploadFoodImg")
	public ModelAndView uploadFoodImg()
	{
		ModelAndView mv=new ModelAndView();
		Food hobby=new Food();
		hobby.getFoodList();
		List<Food> lst=new ArrayList<Food>();
		lst=hobby.getLstfood();
		mv.setViewName("uploadImgFood.jsp");
		mv.addObject("lst", lst);
		return mv;
		 
	}
	@SessionScope
	@RequestMapping("/RegAnimalImg")
	public ModelAndView RegAnimalImg(AnimalImages eobj,ServletRequest request,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
		 
		 try
		 {MultipartFile file=eobj.getFile();
		 String filepath=request.getServletContext().getRealPath("/")+"/UploadAnimalImg/";
		  
		 System.out.println("path="+filepath);
		 File f=new File(filepath);
		 f.mkdir();
		 f=new File(filepath);
		 f.mkdir();
		 try {
			 eobj.getId();
			 int mx=eobj.getAnimalImgId();
			 String fileName=mx+"."+ file.getOriginalFilename().split("\\.")[1];
			 file.transferTo(new File(filepath+"/"+fileName));
			  
			 eobj.setAnimalimgpath(fileName);
			  
			 if(eobj.registration() )
			 { 
				mv.setViewName("Success.jsp?type=ImgRegAnimal");
			 }
			 else
			 { 
				 mv.setViewName("Failure.jsp?type=ImgRegAnimal");
			 }
			 } catch (IOException e) {
				 
			 }
		 
			 
		 mv.setViewName("Success.jsp?type=ImgRegAnimal");
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 mv.setViewName("Failure.jsp?type=ImgRegAnimal");
		}
		 return mv;
	}
	@SessionScope
	@RequestMapping("/RegFoodImg")
	public ModelAndView RegFoodImg(FoodImages eobj,ServletRequest request,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
		 
		 try
		 {MultipartFile file=eobj.getFile();
		 String filepath=request.getServletContext().getRealPath("/")+"/UploadFoodImg/";
		  
		 System.out.println("path="+filepath);
		 File f=new File(filepath);
		 f.mkdir();
		 f=new File(filepath);
		 f.mkdir();
		 try {
			 
			 int mx=eobj.getId(); 
			 String fileName=mx+"."+ file.getOriginalFilename().split("\\.")[1];
			 file.transferTo(new File(filepath+"/"+fileName));
			  
			 eobj.setFoodimgpath(fileName);
			  
			 if(eobj.registration() )
			 { 
				mv.setViewName("Success.jsp?type=ImgRegFood");
			 }
			 else
			 { 
				 mv.setViewName("Failure.jsp?type=ImgRegFood");
			 }
			 } catch (IOException e) {
				 
			 }
		 
			 
		 mv.setViewName("Success.jsp?type=ImgRegFood");
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 mv.setViewName("Failure.jsp?type=ImgRegFood");
		}
		 return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/editProfileAuth")
	public String editProfileAuth(HttpSession ses)
	{
		return "IdkeyAuthEdit1.jsp";
	}
	 public String getErrorPath() {
	        return "/error";
	    }
	 @SessionScope
		@RequestMapping("/RevIdKeyAuth")
		public ModelAndView RevIdKeyAuth(HttpServletRequest request,HttpSession ses)
		{
		 ModelAndView mv=new ModelAndView();
		 
			 try { 
				 JavaFuns jf=new JavaFuns();
				 Vector v=jf.getValue("select secques from users where userid='"+ses.getAttribute("userid").toString().trim()+"'", 1);
				    String ques=v.elementAt(0).toString().trim();
				    mv.setViewName("squesAuth1.jsp");
				 	mv.addObject("sques",ques); 
					System.out.println("ques="+ques);
					 
			 }
			 catch (Exception e) {
				// TODO: handle exception
				 System.out.println("err="+e.getMessage());
				 mv.setViewName("Failure.jsp?type=squesAuth");
			}
			 return mv;
		}
	 @SessionScope
		@RequestMapping("/squesAuth")
		public ModelAndView squesAuth(HttpServletRequest request,HttpSession ses)
		{
		 ModelAndView mv=new ModelAndView();
		 
			 try { 
				 JavaFuns jf=new JavaFuns();
				 Vector v=jf.getValue("select secques from users where userid='"+ses.getAttribute("userid").toString().trim()+"'", 1);
				    String ques=v.elementAt(0).toString().trim();
				    mv.setViewName("squesAuth.jsp");
				 	mv.addObject("sques",ques); 
					System.out.println("ques="+ques);
					 
			 }
			 catch (Exception e) {
				// TODO: handle exception
				 System.out.println("err="+e.getMessage());
				 mv.setViewName("Failure.jsp?type=squesAuth");
			}
			 return mv;
		}
	 @RequestMapping("/SquesAuth2")
		public ModelAndView SquesAuth2(HttpServletRequest request,HttpSession ses)
		{
			JavaFuns jf=new JavaFuns();
			ModelAndView mv=new ModelAndView();
			try {
				System.out.println("key="+request.getParameter("sques").toString().trim());
				Vector v=jf.getValue("select ans from users where secques='"+request.getParameter("sques").toString().trim()+"' and userid='"+ses.getAttribute("userid").toString().trim()+"' and ans='"+request.getParameter("ans").toString().trim() +"'", 1);
				
				if(v.size()>0)
				{ 
					String idkey="na";
					 Mail mail=new Mail();
					 Vector v1=jf.getValue("select email,aes_decrypt(idkey,userid) as idkey from users where userid='"+ses.getAttribute("userid").toString().trim()+"'", 2);
					 idkey=v1.elementAt(1).toString().trim();
					String email=v1.elementAt(0).toString().trim();
						
					String msg="Dear user, your profile password is "+idkey;
					try {
						if(mail.sendMail(msg, email, "Profile Password")) {}
					}
					catch(Exception ee)
					{
						
					}
					mv.setViewName("Success.jsp?type=idkeyRev");
					
				}
				else
				{ 
					jf.execute("insert into idkeyrev_track values("+Integer.parseInt(ses.getAttribute("sessionid").toString().trim())+",'"+ses.getAttribute("userid").toString().trim()+"',1)");
					mv.setViewName("Failure.jsp?type=IdkeyAuth");
					mv.addObject("activity","IdkeyAuth");
				}
				
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
		    return mv;
		}
		 
	    @RequestMapping("/updateUser")
		public ModelAndView updateuser(UserReg stu,HttpServletRequest request,HttpSession ses)
		{String fileName="NA";
		int addr_track=0,email_track=0,mobile_track=0,shopamt_track=0;	
		ModelAndView mv=new ModelAndView();
		try
			 {
				 stu.setUserid(ses.getAttribute("userid").toString().trim());
				 if(!(stu.getPrevaddr().equals(stu.getAddress())))
				{
					 addr_track=1;
				}
				 if(!(stu.getPrevemail().equals(stu.getEmail())))
					{
						 email_track=1;
					}
				 if(!(stu.getPrevmobile().equals(stu.getMobile())))
					{
						 mobile_track=1;
					}
				 if(stu.getPrevshopAmt()!=stu.getShopAmt())
					{
						 shopamt_track=1;
					}
				 JavaFuns jf=new JavaFuns();
				 String sessid=ses.getAttribute("sessionid").toString().trim();
				 String userid=ses.getAttribute("userid").toString().trim();
				 
				 if(jf.execute("insert into editprofile_track values("+sessid+",'"+userid+"',"+addr_track+","+email_track+","+mobile_track+","+shopamt_track+")")) {}
				 if(stu.update())
					mv.setViewName("Success.jsp?type=updateProfile");
				else
					mv.setViewName("Failure.jsp?type=updateProfile");
			 }
			 catch (Exception e) {
				 System.out.println("in update="+e.getMessage());
					// TODO: handle exception
				 mv.setViewName("Failure.jsp?type=updateProfile");
				}
			 //mv.addObject("activity","StudProfile");
			 return mv;
		}
	@RequestMapping("/IdkeyAuthEdit1")
	public ModelAndView IdkeyAuthEdit1(ProfilePassword user,HttpSession ses)
	{
		JavaFuns jf=new JavaFuns();
		ModelAndView mv=new ModelAndView();
		try {
			//System.out.println("key="+user.getIdkey()+" userid="+user.getUserid());
			if(user.checkIDkey())
			{ 
				try
				{
				List<UserReg> lst = new ArrayList<UserReg>();
				UserReg vs = new UserReg();
				vs.getUserProfile(ses.getAttribute("userid").toString().trim());
				lst=vs.getLstuser();
				mv.addObject("std",lst);
				System.out.println("size="+lst.size());
				}
				catch (Exception e) {
				System.out.println("errr in edit="+e.getMessage());
				}
				mv.setViewName("EditProfile.jsp");
			     
			}
			else
			{ jf.execute("insert into idkey_track values("+Integer.parseInt(ses.getAttribute("sessionid").toString().trim())+",'"+ses.getAttribute("userid").toString().trim()+"',1)");
				mv.setViewName("Failure.jsp?type=IdkeyAuth");
			}
			
		}
		catch (Exception e1) {
			// TODO: handle exception
			mv.setViewName("Failure.jsp?type=IdkeyAuth");
			
		}
		
	    return mv;
	}
	@RequestMapping("/IdkeyAuth1")
	public String IdkeyAuth1(HttpSession ses)
	{
		return "IdkeyAuth.jsp";
	}
	@RequestMapping("/SquesAuth1")
	public ModelAndView SquesAuth1(HttpServletRequest request,HttpSession ses)
	{
		JavaFuns jf=new JavaFuns();
		ModelAndView mv=new ModelAndView();
		try {
			System.out.println("key="+request.getParameter("sques").toString().trim());
			Vector v=jf.getValue("select ans from users where secques='"+request.getParameter("sques").toString().trim()+"' and userid='"+ses.getAttribute("userid").toString().trim()+"' and ans='"+request.getParameter("ans").toString().trim() +"'", 1);
			
			if(v.size()>0)
			{ 
				 int sessionid=jf.insertSessionLog(ses.getAttribute("userid").toString().trim());
				 ses.setAttribute("sessionid", sessionid);
				 mv.setViewName(ses.getAttribute("utype").toString().trim()+"Home");
			     IPAddressGenrator.main(ses.getAttribute("userid").toString().trim());
			}
			else
			{ 
				mv.setViewName("Failure.jsp?type=AuthFailed");
				mv.addObject("activity","AuthFailed");
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	    return mv;
	}
	@SessionScope
	@RequestMapping("/OTPAuth")
	public ModelAndView OTPAuth( HttpServletRequest request,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
	 
		 try {
			 String otp=request.getParameter("otp").toString().trim();
			 String uotp=request.getParameter("uotp").toString().trim();
			  if(otp.equals(uotp))
			 {
				 JavaFuns jf=new JavaFuns();
				 if(jf.execute("update users set loginsts='active' where userid='"+ses.getAttribute("userid").toString().trim()+"'"))
				 {}
				 int sessionid=jf.insertSessionLog(ses.getAttribute("userid").toString().trim());
				 ses.setAttribute("sessionid", sessionid);
				 mv.setViewName(ses.getAttribute("utype").toString().trim()+"Home");
				  
			 }
			 else
			 { 
				mv.setViewName("Failure.jsp?type=OTPAuth"); 
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 mv.setViewName("Failure.jsp?type=OTPAuth"); 
		}
		 return mv;
	}
	@SessionScope
	@RequestMapping("/sendOTP")
	public ModelAndView sendOTP(HttpServletRequest request,HttpSession ses)
	{
	 ModelAndView mv=new ModelAndView();
	 
		 try { 
			    String otp=RandomString.getAlphaNumericString(4);
			    mv.setViewName("OTPVerification.jsp");
			 	mv.addObject("otp",otp);
				Mail mail=new Mail();
				System.out.println("otp="+otp);
				String msg="Dear "+ses.getAttribute("username").toString().trim()+", your one time password is "+otp;
				
				try
				{
				if(mail.sendMail(msg, ses.getAttribute("email").toString().trim(),"One time Password"))
				{
					
				}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			 
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 mv.setViewName("Failure.jsp?type=sendOTP");
		}
		 return mv;
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request)
	{
		Login obj=new Login();
		JavaFuns jf=new JavaFuns();
		 try
		 {
			 HttpSession ses=request.getSession(true);
			 if(request.getParameter("token").trim().equals(ses.getAttribute("token").toString().trim())) {
				 
			 
			 try {
			 
			 Vector v=jf.getValue("select userid from users where userid='"+request.getParameter("txtuserid").trim()+"'", 1);
			 if(v.size()>0)
			 {
				 if(jf.execute("insert into temp values('"+request.getParameter("txtuserid").trim()+"',"+System.currentTimeMillis()+",1)")) {}
				 jf.UpdateAttempts(request.getParameter("txtuserid").trim());
					
			 }}
			 catch (Exception e) {
				// TODO: handle exception
			}
			 if(obj.chkAuthentication(request.getParameter("txtuserid").trim(), request.getParameter("txtpass").trim()))
			 {
				 System.out.println("in auth");
				 int sessionid=jf.insertSessionLog(obj.getUserid());
				 ses.setAttribute("sessionid", sessionid);
				 System.out.println("ses="+sessionid);
				 if(obj.getuType().equals("admin"))
				 { ses.setAttribute("userid", obj.getUserid());
				 System.out.println("userid="+obj.getUserid());
				 System.out.println("userid="+obj.getuType());
				 System.out.println("userid="+obj.getUserName());
				 ses.setAttribute("utype", obj.getuType());
				 ses.setAttribute("username", obj.getUserName());
				 System.out.println("utype="+obj.getuType());
					 return "adminHome";
				 }
				 else
				 {
				 IPAddressGenrator ipobj=new IPAddressGenrator();
				 
				 
				 ses.setAttribute("userid", obj.getUserid());
				 System.out.println("userid="+obj.getUserid());
				 System.out.println("userid="+obj.getuType());
				 System.out.println("userid="+obj.getUserName());
				 ses.setAttribute("utype", obj.getuType());
				 ses.setAttribute("username", obj.getUserName());
				 System.out.println("utype="+obj.getuType());
				 /*if(obj.getuType().equals("user"))
				 {
					 return "getPredictedDiseases";
				 }
				 else*/
				 if(ipobj.main1(obj.getUserid().trim()))
				 {
				 Vector v=jf.getValue("select loginsts from users where userid='"+obj.getUserid().trim()+"'" , 1);
				 
				 if(v.elementAt(0).toString().trim().equals("deactive"))
				 {
					 return "sendOTP";	 
				 }
				 else
				 {
				  
				 return obj.getuType()+"Home";
				 }
				 }
				 else
				 {
					 System.out.println("in sques");
					 return "squesAuth";
				 }
				 }
			 }
			 else
			 { 
				 Vector v=jf.getValue("select loginsts from users where userid='"+request.getParameter("txtuserid").trim()+"'" , 1);
				 if(v.size()>0)
				 {
					 if(v.elementAt(0).toString().trim().equals("deactive"))
					 {
						 Vector v1=jf.getValue("select username,email,utype,userid from users where userid='"+request.getParameter("txtuserid").trim()+"' and pass='"+request.getParameter("txtpass").trim()+"' and loginsts='deactive'" , 4);
						 if(v1.size()>0)
						 {
							 ses.setAttribute("sessionid", "0");
							 ses.setAttribute("username", v1.elementAt(0).toString().trim());
							 ses.setAttribute("userid", v1.elementAt(3).toString().trim());
							 ses.setAttribute("utype", v1.elementAt(2).toString().trim());
							 ses.setAttribute("email", v1.elementAt(1).toString().trim());
							return "sendOTP"; 
						 }
						 else
						 return "Failure.jsp?type=DeactiveAuth";
					 }
					 else
					 {
						 return "Failure.jsp?type=Auth";
					 }
				 }
				 else
				 {
					 return "Failure.jsp?type=Auth";
				 }
				 
			 }
			 }
			 else
			 {
				 return("Failure.jsp?type=Auth");
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("errss="+e.getMessage());
			 return("Failure.jsp?type=Auth");
		}
		 
	}
	@RequestMapping("/Cities")
	public String cities()
	{
		return "Cities.jsp";
	}
	@RequestMapping("updateProfile")
	public ModelAndView updateProfile(UserReg1 obj)
	{
		ModelAndView mv=new ModelAndView();
		 try
		 {
			 if(obj.registration() )
			 {
				 
				 mv.setViewName("Success.jsp?type=UserReg");
					//mv.addObject("userid",request.getParameter("userid").toString().trim());
				// mv.addObject("path","http://localhost:8080/ProfileUpdated");
			
			 }
			 else
			 { 
				 mv.setViewName("Failure.jsp?type=RegUser");
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 mv.setViewName("Failure.jsp?type=RegUser");
		}
		 return mv;
	}
	@RequestMapping("/RegUser")
	public ModelAndView RegUser(UserReg eobj,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("profile.jsp?type=UserReg");
		 try
		 { 
			 if(eobj.registration())
			 {
				 
				 ses.setAttribute("userid", eobj.getUserid());
				 mv.addObject("userid",eobj.getUserid());
				 mv.addObject("username",eobj.getName());
				 return mv;
			 }
			 else
			 { 
				 mv.setViewName("Failure.jsp?type=UserReg");
				 return mv;
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 mv.setViewName("Failure.jsp?type=UserReg");
			 return mv;
		}
		 
	}
	@RequestMapping("/Cities1")
	public String cities1()
	{
		return "Cities1.jsp";
	}
}

