package JavaBeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 

 

public class Orders {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private String title,size,category,subcategory,page,dt,userid;
    private int orderno;
    public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	private int searchCount,prodId,quantity,cartid;
    private double price,totalprice,totalShopPrice;
   private List<Orders> lstorders = new ArrayList<Orders>();
    
	  
public String getSize() {
	return size;
}
public void setSize(String size) {
	this.size = size;
}
public int getOrderno() {
	return orderno;
}
public void setOrderno(int orderno) {
	this.orderno = orderno;
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
 
public List<Orders> getLstorders() {
	return lstorders;
}
public void setLstorders(List<Orders> lstorders) {
	this.lstorders = lstorders;
}
public Orders()
{
	
}
public Orders(ResultSet rs,String details) 
{
	try
	{
	title=rs.getString("productname").toString().trim();
	quantity=rs.getInt("quantity");
	orderno=rs.getInt("orderno");
	size=rs.getString("size");
	price=rs.getDouble("price");
	totalprice=rs.getDouble("total");
	System.out.println("title="+title);
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
public Orders(ResultSet rs) 
{
	try
	{
	title=rs.getString("prodTitle").toString().trim();
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
public void getOrderDetails(int orderno1)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getOrderDetails(?)}");
        csmt.setInt(1, orderno1);
         csmt.execute();
         rs=csmt.getResultSet();
        lstorders=new ArrayList<Orders>();            
        while(rs.next())
        { System.out.println("true");
        lstorders.add(new Orders(rs,"details"));
              
        }
    } 
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}  
public void getOrders(String sts)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getPendingOrders(?,?)}");
       csmt.setString(1,userid);
       csmt.setString(2,sts);
         csmt.execute();
         rs=csmt.getResultSet();
        lstorders=new ArrayList<Orders>();            
        while(rs.next())
        { System.out.println("true");
        lstorders.add(new Orders(rs));
              
        }
    } 
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
	 
	public boolean placeOrder()
    {
        try
        { 
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            Date dt1=new Date();
            dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
            csmt=con.prepareCall("{call insertOrders(?,?)}");
            csmt.setString(1, userid);
            csmt.setString(2, dt);
            csmt.execute();
            rs=csmt.getResultSet();
            int n=0;        
           while(rs.next())
           { System.out.println("true");
           	orderno=rs.getInt("orderno");
           	n++;
           }
                        
            
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
	public boolean cancelOrder()
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
	 
}
