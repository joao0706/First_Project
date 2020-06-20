<?php
session_start();
require_once 'init.php'; 
// Verifica se o usuario esta é logado, se não será redirecionado para a pagina de login
if(!isset($_SESSION["usuariologin"]) || !isset($_SESSION["usuariosenha"])){
    header("location: login.php");
    exit;
}
?>
<!DOCTYPE html>
<html lang="pt">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="x-ua-compatible" content="ie=edge">

  <title>OdontoSys | Registrar Login</title>
  <link rel="icon" href="dist/img/perfilOdonto-com-fundo.jpg">

  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
  
  <script type="text/javascript">
		function validar_login(){
			var login = $("#login").val();
			var senha = $("#senha").val();
				
			if(login == ""){
				alert("*Campo login é obrigatorio");
				$("#login").focus();
				return false;
			}if(senha == ""){
				alert("*Campo senha é obrigatorio");
				$("#senha").focus();
				return false;
			}return true;
		}
	</script>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#"><i class="fas fa-bars"></i></a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="indexfuncionario.php" class="nav-link">Página Inicial</a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="suportetecnico_funcionario.php" class="nav-link">Contato Suporte</a>
      </li>
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
                    <a href="login.php" class="nav-link active">
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
    <a href="indexfuncionario.php" class="brand-link">
      <img src="dist/img/perfilOdonto.png" alt="OdontoSys Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">OdontoSys</span>
    </a>
    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar Menu -->
      <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
        <li class="nav-item">
          <a href="calendario_funcionario.php" class="nav-link">
            <i class="nav-icon far fa-calendar-alt"></i>
            <p>Agenda
              <span class="badge badge-info right"></span><!--Notificação -->
            </p>
          </a>
        </li>
        <li class="nav-item">
          <a href="registrar_login_funcionario.php" class="nav-link">
		      <i class="nav-icon fas fa-users"></i>
            <p>Registrar usuários</p>
            </a>
        </li> 
        <li class="nav-item has-treeview">
          <a href="#" class="nav-link">
            <i class="nav-icon fas fa-users"></i>
            <p>Usuários Cadastrados
              <i class="fas fa-angle-left right"></i>
            </p>
          </a>
          <ul class="nav nav-treeview">
            <li class="nav-item">
              <a href="usuarioscadastrados_funcionario.php" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Funcionários</p>
              </a>
            </li>
            <li class="nav-item">
              <a href="paciente_cadastrados_funcionario.php" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Pacientes</p>
              </a>
            </li>
          </ul>
        </li>
      </ul>
    </div>
    <!-- /.sidebar -->
  </aside>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <section class="content">
      <form action="add_registro_funcionario.php" name="registrar_login" method="POST">
        <div class="container-fluid">
          <div class="row">
            <div class="col-md-6 mt-3">
              <div class="card card-primary">
                <div class="card-header">
                  <h3 class="card-title">Registro de Login</h3>
                </div>
                <!-- /.card-header -->
                <div class="card-body">
                  <div class="form-group">
                    <div class="input-group mb-3">
                <input type="text" class="form-control" id="login" name="login" placeholder="Login">
              <div class="input-group-append">
                <div class="input-group-text">
                  <span class="fas fa-envelope"></span>
                </div>
              </div>
            </div>
            <div class="input-group mb-3">
              <input type="password" class="form-control" id="senha" name="senha" placeholder="Senha">
              <div class="input-group-append">
                <div class="input-group-text">
                  <span class="fas fa-lock"></span>
                </div>
              </div>
            </div>
          </div>
          <label>Niveis de acessos:</label>
			<select name="select_niveis_acesso">
				<option>Selecione</option>
  				<?php
					$result_niveis_acessos = "SELECT * FROM tipo_usuario";
					$resultado_niveis_acesso = mysqli_query($conn, $result_niveis_acessos);
					while($row_niveis_acessos = mysqli_fetch_assoc($resultado_niveis_acesso)){ ?>
					<option value="<?php echo $row_niveis_acessos['id']; ?>"><?php echo $row_niveis_acessos['nome']; ?></option> <?php
					}
				?>
			</select>
          <div class="card-footer" style="margin-left: 80%;">
            <button type="submit" value="Cadastrar" class="btn btn-primary" onclick="return validar_login()">Registrar</button>
          </div>  
        </div>  
      </div>
    </div> 
    </div>
    </div>
  </form>
    <!-- /.Fim Formulario -->
  </section>
  </div>
  </div>
  <!-- Main Footer -->
  <footer class="main-footer">
    <div class="float-right d-none d-sm-block">
      <b>Version</b> 0.0.1
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2019-2020 <a>OdontoSys</a>.</strong> All rights reserved.
  </footer>
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
<!-- Summernote -->
<script src="plugins/summernote/summernote-bs4.min.js"></script>
<script>
  $(function () {
    // Summernote
    $('.textarea').summernote()
  })
</script>
</body>
</html>
