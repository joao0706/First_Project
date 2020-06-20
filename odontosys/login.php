<?php
session_start();
//Incluindo a conexão com banco de dados
include_once("init.php");
?>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>OdontoSys | Login</title>
  <link rel="icon" href="dist/img/perfilOdonto-com-fundo.jpg">

  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- icheck bootstrap -->
  <link rel="stylesheet" href="plugins/icheck-bootstrap/icheck-bootstrap.min.css">
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
				} return true;
			}
		</script>
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="#"><b>OdontoSys</b></a>
  </div>
  <!-- /.login-logo -->
 
  <div class="card">
    <div class="card-body login-card-body">
     <p class="login-box-msg">Faça o login para iniciar a sessão</p>

      <form method="post" action="validalogin.php">
        <div class="input-group mb-3">
          <input type="text" class="form-control" name="login" id="login" placeholder="Login">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-envelope"></span>
            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="password" class="form-control" name="senha" id="senha" placeholder="Senha">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
        </div>
        <table>
          <tr>
            <th>
              <div class="col-4" style="margin-left: 5px;">
                 <button type="submit" value="Acessar" class="btn btn-primary" onclick="return validar_login()">Acessar</button>
              </div>
            </th>
          </tr>  
        </table>
        <!-- /.col -->
      </form>
      <p class="text-conter text-danger text-center">
        <?php 
          //Recuperando o valor da variável global, os erro de login.
          if(isset($_SESSION['loginErro'])){
            echo $_SESSION['loginErro'];
            unset($_SESSION['loginErro']);
        }?>
      </p>        
      <p >
        <?php if (isset($_SESSION['logindeslogado'])) { ?>
          <script>alert('<?= $_SESSION['logindeslogado'] ?>');</script>
        <?php } ?>
      </p>
    </div>
  </div>
</div>   
<!-- /.login-box -->

<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
</body>
</html>
