/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : bookstore

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-25 15:00:00
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('2', '7568010201,9787568010207', '这一次，我们又挨打了', '端木赐香', 'https://img3.doubanio.com/view/subject/m/public/s29096354.jpg', '5.9', '华中科技大学出版社', null, '48.00', '端木赐香\n原名李桂枝，毕业于河南大学历史系，现任教于河南安阳师范学院历史系，副教授。从事中国近代史与中国传统文化批评研究多年。自称平生要务：拆历史的墙角，探文化的陷阱，还原历史，奉献常识。因其文风义理，章立凡先生称其“历史顽主”；文字背后一腔真诚，鄢烈山先生赞其“仁义多情”。著有《有味的传统文化课》《重读晚晴六十年(1851-1911）》《1840：大国之殇》《历史不是哈哈镜：真假袁世凯辨别》等。', '5');

-- ----------------------------
-- Table structure for bookstore
-- ----------------------------
DROP TABLE IF EXISTS `bookstore`;
CREATE TABLE `bookstore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookid` int(11) DEFAULT NULL,
  `ownerid` int(11) DEFAULT NULL,
  `stauts` int(11) DEFAULT NULL COMMENT '已借书，借入书，未借出书',
  `deposit` decimal(10,2) DEFAULT NULL COMMENT '押金数额',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '借阅费用',
  `days` int(11) DEFAULT NULL COMMENT '归还天数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookstore
-- ----------------------------
INSERT INTO `bookstore` VALUES ('1', '2', '5', '0', null, null, null);

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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `payfee` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
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
  `password` varchar(255) DEFAULT NULL,
  `openid` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('4', '111', '111', null, null, null, null);
INSERT INTO `user` VALUES ('5', '222', '22222', null, null, null, null);
