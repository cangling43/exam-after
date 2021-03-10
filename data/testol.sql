/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80022
Source Host           : localhost:3306
Source Database       : testol

Target Server Type    : MYSQL
Target Server Version : 80022
File Encoding         : 65001

Date: 2021-03-10 08:32:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `classes_id` int NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `classes_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名称',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '班级简介',
  `people_num` int DEFAULT NULL COMMENT '班级人数',
  `creator_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者名称',
  `creator_id` int NOT NULL COMMENT '创建者id',
  `joinWay` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'all' COMMENT '班级加入方式  0:不允许加入 1:允许任何人加入  2:需要管理员同意',
  PRIMARY KEY (`classes_id`),
  KEY `FK` (`creator_id`),
  CONSTRAINT `FK` FOREIGN KEY (`creator_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `exam_id` int NOT NULL AUTO_INCREMENT COMMENT '试卷id',
  `exam_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '试卷名称',
  `creator_id` int unsigned NOT NULL COMMENT '创建者id',
  `creator_name` varchar(255) NOT NULL COMMENT '创建者名称',
  `time` int unsigned DEFAULT NULL COMMENT '答题时间(分钟)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `subject_id` int unsigned DEFAULT NULL COMMENT '科目类型id',
  `subject_name` varchar(255) DEFAULT NULL COMMENT '科目类型名称',
  `topic_num` int unsigned DEFAULT '0' COMMENT '题目数量',
  `total_score` float unsigned DEFAULT '0' COMMENT '总分',
  `pass_mark` float unsigned DEFAULT '0' COMMENT '及格分数',
  `permit_copy` int unsigned DEFAULT '1' COMMENT '是否允许复制  0:不允许  1:允许(默认)',
  `disrupt_order` int unsigned DEFAULT '0' COMMENT '是否打乱题目顺序 0:不打乱(默认) 1:打乱顺序',
  `repeat_test` int unsigned DEFAULT '1' COMMENT '允许考生考试次数 默认1',
  `auto_mack` int unsigned DEFAULT '1' COMMENT '是否自动评分 0:否   1:是(默认)',
  PRIMARY KEY (`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for exam_classes
-- ----------------------------
DROP TABLE IF EXISTS `exam_classes`;
CREATE TABLE `exam_classes` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `exam_id` int NOT NULL COMMENT '考试id',
  `classes_id` int NOT NULL COMMENT '班级id',
  `exam_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '考试名称',
  `classes_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名称',
  `release_time` datetime NOT NULL COMMENT '发布时间',
  `start_date` datetime NOT NULL COMMENT '开始时间',
  `deadline` datetime NOT NULL COMMENT '结束时间',
  `publish_answer` int DEFAULT '0' COMMENT '是否公布答案   0:不公布   1:公布答案',
  `publish_score` int DEFAULT '0' COMMENT '是否公布分数   0:不公布   1:公布',
  `status` int DEFAULT '0' COMMENT '状态 0:待批改 1:批改完成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for testpaper
-- ----------------------------
DROP TABLE IF EXISTS `testpaper`;
CREATE TABLE `testpaper` (
  `tp_id` int NOT NULL AUTO_INCREMENT,
  `tp_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `creator_id` int unsigned DEFAULT NULL,
  `time` int unsigned DEFAULT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `subject_id` int unsigned DEFAULT NULL,
  `topic_num` int unsigned DEFAULT '0',
  `total_score` float unsigned DEFAULT '0',
  `pass_mark` float unsigned DEFAULT '0',
  `permit_copy` int unsigned DEFAULT '1',
  `disrupt_order` int unsigned DEFAULT '0',
  `repeat_test` int unsigned DEFAULT '1',
  `auto_mack` int unsigned DEFAULT '1',
  PRIMARY KEY (`tp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for testpaper_classes
-- ----------------------------
DROP TABLE IF EXISTS `testpaper_classes`;
CREATE TABLE `testpaper_classes` (
  `tc_id` int NOT NULL AUTO_INCREMENT,
  `tp_id` int NOT NULL,
  `c_id` int NOT NULL,
  `release_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `deadline` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `publish_answer` int DEFAULT '0',
  `publish_score` int DEFAULT '0',
  `tc_status` int DEFAULT '0',
  PRIMARY KEY (`tc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for testpaper_topic
-- ----------------------------
DROP TABLE IF EXISTS `testpaper_topic`;
CREATE TABLE `testpaper_topic` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tp_id` int NOT NULL,
  `t_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `topic_id` int NOT NULL AUTO_INCREMENT COMMENT '题目id',
  `creator_id` int NOT NULL COMMENT '创建者id',
  `subject_id` int DEFAULT NULL COMMENT '科目类型id',
  `creator_name` varchar(255) NOT NULL COMMENT '创建者id',
  `subject_name` varchar(255) DEFAULT NULL COMMENT '科目类型名称',
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '题目',
  `choice` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '选项',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片',
  `topic_type` int NOT NULL COMMENT '题目类型  0:单选题 1:多选题 2:判断题 3:填空题 4:简答题',
  `correct_answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '正确答案',
  `score` float DEFAULT '0' COMMENT '分数',
  `difficulty` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '中等' COMMENT '难度  简单,中等(默认),困难',
  `analysis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '答案分析',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`topic_id`),
  KEY `suoyin2` (`creator_id`) USING BTREE,
  CONSTRAINT `FK11` FOREIGN KEY (`creator_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

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
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `souyin1` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_grade
-- ----------------------------
DROP TABLE IF EXISTS `user_grade`;
CREATE TABLE `user_grade` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NOT NULL COMMENT '用户id',
  `classes_id` int NOT NULL COMMENT '班级id',
  `exam_id` int NOT NULL COMMENT '考试id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `classes_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名称',
  `exam_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '考试名称',
  `grade` double DEFAULT NULL COMMENT '得分',
  `grade_auto` double DEFAULT NULL COMMENT '得分(自动评分)',
  `answer_date` datetime DEFAULT NULL COMMENT '答题日期',
  `answer_time` int DEFAULT NULL COMMENT '答题时长',
  `mark_status` int DEFAULT '0' COMMENT '批改状态 0:待批改 1:批改完成',
  `exam_status` int DEFAULT '0' COMMENT '试卷状态 0:未完成  1:已完成',
  `mark_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=258 DEFAULT CHARSET=utf8;
