<?php
session_start();
require_once 'conexao.php';

// pega o id da url
$codigo = isset($_GET['codigo']) ? (int) $_GET['codigo'] : null;
// valida o id
if (empty($codigo)) {
    echo "codigo para alteração não definido";
    exit();
}
    
// busca os dados do usuário a ser editado
$PDO = db_connect();
$sql = "SELECT nome, descricao, valor FROM pizzas WHERE codigo = :codigo";
$stmt = $PDO->prepare($sql);
$stmt->bindParam(':codigo', $codigo, PDO::PARAM_INT);
$stmt->execute();

$usuario = $stmt->fetch(PDO::FETCH_ASSOC);

// se o método fetch() não retornar um array, significa que o ID não corresponde a um usuário válido
if (! is_array($usuario)) {
    echo "<script type='javascript'>alert('Pizza selecionado inválido ou inexistente!');";
    echo "javascript:window.location='cardapio_admin.php';</script>";
    exit();
}
?>
<!DOCTYPE html>
<html lang="pt">
<head>

  <meta charset="utf-8">
  </head>
<body bgcolor="#FFFFFF" text="#000000">
     <?php
     include_once "cabecalho.php";
     ?>
     
     <tr bgcolor="#999999"> 
         <td width="39%">
        	<b><font size="2" face="Verdana, Arial, Helvetica, sans-serif">
        	<a href="index.php">Retornar</a></font>
       	 	</b>
       	 </td>
      </tr>
      <tr>
      	<td width="91%">
      		<b><font size="2" face="Verdana, Arial,Helvetica, sans-serif">
      			<a href="cardapio_admin.php">Consulte nosso card&aacute;pio</a></font>
      		</b>
        </td>
      </tr>
	<section class="content">
	<form action="edit_pizzas.php" name="editar_pizzas" method="POST">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6">
					<div class="card card-primary">
						<div class="card-header">
							<h3 class="card-title">Dados</h3>
						</div>
						<!-- /.card-header -->
						<div class="card-body">
							<div class="form-group">
								<label for="nome">Nome</label> <input type="text" class="form-control" id="nome" name="nome" value="<?php echo $usuario['nome'] ?>">
							</div>
							<div class="form-group">
								<label for="descricao">Descricao</label> <input type="text" class="form-control" id="descricao" name="descricao" value="<?php echo $usuario['descricao'] ?>">
							</div>
							<div class="form-group">
								<label for="valor">Valor</label> <input type="text" class="form-control" id="valor" name="valor" value="<?php echo $usuario['valor'] ?>">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="card-footer" style="margin-left: 1%; margin-top: 15px">
			<input type="hidden" name="codigo" value="<?php echo $codigo?>">
			<input type="submit" value="Alterar" class="btn btn-primary"></input>
		</div>
	</form>
</section>
 <?php
 include_once "rodape01.php";
 ?>
</body>
</html>