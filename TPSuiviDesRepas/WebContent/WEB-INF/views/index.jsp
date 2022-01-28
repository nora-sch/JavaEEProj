<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Accueil</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
<!-- Custom styles for this template -->
<%-- <link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet"/> --%>
<%--   <%@ include file="../../css/style.css"%> --%>
<style>
  <%@ include file="../../css/style.css"%>
  </style> 
</head>
<body>
	<header><h1 class="page-title">SUIVI DES REPAS</h1></header>
	<main>
		<div class="main-wrapper">
		<div class="flex-box-center">
			<a href="<%=request.getContextPath()%>/ajoutrepas"><button class="first-page-button">Ajouter un
				nouveau repas</button></a> <a href="<%=request.getContextPath()%>/repas"><button class="first-page-button">Visualiser
				les repas</button></a>
		</div></div>
	</main>
	<footer> </footer>
</body>
</html>