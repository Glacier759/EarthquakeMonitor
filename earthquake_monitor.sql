-- MySQL dump 10.13  Distrib 5.6.12, for Linux (x86_64)
--
-- Host: localhost    Database: earthquake_monitor
-- ------------------------------------------------------
-- Server version	5.6.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `filter_rules_disaster`
--

DROP TABLE IF EXISTS `filter_rules_disaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filter_rules_disaster` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rule` varchar(50) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filter_rules_disaster`
--

LOCK TABLES `filter_rules_disaster` WRITE;
/*!40000 ALTER TABLE `filter_rules_disaster` DISABLE KEYS */;
INSERT INTO `filter_rules_disaster` VALUES (2,'新疆*地震*今晚','2015-05-02 12:28:09'),(3,'乌恰*地震*死亡','2015-05-26 14:33:54'),(4,'新疆*地震','2015-05-26 14:33:54');
/*!40000 ALTER TABLE `filter_rules_disaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filter_rules_pubsentiment`
--

DROP TABLE IF EXISTS `filter_rules_pubsentiment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filter_rules_pubsentiment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `matcher` varchar(100) NOT NULL,
  `unexist` varchar(100) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filter_rules_pubsentiment`
--

LOCK TABLES `filter_rules_pubsentiment` WRITE;
/*!40000 ALTER TABLE `filter_rules_pubsentiment` DISABLE KEYS */;
INSERT INTO `filter_rules_pubsentiment` VALUES (1,'福州*发生地震','S*福建[^，。；？！]发生[^，。；？！]','汶川 新西兰 玉树','2015-05-01 10:59:42'),(2,'我是斯巴达','S*厦门[^，。；？！]发生[^，。；？！]','汶川 新西兰 玉树','2015-05-01 10:59:59'),(4,'戴着眼镜的狗','匹配就是我','米有鼻子','2015-05-01 11:56:10'),(6,'这是一条测试','卧槽我不会写正则','你猜 逗比','2015-05-27 07:18:28'),(7,'来两发试试','正则什么鬼','这个是第一个','2015-05-27 07:19:36'),(8,'这个是第二个','啊哈哈哈哈哈 大水冲了龙王庙','武大郎','2015-05-27 07:19:36');
/*!40000 ALTER TABLE `filter_rules_pubsentiment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filter_rules_whitelist`
--

DROP TABLE IF EXISTS `filter_rules_whitelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filter_rules_whitelist` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(200) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filter_rules_whitelist`
--

LOCK TABLES `filter_rules_whitelist` WRITE;
/*!40000 ALTER TABLE `filter_rules_whitelist` DISABLE KEYS */;
INSERT INTO `filter_rules_whitelist` VALUES (3,'www.glacierlx.com','2015-05-01 11:42:05'),(5,'blog.glacierlx.com','2015-05-27 07:43:17');
/*!40000 ALTER TABLE `filter_rules_whitelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spider_accounts`
--

DROP TABLE IF EXISTS `spider_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spider_accounts` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` char(50) NOT NULL,
  `password` char(50) NOT NULL,
  `lastlogin` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` char(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spider_accounts`
--

LOCK TABLES `spider_accounts` WRITE;
/*!40000 ALTER TABLE `spider_accounts` DISABLE KEYS */;
INSERT INTO `spider_accounts` VALUES (1,'18092794430','lr1992','2015-05-01 09:00:33','weibo'),(2,'13289212979','Rlx0825leehom','2015-05-02 12:29:29','weibo'),(3,'421186071rlx@sina.com','Rlx0825leehom','2015-05-02 12:34:49','weibo'),(4,'rlx421186071@sina.com','Rlx0825leehom','2015-05-02 12:36:35','weibo'),(5,'421186071rlx@sina.cn','Rlx0825leehom','2015-05-02 12:39:04','weibo'),(6,'rrenlixiang@163.com','Rlx0825leehom','2015-05-02 12:47:37','weibo'),(7,'rrenlixiang@sina.com','Rlx0825leehom','2015-05-02 12:48:15','weibo'),(8,'rlx421186071@163.com','Rlx0825leehom','2015-05-02 12:48:15','weibo'),(9,'OurHom.759@gmail.com','Rlx0825leehom','2015-05-02 12:48:15','weibo'),(10,'glacierlx@sina.cn','Rlx0825leehom','2015-05-02 12:52:48','weibo'),(12,'glacier@xiyoulinux.org','Rlx0825leehom','2015-05-02 12:53:27','weibo'),(13,'rlx421186071@sina.cn','Rlx0825leehom','2015-05-02 12:56:23','weibo'),(14,'glacier421186071@163.com','Rlx0825leehom','2015-05-02 13:00:14','weibo'),(15,'glacier421186071@sina.com','Rlx0825leehom','2015-05-02 13:01:19','weibo'),(16,'rrenlixiang@sina.cn','Rlx0825leehom','2015-05-02 13:03:09','weibo'),(17,'rleehom@sina.cn','Rlx0825leehom','2015-05-02 13:03:31','weibo'),(18,'1498781360@qq.com','leirisheng','2015-05-02 13:19:33','weibo'),(20,'2726492626@qq.com','kaishi','2015-04-09 03:46:20','weibo'),(21,'1825682313@qq.com','kaishi','2015-05-01 08:58:50','weibo'),(22,'xy_lr_92@126.com','lr1992','2015-05-01 08:58:55','weibo'),(23,'xylr92@126.com','lr1992','2015-05-01 08:58:59','weibo');
/*!40000 ALTER TABLE `spider_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spider_information`
--

DROP TABLE IF EXISTS `spider_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spider_information` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(200) NOT NULL,
  `title` varchar(500) NOT NULL,
  `source` mediumtext NOT NULL,
  `type` int(1) NOT NULL,
  `rule_id` int(10) unsigned DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spider_information`
--

LOCK TABLES `spider_information` WRITE;
/*!40000 ALTER TABLE `spider_information` DISABLE KEYS */;
INSERT INTO `spider_information` VALUES (1,'http://spider.glacierlx.com','智障儿童的自我逆袭','这里啥都减肥华盛顿和副驾驶的fks的交锋上的金佛ijsdlkfjksdljflk上的副教授的减肥了开始的积分卡lsd就',0,2,'2015-05-27 08:15:33',1),(2,'http://blog.glacierlx.com','这是我的博客','我在这热等着你回来啊等着你回来看那桃花开急急急  在线等！！！',1,1,'2015-05-27 11:34:09',0),(3,'http://www.baidu.com','这是我的ceshi','我asasdasdasdasdasdasd！',1,6,'2015-05-27 12:00:03',0);
/*!40000 ALTER TABLE `spider_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_config`
--

DROP TABLE IF EXISTS `system_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_config` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(5) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_config`
--

LOCK TABLES `system_config` WRITE;
/*!40000 ALTER TABLE `system_config` DISABLE KEYS */;
INSERT INTO `system_config` VALUES (1,0,'2015-05-28 09:09:24',0),(2,1,'2015-05-28 09:09:34',1);
/*!40000 ALTER TABLE `system_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `password` char(32) NOT NULL,
  `privilege` int(1) DEFAULT '0',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `realname` varchar(10) DEFAULT NULL,
  `workplace` varchar(200) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `qqnumber` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'戴着眼镜的狗','glacier@xiyoulinux.org','13289212979','q',1,'2015-05-27 05:38:52','热力学','中华人民共和国','搬砖的','32124442s'),(2,NULL,'421186071@qq.com',NULL,'111',0,'2015-05-26 07:31:27',NULL,NULL,NULL,NULL),(16,'戴着眼镜的狗','OurHom.759@gmail.com','13679257840','q',0,'2015-05-27 13:53:54','热','中国','你猜','111111111');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-28 18:49:30
