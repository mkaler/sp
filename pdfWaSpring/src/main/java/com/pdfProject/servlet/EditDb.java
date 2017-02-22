package com.pdfProject.servlet;

import java.io.IOException;
import java.util.List;


import com.pdfProject.core.ManipulatePDF;
import com.pdfProject.core.Employee;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditDb
 */
public class EditDb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDb() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			
			List<Employee> emps = ManipulatePDF.DBToList(getServletContext().getInitParameter("dbPath"));
			
			request.setAttribute("users", emps);
		
			request.getRequestDispatcher("/template").forward(request, response);
			
			
		} catch (Exception ex) {
			getServletContext().setAttribute("errorMessageEx", ex.getMessage());
			request.getRequestDispatcher("/errorPage").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	

}
