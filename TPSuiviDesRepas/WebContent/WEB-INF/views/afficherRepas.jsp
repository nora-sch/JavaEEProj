<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.javaee.repas.bo.Repas"%>
<%@page import="fr.eni.javaee.repas.bo.Aliment"%>
<%@page import="java.time.format.DateTimeFormatter"%>
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
				<table>
			<thead>
				<tr>
					<th>Date</th>
					<th>Heure</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Repas> listeRepas = (List<Repas>) request.getAttribute("listeRepas");
				if (listeRepas != null && listeRepas.size() > 0) {
					for (Repas repas : listeRepas) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");	
				%>
				<tr>
				<td><%=repas.getDate().format(formatter)%></td>
				<td><%=repas.getHeure()%></td>
				<td>
				<%if(repas.getListeAliments()!=null && repas.getListeAliments().size()>0 && repas.getListeAliments().get(0).getIdAliment()!=0){ %>
				<a href="<%=request.getContextPath()%>/repas?id=<%=repas.getIdRepas()%>">voir contenu</a>
				<%}else{ %>
				<a class="disabled" href="<%=request.getContextPath()%>/repas?id=<%=repas.getIdRepas()%>">voir contenu</a>
				<%} %></td>
				</tr>
				<% if(String.valueOf(repas.getIdRepas()).equals(request.getParameter("id"))){ %>
				<tr>
				<td colspan=3>
				<ul>
				<%for(Aliment aliment: repas.getListeAliments()) {%>
				<li><%=aliment.getNom()%></li>
				<%}%>
				</ul>
				</td>
				</tr>
				<%}%>
			
				<%
				}
				}else{%>
				<p>La liste de repas est vide!</p>
				<%}%>
				</tbody>
				</table>
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
					/* TODO changer l'affichage du partie dateFiltre !*/
					List<Repas> listeRepasParDate = (List<Repas>) request.getAttribute("listeRepasParDate");
					if (listeRepasParDate != null && listeRepasParDate.size() > 0) {%>
						<table>
						<thead>
							<tr>
								<th>Date</th>
								<th>Heure</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
						<%
					
						for (Repas repas : listeRepasParDate) {
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");	
					%>
					<tr>
					<td><%=repas.getDate().format(formatter)%></td>
					<td><%=repas.getHeure()%></td> 
					<td>
					<%if(repas.getListeAliments()!=null && repas.getListeAliments().size()>0){ %>
					<a href="<%=request.getContextPath()%>/repas?id=<%=repas.getIdRepas()%>&dateChoisie<%=request.getParameter("date")%>">voir contenu</a>
					<%} %>
					</td>
					</tr>
					<% if(String.valueOf(repas.getIdRepas()).equals(request.getParameter("id"))&&String.valueOf(repas.getDate()).equals(request.getParameter("dateChoisie"))){ %>
					<tr>
					<td colspan=3>
					<ul>
					<%for(Aliment aliment: repas.getListeAliments()) {%>
					<li><%=aliment.getNom()%></li>
					<%}%>
					</ul>
					</td>
					</tr>
					<%}%>
				
					<%
					}%>
				</tbody>
				</table>
					<%
					if ((String) request.getAttribute("repasParDate") == "false") {
					%>
					<p>aucune repas enregistré à cette date!</p>
					<%
					}}
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