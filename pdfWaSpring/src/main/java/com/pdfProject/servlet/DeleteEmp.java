package com.pdfProject.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteEmp
 */
public class DeleteEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEmp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection con = null;
	    Statement stmt = null;
		try
		{
			
		
			String cf = request.getParameter("toDel");
			
			File db = new File(getServletContext().getInitParameter("dbPath"));
			
			if(!db.exists())
				throw new IOException();
		    
	    	 Class.forName("org.sqlite.JDBC");
	    	 con = DriverManager.getConnection("jdbc:sqlite:"+getServletContext().getInitParameter("dbPath"));
	    	 con.setAutoCommit(false);
	    	 stmt = con.createStatement();
	    	 
	    	 String sql = "DELETE FROM dipendenti " +
	                   "WHERE cf = " + "'"+cf+"'";
	    	 
	    	 stmt.executeUpdate(sql);
	    	 con.commit();
	    	 //stmt.executeUpdate("VACUUM");
	    	// con.commit();
	    	 stmt.close();
	    	 
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
