<?php
include_once "conexao.inc.php";
include_once 'conexao.php';

$valortotal = 0;

$nrped = isset($_SESSION["pedido"]) ? $_SESSION["pedido"] : null;

$sql = "select a.nome, a.valor from PIZZAS a, PEDIDOS b where a.codigo = b.codigo and b.numero=$nrped";
$resultado = mysqli_query($conexao, $sql);

if (!$resultado) {
    echo "Nao foi possivel executar a consulta ($sql) no banco. Erro: " . 
    mysqli_connect_errno() . ". " . mysqli_connect_error() . ".";
    exit;
}
while ($linha = mysqli_fetch_assoc($resultado)) {
    $nompizza = $linha['nome'];
    $valorpizza = $linha['valor'];
    
    $valortotal += $valorpizza;
    echo "<tr>";
    echo " <td><font size='2' face='Arial, Helvetica, sans-serif'>$nompizza</font></td>";
    echo " <td><font size='2' face='Arial, Helvetica, sans-serif'>$valorpizza</font></td>";
    echo "</tr>";
}
mysqli_free_result($resultado);
mysqli_close($conexao);

echo "<tr> ";
echo " <td><font size='2' face='Arial, Helvetica, sans-serif'></font></td>";
echo " <td><font size='2' face='Arial, Helvetica, sans-serif'></font></td>";
echo "</tr>";
echo "<tr bgcolor='#CCCCCC'>";
echo " <td><b><font size='2' face='Arial, Helvetica, sans-serif'>Total</font></b></td>";
echo " <td><b><font size='2' face='Arial, Helvetica, sans-serif'>$valortotal</font></b></td>";
echo "</tr>";
?>