<html>
 <head>
 <title>Cardápio</title>
 <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
 </head>
 <body bgcolor="#FFFFFF" text="#000000" link="#000000" vlink="#000000" 
alink="#000000">
 <?php
 include_once "cabecalho.php";
 ?>
 <table width="83%" border="0" cellspacing="0">
 <tr bgcolor="#999999"> 
 <td width="12%"><b><font size="2" face="Verdana, Arial, Helvetica, sans-
serif">C&oacute;digo</font></b></td>
 <td width="56%"><b><font face="Verdana, Arial, Helvetica, sans-serif" 
size="2">Pizza</font></b></td>
 <td width="32%"><b><font size="2" face="Verdana, Arial, Helvetica, sans-
serif">Valor</font></b></td>

</tr>
 <?php
 include_once "conexao.inc.php";

 $sql = "select * from pizzas order by codigo";
 $resultado = mysqli_query($conexao, $sql);
if (!$resultado) {
 echo "Não foi possível executar a consulta ($sql) no banco. Erro: 
" . mysqli_connect_errno() . ". " . mysqli_connect_error() . ".";
 exit;
 }
 while ($linha = mysqli_fetch_assoc($resultado)) {
 $codigo = $linha['codigo'];
 $pizza = $linha['nome'];
 $descr = $linha['descricao'];
 $valor = $linha['valor'];
 
 echo "<tr bgcolor='#CCCCCC'>";
 echo " <td width='12%'><font face='Verdana, Arial, Helvetica, sans-
serif' size='2'>$codigo</font></td>";
 echo " <td width='56%'><font face='Verdana, Arial, Helvetica, sans-
serif' size='2'>$pizza</font></td>";
 echo " <td width='32%'><font face='Verdana, Arial, Helvetica, sans-
serif' size='2'>$valor</font></td>";
 echo "</tr>";
 echo "<tr bgcolor='#FFFFFF'>";
 echo " <td width='12%'></td>";
 echo " <td width='56%'><font face='Arial, Helvetica, sans-serif' 
size='2'><i>$descr</i></font></td>";
 echo " <td width='32%'></td>";
 
 echo "</tr>";
 
 
 }
 
 mysqli_free_result($resultado);
 mysqli_close($conexao);
 ?>

 </table>
 <table width="100%" border="0" cellspacing="0">
 <tr> 
 <td>&nbsp;</td>
 </tr>
<tr>
 <td><font face="Geneva, Arial, Helvetica, san-serif" size="2"><a 
href="index.php"><b>Retorna</b></a></font></td>
 </tr>
 </table>
 <?php
 include "rodape01.php";
 ?>
 </body>
</html>








