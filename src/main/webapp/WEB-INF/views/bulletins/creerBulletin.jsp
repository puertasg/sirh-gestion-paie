<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="/bootstrap-4.1.1-dist/css/bootstrap.min.css">

<title>Créer Bulletin de Salaire</title>
</head>
<body>
	<div class="container">
		<h1>Créer Bulletin de Salaire</h1>

		<form:form method="post" modelAttribute="bulletin">

			<div class="form-group row">
				<label for="inputPeriode" class="col-form-label col-4">Periode</label>
				<div class="col-8 pr-0">
					<form:select path="periode.id" items="${listPeriodes}"
						itemValue="id" id="inputPeriode" class="form-control"></form:select>
					<div class="invalid-feedback">La période est obligatoire.</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="inputMatricule" class="col-form-label col-4">Matricule</label>
				<div class="col-8 pr-0">
					<form:select path="remunerationEmploye.id"
						items="${listeRemunerationEmploye}" itemValue="id"
						id="inputMatricule" class="form-control"></form:select>
					<div class="invalid-feedback">Le matricule est obligatoire.</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="inputPrimeExceptionnelle" class="col-form-label col-4">Prime
					exceptionelle</label>
				<div class="col-8 pr-0">
					<form:input path="primeExceptionnelle"
						id="inputPrimeExceptionnelle" class="form-control" />
					<div class="invalid-feedback">La prime exceptionnelle est
						obligatoire.</div>
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

	<script src="/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
</body>
</html>