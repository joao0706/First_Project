<?php

require_once 'init.php';


// resgata os valoares do formulário
$nome = isset($_POST['nome']) ? $_POST['nome'] : null;
$rg = isset($_POST['rg']) ? $_POST['rg'] : null;
$cpf = isset($_POST['cpf']) ? $_POST['cpf'] : null;
$cro = isset($_POST['cro']) ? $_POST['cro'] : null;
$email = isset($_POST['email']) ? $_POST['email'] : null;
$telefone = isset($_POST['telefone']) ? $_POST['telefone'] : null;
$select_funcionario = isset($_POST['select_funcionario']) ? $_POST['select_funcionario'] : null;
$id = isset($_POST['id']) ? $_POST['id'] : null; 

// a data vem no formato dd/mm/YYYY

// atualiza o banco
$PDO = db_connect();
$sql = "UPDATE funcionario SET nome = :nome, rg = :rg , cpf = :cpf, cro = :cro, email = :email, telefone = :telefone, tipo_funcionario_id = :select_funcionario WHERE id = :id";
$stmt = $PDO->prepare($sql);
$stmt->bindParam(':nome', $nome);
$stmt->bindParam(':rg', $rg);
$stmt->bindParam(':cpf', $cpf);
$stmt->bindParam(':cro', $cro);
$stmt->bindParam(':email', $email);
$stmt->bindParam(':telefone', $telefone);
$stmt->bindParam(':select_funcionario', $select_funcionario);
$stmt->bindParam(':id', $id, PDO::PARAM_INT);   
 
// validação
if (empty($nome) || empty($cpf) || empty($email))
{
    echo "<script>alert('Preencha todos os campos!');</script>";
    echo "<script>javascript:window.location='cadastrofuncionarioedit_funcionario.php';</script>";
}
 
if ($stmt->execute())
{
    header('Location: usuarioscadastrados_funcionario.php');
}
else
{
    echo "Erro ao alterar";
    print_r($stmt->errorInfo());
}
?>