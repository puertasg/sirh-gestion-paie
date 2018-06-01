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

<title>Bulletin</title>
</head>
<body>

	<div class="container">

		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/paie/index.html">Accueil</a></li>
				<li class="breadcrumb-item"><a href="../bulletins">Bulletins</a></li>
				<li class="breadcrumb-item active" aria-current="page">Visualiser</li>
			</ol>
		</nav>
		<div class="row">
			<h1>Bulletin de salaire</h1>
		</div>

		<div class="row">
			<div class="ml-auto p-1">
				<div class="row">
					<strong>Période</strong>
				</div>
				<div class="row">
					${bulletinAvecCalcul.bulletin.periode}</div>
			</div>
		</div>

		<div class="row">
			<div class="col-4">
				<div class="row">
					<strong>Entreprise</strong>
				</div>
				<div class="row">
					<span class="text-uppercase">${bulletinAvecCalcul.bulletin.remunerationEmploye.entreprise.denomination}</span>
				</div>
				<div class="row">
					<span>SIRET :
						${bulletinAvecCalcul.bulletin.remunerationEmploye.entreprise.siret}</span>
				</div>
			</div>
		</div>

		<div class="row mt-5">
			<strong>Salaire</strong>
		</div>
		<div class="row">
			<table class="table table-bordered table-striped table-sm">
				<thead>
					<tr>
						<th>Rubriques</th>
						<th>Base</th>
						<th>Taux salarial</th>
						<th>Montant salarial</th>
						<th>Taux patronal</th>
						<th>Cot. patronales</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Salaire de base</td>
						<td>${bulletinAvecCalcul.resultatCalculRemuneration.salaireDeBase}</td>
						<td>${bulletinAvecCalcul.resultatCalculRemuneration.totalTauxSalarial}</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>Prime Except.</td>
						<td></td>
						<td></td>
						<td>${bulletinAvecCalcul.bulletin.primeExceptionnelle}</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>Salaire Brut</td>
						<td></td>
						<td></td>
						<td>${bulletinAvecCalcul.resultatCalculRemuneration.salaireBrut}</td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="row mt-2">
			<strong>Cotisations</strong>
		</div>
		<!-- boucler cotisation non imposables -->
		<div class="row">
			<table class="table table-bordered table-striped table-sm">
				<thead>
					<tr>
						<th>Rubriques</th>
						<th>Base</th>
						<th>Taux salarial</th>
						<th>Montant salarial</th>
						<th>Taux patronal</th>
						<th>Cot. patronales</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cotisationNonImposable"
						items="${bulletinAvecCalcul.bulletin.remunerationEmploye.profilRemuneration.cotisationsNonImposables}">
						<tr>
							<td>${cotisationNonImposable.code}
								${cotisationNonImposable.libelle}</td>
							<td>${bulletinAvecCalcul.resultatCalculRemuneration.salaireBrut}</td>
							<td>${cotisationNonImposable.tauxSalarial}</td>
							<td><c:out
									value="${cotisationNonImposable.tauxSalarial * bulletinAvecCalcul.resultatCalculRemuneration.salaireBrut}"></c:out></td>
							<td>${cotisationNonImposable.tauxPatronal}</td>
							<td><c:out
									value="${cotisationNonImposable.tauxPatronal * bulletinAvecCalcul.resultatCalculRemuneration.salaireBrut}"></c:out></td>
						</tr>
					</c:forEach>
					<tr>
						<td>Total retenue</td>
						<td></td>
						<td></td>
						<td>${bulletinAvecCalcul.resultatCalculRemuneration.totalRetenueSalarial}</td>
						<td></td>
						<td>${bulletinAvecCalcul.resultatCalculRemuneration.totalCotisationsPatronales}</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="row mt-2">
			<strong>NET Imposable :
				${bulletinAvecCalcul.resultatCalculRemuneration.netImposable}</strong>
		</div>
		<!-- boucler cotisation imposables -->
		<div class="row">
			<table class="table table-bordered table-striped table-sm">
				<thead>
					<tr>
						<th>Rubriques</th>
						<th>Base</th>
						<th>Taux salarial</th>
						<th>Montant salarial</th>
						<th>Taux patronal</th>
						<th>Cot. patronales</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cotisationImposable"
						items="${bulletinAvecCalcul.bulletin.remunerationEmploye.profilRemuneration.cotisationsImposables}">
						<tr>
							<td>${cotisationImposable.code}
								${cotisationImposable.libelle}</td>
							<td>${bulletinAvecCalcul.resultatCalculRemuneration.salaireBrut}</td>
							<td>${cotisationImposable.tauxSalarial}</td>
							<td><c:out
									value="${cotisationImposable.tauxSalarial * bulletinAvecCalcul.resultatCalculRemuneration.salaireBrut}"></c:out></td>
							<td>${cotisationImposable.tauxPatronal}</td>
							<td><c:out
									value="${cotisationImposable.tauxPatronal * bulletinAvecCalcul.resultatCalculRemuneration.salaireBrut}"></c:out></td>
						</tr>
					</c:forEach>
					<tr>
						<td>Total retenue</td>
						<td></td>
						<td></td>
						<td>${bulletinAvecCalcul.resultatCalculRemuneration.totalCotisationsImposables}</td>
						<td></td>
						<td>${bulletinAvecCalcul.resultatCalculRemuneration.totalCotisationsPatronalesImposables}</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="row">
			<div class="ml-auto p-1">
				<div class="row">
					<strong>Net à payer :
						${bulletinAvecCalcul.resultatCalculRemuneration.netAPayer}</strong>
				</div>
			</div>
		</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>

	<script
		src="${pageContext.servletContext.contextPath}/resources/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
</body>
</html>