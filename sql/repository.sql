-- 创建库
create database if not exists oracle;

-- 切换库
use oracle;


-- 用户表（软删除）
create table if not exists `user`
(
    `id`           bigint                                 not null auto_increment comment '用户ID',
    `email`        varchar(256)                           not null comment '邮箱',
    `password`     varchar(512)                           not null comment '密码',
    `union_id`     varchar(256)                           null comment '微信开放平台id',
    `mp_open_id`   varchar(256)                           null comment '公众号openId',
    `nick_name`    varchar(256)                           null comment '用户昵称',
    `user_avatar`  varchar(1024)                          null comment '用户头像',
    `user_profile` varchar(512)                           null comment '用户简介',
    `user_role`    varchar(256) default 'user'            not null comment '用户角色: user/admin/ban',
    `created_at`   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    `updated_at`   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间',
    `is_deleted`   tinyint(1)   default 0                 not null comment '是否已删除(0-未删除 1-已删除)',
    primary key (`id`),
    index idx_unionId (union_id)
) comment '用户表'
    engine = InnoDB
    default charset = utf8mb4
    collate = utf8mb4_unicode_ci;

insert into `user` (`id`, `email`, `password`) value (1, 'Oracle@qq.com', 'Oracle');


-- 编程语言标签表（软删除）
create table if not exists `language`
(
    `id`         int(11)                                not null auto_increment comment '编程语言标签ID',
    `name`       varchar(64) default '编程语言标签名称' not null comment '编程语言标签名称',
    `creator_id` bigint      default 1                  not null comment '创建人',
    `created_at` datetime    default CURRENT_TIMESTAMP  not null comment '创建时间',
    `updated_at` datetime    default CURRENT_TIMESTAMP  not null on update CURRENT_TIMESTAMP comment '最后更新时间',
    `is_deleted` tinyint(1)  default 0                  not null comment '是否已删除(0-未删除 1-已删除)',
    primary key (`id`),
    foreign key (creator_id) references `user` (id) on update cascade
) comment '编程语言标签表',
    engine = InnoDB
    default charset = utf8mb4
    collate = utf8mb4_unicode_ci;

insert into `language` (`id`, `name`) value (1, '未知语言');


-- 开源许可证类型表（软删除）
create table if not exists `license`
(
    `id`          int(11)                                    not null auto_increment comment '许可证ID',
    `name`        varchar(128) default '许可证名称'          not null comment '许可证名称',
    `url`         varchar(256) default '许可证文本的URL地址' not null comment '许可证文本的URL地址',
    `description` text                                       not null comment '许可证的描述信息',
    `creator_id`  bigint       default 1                     not null comment '创建人',
    `created_at`  datetime     default CURRENT_TIMESTAMP     not null comment '创建时间',
    `updated_at`  datetime     default CURRENT_TIMESTAMP     not null on update CURRENT_TIMESTAMP comment '最后更新时间',
    `is_deleted`  tinyint(1)   default 0                     not null comment '是否已删除(0-未删除 1-已删除)',
    primary key (`id`),
    foreign key (creator_id) references `user` (id) on update cascade
) comment '开源许可证类型表',
    engine = InnoDB
    default charset = utf8mb4
    collate = utf8mb4_unicode_ci;

insert into `license` (`id`, `name`, `url`, `description`)
    value (1, 'GPL', 'https://www.gnu.org/licenses/gpl-3.0.txt', '默认开源许可证');


-- 代码仓库表（软删除）
create table if not exists `repository`
(
    `id`          bigint                                 not null auto_increment comment '仓库ID',
    `name`        varchar(256) default '仓库名称'        not null comment '仓库名称',
    `description` text                                   not null comment '仓库的描述信息',
    `owner_id`    bigint                                 not null comment '仓库所有者的ID',
    `created_at`  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    `updated_at`  datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间',
    `language_id` int(11)      default 1                 not null comment '仓库主要使用的编程语言',
    `stars`       int(11)      default 0                 not null comment '仓库的星标数',
    `forks`       int(11)      default 0                 not null comment '仓库的分支数',
    `readme`      text                                   not null comment '仓库的README文件内容',
    `license_id`  int(11)      default 1                 not null comment '开源许可证类型',
    `is_private`  tinyint(1)   default 0                 not null comment '是否为私有仓库(0-公开 1-私有)',
    `is_deleted`  tinyint(1)   default 0                 not null comment '是否已删除(0-未删除 1-已删除)',
    primary key (`id`),
    foreign key (owner_id) references `user` (id) on update cascade,
    foreign key (language_id) references `language` (id) on update cascade,
    foreign key (license_id) references `license` (id) on update cascade,
    key `idx_owner_id` (`owner_id`),
    key `idx_stars` (`stars`),
    key `idx_forks` (`forks`)
) comment '代码仓库表'
    engine = InnoDB
    default charset = utf8mb4
    collate = utf8mb4_unicode_ci;

insert into `repository` (`name`, `description`, `owner_id`, `readme`) value ('测试仓库', '测试仓库', 1, '测试仓库');

-- 仓库star表（硬删除）
create table if not exists `star`
(
    `id`            bigint auto_increment comment 'id',
    `user_id`       bigint                             not null comment '用户id',
    `repository_id` bigint                             not null comment '仓库id',
    `created_at`    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updated_at`    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间',
    primary key (id),
    foreign key (user_id) references `user` (id) on update cascade,
    foreign key (repository_id) references `repository` (id) on update cascade,
    index idx_userId (user_id),
    index idx_repositoryId (repository_id)
) comment '仓库点赞表';


-- 帖子表
create table if not exists post
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(512)                       null comment '标题',
    content    text                               null comment '内容',
    tags       varchar(1024)                      null comment '标签列表(json 数组)',
    thumbNum   int      default 0                 not null comment '点赞数',
    favourNum  int      default 0                 not null comment '收藏数',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
) comment '帖子' collate = utf8mb4_unicode_ci;

-- 帖子点赞表（硬删除）
create table if not exists post_thumb
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子点赞';

-- 帖子收藏表（硬删除）
create table if not exists post_favour
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子收藏';