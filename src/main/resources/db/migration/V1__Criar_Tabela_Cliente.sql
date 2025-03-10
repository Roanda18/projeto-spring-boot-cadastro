
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `data_aniversario` varchar(40) NOT NULL,
  `cpf` varchar(80) NOT NULL,
  `endereco` varchar(12) NOT NULL,
 
  PRIMARY KEY (`id`)
);


