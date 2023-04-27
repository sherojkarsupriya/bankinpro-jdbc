package com.bankingpro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AppTest{
	
	bankingdao b1=new bankingdao();
	
	@Test
	//positive test case
	public void testlogin() throws Exception {
		
		b1.dbconnection();
		int exp=101;
	    int res=b1.login("Mansi", 5052);
		
		assertEquals(exp,res);
	}
	
//	@Test
//	//negative test case
//	public void testlogin2() throws Exception {
		
//	b1.dbconnection();
//		int exp=101;
//		int res=b1.login("Mans", 5052);//res is actual value from login	
//		assertEquals(exp,res);	
//	}	
	
//	@Test
//   //negative with wrong password
//	public void testlogin3() throws Exception {
		
//		b1.dbconnection();
//		int exp=101;
//	    int res=b1.login("Mansi", 5054);//res is actual value from login	
//		assertEquals(exp,res);
		
//	}
	
//	@Test
//	//customer deposite
//	public void testdeposit()throws Exception{
		
//		b1.dbconnection();
//		int exp=79000;
//		int availamount=b1.deposit(1000, 101);
//		assertEquals(exp,availamount);
//	}
	
//	@Test
//	//positive withdraw test case
//	public void testwithdraw() throws Exception {
		
//		b1.dbconnection();
//		int exp=78000;
//		int amt=b1.withdraw(1000,5052, 101);
//		assertEquals(exp,amt);	
//	}
	
//	@Test
//	//negative withdraw test case
//	public void testwithdraw1() throws Exception {
		
//		b1.dbconnection();
//		int exp=-1;
//		int amt=b1.withdraw(78000,5052,101);
//		assertEquals(exp,amt);
//	}
	
    @Test
    //change password of customer login
    public void testpwd() throws Exception {
    	
    	b1.dbconnection();
    	int exp=1;
    	int res=b1.changepwd(5052,5151,101);
    	assertEquals(exp,res);
    }
    
    @Test
    //delete account
    public void testdel() throws Exception {
    
    b1.dbconnection();
    int exp=1;
    int act=b1.deleteAccount(5151,101);
    assertEquals(exp,act);
    }
    
    @Test
    // new register for customer
    public void testregistercustomer() throws Exception {
    	
    	customer c1=new customer();
    	c1.cusid=101;
    	c1.cusname="Mansi";
    	c1.cuspin=5052;
    	c1.cusamount=60000;
    	
    	int exp=1;
    	b1.dbconnection();
    	int act=b1.registercustomer(c1);
    	assertEquals(exp,act);
    }
}