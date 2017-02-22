<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Home</title>
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
			          <span class="glyphicon glyphicon-refresh"></span> Reload
			        </a>
		          </li>
				</ul>
				<div class="panel panel-info">
				      <div class="panel-heading">Select the file and click on split (2 MegaBytes maximum size)</div>
				      <div class="panel-body">
						<form action = SplitServlet method ="post" enctype="multipart/form-data" >
						  <div class="form-group">
				    		<label class="btn btn-info" for="my-file-selector">
				    			<input name ="PDFtoSPlit" id="my-file-selector" type="file" style="display:none;" accept = "application/pdf"
				    			onchange="$('#upload-file-info').html($(this).val());"/>
				    			Browse
							</label>
							<span class='label label-default' id="upload-file-info"></span>
				    	  </div>
				    	  <div class="form-group">
				    	  	<input name ="split" type="submit" class="btn btn-primary btn-block" value = "Split" disabled/>
				    	  </div>	  
						</form>												      	
				      </div>
				 </div>	
		</div>
		<br>
		<div class= "container">
				<div class="panel panel-info">
				      <div class="panel-heading">See the employees</div>
				      <div class="panel-body">
				      <br>
				      <div class="panel panel-default">
						  <div class="panel-body">Go to the menu below to view the employees</div>
					  </div>
					  <a href="EditDb" class="btn btn-primary btn-block" role="button">Employees menu</a>
 										      	
				      </div>
				 </div>	
		</div>
 
		</div>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>   
		

		
	</body>
		<script type="text/javascript">
	       $(document).ready(
	        function(){
	            $('input:submit').attr('disabled',true);
	            $('input:file').change(
	                function(){
	                    if ($(this).val()){
	                        $('input:submit').removeAttr('disabled'); 
	                    }
	                    else {
	                        $('input:submit').attr('disabled',true);
	                    }
	                });
	        });
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