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
	<main>
		<h1>Liste de repas</h1>
		<div class="list-container">
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
		<a href="./">Retour à l'accueil</a>
		<aside>
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
			<div class="list-container">
				<%
				List<Repas> listeRepasParDate = (List<Repas>) request.getAttribute("listeRepasParDate");
				if(listeRepasParDate != null && listeRepasParDate.size() > 0) {
					for (Repas repas : listeRepasParDate) {
				%>
				<p><%=repas.toString()%></p>
				<%
					}
				}%>

				<% if((String) request.getAttribute("repasParDate")=="false"){ %>
				<p>aucune repas enregistré à cette date!</p>
				<% } %>
			</div>
		</aside>
	</main>
</body>
</html>