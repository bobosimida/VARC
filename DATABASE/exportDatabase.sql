/*
SQLyog v10.2 
MySQL - 5.5.21 : Database - varc
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`varc` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `varc`;

/*Table structure for table `cunmindaibiao` */

DROP TABLE IF EXISTS `cunmindaibiao`;

CREATE TABLE `cunmindaibiao` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `user_id` int(11) NOT NULL COMMENT '村民代表',
  `minzhuxuanju_id` int(11) NOT NULL COMMENT '所属民主选举表的哪条民主选举',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `minzhuxuanju_id` (`minzhuxuanju_id`),
  CONSTRAINT `cunmindaibiao_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `cunmindaibiao_ibfk_2` FOREIGN KEY (`minzhuxuanju_id`) REFERENCES `minzhuxuanju` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cunmindaibiao` */

/*Table structure for table `cunweihui` */

DROP TABLE IF EXISTS `cunweihui`;

CREATE TABLE `cunweihui` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `jieshu` int(11) NOT NULL COMMENT '该村第多少届的村委会',
  `zhiwei` varchar(20) NOT NULL COMMENT '在村委会所任职位',
  `starttime` date NOT NULL COMMENT '任职时间',
  `overtime` date DEFAULT NULL COMMENT '卸任时间,如果为空则代表任职至今',
  `user_id` int(11) NOT NULL COMMENT '任职人',
  `villageinfo_id` int(11) NOT NULL COMMENT '所在村',
  `minzhuxuanju_id` int(11) NOT NULL COMMENT '所属民主选举表的哪条民主选举',
  PRIMARY KEY (`id`),
  KEY `minzhuxuanju_id` (`minzhuxuanju_id`),
  KEY `user_id` (`user_id`),
  KEY `villageinfo_id` (`villageinfo_id`),
  CONSTRAINT `cunweihui_ibfk_1` FOREIGN KEY (`minzhuxuanju_id`) REFERENCES `minzhuxuanju` (`id`),
  CONSTRAINT `cunweihui_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `cunweihui_ibfk_3` FOREIGN KEY (`villageinfo_id`) REFERENCES `villageinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cunweihui` */

/*Table structure for table `email` */

DROP TABLE IF EXISTS `email`;

CREATE TABLE `email` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `send_time` int(11) NOT NULL COMMENT '发件时间',
  `isread` char(1) NOT NULL DEFAULT '0' COMMENT '该信件是否已读， 默认为 0 未读， 1  已读',
  `content` varchar(500) NOT NULL COMMENT '邮件内容',
  `send_id` int(11) NOT NULL COMMENT '发件人id',
  `get_id` int(11) NOT NULL COMMENT '收件人id',
  PRIMARY KEY (`id`),
  KEY `send_id` (`send_id`),
  KEY `get_id` (`get_id`),
  CONSTRAINT `email_ibfk_1` FOREIGN KEY (`send_id`) REFERENCES `user` (`id`),
  CONSTRAINT `email_ibfk_2` FOREIGN KEY (`get_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `email` */

/*Table structure for table `geostate` */

DROP TABLE IF EXISTS `geostate`;

CREATE TABLE `geostate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `info` varchar(6) NOT NULL COMMENT '地理状况信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `geostate` */

insert  into `geostate`(`id`,`info`) values (1,'山区'),(2,'半山区'),(3,'平原');

/*Table structure for table `gongzuojihua` */

DROP TABLE IF EXISTS `gongzuojihua`;

CREATE TABLE `gongzuojihua` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `send_time` int(11) NOT NULL COMMENT '发件时间',
  `isread` char(1) NOT NULL DEFAULT '0' COMMENT '该工作计划是否已读， 默认为 0 未读， 1  已读',
  `jhnd` varchar(11) NOT NULL COMMENT '计划年度',
  `jhmc` varchar(50) NOT NULL COMMENT '计划名称',
  `jhbm` varchar(50) NOT NULL COMMENT '计划部门',
  `jhlx` varchar(50) NOT NULL COMMENT '计划类型',
  `starttime` date NOT NULL COMMENT '计划开始时间',
  `endtime` date DEFAULT NULL COMMENT '计划结束时间',
  `user_id` int(11) NOT NULL COMMENT '实施计划人',
  `content` mediumblob NOT NULL COMMENT '计划内容',
  `send_id` int(11) NOT NULL COMMENT '发件人id',
  `get_id` int(11) NOT NULL COMMENT '收件人id',
  PRIMARY KEY (`id`),
  KEY `send_id` (`send_id`),
  KEY `get_id` (`get_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `gongzuojihua_ibfk_1` FOREIGN KEY (`send_id`) REFERENCES `user` (`id`),
  CONSTRAINT `gongzuojihua_ibfk_2` FOREIGN KEY (`get_id`) REFERENCES `user` (`id`),
  CONSTRAINT `gongzuojihua_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `gongzuojihua` */

/*Table structure for table `houxuanren` */

DROP TABLE IF EXISTS `houxuanren`;

CREATE TABLE `houxuanren` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `piaoshu` int(11) NOT NULL COMMENT '该候选人所获得的票数',
  `timinfangshi_id` int(11) NOT NULL COMMENT '提名方式',
  `user_id` int(11) NOT NULL COMMENT '候选人',
  `minzhuxuanju_id` int(11) NOT NULL COMMENT '所属民主选举表的哪条民主选举',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `minzhuxuanju_id` (`minzhuxuanju_id`),
  KEY `timinfangshi_id` (`timinfangshi_id`),
  CONSTRAINT `houxuanren_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `houxuanren_ibfk_2` FOREIGN KEY (`minzhuxuanju_id`) REFERENCES `minzhuxuanju` (`id`),
  CONSTRAINT `houxuanren_ibfk_3` FOREIGN KEY (`timinfangshi_id`) REFERENCES `timinfangshi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `houxuanren` */

/*Table structure for table `livestate` */

DROP TABLE IF EXISTS `livestate`;

CREATE TABLE `livestate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `info` char(4) NOT NULL COMMENT '居住信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `livestate` */

insert  into `livestate`(`id`,`info`) values (1,'集中'),(2,'一般'),(3,'分散');

/*Table structure for table `minzhuguanli` */

DROP TABLE IF EXISTS `minzhuguanli`;

CREATE TABLE `minzhuguanli` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `tianbaoniandu` date NOT NULL COMMENT '填报年度',
  `minshixietiao` char(1) NOT NULL DEFAULT '0' COMMENT '是否有民事协调委员会，默认 0 没有，1 有',
  `cunweizizhi` char(1) NOT NULL DEFAULT '0' COMMENT '是否有村委自治章程， 默认 0 没有，1 有',
  `bamiancunweihuichengyuan` char(1) NOT NULL DEFAULT '0' COMMENT '是否罢免村委会成员，默认 0 不罢免， 1 罢免',
  `cunweihuirenshu` char(2) NOT NULL COMMENT '村委会成员人数',
  `bamianjianduxiaozuchengyuan` char(1) NOT NULL DEFAULT '0' COMMENT '是否罢免监督小组成员， 默认 0 不罢免，1 罢免',
  `bamianlicaixiaozuchengyuan` char(1) NOT NULL DEFAULT '0' COMMENT '是否罢免理财小组成员， 默认 0 不罢免，1 罢免',
  `shifandanwei` char(1) NOT NULL DEFAULT '0' COMMENT '是否是示范单位， 默认 0 不是，1 是',
  `villageinfo_id` int(11) NOT NULL COMMENT '所属村',
  PRIMARY KEY (`id`),
  KEY `villageinfo_id` (`villageinfo_id`),
  CONSTRAINT `minzhuguanli_ibfk_1` FOREIGN KEY (`villageinfo_id`) REFERENCES `villageinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `minzhuguanli` */

/*Table structure for table `minzhujiandu` */

DROP TABLE IF EXISTS `minzhujiandu`;

CREATE TABLE `minzhujiandu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `tianbaoniandu` date NOT NULL COMMENT '填报年度',
  `gongkaishijian` date NOT NULL COMMENT '公开时间',
  `caiwu` varchar(500) DEFAULT NULL COMMENT '财务公开',
  `cunwu` varchar(500) DEFAULT NULL COMMENT '村务公开',
  `zhengwu` varchar(500) DEFAULT NULL COMMENT '政务公开',
  `gongkaixingshi` varchar(100) DEFAULT NULL COMMENT '公开形式',
  `cwhcybpi` int(11) NOT NULL COMMENT '村委会成员被评议次数',
  `cwjdcybpy` int(11) NOT NULL COMMENT '村务监督成员被评议次数',
  `cglrywgbt` int(11) NOT NULL COMMENT '存管理人员误工补贴次数',
  `villageinfo_id` int(11) NOT NULL COMMENT '所属村',
  PRIMARY KEY (`id`),
  KEY `villageinfo_id` (`villageinfo_id`),
  CONSTRAINT `minzhujiandu_ibfk_1` FOREIGN KEY (`villageinfo_id`) REFERENCES `villageinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `minzhujiandu` */

/*Table structure for table `minzhujuece` */

DROP TABLE IF EXISTS `minzhujuece`;

CREATE TABLE `minzhujuece` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `villageinfo_id` int(11) NOT NULL COMMENT '决策会议表所属村',
  `huiyibaobiao` mediumblob NOT NULL COMMENT '报表文件',
  PRIMARY KEY (`id`),
  KEY `villageinfo_id` (`villageinfo_id`),
  CONSTRAINT `minzhujuece_ibfk_1` FOREIGN KEY (`villageinfo_id`) REFERENCES `villageinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `minzhujuece` */

/*Table structure for table `minzhuxuanju` */

DROP TABLE IF EXISTS `minzhuxuanju`;

CREATE TABLE `minzhuxuanju` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `tianbaoniandu` date NOT NULL COMMENT '填报年度',
  `shangbaoriqi` date NOT NULL COMMENT '上报日期',
  `gongzuobushu` varchar(1000) NOT NULL COMMENT '工作部署',
  `shishifangan` mediumblob COMMENT '实施方案',
  `xuanjuri` date NOT NULL COMMENT '选举日',
  `toupiaofangshi_id` int(11) NOT NULL COMMENT '投票方式',
  `xuanjujindu` char(1) NOT NULL DEFAULT '0' COMMENT '选举进度情况，默认值 0 未完成, 1 完成',
  `villageinfo_id` int(11) NOT NULL COMMENT '所在村',
  PRIMARY KEY (`id`),
  KEY `villageinfo_id` (`villageinfo_id`),
  KEY `toupiaofangshi_id` (`toupiaofangshi_id`),
  CONSTRAINT `minzhuxuanju_ibfk_1` FOREIGN KEY (`villageinfo_id`) REFERENCES `villageinfo` (`id`),
  CONSTRAINT `minzhuxuanju_ibfk_2` FOREIGN KEY (`toupiaofangshi_id`) REFERENCES `toupiaofangshi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `minzhuxuanju` */

/*Table structure for table `nongcunshequjianshe` */

DROP TABLE IF EXISTS `nongcunshequjianshe`;

CREATE TABLE `nongcunshequjianshe` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `tianbaoniandu` date NOT NULL COMMENT '填报年度',
  `clldxtjz` char(1) NOT NULL COMMENT '是否成立领导协调机制',
  `qtbm` varchar(20) DEFAULT NULL COMMENT '牵头部门',
  `jtlxrxm` varchar(20) DEFAULT NULL COMMENT '具体联系人姓名',
  `jtlxrdh` varchar(12) DEFAULT NULL COMMENT '具体联系人电话',
  `fzrzw` varchar(20) DEFAULT NULL COMMENT '负责人职务',
  `nrczys` char(1) NOT NULL COMMENT '是否纳入财政预算',
  `ysbz` int(11) DEFAULT NULL COMMENT '预算标准',
  `xzystrje` int(11) DEFAULT NULL COMMENT '乡镇以上投入经费金额',
  `xzbjtrje` int(11) DEFAULT NULL COMMENT '乡镇本级投入经费金额',
  `villageinfo_id` int(11) NOT NULL COMMENT '所属村',
  PRIMARY KEY (`id`),
  KEY `villageinfo_id` (`villageinfo_id`),
  CONSTRAINT `nongcunshequjianshe_ibfk_1` FOREIGN KEY (`villageinfo_id`) REFERENCES `villageinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `nongcunshequjianshe` */



/*Table structure for table `roll` */

DROP TABLE IF EXISTS `roll`;

CREATE TABLE `roll` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `roll` varchar(10) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `roll` */

insert  into `roll`(`id`,`roll`) values (1,'普通用户'),(2,'村民'),(3,'村委会人员'),(4,'乡镇政府人员'),(5,'系统管理员');

/*Table structure for table `timinfangshi` */

DROP TABLE IF EXISTS `timinfangshi`;

CREATE TABLE `timinfangshi` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `fangshi` varchar(30) NOT NULL COMMENT '提名方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `timinfangshi` */

/*Table structure for table `tongxunlu` */

DROP TABLE IF EXISTS `tongxunlu`;

CREATE TABLE `tongxunlu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `self_id` int(11) NOT NULL COMMENT '持有人id',
  `other_id` int(11) NOT NULL COMMENT '被持有人id',
  PRIMARY KEY (`id`),
  KEY `self_id` (`self_id`),
  KEY `other_id` (`other_id`),
  CONSTRAINT `tongxunlu_ibfk_1` FOREIGN KEY (`self_id`) REFERENCES `user` (`id`),
  CONSTRAINT `tongxunlu_ibfk_2` FOREIGN KEY (`other_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tongxunlu` */

/*Table structure for table `toupiaofangshi` */

DROP TABLE IF EXISTS `toupiaofangshi`;

CREATE TABLE `toupiaofangshi` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `fangshi` varchar(200) NOT NULL COMMENT '投票方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `toupiaofangshi` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `account` varchar(20) NOT NULL COMMENT '用户账号',
  `pwd` varchar(20) NOT NULL COMMENT '用户密码',
  `NAME` varchar(20) DEFAULT NULL COMMENT '用户姓名',
  `unit` varchar(50) DEFAULT NULL COMMENT '用户所在单位',
  `tel` varchar(12) DEFAULT NULL COMMENT '用户手机号码',
  `email` varchar(20) DEFAULT NULL COMMENT '用户邮箱',
  `sex` char(2) DEFAULT NULL COMMENT '用户性别',
  `age` varchar(3) DEFAULT NULL COMMENT '用户年龄',
  `roll_id` int(11) NOT NULL COMMENT '用户角色',
  `villageinfo_id` int(11) DEFAULT NULL COMMENT '用户所属村庄',
  PRIMARY KEY (`id`),
  KEY `roll_id` (`roll_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`roll_id`) REFERENCES `roll` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

/*Table structure for table `villageinfo` */

DROP TABLE IF EXISTS `villageinfo`;

CREATE TABLE `villageinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `NAME` varchar(20) NOT NULL COMMENT '村庄名字',
  `sheng` varchar(12) NOT NULL COMMENT '所在省',
  `shi` varchar(12) NOT NULL COMMENT '所在市',
  `qu` varchar(12) NOT NULL COMMENT '所在区',
  `xiang` varchar(12) NOT NULL COMMENT '所在乡',
  `dibao` int(11) NOT NULL COMMENT '低保人数',
  `wubao` int(11) NOT NULL COMMENT '五保人数',
  `canji` int(11) NOT NULL COMMENT '残疾人数',
  `shengchanzongzhi` int(11) NOT NULL COMMENT '生产总值',
  `renjunshouru` int(11) NOT NULL COMMENT '人均纯收入',
  `nongyechanzhi` int(11) NOT NULL COMMENT '农业产值',
  `cunshouru` int(11) NOT NULL COMMENT '存集体收入',
  `cunzhaiwu` int(11) NOT NULL COMMENT '存集体债务',
  `zirancun` char(1) NOT NULL DEFAULT '0' COMMENT '是否是自然村 ，默认值 0 不是，1 是',
  `cunmingxiaozu` int(11) NOT NULL COMMENT '村明小组数',
  `xiaqumianji` int(11) NOT NULL COMMENT '辖区总面积',
  `gendimianji` int(11) NOT NULL COMMENT '耕地总面积',
  `tongshui` char(1) NOT NULL DEFAULT '0' COMMENT '是否通水，默认值 0 未通水， 1 通水',
  `yinshuikunnan` char(1) NOT NULL DEFAULT '0' COMMENT '是否饮水困难，默认值 0 饮水困难， 1 饮水不困难',
  `tongdian` char(1) NOT NULL DEFAULT '0' COMMENT '是否通电，默认值 0 未通电， 1 通电',
  `cuncuntong` char(1) NOT NULL DEFAULT '0' COMMENT '是否村村通，默认值 0 不是村村通， 1 是村村通',
  `tongwangluo` char(1) NOT NULL DEFAULT '0' COMMENT '是否通网络，默认值 0 未通网络， 1 通网络',
  `tongdianhua` char(1) NOT NULL DEFAULT '0' COMMENT '是否通电话，默认值 0 未通电话， 1 通电话',
  `tongyouxian` char(1) NOT NULL DEFAULT '0' COMMENT '是否通有线，默认值 0 未通有线， 1 通有线',
  `cunxiaoxue` int(11) NOT NULL DEFAULT '0' COMMENT '村小学个数',
  `cunzhongxue` int(11) NOT NULL DEFAULT '0' COMMENT '村中学个数',
  `renkouzongshu` int(11) NOT NULL DEFAULT '0' COMMENT '人口总数',
  `renkouzonghushu` int(11) NOT NULL DEFAULT '0' COMMENT '人口总户数',
  `changzhurenkoushu` int(11) NOT NULL DEFAULT '0' COMMENT '常住人口数',
  `changzhurenkouzonghushu` int(11) NOT NULL DEFAULT '0' COMMENT '常住人口总户数',
  `hujirenkoushu` int(11) NOT NULL DEFAULT '0' COMMENT '户籍人口数',
  `hujirenkouzonghushu` int(11) NOT NULL DEFAULT '0' COMMENT '户籍人口总户数',
  `nongyerenkoushu` int(11) NOT NULL DEFAULT '0' COMMENT '农业人口数',
  `liudongrenkoushu` int(11) NOT NULL DEFAULT '0' COMMENT '流动人口数',
  `renzaihuzaishu` int(11) NOT NULL DEFAULT '0' COMMENT '人在户在人数',
  `renzaihubuzaishu` int(11) NOT NULL DEFAULT '0' COMMENT '人在户不在人数',
  `renbuzaihuzaishu` int(11) NOT NULL DEFAULT '0' COMMENT '人不在户在人数',
  `livestate_id` int(11) NOT NULL COMMENT '村庄居住信息',
  `geostate_id` int(11) NOT NULL COMMENT '村庄地理状况信息',
  PRIMARY KEY (`id`),
  KEY `livestate_id` (`livestate_id`),
  KEY `geostate_id` (`geostate_id`),
  CONSTRAINT `villageinfo_ibfk_1` FOREIGN KEY (`livestate_id`) REFERENCES `livestate` (`id`),
  CONSTRAINT `villageinfo_ibfk_2` FOREIGN KEY (`geostate_id`) REFERENCES `geostate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `villageinfo` */

/*Table structure for table `xiaohua` */

DROP TABLE IF EXISTS `xiaohua`;

CREATE TABLE `xiaohua` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `user_id` int(11) NOT NULL COMMENT '发布人id',
  `content` varchar(300) NOT NULL COMMENT '内容',
  `puttime` date NOT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `xiaohua_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `xiaohua` */

/*Table structure for table `xuanjuweiyuanhui` */

DROP TABLE IF EXISTS `xuanjuweiyuanhui`;

CREATE TABLE `xuanjuweiyuanhui` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `user_id` int(11) NOT NULL COMMENT '选举委员会人员',
  `minzhuxuanju_id` int(11) NOT NULL COMMENT '所属民主选举表的哪条民主选举',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `minzhuxuanju_id` (`minzhuxuanju_id`),
  CONSTRAINT `xuanjuweiyuanhui_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `xuanjuweiyuanhui_ibfk_2` FOREIGN KEY (`minzhuxuanju_id`) REFERENCES `minzhuxuanju` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `xuanjuweiyuanhui` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
