<?php
    session_start(); 
 
    unset(
        $_SESSION['usuarioId'],
        $_SESSION['usuariologin'],
        $_SESSION['usuariosenha'],
        $_SESSION['usuarioNiveisAcessoId']
    );
    $_SESSION['logindeslogado'] = "Deslogado com sucesso";
    //redirecionar o usuario para a página de login
    header("Location: index.php");

?> 
