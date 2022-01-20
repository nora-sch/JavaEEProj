<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CHIFOUMI</title>
</head>
<body>
	<header></header>
	<main>
		<div id="chifoumi-form-wrapper">
			<h1>CHIFOUMI</h1>
			<p>choisissez action :</p>


			<form method="post" action="<%= request.getContextPath()%>/">
				<%
				String[] actions = (String[])request.getAttribute("actions");
				int index = 0;
				for(String action : actions){ %>
				
				<input type="radio" id="<%=action%>" name="choix" value="<%=index%>">
				<label for="<%=action%>"><%=action%></label><br>

				<% index++;			  				  
				}
				%>
				<input type="submit" value="Valider" />
			</form>
		</div>
	</main>
	<footer> Copyright - n-sch - 2022 </footer>
</body>
</html>