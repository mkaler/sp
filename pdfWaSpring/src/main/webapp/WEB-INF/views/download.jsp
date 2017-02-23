<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Download</title>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
		integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		
		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" 
		integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
		
		<style>
			body {background-color: #ffffff;}
		</style>		
		
	</head>
	<body>
		<div class="container-fluid">
			  <div class="jumbotron">
			    <h1>Manager</h1>
			    <p>Create single .pdf file for a single employee, from a .pdf file containing information regarding all
			    the employees</p>
			  </div>
			</div>	
			<div class= "container">
				<ul class="pager">
				  <li class="previous">
			        <a href="/pro/" class="btn btn-lg">
			          <span class="glyphicon glyphicon-home"></span> Home
			        </a>
		          </li>
				</ul>
				<div class="panel panel-success">
				      <div class="panel-heading">Your files are ready</div>
				      <div class="panel-body">
				      	<p>Click on this button to see the single files
							<button data-toggle="collapse" class="btn btn-info"data-target="#names">Files<span
							 class="badge"><c:out value="${requestScope.filesNames.size()}"></c:out></span><span class="glyphicon glyphicon-menu-down"></span></button>
							<div id="names" class="collapse">
								<form action="DownloadSingleFiles" method = "get" id ="downloadSingle">
								</form>
									 <ul class="list-group">
									 	
									 	<c:forEach items="${requestScope.filesNames}" var= "name" >
											  <li class="list-group-item"><div align="justify"><c:out value = "${name}" ></c:out></div>
											  <br>
												 <div class="btn-group">
												 	<div align = "justify">
														<button form = "downloadSingle" name  = "subButton" value ="${name}" type="submit" 
															class="btn btn-success" onclick="submit">
															<span class="glyphicon glyphicon-download"></span> download
														</button>
													 </div>
												 </div>
											  </li>
									  	 </c:forEach>
									 </ul>					
							</div>	
							<br><br>
							<form action="ZipServlet" method = "get" id ="download">
							</form>
							<p>Download all the files at once in zip format
								<button form = "download" type="submit" class="btn btn-success" onclick="submit">
									<span class="glyphicon glyphicon-download-alt"></span> download
								</button>
							</p>			      	
				      </div>
				 </div>	
		</div>
		<div class = "container">	
			<div class="alert alert-success alert-dismissable">
			    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
			    <strong>Success!</strong> Data extracted from the given PDF
			 </div>		 	
		<table class="table">
		    <thead>
		      <tr>
		        <th>Name</th>
		        <th>Last name</th>
		        <th>CF</th>
		        <th>Month</th>
		        <th>Year</th>
		        <th>Salary</th>
		      </tr>
		    </thead>
		    
		    <tbody>
		    	<c:forEach items="${requestScope.users}" var= "emp" >
			      <tr>
			        <td><c:out value="${emp.name}"> </c:out></td>
			        <td><c:out value="${emp.lastName}"></c:out></td>
			        <td><c:out value="${emp.cf}"> </c:out></td>
			         <td><c:out value="${emp.mese}"> </c:out></td>
			          <td><c:out value="${emp.anno}"> </c:out></td>
			           <td><c:out value="${emp.cifra}"> </c:out></td>
			      </tr>
			     </c:forEach>
		    </tbody>
		 </table>
		</div>
		<br><br><br><br>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
		  
		
	</body>
</html>