/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : community

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-12-02 22:41:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父类id',
  `type` int(11) DEFAULT NULL COMMENT '父类类型',
  `commentator` int(11) DEFAULT NULL COMMENT '评论人id',
  `gmt_create` bigint(20) DEFAULT NULL COMMENT '评论时间',
  `gmt_modified` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `content` varchar(1024) DEFAULT NULL COMMENT '评论类容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '21', '1', '26', '1575297254798', '1575297254798', 'zhehhjsdhjhfjhfjsdhfj');
INSERT INTO `comment` VALUES ('2', '22', '1', '26', '1575297312652', '1575297312652', 'zhehhjsdhjhfjhfjsdhfj');
INSERT INTO `comment` VALUES ('3', '22', '1', '26', '1575297509385', '1575297509385', 'kjhkhjhj');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('22', '123123', '杀杀杀', '1575191270405', '1575193229106', '26', '2', '72', '0', '是的');
INSERT INTO `question` VALUES ('23', '而非微软去', '微软微软·111', '1575191288141', '1575194190464', '26', '0', '7', '0', '123');
INSERT INTO `question` VALUES ('24', '1231', '12312', '1575191294886', '1575191294886', '26', '0', '0', '0', '手动阀手动阀');
INSERT INTO `question` VALUES ('25', '2332', '为凤尾', '1575191302868', '1575191302868', '26', '0', '0', '0', '去问我去饿');
INSERT INTO `question` VALUES ('26', '323去', '电风扇电风扇', '1575191312219', '1575191312219', '26', '0', '0', '0', '威威');

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
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('26', 'Aftery', '37588923', 'e1ec4f5b-6ead-46a1-979d-e98ae21da0fb', '1574774519568', '1575292781940', 'https://avatars2.githubusercontent.com/u/37588923?v=4');

-- ----------------------------
-- View structure for questionuser
-- ----------------------------
DROP VIEW IF EXISTS `questionuser`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `questionuser` AS select `question`.`id` AS `id`,`question`.`title` AS `title`,`question`.`description` AS `description`,`question`.`gmt_create` AS `gmt_create`,`question`.`gmt_modified` AS `gmt_modified`,`question`.`creator` AS `creator`,`question`.`comment_count` AS `comment_count`,`question`.`view_count` AS `view_count`,`question`.`like_count` AS `like_count`,`question`.`tag` AS `tag`,`user`.`id` AS `user_id`,`user`.`name` AS `user_name`,`user`.`account_id` AS `user_account_id`,`user`.`token` AS `user_token`,`user`.`gmt_create` AS `user_gmt_create`,`user`.`gmt_modified` AS `user_gmt_modified`,`user`.`avatar_url` AS `user_avatar_url` from (`question` join `user` on((`question`.`creator` = `user`.`id`))) ;
