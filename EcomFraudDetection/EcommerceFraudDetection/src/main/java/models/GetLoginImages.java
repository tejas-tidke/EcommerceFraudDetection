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
import services.Base64Encoder;
 
 

 

public class GetLoginImages {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
private String id,imgPath,userid11;
 
private List<GetLoginImages> lstloginImg;
 

 
public String getUserid11() {
	return userid11;
}
public void setUserid11(String userid11) {
	this.userid11 = userid11;
}
public GetLoginImages()
{
	
}
public GetLoginImages(ResultSet rs,String uid,String cate1)
{
	try
	{
		Base64Encoder encoder=new Base64Encoder(); 
	imgPath=rs.getString("imgPath").toString().trim();
	String param=rs.getString("id")+"|"+uid+"|"+cate1;
	id=encoder.encode(param.getBytes());
	  
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
  
public void getImagesList(String userid1,String authCate)
{
    try
    {
    	userid11=userid1;
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getLoginImages(?,?)}");
        lstloginImg=new ArrayList<GetLoginImages>();
        csmt.setString(1, userid1);
        csmt.setString(2, authCate);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstloginImg.add(new GetLoginImages(rs,userid1,authCate));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}   

public void loginLog(String userid1,String authCate,String sts)
{
	String id1="NA";
    try
    {
    	java.util.Date dt1=new java.util.Date();
    	String dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
    	String tm=dt1.getHours()+":"+dt1.getMinutes();
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call insertAuthLog(?,?,?,?,?)}");
         
        csmt.setString(1, userid1);
        csmt.setString(2, authCate);
        csmt.setString(3, dt);
        csmt.setString(4, tm);
        csmt.setString(5, sts);
         int n=csmt.executeUpdate();
                     
        
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
     
}
public int getFailureAttempts(String userid1)
{
int cnt=0;
    try
    {
    	java.util.Date dt1=new java.util.Date();
	String dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
	
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getFailureAttempts(?,?)}");
         
        csmt.setString(1, userid1);
        csmt.setString(2, dt);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        cnt=rs.getInt("cnt") ;      
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
    return cnt;
}
public String getLoginImgId(String userid1,String authCate)
{
	String id1="NA";
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getAuthImgId(?,?)}");
         
        csmt.setString(1, userid1);
        csmt.setString(2, authCate);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        id1=rs.getString("id").trim();      
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
    return id1;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getImgPath() {
	return imgPath;
}
public void setImgPath(String imgPath) {
	this.imgPath = imgPath;
}
public List<GetLoginImages> getLstloginImg() {
	return lstloginImg;
}
public void setLstloginImg(List<GetLoginImages> lstloginImg) {
	this.lstloginImg = lstloginImg;
}   
}
