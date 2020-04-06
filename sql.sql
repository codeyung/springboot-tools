CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL DEFAULT '' COMMENT '姓名',
  `age` tinyint(4) unsigned NOT NULL COMMENT '年龄',
  `gender` tinyint(1) NOT NULL COMMENT '性别 0男1女',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';