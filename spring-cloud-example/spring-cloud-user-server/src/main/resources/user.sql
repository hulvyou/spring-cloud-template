/*
 Navicat Premium Data Transfer

 Source Server         : 119.23.141.67
 Source Server Type    : MySQL
 Source Server Version : 50552
 Source Host           : 119.23.141.67
 Source Database       : userDetailDO

 Target Server Type    : MySQL
 Target Server Version : 50552
 File Encoding         : utf-8

 Date: 09/14/2017 11:18:44 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_authorities`
-- ----------------------------
DROP TABLE IF EXISTS `t_authorities`;
CREATE TABLE `t_authorities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parentId` bigint(20) DEFAULT NULL COMMENT '父ID',
  `authority` varchar(200) DEFAULT NULL COMMENT '权限资源',
  `name` varchar(100) DEFAULT NULL COMMENT '名字',
  `code` varchar(100) DEFAULT NULL COMMENT 'code',
  `level` int(2) DEFAULT NULL COMMENT '层级',
  `sort` int(2) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名字',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `secret` varchar(100) DEFAULT NULL COMMENT '密码',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
