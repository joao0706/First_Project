CREATE database agenda;
use agenda;


CREATE TABLE `contato` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `nome` varchar(50) NOT NULL,
 `telefone` varchar(50) NULL,
 `email` varchar(50) NULL,
 `endereco` TEXT NULL,
`site` varchar(70) NULL,
 PRIMARY KEY (`id`)
);
