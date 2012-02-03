-- MySQL dump 10.13  Distrib 5.5.16, for osx10.6 (i386)
--
-- Host: localhost    Database: uengine
-- ------------------------------------------------------
-- Server version	5.5.16-log

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
-- Table structure for table `APPKEYS`
--

DROP TABLE IF EXISTS `APPKEYS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `APPKEYS` (
  `APPKEY` varchar(36) NOT NULL,
  `CREATIONTIME` datetime DEFAULT NULL,
  `LASTACCESSEDTIME` datetime DEFAULT NULL,
  PRIMARY KEY (`APPKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `APPKEYS`
--

LOCK TABLES `APPKEYS` WRITE;
/*!40000 ALTER TABLE `APPKEYS` DISABLE KEYS */;
INSERT INTO `APPKEYS` VALUES ('0d50da56-0d16-48d9-b1c7-cdc1b290846b','2011-09-26 00:21:34','2011-09-26 00:52:57'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','2011-09-26 21:10:42','2011-09-26 22:57:24'),('1b8f1070-138b-4205-8db2-1da52da22f77','2011-09-26 01:28:56','2011-09-26 01:28:56'),('3dda0808-8b37-4277-b27f-7e2421da607f','2011-10-19 11:11:21','2011-10-19 11:11:29'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','2011-10-15 16:59:40','2011-10-18 01:00:18'),('6294255c-5579-43d5-bdac-f121c586684c','2011-09-25 02:20:49','2011-09-26 02:26:36'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','2011-10-14 20:09:41','2011-10-15 00:33:19'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','2011-11-12 22:11:15','2011-11-12 22:14:53'),('85d117ed-3900-4763-922d-febcf53fb7bd','2011-09-26 01:10:12','2011-09-26 01:10:12'),('907d94eb-cba7-4677-b309-71391a55853b','2011-09-25 23:52:20','2011-09-25 23:52:29'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','2011-10-15 01:18:40','2011-10-15 14:52:47'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','2011-09-25 23:56:00','2011-09-25 23:57:30'),('aaf285fb-e23f-4639-96bc-16ba9936884c','2011-10-17 02:34:20','2011-10-17 02:34:23'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','2011-10-18 14:38:47','2011-10-18 14:38:54'),('cfaf7fa0-bf01-4049-8330-ebe6b4bb86ee','2011-09-26 00:21:34','2011-09-26 00:21:34'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','2011-09-26 00:56:00','2011-09-26 20:45:30');
/*!40000 ALTER TABLE `APPKEYS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `APPKEY_ATTRIBUTES`
--

DROP TABLE IF EXISTS `APPKEY_ATTRIBUTES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `APPKEY_ATTRIBUTES` (
  `APPKEY` varchar(36) NOT NULL,
  `ATTRIBUTEKEY` varchar(30) NOT NULL,
  `ATTRIBUTEVALUE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`APPKEY`,`ATTRIBUTEKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `APPKEY_ATTRIBUTES`
--

LOCK TABLES `APPKEY_ATTRIBUTES` WRITE;
/*!40000 ALTER TABLE `APPKEY_ATTRIBUTES` DISABLE KEYS */;
INSERT INTO `APPKEY_ATTRIBUTES` VALUES ('0d50da56-0d16-48d9-b1c7-cdc1b290846b','instanceCount','35'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','instanceFrom','0'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserComName','uEngine Solutions'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserEmail',NULL),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserFacebookId',NULL),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserFullName','Tester'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserGlobalCom','uEngine'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserId','test'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserIsAdmin','1'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserJikName','Tester'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserLocale','en'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserMsnId',NULL),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserNateonId',NULL),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserPartCode',''),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserPartName',NULL),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserPw','test'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','mailCount','20'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','mailFrom','0'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','uxStyle','waveStyle'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','instanceCount','35'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','instanceFrom','11'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserComName','uEngine Solutions'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserEmail',NULL),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserFacebookId',NULL),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserFullName','Tester'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserGlobalCom','uEngine'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserId','test'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserIsAdmin','1'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserJikName','Tester'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserLocale','en'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserMsnId',NULL),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserNateonId',NULL),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserPartCode',''),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserPartName',NULL),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserPw','test'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','mailCount','20'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','mailFrom','0'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','uxStyle','waveStyle'),('3dda0808-8b37-4277-b27f-7e2421da607f','instanceCount','35'),('3dda0808-8b37-4277-b27f-7e2421da607f','instanceFrom','17'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserComName','uEngine Solutions'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserEmail',NULL),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserFacebookId','100002969720934'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserFullName','Tester'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserGlobalCom','uEngine'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserId','test'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserIsAdmin','1'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserJikName','Tester'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserLocale','en'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserMsnId',NULL),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserNateonId',NULL),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserPartCode',''),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserPartName',NULL),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserPw','test'),('3dda0808-8b37-4277-b27f-7e2421da607f','mailCount','20'),('3dda0808-8b37-4277-b27f-7e2421da607f','mailFrom','0'),('3dda0808-8b37-4277-b27f-7e2421da607f','uxStyle','waveStyle'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','instanceCount','35'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','instanceFrom','11'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserComName','uEngine Solutions'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserEmail',NULL),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserFacebookId','100002969720934'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserFullName','Tester'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserGlobalCom','uEngine'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserId','test'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserIsAdmin','1'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserJikName','Tester'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserLocale','en'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserMsnId',NULL),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserNateonId',NULL),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserPartCode',''),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserPartName',NULL),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserPw','test'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','mailCount','20'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','mailFrom','0'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','uxStyle','waveStyle'),('6294255c-5579-43d5-bdac-f121c586684c','instanceCount','35'),('6294255c-5579-43d5-bdac-f121c586684c','instanceFrom','11'),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserComName','uEngine Solutions'),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserEmail',NULL),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserFacebookId',NULL),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserFullName','Tester'),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserGlobalCom','uEngine'),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserId','test'),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserIsAdmin','1'),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserJikName','Tester'),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserLocale','en'),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserMsnId',NULL),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserNateonId',NULL),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserPartCode',''),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserPartName',NULL),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserPw','test'),('6294255c-5579-43d5-bdac-f121c586684c','mailCount','20'),('6294255c-5579-43d5-bdac-f121c586684c','mailFrom','0'),('6294255c-5579-43d5-bdac-f121c586684c','uxStyle','waveStyle'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','instanceCount','35'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','instanceFrom','11'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserComName','uEngine Solutions'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserEmail',NULL),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserFacebookId','100002969720934'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserFullName','Tester'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserGlobalCom','uEngine'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserId','test'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserIsAdmin','1'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserJikName','Tester'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserLocale','en'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserMsnId',NULL),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserNateonId',NULL),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserPartCode',''),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserPartName',NULL),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserPw','test'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','mailCount','20'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','mailFrom','0'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','uxStyle','waveStyle'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','instanceCount','35'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','instanceFrom','4'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserComName','uEngine Solutions'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserEmail',NULL),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserFacebookId','100002969720934'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserFullName','Tester'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserGlobalCom','uEngine'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserId','test'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserIsAdmin','1'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserJikName','Tester'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserLocale','en'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserMsnId',NULL),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserNateonId',NULL),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserPartCode',''),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserPartName',NULL),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserPw','test'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','mailCount','20'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','mailFrom','0'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','uxStyle','waveStyle'),('907d94eb-cba7-4677-b309-71391a55853b','instanceCount','35'),('907d94eb-cba7-4677-b309-71391a55853b','instanceFrom','24'),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserComName','uEngine Solutions'),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserEmail',NULL),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserFacebookId',NULL),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserFullName','Tester'),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserGlobalCom','uEngine'),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserId','test'),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserIsAdmin','1'),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserJikName','Tester'),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserLocale','en'),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserMsnId',NULL),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserNateonId',NULL),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserPartCode',''),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserPartName',NULL),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserPw','test'),('907d94eb-cba7-4677-b309-71391a55853b','mailCount','20'),('907d94eb-cba7-4677-b309-71391a55853b','mailFrom','0'),('907d94eb-cba7-4677-b309-71391a55853b','uxStyle','waveStyle'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','instanceCount','35'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','instanceFrom','11'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserComName','uEngine Solutions'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserEmail',NULL),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserFacebookId','100002969720934'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserFullName','Tester'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserGlobalCom','uEngine'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserId','test'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserIsAdmin','1'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserJikName','Tester'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserLocale','en'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserMsnId',NULL),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserNateonId',NULL),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserPartCode',''),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserPartName',NULL),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserPw','test'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','mailCount','20'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','mailFrom','0'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','uxStyle','waveStyle'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','instanceCount','35'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','instanceFrom','0'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserComName','uEngine Solutions'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserEmail',NULL),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserFacebookId',NULL),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserFullName','Tester'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserGlobalCom','uEngine'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserId','test'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserIsAdmin','1'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserJikName','Tester'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserLocale','en'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserMsnId',NULL),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserNateonId',NULL),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserPartCode',''),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserPartName',NULL),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserPw','test'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','mailCount','20'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','mailFrom','0'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','uxStyle','waveStyle'),('aaf285fb-e23f-4639-96bc-16ba9936884c','instanceCount','35'),('aaf285fb-e23f-4639-96bc-16ba9936884c','instanceFrom','11'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserComName','uEngine Solutions'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserEmail',NULL),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserFacebookId','100002969720934'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserFullName','Tester'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserGlobalCom','uEngine'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserId','test'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserIsAdmin','1'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserJikName','Tester'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserLocale','en'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserMsnId',NULL),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserNateonId',NULL),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserPartCode',''),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserPartName',NULL),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserPw','test'),('aaf285fb-e23f-4639-96bc-16ba9936884c','mailCount','20'),('aaf285fb-e23f-4639-96bc-16ba9936884c','mailFrom','0'),('aaf285fb-e23f-4639-96bc-16ba9936884c','uxStyle','waveStyle'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','instanceCount','35'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','instanceFrom','11'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserComName','uEngine Solutions'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserEmail',NULL),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserFacebookId','100002969720934'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserFullName','Tester'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserGlobalCom','uEngine'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserId','test'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserIsAdmin','1'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserJikName','Tester'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserLocale','en'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserMsnId',NULL),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserNateonId',NULL),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserPartCode',''),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserPartName',NULL),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserPw','test'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','mailCount','20'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','mailFrom','0'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','uxStyle','waveStyle'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','instanceCount','35'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','instanceFrom','11'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserComName','uEngine Solutions'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserEmail',NULL),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserFacebookId',NULL),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserFullName','Tester'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserGlobalCom','uEngine'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserId','test'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserIsAdmin','1'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserJikName','Tester'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserLocale','en'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserMsnId',NULL),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserNateonId',NULL),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserPartCode',''),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserPartName',NULL),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserPw','test'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','mailCount','20'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','mailFrom','0'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','uxStyle','waveStyle');
/*!40000 ALTER TABLE `APPKEY_ATTRIBUTES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BIZRATE`
--

DROP TABLE IF EXISTS `BIZRATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BIZRATE` (
  `EMPCODE` varchar(20) NOT NULL DEFAULT '',
  `YEARMM` varchar(6) NOT NULL DEFAULT '',
  `BIZDAY` int(11) DEFAULT NULL,
  `WORKEDDAY` int(11) DEFAULT NULL,
  `LATEDDAY` int(11) DEFAULT NULL,
  PRIMARY KEY (`EMPCODE`,`YEARMM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BIZRATE`
--

LOCK TABLES `BIZRATE` WRITE;
/*!40000 ALTER TABLE `BIZRATE` DISABLE KEYS */;
/*!40000 ALTER TABLE `BIZRATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BPM_ACLTABLE`
--

DROP TABLE IF EXISTS `BPM_ACLTABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BPM_ACLTABLE` (
  `ACLTABLEID` int(11) NOT NULL DEFAULT '0',
  `DEFID` int(11) DEFAULT NULL,
  `COMCODE` varchar(20) DEFAULT NULL,
  `PARTCODE` varchar(20) DEFAULT NULL,
  `EMPCODE` varchar(20) DEFAULT NULL,
  `ROLECODE` varchar(20) DEFAULT NULL,
  `DEFAULTUSER` char(1) DEFAULT NULL,
  `PERMISSION` char(1) DEFAULT NULL,
  PRIMARY KEY (`ACLTABLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BPM_ACLTABLE`
--

LOCK TABLES `BPM_ACLTABLE` WRITE;
/*!40000 ALTER TABLE `BPM_ACLTABLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `BPM_ACLTABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BPM_DEADLN_DIM`
--

DROP TABLE IF EXISTS `BPM_DEADLN_DIM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BPM_DEADLN_DIM` (
  `DEADLN_ID` int(11) NOT NULL,
  `DEADLN_DESC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DEADLN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BPM_DEADLN_DIM`
--

LOCK TABLES `BPM_DEADLN_DIM` WRITE;
/*!40000 ALTER TABLE `BPM_DEADLN_DIM` DISABLE KEYS */;
/*!40000 ALTER TABLE `BPM_DEADLN_DIM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BPM_PRFM_FACT_2006`
--

DROP TABLE IF EXISTS `BPM_PRFM_FACT_2006`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BPM_PRFM_FACT_2006` (
  `INST_ID` int(11) DEFAULT NULL,
  `ROOTINST_ID` int(11) DEFAULT NULL,
  `RSRC_ID` varchar(50) DEFAULT NULL,
  `TIME_ID` int(11) DEFAULT NULL,
  `ACT_ID` varchar(100) DEFAULT NULL,
  `ACT_NAME` varchar(100) DEFAULT NULL,
  `DEF_ID` int(11) DEFAULT NULL,
  `DEF_NAME` varchar(200) DEFAULT NULL,
  `PRSNGTIME` int(11) DEFAULT NULL,
  `TRNRNDTIME` int(11) DEFAULT NULL,
  `IDLETIME` int(11) DEFAULT NULL,
  `CHOVRTIME` int(11) DEFAULT NULL,
  `SSPNDTIME` int(11) DEFAULT NULL,
  `COST` int(11) DEFAULT NULL,
  `SLA_STFTRY` int(11) DEFAULT NULL,
  `MODTIME` date DEFAULT NULL,
  `DEADLNHT` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BPM_PRFM_FACT_2006`
--

LOCK TABLES `BPM_PRFM_FACT_2006` WRITE;
/*!40000 ALTER TABLE `BPM_PRFM_FACT_2006` DISABLE KEYS */;
INSERT INTO `BPM_PRFM_FACT_2006` VALUES (1,1,'test',20110925,'1','Ping',3,'Ping',0,NULL,NULL,NULL,NULL,0,NULL,'2011-09-25',0),(2,1,'test',20110925,'1','Reply',2,'Reply',0,NULL,NULL,NULL,NULL,0,NULL,'2011-09-25',0),(3,3,'test',20110925,'1','Ping',3,'Ping',0,NULL,NULL,NULL,NULL,0,NULL,'2011-09-25',0),(5,5,'test',20110925,'1','업무요청사항등록',17,'업무요청',0,NULL,NULL,NULL,NULL,0,NULL,'2011-09-25',0),(6,6,'test',20110925,'1','Ping',3,'Ping',0,NULL,NULL,NULL,NULL,0,NULL,'2011-09-25',0),(8,8,'test',20110925,'1','Ping',3,'Ping',0,NULL,NULL,NULL,NULL,0,NULL,'2011-09-25',0),(9,9,'test',20110925,'1','Ping',3,'Ping',0,NULL,NULL,NULL,NULL,0,NULL,'2011-09-25',0),(10,10,'test',20110925,'1','Ping',3,'Ping',0,NULL,NULL,NULL,NULL,0,NULL,'2011-09-25',0),(11,11,'test',20110925,'1','Ping',3,'Ping',0,NULL,NULL,NULL,NULL,0,NULL,'2011-09-25',0),(12,12,'test',20110925,'1','Ping',3,'Ping',0,NULL,NULL,NULL,NULL,0,NULL,'2011-09-25',0),(13,13,'test',20110925,'1','Ping',3,'Ping',0,NULL,NULL,NULL,NULL,0,NULL,'2011-09-25',0),(14,14,'test',20110925,'1','Ping',3,'Ping',0,NULL,NULL,NULL,NULL,0,NULL,'2011-09-25',0),(15,15,'test',20110925,'1','Ping',3,'Ping',0,NULL,NULL,NULL,NULL,0,NULL,'2011-09-25',0),(22,22,'test',20110926,'1','이슈 등록',27,'이슈처리',0,NULL,NULL,NULL,NULL,0,NULL,'2011-09-26',0),(24,24,'test',20110926,'1','이슈 등록',27,'이슈처리',0,NULL,NULL,NULL,NULL,0,NULL,'2011-09-26',0),(26,26,'test',20110926,'1','이슈 등록',27,'이슈처리',0,NULL,NULL,NULL,NULL,0,NULL,'2011-09-26',0),(28,28,'test',20111015,'1','이슈 등록',27,'이슈처리',0,NULL,NULL,NULL,NULL,0,NULL,'2011-10-15',0),(68,68,'test',20111112,'1','Ping',3,'Ping',0,NULL,NULL,NULL,NULL,0,NULL,'2011-11-12',0),(93,93,'1401720840',20111229,'1','form',41,'Noname',535,NULL,NULL,NULL,NULL,0,NULL,'2011-12-29',0),(93,93,'1401720840',20111229,'1','form',41,'Noname',539,NULL,NULL,NULL,NULL,0,NULL,'2011-12-29',0),(94,94,'1401720840',20111229,'1','form',41,'Noname',0,NULL,NULL,NULL,NULL,0,NULL,'2011-12-29',0),(95,95,'1401720840',20111229,'1','form',41,'Noname',0,NULL,NULL,NULL,NULL,0,NULL,'2011-12-29',0),(96,96,'1401720840',20111229,'1','form',41,'Noname',0,NULL,NULL,NULL,NULL,0,NULL,'2011-12-29',0),(97,97,'1401720840',20111229,'1','form',41,'Noname',0,NULL,NULL,NULL,NULL,0,NULL,'2011-12-29',0),(98,98,'1401720840',20111229,'1','form',41,'Noname',0,NULL,NULL,NULL,NULL,0,NULL,'2011-12-29',0),(100,100,'1401720840',20111229,'1','form',41,'Noname',0,NULL,NULL,NULL,NULL,0,NULL,'2011-12-29',0),(101,101,'1401720840',20111229,'1','form',41,'Noname',0,NULL,NULL,NULL,NULL,0,NULL,'2011-12-29',0),(102,102,'1401720840',20111229,'1','form',41,'Noname',2,NULL,NULL,NULL,NULL,0,NULL,'2011-12-29',0),(103,103,'1401720840',20111229,'1','form',41,'Noname',1,NULL,NULL,NULL,NULL,0,NULL,'2011-12-29',0),(112,112,'1401720840',20120127,'1','Human work',77,'metaworksObjTest',163,NULL,NULL,NULL,NULL,0,NULL,'2012-01-28',0),(113,113,'1401720840',20120128,'1','Human work',77,'metaworksObjTest',0,NULL,NULL,NULL,NULL,0,NULL,'2012-01-28',0),(115,115,'1401720840',20120130,'1','Class 개발 요청',84,'클래스개발 프로세스',3,NULL,NULL,NULL,NULL,0,NULL,'2012-01-30',0),(116,116,'1401720840',20120130,'1','Class 개발 요청',84,'클래스개발 프로세스',67,NULL,NULL,NULL,NULL,0,NULL,'2012-01-30',0),(121,121,'1401720840',20120130,'1','클래스 개발 요청',87,'Request Class ',39,NULL,NULL,NULL,NULL,0,NULL,'2012-01-30',0),(123,123,'1401720840',20120131,'1','클래스 개발 요청',87,'Request Class ',1,NULL,NULL,NULL,NULL,0,NULL,'2012-01-31',0);
/*!40000 ALTER TABLE `BPM_PRFM_FACT_2006` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BPM_PROCDEF`
--

DROP TABLE IF EXISTS `BPM_PROCDEF`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BPM_PROCDEF` (
  `DEFID` int(11) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `PARENTFOLDER` int(11) DEFAULT NULL,
  `ISFOLDER` int(11) DEFAULT '0',
  `ISADHOC` int(11) DEFAULT '0',
  `OBJTYPE` varchar(10) DEFAULT NULL,
  `PRODVER` int(11) DEFAULT '0',
  `PRODVERID` int(11) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `ISDELETED` int(11) DEFAULT '0',
  `MODDATE` datetime DEFAULT NULL,
  `ALIAS` varchar(100) DEFAULT NULL,
  `COMCODE` varchar(20) DEFAULT NULL,
  `ISVISIBLE` int(11) DEFAULT NULL,
  PRIMARY KEY (`DEFID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BPM_PROCDEF`
--

LOCK TABLES `BPM_PROCDEF` WRITE;
/*!40000 ALTER TABLE `BPM_PROCDEF` DISABLE KEYS */;
INSERT INTO `BPM_PROCDEF` VALUES (1,NULL,-1,1,0,'folder',0,NULL,'System Definitions',0,NULL,NULL,'uEngine',NULL),(2,NULL,1,0,0,'process',1,1,'Reply',0,NULL,'replyProcess','uEngine',NULL),(3,NULL,1,0,0,'process',1,2,'Ping',0,NULL,'pingProcess','uEngine',NULL),(4,NULL,1,0,0,'process',1,3,'masterProcess',0,NULL,'masterProcess','uEngine',NULL),(5,NULL,1,0,0,'process',1,4,'mailAttachProcess',0,NULL,'mailAttachProcess','uEngine',NULL),(6,NULL,1,0,0,'process',1,5,'InstancePreview',0,NULL,'InstancePreview','uEngine',NULL),(7,NULL,1,0,0,'process',1,6,'페북에 공유하기',0,NULL,'','uEngine',NULL),(8,NULL,-1,1,0,'folder',0,NULL,'업무',0,NULL,NULL,'uEngine',NULL),(9,NULL,8,1,0,'folder',0,NULL,'01. 업무요청',0,NULL,NULL,'uEngine',NULL),(10,NULL,8,1,0,'folder',0,NULL,'02. 이슈처리',0,NULL,NULL,'uEngine',NULL),(11,NULL,10,1,0,'folder',0,NULL,'_Form',0,NULL,NULL,'uEngine',NULL),(12,NULL,9,0,0,'form',1,7,'폼_이슈등록',0,NULL,'frmisuset','uEngine',NULL),(13,NULL,9,0,0,'form',1,8,'폼_이슈처리여부판단',0,NULL,'frmisucntyn','uEngine',NULL),(14,NULL,9,0,0,'form',1,9,'폼_이슈처리결과입력',0,NULL,'frmisurstset','uEngine',NULL),(15,NULL,9,0,0,'form',1,10,'폼_이슈처리결과확인',0,NULL,'frmisurstrev','uEngine',NULL),(16,NULL,9,0,0,'form',1,11,'폼_이슈처리불가알림',0,NULL,'frmisucnlarm','uEngine',NULL),(17,NULL,9,0,0,'process',1,12,'업무요청',0,NULL,'praisutrk','uEngine',NULL),(18,NULL,11,0,0,'form',1,13,'5whyform',0,NULL,'fivewhyform_t','uEngine',NULL),(19,NULL,11,0,0,'form',1,14,'결과보고 확인',0,NULL,'ResultReportCheckForm_f_t','uEngine',NULL),(20,NULL,11,0,0,'form',1,15,'이슈처리지연알림',0,NULL,'timeout_t','uEngine',NULL),(21,NULL,11,0,0,'form',1,16,'이슈처리승인여부',0,NULL,'isRunningCan_t','uEngine',NULL),(22,NULL,11,0,0,'form',1,17,'이슈등록',0,NULL,'formRegIssue_f_t','uEngine',NULL),(23,NULL,11,0,0,'form',1,18,'결과처리통보',0,NULL,'formcheccreportnotice_t','uEngine',NULL),(24,NULL,11,0,0,'form',1,19,'결과보고',0,NULL,'FormResponseReport_f_t','uEngine',NULL),(25,NULL,11,0,0,'form',1,20,'계획보고 확인',0,NULL,'FormCheckReportPlan_f_t','uEngine',NULL),(26,NULL,11,0,0,'form',1,21,'계획보고',0,NULL,'FormReportPlan_f_t','uEngine',NULL),(27,NULL,10,0,0,'process',1,22,'이슈처리',0,NULL,'ProcessIssueTracking_p_t','uEngine',NULL),(36,NULL,-1,1,0,'folder',0,NULL,'test',0,NULL,NULL,NULL,NULL),(37,NULL,8,0,0,'process',2,24,'defaultWIH_test',0,NULL,'defaultWIH_sample','uEngine',NULL),(38,'description',-1,0,0,'form',0,NULL,'newFormDef',0,NULL,'formDef',NULL,NULL),(39,'description',-1,0,0,'form',0,26,'form2',0,NULL,'form2',NULL,NULL),(40,'description',-1,0,0,'form',0,27,'form3',0,NULL,'form3',NULL,NULL),(41,NULL,-1,0,0,'process',1,28,'Noname',0,NULL,'metaworks3process',NULL,NULL),(42,'description',-1,0,0,'form',0,29,'testForm',0,NULL,'testForm',NULL,NULL),(43,'description',-1,0,0,'class',0,30,'test.Person',1,NULL,'test.Person',NULL,NULL),(44,'description',-1,0,0,'class',0,31,'test/Person.java',1,NULL,'test/Person.java',NULL,NULL),(46,'description',-1,0,0,'class',0,32,'test/Person2.java',1,NULL,'test/Person2.java',NULL,NULL),(47,'description',-1,0,0,'class',0,33,'Person',1,NULL,'test/Person3.java',NULL,NULL),(48,'description',-1,0,0,'class',0,34,'Person',1,NULL,'test/Person4.java',NULL,NULL),(49,'description',-1,0,0,'class',0,35,'Person',1,NULL,'test4/Person.java',NULL,NULL),(51,'description',-1,0,0,'class',0,36,'Person',1,NULL,'test5/Person.java',NULL,NULL),(53,'description',-1,0,0,'class',0,38,'Person',1,NULL,'test8/Person.java',NULL,NULL),(54,'description',-1,0,0,'class',0,39,'Person',1,NULL,'test9/Person.java',NULL,NULL),(55,'description',-1,0,0,'class',0,40,'Person',1,NULL,'test10/Person.java',NULL,NULL),(56,'description',-1,0,0,'class',0,41,'Person',1,NULL,'test11/Person.java',NULL,NULL),(57,'description',-1,0,0,'form',0,42,'Test',0,NULL,'test/Test.java',NULL,NULL),(58,'description',-1,0,0,'class',0,43,'Person',1,NULL,'test14/Person.java',NULL,NULL),(59,'description',-1,0,0,'class',0,44,'Person',1,NULL,'test20/Person.java',NULL,NULL),(60,'description',-1,0,0,'class',0,45,'Person',1,NULL,'test40/Person.java',NULL,NULL),(61,'description',-1,0,0,'class',0,46,'Person',1,NULL,'test100/Person.java',NULL,NULL),(62,'description',-1,0,0,'class',1,63,'Addressbook',1,NULL,'test90/Addressbook.java',NULL,NULL),(65,'description',-1,0,0,'class',0,60,'Addressbook',1,NULL,'test110/Addressbook.java',NULL,NULL),(69,'description',1,0,0,'class',0,76,'Contact',0,NULL,'test/Contact.java',NULL,NULL),(72,'description',1,0,0,'class',0,111,'Addressbook',0,NULL,'test/Addressbook.java',NULL,NULL),(73,'description',1,0,0,'class',0,112,'Person',0,NULL,'test1/Person.java',NULL,NULL),(74,'description',1,0,0,'class',0,114,'Person',0,NULL,'com/abc/Person.java',NULL,NULL),(76,'description',1,0,0,'class',1,122,'Addressbook',0,NULL,'test2/Addressbook.java',NULL,NULL),(77,NULL,1,0,0,'process',2,130,'metaworksObjTest',0,NULL,'metaworksObjTest',NULL,NULL),(78,'description',1,0,0,'class',0,134,'CRC',0,NULL,'org/kalm/CRC.java',NULL,NULL),(81,'description',1,0,0,'class',0,135,'TextLine',0,NULL,'org/uengine/TextLine.java',NULL,NULL),(83,'description',1,0,0,'class',0,138,'CRCCard',0,NULL,'org/kalm/CRCCard.java',NULL,NULL),(84,NULL,1,0,0,'process',1,140,'????? ????',0,NULL,'classDev',NULL,NULL),(87,NULL,1,0,0,'process',3,144,'Request Class ',0,NULL,'reqCls',NULL,NULL),(92,'description',1,0,0,'class',0,208,'Guage',0,NULL,'com/test/widget/Guage.java',NULL,NULL),(93,'description',1,0,0,'class',0,214,'Person',0,NULL,'test900/Person.java',NULL,NULL),(94,'description',1,0,0,'class',0,231,'Area',0,NULL,'com/test/widget/chart/Area.java',NULL,NULL),(95,'description',1,0,0,'class',0,234,'Dashboard',0,NULL,'com/test/dashboard/Dashboard.java',NULL,NULL),(96,'description',1,0,0,'class',0,237,'Node',0,NULL,'org/codi/knol/Node.java',NULL,NULL),(97,'description',1,0,0,'class',0,242,'INode',0,NULL,'org/codi/knol/INode.java',NULL,NULL);
/*!40000 ALTER TABLE `BPM_PROCDEF` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BPM_PROCDEFVER`
--

DROP TABLE IF EXISTS `BPM_PROCDEFVER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BPM_PROCDEFVER` (
  `DEFVERID` int(11) NOT NULL DEFAULT '0',
  `FILEPATH` varchar(255) DEFAULT NULL,
  `VER` int(11) DEFAULT NULL,
  `DEFID` int(11) DEFAULT NULL,
  `DEFNAME` varchar(255) DEFAULT NULL,
  `MODDATE` datetime DEFAULT NULL,
  `PRODSTARTDATE` datetime DEFAULT NULL,
  `ISDELETED` int(11) DEFAULT '0',
  PRIMARY KEY (`DEFVERID`),
  KEY `FK890156D446F158C1` (`DEFID`),
  CONSTRAINT `FK890156D446F158C1` FOREIGN KEY (`DEFID`) REFERENCES `BPM_PROCDEF` (`DEFID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BPM_PROCDEFVER`
--

LOCK TABLES `BPM_PROCDEFVER` WRITE;
/*!40000 ALTER TABLE `BPM_PROCDEFVER` DISABLE KEYS */;
INSERT INTO `BPM_PROCDEFVER` VALUES (1,'LINK:1.process',1,2,'Reply','2011-09-25 16:23:23',NULL,0),(2,'LINK:2.process',1,3,'Ping','2011-09-25 16:23:23',NULL,0),(3,'LINK:3.process',1,4,'masterProcess','2011-09-25 16:23:23',NULL,0),(4,'LINK:4.process',1,5,'mailAttachProcess','2011-09-25 16:23:23',NULL,0),(5,'LINK:5.process',1,6,'InstancePreview','2011-09-25 16:23:23',NULL,0),(6,'LINK:6.process',1,7,'페북에 공유하기','2011-09-25 16:23:23',NULL,0),(7,'LINK:7.form',1,12,'폼_이슈등록','2011-09-25 16:26:05',NULL,0),(8,'LINK:8.form',1,13,'폼_이슈처리여부판단','2011-09-25 16:26:05',NULL,0),(9,'LINK:9.form',1,14,'폼_이슈처리결과입력','2011-09-25 16:26:05',NULL,0),(10,'LINK:10.form',1,15,'폼_이슈처리결과확인','2011-09-25 16:26:05',NULL,0),(11,'LINK:11.form',1,16,'폼_이슈처리불가알림','2011-09-25 16:26:05',NULL,0),(12,'LINK:12.process',1,17,'업무요청','2011-09-25 16:26:05',NULL,0),(13,'LINK:13.form',1,18,'5whyform','2011-09-25 16:26:06',NULL,0),(14,'LINK:14.form',1,19,'결과보고 확인','2011-09-25 16:26:06',NULL,0),(15,'LINK:15.form',1,20,'이슈처리지연알림','2011-09-25 16:26:06',NULL,0),(16,'LINK:16.form',1,21,'이슈처리승인여부','2011-09-25 16:26:06',NULL,0),(17,'LINK:17.form',1,22,'이슈등록','2011-09-25 16:26:06',NULL,0),(18,'LINK:18.form',1,23,'결과처리통보','2011-09-25 16:26:06',NULL,0),(19,'LINK:19.form',1,24,'결과보고','2011-09-25 16:26:06',NULL,0),(20,'LINK:20.form',1,25,'계획보고 확인','2011-09-25 16:26:06',NULL,0),(21,'LINK:21.form',1,26,'계획보고','2011-09-25 16:26:06',NULL,0),(22,'LINK:22.process',1,27,'이슈처리','2011-09-25 16:26:06',NULL,0),(23,'LINK:23.upd',1,37,'defaultWIH_test','2011-10-18 14:42:05',NULL,0),(24,'LINK:24.upd',2,37,'defaultWIH_test','2011-10-19 11:12:37',NULL,0),(25,'LINK:25.form',0,38,'newFormDef','2011-12-28 23:56:17',NULL,0),(26,'LINK:26.form',0,39,'form2','2011-12-29 00:31:14',NULL,0),(27,'LINK:27.form',0,40,'form3','2011-12-29 01:37:30',NULL,0),(28,'LINK:28.upd',1,41,'Noname','2011-12-29 01:39:22',NULL,0),(29,'LINK:29.form',0,42,'testForm','2012-01-04 17:50:19',NULL,0),(30,'LINK:30.form',0,43,'test.Person','2012-01-14 23:55:56',NULL,0),(31,'LINK:31.form',0,44,'test/Person.java','2012-01-14 23:57:01',NULL,0),(32,'LINK:32.form',0,46,'test/Person2.java','2012-01-15 00:05:59',NULL,0),(33,'LINK:33.form',0,47,'Person','2012-01-15 00:20:49',NULL,0),(34,'LINK:34.form',0,48,'Person','2012-01-15 00:28:14',NULL,0),(35,'LINK:35.form',0,49,'Person','2012-01-15 09:25:04',NULL,0),(36,'LINK:36.form',0,51,'Person','2012-01-15 09:27:04',NULL,0),(38,'LINK:38.form',0,53,'Person','2012-01-15 12:51:37',NULL,0),(39,'LINK:39.form',0,54,'Person','2012-01-15 13:01:44',NULL,0),(40,'LINK:40.form',0,55,'Person','2012-01-15 17:24:17',NULL,0),(41,'LINK:41.form',0,56,'Person','2012-01-15 17:43:14',NULL,0),(42,'LINK:42.form',0,57,'Test','2012-01-15 18:45:35',NULL,0),(43,'LINK:43.form',0,58,'Person','2012-01-17 19:10:38',NULL,0),(44,'LINK:44.form',0,59,'Person','2012-01-17 20:42:12',NULL,0),(45,'LINK:45.form',0,60,'Person','2012-01-20 15:29:54',NULL,0),(46,'LINK:46_rev31.form',0,61,'Person','2012-01-24 16:31:19',NULL,0),(49,'LINK:49_rev8.form',0,62,'Addressbook','2012-01-22 00:12:03',NULL,0),(60,'LINK:60.form',0,65,'Addressbook','2012-01-22 00:19:09',NULL,0),(63,'LINK:63_rev5.class',1,62,'Addressbook','2012-01-24 16:32:25',NULL,0),(76,'LINK:76_rev13.class',0,69,'Contact','2012-01-28 12:31:01',NULL,0),(111,'LINK:111.class',0,72,'Addressbook','2012-01-25 16:08:28',NULL,0),(112,'LINK:112_rev8.class',0,73,'Person','2012-01-25 17:39:31',NULL,0),(114,'LINK:114_rev2.class',0,74,'Person','2012-01-25 16:17:54',NULL,0),(120,'LINK:120_rev1.class',0,76,'Addressbook','2012-01-25 16:26:18',NULL,0),(122,'LINK:122_rev5.class',1,76,'Addressbook','2012-02-01 13:39:53',NULL,0),(129,'LINK:129.upd',1,77,'metaworksObjTest','2012-01-27 15:33:37',NULL,0),(130,'LINK:130.upd',2,77,'metaworksObjTest','2012-01-27 22:24:28',NULL,0),(134,'LINK:134_rev5.class',0,78,'CRC','2012-02-01 14:33:17',NULL,0),(135,'LINK:135_rev2.class',0,81,'TextLine','2012-01-30 10:48:41',NULL,0),(138,'LINK:138_rev56.class',0,83,'CRCCard','2012-02-01 13:40:20',NULL,0),(140,'LINK:140.upd',1,84,'????? ????','2012-01-30 14:35:45',NULL,0),(142,'LINK:142.upd',1,87,'Request Class ','2012-01-30 21:30:00',NULL,0),(143,'LINK:143.upd',2,87,'Request Class ','2012-01-30 21:45:35',NULL,0),(144,'LINK:144.upd',3,87,'Request Class ','2012-01-30 21:49:26',NULL,0),(208,'LINK:208_rev18.class',0,92,'Guage','2012-02-02 08:32:23',NULL,0),(214,'LINK:214_rev3.class',0,93,'Person','2012-02-02 07:05:34',NULL,0),(231,'LINK:231_rev2.class',0,94,'Area','2012-02-02 11:13:10',NULL,0),(234,'LINK:234_rev2.class',0,95,'Dashboard','2012-02-02 13:56:15',NULL,0),(237,'LINK:237_rev33.class',0,96,'Node','2012-02-02 18:03:02',NULL,0),(242,'LINK:242_rev12.class',0,97,'INode','2012-02-02 18:00:04',NULL,0);
/*!40000 ALTER TABLE `BPM_PROCDEFVER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BPM_PROCINST`
--

DROP TABLE IF EXISTS `BPM_PROCINST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BPM_PROCINST` (
  `INSTID` int(11) NOT NULL,
  `DEFVERID` int(11) DEFAULT NULL,
  `DEFID` int(11) DEFAULT NULL,
  `DEFNAME` varchar(255) DEFAULT NULL,
  `DEFPATH` varchar(255) DEFAULT NULL,
  `DEFMODDATE` datetime DEFAULT NULL,
  `STARTEDDATE` datetime DEFAULT NULL,
  `FINISHEDDATE` datetime DEFAULT NULL,
  `DUEDATE` datetime DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `ISDELETED` int(11) DEFAULT '0',
  `ISADHOC` int(11) DEFAULT '0',
  `ISARCHIVE` int(11) DEFAULT '0',
  `ISSUBPROCESS` int(11) DEFAULT '0',
  `ISEVENTHANDLER` int(11) DEFAULT '0',
  `ROOTINSTID` int(11) DEFAULT NULL,
  `MAININSTID` int(11) DEFAULT NULL,
  `MAINDEFVERID` int(11) DEFAULT NULL,
  `MAINACTTRCTAG` varchar(20) DEFAULT NULL,
  `MAINEXECSCOPE` varchar(20) DEFAULT NULL,
  `ABSTRCPATH` varchar(200) DEFAULT NULL,
  `DONTRETURN` int(11) DEFAULT NULL,
  `MODDATE` datetime DEFAULT NULL,
  `EXT1` varchar(100) DEFAULT NULL,
  `INITEP` varchar(100) DEFAULT NULL,
  `INITRSNM` varchar(100) DEFAULT NULL,
  `CURREP` varchar(100) DEFAULT NULL,
  `CURRRSNM` varchar(100) DEFAULT NULL,
  `STRATEGYID` int(11) DEFAULT NULL,
  `PREVCURREP` varchar(100) DEFAULT NULL,
  `PREVCURRRSNM` varchar(100) DEFAULT NULL,
  `STARCNT` int(11) DEFAULT NULL,
  `STARRSNM` varchar(100) DEFAULT NULL,
  `STARFLAG` int(11) DEFAULT NULL,
  `ABSTRACTINST` text,
  `CURRTRCTAG` int(11) DEFAULT NULL,
  `PREVTRCTAG` int(11) DEFAULT NULL,
  `INITCOMCD` varchar(20) DEFAULT NULL,
  `SECUOPT` char(1) DEFAULT '0',
  PRIMARY KEY (`INSTID`),
  KEY `FKF57F151C46F158C1` (`DEFID`),
  KEY `FKF57F151C78EB68E6` (`ROOTINSTID`),
  CONSTRAINT `FKF57F151C46F158C1` FOREIGN KEY (`DEFID`) REFERENCES `BPM_PROCDEF` (`DEFID`),
  CONSTRAINT `FKF57F151C78EB68E6` FOREIGN KEY (`ROOTINSTID`) REFERENCES `BPM_PROCINST` (`INSTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BPM_PROCINST`
--

LOCK TABLES `BPM_PROCINST` WRITE;
/*!40000 ALTER TABLE `BPM_PROCINST` DISABLE KEYS */;
INSERT INTO `BPM_PROCINST` VALUES (65,22,27,'이슈처리',NULL,'2011-09-25 16:26:06','2011-11-12 22:04:41',NULL,'2011-11-22 22:04:41','Running',NULL,'이슈처리65',0,0,0,0,0,65,NULL,NULL,NULL,NULL,NULL,NULL,'2011-11-12 22:04:41',NULL,'test','Tester',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(66,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-11-12 22:05:11',NULL,'2011-11-22 22:05:11','Running',NULL,'defaultWIH_test66',0,0,0,0,0,66,NULL,NULL,NULL,NULL,NULL,NULL,'2011-11-12 22:05:12',NULL,'test','Tester',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(67,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-11-12 22:06:17',NULL,'2011-11-22 22:06:24','Running',NULL,'defaultWIH_test67',0,0,0,0,0,67,NULL,NULL,NULL,NULL,NULL,NULL,'2011-11-12 22:07:54',NULL,'test','Tester',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(68,2,3,'Ping',NULL,'2011-09-25 16:23:23','2011-11-12 22:14:44',NULL,'2011-11-22 22:14:44','Running',NULL,'sdfsdfadfasdf\n',0,0,0,0,0,68,NULL,NULL,NULL,NULL,NULL,NULL,'2011-11-12 22:14:53',NULL,'test','Tester','test','Tester',0,NULL,NULL,NULL,NULL,NULL,'sdfsdfadfasdf\n',NULL,NULL,'uEngine','0'),(73,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-16 13:34:41',NULL,'2011-12-26 13:34:41','Running',NULL,'defaultWIH_test73',0,0,0,0,0,73,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-16 13:34:41',NULL,'1401720840','1401720840',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),(74,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-20 00:07:37',NULL,'2011-12-30 00:07:37','Running',NULL,'defaultWIH_test74',0,0,0,0,0,74,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-20 00:07:40',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(75,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-20 00:49:27',NULL,'2011-12-30 00:49:27','Running',NULL,'defaultWIH_test75',0,0,0,0,0,75,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-20 00:50:02',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(76,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-20 01:16:30',NULL,'2011-12-30 01:16:30','Running',NULL,'defaultWIH_test76',0,0,0,0,0,76,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-20 01:16:42',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(77,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-20 01:16:55',NULL,'2011-12-30 01:16:55','Running',NULL,'defaultWIH_test77',0,0,0,0,0,77,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-20 01:17:23',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(78,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-20 01:18:15',NULL,'2011-12-30 01:18:15','Running',NULL,'defaultWIH_test78',0,0,0,0,0,78,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-20 01:18:16',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(79,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-20 01:18:26',NULL,'2011-12-30 01:18:26','Running',NULL,'defaultWIH_test79',0,0,0,0,0,79,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-20 01:18:27',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(80,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-20 01:37:19',NULL,'2011-12-30 01:37:20','Running',NULL,'defaultWIH_test80',0,0,0,0,0,80,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-20 01:37:22',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(81,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-20 01:38:16',NULL,'2011-12-30 01:38:16','Running',NULL,'defaultWIH_test81',0,0,0,0,0,81,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-20 01:38:17',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(82,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-20 01:54:20','2011-12-20 04:11:48','2011-12-30 01:54:20','Stopped',NULL,'defaultWIH_test82',0,0,0,0,0,82,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-20 04:11:48',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(83,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-20 01:55:06',NULL,'2011-12-30 01:55:06','Running',NULL,'defaultWIH_test83',0,0,0,0,0,83,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-20 01:55:07',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(84,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-20 03:29:11',NULL,'2011-12-30 03:29:11','Running',NULL,'defaultWIH_test84',0,0,0,0,0,84,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-21 15:43:31',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(85,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-21 01:28:06',NULL,'2011-12-31 01:28:06','Running',NULL,'defaultWIH_test85',0,0,0,0,0,85,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-21 01:28:09',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(86,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-21 14:02:32',NULL,'2011-12-31 14:02:32','Running',NULL,'defaultWIH_test86',0,0,0,0,0,86,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-21 14:55:27',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(87,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-22 21:44:17',NULL,'2012-01-01 21:44:17','Running',NULL,'defaultWIH_test87',0,0,0,0,0,87,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-22 21:44:20',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(88,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-22 21:44:24',NULL,'2012-01-01 21:44:24','Running',NULL,'defaultWIH_test88',0,0,0,0,0,88,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-22 21:44:24',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(89,3,4,'masterProcess',NULL,'2011-09-25 16:23:23','2011-12-22 23:47:41','2011-12-22 23:47:41','2012-01-01 23:47:41','Completed',NULL,'masterProcess89',0,0,0,0,0,89,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-22 23:47:41',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0'),(90,12,17,'업무요청',NULL,'2011-09-25 16:26:05','2011-12-22 23:47:59',NULL,'2012-01-01 23:47:59','Running',NULL,'업무요청90',0,0,0,0,0,90,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-23 01:16:44',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(91,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2011-12-23 01:35:53',NULL,'2012-01-02 01:35:53','Running',NULL,'defaultWIH_test91',0,0,0,0,0,91,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-23 15:59:57',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(92,1,2,'Reply',NULL,'2011-09-25 16:23:23','2011-12-23 15:59:19',NULL,'2012-01-02 15:59:19','Running',NULL,'Reply92',0,0,0,0,0,92,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-23 15:59:22',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(93,28,41,'Noname',NULL,'2011-12-29 01:39:22','2011-12-29 01:47:22','2011-12-29 10:46:42','2012-01-08 01:47:22','Completed',NULL,'Noname93',0,0,0,0,0,93,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-29 10:46:42',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(94,28,41,'Noname',NULL,'2011-12-29 01:39:22','2011-12-29 11:20:37','2011-12-29 11:20:58','2012-01-08 11:20:37','Completed',NULL,'Noname94',0,0,0,0,0,94,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-29 11:20:58',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(95,28,41,'Noname',NULL,'2011-12-29 01:39:22','2011-12-29 13:25:09','2011-12-29 13:25:22','2012-01-08 13:25:09','Completed',NULL,'Noname95',0,0,0,0,0,95,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-29 13:25:22',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(96,28,41,'Noname',NULL,'2011-12-29 01:39:22','2011-12-29 13:26:43','2011-12-29 13:27:28','2012-01-08 13:26:43','Completed',NULL,'Noname96',0,0,0,0,0,96,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-29 13:27:28',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(97,28,41,'Noname',NULL,'2011-12-29 01:39:22','2011-12-29 13:34:34','2011-12-29 13:34:53','2012-01-08 13:34:34','Completed',NULL,'Noname97',0,0,0,0,0,97,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-29 13:34:53',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(98,28,41,'Noname',NULL,'2011-12-29 01:39:22','2011-12-29 13:36:39','2011-12-29 13:37:35','2012-01-08 13:36:39','Completed',NULL,'Noname98',0,0,0,0,0,98,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-29 13:37:35',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(99,28,41,'Noname',NULL,'2011-12-29 01:39:22','2011-12-29 13:41:48',NULL,'2012-01-08 13:41:48','Running',NULL,'Noname99',0,0,0,0,0,99,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-29 13:41:50',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(100,28,41,'Noname',NULL,'2011-12-29 01:39:22','2011-12-29 13:44:02','2011-12-29 13:44:51','2012-01-08 13:44:02','Completed',NULL,'Noname100',0,0,0,0,0,100,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-29 13:44:51',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(101,28,41,'Noname',NULL,'2011-12-29 01:39:22','2011-12-29 13:51:36','2011-12-29 13:52:34','2012-01-08 13:51:36','Completed',NULL,'Noname101',0,0,0,0,0,101,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-29 13:52:34',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(102,28,41,'Noname',NULL,'2011-12-29 01:39:22','2011-12-29 13:52:59','2011-12-29 13:55:37','2012-01-08 13:52:59','Completed',NULL,'Noname102',0,0,0,0,0,102,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-29 13:55:37',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(103,28,41,'Noname',NULL,'2011-12-29 01:39:22','2011-12-29 13:58:00','2011-12-29 13:59:31','2012-01-08 13:58:00','Completed',NULL,'Noname103',0,0,0,0,0,103,NULL,NULL,NULL,NULL,NULL,NULL,'2011-12-29 13:59:31',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(104,28,41,'Noname',NULL,'2011-12-29 01:39:22','2012-01-04 18:08:13',NULL,'2012-01-14 18:08:13','Running',NULL,'Noname104',0,0,0,0,0,104,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-04 18:09:36',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(105,1,2,'Reply',NULL,'2011-09-25 16:23:23','2012-01-08 21:20:39',NULL,'2012-01-18 21:20:39','Running',NULL,'Reply105',0,0,0,0,0,105,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-08 21:20:46',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(106,24,37,'defaultWIH_test',NULL,'2011-10-19 11:12:37','2012-01-08 21:21:10',NULL,'2012-01-18 21:21:10','Running',NULL,'defaultWIH_test106',0,0,0,0,0,106,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-10 17:37:47',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(107,22,27,'이슈처리',NULL,'2011-09-25 16:26:06','2012-01-08 21:43:51',NULL,'2012-01-18 21:43:51','Running',NULL,'이슈처리107',0,0,0,0,0,107,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-08 22:42:22',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(108,129,77,'metaworksObjTest',NULL,'2012-01-27 15:33:37','2012-01-27 17:52:53',NULL,'2012-02-06 17:52:53','Running',NULL,'metaworksObjTest108',0,0,0,0,0,108,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-27 17:52:57',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(109,129,77,'metaworksObjTest',NULL,'2012-01-27 15:33:37','2012-01-27 17:57:04',NULL,'2012-02-06 17:57:04','Running',NULL,'metaworksObjTest109',0,0,0,0,0,109,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-27 17:57:07',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(110,129,77,'metaworksObjTest',NULL,'2012-01-27 15:33:37','2012-01-27 19:14:32',NULL,'2012-02-06 19:14:32','Running',NULL,'metaworksObjTest110',0,0,0,0,0,110,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-27 19:14:35',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(111,130,77,'metaworksObjTest',NULL,'2012-01-27 22:24:28','2012-01-27 22:24:50',NULL,'2012-02-06 22:24:50','Running',NULL,'metaworksObjTest111',0,0,0,0,0,111,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-27 22:24:53',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(112,130,77,'metaworksObjTest',NULL,'2012-01-27 22:24:28','2012-01-27 22:44:24','2012-01-28 01:30:11','2012-02-06 22:44:24','Completed',NULL,'jinyoung',0,0,0,0,0,112,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-28 01:30:11',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(113,130,77,'metaworksObjTest',NULL,'2012-01-27 22:24:28','2012-01-28 01:47:12','2012-01-28 01:47:40','2012-02-07 01:47:12','Completed',NULL,'장진영',0,0,0,0,0,113,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-28 01:47:40',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(114,130,77,'metaworksObjTest',NULL,'2012-01-27 22:24:28','2012-01-30 11:15:24',NULL,'2012-02-09 11:15:24','Running',NULL,'metaworksObjTest114',0,0,0,0,0,114,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-30 20:28:22',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(115,140,84,'클래스개발 프로세스',NULL,'2012-01-30 14:35:45','2012-01-30 14:36:17',NULL,'2012-02-09 14:36:17','Running',NULL,'',0,0,0,0,0,115,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-30 14:39:59',NULL,'1401720840','장진영','1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(116,140,84,'클래스개발 프로세스',NULL,'2012-01-30 14:35:45','2012-01-30 18:01:19',NULL,'2012-02-09 18:01:19','Running',NULL,'패키지명클래스명',0,0,0,0,0,116,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-30 19:08:26',NULL,'1401720840','장진영','1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(117,143,87,'Request Class ',NULL,'2012-01-30 21:45:35','2012-01-30 21:46:01',NULL,'2012-02-09 21:46:02','Running',NULL,'Request Class 117',0,0,0,0,0,117,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-30 21:46:06',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(118,143,87,'Request Class ',NULL,'2012-01-30 21:45:35','2012-01-30 21:47:01',NULL,'2012-02-09 21:47:01','Running',NULL,'Request Class 118',0,0,0,0,0,118,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-30 21:47:03',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(119,143,87,'Request Class ',NULL,'2012-01-30 21:45:35','2012-01-30 21:47:49',NULL,'2012-02-09 21:47:49','Running',NULL,'Request Class 119',0,0,0,0,0,119,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-30 21:47:53',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(120,144,87,'Request Class ',NULL,'2012-01-30 21:49:26','2012-01-30 21:50:56',NULL,'2012-02-09 21:50:56','Running',NULL,'Request Class 120',0,0,0,0,0,120,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-30 21:50:57',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(121,144,87,'Request Class ',NULL,'2012-01-30 21:49:26','2012-01-30 22:02:03','2012-01-30 22:41:41','2012-02-09 22:02:03','Completed',NULL,'Request Class 121',0,0,0,0,0,121,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-31 00:34:04',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(122,144,87,'Request Class ',NULL,'2012-01-30 21:49:26','2012-01-30 23:59:54',NULL,'2012-02-09 23:59:54','Running',NULL,'Request Class 122',0,0,0,0,0,122,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-30 23:59:56',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(123,144,87,'Request Class ',NULL,'2012-01-30 21:49:26','2012-01-31 19:26:43','2012-01-31 19:27:48','2012-02-10 19:26:43','Completed',NULL,'Request Class 123',0,0,0,0,0,123,NULL,NULL,NULL,NULL,NULL,NULL,'2012-01-31 19:27:48',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0'),(124,144,87,'Request Class ',NULL,'2012-01-30 21:49:26','2012-02-01 17:51:33',NULL,'2012-02-11 17:51:33','Running',NULL,'Request Class 124',0,0,0,0,0,124,NULL,NULL,NULL,NULL,NULL,NULL,'2012-02-01 17:51:53',NULL,'1401720840','장진영',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'uEngine','0');
/*!40000 ALTER TABLE `BPM_PROCINST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BPM_PROCVAR`
--

DROP TABLE IF EXISTS `BPM_PROCVAR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BPM_PROCVAR` (
  `VARID` int(11) NOT NULL AUTO_INCREMENT,
  `INSTID` int(11) DEFAULT NULL,
  `DATATYPE` int(11) DEFAULT NULL,
  `VALUESTRING` varchar(4000) DEFAULT NULL,
  `VALUELONG` int(11) DEFAULT NULL,
  `VALUEBOOLEAN` int(11) DEFAULT NULL,
  `VALUEDATE` datetime DEFAULT NULL,
  `VARINDEX` int(11) DEFAULT NULL,
  `TRCTAG` varchar(20) DEFAULT NULL,
  `ISPROPERTY` int(11) DEFAULT NULL,
  `MODDATE` datetime DEFAULT NULL,
  `KEYNAME` varchar(100) DEFAULT NULL,
  `KEYSTRING` varchar(200) DEFAULT NULL,
  `FILECONTENT` text,
  `HTMLFILEPATH` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`VARID`)
) ENGINE=InnoDB AUTO_INCREMENT=11155 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BPM_PROCVAR`
--

LOCK TABLES `BPM_PROCVAR` WRITE;
/*!40000 ALTER TABLE `BPM_PROCVAR` DISABLE KEYS */;
INSERT INTO `BPM_PROCVAR` VALUES (10413,65,6,'',0,0,'2011-11-12 22:04:41',0,'1',1,'2011-11-12 22:04:41','_start_time','1:_start_time:prop',NULL,NULL),(10414,65,2,'',0,0,NULL,0,'40',1,'2011-11-12 22:04:41','currentStep','40:currentStep:prop',NULL,NULL),(10415,65,1,'Running',0,0,NULL,0,'73',1,'2011-11-12 22:04:41','_status','73:_status:prop',NULL,NULL),(10416,65,2,'',0,0,NULL,0,'74',1,'2011-11-12 22:04:41','currentStep','74:currentStep:prop',NULL,NULL),(10417,65,2,'',0,0,NULL,0,'73',1,'2011-11-12 22:04:41','currentStep','73:currentStep:prop',NULL,NULL),(10418,65,1,'1',0,0,NULL,0,'',1,'2011-11-12 22:04:41','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10419,65,2,'',0,0,NULL,0,'32',1,'2011-11-12 22:04:41','currentStep','32:currentStep:prop',NULL,NULL),(10420,65,1,'57',0,0,NULL,0,'1',1,'2011-11-12 22:04:41','_task id','1:_task id:prop',NULL,NULL),(10421,65,1,'Running',0,0,NULL,0,'1',1,'2011-11-12 22:04:41','_status','1:_status:prop',NULL,NULL),(10422,65,1,'',0,0,NULL,0,'',1,'2011-11-12 22:04:41','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10423,65,6,'',0,0,'2011-11-12 22:04:41',0,'73',1,'2011-11-12 22:04:41','_start_time','73:_start_time:prop',NULL,NULL),(10424,65,6,'',0,0,'2011-11-12 22:04:41',0,'',1,'2011-11-12 22:04:41','_start_time',':_start_time:prop',NULL,NULL),(10425,65,2,'',0,0,NULL,0,'7',1,'2011-11-12 22:04:41','currentStep','7:currentStep:prop',NULL,NULL),(10426,65,6,'',0,0,'2011-11-22 22:04:41',0,'',1,'2011-11-12 22:04:41','_due date',':_due date:prop',NULL,NULL),(10427,65,1,'Running',0,0,NULL,0,'',1,'2011-11-12 22:04:41','_status',':_status:prop',NULL,NULL),(10428,65,2,'',0,0,NULL,0,'',1,'2011-11-12 22:04:41','currentStep',':currentStep:prop',NULL,NULL),(10429,65,2,'',0,0,NULL,0,'77',1,'2011-11-12 22:04:41','currentStep','77:currentStep:prop',NULL,NULL),(10430,65,2,'',0,0,NULL,0,'24',1,'2011-11-12 22:04:41','currentStep','24:currentStep:prop',NULL,NULL),(10431,65,6,'',0,0,'2011-11-17 22:04:41',0,'1',1,'2011-11-12 22:04:41','_due date','1:_due date:prop',NULL,NULL),(10432,66,1,'58',0,0,NULL,0,'1',1,'2011-11-12 22:05:12','_task id','1:_task id:prop',NULL,NULL),(10433,66,1,'',0,0,NULL,0,'',1,'2011-11-12 22:05:12','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10434,66,1,'Running',0,0,NULL,0,'',1,'2011-11-12 22:05:12','_status',':_status:prop',NULL,NULL),(10435,66,6,'',0,0,'2011-11-12 22:05:11',0,'',1,'2011-11-12 22:05:12','_start_time',':_start_time:prop',NULL,NULL),(10436,66,1,'Running',0,0,NULL,0,'1',1,'2011-11-12 22:05:12','_status','1:_status:prop',NULL,NULL),(10437,66,6,'',0,0,'2011-11-22 22:05:11',0,'',1,'2011-11-12 22:05:12','_due date',':_due date:prop',NULL,NULL),(10438,66,2,'',0,0,NULL,0,'',1,'2011-11-12 22:05:12','currentStep',':currentStep:prop',NULL,NULL),(10439,66,1,'1',0,0,NULL,0,'',1,'2011-11-12 22:05:12','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10440,66,6,'',0,0,'2011-11-12 22:05:11',0,'1',1,'2011-11-12 22:05:12','_start_time','1:_start_time:prop',NULL,NULL),(10441,66,6,'',0,0,'2011-11-17 22:05:11',0,'1',1,'2011-11-12 22:05:12','_due date','1:_due date:prop',NULL,NULL),(10442,67,1,'59',0,0,NULL,0,'1',1,'2011-11-12 22:07:54','_task id','1:_task id:prop',NULL,NULL),(10443,67,1,'',0,0,NULL,0,'',1,'2011-11-12 22:07:55','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10444,67,1,'Running',0,0,NULL,0,'',1,'2011-11-12 22:07:55','_status',':_status:prop',NULL,NULL),(10445,67,6,'',0,0,'2011-11-12 22:06:24',0,'',1,'2011-11-12 22:07:55','_start_time',':_start_time:prop',NULL,NULL),(10446,67,1,'Running',0,0,NULL,0,'1',1,'2011-11-12 22:07:55','_status','1:_status:prop',NULL,NULL),(10447,67,6,'',0,0,'2011-11-22 22:06:24',0,'',1,'2011-11-12 22:07:55','_due date',':_due date:prop',NULL,NULL),(10448,67,2,'',0,0,NULL,0,'',1,'2011-11-12 22:07:55','currentStep',':currentStep:prop',NULL,NULL),(10449,67,1,'1',0,0,NULL,0,'',1,'2011-11-12 22:07:55','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10450,67,6,'',0,0,'2011-11-12 22:06:24',0,'1',1,'2011-11-12 22:07:55','_start_time','1:_start_time:prop',NULL,NULL),(10451,67,6,'',0,0,'2011-11-17 22:06:24',0,'1',1,'2011-11-12 22:07:55','_due date','1:_due date:prop',NULL,NULL),(10452,68,6,'',0,0,'2011-11-12 22:14:44',0,'1',1,'2011-11-12 22:14:50','_start_time','1:_start_time:prop',NULL,NULL),(10453,68,2,'',1,0,NULL,0,'3',1,'2011-11-12 22:14:50','currentStep','3:currentStep:prop',NULL,NULL),(10454,68,1,'',0,0,NULL,0,'',1,'2011-11-12 22:14:50','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10455,68,6,'',0,0,'2011-11-17 22:14:49',0,'2',1,'2011-11-12 22:14:50','_due date','2:_due date:prop',NULL,NULL),(10456,68,1,'2',0,0,NULL,0,'',1,'2011-11-12 22:14:50','MESSAGE_onHumanActivityResult2',':MESSAGE_onHumanActivityResult2:prop',NULL,NULL),(10457,68,1,'Running',0,0,NULL,0,'3',1,'2011-11-12 22:14:50','_status','3:_status:prop',NULL,NULL),(10458,68,1,'60',0,0,NULL,0,'1',1,'2011-11-12 22:14:50','_task id','1:_task id:prop',NULL,NULL),(10459,68,1,'Completed',0,0,NULL,0,'1',1,'2011-11-12 22:14:50','_status','1:_status:prop',NULL,NULL),(10460,68,6,'',0,0,'2011-11-12 22:14:44',0,'3',1,'2011-11-12 22:14:50','_start_time','3:_start_time:prop',NULL,NULL),(10461,68,1,'61',0,0,NULL,0,'2',1,'2011-11-12 22:14:50','_task id','2:_task id:prop',NULL,NULL),(10462,68,6,'',0,0,'2011-11-12 22:14:49',0,'2',1,'2011-11-12 22:14:50','_start_time','2:_start_time:prop',NULL,NULL),(10463,68,1,'3',0,0,NULL,0,'',1,'2011-11-12 22:14:50','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10464,68,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <name>Initiator</name>\n  <resourceName>Tester</resourceName>\n  <endpoint>test</endpoint>\n  <companyId>uEngine</companyId>\n  <groupId></groupId>\n  <title>Tester</title>\n  <assignType>0</assignType>\n  <dispatchingOption>-1</dispatchingOption>\n  <isMale>true</isMale>\n  <birthday>2011-11-12 22:11:30.13 KST</birthday>\n  <isGroup>false</isGroup>\n  <locale>en</locale>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2011-11-12 22:14:50','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(10465,68,6,'',0,0,'2011-11-12 22:14:44',0,'',1,'2011-11-12 22:14:50','_start_time',':_start_time:prop',NULL,NULL),(10466,68,1,'1',0,0,NULL,0,'2',1,'2011-11-12 22:14:50','_previous','2:_previous:prop',NULL,NULL),(10467,68,6,'',0,0,'2011-11-12 22:14:49',0,'1',1,'2011-11-12 22:14:50','_end_time','1:_end_time:prop',NULL,NULL),(10468,68,6,'',0,0,'2011-11-22 22:14:44',0,'',1,'2011-11-12 22:14:50','_due date',':_due date:prop',NULL,NULL),(10469,68,1,'Running',0,0,NULL,0,'',1,'2011-11-12 22:14:50','_status',':_status:prop',NULL,NULL),(10470,68,2,'',0,0,NULL,0,'',1,'2011-11-12 22:14:50','currentStep',':currentStep:prop',NULL,NULL),(10471,68,1,'Running',0,0,NULL,0,'2',1,'2011-11-12 22:14:50','_status','2:_status:prop',NULL,NULL),(10472,68,1,'sdfsdfadfasdf\n',0,0,NULL,0,'',0,'2011-11-12 22:14:50','message',':message',NULL,NULL),(10473,68,6,'',0,0,'2011-11-17 22:14:44',0,'1',1,'2011-11-12 22:14:50','_due date','1:_due date:prop',NULL,NULL),(10474,73,1,'62',0,0,NULL,0,'1',1,'2011-12-16 13:34:41','_task id','1:_task id:prop',NULL,NULL),(10475,73,1,'',0,0,NULL,0,'',1,'2011-12-16 13:34:41','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10476,73,1,'Running',0,0,NULL,0,'',1,'2011-12-16 13:34:41','_status',':_status:prop',NULL,NULL),(10477,73,6,'',0,0,'2011-12-16 13:34:41',0,'',1,'2011-12-16 13:34:41','_start_time',':_start_time:prop',NULL,NULL),(10478,73,1,'Running',0,0,NULL,0,'1',1,'2011-12-16 13:34:41','_status','1:_status:prop',NULL,NULL),(10479,73,6,'',0,0,'2011-12-26 13:34:41',0,'',1,'2011-12-16 13:34:41','_due date',':_due date:prop',NULL,NULL),(10480,73,2,'',0,0,NULL,0,'',1,'2011-12-16 13:34:41','currentStep',':currentStep:prop',NULL,NULL),(10481,73,1,'1',0,0,NULL,0,'',1,'2011-12-16 13:34:41','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10482,73,6,'',0,0,'2011-12-16 13:34:41',0,'1',1,'2011-12-16 13:34:41','_start_time','1:_start_time:prop',NULL,NULL),(10483,73,6,'',0,0,'2011-12-21 13:34:41',0,'1',1,'2011-12-16 13:34:41','_due date','1:_due date:prop',NULL,NULL),(10484,74,1,'63',0,0,NULL,0,'1',1,'2011-12-20 00:07:40','_task id','1:_task id:prop',NULL,NULL),(10485,74,1,'',0,0,NULL,0,'',1,'2011-12-20 00:07:40','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10486,74,1,'Running',0,0,NULL,0,'',1,'2011-12-20 00:07:40','_status',':_status:prop',NULL,NULL),(10487,74,6,'',0,0,'2011-12-20 00:07:37',0,'',1,'2011-12-20 00:07:40','_start_time',':_start_time:prop',NULL,NULL),(10488,74,1,'Running',0,0,NULL,0,'1',1,'2011-12-20 00:07:40','_status','1:_status:prop',NULL,NULL),(10489,74,6,'',0,0,'2011-12-30 00:07:37',0,'',1,'2011-12-20 00:07:40','_due date',':_due date:prop',NULL,NULL),(10490,74,2,'',0,0,NULL,0,'',1,'2011-12-20 00:07:40','currentStep',':currentStep:prop',NULL,NULL),(10491,74,1,'1',0,0,NULL,0,'',1,'2011-12-20 00:07:40','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10492,74,6,'',0,0,'2011-12-20 00:07:37',0,'1',1,'2011-12-20 00:07:40','_start_time','1:_start_time:prop',NULL,NULL),(10493,74,6,'',0,0,'2011-12-25 00:07:37',0,'1',1,'2011-12-20 00:07:40','_due date','1:_due date:prop',NULL,NULL),(10494,75,1,'64',0,0,NULL,0,'1',1,'2011-12-20 00:50:02','_task id','1:_task id:prop',NULL,NULL),(10495,75,1,'',0,0,NULL,0,'',1,'2011-12-20 00:50:02','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10496,75,1,'Running',0,0,NULL,0,'',1,'2011-12-20 00:50:02','_status',':_status:prop',NULL,NULL),(10497,75,6,'',0,0,'2011-12-20 00:49:27',0,'',1,'2011-12-20 00:50:02','_start_time',':_start_time:prop',NULL,NULL),(10498,75,1,'Running',0,0,NULL,0,'1',1,'2011-12-20 00:50:02','_status','1:_status:prop',NULL,NULL),(10499,75,6,'',0,0,'2011-12-30 00:49:27',0,'',1,'2011-12-20 00:50:02','_due date',':_due date:prop',NULL,NULL),(10500,75,2,'',0,0,NULL,0,'',1,'2011-12-20 00:50:02','currentStep',':currentStep:prop',NULL,NULL),(10501,75,1,'1',0,0,NULL,0,'',1,'2011-12-20 00:50:02','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10502,75,6,'',0,0,'2011-12-20 00:49:27',0,'1',1,'2011-12-20 00:50:02','_start_time','1:_start_time:prop',NULL,NULL),(10503,75,6,'',0,0,'2011-12-25 00:49:27',0,'1',1,'2011-12-20 00:50:02','_due date','1:_due date:prop',NULL,NULL),(10504,76,1,'65',0,0,NULL,0,'1',1,'2011-12-20 01:16:42','_task id','1:_task id:prop',NULL,NULL),(10505,76,1,'',0,0,NULL,0,'',1,'2011-12-20 01:16:42','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10506,76,1,'Running',0,0,NULL,0,'',1,'2011-12-20 01:16:42','_status',':_status:prop',NULL,NULL),(10507,76,6,'',0,0,'2011-12-20 01:16:30',0,'',1,'2011-12-20 01:16:42','_start_time',':_start_time:prop',NULL,NULL),(10508,76,1,'Running',0,0,NULL,0,'1',1,'2011-12-20 01:16:42','_status','1:_status:prop',NULL,NULL),(10509,76,6,'',0,0,'2011-12-30 01:16:30',0,'',1,'2011-12-20 01:16:42','_due date',':_due date:prop',NULL,NULL),(10510,76,2,'',0,0,NULL,0,'',1,'2011-12-20 01:16:42','currentStep',':currentStep:prop',NULL,NULL),(10511,76,1,'1',0,0,NULL,0,'',1,'2011-12-20 01:16:42','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10512,76,6,'',0,0,'2011-12-20 01:16:30',0,'1',1,'2011-12-20 01:16:42','_start_time','1:_start_time:prop',NULL,NULL),(10513,76,6,'',0,0,'2011-12-25 01:16:30',0,'1',1,'2011-12-20 01:16:42','_due date','1:_due date:prop',NULL,NULL),(10514,77,1,'66',0,0,NULL,0,'1',1,'2011-12-20 01:17:23','_task id','1:_task id:prop',NULL,NULL),(10515,77,1,'',0,0,NULL,0,'',1,'2011-12-20 01:17:23','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10516,77,1,'Running',0,0,NULL,0,'',1,'2011-12-20 01:17:23','_status',':_status:prop',NULL,NULL),(10517,77,6,'',0,0,'2011-12-20 01:16:55',0,'',1,'2011-12-20 01:17:23','_start_time',':_start_time:prop',NULL,NULL),(10518,77,1,'Running',0,0,NULL,0,'1',1,'2011-12-20 01:17:23','_status','1:_status:prop',NULL,NULL),(10519,77,6,'',0,0,'2011-12-30 01:16:55',0,'',1,'2011-12-20 01:17:23','_due date',':_due date:prop',NULL,NULL),(10520,77,2,'',0,0,NULL,0,'',1,'2011-12-20 01:17:23','currentStep',':currentStep:prop',NULL,NULL),(10521,77,1,'1',0,0,NULL,0,'',1,'2011-12-20 01:17:23','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10522,77,6,'',0,0,'2011-12-20 01:16:55',0,'1',1,'2011-12-20 01:17:23','_start_time','1:_start_time:prop',NULL,NULL),(10523,77,6,'',0,0,'2011-12-25 01:16:55',0,'1',1,'2011-12-20 01:17:23','_due date','1:_due date:prop',NULL,NULL),(10524,78,1,'67',0,0,NULL,0,'1',1,'2011-12-20 01:18:16','_task id','1:_task id:prop',NULL,NULL),(10525,78,1,'',0,0,NULL,0,'',1,'2011-12-20 01:18:16','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10526,78,1,'Running',0,0,NULL,0,'',1,'2011-12-20 01:18:16','_status',':_status:prop',NULL,NULL),(10527,78,6,'',0,0,'2011-12-20 01:18:15',0,'',1,'2011-12-20 01:18:16','_start_time',':_start_time:prop',NULL,NULL),(10528,78,1,'Running',0,0,NULL,0,'1',1,'2011-12-20 01:18:16','_status','1:_status:prop',NULL,NULL),(10529,78,6,'',0,0,'2011-12-30 01:18:15',0,'',1,'2011-12-20 01:18:16','_due date',':_due date:prop',NULL,NULL),(10530,78,2,'',0,0,NULL,0,'',1,'2011-12-20 01:18:16','currentStep',':currentStep:prop',NULL,NULL),(10531,78,1,'1',0,0,NULL,0,'',1,'2011-12-20 01:18:16','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10532,78,6,'',0,0,'2011-12-20 01:18:15',0,'1',1,'2011-12-20 01:18:16','_start_time','1:_start_time:prop',NULL,NULL),(10533,78,6,'',0,0,'2011-12-25 01:18:15',0,'1',1,'2011-12-20 01:18:16','_due date','1:_due date:prop',NULL,NULL),(10534,79,1,'68',0,0,NULL,0,'1',1,'2011-12-20 01:18:27','_task id','1:_task id:prop',NULL,NULL),(10535,79,1,'',0,0,NULL,0,'',1,'2011-12-20 01:18:27','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10536,79,1,'Running',0,0,NULL,0,'',1,'2011-12-20 01:18:27','_status',':_status:prop',NULL,NULL),(10537,79,6,'',0,0,'2011-12-20 01:18:26',0,'',1,'2011-12-20 01:18:27','_start_time',':_start_time:prop',NULL,NULL),(10538,79,1,'Running',0,0,NULL,0,'1',1,'2011-12-20 01:18:27','_status','1:_status:prop',NULL,NULL),(10539,79,6,'',0,0,'2011-12-30 01:18:26',0,'',1,'2011-12-20 01:18:27','_due date',':_due date:prop',NULL,NULL),(10540,79,2,'',0,0,NULL,0,'',1,'2011-12-20 01:18:27','currentStep',':currentStep:prop',NULL,NULL),(10541,79,1,'1',0,0,NULL,0,'',1,'2011-12-20 01:18:27','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10542,79,6,'',0,0,'2011-12-20 01:18:26',0,'1',1,'2011-12-20 01:18:27','_start_time','1:_start_time:prop',NULL,NULL),(10543,79,6,'',0,0,'2011-12-25 01:18:26',0,'1',1,'2011-12-20 01:18:27','_due date','1:_due date:prop',NULL,NULL),(10544,80,1,'69',0,0,NULL,0,'1',1,'2011-12-20 01:37:22','_task id','1:_task id:prop',NULL,NULL),(10545,80,1,'',0,0,NULL,0,'',1,'2011-12-20 01:37:22','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10546,80,1,'Running',0,0,NULL,0,'',1,'2011-12-20 01:37:22','_status',':_status:prop',NULL,NULL),(10547,80,6,'',0,0,'2011-12-20 01:37:20',0,'',1,'2011-12-20 01:37:22','_start_time',':_start_time:prop',NULL,NULL),(10548,80,1,'Running',0,0,NULL,0,'1',1,'2011-12-20 01:37:22','_status','1:_status:prop',NULL,NULL),(10549,80,6,'',0,0,'2011-12-30 01:37:20',0,'',1,'2011-12-20 01:37:22','_due date',':_due date:prop',NULL,NULL),(10550,80,2,'',0,0,NULL,0,'',1,'2011-12-20 01:37:22','currentStep',':currentStep:prop',NULL,NULL),(10551,80,1,'1',0,0,NULL,0,'',1,'2011-12-20 01:37:22','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10552,80,6,'',0,0,'2011-12-20 01:37:20',0,'1',1,'2011-12-20 01:37:22','_start_time','1:_start_time:prop',NULL,NULL),(10553,80,6,'',0,0,'2011-12-25 01:37:20',0,'1',1,'2011-12-20 01:37:22','_due date','1:_due date:prop',NULL,NULL),(10554,81,1,'70',0,0,NULL,0,'1',1,'2011-12-20 01:38:17','_task id','1:_task id:prop',NULL,NULL),(10555,81,1,'',0,0,NULL,0,'',1,'2011-12-20 01:38:17','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10556,81,1,'Running',0,0,NULL,0,'',1,'2011-12-20 01:38:17','_status',':_status:prop',NULL,NULL),(10557,81,6,'',0,0,'2011-12-20 01:38:16',0,'',1,'2011-12-20 01:38:17','_start_time',':_start_time:prop',NULL,NULL),(10558,81,1,'Running',0,0,NULL,0,'1',1,'2011-12-20 01:38:17','_status','1:_status:prop',NULL,NULL),(10559,81,6,'',0,0,'2011-12-30 01:38:16',0,'',1,'2011-12-20 01:38:17','_due date',':_due date:prop',NULL,NULL),(10560,81,2,'',0,0,NULL,0,'',1,'2011-12-20 01:38:17','currentStep',':currentStep:prop',NULL,NULL),(10561,81,1,'1',0,0,NULL,0,'',1,'2011-12-20 01:38:17','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10562,81,6,'',0,0,'2011-12-20 01:38:16',0,'1',1,'2011-12-20 01:38:17','_start_time','1:_start_time:prop',NULL,NULL),(10563,81,6,'',0,0,'2011-12-25 01:38:16',0,'1',1,'2011-12-20 01:38:17','_due date','1:_due date:prop',NULL,NULL),(10564,82,1,'71',0,0,NULL,0,'1',1,'2011-12-20 01:54:22','_task id','1:_task id:prop',NULL,NULL),(10565,82,1,'',0,0,NULL,0,'',1,'2011-12-20 01:54:22','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10567,82,6,'',0,0,'2011-12-20 01:54:20',0,'',1,'2011-12-20 01:54:22','_start_time',':_start_time:prop',NULL,NULL),(10569,82,6,'',0,0,'2011-12-30 01:54:20',0,'',1,'2011-12-20 01:54:22','_due date',':_due date:prop',NULL,NULL),(10570,82,2,'',0,0,NULL,0,'',1,'2011-12-20 01:54:22','currentStep',':currentStep:prop',NULL,NULL),(10571,82,1,'1',0,0,NULL,0,'',1,'2011-12-20 01:54:22','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10572,82,6,'',0,0,'2011-12-20 01:54:20',0,'1',1,'2011-12-20 01:54:22','_start_time','1:_start_time:prop',NULL,NULL),(10573,82,6,'',0,0,'2011-12-25 01:54:20',0,'1',1,'2011-12-20 01:54:22','_due date','1:_due date:prop',NULL,NULL),(10574,83,1,'72',0,0,NULL,0,'1',1,'2011-12-20 01:55:07','_task id','1:_task id:prop',NULL,NULL),(10575,83,1,'',0,0,NULL,0,'',1,'2011-12-20 01:55:07','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10576,83,1,'Running',0,0,NULL,0,'',1,'2011-12-20 01:55:07','_status',':_status:prop',NULL,NULL),(10577,83,6,'',0,0,'2011-12-20 01:55:06',0,'',1,'2011-12-20 01:55:07','_start_time',':_start_time:prop',NULL,NULL),(10578,83,1,'Running',0,0,NULL,0,'1',1,'2011-12-20 01:55:07','_status','1:_status:prop',NULL,NULL),(10579,83,6,'',0,0,'2011-12-30 01:55:06',0,'',1,'2011-12-20 01:55:07','_due date',':_due date:prop',NULL,NULL),(10580,83,2,'',0,0,NULL,0,'',1,'2011-12-20 01:55:07','currentStep',':currentStep:prop',NULL,NULL),(10581,83,1,'1',0,0,NULL,0,'',1,'2011-12-20 01:55:07','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10582,83,6,'',0,0,'2011-12-20 01:55:06',0,'1',1,'2011-12-20 01:55:07','_start_time','1:_start_time:prop',NULL,NULL),(10583,83,6,'',0,0,'2011-12-25 01:55:06',0,'1',1,'2011-12-20 01:55:07','_due date','1:_due date:prop',NULL,NULL),(10584,84,1,'73',0,0,NULL,0,'1',1,'2011-12-20 03:29:15','_task id','1:_task id:prop',NULL,NULL),(10585,84,1,'',0,0,NULL,0,'',1,'2011-12-20 03:29:15','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10586,84,1,'Running',0,0,NULL,0,'',1,'2011-12-20 03:29:15','_status',':_status:prop',NULL,NULL),(10587,84,6,'',0,0,'2011-12-20 03:29:11',0,'',1,'2011-12-20 03:29:15','_start_time',':_start_time:prop',NULL,NULL),(10588,84,1,'Running',0,0,NULL,0,'1',1,'2011-12-20 03:29:15','_status','1:_status:prop',NULL,NULL),(10589,84,6,'',0,0,'2011-12-30 03:29:11',0,'',1,'2011-12-20 03:29:15','_due date',':_due date:prop',NULL,NULL),(10590,84,2,'',0,0,NULL,0,'',1,'2011-12-20 03:29:15','currentStep',':currentStep:prop',NULL,NULL),(10591,84,1,'1',0,0,NULL,0,'',1,'2011-12-20 03:29:15','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10592,84,6,'',0,0,'2011-12-20 03:29:11',0,'1',1,'2011-12-20 03:29:15','_start_time','1:_start_time:prop',NULL,NULL),(10593,84,6,'',0,0,'2011-12-25 03:29:11',0,'1',1,'2011-12-20 03:29:15','_due date','1:_due date:prop',NULL,NULL),(10594,82,1,'Stopped',0,0,NULL,0,'1',1,'2011-12-20 04:11:48','_status','1:_status:prop',NULL,NULL),(10595,82,1,'Stopped',0,0,NULL,0,'',1,'2011-12-20 04:11:48','_status',':_status:prop',NULL,NULL),(10596,85,1,'74',0,0,NULL,0,'1',1,'2011-12-21 01:28:09','_task id','1:_task id:prop',NULL,NULL),(10597,85,1,'',0,0,NULL,0,'',1,'2011-12-21 01:28:09','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10598,85,1,'Running',0,0,NULL,0,'',1,'2011-12-21 01:28:09','_status',':_status:prop',NULL,NULL),(10599,85,6,'',0,0,'2011-12-21 01:28:06',0,'',1,'2011-12-21 01:28:09','_start_time',':_start_time:prop',NULL,NULL),(10600,85,1,'Running',0,0,NULL,0,'1',1,'2011-12-21 01:28:09','_status','1:_status:prop',NULL,NULL),(10601,85,6,'',0,0,'2011-12-31 01:28:06',0,'',1,'2011-12-21 01:28:09','_due date',':_due date:prop',NULL,NULL),(10602,85,2,'',0,0,NULL,0,'',1,'2011-12-21 01:28:09','currentStep',':currentStep:prop',NULL,NULL),(10603,85,1,'1',0,0,NULL,0,'',1,'2011-12-21 01:28:09','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10604,85,6,'',0,0,'2011-12-21 01:28:06',0,'1',1,'2011-12-21 01:28:09','_start_time','1:_start_time:prop',NULL,NULL),(10605,85,6,'',0,0,'2011-12-26 01:28:06',0,'1',1,'2011-12-21 01:28:09','_due date','1:_due date:prop',NULL,NULL),(10606,86,1,'75',0,0,NULL,0,'1',1,'2011-12-21 14:02:34','_task id','1:_task id:prop',NULL,NULL),(10607,86,1,'',0,0,NULL,0,'',1,'2011-12-21 14:02:34','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10608,86,1,'Running',0,0,NULL,0,'',1,'2011-12-21 14:02:34','_status',':_status:prop',NULL,NULL),(10609,86,6,'',0,0,'2011-12-21 14:02:32',0,'',1,'2011-12-21 14:02:34','_start_time',':_start_time:prop',NULL,NULL),(10610,86,1,'Running',0,0,NULL,0,'1',1,'2011-12-21 14:02:34','_status','1:_status:prop',NULL,NULL),(10611,86,6,'',0,0,'2011-12-31 14:02:32',0,'',1,'2011-12-21 14:02:34','_due date',':_due date:prop',NULL,NULL),(10612,86,2,'',0,0,NULL,0,'',1,'2011-12-21 14:02:34','currentStep',':currentStep:prop',NULL,NULL),(10613,86,1,'1',0,0,NULL,0,'',1,'2011-12-21 14:02:34','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10614,86,6,'',0,0,'2011-12-21 14:02:32',0,'1',1,'2011-12-21 14:02:34','_start_time','1:_start_time:prop',NULL,NULL),(10615,86,6,'',0,0,'2011-12-26 14:02:32',0,'1',1,'2011-12-21 14:02:34','_due date','1:_due date:prop',NULL,NULL),(10616,87,1,'83',0,0,NULL,0,'1',1,'2011-12-22 21:44:20','_task id','1:_task id:prop',NULL,NULL),(10617,87,1,'',0,0,NULL,0,'',1,'2011-12-22 21:44:20','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10618,87,1,'Running',0,0,NULL,0,'',1,'2011-12-22 21:44:20','_status',':_status:prop',NULL,NULL),(10619,87,6,'',0,0,'2011-12-22 21:44:17',0,'',1,'2011-12-22 21:44:20','_start_time',':_start_time:prop',NULL,NULL),(10620,87,1,'Running',0,0,NULL,0,'1',1,'2011-12-22 21:44:20','_status','1:_status:prop',NULL,NULL),(10621,87,6,'',0,0,'2012-01-01 21:44:17',0,'',1,'2011-12-22 21:44:20','_due date',':_due date:prop',NULL,NULL),(10622,87,2,'',0,0,NULL,0,'',1,'2011-12-22 21:44:20','currentStep',':currentStep:prop',NULL,NULL),(10623,87,1,'1',0,0,NULL,0,'',1,'2011-12-22 21:44:20','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10624,87,6,'',0,0,'2011-12-22 21:44:17',0,'1',1,'2011-12-22 21:44:20','_start_time','1:_start_time:prop',NULL,NULL),(10625,87,6,'',0,0,'2011-12-27 21:44:17',0,'1',1,'2011-12-22 21:44:20','_due date','1:_due date:prop',NULL,NULL),(10626,88,1,'84',0,0,NULL,0,'1',1,'2011-12-22 21:44:25','_task id','1:_task id:prop',NULL,NULL),(10627,88,1,'',0,0,NULL,0,'',1,'2011-12-22 21:44:25','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10628,88,1,'Running',0,0,NULL,0,'',1,'2011-12-22 21:44:25','_status',':_status:prop',NULL,NULL),(10629,88,6,'',0,0,'2011-12-22 21:44:24',0,'',1,'2011-12-22 21:44:25','_start_time',':_start_time:prop',NULL,NULL),(10630,88,1,'Running',0,0,NULL,0,'1',1,'2011-12-22 21:44:25','_status','1:_status:prop',NULL,NULL),(10631,88,6,'',0,0,'2012-01-01 21:44:24',0,'',1,'2011-12-22 21:44:25','_due date',':_due date:prop',NULL,NULL),(10632,88,2,'',0,0,NULL,0,'',1,'2011-12-22 21:44:25','currentStep',':currentStep:prop',NULL,NULL),(10633,88,1,'1',0,0,NULL,0,'',1,'2011-12-22 21:44:25','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10634,88,6,'',0,0,'2011-12-22 21:44:24',0,'1',1,'2011-12-22 21:44:25','_start_time','1:_start_time:prop',NULL,NULL),(10635,88,6,'',0,0,'2011-12-27 21:44:24',0,'1',1,'2011-12-22 21:44:25','_due date','1:_due date:prop',NULL,NULL),(10636,89,6,'',0,0,'2011-12-22 23:47:41',0,'1',1,'2011-12-22 23:47:41','_end_time','1:_end_time:prop',NULL,NULL),(10637,89,1,'',0,0,NULL,0,'',1,'2011-12-22 23:47:41','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10638,89,1,'Completed',0,0,NULL,0,'',1,'2011-12-22 23:47:41','_status',':_status:prop',NULL,NULL),(10639,89,6,'',0,0,'2011-12-22 23:47:41',0,'',1,'2011-12-22 23:47:41','_start_time',':_start_time:prop',NULL,NULL),(10640,89,2,'',0,0,NULL,0,'1',1,'2011-12-22 23:47:41','currentStep','1:currentStep:prop',NULL,NULL),(10641,89,1,'Completed',0,0,NULL,0,'1',1,'2011-12-22 23:47:41','_status','1:_status:prop',NULL,NULL),(10642,89,6,'',0,0,'2011-12-22 23:47:41',0,'',1,'2011-12-22 23:47:41','_end_time',':_end_time:prop',NULL,NULL),(10643,89,6,'',0,0,'2012-01-01 23:47:41',0,'',1,'2011-12-22 23:47:41','_due date',':_due date:prop',NULL,NULL),(10644,89,2,'',1,0,NULL,0,'',1,'2011-12-22 23:47:41','currentStep',':currentStep:prop',NULL,NULL),(10645,89,6,'',0,0,'2011-12-22 23:47:41',0,'1',1,'2011-12-22 23:47:41','_start_time','1:_start_time:prop',NULL,NULL),(10646,90,1,'85',0,0,NULL,0,'1',1,'2011-12-22 23:48:01','_task id','1:_task id:prop',NULL,NULL),(10647,90,2,'',0,0,NULL,0,'3',1,'2011-12-22 23:48:01','currentStep','3:currentStep:prop',NULL,NULL),(10648,90,2,'',0,0,NULL,0,'6',1,'2011-12-22 23:48:01','currentStep','6:currentStep:prop',NULL,NULL),(10649,90,1,'',0,0,NULL,0,'',1,'2011-12-22 23:48:01','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10650,90,1,'Running',0,0,NULL,0,'',1,'2011-12-22 23:48:01','_status',':_status:prop',NULL,NULL),(10651,90,2,'',0,0,NULL,0,'11',1,'2011-12-22 23:48:01','currentStep','11:currentStep:prop',NULL,NULL),(10652,90,6,'',0,0,'2011-12-22 23:47:59',0,'',1,'2011-12-22 23:48:01','_start_time',':_start_time:prop',NULL,NULL),(10653,90,1,'Running',0,0,NULL,0,'1',1,'2011-12-22 23:48:01','_status','1:_status:prop',NULL,NULL),(10654,90,6,'',0,0,'2012-01-01 23:47:59',0,'',1,'2011-12-22 23:48:01','_due date',':_due date:prop',NULL,NULL),(10655,90,2,'',0,0,NULL,0,'8',1,'2011-12-22 23:48:01','currentStep','8:currentStep:prop',NULL,NULL),(10656,90,2,'',0,0,NULL,0,'',1,'2011-12-22 23:48:01','currentStep',':currentStep:prop',NULL,NULL),(10657,90,1,'1',0,0,NULL,0,'',1,'2011-12-22 23:48:01','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10658,90,6,'',0,0,'2011-12-27 23:47:59',0,'1',1,'2011-12-22 23:48:01','_due date','1:_due date:prop',NULL,NULL),(10659,90,6,'',0,0,'2011-12-22 23:47:59',0,'1',1,'2011-12-22 23:48:01','_start_time','1:_start_time:prop',NULL,NULL),(10660,91,1,'93',0,0,NULL,0,'1',1,'2011-12-23 01:35:56','_task id','1:_task id:prop',NULL,NULL),(10661,91,1,'',0,0,NULL,0,'',1,'2011-12-23 01:35:56','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10662,91,1,'Running',0,0,NULL,0,'',1,'2011-12-23 01:35:56','_status',':_status:prop',NULL,NULL),(10663,91,6,'',0,0,'2011-12-23 01:35:53',0,'',1,'2011-12-23 01:35:56','_start_time',':_start_time:prop',NULL,NULL),(10664,91,1,'Running',0,0,NULL,0,'1',1,'2011-12-23 01:35:56','_status','1:_status:prop',NULL,NULL),(10665,91,6,'',0,0,'2012-01-02 01:35:53',0,'',1,'2011-12-23 01:35:56','_due date',':_due date:prop',NULL,NULL),(10666,91,2,'',0,0,NULL,0,'',1,'2011-12-23 01:35:56','currentStep',':currentStep:prop',NULL,NULL),(10667,91,1,'1',0,0,NULL,0,'',1,'2011-12-23 01:35:56','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10668,91,6,'',0,0,'2011-12-23 01:35:53',0,'1',1,'2011-12-23 01:35:56','_start_time','1:_start_time:prop',NULL,NULL),(10669,91,6,'',0,0,'2011-12-28 01:35:53',0,'1',1,'2011-12-23 01:35:56','_due date','1:_due date:prop',NULL,NULL),(10670,92,1,'94',0,0,NULL,0,'1',1,'2011-12-23 15:59:23','_task id','1:_task id:prop',NULL,NULL),(10671,92,1,'',0,0,NULL,0,'',1,'2011-12-23 15:59:23','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10672,92,1,'Running',0,0,NULL,0,'',1,'2011-12-23 15:59:23','_status',':_status:prop',NULL,NULL),(10673,92,6,'',0,0,'2011-12-23 15:59:19',0,'',1,'2011-12-23 15:59:23','_start_time',':_start_time:prop',NULL,NULL),(10674,92,1,'Running',0,0,NULL,0,'1',1,'2011-12-23 15:59:23','_status','1:_status:prop',NULL,NULL),(10675,92,6,'',0,0,'2012-01-02 15:59:19',0,'',1,'2011-12-23 15:59:23','_due date',':_due date:prop',NULL,NULL),(10676,92,2,'',0,0,NULL,0,'',1,'2011-12-23 15:59:23','currentStep',':currentStep:prop',NULL,NULL),(10677,92,1,'1',0,0,NULL,0,'',1,'2011-12-23 15:59:23','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10678,92,6,'',0,0,'2011-12-23 15:59:19',0,'1',1,'2011-12-23 15:59:23','_start_time','1:_start_time:prop',NULL,NULL),(10679,92,6,'',0,0,'2011-12-28 15:59:19',0,'1',1,'2011-12-23 15:59:23','_due date','1:_due date:prop',NULL,NULL),(10680,93,1,'96',0,0,NULL,0,'1',1,'2011-12-29 01:47:24','_task id','1:_task id:prop',NULL,NULL),(10681,93,1,'',0,0,NULL,0,'',1,'2011-12-29 01:47:24','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10683,93,6,'',0,0,'2011-12-29 01:47:22',0,'',1,'2011-12-29 01:47:25','_start_time',':_start_time:prop',NULL,NULL),(10685,93,6,'',0,0,'2012-01-08 01:47:22',0,'',1,'2011-12-29 01:47:25','_due date',':_due date:prop',NULL,NULL),(10687,93,1,'1',0,0,NULL,0,'',1,'2011-12-29 01:47:25','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10688,93,6,'',0,0,'2011-12-29 01:47:22',0,'1',1,'2011-12-29 01:47:25','_start_time','1:_start_time:prop',NULL,NULL),(10689,93,6,'',0,0,'2012-01-03 01:47:22',0,'1',1,'2011-12-29 01:47:25','_due date','1:_due date:prop',NULL,NULL),(10690,93,2,'',1,0,NULL,0,'',1,'2011-12-29 10:46:43','currentStep',':currentStep:prop',NULL,NULL),(10691,93,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <resourceName>장진영</resourceName>\n  <endpoint>1401720840</endpoint>\n  <assignType>0</assignType>\n  <dispatchingOption>-1</dispatchingOption>\n  <isMale>true</isMale>\n  <isGroup>false</isGroup>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2011-12-29 10:46:43','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(10692,93,9,'<org.uengine.contexts.HtmlFormContext>\n  <formDefId>[form3]@27</formDefId>\n  <filePath>2011/12/29/93_2011122910464035.xml</filePath>\n  <htmlPath>/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/93/96.html</htmlPath>\n</org.uengine.contexts.HtmlFormContext>',0,0,NULL,0,'',0,'2011-12-29 10:46:43','var1',':var1',NULL,'/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/93/96.html'),(10693,93,6,'',0,0,'2011-12-29 10:46:42',0,'1',1,'2011-12-29 10:46:43','_end_time','1:_end_time:prop',NULL,NULL),(10694,93,1,'Completed',0,0,NULL,0,'1',1,'2011-12-29 10:46:43','_status','1:_status:prop',NULL,NULL),(10695,93,1,'Completed',0,0,NULL,0,'',1,'2011-12-29 10:46:43','_status',':_status:prop',NULL,NULL),(10696,93,6,'',0,0,'2011-12-29 10:46:42',0,'',1,'2011-12-29 10:46:43','_end_time',':_end_time:prop',NULL,NULL),(10697,94,1,'97',0,0,NULL,0,'1',1,'2011-12-29 11:20:40','_task id','1:_task id:prop',NULL,NULL),(10698,94,1,'',0,0,NULL,0,'',1,'2011-12-29 11:20:40','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10700,94,6,'',0,0,'2011-12-29 11:20:37',0,'',1,'2011-12-29 11:20:40','_start_time',':_start_time:prop',NULL,NULL),(10702,94,6,'',0,0,'2012-01-08 11:20:37',0,'',1,'2011-12-29 11:20:40','_due date',':_due date:prop',NULL,NULL),(10704,94,1,'1',0,0,NULL,0,'',1,'2011-12-29 11:20:40','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10705,94,6,'',0,0,'2011-12-29 11:20:37',0,'1',1,'2011-12-29 11:20:40','_start_time','1:_start_time:prop',NULL,NULL),(10706,94,6,'',0,0,'2012-01-03 11:20:38',0,'1',1,'2011-12-29 11:20:40','_due date','1:_due date:prop',NULL,NULL),(10707,94,2,'',1,0,NULL,0,'',1,'2011-12-29 11:20:58','currentStep',':currentStep:prop',NULL,NULL),(10708,94,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <resourceName>장진영</resourceName>\n  <endpoint>1401720840</endpoint>\n  <assignType>0</assignType>\n  <dispatchingOption>-1</dispatchingOption>\n  <isMale>true</isMale>\n  <isGroup>false</isGroup>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2011-12-29 11:20:58','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(10709,94,9,'<org.uengine.contexts.HtmlFormContext>\n  <formDefId>[form3]@27</formDefId>\n  <filePath>2011/12/29/94_20111229112058110.xml</filePath>\n  <htmlPath>/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/94/97.html</htmlPath>\n</org.uengine.contexts.HtmlFormContext>',0,0,NULL,0,'',0,'2011-12-29 11:20:58','var1',':var1',NULL,'/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/94/97.html'),(10710,94,6,'',0,0,'2011-12-29 11:20:58',0,'1',1,'2011-12-29 11:20:58','_end_time','1:_end_time:prop',NULL,NULL),(10711,94,1,'Completed',0,0,NULL,0,'1',1,'2011-12-29 11:20:58','_status','1:_status:prop',NULL,NULL),(10712,94,1,'Completed',0,0,NULL,0,'',1,'2011-12-29 11:20:58','_status',':_status:prop',NULL,NULL),(10713,94,6,'',0,0,'2011-12-29 11:20:58',0,'',1,'2011-12-29 11:20:58','_end_time',':_end_time:prop',NULL,NULL),(10714,95,1,'98',0,0,NULL,0,'1',1,'2011-12-29 13:25:12','_task id','1:_task id:prop',NULL,NULL),(10715,95,1,'',0,0,NULL,0,'',1,'2011-12-29 13:25:12','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10717,95,6,'',0,0,'2011-12-29 13:25:09',0,'',1,'2011-12-29 13:25:12','_start_time',':_start_time:prop',NULL,NULL),(10719,95,6,'',0,0,'2012-01-08 13:25:09',0,'',1,'2011-12-29 13:25:12','_due date',':_due date:prop',NULL,NULL),(10721,95,1,'1',0,0,NULL,0,'',1,'2011-12-29 13:25:12','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10722,95,6,'',0,0,'2011-12-29 13:25:09',0,'1',1,'2011-12-29 13:25:12','_start_time','1:_start_time:prop',NULL,NULL),(10723,95,6,'',0,0,'2012-01-03 13:25:09',0,'1',1,'2011-12-29 13:25:12','_due date','1:_due date:prop',NULL,NULL),(10724,95,2,'',1,0,NULL,0,'',1,'2011-12-29 13:25:22','currentStep',':currentStep:prop',NULL,NULL),(10725,95,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <resourceName>장진영</resourceName>\n  <endpoint>1401720840</endpoint>\n  <assignType>0</assignType>\n  <dispatchingOption>-1</dispatchingOption>\n  <isMale>true</isMale>\n  <isGroup>false</isGroup>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2011-12-29 13:25:22','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(10726,95,9,'<org.uengine.contexts.HtmlFormContext>\n  <formDefId>[form3]@27</formDefId>\n  <filePath>2011/12/29/95_20111229132522481.xml</filePath>\n  <htmlPath>/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/95/98.html</htmlPath>\n</org.uengine.contexts.HtmlFormContext>',0,0,NULL,0,'',0,'2011-12-29 13:25:22','var1',':var1',NULL,'/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/95/98.html'),(10727,95,6,'',0,0,'2011-12-29 13:25:22',0,'1',1,'2011-12-29 13:25:22','_end_time','1:_end_time:prop',NULL,NULL),(10728,95,1,'Completed',0,0,NULL,0,'1',1,'2011-12-29 13:25:22','_status','1:_status:prop',NULL,NULL),(10729,95,1,'Completed',0,0,NULL,0,'',1,'2011-12-29 13:25:22','_status',':_status:prop',NULL,NULL),(10730,95,6,'',0,0,'2011-12-29 13:25:22',0,'',1,'2011-12-29 13:25:22','_end_time',':_end_time:prop',NULL,NULL),(10731,96,1,'99',0,0,NULL,0,'1',1,'2011-12-29 13:26:45','_task id','1:_task id:prop',NULL,NULL),(10732,96,1,'',0,0,NULL,0,'',1,'2011-12-29 13:26:45','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10734,96,6,'',0,0,'2011-12-29 13:26:43',0,'',1,'2011-12-29 13:26:45','_start_time',':_start_time:prop',NULL,NULL),(10736,96,6,'',0,0,'2012-01-08 13:26:43',0,'',1,'2011-12-29 13:26:45','_due date',':_due date:prop',NULL,NULL),(10738,96,1,'1',0,0,NULL,0,'',1,'2011-12-29 13:26:45','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10739,96,6,'',0,0,'2011-12-29 13:26:43',0,'1',1,'2011-12-29 13:26:45','_start_time','1:_start_time:prop',NULL,NULL),(10740,96,6,'',0,0,'2012-01-03 13:26:43',0,'1',1,'2011-12-29 13:26:45','_due date','1:_due date:prop',NULL,NULL),(10741,96,2,'',1,0,NULL,0,'',1,'2011-12-29 13:27:29','currentStep',':currentStep:prop',NULL,NULL),(10742,96,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <resourceName>장진영</resourceName>\n  <endpoint>1401720840</endpoint>\n  <assignType>0</assignType>\n  <dispatchingOption>-1</dispatchingOption>\n  <isMale>true</isMale>\n  <isGroup>false</isGroup>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2011-12-29 13:27:29','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(10743,96,9,'<org.uengine.contexts.HtmlFormContext>\n  <formDefId>[form3]@27</formDefId>\n  <filePath>2011/12/29/96_20111229132728622.xml</filePath>\n  <htmlPath>/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/96/99.html</htmlPath>\n</org.uengine.contexts.HtmlFormContext>',0,0,NULL,0,'',0,'2011-12-29 13:27:29','var1',':var1',NULL,'/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/96/99.html'),(10744,96,6,'',0,0,'2011-12-29 13:27:28',0,'1',1,'2011-12-29 13:27:29','_end_time','1:_end_time:prop',NULL,NULL),(10745,96,1,'Completed',0,0,NULL,0,'1',1,'2011-12-29 13:27:29','_status','1:_status:prop',NULL,NULL),(10746,96,1,'Completed',0,0,NULL,0,'',1,'2011-12-29 13:27:29','_status',':_status:prop',NULL,NULL),(10747,96,6,'',0,0,'2011-12-29 13:27:28',0,'',1,'2011-12-29 13:27:29','_end_time',':_end_time:prop',NULL,NULL),(10748,97,1,'100',0,0,NULL,0,'1',1,'2011-12-29 13:34:38','_task id','1:_task id:prop',NULL,NULL),(10749,97,1,'',0,0,NULL,0,'',1,'2011-12-29 13:34:38','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10751,97,6,'',0,0,'2011-12-29 13:34:34',0,'',1,'2011-12-29 13:34:38','_start_time',':_start_time:prop',NULL,NULL),(10753,97,6,'',0,0,'2012-01-08 13:34:34',0,'',1,'2011-12-29 13:34:38','_due date',':_due date:prop',NULL,NULL),(10755,97,1,'1',0,0,NULL,0,'',1,'2011-12-29 13:34:38','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10756,97,6,'',0,0,'2011-12-29 13:34:34',0,'1',1,'2011-12-29 13:34:38','_start_time','1:_start_time:prop',NULL,NULL),(10757,97,6,'',0,0,'2012-01-03 13:34:34',0,'1',1,'2011-12-29 13:34:38','_due date','1:_due date:prop',NULL,NULL),(10758,97,2,'',1,0,NULL,0,'',1,'2011-12-29 13:34:53','currentStep',':currentStep:prop',NULL,NULL),(10759,97,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <resourceName>장진영</resourceName>\n  <endpoint>1401720840</endpoint>\n  <assignType>0</assignType>\n  <dispatchingOption>-1</dispatchingOption>\n  <isMale>true</isMale>\n  <isGroup>false</isGroup>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2011-12-29 13:34:53','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(10760,97,9,'<org.uengine.contexts.HtmlFormContext>\n  <formDefId>[form3]@27</formDefId>\n  <filePath>2011/12/29/97_20111229133452792.xml</filePath>\n  <htmlPath>/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/97/100.html</htmlPath>\n</org.uengine.contexts.HtmlFormContext>',0,0,NULL,0,'',0,'2011-12-29 13:34:53','var1',':var1',NULL,'/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/97/100.html'),(10761,97,6,'',0,0,'2011-12-29 13:34:53',0,'1',1,'2011-12-29 13:34:53','_end_time','1:_end_time:prop',NULL,NULL),(10762,97,1,'Completed',0,0,NULL,0,'1',1,'2011-12-29 13:34:53','_status','1:_status:prop',NULL,NULL),(10763,97,1,'Completed',0,0,NULL,0,'',1,'2011-12-29 13:34:53','_status',':_status:prop',NULL,NULL),(10764,97,6,'',0,0,'2011-12-29 13:34:53',0,'',1,'2011-12-29 13:34:53','_end_time',':_end_time:prop',NULL,NULL),(10765,98,1,'101',0,0,NULL,0,'1',1,'2011-12-29 13:36:41','_task id','1:_task id:prop',NULL,NULL),(10766,98,1,'',0,0,NULL,0,'',1,'2011-12-29 13:36:41','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10768,98,6,'',0,0,'2011-12-29 13:36:39',0,'',1,'2011-12-29 13:36:41','_start_time',':_start_time:prop',NULL,NULL),(10770,98,6,'',0,0,'2012-01-08 13:36:39',0,'',1,'2011-12-29 13:36:41','_due date',':_due date:prop',NULL,NULL),(10772,98,1,'1',0,0,NULL,0,'',1,'2011-12-29 13:36:41','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10773,98,6,'',0,0,'2011-12-29 13:36:39',0,'1',1,'2011-12-29 13:36:41','_start_time','1:_start_time:prop',NULL,NULL),(10774,98,6,'',0,0,'2012-01-03 13:36:39',0,'1',1,'2011-12-29 13:36:41','_due date','1:_due date:prop',NULL,NULL),(10775,98,2,'',1,0,NULL,0,'',1,'2011-12-29 13:37:35','currentStep',':currentStep:prop',NULL,NULL),(10776,98,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <resourceName>장진영</resourceName>\n  <endpoint>1401720840</endpoint>\n  <assignType>0</assignType>\n  <dispatchingOption>-1</dispatchingOption>\n  <isMale>true</isMale>\n  <isGroup>false</isGroup>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2011-12-29 13:37:35','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(10777,98,9,'<org.uengine.contexts.HtmlFormContext>\n  <formDefId>[form3]@27</formDefId>\n  <filePath>2011/12/29/98_20111229133734783.xml</filePath>\n  <htmlPath>/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/98/101.html</htmlPath>\n</org.uengine.contexts.HtmlFormContext>',0,0,NULL,0,'',0,'2011-12-29 13:37:35','var1',':var1',NULL,'/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/98/101.html'),(10778,98,6,'',0,0,'2011-12-29 13:37:34',0,'1',1,'2011-12-29 13:37:35','_end_time','1:_end_time:prop',NULL,NULL),(10779,98,1,'Completed',0,0,NULL,0,'1',1,'2011-12-29 13:37:35','_status','1:_status:prop',NULL,NULL),(10780,98,1,'Completed',0,0,NULL,0,'',1,'2011-12-29 13:37:35','_status',':_status:prop',NULL,NULL),(10781,98,6,'',0,0,'2011-12-29 13:37:35',0,'',1,'2011-12-29 13:37:35','_end_time',':_end_time:prop',NULL,NULL),(10782,99,1,'102',0,0,NULL,0,'1',1,'2011-12-29 13:41:50','_task id','1:_task id:prop',NULL,NULL),(10783,99,1,'',0,0,NULL,0,'',1,'2011-12-29 13:41:50','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10784,99,1,'Running',0,0,NULL,0,'',1,'2011-12-29 13:41:50','_status',':_status:prop',NULL,NULL),(10785,99,6,'',0,0,'2011-12-29 13:41:48',0,'',1,'2011-12-29 13:41:50','_start_time',':_start_time:prop',NULL,NULL),(10786,99,1,'Running',0,0,NULL,0,'1',1,'2011-12-29 13:41:50','_status','1:_status:prop',NULL,NULL),(10787,99,6,'',0,0,'2012-01-08 13:41:48',0,'',1,'2011-12-29 13:41:50','_due date',':_due date:prop',NULL,NULL),(10788,99,2,'',0,0,NULL,0,'',1,'2011-12-29 13:41:50','currentStep',':currentStep:prop',NULL,NULL),(10789,99,1,'1',0,0,NULL,0,'',1,'2011-12-29 13:41:50','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10790,99,6,'',0,0,'2011-12-29 13:41:48',0,'1',1,'2011-12-29 13:41:50','_start_time','1:_start_time:prop',NULL,NULL),(10791,99,6,'',0,0,'2012-01-03 13:41:48',0,'1',1,'2011-12-29 13:41:50','_due date','1:_due date:prop',NULL,NULL),(10792,100,1,'103',0,0,NULL,0,'1',1,'2011-12-29 13:44:06','_task id','1:_task id:prop',NULL,NULL),(10793,100,1,'',0,0,NULL,0,'',1,'2011-12-29 13:44:06','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10795,100,6,'',0,0,'2011-12-29 13:44:02',0,'',1,'2011-12-29 13:44:06','_start_time',':_start_time:prop',NULL,NULL),(10797,100,6,'',0,0,'2012-01-08 13:44:02',0,'',1,'2011-12-29 13:44:06','_due date',':_due date:prop',NULL,NULL),(10799,100,1,'1',0,0,NULL,0,'',1,'2011-12-29 13:44:06','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10800,100,6,'',0,0,'2011-12-29 13:44:02',0,'1',1,'2011-12-29 13:44:06','_start_time','1:_start_time:prop',NULL,NULL),(10801,100,6,'',0,0,'2012-01-03 13:44:02',0,'1',1,'2011-12-29 13:44:06','_due date','1:_due date:prop',NULL,NULL),(10802,100,2,'',1,0,NULL,0,'',1,'2011-12-29 13:44:51','currentStep',':currentStep:prop',NULL,NULL),(10803,100,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <resourceName>장진영</resourceName>\n  <endpoint>1401720840</endpoint>\n  <assignType>0</assignType>\n  <dispatchingOption>-1</dispatchingOption>\n  <isMale>true</isMale>\n  <isGroup>false</isGroup>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2011-12-29 13:44:51','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(10804,100,9,'<org.uengine.contexts.HtmlFormContext>\n  <formDefId>[form3]@27</formDefId>\n  <filePath>2011/12/29/100_20111229134450817.xml</filePath>\n  <htmlPath>/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/100/103.html</htmlPath>\n</org.uengine.contexts.HtmlFormContext>',0,0,NULL,0,'',0,'2011-12-29 13:44:51','var1',':var1',NULL,'/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/100/103.html'),(10805,100,6,'',0,0,'2011-12-29 13:44:51',0,'1',1,'2011-12-29 13:44:51','_end_time','1:_end_time:prop',NULL,NULL),(10806,100,1,'Completed',0,0,NULL,0,'1',1,'2011-12-29 13:44:51','_status','1:_status:prop',NULL,NULL),(10807,100,1,'Completed',0,0,NULL,0,'',1,'2011-12-29 13:44:51','_status',':_status:prop',NULL,NULL),(10808,100,6,'',0,0,'2011-12-29 13:44:51',0,'',1,'2011-12-29 13:44:51','_end_time',':_end_time:prop',NULL,NULL),(10809,101,1,'104',0,0,NULL,0,'1',1,'2011-12-29 13:51:38','_task id','1:_task id:prop',NULL,NULL),(10810,101,1,'',0,0,NULL,0,'',1,'2011-12-29 13:51:38','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10812,101,6,'',0,0,'2011-12-29 13:51:36',0,'',1,'2011-12-29 13:51:38','_start_time',':_start_time:prop',NULL,NULL),(10814,101,6,'',0,0,'2012-01-08 13:51:36',0,'',1,'2011-12-29 13:51:38','_due date',':_due date:prop',NULL,NULL),(10816,101,1,'1',0,0,NULL,0,'',1,'2011-12-29 13:51:38','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10817,101,6,'',0,0,'2011-12-29 13:51:36',0,'1',1,'2011-12-29 13:51:38','_start_time','1:_start_time:prop',NULL,NULL),(10818,101,6,'',0,0,'2012-01-03 13:51:36',0,'1',1,'2011-12-29 13:51:38','_due date','1:_due date:prop',NULL,NULL),(10819,101,2,'',1,0,NULL,0,'',1,'2011-12-29 13:52:34','currentStep',':currentStep:prop',NULL,NULL),(10820,101,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <resourceName>장진영</resourceName>\n  <endpoint>1401720840</endpoint>\n  <assignType>0</assignType>\n  <dispatchingOption>-1</dispatchingOption>\n  <isMale>true</isMale>\n  <isGroup>false</isGroup>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2011-12-29 13:52:34','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(10821,101,9,'<org.uengine.contexts.HtmlFormContext>\n  <formDefId>[form3]@27</formDefId>\n  <filePath>2011/12/29/101_20111229135233869.xml</filePath>\n  <htmlPath>/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/101/104.html</htmlPath>\n</org.uengine.contexts.HtmlFormContext>',0,0,NULL,0,'',0,'2011-12-29 13:52:34','var1',':var1',NULL,'/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/101/104.html'),(10822,101,6,'',0,0,'2011-12-29 13:52:34',0,'1',1,'2011-12-29 13:52:34','_end_time','1:_end_time:prop',NULL,NULL),(10823,101,1,'Completed',0,0,NULL,0,'1',1,'2011-12-29 13:52:34','_status','1:_status:prop',NULL,NULL),(10824,101,1,'Completed',0,0,NULL,0,'',1,'2011-12-29 13:52:34','_status',':_status:prop',NULL,NULL),(10825,101,6,'',0,0,'2011-12-29 13:52:34',0,'',1,'2011-12-29 13:52:34','_end_time',':_end_time:prop',NULL,NULL),(10826,102,1,'105',0,0,NULL,0,'1',1,'2011-12-29 13:53:01','_task id','1:_task id:prop',NULL,NULL),(10827,102,1,'',0,0,NULL,0,'',1,'2011-12-29 13:53:01','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10829,102,6,'',0,0,'2011-12-29 13:52:59',0,'',1,'2011-12-29 13:53:01','_start_time',':_start_time:prop',NULL,NULL),(10831,102,6,'',0,0,'2012-01-08 13:52:59',0,'',1,'2011-12-29 13:53:01','_due date',':_due date:prop',NULL,NULL),(10833,102,1,'1',0,0,NULL,0,'',1,'2011-12-29 13:53:01','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10834,102,6,'',0,0,'2011-12-29 13:52:59',0,'1',1,'2011-12-29 13:53:01','_start_time','1:_start_time:prop',NULL,NULL),(10835,102,6,'',0,0,'2012-01-03 13:52:59',0,'1',1,'2011-12-29 13:53:01','_due date','1:_due date:prop',NULL,NULL),(10836,102,2,'',1,0,NULL,0,'',1,'2011-12-29 13:55:37','currentStep',':currentStep:prop',NULL,NULL),(10837,102,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <resourceName>장진영</resourceName>\n  <endpoint>1401720840</endpoint>\n  <assignType>0</assignType>\n  <dispatchingOption>-1</dispatchingOption>\n  <isMale>true</isMale>\n  <isGroup>false</isGroup>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2011-12-29 13:55:37','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(10838,102,9,'<org.uengine.contexts.HtmlFormContext>\n  <formDefId>[form3]@27</formDefId>\n  <filePath>2011/12/29/102_2011122913553750.xml</filePath>\n  <htmlPath>/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/102/105.html</htmlPath>\n</org.uengine.contexts.HtmlFormContext>',0,0,NULL,0,'',0,'2011-12-29 13:55:37','var1',':var1',NULL,'/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/102/105.html'),(10839,102,6,'',0,0,'2011-12-29 13:55:37',0,'1',1,'2011-12-29 13:55:37','_end_time','1:_end_time:prop',NULL,NULL),(10840,102,1,'Completed',0,0,NULL,0,'1',1,'2011-12-29 13:55:37','_status','1:_status:prop',NULL,NULL),(10841,102,1,'Completed',0,0,NULL,0,'',1,'2011-12-29 13:55:37','_status',':_status:prop',NULL,NULL),(10842,102,6,'',0,0,'2011-12-29 13:55:37',0,'',1,'2011-12-29 13:55:37','_end_time',':_end_time:prop',NULL,NULL),(10843,103,1,'106',0,0,NULL,0,'1',1,'2011-12-29 13:58:04','_task id','1:_task id:prop',NULL,NULL),(10844,103,1,'',0,0,NULL,0,'',1,'2011-12-29 13:58:04','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10846,103,6,'',0,0,'2011-12-29 13:58:00',0,'',1,'2011-12-29 13:58:04','_start_time',':_start_time:prop',NULL,NULL),(10848,103,6,'',0,0,'2012-01-08 13:58:00',0,'',1,'2011-12-29 13:58:04','_due date',':_due date:prop',NULL,NULL),(10850,103,1,'1',0,0,NULL,0,'',1,'2011-12-29 13:58:04','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10851,103,6,'',0,0,'2011-12-29 13:58:00',0,'1',1,'2011-12-29 13:58:04','_start_time','1:_start_time:prop',NULL,NULL),(10852,103,6,'',0,0,'2012-01-03 13:58:00',0,'1',1,'2011-12-29 13:58:04','_due date','1:_due date:prop',NULL,NULL),(10853,103,2,'',1,0,NULL,0,'',1,'2011-12-29 13:59:31','currentStep',':currentStep:prop',NULL,NULL),(10854,103,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <resourceName>장진영</resourceName>\n  <endpoint>1401720840</endpoint>\n  <assignType>0</assignType>\n  <dispatchingOption>-1</dispatchingOption>\n  <isMale>true</isMale>\n  <isGroup>false</isGroup>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2011-12-29 13:59:31','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(10855,103,9,'<org.uengine.contexts.HtmlFormContext>\n  <formDefId>[form3]@27</formDefId>\n  <filePath>2011/12/29/103_20111229135930714.xml</filePath>\n  <htmlPath>/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/103/106.html</htmlPath>\n</org.uengine.contexts.HtmlFormContext>',0,0,NULL,0,'',0,'2011-12-29 13:59:31','var1',':var1',NULL,'/Users/jyjang/Downloads/codi-was/bin/uengine/definition/history/2011/103/106.html'),(10856,103,6,'',0,0,'2011-12-29 13:59:31',0,'1',1,'2011-12-29 13:59:31','_end_time','1:_end_time:prop',NULL,NULL),(10857,103,1,'Completed',0,0,NULL,0,'1',1,'2011-12-29 13:59:31','_status','1:_status:prop',NULL,NULL),(10858,103,1,'Completed',0,0,NULL,0,'',1,'2011-12-29 13:59:31','_status',':_status:prop',NULL,NULL),(10859,103,6,'',0,0,'2011-12-29 13:59:31',0,'',1,'2011-12-29 13:59:31','_end_time',':_end_time:prop',NULL,NULL),(10860,104,1,'109',0,0,NULL,0,'1',1,'2012-01-04 18:08:16','_task id','1:_task id:prop',NULL,NULL),(10861,104,1,'',0,0,NULL,0,'',1,'2012-01-04 18:08:16','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10862,104,1,'Running',0,0,NULL,0,'',1,'2012-01-04 18:08:16','_status',':_status:prop',NULL,NULL),(10863,104,6,'',0,0,'2012-01-04 18:08:13',0,'',1,'2012-01-04 18:08:16','_start_time',':_start_time:prop',NULL,NULL),(10864,104,1,'Running',0,0,NULL,0,'1',1,'2012-01-04 18:08:16','_status','1:_status:prop',NULL,NULL),(10865,104,6,'',0,0,'2012-01-14 18:08:13',0,'',1,'2012-01-04 18:08:16','_due date',':_due date:prop',NULL,NULL),(10866,104,2,'',0,0,NULL,0,'',1,'2012-01-04 18:08:16','currentStep',':currentStep:prop',NULL,NULL),(10867,104,1,'1',0,0,NULL,0,'',1,'2012-01-04 18:08:16','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10868,104,6,'',0,0,'2012-01-04 18:08:13',0,'1',1,'2012-01-04 18:08:16','_start_time','1:_start_time:prop',NULL,NULL),(10869,104,6,'',0,0,'2012-01-09 18:08:13',0,'1',1,'2012-01-04 18:08:16','_due date','1:_due date:prop',NULL,NULL),(10870,105,1,'113',0,0,NULL,0,'1',1,'2012-01-08 21:20:46','_task id','1:_task id:prop',NULL,NULL),(10871,105,1,'',0,0,NULL,0,'',1,'2012-01-08 21:20:46','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10872,105,1,'Running',0,0,NULL,0,'',1,'2012-01-08 21:20:46','_status',':_status:prop',NULL,NULL),(10873,105,6,'',0,0,'2012-01-08 21:20:39',0,'',1,'2012-01-08 21:20:46','_start_time',':_start_time:prop',NULL,NULL),(10874,105,1,'Running',0,0,NULL,0,'1',1,'2012-01-08 21:20:46','_status','1:_status:prop',NULL,NULL),(10875,105,6,'',0,0,'2012-01-18 21:20:39',0,'',1,'2012-01-08 21:20:46','_due date',':_due date:prop',NULL,NULL),(10876,105,2,'',0,0,NULL,0,'',1,'2012-01-08 21:20:46','currentStep',':currentStep:prop',NULL,NULL),(10877,105,1,'1',0,0,NULL,0,'',1,'2012-01-08 21:20:46','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10878,105,6,'',0,0,'2012-01-08 21:20:39',0,'1',1,'2012-01-08 21:20:46','_start_time','1:_start_time:prop',NULL,NULL),(10879,105,6,'',0,0,'2012-01-13 21:20:39',0,'1',1,'2012-01-08 21:20:46','_due date','1:_due date:prop',NULL,NULL),(10880,106,1,'114',0,0,NULL,0,'1',1,'2012-01-08 21:21:12','_task id','1:_task id:prop',NULL,NULL),(10881,106,1,'',0,0,NULL,0,'',1,'2012-01-08 21:21:12','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10882,106,1,'Running',0,0,NULL,0,'',1,'2012-01-08 21:21:12','_status',':_status:prop',NULL,NULL),(10883,106,6,'',0,0,'2012-01-08 21:21:10',0,'',1,'2012-01-08 21:21:12','_start_time',':_start_time:prop',NULL,NULL),(10884,106,1,'Running',0,0,NULL,0,'1',1,'2012-01-08 21:21:12','_status','1:_status:prop',NULL,NULL),(10885,106,6,'',0,0,'2012-01-18 21:21:10',0,'',1,'2012-01-08 21:21:12','_due date',':_due date:prop',NULL,NULL),(10886,106,2,'',0,0,NULL,0,'',1,'2012-01-08 21:21:12','currentStep',':currentStep:prop',NULL,NULL),(10887,106,1,'1',0,0,NULL,0,'',1,'2012-01-08 21:21:12','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10888,106,6,'',0,0,'2012-01-08 21:21:10',0,'1',1,'2012-01-08 21:21:12','_start_time','1:_start_time:prop',NULL,NULL),(10889,106,6,'',0,0,'2012-01-13 21:21:10',0,'1',1,'2012-01-08 21:21:12','_due date','1:_due date:prop',NULL,NULL),(10890,107,6,'',0,0,'2012-01-08 21:43:51',0,'1',1,'2012-01-08 21:43:56','_start_time','1:_start_time:prop',NULL,NULL),(10891,107,2,'',0,0,NULL,0,'40',1,'2012-01-08 21:43:56','currentStep','40:currentStep:prop',NULL,NULL),(10892,107,1,'Running',0,0,NULL,0,'73',1,'2012-01-08 21:43:56','_status','73:_status:prop',NULL,NULL),(10893,107,2,'',0,0,NULL,0,'74',1,'2012-01-08 21:43:56','currentStep','74:currentStep:prop',NULL,NULL),(10894,107,2,'',0,0,NULL,0,'73',1,'2012-01-08 21:43:56','currentStep','73:currentStep:prop',NULL,NULL),(10895,107,1,'1',0,0,NULL,0,'',1,'2012-01-08 21:43:56','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10896,107,2,'',0,0,NULL,0,'32',1,'2012-01-08 21:43:56','currentStep','32:currentStep:prop',NULL,NULL),(10897,107,1,'115',0,0,NULL,0,'1',1,'2012-01-08 21:43:56','_task id','1:_task id:prop',NULL,NULL),(10898,107,1,'Running',0,0,NULL,0,'1',1,'2012-01-08 21:43:56','_status','1:_status:prop',NULL,NULL),(10899,107,1,'',0,0,NULL,0,'',1,'2012-01-08 21:43:56','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10900,107,6,'',0,0,'2012-01-08 21:43:51',0,'73',1,'2012-01-08 21:43:56','_start_time','73:_start_time:prop',NULL,NULL),(10901,107,6,'',0,0,'2012-01-08 21:43:51',0,'',1,'2012-01-08 21:43:56','_start_time',':_start_time:prop',NULL,NULL),(10902,107,2,'',0,0,NULL,0,'7',1,'2012-01-08 21:43:56','currentStep','7:currentStep:prop',NULL,NULL),(10903,107,6,'',0,0,'2012-01-18 21:43:51',0,'',1,'2012-01-08 21:43:56','_due date',':_due date:prop',NULL,NULL),(10904,107,1,'Running',0,0,NULL,0,'',1,'2012-01-08 21:43:56','_status',':_status:prop',NULL,NULL),(10905,107,2,'',0,0,NULL,0,'',1,'2012-01-08 21:43:56','currentStep',':currentStep:prop',NULL,NULL),(10906,107,2,'',0,0,NULL,0,'77',1,'2012-01-08 21:43:56','currentStep','77:currentStep:prop',NULL,NULL),(10907,107,2,'',0,0,NULL,0,'24',1,'2012-01-08 21:43:56','currentStep','24:currentStep:prop',NULL,NULL),(10908,107,6,'',0,0,'2012-01-13 21:43:51',0,'1',1,'2012-01-08 21:43:56','_due date','1:_due date:prop',NULL,NULL),(10909,108,1,'120',0,0,NULL,0,'1',1,'2012-01-27 17:52:57','_task id','1:_task id:prop',NULL,NULL),(10910,108,1,'',0,0,NULL,0,'',1,'2012-01-27 17:52:57','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10911,108,1,'Running',0,0,NULL,0,'',1,'2012-01-27 17:52:57','_status',':_status:prop',NULL,NULL),(10912,108,6,'',0,0,'2012-01-27 17:52:53',0,'',1,'2012-01-27 17:52:57','_start_time',':_start_time:prop',NULL,NULL),(10913,108,1,'Running',0,0,NULL,0,'1',1,'2012-01-27 17:52:57','_status','1:_status:prop',NULL,NULL),(10914,108,6,'',0,0,'2012-02-06 17:52:53',0,'',1,'2012-01-27 17:52:57','_due date',':_due date:prop',NULL,NULL),(10915,108,2,'',0,0,NULL,0,'',1,'2012-01-27 17:52:57','currentStep',':currentStep:prop',NULL,NULL),(10916,108,1,'1',0,0,NULL,0,'',1,'2012-01-27 17:52:57','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10917,108,6,'',0,0,'2012-01-27 17:52:54',0,'1',1,'2012-01-27 17:52:57','_start_time','1:_start_time:prop',NULL,NULL),(10918,108,6,'',0,0,'2012-02-01 17:52:54',0,'1',1,'2012-01-27 17:52:57','_due date','1:_due date:prop',NULL,NULL),(10919,109,1,'121',0,0,NULL,0,'1',1,'2012-01-27 17:57:07','_task id','1:_task id:prop',NULL,NULL),(10920,109,1,'',0,0,NULL,0,'',1,'2012-01-27 17:57:07','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10921,109,1,'Running',0,0,NULL,0,'',1,'2012-01-27 17:57:07','_status',':_status:prop',NULL,NULL),(10922,109,6,'',0,0,'2012-01-27 17:57:04',0,'',1,'2012-01-27 17:57:07','_start_time',':_start_time:prop',NULL,NULL),(10923,109,1,'Running',0,0,NULL,0,'1',1,'2012-01-27 17:57:07','_status','1:_status:prop',NULL,NULL),(10924,109,6,'',0,0,'2012-02-06 17:57:04',0,'',1,'2012-01-27 17:57:07','_due date',':_due date:prop',NULL,NULL),(10925,109,2,'',0,0,NULL,0,'',1,'2012-01-27 17:57:07','currentStep',':currentStep:prop',NULL,NULL),(10926,109,1,'1',0,0,NULL,0,'',1,'2012-01-27 17:57:07','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10927,109,6,'',0,0,'2012-01-27 17:57:04',0,'1',1,'2012-01-27 17:57:07','_start_time','1:_start_time:prop',NULL,NULL),(10928,109,6,'',0,0,'2012-02-01 17:57:04',0,'1',1,'2012-01-27 17:57:07','_due date','1:_due date:prop',NULL,NULL),(10929,110,1,'122',0,0,NULL,0,'1',1,'2012-01-27 19:14:35','_task id','1:_task id:prop',NULL,NULL),(10930,110,1,'',0,0,NULL,0,'',1,'2012-01-27 19:14:35','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10931,110,1,'Running',0,0,NULL,0,'',1,'2012-01-27 19:14:35','_status',':_status:prop',NULL,NULL),(10932,110,6,'',0,0,'2012-01-27 19:14:32',0,'',1,'2012-01-27 19:14:35','_start_time',':_start_time:prop',NULL,NULL),(10933,110,1,'Running',0,0,NULL,0,'1',1,'2012-01-27 19:14:35','_status','1:_status:prop',NULL,NULL),(10934,110,6,'',0,0,'2012-02-06 19:14:32',0,'',1,'2012-01-27 19:14:35','_due date',':_due date:prop',NULL,NULL),(10935,110,2,'',0,0,NULL,0,'',1,'2012-01-27 19:14:35','currentStep',':currentStep:prop',NULL,NULL),(10936,110,1,'1',0,0,NULL,0,'',1,'2012-01-27 19:14:35','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10937,110,6,'',0,0,'2012-01-27 19:14:32',0,'1',1,'2012-01-27 19:14:35','_start_time','1:_start_time:prop',NULL,NULL),(10938,110,6,'',0,0,'2012-02-01 19:14:33',0,'1',1,'2012-01-27 19:14:35','_due date','1:_due date:prop',NULL,NULL),(10939,111,1,'123',0,0,NULL,0,'1',1,'2012-01-27 22:24:53','_task id','1:_task id:prop',NULL,NULL),(10940,111,1,'',0,0,NULL,0,'',1,'2012-01-27 22:24:53','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10941,111,1,'Running',0,0,NULL,0,'',1,'2012-01-27 22:24:53','_status',':_status:prop',NULL,NULL),(10942,111,6,'',0,0,'2012-01-27 22:24:50',0,'',1,'2012-01-27 22:24:53','_start_time',':_start_time:prop',NULL,NULL),(10943,111,1,'Running',0,0,NULL,0,'1',1,'2012-01-27 22:24:53','_status','1:_status:prop',NULL,NULL),(10944,111,6,'',0,0,'2012-02-06 22:24:50',0,'',1,'2012-01-27 22:24:53','_due date',':_due date:prop',NULL,NULL),(10945,111,2,'',0,0,NULL,0,'',1,'2012-01-27 22:24:53','currentStep',':currentStep:prop',NULL,NULL),(10946,111,1,'1',0,0,NULL,0,'',1,'2012-01-27 22:24:53','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10947,111,6,'',0,0,'2012-01-27 22:24:50',0,'1',1,'2012-01-27 22:24:53','_start_time','1:_start_time:prop',NULL,NULL),(10948,111,6,'',0,0,'2012-02-01 22:24:50',0,'1',1,'2012-01-27 22:24:53','_due date','1:_due date:prop',NULL,NULL),(10949,112,1,'124',0,0,NULL,0,'1',1,'2012-01-27 22:44:27','_task id','1:_task id:prop',NULL,NULL),(10950,112,1,'',0,0,NULL,0,'',1,'2012-01-27 22:44:27','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10952,112,6,'',0,0,'2012-01-27 22:44:24',0,'',1,'2012-01-27 22:44:27','_start_time',':_start_time:prop',NULL,NULL),(10954,112,6,'',0,0,'2012-02-06 22:44:24',0,'',1,'2012-01-27 22:44:27','_due date',':_due date:prop',NULL,NULL),(10957,112,6,'',0,0,'2012-01-27 22:44:24',0,'1',1,'2012-01-27 22:44:27','_start_time','1:_start_time:prop',NULL,NULL),(10958,112,6,'',0,0,'2012-02-01 22:44:24',0,'1',1,'2012-01-27 22:44:27','_due date','1:_due date:prop',NULL,NULL),(10959,112,1,'',0,0,NULL,0,'',0,'2012-01-28 01:30:11','name',':name',NULL,NULL),(10960,112,6,'',0,0,'2012-01-28 01:27:39',0,'1',1,'2012-01-28 01:30:11','_end_time','1:_end_time:prop',NULL,NULL),(10961,112,1,'Completed',0,0,NULL,0,'6',1,'2012-01-28 01:30:11','_status','6:_status:prop',NULL,NULL),(10962,112,6,'',0,0,'2012-01-28 01:30:11',0,'6',1,'2012-01-28 01:30:11','_end_time','6:_end_time:prop',NULL,NULL),(10963,112,-1,'<test1.Person>\n  <name>jinyoung</name>\n  <age>0</age>\n</test1.Person>',0,0,NULL,0,'',0,'2012-01-28 01:30:11','metaworksObj',':metaworksObj',NULL,NULL),(10964,112,1,'Completed',0,0,NULL,0,'',1,'2012-01-28 01:30:11','_status',':_status:prop',NULL,NULL),(10965,112,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <resourceName>장진영</resourceName>\n  <endpoint>1401720840</endpoint>\n  <assignType>0</assignType>\n  <dispatchingOption>-1</dispatchingOption>\n  <isMale>true</isMale>\n  <isGroup>false</isGroup>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2012-01-28 01:30:11','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(10966,112,1,'Completed',0,0,NULL,0,'1',1,'2012-01-28 01:30:11','_status','1:_status:prop',NULL,NULL),(10967,112,6,'',0,0,'2012-01-28 01:30:11',0,'',1,'2012-01-28 01:30:11','_end_time',':_end_time:prop',NULL,NULL),(10968,112,6,'',0,0,'2012-01-28 01:27:39',0,'6',1,'2012-01-28 01:30:11','_start_time','6:_start_time:prop',NULL,NULL),(10969,112,2,'',2,0,NULL,0,'',1,'2012-01-28 01:30:11','currentStep',':currentStep:prop',NULL,NULL),(10970,112,1,'',0,0,NULL,0,'',1,'2012-01-28 01:30:11','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10971,113,1,'125',0,0,NULL,0,'1',1,'2012-01-28 01:47:16','_task id','1:_task id:prop',NULL,NULL),(10972,113,1,'',0,0,NULL,0,'',1,'2012-01-28 01:47:16','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10974,113,6,'',0,0,'2012-01-28 01:47:12',0,'',1,'2012-01-28 01:47:16','_start_time',':_start_time:prop',NULL,NULL),(10976,113,6,'',0,0,'2012-02-07 01:47:12',0,'',1,'2012-01-28 01:47:16','_due date',':_due date:prop',NULL,NULL),(10979,113,6,'',0,0,'2012-01-28 01:47:12',0,'1',1,'2012-01-28 01:47:16','_start_time','1:_start_time:prop',NULL,NULL),(10980,113,6,'',0,0,'2012-02-02 01:47:12',0,'1',1,'2012-01-28 01:47:16','_due date','1:_due date:prop',NULL,NULL),(10981,113,1,'',0,0,NULL,0,'',0,'2012-01-28 01:47:40','name',':name',NULL,NULL),(10982,113,6,'',0,0,'2012-01-28 01:47:28',0,'1',1,'2012-01-28 01:47:40','_end_time','1:_end_time:prop',NULL,NULL),(10983,113,1,'Completed',0,0,NULL,0,'6',1,'2012-01-28 01:47:40','_status','6:_status:prop',NULL,NULL),(10984,113,6,'',0,0,'2012-01-28 01:47:40',0,'6',1,'2012-01-28 01:47:40','_end_time','6:_end_time:prop',NULL,NULL),(10985,113,-1,'<test1.Person>\n  <name>장진영</name>\n  <age>0</age>\n</test1.Person>',0,0,NULL,0,'',0,'2012-01-28 01:47:40','metaworksObj',':metaworksObj',NULL,NULL),(10986,113,1,'Completed',0,0,NULL,0,'',1,'2012-01-28 01:47:40','_status',':_status:prop',NULL,NULL),(10987,113,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <resourceName>장진영</resourceName>\n  <endpoint>1401720840</endpoint>\n  <assignType>0</assignType>\n  <dispatchingOption>-1</dispatchingOption>\n  <isMale>true</isMale>\n  <isGroup>false</isGroup>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2012-01-28 01:47:40','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(10988,113,1,'Completed',0,0,NULL,0,'1',1,'2012-01-28 01:47:40','_status','1:_status:prop',NULL,NULL),(10989,113,6,'',0,0,'2012-01-28 01:47:40',0,'',1,'2012-01-28 01:47:40','_end_time',':_end_time:prop',NULL,NULL),(10990,113,6,'',0,0,'2012-01-28 01:47:28',0,'6',1,'2012-01-28 01:47:40','_start_time','6:_start_time:prop',NULL,NULL),(10991,113,2,'',2,0,NULL,0,'',1,'2012-01-28 01:47:40','currentStep',':currentStep:prop',NULL,NULL),(10992,113,1,'',0,0,NULL,0,'',1,'2012-01-28 01:47:40','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(10993,114,1,'126',0,0,NULL,0,'1',1,'2012-01-30 11:15:31','_task id','1:_task id:prop',NULL,NULL),(10994,114,1,'',0,0,NULL,0,'',1,'2012-01-30 11:15:31','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(10995,114,1,'Running',0,0,NULL,0,'',1,'2012-01-30 11:15:31','_status',':_status:prop',NULL,NULL),(10996,114,6,'',0,0,'2012-01-30 11:15:24',0,'',1,'2012-01-30 11:15:31','_start_time',':_start_time:prop',NULL,NULL),(10997,114,1,'Running',0,0,NULL,0,'1',1,'2012-01-30 11:15:31','_status','1:_status:prop',NULL,NULL),(10998,114,6,'',0,0,'2012-02-09 11:15:24',0,'',1,'2012-01-30 11:15:31','_due date',':_due date:prop',NULL,NULL),(10999,114,2,'',0,0,NULL,0,'',1,'2012-01-30 11:15:31','currentStep',':currentStep:prop',NULL,NULL),(11000,114,1,'1',0,0,NULL,0,'',1,'2012-01-30 11:15:31','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(11001,114,6,'',0,0,'2012-01-30 11:15:24',0,'1',1,'2012-01-30 11:15:31','_start_time','1:_start_time:prop',NULL,NULL),(11002,114,6,'',0,0,'2012-02-04 11:15:24',0,'1',1,'2012-01-30 11:15:31','_due date','1:_due date:prop',NULL,NULL),(11003,115,1,'127',0,0,NULL,0,'1',1,'2012-01-30 14:36:20','_task id','1:_task id:prop',NULL,NULL),(11004,115,1,'',0,0,NULL,0,'',1,'2012-01-30 14:36:20','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(11005,115,1,'Running',0,0,NULL,0,'',1,'2012-01-30 14:36:20','_status',':_status:prop',NULL,NULL),(11006,115,6,'',0,0,'2012-01-30 14:36:17',0,'',1,'2012-01-30 14:36:20','_start_time',':_start_time:prop',NULL,NULL),(11008,115,6,'',0,0,'2012-02-09 14:36:17',0,'',1,'2012-01-30 14:36:20','_due date',':_due date:prop',NULL,NULL),(11011,115,6,'',0,0,'2012-01-30 14:36:17',0,'1',1,'2012-01-30 14:36:20','_start_time','1:_start_time:prop',NULL,NULL),(11012,115,6,'',0,0,'2012-02-04 14:36:17',0,'1',1,'2012-01-30 14:36:20','_due date','1:_due date:prop',NULL,NULL),(11013,115,1,'Running',0,0,NULL,0,'3',1,'2012-01-30 14:39:59','_status','3:_status:prop',NULL,NULL),(11014,115,6,'',0,0,'2012-01-30 14:39:56',0,'1',1,'2012-01-30 14:39:59','_end_time','1:_end_time:prop',NULL,NULL),(11015,115,6,'',0,0,'2012-02-04 14:39:56',0,'3',1,'2012-01-30 14:39:59','_due date','3:_due date:prop',NULL,NULL),(11016,115,6,'',0,0,'2012-01-30 14:39:56',0,'5',1,'2012-01-30 14:39:59','_end_time','5:_end_time:prop',NULL,NULL),(11017,115,6,'',0,0,'2012-01-30 14:39:56',0,'3',1,'2012-01-30 14:39:59','_start_time','3:_start_time:prop',NULL,NULL),(11018,115,1,'Completed',0,0,NULL,0,'5',1,'2012-01-30 14:39:59','_status','5:_status:prop',NULL,NULL),(11019,115,1,'3',0,0,NULL,0,'',1,'2012-01-30 14:39:59','MESSAGE_onHumanActivityResult3',':MESSAGE_onHumanActivityResult3:prop',NULL,NULL),(11020,115,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <resourceName>장진영</resourceName>\n  <endpoint>1401720840</endpoint>\n  <assignType>0</assignType>\n  <dispatchingOption>-1</dispatchingOption>\n  <isMale>true</isMale>\n  <isGroup>false</isGroup>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2012-01-30 14:39:59','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(11021,115,-1,'<org.kalm.CRCCard/>',0,0,NULL,0,'',0,'2012-01-30 14:39:59','classRequestCard',':classRequestCard',NULL,NULL),(11022,115,1,'Completed',0,0,NULL,0,'1',1,'2012-01-30 14:39:59','_status','1:_status:prop',NULL,NULL),(11023,115,1,'1',0,0,NULL,0,'3',1,'2012-01-30 14:39:59','_previous','3:_previous:prop',NULL,NULL),(11024,115,2,'',2,0,NULL,0,'',1,'2012-01-30 14:39:59','currentStep',':currentStep:prop',NULL,NULL),(11025,115,1,'',0,0,NULL,0,'',1,'2012-01-30 14:39:59','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(11026,115,6,'',0,0,'2012-01-30 14:39:56',0,'5',1,'2012-01-30 14:39:59','_start_time','5:_start_time:prop',NULL,NULL),(11027,115,1,'128',0,0,NULL,0,'3',1,'2012-01-30 14:39:59','_task id','3:_task id:prop',NULL,NULL),(11028,116,1,'129',0,0,NULL,0,'1',1,'2012-01-30 18:01:22','_task id','1:_task id:prop',NULL,NULL),(11029,116,1,'',0,0,NULL,0,'',1,'2012-01-30 18:01:22','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(11030,116,1,'Running',0,0,NULL,0,'',1,'2012-01-30 18:01:22','_status',':_status:prop',NULL,NULL),(11031,116,6,'',0,0,'2012-01-30 18:01:19',0,'',1,'2012-01-30 18:01:22','_start_time',':_start_time:prop',NULL,NULL),(11033,116,6,'',0,0,'2012-02-09 18:01:19',0,'',1,'2012-01-30 18:01:22','_due date',':_due date:prop',NULL,NULL),(11036,116,6,'',0,0,'2012-01-30 18:01:19',0,'1',1,'2012-01-30 18:01:22','_start_time','1:_start_time:prop',NULL,NULL),(11037,116,6,'',0,0,'2012-02-04 18:01:20',0,'1',1,'2012-01-30 18:01:22','_due date','1:_due date:prop',NULL,NULL),(11038,116,1,'Running',0,0,NULL,0,'3',1,'2012-01-30 19:08:26','_status','3:_status:prop',NULL,NULL),(11039,116,6,'',0,0,'2012-01-30 19:08:23',0,'1',1,'2012-01-30 19:08:26','_end_time','1:_end_time:prop',NULL,NULL),(11040,116,6,'',0,0,'2012-02-04 19:08:23',0,'3',1,'2012-01-30 19:08:26','_due date','3:_due date:prop',NULL,NULL),(11041,116,2,'',0,0,NULL,0,'1',1,'2012-01-30 19:08:26','tokenCount','1:tokenCount:prop',NULL,NULL),(11042,116,6,'',0,0,'2012-01-30 19:08:23',0,'5',1,'2012-01-30 19:08:26','_end_time','5:_end_time:prop',NULL,NULL),(11043,116,6,'',0,0,'2012-01-30 19:08:23',0,'3',1,'2012-01-30 19:08:26','_start_time','3:_start_time:prop',NULL,NULL),(11044,116,1,'Completed',0,0,NULL,0,'5',1,'2012-01-30 19:08:26','_status','5:_status:prop',NULL,NULL),(11045,116,1,'3',0,0,NULL,0,'',1,'2012-01-30 19:08:26','MESSAGE_onHumanActivityResult3',':MESSAGE_onHumanActivityResult3:prop',NULL,NULL),(11046,116,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <resourceName>장진영</resourceName>\n  <endpoint>1401720840</endpoint>\n  <assignType>0</assignType>\n  <dispatchingOption>0</dispatchingOption>\n  <isMale>true</isMale>\n  <isGroup>false</isGroup>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n  <multipleMappings>\n    <com.defaultcompany.organization.DefaultCompanyRoleMapping reference=\"../..\"/>\n  </multipleMappings>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2012-01-30 19:08:26','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(11047,116,-1,'<org.kalm.CRCCard>\n  <className>클래스명</className>\n  <packageName>패키지명</packageName>\n</org.kalm.CRCCard>',0,0,NULL,0,'',0,'2012-01-30 19:08:26','classRequestCard',':classRequestCard',NULL,NULL),(11048,116,1,'Completed',0,0,NULL,0,'1',1,'2012-01-30 19:08:26','_status','1:_status:prop',NULL,NULL),(11049,116,2,'',0,0,NULL,0,'5',1,'2012-01-30 19:08:26','tokenCount','5:tokenCount:prop',NULL,NULL),(11050,116,1,'1',0,0,NULL,0,'3',1,'2012-01-30 19:08:26','_previous','3:_previous:prop',NULL,NULL),(11051,116,2,'',2,0,NULL,0,'',1,'2012-01-30 19:08:26','currentStep',':currentStep:prop',NULL,NULL),(11052,116,1,'',0,0,NULL,0,'',1,'2012-01-30 19:08:26','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(11053,116,6,'',0,0,'2012-01-30 19:08:23',0,'5',1,'2012-01-30 19:08:26','_start_time','5:_start_time:prop',NULL,NULL),(11054,116,1,'144',0,0,NULL,0,'3',1,'2012-01-30 19:08:26','_task id','3:_task id:prop',NULL,NULL),(11055,117,1,'213',0,0,NULL,0,'1',1,'2012-01-30 21:46:06','_task id','1:_task id:prop',NULL,NULL),(11056,117,1,'',0,0,NULL,0,'',1,'2012-01-30 21:46:06','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(11057,117,1,'Running',0,0,NULL,0,'',1,'2012-01-30 21:46:06','_status',':_status:prop',NULL,NULL),(11058,117,6,'',0,0,'2012-01-30 21:46:01',0,'',1,'2012-01-30 21:46:06','_start_time',':_start_time:prop',NULL,NULL),(11059,117,1,'Running',0,0,NULL,0,'1',1,'2012-01-30 21:46:06','_status','1:_status:prop',NULL,NULL),(11060,117,6,'',0,0,'2012-02-09 21:46:02',0,'',1,'2012-01-30 21:46:06','_due date',':_due date:prop',NULL,NULL),(11061,117,2,'',0,0,NULL,0,'',1,'2012-01-30 21:46:06','currentStep',':currentStep:prop',NULL,NULL),(11062,117,1,'1',0,0,NULL,0,'',1,'2012-01-30 21:46:06','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(11063,117,6,'',0,0,'2012-01-30 21:46:02',0,'1',1,'2012-01-30 21:46:06','_start_time','1:_start_time:prop',NULL,NULL),(11064,117,6,'',0,0,'2012-02-04 21:46:02',0,'1',1,'2012-01-30 21:46:06','_due date','1:_due date:prop',NULL,NULL),(11065,118,1,'214',0,0,NULL,0,'1',1,'2012-01-30 21:47:03','_task id','1:_task id:prop',NULL,NULL),(11066,118,1,'',0,0,NULL,0,'',1,'2012-01-30 21:47:03','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(11067,118,1,'Running',0,0,NULL,0,'',1,'2012-01-30 21:47:03','_status',':_status:prop',NULL,NULL),(11068,118,6,'',0,0,'2012-01-30 21:47:01',0,'',1,'2012-01-30 21:47:03','_start_time',':_start_time:prop',NULL,NULL),(11069,118,1,'Running',0,0,NULL,0,'1',1,'2012-01-30 21:47:03','_status','1:_status:prop',NULL,NULL),(11070,118,6,'',0,0,'2012-02-09 21:47:01',0,'',1,'2012-01-30 21:47:03','_due date',':_due date:prop',NULL,NULL),(11071,118,2,'',0,0,NULL,0,'',1,'2012-01-30 21:47:03','currentStep',':currentStep:prop',NULL,NULL),(11072,118,1,'1',0,0,NULL,0,'',1,'2012-01-30 21:47:03','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(11073,118,6,'',0,0,'2012-01-30 21:47:01',0,'1',1,'2012-01-30 21:47:03','_start_time','1:_start_time:prop',NULL,NULL),(11074,118,6,'',0,0,'2012-02-04 21:47:01',0,'1',1,'2012-01-30 21:47:03','_due date','1:_due date:prop',NULL,NULL),(11075,119,1,'215',0,0,NULL,0,'1',1,'2012-01-30 21:47:53','_task id','1:_task id:prop',NULL,NULL),(11076,119,1,'',0,0,NULL,0,'',1,'2012-01-30 21:47:53','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(11077,119,1,'Running',0,0,NULL,0,'',1,'2012-01-30 21:47:53','_status',':_status:prop',NULL,NULL),(11078,119,6,'',0,0,'2012-01-30 21:47:49',0,'',1,'2012-01-30 21:47:53','_start_time',':_start_time:prop',NULL,NULL),(11079,119,1,'Running',0,0,NULL,0,'1',1,'2012-01-30 21:47:53','_status','1:_status:prop',NULL,NULL),(11080,119,6,'',0,0,'2012-02-09 21:47:49',0,'',1,'2012-01-30 21:47:53','_due date',':_due date:prop',NULL,NULL),(11081,119,2,'',0,0,NULL,0,'',1,'2012-01-30 21:47:53','currentStep',':currentStep:prop',NULL,NULL),(11082,119,1,'1',0,0,NULL,0,'',1,'2012-01-30 21:47:53','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(11083,119,6,'',0,0,'2012-01-30 21:47:49',0,'1',1,'2012-01-30 21:47:53','_start_time','1:_start_time:prop',NULL,NULL),(11084,119,6,'',0,0,'2012-02-04 21:47:49',0,'1',1,'2012-01-30 21:47:53','_due date','1:_due date:prop',NULL,NULL),(11085,120,1,'216',0,0,NULL,0,'1',1,'2012-01-30 21:50:57','_task id','1:_task id:prop',NULL,NULL),(11086,120,1,'',0,0,NULL,0,'',1,'2012-01-30 21:50:57','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(11087,120,1,'Running',0,0,NULL,0,'',1,'2012-01-30 21:50:57','_status',':_status:prop',NULL,NULL),(11088,120,6,'',0,0,'2012-01-30 21:50:56',0,'',1,'2012-01-30 21:50:57','_start_time',':_start_time:prop',NULL,NULL),(11089,120,1,'Running',0,0,NULL,0,'1',1,'2012-01-30 21:50:57','_status','1:_status:prop',NULL,NULL),(11090,120,6,'',0,0,'2012-02-09 21:50:56',0,'',1,'2012-01-30 21:50:57','_due date',':_due date:prop',NULL,NULL),(11091,120,2,'',0,0,NULL,0,'',1,'2012-01-30 21:50:57','currentStep',':currentStep:prop',NULL,NULL),(11092,120,1,'1',0,0,NULL,0,'',1,'2012-01-30 21:50:57','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(11093,120,6,'',0,0,'2012-01-30 21:50:56',0,'1',1,'2012-01-30 21:50:57','_start_time','1:_start_time:prop',NULL,NULL),(11094,120,6,'',0,0,'2012-02-04 21:50:56',0,'1',1,'2012-01-30 21:50:57','_due date','1:_due date:prop',NULL,NULL),(11095,121,1,'217',0,0,NULL,0,'1',1,'2012-01-30 22:02:08','_task id','1:_task id:prop',NULL,NULL),(11096,121,1,'',0,0,NULL,0,'',1,'2012-01-30 22:02:08','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(11098,121,6,'',0,0,'2012-01-30 22:02:03',0,'',1,'2012-01-30 22:02:08','_start_time',':_start_time:prop',NULL,NULL),(11100,121,6,'',0,0,'2012-02-09 22:02:03',0,'',1,'2012-01-30 22:02:08','_due date',':_due date:prop',NULL,NULL),(11103,121,6,'',0,0,'2012-01-30 22:02:03',0,'1',1,'2012-01-30 22:02:08','_start_time','1:_start_time:prop',NULL,NULL),(11104,121,6,'',0,0,'2012-02-04 22:02:04',0,'1',1,'2012-01-30 22:02:08','_due date','1:_due date:prop',NULL,NULL),(11105,121,6,'',0,0,'2012-01-30 22:41:41',0,'1',1,'2012-01-30 22:41:41','_end_time','1:_end_time:prop',NULL,NULL),(11106,121,2,'',0,0,NULL,0,'1',1,'2012-01-30 22:41:41','tokenCount','1:tokenCount:prop',NULL,NULL),(11107,121,1,'Completed',0,0,NULL,0,'',1,'2012-01-30 22:41:41','_status',':_status:prop',NULL,NULL),(11108,121,2,'',0,0,NULL,0,'',1,'2012-01-30 22:41:41','tokenCount',':tokenCount:prop',NULL,NULL),(11109,121,-1,'<org.uengine.codi.activitytypes.wih.ClassDevelopmentWorkItemHandler>\n  <resourceName>test/Addressbook.java</resourceName>\n</org.uengine.codi.activitytypes.wih.ClassDevelopmentWorkItemHandler>',0,0,NULL,0,'',0,'2012-01-30 22:41:41','CRCard',':CRCard',NULL,NULL),(11110,121,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <resourceName>장진영</resourceName>\n  <endpoint>1401720840</endpoint>\n  <assignType>0</assignType>\n  <dispatchingOption>0</dispatchingOption>\n  <isMale>true</isMale>\n  <isGroup>false</isGroup>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n  <multipleMappings>\n    <com.defaultcompany.organization.DefaultCompanyRoleMapping reference=\"../..\"/>\n  </multipleMappings>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2012-01-30 22:41:41','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(11111,121,1,'Completed',0,0,NULL,0,'1',1,'2012-01-30 22:41:41','_status','1:_status:prop',NULL,NULL),(11112,121,6,'',0,0,'2012-01-30 22:41:41',0,'',1,'2012-01-30 22:41:41','_end_time',':_end_time:prop',NULL,NULL),(11113,121,2,'',1,0,NULL,0,'',1,'2012-01-30 22:41:41','currentStep',':currentStep:prop',NULL,NULL),(11114,121,1,'',0,0,NULL,0,'',1,'2012-01-30 22:41:41','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(11115,122,1,'221',0,0,NULL,0,'1',1,'2012-01-30 23:59:56','_task id','1:_task id:prop',NULL,NULL),(11116,122,1,'',0,0,NULL,0,'',1,'2012-01-30 23:59:56','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(11117,122,1,'Running',0,0,NULL,0,'',1,'2012-01-30 23:59:56','_status',':_status:prop',NULL,NULL),(11118,122,6,'',0,0,'2012-01-30 23:59:54',0,'',1,'2012-01-30 23:59:56','_start_time',':_start_time:prop',NULL,NULL),(11119,122,1,'Running',0,0,NULL,0,'1',1,'2012-01-30 23:59:56','_status','1:_status:prop',NULL,NULL),(11120,122,6,'',0,0,'2012-02-09 23:59:54',0,'',1,'2012-01-30 23:59:56','_due date',':_due date:prop',NULL,NULL),(11121,122,2,'',0,0,NULL,0,'',1,'2012-01-30 23:59:56','currentStep',':currentStep:prop',NULL,NULL),(11122,122,1,'1',0,0,NULL,0,'',1,'2012-01-30 23:59:56','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(11123,122,6,'',0,0,'2012-01-30 23:59:54',0,'1',1,'2012-01-30 23:59:56','_start_time','1:_start_time:prop',NULL,NULL),(11124,122,6,'',0,0,'2012-02-04 23:59:54',0,'1',1,'2012-01-30 23:59:56','_due date','1:_due date:prop',NULL,NULL),(11125,123,1,'231',0,0,NULL,0,'1',1,'2012-01-31 19:26:46','_task id','1:_task id:prop',NULL,NULL),(11126,123,1,'',0,0,NULL,0,'',1,'2012-01-31 19:26:46','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(11128,123,6,'',0,0,'2012-01-31 19:26:43',0,'',1,'2012-01-31 19:26:46','_start_time',':_start_time:prop',NULL,NULL),(11130,123,6,'',0,0,'2012-02-10 19:26:43',0,'',1,'2012-01-31 19:26:46','_due date',':_due date:prop',NULL,NULL),(11133,123,6,'',0,0,'2012-01-31 19:26:43',0,'1',1,'2012-01-31 19:26:46','_start_time','1:_start_time:prop',NULL,NULL),(11134,123,6,'',0,0,'2012-02-05 19:26:43',0,'1',1,'2012-01-31 19:26:46','_due date','1:_due date:prop',NULL,NULL),(11135,123,6,'',0,0,'2012-01-31 19:27:48',0,'1',1,'2012-01-31 19:27:48','_end_time','1:_end_time:prop',NULL,NULL),(11136,123,2,'',0,0,NULL,0,'1',1,'2012-01-31 19:27:48','tokenCount','1:tokenCount:prop',NULL,NULL),(11137,123,1,'Completed',0,0,NULL,0,'',1,'2012-01-31 19:27:48','_status',':_status:prop',NULL,NULL),(11138,123,2,'',0,0,NULL,0,'',1,'2012-01-31 19:27:48','tokenCount',':tokenCount:prop',NULL,NULL),(11139,123,-1,'<org.uengine.codi.activitytypes.wih.ClassDevelopmentWorkItemHandler>\n  <resourceName>test2/Addressbook.java</resourceName>\n</org.uengine.codi.activitytypes.wih.ClassDevelopmentWorkItemHandler>',0,0,NULL,0,'',0,'2012-01-31 19:27:48','CRCard',':CRCard',NULL,NULL),(11140,123,-1,'<com.defaultcompany.organization.DefaultCompanyRoleMapping>\n  <resourceName>장진영</resourceName>\n  <endpoint>1401720840</endpoint>\n  <assignType>0</assignType>\n  <dispatchingOption>0</dispatchingOption>\n  <isMale>true</isMale>\n  <isGroup>false</isGroup>\n  <cursor>0</cursor>\n  <isSingle>false</isSingle>\n  <multipleMappings>\n    <com.defaultcompany.organization.DefaultCompanyRoleMapping reference=\"../..\"/>\n  </multipleMappings>\n</com.defaultcompany.organization.DefaultCompanyRoleMapping>',0,0,NULL,0,'1',1,'2012-01-31 19:27:48','_completed rolemapping','1:_completed rolemapping:prop',NULL,NULL),(11141,123,1,'Completed',0,0,NULL,0,'1',1,'2012-01-31 19:27:48','_status','1:_status:prop',NULL,NULL),(11142,123,6,'',0,0,'2012-01-31 19:27:48',0,'',1,'2012-01-31 19:27:48','_end_time',':_end_time:prop',NULL,NULL),(11143,123,2,'',1,0,NULL,0,'',1,'2012-01-31 19:27:48','currentStep',':currentStep:prop',NULL,NULL),(11144,123,1,'',0,0,NULL,0,'',1,'2012-01-31 19:27:48','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(11145,124,1,'244',0,0,NULL,0,'1',1,'2012-02-01 17:51:35','_task id','1:_task id:prop',NULL,NULL),(11146,124,1,'',0,0,NULL,0,'',1,'2012-02-01 17:51:35','MESSAGE_event',':MESSAGE_event:prop',NULL,NULL),(11147,124,1,'Running',0,0,NULL,0,'',1,'2012-02-01 17:51:35','_status',':_status:prop',NULL,NULL),(11148,124,6,'',0,0,'2012-02-01 17:51:33',0,'',1,'2012-02-01 17:51:35','_start_time',':_start_time:prop',NULL,NULL),(11149,124,1,'Running',0,0,NULL,0,'1',1,'2012-02-01 17:51:35','_status','1:_status:prop',NULL,NULL),(11150,124,6,'',0,0,'2012-02-11 17:51:33',0,'',1,'2012-02-01 17:51:35','_due date',':_due date:prop',NULL,NULL),(11151,124,2,'',0,0,NULL,0,'',1,'2012-02-01 17:51:35','currentStep',':currentStep:prop',NULL,NULL),(11152,124,1,'1',0,0,NULL,0,'',1,'2012-02-01 17:51:35','MESSAGE_onHumanActivityResult1',':MESSAGE_onHumanActivityResult1:prop',NULL,NULL),(11153,124,6,'',0,0,'2012-02-01 17:51:33',0,'1',1,'2012-02-01 17:51:35','_start_time','1:_start_time:prop',NULL,NULL),(11154,124,6,'',0,0,'2012-02-06 17:51:33',0,'1',1,'2012-02-01 17:51:35','_due date','1:_due date:prop',NULL,NULL);
/*!40000 ALTER TABLE `BPM_PROCVAR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BPM_ROLEMAPPING`
--

DROP TABLE IF EXISTS `BPM_ROLEMAPPING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BPM_ROLEMAPPING` (
  `ROLEMAPPINGID` int(11) NOT NULL AUTO_INCREMENT,
  `INSTID` int(11) DEFAULT NULL,
  `ROOTINSTID` int(11) DEFAULT NULL,
  `ROLENAME` varchar(20) DEFAULT NULL,
  `ENDPOINT` varchar(255) DEFAULT NULL,
  `VALUE` varchar(4000) DEFAULT NULL,
  `RESNAME` varchar(200) DEFAULT NULL,
  `ASSIGNTYPE` int(11) DEFAULT NULL,
  `ASSIGNPARAM1` varchar(100) DEFAULT NULL,
  `DISPATCHOPTION` int(11) DEFAULT NULL,
  `DISPATCHPARAM1` varchar(100) DEFAULT NULL,
  `GROUPID` varchar(30) DEFAULT NULL,
  `ISREFERENCER` int(1) DEFAULT '0',
  PRIMARY KEY (`ROLEMAPPINGID`)
) ENGINE=InnoDB AUTO_INCREMENT=711 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BPM_ROLEMAPPING`
--

LOCK TABLES `BPM_ROLEMAPPING` WRITE;
/*!40000 ALTER TABLE `BPM_ROLEMAPPING` DISABLE KEYS */;
INSERT INTO `BPM_ROLEMAPPING` VALUES (640,65,65,'Initiator','test',NULL,'Tester',0,NULL,-1,NULL,'',0),(641,66,66,'Initiator','test',NULL,'Tester',0,NULL,-1,NULL,'',0),(642,67,67,'Initiator','test',NULL,'Tester',0,NULL,-1,NULL,'',0),(643,68,68,'Initiator','test',NULL,'Tester',0,NULL,-1,NULL,'',0),(644,68,68,'test','test',NULL,'Tester',0,NULL,-1,NULL,'',0),(645,73,73,'Initiator','1401720840',NULL,'1401720840',0,NULL,-1,NULL,NULL,0),(646,74,74,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(647,75,75,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(648,76,76,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(649,77,77,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(650,78,78,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(651,79,79,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(652,80,80,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(653,81,81,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(654,82,82,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(655,83,83,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(656,84,84,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(657,85,85,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(658,86,86,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(659,86,86,'follower_정진호','593670837',NULL,'정진호',0,NULL,-1,NULL,NULL,0),(661,84,84,'follower_유영만','579204709',NULL,'유영만',0,NULL,-1,NULL,NULL,0),(662,84,84,'follower_서진호','608461498',NULL,'서진호',0,NULL,-1,NULL,NULL,0),(663,84,84,'follower_정진호','593670837',NULL,'정진호',0,NULL,-1,NULL,NULL,0),(664,87,87,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(665,88,88,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(666,90,90,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(667,90,90,'follower_유영만','579204709',NULL,'유영만',0,NULL,-1,NULL,NULL,0),(668,91,91,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(669,92,92,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(670,91,91,'follower_유영만','579204709',NULL,'유영만',0,NULL,-1,NULL,NULL,0),(671,93,93,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(672,94,94,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(673,95,95,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(674,96,96,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(675,97,97,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(676,98,98,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(677,99,99,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(678,100,100,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(679,101,101,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(680,102,102,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(681,103,103,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(682,104,104,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(683,104,104,'follower_정진호','593670837',NULL,'정진호',0,NULL,-1,NULL,NULL,0),(684,105,105,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(685,106,106,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(686,107,107,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(687,107,107,'follower_유영만','579204709',NULL,'유영만',0,NULL,-1,NULL,NULL,0),(688,107,107,'follower_정진호','593670837',NULL,'정진호',0,NULL,-1,NULL,NULL,0),(689,106,106,'follower_유영만','579204709',NULL,'유영만',0,NULL,-1,NULL,NULL,0),(690,108,108,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(691,109,109,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(692,110,110,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(693,111,111,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(694,112,112,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(695,113,113,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(696,114,114,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(697,115,115,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(698,116,116,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(699,114,114,'follower_조진원','100002899287992',NULL,'조진원',0,NULL,-1,NULL,NULL,0),(700,117,117,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(701,118,118,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(702,119,119,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(703,120,120,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(704,121,121,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(705,122,122,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(706,121,121,'follower_조진원','100002899287992',NULL,'조진원',0,NULL,-1,NULL,NULL,0),(707,123,123,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(708,123,123,'follower_조진원','100002899287992',NULL,'조진원',0,NULL,-1,NULL,NULL,0),(709,124,124,'Initiator','1401720840',NULL,'장진영',0,NULL,-1,NULL,NULL,0),(710,124,124,'follower_조진원','100002899287992',NULL,'조진원',0,NULL,-1,NULL,NULL,0);
/*!40000 ALTER TABLE `BPM_ROLEMAPPING` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BPM_RSRC_DIM`
--

DROP TABLE IF EXISTS `BPM_RSRC_DIM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BPM_RSRC_DIM` (
  `RSRC_ID` varchar(20) NOT NULL,
  `RSRC_NAME` varchar(100) DEFAULT NULL,
  `DEPT_NAME` varchar(100) DEFAULT NULL,
  `DEPT_ID` varchar(20) DEFAULT NULL,
  `PSTN_NAME` varchar(50) DEFAULT NULL,
  `PSTN_ID` varchar(20) DEFAULT NULL,
  `TITLE` varchar(50) DEFAULT NULL,
  `GENDER` int(11) DEFAULT NULL,
  `BIRTHDAY` int(11) DEFAULT NULL,
  PRIMARY KEY (`RSRC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BPM_RSRC_DIM`
--

LOCK TABLES `BPM_RSRC_DIM` WRITE;
/*!40000 ALTER TABLE `BPM_RSRC_DIM` DISABLE KEYS */;
INSERT INTO `BPM_RSRC_DIM` VALUES ('1401720840','1401720840',NULL,NULL,NULL,NULL,NULL,1,NULL),('PJJ','Jongjun Park','Consulting 1Team','CON1',NULL,NULL,NULL,0,NULL),('test','Tester','','',NULL,NULL,NULL,1,2011);
/*!40000 ALTER TABLE `BPM_RSRC_DIM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BPM_SEQ`
--

DROP TABLE IF EXISTS `BPM_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BPM_SEQ` (
  `SEQ` int(11) DEFAULT NULL,
  `TBNAME` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `MODDATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BPM_SEQ`
--

LOCK TABLES `BPM_SEQ` WRITE;
/*!40000 ALTER TABLE `BPM_SEQ` DISABLE KEYS */;
INSERT INTO `BPM_SEQ` VALUES (2,'SEQ_FAVORITE_CONTACTS',NULL,NULL),(97,'procdef','procdef','2012-02-02 15:08:24'),(283,'procdefver','procdefver','2012-02-02 18:03:02'),(124,'procinst','procinst','2012-02-01 17:51:33'),(249,'workitem','workitem','2012-02-02 17:53:02'),(2,'tag','tag','2011-09-26 00:57:04');
/*!40000 ALTER TABLE `BPM_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BPM_STRINST`
--

DROP TABLE IF EXISTS `BPM_STRINST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BPM_STRINST` (
  `INSTID` int(11) DEFAULT NULL,
  `STRTGID` int(11) DEFAULT NULL,
  `ISDELETED` varchar(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BPM_STRINST`
--

LOCK TABLES `BPM_STRINST` WRITE;
/*!40000 ALTER TABLE `BPM_STRINST` DISABLE KEYS */;
/*!40000 ALTER TABLE `BPM_STRINST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BPM_STRTG`
--

DROP TABLE IF EXISTS `BPM_STRTG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BPM_STRTG` (
  `STRTGID` int(11) NOT NULL DEFAULT '0',
  `STRTGNM` varchar(100) DEFAULT NULL,
  `TYPE` varchar(30) DEFAULT NULL,
  `ISDELETED` varchar(1) DEFAULT '0',
  `STATUS` char(10) DEFAULT NULL,
  `INSTCNT` int(11) DEFAULT NULL,
  `CMPLTINSTCNT` int(11) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMCODE` varchar(20) DEFAULT NULL,
  `STARTDATE` date DEFAULT NULL,
  `ENDDATE` date DEFAULT NULL,
  `PARTCODE` varchar(20) DEFAULT NULL,
  `PARTNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`STRTGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BPM_STRTG`
--

LOCK TABLES `BPM_STRTG` WRITE;
/*!40000 ALTER TABLE `BPM_STRTG` DISABLE KEYS */;
/*!40000 ALTER TABLE `BPM_STRTG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BPM_STRTG_PARENTMAPPING`
--

DROP TABLE IF EXISTS `BPM_STRTG_PARENTMAPPING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BPM_STRTG_PARENTMAPPING` (
  `STRTGID` int(11) DEFAULT NULL,
  `PARENTID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BPM_STRTG_PARENTMAPPING`
--

LOCK TABLES `BPM_STRTG_PARENTMAPPING` WRITE;
/*!40000 ALTER TABLE `BPM_STRTG_PARENTMAPPING` DISABLE KEYS */;
/*!40000 ALTER TABLE `BPM_STRTG_PARENTMAPPING` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BPM_TAG`
--

DROP TABLE IF EXISTS `BPM_TAG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BPM_TAG` (
  `TAGID` int(11) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `COMCODE` varchar(20) NOT NULL,
  PRIMARY KEY (`TAGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BPM_TAG`
--

LOCK TABLES `BPM_TAG` WRITE;
/*!40000 ALTER TABLE `BPM_TAG` DISABLE KEYS */;
INSERT INTO `BPM_TAG` VALUES (1,'01. 업무요청','uEngine'),(2,'02. 이슈처리','uEngine');
/*!40000 ALTER TABLE `BPM_TAG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BPM_TAGMAPPING`
--

DROP TABLE IF EXISTS `BPM_TAGMAPPING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BPM_TAGMAPPING` (
  `TAGID` int(11) NOT NULL,
  `INSTID` int(11) NOT NULL,
  `ROOTINSTID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BPM_TAGMAPPING`
--

LOCK TABLES `BPM_TAGMAPPING` WRITE;
/*!40000 ALTER TABLE `BPM_TAGMAPPING` DISABLE KEYS */;
INSERT INTO `BPM_TAGMAPPING` VALUES (1,5,5),(2,22,22),(2,24,24),(2,26,26),(2,28,28);
/*!40000 ALTER TABLE `BPM_TAGMAPPING` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BPM_TIME_DIM`
--

DROP TABLE IF EXISTS `BPM_TIME_DIM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BPM_TIME_DIM` (
  `TIME_ID` int(11) NOT NULL,
  `YR` int(11) DEFAULT NULL,
  `MNTH` int(11) DEFAULT NULL,
  `QUTR` int(11) DEFAULT NULL,
  `DAY` int(11) DEFAULT NULL,
  `MODDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`TIME_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BPM_TIME_DIM`
--

LOCK TABLES `BPM_TIME_DIM` WRITE;
/*!40000 ALTER TABLE `BPM_TIME_DIM` DISABLE KEYS */;
/*!40000 ALTER TABLE `BPM_TIME_DIM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMMENTS`
--

DROP TABLE IF EXISTS `COMMENTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COMMENTS` (
  `COMMENTID` int(11) NOT NULL,
  `EMPCODE` varchar(20) NOT NULL,
  `CONTENTTYPE` int(11) NOT NULL,
  `CONTENTID` int(11) NOT NULL,
  `ISGOOD` int(11) DEFAULT '0',
  `COMMENT` varchar(280) DEFAULT NULL,
  `MODDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`COMMENTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMMENTS`
--

LOCK TABLES `COMMENTS` WRITE;
/*!40000 ALTER TABLE `COMMENTS` DISABLE KEYS */;
/*!40000 ALTER TABLE `COMMENTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMTABLE`
--

DROP TABLE IF EXISTS `COMTABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COMTABLE` (
  `COMCODE` varchar(20) NOT NULL DEFAULT '',
  `COMNAME` varchar(30) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `ISDELETED` varchar(1) DEFAULT '0',
  PRIMARY KEY (`COMCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMTABLE`
--

LOCK TABLES `COMTABLE` WRITE;
/*!40000 ALTER TABLE `COMTABLE` DISABLE KEYS */;
INSERT INTO `COMTABLE` VALUES ('1401720840','1401720840',NULL,'0'),('MasterSystemCompany','Master System Company',NULL,'0'),('uEngine','uEngine Solutions',NULL,'0');
/*!40000 ALTER TABLE `COMTABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DOC_COMMENTS`
--

DROP TABLE IF EXISTS `DOC_COMMENTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DOC_COMMENTS` (
  `ID` int(11) NOT NULL,
  `INSTANCE_ID` int(11) DEFAULT NULL,
  `CONTENTS` varchar(3000) DEFAULT NULL,
  `OPT_TYPE` varchar(100) DEFAULT NULL,
  `EMPNO` varchar(20) DEFAULT NULL,
  `EMPNAME` varchar(100) DEFAULT NULL,
  `EMPTITLE` varchar(100) DEFAULT NULL,
  `TRACINGTAG` varchar(10) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(20) DEFAULT NULL,
  `APPRTITLE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DOC_COMMENTS`
--

LOCK TABLES `DOC_COMMENTS` WRITE;
/*!40000 ALTER TABLE `DOC_COMMENTS` DISABLE KEYS */;
/*!40000 ALTER TABLE `DOC_COMMENTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMPTABLE`
--

DROP TABLE IF EXISTS `EMPTABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EMPTABLE` (
  `EMPCODE` varchar(20) NOT NULL DEFAULT '',
  `EMPNAME` varchar(100) DEFAULT NULL,
  `PASSWORD` varchar(20) DEFAULT NULL,
  `ISADMIN` int(11) DEFAULT NULL,
  `JIKNAME` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `PARTCODE` varchar(20) DEFAULT NULL,
  `GLOBALCOM` varchar(20) DEFAULT NULL,
  `ISDELETED` varchar(1) DEFAULT '0',
  `LOCALE` varchar(2) DEFAULT 'en',
  `NATEON` varchar(50) DEFAULT NULL,
  `MSN` varchar(50) DEFAULT NULL,
  `MOBILECMP` varchar(50) DEFAULT NULL,
  `MOBILENO` varchar(50) DEFAULT NULL,
  `FACEBOOKID` varchar(100) DEFAULT NULL,
  `FACEBOOKPASSWORD` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`EMPCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMPTABLE`
--

LOCK TABLES `EMPTABLE` WRITE;
/*!40000 ALTER TABLE `EMPTABLE` DISABLE KEYS */;
INSERT INTO `EMPTABLE` VALUES ('1401720840','1401720840','test',1,NULL,'jyjang@uengine.org','1401720840','1401720840','0','ko',NULL,NULL,NULL,NULL,'',NULL),('CJK','Jaekil Choi','test',0,'과장',NULL,'PM','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('CJS','Jisun Choi','test',0,'사원',NULL,'QA','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('CWS','Wooseok Choi','test',0,'사원',NULL,'PM','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('HJR','Jeongryeon Ha','test',0,'과장',NULL,'PI','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('JSY','Sungyeol Jung','test',0,'과장',NULL,'CON2','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('KEM','Eunmi Kim','test',0,'사원',NULL,'MANAGEMENT','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('KHK','Hyungkook Kim','test',0,'사원',NULL,'SOLUTION','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('KHN','Hannah Kim','test',0,'대리',NULL,'MAR','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('KSG','Seogu Kang','test',0,'대리',NULL,'SALES','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('KTW','Taiwook Kang','test',0,'과장',NULL,'PI','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('KYT','Youngtak Kim','test',0,'과장',NULL,'SOLUTION','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('LDH','Donghyun Lee','test',0,'과장',NULL,'QA','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('LHS','Hyeseon Lee','test',0,'대리',NULL,'MANAGEMENT','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('LJW','Jinwon Lee','test',0,'사원',NULL,'SOLUTION','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('MasterSystemAdmin','Master System Admin User','test',-1,NULL,NULL,'MasterSystemPart','MasterSystemCompany','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('PDJ','DongJo Park','test',0,'차장',NULL,'PI','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('PJJ','Jongjun Park','test',0,'대리',NULL,'CON1','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('PMK','Myeongkyun Bok','test',0,'차장',NULL,'QA','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('RSH','Suengho Ryu','test',0,'사원',NULL,'CON2','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('SHJ','Hojoon sung','test',0,'과장',NULL,'MAR','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('SSH','Sanghyo Song','test',0,'이사',NULL,'MARKETING','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('test','Tester','test',1,'Tester',NULL,'','uEngine','0','en',NULL,NULL,NULL,NULL,'100002969720934',NULL),('test_kp','Tester_ko','test',1,'Tester',NULL,'','uEngine','0','ko',NULL,NULL,NULL,NULL,NULL,NULL),('uEngineAdmin','uEngine Admin','test',1,'Tenant Admin',NULL,'uEngineTenantAdmin','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('YBS','ByeoungSun Yun','test',0,'사원',NULL,'CON1','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('YJY','Jooyong Yook','test',0,'차장',NULL,'CON1','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('ZSZ','Shuzhu Zhang','test',0,'대리',NULL,'MAR','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `EMPTABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMP_PROP_TABLE`
--

DROP TABLE IF EXISTS `EMP_PROP_TABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EMP_PROP_TABLE` (
  `PROPID` int(11) NOT NULL,
  `COMCODE` varchar(20) NOT NULL,
  `EMPCODE` varchar(20) NOT NULL,
  `PROPKEY` varchar(40) NOT NULL,
  `PROPVALUE` varchar(100) NOT NULL,
  PRIMARY KEY (`PROPID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMP_PROP_TABLE`
--

LOCK TABLES `EMP_PROP_TABLE` WRITE;
/*!40000 ALTER TABLE `EMP_PROP_TABLE` DISABLE KEYS */;
INSERT INTO `EMP_PROP_TABLE` VALUES (1,'uEngine','JJY','loggedMailId','test'),(2,'uEngine','JJY','loggedMailPw','test1234');
/*!40000 ALTER TABLE `EMP_PROP_TABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FAVORITE_CONTACTS`
--

DROP TABLE IF EXISTS `FAVORITE_CONTACTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FAVORITE_CONTACTS` (
  `FAV_CONTACT_ID` int(11) NOT NULL DEFAULT '0',
  `EMPCODE` varchar(20) DEFAULT NULL,
  `FRIENDTYPE` int(11) DEFAULT NULL,
  `FRIENDID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`FAV_CONTACT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FAVORITE_CONTACTS`
--

LOCK TABLES `FAVORITE_CONTACTS` WRITE;
/*!40000 ALTER TABLE `FAVORITE_CONTACTS` DISABLE KEYS */;
INSERT INTO `FAVORITE_CONTACTS` VALUES (1,'test',0,'PJJ'),(50,'test',2,'115300695203836');
/*!40000 ALTER TABLE `FAVORITE_CONTACTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INSTLISTVIEW`
--

DROP TABLE IF EXISTS `INSTLISTVIEW`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `INSTLISTVIEW` (
  `INSTID` int(11) DEFAULT NULL,
  `DEFVERID` int(11) DEFAULT NULL,
  `DEFID` int(11) DEFAULT NULL,
  `DEFNAME` varchar(255) DEFAULT NULL,
  `DEFPATH` varchar(255) DEFAULT NULL,
  `DEFMODDATE` datetime DEFAULT NULL,
  `STARTEDDATE` datetime DEFAULT NULL,
  `FINISHEDDATE` datetime DEFAULT NULL,
  `DUEDATE` datetime DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  `INFO` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `ISDELETED` int(11) DEFAULT NULL,
  `ISADHOC` int(11) DEFAULT NULL,
  `ISARCHIVE` int(11) DEFAULT NULL,
  `ISSUBPROCESS` int(11) DEFAULT NULL,
  `ISEVENTHANDLER` int(11) DEFAULT NULL,
  `ROOTINSTID` int(11) DEFAULT NULL,
  `MAININSTID` int(11) DEFAULT NULL,
  `MAINDEFVERID` int(11) DEFAULT NULL,
  `MAINACTTRCTAG` varchar(20) DEFAULT NULL,
  `MAINEXECSCOPE` varchar(20) DEFAULT NULL,
  `ABSTRCPATH` varchar(200) DEFAULT NULL,
  `DONTRETURN` int(11) DEFAULT NULL,
  `MODDATE` datetime DEFAULT NULL,
  `EXT1` varchar(100) DEFAULT NULL,
  `INITEP` varchar(100) DEFAULT NULL,
  `INITRSNM` varchar(100) DEFAULT NULL,
  `CURREP` varchar(100) DEFAULT NULL,
  `CURRRSNM` varchar(100) DEFAULT NULL,
  `STRATEGYID` int(11) DEFAULT NULL,
  `SAIDGOODUSERSCOUNT` bigint(21) DEFAULT NULL,
  `SAIDGOODUSERSNAME` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INSTLISTVIEW`
--

LOCK TABLES `INSTLISTVIEW` WRITE;
/*!40000 ALTER TABLE `INSTLISTVIEW` DISABLE KEYS */;
/*!40000 ALTER TABLE `INSTLISTVIEW` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_ARROWLINK`
--

DROP TABLE IF EXISTS `MM_ARROWLINK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_ARROWLINK` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NODE_ID` bigint(20) NOT NULL,
  `COLOR` varchar(10) DEFAULT NULL,
  `DESTINATION` varchar(50) DEFAULT NULL,
  `ENDARROW` varchar(20) DEFAULT NULL,
  `ENDINCLINATION` varchar(20) DEFAULT NULL,
  `IDENTITY` varchar(50) DEFAULT NULL,
  `STARTARROW` varchar(20) DEFAULT NULL,
  `STARTINCLINATION` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_ARROWLINK`
--

LOCK TABLES `MM_ARROWLINK` WRITE;
/*!40000 ALTER TABLE `MM_ARROWLINK` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_ARROWLINK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_ATTRIBUTE`
--

DROP TABLE IF EXISTS `MM_ATTRIBUTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_ATTRIBUTE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NODE_ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `VALUE` longtext,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_ATTRIBUTE`
--

LOCK TABLES `MM_ATTRIBUTE` WRITE;
/*!40000 ALTER TABLE `MM_ATTRIBUTE` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_ATTRIBUTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_ATTRIBUTE_REGISTRY`
--

DROP TABLE IF EXISTS `MM_ATTRIBUTE_REGISTRY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_ATTRIBUTE_REGISTRY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MAP_REVISION_ID` bigint(20) NOT NULL,
  `SHOW_ATTRIBUTES` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_ATTRIBUTE_REGISTRY`
--

LOCK TABLES `MM_ATTRIBUTE_REGISTRY` WRITE;
/*!40000 ALTER TABLE `MM_ATTRIBUTE_REGISTRY` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_ATTRIBUTE_REGISTRY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_CATEGORIES`
--

DROP TABLE IF EXISTS `MM_CATEGORIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_CATEGORIES` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `LFT` int(10) unsigned DEFAULT NULL,
  `RGT` int(10) unsigned DEFAULT NULL,
  `PARENTID` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_CATEGORIES`
--

LOCK TABLES `MM_CATEGORIES` WRITE;
/*!40000 ALTER TABLE `MM_CATEGORIES` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_CATEGORIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_CLOUD`
--

DROP TABLE IF EXISTS `MM_CLOUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_CLOUD` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NODE_ID` bigint(20) NOT NULL,
  `COLOR` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_CLOUD`
--

LOCK TABLES `MM_CLOUD` WRITE;
/*!40000 ALTER TABLE `MM_CLOUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_CLOUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_EDGE`
--

DROP TABLE IF EXISTS `MM_EDGE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_EDGE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NODE_ID` bigint(20) NOT NULL,
  `COLOR` varchar(10) DEFAULT NULL,
  `STYLE` varchar(20) DEFAULT NULL,
  `WIDTH` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_EDGE`
--

LOCK TABLES `MM_EDGE` WRITE;
/*!40000 ALTER TABLE `MM_EDGE` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_EDGE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_FONT`
--

DROP TABLE IF EXISTS `MM_FONT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_FONT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NODE_ID` bigint(20) NOT NULL,
  `BOLD` varchar(5) DEFAULT NULL,
  `ITALIC` varchar(5) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `SIZE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_FONT`
--

LOCK TABLES `MM_FONT` WRITE;
/*!40000 ALTER TABLE `MM_FONT` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_FONT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_FOREIGNOBJECT`
--

DROP TABLE IF EXISTS `MM_FOREIGNOBJECT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_FOREIGNOBJECT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NODE_ID` bigint(20) NOT NULL,
  `CONTENT` longtext,
  `WIDTH` bigint(20) DEFAULT '0',
  `HEIGHT` bigint(20) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_FOREIGNOBJECT`
--

LOCK TABLES `MM_FOREIGNOBJECT` WRITE;
/*!40000 ALTER TABLE `MM_FOREIGNOBJECT` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_FOREIGNOBJECT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_GROUP`
--

DROP TABLE IF EXISTS `MM_GROUP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_GROUP` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SUMMARY` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `USERID` bigint(20) unsigned NOT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `POLICY` bigint(20) unsigned NOT NULL,
  `CATEGORYID` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_GROUP`
--

LOCK TABLES `MM_GROUP` WRITE;
/*!40000 ALTER TABLE `MM_GROUP` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_GROUP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_GROUP_MEMBER`
--

DROP TABLE IF EXISTS `MM_GROUP_MEMBER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_GROUP_MEMBER` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `GROUPID` bigint(20) unsigned NOT NULL,
  `USERID` bigint(20) unsigned NOT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `STATUS` bigint(20) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_GROUP_MEMBER`
--

LOCK TABLES `MM_GROUP_MEMBER` WRITE;
/*!40000 ALTER TABLE `MM_GROUP_MEMBER` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_GROUP_MEMBER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_GROUP_MEMBER_STATUS_TYPE`
--

DROP TABLE IF EXISTS `MM_GROUP_MEMBER_STATUS_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_GROUP_MEMBER_STATUS_TYPE` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SHORTNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_GROUP_MEMBER_STATUS_TYPE`
--

LOCK TABLES `MM_GROUP_MEMBER_STATUS_TYPE` WRITE;
/*!40000 ALTER TABLE `MM_GROUP_MEMBER_STATUS_TYPE` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_GROUP_MEMBER_STATUS_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_GROUP_PASSWORD`
--

DROP TABLE IF EXISTS `MM_GROUP_PASSWORD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_GROUP_PASSWORD` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `GROUPID` bigint(20) unsigned NOT NULL,
  `PASSWORD` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_GROUP_PASSWORD`
--

LOCK TABLES `MM_GROUP_PASSWORD` WRITE;
/*!40000 ALTER TABLE `MM_GROUP_PASSWORD` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_GROUP_PASSWORD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_GROUP_POLICY_TYPE`
--

DROP TABLE IF EXISTS `MM_GROUP_POLICY_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_GROUP_POLICY_TYPE` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SHORTNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_GROUP_POLICY_TYPE`
--

LOCK TABLES `MM_GROUP_POLICY_TYPE` WRITE;
/*!40000 ALTER TABLE `MM_GROUP_POLICY_TYPE` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_GROUP_POLICY_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_HOOK`
--

DROP TABLE IF EXISTS `MM_HOOK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_HOOK` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NODE_ID` bigint(20) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_HOOK`
--

LOCK TABLES `MM_HOOK` WRITE;
/*!40000 ALTER TABLE `MM_HOOK` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_HOOK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_ICON`
--

DROP TABLE IF EXISTS `MM_ICON`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_ICON` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NODE_ID` bigint(20) NOT NULL,
  `BUILTIN` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_ICON`
--

LOCK TABLES `MM_ICON` WRITE;
/*!40000 ALTER TABLE `MM_ICON` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_ICON` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_MAP`
--

DROP TABLE IF EXISTS `MM_MAP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_MAP` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(1000) NOT NULL,
  `VERSION` varchar(20) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  `MAP_KEY` varchar(100) NOT NULL,
  `VIEWCOUNT` int(11) DEFAULT NULL,
  `MAP_STYLE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_MAP`
--

LOCK TABLES `MM_MAP` WRITE;
/*!40000 ALTER TABLE `MM_MAP` DISABLE KEYS */;
INSERT INTO `MM_MAP` VALUES (1,'uEngine Solutions','0.9.0','2011-09-25 13:35:26','ZWE2MmI4MDEtZWQxOS00ZjIxLWJjM2ItN2EyMTIyNzY3ZTll',NULL,NULL),(2,'1401720840','0.9.0','2011-09-26 21:10:47','NGVkZmYzYzctNDRiMy00ZTQxLWE5NDItMWNlNzE2NjIxZTQ4',NULL,NULL),(3,'Reply','0.9.0','2011-10-15 17:01:53','ZTRiNGMyZTQtNjcyYi00YmEzLTk2YTAtYjg0MWMxOTYyNjcy',NULL,NULL);
/*!40000 ALTER TABLE `MM_MAP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_MAP_OWNER`
--

DROP TABLE IF EXISTS `MM_MAP_OWNER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_MAP_OWNER` (
  `ID` bigint(20) NOT NULL,
  `MAPID` bigint(20) NOT NULL,
  `OWNER` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_MAP_OWNER`
--

LOCK TABLES `MM_MAP_OWNER` WRITE;
/*!40000 ALTER TABLE `MM_MAP_OWNER` DISABLE KEYS */;
INSERT INTO `MM_MAP_OWNER` VALUES (1,1,'uEngine'),(2,2,'1401720840');
/*!40000 ALTER TABLE `MM_MAP_OWNER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_MAP_REVISION`
--

DROP TABLE IF EXISTS `MM_MAP_REVISION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_MAP_REVISION` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MAP_ID` bigint(20) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  `CURRENT_REVISION_P` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_MAP_REVISION`
--

LOCK TABLES `MM_MAP_REVISION` WRITE;
/*!40000 ALTER TABLE `MM_MAP_REVISION` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_MAP_REVISION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_MAP_TIMELINE`
--

DROP TABLE IF EXISTS `MM_MAP_TIMELINE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_MAP_TIMELINE` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `MAP_ID` bigint(20) unsigned NOT NULL,
  `XML` longblob NOT NULL,
  `SAVED` bigint(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_MAP_TIMELINE`
--

LOCK TABLES `MM_MAP_TIMELINE` WRITE;
/*!40000 ALTER TABLE `MM_MAP_TIMELINE` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_MAP_TIMELINE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_NODE`
--

DROP TABLE IF EXISTS `MM_NODE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_NODE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MAP_REVISION_ID` bigint(20) NOT NULL,
  `BACKGROUND_COLOR` varchar(10) DEFAULT NULL,
  `COLOR` varchar(10) DEFAULT NULL,
  `FOLDED` varchar(5) DEFAULT NULL,
  `IDENTITY` varchar(50) DEFAULT NULL,
  `LINK` varchar(1000) DEFAULT NULL,
  `POSITION` varchar(10) DEFAULT NULL,
  `STYLE` varchar(10) DEFAULT NULL,
  `TEXT` longtext,
  `CREATED` varchar(15) DEFAULT NULL,
  `MODIFIED` varchar(15) DEFAULT NULL,
  `HGAP` int(11) DEFAULT NULL,
  `VGAP` int(11) DEFAULT NULL,
  `VSHIFT` int(11) DEFAULT NULL,
  `ENCRYPTED_CONTENT` longtext,
  `NODE_TYPE` varchar(15) DEFAULT NULL,
  `LFT` int(11) DEFAULT NULL,
  `RGT` int(11) DEFAULT NULL,
  `PARENT_ID` bigint(20) DEFAULT NULL,
  `REFERENCE_NODES` longtext,
  `USERJSON` varchar(4000) DEFAULT NULL,
  `WRITER` varchar(200) DEFAULT NULL,
  `ISONTOLOGY` int(11) DEFAULT '0',
  `FACEBOOK_ID` varchar(500) DEFAULT NULL,
  `MAP_ID` bigint(20) unsigned NOT NULL,
  `CREATOR` varchar(20) NOT NULL,
  `CREATOR_IP` varchar(40) DEFAULT NULL,
  `MODIFIER` varchar(20) NOT NULL,
  `MODIFIER_IP` varchar(40) DEFAULT NULL,
  `EXTRA_DATA` text,
  `IIDENTITY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=276 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_NODE`
--

LOCK TABLES `MM_NODE` WRITE;
/*!40000 ALTER TABLE `MM_NODE` DISABLE KEYS */;
INSERT INTO `MM_NODE` VALUES (260,0,'#660000','#ffffff','false','ID_901618295',NULL,NULL,NULL,'uEngine Solutions','1316925326519','1317039442491',-295,NULL,-30,NULL,NULL,1,14,0,NULL,NULL,NULL,0,NULL,1,'',NULL,'test','0:0:0:0:0:0:0:1%0',NULL,NULL),(261,0,'#c4a0b3',NULL,'false','ID_1635425370',NULL,'right',NULL,'중간에 애매한거 보냈다가 오류나면','1316934906809','1317045666649',NULL,NULL,NULL,NULL,NULL,2,11,260,NULL,NULL,NULL,0,NULL,1,'test','0:0:0:0:0:0:0:1%0','test','0:0:0:0:0:0:0:1%0',NULL,NULL),(262,0,'#DEBCC3',NULL,'false','ID_1712112131',NULL,NULL,NULL,'음.. 저장이 좔 되는균','1316934935950','1317017410523',NULL,NULL,NULL,NULL,NULL,3,4,261,NULL,NULL,NULL,0,NULL,1,'test','0:0:0:0:0:0:0:1%0','test','0:0:0:0:0:0:0:1%0',NULL,NULL),(265,0,'#CBBEC5',NULL,'false','ID_1016695379',NULL,NULL,NULL,'테스트합니다','1316935578781','1317039456740',NULL,NULL,NULL,NULL,NULL,5,10,261,NULL,NULL,NULL,0,NULL,1,'test','0:0:0:0:0:0:0:1%0','test','0:0:0:0:0:0:0:1%0',NULL,NULL),(268,0,'#D5DED9','#ffffff','false','ID_1609238373',NULL,NULL,NULL,'이제 됐어요... ㅜㅜ','1317016135427','1317039458042',7,NULL,-6,NULL,NULL,6,9,265,NULL,NULL,NULL,0,NULL,1,'test','0:0:0:0:0:0:0:1%0','test','0:0:0:0:0:0:0:1%0',NULL,NULL),(269,0,'#c4a0b3',NULL,'false','ID_194240397',NULL,'right',NULL,'dfsdad','1317017359011','1317019342047',92,NULL,-11,NULL,NULL,7,8,268,NULL,NULL,NULL,0,NULL,1,'test','0:0:0:0:0:0:0:1%0','test','0:0:0:0:0:0:0:1%0',NULL,NULL),(270,0,'#660000','#ffffff','false','ID_920241272',NULL,NULL,NULL,'1401720840','1317039047320','1317039056291',-154,NULL,-15,NULL,NULL,1,8,0,NULL,NULL,NULL,0,NULL,2,'',NULL,'1401720840','0:0:0:0:0:0:0:1%0',NULL,NULL),(271,0,'#c4a0b3',NULL,'false','ID_1268584850',NULL,'right',NULL,'뭘 입력한다','1317039059341','1317039065394',NULL,NULL,NULL,NULL,NULL,2,3,270,NULL,NULL,NULL,0,NULL,2,'1401720840','0:0:0:0:0:0:0:1%0','1401720840','0:0:0:0:0:0:0:1%0',NULL,NULL),(272,0,'#829ddb',NULL,'false','ID_1476746703',NULL,'right',NULL,'페이스북 테스트입니다','1317039065513','1317039310376',NULL,NULL,NULL,NULL,NULL,4,7,270,NULL,NULL,NULL,0,NULL,2,'1401720840','0:0:0:0:0:0:0:1%0','1401720840','0:0:0:0:0:0:0:1%0',NULL,NULL),(273,0,'#A5C7FB',NULL,'false','ID_523865388',NULL,NULL,NULL,'죄수번호가 뜨는군요','1317039070943','1317039079006',NULL,NULL,NULL,NULL,NULL,5,6,272,NULL,NULL,NULL,0,NULL,2,'1401720840','0:0:0:0:0:0:0:1%0','1401720840','0:0:0:0:0:0:0:1%0',NULL,NULL),(274,0,'#829ddb',NULL,'false','ID_1998165915',NULL,'right',NULL,'담벼락에 게시한 내용에 대한 친구들의 댓글을 자동으로 수집해와서 내 지식에 수집해주는 기능','1317043952588','1317045055602',NULL,NULL,NULL,NULL,NULL,12,13,260,NULL,NULL,NULL,0,NULL,1,'test','0:0:0:0:0:0:0:1%0','test','0:0:0:0:0:0:0:1%0',NULL,NULL),(275,0,NULL,NULL,NULL,'ID_1536885496',NULL,NULL,NULL,'Reply','1318665713476','1318665713476',NULL,NULL,NULL,NULL,NULL,1,2,0,NULL,NULL,NULL,0,NULL,3,'',NULL,'',NULL,NULL,NULL);
/*!40000 ALTER TABLE `MM_NODE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_PARAMETERS`
--

DROP TABLE IF EXISTS `MM_PARAMETERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_PARAMETERS` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `HOOK_ID` bigint(20) NOT NULL,
  `REMINDUSERAT` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_PARAMETERS`
--

LOCK TABLES `MM_PARAMETERS` WRITE;
/*!40000 ALTER TABLE `MM_PARAMETERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_PARAMETERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_PERSISTENT_LOGIN`
--

DROP TABLE IF EXISTS `MM_PERSISTENT_LOGIN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_PERSISTENT_LOGIN` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `USERID` bigint(20) unsigned NOT NULL,
  `PERSISTENT_KEY` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `LASTLOGIN` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_PERSISTENT_LOGIN`
--

LOCK TABLES `MM_PERSISTENT_LOGIN` WRITE;
/*!40000 ALTER TABLE `MM_PERSISTENT_LOGIN` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_PERSISTENT_LOGIN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_QUEUEDATA`
--

DROP TABLE IF EXISTS `MM_QUEUEDATA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_QUEUEDATA` (
  `ROOMNUMBER` longtext,
  `TEXTDATA` longtext,
  `CREATED` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_QUEUEDATA`
--

LOCK TABLES `MM_QUEUEDATA` WRITE;
/*!40000 ALTER TABLE `MM_QUEUEDATA` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_QUEUEDATA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_RICHCONTENT`
--

DROP TABLE IF EXISTS `MM_RICHCONTENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_RICHCONTENT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NODE_ID` bigint(20) NOT NULL,
  `TYPE` varchar(5) DEFAULT NULL,
  `CONTENT` longtext,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_RICHCONTENT`
--

LOCK TABLES `MM_RICHCONTENT` WRITE;
/*!40000 ALTER TABLE `MM_RICHCONTENT` DISABLE KEYS */;
INSERT INTO `MM_RICHCONTENT` VALUES (2,266,'NODE','<html>\n   <head>\n \n   </head>\n   <body>\n <p>test</p>\n   </body>\n </html>');
/*!40000 ALTER TABLE `MM_RICHCONTENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_ROLE`
--

DROP TABLE IF EXISTS `MM_ROLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_ROLE` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SHORTNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_ROLE`
--

LOCK TABLES `MM_ROLE` WRITE;
/*!40000 ALTER TABLE `MM_ROLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_ROLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_ROLE_ASSIGNMENTS`
--

DROP TABLE IF EXISTS `MM_ROLE_ASSIGNMENTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_ROLE_ASSIGNMENTS` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ROLEID` bigint(20) unsigned NOT NULL,
  `USERID` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ROLEID` (`ROLEID`,`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_ROLE_ASSIGNMENTS`
--

LOCK TABLES `MM_ROLE_ASSIGNMENTS` WRITE;
/*!40000 ALTER TABLE `MM_ROLE_ASSIGNMENTS` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_ROLE_ASSIGNMENTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_ROLE_CAPABILITIES`
--

DROP TABLE IF EXISTS `MM_ROLE_CAPABILITIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_ROLE_CAPABILITIES` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ROLEID` bigint(20) unsigned NOT NULL,
  `CAPABILITY` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `PERMISSION` bigint(10) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ROLEID` (`ROLEID`,`CAPABILITY`,`PERMISSION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_ROLE_CAPABILITIES`
--

LOCK TABLES `MM_ROLE_CAPABILITIES` WRITE;
/*!40000 ALTER TABLE `MM_ROLE_CAPABILITIES` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_ROLE_CAPABILITIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_SHARE`
--

DROP TABLE IF EXISTS `MM_SHARE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_SHARE` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `MAPID` bigint(20) unsigned NOT NULL,
  `SHARETYPE` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `MAPID` (`MAPID`,`SHARETYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_SHARE`
--

LOCK TABLES `MM_SHARE` WRITE;
/*!40000 ALTER TABLE `MM_SHARE` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_SHARE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_SHARE_GROUP`
--

DROP TABLE IF EXISTS `MM_SHARE_GROUP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_SHARE_GROUP` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `SHAREID` bigint(20) unsigned NOT NULL,
  `GROUPID` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `SHAREID` (`SHAREID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_SHARE_GROUP`
--

LOCK TABLES `MM_SHARE_GROUP` WRITE;
/*!40000 ALTER TABLE `MM_SHARE_GROUP` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_SHARE_GROUP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_SHARE_PASSWORD`
--

DROP TABLE IF EXISTS `MM_SHARE_PASSWORD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_SHARE_PASSWORD` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `SHAREID` bigint(20) unsigned NOT NULL,
  `PASSWORD` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SHAREID` (`SHAREID`,`PASSWORD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_SHARE_PASSWORD`
--

LOCK TABLES `MM_SHARE_PASSWORD` WRITE;
/*!40000 ALTER TABLE `MM_SHARE_PASSWORD` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_SHARE_PASSWORD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_SHARE_PERMISSION`
--

DROP TABLE IF EXISTS `MM_SHARE_PERMISSION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_SHARE_PERMISSION` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `SHAREID` bigint(20) unsigned NOT NULL,
  `PERMISSIONTYPE` bigint(20) unsigned NOT NULL,
  `PERMITED` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_SHARE_PERMISSION`
--

LOCK TABLES `MM_SHARE_PERMISSION` WRITE;
/*!40000 ALTER TABLE `MM_SHARE_PERMISSION` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_SHARE_PERMISSION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_SHARE_PERMISSION_TYPE`
--

DROP TABLE IF EXISTS `MM_SHARE_PERMISSION_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_SHARE_PERMISSION_TYPE` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SHORTNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_SHARE_PERMISSION_TYPE`
--

LOCK TABLES `MM_SHARE_PERMISSION_TYPE` WRITE;
/*!40000 ALTER TABLE `MM_SHARE_PERMISSION_TYPE` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_SHARE_PERMISSION_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_SHARE_TYPE`
--

DROP TABLE IF EXISTS `MM_SHARE_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_SHARE_TYPE` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SHORTNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_SHARE_TYPE`
--

LOCK TABLES `MM_SHARE_TYPE` WRITE;
/*!40000 ALTER TABLE `MM_SHARE_TYPE` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_SHARE_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MM_USER`
--

DROP TABLE IF EXISTS `MM_USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MM_USER` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `EMAIL` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `FIRSTNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `LASTNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `PASSWORD` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `AUTH` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'MANUAL',
  `CONFIRMED` tinyint(1) NOT NULL DEFAULT '0',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0',
  `CREATED` int(15) unsigned NOT NULL DEFAULT '0',
  `LAST_ACCESS` int(15) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MM_USER`
--

LOCK TABLES `MM_USER` WRITE;
/*!40000 ALTER TABLE `MM_USER` DISABLE KEYS */;
/*!40000 ALTER TABLE `MM_USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Node`
--

DROP TABLE IF EXISTS `Node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Node` (
  `text` varchar(100) DEFAULT NULL,
  `parent` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Node`
--

LOCK TABLES `Node` WRITE;
/*!40000 ALTER TABLE `Node` DISABLE KEYS */;
INSERT INTO `Node` VALUES ('adfsdfds',NULL),('sfsdf',NULL),('fsfd',NULL),('dsfsfdsfdfdfsghh',NULL),('dgdg',NULL),('23456',NULL);
/*!40000 ALTER TABLE `Node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PARTTABLE`
--

DROP TABLE IF EXISTS `PARTTABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PARTTABLE` (
  `GLOBALCOM` varchar(20) DEFAULT NULL,
  `PARTCODE` varchar(20) NOT NULL,
  `PARTNAME` varchar(30) DEFAULT NULL,
  `PARENT_PARTCODE` varchar(20) DEFAULT NULL,
  `ISDELETED` varchar(1) DEFAULT '0',
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMCODE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PARTCODE`),
  KEY `FK3B63679B4506931C` (`COMCODE`),
  CONSTRAINT `FK3B63679B4506931C` FOREIGN KEY (`COMCODE`) REFERENCES `COMTABLE` (`COMCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PARTTABLE`
--

LOCK TABLES `PARTTABLE` WRITE;
/*!40000 ALTER TABLE `PARTTABLE` DISABLE KEYS */;
INSERT INTO `PARTTABLE` VALUES ('1401720840','1401720840','1401720840',NULL,'0',NULL,NULL),('uEngine','CEO','CEO',NULL,'0','CEO',NULL),('uEngine','CON1','Consulting 1Team','CONSULTING','0','컨설팅 1팀',NULL),('uEngine','CON2','Consulting 2Team','CONSULTING','0','컨설팅 2팀',NULL),('uEngine','CONSULTING','CONSULTING',NULL,'0','컨설팅 부서',NULL),('uEngine','DEV','DEVELOPMENT',NULL,'0','연구개발 부서',NULL),('uEngine','MANAGEMENT','Management Support Team','MARKETING','0','경영지원팀',NULL),('uEngine','MAR','Marketing Team','MARKETING','0','마케팅 팀',NULL),('uEngine','MARKETING','MARKETING AND SALES',NULL,'0','마케팅&세일즈 부서',NULL),('MasterSystemCompany','MasterSystemPart','Master System Admin Group',NULL,'0',NULL,NULL),('uEngine','PI','PI Team','DEV','0','PI팀',NULL),('uEngine','PM','PM Team','DEV','0','PM팀',NULL),('uEngine','QA','QA Team','DEV','0','QA팀',NULL),('uEngine','SALES','Sales Team','MARKETING','0','세일즈 팀',NULL),('uEngine','SOLUTION','Solution Team','CONSULTING','0','솔루션 사업팀',NULL),('uEngine','uEngineTenantAdmin','uEngine Tenand Admin',NULL,'0',NULL,NULL);
/*!40000 ALTER TABLE `PARTTABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROCESSMARKET_CATEGORY`
--

DROP TABLE IF EXISTS `PROCESSMARKET_CATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROCESSMARKET_CATEGORY` (
  `CATEGORYID` int(11) NOT NULL,
  `CATEGORYNAME` varchar(255) NOT NULL,
  `PARENTID` int(11) DEFAULT '-1',
  `MODDATE` datetime DEFAULT NULL,
  `ISDELETED` int(11) DEFAULT '0',
  PRIMARY KEY (`CATEGORYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROCESSMARKET_CATEGORY`
--

LOCK TABLES `PROCESSMARKET_CATEGORY` WRITE;
/*!40000 ALTER TABLE `PROCESSMARKET_CATEGORY` DISABLE KEYS */;
/*!40000 ALTER TABLE `PROCESSMARKET_CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROCESSMARKET_ITEM`
--

DROP TABLE IF EXISTS `PROCESSMARKET_ITEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROCESSMARKET_ITEM` (
  `ITEMID` int(11) NOT NULL,
  `CATEGORYID` int(11) NOT NULL,
  `ITEMNAME` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(1000) NOT NULL,
  `VERSION` int(11) NOT NULL,
  `COMCODE` varchar(30) NOT NULL,
  `PRICE` int(11) NOT NULL,
  `FILEPATH` varchar(255) NOT NULL,
  `LOGOIMAGEFILEPATH` varchar(255) DEFAULT NULL,
  `IMAGE1FILEPATH` varchar(255) DEFAULT NULL,
  `IMAGE2FILEPATH` varchar(255) DEFAULT NULL,
  `IMAGE3FILEPATH` varchar(255) DEFAULT NULL,
  `IMAGE4FILEPATH` varchar(255) DEFAULT NULL,
  `IMAGE5FILEPATH` varchar(255) DEFAULT NULL,
  `REGDATE` date NOT NULL,
  `STARPOINT` int(11) DEFAULT NULL,
  `ISDELETED` int(11) DEFAULT '0',
  `MODDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ITEMID`),
  KEY `FKAB6087874506931C` (`COMCODE`),
  CONSTRAINT `FKAB6087874506931C` FOREIGN KEY (`COMCODE`) REFERENCES `COMTABLE` (`COMCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROCESSMARKET_ITEM`
--

LOCK TABLES `PROCESSMARKET_ITEM` WRITE;
/*!40000 ALTER TABLE `PROCESSMARKET_ITEM` DISABLE KEYS */;
/*!40000 ALTER TABLE `PROCESSMARKET_ITEM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROCESSMARKET_PURCHASE`
--

DROP TABLE IF EXISTS `PROCESSMARKET_PURCHASE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROCESSMARKET_PURCHASE` (
  `PURCHASEID` int(11) NOT NULL,
  `ITEMID` int(11) NOT NULL,
  `COMCODE` varchar(30) NOT NULL,
  `PRICE` int(11) NOT NULL,
  `PURCHASEDDATE` datetime NOT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  `STARPOINT` int(11) DEFAULT NULL,
  `ISDELETED` int(11) DEFAULT '0',
  `MODDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`PURCHASEID`),
  KEY `FK9305AF757A09946B` (`ITEMID`),
  KEY `FK9305AF754506931C` (`COMCODE`),
  CONSTRAINT `FK9305AF754506931C` FOREIGN KEY (`COMCODE`) REFERENCES `COMTABLE` (`COMCODE`),
  CONSTRAINT `FK9305AF757A09946B` FOREIGN KEY (`ITEMID`) REFERENCES `PROCESSMARKET_ITEM` (`ITEMID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROCESSMARKET_PURCHASE`
--

LOCK TABLES `PROCESSMARKET_PURCHASE` WRITE;
/*!40000 ALTER TABLE `PROCESSMARKET_PURCHASE` DISABLE KEYS */;
/*!40000 ALTER TABLE `PROCESSMARKET_PURCHASE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Posting`
--

DROP TABLE IF EXISTS `Posting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Posting` (
  `writer` varchar(100) DEFAULT NULL,
  `document` varchar(300) DEFAULT NULL,
  `likeit` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Posting`
--

LOCK TABLES `Posting` WRITE;
/*!40000 ALTER TABLE `Posting` DISABLE KEYS */;
INSERT INTO `Posting` VALUES ('jinyoung jang','ddsfd',1),('jinyoung jang','sdfsdf',1),('jinyoung jang','sfadf',0),('jinyoung jang','xxxxx',0),('jinyoung jang','eee',1),('jinyoung jang',NULL,0),('jinyoung jang',NULL,0),('jinyoung jang','testse',1),('jinyoung jang','tsetsetset',0),('jinyoung jang','dfsd',0),('jinyoung jang','sadasdad',0),('jinyoung jang','ssss',1),('jinyoung jang','ss',1),('jinyoung jang','dsfsdf',1),('jinyoung jang','sdfsdfsdfsdfsd',1),('jinyoung jang','sdfsadf',1),('jinyoung jang','fsfsdf',1),('jinyoung jang','efsfd',0),('jinyoung jang','dsfadfdasfasdf',0),('jinyoung jang','estet',0),('jinyoung jang','test',0),('jinyoung jang','sdfdsfds',0),('jinyoung jang','tstsesete',0),('jinyoung jang','dsfdf',0),('jinyoung jang','sfkakafsdjskdfjlfjksald',0),('jinyoung jang','ddd',0);
/*!40000 ALTER TABLE `Posting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROLETABLE`
--

DROP TABLE IF EXISTS `ROLETABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROLETABLE` (
  `ROLECODE` varchar(20) NOT NULL,
  `COMCODE` varchar(20) DEFAULT NULL,
  `DESCR` varchar(255) DEFAULT NULL,
  `ISDELETED` varchar(1) DEFAULT '0',
  PRIMARY KEY (`ROLECODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLETABLE`
--

LOCK TABLES `ROLETABLE` WRITE;
/*!40000 ALTER TABLE `ROLETABLE` DISABLE KEYS */;
INSERT INTO `ROLETABLE` VALUES ('1401720840','1401720840','1401720840','0'),('DepartmentReader','uEngine','DepartmentReader','0'),('MasterSystemAdmin','MasterSystemCompany','Master System Admin Role for SaaSable ProcessCodi','0'),('TeamReader','uEngine','TeamReader','0');
/*!40000 ALTER TABLE `ROLETABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROLEUSERTABLE`
--

DROP TABLE IF EXISTS `ROLEUSERTABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROLEUSERTABLE` (
  `ROLECODE` varchar(20) NOT NULL,
  `EMPCODE` varchar(20) NOT NULL,
  PRIMARY KEY (`ROLECODE`,`EMPCODE`),
  KEY `FK8CEEB30D7C65B1A` (`ROLECODE`),
  CONSTRAINT `FK8CEEB30D7C65B1A` FOREIGN KEY (`ROLECODE`) REFERENCES `ROLETABLE` (`ROLECODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLEUSERTABLE`
--

LOCK TABLES `ROLEUSERTABLE` WRITE;
/*!40000 ALTER TABLE `ROLEUSERTABLE` DISABLE KEYS */;
INSERT INTO `ROLEUSERTABLE` VALUES ('1401720840','1401720840'),('DepartmentReader','KBS'),('DepartmentReader','KSH'),('DepartmentReader','SSH'),('MasterSystemAdmin','MasterSystemAdmin'),('TeamReader','CJK'),('TeamReader','JSY'),('TeamReader','KSG'),('TeamReader','KYT'),('TeamReader','LHS'),('TeamReader','PDJ'),('TeamReader','PMK'),('TeamReader','SHJ'),('TeamReader','YJY');
/*!40000 ALTER TABLE `ROLEUSERTABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SCHEDULE_TABLE`
--

DROP TABLE IF EXISTS `SCHEDULE_TABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SCHEDULE_TABLE` (
  `SCHE_IDX` int(11) DEFAULT NULL,
  `INSTID` int(11) DEFAULT NULL,
  `TRCTAG` varchar(100) DEFAULT NULL,
  `STARTDATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SCHEDULE_TABLE`
--

LOCK TABLES `SCHEDULE_TABLE` WRITE;
/*!40000 ALTER TABLE `SCHEDULE_TABLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `SCHEDULE_TABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TENANT_CANDIDATE`
--

DROP TABLE IF EXISTS `TENANT_CANDIDATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TENANT_CANDIDATE` (
  `ADMINEMAIL` varchar(100) NOT NULL,
  `ADMINID` varchar(20) NOT NULL,
  `ADMINPASSWORD` varchar(20) NOT NULL,
  `TENANTID` varchar(20) NOT NULL,
  `TENANTNAME` varchar(20) NOT NULL,
  `PARTID` varchar(20) NOT NULL,
  `PARTNAME` varchar(20) NOT NULL,
  `TELNO` varchar(20) DEFAULT NULL,
  `ISCERTIFIED` int(11) DEFAULT '0',
  `CERTIFIEDDATE` datetime DEFAULT NULL,
  `ISDELETED` int(11) DEFAULT '0',
  `MODDATE` datetime NOT NULL,
  PRIMARY KEY (`ADMINEMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TENANT_CANDIDATE`
--

LOCK TABLES `TENANT_CANDIDATE` WRITE;
/*!40000 ALTER TABLE `TENANT_CANDIDATE` DISABLE KEYS */;
INSERT INTO `TENANT_CANDIDATE` VALUES ('uEngineGroup@uengine.org','uEngineAdmin','test','uEngine','uEngine Solutions','uEngineTenantAdmin','uEngine Tenant Admin','02-567-8301',1,'2011-08-11 12:32:40',NULL,'2011-08-11 12:32:40');
/*!40000 ALTER TABLE `TENANT_CANDIDATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_worklist`
--

DROP TABLE IF EXISTS `bpm_worklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_worklist` (
  `TASKID` int(11) NOT NULL,
  `TITLE` varchar(200) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `ENDPOINT` varchar(200) DEFAULT NULL,
  `STATUS` varchar(100) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `STARTDATE` datetime DEFAULT NULL,
  `ENDDATE` datetime DEFAULT NULL,
  `DUEDATE` datetime DEFAULT NULL,
  `INSTID` int(11) DEFAULT NULL,
  `DEFID` int(11) DEFAULT NULL,
  `DEFNAME` varchar(200) DEFAULT NULL,
  `TRCTAG` varchar(100) DEFAULT NULL,
  `TOOL` varchar(200) DEFAULT NULL,
  `PARAMETER` varchar(4000) DEFAULT NULL,
  `GROUPID` int(11) DEFAULT NULL,
  `GROUPNAME` int(11) DEFAULT NULL,
  `EXT1` varchar(200) DEFAULT NULL,
  `EXT2` varchar(200) DEFAULT NULL,
  `EXT3` varchar(200) DEFAULT NULL,
  `ISURGENT` int(11) DEFAULT NULL,
  `HASATTACHFILE` int(11) DEFAULT NULL,
  `HASCOMMENT` int(11) DEFAULT NULL,
  `DOCUMENTCATEGORY` varchar(200) DEFAULT NULL,
  `ISDELETED` int(11) DEFAULT NULL,
  `ROOTINSTID` int(11) DEFAULT NULL,
  `DISPATCHOPTION` int(11) DEFAULT NULL,
  `DISPATCHPARAM1` varchar(100) DEFAULT NULL,
  `ROLENAME` varchar(100) DEFAULT NULL,
  `RESNAME` varchar(100) DEFAULT NULL,
  `REFROLENAME` varchar(100) DEFAULT NULL,
  `EXECSCOPE` varchar(5) DEFAULT NULL,
  `SAVEDATE` datetime DEFAULT NULL,
  `ABSTRACT` text,
  `type` char(10) DEFAULT NULL,
  `content` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`TASKID`),
  KEY `FK33852DAFF5139497` (`DEFID`),
  KEY `FK33852DAFE10386FC` (`ENDPOINT`),
  KEY `FK33852DAF63959984` (`INSTID`),
  KEY `FK33852DAF78EB68E6` (`ROOTINSTID`),
  CONSTRAINT `FK33852DAF63959984` FOREIGN KEY (`INSTID`) REFERENCES `BPM_PROCINST` (`INSTID`),
  CONSTRAINT `FK33852DAF78EB68E6` FOREIGN KEY (`ROOTINSTID`) REFERENCES `BPM_PROCINST` (`INSTID`),
  CONSTRAINT `FK33852DAFE10386FC` FOREIGN KEY (`ENDPOINT`) REFERENCES `EMPTABLE` (`EMPCODE`),
  CONSTRAINT `FK33852DAFF5139497` FOREIGN KEY (`DEFID`) REFERENCES `BPM_PROCDEFVER` (`DEFVERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bpm_worklist`
--

LOCK TABLES `bpm_worklist` WRITE;
/*!40000 ALTER TABLE `bpm_worklist` DISABLE KEYS */;
INSERT INTO `bpm_worklist` VALUES (57,'이슈 등록',NULL,'test','NEW',1,'2011-11-12 22:04:41',NULL,'2011-11-17 22:04:41',65,22,'이슈처리','1','formHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,65,0,NULL,'Initiator','Tester','null',NULL,NULL,NULL,NULL,NULL),(58,'첫번째 단계',NULL,'test','NEW',1,'2011-11-12 22:05:11',NULL,'2011-11-17 22:05:11',66,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,66,0,NULL,'Initiator','Tester','null',NULL,NULL,NULL,NULL,NULL),(59,'첫번째 단계',NULL,'test','NEW',1,'2011-11-12 22:06:24',NULL,'2011-11-17 22:06:24',67,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,67,0,NULL,'Initiator','Tester','null',NULL,NULL,NULL,NULL,NULL),(60,'Ping',NULL,'test','COMPLETED',1,'2011-11-12 22:14:44','2011-11-12 22:14:44','2011-11-17 22:14:44',68,2,'Ping','1','codiReplyHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,68,0,NULL,'Initiator','Tester','null',NULL,NULL,'sdfsdfadfasdf\n',NULL,NULL),(61,'Reply',NULL,'test','NEW',1,'2011-11-12 22:14:49',NULL,'2011-11-17 22:14:49',68,2,'Ping','2','codiReplyHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,68,0,NULL,'Initiator','Tester','null',NULL,NULL,NULL,NULL,NULL),(62,'첫번째 단계',NULL,'1401720840','NEW',1,'2011-12-16 13:34:41',NULL,'2011-12-21 13:34:41',73,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,73,0,NULL,'Initiator','1401720840','null',NULL,NULL,NULL,NULL,NULL),(63,'첫번째 단계',NULL,'1401720840','NEW',1,'2011-12-20 00:07:39',NULL,'2011-12-25 00:07:37',74,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,74,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(64,'첫번째 단계',NULL,'1401720840','NEW',1,'2011-12-20 00:49:58',NULL,'2011-12-25 00:49:27',75,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,75,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(65,'첫번째 단계',NULL,'1401720840','NEW',1,'2011-12-20 01:16:40',NULL,'2011-12-25 01:16:30',76,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,76,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(66,'첫번째 단계',NULL,'1401720840','NEW',1,'2011-12-20 01:17:23',NULL,'2011-12-25 01:16:55',77,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,77,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(67,'첫번째 단계',NULL,'1401720840','NEW',1,'2011-12-20 01:18:16',NULL,'2011-12-25 01:18:15',78,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,78,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(68,'첫번째 단계',NULL,'1401720840','NEW',1,'2011-12-20 01:18:27',NULL,'2011-12-25 01:18:26',79,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,79,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(69,'첫번째 단계',NULL,'1401720840','NEW',1,'2011-12-20 01:37:22',NULL,'2011-12-25 01:37:20',80,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,80,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(70,'첫번째 단계',NULL,'1401720840','NEW',1,'2011-12-20 01:38:17',NULL,'2011-12-25 01:38:16',81,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,81,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(71,'첫번째 단계',NULL,'1401720840','STOPPED',1,'2011-12-20 01:54:22',NULL,'2011-12-25 01:54:20',82,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,82,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(72,'첫번째 단계',NULL,'1401720840','NEW',1,'2011-12-20 01:55:07',NULL,'2011-12-25 01:55:06',83,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,83,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(73,'첫번째 단계',NULL,'1401720840','NEW',1,'2011-12-20 03:29:14',NULL,'2011-12-25 03:29:11',84,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,84,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(74,'첫번째 단계',NULL,'1401720840','NEW',1,'2011-12-21 01:28:09',NULL,'2011-12-26 01:28:06',85,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,85,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(75,'첫번째 단계',NULL,'1401720840','NEW',1,'2011-12-21 14:02:34',NULL,'2011-12-26 14:02:32',86,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,86,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(76,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(77,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(79,NULL,NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80,NULL,NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(81,NULL,NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(82,'title 이 이제는 입력이 되겠지... ',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,84,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(83,'첫번째 단계',NULL,'1401720840','NEW',1,'2011-12-22 21:44:20',NULL,'2011-12-27 21:44:17',87,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,87,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(84,'첫번째 단계',NULL,'1401720840','NEW',1,'2011-12-22 21:44:24',NULL,'2011-12-27 21:44:24',88,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,88,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(85,'업무요청사항등록',NULL,'1401720840','NEW',1,'2011-12-22 23:48:01',NULL,'2011-12-27 23:47:59',90,12,'업무요청','1','formHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,90,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(87,NULL,NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,90,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'dsadsfdsfds'),(88,'comment 내용',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,90,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(89,'이런 저런 내용은 실시간으로...',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,90,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90,'생각보다 리프래시가 오래걸리는 이유는??',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,90,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(91,'코멘트가 넘어가면 어떻게 되누?',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,90,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(92,'그 뒤에서 시작할까?',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,90,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(93,'첫번째 단계',NULL,'1401720840','NEW',1,'2011-12-23 01:35:55',NULL,'2011-12-28 01:35:53',91,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,91,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(94,'Reply',NULL,'1401720840','NEW',1,'2011-12-23 15:59:22',NULL,'2011-12-28 15:59:19',92,1,'Reply','1','codiReplyHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,92,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(95,'코맨트',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,91,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(96,'form',NULL,'1401720840','COMPLETED',1,'2011-12-29 01:47:24','2011-12-29 10:46:42','2012-01-03 01:47:22',93,28,'Noname','1','mw3.org.uengine.codi.activitytypes.wih.FormWorkItemHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,93,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(97,'form',NULL,'1401720840','COMPLETED',1,'2011-12-29 11:20:40','2011-12-29 11:20:58','2012-01-03 11:20:38',94,28,'Noname','1','mw3.org.uengine.codi.activitytypes.wih.FormWorkItemHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,94,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(98,'form',NULL,'1401720840','COMPLETED',1,'2011-12-29 13:25:11','2011-12-29 13:25:22','2012-01-03 13:25:09',95,28,'Noname','1','mw3.org.uengine.codi.activitytypes.wih.FormWorkItemHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,95,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(99,'form',NULL,'1401720840','COMPLETED',1,'2011-12-29 13:26:45','2011-12-29 13:27:28','2012-01-03 13:26:43',96,28,'Noname','1','mw3.org.uengine.codi.activitytypes.wih.FormWorkItemHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,96,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(100,'form',NULL,'1401720840','COMPLETED',1,'2011-12-29 13:34:38','2011-12-29 13:34:52','2012-01-03 13:34:34',97,28,'Noname','1','mw3.org.uengine.codi.activitytypes.wih.FormWorkItemHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,97,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(101,'form',NULL,'1401720840','COMPLETED',1,'2011-12-29 13:36:40','2011-12-29 13:37:34','2012-01-03 13:36:39',98,28,'Noname','1','mw3.org.uengine.codi.activitytypes.wih.FormWorkItemHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,98,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(102,'form',NULL,'1401720840','NEW',1,'2011-12-29 13:41:50',NULL,'2012-01-03 13:41:48',99,28,'Noname','1','mw3.org.uengine.codi.activitytypes.wih.FormWorkItemHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,99,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(103,'form',NULL,'1401720840','COMPLETED',1,'2011-12-29 13:44:05','2011-12-29 13:44:50','2012-01-03 13:44:02',100,28,'Noname','1','mw3.org.uengine.codi.activitytypes.wih.FormWorkItemHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,100,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(104,'form',NULL,'1401720840','COMPLETED',1,'2011-12-29 13:51:38','2011-12-29 13:52:33','2012-01-03 13:51:36',101,28,'Noname','1','mw3.org.uengine.codi.activitytypes.wih.FormWorkItemHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,101,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(105,'form',NULL,'1401720840','COMPLETED',1,'2011-12-29 13:53:01','2011-12-29 13:55:37','2012-01-03 13:52:59',102,28,'Noname','1','mw3.org.uengine.codi.activitytypes.wih.FormWorkItemHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,102,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(106,'form',NULL,'1401720840','COMPLETED',1,'2011-12-29 13:58:03','2011-12-29 13:59:30','2012-01-03 13:58:00',103,28,'Noname','1','mw3.org.uengine.codi.activitytypes.wih.FormWorkItemHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,103,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(107,'chatting',NULL,NULL,NULL,NULL,NULL,NULL,NULL,103,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(108,NULL,NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,103,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'dsafasdfasdfs'),(109,'form',NULL,'1401720840','NEW',1,'2012-01-04 18:08:16',NULL,'2012-01-09 18:08:13',104,28,'Noname','1','mw3.org.uengine.codi.activitytypes.wih.FormWorkItemHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,104,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(110,'새로운 정보가 얼마나 빠르고 쉽게 추가 되는가???',NULL,NULL,NULL,NULL,NULL,NULL,NULL,104,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(111,'빠르고 쉽게... 추가되는 되는데, 그 모습이 그냥 prepend나 append 되게 옵션처리하는 것. 그것은...?',NULL,NULL,NULL,NULL,NULL,NULL,NULL,104,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(112,'스크롤이 생기지도 않아야 하고, new Item이 list에 포함되어서도 안될것임. ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,104,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(113,'Reply',NULL,'1401720840','NEW',1,'2012-01-08 21:20:45',NULL,'2012-01-13 21:20:39',105,1,'Reply','1','codiReplyHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,105,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(114,'첫번째 단계',NULL,'1401720840','NEW',1,'2012-01-08 21:21:11',NULL,'2012-01-13 21:21:10',106,24,'defaultWIH_test','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,106,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(115,'이슈 등록',NULL,'1401720840','NEW',1,'2012-01-08 21:43:55',NULL,'2012-01-13 21:43:51',107,22,'이슈처리','1','formHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,107,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(116,'test',NULL,NULL,NULL,NULL,NULL,NULL,NULL,107,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(117,'adsfsfsdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,107,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(118,'dsfdafsdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,107,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(119,'hukhkjhjkhjk',NULL,NULL,NULL,NULL,NULL,NULL,NULL,106,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(120,'Human work',NULL,'1401720840','NEW',1,'2012-01-27 17:52:56',NULL,'2012-02-01 17:52:54',108,129,'metaworksObjTest','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,108,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(121,'Human work',NULL,'1401720840','NEW',1,'2012-01-27 17:57:06',NULL,'2012-02-01 17:57:04',109,129,'metaworksObjTest','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,109,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(122,'Human work',NULL,'1401720840','NEW',1,'2012-01-27 19:14:34',NULL,'2012-02-01 19:14:33',110,129,'metaworksObjTest','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,110,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(123,'Human work',NULL,'1401720840','NEW',1,'2012-01-27 22:24:52',NULL,'2012-02-01 22:24:50',111,130,'metaworksObjTest','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,111,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(124,'Human work',NULL,'1401720840','COMPLETED',1,'2012-01-27 22:44:27','2012-01-28 01:27:39','2012-02-01 22:44:24',112,130,'metaworksObjTest','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,112,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(125,'Human work',NULL,'1401720840','COMPLETED',1,'2012-01-28 01:47:16','2012-01-28 01:47:28','2012-02-02 01:47:12',113,130,'metaworksObjTest','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,113,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(126,'Human work',NULL,'1401720840','NEW',1,'2012-01-30 11:15:30',NULL,'2012-02-04 11:15:24',114,130,'metaworksObjTest','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,114,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(127,'Class 개발 요청',NULL,'1401720840','COMPLETED',1,'2012-01-30 14:36:19','2012-01-30 14:39:56','2012-02-04 14:36:17',115,140,'클래스개발 프로세스','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,115,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(128,'코딩',NULL,'1401720840','NEW',1,'2012-01-30 14:39:56',NULL,'2012-02-04 14:39:56',115,140,'클래스개발 프로세스','3','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,115,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(129,'Class 개발 요청',NULL,'1401720840','COMPLETED',1,'2012-01-30 18:01:21','2012-01-30 19:08:23','2012-02-04 18:01:20',116,140,'클래스개발 프로세스','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,116,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(132,'staetefsdfa',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(133,'어떤 메시지를 남길까??',NULL,NULL,NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(134,'이거 괜찮군... ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(135,'이게 되다니.. 신기하네.. ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(136,'안될줄 알았어... ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(137,'한줄 전체 내려보자',NULL,NULL,NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(138,'계속간다. ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(139,'ㄱㄴ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(140,'ㅇ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(141,'ㅇ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(142,'ㅁㅇㄴㄹ망ㄴ러ㅣㄴ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(143,'괜찮지?',NULL,NULL,NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(144,'코딩',NULL,'1401720840','NEW',1,'2012-01-30 19:08:23',NULL,'2012-02-04 19:08:23',116,140,'클래스개발 프로세스','3','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,116,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(145,'dsfsdfsadfdsa',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(146,'sdfsadfsda',NULL,NULL,NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(147,'sadfsad',NULL,NULL,NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(148,'dfsadfa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(149,NULL,NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(150,NULL,NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(151,NULL,NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(152,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(153,NULL,NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(154,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(155,NULL,NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(156,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(157,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(158,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(159,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(160,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(161,'이제 페이스북',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(162,'처음로딩해서?',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(163,'음.. 좋아좋아.. ',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(164,'페이스북',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(165,'처럼',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(166,'됐죠???',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(167,'아주좋아',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(168,'아ㅗㅇㅁㄴㅇㄹ',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(169,'ㄴㅁㅇ라ㅗㅁㄴ알',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(170,'ㅁㄴㅇㄹㅁㄴㅇㄹ',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(171,'ㄴㅇㅁㄹㄴㅁ',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(172,NULL,NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(173,NULL,NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(174,NULL,NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(175,NULL,NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(176,'dskfjhsakdjfsdafd',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(177,'sdfsa',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(178,'sadf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(179,'sadfsadf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(180,'sdafsadf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(181,'sadfsadfas',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(182,'dfsadfsadf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(183,'sadfsadfs',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(184,'fdssa',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(185,'fsadf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(186,'df',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(187,'fsadfsda',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(188,'fasdf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(189,'asdfsdaf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(190,'asdfsadfasd',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(191,'fsdaf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(192,'fasdf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(193,'sad',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(194,'sdaf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(195,'sda',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(196,'sd',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(197,'fsda',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(198,'fsad',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(199,'f',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(200,'df',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(201,'asdf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(202,'sadf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(203,'sad',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(204,NULL,NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(205,'adf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(206,'s',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(207,'f',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(208,'asdsdf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,116,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(209,'lkdjflskjfkldajlasf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(210,'sdafasdf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(211,'sadfasdf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(212,'sadfsadfasd',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,114,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(213,'클래스 개발 요청',NULL,'1401720840','NEW',1,'2012-01-30 21:46:05',NULL,'2012-02-04 21:46:02',117,143,'Request Class ','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,117,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(214,'클래스 개발 요청',NULL,'1401720840','NEW',1,'2012-01-30 21:47:02',NULL,'2012-02-04 21:47:01',118,143,'Request Class ','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,118,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(215,'클래스 개발 요청',NULL,'1401720840','NEW',1,'2012-01-30 21:47:53',NULL,'2012-02-04 21:47:49',119,143,'Request Class ','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,119,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(216,'클래스 개발 요청',NULL,'1401720840','NEW',1,'2012-01-30 21:50:57',NULL,'2012-02-04 21:50:56',120,144,'Request Class ','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,120,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(217,'클래스 개발 요청',NULL,'1401720840','COMPLETED',1,'2012-01-30 22:02:08','2012-01-30 22:41:41','2012-02-04 22:02:04',121,144,'Request Class ','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,121,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(218,'어떻게 하는게',NULL,NULL,NULL,NULL,NULL,NULL,NULL,121,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(219,'좋은건지 몰겠네',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,121,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(220,'어찌',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,121,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(221,'클래스 개발 요청',NULL,'1401720840','NEW',1,'2012-01-30 23:59:56',NULL,'2012-02-04 23:59:54',122,144,'Request Class ','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,122,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(222,'dlf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,121,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(223,'이걸 확대해서 쓰면.... 페이스북으로 친구에게 작업을 요청할 수 도 있군.. ',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,121,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(224,'그러면... 다음으로 할것은 담당자 지정버튼?',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,121,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(225,'dslfjladf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,122,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(226,'asdfasdf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,122,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(227,'dsfasdf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,122,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(228,'asdfasd',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,122,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(229,'fsadf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,122,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(230,'sdfasd',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,122,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(231,'클래스 개발 요청',NULL,'1401720840','COMPLETED',1,'2012-01-31 19:26:46','2012-01-31 19:27:48','2012-02-05 19:26:43',123,144,'Request Class ','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,123,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(232,'댓글이',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,123,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(233,'fasdf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,123,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(234,'sfsdaf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,123,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(235,'sdfsda',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,123,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(236,'dsf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,123,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(237,'asdfsda',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,123,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(238,'adsf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,123,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(239,'sadf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,123,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(240,'sdaf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,123,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(241,'daf',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,123,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(242,'dafsd',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,123,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(243,'ds',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,123,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(244,'클래스 개발 요청',NULL,'1401720840','NEW',1,'2012-02-01 17:51:34',NULL,'2012-02-06 17:51:33',124,144,'Request Class ','1','defaultHandler',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,124,0,NULL,'Initiator','장진영','null',NULL,NULL,NULL,NULL,NULL),(245,'vcxcv',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,124,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(246,'xcvcx',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,124,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(247,'vxcv',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,124,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(248,'vc',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,124,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(249,'cxvvc',NULL,'1401720840',NULL,NULL,NULL,NULL,NULL,124,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `bpm_worklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `categoryId` int(11) DEFAULT NULL,
  `categoryName` varchar(255) DEFAULT NULL,
  `parentCategoryId` int(11) DEFAULT NULL,
  `modDate` datetime DEFAULT NULL,
  `deleted` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (0,'재무/회계',-1,NULL,0),(1,'인사/급여',-1,NULL,0),(2,'영업 관리',-1,NULL,0),(3,'생산 관리',-1,NULL,0),(4,'설비 관리',-1,NULL,0),(5,'구매 관리',-1,NULL,0),(6,'수출입 관리',-1,NULL,0),(7,'재고 관리',-1,NULL,0),(8,'품질 관리',-1,NULL,0),(9,'기술 정보 관리',-1,NULL,0);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `userid` varchar(20) DEFAULT NULL,
  `friendId` varchar(20) DEFAULT NULL,
  `friendName` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES ('1401720840','565089870','Jae-Heon Kim'),('1401720840','565089870','Jae-Heon Kim'),('1401720840','565089870','Jae-Heon Kim'),('1401720840','593670837','정진호'),('1401720840','579204709','유영만'),('1401720840','608461498','서진호'),('1401720840','549825240','Jihoon Choi'),('1401720840','100002899287992','조진원'),('1401720840','1401720840','장진영');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contents`
--

DROP TABLE IF EXISTS `contents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contents` (
  `contentId` int(11) DEFAULT NULL,
  `menuid` int(11) DEFAULT NULL,
  `type` char(10) DEFAULT NULL,
  `paragraph` varchar(3000) DEFAULT NULL,
  `url` varchar(300) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `orderid` int(11) DEFAULT NULL,
  `writerid` varchar(50) DEFAULT NULL,
  `writername` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contents`
--

LOCK TABLES `contents` WRITE;
/*!40000 ALTER TABLE `contents` DISABLE KEYS */;
INSERT INTO `contents` VALUES (7,5,'0','내용을 추가',NULL,0,0,NULL,NULL,NULL),(9,8,'0','1. 기본 입력창 만들기 -1.1. 달력, 1.2. 텍스트영역, 1.3. 소스코드입력, 1.4. ',NULL,0,0,NULL,NULL,NULL),(15,2,'file',NULL,NULL,0,0,NULL,NULL,NULL),(17,2,'file',NULL,NULL,0,0,NULL,NULL,NULL),(27,9,'p','  안녕하세요... 새새 메뉴 내용입니다. ',NULL,0,0,NULL,NULL,NULL),(28,9,'p','  두번째 단락.. ',NULL,0,0,NULL,NULL,NULL),(32,4,'p','새 새 메뉴에 대한 글쓰기를 합니다. ',NULL,0,0,NULL,NULL,NULL),(43,5,'p','  다른 메뉴에 넣은값은? 첫번째.. ',NULL,0,0,2,NULL,NULL),(44,5,'p','  두번째 넣은 값은?',NULL,0,0,0,NULL,NULL),(52,3,'p','  새새메뉴',NULL,0,0,1,NULL,NULL),(53,16,'p','<h1> 메타데이터란? <h1>\n\n\'데이터를 위한 데이터\' 의 뜻인 메타데이터는 ....',NULL,0,0,1,NULL,NULL),(54,17,'p','<h1> 스펙요청은 우측 Feedback란에 남겨주시면 채택된 스펙이 이하에 등록처리됩니다 </h1>\n\n* 피드백을 등록하려면 페이스북 로그인이 필요합니다.',NULL,0,0,1,NULL,NULL),(55,18,'p','기존 사례\n<ul>\n<li> BPM의 여수신 프로세스에 적용한 사례\n<li> 보험 추천 상품 사례 - 엔터프라이즈 매시업\n<li> 상품 상담 - 심사 프로세스\n<li> 룰과의 연계를 통한 심사 프로세스 - 고객 스코어링\n<li> CEP엔진과의 연계를 통한 사기 검출, 위기 검출 프로세스\n\n</ul>',NULL,0,0,2,NULL,NULL),(56,18,'p','제품을 왜 만드는가?\n  <ul>\n<li>외산 교체 - 호주의 금융공학 제품들의 시장 크기 - 그리고 그의 분석 --> 엑스페리언 --> BPM + BRE의 아주 잘 융합된 구조\n<li>시장 접근 방향 - 오픈 이노베이션 - 세계시장 진입\n</ul>',NULL,0,0,1,NULL,NULL),(57,18,'p','  TODO\n<p>\n1. 프로세스 매시업 플랫폼 화면<br>\n2. 룰과의 연계 시나리오 - 엑스페리언 시나리오 참조, KCB룰 케이스 ',NULL,0,0,3,NULL,NULL),(58,15,'p','  소개 내용을 넣을 예정',NULL,0,0,1,NULL,NULL),(60,16,'file',NULL,'/Users/jyjang/Documents/1_�������곌���낵湲곕�.doc',0,0,2,NULL,NULL),(61,16,'file',NULL,'/Users/jyjang/Documents/123 (1).jpg',0,0,3,NULL,NULL),(63,19,'p',NULL,NULL,0,0,1,NULL,NULL),(64,19,'file','image/jpeg','fileSystem/_Users_jyjang_Documents_123.jpg',0,0,2,NULL,NULL),(65,19,'file','application/pdf','fileSystem/metaworks3.pdf',0,0,3,NULL,NULL),(66,18,'p','value for 206',NULL,0,0,4,NULL,NULL),(69,18,'src','1234567891011121314151617181920212223242526package org.metaworks.website;import org.metaworks.annotation.Face;import org.metaworks.annotation.Hidden;public class SourceCodeContents extends Contents{        public SourceCodeContents(){        setType(\"src\");    }    @Override    @Hidden(on=false) //overrides the annotation    @Face(            ejsPath=\"genericfaces/SourceEditor.ejs\",            options={\"width\"},            values={\"670\"}                        )    public String getParagraph() {        // TODO Auto-generated method stub        return super.getParagraph();    }}',NULL,0,0,5,'1401720840','장진영'),(71,18,'src','public class Test{\n    \n    String name;\n    \n    \n}',NULL,0,0,6,'1401720840','장진영'),(72,21,'src','package org.metaworks.example;\n\nimport java.util.ArrayList;\nimport java.util.Hashtable;\n\n//import org.springframework.context.annotation.Scope;\n//import org.springframework.stereotype.Service;\n\n\npublic class AddressBook implements IAddressBook{\n    \n	static Hashtable<String, IPerson> book = new Hashtable<String, IPerson>();\n	\n	public IPerson findPerson(String name){\n		return book.get(name);\n	}\n	\n	public IPerson[] listPersonsInSameAge(int age){\n		ArrayList<IPerson> list = new ArrayList();\n		\n		for(String key : book.keySet()){\n			IPerson p = book.get(key);\n			if(p.getAge()==age){\n				list.add(p);\n			}\n		}\n		\n		IPerson[] plist = new IPerson[list.size()];\n		list.toArray(plist);\n		return plist;\n	}\n	\n	public void putPerson(IPerson person){\n		book.put(person.getName(), person);\n	}\n	\n	public PersonAndContact findPersonAndContact(String name){\n		IPerson p = findPerson(name);\n		PersonAndContact personAndContact = new PersonAndContact();\n		personAndContact.setContact(p.getContact());\n		personAndContact.setPerson(p);\n		\n		personAndContact.setId(\"#ID001\");\n		personAndContact.setOrder(5);\n		\n		return personAndContact;\n	}\n	\n}\n\n	',NULL,0,0,1,'1401720840','장진영'),(73,23,'src','package org.metaworks.example;\n\nimport java.util.ArrayList;\nimport java.util.Hashtable;\n\n//import org.springframework.context.annotation.Scope;\n//import org.springframework.stereotype.Service;\n\n\npublic class AddressBook implements IAddressBook{\n    \n	static Hashtable<String, IPerson> book = new Hashtable<String, IPerson>();\n	\n	public IPerson findPerson(String name){\n		return book.get(name);\n	}\n	\n	public IPerson[] listPersonsInSameAge(int age){\n		ArrayList<IPerson> list = new ArrayList();\n		\n		for(String key : book.keySet()){\n			IPerson p = book.get(key);\n			if(p.getAge()==age){\n				list.add(p);\n			}\n		}\n		\n		IPerson[] plist = new IPerson[list.size()];\n		list.toArray(plist);\n		return plist;\n	}\n	\n	public void putPerson(IPerson person){\n		book.put(person.getName(), person);\n	}\n	\n	public PersonAndContact findPersonAndContact(String name){\n		IPerson p = findPerson(name);\n		PersonAndContact personAndContact = new PersonAndContact();\n		personAndContact.setContact(p.getContact());\n		personAndContact.setPerson(p);\n		\n		personAndContact.setId(\"#ID001\");\n		personAndContact.setOrder(5);\n		\n		return personAndContact;\n	}\n	\n}\n\n	',NULL,0,0,1,'1401720840','장진영'),(74,25,'src','package org.metaworks.example;\n\nimport org.metaworks.annotation.Face;\nimport org.metaworks.annotation.Id;\nimport org.metaworks.annotation.NonLoadable;\nimport org.metaworks.annotation.Table;\nimport org.metaworks.dao.IDAO;\n\n    @Table(name=\"Person\")\n	public interface IPerson extends IDAO{\n		\n			@Id\n			public String getName();\n			public void setName(String name);\n	\n			public int getAge();\n			public void setAge(int age);\n			\n			@NonLoadable\n			public Contact getContact();\n			public void setContact(Contact contact);\n			\n			@Face(ejsPath=\"faces/image.ejs\")\n			public String getPortraitURL();\n			public void setPortraitURL(String portraitURL);\n			\n			\n			public boolean isMyFried();\n			public void setMyFried(boolean isMyFried);\n			\n			///method\n			\n			public IPerson fill() throws Exception;\n	}',NULL,0,0,1,'1401720840','장진영'),(75,26,'src','package org.metaworks.example;\n\nimport org.metaworks.annotation.Face;\nimport org.metaworks.annotation.ServiceMethod;\nimport org.metaworks.dao.Database;\n\npublic class Person extends Database<IPerson> implements IPerson{\n\n    String name;\n\n		public String getName() {\n			return name;\n		}\n		public void setName(String name) {\n			this.name = name;\n		}\n\n\n	int age;\n	\n		public int getAge() {\n			return age;\n		}\n		public void setAge(int age) {\n			this.age = age;\n		}\n\n\n	Contact contact;\n	\n		public Contact getContact() {\n			return contact;\n		}\n		public void setContact(Contact contact) {\n			this.contact = contact;\n		}\n\n\n	String portraitURL;\n	@Face(ejsPath=\"faces/image.ejs\")\n\n		public String getPortraitURL() {\n			return portraitURL;\n		}\n		public void setPortraitURL(String portraitURL) {\n			this.portraitURL = portraitURL;\n		}\n\n\n	boolean isMyFried;\n\n		public boolean isMyFried() {\n			return isMyFried;\n		}\n		public void setMyFried(boolean isMyFried) {\n			this.isMyFried = isMyFried;\n		}\n	\n\n	@ServiceMethod\n	public IPerson fill() throws Exception{\n		return databaseMe();\n	}\n	\n	@ServiceMethod(target=TARGET_AUTO)\n	public PersonDetail wall() throws Exception{\n		return new PersonDetail(this);\n	}\n	\n	\n}\n',NULL,0,0,1,'1401720840','장진영'),(76,21,'p','  adfsfsf',NULL,0,0,0,'1401720840','장진영'),(77,21,'p','  sfasf',NULL,0,0,3,'1401720840','장진영'),(78,24,'p','  ergdfgsg',NULL,0,0,2,'1401720840','장진영'),(79,24,'p','  sfggfggdf',NULL,0,0,1,'1401720840','장진영');
/*!40000 ALTER TABLE `contents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facebookloginuser`
--

DROP TABLE IF EXISTS `facebookloginuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facebookloginuser` (
  `userid` char(100) DEFAULT NULL,
  `admin` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facebookloginuser`
--

LOCK TABLES `facebookloginuser` WRITE;
/*!40000 ALTER TABLE `facebookloginuser` DISABLE KEYS */;
INSERT INTO `facebookloginuser` VALUES ('1401720840',1);
/*!40000 ALTER TABLE `facebookloginuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `feedbackId` int(11) DEFAULT NULL,
  `menuid` char(10) DEFAULT NULL,
  `postdate` date DEFAULT NULL,
  `text` varchar(1000) DEFAULT NULL,
  `writerid` varchar(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `writername` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,'25',NULL,NULL,'1401720840',NULL,'장진영'),(2,'-1',NULL,NULL,'1401720840',NULL,'장진영'),(3,'-1',NULL,'의견바랍니다.','1401720840',NULL,'장진영'),(4,'-1',NULL,'Good job! 그런데 20라인은 잠재오류가 있을 수 있습니다. ','100002969720934',NULL,'Jinyoung Jang'),(5,'-1',NULL,'아, 그런가요?','1401720840',NULL,'장진영'),(6,'25',NULL,'sljfdldsffa','1401720840',NULL,'장진영'),(7,'25',NULL,'sdfsadfsadf','1401720840',NULL,'장진영'),(8,'25',NULL,'sdfsdaklfjksdfljdsjklf','1401720840',NULL,'장진영'),(9,'25',NULL,'what?','100002969720934',NULL,'Jinyoung Jang');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `userId` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `ext1` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('d','d',NULL),('jinyoung jang','123',NULL),('jinyoung jang','123',NULL),('joe bloggs','111',NULL),('jjy','18925',NULL),('kkk','1234',NULL),('jinyoung','12334',NULL),('harry','1234',NULL),('jjy','1234',NULL),('jjy','12345',NULL),('kim','12345',NULL);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marketItem`
--

DROP TABLE IF EXISTS `marketItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marketItem` (
  `itemId` int(11) DEFAULT NULL,
  `categoryId` int(11) DEFAULT NULL,
  `itemName` varchar(255) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `comCode` varchar(30) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  `logoImageFilePath` varchar(255) DEFAULT NULL,
  `image1FilePath` varchar(255) DEFAULT NULL,
  `image2FilePath` varchar(255) DEFAULT NULL,
  `image3FilePath` varchar(255) DEFAULT NULL,
  `image4FilePath` varchar(255) DEFAULT NULL,
  `image5FilePath` varchar(255) DEFAULT NULL,
  `regDate` date DEFAULT NULL,
  `starPoint` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `modDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marketItem`
--

LOCK TABLES `marketItem` WRITE;
/*!40000 ALTER TABLE `marketItem` DISABLE KEYS */;
INSERT INTO `marketItem` VALUES (1,0,NULL,'정의',0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(2,1,NULL,'새로운 파일 테스트',0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(3,0,NULL,'이건 저장될걸',0,NULL,0,'fileSystem/mw3.png',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(4,0,NULL,'아이템 추가하자... ',0,NULL,0,'fileSystem/123.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(5,0,NULL,'sdfsdafdsf',0,NULL,0,'fileSystem/1_�������곌���낵湲곕�.doc',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(6,0,NULL,'sdfasdfdasfsdf',0,NULL,0,'fileSystem/_Users_jyjang_Documents_123 (2).jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL);
/*!40000 ALTER TABLE `marketItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `menuId` int(11) DEFAULT NULL,
  `parentMenuId` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `orderid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (18,0,'메타웍스 소개',1,0),(19,0,'기능요청',1,0),(20,19,'테스트하부메뉴',1,1),(21,-1,'org.metaworks.example',1,1),(22,-1,'org.metaworks.dao',1,2),(23,21,'AddressBook',1,1),(24,21,'Contact',1,2),(25,21,'IPerson.java',1,3),(26,21,'Person.java',1,4),(27,21,'PersonAndContact.java',1,5),(28,-1,'org.metaworks.controller',1,3),(29,-1,'org.metaworks.appstore',1,4),(30,-1,'org.metaworks.test',1,5);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_acl`
--

DROP TABLE IF EXISTS `mm_acl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_acl` (
  `permission` char(10) DEFAULT NULL,
  `nodeid` char(10) DEFAULT NULL,
  `aclid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_acl`
--

LOCK TABLES `mm_acl` WRITE;
/*!40000 ALTER TABLE `mm_acl` DISABLE KEYS */;
INSERT INTO `mm_acl` VALUES ('M','2',3),('D','2',13),('P','2',14),('D','2',15),('P','1',16);
/*!40000 ALTER TABLE `mm_acl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `mm_categories_view`
--

DROP TABLE IF EXISTS `mm_categories_view`;
/*!50001 DROP VIEW IF EXISTS `mm_categories_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `mm_categories_view` (
  `ID` bigint(20) unsigned,
  `NAME` varchar(1000),
  `LFT` int(10) unsigned,
  `RGT` int(10) unsigned,
  `PARENTID` bigint(20) unsigned,
  `DEPTH` bigint(22),
  `IS_LEAF` int(1)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `name` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `portraitURL` varchar(300) DEFAULT NULL,
  `myFried` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES ('jinyoung jang',NULL,'http://www.bloter.net/files/2009/02/uengineceo090209.jpg',1),('joe bloggs',NULL,'http://izzet.com/pictures/tzcvvrfbf7izn0mr28ew3pz5jcy42tobdvnc6x7bo88f4eb5b3yew5j6vzxjs6uy0idpp0trobinson_fred.jpg',0);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prefer`
--

DROP TABLE IF EXISTS `prefer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prefer` (
  `userId` int(11) DEFAULT NULL,
  `feedbackId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prefer`
--

LOCK TABLES `prefer` WRITE;
/*!40000 ALTER TABLE `prefer` DISABLE KEYS */;
/*!40000 ALTER TABLE `prefer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(11) DEFAULT NULL,
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `emailaddr` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `portrait` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `worklistview`
--

DROP TABLE IF EXISTS `worklistview`;
/*!50001 DROP VIEW IF EXISTS `worklistview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `worklistview` (
  `TASKID` int(11),
  `TITLE` varchar(200),
  `DESCRIPTION` varchar(500),
  `ENDPOINT` varchar(200),
  `STATUS` varchar(100),
  `PRIORITY` int(11),
  `STARTDATE` datetime,
  `ENDDATE` datetime,
  `DUEDATE` datetime,
  `INSTID` int(11),
  `DEFID` int(11),
  `DEFNAME` varchar(200),
  `TRCTAG` varchar(100),
  `TOOL` varchar(200),
  `PARAMETER` varchar(4000),
  `GROUPID` int(11),
  `GROUPNAME` int(11),
  `EXT1` varchar(200),
  `EXT2` varchar(200),
  `EXT3` varchar(200),
  `ISURGENT` int(11),
  `HASATTACHFILE` int(11),
  `HASCOMMENT` int(11),
  `DOCUMENTCATEGORY` varchar(200),
  `ISDELETED` int(11),
  `ROOTINSTID` int(11),
  `DISPATCHOPTION` int(11),
  `DISPATCHPARAM1` varchar(100),
  `ROLENAME` varchar(100),
  `RESNAME` varchar(100),
  `REFROLENAME` varchar(100),
  `EXECSCOPE` varchar(5),
  `SAVEDATE` datetime,
  `ABSTRACT` text,
  `SAIDGOODUSERSCOUNT` bigint(21),
  `SAIDGOODUSERSNAME` varchar(100)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `mm_categories_view`
--

/*!50001 DROP TABLE IF EXISTS `mm_categories_view`*/;
/*!50001 DROP VIEW IF EXISTS `mm_categories_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `mm_categories_view` AS (select `NODE`.`ID` AS `ID`,`NODE`.`NAME` AS `NAME`,`NODE`.`LFT` AS `LFT`,`NODE`.`RGT` AS `RGT`,`NODE`.`PARENTID` AS `PARENTID`,(count(`PARENT`.`NAME`) - 1) AS `DEPTH`,((`NODE`.`RGT` - `NODE`.`LFT`) = 1) AS `IS_LEAF` from (`mm_categories` `NODE` join `mm_categories` `PARENT`) where (`NODE`.`LFT` between `PARENT`.`LFT` and `PARENT`.`RGT`) group by `NODE`.`ID`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `worklistview`
--

/*!50001 DROP TABLE IF EXISTS `worklistview`*/;
/*!50001 DROP VIEW IF EXISTS `worklistview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `worklistview` AS (select `WL`.`TASKID` AS `TASKID`,`WL`.`TITLE` AS `TITLE`,`WL`.`DESCRIPTION` AS `DESCRIPTION`,`WL`.`ENDPOINT` AS `ENDPOINT`,`WL`.`STATUS` AS `STATUS`,`WL`.`PRIORITY` AS `PRIORITY`,`WL`.`STARTDATE` AS `STARTDATE`,`WL`.`ENDDATE` AS `ENDDATE`,`WL`.`DUEDATE` AS `DUEDATE`,`WL`.`INSTID` AS `INSTID`,`WL`.`DEFID` AS `DEFID`,`WL`.`DEFNAME` AS `DEFNAME`,`WL`.`TRCTAG` AS `TRCTAG`,`WL`.`TOOL` AS `TOOL`,`WL`.`PARAMETER` AS `PARAMETER`,`WL`.`GROUPID` AS `GROUPID`,`WL`.`GROUPNAME` AS `GROUPNAME`,`WL`.`EXT1` AS `EXT1`,`WL`.`EXT2` AS `EXT2`,`WL`.`EXT3` AS `EXT3`,`WL`.`ISURGENT` AS `ISURGENT`,`WL`.`HASATTACHFILE` AS `HASATTACHFILE`,`WL`.`HASCOMMENT` AS `HASCOMMENT`,`WL`.`DOCUMENTCATEGORY` AS `DOCUMENTCATEGORY`,`WL`.`ISDELETED` AS `ISDELETED`,`WL`.`ROOTINSTID` AS `ROOTINSTID`,`WL`.`DISPATCHOPTION` AS `DISPATCHOPTION`,`WL`.`DISPATCHPARAM1` AS `DISPATCHPARAM1`,`WL`.`ROLENAME` AS `ROLENAME`,`WL`.`RESNAME` AS `RESNAME`,`WL`.`REFROLENAME` AS `REFROLENAME`,`WL`.`EXECSCOPE` AS `EXECSCOPE`,`WL`.`SAVEDATE` AS `SAVEDATE`,`WL`.`ABSTRACT` AS `ABSTRACT`,(select count(0) from `comments` `COMM` where ((`COMM`.`CONTENTID` = `WL`.`TASKID`) and (`COMM`.`CONTENTTYPE` = 2) and (`COMM`.`ISGOOD` = 1))) AS `SAIDGOODUSERSCOUNT`,(select `EMP`.`EMPNAME` from `emptable` `EMP` where (`EMP`.`EMPCODE` = (select `COMM`.`EMPCODE` from `comments` `COMM` where (`COMM`.`COMMENTID` = (select min(`comments`.`COMMENTID`) from `comments` where ((`comments`.`CONTENTID` = `WL`.`TASKID`) and (`comments`.`CONTENTTYPE` = 2) and (`comments`.`ISGOOD` = 1))))))) AS `SAIDGOODUSERSNAME` from `bpm_worklist` `WL`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-02-02 21:35:51