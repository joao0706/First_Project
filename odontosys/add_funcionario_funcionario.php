<?php
require_once 'init.php';

// pega os dados do formuário
$nome = isset($_POST['nome']) ? $_POST['nome'] : null;
$rg = isset($_POST['rg']) ? $_POST['rg'] : null;
$cpf = isset($_POST['cpf']) ? $_POST['cpf'] : null;
$cro = isset($_POST['cro']) ? $_POST['cro'] : null;
$email = isset($_POST['email']) ? $_POST['email'] : null;
$telefone = isset($_POST['telefone']) ? $_POST['telefone'] : null;
$select_funcionario = isset($_POST['select_funcionario']) ? $_POST['select_funcionario'] : null;

// insere no banco
$PDO = db_connect();

$sql = "INSERT INTO funcionario(nome, rg, cpf, cro, email, telefone, tipo_funcionario_id)
        VALUES(:nome, :rg, :cpf, :cro, :email,:telefone, :select_funcionario)";
$stmt = $PDO->prepare($sql);
$stmt->bindParam(':nome', $nome);
$stmt->bindParam(':rg', $rg);
$stmt->bindParam(':cpf', $cpf);
$stmt->bindParam(':cro', $cro);
$stmt->bindParam(':email', $email);
$stmt->bindParam(':telefone', $telefone);
$stmt->bindParam(':select_funcionario', $select_funcionario);

 
// validação
if (empty($nome) || empty($cpf) || empty($email) || empty($telefone))
{
    echo "<script>alert('Preencha todos os campos!');</script>";
    echo "<script>javascript:window.location='cadastrofuncionario_funcionario.php';</script>";
}
 


if ($stmt->execute())
{
    header('Location: usuarioscadastrados_funcionario.php');
}
else
{
    echo "Erro ao cadastrar";
    print_r($stmt->errorInfo());
}