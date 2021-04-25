/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80022
Source Host           : localhost:3306
Source Database       : testol

Target Server Type    : MYSQL
Target Server Version : 80022
File Encoding         : 65001

Date: 2021-04-25 21:33:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for approval
-- ----------------------------
DROP TABLE IF EXISTS `approval`;
CREATE TABLE `approval` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `student_id` int NOT NULL COMMENT '申请人id',
  `teacher_id` int NOT NULL COMMENT '教师id',
  `classes_id` int NOT NULL COMMENT '班级id',
  `apply_date` datetime DEFAULT NULL COMMENT '申请时间',
  `status` int NOT NULL DEFAULT '0' COMMENT '状态0：审批中，1：同意，2：拒绝',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of approval
-- ----------------------------
INSERT INTO `approval` VALUES ('2', '2', '1', '27', '2021-04-24 21:13:41', '0');
INSERT INTO `approval` VALUES ('7', '3', '1', '27', '2021-04-24 21:06:54', '0');
INSERT INTO `approval` VALUES ('8', '7', '1', '27', '2021-04-24 21:07:27', '0');

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `classes_id` int NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `classes_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名称',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `introduction` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '班级简介',
  `people_num` int DEFAULT NULL COMMENT '班级人数',
  `creator_id` int NOT NULL COMMENT '创建者id',
  `joinWay` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'all' COMMENT '班级加入方式  no:不允许加入 all:允许任何人加入  apply:需要管理员同意',
  PRIMARY KEY (`classes_id`),
  KEY `FK` (`creator_id`),
  CONSTRAINT `FK` FOREIGN KEY (`creator_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES ('1', 'test', null, '2020-11-07 13:47:01', '123456', '2', '1', 'all');
INSERT INTO `classes` VALUES ('2', 'test1', null, '2020-11-07 13:46:56', '我修改成功的第一个班级', '2', '1', 'apply');
INSERT INTO `classes` VALUES ('3', '122', null, '2020-11-07 15:56:47', '122', '0', '1', 'no');
INSERT INTO `classes` VALUES ('4', '我创建的第一个班级', null, '2020-11-17 18:23:48', '', '1', '1', 'all');
INSERT INTO `classes` VALUES ('6', 'java', null, '2020-11-17 19:12:25', '', '1', '1', 'all');
INSERT INTO `classes` VALUES ('7', 'java_ee', null, '2020-11-17 19:17:05', '', '1', '1', 'all');
INSERT INTO `classes` VALUES ('8', '关联成功的第一个班级', null, '2020-11-17 19:19:22', '', '1', '1', 'all');
INSERT INTO `classes` VALUES ('9', '关联成功的第一个班级', null, '2020-11-17 19:23:24', '', '0', '1', 'all');
INSERT INTO `classes` VALUES ('10', '关联成功的第一个班级', null, '2020-11-17 19:26:15', '', '1', '1', 'all');
INSERT INTO `classes` VALUES ('11', '关联成功的第一个班级', null, '2020-11-17 19:28:23', 'abc\ndef', '2', '1', 'all');
INSERT INTO `classes` VALUES ('12', '事务完成的第一个班级', null, '2020-11-18 17:33:00', '', '1', '1', 'all');
INSERT INTO `classes` VALUES ('13', 'token成功的第一个班级', null, '2020-12-02 14:07:54', '无111', '1', '1', 'no');
INSERT INTO `classes` VALUES ('14', '演示创建的班级', null, '2021-01-04 22:51:03', '无', '0', '1', 'all');
INSERT INTO `classes` VALUES ('15', '禁止加入的班级', null, '2021-01-05 21:53:16', '无', '1', '1', 'no');
INSERT INTO `classes` VALUES ('16', '11111', '2021-03-07 18:03:25', '2021-03-07 18:03:25', '', '1', '1', 'all');
INSERT INTO `classes` VALUES ('17', '自动批改成功的班级', '2021-03-09 21:17:19', '2021-03-07 18:10:29', '阿斯蒂111', '2', '1', 'all');
INSERT INTO `classes` VALUES ('18', 'sdfsd', '2021-03-07 18:10:59', '2021-03-07 18:10:59', '', '2', '1', 'all');
INSERT INTO `classes` VALUES ('19', '1111222', '2021-03-07 19:40:56', '2021-03-07 19:20:07', 'ssss', '1', '1', '允许任何人加入');
INSERT INTO `classes` VALUES ('20', '的发射点', '2021-03-07 23:17:25', '2021-03-07 23:17:25', null, '2', '1', 'all');
INSERT INTO `classes` VALUES ('21', '1231321', '2021-03-07 23:41:15', '2021-03-07 23:41:15', '2认为人', '2', '1', 'all');
INSERT INTO `classes` VALUES ('22', '修改班级名称1', '2021-03-07 23:42:52', '2021-03-07 23:42:52', '士大夫感到', '2', '1', 'all');
INSERT INTO `classes` VALUES ('23', '检查创建班级名称', '2021-03-10 16:20:10', '2021-03-10 16:20:10', '安抚大使', '0', '1', 'all');
INSERT INTO `classes` VALUES ('24', '岁的法国', '2021-03-10 16:22:12', '2021-03-10 16:22:12', '啊沙发上', '1', '1', 'all');
INSERT INTO `classes` VALUES ('25', '演示创建的', '2021-03-11 10:04:56', '2021-03-11 10:04:56', '的方式敢死队风格', '1', '1', 'all');
INSERT INTO `classes` VALUES ('26', '测试12', '2021-04-02 09:16:32', '2021-04-02 09:16:32', '奥迪', '4', '1', 'all');
INSERT INTO `classes` VALUES ('27', '组件', '2021-04-10 22:42:41', '2021-04-10 22:42:41', '这是组件创建的班级123123', '0', '1', 'apply');
INSERT INTO `classes` VALUES ('28', '萨蒂符合', '2021-04-19 20:13:43', '2021-04-19 20:13:43', '阿斯蒂芬', '1', '1', 'no');

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `exam_id` int NOT NULL AUTO_INCREMENT COMMENT '试卷id',
  `exam_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '试卷名称',
  `creator_id` int unsigned NOT NULL COMMENT '创建者id',
  `time` int unsigned DEFAULT NULL COMMENT '答题时间(分钟)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `subject_id` int unsigned DEFAULT NULL COMMENT '科目类型id',
  `subject_name` varchar(255) DEFAULT NULL COMMENT '科目类型名称',
  `topic_num` int unsigned DEFAULT '0' COMMENT '题目数量',
  `total_score` float unsigned DEFAULT '0' COMMENT '总分',
  `pass_mark` float unsigned DEFAULT '0' COMMENT '及格分数',
  `permit_copy` int unsigned DEFAULT '1' COMMENT '是否允许复制  0:不允许  1:允许(默认)',
  `switch_page` int DEFAULT '-1' COMMENT '允许页面切换次数  -1: 允许多次切换  5: 默认',
  `disrupt_order` int unsigned DEFAULT '0' COMMENT '是否打乱题目顺序 0:不打乱(默认) 1:打乱顺序',
  `repeat_test` int unsigned DEFAULT '1' COMMENT '允许考生考试次数 默认1',
  `auto_mack` int unsigned DEFAULT '1' COMMENT '是否自动评分 0:否   1:是(默认)',
  PRIMARY KEY (`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES ('1', 'test', '1', '60', '2021-01-05 21:17:08', null, '1', null, '6', '100', '60', '0', '-1', '1', '3', '1');
INSERT INTO `exam` VALUES ('4', '填空题与简答题的自动评分', '1', '120', '2021-01-24 21:54:20', null, '1', null, '10', '120', '60', '0', '-1', '0', '1', '1');
INSERT INTO `exam` VALUES ('7', '新建的试卷', '1', '80', '2021-01-05 21:58:47', null, '1', null, '6', '100', '60', '0', '-1', '0', '1', '0');
INSERT INTO `exam` VALUES ('10', '验证题目乱序功能', '1', '60', '2021-01-28 16:22:03', null, '1', null, '10', '100', '0', '0', '6', '1', '1', '0');
INSERT INTO `exam` VALUES ('11', '测试成绩试卷成功后自动跳转url', '1', '60', '2021-01-28 15:05:33', null, '1', null, '1', '100', '0', '0', '-1', '0', '1', '0');
INSERT INTO `exam` VALUES ('12', '测试成绩试卷成功后自动跳转url2', '1', '60', '2021-01-28 15:10:21', null, '1', null, '1', '50', '0', '0', '-1', '1', '1', '1');
INSERT INTO `exam` VALUES ('13', '点击编辑试卷名称', '1', '60', '2021-03-08 15:29:05', '2021-03-08 15:29:05', null, null, '16', '0', null, '0', '-1', '0', '1', '0');
INSERT INTO `exam` VALUES ('14', '修改试卷名称', '1', '60', '2021-03-08 15:45:56', '2021-03-08 15:45:56', null, null, '2', '110', '0', '1', '-1', '0', '1', '1');
INSERT INTO `exam` VALUES ('15', '不想加班拉', '1', '10', '2021-03-11 10:12:43', '2021-03-11 10:12:43', null, null, '9', '120', '100', '0', '-1', '1', '1', '1');
INSERT INTO `exam` VALUES ('19', '测试试卷', '1', '60', '2021-04-02 09:27:17', '2021-04-02 09:33:20', null, null, '5', '50', '30', '0', '-1', '0', '1', '1');
INSERT INTO `exam` VALUES ('20', '试卷发布后不能修改', '1', '60', '2021-04-12 13:01:14', '2021-04-12 15:43:31', null, null, '1', '10', '10', '0', '-1', '0', '1', '1');
INSERT INTO `exam` VALUES ('21', '这是复制出来的试卷111', '1', '60', '2021-04-12 15:58:31', '2021-04-25 09:07:49', null, null, '6', '60', '30', '1', '1', '0', '1', '1');
INSERT INTO `exam` VALUES ('22', 'yashin', '1', '120', '2021-04-19 20:40:36', '2021-04-19 20:40:36', null, null, '5', '50', '10', '0', '-1', '0', '1', '0');
INSERT INTO `exam` VALUES ('23', '视频演示', '1', '120', '2021-04-19 21:04:38', '2021-04-19 21:04:38', null, null, '5', '45', '10', '0', '7', '1', '1', '1');
INSERT INTO `exam` VALUES ('24', '必填', '1', '60', '2021-04-25 09:12:20', '2021-04-25 10:03:18', null, null, '7', '70', '30', '0', '100', '1', '1', '1');

-- ----------------------------
-- Table structure for exam_classes
-- ----------------------------
DROP TABLE IF EXISTS `exam_classes`;
CREATE TABLE `exam_classes` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `exam_id` int NOT NULL COMMENT '考试id',
  `classes_id` int NOT NULL COMMENT '班级id',
  `release_time` datetime NOT NULL COMMENT '发布时间',
  `start_date` datetime NOT NULL COMMENT '开始时间',
  `deadline` datetime NOT NULL COMMENT '结束时间',
  `publish_answer` int(10) unsigned zerofill DEFAULT '0000000000' COMMENT '是否公布答案   0:不公布   1:公布答案',
  `publish_score` int(10) unsigned zerofill DEFAULT '0000000000' COMMENT '是否公布分数   0:不公布   1:公布',
  `status` int DEFAULT '0' COMMENT '状态 0:待批改 1:批改完成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_classes
-- ----------------------------
INSERT INTO `exam_classes` VALUES ('1', '1', '1', '2021-03-08 20:49:51', '2021-03-17 00:00:00', '2021-03-24 00:00:00', '0000000000', '0000000000', '0');
INSERT INTO `exam_classes` VALUES ('4', '4', '1', '2020-12-11 20:35:56', '2020-12-03 00:00:00', '2020-12-11 00:00:00', '0000000001', '0000000001', '0');
INSERT INTO `exam_classes` VALUES ('5', '4', '13', '2020-12-16 21:40:33', '2020-12-16 00:00:00', '2021-01-21 00:00:00', '0000000000', '0000000001', '0');
INSERT INTO `exam_classes` VALUES ('7', '7', '15', '2021-01-05 22:00:14', '2021-01-05 00:00:00', '2021-01-06 00:00:00', '0000000000', '0000000000', '0');
INSERT INTO `exam_classes` VALUES ('9', '1', '14', '2021-02-17 19:33:06', '2021-02-04 00:00:00', '2022-03-17 00:00:00', '0000000000', '0000000000', '0');
INSERT INTO `exam_classes` VALUES ('10', '7', '1', '2021-02-19 21:13:00', '2021-02-02 00:00:00', '2021-02-11 00:00:00', '0000000000', '0000000000', '0');
INSERT INTO `exam_classes` VALUES ('11', '1', '19', '2021-03-07 23:16:39', '2021-03-16 00:00:00', '2021-03-23 00:00:00', '0000000000', '0000000000', '0');
INSERT INTO `exam_classes` VALUES ('12', '1', '17', '2021-03-07 23:16:39', '2021-03-16 00:00:00', '2021-03-23 00:00:00', '0000000000', '0000000000', '0');
INSERT INTO `exam_classes` VALUES ('13', '10', '22', '2021-04-18 20:31:00', '2021-04-07 00:00:00', '2021-04-30 00:00:00', '0000000001', '0000000001', '0');
INSERT INTO `exam_classes` VALUES ('14', '4', '22', '2021-03-10 08:37:30', '2021-03-03 00:00:00', '2021-03-18 00:00:00', '0000000001', '0000000001', '0');
INSERT INTO `exam_classes` VALUES ('15', '4', '21', '2021-03-09 15:38:18', '2021-03-03 00:00:00', '2021-04-30 00:00:00', '0000000000', '0000000000', '0');
INSERT INTO `exam_classes` VALUES ('16', '4', '20', '2021-03-10 13:35:10', '2021-03-03 00:00:00', '2021-03-18 00:00:00', '0000000001', '0000000000', '0');
INSERT INTO `exam_classes` VALUES ('17', '4', '19', '2021-03-09 15:38:18', '2021-03-03 00:00:00', '2021-04-30 00:00:00', '0000000000', '0000000000', '0');
INSERT INTO `exam_classes` VALUES ('18', '4', '17', '2021-03-10 08:37:57', '2021-03-03 00:00:00', '2021-03-18 00:00:00', '0000000001', '0000000001', '0');
INSERT INTO `exam_classes` VALUES ('19', '14', '22', '2021-03-10 10:20:56', '2021-03-03 00:00:00', '2021-03-18 00:00:00', '0000000000', '0000000000', '0');
INSERT INTO `exam_classes` VALUES ('21', '12', '21', '2021-03-10 11:13:15', '2021-03-04 00:00:00', '2021-03-18 00:00:00', '0000000000', '0000000000', '0');
INSERT INTO `exam_classes` VALUES ('22', '15', '25', '2021-03-20 18:41:16', '2021-03-03 00:00:00', '2021-03-19 00:00:00', '0000000000', '0000000000', '0');
INSERT INTO `exam_classes` VALUES ('23', '15', '1', '2021-04-03 21:42:51', '2021-03-03 00:00:00', '2021-04-20 00:00:00', '0000000001', '0000000001', '0');
INSERT INTO `exam_classes` VALUES ('24', '1', '25', '2021-03-20 18:55:53', '2021-03-10 00:00:00', '2021-03-17 00:00:00', '0000000000', '0000000000', '0');
INSERT INTO `exam_classes` VALUES ('25', '19', '26', '2021-04-02 11:21:27', '2021-04-01 00:00:00', '2021-05-14 00:00:00', '0000000001', '0000000001', '0');
INSERT INTO `exam_classes` VALUES ('26', '1', '26', '2021-04-10 18:11:12', '2021-05-05 00:00:00', '2021-05-19 00:00:00', '0000000001', '0000000000', '0');
INSERT INTO `exam_classes` VALUES ('27', '22', '28', '2021-04-19 20:43:12', '2021-04-14 00:00:00', '2021-04-30 00:00:00', '0000000000', '0000000000', '0');
INSERT INTO `exam_classes` VALUES ('28', '23', '28', '2021-04-19 21:12:45', '2021-04-06 00:00:00', '2021-04-30 00:00:00', '0000000001', '0000000001', '0');
INSERT INTO `exam_classes` VALUES ('29', '24', '26', '2021-04-25 10:05:53', '2021-04-14 00:00:00', '2021-05-29 00:00:00', '0000000000', '0000000000', '0');

-- ----------------------------
-- Table structure for exam_topic
-- ----------------------------
DROP TABLE IF EXISTS `exam_topic`;
CREATE TABLE `exam_topic` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `exam_id` int NOT NULL COMMENT '考试id',
  `topic_id` int NOT NULL COMMENT '题目id',
  `topic_type` int NOT NULL COMMENT '题目类型  0:单选题 1:多选题 2:判断题 3:填空题 4:简答题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_topic
-- ----------------------------
INSERT INTO `exam_topic` VALUES ('1', '1', '1', '0');
INSERT INTO `exam_topic` VALUES ('3', '4', '3', '0');
INSERT INTO `exam_topic` VALUES ('4', '4', '4', '0');
INSERT INTO `exam_topic` VALUES ('5', '4', '5', '0');
INSERT INTO `exam_topic` VALUES ('6', '4', '6', '0');
INSERT INTO `exam_topic` VALUES ('7', '4', '7', '0');
INSERT INTO `exam_topic` VALUES ('11', '4', '11', '0');
INSERT INTO `exam_topic` VALUES ('12', '4', '12', '0');
INSERT INTO `exam_topic` VALUES ('18', '1', '18', '0');
INSERT INTO `exam_topic` VALUES ('19', '1', '19', '0');
INSERT INTO `exam_topic` VALUES ('20', '1', '20', '0');
INSERT INTO `exam_topic` VALUES ('21', '1', '21', '0');
INSERT INTO `exam_topic` VALUES ('22', '7', '22', '0');
INSERT INTO `exam_topic` VALUES ('23', '7', '23', '0');
INSERT INTO `exam_topic` VALUES ('24', '7', '24', '0');
INSERT INTO `exam_topic` VALUES ('25', '7', '25', '0');
INSERT INTO `exam_topic` VALUES ('26', '7', '26', '0');
INSERT INTO `exam_topic` VALUES ('27', '7', '27', '0');
INSERT INTO `exam_topic` VALUES ('28', '10', '28', '0');
INSERT INTO `exam_topic` VALUES ('29', '10', '29', '0');
INSERT INTO `exam_topic` VALUES ('30', '10', '30', '0');
INSERT INTO `exam_topic` VALUES ('31', '10', '31', '0');
INSERT INTO `exam_topic` VALUES ('32', '10', '32', '0');
INSERT INTO `exam_topic` VALUES ('33', '10', '33', '0');
INSERT INTO `exam_topic` VALUES ('34', '10', '34', '0');
INSERT INTO `exam_topic` VALUES ('35', '10', '35', '0');
INSERT INTO `exam_topic` VALUES ('36', '10', '36', '0');
INSERT INTO `exam_topic` VALUES ('37', '10', '37', '0');
INSERT INTO `exam_topic` VALUES ('38', '11', '38', '0');
INSERT INTO `exam_topic` VALUES ('39', '12', '39', '0');
INSERT INTO `exam_topic` VALUES ('41', '1', '41', '0');
INSERT INTO `exam_topic` VALUES ('42', '14', '42', '0');
INSERT INTO `exam_topic` VALUES ('43', '4', '43', '4');
INSERT INTO `exam_topic` VALUES ('44', '4', '44', '3');
INSERT INTO `exam_topic` VALUES ('45', '4', '45', '3');
INSERT INTO `exam_topic` VALUES ('46', '14', '46', '1');
INSERT INTO `exam_topic` VALUES ('47', '15', '47', '0');
INSERT INTO `exam_topic` VALUES ('48', '15', '48', '0');
INSERT INTO `exam_topic` VALUES ('49', '15', '49', '0');
INSERT INTO `exam_topic` VALUES ('51', '15', '51', '1');
INSERT INTO `exam_topic` VALUES ('52', '15', '52', '1');
INSERT INTO `exam_topic` VALUES ('53', '15', '53', '2');
INSERT INTO `exam_topic` VALUES ('54', '15', '54', '3');
INSERT INTO `exam_topic` VALUES ('55', '15', '55', '4');
INSERT INTO `exam_topic` VALUES ('56', '15', '56', '1');
INSERT INTO `exam_topic` VALUES ('57', '19', '57', '0');
INSERT INTO `exam_topic` VALUES ('58', '19', '58', '1');
INSERT INTO `exam_topic` VALUES ('59', '19', '59', '2');
INSERT INTO `exam_topic` VALUES ('60', '19', '60', '3');
INSERT INTO `exam_topic` VALUES ('61', '19', '61', '4');
INSERT INTO `exam_topic` VALUES ('64', '20', '64', '0');
INSERT INTO `exam_topic` VALUES ('65', '21', '65', '0');
INSERT INTO `exam_topic` VALUES ('67', '21', '67', '2');
INSERT INTO `exam_topic` VALUES ('68', '21', '68', '3');
INSERT INTO `exam_topic` VALUES ('69', '21', '69', '4');
INSERT INTO `exam_topic` VALUES ('70', '22', '70', '0');
INSERT INTO `exam_topic` VALUES ('71', '22', '71', '0');
INSERT INTO `exam_topic` VALUES ('72', '22', '72', '1');
INSERT INTO `exam_topic` VALUES ('73', '22', '73', '3');
INSERT INTO `exam_topic` VALUES ('74', '22', '74', '4');
INSERT INTO `exam_topic` VALUES ('75', '23', '75', '0');
INSERT INTO `exam_topic` VALUES ('76', '23', '76', '0');
INSERT INTO `exam_topic` VALUES ('77', '23', '77', '0');
INSERT INTO `exam_topic` VALUES ('78', '23', '78', '3');
INSERT INTO `exam_topic` VALUES ('79', '23', '79', '4');
INSERT INTO `exam_topic` VALUES ('80', '21', '80', '0');
INSERT INTO `exam_topic` VALUES ('81', '21', '81', '1');
INSERT INTO `exam_topic` VALUES ('82', '24', '82', '0');
INSERT INTO `exam_topic` VALUES ('83', '24', '83', '0');
INSERT INTO `exam_topic` VALUES ('84', '24', '84', '0');
INSERT INTO `exam_topic` VALUES ('85', '24', '85', '1');
INSERT INTO `exam_topic` VALUES ('86', '24', '86', '2');
INSERT INTO `exam_topic` VALUES ('87', '24', '87', '3');
INSERT INTO `exam_topic` VALUES ('88', '24', '88', '4');

-- ----------------------------
-- Table structure for subjects
-- ----------------------------
DROP TABLE IF EXISTS `subjects`;
CREATE TABLE `subjects` (
  `subjects_id` int NOT NULL AUTO_INCREMENT COMMENT '科目id',
  `subjects_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '科目名称',
  `create_id` int DEFAULT NULL COMMENT '创建者id',
  `create_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者名称',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`subjects_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subjects
-- ----------------------------
INSERT INTO `subjects` VALUES ('1', 'java', '1', '2020-11-28', null, null);
INSERT INTO `subjects` VALUES ('2', 'mysql', '1', '2020-11-29', null, null);

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `topic_id` int NOT NULL AUTO_INCREMENT COMMENT '题目id',
  `creator_id` int NOT NULL COMMENT '创建者id',
  `subject_id` int DEFAULT NULL COMMENT '科目类型id',
  `subject_name` varchar(255) DEFAULT NULL COMMENT '科目类型名称',
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '题目',
  `choice` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '选项',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片',
  `topic_type` int NOT NULL COMMENT '题目类型  0:单选题 1:多选题 2:判断题 3:填空题 4:简答题',
  `correct_answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '正确答案',
  `score` float DEFAULT '0' COMMENT '分数',
  `difficulty` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '中等' COMMENT '难度  简单,中等(默认),困难',
  `analysis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '答案分析',
  `required` int DEFAULT '0' COMMENT '非必填:0   必填:1',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`topic_id`),
  KEY `suoyin2` (`creator_id`) USING BTREE,
  CONSTRAINT `FK11` FOREIGN KEY (`creator_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('1', '1', '1', null, '我的第一个单选题', '选项1\n选项2', '1', '0', '选项1', '20', '中等', '答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('2', '1', '1', null, '我的第一个多选题', '选项1', '2', '1', '选项1', '10', '中等', '答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('3', '1', '1', null, '这是我第一次编辑试卷', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '20', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('4', '1', '1', null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '1', '选项3\n选项4', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('5', '1', '1', null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '2', 'true', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('6', '1', '1', null, '这是我___新建的题目___1111', '选项1\n选项2\n选项3\n选项4', null, '3', 'a\nb', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('7', '1', '1', null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '4', '无法\n宿舍', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('8', '1', '1', null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '0', '选项2', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('9', '1', '1', null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '2', '', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('10', '1', '1', null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '1', '选项1\n选项2\n选项3\n选项4', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('11', '1', '1', null, '这是我新建的题目`12', '选项1\n选项2\n选项3\n选项4', null, '0', '选项3', '20', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('12', '1', '1', null, '这是我新建的题目123', '选项1\n选项2\n选项3\n选项4', null, '4', '123\n999', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('18', '2', '1', null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '1', '选项2\n选项3', '20', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('19', '2', '1', null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '2', 'false', '20', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('20', '2', '1', null, '这是我新___建的题目', '选项1\n选项2\n选项3\n选项4', null, '3', '111', '20', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('21', '2', '1', null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '4', 'qqq', '20', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('22', '1', '1', null, '单选题11111111', '选项1\n选项2\n选项3\n选项4', null, '0', '选项2', '10', '简单', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('23', '1', '1', null, '单选题222222222', '选项1\n选项2\n选项3\n选项4', null, '0', '选项2', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('24', '1', '1', null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '1', '', '20', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('25', '1', '1', null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '2', '', '20', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('26', '1', '1', null, '这是我___新建的题目___', '选项1\n选项2\n选项3\n选项4', null, '3', '111\n222', '20', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('27', '1', '1', null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '4', '关键词1\n关键词2', '20', '困难', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('28', '1', '1', null, '这是我新建的题目1', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('29', '1', '1', null, '这是我新建的题目2', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('30', '1', '1', null, '这是我新建的题目3', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('31', '1', '1', null, '这是我新建的题目4', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('32', '1', '1', null, '这是我新建的题目5', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('33', '1', '1', null, '这是我新建的题目6', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('34', '1', '1', null, '这是我新建的题目7', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('35', '1', '1', null, '这是我新建的题目8', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('36', '1', '1', null, '这是我新建的题目9', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('37', '1', '1', null, '这是我新建的题目10', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '10', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('38', '1', '1', null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '0', '', '100', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('39', '1', '1', null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '50', '中等', '这是答案分析', '0', null, null);
INSERT INTO `topic` VALUES ('40', '1', '1', null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '0', '中等', '这是答案分析', '0', '2021-03-08 15:15:58', '2021-03-08 15:15:58');
INSERT INTO `topic` VALUES ('41', '1', '1', null, '这是我新建的题目2222222222222', '选项1\n选项2\n选项3\n选项4', null, '0', '选项3', '0', '中等', '这是答案分析', '0', '2021-03-08 15:16:55', '2021-03-08 15:16:55');
INSERT INTO `topic` VALUES ('42', '1', null, null, '这是我新建的题目啊啊啊啊啊啊啊啊', '选项1\n选项2\n选项3\n选项4', null, '0', '', '100', '中等', '这是答案分析', '0', '2021-03-08 15:45:56', '2021-03-08 15:45:56');
INSERT INTO `topic` VALUES ('43', '1', '1', null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '4', 'a\nb\nc', '10', '中等', '这是答案分析', '0', '2021-03-09 15:34:49', '2021-03-09 15:34:49');
INSERT INTO `topic` VALUES ('44', '1', '1', null, '这是我新建的题目___2222___', '选项1\n选项2\n选项3\n选项4', null, '3', '对对对\n冲冲冲', '10', '中等', '这是答案分析', '0', '2021-03-09 15:45:03', '2021-03-09 15:45:03');
INSERT INTO `topic` VALUES ('45', '1', '1', null, '___这是我新建的题目___33333333333', '选项1\n选项2\n选项3\n选项4', null, '3', 'aaa\nbbb', '10', '中等', '这是答案分析', '0', '2021-03-09 15:47:59', '2021-03-09 15:47:59');
INSERT INTO `topic` VALUES ('46', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '1', '', '10', '中等', '这是答案分析', '0', '2021-03-10 10:21:23', '2021-03-10 10:21:23');
INSERT INTO `topic` VALUES ('47', '1', null, null, '这是我新建的题目阿斯蒂芬1', '选项1 发士大夫\n选项2\n选项3\n选项4', null, '0', '选项1 发士大夫', '20', '困难', '这是答案分析', '0', '2021-03-11 10:12:43', '2021-03-11 10:12:43');
INSERT INTO `topic` VALUES ('48', '1', null, null, '这是我新建的题目22222222222', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '20', '中等', '这是答案分析', '0', '2021-03-11 10:12:43', '2021-03-11 10:12:43');
INSERT INTO `topic` VALUES ('49', '1', null, null, '这是我新建的题目33333333333', '选项1\n选项2\n选项3\n选项4', null, '0', '选项4', '20', '中等', '这是答案分析', '0', '2021-03-11 10:12:43', '2021-03-11 10:12:43');
INSERT INTO `topic` VALUES ('50', '1', null, null, '这是我新建的题目444444444444', '选项1\n选项2\n选项3\n选项4', null, '0', '选项3', '20', '中等', '这是答案分析', '0', '2021-03-11 10:12:43', '2021-03-11 10:12:43');
INSERT INTO `topic` VALUES ('51', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '1', '选项1\n选项3', '10', '中等', '这是答案分析', '0', '2021-03-11 10:12:43', '2021-03-11 10:12:43');
INSERT INTO `topic` VALUES ('52', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '1', '选项1\n选项2\n选项3\n选项4', '10', '中等', '这是答案分析', '0', '2021-03-11 10:12:43', '2021-03-11 10:12:43');
INSERT INTO `topic` VALUES ('53', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '2', 'true', '10', '中等', '这是答案分析', '0', '2021-03-11 10:12:43', '2021-03-11 10:12:43');
INSERT INTO `topic` VALUES ('54', '1', null, null, '这是我___新建的题目___', '选项1\n选项2\n选项3\n选项4', null, '3', '对对对\n冲冲冲', '10', '中等', '这是答案分析', '0', '2021-03-11 10:12:43', '2021-03-11 10:12:43');
INSERT INTO `topic` VALUES ('55', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '4', '123\n456', '10', '中等', '这是答案分析', '0', '2021-03-11 10:12:43', '2021-03-11 10:12:43');
INSERT INTO `topic` VALUES ('56', '1', null, null, '这是修改试卷的题目', '选项1\n选项2\n选项3\n选项4', null, '1', '', '10', '中等', '这是答案分析', '0', '2021-03-11 10:14:35', '2021-03-11 10:14:35');
INSERT INTO `topic` VALUES ('57', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '10', '中等', '这是答案分析', '0', '2021-04-02 09:27:17', '2021-04-02 09:27:17');
INSERT INTO `topic` VALUES ('58', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '1', '选项2\n选项3', '10', '中等', '这是答案分析', '0', '2021-04-02 09:27:17', '2021-04-02 09:27:17');
INSERT INTO `topic` VALUES ('59', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '2', 'true', '10', '中等', '这是答案分析', '0', '2021-04-02 09:27:17', '2021-04-02 09:27:17');
INSERT INTO `topic` VALUES ('60', '1', null, null, '这是我___新建的题目___', '选项1\n选项2\n选项3\n选项4', null, '3', '111\n222', '10', '中等', '这是答案分析', '0', '2021-04-02 09:27:17', '2021-04-02 09:27:17');
INSERT INTO `topic` VALUES ('61', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '4', '123\n456', '10', '中等', '这是答案分析', '0', '2021-04-02 09:27:17', '2021-04-02 09:27:17');
INSERT INTO `topic` VALUES ('62', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '0', '', '10', '中等', '这是答案分析', '0', '2021-04-02 09:33:04', '2021-04-02 09:33:04');
INSERT INTO `topic` VALUES ('63', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '10', '中等', '这是答案分析', '0', '2021-04-02 09:33:09', '2021-04-02 09:33:09');
INSERT INTO `topic` VALUES ('64', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '10', '中等', '这是答案分析', '0', '2021-04-12 13:01:14', '2021-04-12 13:01:14');
INSERT INTO `topic` VALUES ('65', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '10', '中等', '这是答案分析', '0', '2021-04-12 15:58:31', '2021-04-12 15:58:31');
INSERT INTO `topic` VALUES ('66', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '1', '选项2\n选项3', '10', '中等', '这是答案分析', '0', '2021-04-12 15:58:31', '2021-04-12 15:58:31');
INSERT INTO `topic` VALUES ('67', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '2', 'true', '10', '中等', '这是答案分析', '0', '2021-04-12 15:58:31', '2021-04-12 15:58:31');
INSERT INTO `topic` VALUES ('68', '1', null, null, '这是我___新建的题目___', '选项1\n选项2\n选项3\n选项4', null, '3', '111\n222', '10', '中等', '这是答案分析', '0', '2021-04-12 15:58:31', '2021-04-12 15:58:31');
INSERT INTO `topic` VALUES ('69', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '4', '123\n456', '10', '中等', '这是答案分析', '0', '2021-04-12 15:58:31', '2021-04-12 15:58:31');
INSERT INTO `topic` VALUES ('70', '1', null, null, '这是我新建的题目22222', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '10', '中等', '这是答案分析', null, '2021-04-19 20:40:36', '2021-04-19 20:40:36');
INSERT INTO `topic` VALUES ('71', '1', null, null, '这是我新建的题目11111111111111111', '选项1\n选项2\n选项3\n选项4', null, '0', '选项2', '10', '中等', '这是答案分析', null, '2021-04-19 20:40:36', '2021-04-19 20:40:36');
INSERT INTO `topic` VALUES ('72', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '1', '选项2\n选项3', '10', '中等', '这是答案分析', null, '2021-04-19 20:40:36', '2021-04-19 20:40:36');
INSERT INTO `topic` VALUES ('73', '1', null, null, '这是我新建的题目______', '选项1\n选项2\n选项3\n选项4', null, '3', '123\n1111', '10', '中等', '这是答案分析', null, '2021-04-19 20:40:36', '2021-04-19 20:40:36');
INSERT INTO `topic` VALUES ('74', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '4', '123\n456', '10', '中等', '这是答案分析', null, '2021-04-19 20:40:36', '2021-04-19 20:40:36');
INSERT INTO `topic` VALUES ('75', '1', null, null, '这是我新建的2222222222222', '选项1\n选项2\n选项3\n选项4', null, '0', '选项2', '5', '中等', '这是答案分析', null, '2021-04-19 21:04:38', '2021-04-19 21:04:38');
INSERT INTO `topic` VALUES ('76', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '0', '选项3', '10', '中等', '这是答案分析', null, '2021-04-19 21:04:38', '2021-04-19 21:04:38');
INSERT INTO `topic` VALUES ('77', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '0', '选项2', '10', '中等', '这是答案分析', null, '2021-04-19 21:04:38', '2021-04-19 21:04:38');
INSERT INTO `topic` VALUES ('78', '1', null, null, '这是我___新建的题目___', '选项1\n选项2\n选项3\n选项4', null, '3', '1111\n2222', '10', '中等', '这是答案分析', null, '2021-04-19 21:04:38', '2021-04-19 21:04:38');
INSERT INTO `topic` VALUES ('79', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '4', '123\n456', '10', '中等', '这是答案分析', null, '2021-04-19 21:04:38', '2021-04-19 21:04:38');
INSERT INTO `topic` VALUES ('80', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '0', '选项2', '10', '中等', '这是答案分析', '0', '2021-04-19 21:07:02', '2021-04-19 21:07:02');
INSERT INTO `topic` VALUES ('81', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '1', '选项3\n选项4', '10', '中等', '这是答案分析', '0', '2021-04-25 09:07:49', '2021-04-25 09:07:49');
INSERT INTO `topic` VALUES ('82', '1', null, null, '这是我新建的题目1', '选项1\n选项2\n选项3\n选项4', null, '0', '选项1', '10', '中等', '这是答案分析', '1', '2021-04-25 09:12:20', '2021-04-25 09:12:20');
INSERT INTO `topic` VALUES ('83', '1', null, null, '这是我新建的题目2', '选项1\n选项2\n选项3\n选项4', null, '0', '选项2', '10', '中等', '这是答案分析', '0', '2021-04-25 09:12:20', '2021-04-25 09:12:20');
INSERT INTO `topic` VALUES ('84', '1', null, null, '这是我新建的题目3', '选项1\n选项2\n选项3\n选项4', null, '0', '选项3', '10', '中等', '这是答案分析', '0', '2021-04-25 09:12:20', '2021-04-25 09:12:20');
INSERT INTO `topic` VALUES ('85', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '1', '选项1\n选项3', '10', '中等', '这是答案分析', '1', '2021-04-25 09:12:20', '2021-04-25 09:12:20');
INSERT INTO `topic` VALUES ('86', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '2', 'true', '10', '中等', '这是答案分析', '1', '2021-04-25 09:12:20', '2021-04-25 09:12:20');
INSERT INTO `topic` VALUES ('87', '1', null, null, '这是我新建的题目______', '选项1\n选项2\n选项3\n选项4', null, '3', '111\n222', '10', '中等', '这是答案分析', '0', '2021-04-25 09:12:20', '2021-04-25 09:12:20');
INSERT INTO `topic` VALUES ('88', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '4', '123\n456', '10', '中等', '这是答案分析', '0', '2021-04-25 09:12:20', '2021-04-25 09:12:20');
INSERT INTO `topic` VALUES ('89', '1', null, null, '这是我新建的题目空', '选项1\n选项2\n选项3\n选项4', null, '0', '', '10', '中等', '这是答案分析', '0', '2021-04-25 09:55:56', '2021-04-25 09:55:56');
INSERT INTO `topic` VALUES ('90', '1', null, null, '这是我新建的题目', '选项1\n选项2\n选项3\n选项4', null, '1', '', '10', '中等', '这是答案分析', '0', '2021-04-25 10:00:43', '2021-04-25 10:00:43');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '男' COMMENT '性别',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电子邮箱',
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'student' COMMENT '身份 student:学生  teacher:是教师身份',
  `work` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工作职位',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像图片',
  `photo_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片名称',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `souyin1` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '男', '13000000000', '762489117@qq.com', 'teacher', '教师', '2021-04-10 15:36:15', '2020-11-16 15:51:00', '26651ce8c393400898d71a64da22d92a.jpg', 'fbab4835a816e500f49fda12633f9873.jpg');
INSERT INTO `user` VALUES ('2', '李子建abc', '男', '13000000002', '1@qq.com', 'student', null, '2021-04-10 22:12:56', '2020-11-16 15:51:00', '0ecc355b9e6e49cf9732273b1f79a6ed.jpg', 'fbab4835a816e500f49fda12633f9873.jpg');
INSERT INTO `user` VALUES ('3', '1111', '女', '13000000001', '2@qq.com', 'student', '', '2021-04-19 21:14:38', '2020-11-16 15:51:00', '76c2e46f1b3c4120afe3e0d103507531.png', 'snipaste20210321_172951.png');
INSERT INTO `user` VALUES ('5', 'aaa1', '男', null, '11@qq.com', 'student', '', null, '2020-11-27 13:11:49', null, null);
INSERT INTO `user` VALUES ('7', 'tatata', '男', '', '3@qq.com', 'student', '', null, '2020-12-02 18:57:33', null, null);
INSERT INTO `user` VALUES ('11', '李子健11', '男', '13000000009', '123@qq.com', 'student', '', null, '2021-01-05 20:20:24', null, null);
INSERT INTO `user` VALUES ('12', '李子健', '男', '13000000011', '123456@qq.com', 'student', '', null, '2021-01-05 21:50:34', null, null);
INSERT INTO `user` VALUES ('14', '阿斯蒂芬', null, '13128746362', '4@qq.com', 'student', null, '2021-03-10 14:33:44', '2021-03-10 14:33:44', null, null);

-- ----------------------------
-- Table structure for user_classes
-- ----------------------------
DROP TABLE IF EXISTS `user_classes`;
CREATE TABLE `user_classes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户id',
  `classes_id` int NOT NULL COMMENT '班级id',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份   creator:创建者  student:学生  admin:管理员',
  `enter_date` datetime DEFAULT NULL COMMENT '加入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_classes
-- ----------------------------
INSERT INTO `user_classes` VALUES ('7', '2', '2', 'admin', '2020-11-16 15:51:00');
INSERT INTO `user_classes` VALUES ('8', '2', '11', 'student', '2020-11-19 16:30:28');
INSERT INTO `user_classes` VALUES ('9', '2', '12', 'student', '2020-11-19 17:15:59');
INSERT INTO `user_classes` VALUES ('10', '3', '2', 'student', '2020-11-17 20:19:58');
INSERT INTO `user_classes` VALUES ('11', '3', '6', 'student', '2020-11-17 20:33:33');
INSERT INTO `user_classes` VALUES ('12', '3', '11', 'student', '2020-11-17 20:23:30');
INSERT INTO `user_classes` VALUES ('16', '2', '13', 'student', '2020-12-02 19:22:46');
INSERT INTO `user_classes` VALUES ('23', '12', '15', 'student', '2021-01-05 22:01:01');
INSERT INTO `user_classes` VALUES ('34', '2', '1', 'student', '2021-03-08 21:05:10');
INSERT INTO `user_classes` VALUES ('35', '5', '1', 'student', '2021-03-08 23:44:27');
INSERT INTO `user_classes` VALUES ('37', '5', '22', 'student', '2021-03-09 14:15:33');
INSERT INTO `user_classes` VALUES ('41', '2', '19', 'student', '2021-03-09 16:05:28');
INSERT INTO `user_classes` VALUES ('42', '2', '17', 'student', '2021-03-09 16:07:11');
INSERT INTO `user_classes` VALUES ('43', '5', '17', 'student', '2021-03-09 19:29:09');
INSERT INTO `user_classes` VALUES ('44', '5', '20', 'student', '2021-03-09 19:36:48');
INSERT INTO `user_classes` VALUES ('45', '5', '21', 'student', '2021-03-09 19:39:12');
INSERT INTO `user_classes` VALUES ('46', '2', '21', 'student', '2021-03-10 08:36:19');
INSERT INTO `user_classes` VALUES ('47', '2', '20', 'student', '2021-03-10 10:49:35');
INSERT INTO `user_classes` VALUES ('52', '14', '25', 'student', '2021-03-11 10:27:12');
INSERT INTO `user_classes` VALUES ('55', '2', '26', 'student', '2021-04-02 09:35:06');
INSERT INTO `user_classes` VALUES ('56', '5', '26', 'student', '2021-04-02 10:38:49');
INSERT INTO `user_classes` VALUES ('57', '3', '26', 'student', '2021-04-02 11:44:10');
INSERT INTO `user_classes` VALUES ('59', '7', '26', 'student', '2021-04-14 17:54:50');
INSERT INTO `user_classes` VALUES ('60', '3', '24', 'student', '2021-04-18 20:29:28');
INSERT INTO `user_classes` VALUES ('61', '3', '22', 'student', '2021-04-18 20:29:39');
INSERT INTO `user_classes` VALUES ('63', '3', '28', 'student', '2021-04-19 21:07:48');

-- ----------------------------
-- Table structure for user_grade
-- ----------------------------
DROP TABLE IF EXISTS `user_grade`;
CREATE TABLE `user_grade` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NOT NULL COMMENT '用户id',
  `classes_id` int NOT NULL COMMENT '班级id',
  `exam_id` int NOT NULL COMMENT '考试id',
  `grade` double DEFAULT '0' COMMENT '得分',
  `grade_auto` double DEFAULT '0' COMMENT '得分(自动评分)',
  `answer_date` datetime DEFAULT NULL COMMENT '答题日期',
  `answer_time` int DEFAULT NULL COMMENT '答题时长',
  `mark_status` int DEFAULT '0' COMMENT '批改状态 0:待批改 1:批改完成',
  `exam_status` int DEFAULT '0' COMMENT '试卷状态 0:未完成  1:已完成',
  `mark_date` datetime DEFAULT NULL COMMENT '批改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_grade
-- ----------------------------
INSERT INTO `user_grade` VALUES ('1', '2', '1', '4', '20', '20', '2020-12-20 10:22:40', '60', '0', '1', null);
INSERT INTO `user_grade` VALUES ('2', '2', '1', '1', '0', '40', '2021-01-05 21:47:24', '8', '0', '1', null);
INSERT INTO `user_grade` VALUES ('3', '12', '1', '1', '0', '0', '2021-01-05 21:52:14', '46', '0', '1', null);
INSERT INTO `user_grade` VALUES ('4', '2', '1', '10', null, '20', '2021-03-08 20:32:39', '594', '0', '1', null);
INSERT INTO `user_grade` VALUES ('5', '2', '22', '10', null, '0', '2021-03-09 13:46:20', '13', '0', '1', null);
INSERT INTO `user_grade` VALUES ('6', '5', '22', '10', null, '40', '2021-03-09 14:15:47', '10', '0', '1', null);
INSERT INTO `user_grade` VALUES ('7', '2', '22', '4', '45', '45', '2021-03-09 15:30:56', '62', '0', '1', null);
INSERT INTO `user_grade` VALUES ('8', '2', '21', '4', '60', '60', '2021-03-09 15:49:50', '83', '0', '1', null);
INSERT INTO `user_grade` VALUES ('9', '2', '17', '4', '85', '85', '2021-03-09 15:59:42', '136', '0', '1', null);
INSERT INTO `user_grade` VALUES ('10', '2', '19', '4', '109', '90', '2021-03-09 16:08:51', '136', '1', '1', '2021-03-09 19:05:18');
INSERT INTO `user_grade` VALUES ('11', '5', '17', '4', '8.333333333333334', '8.333333333333334', '2021-03-09 19:29:38', '24', '0', '1', null);
INSERT INTO `user_grade` VALUES ('12', '5', '20', '4', '13.333333333333334', '13.333333333333334', '2021-03-09 19:37:28', '35', '0', '1', null);
INSERT INTO `user_grade` VALUES ('13', '5', '21', '4', '3', '3', '2021-03-09 19:39:24', '4', '0', '1', null);
INSERT INTO `user_grade` VALUES ('14', '2', '20', '4', '20', '20', '2021-03-10 13:34:26', '14', '0', '1', null);
INSERT INTO `user_grade` VALUES ('15', '14', '25', '15', '57', '50', '2021-03-11 10:31:05', '90', '1', '1', '2021-03-11 10:40:36');
INSERT INTO `user_grade` VALUES ('16', '1', '25', '15', '50', '50', '2021-03-11 10:39:42', '601', '0', '1', null);
INSERT INTO `user_grade` VALUES ('17', '2', '1', '15', '40', '40', '2021-03-18 20:59:15', '18', '0', '1', null);
INSERT INTO `user_grade` VALUES ('18', '2', '26', '19', '25', '25', '2021-04-02 10:32:48', '74', '0', '1', null);
INSERT INTO `user_grade` VALUES ('19', '5', '26', '19', '18.6666', '20', '2021-04-02 10:42:19', '71', '1', '1', '2021-04-11 15:29:15');
INSERT INTO `user_grade` VALUES ('20', '3', '26', '19', '35', '35', '2021-04-02 11:47:28', '54', '0', '1', null);
INSERT INTO `user_grade` VALUES ('21', '3', '28', '22', null, '30', '2021-04-19 20:44:36', '82', '0', '1', null);
INSERT INTO `user_grade` VALUES ('22', '3', '28', '23', '30.5', '25', '2021-04-19 21:09:45', '154', '1', '1', '2021-04-19 21:11:44');

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password` (
  `user_id` int NOT NULL COMMENT '用户id',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录密码',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES ('1', 'admin');
INSERT INTO `user_password` VALUES ('2', 'admin');
INSERT INTO `user_password` VALUES ('3', 'admin');
INSERT INTO `user_password` VALUES ('5', 'admin');
INSERT INTO `user_password` VALUES ('7', 'admin');
INSERT INTO `user_password` VALUES ('8', '123456');
INSERT INTO `user_password` VALUES ('9', '123456');
INSERT INTO `user_password` VALUES ('10', '123456');
INSERT INTO `user_password` VALUES ('11', '123456');
INSERT INTO `user_password` VALUES ('12', '123456');
INSERT INTO `user_password` VALUES ('14', '123123');

-- ----------------------------
-- Table structure for user_topic
-- ----------------------------
DROP TABLE IF EXISTS `user_topic`;
CREATE TABLE `user_topic` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `classes_id` int DEFAULT NULL COMMENT '班级id',
  `exam_id` int DEFAULT NULL COMMENT '试卷id',
  `topic_id` int DEFAULT NULL COMMENT '题目id',
  `user_answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户答案',
  `user_score` double DEFAULT NULL COMMENT '用户得分',
  `topic_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '0:待批改 1:批改完成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=320 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_topic
-- ----------------------------
INSERT INTO `user_topic` VALUES ('29', '2', '1', '4', '3', '选项1', '10', '0');
INSERT INTO `user_topic` VALUES ('30', '2', '1', '4', '4', '选项3\n选项4', '0', '0');
INSERT INTO `user_topic` VALUES ('31', '2', '1', '4', '5', 'true', '10', '1');
INSERT INTO `user_topic` VALUES ('32', '2', '1', '4', '6', 'qq', '0', '0');
INSERT INTO `user_topic` VALUES ('33', '2', '1', '4', '7', '111', '0', '0');
INSERT INTO `user_topic` VALUES ('34', '2', '1', '4', '11', '选项4', '0', '1');
INSERT INTO `user_topic` VALUES ('35', '2', '1', '4', '12', 'weew', '0', '0');
INSERT INTO `user_topic` VALUES ('71', '2', '1', '1', '1', '选项1', '0', '0');
INSERT INTO `user_topic` VALUES ('72', '2', '1', '1', '18', '选项1', '0', '0');
INSERT INTO `user_topic` VALUES ('73', '2', '1', '1', '19', 'false', '0', '0');
INSERT INTO `user_topic` VALUES ('74', '2', '1', '1', '20', '111', '0', '0');
INSERT INTO `user_topic` VALUES ('75', '2', '1', '1', '21', 'qqq', '0', '0');
INSERT INTO `user_topic` VALUES ('76', '12', '1', '1', '1', '选项1', '0', '0');
INSERT INTO `user_topic` VALUES ('77', '12', '1', '1', '18', '选项2\n选项3', '0', '0');
INSERT INTO `user_topic` VALUES ('78', '12', '1', '1', '19', 'false', '0', '0');
INSERT INTO `user_topic` VALUES ('79', '12', '1', '1', '20', '11', '0', '0');
INSERT INTO `user_topic` VALUES ('80', '12', '1', '1', '21', '222', '0', '0');
INSERT INTO `user_topic` VALUES ('161', '2', '1', '10', '31', '选项2', '0', '0');
INSERT INTO `user_topic` VALUES ('162', '2', '1', '10', '30', '选项3', '0', '0');
INSERT INTO `user_topic` VALUES ('163', '2', '1', '10', '32', '选项2', '0', '0');
INSERT INTO `user_topic` VALUES ('164', '2', '1', '10', '29', '选项3', '0', '0');
INSERT INTO `user_topic` VALUES ('165', '2', '1', '10', '33', '选项1', '0', '0');
INSERT INTO `user_topic` VALUES ('166', '2', '1', '10', '28', '选项2', '0', '0');
INSERT INTO `user_topic` VALUES ('167', '2', '1', '10', '35', '选项3', '0', '0');
INSERT INTO `user_topic` VALUES ('168', '2', '1', '10', '34', '选项4', '0', '0');
INSERT INTO `user_topic` VALUES ('169', '2', '1', '10', '36', '', '10', '0');
INSERT INTO `user_topic` VALUES ('170', '2', '1', '10', '37', '', '10', '0');
INSERT INTO `user_topic` VALUES ('171', '2', '22', '10', '29', '选项1', '0', '0');
INSERT INTO `user_topic` VALUES ('172', '2', '22', '10', '34', '选项1', '0', '0');
INSERT INTO `user_topic` VALUES ('173', '2', '22', '10', '31', '选项3', '0', '0');
INSERT INTO `user_topic` VALUES ('174', '2', '22', '10', '37', '选项2', '0', '0');
INSERT INTO `user_topic` VALUES ('175', '2', '22', '10', '30', '选项3', '0', '0');
INSERT INTO `user_topic` VALUES ('176', '2', '22', '10', '28', '选项4', '0', '0');
INSERT INTO `user_topic` VALUES ('177', '2', '22', '10', '35', '选项1', '0', '0');
INSERT INTO `user_topic` VALUES ('178', '2', '22', '10', '36', '选项2', '0', '0');
INSERT INTO `user_topic` VALUES ('179', '2', '22', '10', '32', '选项3', '0', '0');
INSERT INTO `user_topic` VALUES ('180', '2', '22', '10', '33', '选项2', '0', '0');
INSERT INTO `user_topic` VALUES ('181', '5', '22', '10', '28', '选项1', '10', '0');
INSERT INTO `user_topic` VALUES ('182', '5', '22', '10', '32', '选项1', '10', '0');
INSERT INTO `user_topic` VALUES ('183', '5', '22', '10', '33', '选项1', '10', '0');
INSERT INTO `user_topic` VALUES ('184', '5', '22', '10', '30', '选项2', '0', '0');
INSERT INTO `user_topic` VALUES ('185', '5', '22', '10', '31', '选项4', '0', '0');
INSERT INTO `user_topic` VALUES ('186', '5', '22', '10', '29', '选项4', '0', '0');
INSERT INTO `user_topic` VALUES ('187', '5', '22', '10', '34', '选项2', '0', '0');
INSERT INTO `user_topic` VALUES ('188', '5', '22', '10', '36', '选项3', '0', '0');
INSERT INTO `user_topic` VALUES ('189', '5', '22', '10', '35', '选项1', '10', '0');
INSERT INTO `user_topic` VALUES ('190', '5', '22', '10', '37', '', '0', '0');
INSERT INTO `user_topic` VALUES ('191', '2', '22', '4', '3', '选项1', '20', '0');
INSERT INTO `user_topic` VALUES ('192', '2', '22', '4', '4', '选项3\n选项4', '10', '0');
INSERT INTO `user_topic` VALUES ('193', '2', '22', '4', '12', '无法', '0', '0');
INSERT INTO `user_topic` VALUES ('194', '2', '22', '4', '7', '哇哇哇哇哇', '0', '0');
INSERT INTO `user_topic` VALUES ('195', '2', '22', '4', '11', '选项4', '0', '0');
INSERT INTO `user_topic` VALUES ('196', '2', '22', '4', '6', 'a\nc', '5', '0');
INSERT INTO `user_topic` VALUES ('197', '2', '22', '4', '5', 'true', '10', '0');
INSERT INTO `user_topic` VALUES ('198', '2', '21', '4', '7', 'asdfasd无法dsfsdf宿舍asdf', '10', '0');
INSERT INTO `user_topic` VALUES ('199', '2', '21', '4', '3', '选项1', '20', '0');
INSERT INTO `user_topic` VALUES ('200', '2', '21', '4', '11', '选项4', '0', '0');
INSERT INTO `user_topic` VALUES ('201', '2', '21', '4', '4', '选项3\n选项4', '10', '0');
INSERT INTO `user_topic` VALUES ('202', '2', '21', '4', '5', 'true', '10', '0');
INSERT INTO `user_topic` VALUES ('203', '2', '21', '4', '6', 'a\nb', '10', '0');
INSERT INTO `user_topic` VALUES ('204', '2', '21', '4', '45', '对对对\nsss', '0', '0');
INSERT INTO `user_topic` VALUES ('205', '2', '21', '4', '44', '', '0', '0');
INSERT INTO `user_topic` VALUES ('206', '2', '21', '4', '43', '随时随地123撒供奉的是', '0', '0');
INSERT INTO `user_topic` VALUES ('207', '2', '21', '4', '12', '', '0', '0');
INSERT INTO `user_topic` VALUES ('208', '2', '17', '4', '3', '选项1', '20', '0');
INSERT INTO `user_topic` VALUES ('209', '2', '17', '4', '4', '选项3\n选项4', '10', '0');
INSERT INTO `user_topic` VALUES ('210', '2', '17', '4', '5', 'true', '10', '0');
INSERT INTO `user_topic` VALUES ('211', '2', '17', '4', '6', 'a\nb', '10', '0');
INSERT INTO `user_topic` VALUES ('212', '2', '17', '4', '7', 'asdfasd无法dsfsdf宿舍asdf', '10', '0');
INSERT INTO `user_topic` VALUES ('213', '2', '17', '4', '11', '选项3', '20', '0');
INSERT INTO `user_topic` VALUES ('214', '2', '17', '4', '12', '阿斯蒂芬123是法国岁的法国', '5', '0');
INSERT INTO `user_topic` VALUES ('215', '2', '17', '4', '43', '啊身份认同为仁天皇', '0', '0');
INSERT INTO `user_topic` VALUES ('216', '2', '17', '4', '44', '对对对\n杀杀杀', '0', '0');
INSERT INTO `user_topic` VALUES ('217', '2', '17', '4', '45', '阿斯蒂芬\n委任为', '0', '0');
INSERT INTO `user_topic` VALUES ('218', '2', '19', '4', '3', '选项1', '20', '1');
INSERT INTO `user_topic` VALUES ('219', '2', '19', '4', '4', '选项3\n选项4', '10', '1');
INSERT INTO `user_topic` VALUES ('220', '2', '19', '4', '5', 'true', '10', '1');
INSERT INTO `user_topic` VALUES ('221', '2', '19', '4', '6', 'a\nb', '10', '1');
INSERT INTO `user_topic` VALUES ('222', '2', '19', '4', '7', 'asdfasd无法dsfsdf宿舍asdf', '9', '1');
INSERT INTO `user_topic` VALUES ('223', '2', '19', '4', '11', '选项3', '20', '1');
INSERT INTO `user_topic` VALUES ('224', '2', '19', '4', '12', '是法国123和交付条件', '5', '1');
INSERT INTO `user_topic` VALUES ('225', '2', '19', '4', '43', '十大高手', '10', '1');
INSERT INTO `user_topic` VALUES ('226', '2', '19', '4', '44', '对对对\n是德国', '5', '1');
INSERT INTO `user_topic` VALUES ('227', '2', '19', '4', '45', '法国皇帝\n而已', '10', '1');
INSERT INTO `user_topic` VALUES ('228', '5', '17', '4', '3', '选项3', '0', '0');
INSERT INTO `user_topic` VALUES ('229', '5', '17', '4', '4', '选项2\n选项3', '0', '0');
INSERT INTO `user_topic` VALUES ('230', '5', '17', '4', '5', 'false', '0', '0');
INSERT INTO `user_topic` VALUES ('231', '5', '17', '4', '6', 'q\nw', '0', '0');
INSERT INTO `user_topic` VALUES ('232', '5', '17', '4', '7', 'asdf', '0', '0');
INSERT INTO `user_topic` VALUES ('233', '5', '17', '4', '11', '选项2', '0', '0');
INSERT INTO `user_topic` VALUES ('234', '5', '17', '4', '12', '123vasfv', '5', '0');
INSERT INTO `user_topic` VALUES ('235', '5', '17', '4', '43', 'asdfwe', '3.3333333333333335', '0');
INSERT INTO `user_topic` VALUES ('236', '5', '17', '4', '44', 'w\nq', '0', '0');
INSERT INTO `user_topic` VALUES ('237', '5', '17', '4', '45', 'w\nw', '0', '0');
INSERT INTO `user_topic` VALUES ('238', '5', '20', '4', '3', '', '0', '0');
INSERT INTO `user_topic` VALUES ('239', '5', '20', '4', '4', '', '0', '0');
INSERT INTO `user_topic` VALUES ('240', '5', '20', '4', '5', '', '0', '0');
INSERT INTO `user_topic` VALUES ('241', '5', '20', '4', '6', 'q\nw', '0', '0');
INSERT INTO `user_topic` VALUES ('242', '5', '20', '4', '7', '士大夫个人', '0', '0');
INSERT INTO `user_topic` VALUES ('243', '5', '20', '4', '11', '', '0', '0');
INSERT INTO `user_topic` VALUES ('244', '5', '20', '4', '12', '123 而非公司的', '5', '0');
INSERT INTO `user_topic` VALUES ('245', '5', '20', '4', '43', 'asdfqq', '3.3333333333333335', '0');
INSERT INTO `user_topic` VALUES ('246', '5', '20', '4', '44', '冲冲冲\n时代', '5', '0');
INSERT INTO `user_topic` VALUES ('247', '5', '20', '4', '45', '时代\n得分', '0', '0');
INSERT INTO `user_topic` VALUES ('248', '5', '21', '4', '3', '', '0', '0');
INSERT INTO `user_topic` VALUES ('249', '5', '21', '4', '4', '', '0', '0');
INSERT INTO `user_topic` VALUES ('250', '5', '21', '4', '5', '', '0', '0');
INSERT INTO `user_topic` VALUES ('251', '5', '21', '4', '6', '', '0', '0');
INSERT INTO `user_topic` VALUES ('252', '5', '21', '4', '7', '', '0', '0');
INSERT INTO `user_topic` VALUES ('253', '5', '21', '4', '11', '', '0', '0');
INSERT INTO `user_topic` VALUES ('254', '5', '21', '4', '12', '', '0', '0');
INSERT INTO `user_topic` VALUES ('255', '5', '21', '4', '43', 'asdfwer', '3', '0');
INSERT INTO `user_topic` VALUES ('256', '5', '21', '4', '44', '', '0', '0');
INSERT INTO `user_topic` VALUES ('257', '5', '21', '4', '45', '', '0', '0');
INSERT INTO `user_topic` VALUES ('258', '2', '20', '4', '3', '选项2', '0', '0');
INSERT INTO `user_topic` VALUES ('259', '2', '20', '4', '4', '选项2\n选项3', '0', '0');
INSERT INTO `user_topic` VALUES ('260', '2', '20', '4', '5', 'false', '0', '0');
INSERT INTO `user_topic` VALUES ('261', '2', '20', '4', '6', '', '0', '0');
INSERT INTO `user_topic` VALUES ('262', '2', '20', '4', '7', '', '0', '0');
INSERT INTO `user_topic` VALUES ('263', '2', '20', '4', '11', '选项3', '20', '0');
INSERT INTO `user_topic` VALUES ('264', '2', '20', '4', '12', '', '0', '0');
INSERT INTO `user_topic` VALUES ('265', '2', '20', '4', '43', '', '0', '0');
INSERT INTO `user_topic` VALUES ('266', '2', '20', '4', '44', '', '0', '0');
INSERT INTO `user_topic` VALUES ('267', '2', '20', '4', '45', '', '0', '0');
INSERT INTO `user_topic` VALUES ('268', '14', '25', '15', '56', '选项3', '0', '1');
INSERT INTO `user_topic` VALUES ('269', '14', '25', '15', '54', '冲冲冲\n埃是法国违规', '8', '1');
INSERT INTO `user_topic` VALUES ('270', '14', '25', '15', '51', '选项1\n选项2\n选项3\n选项4', '0', '1');
INSERT INTO `user_topic` VALUES ('271', '14', '25', '15', '49', '选项1', '0', '1');
INSERT INTO `user_topic` VALUES ('272', '14', '25', '15', '55', '视频豆腐干和123坳隧道回复', '9', '1');
INSERT INTO `user_topic` VALUES ('273', '14', '25', '15', '47', '选项1 发士大夫', '20', '1');
INSERT INTO `user_topic` VALUES ('274', '14', '25', '15', '52', '选项2\n选项1', '0', '1');
INSERT INTO `user_topic` VALUES ('275', '14', '25', '15', '48', '选项1', '20', '1');
INSERT INTO `user_topic` VALUES ('276', '14', '25', '15', '53', 'false', '0', '1');
INSERT INTO `user_topic` VALUES ('277', '1', '25', '15', '56', '选项3', '0', '0');
INSERT INTO `user_topic` VALUES ('278', '1', '25', '15', '54', '冲冲冲\n埃是法国违规', '5', '0');
INSERT INTO `user_topic` VALUES ('279', '1', '25', '15', '51', '选项1\n选项2\n选项3\n选项4', '0', '0');
INSERT INTO `user_topic` VALUES ('280', '1', '25', '15', '49', '选项1', '0', '0');
INSERT INTO `user_topic` VALUES ('281', '1', '25', '15', '55', '视频豆腐干和123坳隧道回复', '5', '0');
INSERT INTO `user_topic` VALUES ('282', '1', '25', '15', '47', '选项1 发士大夫', '20', '0');
INSERT INTO `user_topic` VALUES ('283', '1', '25', '15', '52', '选项2\n选项1', '0', '0');
INSERT INTO `user_topic` VALUES ('284', '1', '25', '15', '48', '选项1', '20', '0');
INSERT INTO `user_topic` VALUES ('285', '1', '25', '15', '53', 'false', '0', '0');
INSERT INTO `user_topic` VALUES ('286', '2', '1', '15', '51', '选项2\n选项3', '0', '0');
INSERT INTO `user_topic` VALUES ('287', '2', '1', '15', '53', 'true', '10', '0');
INSERT INTO `user_topic` VALUES ('288', '2', '1', '15', '49', '选项1', '0', '0');
INSERT INTO `user_topic` VALUES ('289', '2', '1', '15', '55', '阿斯蒂芬123', '5', '0');
INSERT INTO `user_topic` VALUES ('290', '2', '1', '15', '47', '选项3', '0', '0');
INSERT INTO `user_topic` VALUES ('291', '2', '1', '15', '54', '冲冲冲\n阿迪斯', '5', '0');
INSERT INTO `user_topic` VALUES ('292', '2', '1', '15', '52', '选项2\n选项3', '0', '0');
INSERT INTO `user_topic` VALUES ('293', '2', '1', '15', '48', '选项1', '20', '0');
INSERT INTO `user_topic` VALUES ('294', '2', '1', '15', '56', '选项2\n选项3', '0', '0');
INSERT INTO `user_topic` VALUES ('295', '2', '26', '19', '57', '选项1', '10', '0');
INSERT INTO `user_topic` VALUES ('296', '2', '26', '19', '58', '选项2\n选项3', '10', '0');
INSERT INTO `user_topic` VALUES ('297', '2', '26', '19', '59', 'false', '0', '0');
INSERT INTO `user_topic` VALUES ('298', '2', '26', '19', '60', 'www\n222', '0', '0');
INSERT INTO `user_topic` VALUES ('299', '2', '26', '19', '61', 'asd123aif', '5', '0');
INSERT INTO `user_topic` VALUES ('300', '5', '26', '19', '57', '选项1', '10', '1');
INSERT INTO `user_topic` VALUES ('301', '5', '26', '19', '58', '选项1\n选项2', '0', '1');
INSERT INTO `user_topic` VALUES ('302', '5', '26', '19', '59', 'false', '0', '1');
INSERT INTO `user_topic` VALUES ('303', '5', '26', '19', '60', 'qwe\n222', '6.6666', '1');
INSERT INTO `user_topic` VALUES ('304', '5', '26', '19', '61', 'asdf456fdg', '2', '1');
INSERT INTO `user_topic` VALUES ('305', '3', '26', '19', '57', '选项1', '10', '0');
INSERT INTO `user_topic` VALUES ('306', '3', '26', '19', '58', '选项1\n选项4', '0', '0');
INSERT INTO `user_topic` VALUES ('307', '3', '26', '19', '59', 'true', '10', '0');
INSERT INTO `user_topic` VALUES ('308', '3', '26', '19', '60', '123\n222', '5', '0');
INSERT INTO `user_topic` VALUES ('309', '3', '26', '19', '61', 'asdif123456fdasdf', '10', '0');
INSERT INTO `user_topic` VALUES ('310', '3', '28', '22', '70', '选项1', '10', '0');
INSERT INTO `user_topic` VALUES ('311', '3', '28', '22', '71', '选项2', '10', '0');
INSERT INTO `user_topic` VALUES ('312', '3', '28', '22', '72', '选项3\n选项2', '0', '0');
INSERT INTO `user_topic` VALUES ('313', '3', '28', '22', '73', '123\n111', '5', '0');
INSERT INTO `user_topic` VALUES ('314', '3', '28', '22', '74', '123', '5', '0');
INSERT INTO `user_topic` VALUES ('315', '3', '28', '23', '78', '111\n2222', '5.5', '1');
INSERT INTO `user_topic` VALUES ('316', '3', '28', '23', '79', '坳隧道回复吧123阿斯蒂芬', '10', '1');
INSERT INTO `user_topic` VALUES ('317', '3', '28', '23', '77', '选项2', '10', '1');
INSERT INTO `user_topic` VALUES ('318', '3', '28', '23', '75', '选项2', '5', '1');
INSERT INTO `user_topic` VALUES ('319', '3', '28', '23', '76', '选项2', '0', '1');
