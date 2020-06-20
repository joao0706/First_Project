<?php
require_once 'conexao.php';
require_once 'conexao.inc.php';

// pega o ID da URL
$codigo = isset($_GET['codigo']) ? $_GET['codigo'] : null;

// valida o ID
if (empty($codigo))
{
    echo "ID não informado";
    exit;
}

// remove do banco
$PDO = db_connect();
$sql = "DELETE FROM pizzas WHERE codigo = :codigo";
$stmt = $PDO->prepare($sql);
$stmt->bindParam(':codigo', $codigo, PDO::PARAM_INT);

if ($stmt->execute())
{
    header('Location: cardapio_admin.php');
}
else
{
    echo "Erro ao remover";
    print_r($stmt->errorInfo());
}
?>