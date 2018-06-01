<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/bootstrap-4.1.1-dist/css/bootstrap.min.css">

<title>Liste des employés</title>
</head>
<body>

	<div class="container">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/paie/index.html">Accueil</a></li>
				<li class="breadcrumb-item active">Employés</li>
			</ol>
		</nav>
		<div class="row">
			<h1>Employés</h1>
		</div>

		<div class="row">
			<div class="ml-auto p-1">
				<a href="./employes/creer" class="btn btn-primary">Ajouter un
					employé</a>
			</div>
		</div>

		<div class="row">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>Date/heure de création</th>
						<th>Matricule</th>
						<th>Grade</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="listeRemunerationEmploye"
						items="${listeRemunerationEmploye}">
						<tr>
							<td><fmt:formatDate
									value="${listeRemunerationEmploye.dateCreationToDate()}"
									pattern="dd/MM/yyyy HH:mm" /></td>
							<td>${listeRemunerationEmploye.matricule}</td>
							<td>${listeRemunerationEmploye.grade}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>

	<script src="${pageContext.servletContext.contextPath}/resources/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
</body>
</html>