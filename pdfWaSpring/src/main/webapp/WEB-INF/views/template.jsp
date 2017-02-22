<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Users</title>
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
		<div class = "container">
			<ul class="pager">
			  <li class="previous">
		        <a href="/pro/" class="btn btn-lg">
		          <span class="glyphicon glyphicon-home"></span> Home
		        </a>
	        </li>
			</ul>
		    <div class="panel panel-info">
		      <div class="panel-heading">Employees</div>
		      <div class="panel-body">Add or remove an employee from the list</div>
		    </div>	
		    <button type="button" class="btn btn-info btn-block" data-toggle="collapse" data-target="#demo">Add an employee</button>
				  <div id="demo" class="collapse">
				  <br><br>
						 <form class="form-inline" action = "AddEmp" method = "post">
						  <div class="form-group">
							<label for="fname">First name:</label>
							  <input type="text" class="form-control" name="fname">
						  </div>
						  <div class="form-group">
							<label for="lname">Last name:</label>
							  <input type="text" class="form-control" name="lname">
						  </div>
						  <div class="form-group">
							<label for="cf">CF:</label>
							  <input type="text" class="form-control" name="cf">
						  </div>
						  <button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-plus"></span></button>
						</form>	
								    
				  </div>
				  <br><br>		 	
		<form action="DeleteEmp" method="post" id="toDel">            
		  <table class="table">
		    <thead>
		      <tr>
		        <th>Name</th>
		        <th>Last name</th>
		        <th>CF</th>
		      </tr>
		    </thead>
		    
		    <tbody>
		    	<c:forEach items="${requestScope.users}" var= "emp" >
			      <tr>
			        <td><c:out value="${emp.name}"> </c:out></td>
			        <td><c:out value="${emp.lastName}"></c:out></td>
			        <td><c:out value="${emp.cf}"> </c:out></td>
			        <td><input type="radio" name="toDel" value="${emp.cf}"></td>
			      </tr>
			     </c:forEach>
		    </tbody>
		  </table>
		 </form>
		 <button class="btn btn-danger btn-block" form="toDel" type="submit" value="Submit" onclick="clicked();">Remove 
		 <span class="glyphicon glyphicon-remove"></span></button>
		 
	</div>
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>  
		   <br><br><br><br><br><br><br><br><br><br>
	</body>
	<script type="text/javascript">
	    function clicked() {
	       if (confirm('are you sure?')) {
	           yourformelement.submit();
	       } else {
	           return false;
	       }
	    }
    </script>
	<script type="text/javascript">
		var cookie = ("" + document.cookie);
		if(!cookie.match(/\S/))
		{
		    document.cookie = "refresh";
		    location.reload(true);
		}
		else
		{
		    document.cookie = "refresh; expires=Thu, 01 Jan 1970 00:00:00 GMT";
		}
	</script>
</html>