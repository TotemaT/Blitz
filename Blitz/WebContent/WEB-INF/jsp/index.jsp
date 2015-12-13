<body>
	<div class=".container-fluid">
		<nav class="pull-right">
			<ul class="nav nav-pills">
				<li role="presentation">
					<button type="button" id="signin-button"
						class="btn btn-primary btn-lg" data-toggle="modal"
						data-target="#signup-modal">S'inscrire</button>
				</li>
				<li role="presentation">
					<button type="button" id="signup-button"
						class="btn btn-primary btn-lg" data-toggle="modal"
						data-target="#signin-modal">Se Connecter</button>
				</li>
			</ul>
		</nav>

		<!--  SIGN-UP MODAL -->
		<div class="modal fade" id="signup-modal" tabindex="-1" role="dialog"
			aria-labelledby="signup-modal-label">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Annuler">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="signup-modal-label">Inscription</h4>
					</div>
					<div class="modal-body">
						<form class="form" role="form">
							<!-- NICKNAME -->
							<div class="form-group">
								<label class="control-label" for="nickname">Pseudo :</label> <input
									type="text" class="form-control" id="signin-nickname">
							</div>
							<!-- PASSWORD -->
							<div class="form-group">
								<label class="control-label" for="signin-password">Mot
									de Passe :</label> <input type="password" class="form-control"
									id="signin-password">
							</div>
							<!-- REPEAT PASSWORD -->
							<div class="form-group">
								<label class="control-labl" for="signin-repeat-password">R�p�tez
									le mot-de-passe :</label> <input type="password" class="form-control"
									id="signin-repeat-password">
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
						<button type="button" class="btn btn-primary">S'inscrire</button>
					</div>
				</div>
			</div>
		</div>

	</div>

	<script src="lib/js/signup.js"></script>
	<script src="lib/js/signin.js"></script>