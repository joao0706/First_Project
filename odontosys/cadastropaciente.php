<?php
session_start();
require 'init.php';

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

  <title>OdontoSys | Cadastro Paciente</title>
  <link rel="icon" href="dist/img/perfilOdonto-com-fundo.jpg">

  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">

  <script>
    function getDadosPorCEP(cep){
      let url = 'https://viacep.com.br/ws/'+cep+'/json/unicode/'

      let xmlHTTP = new XMLHttpRequest()
      xmlHTTP.open('GET', url)

      xmlHTTP.onreadystatechange = () =>{
        if(xmlHTTP.readyState == 4 && xmlHTTP.status == 200){
          let dadosJSONText = xmlHTTP.responseText
          let dadosJSONObj = JSON.parse(dadosJSONText)
          
          document.getElementById('rua').value = dadosJSONObj.logradouro
          document.getElementById('bairro').value = dadosJSONObj.bairro
          document.getElementById('cidade').value = dadosJSONObj.localidade
          document.getElementById('uf').value = dadosJSONObj.uf
        }
      }
      xmlHTTP.send()
    }
  </script>
    <script type="text/javascript">
			function validar_cadastropaciente(){
				var nome = $("#nome").val();
				var email = $("#email").val().value;
				var cpf = $("#cpf").val().value;
				var telefone = $("#telefone").val().value;
				
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
				}if(telefone == ""){
					alert("Campo telefone é obrigatorio");
					$("#telefone").val().focus();
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
        <a href="index.php" class="nav-link">Página Inicial</a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="suportetecnico.php" class="nav-link">Contato Suporte</a>
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
    <a href="index.php" class="brand-link">
      <img src="dist/img/perfilOdonto.png" alt="OdontoSys Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">OdontoSys</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar Menu -->
      <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
        <li class="nav-item">
          <a href="calendario.php" class="nav-link">
          <i class="nav-icon far fa-calendar-alt"></i>
          <p>Agenda
          <span class="badge badge-info right"></span><!--Notificação -->
          </p>
          </a>
        </li>  
        <li class="nav-item">
          <a href="registrar_login.php" class="nav-link">
				  <i class="nav-icon fas fa-users"></i>
          <p>Registrar usuários</p>
          </a>
        </li>
			  <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-users"></i>
              <p>
                Usuários Cadastrados
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
          <div class="col-sm-8">
            <h1 class="m-0 text-dark">
              <b>Cadastro de Usuários - Paciente</b>
            </h1>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Inicio formulario -->
    <section class="content">
      <form action="add_paciente.php" name="cadastropaciente" method="POST">
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
                      <label for="nome">Nome Completo</label>
                      <input type="text" class="form-control" id="nome" name="nome">
                    </div>
                     <div class="form-group">  
                      <label for="data_cadastro">Data de cadastro</label>
                      <input type="date" class="form-control" id="data_cadastro" name="data_cadastro">
                    </div>
                    <div class="form-group">  
                      <label for="data_nascimento">Data de Nascimento</label>
                      <input type="date" class="form-control" id="data_nascimento" name="data_nascimento">
                    </div>
                     <div class="form-group">  
                      <label for="cpf">CPF</label>
                      <input type="text" class="form-control" id="cpf" name="cpf" placeholder="000.000.000-00">	
                    </div>
                     <div class="form-group">  
                      <label for="rg">RG</label>
                      <input type="text" class="form-control" id="rg" name="rg" placeholder="0.000.000">	
                    </div>
                  </div>
                  <!-- /.card-body -->
              </div>
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Endereço</h3>
              </div>
              <!-- /.card-header -->
                <div class="card-body">
                  <div class="form-group">
                    <label for="cep">CEP</label>
                    <input type="text" class="form-control" id="cep" name="cep" placeholder="00000-000" onblur="getDadosPorCEP(this.value)">
                  </div>
                  <div class="form-group">  
                    <label for="uf">UF</label>
                    <input type="text" class="form-control" id="uf" name="uf"  placeholder="UF">
                  </div>
                  <div class="form-group">  
                    <label for="cidade">Cidade</label>
                    <input type="text" class="form-control" id="cidade" name="cidade">
                  </div>
                  <div class="form-group">  
                    <label for="bairro">Bairro</label>
                    <input type="text" class="form-control" id="bairro" name="bairro">
                  </div>
                  <div class="form-group">
                    <label for="rua">Rua</label>
                    <input type="text" class="form-control" id="rua" name="rua">
                  </div>
                  <div class="form-group">
                    <label for="numero">Número</label>
                    <input type="text" class="form-control" id="numero" name="numero">
                  </div>
                  <div class="form-group">
                    <label for="complemento">Complemento</label>
                    <input type="text" class="form-control" id="complemento" name="complemento">
                  </div>
                </div>
                <!-- /.card-body -->
            </div>

            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Contato</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <div class="form-group">
                  <label for="telefone">Telefone</label>
                  <input type="number" class="form-control" id="telefone" name="telefone" placeholder="(00)90000-0000">

                </div>
                <div class="form-group">  
                  <label for="email">Email</label>
                  <input type="text" class="form-control" id="email" name="email" placeholder="exemplo@exemplo.com.br">
                </div>
              </div>
              <!-- /.card-body -->
            </div>
           <!-- Inicio observações - textArea -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Observação</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body pad">
                <div class="mb-3">
                  <input class="text" id="observacao" name="observacao"
                    style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></input>
                </div>
                <p class="text-sm mb-0">
                  Documente as informações necessárias sobre o cadastro.
                </p>
              </div> 
            </div>
            <!--fim observações-->
            
            <!-- Inicio Descrição - textArea -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Descricão da consulta</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body pad">
                <div class="mb-3">
                  <input class="text" id="descricao_consulta" name="descricao_consulta"
                    style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></input>
                </div>
              </div> 
            </div>
            <!--fim Descrição-->
                        
          <div class="card-footer" style="margin-left: 80%;">
            <button type="submit" value="Cadastrar" class="btn btn-primary" onclick="return validar_cadastropaciente()">Registrar</button>
          </div>
      </form>
      <!-- /.Fim Formulario -->
    </section>
  </div>
  <!-- /.content-wrapper -->
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
