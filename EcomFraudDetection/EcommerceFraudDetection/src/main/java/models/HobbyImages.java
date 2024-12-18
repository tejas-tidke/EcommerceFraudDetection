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
 
 

 

public class HobbyImages {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
private String hobbyimgpath;
private int hobbyId,hobbyImgId;
private List<HobbyImages> lsthobby;
private MultipartFile file;


public MultipartFile getFile() {
	return file;
}

public void setFile(MultipartFile file) {
	this.file = file;
}

public String getHobbyimgpath() {
	return hobbyimgpath;
}

public void setHobbyimgpath(String hobbyimgpath) {
	this.hobbyimgpath = hobbyimgpath;
}

public int getHobbyId() {
	return hobbyId;
}

public void setHobbyId(int hobbyId) {
	this.hobbyId = hobbyId;
}

public int getHobbyImgId() {
	return hobbyImgId;
}

public void setHobbyImgId(int hobbyImgId) {
	this.hobbyImgId = hobbyImgId;
}

public List<HobbyImages> getLsthobby() {
	return lsthobby;
}

public void setLsthobby(List<HobbyImages> lsthobby) {
	this.lsthobby = lsthobby;
}

public void getId()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getMaxIdHobbyImg()}");
       
         csmt.execute();
         rs=csmt.getResultSet();
                    
        boolean auth=false;
        while(rs.next())
        { System.out.println("true");
            auth=true;
            
            hobbyImgId=rs.getInt("mxid");
            if(hobbyImgId==0)
            	hobbyImgId=1001;
            else
            	hobbyImgId+=1;
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}

public HobbyImages()
{
	
}
public HobbyImages(ResultSet rs)
{
	try
	{
	hobbyimgpath=rs.getString("hobbyImgPath").toString().trim();
	hobbyId=rs.getInt("hobbyId");
	hobbyImgId=rs.getInt("hobbyImgId");
	 
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
        csmt=con.prepareCall("{call getHobbyImages(?)}");
        lsthobby=new ArrayList<HobbyImages>();
        csmt.setInt(1, hobbyId);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lsthobby.add(new HobbyImages(rs));
              
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
        csmt=con.prepareCall("{call insertHobbyImg(?,?,?)}");
        csmt.setInt(1, hobbyImgId);
        csmt.setInt(2, hobbyId);
        csmt.setString(3, hobbyimgpath);
         
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
