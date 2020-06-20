<?php
function mesextenso($mes) {
 if ($mes == '01') {
 $novomes = 'Janeiro';
 } elseif ($mes == '02') {
 $novomes = 'Fevereiro';
 } elseif ($mes == '03') {

$novomes = 'Março';
 } elseif ($mes == '04') {
 $novomes = 'Abril';
 } elseif ($mes == '05') {
 $novomes = 'Maio';
 } elseif ($mes == '06') {
 $novomes = 'Junho';
 } elseif ($mes == '07') {
 $novomes = 'Julho';
 } elseif ($mes == '08') {
 $novomes = 'Agosto';
 } elseif ($mes == '09') {
 $novomes = 'Setembro';
 } elseif ($mes == '10') {
 $novomes = 'Outubro';
 } elseif ($mes == '11') {
 $novomes = 'Novembro';
 } elseif ($mes == '12') {
 $novomes = 'Dezembro';
 }
 return ($novomes);
}
$dataaux = date("Y-m-d", time());
$dia = substr($dataaux, 8, 2);
$mes = substr($dataaux, 5, 2);
$ano = substr($dataaux, 0, 4);
$mes = mesextenso($mes);
?>
<table width="100%" border="0" cellspacing="0">
 <tr> 
 <td height="91"><img src="imagens/titulo.jpg" width="256" height="99"/></td>
 </tr>
 <tr>
 <td bgcolor="#CCCCCC"> 
 <div align="right"><font size="2" face="Verdana, Arial, Helvetica, sans-
serif"><b><font face="Geneva, Arial, Helvetica, san-serif">

<?php
 echo "$dia de $mes de $ano";
 ?>
 </font></b></font></div>
 </td>
 </tr>
</table>