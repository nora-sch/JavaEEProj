<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.javaee.repas.bo.Repas"%>
<%@ page import="fr.eni.javaee.repas.messages.LecteurMessage"%>
<%@page import="fr.eni.javaee.repas.bo.Aliment"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter repas</title>
<!-- Custom styles for this template -->
<style>
  <%@ include file="../../css/style.css"%>
  </style> 
</head>
<body>
	<header></header>
	<main>
		<div class="main-wrapper">
			<div class="form-wrapper">
				<h3>AJOUT</h3>
	
				<form action="<%=request.getContextPath()%>/ajoutrepas"
					method="post">

					<label for="date">Date : </label> <input type="date" id="date"
						name="date" value="<%=request.getParameter("date")%>" /> <br />
					<label for="heure">Heure : </label> <input type="time" id="heure"
						name="heure" value="<%=request.getParameter("heure")%>" /> <br />
					<label for="aliments">Aliments : </label>
					<textarea id="aliments" name="aliments"><%=request.getParameter("aliments") != null ? request.getParameter("aliments") : ""%></textarea>
					<br /> <input type="submit" value="Valider" /> <a
						href="<%=request.getContextPath()%>/ajoutrepas"><input
						type="button" value="Effacer" /></a>
				</form>
				<%
			List<Integer> listeCodesErreur = (List<Integer>)request.getAttribute("listeCodesErreur");
			if(listeCodesErreur!=null)
			{
		%>
				<p class="error-msg">Erreur, le repas n'a pas pu être ajouté :</p>
				<ul>
		<%
				for(int codeErreur:listeCodesErreur)
				{
		%>
					<li class="error-msg"><%=LecteurMessage.getMessageErreur(codeErreur)%></li>
			<% } %>
			</ul>
		<% } %>
			</div>
			<aside>
				<%
				Repas repas = (Repas) request.getAttribute("repas");
				if (repas != null) {
				%>
				<p><%=repas.getDate()%></p>
				<p><%=repas.getHeure()%></p>
				<ul>
				<% for(Aliment aliment : repas.getListeAliments()){
					%>
					<li><%=aliment.getNom()%></li>
				<% }%>
				</ul>
				<%
				}
				%>
			</aside>
		</div>
	</main>
	<footer> 	 <a href="./repas">Voir la
					liste de repas</a>
					<a href="./">Retour à l'accueil</a></footer>
</body>
</html>