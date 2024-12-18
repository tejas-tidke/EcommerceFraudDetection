/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;
 
  
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
 
 
  
/**
 *
 * @author megha
 */
public class JavaFuns {
        Connection con;
     PreparedStatement pst;
     CallableStatement cst;
     ResultSet rs;
     public int FetchMax(String fldnm,String tblnm)
     {
        int maxid=1000;
     try
     {
     	  DBConnector obj=new  DBConnector();
           con=obj.connect();
         String qr="select max("+fldnm+") as mxid from "+tblnm;
         pst=con.prepareStatement(qr);
         rs=pst.executeQuery();
         while(rs.next())
         maxid=rs.getInt("mxid");
         if(maxid==0)
             maxid=1000;
     }
     catch(Exception ex)
     {
         System.out.println("err="+ex.getMessage());
     maxid=1000;
     }
     return (maxid+1);
     }
    public Vector getValue(String qr,int nocol)
    {    
        Vector v=new Vector();
        Connection con=null;
        Statement st;
        ResultSet rs;
        v.clear();
         try{ 
              DBConnector obj=new  DBConnector();
             con=obj.connect();
           st=con.createStatement();
           System.out.println("query="+qr);
           rs=st.executeQuery(qr);
          
           while(rs.next())
           {
               for(int i=1;i<=nocol;i++)
               {
               v.addElement(rs.getString(i));
               System.out.println(rs.getString(i));
               }              
           }
         }
         catch(Exception e)
        {
            System.out.println("Error in processing due to "+e.getMessage());
        }   
          finally
         {
             try{
             con.close();}catch(Exception ex){}
         }
        return(v);        
    }
    public boolean execute(String qr)
    {
         Boolean sts=false;
         try
        {
        	 DBConnector obj=new DBConnector();
            con=obj.connect();
            Statement st=con.createStatement();
            st.executeUpdate(qr);
            sts=true;
        }
        catch(Exception ex)
        {
        sts=false;
        }
         finally
         {
             try{
             con.close();}catch(Exception ex){}
         }
        return sts;  
        
    }  
    public void UpdateAttempts(String userid)
    {
    	Connection con;
        CallableStatement csmt;
        ResultSet rs; 
	        try
	        {
	            DBConnector obj=new  DBConnector();
	            con=obj.connect();
	           
	            csmt=con.prepareCall("{call UpdateAttempts(? )}");
	            csmt.setString(1, userid);
	            
	            int n=csmt.executeUpdate();
	        }
	        catch(Exception ex)
	        {
	            System.out.println("err="+ex.getMessage());
	            
	        }
    }
    public void threatLog(String userid)
    {
    	Connection con;
        CallableStatement csmt;
        ResultSet rs; 
	        try
	        {
	            DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            String dt="";
	            int hr=0;
	            int min=0;
	            Date dt1=new Date();
	            dt=(dt1.getDate())+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
	            hr=dt1.getHours();
	            min=dt1.getMinutes();
	            csmt=con.prepareCall("{call insertFailureAttempt(?,?,?,?)}");
	            csmt.setString(1, userid);
	            csmt.setString(2, dt);
	            csmt.setInt(3, hr);
	            csmt.setInt(4, min);
	            int n=csmt.executeUpdate();
	        }
	        catch(Exception ex)
	        {
	            System.out.println("err="+ex.getMessage());
	            
	        }
    }
    public int insertSessionLog(String userid)
    {
    	int sessionid=0;
    	Connection con;
        CallableStatement csmt;
        ResultSet rs; 
        double authTime=0;
	        try
	        {
	        	System.out.println("uid="+userid);
	        	Vector v=getValue("select tm from temp where userid='"+userid+"'", 1);
	        	double currentMiliSec=System.currentTimeMillis();
	        	double prevMiliSec=Double.parseDouble(v.elementAt(0).toString().trim());
	        	authTime= (prevMiliSec-currentMiliSec) ;
	            DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            String dt="";
	            int hr=0;
	            int min=0;
	            Date dt1=new Date();
	            dt=(dt1.getDate())+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
	            hr=dt1.getHours();
	            min=dt1.getMinutes();
	            csmt=con.prepareCall("{call insertSessionLog(?,?,?,?)}");
	            csmt.setString(1, userid);
	            csmt.setString(2, dt);
	            csmt.setString(3, hr+":"+min);
	            csmt.setDouble(4, authTime);
	            csmt.execute();
	            rs=csmt.getResultSet();
	            if(rs.next())
	            {
	            	sessionid=rs.getInt("sessionid");
	            }
	            if(execute("delete from temp where userid='"+userid.trim()+"'")) {}
	        }
	        catch(Exception ex)
	        {
	            System.out.println("err="+ex.getMessage());
	            
	        }
	        return sessionid;
    }
    public boolean insertInputAttributes(String userid,String sessid)
    {
        try
        {
 
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            CallableStatement csmt=con.prepareCall("{call insertAttributes(?,?)}");
            csmt.setString(1, userid);
            
            csmt.setInt(2, Integer.parseInt(sessid.trim())); 
             int n=csmt.executeUpdate(); 
            if(n>0)
            {
            	  
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
    public String checkBehavior(String userid,String sessid)
    {
    	String behav="normal";
        try
        {
 
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            CallableStatement csmt=con.prepareCall("{call checkBehavior(?,?)}");
                       
            csmt.setInt(1, Integer.parseInt(sessid.trim())); 
            csmt.setString(2, userid);
            csmt.execute();
            rs=csmt.getResultSet();
                        
           while(rs.next())
           {
        	   behav=rs.getString("behavior").toString().trim();
        	   System.out.println("behavior="+behav);
            } 
        }
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
            
        }
        return behav;
    }
}
