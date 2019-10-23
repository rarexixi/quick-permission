create database `quick_permission`
    default character set utf8
    collate utf8_general_ci;

use `quick_permission`;

drop table if exists `sys_user`;
create table `sys_user`
(
    `user_id`     int                                    not null auto_increment comment '用户ID',
    `username`    varchar(64)                            not null comment '用户名',
    `password`    varchar(128) default ''                not null comment '密码',
    `salt`        varchar(16)  default ''                not null comment '盐',
    `email`       varchar(128)                           not null comment '邮箱',
    `mobile`      varchar(128)                           not null comment '手机号',
    `real_name`   varchar(32)  default ''                not null comment '姓名',
    `avatar`      varchar(256) default ''                not null comment '头像',

    `deleted`     tinyint      default 0                 not null comment '是否删除',
    `create_user` int          default 0                 not null comment '创建人',
    `update_user` int          default 0                 not null comment '修改人',
    `create_time` datetime     default current_timestamp not null comment '创建时间',
    `update_time` datetime     default current_timestamp not null on update current_timestamp comment '更新时间',

    primary key (`user_id`),
    unique (`username`),
    unique (`email`),
    unique (`mobile`),
    unique (`real_name`)
) engine = InnoDB
  default charset = utf8
  collate = utf8_unicode_ci
  auto_increment = 100000000
    comment ='系统用户';

drop table if exists `sys_role`;
create table `sys_role`
(
    `role_id`     int                                    not null auto_increment comment '角色ID',
    `role_code`   varchar(64)                            not null comment '角色编码',
    `role_name`   varchar(64)                            not null comment '角色名称',
    `built_in`    int          default 0                 not null comment '是否内置角色 (0否, 1是)',
    `remark`      varchar(128) default ''                not null comment '备注',

    `deleted`     tinyint      default 0                 not null comment '是否删除',
    `create_user` int          default 0                 not null comment '创建人',
    `update_user` int          default 0                 not null comment '修改人',
    `create_time` datetime     default current_timestamp not null comment '创建时间',
    `update_time` datetime     default current_timestamp not null on update current_timestamp comment '更新时间',

    primary key (`role_id`),
    unique (`role_code`)
) engine = InnoDB
  default charset = utf8
  collate = utf8_unicode_ci
  auto_increment = 100000000
    comment ='角色';

drop table if exists `sys_permission`;
create table `sys_permission`
(
    `permission_id`   int                                    not null auto_increment comment '权限ID',
    `parent_id`       int          default 0                 not null comment '模块ID，一级模块为0',
    `permission_code` varchar(64)                            not null comment '权限编码',
    `permission_name` varchar(64)                            not null comment '权限名称',
    `type`            tinyint      default 0                 not null comment '类型 (0模块,1权限)',
    `remark`          varchar(128) default ''                not null comment '备注',

    `deleted`         tinyint      default 0                 not null comment '是否删除',
    `create_user`     int          default 0                 not null comment '创建人',
    `update_user`     int          default 0                 not null comment '修改人',
    `create_time`     datetime     default current_timestamp not null comment '创建时间',
    `update_time`     datetime     default current_timestamp not null on update current_timestamp comment '更新时间',

    primary key (`permission_id`)
) engine = InnoDB
  default charset = utf8
  collate = utf8_unicode_ci
  auto_increment = 100000000
    comment ='权限';

drop table if exists `sys_menu`;
create table `sys_menu`
(
    `menu_id`     int                                    not null auto_increment comment '按钮ID',
    `parent_id`   int          default 0                 not null comment '父菜单ID，一级菜单为0',
    `menu_code`   varchar(64)  default ''                not null comment '菜单编码',
    `menu_name`   varchar(64)                            not null comment '菜单名称',
    `url`         varchar(256) default ''                not null comment '菜单URL',
    `type`        tinyint      default 0                 not null comment '类型 (0目录,1菜单)',
    `icon`        varchar(64)  default ''                not null comment '菜单图标',
    `sort`        int          default 0                 not null comment '排序',
    `remark`      varchar(128) default ''                not null comment '备注',

    `deleted`     tinyint      default 0                 not null comment '是否删除',
    `create_user` int          default 0                 not null comment '创建人',
    `update_user` int          default 0                 not null comment '修改人',
    `create_time` datetime     default current_timestamp not null comment '创建时间',
    `update_time` datetime     default current_timestamp not null on update current_timestamp comment '更新时间',

    primary key (`menu_id`),
    index (`sort`)
) engine = InnoDB
  default charset = utf8
  collate = utf8_unicode_ci
  auto_increment = 100000000
    comment ='菜单';

drop table if exists `sys_user_role`;
create table `sys_user_role`
(
    `user_id` int not null comment '用户ID',
    `role_id` int not null comment '角色ID',
    primary key (`user_id`, `role_id`)
) engine = InnoDB
  default charset = utf8
  collate = utf8_unicode_ci
    comment ='用户角色';

drop table if exists `sys_role_menu`;
create table `sys_role_menu`
(
    `role_id` int not null comment '角色ID',
    `menu_id` int not null comment '菜单ID',
    primary key (`role_id`, `menu_id`)
) engine = InnoDB
  default charset = utf8
  collate = utf8_unicode_ci
    comment ='角色菜单';

drop table if exists `sys_role_permission`;
create table `sys_role_permission`
(
    `role_id`       int not null comment '角色ID',
    `permission_id` int not null comment '权限ID',
    primary key (`role_id`, `permission_id`)
) engine = InnoDB
  default charset = utf8
  collate = utf8_unicode_ci
    comment ='角色权限';

drop table if exists `sys_menu_permission`;
create table `sys_menu_permission`
(
    `menu_id`       int not null comment '菜单ID',
    `permission_id` int not null comment '权限ID',
    primary key (`menu_id`, `permission_id`)
) engine = InnoDB
  default charset = utf8
  collate = utf8_unicode_ci
    comment ='菜单权限';

drop table if exists `sys_option`;
create table `sys_option`
(
    `id`          int                                     not null auto_increment comment '枚举ID',
    `option_code` varchar(128)                            not null comment '编码',
    `value`       varchar(32)                             not null comment '值',
    `text`        varchar(64)   default ''                not null comment '展示文字',
    `sort`        int           default 0                 not null comment '排序',
    `parent_id`   int           default 0                 not null comment '父ID',
    `type`        tinyint       default 0                 not null comment '类型 (0分组,1选项)',
    `built_in`    tinyint       default 0                 not null comment '是否系统内置 (0否,1是)',
    `ext`         varchar(1024) default ''                not null comment '扩展信息',

    `deleted`     tinyint       default 0                 not null comment '是否删除',
    `create_user` int           default 0                 not null comment '创建人',
    `update_user` int           default 0                 not null comment '修改人',
    `create_time` datetime      default current_timestamp not null comment '创建时间',
    `update_time` datetime      default current_timestamp not null on update current_timestamp comment '更新时间',

    primary key (`id`),
    unique (`option_code`),
    unique (`parent_id`, `value`)
) engine = InnoDB
  default charset = utf8
  collate = utf8_unicode_ci
    comment ='系统选项';

drop table if exists `sys_config`;
create table `sys_config`
(
    `key`         varchar(64)                             not null comment '配置键',
    `value`       varchar(2048) default ''                not null comment '配置值',
    `remark`      varchar(512)  default ''                not null comment '备注',

    `deleted`     tinyint       default 0                 not null comment '是否删除',
    `create_user` int           default 0                 not null comment '创建人',
    `update_user` int           default 0                 not null comment '修改人',
    `create_time` datetime      default current_timestamp not null comment '创建时间',
    `update_time` datetime      default current_timestamp not null on update current_timestamp comment '更新时间',

    primary key (`key`)
) engine = InnoDB
  default charset = utf8
  collate = utf8_unicode_ci
    comment ='系统配置';

drop table if exists `sys_user_token`;
create table `sys_user_token`
(
    `user_id`   int          not null,
    `token`     varchar(128) not null comment 'token',
    `expire_at` datetime     not null comment '过期时间',
    primary key (`user_id`),
    unique (`token`)
) engine = InnoDB
  default charset = utf8
  collate = utf8_unicode_ci
  auto_increment = 100000000
    comment ='系统用户Token';

drop table if exists `sys_captcha`;
create table `sys_captcha`
(
    `uuid`      varchar(32) not null comment '标识',
    `code`      varchar(8)  not null comment '验证码',
    `expire_at` datetime    not null comment '过期时间',
    primary key (`uuid`)
) engine = InnoDB
  default charset = utf8
  collate = utf8_unicode_ci
    comment ='系统验证码';

insert into sys_role(role_id, role_code, role_name)
values (100000000, 'administrator', '超级管理员'),
       (100000001, 'level1', '一级管理员'),
       (100000002, 'level2', '二级管理员'),
       (100000003, 'level3', '三级管理员');

insert into sys_user(user_id, username, password, salt, email, mobile)
values (100000000, 'administrator', '123456', '123456', 'admin@123.com', '13233333333');

insert into sys_user_role(user_id, role_id)
values (100000000, 100000000);

insert into sys_menu(menu_id, parent_id, menu_code, menu_name, url, type, icon, sort)
values (100000000, 0, 'permission-config', '权限管理', '', 0, 'people', 1),
       (100000001, 100000000, 'sys-role', '角色', '/sys/role/index.html', 1, '', 1),
       (100000002, 100000000, 'sys-user', '系统用户', '/sys/user/index.html', 1, '', 2),
       (100000003, 100000000, 'sys-menu', '菜单', '/sys/menu/index.html', 1, '', 3),
       (100000004, 100000000, 'sys-permission', '权限', '/sys/permission/index.html', 1, '', 4),
       (100000005, 0, 'system-config', '系统配置', '', 0, 'build', 1),
       (100000006, 100000005, 'sys-config', '系统配置', '/sys/config/index.html', 1, '', 1),
       (100000007, 100000005, 'sys-option', '系统选项', '/sys/option/index.html', 1, '', 2);

insert into sys_option(id, option_code, value, text, parent_id, type, built_in)
values (100000000, 'boolean', 'boolean', 'boolean', 0, 0, 1),
       (100000001, 'boolean:0', '0', '否', 100000000, 1, 1),
       (100000002, 'boolean:1', '1', '是', 100000000, 1, 1),
       (100000003, 'sys_option_type', 'sys_option_type', '选项类型', 0, 0, 1),
       (100000004, 'sys_option_type:0', '0', '分组', 100000003, 1, 1),
       (100000005, 'sys_option_type:1', '1', '选项', 100000003, 1, 1),
       (100000006, 'sys_menu_type', 'sys_menu_type', '菜单类型', 0, 0, 1),
       (100000007, 'sys_menu_type:0', '0', '目录', 100000006, 1, 1),
       (100000008, 'sys_menu_type:1', '1', '菜单', 100000006, 1, 1),
       (100000009, 'sys_permission_type', 'sys_permission_type', '权限类型', 0, 0, 1),
       (100000010, 'sys_permission_type:0', '0', '模块', 100000009, 1, 1),
       (100000011, 'sys_permission_type:1', '1', '权限', 100000009, 1, 1);

insert into sys_permission(permission_id, parent_id, permission_code, permission_name, type)
values (100000000, 0, 'sys', '系统', 0),
       (100000001, 100000000, 'sys:config', '系统配置', 0),
       (100000002, 100000001, 'sys:config:view', '查询系统配置', 1),
       (100000003, 100000001, 'sys:config:add', '添加系统配置', 1),
       (100000004, 100000001, 'sys:config:del', '删除系统配置', 1),
       (100000005, 100000001, 'sys:config:disable', '禁用系统配置', 1),
       (100000006, 100000001, 'sys:config:enable', '启用系统配置', 1),
       (100000007, 100000001, 'sys:config:update', '修改系统配置', 1),
       (100000008, 100000001, 'sys:config:export', '导出系统配置', 1),

       (100000009, 100000000, 'sys:option', '系统选项', 0),
       (100000010, 100000009, 'sys:option:view', '查询系统选项', 1),
       (100000011, 100000009, 'sys:option:add', '添加系统选项', 1),
       (100000012, 100000009, 'sys:option:del', '删除系统选项', 1),
       (100000013, 100000009, 'sys:option:disable', '禁用系统选项', 1),
       (100000014, 100000009, 'sys:option:enable', '启用系统选项', 1),
       (100000015, 100000009, 'sys:option:update', '修改系统选项', 1),
       (100000016, 100000009, 'sys:option:export', '导出系统选项', 1),

       (100000017, 100000000, 'sys:role', '用户角色', 0),
       (100000018, 100000017, 'sys:role:view', '查询用户角色', 1),
       (100000019, 100000017, 'sys:role:add', '添加用户角色', 1),
       (100000020, 100000017, 'sys:role:del', '删除用户角色', 1),
       (100000021, 100000017, 'sys:role:disable', '禁用用户角色', 1),
       (100000022, 100000017, 'sys:role:enable', '启用用户角色', 1),
       (100000023, 100000017, 'sys:role:update', '修改用户角色', 1),
       (100000024, 100000017, 'sys:role:export', '导出用户角色', 1),
       (100000025, 100000017, 'sys:role:menu', '管理用户角色菜单', 1),
       (100000026, 100000017, 'sys:role:permission', '管理用户角色权限', 1),

       (100000027, 100000000, 'sys:user', '用户', 0),
       (100000028, 100000027, 'sys:user:view', '查询用户', 1),
       (100000029, 100000027, 'sys:user:add', '添加用户', 1),
       (100000030, 100000027, 'sys:user:del', '删除用户', 1),
       (100000031, 100000027, 'sys:user:disable', '禁用用户', 1),
       (100000032, 100000027, 'sys:user:enable', '启用用户', 1),
       (100000033, 100000027, 'sys:user:update', '修改用户', 1),
       (100000034, 100000027, 'sys:user:export', '导出用户', 1),
       (100000035, 100000027, 'sys:user:role', '管理用户角色', 1),

       (100000036, 100000000, 'sys:menu', '菜单管理', 0),
       (100000037, 100000036, 'sys:menu:view', '查询菜单', 1),
       (100000038, 100000036, 'sys:menu:add', '添加菜单', 1),
       (100000039, 100000036, 'sys:menu:del', '删除菜单', 1),
       (100000040, 100000036, 'sys:menu:disable', '禁用菜单', 1),
       (100000041, 100000036, 'sys:menu:enable', '启用菜单', 1),
       (100000042, 100000036, 'sys:menu:update', '修改菜单', 1),
       (100000043, 100000036, 'sys:menu:export', '导出菜单', 1),
       (100000044, 100000036, 'sys:menu:permission', '管理菜单权限', 1),

       (100000045, 100000000, 'sys:permission', '权限管理', 0),
       (100000046, 100000045, 'sys:permission:view', '查询权限', 1),
       (100000047, 100000045, 'sys:permission:add', '添加权限', 1),
       (100000048, 100000045, 'sys:permission:del', '删除权限', 1),
       (100000049, 100000045, 'sys:permission:disable', '禁用权限', 1),
       (100000050, 100000045, 'sys:permission:enable', '启用权限', 1),
       (100000051, 100000045, 'sys:permission:update', '修改权限', 1),
       (100000052, 100000045, 'sys:permission:export', '导出权限', 1);
