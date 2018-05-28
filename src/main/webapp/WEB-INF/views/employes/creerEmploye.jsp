<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">

<title>Créer Employe</title>
</head>
<body>
	<div class="container">
		<h1>Créer Employe</h1>
		<p>Préfixe Matricule : ${prefixMatricule}</p>
		
		<form:form method="post" modelAttribute="remEmploye">
		
			<div class="form-group row">
                <label for="inputMatricule" class="col-form-label col-4">Matricule</label>
                <div class="col-8 pr-0">
                    <form:input path="matricule" id="inputMatricule" class="form-control" />
                    <div class="invalid-feedback">
                        Le matricule est obligatoire.
                    </div>
                </div>
            </div>

            <div class="form-group row">
                <label for="inputEntreprise" class="col-form-label col-4">Entreprise</label>
                <div class="col-8 pr-0">
                    <form:select path="entreprise" id="inputEntreprise" class="form-control">
                    	<form:options items="${listEntreprise}" itemValue="id"/>
                    </form:select>
                    <div class="invalid-feedback">
                        L'entreprise est obligatoire.
                    </div>
                </div>
            </div>

            <div class="form-group row">
                <label for="inputProfilRemuneration" class="col-form-label col-4">Profil de rémunération</label>
                <div class="col-8 pr-0">
                    <form:select path="profilRemuneration" id="inputProfilRemuneration" class="form-control">
                    	<form:options items="${listProfils}" itemValue="id"/>
                    </form:select>
                    <div class="invalid-feedback">
                        Le profil de rémunération est obligatoire.
                    </div>
                </div>
            </div>

            <div class="form-group row">
                <label for="inputGrade" class="col-form-label col-4">Grade</label>
                <div class="col-8 pr-0">
                    <form:select path="grade" id="inputGrade" class="form-control">
                    	<form:options items="${listGrades}" itemValue="id"/>
                    </form:select>
                    <div class="invalid-feedback">
                        Le grade est obligatoire.
                    </div>
                </div>
            </div>
            
            <form:hidden path="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="row">
                <div class="ml-auto">
                    <input type="submit" id="buttonCreer" class="btn btn-sm btn-success">Créer</button>
                </div>
            </div>
		</form:form>
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