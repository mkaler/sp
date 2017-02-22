<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Error</title>
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
			    <p>Create a single .pdf file for a single employee, from a .pdf file containing information regarding all
			    the employees</p>
			  </div>
		</div>	
			
		<div class="container-fluid">
		  
			 <div class= "container">
					<ul class="pager">
					  <li class="previous">
				        <a href="/pro/" class="btn btn-lg">
				          <span class="glyphicon glyphicon-home"></span> Home
				        </a>
			          </li>
					</ul>
					<div class="alert alert-danger">
					  <strong>Error!</strong> <c:out value="${error}"></c:out>
					</div>
					<div class="alert alert-danger">
					  <strong>Error!</strong> <c:out value="${errorMessageEx}"></c:out>
					</div>
			</div>	
		</div>
	</body>
</html>