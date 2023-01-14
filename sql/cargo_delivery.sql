-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: cargo_delivery
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idCargoType` int NOT NULL,
  `weight` double DEFAULT NULL,
  `length` double DEFAULT NULL,
  `width` double DEFAULT NULL,
  `height` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `cargoFK_idx` (`idCargoType`),
  CONSTRAINT `cargoFK` FOREIGN KEY (`idCargoType`) REFERENCES `cargo_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=281 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES (1,2,0,0,0,0),(2,2,0,0,0,0),(3,1,150,300,400,300),(4,3,50,300,400,200),(5,1,100,100,100,100),(6,3,0.5,20,8,20),(7,2,0,0,0,0),(8,2,0,0,0,0),(9,2,0,0,0,0),(10,2,0,0,0,0),(11,2,0,0,0,0),(12,2,0,0,0,0),(13,2,0,0,0,0),(14,2,0,0,0,0),(15,2,0,0,0,0),(16,2,0,0,0,0),(17,2,0,0,0,0),(18,2,0,0,0,0),(19,2,0,0,0,0),(20,2,0,0,0,0),(21,2,0,0,0,0),(22,2,0,0,0,0),(23,2,0,0,0,0),(24,2,0,0,0,0),(25,2,0,0,0,0),(26,2,0,0,0,0),(27,2,0,0,0,0),(28,2,0,0,0,0),(29,2,0,0,0,0),(30,3,150,300,400,0),(31,2,0,0,0,0),(32,2,0,0,0,0),(33,1,150,300,400,300),(34,3,150,300,400,300),(35,2,0,0,0,0),(36,2,0,0,0,0),(37,1,150,300,400,300),(38,3,0.5,25,10,20),(39,1,150,300,400,300),(40,2,0,0,0,0),(41,2,0,0,0,0),(42,2,0,0,0,0),(43,2,0,0,0,0),(44,2,0,0,0,0),(45,3,150,300,400,300),(46,2,0,0,0,0),(47,3,150,300,400,300),(48,2,0,0,0,0),(49,1,150,300,400,300),(50,3,0.5,20,10,20),(51,3,0.5,20,10,20),(52,2,0,0,0,0),(53,2,0,0,0,0),(54,3,0,0,0,0),(55,3,0.5,20,10,20),(56,3,50000000,70000000,70000000,70000000),(57,1,0,100,100,100),(58,1,0,0,0,0),(59,1,0,0,0,0),(60,2,0,0,0,0),(61,2,0,0,0,0),(62,3,0.5,20,10,20),(63,2,0,0,0,0),(64,2,0,0,0,0),(65,2,0,0,0,0),(66,2,0,0,0,0),(67,1,50,100,100,200),(68,2,0,0,0,0),(69,2,0,0,0,0),(70,2,0,0,0,0),(71,2,0,0,0,0),(72,3,0.5,20,10,20),(73,2,0,0,0,0),(74,2,0,0,0,0),(75,3,0.5,20,10,20),(76,2,0,0,0,0),(77,2,0,0,0,0),(78,2,0,0,0,0),(79,2,0,0,0,0),(80,2,0,0,0,0),(81,2,0,0,0,0),(82,2,0,0,0,0),(83,2,0,0,0,0),(84,2,0,0,0,0),(85,2,0,0,0,0),(86,3,8,13,79,10),(87,2,0,0,0,0),(88,2,0,0,0,0),(106,1,60,60,60,60),(107,1,60,60,60,60),(109,1,60,60,60,60),(110,1,60,60,60,60),(111,1,60,60,60,60),(119,1,60,60,60,60),(120,1,60,60,60,60),(136,3,0.5,20,10,20),(137,2,0,0,0,0),(147,1,60,60,60,60);
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargo_type`
--

DROP TABLE IF EXISTS `cargo_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargo_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo_type`
--

LOCK TABLES `cargo_type` WRITE;
/*!40000 ALTER TABLE `cargo_type` DISABLE KEYS */;
INSERT INTO `cargo_type` VALUES (1,'cargo'),(2,'document'),(3,'parcel');
/*!40000 ALTER TABLE `cargo_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `name_ua` varchar(50) DEFAULT NULL,
  `idRegion` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idRegion` (`idRegion`),
  CONSTRAINT `city_ibfk_1` FOREIGN KEY (`idRegion`) REFERENCES `region` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Kyiv','Київ',4),(2,'Warsaw','Варшава',4),(3,'Praga','Прага',4),(4,'Budapest','Будапешт',4),(5,'Lviv','Львів',4),(6,'Istanbul','Стамбул',3),(7,'Rome','Рим',3),(8,'Athens','Афіни',3),(9,'Zagreb','Загреб',3),(10,'Milan','Мілан',3),(11,'Paris','Париж',2),(12,'Madrid','Мадрид',2),(13,'Berlin','Берлін',2),(14,'Amsterdam','Амстердам',2),(15,'Brussels','Брюсель',2),(16,'London','Лондон',1),(17,'Oslo','Осло',1),(18,'Kopenhagen','Копенгаген',1),(19,'Stockholm','Стокгольм',1),(20,'Helsinki','Гельсінкі',1);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` VALUES (1,'Registered'),(2,'Waiting for payment'),(3,'Paid'),(4,'Declined'),(6,'Delivered');
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cargoId` int NOT NULL,
  `userId` int NOT NULL,
  `receiverNum` varchar(80) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `senderCityId` int NOT NULL,
  `receiverCityId` int NOT NULL,
  `orderStatusId` int NOT NULL,
  `dateOfRegister` datetime NOT NULL,
  `dateOfArrival` date DEFAULT NULL,
  `daysToDeliver` int DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cargoId` (`cargoId`),
  KEY `userId` (`userId`),
  KEY `orderStatusId` (`orderStatusId`),
  KEY `senderCityId` (`senderCityId`),
  KEY `receiverCityId` (`receiverCityId`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`cargoId`) REFERENCES `cargo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`orderStatusId`) REFERENCES `order_status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_ibfk_4` FOREIGN KEY (`senderCityId`) REFERENCES `city` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_ibfk_5` FOREIGN KEY (`receiverCityId`) REFERENCES `city` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=255 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,3,1,NULL,NULL,2,10,2,'2022-12-10 13:10:27',NULL,NULL,47),(2,4,1,NULL,NULL,1,20,3,'2022-12-10 13:13:27',NULL,NULL,20.5),(3,5,1,NULL,NULL,3,8,3,'2022-12-11 16:04:23',NULL,NULL,42),(4,6,1,NULL,NULL,5,1,3,'2022-12-11 17:30:08',NULL,NULL,12.05),(6,1,3,NULL,NULL,2,5,2,'2022-12-11 16:04:23',NULL,NULL,22.5),(8,2,1,NULL,NULL,1,2,3,'2022-12-11 16:04:23',NULL,NULL,45),(10,9,1,NULL,NULL,1,2,3,'2022-12-11 16:04:23',NULL,NULL,45),(11,10,1,NULL,NULL,1,2,6,'2022-12-11 16:04:23','2023-01-02',NULL,45),(12,16,1,NULL,NULL,1,2,3,'2022-12-11 16:04:23',NULL,NULL,45),(13,15,1,NULL,NULL,1,2,3,'2022-12-11 16:04:23',NULL,NULL,45),(15,14,1,NULL,NULL,1,2,3,'2022-12-11 16:04:23',NULL,NULL,45),(16,13,1,NULL,NULL,1,2,3,'2022-12-11 16:04:23',NULL,NULL,45),(17,12,1,NULL,NULL,1,2,3,'2022-12-11 16:04:23',NULL,NULL,45),(18,11,1,NULL,NULL,1,2,3,'2022-12-11 16:04:23',NULL,NULL,45),(20,25,1,NULL,NULL,1,2,3,'2022-12-11 16:04:23',NULL,NULL,45),(21,24,1,NULL,NULL,1,2,3,'2022-12-11 16:04:23',NULL,NULL,45),(22,23,1,NULL,NULL,1,2,3,'2022-12-11 16:04:23',NULL,NULL,45),(23,26,1,NULL,NULL,3,7,4,'2022-12-16 15:16:14',NULL,NULL,7.75),(24,27,1,NULL,NULL,2,6,4,'2022-12-16 15:50:11',NULL,NULL,7.75),(25,28,1,NULL,NULL,2,1,2,'2022-12-16 16:01:43',NULL,NULL,6),(26,29,1,NULL,NULL,2,8,6,'2022-12-17 12:44:38','2023-01-02',NULL,7.75),(27,30,1,NULL,NULL,5,10,3,'2022-12-17 12:46:05',NULL,NULL,30.5),(28,31,1,NULL,NULL,1,2,6,'2022-12-20 14:25:31','2023-01-02',NULL,6),(29,32,3,NULL,NULL,5,3,2,'2022-12-21 11:25:44',NULL,NULL,6),(30,33,1,NULL,NULL,2,10,3,'2022-12-21 11:39:14',NULL,NULL,47),(31,34,1,NULL,NULL,9,10,3,'2022-12-21 11:39:27',NULL,NULL,27),(32,35,3,NULL,NULL,2,9,2,'2022-12-21 11:43:46',NULL,NULL,7.75),(34,37,1,NULL,NULL,18,4,3,'2022-12-21 20:18:20',NULL,NULL,47),(36,39,1,NULL,NULL,2,8,3,'2022-12-22 16:48:34',NULL,NULL,47),(37,40,1,NULL,NULL,3,2,6,'2022-12-24 11:36:38','2022-12-24',NULL,6),(38,42,1,NULL,NULL,4,10,6,'2022-12-24 11:44:20','2022-12-28',NULL,7.75),(39,43,1,NULL,NULL,7,10,6,'2022-12-24 12:13:08','2022-12-24',NULL,6),(40,44,1,NULL,NULL,1,8,6,'2022-12-24 12:16:38','2022-12-26',NULL,7.75),(41,45,1,NULL,NULL,3,10,6,'2022-12-24 12:25:08','2022-12-28',4,30.5),(42,46,1,NULL,NULL,11,1,6,'2022-12-24 12:25:47','2022-12-29',5,9),(43,47,1,NULL,NULL,1,1,6,'2022-12-24 12:26:09','2022-12-26',2,25),(44,48,1,NULL,NULL,4,9,6,'2022-12-24 12:32:29','2022-12-28',4,7.75),(45,49,1,NULL,NULL,10,18,1,'2022-12-24 13:28:51',NULL,5,52),(46,50,1,NULL,NULL,2,8,4,'2022-12-24 14:13:19',NULL,4,15.55),(47,52,1,'Package identifier: book\nReceiver name: Vladyslav Levchuk',NULL,1,9,2,'2022-12-24 14:20:24',NULL,4,7.75),(48,53,1,'Package identifier: book\nReceiver name: Vladyslav Levchuk',NULL,1,6,6,'2022-12-24 14:23:48','2022-12-25',4,7.75),(50,55,1,'Package identifier: book\nReceiver name: Vladyslav Levchuk',NULL,10,17,6,'2022-12-25 15:49:11','2022-12-30',5,18.05),(51,56,1,'Package identifier: \nReceiver name: ',NULL,1,5,4,'2022-12-28 12:48:28',NULL,2,5104205.24),(52,57,1,'Package identifier: none\nReceiver name: ',NULL,1,3,4,'2022-12-28 13:03:08',NULL,2,7),(53,58,1,'Package identifier: none\nReceiver name: none',NULL,1,1,2,'2022-12-28 13:06:40',NULL,2,7),(54,59,1,'Package identifier: none\nReceiver name: none',NULL,1,1,4,'2022-12-28 13:08:00',NULL,2,7),(55,60,1,'Package identifier: book\nReceiver name: Vladyslav Levchuk',NULL,3,10,2,'2022-12-30 12:03:31',NULL,4,1.35),(56,61,1,'Package identifier: book\nReceiver name: Vladyslav Levchuk',NULL,3,8,2,'2022-12-30 13:05:45',NULL,4,1.6),(57,62,1,'Package identifier: book\nReceiver name: Vladyslav Levchuk',NULL,2,8,4,'2022-12-30 14:10:12',NULL,4,4.1),(58,63,1,'Package identifier: \nReceiver name: ',NULL,3,11,2,'2022-12-31 11:14:59',NULL,5,1.6),(59,64,1,'Package identifier: \nReceiver name: ',NULL,2,6,2,'2022-12-31 11:16:22',NULL,4,1.35),(60,65,1,'Package identifier: book\nReceiver name: Vladyslav Levchuk',NULL,3,8,4,'2023-01-02 19:46:50',NULL,4,1.35),(61,66,1,'Package identifier: book\nReceiver name: Vladyslav Levchuk',NULL,2,9,4,'2023-01-02 19:47:15',NULL,4,1.6),(62,67,1,'Package identifier: book\nReceiver name: Vladyslav Levchuk',NULL,2,10,4,'2023-01-02 19:48:38',NULL,4,14.5),(63,68,1,'Package identifier: book\nReceiver name: Vladyslav Levchuk',NULL,3,8,6,'2023-01-02 21:27:45','2023-01-07',4,1.6),(64,69,1,'Package identifier: book\nReceiver name: Vladyslav Levchuk',NULL,9,6,6,'2023-01-02 21:29:34','2023-01-05',2,1),(65,70,1,'Package identifier: book\nReceiver name: Vladyslav Levchuk',NULL,4,10,4,'2023-01-02 21:34:30',NULL,4,1.6),(66,71,1,'Package identifier: book\nReceiver name: Vladyslav Levchuk',NULL,5,8,6,'2023-01-02 21:34:58','2023-01-06',4,1.6),(67,72,1,'Package identifier: book\nReceiver name: Vladyslav Levchuk',NULL,5,5,6,'2023-01-03 20:55:29','2023-01-05',2,3.05),(68,73,1,'Package identifier: book\nReceiver name: Vladyslav Levchuk',NULL,2,7,2,'2023-01-03 21:55:29',NULL,4,1.35),(69,74,1,'Package identifier: book\nReceiver name: Vladyslav Levchuk',NULL,2,9,4,'2023-01-04 13:56:37',NULL,4,1.6),(70,75,1,'0976000853','книга',2,9,2,'2023-01-04 19:44:04',NULL,4,5.1),(73,78,1,'0976000853','книга',2,9,2,'2023-01-04 22:40:08',NULL,4,1.6),(74,79,1,'0976000853','&#1082;&#1085;&#1080;&#1075;&#1072;',4,9,4,'2023-01-04 22:41:07',NULL,4,1.35),(75,80,1,'0976000853','книга',2,6,4,'2023-01-04 23:05:40',NULL,4,1.6),(76,81,1,'0976000853','книга',1,10,4,'2023-01-04 23:05:58',NULL,4,1.6),(77,82,1,'0976000853','книга',2,8,4,'2023-01-04 23:17:56',NULL,4,1.35),(78,83,1,'0976000853','книга',2,4,6,'2023-01-04 23:20:54','2023-01-07',2,1.25),(79,84,1,'0976666853','книга',6,2,4,'2023-01-05 00:38:16',NULL,4,1.35),(80,85,1,'1111000853','книга',1,9,6,'2023-01-05 00:42:00','2023-01-09',4,1.35),(81,86,29,'0976000853','bob',1,2,6,'2023-01-05 01:09:26','2023-01-07',2,4.81),(82,87,29,'0976000853','книга',2,7,6,'2023-01-05 01:17:16','2023-01-09',4,1.35),(83,88,1,'0976000853','книга',6,9,2,'2023-01-05 03:09:54',NULL,2,1.25),(125,137,1,'0976000853','документ',2,7,6,'2023-01-10 19:10:18','2023-01-10',4,1.35);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `region` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tag` varchar(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (1,'N'),(2,'W'),(3,'S'),(4,'E');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Authorized User'),(2,'Manager');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `lastname` varchar(200) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(250) NOT NULL,
  `roleId` int NOT NULL,
  `telNumber` varchar(20) NOT NULL,
  `balance` float DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `telNumber_UNIQUE` (`telNumber`),
  KEY `userFK_idx` (`roleId`),
  CONSTRAINT `userFK` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=351 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Vlad','Левчук','v@gmail.com','$2a$10$abw3BOfSbv.OvHYpaoVn3ezy83SzAAMy03MtQHMEN6QXGQatPNwya',1,'0976000853',50),(3,'Vladyslav','Levchuk','manager@gmail.com','$2a$10$PScTkdJ0XMuCJGFdf381z.UPqTGnDN0VlgM.16WZj6FYhd6BWl9Ma',2,'976000853',100000),(20,'Vladyslav','Levchuk','denni@gmail.com','$2a$10$OfRrw6A2UTBcf6BBp6Jzqu4VDrrTm.A8Qq6olbbNCT/D0slGEJ8lq',1,'0976222853',0),(24,'Ігор','Пип','r@gmailc.om','$2a$10$aRx0r.2QznoeWI112GfW/e8vIYJJuY6g5KreSAeBF/09xDHK8wiBG',1,'0976077753',0),(26,'Степан','Віновський','s@gmail.com','$2a$10$LC9F7tKA0Xmsm51goAWqJuICeGAWsai7LTK1gUqKknd0CADeRmGgG',1,'0976021853',0),(27,'Кирило','Мефодій','w@gmail.com','$2a$10$eQNsi4TN2Jm78wj3ZL6TbeaZ96ERvS35Wa60Am5yXlmd0.y43HyZa',1,'8886000853',0),(28,'Назар','Стефаник','n@gmail.com','$2a$10$G4FGuVEMizuEPJt/NPRaeePasRoP2d4ColzD2aJiXzvGBFKf5baGO',1,'1111000853',0),(29,'Olexander','Stepanenko','sasha28jedi@lpml.com.ua','$2a$10$DPHvqZhnDxS77yhhBasX8uE8J/lKcJ1TGQ5SJL49Jf69W28oo8D22',1,'0976000852',0),(350,'vlad','levchuk','vv@gmail.com','$2a$10$GOOcIIE.17bEFAdaVJeJ6.hlkirwVmtlKpPIY.qDnLzJC9cvWPPqm',1,'0931235678',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-15  1:49:47
