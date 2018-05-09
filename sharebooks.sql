/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : sharebooks

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-09 16:42:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(255) DEFAULT NULL COMMENT '图书背面ISBN编号',
  `title` varchar(255) DEFAULT NULL COMMENT '书名',
  `author` varchar(255) DEFAULT NULL COMMENT '作者',
  `image` varchar(255) DEFAULT NULL COMMENT '预览图',
  `rating` varchar(255) DEFAULT NULL COMMENT '豆瓣评分',
  `publisher` varchar(255) DEFAULT NULL COMMENT '出版社',
  `translator` varchar(255) DEFAULT NULL COMMENT '译者',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `authorintro` varchar(255) DEFAULT NULL COMMENT '作者介绍',
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------

-- ----------------------------
-- Table structure for bookstore
-- ----------------------------
DROP TABLE IF EXISTS `bookstore`;
CREATE TABLE `bookstore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookId` int(11) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `stauts` int(11) DEFAULT NULL COMMENT '已借书，借入书，未借出书',
  `deposit` decimal(10,2) DEFAULT NULL COMMENT '押金数额',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '借阅费用',
  `days` int(11) DEFAULT NULL COMMENT '归还天数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookstore
-- ----------------------------

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `followid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of follow
-- ----------------------------

-- ----------------------------
-- Table structure for pay
-- ----------------------------
DROP TABLE IF EXISTS `pay`;
CREATE TABLE `pay` (
  `id` int(11) DEFAULT NULL,
  `bookid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `payfee` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pay
-- ----------------------------

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(11) NOT NULL,
  `bookid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `openid` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
