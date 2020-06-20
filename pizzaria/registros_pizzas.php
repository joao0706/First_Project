<!DOCTYPE html>
<html lang="pt">
<head>
  <?php
session_start();
require_once 'conexao.php';
?>
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
      			<a href="cardapio_admin.php">- Card&aacute;pio</a></font>
      		</b>
        </td>
      </tr>
	<section class="content">
	<form action="add_pizzas.php" name="registrar_pizzas" method="POST">
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
								<label for="codigo">Codigo</label> <input type="text"
									class="form-control" id="codigo" name="codigo">
							</div>
							<div class="form-group">
								<label for="nome">Nome</label> <input type="text"
									class="form-control" id="nome" name="nome">
							</div>
							<div class="form-group">
								<label for="descricao">Descricao</label> <input type="text"
									class="form-control" id="descricao" name="descricao">
							</div>
							<div class="form-group">
								<label for="valor">Valor</label> <input type="text"
									class="form-control" id="valor" name="valor">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="card-footer" style="margin-left: 1%; margin-top: 15px">
			<button type="submit" value="Cadastrar" class="btn btn-primary">Cadastrar</button>
		</div>
	</form>
</section>
 <?php
 include_once "rodape01.php";
 ?>
</body>
</html>