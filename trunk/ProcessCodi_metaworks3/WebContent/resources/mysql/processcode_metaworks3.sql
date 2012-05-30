-- MySQL dump 10.13  Distrib 5.5.13, for Win32 (x86)
--
-- Host: localhost    Database: uengine
-- ------------------------------------------------------
-- Server version	5.5.13-log

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

SET FOREIGN_KEY_CHECKS = 0;
 
SELECT @@FOREIGN_KEY_CHECKS;

--
-- Table structure for table `appkey_attributes`
--

DROP TABLE IF EXISTS `appkey_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appkey_attributes` (
  `APPKEY` varchar(36) NOT NULL,
  `ATTRIBUTEKEY` varchar(30) NOT NULL,
  `ATTRIBUTEVALUE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`APPKEY`,`ATTRIBUTEKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appkey_attributes`
--

LOCK TABLES `appkey_attributes` WRITE;
/*!40000 ALTER TABLE `appkey_attributes` DISABLE KEYS */;
INSERT INTO `appkey_attributes` VALUES ('0d50da56-0d16-48d9-b1c7-cdc1b290846b','instanceCount','35'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','instanceFrom','0'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserComName','uEngine Solutions'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserEmail',NULL),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserFacebookId',NULL),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserFullName','Tester'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserGlobalCom','uEngine'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserId','test'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserIsAdmin','1'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserJikName','Tester'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserLocale','en'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserMsnId',NULL),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserNateonId',NULL),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserPartCode',''),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserPartName',NULL),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','loggedUserPw','test'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','mailCount','20'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','mailFrom','0'),('0d50da56-0d16-48d9-b1c7-cdc1b290846b','uxStyle','waveStyle'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','instanceCount','35'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','instanceFrom','11'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserComName','uEngine Solutions'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserEmail',NULL),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserFacebookId',NULL),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserFullName','Tester'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserGlobalCom','uEngine'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserId','test'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserIsAdmin','1'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserJikName','Tester'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserLocale','en'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserMsnId',NULL),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserNateonId',NULL),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserPartCode',''),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserPartName',NULL),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','loggedUserPw','test'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','mailCount','20'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','mailFrom','0'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','uxStyle','waveStyle'),('3dda0808-8b37-4277-b27f-7e2421da607f','instanceCount','35'),('3dda0808-8b37-4277-b27f-7e2421da607f','instanceFrom','17'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserComName','uEngine Solutions'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserEmail',NULL),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserFacebookId','100002969720934'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserFullName','Tester'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserGlobalCom','uEngine'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserId','test'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserIsAdmin','1'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserJikName','Tester'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserLocale','en'),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserMsnId',NULL),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserNateonId',NULL),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserPartCode',''),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserPartName',NULL),('3dda0808-8b37-4277-b27f-7e2421da607f','loggedUserPw','test'),('3dda0808-8b37-4277-b27f-7e2421da607f','mailCount','20'),('3dda0808-8b37-4277-b27f-7e2421da607f','mailFrom','0'),('3dda0808-8b37-4277-b27f-7e2421da607f','uxStyle','waveStyle'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','instanceCount','35'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','instanceFrom','11'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserComName','uEngine Solutions'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserEmail',NULL),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserFacebookId','100002969720934'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserFullName','Tester'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserGlobalCom','uEngine'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserId','test'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserIsAdmin','1'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserJikName','Tester'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserLocale','en'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserMsnId',NULL),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserNateonId',NULL),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserPartCode',''),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserPartName',NULL),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','loggedUserPw','test'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','mailCount','20'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','mailFrom','0'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','uxStyle','waveStyle'),('6294255c-5579-43d5-bdac-f121c586684c','instanceCount','35'),('6294255c-5579-43d5-bdac-f121c586684c','instanceFrom','11'),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserComName','uEngine Solutions'),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserEmail',NULL),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserFacebookId',NULL),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserFullName','Tester'),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserGlobalCom','uEngine'),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserId','test'),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserIsAdmin','1'),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserJikName','Tester'),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserLocale','en'),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserMsnId',NULL),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserNateonId',NULL),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserPartCode',''),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserPartName',NULL),('6294255c-5579-43d5-bdac-f121c586684c','loggedUserPw','test'),('6294255c-5579-43d5-bdac-f121c586684c','mailCount','20'),('6294255c-5579-43d5-bdac-f121c586684c','mailFrom','0'),('6294255c-5579-43d5-bdac-f121c586684c','uxStyle','waveStyle'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','instanceCount','35'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','instanceFrom','11'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserComName','uEngine Solutions'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserEmail',NULL),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserFacebookId','100002969720934'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserFullName','Tester'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserGlobalCom','uEngine'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserId','test'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserIsAdmin','1'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserJikName','Tester'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserLocale','en'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserMsnId',NULL),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserNateonId',NULL),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserPartCode',''),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserPartName',NULL),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','loggedUserPw','test'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','mailCount','20'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','mailFrom','0'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','uxStyle','waveStyle'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','instanceCount','35'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','instanceFrom','4'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserComName','uEngine Solutions'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserEmail',NULL),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserFacebookId','100002969720934'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserFullName','Tester'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserGlobalCom','uEngine'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserId','test'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserIsAdmin','1'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserJikName','Tester'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserLocale','en'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserMsnId',NULL),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserNateonId',NULL),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserPartCode',''),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserPartName',NULL),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','loggedUserPw','test'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','mailCount','20'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','mailFrom','0'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','uxStyle','waveStyle'),('907d94eb-cba7-4677-b309-71391a55853b','instanceCount','35'),('907d94eb-cba7-4677-b309-71391a55853b','instanceFrom','24'),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserComName','uEngine Solutions'),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserEmail',NULL),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserFacebookId',NULL),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserFullName','Tester'),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserGlobalCom','uEngine'),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserId','test'),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserIsAdmin','1'),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserJikName','Tester'),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserLocale','en'),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserMsnId',NULL),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserNateonId',NULL),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserPartCode',''),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserPartName',NULL),('907d94eb-cba7-4677-b309-71391a55853b','loggedUserPw','test'),('907d94eb-cba7-4677-b309-71391a55853b','mailCount','20'),('907d94eb-cba7-4677-b309-71391a55853b','mailFrom','0'),('907d94eb-cba7-4677-b309-71391a55853b','uxStyle','waveStyle'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','instanceCount','35'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','instanceFrom','11'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserComName','uEngine Solutions'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserEmail',NULL),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserFacebookId','100002969720934'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserFullName','Tester'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserGlobalCom','uEngine'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserId','test'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserIsAdmin','1'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserJikName','Tester'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserLocale','en'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserMsnId',NULL),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserNateonId',NULL),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserPartCode',''),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserPartName',NULL),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','loggedUserPw','test'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','mailCount','20'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','mailFrom','0'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','uxStyle','waveStyle'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','instanceCount','35'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','instanceFrom','0'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserComName','uEngine Solutions'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserEmail',NULL),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserFacebookId',NULL),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserFullName','Tester'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserGlobalCom','uEngine'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserId','test'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserIsAdmin','1'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserJikName','Tester'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserLocale','en'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserMsnId',NULL),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserNateonId',NULL),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserPartCode',''),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserPartName',NULL),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','loggedUserPw','test'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','mailCount','20'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','mailFrom','0'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','uxStyle','waveStyle'),('aaf285fb-e23f-4639-96bc-16ba9936884c','instanceCount','35'),('aaf285fb-e23f-4639-96bc-16ba9936884c','instanceFrom','11'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserComName','uEngine Solutions'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserEmail',NULL),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserFacebookId','100002969720934'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserFullName','Tester'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserGlobalCom','uEngine'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserId','test'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserIsAdmin','1'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserJikName','Tester'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserLocale','en'),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserMsnId',NULL),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserNateonId',NULL),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserPartCode',''),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserPartName',NULL),('aaf285fb-e23f-4639-96bc-16ba9936884c','loggedUserPw','test'),('aaf285fb-e23f-4639-96bc-16ba9936884c','mailCount','20'),('aaf285fb-e23f-4639-96bc-16ba9936884c','mailFrom','0'),('aaf285fb-e23f-4639-96bc-16ba9936884c','uxStyle','waveStyle'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','instanceCount','35'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','instanceFrom','11'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserComName','uEngine Solutions'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserEmail',NULL),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserFacebookId','100002969720934'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserFullName','Tester'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserGlobalCom','uEngine'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserId','test'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserIsAdmin','1'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserJikName','Tester'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserLocale','en'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserMsnId',NULL),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserNateonId',NULL),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserPartCode',''),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserPartName',NULL),('abdf8142-dd66-42f3-96ca-ae478183b6e4','loggedUserPw','test'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','mailCount','20'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','mailFrom','0'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','uxStyle','waveStyle'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','instanceCount','35'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','instanceFrom','11'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserComName','uEngine Solutions'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserEmail',NULL),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserFacebookId',NULL),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserFullName','Tester'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserGlobalCom','uEngine'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserId','test'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserIsAdmin','1'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserJikName','Tester'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserLocale','en'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserMsnId',NULL),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserNateonId',NULL),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserPartCode',''),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserPartName',NULL),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','loggedUserPw','test'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','mailCount','20'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','mailFrom','0'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','uxStyle','waveStyle');
/*!40000 ALTER TABLE `appkey_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appkeys`
--

DROP TABLE IF EXISTS `appkeys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appkeys` (
  `APPKEY` varchar(36) NOT NULL,
  `CREATIONTIME` datetime DEFAULT NULL,
  `LASTACCESSEDTIME` datetime DEFAULT NULL,
  PRIMARY KEY (`APPKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appkeys`
--

LOCK TABLES `appkeys` WRITE;
/*!40000 ALTER TABLE `appkeys` DISABLE KEYS */;
INSERT INTO `appkeys` VALUES ('0d50da56-0d16-48d9-b1c7-cdc1b290846b','2011-09-26 00:21:34','2011-09-26 00:52:57'),('0dcadd5a-45c8-4f70-8cef-cebafc2a23c2','2011-09-26 21:10:42','2011-09-26 22:57:24'),('1b8f1070-138b-4205-8db2-1da52da22f77','2011-09-26 01:28:56','2011-09-26 01:28:56'),('3dda0808-8b37-4277-b27f-7e2421da607f','2011-10-19 11:11:21','2011-10-19 11:11:29'),('5f6512d2-5f2e-4828-a7e3-a5639e6d4288','2011-10-15 16:59:40','2011-10-18 01:00:18'),('6294255c-5579-43d5-bdac-f121c586684c','2011-09-25 02:20:49','2011-09-26 02:26:36'),('7ad440d0-8bd3-4f0e-8b22-a7a993a2840b','2011-10-14 20:09:41','2011-10-15 00:33:19'),('83b184e6-bfc4-4f0f-a972-8dc8fa383009','2011-11-12 22:11:15','2011-11-12 22:14:53'),('85d117ed-3900-4763-922d-febcf53fb7bd','2011-09-26 01:10:12','2011-09-26 01:10:12'),('907d94eb-cba7-4677-b309-71391a55853b','2011-09-25 23:52:20','2011-09-25 23:52:29'),('9f872f1f-1feb-4c8f-9228-a45b16c9523a','2011-10-15 01:18:40','2011-10-15 14:52:47'),('a0f9448f-19c8-4b7b-b872-0623bd6177a2','2011-09-25 23:56:00','2011-09-25 23:57:30'),('aaf285fb-e23f-4639-96bc-16ba9936884c','2011-10-17 02:34:20','2011-10-17 02:34:23'),('abdf8142-dd66-42f3-96ca-ae478183b6e4','2011-10-18 14:38:47','2011-10-18 14:38:54'),('cfaf7fa0-bf01-4049-8330-ebe6b4bb86ee','2011-09-26 00:21:34','2011-09-26 00:21:34'),('d7b3b286-0bd4-47d1-bbb9-09bbdfda4c7f','2011-09-26 00:56:00','2011-09-26 20:45:30');
/*!40000 ALTER TABLE `appkeys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bizrate`
--

DROP TABLE IF EXISTS `bizrate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bizrate` (
  `EMPCODE` varchar(20) NOT NULL DEFAULT '',
  `YEARMM` varchar(6) NOT NULL DEFAULT '',
  `BIZDAY` int(11) DEFAULT NULL,
  `WORKEDDAY` int(11) DEFAULT NULL,
  `LATEDDAY` int(11) DEFAULT NULL,
  PRIMARY KEY (`EMPCODE`,`YEARMM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bizrate`
--

LOCK TABLES `bizrate` WRITE;
/*!40000 ALTER TABLE `bizrate` DISABLE KEYS */;
/*!40000 ALTER TABLE `bizrate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_acltable`
--

DROP TABLE IF EXISTS `bpm_acltable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_acltable` (
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
-- Dumping data for table `bpm_acltable`
--

LOCK TABLES `bpm_acltable` WRITE;
/*!40000 ALTER TABLE `bpm_acltable` DISABLE KEYS */;
/*!40000 ALTER TABLE `bpm_acltable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_deadln_dim`
--

DROP TABLE IF EXISTS `bpm_deadln_dim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_deadln_dim` (
  `DEADLN_ID` int(11) NOT NULL,
  `DEADLN_DESC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DEADLN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bpm_deadln_dim`
--

LOCK TABLES `bpm_deadln_dim` WRITE;
/*!40000 ALTER TABLE `bpm_deadln_dim` DISABLE KEYS */;
/*!40000 ALTER TABLE `bpm_deadln_dim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_prfm_fact_2006`
--

DROP TABLE IF EXISTS `bpm_prfm_fact_2006`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_prfm_fact_2006` (
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
-- Dumping data for table `bpm_prfm_fact_2006`
--

LOCK TABLES `bpm_prfm_fact_2006` WRITE;
/*!40000 ALTER TABLE `bpm_prfm_fact_2006` DISABLE KEYS */;
/*!40000 ALTER TABLE `bpm_prfm_fact_2006` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_procdef`
--

DROP TABLE IF EXISTS `bpm_procdef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_procdef` (
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
  `author` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`DEFID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bpm_procdef`
--

LOCK TABLES `bpm_procdef` WRITE;
/*!40000 ALTER TABLE `bpm_procdef` DISABLE KEYS */;
INSERT INTO `bpm_procdef` VALUES (256,NULL,-1,1,0,'folder',0,NULL,'1111',0,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `bpm_procdef` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_procdefver`
--

DROP TABLE IF EXISTS `bpm_procdefver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_procdefver` (
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
  CONSTRAINT `FK890156D446F158C1` FOREIGN KEY (`DEFID`) REFERENCES `bpm_procdef` (`DEFID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bpm_procdefver`
--

LOCK TABLES `bpm_procdefver` WRITE;
/*!40000 ALTER TABLE `bpm_procdefver` DISABLE KEYS */;
/*!40000 ALTER TABLE `bpm_procdefver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_procinst`
--

DROP TABLE IF EXISTS `bpm_procinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_procinst` (
  `INSTID` int(11) NOT NULL,
  `DEFVERID` varchar(100) DEFAULT NULL,
  `DEFID` varchar(100) DEFAULT NULL,
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
  KEY `FKF57F151C78EB68E6` (`ROOTINSTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bpm_procinst`
--

LOCK TABLES `bpm_procinst` WRITE;
/*!40000 ALTER TABLE `bpm_procinst` DISABLE KEYS */;
/*!40000 ALTER TABLE `bpm_procinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_procvar`
--

DROP TABLE IF EXISTS `bpm_procvar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_procvar` (
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
) ENGINE=InnoDB AUTO_INCREMENT=499 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bpm_procvar`
--

LOCK TABLES `bpm_procvar` WRITE;
/*!40000 ALTER TABLE `bpm_procvar` DISABLE KEYS */;
/*!40000 ALTER TABLE `bpm_procvar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_rolemapping`
--

DROP TABLE IF EXISTS `bpm_rolemapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_rolemapping` (
  `ROLEMAPPINGID` int(11) NOT NULL AUTO_INCREMENT,
  `INSTID` int(11) DEFAULT NULL,
  `ROOTINSTID` int(11) DEFAULT NULL,
  `ROLENAME` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bpm_rolemapping`
--

LOCK TABLES `bpm_rolemapping` WRITE;
/*!40000 ALTER TABLE `bpm_rolemapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `bpm_rolemapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_rsrc_dim`
--

DROP TABLE IF EXISTS `bpm_rsrc_dim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_rsrc_dim` (
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
-- Dumping data for table `bpm_rsrc_dim`
--

LOCK TABLES `bpm_rsrc_dim` WRITE;
/*!40000 ALTER TABLE `bpm_rsrc_dim` DISABLE KEYS */;
INSERT INTO `bpm_rsrc_dim` VALUES ('100002899287992','조진원',NULL,NULL,NULL,NULL,NULL,1,NULL),('1401720840','1401720840',NULL,NULL,NULL,NULL,NULL,1,NULL),('PJJ','Jongjun Park','Consulting 1Team','CON1',NULL,NULL,NULL,0,NULL),('test','Tester','','',NULL,NULL,NULL,1,2011);
/*!40000 ALTER TABLE `bpm_rsrc_dim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_seq`
--

DROP TABLE IF EXISTS `bpm_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_seq` (
  `SEQ` int(11) DEFAULT NULL,
  `TBNAME` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `MODDATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bpm_seq`
--

LOCK TABLES `bpm_seq` WRITE;
/*!40000 ALTER TABLE `bpm_seq` DISABLE KEYS */;
INSERT INTO `bpm_seq` VALUES (2,'SEQ_FAVORITE_CONTACTS',NULL,NULL),(256,'procdef','procdef','2012-05-18 14:58:20'),(380,'procdefver','procdefver','2012-03-22 00:58:52'),(175,'procinst','procinst','2012-03-29 18:40:10'),(187,'workitem','workitem','2012-03-29 18:40:11'),(2,'tag','tag','2011-09-26 00:57:04'),(41,'WORKSPACE','WORKSPACE','2012-03-15 18:43:55'),(1,'PEOPLEINWS','PEOPLEINWS','2012-03-15 11:02:44'),(29,'PEOPLEWS','PEOPLEWS','2012-03-15 18:43:55');
/*!40000 ALTER TABLE `bpm_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_strinst`
--

DROP TABLE IF EXISTS `bpm_strinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_strinst` (
  `INSTID` int(11) DEFAULT NULL,
  `STRTGID` int(11) DEFAULT NULL,
  `ISDELETED` varchar(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bpm_strinst`
--

LOCK TABLES `bpm_strinst` WRITE;
/*!40000 ALTER TABLE `bpm_strinst` DISABLE KEYS */;
/*!40000 ALTER TABLE `bpm_strinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_strtg`
--

DROP TABLE IF EXISTS `bpm_strtg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_strtg` (
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
-- Dumping data for table `bpm_strtg`
--

LOCK TABLES `bpm_strtg` WRITE;
/*!40000 ALTER TABLE `bpm_strtg` DISABLE KEYS */;
/*!40000 ALTER TABLE `bpm_strtg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_strtg_parentmapping`
--

DROP TABLE IF EXISTS `bpm_strtg_parentmapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_strtg_parentmapping` (
  `STRTGID` int(11) DEFAULT NULL,
  `PARENTID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bpm_strtg_parentmapping`
--

LOCK TABLES `bpm_strtg_parentmapping` WRITE;
/*!40000 ALTER TABLE `bpm_strtg_parentmapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `bpm_strtg_parentmapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_tag`
--

DROP TABLE IF EXISTS `bpm_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_tag` (
  `TAGID` int(11) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `COMCODE` varchar(20) NOT NULL,
  PRIMARY KEY (`TAGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bpm_tag`
--

LOCK TABLES `bpm_tag` WRITE;
/*!40000 ALTER TABLE `bpm_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `bpm_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_tagmapping`
--

DROP TABLE IF EXISTS `bpm_tagmapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_tagmapping` (
  `TAGID` int(11) NOT NULL,
  `INSTID` int(11) NOT NULL,
  `ROOTINSTID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bpm_tagmapping`
--

LOCK TABLES `bpm_tagmapping` WRITE;
/*!40000 ALTER TABLE `bpm_tagmapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `bpm_tagmapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpm_time_dim`
--

DROP TABLE IF EXISTS `bpm_time_dim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_time_dim` (
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
-- Dumping data for table `bpm_time_dim`
--

LOCK TABLES `bpm_time_dim` WRITE;
/*!40000 ALTER TABLE `bpm_time_dim` DISABLE KEYS */;
/*!40000 ALTER TABLE `bpm_time_dim` ENABLE KEYS */;
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
  `DEFID` varchar(100) DEFAULT NULL,
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
  KEY `FK33852DAF78EB68E6` (`ROOTINSTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bpm_worklist`
--

LOCK TABLES `bpm_worklist` WRITE;
/*!40000 ALTER TABLE `bpm_worklist` DISABLE KEYS */;
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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
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
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `CompanyCode` char(4) NOT NULL,
  `CompanyName` varchar(20) NOT NULL,
  `typeCode` char(4) DEFAULT NULL,
  `typeName` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comtable`
--

DROP TABLE IF EXISTS `comtable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comtable` (
  `COMCODE` varchar(20) NOT NULL DEFAULT '',
  `COMNAME` varchar(30) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `ISDELETED` varchar(1) DEFAULT '0',
  PRIMARY KEY (`COMCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comtable`
--

LOCK TABLES `comtable` WRITE;
/*!40000 ALTER TABLE `comtable` DISABLE KEYS */;
INSERT INTO `comtable` VALUES ('1401720840','1401720840',NULL,'0'),('MasterSystemCompany','Master System Company',NULL,'0'),('uEngine','uEngine Solutions',NULL,'0');
/*!40000 ALTER TABLE `comtable` ENABLE KEYS */;
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
INSERT INTO `contact` VALUES ('1401720840','565089870','Jae-Heon Kim'),('1401720840','593670837','정진호'),('1401720840','579204709','유영만'),('1401720840','608461498','서진호'),('100002899287992','1401720840','장진영'),('1401720840','100000007816099','류승호'),('1401720840','100000682377840','Kiwon Park'),('1401720840','100002899287992','조진원'),('100002899287992','100000007816099','류승호'),('100002899287992','100000319278646','이진원'),('100002899287992','100000569910670','박성수'),('100002899287992','100000682377840','Kiwon Park'),('100002899287992','100001062388946','DongHyun Lee'),('100002899287992','100001228353864','김보상'),('100002899287992','100001346841314','오병택'),('100002899287992','100001361210479','유엔진솔루션즈'),('100002899287992','100001471881804','강서구'),('100002899287992','100001490120370','김형국'),('100002899287992','100001503655048','김영재'),('100002899287992','100001517300062','윤병선');
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
INSERT INTO `contents` VALUES (7,5,'0','내용을 추가',NULL,0,0,NULL,NULL,NULL),(9,8,'0','1. 기본 입력창 만들기 -1.1. 달력, 1.2. 텍스트영역, 1.3. 소스코드입력, 1.4. ',NULL,0,0,NULL,NULL,NULL),(15,2,'file',NULL,NULL,0,0,NULL,NULL,NULL),(17,2,'file',NULL,NULL,0,0,NULL,NULL,NULL),(27,9,'p','  안녕하세요... 새새 메뉴 내용입니다. ',NULL,0,0,NULL,NULL,NULL),(28,9,'p','  두번째 단락.. ',NULL,0,0,NULL,NULL,NULL),(32,4,'p','새 새 메뉴에 대한 글쓰기를 합니다. ',NULL,0,0,NULL,NULL,NULL),(43,5,'p','  다른 메뉴에 넣은값은? 첫번째.. ',NULL,0,0,2,NULL,NULL),(44,5,'p','  두번째 넣은 값은?',NULL,0,0,0,NULL,NULL),(52,3,'p','  새새메뉴',NULL,0,0,1,NULL,NULL),(53,16,'p','<h1> 메타데이터란? <h1>\n\n\'데이터를 위한 데이터\' 의 뜻인 메타데이터는 ....',NULL,0,0,1,NULL,NULL),(54,17,'p','<h1> 스펙요청은 우측 Feedback란에 남겨주시면 채택된 스펙이 이하에 등록처리됩니다 </h1>\n\n* 피드백을 등록하려면 페이스북 로그인이 필요합니다.',NULL,0,0,1,NULL,NULL),(55,18,'p','기존 사례\n<ul>\n<li> BPM의 여수신 프로세스에 적용한 사례\n<li> 보험 추천 상품 사례 - 엔터프라이즈 매시업\n<li> 상품 상담 - 심사 프로세스\n<li> 룰과의 연계를 통한 심사 프로세스 - 고객 스코어링\n<li> CEP엔진과의 연계를 통한 사기 검출, 위기 검출 프로세스\n\n</ul>',NULL,0,0,2,NULL,NULL),(56,18,'p','제품을 왜 만드는가?\n  <ul>\n<li>외산 교체 - 호주의 금융공학 제품들의 시장 크기 - 그리고 그의 분석 --> 엑스페리언 --> BPM + BRE의 아주 잘 융합된 구조\n<li>시장 접근 방향 - 오픈 이노베이션 - 세계시장 진입\n</ul>',NULL,0,0,1,NULL,NULL),(57,18,'p','  TODO\n<p>\n1. 프로세스 매시업 플랫폼 화면<br>\n2. 룰과의 연계 시나리오 - 엑스페리언 시나리오 참조, KCB룰 케이스 ',NULL,0,0,3,NULL,NULL),(58,15,'p','  소개 내용을 넣을 예정',NULL,0,0,1,NULL,NULL),(60,16,'file',NULL,'/Users/jyjang/Documents/1_�������곌���낵湲곕�.doc',0,0,2,NULL,NULL),(61,16,'file',NULL,'/Users/jyjang/Documents/123 (1).jpg',0,0,3,NULL,NULL),(63,19,'p',NULL,NULL,0,0,1,NULL,NULL),(64,19,'file','image/jpeg','fileSystem/_Users_jyjang_Documents_123.jpg',0,0,2,NULL,NULL),(65,19,'file','application/pdf','fileSystem/metaworks3.pdf',0,0,3,NULL,NULL),(66,18,'p','value for 206',NULL,0,0,4,NULL,NULL),(69,18,'src','1234567891011121314151617181920212223242526package org.metaworks.website;import org.metaworks.annotation.Face;import org.metaworks.annotation.Hidden;public class SourceCodeContents extends Contents{        public SourceCodeContents(){        setType(\"src\");    }    @Override    @Hidden(on=false) //overrides the annotation    @Face(            ejsPath=\"genericfaces/SourceEditor.ejs\",            options={\"width\"},            values={\"670\"}                        )    public String getParagraph() {        // TODO Auto-generated method stub        return super.getParagraph();    }}',NULL,0,0,5,'1401720840','장진영'),(71,18,'src','public class Test{\n    \n    String name;\n    \n    \n}',NULL,0,0,6,'1401720840','장진영'),(72,21,'src','package org.metaworks.example;\n\nimport java.util.ArrayList;\nimport java.util.Hashtable;\n\n//import org.springframework.context.annotation.Scope;\n//import org.springframework.stereotype.Service;\n\n\npublic class AddressBook implements IAddressBook{\n    \n	static Hashtable<String, IPerson> book = new Hashtable<String, IPerson>();\n	\n	public IPerson findPerson(String name){\n return book.get(name);\n	}\n	\n	public IPerson[] listPersonsInSameAge(int age){\n ArrayList<IPerson> list = new ArrayList();\n	 \n	 for(String key : book.keySet()){\n IPerson p = book.get(key);\n	 if(p.getAge()==age){\n	 list.add(p);\n	 }\n	 }\n	 \n IPerson[] plist = new IPerson[list.size()];\n	 list.toArray(plist);\n	 return plist;\n	}\n \n	public void putPerson(IPerson person){\n	 book.put(person.getName(), person);\n	}\n	\n	public PersonAndContact findPersonAndContact(String name){\n IPerson p = findPerson(name);\n	 PersonAndContact personAndContact = new PersonAndContact();\n	 personAndContact.setContact(p.getContact());\n personAndContact.setPerson(p);\n	 \n	 personAndContact.setId(\"#ID001\");\n personAndContact.setOrder(5);\n	 \n	 return personAndContact;\n	}\n	\n}\n\n ',NULL,0,0,1,'1401720840','장진영'),(73,23,'src','package org.metaworks.example;\n\nimport java.util.ArrayList;\nimport java.util.Hashtable;\n\n//import org.springframework.context.annotation.Scope;\n//import org.springframework.stereotype.Service;\n\n\npublic class AddressBook implements IAddressBook{\n    \n	static Hashtable<String, IPerson> book = new Hashtable<String, IPerson>();\n	\n	public IPerson findPerson(String name){\n	 return book.get(name);\n	}\n	\n	public IPerson[] listPersonsInSameAge(int age){\n	 ArrayList<IPerson> list = new ArrayList();\n	 \n for(String key : book.keySet()){\n	 IPerson p = book.get(key);\n if(p.getAge()==age){\n	 list.add(p);\n	 }\n	 }\n	 \n	 IPerson[] plist = new IPerson[list.size()];\n	 list.toArray(plist);\n	 return plist;\n	}\n	\n	public void putPerson(IPerson person){\n	 book.put(person.getName(), person);\n	}\n	\n public PersonAndContact findPersonAndContact(String name){\n	 IPerson p = findPerson(name);\n	 PersonAndContact personAndContact = new PersonAndContact();\n	 personAndContact.setContact(p.getContact());\n personAndContact.setPerson(p);\n	 \n	 personAndContact.setId(\"#ID001\");\n personAndContact.setOrder(5);\n	 \n	 return personAndContact;\n	}\n	\n}\n\n ',NULL,0,0,1,'1401720840','장진영'),(74,25,'src','package org.metaworks.example;\n\nimport org.metaworks.annotation.Face;\nimport org.metaworks.annotation.Id;\nimport org.metaworks.annotation.NonLoadable;\nimport org.metaworks.annotation.Table;\nimport org.metaworks.dao.IDAO;\n\n    @Table(name=\"Person\")\n	public interface IPerson extends IDAO{\n	 \n	 @Id\n	 public String getName();\n	 public void setName(String name);\n	\n	 public int getAge();\n	 public void setAge(int age);\n	 \n @NonLoadable\n	 public Contact getContact();\n	 public void setContact(Contact contact);\n	 \n	 @Face(ejsPath=\"faces/image.ejs\")\n	 public String getPortraitURL();\n	 public void setPortraitURL(String portraitURL);\n	 \n	 \n	 public boolean isMyFried();\n	 public void setMyFried(boolean isMyFried);\n	 \n	 ///method\n \n	 public IPerson fill() throws Exception;\n	}',NULL,0,0,1,'1401720840','장진영'),(75,26,'src','package org.metaworks.example;\n\nimport org.metaworks.annotation.Face;\nimport org.metaworks.annotation.ServiceMethod;\nimport org.metaworks.dao.Database;\n\npublic class Person extends Database<IPerson> implements IPerson{\n\n    String name;\n\n	 public String getName() {\n	 return name;\n	 }\n	 public void setName(String name) {\n	 this.name = name;\n	 }\n\n\n	int age;\n	\n	 public int getAge() {\n	 return age;\n	 }\n	 public void setAge(int age) {\n	 this.age = age;\n	 }\n\n\n	Contact contact;\n	\n	 public Contact getContact() {\n	 return contact;\n	 }\n	 public void setContact(Contact contact) {\n	 this.contact = contact;\n	 }\n\n\n	String portraitURL;\n @Face(ejsPath=\"faces/image.ejs\")\n\n	 public String getPortraitURL() {\n	 return portraitURL;\n	 }\n	 public void setPortraitURL(String portraitURL) {\n this.portraitURL = portraitURL;\n	 }\n\n\n	boolean isMyFried;\n\n	 public boolean isMyFried() {\n	 return isMyFried;\n	 }\n	 public void setMyFried(boolean isMyFried) {\n	 this.isMyFried = isMyFried;\n	 }\n	\n\n	@ServiceMethod\n	public IPerson fill() throws Exception{\n	 return databaseMe();\n	}\n	\n @ServiceMethod(target=TARGET_AUTO)\n	public PersonDetail wall() throws Exception{\n	 return new PersonDetail(this);\n	}\n	\n \n}\n',NULL,0,0,1,'1401720840','장진영'),(76,21,'p','  adfsfsf',NULL,0,0,0,'1401720840','장진영'),(77,21,'p','  sfasf',NULL,0,0,3,'1401720840','장진영'),(78,24,'p','  ergdfgsg',NULL,0,0,2,'1401720840','장진영'),(79,24,'p','  sfggfggdf',NULL,0,0,1,'1401720840','장진영');
/*!40000 ALTER TABLE `contents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doc_comments`
--

DROP TABLE IF EXISTS `doc_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doc_comments` (
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
-- Dumping data for table `doc_comments`
--

LOCK TABLES `doc_comments` WRITE;
/*!40000 ALTER TABLE `doc_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `doc_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_prop_table`
--

DROP TABLE IF EXISTS `emp_prop_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emp_prop_table` (
  `PROPID` int(11) NOT NULL,
  `COMCODE` varchar(20) NOT NULL,
  `EMPCODE` varchar(20) NOT NULL,
  `PROPKEY` varchar(40) NOT NULL,
  `PROPVALUE` varchar(100) NOT NULL,
  PRIMARY KEY (`PROPID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_prop_table`
--

LOCK TABLES `emp_prop_table` WRITE;
/*!40000 ALTER TABLE `emp_prop_table` DISABLE KEYS */;
INSERT INTO `emp_prop_table` VALUES (1,'uEngine','JJY','loggedMailId','test'),(2,'uEngine','JJY','loggedMailPw','test1234');
/*!40000 ALTER TABLE `emp_prop_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employeeCode` varchar(4) DEFAULT NULL,
  `employeeName` varchar(20) DEFAULT NULL,
  `companyCode` varchar(4) DEFAULT NULL,
  `companyName` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('E001','조진원',NULL,NULL),('E002','E002',NULL,NULL),('E003','E003','C001','C001'),('a','a',NULL,NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emptable`
--

DROP TABLE IF EXISTS `emptable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emptable` (
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
-- Dumping data for table `emptable`
--

LOCK TABLES `emptable` WRITE;
/*!40000 ALTER TABLE `emptable` DISABLE KEYS */;
INSERT INTO `emptable` VALUES ('1401720840','1401720840','test',1,NULL,'jyjang@uengine.org','1401720840','1401720840','0','ko',NULL,NULL,NULL,NULL,'',NULL),('CJK','Jaekil Choi','test',0,'과장',NULL,'PM','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('CJS','Jisun Choi','test',0,'사원',NULL,'QA','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('CWS','Wooseok Choi','test',0,'사원',NULL,'PM','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('HJR','Jeongryeon Ha','test',0,'과장',NULL,'PI','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('JSY','Sungyeol Jung','test',0,'과장',NULL,'CON2','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('KEM','Eunmi Kim','test',0,'사원',NULL,'MANAGEMENT','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('KHK','Hyungkook Kim','test',0,'사원',NULL,'SOLUTION','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('KHN','Hannah Kim','test',0,'대리',NULL,'MAR','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('KSG','Seogu Kang','test',0,'대리',NULL,'SALES','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('KTW','Taiwook Kang','test',0,'과장',NULL,'PI','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('KYT','Youngtak Kim','test',0,'과장',NULL,'SOLUTION','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('LDH','Donghyun Lee','test',0,'과장',NULL,'QA','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('LHS','Hyeseon Lee','test',0,'대리',NULL,'MANAGEMENT','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('LJW','Jinwon Lee','test',0,'사원',NULL,'SOLUTION','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('MasterSystemAdmin','Master System Admin User','test',-1,NULL,NULL,'MasterSystemPart','MasterSystemCompany','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('PDJ','DongJo Park','test',0,'차장',NULL,'PI','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('PJJ','Jongjun Park','test',0,'대리',NULL,'CON1','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('PMK','Myeongkyun Bok','test',0,'차장',NULL,'QA','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('RSH','Suengho Ryu','test',0,'사원',NULL,'CON2','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('SHJ','Hojoon sung','test',0,'과장',NULL,'MAR','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('SSH','Sanghyo Song','test',0,'이사',NULL,'MARKETING','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('test','Tester','test',1,'Tester',NULL,'','uEngine','0','en',NULL,NULL,NULL,NULL,'100002969720934',NULL),('test_kp','Tester_ko','test',1,'Tester',NULL,'','uEngine','0','ko',NULL,NULL,NULL,NULL,NULL,NULL),('uEngineAdmin','uEngine Admin','test',1,'Tenant Admin',NULL,'uEngineTenantAdmin','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('YBS','ByeoungSun Yun','test',0,'사원',NULL,'CON1','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('YJY','Jooyong Yook','test',0,'차장',NULL,'CON1','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),('ZSZ','Shuzhu Zhang','test',0,'대리',NULL,'MAR','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `emptable` ENABLE KEYS */;
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
INSERT INTO `facebookloginuser` VALUES ('1401720840',1),('100002899287992',1);
/*!40000 ALTER TABLE `facebookloginuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_contacts`
--

DROP TABLE IF EXISTS `favorite_contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite_contacts` (
  `FAV_CONTACT_ID` int(11) NOT NULL DEFAULT '0',
  `EMPCODE` varchar(20) DEFAULT NULL,
  `FRIENDTYPE` int(11) DEFAULT NULL,
  `FRIENDID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`FAV_CONTACT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_contacts`
--

LOCK TABLES `favorite_contacts` WRITE;
/*!40000 ALTER TABLE `favorite_contacts` DISABLE KEYS */;
INSERT INTO `favorite_contacts` VALUES (1,'test',0,'PJJ'),(50,'test',2,'115300695203836');
/*!40000 ALTER TABLE `favorite_contacts` ENABLE KEYS */;
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
-- Table structure for table `instlistview`
--

DROP TABLE IF EXISTS `instlistview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instlistview` (
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
-- Dumping data for table `instlistview`
--

LOCK TABLES `instlistview` WRITE;
/*!40000 ALTER TABLE `instlistview` DISABLE KEYS */;
/*!40000 ALTER TABLE `instlistview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` char(6) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT 'item name'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
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
-- Table structure for table `marketitem`
--

DROP TABLE IF EXISTS `marketitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marketitem` (
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
-- Dumping data for table `marketitem`
--

LOCK TABLES `marketitem` WRITE;
/*!40000 ALTER TABLE `marketitem` DISABLE KEYS */;
INSERT INTO `marketitem` VALUES (1,0,NULL,'정의',0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(2,1,NULL,'새로운 파일 테스트',0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(3,0,NULL,'이건 저장될걸',0,NULL,0,'fileSystem/mw3.png',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(4,0,NULL,'아이템 추가하자... ',0,NULL,0,'fileSystem/123.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(5,0,NULL,'sdfsdafdsf',0,NULL,0,'fileSystem/1_�������곌���낵湲곕�.doc',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(6,0,NULL,'sdfasdfdasfsdf',0,NULL,0,'fileSystem/_Users_jyjang_Documents_123 (2).jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL);
/*!40000 ALTER TABLE `marketitem` ENABLE KEYS */;
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
-- Table structure for table `mm_arrowlink`
--

DROP TABLE IF EXISTS `mm_arrowlink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_arrowlink` (
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
-- Dumping data for table `mm_arrowlink`
--

LOCK TABLES `mm_arrowlink` WRITE;
/*!40000 ALTER TABLE `mm_arrowlink` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_arrowlink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_attribute`
--

DROP TABLE IF EXISTS `mm_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_attribute` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NODE_ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `VALUE` longtext,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_attribute`
--

LOCK TABLES `mm_attribute` WRITE;
/*!40000 ALTER TABLE `mm_attribute` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_attribute_registry`
--

DROP TABLE IF EXISTS `mm_attribute_registry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_attribute_registry` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MAP_REVISION_ID` bigint(20) NOT NULL,
  `SHOW_ATTRIBUTES` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_attribute_registry`
--

LOCK TABLES `mm_attribute_registry` WRITE;
/*!40000 ALTER TABLE `mm_attribute_registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_attribute_registry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_categories`
--

DROP TABLE IF EXISTS `mm_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_categories` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `LFT` int(10) unsigned DEFAULT NULL,
  `RGT` int(10) unsigned DEFAULT NULL,
  `PARENTID` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_categories`
--

LOCK TABLES `mm_categories` WRITE;
/*!40000 ALTER TABLE `mm_categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_categories` ENABLE KEYS */;
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
-- Table structure for table `mm_cloud`
--

DROP TABLE IF EXISTS `mm_cloud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_cloud` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NODE_ID` bigint(20) NOT NULL,
  `COLOR` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_cloud`
--

LOCK TABLES `mm_cloud` WRITE;
/*!40000 ALTER TABLE `mm_cloud` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_cloud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_edge`
--

DROP TABLE IF EXISTS `mm_edge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_edge` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NODE_ID` bigint(20) NOT NULL,
  `COLOR` varchar(10) DEFAULT NULL,
  `STYLE` varchar(20) DEFAULT NULL,
  `WIDTH` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_edge`
--

LOCK TABLES `mm_edge` WRITE;
/*!40000 ALTER TABLE `mm_edge` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_edge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_font`
--

DROP TABLE IF EXISTS `mm_font`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_font` (
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
-- Dumping data for table `mm_font`
--

LOCK TABLES `mm_font` WRITE;
/*!40000 ALTER TABLE `mm_font` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_font` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_foreignobject`
--

DROP TABLE IF EXISTS `mm_foreignobject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_foreignobject` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NODE_ID` bigint(20) NOT NULL,
  `CONTENT` longtext,
  `WIDTH` bigint(20) DEFAULT '0',
  `HEIGHT` bigint(20) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_foreignobject`
--

LOCK TABLES `mm_foreignobject` WRITE;
/*!40000 ALTER TABLE `mm_foreignobject` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_foreignobject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_group`
--

DROP TABLE IF EXISTS `mm_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_group` (
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
-- Dumping data for table `mm_group`
--

LOCK TABLES `mm_group` WRITE;
/*!40000 ALTER TABLE `mm_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_group_member`
--

DROP TABLE IF EXISTS `mm_group_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_group_member` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `GROUPID` bigint(20) unsigned NOT NULL,
  `USERID` bigint(20) unsigned NOT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `STATUS` bigint(20) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_group_member`
--

LOCK TABLES `mm_group_member` WRITE;
/*!40000 ALTER TABLE `mm_group_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_group_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_group_member_status_type`
--

DROP TABLE IF EXISTS `mm_group_member_status_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_group_member_status_type` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SHORTNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_group_member_status_type`
--

LOCK TABLES `mm_group_member_status_type` WRITE;
/*!40000 ALTER TABLE `mm_group_member_status_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_group_member_status_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_group_password`
--

DROP TABLE IF EXISTS `mm_group_password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_group_password` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `GROUPID` bigint(20) unsigned NOT NULL,
  `PASSWORD` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_group_password`
--

LOCK TABLES `mm_group_password` WRITE;
/*!40000 ALTER TABLE `mm_group_password` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_group_password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_group_policy_type`
--

DROP TABLE IF EXISTS `mm_group_policy_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_group_policy_type` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SHORTNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_group_policy_type`
--

LOCK TABLES `mm_group_policy_type` WRITE;
/*!40000 ALTER TABLE `mm_group_policy_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_group_policy_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_hook`
--

DROP TABLE IF EXISTS `mm_hook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_hook` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NODE_ID` bigint(20) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_hook`
--

LOCK TABLES `mm_hook` WRITE;
/*!40000 ALTER TABLE `mm_hook` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_hook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_icon`
--

DROP TABLE IF EXISTS `mm_icon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_icon` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NODE_ID` bigint(20) NOT NULL,
  `BUILTIN` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_icon`
--

LOCK TABLES `mm_icon` WRITE;
/*!40000 ALTER TABLE `mm_icon` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_icon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_map`
--

DROP TABLE IF EXISTS `mm_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_map` (
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
-- Dumping data for table `mm_map`
--

LOCK TABLES `mm_map` WRITE;
/*!40000 ALTER TABLE `mm_map` DISABLE KEYS */;
INSERT INTO `mm_map` VALUES (1,'uEngine Solutions','0.9.0','2011-09-25 13:35:26','ZWE2MmI4MDEtZWQxOS00ZjIxLWJjM2ItN2EyMTIyNzY3ZTll',NULL,NULL),(2,'1401720840','0.9.0','2011-09-26 21:10:47','NGVkZmYzYzctNDRiMy00ZTQxLWE5NDItMWNlNzE2NjIxZTQ4',NULL,NULL),(3,'Reply','0.9.0','2011-10-15 17:01:53','ZTRiNGMyZTQtNjcyYi00YmEzLTk2YTAtYjg0MWMxOTYyNjcy',NULL,NULL);
/*!40000 ALTER TABLE `mm_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_map_owner`
--

DROP TABLE IF EXISTS `mm_map_owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_map_owner` (
  `ID` bigint(20) NOT NULL,
  `MAPID` bigint(20) NOT NULL,
  `OWNER` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_map_owner`
--

LOCK TABLES `mm_map_owner` WRITE;
/*!40000 ALTER TABLE `mm_map_owner` DISABLE KEYS */;
INSERT INTO `mm_map_owner` VALUES (1,1,'uEngine'),(2,2,'1401720840');
/*!40000 ALTER TABLE `mm_map_owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_map_revision`
--

DROP TABLE IF EXISTS `mm_map_revision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_map_revision` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MAP_ID` bigint(20) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  `CURRENT_REVISION_P` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_map_revision`
--

LOCK TABLES `mm_map_revision` WRITE;
/*!40000 ALTER TABLE `mm_map_revision` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_map_revision` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_map_timeline`
--

DROP TABLE IF EXISTS `mm_map_timeline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_map_timeline` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `MAP_ID` bigint(20) unsigned NOT NULL,
  `XML` longblob NOT NULL,
  `SAVED` bigint(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_map_timeline`
--

LOCK TABLES `mm_map_timeline` WRITE;
/*!40000 ALTER TABLE `mm_map_timeline` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_map_timeline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_node`
--

DROP TABLE IF EXISTS `mm_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_node` (
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
-- Dumping data for table `mm_node`
--

LOCK TABLES `mm_node` WRITE;
/*!40000 ALTER TABLE `mm_node` DISABLE KEYS */;
INSERT INTO `mm_node` VALUES (260,0,'#660000','#ffffff','false','ID_901618295',NULL,NULL,NULL,'uEngine Solutions','1316925326519','1317039442491',-295,NULL,-30,NULL,NULL,1,14,0,NULL,NULL,NULL,0,NULL,1,'',NULL,'test','0:0:0:0:0:0:0:1%0',NULL,NULL),(261,0,'#c4a0b3',NULL,'false','ID_1635425370',NULL,'right',NULL,'중간에 애매한거 보냈다가 오류나면','1316934906809','1317045666649',NULL,NULL,NULL,NULL,NULL,2,11,260,NULL,NULL,NULL,0,NULL,1,'test','0:0:0:0:0:0:0:1%0','test','0:0:0:0:0:0:0:1%0',NULL,NULL),(262,0,'#DEBCC3',NULL,'false','ID_1712112131',NULL,NULL,NULL,'음.. 저장이 좔 되는균','1316934935950','1317017410523',NULL,NULL,NULL,NULL,NULL,3,4,261,NULL,NULL,NULL,0,NULL,1,'test','0:0:0:0:0:0:0:1%0','test','0:0:0:0:0:0:0:1%0',NULL,NULL),(265,0,'#CBBEC5',NULL,'false','ID_1016695379',NULL,NULL,NULL,'테스트합니다','1316935578781','1317039456740',NULL,NULL,NULL,NULL,NULL,5,10,261,NULL,NULL,NULL,0,NULL,1,'test','0:0:0:0:0:0:0:1%0','test','0:0:0:0:0:0:0:1%0',NULL,NULL),(268,0,'#D5DED9','#ffffff','false','ID_1609238373',NULL,NULL,NULL,'이제 됐어요... ㅜㅜ','1317016135427','1317039458042',7,NULL,-6,NULL,NULL,6,9,265,NULL,NULL,NULL,0,NULL,1,'test','0:0:0:0:0:0:0:1%0','test','0:0:0:0:0:0:0:1%0',NULL,NULL),(269,0,'#c4a0b3',NULL,'false','ID_194240397',NULL,'right',NULL,'dfsdad','1317017359011','1317019342047',92,NULL,-11,NULL,NULL,7,8,268,NULL,NULL,NULL,0,NULL,1,'test','0:0:0:0:0:0:0:1%0','test','0:0:0:0:0:0:0:1%0',NULL,NULL),(270,0,'#660000','#ffffff','false','ID_920241272',NULL,NULL,NULL,'1401720840','1317039047320','1317039056291',-154,NULL,-15,NULL,NULL,1,8,0,NULL,NULL,NULL,0,NULL,2,'',NULL,'1401720840','0:0:0:0:0:0:0:1%0',NULL,NULL),(271,0,'#c4a0b3',NULL,'false','ID_1268584850',NULL,'right',NULL,'뭘 입력한다','1317039059341','1317039065394',NULL,NULL,NULL,NULL,NULL,2,3,270,NULL,NULL,NULL,0,NULL,2,'1401720840','0:0:0:0:0:0:0:1%0','1401720840','0:0:0:0:0:0:0:1%0',NULL,NULL),(272,0,'#829ddb',NULL,'false','ID_1476746703',NULL,'right',NULL,'페이스북 테스트입니다','1317039065513','1317039310376',NULL,NULL,NULL,NULL,NULL,4,7,270,NULL,NULL,NULL,0,NULL,2,'1401720840','0:0:0:0:0:0:0:1%0','1401720840','0:0:0:0:0:0:0:1%0',NULL,NULL),(273,0,'#A5C7FB',NULL,'false','ID_523865388',NULL,NULL,NULL,'죄수번호가 뜨는군요','1317039070943','1317039079006',NULL,NULL,NULL,NULL,NULL,5,6,272,NULL,NULL,NULL,0,NULL,2,'1401720840','0:0:0:0:0:0:0:1%0','1401720840','0:0:0:0:0:0:0:1%0',NULL,NULL),(274,0,'#829ddb',NULL,'false','ID_1998165915',NULL,'right',NULL,'담벼락에 게시한 내용에 대한 친구들의 댓글을 자동으로 수집해와서 내 지식에 수집해주는 기능','1317043952588','1317045055602',NULL,NULL,NULL,NULL,NULL,12,13,260,NULL,NULL,NULL,0,NULL,1,'test','0:0:0:0:0:0:0:1%0','test','0:0:0:0:0:0:0:1%0',NULL,NULL),(275,0,NULL,NULL,NULL,'ID_1536885496',NULL,NULL,NULL,'Reply','1318665713476','1318665713476',NULL,NULL,NULL,NULL,NULL,1,2,0,NULL,NULL,NULL,0,NULL,3,'',NULL,'',NULL,NULL,NULL);
/*!40000 ALTER TABLE `mm_node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_parameters`
--

DROP TABLE IF EXISTS `mm_parameters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_parameters` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `HOOK_ID` bigint(20) NOT NULL,
  `REMINDUSERAT` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_parameters`
--

LOCK TABLES `mm_parameters` WRITE;
/*!40000 ALTER TABLE `mm_parameters` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_parameters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_persistent_login`
--

DROP TABLE IF EXISTS `mm_persistent_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_persistent_login` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `USERID` bigint(20) unsigned NOT NULL,
  `PERSISTENT_KEY` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `LASTLOGIN` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_persistent_login`
--

LOCK TABLES `mm_persistent_login` WRITE;
/*!40000 ALTER TABLE `mm_persistent_login` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_persistent_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_queuedata`
--

DROP TABLE IF EXISTS `mm_queuedata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_queuedata` (
  `ROOMNUMBER` longtext,
  `TEXTDATA` longtext,
  `CREATED` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_queuedata`
--

LOCK TABLES `mm_queuedata` WRITE;
/*!40000 ALTER TABLE `mm_queuedata` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_queuedata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_richcontent`
--

DROP TABLE IF EXISTS `mm_richcontent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_richcontent` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NODE_ID` bigint(20) NOT NULL,
  `TYPE` varchar(5) DEFAULT NULL,
  `CONTENT` longtext,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_richcontent`
--

LOCK TABLES `mm_richcontent` WRITE;
/*!40000 ALTER TABLE `mm_richcontent` DISABLE KEYS */;
INSERT INTO `mm_richcontent` VALUES (2,266,'NODE','<html>\n   <head>\n \n   </head>\n   <body>\n <p>test</p>\n   </body>\n </html>');
/*!40000 ALTER TABLE `mm_richcontent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_role`
--

DROP TABLE IF EXISTS `mm_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_role` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SHORTNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_role`
--

LOCK TABLES `mm_role` WRITE;
/*!40000 ALTER TABLE `mm_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_role_assignments`
--

DROP TABLE IF EXISTS `mm_role_assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_role_assignments` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ROLEID` bigint(20) unsigned NOT NULL,
  `USERID` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ROLEID` (`ROLEID`,`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_role_assignments`
--

LOCK TABLES `mm_role_assignments` WRITE;
/*!40000 ALTER TABLE `mm_role_assignments` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_role_assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_role_capabilities`
--

DROP TABLE IF EXISTS `mm_role_capabilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_role_capabilities` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ROLEID` bigint(20) unsigned NOT NULL,
  `CAPABILITY` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `PERMISSION` bigint(10) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ROLEID` (`ROLEID`,`CAPABILITY`,`PERMISSION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_role_capabilities`
--

LOCK TABLES `mm_role_capabilities` WRITE;
/*!40000 ALTER TABLE `mm_role_capabilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_role_capabilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_share`
--

DROP TABLE IF EXISTS `mm_share`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_share` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `MAPID` bigint(20) unsigned NOT NULL,
  `SHARETYPE` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `MAPID` (`MAPID`,`SHARETYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_share`
--

LOCK TABLES `mm_share` WRITE;
/*!40000 ALTER TABLE `mm_share` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_share` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_share_group`
--

DROP TABLE IF EXISTS `mm_share_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_share_group` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `SHAREID` bigint(20) unsigned NOT NULL,
  `GROUPID` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `SHAREID` (`SHAREID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_share_group`
--

LOCK TABLES `mm_share_group` WRITE;
/*!40000 ALTER TABLE `mm_share_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_share_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_share_password`
--

DROP TABLE IF EXISTS `mm_share_password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_share_password` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `SHAREID` bigint(20) unsigned NOT NULL,
  `PASSWORD` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SHAREID` (`SHAREID`,`PASSWORD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_share_password`
--

LOCK TABLES `mm_share_password` WRITE;
/*!40000 ALTER TABLE `mm_share_password` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_share_password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_share_permission`
--

DROP TABLE IF EXISTS `mm_share_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_share_permission` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `SHAREID` bigint(20) unsigned NOT NULL,
  `PERMISSIONTYPE` bigint(20) unsigned NOT NULL,
  `PERMITED` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_share_permission`
--

LOCK TABLES `mm_share_permission` WRITE;
/*!40000 ALTER TABLE `mm_share_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_share_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_share_permission_type`
--

DROP TABLE IF EXISTS `mm_share_permission_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_share_permission_type` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SHORTNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_share_permission_type`
--

LOCK TABLES `mm_share_permission_type` WRITE;
/*!40000 ALTER TABLE `mm_share_permission_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_share_permission_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_share_type`
--

DROP TABLE IF EXISTS `mm_share_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_share_type` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SHORTNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mm_share_type`
--

LOCK TABLES `mm_share_type` WRITE;
/*!40000 ALTER TABLE `mm_share_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_share_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mm_user`
--

DROP TABLE IF EXISTS `mm_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mm_user` (
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
-- Dumping data for table `mm_user`
--

LOCK TABLES `mm_user` WRITE;
/*!40000 ALTER TABLE `mm_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `mm_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parttable`
--

DROP TABLE IF EXISTS `parttable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parttable` (
  `GLOBALCOM` varchar(20) DEFAULT NULL,
  `PARTCODE` varchar(20) NOT NULL,
  `PARTNAME` varchar(30) DEFAULT NULL,
  `PARENT_PARTCODE` varchar(20) DEFAULT NULL,
  `ISDELETED` varchar(1) DEFAULT '0',
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMCODE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PARTCODE`),
  KEY `FK3B63679B4506931C` (`COMCODE`),
  CONSTRAINT `FK3B63679B4506931C` FOREIGN KEY (`COMCODE`) REFERENCES `comtable` (`COMCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parttable`
--

LOCK TABLES `parttable` WRITE;
/*!40000 ALTER TABLE `parttable` DISABLE KEYS */;
INSERT INTO `parttable` VALUES ('1401720840','1401720840','1401720840',NULL,'0',NULL,NULL),('uEngine','CEO','CEO',NULL,'0','CEO',NULL),('uEngine','CON1','Consulting 1Team','CONSULTING','0','컨설팅 1팀',NULL),('uEngine','CON2','Consulting 2Team','CONSULTING','0','컨설팅 2팀',NULL),('uEngine','CONSULTING','CONSULTING',NULL,'0','컨설팅 부서',NULL),('uEngine','DEV','DEVELOPMENT',NULL,'0','연구개발 부서',NULL),('uEngine','MANAGEMENT','Management Support Team','MARKETING','0','경영지원팀',NULL),('uEngine','MAR','Marketing Team','MARKETING','0','마케팅 팀',NULL),('uEngine','MARKETING','MARKETING AND SALES',NULL,'0','마케팅&세일즈 부서',NULL),('MasterSystemCompany','MasterSystemPart','Master System Admin Group',NULL,'0',NULL,NULL),('uEngine','PI','PI Team','DEV','0','PI팀',NULL),('uEngine','PM','PM Team','DEV','0','PM팀',NULL),('uEngine','QA','QA Team','DEV','0','QA팀',NULL),('uEngine','SALES','Sales Team','MARKETING','0','세일즈 팀',NULL),('uEngine','SOLUTION','Solution Team','CONSULTING','0','솔루션 사업팀',NULL),('uEngine','uEngineTenantAdmin','uEngine Tenand Admin',NULL,'0',NULL,NULL);
/*!40000 ALTER TABLE `parttable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people` (
  `name` varchar(20) NOT NULL COMMENT '이름',
  `id` varchar(30) NOT NULL COMMENT 'e-mail (key)',
  `password` varchar(20) NOT NULL COMMENT '비밀번호',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES ('조진원','somehow@uengine.org','1234');
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peoplews`
--

DROP TABLE IF EXISTS `peoplews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `peoplews` (
  `id` int(10) NOT NULL,
  `workspaceId` int(10) NOT NULL,
  `peopleId` varchar(30) NOT NULL,
  `orderNo` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='People in workspace';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peoplews`
--

LOCK TABLES `peoplews` WRITE;
/*!40000 ALTER TABLE `peoplews` DISABLE KEYS */;
INSERT INTO `peoplews` VALUES (28,40,'somehow@uengine.org',0),(29,41,'somehow@uengine.org',0);
/*!40000 ALTER TABLE `peoplews` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Table structure for table `posting`
--

DROP TABLE IF EXISTS `posting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posting` (
  `writer` varchar(100) DEFAULT NULL,
  `document` varchar(300) DEFAULT NULL,
  `likeit` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posting`
--

LOCK TABLES `posting` WRITE;
/*!40000 ALTER TABLE `posting` DISABLE KEYS */;
INSERT INTO `posting` VALUES ('jinyoung jang','ddsfd',1),('jinyoung jang','sdfsdf',1),('jinyoung jang','sfadf',0),('jinyoung jang','xxxxx',0),('jinyoung jang','eee',1),('jinyoung jang',NULL,0),('jinyoung jang',NULL,0),('jinyoung jang','testse',1),('jinyoung jang','tsetsetset',0),('jinyoung jang','dfsd',0),('jinyoung jang','sadasdad',0),('jinyoung jang','ssss',1),('jinyoung jang','ss',1),('jinyoung jang','dsfsdf',1),('jinyoung jang','sdfsdfsdfsdfsd',1),('jinyoung jang','sdfsadf',1),('jinyoung jang','fsfsdf',1),('jinyoung jang','efsfd',0),('jinyoung jang','dsfadfdasfasdf',0),('jinyoung jang','estet',0),('jinyoung jang','test',0),('jinyoung jang','sdfdsfds',0),('jinyoung jang','tstsesete',0),('jinyoung jang','dsfdf',0),('jinyoung jang','sfkakafsdjskdfjlfjksald',0),('jinyoung jang','ddd',0);
/*!40000 ALTER TABLE `posting` ENABLE KEYS */;
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
-- Table structure for table `processmarket_category`
--

DROP TABLE IF EXISTS `processmarket_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processmarket_category` (
  `CATEGORYID` int(11) NOT NULL,
  `CATEGORYNAME` varchar(255) NOT NULL,
  `PARENTID` int(11) DEFAULT '-1',
  `MODDATE` datetime DEFAULT NULL,
  `ISDELETED` int(11) DEFAULT '0',
  PRIMARY KEY (`CATEGORYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processmarket_category`
--

LOCK TABLES `processmarket_category` WRITE;
/*!40000 ALTER TABLE `processmarket_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `processmarket_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processmarket_item`
--

DROP TABLE IF EXISTS `processmarket_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processmarket_item` (
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
  CONSTRAINT `FKAB6087874506931C` FOREIGN KEY (`COMCODE`) REFERENCES `comtable` (`COMCODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processmarket_item`
--

LOCK TABLES `processmarket_item` WRITE;
/*!40000 ALTER TABLE `processmarket_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `processmarket_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processmarket_purchase`
--

DROP TABLE IF EXISTS `processmarket_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processmarket_purchase` (
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
  CONSTRAINT `FK9305AF754506931C` FOREIGN KEY (`COMCODE`) REFERENCES `comtable` (`COMCODE`),
  CONSTRAINT `FK9305AF757A09946B` FOREIGN KEY (`ITEMID`) REFERENCES `processmarket_item` (`ITEMID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processmarket_purchase`
--

LOCK TABLES `processmarket_purchase` WRITE;
/*!40000 ALTER TABLE `processmarket_purchase` DISABLE KEYS */;
/*!40000 ALTER TABLE `processmarket_purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `projectId` int(5) NOT NULL,
  `projectName` varchar(50) NOT NULL,
  PRIMARY KEY (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roletable`
--

DROP TABLE IF EXISTS `roletable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roletable` (
  `ROLECODE` varchar(20) NOT NULL,
  `COMCODE` varchar(20) DEFAULT NULL,
  `DESCR` varchar(255) DEFAULT NULL,
  `ISDELETED` varchar(1) DEFAULT '0',
  PRIMARY KEY (`ROLECODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roletable`
--

LOCK TABLES `roletable` WRITE;
/*!40000 ALTER TABLE `roletable` DISABLE KEYS */;
INSERT INTO `roletable` VALUES ('1401720840','1401720840','1401720840','0'),('DepartmentReader','uEngine','DepartmentReader','0'),('MasterSystemAdmin','MasterSystemCompany','Master System Admin Role for SaaSable ProcessCodi','0'),('TeamReader','uEngine','TeamReader','0');
/*!40000 ALTER TABLE `roletable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roleusertable`
--

DROP TABLE IF EXISTS `roleusertable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roleusertable` (
  `ROLECODE` varchar(20) NOT NULL,
  `EMPCODE` varchar(20) NOT NULL,
  PRIMARY KEY (`ROLECODE`,`EMPCODE`),
  KEY `FK8CEEB30D7C65B1A` (`ROLECODE`),
  CONSTRAINT `FK8CEEB30D7C65B1A` FOREIGN KEY (`ROLECODE`) REFERENCES `roletable` (`ROLECODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roleusertable`
--

LOCK TABLES `roleusertable` WRITE;
/*!40000 ALTER TABLE `roleusertable` DISABLE KEYS */;
INSERT INTO `roleusertable` VALUES ('1401720840','1401720840'),('DepartmentReader','KBS'),('DepartmentReader','KSH'),('DepartmentReader','SSH'),('MasterSystemAdmin','MasterSystemAdmin'),('TeamReader','CJK'),('TeamReader','JSY'),('TeamReader','KSG'),('TeamReader','KYT'),('TeamReader','LHS'),('TeamReader','PDJ'),('TeamReader','PMK'),('TeamReader','SHJ'),('TeamReader','YJY');
/*!40000 ALTER TABLE `roleusertable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_table`
--

DROP TABLE IF EXISTS `schedule_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule_table` (
  `SCHE_IDX` int(11) DEFAULT NULL,
  `INSTID` int(11) DEFAULT NULL,
  `TRCTAG` varchar(100) DEFAULT NULL,
  `STARTDATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_table`
--

LOCK TABLES `schedule_table` WRITE;
/*!40000 ALTER TABLE `schedule_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `springtable`
--

DROP TABLE IF EXISTS `springtable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `springtable` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `addr` varchar(30) DEFAULT NULL,
  `cellphone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `seq` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `springtable`
--

LOCK TABLES `springtable` WRITE;
/*!40000 ALTER TABLE `springtable` DISABLE KEYS */;
/*!40000 ALTER TABLE `springtable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_candidate`
--

DROP TABLE IF EXISTS `tenant_candidate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenant_candidate` (
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
-- Dumping data for table `tenant_candidate`
--

LOCK TABLES `tenant_candidate` WRITE;
/*!40000 ALTER TABLE `tenant_candidate` DISABLE KEYS */;
INSERT INTO `tenant_candidate` VALUES ('uEngineGroup@uengine.org','uEngineAdmin','test','uEngine','uEngine Solutions','uEngineTenantAdmin','uEngine Tenant Admin','02-567-8301',1,'2011-08-11 12:32:40',NULL,'2011-08-11 12:32:40');
/*!40000 ALTER TABLE `tenant_candidate` ENABLE KEYS */;
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
-- Table structure for table `user2`
--

DROP TABLE IF EXISTS `user2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user2` (
  `name` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user2`
--

LOCK TABLES `user2` WRITE;
/*!40000 ALTER TABLE `user2` DISABLE KEYS */;
/*!40000 ALTER TABLE `user2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user5`
--

DROP TABLE IF EXISTS `user5`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user5` (
  `name` varchar(5) NOT NULL,
  `age` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user5`
--

LOCK TABLES `user5` WRITE;
/*!40000 ALTER TABLE `user5` DISABLE KEYS */;
/*!40000 ALTER TABLE `user5` ENABLE KEYS */;
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
  `DEFID` varchar(100),
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
-- Table structure for table `workspace`
--

DROP TABLE IF EXISTS `workspace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workspace` (
  `id` int(10) NOT NULL,
  `name` varchar(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workspace`
--

LOCK TABLES `workspace` WRITE;
/*!40000 ALTER TABLE `workspace` DISABLE KEYS */;
INSERT INTO `workspace` VALUES (40,'aaaa'),(41,'bbbb');
/*!40000 ALTER TABLE `workspace` ENABLE KEYS */;
UNLOCK TABLES;

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
/*!50001 VIEW `mm_categories_view` AS (select `node`.`ID` AS `ID`,`node`.`NAME` AS `NAME`,`node`.`LFT` AS `LFT`,`node`.`RGT` AS `RGT`,`node`.`PARENTID` AS `PARENTID`,(count(`parent`.`NAME`) - 1) AS `DEPTH`,((`node`.`RGT` - `node`.`LFT`) = 1) AS `IS_LEAF` from (`mm_categories` `node` join `mm_categories` `parent`) where (`node`.`LFT` between `parent`.`LFT` and `parent`.`RGT`) group by `node`.`ID`) */;
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
/*!50001 VIEW `worklistview` AS (select `wl`.`TASKID` AS `TASKID`,`wl`.`TITLE` AS `TITLE`,`wl`.`DESCRIPTION` AS `DESCRIPTION`,`wl`.`ENDPOINT` AS `ENDPOINT`,`wl`.`STATUS` AS `STATUS`,`wl`.`PRIORITY` AS `PRIORITY`,`wl`.`STARTDATE` AS `STARTDATE`,`wl`.`ENDDATE` AS `ENDDATE`,`wl`.`DUEDATE` AS `DUEDATE`,`wl`.`INSTID` AS `INSTID`,`wl`.`DEFID` AS `DEFID`,`wl`.`DEFNAME` AS `DEFNAME`,`wl`.`TRCTAG` AS `TRCTAG`,`wl`.`TOOL` AS `TOOL`,`wl`.`PARAMETER` AS `PARAMETER`,`wl`.`GROUPID` AS `GROUPID`,`wl`.`GROUPNAME` AS `GROUPNAME`,`wl`.`EXT1` AS `EXT1`,`wl`.`EXT2` AS `EXT2`,`wl`.`EXT3` AS `EXT3`,`wl`.`ISURGENT` AS `ISURGENT`,`wl`.`HASATTACHFILE` AS `HASATTACHFILE`,`wl`.`HASCOMMENT` AS `HASCOMMENT`,`wl`.`DOCUMENTCATEGORY` AS `DOCUMENTCATEGORY`,`wl`.`ISDELETED` AS `ISDELETED`,`wl`.`ROOTINSTID` AS `ROOTINSTID`,`wl`.`DISPATCHOPTION` AS `DISPATCHOPTION`,`wl`.`DISPATCHPARAM1` AS `DISPATCHPARAM1`,`wl`.`ROLENAME` AS `ROLENAME`,`wl`.`RESNAME` AS `RESNAME`,`wl`.`REFROLENAME` AS `REFROLENAME`,`wl`.`EXECSCOPE` AS `EXECSCOPE`,`wl`.`SAVEDATE` AS `SAVEDATE`,`wl`.`ABSTRACT` AS `ABSTRACT`,(select count(0) from `comments` `comm` where ((`comm`.`CONTENTID` = `wl`.`TASKID`) and (`comm`.`CONTENTTYPE` = 2) and (`comm`.`ISGOOD` = 1))) AS `SAIDGOODUSERSCOUNT`,(select `emp`.`EMPNAME` from `emptable` `emp` where (`emp`.`EMPCODE` = (select `comm`.`EMPCODE` from `comments` `comm` where (`comm`.`COMMENTID` = (select min(`comments`.`COMMENTID`) from `comments` where ((`comments`.`CONTENTID` = `wl`.`TASKID`) and (`comments`.`CONTENTTYPE` = 2) and (`comments`.`ISGOOD` = 1))))))) AS `SAIDGOODUSERSNAME` from `bpm_worklist` `wl`) */;
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

SET FOREIGN_KEY_CHECKS = 1;

-- Dump completed on 2012-05-21 12:46:20
