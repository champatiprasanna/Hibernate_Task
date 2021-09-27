package com.hibernate.Login.Dao;


import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.Login.pojo.LoginPojo;

public class LogInDao {
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		Configuration cref = new Configuration();
		cref.configure("hibernate.cfg.xml");
		SessionFactory sfref = cref.buildSessionFactory();
		Session sref = sfref.openSession();
		Transaction tref = sref.beginTransaction();
		Scanner scobj = new Scanner(System.in);
		System.out.println("1. Register" +"\n" + "2. Login" + "\n" + "3. Admin");
		System.out.println("Enter your choice");
		int uchoice = scobj.nextInt();
		
		if(uchoice ==1) {
			System.out.println("Welcome to registration page");
			System.out.println("Enter your id, username, email, password, mobile, gender");
			int id = scobj.nextInt();
			String username = scobj.next();
			String email = scobj.next();
			String password = scobj.next();
			int mobile = scobj.nextInt();
			String gender = scobj.next();
			LoginPojo lobj = new LoginPojo();
			lobj.setId(id);
			lobj.setUsername(username);
			lobj.setEmail(email);
			lobj.setPassword(password);
			lobj.setMobile(mobile);
			lobj.setGender(gender);
			sref.save(lobj);
			System.out.println("values inserted!");
		}
		
		else if(uchoice ==2){
			System.out.println("Enter your username/email and password");
			String emailUser = scobj.next();
			String pass = scobj.next();
			Query qref = sref.createQuery("from LoginPojo where email = :eref or username = :uref and password = :pref");
			qref.setParameter("eref", emailUser);
			qref.setParameter("uref", emailUser);
			qref.setParameter("pref", pass);
			System.out.println("Login success!");
			
			System.out.println("update mobile number");
			System.out.println("Enter mobile number to update");
			int mobile = scobj.nextInt();
			Query uqref = sref.createQuery("update from LoginPojo set mobile = :mref where email = :ref1 or username = :ref2");
			uqref.setParameter("mref", mobile);
		    uqref.setParameter("ref1",emailUser);	
		    uqref.setParameter("ref2", emailUser);
			uqref.executeUpdate();
			System.out.println("Mobile num updated");
				
			
		}		
		else if(uchoice == 3) {
			System.out.println("Enter password");
			String password = scobj.next();
			if(password.equals("1234")) {
				System.out.println("1)all user details" + "\n" + "2)user’s login credential – username, email, password" + "\n" +"3)Total number of users" + "\n"+ "4)delete particular user");
			    System.out.println("Enter your choice of operation");
			    int ochoice = scobj.nextInt();
			    if(ochoice ==1){
					Query aqref = sref.createQuery("from LoginPojo");
					List alref = aqref.list();
					Iterator aitref = alref.iterator();
					while(aitref.hasNext()){
						LoginPojo aaref = (LoginPojo)aitref.next();
						System.out.println(aaref.getId() + " " + aaref.getUsername() + " " + aaref.getEmail()+" "+aaref.getPassword() + " " + aaref.getMobile() + " " + aaref.getGender());   
				}
			    }
					
				if(ochoice ==2) {
					Query sqref = sref.createQuery("select username, email, password from LoginPojo");
					List lsref = sqref.list();
					Iterator itsref = lsref.iterator();
					while(itsref.hasNext()) {
						Object o[] = (Object[])itsref.next();
						System.out.println(o[0] + " " + o[1] + " " + o[2]);
						
					}
					
				}
			    
			   if(ochoice == 3) {
				   Query cqref = sref.createQuery("select count(username) from LoginPojo");
					List clref =cqref.list();
					System.out.println("The count of users:" + "" + clref.get(0) );
			   }
			   
			   if(ochoice == 4) {
				   System.out.println("Enter id to delete");
				   int id = scobj.nextInt();
				   Query dqref = sref.createQuery("delete from LoginPojo where id =:idref");
				   dqref.setParameter("idref",id);
				   dqref.executeUpdate();
				   System.out.println("Values deleted!");
			   }
	
			 }
			}
		else {
			System.out.println("Invalid choice!");
		}
			
		tref.commit();
		sref.close();
		sfref.close();
		
		
	}}
	



/*/*  -- AutoTable Pojo - HCQl
AutoTablePojo at = new AutoTablePojo();
at.setId(5);
at.setName("rose");
at.setEmail("rose@123");
at.setPassword("123");
at.setAge(36);

System.out.println("Enter the state: true/false ");
Scanner scobj = new Scanner(System.in);
boolean bn = scobj.nextBoolean();
at.setActive(bn);
at.setMobile(6789012);

System.out.println("Values inserted");
sref.save(at);

*/
