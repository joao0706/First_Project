<html>
<head>
<title> Login de Usuário </title>
</head>
<body bgcolor="#FFFFFF" text="#000000" link="#000000" vlink="#000000" alink="#000000">
 <?php
 include_once "cabecalho.php";
 ?>
<form method="POST" action="valida_login.php">
    <label>Login:</label><input type="text" name="login" id="login"><br>
    <label>Senha:</label><input type="password" name="senha" id="senha"><br>
    <input type="submit" value="entrar" id="entrar" name="entrar"><br>
</form>
 <?php
 include "rodape.php";
 ?>
</body>
