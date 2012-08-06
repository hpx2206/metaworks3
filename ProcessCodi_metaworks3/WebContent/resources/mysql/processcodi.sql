SET FOREIGN_KEY_CHECKS = 0;

 
SELECT @@FOREIGN_KEY_CHECKS;

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


CREATE TABLE `bpm_knol` (
	`id` VARCHAR(20) NOT NULL,
	`name` VARCHAR(500) NULL DEFAULT NULL,
	`linkedInstId` INT(11) NULL DEFAULT NULL,
	`parentId` VARCHAR(20) NOT NULL,
	`no` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `parentId` (`parentId`, `no`)
);

CREATE TABLE `processmap` (
	`mapId` VARCHAR(100) NOT NULL DEFAULT '',
	
	`defId` VARCHAR(50) NOT NULL DEFAULT '',
	`name` VARCHAR(50) NULL DEFAULT NULL,
	`iconPath` VARCHAR(255) NULL DEFAULT NULL,
	`color` VARCHAR(10) NULL DEFAULT NULL,
	`no` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`mapId`)
);

alter table bpm_knol add column authorid varchar(100);

alter table bpm_knol add column type char(10);


-- Dump completed on 2012-05-21 12:46:20


 alter table contact add column network char(10);
 
 	create table bpm_noti(
		notiId long,
		userId char(100),
		actorId char(100),
		instId int,
		actAbstract varchar(300),
		taskId int,
		type int,
		inputdate timestamp,
		confirm int
	);

	
	 -- 3000자 이상의 content 가 입력되면 전체 본문을 파일로 저장할 위치를 받아놓음
	 alter table bpm_worklist add column extfile varchar(200);
	 
	 
	 alter table emptable add column preferux char(10);
	 
	 
	 alter table emptable add column prefermob char(10);
	 
	 
	 alter table emptable add column mood varchar(100);
	 
	 
	 
	 alter table processmap add column cmphrase char(200);
	 alter table processmap add column cmtrgr varchar(20); 
	 
	 alter table bpm_procinst add column lastcmnt varchar(200);
	 
	 alter table bpm_worklist add prevver int(11);
	 alter table bpm_worklist add nextver int(11);
	 
	 
	 alter table contact add column mood varchar(100);

	 
	 alter table bpm_procinst add column assignee varchar(100);
	 
	 
	 
	 create table bpm_roledef(roledefid varchar(100), defId varchar(50), roleName char(20), mappedUserId varchar(50), comCode varchar(50));

	 drop table processmap;
	 
	 
	CREATE TABLE `processmap` (
		`mapId` VARCHAR(100) NOT NULL DEFAULT '',
		`defId` VARCHAR(50) NOT NULL DEFAULT '',
		`name` VARCHAR(50) NULL DEFAULT NULL,
		`iconPath` VARCHAR(255) NULL DEFAULT NULL,
		`color` VARCHAR(10) NULL DEFAULT NULL,
		`comcode` VARCHAR(10) NULL DEFAULT NULL,
		`no` INT(11) NULL DEFAULT NULL,
		
		cmphrase char(200),
	 	cmtrgr varchar(20),

		PRIMARY KEY (`mapId`)
	);
	 
	 alter table bpm_procinst add column initcmpl int(1);
	 
	 
	alter table comtable add column repmail varchar(100); -- 대표메일 주
	alter table comtable add column repMlHst varchar(100); -- 대표메일 호스
	alter table comtable add column repMlPwd varchar(100); -- 대표메일 패스워드 
