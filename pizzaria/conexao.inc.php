<?php
/**
* Arquivo de conex�o com o MySQL
* @author Alexandre Altair de Melo
* @since 27/10/2011
*/
define('DB_HOST', 'localhost');
define('DB_LOGIN', 'root');
define('DB_PASSWORD', '');
define('DB_SCHEMA', 'pizzaria');

$conexao = mysqli_connect(DB_HOST, DB_LOGIN, DB_PASSWORD, DB_SCHEMA);
 
if (!$conexao) {
    echo "Erro ao conectar com o banco! Erro: " . mysqli_connect_errno() . ". " . 
    mysqli_connect_error() . ".";
    exit();
}
?>

