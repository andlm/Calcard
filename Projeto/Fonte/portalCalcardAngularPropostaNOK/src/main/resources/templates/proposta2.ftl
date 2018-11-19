<!DOCTYPE html>
<html lang="en" ng-app="crudProposta">
<head lang="en">

<title>Calcard - 2018 - Resultado da an&aacute;lise</title>

</head>
<head>
	<title>Calcard - 2018 - Resultado da an&aacute;lise</title>
	<link href="http://localhost:8080/portalCalcardRestService/css/app.css" 	rel="stylesheet" />
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="http://localhost:8080/portalCalcardRestService/css/app.css" rel="stylesheet"/>
</head>
<body>
	<div class="container">

		<script src="http://localhost:8080/portalCalcardRestService/js/lib/angular.min.js" ></script>
        <script src="http://localhost:8080/portalCalcardRestService/js/lib/angular-ui-router.min.js" ></script>
        <script src="http://localhost:8080/portalCalcardRestService/js/lib/localforage.min.js" ></script>
        <script src="http://localhost:8080/portalCalcardRestService/js/lib/ngStorage.min.js"></script>
        <script src="http://localhost:8080/portalCalcardRestService/js/app/app.js"></script>
        <script src="http://localhost:8080/portalCalcardRestService/js/app/PropostaService.js"></script>
        <script src="http://localhost:8080/portalCalcardRestService/js/app/PropostaController.js"></script>

		<div class="jumbotron">

			<div class="panel panel-default">

				<div class="panel-heading">
					<strong>Resultado da an&aacute;lise</strong>
				</div>
				    
				<div class="panel-footer">
					<div class="formcontainer">
						<div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
		            	<div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
						<form ng-submit="ctrl.submit()" name="frmbuscarProposta" class="form-horizontal">
							<div class="form-group row">
								<div class="col-sm-4">
									<label>CPF</label>
									<input type="text" ng-model="ctrl.proposta.cpf" id="cpf" class="form-control input-sm" placeholder="Entre com o cpf" required ng-minlength="3"/>
								</div>
							</div>
							<div class="form-group row">
								<input type="submit"  value="Pesquisar" class="btn btn-primary btn-sm" ng-disabled="frmbuscarProposta.$invalid || frmbuscarProposta.$pristine">
								<a class="btn btn-sm btn-success" id="btnAdicionar" >Adicionar</a>
							</div>
						</form>
					</div>
				</div>
				
				<div class="panel-body">
					<div class="table-responsive">
						<table
							class="table table-sm table-striped table-hover table-bordered">
							<thead>
								<tr>
									<th></th>
									<th>Nome</th>
									<th>Idade</th>
									<th>Sexo</th>
									<th>Estado civil</th>
									<th>Estado</th>
									<th>Dependentes</th>
									<th>Renda Mensal R$</th>
									<th>Resultado análise</th>
									<th>Limite</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="post in ctrl.getPropostas()">
									<td>
										<div class="btn-group pull-right">
											<table>
												<tr>
													<td>
														<td><button type="button" id="btExcluir" ng-click="ctrl.removeProposta(post.id)" class="delete btn btn-sm btn-danger">Excluir</button>
													</td>
												</tr>
											</table>
										</div>
									</td>
									<td>{{post.nome}}</td>
									<td>{{post.idade}}</td>
									<td>{{post.sexo}}</td>
									<td>{{post.estadocivil}}</td>
									<td>{{post.estado}}</td>
									<td>{{post.dependentes}}</td>
									<td>{{post.renda}}</td>
									<td>{{post.resultadoanalise}}</td>
									<td>{{post.limite}}</td>
									
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				
			</div>

		</div>

	</div>

</body>
</html>