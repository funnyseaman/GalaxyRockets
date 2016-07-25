/*
Navicat MySQL Data Transfer

Source Server         : issweb
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : linkedin

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2016-07-22 19:22:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `focus`
-- ----------------------------
DROP TABLE IF EXISTS `focus`;
CREATE TABLE `focus` (
  `focus_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `focused_user_id` int(11) NOT NULL,
  PRIMARY KEY (`focus_id`),
  KEY `user_id` (`user_id`),
  KEY `focused_user_id` (`focused_user_id`),
  CONSTRAINT `focused_user_id` FOREIGN KEY (`focused_user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of focus
-- ----------------------------
INSERT INTO `focus` VALUES ('12', '1', '1');

-- ----------------------------
-- Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_author` int(11) NOT NULL,
  `task_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `task_text` text,
  `task_pic` varchar(40) DEFAULT NULL,
  `parent_task_id` int(11) DEFAULT NULL,
  `tree_level` int(1) NOT NULL,
  `task_title` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  KEY `task_author` (`task_author`),
  KEY `parent_task_id` (`parent_task_id`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`task_author`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `task_ibfk_2` FOREIGN KEY (`parent_task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('35', '1', '2016-07-22 18:05:53', '帖子', null, null, '1', null);
INSERT INTO `task` VALUES ('36', '1', '2016-07-22 18:08:48', '帖子二', null, null, '1', null);
INSERT INTO `task` VALUES ('37', '1', '2016-07-22 18:44:47', '2', null, null, '1', null);
INSERT INTO `task` VALUES ('38', '1', '2016-07-22 18:59:44', 'asfcasc', null, null, '1', null);

-- ----------------------------
-- Table structure for `taskjudgement`
-- ----------------------------
DROP TABLE IF EXISTS `taskjudgement`;
CREATE TABLE `taskjudgement` (
  `task_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `judgement_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`judgement_id`),
  KEY `task_id` (`task_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `taskjudgement_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `taskjudgement_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taskjudgement
-- ----------------------------

-- ----------------------------
-- Table structure for `tasktag`
-- ----------------------------
DROP TABLE IF EXISTS `tasktag`;
CREATE TABLE `tasktag` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`task_id`),
  KEY `tag_id` (`tag_id`),
  CONSTRAINT `tasktag_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tasktag_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tasktag
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  `user_email` varchar(20) NOT NULL,
  `user_school` varchar(20) DEFAULT NULL,
  `user_major` varchar(20) DEFAULT NULL,
  `user_head` varchar(30) DEFAULT NULL,
  `user_sex` int(1) DEFAULT NULL,
  `user_sign` varchar(40) DEFAULT 'This user is so lazy .',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'wr', '654321', 'wr819697746@qq.com', 'wuda', 'IT', '', '0', 'ooooo');
INSERT INTO `user` VALUES ('2', 'weiran', '654321', 'wr819697746@qq.com', null, null, null, null, 'This user is so lazy .');

-- ----------------------------
-- Table structure for `userfavoritetask`
-- ----------------------------
DROP TABLE IF EXISTS `userfavoritetask`;
CREATE TABLE `userfavoritetask` (
  `user_id` int(11) NOT NULL,
  `task_id` int(11) NOT NULL,
  `uft_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`uft_id`),
  KEY `user_id` (`user_id`),
  KEY `task_id` (`task_id`),
  CONSTRAINT `userfavoritetask_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userfavoritetask_ibfk_2` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userfavoritetask
-- ----------------------------
