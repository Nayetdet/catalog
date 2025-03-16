CREATE TABLE IF NOT EXISTS `user_permission` (
      `user_id` BIGINT NOT NULL,
      `permission_id` BIGINT NOT NULL,
      PRIMARY KEY (`user_id`, `permission_id`),
      FOREIGN KEY (`user_id`) REFERENCES user (`id`),
      FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
);
