package com.javatpoint.dao;

import java.sql.*;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
//import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.javatpoint.dao.LibrarianDao;

public class DB {

	public static SessionFactory sf = null;
	static {
		try {
			Configuration config = new Configuration();
			config.configure();
			// ServiceRegistry sr=new
			// ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
			sf = config.buildSessionFactory();
			// SessionFactory sf=new
			// AnnotationConfiguration().configure().buildSessionFactory();
			// return sf;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		// Session session=sessionf.openSession();
		// Transaction tx=session.beginTransaction();
		// session.save(arg0);
		// tx.commit();
	}

	public static Connection getCon() {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://LOCALHOST:5432/elib", "postgres", "postgres");

		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
}
