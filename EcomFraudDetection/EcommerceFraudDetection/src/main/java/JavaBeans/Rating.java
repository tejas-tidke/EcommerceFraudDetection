package JavaBeans;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
  


public class Rating {
	private int reviewId,prodId;
	private String dt;
	private double prating;
	private String userid,username;
	Connection con;
	CallableStatement csmt;
	ResultSet rs;
	List<Rating> lstreviews;
	 
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public double getPrating() {
		return prating;
	}
	public void setPrating(double prating) {
		this.prating = prating;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Rating> getLstreviews() {
		return lstreviews;
	}
	public void setLstreviews(List<Rating> lstreviews) {
		this.lstreviews = lstreviews;
	}
	public Rating()
	{
		
	}
	public Rating(ResultSet rs)
	{
		try
		{
		reviewId=rs.getInt("rateid");
		 
		dt=rs.getString("dt").toString().trim();
		userid=rs.getString("userid").toString().trim();
		username=rs.getString("username").toString().trim();
		prating=rs.getInt("prating");
		  
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	public boolean registerRating() {
	
	Connection con;
	CallableStatement csmt;
	DBConnector gc = new DBConnector();
	String sts="";
	
	try {
		 
		con=gc.connect();
		 java.util.Date dt1=new Date();
		 dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
		 csmt=con.prepareCall("{call insertRating(?,?,?,?,?)}");
	         csmt.setString(1, userid);
	        csmt.setString(2, username);
	       csmt.setInt(3, prodId);
	         csmt.setString(4, dt);
	        csmt.setDouble(5, prating);
	       
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
	catch(Exception ex) {
		System.out.println("err="+ex.getMessage());
		ex.printStackTrace();
		return false; 
	}
	 
	}
	public void getRatings()
 	{
 	    try
 	    {
 	    	DBConnector obj = new DBConnector();
 	         
 	        con=obj.connect()  ;
 	        csmt=con.prepareCall("{call getRatings(?)}"); 
 	        csmt.setInt(1, prodId);
 	         csmt.execute();
 	         rs=csmt.getResultSet();
 	           lstreviews=new ArrayList<Rating>();          
 	        while(rs.next())
 	        { System.out.println("true");
 	       lstreviews.add(new Rating(rs)); 
 	        }
 	    }
 	       
 	     
 	    catch(Exception ex)
 	    {
 	        System.out.println("err="+ex.getMessage());
 	         
 	    }
 	}
	 
}
