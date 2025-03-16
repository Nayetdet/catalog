CREATE TABLE IF NOT EXISTS `permission` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `description` varchar(255) NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
