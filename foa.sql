/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : foa

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-04-28 10:17:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for fishery
-- ----------------------------
DROP TABLE IF EXISTS `fishery`;
CREATE TABLE `fishery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `bind_status` varchar(255) DEFAULT NULL,
  `bind_address` varchar(255) DEFAULT NULL,
  `sell_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80009 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fishery
-- ----------------------------
INSERT INTO `fishery` VALUES ('1', null, '1', null, null, null);
INSERT INTO `fishery` VALUES ('2', null, null, null, null, 'unselled');
INSERT INTO `fishery` VALUES ('3', '渔场', null, null, null, 'selling');
INSERT INTO `fishery` VALUES ('5', null, null, null, null, 'selling');
INSERT INTO `fishery` VALUES ('10', 'niha', null, null, null, null);
INSERT INTO `fishery` VALUES ('11', null, null, null, null, null);
INSERT INTO `fishery` VALUES ('12', '1122', null, null, null, 'selling');
INSERT INTO `fishery` VALUES ('14', 'ee', null, null, null, 'selling');
INSERT INTO `fishery` VALUES ('15', 'asdf', null, null, null, null);
INSERT INTO `fishery` VALUES ('18', 'ceshi', null, null, null, null);
INSERT INTO `fishery` VALUES ('20', null, null, null, null, null);
INSERT INTO `fishery` VALUES ('21', null, null, null, null, null);
INSERT INTO `fishery` VALUES ('25', '123', null, null, null, null);
INSERT INTO `fishery` VALUES ('26', 'hello', null, null, null, null);
INSERT INTO `fishery` VALUES ('27', 'metou', null, null, null, 'unselled');
INSERT INTO `fishery` VALUES ('28', 'hello', null, null, null, null);
INSERT INTO `fishery` VALUES ('31', 'hello', null, null, null, null);
INSERT INTO `fishery` VALUES ('32', 'hello', null, null, null, 'selling');
INSERT INTO `fishery` VALUES ('33', 'fdsfas', null, null, null, 'selling');
INSERT INTO `fishery` VALUES ('34', '总是', null, null, null, 'selling');
INSERT INTO `fishery` VALUES ('36', 'sdfs', null, null, null, null);
INSERT INTO `fishery` VALUES ('38', 'bucunzai', null, null, null, null);
INSERT INTO `fishery` VALUES ('39', 'adfad', null, null, null, null);
INSERT INTO `fishery` VALUES ('40', 'fuck', null, null, null, null);
INSERT INTO `fishery` VALUES ('78', null, null, null, null, null);
INSERT INTO `fishery` VALUES ('95', '你好', null, null, null, null);
INSERT INTO `fishery` VALUES ('96', null, null, null, null, null);
INSERT INTO `fishery` VALUES ('99', 'hello', null, null, null, null);
INSERT INTO `fishery` VALUES ('100', '阿斯蒂芬', null, null, null, null);
INSERT INTO `fishery` VALUES ('101', '爱对方水电费', null, null, null, null);
INSERT INTO `fishery` VALUES ('123', null, '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', 'unbinding', '0x123112312312', null);
INSERT INTO `fishery` VALUES ('234', null, '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', null, null, null);
INSERT INTO `fishery` VALUES ('344', null, '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', null, null, null);
INSERT INTO `fishery` VALUES ('678', null, null, 'binding', '0x123112312312', null);
INSERT INTO `fishery` VALUES ('890', null, '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', null, null, null);
INSERT INTO `fishery` VALUES ('999', null, null, 'binding', 'comesdf', null);
INSERT INTO `fishery` VALUES ('40000', null, '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', null, null, null);
INSERT INTO `fishery` VALUES ('80000', null, '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', null, null, null);
INSERT INTO `fishery` VALUES ('80001', null, null, null, null, null);
INSERT INTO `fishery` VALUES ('80002', null, null, null, null, null);
INSERT INTO `fishery` VALUES ('80003', null, '1', null, null, null);
INSERT INTO `fishery` VALUES ('80004', null, '1', null, null, null);
INSERT INTO `fishery` VALUES ('80005', null, '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', null, null, null);
INSERT INTO `fishery` VALUES ('80006', null, '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', null, null, null);
INSERT INTO `fishery` VALUES ('80007', null, '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', null, null, null);
INSERT INTO `fishery` VALUES ('80008', null, '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', null, null, null);

-- ----------------------------
-- Table structure for market
-- ----------------------------
DROP TABLE IF EXISTS `market`;
CREATE TABLE `market` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fishery_id` int(11) DEFAULT NULL,
  `sell_status` varchar(255) DEFAULT NULL,
  `start_price` decimal(10,2) DEFAULT NULL,
  `stop_price` decimal(10,2) DEFAULT NULL,
  `sell_duration` decimal(10,2) DEFAULT NULL,
  `sell_start_time` varchar(255) DEFAULT NULL,
  `static_price` decimal(10,2) DEFAULT NULL,
  `favor_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of market
-- ----------------------------
INSERT INTO `market` VALUES ('7', '12', 'selling', '0.00', '0.00', '1.00', '2018-04-21 12:43:49', null, '0');
INSERT INTO `market` VALUES ('9', '33', 'selling', '0.00', '0.01', '1.00', '2018-04-22 00:41:53', null, '0');
INSERT INTO `market` VALUES ('10', '34', 'selling', '0.01', '0.00', '1.00', '2018-04-22 00:45:18', null, '0');
INSERT INTO `market` VALUES ('11', '32', 'selling', '1.00', '1.00', '1.00', '2018-04-22 00:57:09', null, '0');
INSERT INTO `market` VALUES ('12', '3', 'selling', '1.00', '1.00', '1.00', '2018-04-22 21:53:00', null, '0');
INSERT INTO `market` VALUES ('13', '14', 'selling', '0.00', '0.00', '1.00', '2018-04-23 17:50:17', null, '0');
INSERT INTO `market` VALUES ('14', '5', 'selling', '0.02', '0.05', '1.00', '2018-04-24 11:53:25', null, '0');

-- ----------------------------
-- Table structure for opslog
-- ----------------------------
DROP TABLE IF EXISTS `opslog`;
CREATE TABLE `opslog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `action` varchar(255) DEFAULT NULL,
  `txhash` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `action_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=336 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of opslog
-- ----------------------------
INSERT INTO `opslog` VALUES ('1', '1', '设置渔场名称', null, null, '2018-04-06 14:35:39');
INSERT INTO `opslog` VALUES ('2', '1', '设置渔场名称', null, null, '2018-04-06 14:37:29');
INSERT INTO `opslog` VALUES ('3', '1', '设置渔场名称', null, null, '2018-04-06 14:38:05');
INSERT INTO `opslog` VALUES ('4', '1', '设置渔场名称', null, null, '2018-04-06 14:39:22');
INSERT INTO `opslog` VALUES ('5', '1', '设置渔场名称', null, null, '2018-04-06 14:40:08');
INSERT INTO `opslog` VALUES ('6', '1', '设置渔场名称', null, null, '2018-04-06 14:40:37');
INSERT INTO `opslog` VALUES ('7', '1', '设置渔场名称', null, null, '2018-04-06 14:40:50');
INSERT INTO `opslog` VALUES ('8', '1', '设置渔场名称', null, null, '2018-04-06 14:42:56');
INSERT INTO `opslog` VALUES ('9', '1', '设置渔场名称', null, null, '2018-04-06 14:43:31');
INSERT INTO `opslog` VALUES ('10', '1', '设置渔场名称', null, 'good', '2018-04-06 14:44:38');
INSERT INTO `opslog` VALUES ('11', '1', '设置渔场名称', 'ok', 'good', '2018-04-06 14:44:57');
INSERT INTO `opslog` VALUES ('12', '1', '设置渔场名称', 'ok', 'good', '2018-04-06 14:45:17');
INSERT INTO `opslog` VALUES ('13', '1', '绑定游戏地址', 'ok', 'good', '2018-04-06 14:53:03');
INSERT INTO `opslog` VALUES ('14', '1', '绑定游戏地址', 'ok', 'good', '2018-04-06 14:54:30');
INSERT INTO `opslog` VALUES ('15', '1', '取消绑定', 'ok', 'good', '2018-04-06 14:55:59');
INSERT INTO `opslog` VALUES ('16', '1', '出售', 'ok', 'good', '2018-04-06 14:58:20');
INSERT INTO `opslog` VALUES ('17', '1', '出售', 'ok', 'good', '2018-04-06 14:58:43');
INSERT INTO `opslog` VALUES ('18', '1', '出售', 'ok', 'good', '2018-04-06 14:59:50');
INSERT INTO `opslog` VALUES ('19', '1', '出售', 'ok', 'good', '2018-04-06 15:04:20');
INSERT INTO `opslog` VALUES ('20', '1', '出售', 'ok', 'good', '2018-04-06 15:04:58');
INSERT INTO `opslog` VALUES ('21', '1', '出售', 'ok', 'good', '2018-04-06 15:05:23');
INSERT INTO `opslog` VALUES ('22', '1', '出售', 'ok', 'good', '2018-04-06 15:07:38');
INSERT INTO `opslog` VALUES ('23', '1', '取消绑定', 'ok', 'good', '2018-04-06 15:09:13');
INSERT INTO `opslog` VALUES ('24', '1', '取消出售', 'ok', 'good', '2018-04-06 15:10:21');
INSERT INTO `opslog` VALUES ('25', '1', '购买渔场', 'ok', 'good', '2018-04-06 15:12:16');
INSERT INTO `opslog` VALUES ('26', '1', '购买渔场', 'ok', 'good', '2018-04-06 15:12:52');
INSERT INTO `opslog` VALUES ('27', '1', '购买渔场', 'ok', 'good', '2018-04-06 15:12:55');
INSERT INTO `opslog` VALUES ('28', '1', '购买渔场', 'ok', 'good', '2018-04-06 15:12:56');
INSERT INTO `opslog` VALUES ('29', '1', '购买渔场', 'ok', 'good', '2018-04-06 15:12:56');
INSERT INTO `opslog` VALUES ('30', '1', '购买渔场', 'ok', 'good', '2018-04-06 15:17:51');
INSERT INTO `opslog` VALUES ('31', '1', '赞', null, null, '2018-04-06 15:20:14');
INSERT INTO `opslog` VALUES ('32', '1', '出售', 'ok', 'good', '2018-04-06 15:20:49');
INSERT INTO `opslog` VALUES ('33', '1', '赞', null, null, '2018-04-06 15:21:04');
INSERT INTO `opslog` VALUES ('34', '1', '赞', null, null, '2018-04-06 15:21:08');
INSERT INTO `opslog` VALUES ('35', '1', '赞', null, null, '2018-04-06 15:21:09');
INSERT INTO `opslog` VALUES ('36', '1', '赞', null, null, '2018-04-06 15:21:09');
INSERT INTO `opslog` VALUES ('37', '1', '赞', null, null, '2018-04-06 15:21:09');
INSERT INTO `opslog` VALUES ('38', '1', '赞', null, null, '2018-04-06 15:21:10');
INSERT INTO `opslog` VALUES ('39', '1', '赞', null, null, '2018-04-06 15:21:10');
INSERT INTO `opslog` VALUES ('40', '1', '赞', null, null, '2018-04-06 15:21:10');
INSERT INTO `opslog` VALUES ('41', '1', '赞', null, null, '2018-04-06 15:21:11');
INSERT INTO `opslog` VALUES ('42', '1', '赞', null, null, '2018-04-06 15:21:11');
INSERT INTO `opslog` VALUES ('43', '1', '赞', null, null, '2018-04-06 15:21:11');
INSERT INTO `opslog` VALUES ('44', '1', '设置渔场名称', 'ok', 'good', '2018-04-15 14:51:16');
INSERT INTO `opslog` VALUES ('45', '221112', '设置渔场名称', 'ok', 'good', '2018-04-15 14:51:30');
INSERT INTO `opslog` VALUES ('46', '1', '设置渔场名称', 'ok', 'good', '2018-04-15 14:52:04');
INSERT INTO `opslog` VALUES ('47', '221112', '设置渔场名称', 'ok', 'good', '2018-04-15 15:10:56');
INSERT INTO `opslog` VALUES ('48', '221112', '设置渔场名称', 'ok', 'good', '2018-04-15 15:11:21');
INSERT INTO `opslog` VALUES ('49', '221112', '设置渔场名称', 'ok', 'good', '2018-04-15 15:11:22');
INSERT INTO `opslog` VALUES ('50', '221112', '设置渔场名称', 'ok', 'good', '2018-04-15 15:11:22');
INSERT INTO `opslog` VALUES ('51', '221112', '设置渔场名称', 'ok', 'good', '2018-04-15 15:11:23');
INSERT INTO `opslog` VALUES ('52', '221112', '设置渔场名称', 'ok', 'good', '2018-04-15 15:11:33');
INSERT INTO `opslog` VALUES ('53', '221112', '绑定游戏地址', 'ok', 'good', '2018-04-15 15:12:16');
INSERT INTO `opslog` VALUES ('54', '221112', '取消绑定', 'ok', 'good', '2018-04-15 15:12:36');
INSERT INTO `opslog` VALUES ('55', '221112', '出售', 'ok', 'good', '2018-04-15 15:12:50');
INSERT INTO `opslog` VALUES ('56', '221112', '取消出售', 'ok', 'good', '2018-04-15 15:13:05');
INSERT INTO `opslog` VALUES ('57', '221112', '购买渔场', 'ok', 'good', '2018-04-15 15:13:18');
INSERT INTO `opslog` VALUES ('58', '221112', '赞', null, null, '2018-04-15 15:13:32');
INSERT INTO `opslog` VALUES ('59', '221112', '取消绑定', 'ok', 'good', '2018-04-15 15:25:53');
INSERT INTO `opslog` VALUES ('60', '221112', '出售', 'ok', 'good', '2018-04-15 15:26:11');
INSERT INTO `opslog` VALUES ('61', '221112', '设置渔场名称', 'ok', 'good', '2018-04-15 15:59:39');
INSERT INTO `opslog` VALUES ('62', '221112', '绑定游戏地址', 'ok', 'good', '2018-04-15 15:59:45');
INSERT INTO `opslog` VALUES ('63', '221112', '绑定游戏地址', 'ok', 'good', '2018-04-15 21:12:51');
INSERT INTO `opslog` VALUES ('64', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0xb94550374965c7daa0d68a43793d742cf177f3f5fd99978ae6d62d1a5bbfd25a', '', '2018-04-18 10:35:29');
INSERT INTO `opslog` VALUES ('65', '0x4229040bf34c86fc493b012d0cfc9e62a570c141', '出售', '0x032d795e7894c4002f16ba0da6ff1e293d7433ee2631525b3c0cf8f833ffe723', '', '2018-04-18 10:47:35');
INSERT INTO `opslog` VALUES ('66', '0x4229040bf34c86fc493b012d0cfc9e62a570c141', '出售', '0x41b1fd737bd9aa714369c5c97b93924cd5abb99eaf6bc2a3aef88d6e16f8c4fc', '', '2018-04-18 10:47:54');
INSERT INTO `opslog` VALUES ('67', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0x7f564eb862285c92978ac55daaff3c837af1e8809a26155dd2809e142b3eb5a1', '', '2018-04-18 10:54:32');
INSERT INTO `opslog` VALUES ('68', '0x4229040bf34c86fc493b012d0cfc9e62a570c141', '出售', '0x03401d633e7b33c71233aac44e713928e04e733e131c92957b588f0e497688f7', '', '2018-04-18 11:15:34');
INSERT INTO `opslog` VALUES ('69', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0x2e05efe0c2ca817207180e856802e3d0ce159353a4026509288f95f0d7a11924', '', '2018-04-18 13:53:04');
INSERT INTO `opslog` VALUES ('70', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0xfa0cd7a43cbb5428f1901ccafe6c9fe428ff7fdb4f07f78a8c7825ebbf809ee6', '', '2018-04-18 13:58:37');
INSERT INTO `opslog` VALUES ('71', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0x56d772f000fd387a55068e5fb6ace46773daa15279326772d4d1e0be75a7995a', '', '2018-04-18 14:03:53');
INSERT INTO `opslog` VALUES ('72', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0xbea3a404a3a26c26fbd0bac850b8a52c613e9123a7e5ab0f0532b7eeeee2603a', '', '2018-04-18 14:06:03');
INSERT INTO `opslog` VALUES ('73', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0xdd7dcd359accf420943c1523e3e11470659ee9b54dae1f4aa92eeffeb5306cb7', '', '2018-04-19 17:12:07');
INSERT INTO `opslog` VALUES ('74', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0x22b7970640b663f30369bcf682da76c3e96d76eef133c958e31532b024d504b9', '', '2018-04-19 17:12:28');
INSERT INTO `opslog` VALUES ('75', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0x7dbee936df3d304eae54940ddadc429bf1404e2c3d31be961a652bce807a9124', '', '2018-04-19 17:14:10');
INSERT INTO `opslog` VALUES ('76', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0x406f745b5cf8e9feced7c5f0e183eeaaf816a19c9e44db4efe3dccc05301e91c', '', '2018-04-19 17:14:32');
INSERT INTO `opslog` VALUES ('77', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0xedf35d29a27cdf7ff0bb0e193058aae75a6c69723c5a2364f1dcbbea6425aaa2', '', '2018-04-19 17:36:09');
INSERT INTO `opslog` VALUES ('78', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0xd948cc16d34ff7c238eaabd78993a7fa11b99d5dc37b3f5ff3b129e26a890b79', '', '2018-04-19 17:37:11');
INSERT INTO `opslog` VALUES ('79', '0x4229040bf34c86fc493b012d0cfc9e62a570c141', '出售', '0xf66a5a6417bfe89a104bbe84edc1f97a1b6b439c17f803a295cff8e9d5238f5b', '', '2018-04-19 20:15:07');
INSERT INTO `opslog` VALUES ('80', '0x4229040bf34c86fc493b012d0cfc9e62a570c141', '出售', '0xd83c756b5631099e0f0c4a7b254578f171ac534367d66d0707f7f20da2627a7a', '', '2018-04-19 20:20:36');
INSERT INTO `opslog` VALUES ('81', '0x4229040bf34c86fc493b012d0cfc9e62a570c141', '设置渔场名称', '', '', '2018-04-20 09:39:16');
INSERT INTO `opslog` VALUES ('82', '0x4229040bf34c86fc493b012d0cfc9e62a570c141', '设置渔场名称', '', '', '2018-04-20 10:10:57');
INSERT INTO `opslog` VALUES ('83', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0xd2ee627ef20edc615ef5088e4da9103745a24b3b8396ea9f8f6b7112a3badf47', '', '2018-04-20 10:24:56');
INSERT INTO `opslog` VALUES ('84', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0x4253fda9e2e432386147ac0197c27991af92aa6e26dffdf152aa22bea6a44a92', '', '2018-04-20 10:29:43');
INSERT INTO `opslog` VALUES ('85', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0x56e920851e29b27528b8bdd87eb6b3fab20a13a49f786fbc2339f93175181ee9', '', '2018-04-20 11:29:24');
INSERT INTO `opslog` VALUES ('86', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '设置渔场名称', '', '', '2018-04-20 13:46:08');
INSERT INTO `opslog` VALUES ('87', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '设置渔场名称', '', '', '2018-04-20 13:46:14');
INSERT INTO `opslog` VALUES ('88', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '设置渔场名称', '', '', '2018-04-20 13:46:20');
INSERT INTO `opslog` VALUES ('89', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '设置渔场名称', '', '', '2018-04-20 13:46:33');
INSERT INTO `opslog` VALUES ('90', '0xc361a52154e155c971ca527085ce917f031e4e85', '出售', '0x7a6cb0c334e7c3a8308c7e29f83e46d0c3981a6ca5a6c4fbb84c335b320eb3f5', '', '2018-04-20 13:55:00');
INSERT INTO `opslog` VALUES ('91', '0xc361a52154e155c971ca527085ce917f031e4e85', '出售', '0x7eb107166b57eaf009d8bba04dee8b2e756df802f15f2d72d28e9decdb2853ff', '', '2018-04-20 13:55:49');
INSERT INTO `opslog` VALUES ('92', '0xc361a52154e155c971ca527085ce917f031e4e85', '出售', '0xfb9a97c762e6cddc2afc03db19745b956e3615a7b0d49eb62a0f6704555e03a3', '', '2018-04-20 13:57:09');
INSERT INTO `opslog` VALUES ('93', '0xc361a52154e155c971ca527085ce917f031e4e85', '出售', '0x252ce2f509740ce12ceb5b56b3ad913c7f73e2bde7fd4ee8f075bed7b5a7f7f3', '', '2018-04-20 14:03:20');
INSERT INTO `opslog` VALUES ('94', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '设置渔场名称', '', '', '2018-04-20 14:59:35');
INSERT INTO `opslog` VALUES ('95', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '设置渔场名称', '', '', '2018-04-20 16:05:03');
INSERT INTO `opslog` VALUES ('96', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0xc97b667886859a5b0431ba4c8a4e083e92b0f7a1ddef127cd2d6719b48211aa4', '', '2018-04-20 18:01:27');
INSERT INTO `opslog` VALUES ('97', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0x35afd005f3a225bbf94fce135758457102852d281bf705c52b11a9c729e7ee8c', '', '2018-04-20 18:02:30');
INSERT INTO `opslog` VALUES ('98', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '设置渔场名称', '', '', '2018-04-21 12:04:21');
INSERT INTO `opslog` VALUES ('99', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '取消出售', '0x6d6bd9842b9f5bf1b2b21dc1c40cae52072543d21cdba25d6c0b646314403cca', '', '2018-04-21 12:10:28');
INSERT INTO `opslog` VALUES ('100', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '出售', '0xc60a5ce5661df780e48fde10cbd3bb1dc50e2e50221a420be3b8657a127b8927', '', '2018-04-21 12:11:41');
INSERT INTO `opslog` VALUES ('101', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '出售', '0x556c1eee5d22269d0a1bebd136e74a77dc604efe268e039e0c890f0d1be881e5', '', '2018-04-21 12:12:29');
INSERT INTO `opslog` VALUES ('102', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '设置渔场名称', '', '', '2018-04-21 12:15:58');
INSERT INTO `opslog` VALUES ('103', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '设置渔场名称', '', '', '2018-04-21 12:16:19');
INSERT INTO `opslog` VALUES ('104', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '出售', '0xf96bfd61229f6df498e28db5e592640e29fc341c541c784185908adf4eff62c4', '', '2018-04-21 12:29:07');
INSERT INTO `opslog` VALUES ('105', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '出售', '0xf9b18757ca251555f8b5e5f9c1a3914101d4a94a4221a2c77c188aa85e4213a7', '', '2018-04-21 12:41:17');
INSERT INTO `opslog` VALUES ('106', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '取消出售', '0x900edcd5d604e12352479ba9be77d2edea60093b78f42091b336eeca1c47b08d', '', '2018-04-21 12:42:36');
INSERT INTO `opslog` VALUES ('107', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '取消出售', '0xce16b0ff33d56e1c2d349ffff5a2b63d1208836792718ebac86a06da0df8e12d', '', '2018-04-21 12:42:52');
INSERT INTO `opslog` VALUES ('108', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '出售', '0x6111c444c0e78f12f40ed2387ddef73482b57dc54b6916193e371d6d69946268', '', '2018-04-21 12:43:49');
INSERT INTO `opslog` VALUES ('109', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '出售', '0x707df51fda717f625aacce2f59c8157c3f581c497b6f832d462288c3f644ea7b', '', '2018-04-21 12:44:14');
INSERT INTO `opslog` VALUES ('110', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '出售', '0xacf467fe2270aa7d9de24fc1a5b2739fe30674c429a36d3649110dad41156c97', '', '2018-04-21 12:44:43');
INSERT INTO `opslog` VALUES ('111', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '出售', '0x96dfabb85ba949cf220e27cb8cdb6f92722600d9fde43872d33426747f5fb56e', '', '2018-04-21 12:45:21');
INSERT INTO `opslog` VALUES ('112', '0x4229040bf34c86fc493b012d0cfc9e62a570c141', '出售', '0xee407b893fac841fb0fe288300b3c22c0f88248f146265b9f807ab760929b485', '', '2018-04-21 16:23:36');
INSERT INTO `opslog` VALUES ('113', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '出售', '0x1c5a6e09afd963c5c2351efe1b53ee29911ca6b62b7491b6b80753315b884229', '', '2018-04-21 16:37:25');
INSERT INTO `opslog` VALUES ('114', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '设置渔场名称', '', '', '2018-04-21 16:38:20');
INSERT INTO `opslog` VALUES ('115', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '设置渔场名称', '', '', '2018-04-21 16:38:27');
INSERT INTO `opslog` VALUES ('116', '0x16abd7314865fde554cf8fbfd1d788ad709ba976', '取消出售', '0xacf2bc16bdee052a7dba18390083cef6e4d97d4daa0d68ecb85157c780bf4492', '', '2018-04-21 16:39:02');
INSERT INTO `opslog` VALUES ('117', '0x4229040bf34c86fc493b012d0cfc9e62a570c141', '取消出售', '0xa92855bb883771535c3c66084adc810bf32fc2762c3cf8796ad93acb19c460df', '', '2018-04-21 17:40:37');
INSERT INTO `opslog` VALUES ('118', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '设置渔场名称', '', '', '2018-04-21 18:22:42');
INSERT INTO `opslog` VALUES ('119', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '设置渔场名称', '', '', '2018-04-22 00:35:15');
INSERT INTO `opslog` VALUES ('120', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '设置渔场名称', '', '', '2018-04-22 00:36:14');
INSERT INTO `opslog` VALUES ('121', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '出售', '0xc50e322fa520ee6554e63b4e8b2f66b6493bd4e6ee8176c7e7a1489dd7a0c3ce', '', '2018-04-22 00:41:53');
INSERT INTO `opslog` VALUES ('122', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '出售', '0x2785ea6b3ff4444ba03b4a807f88cc90b59c02aba37184bf56656d583a3e2567', '', '2018-04-22 00:45:18');
INSERT INTO `opslog` VALUES ('123', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '出售', '0x1abf3f1299bde3a162cb5ae402bef229bc4e19e744305961ff37fe8f70c2b657', '', '2018-04-22 00:45:45');
INSERT INTO `opslog` VALUES ('124', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '出售', '0xe9e8217feddefc4fbcd65d7524fe045f124c5435a753612197ae284df406307f', '', '2018-04-22 00:46:04');
INSERT INTO `opslog` VALUES ('125', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '取消出售', '0xb83c09b5b3bd8704c4f742eefcfeb635aed854978dc9ee440444c50a13c0795b', '', '2018-04-22 00:52:56');
INSERT INTO `opslog` VALUES ('126', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '出售', '0xfc8f3de8c9d6ab2d64e781cdf06e3af4cc2d91b6002ddb1bc58ab04948c6044f', '', '2018-04-22 00:53:42');
INSERT INTO `opslog` VALUES ('127', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '出售', '0x9bfb6cb7001f630c59c6aff9e0bf17a833196e0fdfecc564d0d64dd30b30b85b', '', '2018-04-22 00:54:56');
INSERT INTO `opslog` VALUES ('128', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '取消出售', '0x19e71b5278e7ffa0f798aed2effcde649e4d81d639912270d2bacd60950c9001', '', '2018-04-22 00:55:17');
INSERT INTO `opslog` VALUES ('129', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '出售', '0x652845bf6e374f448e53e56b03e1db782863d10d8af7744d48071f87ddda3181', '', '2018-04-22 00:56:52');
INSERT INTO `opslog` VALUES ('130', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '出售', '0xf41ca8d06c09180abd2ea713aea8d1aadf99f405644d57e20e67b9847e14efbc', '', '2018-04-22 00:57:09');
INSERT INTO `opslog` VALUES ('131', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '设置渔场名称', '', '', '2018-04-22 10:34:27');
INSERT INTO `opslog` VALUES ('132', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '设置渔场名称', '', '', '2018-04-22 14:48:17');
INSERT INTO `opslog` VALUES ('133', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '设置渔场名称', 'kkk', '', '2018-04-22 15:23:30');
INSERT INTO `opslog` VALUES ('134', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '设置渔场名称', 'kkk', '', '2018-04-22 15:23:40');
INSERT INTO `opslog` VALUES ('135', '0x4229040bf34c86fc493b012d0cfc9e62a570c141', '出售', '0xddc7fc2f96fe0e5541fd76377e1d4c80029cc72f4ec9cd8a89ba16c4a7aa13c1', '', '2018-04-22 21:53:00');
INSERT INTO `opslog` VALUES ('136', '0x07dcb393f26fc4336ac34b0d17ac1af350249a51', '出售', '0x89d0c1a12381cd5e54371199f2aece69cc3a267f8140bd95f76b0c67f91025f4', '', '2018-04-23 14:18:55');
INSERT INTO `opslog` VALUES ('137', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '出售', '0x2067065cdc157ec5c3993de65d5d28b32c456ab5834522e70d5bd2bb0b977bdc', '', '2018-04-23 17:50:17');
INSERT INTO `opslog` VALUES ('138', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '出售', '0x848cb69356cef44dc77d747dcd49599901af9472d724c65f0c02b4cc468f1c6a', '', '2018-04-23 22:45:10');
INSERT INTO `opslog` VALUES ('139', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '出售', '0xccfd4020141863236bb04814ab3c87db9abfe67011d3fa8fb72b978838aa4611', '', '2018-04-23 22:53:27');
INSERT INTO `opslog` VALUES ('140', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '出售', '0xaf4ab8a45ade35b72d8e99296ca60afadb0e41e5ef8abaf6c387aa069be66b5c', '', '2018-04-23 23:04:40');
INSERT INTO `opslog` VALUES ('141', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '出售', '0xdfba23cb51f5d218f44125956b66a77bba2e63175b62dc5b008f1291e1d8252f', '', '2018-04-23 23:18:53');
INSERT INTO `opslog` VALUES ('142', '0xc1e9c199914ead27ed8ffcdb24fcf247326ed2c3', '取消出售', '0xe754bcead7c2a51a6d514b19a17dfa07cbbf487bb1e9bb25657ddc7618c37514', '', '2018-04-23 23:29:25');
INSERT INTO `opslog` VALUES ('143', '0xc361a52154e155c971ca527085ce917f031e4e85', '设置渔场名称', '', '', '2018-04-24 10:55:07');
INSERT INTO `opslog` VALUES ('144', '0x0299345e865858fee01dc4e2494059b74641224d', '出售', '0x5435aecee15fcd9d8917873a87bc51207e795b48a432a50a3ccf1ff268b14537', '', '2018-04-24 11:53:25');
INSERT INTO `opslog` VALUES ('145', '0x07dcb393f26fc4336ac34b0d17ac1af350249a51', '出售', '0x63c41699229105979df647c6e1629e4f168c9fddc9ec1e5e5bc8056c94ceb91e', '', '2018-04-24 14:10:05');
INSERT INTO `opslog` VALUES ('146', '0x07dcb393f26fc4336ac34b0d17ac1af350249a51', '出售', '0x9c5486fb05fbd30af990a355591d81c7a906bb5919abc9c8aa2791a666054adc', '', '2018-04-24 14:10:44');
INSERT INTO `opslog` VALUES ('147', '0x07dcb393f26fc4336ac34b0d17ac1af350249a51', '出售', '0xbebf26b2dd39452bc706f8bef75106b85e6265c4c82040a6ffb80fe29fa075ff', '', '2018-04-24 14:12:02');
INSERT INTO `opslog` VALUES ('148', '0x0299345e865858fee01dc4e2494059b74641224d', '出售', '0xdbe2de2c50274e7c3865215cd73461dcb70bf58ba70d1e5797179caca4a70f13', '', '2018-04-24 20:23:54');
INSERT INTO `opslog` VALUES ('149', '0x0299345e865858fee01dc4e2494059b74641224d', '出售', '0x3007dbe9388e629e306a80c188ab9fee4b3eb5bee4085194bd199304824a0f00', '', '2018-04-24 20:24:20');
INSERT INTO `opslog` VALUES ('150', '0x0299345e865858fee01dc4e2494059b74641224d', '出售', '0xcecd9914d90a49cddada946bc247ce98f1f5bd25592c73b5960b7ca73576ea5b', '', '2018-04-24 20:30:28');
INSERT INTO `opslog` VALUES ('151', '0x0299345e865858fee01dc4e2494059b74641224d', '出售', '0xf1e21ecd04a7228b6fe3dc79ad954f2eeec8d449ecde9ed3a5d1beebc77b921b', '', '2018-04-24 20:32:51');
INSERT INTO `opslog` VALUES ('152', '0x0299345e865858fee01dc4e2494059b74641224d', '出售', '0x0f037ba91e611fbc4f6152196c8b64733d09615f1dd4b470c04de572dcd617ed', '', '2018-04-24 20:35:11');
INSERT INTO `opslog` VALUES ('153', '0xc361a52154e155c971ca527085ce917f031e4e85', '设置渔场名称', '', '', '2018-04-26 13:06:01');
INSERT INTO `opslog` VALUES ('154', '0xc361a52154e155c971ca527085ce917f031e4e85', '设置渔场名称', '', '', '2018-04-26 13:06:43');
INSERT INTO `opslog` VALUES ('155', '0xc361a52154e155c971ca527085ce917f031e4e85', '设置渔场名称', '', '', '2018-04-26 13:07:39');
INSERT INTO `opslog` VALUES ('156', '0xc361a52154e155c971ca527085ce917f031e4e85', '设置渔场名称', '', '', '2018-04-26 13:09:29');
INSERT INTO `opslog` VALUES ('157', '0xc361a52154e155c971ca527085ce917f031e4e85', '设置渔场名称', '', '', '2018-04-26 13:10:09');
INSERT INTO `opslog` VALUES ('158', '0xc361a52154e155c971ca527085ce917f031e4e85', '设置渔场名称', '', '', '2018-04-26 13:10:14');
INSERT INTO `opslog` VALUES ('159', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123123123123', null, '2018-04-26 15:13:02');
INSERT INTO `opslog` VALUES ('160', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123123123123', null, '2018-04-26 15:13:22');
INSERT INTO `opslog` VALUES ('161', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123123123123', null, '2018-04-26 15:13:41');
INSERT INTO `opslog` VALUES ('162', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123123123123', null, '2018-04-26 15:14:27');
INSERT INTO `opslog` VALUES ('163', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', null, '2018-04-26 15:15:13');
INSERT INTO `opslog` VALUES ('164', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '设置渔场名称', '', '', '2018-04-26 15:19:39');
INSERT INTO `opslog` VALUES ('165', '0xa2e25f782a0f616f0432c8d13b6f0c5e56b455b2', '设置渔场名称', '', '', '2018-04-26 15:19:47');
INSERT INTO `opslog` VALUES ('166', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', null, '2018-04-26 15:24:04');
INSERT INTO `opslog` VALUES ('167', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', null, '2018-04-26 15:24:17');
INSERT INTO `opslog` VALUES ('168', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', null, '2018-04-26 15:24:25');
INSERT INTO `opslog` VALUES ('169', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', null, '2018-04-26 15:24:29');
INSERT INTO `opslog` VALUES ('170', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', null, '2018-04-26 15:24:38');
INSERT INTO `opslog` VALUES ('171', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', null, '2018-04-26 15:24:45');
INSERT INTO `opslog` VALUES ('172', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', null, '2018-04-26 15:24:48');
INSERT INTO `opslog` VALUES ('173', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', null, '2018-04-26 15:24:49');
INSERT INTO `opslog` VALUES ('174', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', null, '2018-04-26 15:24:50');
INSERT INTO `opslog` VALUES ('175', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', null, '2018-04-26 15:24:50');
INSERT INTO `opslog` VALUES ('176', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', null, '2018-04-26 15:24:51');
INSERT INTO `opslog` VALUES ('177', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', null, '2018-04-26 15:24:51');
INSERT INTO `opslog` VALUES ('178', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', null, '2018-04-26 15:24:52');
INSERT INTO `opslog` VALUES ('179', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', null, '2018-04-26 15:25:21');
INSERT INTO `opslog` VALUES ('180', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:14');
INSERT INTO `opslog` VALUES ('181', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:20');
INSERT INTO `opslog` VALUES ('182', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:22');
INSERT INTO `opslog` VALUES ('183', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:22');
INSERT INTO `opslog` VALUES ('184', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:23');
INSERT INTO `opslog` VALUES ('185', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:23');
INSERT INTO `opslog` VALUES ('186', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:24');
INSERT INTO `opslog` VALUES ('187', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:27');
INSERT INTO `opslog` VALUES ('188', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:28');
INSERT INTO `opslog` VALUES ('189', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:29');
INSERT INTO `opslog` VALUES ('190', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:29');
INSERT INTO `opslog` VALUES ('191', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:30');
INSERT INTO `opslog` VALUES ('192', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:30');
INSERT INTO `opslog` VALUES ('193', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:31');
INSERT INTO `opslog` VALUES ('194', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:31');
INSERT INTO `opslog` VALUES ('195', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:33');
INSERT INTO `opslog` VALUES ('196', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:34');
INSERT INTO `opslog` VALUES ('197', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:35');
INSERT INTO `opslog` VALUES ('198', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:35');
INSERT INTO `opslog` VALUES ('199', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:36');
INSERT INTO `opslog` VALUES ('200', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:26:36');
INSERT INTO `opslog` VALUES ('201', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:28:24');
INSERT INTO `opslog` VALUES ('202', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:29:02');
INSERT INTO `opslog` VALUES ('203', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '绑定游戏地址', '123123123123', 'sdfasdf', '2018-04-26 15:29:08');
INSERT INTO `opslog` VALUES ('204', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:22');
INSERT INTO `opslog` VALUES ('205', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:25');
INSERT INTO `opslog` VALUES ('206', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:26');
INSERT INTO `opslog` VALUES ('207', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:45');
INSERT INTO `opslog` VALUES ('208', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:46');
INSERT INTO `opslog` VALUES ('209', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:47');
INSERT INTO `opslog` VALUES ('210', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:48');
INSERT INTO `opslog` VALUES ('211', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:48');
INSERT INTO `opslog` VALUES ('212', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:49');
INSERT INTO `opslog` VALUES ('213', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:50');
INSERT INTO `opslog` VALUES ('214', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:50');
INSERT INTO `opslog` VALUES ('215', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:51');
INSERT INTO `opslog` VALUES ('216', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:51');
INSERT INTO `opslog` VALUES ('217', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:52');
INSERT INTO `opslog` VALUES ('218', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:52');
INSERT INTO `opslog` VALUES ('219', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:53');
INSERT INTO `opslog` VALUES ('220', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:53');
INSERT INTO `opslog` VALUES ('221', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:54');
INSERT INTO `opslog` VALUES ('222', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:54');
INSERT INTO `opslog` VALUES ('223', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:54');
INSERT INTO `opslog` VALUES ('224', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:55');
INSERT INTO `opslog` VALUES ('225', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:55');
INSERT INTO `opslog` VALUES ('226', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:56');
INSERT INTO `opslog` VALUES ('227', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:56');
INSERT INTO `opslog` VALUES ('228', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:56');
INSERT INTO `opslog` VALUES ('229', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:57');
INSERT INTO `opslog` VALUES ('230', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:57');
INSERT INTO `opslog` VALUES ('231', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B5', '取消绑定', '123123123123', 'sdfasdf', '2018-04-26 15:37:58');
INSERT INTO `opslog` VALUES ('232', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售', '123123123123', null, '2018-04-26 15:40:35');
INSERT INTO `opslog` VALUES ('233', '1', '购买渔场', 'ok', 'good', '2018-04-27 00:14:12');
INSERT INTO `opslog` VALUES ('234', '22222', '赠送渔场', null, '渔场ID:111 ,赠送地址:22222', '2018-04-27 01:02:19');
INSERT INTO `opslog` VALUES ('235', '22222', '赠送渔场', null, '渔场ID:111 ,赠送地址:22222', '2018-04-27 01:02:36');
INSERT INTO `opslog` VALUES ('236', '221112', '出售渔场', 'ok', '渔场ID:1,起始价格:10.01, 终止价格:11.55, 销售时长:8000', '2018-04-27 01:03:02');
INSERT INTO `opslog` VALUES ('237', '221112', '取消出售渔场', 'ok', '渔场ID:1', '2018-04-27 01:03:14');
INSERT INTO `opslog` VALUES ('238', '221112', '绑定渔场', 'ok', '渔场ID:999 ,绑定地址:221112', '2018-04-27 01:03:54');
INSERT INTO `opslog` VALUES ('239', '1', '购买新渔场', 'ok', 'good', '2018-04-27 01:04:21');
INSERT INTO `opslog` VALUES ('240', '1', '购买新渔场', 'ok', 'good', '2018-04-27 01:06:37');
INSERT INTO `opslog` VALUES ('241', '1', '购买新渔场', 'ok', null, '2018-04-27 01:28:04');
INSERT INTO `opslog` VALUES ('242', '1', '购买新渔场', 'ok', null, '2018-04-27 01:28:07');
INSERT INTO `opslog` VALUES ('243', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x123', '2018-04-27 10:51:55');
INSERT INTO `opslog` VALUES ('244', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x123', '2018-04-27 10:52:00');
INSERT INTO `opslog` VALUES ('245', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x123', '2018-04-27 10:52:01');
INSERT INTO `opslog` VALUES ('246', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x123', '2018-04-27 10:52:01');
INSERT INTO `opslog` VALUES ('247', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x123', '2018-04-27 10:52:03');
INSERT INTO `opslog` VALUES ('248', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x123', '2018-04-27 10:52:20');
INSERT INTO `opslog` VALUES ('249', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x123', '2018-04-27 10:52:21');
INSERT INTO `opslog` VALUES ('250', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x123', '2018-04-27 10:52:28');
INSERT INTO `opslog` VALUES ('251', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x123', '2018-04-27 10:52:32');
INSERT INTO `opslog` VALUES ('252', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x123', '2018-04-27 10:52:36');
INSERT INTO `opslog` VALUES ('253', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x123', '2018-04-27 10:53:53');
INSERT INTO `opslog` VALUES ('254', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x123', '2018-04-27 10:53:55');
INSERT INTO `opslog` VALUES ('255', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x123', '2018-04-27 10:53:56');
INSERT INTO `opslog` VALUES ('256', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x1234566', '2018-04-27 10:53:59');
INSERT INTO `opslog` VALUES ('257', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x1234566', '2018-04-27 10:54:00');
INSERT INTO `opslog` VALUES ('258', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x1234566', '2018-04-27 10:54:01');
INSERT INTO `opslog` VALUES ('259', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x1234566', '2018-04-27 10:54:02');
INSERT INTO `opslog` VALUES ('260', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x1234566', '2018-04-27 10:54:02');
INSERT INTO `opslog` VALUES ('261', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x1234566', '2018-04-27 10:54:03');
INSERT INTO `opslog` VALUES ('262', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x1234566', '2018-04-27 10:54:04');
INSERT INTO `opslog` VALUES ('263', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x1234566', '2018-04-27 10:54:04');
INSERT INTO `opslog` VALUES ('264', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x1234566', '2018-04-27 10:54:05');
INSERT INTO `opslog` VALUES ('265', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '解除渔场绑定', '123123123123', '渔场ID:123', '2018-04-27 10:56:20');
INSERT INTO `opslog` VALUES ('266', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '解除渔场绑定', '123123123123', '渔场ID:123', '2018-04-27 10:56:26');
INSERT INTO `opslog` VALUES ('267', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '解除渔场绑定', '123123123123', '渔场ID:123', '2018-04-27 10:56:27');
INSERT INTO `opslog` VALUES ('268', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '解除渔场绑定', '123123123123', '渔场ID:123', '2018-04-27 10:56:28');
INSERT INTO `opslog` VALUES ('269', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '解除渔场绑定', '123123123123', '渔场ID:123', '2018-04-27 10:56:29');
INSERT INTO `opslog` VALUES ('270', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '解除渔场绑定', '123123123123', '渔场ID:123', '2018-04-27 10:56:29');
INSERT INTO `opslog` VALUES ('271', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '解除渔场绑定', '123123123123', '渔场ID:123', '2018-04-27 10:56:30');
INSERT INTO `opslog` VALUES ('272', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '解除渔场绑定', '123123123123', '渔场ID:123', '2018-04-27 10:56:31');
INSERT INTO `opslog` VALUES ('273', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '解除渔场绑定', '123123123123', '渔场ID:123', '2018-04-27 10:56:31');
INSERT INTO `opslog` VALUES ('274', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '解除渔场绑定', '123123123123', '渔场ID:123', '2018-04-27 10:56:31');
INSERT INTO `opslog` VALUES ('275', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '解除渔场绑定', '123123123123', '渔场ID:123', '2018-04-27 10:56:32');
INSERT INTO `opslog` VALUES ('276', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '解除渔场绑定', '123123123123', '渔场ID:123', '2018-04-27 10:56:33');
INSERT INTO `opslog` VALUES ('277', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定渔场', '123123123123', '渔场ID:123 ,绑定地址:0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '2018-04-27 10:56:57');
INSERT INTO `opslog` VALUES ('278', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定渔场', '123123123123', '渔场ID:123 ,绑定地址:0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '2018-04-27 10:57:15');
INSERT INTO `opslog` VALUES ('279', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定渔场', '123123123123', '渔场ID:678 ,绑定地址:0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '2018-04-27 10:57:22');
INSERT INTO `opslog` VALUES ('280', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定渔场', '123123123123', '渔场ID:678 ,绑定地址:0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '2018-04-27 10:57:26');
INSERT INTO `opslog` VALUES ('281', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定渔场', '123123123123', '渔场ID:678 ,绑定地址:0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '2018-04-27 10:57:26');
INSERT INTO `opslog` VALUES ('282', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定渔场', '123123123123', '渔场ID:678 ,绑定地址:0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '2018-04-27 10:57:27');
INSERT INTO `opslog` VALUES ('283', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '取消出售渔场', '123123123123', '渔场ID:null', '2018-04-27 10:59:09');
INSERT INTO `opslog` VALUES ('284', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '取消出售渔场', '123123123123', '渔场ID:null', '2018-04-27 10:59:17');
INSERT INTO `opslog` VALUES ('285', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '取消出售渔场', '123123123123', '渔场ID:123', '2018-04-27 11:00:29');
INSERT INTO `opslog` VALUES ('286', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x1234566', '2018-04-27 11:00:40');
INSERT INTO `opslog` VALUES ('287', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x1234566', '2018-04-27 11:00:44');
INSERT INTO `opslog` VALUES ('288', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x1234566', '2018-04-27 11:00:44');
INSERT INTO `opslog` VALUES ('289', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:0 ,赠送地址:0x1234566', '2018-04-27 11:00:45');
INSERT INTO `opslog` VALUES ('290', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:4 ,赠送地址:0x1234566', '2018-04-27 11:01:00');
INSERT INTO `opslog` VALUES ('291', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:4 ,赠送地址:0x1234566', '2018-04-27 11:01:01');
INSERT INTO `opslog` VALUES ('292', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:4 ,赠送地址:0x1234566', '2018-04-27 11:01:01');
INSERT INTO `opslog` VALUES ('293', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:4 ,赠送地址:0x1234566', '2018-04-27 11:01:31');
INSERT INTO `opslog` VALUES ('294', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:4 ,赠送地址:0x1234566', '2018-04-27 11:01:32');
INSERT INTO `opslog` VALUES ('295', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '取消出售渔场', '123123123123', '渔场ID:123', '2018-04-27 11:01:38');
INSERT INTO `opslog` VALUES ('296', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123, 终止价格:345, 销售时长:1234', '2018-04-27 11:03:26');
INSERT INTO `opslog` VALUES ('297', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123, 终止价格:345, 销售时长:234', '2018-04-27 11:03:50');
INSERT INTO `opslog` VALUES ('298', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123, 终止价格:345, 销售时长:234', '2018-04-27 11:03:56');
INSERT INTO `opslog` VALUES ('299', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123, 终止价格:345, 销售时长:234', '2018-04-27 11:03:57');
INSERT INTO `opslog` VALUES ('300', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123, 终止价格:345, 销售时长:234', '2018-04-27 11:03:58');
INSERT INTO `opslog` VALUES ('301', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123, 终止价格:345, 销售时长:234', '2018-04-27 11:03:59');
INSERT INTO `opslog` VALUES ('302', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123, 终止价格:345, 销售时长:234', '2018-04-27 11:03:59');
INSERT INTO `opslog` VALUES ('303', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123, 终止价格:345, 销售时长:234', '2018-04-27 11:04:00');
INSERT INTO `opslog` VALUES ('304', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123, 终止价格:345, 销售时长:234', '2018-04-27 11:04:00');
INSERT INTO `opslog` VALUES ('305', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123, 终止价格:345, 销售时长:234', '2018-04-27 11:04:01');
INSERT INTO `opslog` VALUES ('306', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123, 终止价格:345, 销售时长:234', '2018-04-27 11:04:01');
INSERT INTO `opslog` VALUES ('307', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123, 终止价格:345, 销售时长:234', '2018-04-27 11:04:01');
INSERT INTO `opslog` VALUES ('308', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123, 终止价格:345, 销售时长:234', '2018-04-27 11:04:02');
INSERT INTO `opslog` VALUES ('309', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123, 终止价格:345, 销售时长:2', '2018-04-27 11:04:38');
INSERT INTO `opslog` VALUES ('310', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123, 终止价格:345.0, 销售时长:2', '2018-04-27 11:04:43');
INSERT INTO `opslog` VALUES ('311', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123, 终止价格:345.5656, 销售时长:2', '2018-04-27 11:04:49');
INSERT INTO `opslog` VALUES ('312', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123.343, 终止价格:345.5656, 销售时长:2', '2018-04-27 11:04:55');
INSERT INTO `opslog` VALUES ('313', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', '渔场ID:80000', '2018-04-27 11:07:18');
INSERT INTO `opslog` VALUES ('314', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', '渔场ID:80000', '2018-04-27 11:11:26');
INSERT INTO `opslog` VALUES ('315', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', '渔场ID:80000', '2018-04-27 11:11:44');
INSERT INTO `opslog` VALUES ('316', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', '渔场ID:80000', '2018-04-27 11:12:06');
INSERT INTO `opslog` VALUES ('317', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', '渔场ID:234', '2018-04-27 11:12:20');
INSERT INTO `opslog` VALUES ('318', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', '渔场ID:890', '2018-04-27 11:12:28');
INSERT INTO `opslog` VALUES ('319', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '出售渔场', '123123123123', '渔场ID:123,起始价格:123.343, 终止价格:345.5656, 销售时长:2', '2018-04-27 11:14:34');
INSERT INTO `opslog` VALUES ('320', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '取消出售渔场', '123123123123', '渔场ID:123', '2018-04-27 11:14:46');
INSERT INTO `opslog` VALUES ('321', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '赠送渔场', '123123123123', '渔场ID:4 ,赠送地址:0x1234566', '2018-04-27 11:14:57');
INSERT INTO `opslog` VALUES ('322', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '绑定渔场', '123123123123', '渔场ID:678 ,绑定地址:0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '2018-04-27 11:15:12');
INSERT INTO `opslog` VALUES ('323', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '解除渔场绑定', '123123123123', '渔场ID:123', '2018-04-27 11:15:27');
INSERT INTO `opslog` VALUES ('324', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', '渔场ID:890', '2018-04-27 11:16:49');
INSERT INTO `opslog` VALUES ('325', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买渔场', '123nksdjfasjdfadsf', '渔场ID:890', '2018-04-27 11:17:05');
INSERT INTO `opslog` VALUES ('326', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买新渔场', '123nksdjfasjdfadsf', null, '2018-04-27 11:17:19');
INSERT INTO `opslog` VALUES ('327', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买新渔场', '123nksdjfasjdfadsf', null, '2018-04-27 11:17:56');
INSERT INTO `opslog` VALUES ('328', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买新渔场', '123nksdjfasjdfadsf', null, '2018-04-27 11:18:02');
INSERT INTO `opslog` VALUES ('329', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', '购买新渔场', '123nksdjfasjdfadsf', null, '2018-04-27 11:18:03');
INSERT INTO `opslog` VALUES ('330', '0x4229040BF34c86fc493b012D0cFc9e62A570C141222', '赠送渔场', null, '渔场ID:11111 ,赠送地址:com.myweb.vo.FisherySend@229cb834', '2018-04-27 12:19:37');
INSERT INTO `opslog` VALUES ('331', '0x4229040BF34c86fc493b012D0cFc9e62A570C141222', '赠送渔场', null, '渔场ID:11111 ,赠送地址:11111111111111111111111111', '2018-04-27 12:20:36');
INSERT INTO `opslog` VALUES ('332', '0x4229040BF34c86fc493b012D0cFc9e62A570C141222', '赠送渔场', null, '渔场ID:11111 ,赠送地址:11111111111111111111111111', '2018-04-27 12:20:52');
INSERT INTO `opslog` VALUES ('333', '0x4229040BF34c86fc493b012D0cFc9e62A570C141222', '出售渔场', null, '渔场ID:null,起始价格:null, 终止价格:null, 销售时长:null天', '2018-04-27 12:21:00');
INSERT INTO `opslog` VALUES ('334', '11111111111111111111111111', '出售渔场', null, '渔场ID:null,起始价格:null, 终止价格:null, 销售时长:1.32天', '2018-04-27 12:25:20');
INSERT INTO `opslog` VALUES ('335', '11111111111111111111111111', '出售渔场', null, '渔场ID:11111,起始价格:null, 终止价格:null, 销售时长:1.32天', '2018-04-27 12:25:30');

-- ----------------------------
-- Table structure for refer
-- ----------------------------
DROP TABLE IF EXISTS `refer`;
CREATE TABLE `refer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `txhash` varchar(255) DEFAULT NULL,
  `refer_code` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `refer_fee` decimal(10,4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of refer
-- ----------------------------
INSERT INTO `refer` VALUES ('1', null, 'NNoxWNqV', null, null, '1.8500');
INSERT INTO `refer` VALUES ('2', null, 'NNoxWNqV', null, null, '3.5300');
INSERT INTO `refer` VALUES ('3', '123123123123', 'tjmxqsgc', 'buy', '2018-04-26 15:13:22', '10.1000');
INSERT INTO `refer` VALUES ('4', '123123123123', 'F7kAHvLk', 'buy', '2018-04-26 15:13:41', '10.1000');
INSERT INTO `refer` VALUES ('5', '123123123123', 'F7kAHvLk', 'buy', '2018-04-26 15:14:27', '10.1000');
INSERT INTO `refer` VALUES ('6', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-26 15:15:13', '10.1000');
INSERT INTO `refer` VALUES ('7', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-26 15:24:04', '10.1000');
INSERT INTO `refer` VALUES ('8', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-26 15:24:29', '10.1000');
INSERT INTO `refer` VALUES ('9', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-26 15:24:38', '10.1000');
INSERT INTO `refer` VALUES ('10', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-26 15:24:45', '10.1000');
INSERT INTO `refer` VALUES ('11', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-26 15:24:48', '10.1000');
INSERT INTO `refer` VALUES ('12', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-26 15:24:49', '10.1000');
INSERT INTO `refer` VALUES ('13', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-26 15:24:50', '10.1000');
INSERT INTO `refer` VALUES ('14', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-26 15:24:50', '10.1000');
INSERT INTO `refer` VALUES ('15', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-26 15:24:51', '10.1000');
INSERT INTO `refer` VALUES ('16', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-26 15:24:51', '10.1000');
INSERT INTO `refer` VALUES ('17', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-26 15:24:52', '10.1000');
INSERT INTO `refer` VALUES ('18', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-27 11:07:18', '10.1000');
INSERT INTO `refer` VALUES ('19', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-27 11:11:26', '10.1000');
INSERT INTO `refer` VALUES ('20', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-27 11:11:44', '10.1000');
INSERT INTO `refer` VALUES ('21', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-27 11:12:06', '10.1000');
INSERT INTO `refer` VALUES ('22', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-27 11:12:20', '10.1000');
INSERT INTO `refer` VALUES ('23', '123nksdjfasjdfadsf', 'F7kAHvLk', 'buy', '2018-04-27 11:12:28', '10.1000');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `sign_message` varchar(255) DEFAULT NULL,
  `signed_message` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `refer_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '11111:', '11111:', '11111', 'come', 'F:\\img\\oZxpZO7q.jpg', 'NNoxWNqV');
INSERT INTO `user` VALUES ('2', '11111', '11111', '11111', null, 'F:\\img\\hfwmp2rL.jpg', 'SUfKRom0');
INSERT INTO `user` VALUES ('3', '11111', '11111', '11111', null, null, 'bJDf4xq3');
INSERT INTO `user` VALUES ('5', '11111', '11111', '11111', null, null, 'hr1agJag');
INSERT INTO `user` VALUES ('6', '11111', '11111', '11111', null, null, 'BNKsYEMC');
INSERT INTO `user` VALUES ('7', '11111', '11111', '11111', null, null, 'XUVnq12k');
INSERT INTO `user` VALUES ('8', '11111', '11111', '11111', null, null, 'zr9DGjBO');
INSERT INTO `user` VALUES ('9', '11111', '11111', '11111', null, null, 'VAMyknYJ');
INSERT INTO `user` VALUES ('10', '11111', '11111', '11111', null, null, 'NlfnHyqY');
INSERT INTO `user` VALUES ('11', '111112', '11111', '11111', null, null, 'zliHOBBv');
INSERT INTO `user` VALUES ('12', '222', '11111', '11111', 'come', null, 'kLTMoRMC');
INSERT INTO `user` VALUES ('13', '221112', '11111', '11111', 'sscomse', 'u1Yc7tDC.jpg', 'DgTWNrpq');
INSERT INTO `user` VALUES ('14', '2211122', '11111', '11111', 'come', null, 'Tg77rXWz');
INSERT INTO `user` VALUES ('15', '0x12b63c328a45ed38ea266461bb6b3db2f8ce14ab', 'fishone', 'web3jiami', null, null, 'Luij4Kp3');
INSERT INTO `user` VALUES ('16', '221332', null, null, null, null, 'JzEiMrkZ');
INSERT INTO `user` VALUES ('17', '22132', null, null, null, null, '5aUmWcZq');
INSERT INTO `user` VALUES ('18', '0x123454', null, null, null, null, 'vhggTHac');
INSERT INTO `user` VALUES ('19', '0x16aBD7314865fDE554Cf8FBfD1D788aD709bA976', null, null, null, null, 'yeWJR9vF');
INSERT INTO `user` VALUES ('20', '0x16aBD7314865fDE554Cf8FBfD1D788aD709bA976', null, null, null, null, 'wWvg9wD5');
INSERT INTO `user` VALUES ('21', '?refer_code=yeWJR9vF', null, null, null, null, 'RBM8Qc7I');
INSERT INTO `user` VALUES ('22', '6540', null, null, null, null, 'xAM5p6X8');
INSERT INTO `user` VALUES ('23', '123123132', null, null, null, null, 'FiZIzn8W');
INSERT INTO `user` VALUES ('24', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B2', null, null, '123', 'YqlFsAQ7.png', 'F7kAHvLk');
INSERT INTO `user` VALUES ('25', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B', null, null, null, null, 'vVtHxMT2');
INSERT INTO `user` VALUES ('26', '0x4229040BF34c86fc493b012D0cFc9e62A570C141', null, null, '1232232', null, 'uGSEGiwg');
INSERT INTO `user` VALUES ('27', '0x03d4b8361BC46Ff64f288FDAed502E8C5Ff54815', null, null, null, null, 'iee6CRU5');
INSERT INTO `user` VALUES ('28', '111', null, null, null, null, 'MccAZnOR');
INSERT INTO `user` VALUES ('29', '0xA2e25F782a0F616f0432C8d13B6F0C5e56B455B1', null, null, null, null, '3JyPgNJA');
INSERT INTO `user` VALUES ('30', '0x07dcb393f26fc4336ac34b0d17ac1af350249a51', null, null, 'FishOne小助手', null, 'NMM5LDaN');
INSERT INTO `user` VALUES ('31', '0x0299345e865858fee01dc4e2494059b74641224d', null, null, null, null, 'FeMN2Ek6');
INSERT INTO `user` VALUES ('32', '0x66088434727c0d9529417a311edcba54e9218a9d', null, null, null, null, '5bVWLkaf');
INSERT INTO `user` VALUES ('33', '0x8edf4e5557e1d1bb5c0ba78af44fcfd76a8dacb4', null, null, null, null, 'vpteR5a8');
INSERT INTO `user` VALUES ('34', '0xc361a52154e155c971ca527085ce917f031e4e85', null, null, '123', '3LHecyi1.png', '3aIRHfjW');
INSERT INTO `user` VALUES ('35', '0x1804afa67da91d5ae34b14894f5dabff77679758', null, null, null, null, 'DYmZ6KPv');
INSERT INTO `user` VALUES ('36', '0x499f1c989698dd05cc8494507fc5dac06b21ff4f', null, null, null, null, 's1AosgGd');
