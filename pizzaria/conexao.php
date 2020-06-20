<?php

$servidor = "localhost";
$usuario = "root";
$senha = "";
$dbname = "pizzaria";

$conn = mysqli_connect($servidor, $usuario, $senha, $dbname);

function db_connect()
{
    $servidor = "localhost";
    $usuario = "root";
    $senha = "";
    $dbname = "pizzaria";
    
    $PDO = new PDO('mysql:host=' . $servidor . ';dbname=' . $dbname . ';charset=utf8', $usuario, $senha);
    return $PDO;
}

if(!$conn){
    die("Falha na conexao: " . mysqli_connect_error());
}else{
    //echo "Conexao realizada com sucesso";
}
?>
