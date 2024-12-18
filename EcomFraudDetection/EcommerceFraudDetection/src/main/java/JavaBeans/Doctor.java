package JavaBeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 

public class Doctor {
private String name;
private String mobile;
private String email;
private String eduDetails;
private String profDetails;
private List<Doctor> lstexpert = new ArrayList<Doctor>();
Connection con;
CallableStatement csmt;
PreparedStatement psmt;
ResultSet rs;
 
public List<Doctor> getLstexpert() {
	return lstexpert;
}

public void setLstexpert(List<Doctor> lstexpert) {
	this.lstexpert = lstexpert;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getEduDetails() {
	return eduDetails;
}

public void setEduDetails(String eduDetails) {
	this.eduDetails = eduDetails;
}

public String getProfDetails() {
	return profDetails;
}

public void setProfDetails(String profDetails) {
	this.profDetails = profDetails;
}
public Doctor()
{
	
}
public Doctor(ResultSet rs)
{
	try
	{
	name=rs.getString("doctorName").toString().trim();
	eduDetails=rs.getString("eduDetails").toString().trim();
	profDetails=rs.getString("profDetails").toString().trim();
	email=rs.getString("email").toString().trim();
	mobile=rs.getString("mobile").toString().trim();
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
public void getDoctors(String userid1)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getDoctors()}");
        lstexpert=new ArrayList<Doctor>();
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstexpert.add(new Doctor(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public boolean Registration()
{
    try
    {
    	String userid,pass;
    	userid=name.split("\\ ")[0]+"@";
    	Random rnd=new Random();
    	pass="Heart#"+(rnd.nextInt()+1000);
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call insertDoctor(?,?,?,?,?,?,?)}");
        csmt.setString(1, userid);
        csmt.setString(2, pass);
        csmt.setString(3, name);
        csmt.setString(4, mobile);
        csmt.setString(5, email);
        csmt.setString(6, eduDetails);
        csmt.setString(7, profDetails); 
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
