package com.bankingpro;

import java.util.*;

public class bankingmain {
	
public static void main(String[] args)throws Exception {
		
	 Scanner sc=new Scanner(System.in);
     bankingdao dao=new bankingdao();
     customer c1=new customer();
     
     System.out.println("\t\t*********Welcome to Java Bank**********");
     System.out.println("Press 1 for Registration \npress 2 for Login");
     int op=sc.nextInt();
     
     switch(op) {
     case 1->{   
    	 System.out.println("Enter Customer Id");
         int cid=sc.nextInt();
         sc.nextInt();
         System.out.println("Enter Customer Name");
         String cname=sc.nextLine();
         System.out.println("Enter Customer Pin");
         int cpin=sc.nextInt();
         System.out.println("Enter Customer Account Balance");
         int cbal=sc.nextInt();
     
         c1.cusid=cid;
         c1.cusname=cname;
         c1.cuspin=cpin;
         c1.cusamount=cbal;
       //getting connection to db  
         dao.dbconnection();
       //inserting user details to db
         int res=dao.registercustomer(c1);
       //insertion is success response is 1 otherwise 0
         if(res==1) {
        	 System.out.println("Account creation successful");
         }
         else {
        	 System.out.println("Something went wrong");
         }
     } 
     
     case 2->{
    	 System.out.println("Welcome to Login Page");
    	 //reading username and password for login
    	 System.out.println("Enter Username");
    	 sc.nextLine();
    	 String uname=sc.nextLine();
    	 System.out.println("Enter Password");
    	 int pwd=sc.nextInt();
    	 
    	 //connecting to db
    	 dao.dbconnection();
    	 
    	 int res=dao.login(uname, pwd);
    	 //handling the response
    	 if(res==0) {
    	   System.out.println("username/password are incorrect");
    	 }
    	   else if(res==-1) {
    	   System.out.println("Unable to find the details");
    	    }
    	   else {
    		 System.out.println("Login Successful");
    		 System.out.println("Press 1 for Deposit\nPress 2 for Withdraw\nPress 3 for change password\nPress 4 for Delete Acc");
				int ops=sc.nextInt();
    	 
			switch(ops) {
			case 1->{
				System.out.println("Enter ");
			      {
				int amount=sc.nextInt();
				int bal=dao.deposit(amount,res);	
				System.out.println("Depsoit successful\n Available Amount is :"+bal);
				}
			}
		   case 2->{
			 System.out.println("Enter amount to withdraw");
		     int amount=sc.nextInt();
		     System.out.println("Conform Your password");
		     int confmpwd=sc.nextInt();
		     int availamount=dao.withdraw(amount, confmpwd, res);
		     if(availamount==-1) {
		       System.out.println("Low Balance");
		      }
		    else if(availamount==0) {
		    	System.out.println("Incorrect password");
		    }
		    else {
		    	System.out.println("Withdraw successful \n Available Amount is :"+availamount);
		    	}
		    }
		case 3->{
			System.out.println("Enter current password");
			int currentpwd=sc.nextInt();
			System.out.println("Enter new password");
			int newpwd=sc.nextInt();
			int status=dao.changepwd(currentpwd, newpwd, res);
			if(status==1) {
				System.out.println("Password charged...");
			}
			else {
				System.out.println("Something went Wrong");
			}
		}
		case 4->{
			System.out.println("Enter password to delete");
			int pass=sc.nextInt();
			int status=dao.deleteAccount(pass, res);
			if(status==1) {
				System.out.println("Your account is deleted\n Good Bye!....");
			}
			else {
				System.out.println("Something went Wrong");
			}
		    }
		   }
    	 }
    	 }
		    
	  }
      sc.close();  
      
} 
}       
 
           

	
     