<?php
require_once 'init.php';
 
// pega os dados do formuário
$nome = isset($_POST['nome']) ? $_POST['nome'] : null;
$data_cadastro = isset($_POST['data_cadastro']) ? $_POST['data_cadastro'] : null;
$data_nascimento = isset($_POST['data_nascimento']) ? $_POST['data_nascimento'] : null;
$cpf = isset($_POST['cpf']) ? $_POST['cpf'] : null;
$rg = isset($_POST['rg']) ? $_POST['rg'] : null;
$cep = isset($_POST['cep']) ? $_POST['cep'] : null;
$uf = isset($_POST['uf']) ? $_POST['uf'] : null;
$cidade = isset($_POST['cidade']) ? $_POST['cidade'] : null;
$bairro = isset($_POST['bairro']) ? $_POST['bairro'] : null;
$rua = isset($_POST['rua']) ? $_POST['rua'] : null;
$numero = isset($_POST['numero']) ? $_POST['numero'] : null;
$complemento = isset($_POST['complemento']) ? $_POST['complemento'] : null;
$telefone = isset($_POST['telefone']) ? $_POST['telefone'] : null;
$email = isset($_POST['email']) ? $_POST['email'] : null;
$observacao = isset($_POST['observacao']) ? $_POST['observacao'] : null;

 
// insere no banco
$PDO = db_connect();
$sql = "INSERT INTO paciente(nome, data_cadastro, data_nascimento, cpf, rg, cep, uf, cidade, bairro, rua, numero, complemento, telefone, email, observacao) 
        VALUES(:nome, :data_cadastro, :data_nascimento, :cpf, :rg, :cep, :uf, :cidade, :bairro, :rua, :numero, :complemento, :telefone, :email, :observacao)";
$stmt = $PDO->prepare($sql);
$stmt->bindParam(':nome', $nome);
$stmt->bindParam(':data_cadastro', $data_cadastro);
$stmt->bindParam(':data_nascimento', $data_nascimento);
$stmt->bindParam(':cpf', $cpf);
$stmt->bindParam(':rg', $rg);
$stmt->bindParam(':cep', $cep);
$stmt->bindParam(':uf', $uf);
$stmt->bindParam(':cidade', $cidade);
$stmt->bindParam(':bairro', $bairro);
$stmt->bindParam(':rua', $rua);
$stmt->bindParam(':numero', $numero);
$stmt->bindParam(':complemento', $complemento);
$stmt->bindParam(':telefone', $telefone);
$stmt->bindParam(':email', $email);
$stmt->bindParam(':observacao', $observacao);

 
// validação
if (empty($nome) || empty($cpf) || empty($email) || empty($telefone))
{
    echo "<script>alert('Preencha todos os campos!');</script>";
    echo "<script>javascript:window.location='cadastropaciente_funcionario.php';</script>";
}
 

if ($stmt->execute())
{
    header('Location: paciente_cadastrados_funcionario.php');
}
else
{
    echo "Erro ao cadastrar";
    print_r($stmt->errorInfo());
}



