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
  

public class Food {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
private String foodItems;
private int foodId;
private List<Food> lstfood;
  
public String getFoodItems() {
	return foodItems;
}
public void setFoodItems(String foodItems) {
	this.foodItems = foodItems;
}
public int getFoodId() {
	return foodId;
}
public void setFoodId(int foodId) {
	this.foodId = foodId;
}
public List<Food> getLstfood() {
	return lstfood;
}
public void setLstfood(List<Food> lstfood) {
	this.lstfood = lstfood;
}
public Food()
{
	
}
public Food(ResultSet rs)
{
	try
	{
	foodItems=rs.getString("foodItems").toString().trim();
	foodId=rs.getInt("foodId");
	  
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
  
public void getFoodList()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getFoodItems()}");
        lstfood=new ArrayList<Food>();
         
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstfood.add(new Food(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}  

public void getFoodList1()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getFoodItems1()}");
        lstfood=new ArrayList<Food>();
         
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstfood.add(new Food(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}  
}
