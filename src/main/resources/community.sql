/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : community

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-11-27 23:09:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` text,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `comment_count` int(11) DEFAULT '0',
  `view_count` int(11) DEFAULT '0',
  `like_count` int(11) DEFAULT '0',
  `tag` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `account_id` varchar(100) DEFAULT NULL,
  `token` char(50) DEFAULT NULL,
  `gmt_create` bigint(19) DEFAULT NULL,
  `gmt_modified` bigint(19) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- View structure for questionuser
-- ----------------------------
DROP VIEW IF EXISTS `questionuser`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `questionuser` AS select `question`.`id` AS `id`,`question`.`title` AS `title`,`question`.`description` AS `description`,`question`.`gmt_create` AS `gmt_create`,`question`.`gmt_modified` AS `gmt_modified`,`question`.`creator` AS `creator`,`question`.`comment_count` AS `comment_count`,`question`.`view_count` AS `view_count`,`question`.`like_count` AS `like_count`,`question`.`tag` AS `tag`,`user`.`id` AS `user_id`,`user`.`name` AS `user_name`,`user`.`account_id` AS `user_account_id`,`user`.`token` AS `user_token`,`user`.`gmt_create` AS `user_gmt_create`,`user`.`gmt_modified` AS `user_gmt_modified`,`user`.`avatar_url` AS `user_avatar_url` from (`question` join `user` on((`question`.`creator` = `user`.`id`))) ;
