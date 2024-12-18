package JavaBeans;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
  


public class Reviews {
	private int reviewId,prodId;
	private String reviewText,dt;
	private String userid,username;
	Connection con;
	CallableStatement csmt;
	ResultSet rs;
	List<Reviews> lstreviews;
	 
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	 
	public String getReviewText() {
		return reviewText;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public List<Reviews> getLstreviews() {
		return lstreviews;
	}
	public void setLstreviews(List<Reviews> lstreviews) {
		this.lstreviews = lstreviews;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
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
	public Reviews()
	{
		
	}
	public Reviews(ResultSet rs)
	{
		try
		{
		reviewId=rs.getInt("reviewId");
		
		reviewText=rs.getString("reviewText").toString().trim();
		prodId=rs.getInt("prodId");
		dt=rs.getString("dt").toString().trim();
		userid=rs.getString("userid").toString().trim();
		username=rs.getString("username").toString().trim();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	public boolean registerReview(String path) {
	
	Connection con;
	CallableStatement csmt;
	DBConnector gc = new DBConnector();
	String sts="";
	
	try {
		con=gc.connect();
		 java.util.Date dt1=new Date();
		 dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
		 csmt=con.prepareCall("{call insertReviews(?,?,?,?,?,?,?)}");
	        csmt.setString(1, reviewText);
	        csmt.setString(2, userid);
	        csmt.setString(3, username);
	        csmt.setInt(4, prodId);
	        
	        csmt.setString(5, dt);
	        csmt.setString(6, "NA");
	        csmt.setDouble(7, 0);
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
	public void getReviews()
 	{
 	    try
 	    {
 	    	DBConnector obj = new DBConnector();
 	         
 	        con=obj.connect() ;
 	        csmt=con.prepareCall("{call getReviews(?)}"); 
 	        csmt.setInt(1, prodId);
 	         csmt.execute();
 	         rs=csmt.getResultSet();
 	           lstreviews=new ArrayList<Reviews>();          
 	        while(rs.next())
 	        { System.out.println("true");
 	       lstreviews.add(new Reviews(rs)); 
 	        }
 	    }
 	       
 	     
 	    catch(Exception ex)
 	    {
 	        System.out.println("err="+ex.getMessage());
 	         
 	    }
 	}
	 
}
