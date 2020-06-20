<?php
session_start();
include_once 'conexao.php';


$login = $_POST['login'];
$entrar = $_POST['entrar'];
$senha = md5($_POST['senha']);
//$connect = mysql_connect('localhost','root','');
//$db = mysql_select_db('pizzaria');
if (isset($entrar)) {
    //Buscar na tabela usuario o usuário que corresponde com os dados digitado no formulário
    $result_usuario = "SELECT * FROM usuario WHERE login = '$login' AND senha = '$senha'";
    $resultado_usuario = mysqli_query($conn, $result_usuario);
    $resultado = mysqli_fetch_assoc($resultado_usuario);
    
    if (isset($resultado)){
        echo"<script language='javascript' type='text/javascript'>
        alert('Login e/ou senha incorretos');window.location
        .href='login.php';</script>";
        die();
    }else{
        setcookie("login",$login);
        header("Location:registros_pizzas.php");
    }
}
?>