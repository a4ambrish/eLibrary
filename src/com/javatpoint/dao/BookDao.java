package com.javatpoint.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javatpoint.beans.BookBean;
import com.javatpoint.beans.IssueBookBean;
import com.javatpoint.beans.LibrarianBean;
import com.javatpoint.beans.ReturnIssueBookBean;

public class BookDao {

	public static int save(BookBean bean) {
		int status = 0;

		Session session = DB.sf.openSession();
		Transaction transaction = null;
		try {
			// Hibernate session code this is updated one for git.
			transaction = session.beginTransaction();
			session.save(bean);
			transaction.commit();
			status = 1;
			System.out.println("Records inserted sucessessfully");
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
		/*
		 * try{ Connection con=DB.getCon(); PreparedStatement
		 * ps=con.prepareStatement("insert into e_book values(?,?,?,?,?,?)");
		 * ps.setString(1,bean.getCallno()); ps.setString(2,bean.getName());
		 * ps.setString(3,bean.getAuthor());
		 * ps.setString(4,bean.getPublisher()); ps.setInt(5,bean.getQuantity());
		 * ps.setInt(6,0); status=ps.executeUpdate(); con.close();
		 * 
		 * }catch(Exception e){System.out.println(e);}
		 * 
		 * return status;
		 */
	}

	public static List<BookBean> view() {
		List<BookBean> list = new ArrayList<BookBean>();

		Session session = DB.sf.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List bookbean = session.createQuery("from BookBean").list();

			for (Iterator iterator = bookbean.iterator(); iterator.hasNext();) {
				BookBean bean = (BookBean) iterator.next();
				System.out.println("Retrieving Librarian details: ");
				System.out.println(bean.getCallno() + "  " + bean.getName() + "  " + bean.getAuthor() + "   "
						+ bean.getPublisher() + " " + bean.getQuantity() + " " + bean.getIssued());
				list.add(bean);
			}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;

		/*
		 * try{ Connection con=DB.getCon(); PreparedStatement
		 * ps=con.prepareStatement("select * from e_book"); ResultSet
		 * rs=ps.executeQuery(); while(rs.next()){ BookBean bean=new BookBean();
		 * bean.setCallno(rs.getString("callno"));
		 * bean.setName(rs.getString("name"));
		 * bean.setAuthor(rs.getString("author"));
		 * bean.setPublisher(rs.getString("publisher"));
		 * bean.setQuantity(rs.getInt("quantity"));
		 * bean.setIssued(rs.getInt("issued"));
		 * 
		 * list.add(bean); } con.close();
		 * 
		 * }catch(Exception e){System.out.println(e);}
		 * 
		 * return list;
		 */
	}

	public static int delete(String callno) {
		int status = 0;
		Session session = DB.sf.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			String queryString = "from BookBean where callno= :callno";
			Query query = session.createQuery(queryString);
			query.setString("callno", callno);
			BookBean bean = (BookBean) query.uniqueResult();
			session.delete(bean);
			status = 1;
			transaction.commit();
			System.out.println("Book records deleted!");
		} catch (HibernateException e) {

			transaction.rollback();

			e.printStackTrace();

		} finally {

			session.close();
		}
		return status;

		/*
		 * try{ Connection con=DB.getCon(); PreparedStatement
		 * ps=con.prepareStatement("delete from e_book where callno=?");
		 * ps.setString(1,callno); status=ps.executeUpdate(); con.close();
		 * 
		 * }catch(Exception e){System.out.println(e);}
		 * 
		 * return status;
		 */
	}

	public static int getIssued(String callno) {
		int issued = 0;
		Session session = DB.sf.openSession();
		Transaction transaction = null;
		BookBean bean = new BookBean();
		try {
			transaction = session.beginTransaction();

			bean = (BookBean) session.get(BookBean.class, callno);
			issued = bean.getIssued();
			System.out.println(bean.getIssued());
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}

		finally {
			session.close();
		}
		return issued;

		/*
		 * try{ Connection con=DB.getCon(); PreparedStatement
		 * ps=con.prepareStatement("select * from e_book where callno=?");
		 * ps.setString(1,callno); ResultSet rs=ps.executeQuery();
		 * if(rs.next()){ issued=rs.getInt("issued"); } con.close();
		 * 
		 * }catch(Exception e){System.out.println(e);}
		 * 
		 * return issued;
		 */
	}

	public static boolean checkIssue(String callno) {
		boolean status = false;
		Session session = DB.sf.openSession();

		BookBean bean = new BookBean();

		try {

			String queryString = "from BookBean where callno= :callno and issued < quantity";
			Query query = session.createQuery(queryString);
			query.setString("callno", callno);

			BookBean beans = (BookBean) query.uniqueResult();

			status = beans == null ? Boolean.FALSE : Boolean.TRUE;
			System.out.println(bean.getIssued());

		} catch (HibernateException e) {

			e.printStackTrace();
		}

		finally {
			session.close();
		}
		return status;
		/*
		 * try{ Connection con=DB.getCon(); PreparedStatement
		 * ps=con.prepareStatement(
		 * "select * from e_book where callno=? and quantity>issued");
		 * ps.setString(1,callno); ResultSet rs=ps.executeQuery();
		 * if(rs.next()){ status=true; } con.close();
		 * 
		 * }catch(Exception e){System.out.println(e);}
		 * 
		 * return status;
		 */
	}

	public static int issueBook(IssueBookBean bean) {
		String callno = bean.getCallno();
		boolean checkstatus = checkIssue(callno);
		System.out.println("Check status: " + checkstatus);
		Session session  =null;
		Transaction tr = null;
		if (checkstatus) {
			int status = 0;
			try {
				/*
				Connection con = DB.getCon();
				PreparedStatement ps = con.prepareStatement("insert into e_issuebook values(?,?,?,?,?,?)");
				ps.setString(1, bean.getCallno());
				ps.setString(2, bean.getStudentid());
				ps.setString(3, bean.getStudentname());
				ps.setLong(4, bean.getStudentmobile());
				java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
				ps.setDate(5, currentDate);
				ps.setString(6, "no");

				status = ps.executeUpdate();
				if (status > 0) {
					PreparedStatement ps2 = con.prepareStatement("update e_book set issued=? where callno=?");
					ps2.setInt(1, getIssued(callno) + 1);
					ps2.setString(2, callno);
					status = ps2.executeUpdate();
				}
//				con.close(); */
			
			 session = DB.sf.openSession();
			BookBean bookBean = (BookBean) session.get(BookBean.class, bean.getCallno());
			bookBean.setIssued(bookBean.getIssued()+1);
			 tr = session.beginTransaction();
			
			session.update(bookBean);
			session.save(bean);
			session.flush();
			tr.commit();

			} catch (Exception e) {
				System.out.println(e);
			}finally {
				//tr.rollback();
				session.close();
			}

			return status;
		} else {
			return 0;
		}
	}

	public static int returnBook(String callno, int studentid) {
		int status = 0;
		Session session =null;
		Transaction tr = null;
		try {
			/*Connection con = DB.getCon();
			PreparedStatement ps = con
					.prepareStatement("update e_issuebook set returnstatus='yes' where callno=? and studentid=?");
			ps.setString(1, callno);
			ps.setString(2, studentid + "");

			status = ps.executeUpdate();
			if (status > 0) {
				PreparedStatement ps2 = con.prepareStatement("update e_book set issued=? where callno=?");
				ps2.setInt(1, getIssued(callno) - 1);
				ps2.setString(2, callno);
				status = ps2.executeUpdate();
			}
			con.close();
*/
			 session = DB.sf.openSession();
			tr = session.beginTransaction();
			BookBean bookBean = findBookById(callno);
			IssueBookBean issueBookBean = findIssuesBook(DBConstants.BOOK_ISSUE_STATUS_No, callno, String.valueOf(studentid));
			issueBookBean.setReturnstatus(DBConstants.BOOK_ISSUE_STATUS_YES);
			ReturnIssueBookBean history = new ReturnIssueBookBean();
			history.setCallno(issueBookBean.getCallno());
			history.setIssueddate(issueBookBean.getIssueddate());
			history.setReturndate(new Date());
			history.setReturnstatus(issueBookBean.getReturnstatus());
			history.setStudentid(issueBookBean.getStudentid());
			history.setStudentmobile(issueBookBean.getStudentmobile());
			history.setStudentname(issueBookBean.getStudentname());
			
			
			bookBean.setIssued(bookBean.getIssued()-1);
			session.update(bookBean);
			session.delete(issueBookBean);
			session.save(history);
			
			tr.commit();
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			session.close();
		}

		return status;
	}

	public static List<IssueBookBean> viewIssuedBooks() {
		List<IssueBookBean> list = new ArrayList<IssueBookBean>();
		boolean status = false;
		Session session = DB.sf.openSession();

		try {

			String queryString = "from IssueBookBean ";
			Query query = session.createQuery(queryString);
			list =  query.list();

		} catch (HibernateException e) {

			e.printStackTrace();
		}

		finally {
			session.close();
		}
		
		return list;
	}
	/*
	 * public static int update(LibrarianBean bean){ int status=0; try{
	 * Connection con=DB.getCon(); PreparedStatement ps=con.prepareStatement(
	 * "update e_librarian set name=?,email=?,password=?,mobile=? where id=?");
	 * ps.setString(1,bean.getName()); ps.setString(2,bean.getEmail());
	 * ps.setString(3,bean.getPassword()); ps.setLong(4,bean.getMobile());
	 * ps.setInt(5,bean.getId()); status=ps.executeUpdate(); con.close();
	 * 
	 * }catch(Exception e){System.out.println(e);}
	 * 
	 * return status; } public static LibrarianBean viewById(int id){
	 * LibrarianBean bean=new LibrarianBean(); try{ Connection con=DB.getCon();
	 * PreparedStatement ps=con.prepareStatement(
	 * "select * from e_librarian where id=?"); ps.setInt(1,id); ResultSet
	 * rs=ps.executeQuery(); if(rs.next()){ bean.setId(rs.getInt(1));
	 * bean.setName(rs.getString(2)); bean.setPassword(rs.getString(3));
	 * bean.setEmail(rs.getString(4)); bean.setMobile(rs.getLong(5)); }
	 * con.close();
	 * 
	 * }catch(Exception e){System.out.println(e);}
	 * 
	 * return bean; }
	 */
	
	
	public static BookBean findBookById(String callno){
		
		BookBean temp= null;
		Session session = DB.sf.openSession();
		try {

			String queryString = "from BookBean where callno= :callno";
			Query query = session.createQuery(queryString);
			query.setString("callno", callno);
			temp = (BookBean) query.uniqueResult();
		} catch (HibernateException e) {

			e.printStackTrace();
		}

		finally {
			session.close();
		}
		return temp;
	}
	
public static IssueBookBean findIssuesBook(String returnstatus, String callno, String studentid){
		
	IssueBookBean temp= null;
		Session session = DB.sf.openSession();
		try {

			String queryString = "from IssueBookBean where returnstatus= :returnstatus and callno= :callno and studentid= :studentid ";
			Query query = session.createQuery(queryString);
			query.setString("callno", callno);
			query.setString("returnstatus", returnstatus);
			query.setString("studentid", studentid);
			
			temp = (IssueBookBean) query.uniqueResult();
		} catch (HibernateException e) {

			e.printStackTrace();
		}

		finally {
			session.close();
		}
		return temp;
	}
	public static void main(String[] args) {
		
		returnBook("2", 126);
		returnBook("2", 127);
		
		IssueBookBean issueBookBean = new IssueBookBean();
		
		issueBookBean.setCallno("20");
		issueBookBean.setIssueddate(new Date());
		issueBookBean.setReturnstatus(DBConstants.BOOK_ISSUE_STATUS_No);
issueBookBean.setStudentid("125");
issueBookBean.setStudentmobile(98115451);
issueBookBean.setStudentname("Prshant");
issueBook(issueBookBean);
	
	
	
	
	issueBookBean.setCallno("2");
	issueBookBean.setIssueddate(new Date());
	issueBookBean.setReturnstatus(DBConstants.BOOK_ISSUE_STATUS_No);
issueBookBean.setStudentid("128");
issueBookBean.setStudentmobile(98115451);
issueBookBean.setStudentname("Tanvi");
issueBook(issueBookBean);
//returnBook("20", 125);
	}
}
