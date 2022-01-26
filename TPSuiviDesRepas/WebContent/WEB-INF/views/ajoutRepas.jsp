<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.javaee.repas.bo.Repas"%>
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
			
			</div>
			<aside>
				<%
				Repas repas = (Repas) request.getAttribute("repas");
				if (repas != null) {
				%>
				<p><%=repas.toString()%></p>
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