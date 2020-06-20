<?php
session_start();
require_once 'conexao.php';
require_once 'rodape.php';


// resgata os valoares do formulário
$nome = isset($_POST['nome']) ? $_POST['nome'] : null;
$descricao = isset($_POST['descricao']) ? $_POST['descricao'] : null;
$valor = isset($_POST['valor']) ? $_POST['valor'] : null;
$codigo = isset($_POST['codigo']) ? $_POST['codigo'] : null; 

// atualiza o banco
$PDO = db_connect();
$sql = "UPDATE pizzas SET nome = :nome, descricao = :descricao , valor = :valor WHERE codigo = :codigo";
$stmt = $PDO->prepare($sql);
$stmt->bindParam(':nome', $nome);
$stmt->bindParam(':descricao', $descricao);
$stmt->bindParam(':valor', $valor);
$stmt->bindParam(':codigo', $codigo, PDO::PARAM_INT);   

if ($stmt->execute())
{
    header('Location: cardapio_admin.php');
}
else
{
    echo "Erro ao alterar";
    print_r($stmt->errorInfo());
}
?>