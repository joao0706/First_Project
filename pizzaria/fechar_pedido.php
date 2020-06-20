<?php
// Inicializa a sessão.
if (!isset($_SESSION)) {//Verificar se a sessão não já está aberta.
// Se estiver sendo usado session_name("something"), não esqueça de usá-lo agora!
session_start();
}
// Apaga todas as variáveis da sessão
$_SESSION = array();

// Se é preciso matar a sessão, então os cookies de sessão também devem ser apagados.
// Nota: Isto destruirá a sessão, e não apenas os dados!
if (ini_get("session.use_cookies")) {
    $params = session_get_cookie_params();
    setcookie(session_name(), '', time() - 42000,
        $params["path"], $params["domain"],
        $params["secure"], $params["httponly"]
        );
}
echo "<script>alert('Seu pedido foi solicitado com sucesso!');</script>";
echo "<script>javascript:window.location='index.php';</script>";	
//Apagando todos os dados da sessão:
session_unset();
// Por último, destrói a sessão
session_destroy();
?>