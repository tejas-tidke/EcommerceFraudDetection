package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import JavaBeans.DBConnector;
 
 

 

public class Hobbies {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
private String hobbyText;
private int hobbyId;
private List<Hobbies> lsthobby;
 
  
public String getHobbyText() {
	return hobbyText;
}
public void setHobbyText(String hobbyText) {
	this.hobbyText = hobbyText;
}
public int getHobbyId() {
	return hobbyId;
}
public void setHobbyId(int hobbyId) {
	this.hobbyId = hobbyId;
}
public List<Hobbies> getLsthobby() {
	return lsthobby;
}
public void setLsthobby(List<Hobbies> lsthobby) {
	this.lsthobby = lsthobby;
}
public Hobbies()
{
	
}
public Hobbies(ResultSet rs)
{
	try
	{
	hobbyText=rs.getString("hobbyText").toString().trim();
	hobbyId=rs.getInt("hobbyId");
	  
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
  
public void getHobbiesList()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getHobbies()}");
        lsthobby=new ArrayList<Hobbies>();
         
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lsthobby.add(new Hobbies(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}  

public void getHobbiesList1()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getHobbies1()}");
        lsthobby=new ArrayList<Hobbies>();
         
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lsthobby.add(new Hobbies(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}  
}
