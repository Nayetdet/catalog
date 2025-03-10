CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(300) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` decimal(18,2) NOT NULL,
  PRIMARY KEY (`id`)
);