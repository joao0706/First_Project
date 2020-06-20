<?php

require_once 'init.php';

// resgata os valores do formulário
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
$descricao_consulta = isset($_POST['descricao_consulta']) ? $_POST['descricao_consulta'] : null;
$id = isset($_POST['id']) ? $_POST['id'] : null;

// atualiza o banco
$PDO = db_connect();
$sql = "UPDATE paciente SET nome = :nome, data_cadastro = :data_cadastro, data_nascimento = :data_nascimento, cpf = :cpf, rg = :rg,  cep = :cep, uf = :uf, cidade = :cidade, bairro = :bairro, rua = :rua, numero = :numero, complemento = :complemento, telefone = :telefone, email = :email, observacao = :observacao, descricao_consulta = :descricao_consulta WHERE id = :id";
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
$stmt->bindParam(':descricao_consulta', $descricao_consulta);
$stmt->bindParam(':id', $id, PDO::PARAM_INT);   

if ($stmt->execute())
{
    header('Location: paciente_cadastrados_funcionario.php');
}
else
{
    echo "Erro ao alterar";
    print_r($stmt->errorInfo());
}
?>