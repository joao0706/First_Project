<?php
require_once 'init.php';
 
// pega os dados do formuário
$nome = isset($_POST['nome']) ? $_POST['nome'] : null;
$data_cadastro = isset($_POST['data_cadastro']) ? $_POST['data_cadastro'] : null;
//$data_cadastro = substr($data_cadastro, 5, 2)."-".substr($data_cadastro, 8, 2)."-".substr($data_cadastro, 0,4);
//data_cadastro: 2020-06-16
//06-16-2020
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

// a data vem no formato dd/mm/YYYY
//converter para YYYY-mm-dd
//$isoDate = dateConvert($data_nascimento);
 
// insere no banco
$PDO = db_connect();
$sql = "INSERT INTO paciente(nome, data_cadastro, data_nascimento, cpf, rg, cep, uf, cidade, bairro, rua, numero, complemento, telefone, email, observacao, descricao_consulta) 
        VALUES(:nome, :data_cadastro, :data_nascimento, :cpf, :rg, :cep, :uf, :cidade, :bairro, :rua, :numero, :complemento, :telefone, :email, :observacao, :descricao_consulta)";
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

 
// validação
if (empty($nome) || empty($cpf) || empty($email) || empty($telefone))
{
    echo "<script>alert('Preencha todos os campos!');</script>";
    echo "<script>javascript:window.location='cadastropaciente.php';</script>";
}
 
if ($stmt->execute())
{
    header('Location: paciente_cadastrados.php');
}
else
{
    echo "Erro ao cadastrar";
    print_r($stmt->errorInfo());
}



