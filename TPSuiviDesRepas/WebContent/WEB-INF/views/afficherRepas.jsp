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
<!-- Custom styles for this template -->
<style>
<%@ include file="../../css/style.css"%>
</style>
</head>
<body>
	<header></header>
	<main>
		<div class="main-wrapper">
<div class = "flex-box">
			<div class="list-container">
				<h3>LISTE DE REPAS</h3>
				<%
				List<Repas> listeRepas = (List<Repas>) request.getAttribute("listeRepas");
				if (listeRepas != null && listeRepas.size() > 0) {
					for (Repas repas : listeRepas) {
				%>
				<p><%=repas.toString()%></p>
				<%
				}
				}
				%>
			</div>

			<div class="list-container">
				<div class="form-wrapper">
					<h3>RECHERCHE PAR LA DATE</h3>
					<form action="<%=request.getContextPath()%>/repas" method="post">

						<label for="date">Date : </label> <input type="date" id="date"
							name="date" value="<%=request.getParameter("date")%>" /> <br />
						<input type="submit" value="Valider" /> <a
							href="<%=request.getContextPath()%>/repas"><input
							type="button" value="Réinitialiser" /></a>
					</form>

				</div>
			
					<%
					List<Repas> listeRepasParDate = (List<Repas>) request.getAttribute("listeRepasParDate");
					if (listeRepasParDate != null && listeRepasParDate.size() > 0) {
						for (Repas repas : listeRepasParDate) {
					%>
					<p><%=repas.toString()%></p>
					<%
					}
					}
					%>

					<%
					if ((String) request.getAttribute("repasParDate") == "false") {
					%>
					<p>aucune repas enregistré à cette date!</p>
					<%
					}
					%>
			
			</div>
			</div>
		</div>
	</main>
	<footer>
		<a href="<%=request.getContextPath()%>/ajoutrepas">Ajouter un
			nouveau repas</a><a href="./">Retour à l'accueil</a>
	</footer>
</body>
</html>