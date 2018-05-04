import java.sql.*;

public class DBConnect {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnect(){
	try{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rms","root","");
		st=con.createStatement();
		
		
		
	}catch(Exception ex)
	{
		System.out.println("Error :1 "+ex);
	}
	}
	public String Login(String username,String password){
			System.out.println(username);
			String query = "select * from users where userName='"+username+"' and password='"+password+"'";
			try {
				rs = st.executeQuery(query);
				
				
					rs.next();
					System.out.println(rs.getString("userType"));
					return rs.getString("userType");
				
				
				
			} catch (SQLException e) {
				return "";
				}
			
			
	}
	public String UserCheck(String username){
		System.out.println(username);
		String query = "select * from users where userName='"+username+"'";
		try {
			rs = st.executeQuery(query);
			
			
				rs.next();
				return rs.getString("userType");
			
			
			
		} catch (SQLException e) {
			return "";
			}
		
		
}
	public void AddStaff(String username,String password,String fullname){
	
		String query = "insert into users(userName,password,fullName,userType) values('"+username+"','"+password+"','"+fullname+"','Staff')";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("error : "+e);
		}
		
		
	}
	
	public String ItemCheck(String name){
		String query = "select * from items where itemName='"+name+"'";
		try {
			rs = st.executeQuery(query);
			
			
				rs.next();
				return rs.getString("exists!");
			
			
			
		} catch (SQLException e) {
			return "";
			}
		
		
}
	public void AddItem(String name,int price){
	
		String query = "insert into items(itemName,price) values('"+name+"','"+price+"')";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("error : "+e);
		}
		
		
	}
	public void UpdateItem(String name,int price){
		
		String query = "update items set  price='"+price+"' where itemName='"+name+"'";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("error : "+e);
		}
		
		
	}
	public int SearchItem(String name){
		
		String query = "select * from items where itemName='"+name+"'";
		try {
			rs=st.executeQuery(query);

			rs.next();
			return rs.getInt("price");
		} catch (SQLException e) {
			System.out.println("error : "+e);
			return 0;
		}
	}
	
public void DeleteItem(String name){
		
		String query = "DELETE FROM items WHERE itemName='"+name+"'";
		try {
			st.executeUpdate(query);

			} catch (SQLException e) {
			System.out.println("error : "+e);
			}
	}

public void DeleteStaff(String name){
	
	String query = "DELETE FROM users WHERE userName='"+name+"'";
	try {
		st.executeUpdate(query);

		} catch (SQLException e) {
		System.out.println("error : "+e);
		}
}

public void DeleteOrder(int orderid){
	
	String query = "DELETE FROM orders WHERE orderId='"+orderid+"'";
	String query2= "DELETE FROM ordertotal WHERE orderId='"+orderid+"'";
	try {
		st.executeUpdate(query);
		st.executeUpdate(query2);

		} catch (SQLException e) {
		System.out.println("error : "+e);
		}
}

public void UpdateStaff(String username,String fullname){
	System.out.println(username);
	System.out.println(fullname);
	String query = "update users set  fullName='"+fullname+"' where userName='"+username+"'";
	try {
		st.executeUpdate(query);
	} catch (SQLException e) {
		System.out.println("error : "+e);
	}
	
	
}

public String SearchStaff(String name){
	
	String query = "select * from users where userName='"+name+"'";
	try {
		rs=st.executeQuery(query);

		rs.next();
		return rs.getString("fullName");
	} catch (SQLException e) {
		System.out.println("error : "+e);
		return "";
	}
}
public ResultSet GetItems(){
	
	String query = "select * from items";
	try {
		rs=st.executeQuery(query);

		return rs;
	} catch (SQLException e) {
		System.out.println("error : "+e);
		return null;
	}
}

public ResultSet GetOrders(String username){
	
	String query = "select * from ordertotal where StaffUserName='"+username+"'";
	try {
		rs=st.executeQuery(query);

		return rs;
	} catch (SQLException e) {
		System.out.println("error : "+e);
		return null;
	}
}

public ResultSet GetSubOrder(int orderid){
	
	String query = "select * from orders where orderId='"+orderid+"'";
	try {
		rs=st.executeQuery(query);

		return rs;
	} catch (SQLException e) {
		System.out.println("error : "+e);
		return null;
	}
}

public void AddSubOrder(String name,int quantity,int price){
	System.out.println("error24 : "+name+" "+quantity+" "+price);
	String query2= "select max(orderId) as ID from ordertotal";
	
	try {
		rs=st.executeQuery(query2);
		rs.next();
		
		String query = "insert into Orders(orderId,itemName,price,quantity) values('"+rs.getString("ID")+"','"+name+"','"+price+"','"+quantity+"')";
		st.executeUpdate(query);
	} catch (SQLException e) {
		System.out.println("error : "+e);
	}
	
	
}
public void GenOrder(int total,String username){

	String query = "insert into ordertotal(totalPrice,StaffUserName) values('"+total+"','"+username+"')";
	
	try {
		st.executeUpdate(query);
	} catch (SQLException e) {
		System.out.println("error : "+e);
	}
	
	
}
	
}