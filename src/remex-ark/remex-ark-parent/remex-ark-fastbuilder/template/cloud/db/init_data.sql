INSERT INTO `t_role` (`id`, `name`, `department_id`, `create_time`, `update_time`, `is_delete`, `op_user_id`, `op_user_name`) VALUES
	('bef774c73e044a9a84f8ee74277b4976', 'admin', NULL, '2020-08-20 15:04:02.000', '2020-08-20 15:04:02.000', 0, '7a1e401fa61a4eb8b210d2153d74ce9a', 'admin');
	
INSERT INTO `t_user` (`id`, `name`, `user_no`, `username`, `password`, `role_id`, `role_name`, `is_enabled`, `opw`, `department_id`, `create_time`, `update_time`, `is_delete`, `op_user_id`, `op_user_name`) VALUES
	('7a1e401fa61a4eb8b210d2153d74ce9a', 'admin', 'admin', 'admin', '$2a$10$.Q500cFK8E5VDVuF/aO2qeFYqudarcco82H0UJS1eidfnFIb0gMSG', 'bef774c73e044a9a84f8ee74277b4976', 'admin', 1, '1', NULL, '2020-08-20 15:04:02.000', '2020-08-20 15:04:02.000', 0, '7a1e401fa61a4eb8b210d2153d74ce9a', 'admin');