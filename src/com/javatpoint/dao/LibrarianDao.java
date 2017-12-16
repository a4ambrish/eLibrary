package com.javatpoint.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javatpoint.beans.LibrarianBean;



public class LibrarianDao {

	public static void save(LibrarianBean bean){
		int status=0;
		Session session = DB.sf.openSession();
		Transaction transaction = null;
		try
		{
	//Hibernate session code this is updated one for git.
			transaction = session.beginTransaction();
			session.save(bean);
			transaction.commit();
			System.out.println("Records inserted sucessessfully");
		}
		catch (HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally 
		{
			session.close();
		}
	//JDBC connection code
			/*Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("insert into e_librarian(name,email,password,mobile) values(?,?,?,?)");
			ps.setString(1,bean.getName());
			ps.setString(2,bean.getEmail());
			ps.setString(3,bean.getPassword());
			ps.setString(4,bean.getMobile());
			status=ps.executeUpdate();
			con.close();}
		
		catch(Exception e){System.out.println(e);}
		
		return status;*/
	}
	public static void update(LibrarianBean bean){
		
		Session session = DB.sf.openSession();
		Transaction transaction = null;
		LibrarianBean dbbean=null;
		
		try 
		{
		transaction = session.beginTransaction();
		String queryString = "from LibrarianBean where id = :id";
		Query query = session.createQuery(queryString);
		query.setInteger("id", bean.getId());
		dbbean = (LibrarianBean) query.uniqueResult(); 
		// managaed object for record with id 1 into database
		dbbean.setName(bean.getName());
		dbbean.setEmail(bean.getEmail());
		dbbean.setMobile(bean.getMobile());
		dbbean.setPassword(bean.getPassword());
		//session.update(bean);
		//session.evict(employee); // dettached object
       //Employee1 employee2 = (Employee1) query.uniqueResult();
		System.out.println("-----------------");
		//employee2.setSalary(employee.getSalary()*3);
		//session.update(employee2);
		//session.evict(employee2); 
		//session.merge(employee);  // trying to reattach   object which is already available in the JVM as employee2
		//session.flush();
		transaction.commit();
		System.out.println("Employee records updated!");
		} 
		catch (HibernateException e)
		{
		transaction.rollback();
		e.printStackTrace();
		} 
		finally
		{
		session.close();
		}

		/*int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("update e_librarian set name=?,email=?,password=?,mobile=? where id=?");
			ps.setString(1,bean.getName());
			ps.setString(2,bean.getEmail());
			ps.setString(3,bean.getPassword());
			ps.setString(4,bean.getMobile());
			ps.setInt(5,bean.getId());
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;*/
	}
	
	public static List<LibrarianBean> view()
	
	{
		List<LibrarianBean> list=new ArrayList<LibrarianBean>();
		Session session = DB.sf.openSession();
		Transaction transaction = null;
		try {
		transaction = session.beginTransaction();
		List librarian =session.createQuery("from LibrarianBean").list();
		
	for (Iterator iterator = librarian.iterator(); iterator.hasNext();)
		{
		LibrarianBean bean = (LibrarianBean) iterator.next();
		System.out.println("Retrieving Librarian details: ");
		System.out.println(bean.getName() + "  "
		+ bean.getEmail() + "  " + bean.getMobile()
		+ "   " + bean.getMobile());
		list.add(bean);
		}
		transaction.commit();
		} catch (HibernateException e)
		{
		transaction.rollback();
		e.printStackTrace();
		} 
		finally 
		{
		session.close();
		}

		return list;
		
		
		/*try
		 {
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from e_librarian");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				LibrarianBean bean=new LibrarianBean();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setMobile(rs.getString("mobile"));
				list.add(bean);
		}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;*/
	}
	public static LibrarianBean viewById(int id){
		
		Session session = DB.sf.openSession();
		Transaction transaction = null;
		LibrarianBean bean = new LibrarianBean();
	try{
		transaction = session.beginTransaction();
		
		bean=(LibrarianBean)session.get(LibrarianBean.class,id);

		System.out.println(bean.getName() + "  "
				+ bean.getEmail() + "  " + bean.getMobile()
				+ "   " + bean.getMobile());
	transaction.commit();

		}
	catch(HibernateException e) 
	{
	transaction.rollback();
	e.printStackTrace();
	} 
	
	finally {
	session.close();
	}
	return bean;

		
		
		/*LibrarianBean bean=new LibrarianBean();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from e_librarian where id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setPassword(rs.getString(3));
				bean.setEmail(rs.getString(4));
				bean.setMobile(rs.getString(5));
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return bean;*/
	}
	public static int delete(int id)
	{
		
		Session session = DB.sf.openSession();
		Transaction transaction = null;
		int status=0;
		try {
		transaction = session.beginTransaction();
		String queryString = "from LibrarianBean where id = :id";
		Query query = session.createQuery(queryString);
		query.setInteger("id", id);
        LibrarianBean bean =(LibrarianBean)query.uniqueResult();
		session.delete(bean);
		status=1;
		transaction.commit();
		System.out.println("Employee records deleted!");
		} catch (HibernateException e) {

		transaction.rollback();

		e.printStackTrace();

		} finally 
		{

		session.close();
		}
		return status;
		
		/*int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("delete from e_librarian where id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;*/
	}

public static boolean authenticate(String email,String password){
		boolean status=false;
		Session session = DB.sf.openSession();
		Transaction transaction = null;
		
		try {
		transaction = session.beginTransaction();
		String queryString = "from LibrarianBean where email = :email and password=:password";
		Query query = session.createQuery(queryString);
		query.setString("email", email);
		query.setString("password", password);
		LibrarianBean bean =(LibrarianBean)query.uniqueResult();
		status=true;

		transaction.commit();
		
		} catch (HibernateException e) {

		transaction.rollback();

		e.printStackTrace();

		} finally 
		{

		session.close();
		}
		return status;
		/*try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from e_librarian where email=? and password=?");
			ps.setString(1,email);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				status=true;
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;*/
	}
	
public static String getUserName(String email,String password){
		String name="";
		
		
		Session session = DB.sf.openSession();
		Transaction transaction = null;
		
		try {
		transaction = session.beginTransaction();
		String queryString = "from LibrarianBean where email = :email and password=:password";
		Query query = session.createQuery(queryString);
		query.setString("email", email);
		query.setString("password", password);
		LibrarianBean bean =(LibrarianBean)query.uniqueResult();
		name=bean.getName();

		transaction.commit();
		
		} catch (HibernateException e) {

		transaction.rollback();

		e.printStackTrace();

		} finally 
		{

		session.close();
		}
		return name;

	/*	try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select name from e_librarian where email=? and password=?");
			ps.setString(1,email);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				name=rs.getString(1);
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return name;
	}*/
}}
