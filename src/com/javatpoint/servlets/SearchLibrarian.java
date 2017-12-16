package com.javatpoint.servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javatpoint.beans.LibrarianBean;
import com.javatpoint.dao.LibrarianDao;
	@WebServlet("/SearchLibrarian")
	public class SearchLibrarian extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();	
		String sid=request.getParameter("id");
			int id=Integer.parseInt(sid);
			LibrarianBean viewbyid=LibrarianDao.viewById(id);
			out.println("<table class='table table-bordered table-striped'>");
			out.println("<tr><th>Id</th><th>Name</th><th>Email</th><th>Password</th><th>Mobile</th><th>Edit</th><th>Delete</th></tr>");
			out.println("<tr><td>"+viewbyid.getId()+"</td><td>"+viewbyid.getName()+"</td><td>"+viewbyid.getEmail()+"</td><td>"+viewbyid.getPassword()+"</td><td>"+viewbyid.getMobile()+"</td>");
			
	//		response.sendRedirect("ViewLibrarian");
		}


}
