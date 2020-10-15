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

/*Data for the table `address` */

insert  into `address`(`id`,`uid`,`receiver`,`address`,`mobile`,`province`,`city`,`area`,`post`,`createdate`) values 
(2,1,'温宇航','水上公园','13145213417','山西省','吕梁市','文水县','032100','1599203771817'),
(9,2,'温yh2','回龙观街道','13145213417','北京市','北京市','昌平区','032100','1602491496814'),
(10,8,'温宇航','回龙观街道104号','13145213417','北京市','北京市','昌平区','100002','1602584381735');

/*Table structure for table `invite_rela` */

DROP TABLE IF EXISTS `invite_rela`;

CREATE TABLE `invite_rela` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `inviter_id` int(11) DEFAULT NULL,
  `createdate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `invite_rela` */

insert  into `invite_rela`(`id`,`uid`,`inviter_id`,`createdate`) values 
(1,15,8,'2020-10-14 16:53:19');

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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*Data for the table `order_` */

insert  into `order_`(`id`,`uid`,`pid`,`adid`,`STATUS`,`ordercode`,`couriernumber`,`createdate`) values 
(7,8,18,10,10,'N202010141107290001','13215665153135','2020-10-14 16:53:19'),
(8,8,18,10,20,'N202010141120060001',NULL,'2020-10-14 16:53:19'),
(9,8,18,10,30,'N202010141205010001',NULL,'2020-10-14 16:53:19'),
(10,8,18,10,10,'N202010141207540001',NULL,'2020-10-14 16:53:19'),
(11,8,18,10,10,'N202010141208540001',NULL,'2020-10-14 16:53:19'),
(12,8,18,10,10,'N202010141213280001',NULL,'2020-10-14 16:53:19'),
(13,8,18,10,10,'N202010141216450001',NULL,'2020-10-14 16:53:19'),
(14,8,18,10,10,'N202010141217230001',NULL,'2020-10-14 16:53:19'),
(15,8,18,10,10,'N202010141219130001',NULL,'2020-10-14 16:53:19'),
(16,8,18,10,10,'N202010141221040001',NULL,'2020-10-14 16:53:19'),
(17,8,18,10,10,'N202010141221180001',NULL,'2020-10-14 16:53:19'),
(18,8,18,10,10,'N202010141222580001',NULL,'2020-10-14 16:53:19'),
(19,8,18,10,10,'N202010141223330001',NULL,'2020-10-14 16:53:19'),
(20,8,18,10,10,'N202010141223560001',NULL,'2020-10-14 16:53:19'),
(21,8,18,10,10,'N202010141224160001',NULL,'2020-10-14 16:53:19'),
(22,8,18,10,10,'N202010141224360001',NULL,'2020-10-14 16:53:19'),
(23,8,17,10,10,'N202010141225030001',NULL,'2020-10-14 16:53:19'),
(24,8,17,10,10,'N202010141226250001',NULL,'2020-10-14 16:53:19'),
(25,8,17,10,10,'N202010141227080001',NULL,'2020-10-14 16:53:19'),
(26,8,17,10,10,'N202010141355360001',NULL,'2020-10-14 16:53:19'),
(27,8,17,10,10,'N202010141356070001',NULL,'2020-10-14 16:53:19'),
(28,8,17,10,10,'N202010141401050001',NULL,'2020-10-14 16:53:19'),
(29,8,17,10,10,'N202010141420590001',NULL,'2020-10-14 16:53:19'),
(30,8,17,10,10,'N202010141423420001',NULL,'2020-10-14 16:53:19'),
(31,8,17,10,10,'N202010141424230001',NULL,'2020-10-14 16:53:19'),
(32,8,17,10,10,'N202010141439100001',NULL,'2020-10-14 16:53:19'),
(33,8,17,10,10,'N202010151527360001',NULL,'2020-10-15 15:27:36'),
(34,8,17,10,10,'N202010151530470001',NULL,'2020-10-15 15:30:47'),
(35,8,17,10,10,'N202010151550270001',NULL,'2020-10-15 15:50:27');

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`coin`,`price`,`stock`,`subTitle`,`imageurl`,`energy`,`createdate`) values 
(4,'测试商品01',1111,199.98,0,'不包邮','img/product/1602559008089.jpg',23,'1602560891470'),
(5,'测试商品02',2222,99.98,20,'包邮','img/product/1602560910809.jpg',0,'1602560910945'),
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
(16,'测试商品13',13130,99.97,130,'包邮','img/product/1599105146605.jpg',99999,'1599105200576'),
(17,'测试商品14',14140,99.98,119,'不包邮','img/product/1602209739849.jpg',200,'1602209739964'),
(18,'测试商品15',15150,99.99,0,'包邮','img/product/1602210094530.jpg',102,'1602210094689');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `stepscoin` */

insert  into `stepscoin`(`id`,`uid`,`tran_desc`,`coin`,`rundate`,`convertsteps`,`createdate`) values 
(1,8,'步数转金币',3.04,'2020-09-04',3040,'1597652479055'),
(5,8,'购买 测试商品14 划扣',-14140,'20201015',0,'2020-10-15 15:50:27');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `stepsrecord` */

insert  into `stepsrecord`(`id`,`uid`,`steps`,`rundate`,`convertedsteps`,`createdate`) values 
(1,1,1028,'2020-09-04',0,'20200817');

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
  `energy_total` float DEFAULT '0' COMMENT '包邮能量',
  `createdate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`NAME`,`headimgurl`,`openid`,`unionid`,`phone`,`session_key`,`access_token`,`steps_total`,`coin_total`,`energy_total`,`createdate`) values 
(1,'87','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0','o_pKo1BoTiyGuiJ-HIvx9Hl_l9JM','o88Iy5mwZiWv9NzCZBdEwGkkwb70','13145213417',NULL,NULL,1,3.04,0,'1597652479055'),
(2,'听闻','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0','a_pKo1BoTiyGuiJ-HIvx9Hl_l9JM','a88Iy5mwZiWv9NzCZBdEwGkkwb70','18235825951',NULL,NULL,0,0,0,'1598242263436'),
(3,'孟加拉漫天的日光','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0','b_pKo1BoTiyGuiJ-HIvx9Hl_l9JM','b88Iy5mwZiWv9NzCZBdEwGkkwb70',NULL,NULL,NULL,0,0,0,'1598242399807'),
(4,'Ahau','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0','c_pKo1BoTiyGuiJ-HIvx9Hl_l9JM','c88Iy5mwZiWv9NzCZBdEwGkkwb70',NULL,NULL,NULL,0,0,0,'1598242487681'),
(5,'亲亲子衿','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0','d_pKo1BoTiyGuiJ-HIvx9Hl_l9JM','d88Iy5mwZiWv9NzCZBdEwGkkwb70',NULL,NULL,NULL,0,0,0,'1598242601667'),
(7,'知足常乐的小雪儿','http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0','e_pKo1BoTiyGuiJ-HIvx9Hl_l9JM','e88Iy5mwZiWv9NzCZBdEwGkkwb70',NULL,NULL,NULL,0,0,0,'1598243186320'),
(8,'风度','https://thirdwx.qlogo.cn/mmopen/vi_32/A4Dgzwibc1aUG3iaQcH9zNX776yLXQ4u9icus8MUdqDHeqxy4x1x80EgZPSCtqRgjRg5a5Pvs8icCQbW2ia9sibic2XSA/132','oiR4w5TGrBjEb5_MH6kEdur58aDU','okqcDw1qV3GwOoo95sHQIu-G1OIs',NULL,'6rrt1Ykk7PzveXNIiyllbQ==',NULL,0,87850,60490,'1600676713850'),
(15,'輸ㄋ〝风度','https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKy2a2yT95r6HGgGYsykL5DS1rIAAWcQSibqn2YsgkaY30hLPJIycc5yopCPovogr0px1I7vJV56Xg/132','oiR4w5eV5ZvJ4EctNtTRRNY2E-Mo','okqcDwyy5fzJjLyAUD4tEezdH8kw',NULL,'Fjn6iwQyZHAsNGmtGVDzww==',NULL,0,0,0,'2020-10-14 16:53:19');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
