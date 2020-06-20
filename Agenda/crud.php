<?php
const SERVIDOR = "localhost:3306";
const BANCO = "agenda";
const USUARIO = "root";
const SENHA = "";
if (! isset ( $_POST ['acao'] )) {
print json_encode ( 0 );
return;
}
switch ($_POST ['acao']) {
case 'buscar_contato' :
$contato = new stdClass ();
$contato = json_decode ( $_POST ['contato'] );
try {
$sql = "select * from contato where id = ?";
$conexao = new PDO ( "mysql:host=" . SERVIDOR . ";dbname=" . BANCO, USUARIO, SENHA );
$pre = $conexao->prepare ( $sql );
$pre->execute ( array (
$contato->id
) );
print json_encode ( $pre->fetchAll ( PDO::FETCH_ASSOC ) );
} catch ( PDOException $e ) {
echo "Erro!: " . $e->getMessage () . "<br>";
die ();
} finally {
$conexao = null;
}
break;
case 'listar_contato' :
try {
$sql = "select * from contato";
$conexao = new PDO ( "mysql:host=" . SERVIDOR . ";dbname=" . BANCO, USUARIO, SENHA );
$pre = $conexao->prepare ( $sql );
$pre->execute ();
print json_encode ( $pre->fetchAll ( PDO::FETCH_ASSOC ) );
} catch ( PDOException $e ) {
echo "Erro!: " . $e->getMessage () . "<br>";
die ();
} finally {
$conexao = null;
}
break;
case 'adicionar_contato' :
$contato = new stdClass ();
$contato = json_decode ( $_POST ['contato'] );
try {
$sql = "insert into contato(nome, telefone, email, endereco, site) VALUES (?, ?, ?, ?, ?) ";
$conexao = new PDO ( "mysql:host=" . SERVIDOR . ";dbname=" . BANCO, USUARIO, SENHA );
$pre = $conexao->prepare ( $sql );
$pre->execute ( array (
$contato->nome,
$contato->telefone,
$contato->email,
$contato->endereco,
$contato->site
) );
print json_encode ( $conexao->lastInsertId () );
} catch ( PDOException $e ) {
echo "Erro!: " . $e->getMessage () . "<br>";
die ();
} finally {
$conexao = null;
}
break;
case 'excluir_contato' :
$contato = new stdClass ();
$contato = json_decode ( $_POST ['contato'] );
try {
$sql = "delete from contato where id=? ";
$conexao = new PDO ( "mysql:host=" . SERVIDOR . ";dbname=" . BANCO, USUARIO, SENHA );
$pre = $conexao->prepare ( $sql );
$pre->execute ( array (
$contato->id
) );
print json_encode ( 1 );
} catch ( PDOException $e ) {
echo "Erro!: " . $e->getMessage () . "<br>";
die ();
} finally {
$conexao = null;
}
break;
case 'editar_contato' :
$contato = new stdClass ();
$contato = json_decode ( $_POST ['contato'] );
try {
$sql = "update contato set nome = ?, telefone = ?, email = ?, endereco = ?, site = ? where id = ? ";
$conexao = new PDO ( "mysql:host=" . SERVIDOR . ";dbname=" . BANCO, USUARIO, SENHA );
$pre = $conexao->prepare ( $sql );
$pre->execute ( array (
$contato->nome,
$contato->telefone,
$contato->email,
$contato->endereco,
$contato->site,
$contato->id
) );
print json_encode ( 1 );
} catch ( PDOException $e ) {
echo "Erro!: " . $e->getMessage () . "<br>";
die ();
} finally {
$conexao = null;
}
break;
}
exit ();
?>