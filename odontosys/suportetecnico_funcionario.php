<?php
session_start();
 
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

  <title>OdontoSys | Suporte Técnico</title>
  <link rel="icon" href="./dist/img/perfilOdonto-com-fundo.jpg">

  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
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
        <a href="suporteTecnico_funcionario.php" class="nav-link">Contato Suporte</a>
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
                  <p>
                    Usuário
                  </p>
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
    <a href="indexfuncionario" class="brand-link">
      <img src="dist/img/perfilOdonto.png" width="40px" height="45px" alt="OdontoSys Logo" class="brand-image img-circle elevation-3"
           style="opacity: .8">
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
          <!-- /.sidebar-menu -->
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
          <div class="col-sm-10" style="text-align: center;">
            <h1 class="m-0 text-dark"><b>OdontoSys - Sistema para gerencimento de clínica odontológica</b></h1>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->
    <br>
    <hr/>
    <div class="jumbotron">
      <div class="container">
      	<br />
        <h1>Suporte Técnico</h1>
        <br>
        <p>
          A OdontoSys  disponibliza um suporte para solucionar eventuais problemas de funcionalidade ou dúvidas relacionadas ao sistema.
          Dessa forma, podemos auxiliá-lo nas configurações, manutenção e customizações necessárias
          pensando no melhor para o seu negócio. Nosso atedimento é realizado através de atendimento telefônico, ou conexões e acessos remotos realizado por nossos técnicos.
        </p>
      </div>
    </div>
    <br>
    <hr>
    <div class="container"><!-- container -->
      <!-- Criação de colunas -->
      <div class="row">
        <div class="col">
          <h2>Suporte Técnico Gratuito para os usuários da OdontoSys</h2>
          <p>Você pode contar com um suporte especializado para melhor atende-lo. </p>
        </div>
        
        <div class="col-md-4">
          <h2>Entre em Contato</h2>
         
          <div class="col-md-7">
            <i class="fas fa-envelope display-5 fa-2x"></i>
            <br />
            <p>suporteodontosys@gmail.com</p>
            <i class="fab fa-whatsapp-square display-5 fa-2x"></i> 
            <br />
            <p>
              (47)9 8825-0981
              <br>
              (47)9 9239-2208 
            </p>
          </div>
        </div>
      </div>
    </div>
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


<!-- REQUIRED SCRIPTS -->
<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
</body>
</html>