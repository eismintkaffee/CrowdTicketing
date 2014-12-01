CREATE DATABASE  IF NOT EXISTS `eventticketing` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `eventticketing`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: eventticketing
-- ------------------------------------------------------
-- Server version	5.6.10

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
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(100) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(60) NOT NULL,
  `username` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_messages_users1_idx` (`username`),
  CONSTRAINT `fk_messages_users1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'Hello','This is a dummy message.','John Purcell','john@caveofprogramming.com','johnwpurcell'),(11,'This is a test message','Some message or other goes here.','John purcell','john@caveofprogramming.com','franzkafka'),(12,'Another test message','Please help me, I have fallen down a well and need assistance.','John Purcell','john@caveofprogramming.com','johnwpurcell'),(13,'Existential Angst','Are you still alive?','Franz Kafka','kafka@caveofprogramming.com','johnwpurcell'),(14,'Hungarian','Hi, interest in learning Hungarian?','H. P. Lovecraft','learnhungarianfast@gmail.com','Lovecraft'),(15,'testing','testing','H. P. Lovecraft','lovecraft@caveofprogramming.com','Lovecraft'),(16,'testing','testing','H. P. Lovecraft','lovecraft@caveofprogramming.com','Lovecraft'),(17,'Hello there','Does this work?','John Purcell','johnwpurcell@gmail.com','Lovecraft'),(18,'hello there','hello there John','John Purcell','johnwpurcell@gmail.com','Lovecraft'),(19,'Hello there','This is a test email','John Purcell','johnwpurcell@gmail.com','Lovecraft'),(20,'hello there','this is a test email','John Purcell','johnwpurcell@gmail.com','Lovecraft'),(21,'hello there','this is a test email','John Purcell','johnwpurcell@gmail.com','Lovecraft'),(22,'test test test','test test test test','test test','john@caveofprogramming.com','Lovecraft'),(23,'test test test','test test test test','test test','john@caveofprogramming.com','Lovecraft'),(24,'test test test','test test test test','test test','john@caveofprogramming.com','Lovecraft'),(25,'test test test','test test test test','test test','john@caveofprogramming.com','Lovecraft'),(26,'test test test','test test test test','test test','john@caveofprogramming.com','Lovecraft'),(27,'test test test','test test test test','test test','john@caveofprogramming.com','Lovecraft'),(28,'test test test','test test test test','test test','john@caveofprogramming.com','Lovecraft'),(29,'test message','test message','H. P. Lovecraft','john@caveofprogramming.com','johnwpurcell'),(30,'hii hello how are you','this is a message','chahtrapal sisodiya','cjsisodiya@gmail.com','chhatrapal'),(31,'hii there','nfjnjfvnjnd','chahtrapal sisodiya','cjsisodiya@gmail.com','chhatrapal'),(32,'dnvijnvjicvnijdn','rgrfdncj njfnj h fvncn jc n   c vvf        v  f vc ','chahtrapal sisodiya','cjsisodiya@gmail.com','chhatrapal'),(33,'fnvjfnvjvnjvnj','jjfvjv c jv jfdvfd vvvfvgbgbgbbgb','chahtrapal sisodiya','cjsisodiya@gmail.com','chhatrapal1'),(34,'rgffcvgdfvfgrtgdvf','grgbdfvfdgdvfvdfvvdfvfdvdfv','chahtrapal sisodiya','cjsisodiya@gmail.com','chhatrapal'),(35,'Hello','Testing Testing','Ramya Meruva','meruvaramya116@gmail.com','chhatrapal'),(36,'njfnfndfnifn','neuifnjnjnjcndjncin','chhatrapal','pascle987@gmail.com','RamyaMeruva'),(37,'Hii this is test user','Hello I want to know how can I reach to the place .','newusername','something@gmail.com','RamyaMeruva');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-11 20:19:21
