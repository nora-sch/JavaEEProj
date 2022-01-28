<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.javaee.repas.bo.Repas"%>
<%@page import="fr.eni.javaee.repas.bo.Aliment"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page import="fr.eni.javaee.repas.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste de repas</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
<!-- Custom styles for this template -->
<style>
<%@ include file="../../css/style.css"%>
</style>
</head>
<body>
	<header><h1 class="page-title">LISTE DE REPAS</h1></header>
	<main>
		<div class="main-wrapper">
<div class = "flex-box">
			<div class="list-container">
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
				<a href="<%=request.getContextPath()%>/repas?id=<%=repas.getIdRepas()%>">voir contenu</a>
				</td>
				</tr>
				<% 
		/* 		System.out.println(request.getParameter("id"));  */
				if(String.valueOf(repas.getIdRepas()).equals(request.getParameter("id"))){ %>
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
				<%
			List<Integer> listeCodesErreur = (List<Integer>)request.getAttribute("listeCodesErreur");
			if(listeCodesErreur!=null)
			{
		%>
				<p class="error-msg">Erreur, le repas n'a pas pu être affiché :</p>
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
				<%-- 	<%if(repas.getListeAliments()!=null && repas.getListeAliments().size()>0){ %> --%>
					<a href="<%=request.getContextPath()%>/repas?iddate=<%=repas.getIdRepas()+"-"+request.getParameter("date")%>">voir contenu</a>
				<%--  	<%} %>  --%>
					</td>
					</tr>
					<% 
					//TODO !!!!
				 	System.out.println("concat "+String.valueOf(repas.getIdRepas())+"-"+String.valueOf(repas.getDate())); 
					System.out.println("param iddate "+request.getParameter("iddate"));  
					System.out.println("jsp - repas date  " + repas.getDate());
					if((String.valueOf(repas.getIdRepas())+"-"+String.valueOf(repas.getDate())).equals(request.getParameter("iddate"))){ %>
					
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
			
					<%	}else if ((String) request.getAttribute("repasParDate") == "false") {
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