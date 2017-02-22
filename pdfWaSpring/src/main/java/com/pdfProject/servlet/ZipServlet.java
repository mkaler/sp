package com.pdfProject.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ZipServlet
 */
public class ZipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZipServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{	
			if(new File(getServletContext().getInitParameter("PDFOutDir")).list().length < 1)
			{
				getServletContext().setAttribute("errorMessageEx","Content not avaliable");
				request.getRequestDispatcher("/errorPage").forward(request, response);
				return;
			}
				
			makeZip(request, response);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void zipDir(String dir2zip, ZipOutputStream zos) throws IOException 
	{
	        //create a new File object based on the directory we have to zip File    
	        File zipDir = new File(dir2zip);
	        //get a listing of the directory content 
	        String[] dirList = zipDir.list(); 
	        byte[] readBuffer = new byte[2156]; 
	        int bytesIn = 0; 
	        //loop through dirList, and zip the files 
	        for(int i=0; i<dirList.length; i++) 
	        { 
	            File f = new File(zipDir, dirList[i]); 
	            if(f.isDirectory()) 
	            { 
	                //if the File object is a directory, call this 
	                //function again to add its content recursively 
	                String filePath = f.getPath();
	            //  String filePath = f.getCanonicalPath();

	                zipDir(filePath, zos);
	                //loop again 
	                continue; 
	            } 
	            //if we reached here, the File object f was not  a directory 
	            //create a FileInputStream on top of f 
	            FileInputStream fis = new FileInputStream(f); 
	            // create a new zip entry 
	            ZipEntry anEntry = new ZipEntry(f.getName()); 
	            //place the zip entry in the ZipOutputStream object 
	            zos.putNextEntry(anEntry); 
	            //now write the content of the file to the ZipOutputStream 
	            while((bytesIn = fis.read(readBuffer)) != -1) 
	            { 
	                zos.write(readBuffer, 0, bytesIn); 
	            } 
	            //close the Stream 
	            fis.close(); 
	        }  
	    
	}
	private void makeZip(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try 
	    { 
	        response.setContentType("application/zip");
	        response.setHeader("Content-Disposition", "inline;filename=dividedPDFs.zip");

	        ZipOutputStream zos = new 
	               ZipOutputStream(response.getOutputStream());

	        zipDir(getServletContext().getInitParameter("PDFOutDir"), zos); 

	        zos.close();
	        //request.getRequestDispatcher("/WEB-INF/jsps/acceptFile.jsp").forward(request, response);
	    } 
	    catch(Exception e) 
	    { 
	       throw new Exception("Error while making the zip", e);
	    } 

		
	}

}
