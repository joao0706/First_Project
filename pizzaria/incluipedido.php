<?php
session_start();

include_once "conexao.inc.php";

$nrped = isset($_SESSION["pedido"]) ? $_SESSION["pedido"] : null;

if (isset($nrped)) {
        $sql = "select max(seq) as seq from pedidos where numero=$nrped";
        $resultado = mysqli_query($conexao, $sql);
    if (!$resultado) {
        echo "N�o foi poss�vel executar a consulta ($sql) no banco. Erro: " . 
        mysqli_connect_errno() . ". " . mysqli_connect_error() . ".";
        exit();
    }

    $codigo = mysqli_fetch_array($resultado);
    $seqped = $codigo['seq'];
    $seqped ++;
    mysqli_free_result($resultado);
} else {
    $sql = "select * from ultpedido";
    $resultado = mysqli_query($conexao, $sql);
    
    if (!$resultado) {
        echo "Nao foi possivel executar a consulta ($sql) no banco. Erro: " . 
        mysqli_connect_errno() . ". " . mysqli_connect_error() . ".";
        exit();
    }
    
    $linha = mysqli_fetch_array($resultado);
    $nrped = $linha['pedido'];
    $nrped ++;
    mysqli_free_result($resultado);
    $sql = "update ultpedido set pedido=$nrped";
    $resultado = mysqli_query($conexao, $sql);
    
    if (!$resultado) {
        echo "N�o foi poss�vel executar a consulta ($sql) no banco. Erro: " . 
        mysqli_connect_errno() . ". " . mysqli_connect_error() . ".";
        exit();
    }
    
    $_SESSION["pedido"] = $nrped;
    $seqped = 1;
}

    $indpizza = $_SESSION["pizzas"];
    $indaux = 1;

while ($indaux <= $indpizza) {
    $compo = "cb" . $indaux;
    $compo1 = isset($_POST["$compo"]) ? $_POST["$compo"] : null;
    
    if ($compo1) {
        $sql = "insert into pedidos values ($nrped, $seqped, '$compo1')";
        $resultado = mysqli_query($conexao, $sql);
    if (!$resultado) {
        echo "N�o foi poss�vel executar a consulta ($sql) no banco. Erro: 
        " . mysqli_connect_errno() . ". " . mysqli_connect_error() . ".";
        exit();
    }
    $seqped ++;
    }
    $indaux ++;
}

    mysqli_close($conexao);

    echo "<META HTTP-EQUIV='REFRESH' CONTENT=\"0; URL='meupedido.php'\">";
?>