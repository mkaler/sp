package com.pdfProject.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.pdfProject.core.Employee;
import com.pdfProject.core.ManipulatePDF;

/**
 * Servlet implementation class SplitServlet
 */
public class SplitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int maxFileSize = ((int) java.lang.Math.pow(10, 6)) * 2;

	 
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
			
			FileUtils.cleanDirectory(new File(getServletContext().getInitParameter("PDFOutDir"))); 
			
			uploadPDF(request, response);
			
			splitPDF(file);
			
			List<Employee> emps = ManipulatePDF.DBToListOut(getServletContext().getInitParameter("dbOutPath"));
		
			request.setAttribute("users", emps);
			
			List<String> files = getFilesNames();
			
			request.setAttribute("filesNames", files);
			
			request.getRequestDispatcher("/WEB-INF/views/download.jsp").forward(request, response);
			
		}
		catch(ServletException exS)
		{
			getServletContext().setAttribute("errorMessageEx", exS.getMessage());
			request.getRequestDispatcher("/errorPage").forward(request, response);	
		}
		catch(IOException exIO)
		{
			getServletContext().setAttribute("errorMessageEx", exIO.getMessage());
			request.getRequestDispatcher("/errorPage").forward(request, response);
		}
		catch(Exception ex)
		{
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
	      
	      upload.setFileSizeMax(maxFileSize);

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
	
	private List<String> getFilesNames() throws FileNotFoundException
	{
		
		File folder = new File(getServletContext().getInitParameter("PDFOutDir"));
		File[] listOfFiles = folder.listFiles();
		List<String> files = new ArrayList<String>();
		    for (int i = 0; i < listOfFiles.length; i++)
		    {
		      if (listOfFiles[i].isFile()) 
		      {
		        files.add(listOfFiles[i].getName());
		      }
		    }
		    return files;
	}




}
