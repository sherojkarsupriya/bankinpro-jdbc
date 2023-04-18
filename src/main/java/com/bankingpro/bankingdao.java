package com.bankingpro;

// controller which is communicating with db

import java.sql.*;
public class bankingdao {
    
	
	Connection con=null;
	public void dbconnection() throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingpro","root","123973");
		
	}
    //method to register customer to db
	public int registercustomer(customer c1) throws Exception {
		
	 String query="insert into customer values(?,?,?,?)"; 
	 PreparedStatement pst=con.prepareStatement(query);
	 pst.setInt(1,c1.cusid);
	 pst.setString(2, c1.cusname);
     pst.setInt(3, c1.cuspin);
     pst.setInt(4, c1.cusamount); 
     int res=pst.executeUpdate();
	 return res;
	}
	public int login(String uname,int pwd) throws Exception {
		String query="select * from customer where cusname= '"+uname+"'";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		if(rs.next()) {
			int password=rs.getInt(3);
			if(password==pwd) {
				return rs.getInt(1);
			}
			else {
				return 0;
			}
			}
		else {
			return -1;
         	}
		}
		public int deposit(int amount,int customerid) throws Exception {
			//fetching user details based on customer id
			String 	query2="select  * from customer where cusid="+customerid;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query2);
			rs.next();
			//extracting account balance 
			int bal=rs.getInt(4);
			//updating amount
			amount+=bal;
         	//storing the updated amount
			String query="update customer set cusamount ="+amount+" where cusid="+customerid;
			PreparedStatement pst=con.prepareStatement(query);
			pst.executeUpdate();
			//returning updated amount
			return amount;
		}           
		
		public int withdraw(int amount,int pwd,int cusid) throws Exception {
			//fetching user details
			String query2="select * from customer where cusid="+cusid;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query2);
			rs.next();
			//extracting available amount
			int availamount=rs.getInt(4);
			if(pwd==rs.getInt(3))
			{
				//if available amount is > required amount then only withdraw
                if(amount<availamount) {
                	availamount-=amount;
                	String query="update customer set cusamount="+availamount+" where cusid="+cusid;
                	PreparedStatement pst=con.prepareStatement(query);
                	pst.executeUpdate();
                	return availamount;
                  }
                else {
                	return -1;
                     }
			        }
			    else {
				return 0;
			}
			}
		
		public int changepwd(int currentpwd,int newpwd, int cusid) throws Exception {
			//fetching the user details
			String query2="select * from customer where cusid="+cusid;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query2);
			rs.next();
			//conforming existing password
			if(currentpwd==rs.getInt(3)) {
				//update new pwd in db
				String query="update customer set cuspin="+newpwd+" where cusid="+cusid;
				PreparedStatement pst=con.prepareStatement(query);
				pst.executeUpdate();
				return 1;
			}
			else {
				return 0;
			}
		}

	public int deleteAccount(int pwd,int cusid) throws Exception {
		 
		String query2="select * from customer where cusid="+cusid;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query2);
		rs.next();
		//conforming pwd
		if(pwd==rs.getInt(3)) {
			//delete the account
			String query="delete from customer where cusid="+cusid;
			PreparedStatement pst=con.prepareStatement(query);
			pst.executeUpdate();
			return 1;
		}
		else {
			return 0;
		}
	}
}
         
 
           

	
     
		
	

