<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Choix de couleurs</title>

<%
	String couleurAffiche="black";
	//recherche en session
	//Si la valeur existe en session, écrasez la valeur couleur par défaut
	if(session.getAttribute("couleurSession")!=null)
	{
		couleurAffiche=(String)session.getAttribute("couleurSession");
	}
	System.out.println("couleurAffiche jsp 17 :"+couleurAffiche);
	System.out.println("session.getAttribute(couleurSession) jsp 18 :"+(String)session.getAttribute("couleurSession"));
%>
<style>
body {
  background-color: <%=couleurAffiche%>;
  color: <%=couleurAffiche.equalsIgnoreCase("black")?"white":"black"%>;
  font-family: 'Varela Round', sans-serif;
}
option, select, input{
 font-family: 'Varela Round', sans-serif;
}
</style>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
</head>
<body>
<h1>COULEURS</h1>
				<div class="form-wrapper">
							<form action="<%=request.getContextPath()%>/" method="post">

						<label for="couleurSelectionne"> Selectionnez votre couleur preferé : </label> 
						<select name="couleurSelectionne" id="couleurSelectionne">
						   <%
							String[] listeCouleurs= (String[]) application.getAttribute("listeCouleurs");
							if (listeCouleurs != null && listeCouleurs.length > 0) {
								for (String couleur : listeCouleurs) {
							%>
			  				<option value="<%=couleur%>"<%=couleur.equalsIgnoreCase(couleurAffiche)?"selected":""%>><%=couleur%></option>
			  				<%}
							}%>
  					 </select>
						
						<input type="submit" value="Valider" /> 
										</form>
</body>
</html>