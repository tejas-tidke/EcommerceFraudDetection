package JavaBeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import services.IPAddressGenrator;
 
public class UserReg {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private String name,mobile,profpass,culture,email,city,address, prof,gender, state, userid,pass,dob ,sentOTP,otp;
    private double shopAmt,prevshopAmt;
    private String prevemail,prevaddr,prevmobile,city1,sques,ans ;
   
    private List<UserReg> lstuser;
     
     
      
public String getProfpass() {
		return profpass;
	}
	public void setProfpass(String profpass) {
		this.profpass = profpass;
	}
public double getShopAmt() {
		return shopAmt;
	}
	public void setShopAmt(double shopAmt) {
		this.shopAmt = shopAmt;
	}
	public double getPrevshopAmt() {
		return prevshopAmt;
	}
	public void setPrevshopAmt(double prevshopAmt) {
		this.prevshopAmt = prevshopAmt;
	}
	public String getPrevemail() {
		return prevemail;
	}
	public void setPrevemail(String prevemail) {
		this.prevemail = prevemail;
	}
	public String getPrevaddr() {
		return prevaddr;
	}
	public void setPrevaddr(String prevaddr) {
		this.prevaddr = prevaddr;
	}
	public String getPrevmobile() {
		return prevmobile;
	}
	public void setPrevmobile(String prevmobile) {
		this.prevmobile = prevmobile;
	}
	public String getCity1() {
		return city1;
	}
	public void setCity1(String city1) {
		this.city1 = city1;
	}
	public String getSques() {
		return sques;
	}
	public void setSques(String sques) {
		this.sques = sques;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
public String getCulture() {
		return culture;
	}
	public void setCulture(String culture) {
		this.culture = culture;
	}
public List<UserReg> getLstuser() {
		return lstuser;
	}
	public void setLstuser(List<UserReg> lstuser) {
		this.lstuser = lstuser;
	}
public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProf() {
		return prof;
	}
	public void setProf(String prof) {
		this.prof = prof;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getSentOTP() {
		return sentOTP;
	}
	public void setSentOTP(String sentOTP) {
		this.sentOTP = sentOTP;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
public boolean useridAuth()
{
	boolean flag=false;
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call useridAuth(?)}");
         csmt.setString(1, userid);
         
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        email=rs.getString("email").trim();
        name=rs.getString("username").trim();
        flag=true;
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
    return flag;
}
public boolean updatePass()
{
    try
    {
    	String bodycond="NA";
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call updatePassword(?,?)}");
        csmt.setString(1, userid);
        csmt.setString(2, pass);
        
         int n=csmt.executeUpdate();
         
                    
        
        if(n>0)
        {
            try{con.close();}catch(Exception ex){}
            System.out.println("true");
            return true;
        }
        else
            return false;

        }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
        return false;
    }
}
	/*public void getId()
	    {
	        try
	        {
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call getMaxIdUsers()}");
	           
	             csmt.execute();
	             rs=csmt.getResultSet();
	                        
	            boolean auth=false;
	            while(rs.next())
	            { System.out.println("true");
	                auth=true;
	                
	                mxid=rs.getInt("mxid");
	                  
	            }
	        }
	           
	         
	        catch(Exception ex)
	        {
	            System.out.println("err="+ex.getMessage());
	             
	        }
	    }*/
	public UserReg()
	{
	}
	public UserReg(ResultSet rs)
	{
		try
		{
		name=rs.getString("userName").toString().trim();
		state=rs.getString("state").toString().trim();
		city=rs.getString("city").toString().trim();
		email=rs.getString("email").toString().trim();
		mobile=rs.getString("mobile").toString().trim();
		gender=rs.getString("gender").toString().trim();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err="+e.getMessage());
		}
	}
	public void getUsers()
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getUsers()}");
	        lstuser=new ArrayList<UserReg>();
	         csmt.execute();
	         rs=csmt.getResultSet();
	                     
	        while(rs.next())
	        { System.out.println("true");
	        lstuser.add(new UserReg(rs));
	              
	        }
	    }
	       
	     
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	}
	public boolean update()
	{
	    try
	    {  
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call updateUser(?,?,?,?,?,?,?,?,?,?,?,?,?,? )}");
	        csmt.setString(1, userid);
	        System.out.println(" uid="+userid);
	        csmt.setString(2, "NA");
	        csmt.setString(3, name);
	        System.out.println(" nm="+name);
	        csmt.setString(4,(mobile));
	        System.out.println(" mob="+mobile);
	        csmt.setString(5, (email));
	        System.out.println(" email="+email);
	      if(city==null)
	    	  city=city1;
	        csmt.setString(6, (city));
	        System.out.println(" city="+city);
	        csmt.setString(7, (state));
	        System.out.println(" city="+state);
	        csmt.setString(8, ("NA"));
	        System.out.println(" addr="+address);
	        csmt.setString(9, (address));
	        System.out.println(" dob="+dob);
	        csmt.setString(10, (dob));
	        
	        csmt.setString(11, sques);
	        csmt.setString(12, ans);
	        csmt.setString(13, prof);
	        csmt.setDouble(14, shopAmt);
	          int n=csmt.executeUpdate(); 
	        
	        if(n>0)
	        {
	            try{con.close();}catch(Exception ex){}
	            System.out.println("true");
	            return true;
	        }
	        else
	            return false;

	        }
	       
	     
	    catch(Exception ex)
	    {
	    	
	        System.out.println("err="+ex.getMessage());
	        return false;
	    }
	}
	public void getUserProfile(String userid1)
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        
	        	  csmt=con.prepareCall("{call getUserProfile(?)}");
	        	  csmt.setString(1, userid1);
	        	 
	 	         csmt.execute();
	 	         rs=csmt.getResultSet();
	          
	     
	                     lstuser=new ArrayList<UserReg>();
	        while(rs.next())
	        { System.out.println("true");
	        System.out.println("in getall users"+userid1);
	        UserReg uobj=new UserReg();
	        System.out.println(  "email="+rs.getString("email").trim());
	        uobj.setUserid(rs.getString("userid").toString().trim());
	        uobj.setName(rs.getString("userName").toString().trim());
	        
	        uobj.setEmail(rs.getString("email").toString().trim());
	        uobj.setMobile(rs.getString("mobile").toString().trim());
	        uobj.setState(rs.getString("state").toString().trim());
	        uobj.setCity(rs.getString("city").toString().trim());
	        uobj.setGender(rs.getString("gender").toString().trim());
	        uobj.setDob(rs.getString("dob").toString().trim());
	        uobj.setAddress(rs.getString("addr").toString().trim());
	        uobj.setProf(rs.getString("profession").toString().trim());
	         uobj.setSques(rs.getString("secques").toString().trim());
	        uobj.setAns(rs.getString("ans").toString().trim());
	        uobj.setShopAmt(rs.getDouble("maxPurchaseAmt"));
	       
	        System.out.println("userid="+rs.getString("userid").toString().trim()+" "+rs.getString("userName").toString().trim()+" "+rs.getString("email").toString().trim());
	        lstuser.add(uobj);
	              
	        }
	    } 
	    catch(Exception ex)
	    {
	        System.out.println("err in getuser="+ex.getMessage());
	         
	    }
	}
	public boolean registration()
	    {
	        try
	        {
	        	String bodycond="NA";
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call insertUser(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
	            csmt.setString(1, userid);
	            csmt.setString(2, pass);
	            csmt.setString(3, name);
	            csmt.setString(4, mobile);
	            csmt.setString(5, email);
	         
	            csmt.setString(6, prof);
	            csmt.setString(7, state);
	            csmt.setString(8, city); 
	            csmt.setString(9, address); 
	            csmt.setString(10, gender);
	            csmt.setString(11, dob);
	            csmt.setString(12, culture);
	            csmt.setDouble(13, shopAmt);
	            csmt.setString(14, sques);
	            csmt.setString(15, ans);
	            csmt.setString(16, profpass);
	             int n=csmt.executeUpdate(); 
	            if(n>0)
	            {
	            	IPAddressGenrator.main(userid);  
	                return true;
	            }
	            else
	                return false;

	            } 
	        catch(Exception ex)
	        {
	            System.out.println("err="+ex.getMessage());
	            return false;
	        }
	    }

}
