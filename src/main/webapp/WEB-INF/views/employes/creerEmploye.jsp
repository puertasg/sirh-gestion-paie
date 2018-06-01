<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/bootstrap-4.1.1-dist/css/bootstrap.min.css">

<c:if test="${param.errorMatricule == 'error'}">
	<link rel="stylesheet"
		href="${pageContext.servletContext.contextPath}/resources/css/styleErrors.css">
</c:if>

<title>Créer Employé</title>
</head>
<body>
	<div class="container">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/paie/index.html">Accueil</a></li>
				<li class="breadcrumb-item"><a href="../employes">Employés</a></li>
				<li class="breadcrumb-item active" aria-current="page">Créer</li>
			</ol>
		</nav>
		<h1>Créer Employe</h1>

		<form:form method="post" modelAttribute="remEmploye">

			<div class="form-group row">
				<label for="inputMatricule" class="col-form-label col-4">Matricule</label>
				<div class="col-8 pr-0">
					<form:input path="matricule" id="inputMatricule"
						class="form-control" />
					<div id="invalidMatricule" class="invalid-feedback">Le
						matricule n'a pas été trouvé.</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="inputEntreprise" class="col-form-label col-4">Entreprise</label>
				<div class="col-8 pr-0">
					<form:select path="entreprise.id" items="${listEntreprise}"
						itemValue="id" id="inputEntreprise" class="form-control"></form:select>
					<div class="invalid-feedback">L'entreprise est obligatoire.</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="inputProfilRemuneration" class="col-form-label col-4">Profil
					de rémunération</label>
				<div class="col-8 pr-0">
					<form:select path="profilRemuneration.id" items="${listProfils}"
						itemValue="id" id="inputProfilRemuneration" class="form-control"></form:select>
					<div class="invalid-feedback">Le profil de rémunération est
						obligatoire.</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="inputGrade" class="col-form-label col-4">Grade</label>
				<div class="col-8 pr-0">
					<form:select path="grade.id" items="${listGrades}" itemValue="id"
						id="inputGrade" class="form-control"></form:select>
					<div class="invalid-feedback">Le grade est obligatoire.</div>
				</div>
			</div>

			<div class="row">
				<div class="ml-auto">
					<input type="submit" id="buttonCreer"
						class="btn btn-sm btn-success" value="Créer">
					</button>
				</div>
			</div>
		</form:form>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>

	<script src="${pageContext.servletContext.contextPath}/resources/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>

</body>
</html>