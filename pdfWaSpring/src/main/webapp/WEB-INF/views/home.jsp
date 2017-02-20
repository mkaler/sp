<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Home</title>
	</head>
	<body>
	
	
	 <form action = "split" method ="get">
			<input type="submit" value="gestine libro unico">
	 </form>
	
		<a href="split">Go to split</a>
		<br>
		<br>
		
		<form action = "EditDb" >
			<input type="submit" value="gestione utenti">
		</form> 
		   
	</body>
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