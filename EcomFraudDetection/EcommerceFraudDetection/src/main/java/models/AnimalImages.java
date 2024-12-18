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
 
 

 

public class AnimalImages {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
private String animalimgpath;
private int animalId,animalImgId;
private List<AnimalImages> lstanimal;
private MultipartFile file;


public String getAnimalimgpath() {
	return animalimgpath;
}

public void setAnimalimgpath(String animalimgpath) {
	this.animalimgpath = animalimgpath;
}

public int getAnimalId() {
	return animalId;
}

public void setAnimalId(int animalId) {
	this.animalId = animalId;
}

public int getAnimalImgId() {
	return animalImgId;
}

public void setAnimalImgId(int animalImgId) {
	this.animalImgId = animalImgId;
}

public List<AnimalImages> getLstanimal() {
	return lstanimal;
}

public void setLstanimal(List<AnimalImages> lstanimal) {
	this.lstanimal = lstanimal;
}

public MultipartFile getFile() {
	return file;
}

public void setFile(MultipartFile file) {
	this.file = file;
}
 
public void getId()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getMaxIdAnimalImg()}");
       
         csmt.execute();
         rs=csmt.getResultSet();
                    
        boolean auth=false;
        while(rs.next())
        { System.out.println("true");
            auth=true;
            
            animalImgId=rs.getInt("mxid");
            if(animalImgId==0)
            	animalImgId=1001;
            else
            	animalImgId+=1;
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}

public AnimalImages()
{
	
}
public AnimalImages(ResultSet rs)
{
	try
	{
	animalimgpath=rs.getString("animalImgPath").toString().trim();
	animalId=rs.getInt("animalId");
	animalImgId=rs.getInt("animalImgId");
	 
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
        csmt=con.prepareCall("{call getAnimalImages(?)}");
        lstanimal=new ArrayList<AnimalImages>();
        csmt.setInt(1, hobbyId);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstanimal.add(new AnimalImages(rs));
              
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
        csmt=con.prepareCall("{call insertAnimalImg(?,?,?)}");
        csmt.setInt(1, animalImgId);
        csmt.setInt(2, animalId);
        csmt.setString(3, animalimgpath);
         
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
