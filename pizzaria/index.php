<?php
session_start();
?>
<?php
include_once "conexao.inc.php";
include_once 'conexao.php';
$totpizza = isset($_SESSION['pizzas']) ? $_SESSION['pizzas'] : null;
$numped = isset($_SESSION['pedido']) ? $_SESSION['pedido'] : null;

if (!isset($totpizza)) {
     $sql = "select * from pizzas";
     $resultado = mysqli_query($conexao, $sql);
     
     if (!$resultado) {
         echo "Não foi possivel executar a consulta ($sql) no banco. Erro: " . 
         mysqli_connect_errno() . ". " . mysqli_connect_error() . ".";
         exit;
     }
     
     $totpizza = mysqli_num_rows($resultado);
     $_SESSION['pizzas'] = $totpizza;
     mysqli_free_result($resultado);
     mysqli_close($conexao);
} 
?>
<html>
 <head>
     <title>Pizzaria do IFSC</title>
     <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
     <link href="./resources/bootstrap/css/bootstrap.css" rel="stylesheet">
     <link href="./resources/bootstrap/js/bootstrap.js" rel="stylesheet">
 </head>
 <body bgcolor="#FFFFFF" text="#000000">
     <?php
     include_once "cabecalho.php";
     ?>
 <table width="100%" border="0" cellspacing="0">
     <tr> 
         <td width="49%"> 
         <table width="99%" border="0" cellspacing="0">
         <tr> 
         <td width="9%">&nbsp;</td>
         <td width="91%">&nbsp;</td>
     </tr>
     <tr> 
         <td width="9%"><a href="cardapio.php">
        	<img src="imagens/bullet.gif" width="55" height="50" border="0"></a>
         </td>
         <td width="91%"><b><font size="2" face="Verdana, Arial,Helvetica, sans-serif">Consulte nosso card&aacute;pio</font></b>
         </td>
     </tr>
 <tr> 
 <td width="9%">&nbsp;</td>
 <td width="91%"><b><font size="2" face="Verdana, Arial, 
Helvetica, sans-serif"></font></b></td>
 </tr>
 <tr> 
 <td width="9%"><a href="efetuapedido01.php">
<img src="imagens/bullet.gif" 
width="55" height="50" border="0"></a></td>
 <td width="91%"><b><font size="2" face="Verdana, Arial, 
Helvetica, sans-serif">Pedidos 
 on-line</font></b></td>
 </tr>
 <tr> 
 <td width="9%">&nbsp;</td>
 <td width="91%">&nbsp;</td>
 </tr>
 <tr> 
 <td width="9%" height="21">&nbsp;</td>
 <td width="91%" height="21">
<font face="Arial, Helvetica, sans-
serif" size="2">Existem 
 <?php echo $totpizza; ?> 
 sabores de pizza em nossa loja. Confira !</font></td>
 </tr>
 </table>
 </td>
 <td width="26%">&nbsp; </td>
 <td width="25%">
 <?php
 if (isset($numped)) {
 include "tabpedidos.php";
 }
 ?>
 </td>
 </tr>
</table>
 <?php
 include_once "rodape.php";
 ?>
 </body>
</html>