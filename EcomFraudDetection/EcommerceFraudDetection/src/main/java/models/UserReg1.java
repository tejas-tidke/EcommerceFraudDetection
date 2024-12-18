package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import JavaBeans.DBConnector;
 
public class UserReg1 {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private List<UserReg1> lstuser;
    private String username,userid,userShare,serverShare,state,city,animal,food,hobby;
   public UserReg1() {}
      
     
	public List<UserReg1> getLstuser() {
		return lstuser;
	}

 
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getUserShare() {
		return userShare;
	}


	public void setUserShare(String userShare) {
		this.userShare = userShare;
	}


	public String getServerShare() {
		return serverShare;
	}


	public void setServerShare(String serverShare) {
		this.serverShare = serverShare;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getAnimal() {
		return animal;
	}


	public void setAnimal(String animal) {
		this.animal = animal;
	}


	public String getFood() {
		return food;
	}


	public void setFood(String food) {
		this.food = food;
	}


	public String getHobby() {
		return hobby;
	}


	public void setHobby(String hobby) {
		this.hobby = hobby;
	}


	public void setLstuser(List<UserReg1> lstuser) {
		this.lstuser = lstuser;
	}


	public UserReg1(ResultSet rs)
	{
		try
		{
		state=rs.getString("state").toString().trim();
		city=rs.getString("city").toString().trim();
		animal=rs.getString("animal").toString().trim();
		food=rs.getString("food").toString().trim();
		hobby=rs.getString("hobby").toString().trim();
		userid=rs.getString("userid").toString().trim();
		username=rs.getString("userName").toString().trim();
		 
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
	        lstuser=new ArrayList<UserReg1>();
	         
	         csmt.execute();
	         rs=csmt.getResultSet();
	                     
	        while(rs.next())
	        { System.out.println("true");
	        lstuser.add(new UserReg1(rs));
	              
	        }
	    }
	       
	     
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
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
	public boolean deleteProfile()
    {
        try
        { 
        	Date dt1=new Date();
        	String dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            csmt=con.prepareCall("{call deleteUserProfile(?)}");
            csmt.setString(1, userid);
             
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
	public boolean registration()
	    {
	        try
	        {
	        	Random rnd=new Random();
	        	String serverPart="PswServerShare#"+rnd.nextInt(9999);
	        	System.out.println("animal="+animal);
	        	Date dt1=new Date();
	        	String dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call insertUserProfile(?,?,?,?,?,?,?,?,?,?,?)}");
	            csmt.setString(1, userid);
	            csmt.setString(2, username);
	            csmt.setString(3, animal);
	            csmt.setString(4, hobby);
	            csmt.setString(5, city);
	            csmt.setString(6, food);
	            csmt.setString(7, dt);
	            csmt.setString(8, serverPart); 
	            csmt.setString(9, userShare);
	            csmt.setString(10, serverShare);
	            csmt.setString(11, state);
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
	 
}
