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
  `uid` int(11) DEFAULT NULL,
  `receiver` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `post` varchar(20) DEFAULT NULL,
  `createdate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_address_USER` (`uid`),
  CONSTRAINT `fk_address_USER` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `address` */

/*Table structure for table `order_` */

DROP TABLE IF EXISTS `order_`;

CREATE TABLE `order_` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `adid` int(11) DEFAULT NULL,
  `STATUS` int(11) DEFAULT '10',
  `ordercode` varchar(50) DEFAULT NULL,
  `couriernumber` varchar(50) DEFAULT NULL,
  `createdate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order__USER` (`uid`),
  KEY `fk_order__address` (`adid`),
  KEY `fk_order__product` (`pid`),
  CONSTRAINT `fk_order__USER` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order_` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `coin` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `subTitle` varchar(255) DEFAULT NULL,
  `imageurl` varchar(255) DEFAULT NULL,
  `convertsteps` int(11) DEFAULT NULL,
  `createdate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`coin`,`price`,`stock`,`subTitle`,`imageurl`,`convertsteps`,`createdate`) values 
(4,'测试商品01',1111,99.98,0,'不包邮','img/product/1599116596537.jpg',0,'1599116596598'),
(5,'测试商品02',2222,99.98,20,'包邮','img/product/1599105076224.jpg',0,'1599105076337'),
(6,'测试商品03',3333,99.98,30,'不包邮','img/product/1599105116066.jpg',0,'1599105116129'),
(7,'测试商品04',4444,99.98,40,'包邮','img/product/1599105146605.jpg',0,'1599105146663'),
(8,'测试商品05',5555,99.98,50,'包邮','img/product/1599105176230.jpg',0,'1599105176289'),
(9,'测试商品06',6666,99.98,60,'不包邮','img/product/1599105146605.jpg',0,'1599105200576'),
(10,'测试商品07',7777,99.97,70,'包邮','img/product/1599105146605.jpg',0,'1599105200576'),
(11,'测试商品08',8888,99.97,80,'包邮','img/product/1599105146605.jpg',0,'1599105200576'),
(12,'测试商品09',9999,99.97,90,'包邮','img/product/1599105146605.jpg',0,'1599105200576'),
(13,'测试商品10',10100,99.97,100,'包邮','img/product/1599105146605.jpg',0,'1599105200576'),
(14,'测试商品11',11110,99.97,110,'包邮','img/product/1599105146605.jpg',0,'1599105200576'),
(15,'测试商品12',12120,99.97,120,'包邮','img/product/1599105146605.jpg',0,'1599105200576'),
(16,'测试商品13',13130,99.97,130,'包邮','img/product/1599105146605.jpg',0,'1599105200576');

/*Table structure for table `stepscoin` */

DROP TABLE IF EXISTS `stepscoin`;

CREATE TABLE `stepscoin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `coin` float DEFAULT NULL,
  `rundate` varchar(20) DEFAULT NULL,
  `convertsteps` int(11) DEFAULT NULL,
  `createdate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stepscoin_USER` (`uid`),
  CONSTRAINT `fk_stepscoin_USER` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `stepscoin` */

/*Table structure for table `stepsrecord` */

DROP TABLE IF EXISTS `stepsrecord`;

CREATE TABLE `stepsrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `steps` int(11) NOT NULL DEFAULT '0',
  `rundate` varchar(20) DEFAULT NULL,
  `convertsteps` int(11) NOT NULL DEFAULT '0',
  `createdate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stepsrecord_USER` (`uid`),
  CONSTRAINT `fk_stepsrecord_USER` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `stepsrecord` */

insert  into `stepsrecord`(`id`,`uid`,`steps`,`rundate`,`convertsteps`,`createdate`) values 
(1,1,1028,NULL,0,'20200817');

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
  `coin_total` int(11) DEFAULT '0' COMMENT '金币总量',
  `energy_total` float DEFAULT '0' COMMENT '包邮能量',
  `createdate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`NAME`,`headimgurl`,`openid`,`unionid`,`phone`,`session_key`,`access_token`,`steps_total`,`coin_total`,`energy_total`,`createdate`) values 
(1,'87','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0','o_pKo1BoTiyGuiJ-HIvx9Hl_l9JM','o88Iy5mwZiWv9NzCZBdEwGkkwb70',NULL,NULL,NULL,0,0,0,'1597652479055'),
(2,'听闻','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0','a_pKo1BoTiyGuiJ-HIvx9Hl_l9JM','a88Iy5mwZiWv9NzCZBdEwGkkwb70',NULL,NULL,NULL,0,0,0,'1598242263436'),
(3,'孟加拉漫天的日光','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0','b_pKo1BoTiyGuiJ-HIvx9Hl_l9JM','b88Iy5mwZiWv9NzCZBdEwGkkwb70',NULL,NULL,NULL,0,0,0,'1598242399807'),
(4,'Ahau','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0','c_pKo1BoTiyGuiJ-HIvx9Hl_l9JM','c88Iy5mwZiWv9NzCZBdEwGkkwb70',NULL,NULL,NULL,0,0,0,'1598242487681'),
(5,'亲亲子衿','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0','d_pKo1BoTiyGuiJ-HIvx9Hl_l9JM','d88Iy5mwZiWv9NzCZBdEwGkkwb70',NULL,NULL,NULL,0,0,0,'1598242601667'),
(7,'知足常乐的小雪儿','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0','e_pKo1BoTiyGuiJ-HIvx9Hl_l9JM','e88Iy5mwZiWv9NzCZBdEwGkkwb70',NULL,NULL,NULL,0,0,0,'1598243186320');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
