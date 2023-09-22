-- 创建库
create database if not exists bean_api;

-- 切换库
use bean_api;

-- 用户信息表
create table if not exists bean_api.user
(
    id           bigint auto_increment comment 'id'
        primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    unionId      varchar(256)                           null comment '微信开放平台id',
    mpOpenId     varchar(256)                           null comment '公众号openId',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    phone        char(11)                               null comment '用户手机号',
    gender       tinyint                                null comment '性别 0 女 1 男',
    email        varchar(255)                           null comment '用户邮箱',
    accessKey    varchar(512)                           not null comment 'accessKey',
    secretKey    varchar(512)                           not null comment 'secretKey',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除'
)
    comment '用户' collate = utf8mb4_unicode_ci;


-- 接口信息表

create table if not exists bean_api.interface_info
(
    id               bigint auto_increment comment '主键'
        primary key,
    name             varchar(256)                       not null comment '名称',
    description      varchar(256)                       null comment '描述',
    url              varchar(512)                       not null comment '接口地址',
    requestHeader    text                               null comment '请求头',
    responseHeader   text                               null comment '响应头',
    status           int      default 0                 not null comment '接口状态（0-关闭，1-开启）',
    method           varchar(256)                       not null comment '请求类型',
    requestParams    text                               null comment '请求参数',
    userId           bigint                             not null comment '创建人',
    sdk              varchar(255)                       null comment '接口对应的sdk类路径',
    createTime       datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime       datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete         tinyint  default 0                 not null comment '是否删除(0-未删, 1-已删)',
    parameterExample varchar(255)                       null comment '参数示例'
)
    comment '接口信息';

-- 接口与用户关联信息表

create table if not exists bean_api.user_interface_info
(
    id              bigint auto_increment comment '主键'
        primary key,
    userId          bigint                             not null comment '调用用户 id',
    interfaceInfoId bigint                             not null comment '接口 id',
    totalNum        int      default 0                 not null comment '总调用次数',
    leftNum         int      default 0                 not null comment '剩余调用次数',
    status          int      default 0                 not null comment '0-正常，1-禁用',
    createTime      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime      datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete        tinyint  default 0                 not null comment '是否删除(0-未删, 1-已删)',
    version         int      default 0                 null comment '乐观锁版本号'
)
    comment '用户调用接口关系';

-- 收费接口信息表

create table if not exists bean_api.interface_charging
(
    id              bigint auto_increment comment '主键'
        primary key,
    interfaceId     bigint                             not null comment '接口id',
    charging        float(255, 2)                      not null comment '计费规则（元/条）',
    availablePieces varchar(255)                       not null comment '接口剩余可调用次数',
    userId          bigint                             not null comment '创建人',
    createTime      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime      datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete        tinyint  default 1                 not null comment '是否删除(0-删除 1-正常)'
)
    row_format = DYNAMIC;
