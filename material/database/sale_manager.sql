-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: localhost    Database: sale_manager
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id_cart` int NOT NULL AUTO_INCREMENT,
  `id_customer` int DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `id_discount` int DEFAULT NULL,
  `bill_code` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `minus_acc_point` int DEFAULT NULL,
  PRIMARY KEY (`id_cart`),
  KEY `fk_cart_1_idx` (`id_customer`),
  KEY `fk_cart_discount_idx` (`id_discount`),
  CONSTRAINT `fk_cart_customer` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id_customer`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cart_discount` FOREIGN KEY (`id_discount`) REFERENCES `discount` (`id_discount`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (5,1,'2022-07-11 00:00:00',NULL,'58fc49a7-72a5-4a11-90d2-f049970b7eb5',2),(6,2,'2022-07-11 00:00:00',2,'5c2f4efc-4b6d-49b4-b74a-04379bdf2b70',0),(7,51,'2022-07-11 00:00:00',1,'d7a300a1-16f2-49e4-ba28-e03204bcad11',0),(8,55,'2022-07-11 00:00:00',7,'b25bed4a-11d9-4080-9c91-5dafd332d31c',0),(9,53,'2022-07-11 00:00:00',1,'3c228a98-c583-4403-a3b4-3e7e9cf70f54',0);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_detail`
--

DROP TABLE IF EXISTS `cart_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_detail` (
  `id_product` int NOT NULL,
  `id_cart` int NOT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id_product`,`id_cart`),
  KEY `fk_cart_detail_2_idx` (`id_cart`),
  CONSTRAINT `fk_detail_cart` FOREIGN KEY (`id_cart`) REFERENCES `cart` (`id_cart`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_detail_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_detail`
--

LOCK TABLES `cart_detail` WRITE;
/*!40000 ALTER TABLE `cart_detail` DISABLE KEYS */;
INSERT INTO `cart_detail` VALUES (6,6,1),(7,7,2),(7,8,2),(8,5,1),(8,9,2),(9,5,1),(9,8,2),(11,5,1),(11,8,1),(11,9,1),(12,5,2),(13,6,1),(14,6,1),(14,9,1);
/*!40000 ALTER TABLE `cart_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id_category` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `url_image` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Food','No','/home/nguyennam/Documents/JavaProject/image/category/maxresdefault.jpg'),(2,'Drink','No','/home/nguyennam/Documents/JavaProject/image/category/maxresdefault (1).jpg'),(3,'Category New','No Description - No Comment','/home/nguyennam/Documents/JavaProject/image/category/fast_food.jpg'),(6,'Vegetable Demo','Vegetable Demo - Description Haha','/home/nguyennam/Documents/JavaProject/image/category/vegetable.jpg');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id_customer` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `phone_number` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `accumulated_point` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id_customer`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Nguyen Van Nam','0976335568','nam.nv182698@gmail.com','1'),(2,'Dang Gia Long Khanh','0967291936','khanh27112k.bn@gmail.com','1'),(3,'Tran Ngoc Tien','0353140116','tien.th6@gmail.com','0'),(4,'Vu Thi Thanh','0965579533','thanh.vt11@gmail.com','0'),(50,'Nguyen Viet Bao','0320806968','bao520_bn@gmail.com','0'),(51,'Nguyen Van Duc','0356237747','duc035.ng@gmail.com','1'),(52,'Nguyen Van Dat','0967566241','dat096.nv@gmail.com','0'),(53,'Nguyen Thi Mai','0826593222','maittbn.99@gmail.com','1'),(54,'Nguyen Thi Cuc','0356665857','cuc_hp16@gmail.com','0'),(55,'Nguyen Manh Dung','0985666233','dunghp_1122@gmail.com','1'),(56,'Vu Duc Thinh','0967394775','thinhnd.vd8@gmail.com','0'),(57,'Vu Duc Kien','0966455686','kienvu_26@gmail.com','0'),(58,'Duong Cong Anh','0976505269','conganhduong.16@gmail.com','0'),(59,'Nguyen Tuan Anh','0321215665','tuananh.nb89@gmail.com','0'),(60,'Nguyen Ba Truong','0356886353','truongba.na56@gmail.com','0'),(61,'Nguyen Quang Vinh','0976232556','nguyenquangvinh_34@gmail.com','0'),(62,'Nguyen Minh Quang','0968242887','quangminh_kh19@gmail.com','0'),(63,'Nguyen Thu Hue','0358322767','huetth_30@gmail.com','0'),(64,'Nguyen Diep Anh','0967286566','dienanhnguyen_156@gmail.com','0'),(65,'Tran Van Khai','0989646235','khaioggy20_tt1@gmail.com','0'),(74,'Test 1','0976000','abcd','0');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `id_discount` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `percent_discount` double DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  PRIMARY KEY (`id_discount`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,'Chuong trinh su kien 1','No description',10,'2022-07-11 00:00:00','2022-08-11 00:00:00'),(2,'Su kien 02','No description',20,'2022-06-01 00:00:00','2022-08-01 00:00:00'),(3,'Event 03','No description',12,'2022-07-01 00:00:00','2022-08-11 00:00:00'),(7,'Demo Discount 05','No Description For Demo Discount 04\nHAHAHAHA',50,'2022-07-05 00:00:00','2022-07-25 00:00:00');
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id_product` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `url_image` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `id_category` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id_product`),
  KEY `fk_product_1_idx` (`id_category`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (5,'My Cay Seoul - To Hieu',62000,'/home/nguyennam/Documents/JavaProject/image/product/file_restaurant_photo.jpg',1,50),(6,'Com Tho Xa Xiu',55000,'/home/nguyennam/Documents/JavaProject/image/product/635c3ce5.jpeg',1,49),(7,'Com Tho Ga',50000,'/home/nguyennam/Documents/JavaProject/image/product/2222.jpeg',1,46),(8,'Com Tho Bo',55000,'/home/nguyennam/Documents/JavaProject/image/product/3333.jpeg',1,44),(9,'My Cay Hai San',68000,'/home/nguyennam/Documents/JavaProject/image/product/4444.jpeg',1,47),(10,'Cocacola Lon',15000,'/home/nguyennam/Documents/JavaProject/image/product/5555.jpeg',2,50),(11,'Cam Ep Splash',15000,'/home/nguyennam/Documents/JavaProject/image/product/6666.jpeg',2,47),(12,'Nuoc Suoi Dasani',10000,'/home/nguyennam/Documents/JavaProject/image/product/7777.jpeg',2,48),(13,'Bo Huc',20000,'/home/nguyennam/Documents/JavaProject/image/product/8888.jpeg',2,49),(14,'Beer Ha Noi',15000,'/home/nguyennam/Documents/JavaProject/image/product/beer_ha_noi.jpeg',2,48),(22,'San pham moi 123',123000,'/home/nguyennam/Documents/JavaProject/image/product/file_restaurant_photo.jpg',6,0),(23,'Demo San Pham 2',12343,'/home/nguyennam/Documents/JavaProject/image/product/hariken.png',6,120);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-11 17:30:43
