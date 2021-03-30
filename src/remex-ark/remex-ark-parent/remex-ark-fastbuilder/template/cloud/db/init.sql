-- Data exporting was unselected.
-- Dumping structure for table demo.t_auth
CREATE TABLE IF NOT EXISTS `t_auth` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `code` varchar(100) DEFAULT NULL COMMENT '权限编号',
  `url` varchar(1000) DEFAULT NULL COMMENT 'URL',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父ID',
  `sort` int(8) DEFAULT NULL COMMENT '排序，只影响平级',
  `is_enabled` int(1) DEFAULT '1' COMMENT '是否启用，0：否；1：是；',
  `is_opw` int(1) DEFAULT '1' COMMENT '是否需要确认码，0：否；1：是；',
  `is_second_opw` int(1) DEFAULT '1' COMMENT '是否需要二次确认码，0：否；1：是；',
  `create_time` timestamp(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(3) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除，0：否；1：是',
  `op_user_id` varchar(32) DEFAULT NULL COMMENT '操作人ID',
  `op_user_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- Data exporting was unselected.
-- Dumping structure for table demo.t_demo
CREATE TABLE IF NOT EXISTS `t_demo` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `create_time` timestamp(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(3) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除，0：否；1：是',
  `op_user_id` varchar(32) DEFAULT NULL COMMENT '操作人ID',
  `op_user_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='demo表';

-- Data exporting was unselected.
-- Dumping structure for table demo.t_department
CREATE TABLE IF NOT EXISTS `t_department` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `create_time` timestamp(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(3) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除，0：否；1：是',
  `op_user_id` varchar(32) DEFAULT NULL COMMENT '操作人ID',
  `op_user_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- Data exporting was unselected.
-- Dumping structure for table demo.t_op_log
CREATE TABLE IF NOT EXISTS `t_op_log` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `op_time` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '操作时间',
  `fn_id` varchar(32) DEFAULT NULL COMMENT '功能ID',
  `fn_code` varchar(50) DEFAULT NULL COMMENT '功能编号',
  `fn_name` varchar(100) DEFAULT NULL COMMENT '功能名称',
  `fn_url` varchar(200) DEFAULT NULL COMMENT '功能URL',
  `fn_full_id` varchar(1000) DEFAULT NULL COMMENT '功能完整ID',
  `fn_full_name` varchar(1000) DEFAULT NULL COMMENT '功能完整名称',
  `request_id` varchar(200) DEFAULT NULL COMMENT '请求ID',
  `req` varchar(5000) DEFAULT NULL COMMENT '请求内容',
  `rsp` varchar(5000) DEFAULT NULL COMMENT '响应内容',
  `result` int(1) DEFAULT '1' COMMENT '操作结果，0：失败；1：成功；',
  `op_user_no` varchar(32) DEFAULT NULL COMMENT '操作员工编号',
  `create_time` timestamp(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(3) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除，0：否；1：是',
  `op_user_id` varchar(32) DEFAULT NULL COMMENT '操作人ID',
  `op_user_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  `caller_ip` varchar(200) DEFAULT NULL COMMENT '调用者IP',
  `caller_ip_address` varchar(1000) DEFAULT NULL COMMENT '调用者IP地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- Data exporting was unselected.
-- Dumping structure for table demo.t_role
CREATE TABLE IF NOT EXISTS `t_role` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `department_id` varchar(32) DEFAULT NULL COMMENT '部门ID',
  `create_time` timestamp(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(3) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除，0：否；1：是',
  `op_user_id` varchar(32) DEFAULT NULL COMMENT '操作人ID',
  `op_user_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职位角色表';

-- Data exporting was unselected.
-- Dumping structure for table demo.t_role_auth
CREATE TABLE IF NOT EXISTS `t_role_auth` (
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `auth_id` varchar(32) DEFAULT NULL COMMENT '权限ID',
  `is_auth_second_opw` int(1) DEFAULT NULL COMMENT '是否有二次确认码权限，0：否；1：是；'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关系表';

-- Data exporting was unselected.
-- Dumping structure for table demo.t_user
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `user_no` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `is_enabled` int(1) DEFAULT '1' COMMENT '是否启用，0：否；1：是；',
  `opw` varchar(100) DEFAULT '1' COMMENT '操作码',
  `department_id` varchar(32) DEFAULT NULL COMMENT '部门ID',
  `create_time` timestamp(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(3) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除，0：否；1：是',
  `op_user_id` varchar(32) DEFAULT NULL COMMENT '操作人ID',
  `op_user_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE IF NOT EXISTS `t_company` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `create_time` timestamp(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(3) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除，0：否；1：是',
  `op_user_id` varchar(32) DEFAULT NULL COMMENT '操作人ID',
  `op_user_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司表';

CREATE TABLE IF NOT EXISTS `t_area` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `code` varchar(50) DEFAULT NULL COMMENT '编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `create_time` timestamp(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(3) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除，0：否；1：是',
  `op_user_id` varchar(32) DEFAULT NULL COMMENT '操作人ID',
  `op_user_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区表';

CREATE TABLE IF NOT EXISTS `t_area_code` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `code` varchar(50) DEFAULT NULL COMMENT '编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `create_time` timestamp(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(3) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除，0：否；1：是',
  `op_user_id` varchar(32) DEFAULT NULL COMMENT '操作人ID',
  `op_user_name` varchar(50) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区号表';