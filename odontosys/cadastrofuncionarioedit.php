<?php
require 'init.php';
session_start();

// Verifica se o usuario esta é logado, se não será redirecionado para a pagina de login
if(!isset($_SESSION["usuariologin"]) || !isset($_SESSION["usuariosenha"])){
    header("location: login.php");
    exit;
}

// pega o id da url
$id = isset($_GET['id']) ? (int) $_GET['id'] : null;
// valida o id
if (empty($id)) {
    echo "ID para alteração não definido";
    exit();
}

// busca os dados do usuário a ser editado
$PDO = db_connect();
$sql = "SELECT nome, rg, cpf, cro, email, telefone, tipo_funcionario_id FROM funcionario WHERE id = :id";
$stmt = $PDO->prepare($sql);
$stmt->bindParam(':id', $id, PDO::PARAM_INT);
$stmt->execute();

$usuario = $stmt->fetch(PDO::FETCH_ASSOC);

// se o método fetch() não retornar um array, significa que o ID não corresponde a um usuário válido
if (! is_array($usuario)) {
    echo "<script type='javascript'>alert('Usuário selecionado inválido ou inexistente!');";
    echo "javascript:window.location='usuarioscadastrados.php';</script>";
    exit();
}
?>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<title>OdontoSys | Cadastro Funcionário</title>
<link rel="icon" href="dist/img/perfilOdonto-com-fundo.jpg">

<!-- Font Awesome Icons -->
<link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="dist/css/adminlte.min.css">
<!-- Google Font: Source Sans Pro -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">

	<script type="text/javascript">
		function validar_cadastrofuncionario(){
			var nome = $("#nome").val();
			var email = $("#email").val();
			var cpf = $("#cpf").val();
				
			if(nome == ""){
				alert("*Campo nome é obrigatorio");
				$("#nome").val().focus();
				return false;
			}if(email == ""){
				alert("*Campo email é obrigatorio");
				$("#email").val().focus();
				return false;
			}if(cpf == ""){
				alert("*Campo CPF é obrigatorio");
				$("#cpf").val().focus();
				return false;
			}return true;
		}
	</script>
</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<!-- Navbar -->
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#"><i class="fas fa-bars"></i></a></li>
				<li class="nav-item d-none d-sm-inline-block"><a href="index.php"
					class="nav-link">Página Inicial</a></li>
				<li class="nav-item d-none d-sm-inline-block"><a
					href="suportetecnico.php" class="nav-link">Contato Suporte</a></li>
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
							<li class="nav-item">
								<a href="sair.php" class="nav-link active">
								<i class="fas fa-sign-out-alt nav-icon"></i>
								<p>Sair</p>
								</a>
							</li>
							</ul>
						</ul>              
					</div>
				</li>
			</ul>
		</nav>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Logo -->
			<a href="index.php" class="brand-link"> <img
				src="dist/img/perfilOdonto.png" alt="OdontoSys Logo"
				class="brand-image img-circle elevation-3" style="opacity: .8"> <span
				class="brand-text font-weight-light">OdontoSys</span>
			</a>
			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Sidebar Menu -->
				<ul class="nav nav-pills nav-sidebar flex-column"
					data-widget="treeview" role="menu" data-accordion="false">
					<li class="nav-item"><a href="calendario.php" class="nav-link"> <i
							class="nav-icon far fa-calendar-alt"></i>
							<p>Agenda <span class="badge badge-info right"></span>
								<!--Notificação -->
							</p>
					</a></li>
					<li class="nav-item"><a href="registrar_login.php" class="nav-link">
							<i class="nav-icon fas fa-users"></i>
							<p>Registrar usuários</p>
					</a></li>
					 <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-users"></i>
              <p>Usuários Cadastrados
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="usuarioscadastrados.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Funcionários</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="paciente_cadastrados.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Pacientes</p>
                </a>
              </li>
				</ul>
			</div>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-8">
							<h1 class="m-0 text-dark">
								<b>Cadastro de Usuários - Funcionario</b>
							</h1>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Inicio formulario -->
			<section class="content">
				<form action="edit_funcionario.php" method="POST">
					<div class="container-fluid">
						<div class="row">
							<div class="col-md-6">
								<div class="card card-primary">
									<div class="card-header">
										<h3 class="card-title">Dados Pessoais</h3>
									</div>
									<!-- /.card-header -->
									<div class="card-body">
										<div class="form-group">
											<label for="nome">Nome Completo</label> <input type="text"
												class="form-control" id="nome" name="nome"
												value="<?php echo $usuario['nome'] ?>">
										</div>
										<div class="form-group">
											<label for="email">Email</label> <input type="text"
												class="form-control" id="email" name="email"
												placeholder="exemplo@exemplo.com.br";
												value="<?php echo $usuario['email'] ?>">
										</div>
										<div class="form-group">
											<label for="rg">RG</label> <input type="text"
												class="form-control" id="rg" name="rg"
												placeholder="0.000.000";
												value="<?php echo $usuario['rg'] ?>">
										</div>
										<div class="form-group">
											<label for="cpf">CPF</label> <input type="text"
												class="form-control" id="cpf" name="cpf"
												placeholder="000.000.000-00"
												value="<?php echo $usuario['cpf'] ?>">
										</div>
										<div class="form-group">
											<label for="cro">CRO</label> <input type="text"
												class="form-control" id="cro" name="cro"
												value="<?php echo $usuario['cro'] ?>">
										</div>
										<div class="form-group">
											<label for="telefone">Telefone</label> <input type="text"
												class="form-control" id="telefone" name="telefone"
												value="<?php echo $usuario['telefone'] ?>">
										</div>
								 <label>Tipo de funcionario:</label>

					<select name="select_funcionario">
						<option>Selecione</option>
  					<?php
						$result_funcionarios = "SELECT * FROM tipo_funcionario";
						$resultado_funcionario = mysqli_query($conn, $result_funcionarios);
						while($row_funcionario = mysqli_fetch_assoc($resultado_funcionario)){
							if($usuario['tipo_funcionario_id'] == $row_funcionario['id']){
								echo "<option selected value='".$row_funcionario['id']."'>".$row_funcionario['nome']."</option>";
							}else{
								echo "<option value='".$row_funcionario['id']."'>".$row_funcionario['nome']."</option>";
							}
							
						}
				  	?>
					</select>
								    <div class="card-footer" style="margin-left: 80%;">
                  <input type="hidden" name="id" value="<?php echo $id?>">
                  <input type="submit" value="Alterar" class="btn btn-primary">
								</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</section>
			<!-- /.Fim Formulario -->
		</div>
		<!-- /.content-wrapper -->
	</div>
	</div>
	<!-- Main Footer -->
	<footer class="main-footer">
		<div class="float-right d-none d-sm-block">
			<b>Version</b> 0.0.1
		</div>
		<!-- Default to the left -->
		<strong>Copyright &copy; 2019-2020 <a>OdontoSys</a>.
		</strong> All rights reserved.
	</footer>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->

	<!-- jQuery -->
	<script src="plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="dist/js/demo.js"></script>

	<!--Summernote-->
	<script src="plugins/summernote/summernote-bs4.min.js"></script>
	<script>
  $(function () {
    // Summernote
    $('.textarea').summernote()
  })
</script>
</body>
</html>
