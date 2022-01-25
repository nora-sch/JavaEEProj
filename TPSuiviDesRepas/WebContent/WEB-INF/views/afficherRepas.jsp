<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.javaee.repas.bo.Repas"%>
<%@page import="fr.eni.javaee.repas.bo.Aliment"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste de repas</title>
</head>
<body>
	<h1>Liste de repas</h1>

	<% List<Repas> listeRepas = (List<Repas>)request.getAttribute("listeRepas");
	if(listeRepas!=null && listeRepas.size()>0){
for(Repas repas : listeRepas){%>
	<p><%= repas.toString()%></p>
	<% }
	} %>

	<a href="./">Retour à l'accueil</a>
</body>
</html>