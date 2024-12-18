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
 
 

 

public class FoodImages {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
private String foodimgpath;
private int foodId,foodImgId;
private List<FoodImages> lstfood;
private MultipartFile file;


public MultipartFile getFile() {
	return file;
}

public void setFile(MultipartFile file) {
	this.file = file;
}
 
public String getFoodimgpath() {
	return foodimgpath;
}

public void setFoodimgpath(String foodimgpath) {
	this.foodimgpath = foodimgpath;
}

public int getFoodId() {
	return foodId;
}

public void setFoodId(int foodId) {
	this.foodId = foodId;
}

public int getFoodImgId() {
	return foodImgId;
}

public void setFoodImgId(int foodImgId) {
	this.foodImgId = foodImgId;
}

public List<FoodImages> getLstfood() {
	return lstfood;
}

public void setLstfood(List<FoodImages> lstfood) {
	this.lstfood = lstfood;
}

public int getId()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getMaxIdFoodImg()}");
       
         csmt.execute();
         rs=csmt.getResultSet();
                    
        boolean auth=false;
        while(rs.next())
        { System.out.println("true");
            auth=true;
            
            foodImgId=rs.getInt("mxid");
            if(foodImgId==0)
            	foodImgId=1001;
            else
            	foodImgId+=1;
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
    return foodImgId;
}

public FoodImages()
{
	
}
public FoodImages(ResultSet rs)
{
	try
	{
	foodimgpath=rs.getString("foodImgPath").toString().trim();
	foodId=rs.getInt("foodId");
	foodImgId=rs.getInt("foodImgId");
	 
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
  
public void getImagesList(int hobbyId)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getFoodImages(?)}");
        lstfood=new ArrayList<FoodImages>();
        csmt.setInt(1, foodId);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstfood.add(new FoodImages(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}  
public boolean registration()
{
    try
    { 
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        java.util.Date d=new java.util.Date();
        String dt1=(d.getDate()+"/"+(d.getMonth()+1)+"/"+(d.getYear()+1900));
        String tm1=d.getHours()+":"+d.getMinutes();
        csmt=con.prepareCall("{call insertFoodImg(?,?,?)}");
        csmt.setInt(1, foodImgId);
        csmt.setInt(2, foodId);
        csmt.setString(3, foodimgpath);
         
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
