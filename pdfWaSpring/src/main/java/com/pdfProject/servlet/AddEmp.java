package com.pdfProject.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pdfProject.core.Employee;

/**
 * Servlet implementation class AddEmp
 */
public class AddEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
	    Statement stmt = null;
	    ResultSet rs = null;
		try
		{
			
		
			String name = request.getParameter("fname");
			String lName = request.getParameter("lname");
			String cf = request.getParameter("cf");
			
			File db = new File(getServletContext().getInitParameter("dbPath"));
			
			if(!db.exists())
				throw new IOException();
		    
	    	 Class.forName("org.sqlite.JDBC");
	    	 con = DriverManager.getConnection("jdbc:sqlite:"+getServletContext().getInitParameter("dbPath"));
	    	 con.setAutoCommit(false);
	    	 stmt = con.createStatement();
	    	 rs = stmt.executeQuery( "SELECT * FROM dipendenti;" );
		      while ( rs.next() ) {
		    	 Employee emp = new Employee();
		         emp.setName(rs.getString("nome"));
		         emp.setLastName(rs.getString("cognome"));
		         emp.setCf(rs.getString("cf"));
		         System.out.println(emp.toString());
		      }
	    	 
	    	 String str = "INSERT INTO dipendenti" + " VALUES ('"+name+"','"+lName+"','"+cf+"')";
	    	 
	    	
	    	
	    	stmt.executeUpdate(str);
	    	con.commit();
	    	con.close();
	    	rs.close();
	    	response.sendRedirect("EditDb");
	}
	catch(IOException exIO)
	{
		getServletContext().setAttribute("errorMessageEx", exIO.getMessage());
		request.getRequestDispatcher("/errorPage").forward(request, response);
	}
	catch(SQLException exSQL)
	{
		getServletContext().setAttribute("errorMessageEx", exSQL.getMessage());
		request.getRequestDispatcher("/errorPage").forward(request, response);
	}
	catch(ClassNotFoundException exS)
	{
			getServletContext().setAttribute("errorMessageEx", exS.getMessage());
			request.getRequestDispatcher("/errorPage").forward(request, response);
	}	
	finally
	{
		try
		{
			if(con != null)
				con.close();
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}
		catch(SQLException ex){}
	}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
