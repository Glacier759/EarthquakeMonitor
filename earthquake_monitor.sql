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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filter_rules_disaster`
--

LOCK TABLES `filter_rules_disaster` WRITE;
/*!40000 ALTER TABLE `filter_rules_disaster` DISABLE KEYS */;
INSERT INTO `filter_rules_disaster` VALUES (1,'任立翔*逗比','2015-05-02 12:28:09'),(2,'新疆*地震*今晚','2015-05-02 12:28:09');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filter_rules_pubsentiment`
--

LOCK TABLES `filter_rules_pubsentiment` WRITE;
/*!40000 ALTER TABLE `filter_rules_pubsentiment` DISABLE KEYS */;
INSERT INTO `filter_rules_pubsentiment` VALUES (1,'福州*发生地震','S*福建[^，。；？！]发生[^，。；？！]','汶川 新西兰 玉树','2015-05-01 10:59:42'),(2,'我是斯巴达','S*厦门[^，。；？！]发生[^，。；？！]','汶川 新西兰 玉树','2015-05-01 10:59:59'),(4,'戴着眼镜的狗','匹配就是我','米有鼻子','2015-05-01 11:56:10'),(5,'戴着眼镜的狗','匹配就是我','米有鼻子','2015-05-01 11:56:47');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filter_rules_whitelist`
--

LOCK TABLES `filter_rules_whitelist` WRITE;
/*!40000 ALTER TABLE `filter_rules_whitelist` DISABLE KEYS */;
INSERT INTO `filter_rules_whitelist` VALUES (3,'www.glacierlx.com','2015-05-01 11:42:05');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spider_information`
--

LOCK TABLES `spider_information` WRITE;
/*!40000 ALTER TABLE `spider_information` DISABLE KEYS */;
/*!40000 ALTER TABLE `spider_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20),
  `email` varchar(50),
  `mobile` varchar(20),
  `password` char(32)NOT NULL,
  `privilege` int(1) DEFAULT '0',
  `create_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  `realname` varchar(10),
  `workplace` varchar(200),
  `position` varchar(100),
  `qqnumber` varchar(15),
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'戴着眼镜的狗','glacier@xiyoulinux.org','13289212979','private info',1,'2015-05-02 04:42:48','','','','');
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

-- Dump completed on 2015-05-26 14:53:20
