<form name="form1" method="post" action="incluipedido.php">
 <table width="100%" border="0" cellspacing="0">
 <tr bgcolor="#666666"> 
     <td width="10%"><b><font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#FFFFFF">C&oacute;digo</font></b></td>
     <td width="74%"><b><font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#FFFFFF">Pizza</font></b></td>
     <td width="16%"><b><font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#FFFFFF">Valor (R$)</font></b></td>
 </tr>
 <?php
 $linhasini = 1;
 
 while ($linha = mysqli_fetch_assoc($resultado)) {
 $codigo = $linha['codigo'];
 $nome = $linha['nome'];
 $descr = $linha['descricao'];
 $valor = $linha['valor'];
 $check = "cb";
 $check .= $linhasini;
 echo " <tr>";
 echo " <td width='10%'><font face='Verdana, Arial, Helvetica, sans-serif' size='2'>";
 echo $codigo;
 echo " </font></td>";
 echo " <td width='74%' rowspan='2'>";
 echo " <table width='100%' border='0' cellspacing='0'>";
 echo " <tr>";
 echo " <td colspan='2'>";
 echo " <input type='checkbox' name=$check value=$codigo>";
 echo " <font size='2' face='Verdana, Arial, Helvetica, sans-serif'>";
 echo $nome;
 echo " </font></td>";
 echo " </tr>";
 echo " <tr>";
 echo " <td width='8%'>&nbsp;</td>";
 echo " <td width='92%'><font face='Arial, Helvetica, sans-serif'size='2'><i>";
 echo $descr;
 echo " </i></font></td>";
 echo " </tr>";
 echo " </table>";
 echo " </td>";
 echo " <td width='16%'><font size='2' face='Verdana, Arial, Helvetica, sans-serif'>";
 echo $valor;
 echo " </font></td>";
 echo " </tr>";
 echo " <tr>";
 echo " <td width='10%'>&nbsp;</td>";
 echo " <td width='16%'>&nbsp;</td>";
 echo " </tr>";
 $linhasini++;
 }
 ?>
 </table>
 <table width="100%" border="0" cellspacing="0">
     <tr> 
     	<td>&nbsp;</td>
     </tr>
     <tr> 
     	<td>&nbsp;</td>
     </tr>
     <tr> 
     	<td> 
             <input type="submit" name="Submit" value="Incluir no Pedido">
             <input type="reset" name="Submit2" value=" Limpar ">
     	</td>
     </tr>
     <tr>
    	<td>&nbsp;</td>
     </tr>
     <tr>
     	<td>
 			<font face="Geneva, Arial, Helvetica, san-serif" size="2"><a href="index.php"><b>Retorna</b></a></font>
 		</td>
     </tr>
 </table>
</form>