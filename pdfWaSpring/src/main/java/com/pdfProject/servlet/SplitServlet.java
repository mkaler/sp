package com.pdfProject.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.pdfProject.core.Employee;
import com.pdfProject.core.ManipulatePDF;

/**
 * Servlet implementation class SplitServlet
 */
public class SplitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private File file;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SplitServlet() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			
			uploadPDF(request, response);
			
			splitPDF(file);
			
			List<Employee> emps = ManipulatePDF.DBToListOut(getServletContext().getInitParameter("dbOutPath"));
			
			request.setAttribute("users", emps);
			
			request.getRequestDispatcher("/WEB-INF/views/download.jsp").forward(request, response);
			
		}
		catch(ServletException servletEx)
		{
			servletEx.printStackTrace();
			
		}
		catch(IOException IOEx)
		{
			IOEx.getMessage();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void uploadPDF(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		
		
		// creates the directory if it does not exist
		File uploadDir = new File(getServletContext().getInitParameter("upload"));
		if (!uploadDir.exists()) {
		    uploadDir.mkdir();
		}
		
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	   
	      //factory.setRepository(new File("src/results/"));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);

	      // Parse the request to get file items.
	      List<FileItem> fileItems = upload.parseRequest(request);
		
	      // Process the uploaded file items
	      Iterator<FileItem> i = fileItems.iterator();
	      
	      while ( i.hasNext () ) 
	      {
	         FileItem fi = (FileItem)i.next();
	         
	         if ( !fi.isFormField () )	
	         {
	            String fileName = fi.getName();
	            
	            
	            // Write the file
	            if( fileName.lastIndexOf("\\") >= 0 )
	            {
	               file = new File( getServletContext().getInitParameter("upload") + 
	               fileName.substring( fileName.lastIndexOf("\\"))) ;
	            }
	            else
	            {
	               file = new File( getServletContext().getInitParameter("upload") + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
	            }
	            
	            fi.write( file );
	            System.out.println(file.getAbsolutePath());
	            System.out.println(file.getCanonicalPath());
	            
	         }
	      }
		}
	private void splitPDF(File file) throws Exception
	{
		ManipulatePDF p = new ManipulatePDF();
		p.init(file, getServletContext().getInitParameter("PDFOutDir"),getServletContext().getInitParameter("dbPath"),
				getServletContext().getInitParameter("dbOutPath"));
		System.out.println("Done");
		
	}



}
