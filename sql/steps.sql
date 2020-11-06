/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.5.15 : Database - many_steps
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`many_steps` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `many_steps`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '用户id',
  `receiver` varchar(20) DEFAULT NULL COMMENT '联系人信息',
  `address` varchar(512) DEFAULT NULL COMMENT '详细地址',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `province` varchar(30) DEFAULT NULL COMMENT '省',
  `city` varchar(30) DEFAULT NULL COMMENT '市',
  `area` varchar(30) DEFAULT NULL COMMENT '区',
  `post` varchar(20) DEFAULT NULL COMMENT '邮编',
  `createdate` varchar(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`,`uid`),
  KEY `fk_address_USER` (`uid`),
  CONSTRAINT `fk_address_USER` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Table structure for table `invite_rela` */

DROP TABLE IF EXISTS `invite_rela`;

CREATE TABLE `invite_rela` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `inviter_id` int(11) DEFAULT NULL,
  `createdate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `order_` */

DROP TABLE IF EXISTS `order_`;

CREATE TABLE `order_` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `pid` int(11) DEFAULT NULL COMMENT '商品id',
  `adid` int(10) DEFAULT NULL COMMENT '地址id',
  `STATUS` int(11) DEFAULT '10' COMMENT '订单状态',
  `ordercode` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `couriernumber` varchar(50) DEFAULT NULL COMMENT '快递编号',
  `createdate` varchar(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `fk_order__USER` (`uid`),
  KEY `fk_order__product` (`pid`),
  CONSTRAINT `fk_order__USER` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `coin` float DEFAULT NULL COMMENT '步数金币',
  `price` float DEFAULT NULL COMMENT '商品价格',
  `stock` int(11) DEFAULT NULL COMMENT '商品库存',
  `subTitle` varchar(255) DEFAULT NULL COMMENT '商品标签',
  `imageurl` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `energy` float DEFAULT NULL COMMENT '包邮能量',
  `createdate` varchar(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Table structure for table `stepscoin` */

DROP TABLE IF EXISTS `stepscoin`;

CREATE TABLE `stepscoin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `tran_desc` varchar(255) DEFAULT '步数转金币' COMMENT '交易说明',
  `coin` float DEFAULT '0' COMMENT '单次转换金币',
  `rundate` varchar(20) DEFAULT NULL COMMENT '运动日期',
  `convertsteps` int(11) DEFAULT NULL COMMENT '单次转换步数',
  `createdate` varchar(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `fk_stepscoin_USER` (`uid`),
  CONSTRAINT `fk_stepscoin_USER` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Table structure for table `stepsrecord` */

DROP TABLE IF EXISTS `stepsrecord`;

CREATE TABLE `stepsrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `steps` int(11) NOT NULL DEFAULT '0' COMMENT '单日步数',
  `rundate` varchar(20) DEFAULT NULL COMMENT '运动日期',
  `convertedsteps` int(11) NOT NULL DEFAULT '0' COMMENT '已转换步数',
  `createdate` varchar(20) DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `fk_stepsrecord_USER` (`uid`),
  CONSTRAINT `fk_stepsrecord_USER` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `headimgurl` varchar(255) DEFAULT NULL,
  `openid` varchar(255) DEFAULT NULL,
  `unionid` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `session_key` varchar(255) DEFAULT NULL,
  `access_token` varchar(512) DEFAULT NULL,
  `steps_total` int(11) DEFAULT '0' COMMENT '步数总量',
  `coin_total` float DEFAULT '0' COMMENT '金币总量',
  `invite_total` int(11) DEFAULT '0' COMMENT '邀请人数',
  `createdate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
