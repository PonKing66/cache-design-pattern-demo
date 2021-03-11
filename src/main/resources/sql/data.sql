CREATE TABLE `tb_user`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT,
    `user_name` varchar(255) NOT NULL,
    `address`   varchar(255) NOT NULL,
    `age`       int(11) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;