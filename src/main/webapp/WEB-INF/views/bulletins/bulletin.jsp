<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<div class="row">
			<h1>Bulletin de salaire</h1>
		</div>

		<div class="row">
			<div class="ml-auto p-1">
				<div class="row">
					<strong>Période</strong>
				</div>
				<div class="row"><fmt:formatDate
									value="${bulletinAvecCalcul.key.dateCreationToDate()}"
									pattern="dd/MM/yyyy HH:mm" /></div>
			</div>
		</div>

		<div class="row">
			<div class="col-4">
				<div class="row">
					<strong>Entreprise</strong>
				</div>
				<div class="row">
					<span>DEV ENTREPRISE</span>
				</div>
				<div class="row">
					<span>SIRET :</span>
				</div>
			</div>
		</div>

		<div class="row mt-5">
			<strong>Salaire</strong>
		</div>
		<div class="row">
			<table class="table table-bordered table-striped">
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
						<td>base ici</td>
						<td>taux salarial ici</td>
						<td>montant salarial ici</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>Prime Except.</td>
						<td></td>
						<td></td>
						<td>montant salarial ici</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>Salaire Brut</td>
						<td></td>
						<td></td>
						<td>${bulletinAvecCalcul.value.salaireBrut}</td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="row mt-2">
			<strong>Cotisations</strong>
		</div>

		<div class="row">
			<table class="table table-bordered table-striped">
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
					<!-- début for ici -->
					<tr>
						<td>Rubrique ici</td>
						<td>base ici</td>
						<td>taux salarial ici</td>
						<td>montant salarial ici</td>
						<td>taux patronal ici</td>
						<td>cot patronale ici</td>
					</tr>
					<!-- fin for ici -->
					<tr>
						<td>Total retenue</td>
						<td></td>
						<td></td>
						<td>total montant salarial ici</td>
						<td></td>
						<td>total cot patronale ici</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="row mt-2">
			<strong>NET Imposable : XXXX</strong>
		</div>

		<div class="row">
			<table class="table table-bordered table-striped">
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
					<!-- début for ici -->
					<tr>
						<td>Rubrique ici</td>
						<td>base ici</td>
						<td>taux salarial ici</td>
						<td>montant salarial ici</td>
						<td>taux patronal ici</td>
						<td>cot patronale ici</td>
					</tr>
					<!-- fin for ici -->
					<tr>
						<td>Total retenue</td>
						<td></td>
						<td></td>
						<td>total montant salarial ici</td>
						<td></td>
						<td>total cot patronale ici</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="row">
			<div class="ml-auto p-1">
				<div class="row">
					<strong>Net à payer</strong>
				</div>
			</div>
		</div>

		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
			integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
			integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
			crossorigin="anonymous"></script>
</body>
</html>