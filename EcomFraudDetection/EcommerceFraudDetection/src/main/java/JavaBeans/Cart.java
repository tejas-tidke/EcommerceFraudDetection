package JavaBeans;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

 

public class Cart {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private String title,category,size,subcategory,page,dt,userid;
    public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	private int searchCount,prodId,quantity,cartid;
    private double price,totalprice,totalShopPrice;
   private List<Cart> lstcart = new ArrayList<Cart>();
  
	  
public String getSize() {
	return size;
}
public void setSize(String size) {
	this.size = size;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getCartid() {
	return cartid;
}
public void setCartid(int cartid) {
	this.cartid = cartid;
}
public double getTotalprice() {
	return totalprice;
}
public void setTotalprice(double totalprice) {
	this.totalprice = totalprice;
}
public double getTotalShopPrice() {
	return totalShopPrice;
}
public void setTotalShopPrice(double totalShopPrice) {
	this.totalShopPrice = totalShopPrice;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getSubcategory() {
	return subcategory;
}
public void setSubcategory(String subcategory) {
	this.subcategory = subcategory;
}
public String getPage() {
	return page;
}
public void setPage(String page) {
	this.page = page;
}
public int getSearchCount() {
	return searchCount;
}
public void setSearchCount(int searchCount) {
	this.searchCount = searchCount;
}
public int getProdId() {
	return prodId;
}
public void setProdId(int prodId) {
	this.prodId = prodId;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
} 

public List<Cart> getLstcart() {
	return lstcart;
}
public void setLstcart(List<Cart> lstcart) {
	this.lstcart = lstcart;
}
public Cart()
{
	
}
public Cart(ResultSet rs) 
{
	try
	{
	title=rs.getString("prodTitle").toString().trim();
	size=rs.getString("size").toString().trim();
	category=rs.getString("category").toString().trim();
	subcategory=rs.getString("subcategory").toString().trim();
	dt=rs.getString("dt").toString().trim();
	price=rs.getDouble("price");
	totalprice=rs.getDouble("totalprice");
	prodId=rs.getInt("prodid");
	quantity=rs.getInt("quantity");
	cartid=rs.getInt("cartId");
	System.out.println("title="+title);
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
	public void getCartDetails()
	    {
	        try
	        {
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call getCartDetails(?)}");
	           csmt.setString(1,userid);
	             csmt.execute();
	             rs=csmt.getResultSet();
	            lstcart=new ArrayList<Cart>();            
	            while(rs.next())
	            { System.out.println("true");
	            lstcart.add(new Cart(rs));
	                  
	            }
	        } 
	        catch(Exception ex)
	        {
	            System.out.println("err="+ex.getMessage());
	             
	        }
	    }
	public boolean checkProductInCartORNOT(int prodid1,String userid1)
    {
		boolean flag=false;
        try
        {
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            csmt=con.prepareCall("{call checkProductInCart(?,?)}");
           csmt.setInt(1, prodid1);
           csmt.setString(2, userid1);
             csmt.execute();
             rs=csmt.getResultSet();
            lstcart=new ArrayList<Cart>();            
            while(rs.next())
            { System.out.println("exist");
            flag=true;
                  
            }
        } 
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
             
        }
        return flag;
    }
	public int totalItemsInCart(String userid1)
    {
		int toalItems=0;
        try
        {
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            csmt=con.prepareCall("{call getTotalItemsInCart(?)}");
            csmt.setString(1, userid1);
             csmt.execute();
             rs=csmt.getResultSet();
            lstcart=new ArrayList<Cart>();            
            while(rs.next())
            { System.out.println("items");
             
            toalItems=rs.getInt("items");
            }
        } 
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
             
        }
        return toalItems;
    }
	public boolean addToCart()
    {
        try
        {
        	String bodycond="NA";
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            Date dt1=new Date();
            dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
            csmt=con.prepareCall("{call insertCart(?,?,?,?,?,?,?,?,?,?)}");
            csmt.setString(1, userid);
            csmt.setString(2, title);
            csmt.setInt(3, prodId);
            quantity=1;
            csmt.setInt(4, quantity);
            csmt.setDouble(5, price);
         
            csmt.setDouble(6, price);
            csmt.setString(7, dt);
            csmt.setString(8, category); 
            csmt.setString(9, subcategory); 
            csmt.setString(10, size); 
            
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
	public boolean removeItems()
    {
        try
        { 
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            Date dt1=new Date();
            dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
            csmt=con.prepareCall("{call removeItemFromCart(?)}");
            csmt.setInt(1, cartid); 
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
	public boolean updateItems()
    {
        try
        { 
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            Date dt1=new Date();
            dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
            csmt=con.prepareCall("{call updateCartItem(?,?)}");
            csmt.setInt(1, cartid); 
            csmt.setInt(2, quantity);  
        
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
}
