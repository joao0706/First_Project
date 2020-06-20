<?php 
session_start();
require_once 'init.php';

$login = $_POST['login'];
$senha = $_POST['senha'];
$select_niveis_acesso = $_POST['select_niveis_acesso'];

//echo "$nome_usuario - $email_usuario";

$result_usuario = "INSERT INTO usuario(login, senha, tipo_usuario_id) VALUES ('$login', '$senha', '$select_niveis_acesso')";
$resultado_usuario = mysqli_query($conn, $result_usuario);

// validação
if (empty($login) || empty($senha) || empty($select_niveis_acesso))
{
    echo "<script>alert('Preencha todos os campos!');</script>";
    echo "<script>javascript:window.location='registrar_login_funcionario.php';</script>";
}

if(mysqli_affected_rows($conn) != 0){
    echo "<script>alert('Registro de login enviado com Sucesso!');</script>";
    echo "<script>javascript:window.location='registrar_login_funcionario.php';</script>";				
}else{   
    echo "Erro ao cadastrar";
    print_r($stmt->errorInfo());
}
?>