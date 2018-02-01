/*
Navicat MySQL Data Transfer

Source Server         : 50.116.3.99
Source Server Version : 50720
Source Host           : 50.116.3.99:3306
Source Database       : yeren_cms

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2017-12-06 19:17:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '标题名称',
  `sort` varchar(11) DEFAULT NULL COMMENT '排序',
  `status` varchar(11) DEFAULT NULL COMMENT '上线状态，0为下线，1为上线',
  `category_id` int(11) NOT NULL COMMENT '所属栏目ID',
  `create_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cms_article_data
-- ----------------------------
DROP TABLE IF EXISTS `cms_article_data`;
CREATE TABLE `cms_article_data` (
  `id` int(11) NOT NULL COMMENT '主键',
  `data` text COMMENT '文章内容',
  `create_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cms_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_category`;
CREATE TABLE `cms_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '栏目名称',
  `site_id` int(11) NOT NULL COMMENT '所属站点ID',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点ID（上一个栏目的ID）',
  `sort` varchar(11) DEFAULT NULL COMMENT '排序',
  `status` varchar(11) DEFAULT NULL COMMENT '上线状态',
  `create_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cms_link
-- ----------------------------
DROP TABLE IF EXISTS `cms_link`;
CREATE TABLE `cms_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '链接名称',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `category_id` int(11) NOT NULL COMMENT '所属栏目ID',
  `article_id` int(11) DEFAULT NULL COMMENT '标题ID',
  `sort` varchar(11) DEFAULT NULL COMMENT '排序',
  `status` varchar(11) DEFAULT NULL COMMENT '上线状态',
  `create_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cms_site
-- ----------------------------
DROP TABLE IF EXISTS `cms_site`;
CREATE TABLE `cms_site` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '站点名称',
  `sort` varchar(11) DEFAULT NULL COMMENT '排序',
  `status` varchar(11) DEFAULT NULL COMMENT '上线状态',
  `create_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
