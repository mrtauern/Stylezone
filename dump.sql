-- MySQL dump 10.13  Distrib 5.5.38, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: stylezone
-- ------------------------------------------------------
-- Server version	5.5.38

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
-- Table structure for table `Booking`
--

DROP TABLE IF EXISTS `Booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Booking` (
  `bookingId` int(8) NOT NULL AUTO_INCREMENT,
  `bookingTime` time NOT NULL,
  `bookingDate` date NOT NULL,
  `bookingName` varchar(150) NOT NULL,
  `bookingPhone` int(8) NOT NULL,
  `bookingComment` longtext NOT NULL,
  `fk_staffId` int(4) NOT NULL,
  PRIMARY KEY (`bookingId`),
  KEY `fk_staffId` (`fk_staffId`),
  CONSTRAINT `fk_staffId` FOREIGN KEY (`fk_staffId`) REFERENCES `staff` (`staffId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Booking`
--

LOCK TABLES `Booking` WRITE;
/*!40000 ALTER TABLE `Booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `Booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Holiday`
--

DROP TABLE IF EXISTS `Holiday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Holiday` (
  `holidayId` int(4) NOT NULL AUTO_INCREMENT,
  `HolidayDate` date NOT NULL,
  `holidayName` varchar(45) NOT NULL,
  PRIMARY KEY (`holidayId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Holiday`
--

LOCK TABLES `Holiday` WRITE;
/*!40000 ALTER TABLE `Holiday` DISABLE KEYS */;
/*!40000 ALTER TABLE `Holiday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Opening`
--

DROP TABLE IF EXISTS `Opening`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Opening` (
  `openingId` int(1) NOT NULL AUTO_INCREMENT,
  `openingDay` varchar(10) NOT NULL,
  `openingTime` time NOT NULL,
  `openingClose` time NOT NULL,
  PRIMARY KEY (`openingId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Opening`
--

LOCK TABLES `Opening` WRITE;
/*!40000 ALTER TABLE `Opening` DISABLE KEYS */;
/*!40000 ALTER TABLE `Opening` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff`
--

DROP TABLE IF EXISTS `Staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Staff` (
  `staffId` int(2) NOT NULL AUTO_INCREMENT,
  `staffName` varchar(150) NOT NULL,
  PRIMARY KEY (`staffId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff`
--

LOCK TABLES `Staff` WRITE;
/*!40000 ALTER TABLE `Staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `Staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-22 12:39:32
