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
 
 

 

public class CityImages {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
private String cityimgpath,city,state;
private int cityId,cityImgId;
private List<CityImages> lstcity;
private MultipartFile file;


public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public int getCityImgId() {
	return cityImgId;
}

public void setCityImgId(int cityImgId) {
	this.cityImgId = cityImgId;
}

public int getCityId() {
	return cityId;
}

public void setCityimgpath(String cityimgpath) {
	this.cityimgpath = cityimgpath;
}

public MultipartFile getFile() {
	return file;
}

public void setFile(MultipartFile file) {
	this.file = file;
}

public String getCityimgpath() {
	return cityimgpath;
}

public void setcityimgpath(String cityimgpath) {
	this.cityimgpath = cityimgpath;
}

public int getcityId() {
	return cityId;
}

public void setCityId(int cityId) {
	this.cityId = cityId;
}

public int getcityImgId() {
	return cityImgId;
}

public void setcityImgId(int cityImgId) {
	this.cityImgId = cityImgId;
}

public List<CityImages> getLstcity() {
	return lstcity;
}

public void setLstcity(List<CityImages> lstcity) {
	this.lstcity = lstcity;
}

public int getId()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getMaxIdcityImg()}");
       
         csmt.execute();
         rs=csmt.getResultSet();
                    
        boolean auth=false;
        while(rs.next())
        { System.out.println("true");
            auth=true;
            
            cityImgId=rs.getInt("mxid");
            if(cityImgId==0)
            	cityImgId=1001;
            else
            	cityImgId+=1;
              
        }
        System.out.println("cityid="+cityImgId);
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
    return cityImgId;
}

public CityImages()
{
	
}
public CityImages(ResultSet rs)
{
	try
	{
	cityimgpath=rs.getString("cityImgPath").toString().trim();
	cityId=rs.getInt("city");
	cityImgId=rs.getInt("cityImgId");
	 
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
  
public void getImagesList(int cityId)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getcityImages(?)}");
        lstcity=new ArrayList<CityImages>();
        csmt.setInt(1, cityId);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstcity.add(new CityImages(rs));
              
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
        csmt=con.prepareCall("{call insertcityImg(?,?,?)}");
        csmt.setInt(1, cityImgId);
        csmt.setString(2, city);
        csmt.setString(3, cityimgpath);
         
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
