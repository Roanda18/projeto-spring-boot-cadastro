CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome_usuario` varchar(255) DEFAULT NULL,
  `nome_completo` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `conta_nao_expirada` bit(1) DEFAULT NULL,
  `conta_nao_bloqueada` bit(1) DEFAULT NULL,
  `credencial_nao_expirada` bit(1) DEFAULT NULL,
  `ativo` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_nome_usuario` (`nome_usuario`)
) ENGINE=InnoDB;