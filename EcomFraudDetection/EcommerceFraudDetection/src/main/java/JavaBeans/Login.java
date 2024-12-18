package JavaBeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;
public class Login {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private String userid,uType,userName;
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	public CallableStatement getCsmt() {
		return csmt;
	}
	public void setCsmt(CallableStatement csmt) {
		this.csmt = csmt;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getuType() {
		return uType;
	}
	public void setuType(String uType) {
		this.uType = uType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	 public boolean chkAuthentication(String userid,String pass)
	    {
	        try
	        {
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call userlogin(?,?)}");
	            csmt.setString(1, userid);
	            csmt.setString(2, pass);
	             csmt.execute();
	             rs=csmt.getResultSet();
	                        
	            boolean auth=false;
	            while(rs.next())
	            { System.out.println("true");
	                auth=true;
	                
	                uType=rs.getString("utype");
	                userName=rs.getString("username");
	                
	                 this.userid=rs.getString("userid");
	            }
	            if(auth)
	            {
	                try{con.close();}catch(Exception ex){}
	                System.out.println("true");
	                return true;
	            }
	            else
	            {
	            	JavaFuns jf=new JavaFuns();
	            	 jf.threatLog(userid);
	            	 Date d=new Date();
	            	 String dt=(d.getDate())+"/"+(d.getMonth()+1)+"/"+(d.getYear()+1900);
	            	 Vector v=jf.getValue("select count(*) from threatLog where userid='"+userid.trim()+"' and dt='"+dt+"'", 1);
	            	 if(v.size()>0)
	            	 {
	            	 if(Integer.parseInt(v.elementAt(0).toString().trim())>3)
	            	 {
	            		 if(jf.execute("update users set loginsts='deactive' where userid='"+userid.trim()+"'")) {}
	            	 }
	            	 }
	                return false;
	            }

	            }
	           
	         
	        catch(Exception ex)
	        {
	            System.out.println("err="+ex.getMessage());
	            return false;
	        }
	    }

}
