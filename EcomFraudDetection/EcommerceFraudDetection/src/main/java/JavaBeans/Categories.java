package JavaBeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Categories {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
	private List<String> lstMainCategories;
	private List<String> lstSubCategories;
	public Categories()
	{
	
	} 
	
	
	public List<String> getLstSubCategories() {
		return lstSubCategories;
	}


	public void setLstSubCategories(List<String> lstSubCategories) {
		this.lstSubCategories = lstSubCategories;
	}


	public List<String> getLstMainCategories() {
		return lstMainCategories;
	}
	public void setLstMainCategories(List<String> lstMainCategories) {
		this.lstMainCategories = lstMainCategories;
	}
	 
	public List<String> getCategories()
    {
        try
        {
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            csmt=con.prepareCall("{call getcategories()}");
           
             csmt.execute();
             rs=csmt.getResultSet();
            lstMainCategories=new ArrayList<String>();            
            while(rs.next())
            { System.out.println("true");
            lstMainCategories.add(rs.getString("mainCategory").trim());
                  
            }
        }
           
         
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
             
        }
        return lstMainCategories;
    }
	public List<String> getSubCategories(String category)
    {
        try
        {
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            csmt=con.prepareCall("{call getSubCategories(?)}");
            csmt.setString(1, category);
             csmt.execute();
             rs=csmt.getResultSet();
            lstSubCategories=new ArrayList<String>();            
            while(rs.next())
            { System.out.println("true");
            lstSubCategories.add(rs.getString("category").trim());
                  
            }
        }
           
         
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
             
        }
        return lstSubCategories;
    }
}
