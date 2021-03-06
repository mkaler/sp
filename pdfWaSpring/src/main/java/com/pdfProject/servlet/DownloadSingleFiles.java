package com.pdfProject.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;

/**
 * Servlet implementation class DownloadSingleFiles
 */
public class DownloadSingleFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static final int BUFFER_SIZE = 4096;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadSingleFiles() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String d = request.getParameter("subButton");
		String v = request.getParameter("viewButton");
		if(d != null)
		{
			try
			{
				File toDown = new File(getServletContext().getInitParameter("PDFOutDir")+d);
				
		 
		        // set content attributes for the response
		        response.setContentType("application/pdf");
		        response.setHeader("Content-Disposition", "attachment;filename="+d);
		        
		        response.setContentLength((int)toDown.length());
		        
		        InputStream inputStream = new BufferedInputStream(new FileInputStream(toDown));
		        
		        //FileCopyUtils.copy(inputStream, response.getOutputStream());
		        
		        //inputStream.close();
		 
		        // get output stream of the response
		        OutputStream outStream = response.getOutputStream();
		 
		        byte[] buffer = new byte[BUFFER_SIZE];
		        int bytesRead = -1;
		 
		        // write bytes read from the input stream into the output stream
		        while ((bytesRead = inputStream.read(buffer)) != -1) {
		            outStream.write(buffer, 0, bytesRead);
		        }
		 
		        inputStream.close();
		        outStream.close();
			}
			catch(FileNotFoundException ex)
			{
				getServletContext().setAttribute("errorMessageEx", ex.getMessage());
				request.getRequestDispatcher("/errorPage").forward(request, response);
			}
		}
		else 
			{
				String toView = "/web/viewer.html?file=%output/pdf/"+v;
				System.out.println("V: "+ v);
				request.getRequestDispatcher(toView).forward(request, response);
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
