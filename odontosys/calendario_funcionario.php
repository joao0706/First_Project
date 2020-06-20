<?php
session_start();
 
// Verifica se o usuario esta é logado, se não será redirecionado para a pagina de login
if(!isset($_SESSION["usuariologin"]) || !isset($_SESSION["usuariosenha"])){
    header("location: login.php");
    exit;
}
?>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Diz ao navegador para responder à largura da tela -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>OdontoSys | Agenda</title>
<link rel="icon" href="dist/img/perfilOdonto-com-fundo.jpg">

<!-- Font Awesome -->
<link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">

<link href='dist/css/core/main.min.css' rel='stylesheet' />
<link href='dist/css/daygrid/main.min.css' rel='stylesheet' />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="dist/css/personalizado.css">

<script src='dist/js/core/main.min.js'></script>
<script src='dist/js/interaction/main.min.js'></script>
<script src='dist/js/daygrid/main.min.js'></script>
<script src='dist/js/core/locales/pt-br.js'></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="dist/js/personalizado.js"></script>

<!-- style -->
<link rel="stylesheet" href="dist/css/adminlte.min.css">
<!-- Google Font: Source Sans Pro -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">
</head>
<body>
        <?php
        if (isset($_SESSION['msg'])) {
            echo $_SESSION['msg'];
            unset($_SESSION['msg']);
        }
        ?>
		<div class="wrapper">
		<!-- Navbar -->
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#"><i class="fas fa-bars"></i></a></li>
				<li class="nav-item d-none d-sm-inline-block"><a href="indexfuncionario.php"
					class="nav-link">Página Inicial</a></li>
				<li class="nav-item d-none d-sm-inline-block"><a
					href="suportetecnicofuncionario.php" class="nav-link">Contato Suporte</a></li>
			</ul>

			<!-- Link usuario lado direito-->
			<ul class="navbar-nav ml-auto">
				<!-- Dropdown Menu -->
				<li class="nav-item dropdown">
					<div class="user-panel d-flex">
						<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
							<ul class="nav-item has-treeview">
								<a href="#" class="nav-link">
									<p>Usuário</p>
								</a>
								<ul class="nav nav-treeview">
									<li class="nav-item"><a href="sair.php" class="nav-link active">
										<i class="fas fa-sign-out-alt nav-icon"></i>
										<p>Sair</p>
									</a></li>
								</ul>
							</ul>
						</ul>
					</div>
				</li>
			</ul>
		</nav>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Brand Logo -->
			<a href="#" class="brand-link"> <img src="dist/img/perfilOdonto.png"
				alt="OdontoSys Logo" class="brand-image img-circle elevation-3"
				style="opacity: .8"> <span class="brand-text font-weight-light">OdontoSys</span>
			</a>

			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Sidebar Menu -->
				<ul class="nav nav-pills nav-sidebar flex-column"
					data-widget="treeview" role="menu" data-accordion="false">
					<li class="nav-item">
						<a href="calendario_funcionario.php" class="nav-link"> <i
							class="nav-icon far fa-calendar-alt"></i>
							<p>Agenda <span class="badge badge-info right"></span></p>
						</a>
					</li>
					<li class="nav-item">
						<a href="registrar_login_funcionario.php" class="nav-link">
						<i class="nav-icon fas fa-users"></i>
						<p>Registrar usuários</p>
						</a>
					</li>
					<li class="nav-item has-treeview">
						<a href="#" class="nav-link"> <i class="nav-icon fas fa-users"></i>
							<p>Usuários Cadastrados <i class="fas fa-angle-left right"></i></p>
							</a>
						<ul class="nav nav-treeview">
							<li class="nav-item">
								<a href="usuarioscadastrados_funcionario.php" class="nav-link">
									<i class="far fa-circle nav-icon"></i>
									<p>Funcionários</p>
								</a>
							</li>
							<li class="nav-item">
								<a href="paciente_cadastrados_funcionario.php" class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Pacientes</p>
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- /.sidebar -->
		</aside>

		<div class="content-wrapper">
			<section class="content">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<div class="container-fluid">
						<div class="row mb-2">
							<div class="col-sm-6">
								<h1>Agenda</h1>
							</div>
							<div class="col-sm-6">
								<ol class="breadcrumb float-sm-right">
									<li class="breadcrumb-item"><a href="indexfuncionario.php">Página Inicial</a></li>
									<li class="breadcrumb-item active">Agenda</li>
								</ol>
							</div>
						</div>
					</div>
					<!-- /.container-fluid -->
				</section>
				
				<div id='calendar'></div>

				<div class="modal fade" id="visualizar" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Detalhes do
									Evento</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<div class="visevent">
									<dl class="row">
										<dt class="col-sm-3">ID do evento</dt>
										<dd class="col-sm-9" id="id"></dd>

										<dt class="col-sm-3">Título do evento</dt>
										<dd class="col-sm-9" id="title"></dd>

										<dt class="col-sm-3">Início do evento</dt>
										<dd class="col-sm-9" id="start"></dd>

										<dt class="col-sm-3">Fim do evento</dt>
										<dd class="col-sm-9" id="end"></dd>
									</dl>
									<button class="btn btn-warning btn-canc-vis">Editar</button>
									<a href="" id="apagar_evento" class="btn btn-danger">Apagar</a>
								</div>
								<div class="formedit">
									<span id="msg-edit"></span>
									<form id="editevent" method="POST"
										enctype="multipart/form-data">
										<input type="hidden" name="id" id="id">
										<div class="form-group row">
											<label class="col-sm-2 col-form-label">Título</label>
											<div class="col-sm-10">
												<input type="text" name="title" class="form-control"
													id="title" placeholder="Título do evento">
											</div>
										</div>
										<div class="form-group row">
											<label class="col-sm-2 col-form-label">Color</label>
											<div class="col-sm-10">
												<select name="color" class="form-control" id="color">
													<option value="">Selecione</option>
													<option style="color: #FFD700;" value="#FFD700">Amarelo</option>
													<option style="color: #0071c5;" value="#0071c5">Azul
														Turquesa</option>
													<option style="color: #FF4500;" value="#FF4500">Laranja</option>
													<option style="color: #8B4513;" value="#8B4513">Marrom</option>
													<option style="color: #1C1C1C;" value="#1C1C1C">Preto</option>
													<option style="color: #436EEE;" value="#436EEE">Royal Blue</option>
													<option style="color: #A020F0;" value="#A020F0">Roxo</option>
													<option style="color: #40E0D0;" value="#40E0D0">Turquesa</option>
													<option style="color: #228B22;" value="#228B22">Verde</option>
													<option style="color: #8B0000;" value="#8B0000">Vermelho</option>
												</select>
											</div>
										</div>
										<div class="form-group row">
											<label class="col-sm-2 col-form-label">Início do evento</label>
											<div class="col-sm-10">
												<input type="text" name="start" class="form-control"
													id="start" onkeypress="DataHora(event, this)">
											</div>
										</div>
										<div class="form-group row">
											<label class="col-sm-2 col-form-label">Final do evento</label>
											<div class="col-sm-10">
												<input type="text" name="end" class="form-control" id="end"
													onkeypress="DataHora(event, this)">
											</div>
										</div>
										<div class="form-group row">
											<div class="col-sm-10">
												<button type="button" class="btn btn-primary btn-canc-edit">Cancelar</button>
												<button type="submit" name="CadEvent" id="CadEvent"
													value="CadEvent" class="btn btn-warning">Salvar</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="modal fade" id="cadastrar" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Cadastrar Evento</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<span id="msg-cad"></span>
								<form id="addevent" method="POST" enctype="multipart/form-data">
									<div class="form-group row">
										<label class="col-sm-2 col-form-label">Título</label>
										<div class="col-sm-10">
											<input type="text" name="title" class="form-control"
												id="title" placeholder="Título do evento">
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-sm-2 col-form-label">Color</label>
										<div class="col-sm-10">
											<select name="color" class="form-control" id="color">
												<option value="">Selecione</option>
												<option style="color: #FFD700;" value="#FFD700">Amarelo</option>
												<option style="color: #0071c5;" value="#0071c5">Azul
													Turquesa</option>
												<option style="color: #FF4500;" value="#FF4500">Laranja</option>
												<option style="color: #8B4513;" value="#8B4513">Marrom</option>
												<option style="color: #1C1C1C;" value="#1C1C1C">Preto</option>
												<option style="color: #436EEE;" value="#436EEE">Royal Blue</option>
												<option style="color: #A020F0;" value="#A020F0">Roxo</option>
												<option style="color: #40E0D0;" value="#40E0D0">Turquesa</option>
												<option style="color: #228B22;" value="#228B22">Verde</option>
												<option style="color: #8B0000;" value="#8B0000">Vermelho</option>
											</select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-2 col-form-label">Início do evento</label>
										<div class="col-sm-10">
											<input type="text" name="start" class="form-control"
												id="start" onkeypress="DataHora(event, this)">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-2 col-form-label">Final do evento</label>
										<div class="col-sm-10">
											<input type="text" name="end" class="form-control" id="end"
												onkeypress="DataHora(event, this)">
										</div>
									</div>
									<div class="form-group row">
										<div class="col-sm-10">
											<button type="submit" name="CadEvent" id="CadEvent"
												value="CadEvent" class="btn btn-success">Cadastrar</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
	<!--Fim content wrapper-->
	<footer class="main-footer">
		<div class="float-right d-none d-sm-block">
			<b>Version</b> 0.0.1
		</div>
		<strong>Copyright &copy; 2019-2020 <a>OdontoSys</a>.
		</strong> All rights reserved.
	</footer>

	<!-- Control Sidebar -->
	<aside class="control-sidebar control-sidebar-dark">
		<!-- Control sidebar content goes here -->
	</aside>
	<!-- /.control-sidebar -->

	</div>
	<!--Fim wrapper-->

	<!-- jQuery -->
	<script src="plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- jQuery UI -->
	<script src="plugins/jquery-ui/jquery-ui.min.js"></script>
	<!-- AdminLTE App -->
	<script src="dist/js/adminlte.min.js"></script>

</body>
</html>
