SET FOREIGN_KEY_CHECKS = 0;
SET SQL_SAFE_UPDATES=0;

 
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
INSERT INTO `emptable` VALUES 
('MasterSystemAdmin','Master System Admin User','test',-1,NULL,NULL,'MasterSystemPart','MasterSystemCompany','0','en',NULL,NULL,NULL,NULL,NULL,NULL),
('test','Tester','test',1,'Tester','test@uengine.org','','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL),
('test_kp','Tester_ko','test',1,'Tester','test_kp@uengine.org','','uEngine','0','ko',NULL,NULL,NULL,NULL,NULL,NULL),
('uEngineAdmin','uEngine Admin','test',1,'Tenant Admin',NULL,'uEngineTenantAdmin','uEngine','0','en',NULL,NULL,NULL,NULL,NULL,NULL);
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
INSERT INTO `roletable` VALUES ('MasterSystemAdmin','MasterSystemCompany','Master System Admin Role for SaaSable ProcessCodi','0');
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
INSERT INTO `roleusertable` VALUES ('MasterSystemAdmin','MasterSystemAdmin');
/*!40000 ALTER TABLE `roleusertable` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `bpm_knol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpm_knol` (
	`id` VARCHAR(20) NOT NULL,
	`name` VARCHAR(500) NULL DEFAULT NULL,
	`linkedInstId` INT(11) NULL DEFAULT NULL,
	`parentId` VARCHAR(20) NOT NULL,
	`no` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `parentId` (`parentId`, `no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `processmap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processmap` (
	`mapId` VARCHAR(100) NOT NULL DEFAULT '',
	
	`defId` VARCHAR(50) NOT NULL DEFAULT '',
	`name` VARCHAR(50) NULL DEFAULT NULL,
	`iconPath` VARCHAR(255) NULL DEFAULT NULL,
	`color` VARCHAR(10) NULL DEFAULT NULL,
	`no` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`mapId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table bpm_knol add column authorid varchar(100);

alter table bpm_knol add column type char(10);


-- Dump completed on 2012-05-21 12:46:20


 alter table contact add column network char(10);
 
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
	 
	 
	 DROP TABLE IF EXISTS `bpm_roledef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
	 create table bpm_roledef(
	 	roledefid varchar(100),
	 	defId varchar(50),
	 	roleName char(20),
	 	mappedUserId varchar(50),
	 	comCode varchar(50)
	 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	 
	 alter table bpm_procinst add column initcmpl int(1);
	 
	 
	alter table comtable add column repmail varchar(100); -- 대표메일 주
	alter table comtable add column repMlHst varchar(100); -- 대표메일 호스
	alter table comtable add column repMlPwd varchar(100); -- 대표메일 패스워드 

	
alter table emptable modify column empcode varchar(100);
alter table contact modify column friendId varchar(100);

alter table emptable add column APPKEY varchar(100);

alter table bpm_knol add column vistype char(10);

alter table bpm_knol add column CONNTYPE char(20);
alter table bpm_knol add column URL varchar(200);
alter table bpm_knol add column THUMBNAIL varchar(200);

alter table bpm_knol modify name VARCHAR(1000) ;

alter table contact modify column userId varchar(100);

alter table bpm_procinst add column topicId char(20);

alter table bpm_knol add column SECUOPT CHAR(1) DEFAULT '0';
alter table bpm_knol add column companyId varchar(20);

DROP TABLE IF EXISTS `BPM_TOPICMAPPING`;

CREATE TABLE BPM_TOPICMAPPING (
  TOPICMAPPINGID INT(11) NOT NULL,
  TOPICID VARCHAR(20) NOT NULL,
  USERID VARCHAR(255) DEFAULT NULL,
	USERNAME varchar(1000) DEFAULT NULL,
  PRIMARY KEY (TOPICMAPPINGID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table processmap modify column comcode varchar(20);
alter table bpm_procinst add column progress varchar(10);

alter TABLE `bpm_knol` add (
	`refId` VARCHAR(20) NULL DEFAULT NULL,
	`budget` INT(11) NULL DEFAULT NULL,
	`effort` INT(11) NULL DEFAULT NULL,
	`benfit` INT(11) NULL DEFAULT NULL,
	`penalty` INT(11) NULL DEFAULT NULL,
	`startdate` DATE NULL DEFAULT NULL,
	`enddate` DATE NULL DEFAULT NULL
);

alter table bpm_worklist add column EXT4 varchar(200);
alter table bpm_worklist add column EXT5 varchar(200);
alter table bpm_worklist add column EXT6 varchar(200);
alter table bpm_worklist add column EXT7 varchar(200);
alter table bpm_worklist add column EXT8 varchar(200);
alter table bpm_worklist add column EXT9 varchar(200);
alter table bpm_worklist add column EXT10 varchar(200);

alter table BPM_TOPICMAPPING add column `ASSIGNTYPE` int(11) DEFAULT 0 ;

alter table bpm_worklist add column prtTskId int;

alter table bpm_worklist add column GRPTASKID int(11);
alter table bpm_worklist add column MAJORVER int(5);
alter table bpm_worklist add column MINORVER int(5);

alter table bpm_worklist alter column status set default '';
update bpm_worklist set status = '' where status is null;
alter table bpm_worklist alter column isdeleted set default 0;
update bpm_worklist set isdeleted = 0 where isdeleted is null;

alter table roleusertable modify column EMPCODE varchar(100);

-- 2013.01.31
alter table bpm_procinst add beforeCmnt varchar(200);

-- 2013.02.01
alter table bpm_procinst drop beforeCmnt;
alter table bpm_procinst add lastcmnt2 varchar(200);

-- 2013.02.04
alter table bpm_procinst add BVBENEFIT int(6);
alter table bpm_procinst add BVPENALTY int(6);
alter table bpm_procinst add EFFORT int(6);

DROP TABLE IF EXISTS `INST_EMP_PERF`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
create table INST_EMP_PERF (
	INSTID int(11) NOT NULL,
	EMPCODE varchar(20) NOT NULL,
	BUSINESSVALUE int(10),
	PRIMARY KEY (INSTID, EMPCODE)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 2013.02.14
alter table bpm_procinst add lastCmntEp varchar(100);
alter table bpm_procinst add lastCmntRsnm varchar(100);
alter table bpm_procinst add lastCmnt2Ep varchar(100);
alter table bpm_procinst add lastCmnt2Rsnm varchar(100);

alter table bpm_worklist modify column title varchar(3000);

alter table emptable add approved int(1);

update emptable set approved = 1;


-- 2013.3.5 codi user information 서버 적용시 comcode 확인해서 insert해주세요. empcode는 processCodi.'COMCODE' 입니다. 아래를 예시로
insert into emptable (empcode, empname, globalcom, locale, approved) values('processCodi.uEngine', 'CODI', 'uEngine', 'ko', 1);

alter table INST_EMP_PERF modify column EMPCODE varchar(100);

DROP TABLE IF EXISTS `APP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
-- 2013.03.12
create table APP(
	APPID int(11) NOT NULL,
	APPNAME varchar(100),
	SIMPLEOVERVIEW varchar(200),
	FULLOVERVIEW varchar(1500),
	PRICING varchar(200),
	CREATEDATE datetime DEFAULT NULL,
	UPDATEDATE datetime DEFAULT NULL,
	VERSION varchar(5),
	EXTFILENAME varchar(200),
	FILECONTENT varchar(1000),
	LOGOFILENAME varchar(200),
	LOGOCONTENT varchar(1000),
	STATUS varchar(100),	-- request, approval/reject, published/unpublished
	VENDORID varchar(20),
	CATEGORYID int(11),
	INSTALLCNT int(11) DEFAULT 0,
	ISDELETED int(11) DEFAULT false,
	PRIMARY KEY(APPID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 2013.03.19
DROP TABLE IF EXISTS `APPMAPPING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
create table APPMAPPING(
	APPID int(11) NOT NULL,
	COMCODE  varchar(20) NOT NULL,
	APPNAME varchar(100),
	ISDELETED int(11) DEFAULT false,
	PRIMARY KEY(APPID, COMCODE),
	FOREIGN KEY (APPID) REFERENCES APP (APPID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 2013.03.27
alter table app modify simpleoverview varchar(1000);
alter table app modify fulloverview varchar(5000);

alter table app add url varchar(200);

-- 2013.04.03 컬럼명 수정
alter table bpm_knol change benfit BENEFIT int(11);

-- 2013.05.06 app - project mapping
alter table app add projectId varchar(20);

-- 2013.05.07 project description 추가
alter table bpm_knol add description varchar(1000);

-- 2013.05.13 app테이블 컬럼명 수정(vendorid -> comcode) 속성동일 varchar(20)
alter table app change vendorid comcode varchar(20);

-- 2013.05.14 paasManager user 추가
INSERT INTO `comtable` (`COMCODE`, `COMNAME`) VALUES ('CloudManager', 'cloud manager');
INSERT INTO `emptable` (`empcode`, `EMPNAME`, `PASSWORD`, `ISADMIN`, `EMAIL`,`GLOBALCOM`, `ISDELETED`, `LOCALE`, `approved`) VALUES ('paasManager', 'paasManager', 'admin', '1', 'paasManager@system.com', 'CloudManager', '0', 'ko', '1');
INSERT INTO `roletable` (`ROLECODE`, `COMCODE`, `DESCR`, `ISDELETED`) VALUES ('paasManager', 'CloudManager', 'paasManager', '0');
INSERT INTO `roleusertable` (`ROLECODE`, `EMPCODE`) VALUES ('paasManager', 'paasManager');


-- 2013.05.14
DROP TABLE IF EXISTS `oauth_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE oauth_token (
	user_id VARCHAR(300) NOT NULL,
	access_token VARCHAR(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table app add comname varchar(200);


alter table emptable add guest int(1);
ALTER TABLE `bpm_procinst`  CHANGE COLUMN `BVBENEFIT` `BENEFIT` INT(6) NULL DEFAULT NULL AFTER `lastcmnt2`,  CHANGE COLUMN `BVPENALTY` `PENALTY` INT(6) NULL DEFAULT NULL AFTER `BENEFIT`;

update emptable set guest = 0;

alter table emptable add notiEmail int(1) default 0;

-- recentItem 테이블 설계: 주제, 친구 등 유저가 가장 최신에 클릭한 순으로 정렬하기 위해 만든 타임테이블
-- empcode, type ={topic, friend, ...}, item ={topicId, empcode, ...}, updateDate
DROP TABLE IF EXISTS `recentItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE recentItem(
	empcode VARCHAR(100) NOT NULL,
	type  VARCHAR(10) NOT NULL,
	item VARCHAR(100) NOT NULL,
	updateDate datetime DEFAULT NULL,
	PRIMARY KEY(empcode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- notiEmail 컬럼명 수정
alter table emptable drop notiEmail;
alter table emptable add isMailNoti int(1) default 0;

DROP TABLE IF EXISTS `recentItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE recentItem(
	empcode VARCHAR(100) NOT NULL,
	itemType  VARCHAR(10) NOT NULL,
	itemId VARCHAR(100) NOT NULL,
	updateDate datetime DEFAULT NULL,
	PRIMARY KEY(empcode,itemType,itemId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- emptable에 inviteUser(초대한 사람) 추가
ALTER TABLE `emptable`	ADD COLUMN `inviteUser` VARCHAR(100) NULL DEFAULT NULL;

alter table emptable change isMailNoti mailNoti int(1) default 1;

-- 2013-08-19 인스턴스 목록 성능 개선
ALTER TABLE `bpm_rolemapping` ADD INDEX `RootInstId` (`ROOTINSTID`);
ALTER TABLE `bpm_topicmapping` ADD INDEX `TopicId` (`TOPICID`);

-- 2013-09-12 jinwon
-- crowdsourcing
ALTER TABLE `bpm_procinst`  ADD COLUMN `crowdSourcing` INT(1) NULL DEFAULT '0';
ALTER TABLE `bpm_procinst`  ADD COLUMN `csFacebook` VARCHAR(50) NULL;

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
INSERT INTO `category` VALUES 
	(1,'전체',-1,NULL,0),
	(2,'인사/급여',-1,NULL,0),
	(3,'영업 관리',-1,NULL,0),
	(4,'생산 관리',-1,NULL,0),
	(5,'설비 관리',-1,NULL,0),
	(6,'구매 관리',-1,NULL,0);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;
update emptable set guest = 0;


-- 2013.08.07
create table favoritefile (
 userId varchar(200) not null,
 username varchar(200),
 fileId varchar(100) ,
 fileName varchar(100) ,
 filePath varchar(100) ,
 MODDATE datetime
);

-- 2013.09.06s - 폴더ID, NAME추가 
alter table bpm_worklist add column folderId varchar(100);
alter table bpm_worklist add column folderName varchar(100);

alter table recentitem add column clickedCount varchar(4);

alter table bpm_knol add column ext varchar(3000);

alter table appmapping add column url varchar(50);

alter table bpm_knol add column isreleased boolean;
alter table bpm_knol add column isdistributed boolean;

-- 2013-10-21 기존 테이블 지우고 새로 만들어 주세요 (  나중에 문구 삭제 )
DROP TABLE IF EXISTS `cloudinfo`;
create table cloudinfo(
    id int(20) not null,
    projectId varchar(20),
    serverName varchar(100),
    serverInfo varchar(20),
    serverId varchar(100),
    serverIp varchar(100),
    serverIpId varchar(100),
    rootId varchar(20),
    rootPwd varchar(20),
    osTemplete varchar(100),
    hwTemplete varchar(100),
    serviceTemplete varchar(100),
    serverGroup varchar(50),
    moddate DATETIME,
    primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table appmapping modify url varchar(200);

alter table cloudinfo add column status varchar(20);

-- 2013-10-22 기존 테이블 지우고 새로 만들어 주세요 (  나중에 문구 삭제 )
DROP TABLE IF EXISTS `filepathinfo`;

create table filepathinfo(
    id int NOT NULL,
	projectId varchar(20) not null,
    reflectVer int,
    releaseVer int,
    warPath varchar(100),
    sqlPath varchar(100),
    fileType varchar(20),
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


alter table bpm_knol add column projectalias varchar(1000);

alter table app add column runningVersion int;

alter table app add column subDomain varchar(50);

alter table filepathinfo add column comment varchar(1000);

alter table filepathinfo add column moddate DATETIME;

alter table filepathinfo add column distributor varchar(20);


-- 2013.11.06 문서관리 , content(genericWorkItem)
alter table bpm_procinst add column isdocument int(11) default 0;

alter table bpm_worklist change content content mediumtext;

-- 2013.10.29
alter table comtable add column alias varchar(30);
alter table emptable add column authkey varchar(100);

-- 2013.11.07
create table logtable(
id int(11) not null primary key,
type varchar(20) not null,
empcode varchar(100) not null,
comcode varchar(100),
ip varchar(50),
date varchar(50));

-- 2013.11.08 코디 시스템 관리자 계정.( Email을 인스턴스 발행하는 계정.)
-- insert into emptable values("0", "CODI", "test", "1", null, null, null, "0", "0", "ko", null, null, null, null, null, null, "wave", "auto", null, null, "1", "0", null, "0", "0", null);
insert into emptable (empcode, empname, password, isadmin, globalcom, isdeleted, locale, preferux, prefermob, approved, guest, mailnoti) 
			   values("0", "CODI", "test", 1, "uengine.org", "0", "ko", "wave", "auto", 1, 0, 0);


-- 2013.11.11 dept = input image
alter table PARTTABLE add column url varchar(200);
alter table PARTTABLE add column thumbnail varchar(200);

-- 2013.11.13 노티 관련 테이블
create table notisetting(
    id int NOT NULL,
	userId varchar(20) not null,
	checkLogin int default 1,
    notiAdvice int default 1,
	modiUser int default 1,
	modiTopic int default 1,
	modiOrgan int default 1,
	writeBookmark int default 1,
	writeTopic int default 1,
	writeOrgan int default 1,
	writeInstance int default 1,
	inviteTopic int default 1,
	inviteOrgan int default 1,
	addFriend int default 1,
	beforehandNoti int default 1,
	notiTime int default 1,
	defaultNotiTime varchar(20),
	notiEmail int default 1,
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 11.14 역할과 롤을 연결하기 위한 컬럼 추가
alter table bpm_roledef add column mappedRoleCode varchar(100);
alter table bpm_roledef add column roleDefType varchar(10);


-- 2013.11.14 role 사진 추가
alter table roletable add column URL varchar(200);
alter table roletable add column THUMBNAIL varchar(200);

-- 2013.11.15 인스턴스 파일첨부 표시, 노티리스트 등록 된 순으로
alter table bpm_procinst add column isfileadded int(11) default 0;

-- 2013.11.18 노티 정렬때문에 timestamp -> date 변경
DROP TABLE IF EXISTS `bpm_noti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
create table bpm_noti(
	notiId long,
	userId char(100),
	actorId char(100),
	instId int,
	actAbstract varchar(300),
	taskId int,
	type int,
	inputdate datetime,
	confirm int
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


alter table filepathinfo add column warFileName varchar(30);
alter table filepathinfo add column sqlFileName varchar(30);

-- 2013.12.03 회사 로고 경로 컬럼 추가
alter table comtable add column logoPath varchar(100);

-- 2013.12.18
DROP TABLE IF EXISTS `schedule_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
create table schedule_table(
	SCHE_IDX int(11),
	INSTID int(11),
	TRCTAG varchar(100),
	STARTDATE datetime
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS ServerInfo;
create table ServerInfo(
 vmName     varchar(100),
 iaasServerType varchar(100),
 jobId  varchar(200),
 tempId varchar(200),
 command    varchar(100),
 vmIp       varchar(100),
 vmOutsideIp    varchar(100),
 vmId   varchar(200),
 vmPassword varchar(100),
 status varchar(100),
primary key(vmName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;