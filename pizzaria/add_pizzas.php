 <?php 
session_start();
require_once 'conexao.php';
require_once 'rodape.php';

$codigo = $_POST['codigo'];
$nome = $_POST['nome'];
$descricao = $_POST['descricao'];
$valor = $_POST['valor'];


$result_usuario = "INSERT INTO pizzas(codigo, nome, descricao, valor) VALUES ('$codigo', '$nome', '$descricao', '$valor')";
$resultado_usuario = mysqli_query($conn, $result_usuario);
    
if(mysqli_affected_rows($conn) != 0){
    echo "<script>alert('Registro de pizzas enviado com Sucesso!');</script>";
    echo "<script>javascript:window.location='registros_pizzas.php';</script>";
}else{
    //echo "Erro ao cadastrar";
    //print_r($stmt->errorInfo());
}
?>