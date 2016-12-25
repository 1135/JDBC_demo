/*
Navicat MySQL Data Transfer

Source Server         : jdbc的那个mysql
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-12-25 15:51:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `area` varchar(20) DEFAULT NULL,
  `message` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '张三', '北京', 'The only true wisdom is in knowing you know nothing.\r\n\r\n唯一真正的智慧是自知无知');
INSERT INTO `student` VALUES ('3', 'li', '南京', '裁缝师');
INSERT INTO `student` VALUES ('55', 'bob', 'Canada', 'haha');
