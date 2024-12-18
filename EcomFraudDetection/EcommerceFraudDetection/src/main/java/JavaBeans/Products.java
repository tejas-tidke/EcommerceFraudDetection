package JavaBeans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.web.multipart.MultipartFile;

 

public class Products {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private String title,categories,brand,proddesc,culture,imgUrl,asinNo,category,subcategory;
    private int searchCount,prodId;
    private double price;
   private List<Products> lstproducts = new ArrayList<Products>();
   private MultipartFile file;
   
   
	 
	public String getCulture() {
	return culture;
}
public void setCulture(String culture) {
	this.culture = culture;
}
	public MultipartFile getFile() {
	return file;
}
public void setFile(MultipartFile file) {
	this.file = file;
}
	public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
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
	public int getProdId() {
	return prodId;
}
public void setProdId(int prodId) {
	this.prodId = prodId;
}
	public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getCategories() {
	return categories;
}
public void setCategories(String categories) {
	this.categories = categories;
}
public String getProddesc() {
	return proddesc;
}
public void setProddesc(String proddesc) {
	this.proddesc = proddesc;
}
public String getImgUrl() {
	return imgUrl;
}
public void setImgUrl(String imgUrl) {
	this.imgUrl = imgUrl;
}
public String getAsinNo() {
	return asinNo;
}
public void setAsinNo(String asinNo) {
	this.asinNo = asinNo;
}
public int getSearchCount() {
	return searchCount;
}
public void setSearchCount(int searchCount) {
	this.searchCount = searchCount;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public List<Products> getLstproducts() {
	return lstproducts;
}
public void setLstproducts(List<Products> lstproducts) {
	this.lstproducts = lstproducts;
}
public Products()
{
	
}
public Products(ResultSet rs) 
{
	try
	{
	title=rs.getString("title").toString().trim();
	categories=rs.getString("categories").toString().trim();
	brand=rs.getString("brand").toString().trim();
	proddesc=rs.getString("pdescription").toString().trim();
	price=rs.getDouble("price");
	imgUrl=rs.getString("imgUrl").toString().trim();
	asinNo=rs.getString("asinNo").toString().trim();
	searchCount=rs.getInt("searchCount");
	prodId=rs.getInt("id");
	System.out.println("title="+title);
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
	public void getProducts()
	    {
	        try
	        {
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call getProducts()}");
	           
	             csmt.execute();
	             rs=csmt.getResultSet();
	            lstproducts=new ArrayList<Products>();            
	            while(rs.next())
	            { System.out.println("true");
	            lstproducts.add(new Products(rs));
	                  
	            }
	        }
	           
	         
	        catch(Exception ex)
	        {
	            System.out.println("err="+ex.getMessage());
	             
	        }
	    }
	public void getProductsSearch(String txt)
    {
        try
        {
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            JavaFuns jf=new JavaFuns();
         //   Vector v=jf.getValue("select case when gender='male' then 'Men' else 'Women' end as gender from userdetails where userid='"+uid+"'", 1);
            String qr="select * from prodView where categories like '%"+txt+"%' or title like '%"+txt+"%'  order by (positive-negative) desc";
          System.out.println("qr="+qr);
            PreparedStatement  csmt=con.prepareStatement(qr);
          // csmt.setString(1, uid);
             csmt.execute();
             rs=csmt.getResultSet();
            lstproducts=new ArrayList<Products>();            
            while(rs.next())
            { System.out.println("true");
            lstproducts.add(new Products(rs));
                  
            }
        }
           
         
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
             
        }
    }
	public void getProductsPref(String uid)
    {
        try
        {
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            JavaFuns jf=new JavaFuns();
         //   Vector v=jf.getValue("select case when gender='male' then 'Men' else 'Women' end as gender from userdetails where userid='"+uid+"'", 1);
            String qr="select * from prodView where categories in (select categories from products where title in (select prodnm from searchtrack where userid='"+uid+"') ) or categories in (select categories from  preferredcategories where userid='"+uid+"' ) order by (positive-negative) desc";
          System.out.println("qr="+qr);
            PreparedStatement  csmt=con.prepareStatement(qr);
          // csmt.setString(1, uid);
             csmt.execute();
             rs=csmt.getResultSet();
            lstproducts=new ArrayList<Products>();            
            while(rs.next())
            { System.out.println("true");
            lstproducts.add(new Products(rs));
                  
            }
        }
           
         
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
             
        }
    }
	public void getProductsPref1(String uid)
    {
        try
        {
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            JavaFuns jf=new JavaFuns();
         //   Vector v=jf.getValue("select case when gender='male' then 'Men' else 'Women' end as gender from userdetails where userid='"+uid+"'", 1);
            String qr1="(select userid from userdetails where userid<>'"+uid+"' and  abs(datediff(dob,(select distinct(dob) from userdetails where userid='"+uid+"'))/365) between 0 and 5)";
            String qr="select * from prodView where categories in (select categories from products where title in (select prodnm from searchtrack where userid in "+qr1+") ) or categories in (select categories from  preferredcategories where userid in "+qr1+") order by (positive-negative) desc";
          System.out.println("qr="+qr);
            PreparedStatement  csmt=con.prepareStatement(qr);
          // csmt.setString(1, uid);
             csmt.execute();
             rs=csmt.getResultSet();
            lstproducts=new ArrayList<Products>();            
            while(rs.next())
            { System.out.println("true");
            lstproducts.add(new Products(rs));
                  
            }
        }
           
         
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
             
        }
    }
	public void getProducts1(String uid)
    {
        try
        {
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            JavaFuns jf=new JavaFuns();
         //   Vector v=jf.getValue("select * from prodview where categories like '%/Men/%'", 1);
            String qr="select * from prodview where categories like '%/Men/%'";
          System.out.println("qr="+qr);
            PreparedStatement  csmt=con.prepareStatement(qr);
          // csmt.setString(1, uid);
             csmt.execute();
             rs=csmt.getResultSet();
            lstproducts=new ArrayList<Products>();            
            while(rs.next())
            { System.out.println("true");
            lstproducts.add(new Products(rs));
                  
            }
        }
           
         
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
             
        }
    }

	public void getSimilarProducts(String prodname1,String category1,String brand1)
    {
        try
        {
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            csmt=con.prepareCall("{call getSimilarProducts(?,?,?)}");
            csmt.setString(1, prodname1.trim());
            csmt.setString(2, category1.trim());
            csmt.setString(3, brand1.trim());
             csmt.execute();
             rs=csmt.getResultSet();
            lstproducts=new ArrayList<Products>();            
            while(rs.next())
            { System.out.println("true");
            lstproducts.add(new Products(rs));
                  
            }
        }
           
         
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
             
        }
    }
	    public void getProductsAssProd(String prodname)
	    {
	        try
	        {
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call getAssociatedProducts(?)}");
	            csmt.setString(1, prodname.trim());
	             csmt.execute();
	             rs=csmt.getResultSet();
	            lstproducts=new ArrayList<Products>();            
	            while(rs.next())
	            { System.out.println("true");
	            lstproducts.add(new Products(rs));
	                  
	            }
	        }
	           
	         
	        catch(Exception ex)
	        {
	            System.out.println("err="+ex.getMessage());
	             
	        }
	    }
	public void getProductsPurchaseHist(String uid)
    {
        try
        {
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            csmt=con.prepareCall("{call getPurchaseHistoryWiseProducts(?)}");
            csmt.setString(1, uid.trim());
             csmt.execute();
             rs=csmt.getResultSet();
            lstproducts=new ArrayList<Products>();            
            while(rs.next())
            { System.out.println("true");
            lstproducts.add(new Products(rs));
                  
            }
        }
           
         
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
             
        }
    }
	public void getCategoryWiseProducts()
    {
        try
        {
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            csmt=con.prepareCall("{call getCateWiseProducts(?)}");
           csmt.setString(1, category+"/"+subcategory);
           System.out.println("ccc="+category+"/"+subcategory);
             csmt.execute();
             rs=csmt.getResultSet();
            lstproducts=new ArrayList<Products>();            
            while(rs.next())
            { System.out.println("true");
            lstproducts.add(new Products(rs));
                  
            }
        }
           
         
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
             
        }
    }
	public void getProduct()
    {
        try
        {
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            csmt=con.prepareCall("{call getProduct(?)}");
           csmt.setInt(1, prodId);
             csmt.execute();
             rs=csmt.getResultSet();
            lstproducts=new ArrayList<Products>();            
            while(rs.next())
            { System.out.println("true");
            lstproducts.add(new Products(rs));
                  
            }
        }
           
         
        catch(Exception ex)
        {
            System.out.println("err="+ex.getMessage());
             
        }
    }
}
