<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Résultats</title>
</head>
<body>
	<%
	String choixUtilisateur = (String) request.getAttribute("choixUtilisateur");
	String choixServeur = (String) request.getAttribute("choixServeur");
	String resultat = (String) request.getAttribute("result");
	%>
	<p>
		Vous avez choisi :
		<%=choixUtilisateur%></p>
	<p>
		Ordinateur a choisi :
		<%=choixServeur%></p>
	<h3><%=resultat%></h3>
	<!-- TODO lien pour retenter -->
</body>
</html>