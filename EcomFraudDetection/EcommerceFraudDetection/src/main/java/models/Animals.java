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
 
 

 

public class Animals {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
private String animalName;
private int animalId;
private List<Animals> lstanimal;
 
   
public String getAnimalName() {
	return animalName;
}
public void setAnimalName(String animalName) {
	this.animalName = animalName;
}
public int getAnimalId() {
	return animalId;
}
public void setAnimalId(int animalId) {
	this.animalId = animalId;
}
public List<Animals> getLstanimal() {
	return lstanimal;
}
public void setLstanimal(List<Animals> lstanimal) {
	this.lstanimal = lstanimal;
}
public Animals()
{
	
}
public Animals(ResultSet rs)
{
	try
	{
	animalName=rs.getString("animalName").toString().trim();
	animalId=rs.getInt("animalId");
	  
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
  
public void getAnimalList()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getAnimals()}");
        lstanimal=new ArrayList<Animals>();
         
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstanimal.add(new Animals(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}  
public void getAnimalList1()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getAnimals1()}");
        lstanimal=new ArrayList<Animals>();
         
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstanimal.add(new Animals(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}  
}
