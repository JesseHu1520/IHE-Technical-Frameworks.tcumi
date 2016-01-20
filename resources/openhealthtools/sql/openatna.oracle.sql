--------------------------------------------------------
--  File created - 星期五-二月-07-2014   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence HIBERNATE_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "HIBERNATE_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 91 CACHE 10 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table CODES
--------------------------------------------------------

  CREATE TABLE "CODES" 
   (	"CODETYPE" VARCHAR2(32), 
	"ID" NUMBER(20,0), 
	"CODE" VARCHAR2(256), 
	"ORIGINALTEXT" VARCHAR2(256), 
	"CODESYSTEM" VARCHAR2(256), 
	"CODESYSTEMNAME" VARCHAR2(256), 
	"TYPE" NUMBER(12,0), 
	"DISPLAYNAME" VARCHAR2(256), 
	"VERSION" NUMBER(12,0)
   ) ;
--------------------------------------------------------
--  DDL for Table DESCRIPTIONS_SOP_CLASSES
--------------------------------------------------------

  CREATE TABLE "DESCRIPTIONS_SOP_CLASSES" 
   (	"OBJECT_DESCRIPTIONS_ID" NUMBER(19,0), 
	"SOPCLASSES_ID" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table DETAIL_TYPES
--------------------------------------------------------

  CREATE TABLE "DETAIL_TYPES" 
   (	"ID" NUMBER(20,0), 
	"TYPE" VARCHAR2(256), 
	"VERSION" NUMBER(12,0)
   ) ;
--------------------------------------------------------
--  DDL for Table ERRORS
--------------------------------------------------------

  CREATE TABLE "ERRORS" 
   (	"ID" NUMBER(19,0), 
	"ERRORMESSAGE" VARCHAR2(255 CHAR), 
	"ERRORTIMESTAMP" TIMESTAMP (6), 
	"PAYLOAD" BLOB, 
	"SOURCEIP" VARCHAR2(255 CHAR), 
	"STACKTRACE" BLOB, 
	"VERSION" NUMBER(10,0)
   ) ;
--------------------------------------------------------
--  DDL for Table EVENT_TYPES_CODES
--------------------------------------------------------

  CREATE TABLE "EVENT_TYPES_CODES" 
   (	"MESSAGES_ID" NUMBER(19,0), 
	"EVENTTYPECODES_ID" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table EVENT_TYPES_TO_CODES
--------------------------------------------------------

  CREATE TABLE "EVENT_TYPES_TO_CODES" 
   (	"EVENT_TYPE" NUMBER(20,0), 
	"CODE" NUMBER(20,0)
   ) ;
--------------------------------------------------------
--  DDL for Table MESSAGES
--------------------------------------------------------

  CREATE TABLE "MESSAGES" 
   (	"ID" NUMBER(20,0), 
	"EVENTACTIONCODE" VARCHAR2(256), 
	"EVENTDATETIME" TIMESTAMP (6), 
	"EVENTOUTCOME" NUMBER(12,0), 
	"EVENTID_ID" NUMBER(20,0), 
	"SOURCEADDRESS" VARCHAR2(255 CHAR)
   ) ;
--------------------------------------------------------
--  DDL for Table MESSAGES_MESSAGE_OBJECTS
--------------------------------------------------------

  CREATE TABLE "MESSAGES_MESSAGE_OBJECTS" 
   (	"MESSAGES_ID" NUMBER(20,0), 
	"MESSAGEOBJECTS_ID" NUMBER(20,0)
   ) ;
--------------------------------------------------------
--  DDL for Table MESSAGES_MESSAGE_PARTICIPANTS
--------------------------------------------------------

  CREATE TABLE "MESSAGES_MESSAGE_PARTICIPANTS" 
   (	"MESSAGES_ID" NUMBER(20,0), 
	"MESSAGEPARTICIPANTS_ID" NUMBER(20,0)
   ) ;
--------------------------------------------------------
--  DDL for Table MESSAGES_MESSAGE_SOURCES
--------------------------------------------------------

  CREATE TABLE "MESSAGES_MESSAGE_SOURCES" 
   (	"MESSAGES_ID" NUMBER(20,0), 
	"MESSAGESOURCES_ID" NUMBER(20,0)
   ) ;
--------------------------------------------------------
--  DDL for Table MESSAGES_MOBJECTS
--------------------------------------------------------

  CREATE TABLE "MESSAGES_MOBJECTS" 
   (	"MESSAGES_ID" NUMBER(19,0), 
	"MESSAGEOBJECTS_ID" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table MESSAGES_MPARTICIPANTS
--------------------------------------------------------

  CREATE TABLE "MESSAGES_MPARTICIPANTS" 
   (	"MESSAGES_ID" NUMBER(19,0), 
	"MESSAGEPARTICIPANTS_ID" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table MESSAGES_MSOURCES
--------------------------------------------------------

  CREATE TABLE "MESSAGES_MSOURCES" 
   (	"MESSAGES_ID" NUMBER(19,0), 
	"MESSAGESOURCES_ID" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table MESSAGE_OBJECTS
--------------------------------------------------------

  CREATE TABLE "MESSAGE_OBJECTS" 
   (	"ID" NUMBER(20,0), 
	"OBJECTQUERY" BLOB, 
	"OBJECTDATALIFECYCLE" NUMBER(8,0), 
	"OBJECT_ID" NUMBER(20,0)
   ) ;
--------------------------------------------------------
--  DDL for Table MESSAGE_OBJECTS_OBJECT_DETAILS
--------------------------------------------------------

  CREATE TABLE "MESSAGE_OBJECTS_OBJECT_DETAILS" 
   (	"MESSAGE_OBJECTS_ID" NUMBER(20,0), 
	"DETAILS_ID" NUMBER(20,0)
   ) ;
--------------------------------------------------------
--  DDL for Table MESSAGE_PARTICIPANTS
--------------------------------------------------------

  CREATE TABLE "MESSAGE_PARTICIPANTS" 
   (	"ID" NUMBER(20,0), 
	"USERISREQUESTOR" NUMBER(1,0), 
	"PARTICIPANT_ID" NUMBER(20,0), 
	"NETWORKACCESSPOINT_ID" NUMBER(20,0)
   ) ;
--------------------------------------------------------
--  DDL for Table MESSAGE_SOURCES
--------------------------------------------------------

  CREATE TABLE "MESSAGE_SOURCES" 
   (	"ID" NUMBER(20,0), 
	"SOURCE_ID" NUMBER(20,0)
   ) ;
--------------------------------------------------------
--  DDL for Table MOBJECTS_DETAILS
--------------------------------------------------------

  CREATE TABLE "MOBJECTS_DETAILS" 
   (	"MESSAGE_OBJECTS_ID" NUMBER(19,0), 
	"DETAILS_ID" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table NETWORK_ACCESS_POINTS
--------------------------------------------------------

  CREATE TABLE "NETWORK_ACCESS_POINTS" 
   (	"ID" NUMBER(20,0), 
	"IDENTIFIER" VARCHAR2(256), 
	"TYPE" NUMBER(8,0), 
	"VERSION" NUMBER(12,0)
   ) ;
--------------------------------------------------------
--  DDL for Table OBJECTS
--------------------------------------------------------

  CREATE TABLE "OBJECTS" 
   (	"ID" NUMBER(20,0), 
	"OBJECTID" VARCHAR2(256), 
	"OBJECTNAME" VARCHAR2(256), 
	"OBJECTTYPECODE" NUMBER(8,0), 
	"OBJECTTYPECODEROLE" NUMBER(8,0), 
	"OBJECTSENSITIVITY" VARCHAR2(256), 
	"VERSION" NUMBER(12,0), 
	"OBJECTIDTYPECODE_ID" NUMBER(20,0)
   ) ;
--------------------------------------------------------
--  DDL for Table OBJECTS_DESCRIPTIONS
--------------------------------------------------------

  CREATE TABLE "OBJECTS_DESCRIPTIONS" 
   (	"OBJECTS_ID" NUMBER(19,0), 
	"OBJECTDESCRIPTIONS_ID" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table OBJECTS_DETAIL_TYPES
--------------------------------------------------------

  CREATE TABLE "OBJECTS_DETAIL_TYPES" 
   (	"OBJECTS_ID" NUMBER(20,0), 
	"OBJECTDETAILTYPES_ID" NUMBER(20,0)
   ) ;
--------------------------------------------------------
--  DDL for Table OBJECT_DESCRIPTIONS
--------------------------------------------------------

  CREATE TABLE "OBJECT_DESCRIPTIONS" 
   (	"ID" NUMBER(19,0), 
	"ACCESSIONNUMBERS" VARCHAR2(255 CHAR), 
	"MPPSUIDS" VARCHAR2(255 CHAR), 
	"VERSION" NUMBER(10,0)
   ) ;
--------------------------------------------------------
--  DDL for Table OBJECT_DETAILS
--------------------------------------------------------

  CREATE TABLE "OBJECT_DETAILS" 
   (	"ID" NUMBER(20,0), 
	"VALUE" BLOB, 
	"TYPE" VARCHAR2(256)
   ) ;
--------------------------------------------------------
--  DDL for Table PARTICIPANTS
--------------------------------------------------------

  CREATE TABLE "PARTICIPANTS" 
   (	"ID" NUMBER(20,0), 
	"USERID" VARCHAR2(256), 
	"ALTERNATIVEUSERID" VARCHAR2(256), 
	"VERSION" NUMBER(12,0), 
	"USERNAME" VARCHAR2(256)
   ) ;
--------------------------------------------------------
--  DDL for Table PARTICIPANTS_CODES
--------------------------------------------------------

  CREATE TABLE "PARTICIPANTS_CODES" 
   (	"PARTICIPANTS_ID" NUMBER(19,0), 
	"PARTICIPANTTYPECODES_ID" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table PARTICIPANTS_TO_CODES
--------------------------------------------------------

  CREATE TABLE "PARTICIPANTS_TO_CODES" 
   (	"PARTICIPANT" NUMBER(20,0), 
	"CODE" NUMBER(20,0)
   ) ;
--------------------------------------------------------
--  DDL for Table PROVISIONAL_MESSAGES
--------------------------------------------------------

  CREATE TABLE "PROVISIONAL_MESSAGES" 
   (	"ID" NUMBER(19,0), 
	"CONTENT" BLOB, 
	"VERSION" NUMBER(10,0)
   ) ;
--------------------------------------------------------
--  DDL for Table SOP_CLASSES
--------------------------------------------------------

  CREATE TABLE "SOP_CLASSES" 
   (	"ID" NUMBER(19,0), 
	"INSTANCEUIDS" VARCHAR2(255 CHAR), 
	"NUMBEROFINSTANCES" NUMBER(10,0), 
	"SOPID" VARCHAR2(255 CHAR), 
	"VERSION" NUMBER(10,0)
   ) ;
--------------------------------------------------------
--  DDL for Table SOURCES
--------------------------------------------------------

  CREATE TABLE "SOURCES" 
   (	"ID" NUMBER(20,0), 
	"SOURCEID" VARCHAR2(256), 
	"ENTERPRISESITEID" VARCHAR2(256), 
	"VERSION" NUMBER(12,0)
   ) ;
--------------------------------------------------------
--  DDL for Table SOURCES_CODES
--------------------------------------------------------

  CREATE TABLE "SOURCES_CODES" 
   (	"SOURCES_ID" NUMBER(19,0), 
	"SOURCETYPECODES_ID" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table SOURCES_TO_CODES
--------------------------------------------------------

  CREATE TABLE "SOURCES_TO_CODES" 
   (	"SOURCE" NUMBER(20,0), 
	"CODE" NUMBER(20,0)
   ) ;

---------------------------------------------------
--   DATA FOR TABLE CODES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into CODES
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('AUDIT_SOURCE',1,'4',null,null,'RFC-3881',1,'Application Server Process',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('AUDIT_SOURCE',2,'2',null,null,'RFC-3881',1,'Data Acquisition Device',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('AUDIT_SOURCE',3,'5',null,null,'RFC-3881',1,'Database Server Process',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('AUDIT_SOURCE',4,'3',null,null,'RFC-3881',1,'Web Server Process',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('AUDIT_SOURCE',5,'8',null,null,'RFC-3881',1,'Operating Software',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('AUDIT_SOURCE',6,'7',null,null,'RFC-3881',1,'Network Component',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('AUDIT_SOURCE',7,'1',null,null,'RFC-3881',1,'End User Display Device',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('AUDIT_SOURCE',8,'6',null,null,'RFC-3881',1,'Security Server',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('AUDIT_SOURCE',9,'9',null,null,'RFC-3881',1,'External Source',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',10,'ITI-21',null,null,'IHE Transactions',4,'Patient Demographics Query',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',11,'11',null,null,'RFC-3881',4,'User Identifier',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',12,'8',null,null,'RFC-3881',4,'Report Name',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',13,'10',null,null,'RFC-3881',4,'Search Criteria',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',14,'9',null,null,'RFC-3881',4,'Report Nunber',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',15,'110181',null,null,'DCM',4,'SOP Class UID',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',16,'12',null,null,'RFC-3881',4,'URI',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',17,'6',null,null,'RFC-3881',4,'Account Number',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',18,'110182',null,null,'DCM',4,'NodeID',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',19,'3',null,null,'RFC-3881',4,'Encounter Number',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',20,'110180',null,null,'DCM',4,'Study Instance UID',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',21,'2',null,null,'RFC-3881',4,'Patient Number',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',22,'5',null,null,'RFC-3881',4,'Social Security Number',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',23,'urn:uuid:a54d6aa5-d40d-43f9-88c5-b4633d873bdd',null,null,'IHE XDS Metadata',4,'SubmissionSet ClassificationNode',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',24,'1',null,null,'RFC-3881',4,'Medical Record Number',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',25,'4',null,null,'RFC-3881',4,'Enrollee Number',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('PARTICIPANT_OBJECT_ID_TYPE',26,'7',null,null,'RFC-3881',4,'Guarantor Number',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('ACTIVE_PARTICIPANT',27,'110155',null,null,'DCM',0,'Source Media',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('ACTIVE_PARTICIPANT',28,'110154',null,null,'DCM',0,'Destination Media',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('ACTIVE_PARTICIPANT',29,'110152',null,null,'DCM',0,'Destination',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('ACTIVE_PARTICIPANT',30,'110151',null,null,'DCM',0,'Application Launcher',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('ACTIVE_PARTICIPANT',31,'110150',null,null,'DCM',0,'Application',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('ACTIVE_PARTICIPANT',32,'110153',null,null,'DCM',0,'Source',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',33,'110109',null,null,'DCM',2,'Order Record',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',34,'110103',null,null,'DCM',2,'DICOM Instances Accessed',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',35,'110113',null,null,'DCM',2,'Security Alert',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',36,'110106',null,null,'DCM',2,'Export',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',37,'110108',null,null,'DCM',2,'Network Entry',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',38,'110111',null,null,'DCM',2,'Proceedure Record',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',39,'IHE0005',null,null,'IHE',2,'Patient Care Protocol',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',40,'IHE0002',null,null,'IHE',2,'Medication Event',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',41,'110107',null,null,'DCM',2,'Import',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',42,'110112',null,null,'DCM',2,'Query',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',43,'110102',null,null,'DCM',2,'Being Transfering DICOM Instances',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',44,'110100',null,null,'DCM',2,'Application Activity',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',45,'110114',null,null,'DCM',2,'User Authentication',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',46,'IHE0003',null,null,'IHE',2,'Patient Care Resource Assignment',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',47,'IHE0001',null,null,'IHE',2,'Health Services Provision Event',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',48,'110110',null,null,'DCM',2,'Patient Record',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',49,'110101',null,null,'DCM',2,'Audit Log Used',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',50,'IHE0004',null,null,'IHE',2,'Patient Care Episode',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',51,'110105',null,null,'DCM',2,'DICOM Study Deleted',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_ID',52,'110104',null,null,'DCM',2,'DICOM Instances Transferred',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',53,'110131',null,null,'DCM',3,'Software Configuration',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',54,'110123',null,null,'DCM',3,'Logout',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',55,'ITI-48',null,null,'IHE Transactions',3,'Retrieve Value Set',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',56,'ITI-39',null,null,'IHE Transactions',3,'Cross Gateway Retrieve',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',57,'ITI-21',null,null,'IHE Transactions',3,'Patient Demographics Query',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',58,'ITI-41',null,null,'IHE Transactions',3,'Provide and Register Document Set-b',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',59,'ITI-8',null,null,'IHE Transactions',3,'Patient Identity Feed',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',60,'110122',null,null,'DCM',3,'Login',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',61,'ITI-17',null,null,'IHE Transactions',3,'Retrieve Document',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',62,'110133',null,null,'DCM',3,'Audit Recording Stopped',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',63,'110132',null,null,'DCM',3,'Use of Restritcted Function',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',64,'ITI-30',null,null,'IHE Transactions',3,'Patient Identity Management',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',65,'ITI-18',null,null,'IHE Transactions',3,'Registry Stored Query',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',66,'ITI-22',null,null,'IHE Transactions',3,'Patient Demographics and Visit Query',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',67,'110125',null,null,'DCM',3,'Detach',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',68,'110129',null,null,'DCM',3,'Security Configuration',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',69,'ITI-43',null,null,'IHE Transactions',3,'Retrieve Document Set',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',70,'110120',null,null,'DCM',3,'Application Start',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',71,'110124',null,null,'DCM',3,'Attach',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',72,'110136',null,null,'DCM',3,'Security Roles Changed',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',73,'110137',null,null,'DCM',3,'User Security Attributes Changed',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',74,'ITI-42',null,null,'IHE Transactions',3,'Register Document Set-b',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',75,'110126',null,null,'DCM',3,'Node Authentication',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',76,'ITI-10',null,null,'IHE Transactions',3,'PIX Update Notification',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',77,'110121',null,null,'DCM',3,'Application Stop',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',78,'ITI-38',null,null,'IHE Transactions',3,'Cross Gateway Query',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',79,'ITI-15',null,null,'IHE Transactions',3,'Provide and Register Document Set',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',80,'ITI-32',null,null,'IHE Transactions',3,'Distribute Document Set on Media',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',81,'110130',null,null,'DCM',3,'Hardware Configuration',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',82,'ITI-16',null,null,'IHE Transactions',3,'Registry SQL Query',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',83,'ITI-14',null,null,'IHE Transactions',3,'Register Document Set',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',84,'110128',null,null,'DCM',3,'Network Configuration',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',85,'110135',null,null,'DCM',3,'Object Security Attributes Changed',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',86,'110134',null,null,'DCM',3,'Audit Recording Started',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',87,'110127',null,null,'DCM',3,'Emergency Override',0);
Insert into CODES (CODETYPE,ID,CODE,ORIGINALTEXT,CODESYSTEM,CODESYSTEMNAME,TYPE,DISPLAYNAME,VERSION) values ('EVENT_TYPE',88,'ITI-51',null,null,'IHE Transactions',3,'Multi-Patient Query',0);

---------------------------------------------------
--   END DATA FOR TABLE CODES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE DESCRIPTIONS_SOP_CLASSES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into DESCRIPTIONS_SOP_CLASSES

---------------------------------------------------
--   END DATA FOR TABLE DESCRIPTIONS_SOP_CLASSES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE DETAIL_TYPES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into DETAIL_TYPES

---------------------------------------------------
--   END DATA FOR TABLE DETAIL_TYPES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE ERRORS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into ERRORS

---------------------------------------------------
--   END DATA FOR TABLE ERRORS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE EVENT_TYPES_CODES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into EVENT_TYPES_CODES

---------------------------------------------------
--   END DATA FOR TABLE EVENT_TYPES_CODES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE EVENT_TYPES_TO_CODES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into EVENT_TYPES_TO_CODES

---------------------------------------------------
--   END DATA FOR TABLE EVENT_TYPES_TO_CODES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE MESSAGES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into MESSAGES

---------------------------------------------------
--   END DATA FOR TABLE MESSAGES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE MESSAGES_MESSAGE_OBJECTS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into MESSAGES_MESSAGE_OBJECTS

---------------------------------------------------
--   END DATA FOR TABLE MESSAGES_MESSAGE_OBJECTS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE MESSAGES_MESSAGE_PARTICIPANTS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into MESSAGES_MESSAGE_PARTICIPANTS

---------------------------------------------------
--   END DATA FOR TABLE MESSAGES_MESSAGE_PARTICIPANTS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE MESSAGES_MESSAGE_SOURCES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into MESSAGES_MESSAGE_SOURCES

---------------------------------------------------
--   END DATA FOR TABLE MESSAGES_MESSAGE_SOURCES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE MESSAGES_MOBJECTS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into MESSAGES_MOBJECTS

---------------------------------------------------
--   END DATA FOR TABLE MESSAGES_MOBJECTS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE MESSAGES_MPARTICIPANTS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into MESSAGES_MPARTICIPANTS

---------------------------------------------------
--   END DATA FOR TABLE MESSAGES_MPARTICIPANTS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE MESSAGES_MSOURCES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into MESSAGES_MSOURCES

---------------------------------------------------
--   END DATA FOR TABLE MESSAGES_MSOURCES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE MESSAGE_OBJECTS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into MESSAGE_OBJECTS

---------------------------------------------------
--   END DATA FOR TABLE MESSAGE_OBJECTS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE MESSAGE_OBJECTS_OBJECT_DETAILS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into MESSAGE_OBJECTS_OBJECT_DETAILS

---------------------------------------------------
--   END DATA FOR TABLE MESSAGE_OBJECTS_OBJECT_DETAILS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE MESSAGE_PARTICIPANTS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into MESSAGE_PARTICIPANTS

---------------------------------------------------
--   END DATA FOR TABLE MESSAGE_PARTICIPANTS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE MESSAGE_SOURCES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into MESSAGE_SOURCES

---------------------------------------------------
--   END DATA FOR TABLE MESSAGE_SOURCES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE MOBJECTS_DETAILS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into MOBJECTS_DETAILS

---------------------------------------------------
--   END DATA FOR TABLE MOBJECTS_DETAILS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE NETWORK_ACCESS_POINTS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into NETWORK_ACCESS_POINTS

---------------------------------------------------
--   END DATA FOR TABLE NETWORK_ACCESS_POINTS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE OBJECTS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into OBJECTS

---------------------------------------------------
--   END DATA FOR TABLE OBJECTS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE OBJECTS_DESCRIPTIONS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into OBJECTS_DESCRIPTIONS

---------------------------------------------------
--   END DATA FOR TABLE OBJECTS_DESCRIPTIONS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE OBJECTS_DETAIL_TYPES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into OBJECTS_DETAIL_TYPES

---------------------------------------------------
--   END DATA FOR TABLE OBJECTS_DETAIL_TYPES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE OBJECT_DESCRIPTIONS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into OBJECT_DESCRIPTIONS

---------------------------------------------------
--   END DATA FOR TABLE OBJECT_DESCRIPTIONS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE OBJECT_DETAILS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into OBJECT_DETAILS

---------------------------------------------------
--   END DATA FOR TABLE OBJECT_DETAILS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE PARTICIPANTS
--   FILTER = none used
---------------------------------------------------
REM INSERTING into PARTICIPANTS

---------------------------------------------------
--   END DATA FOR TABLE PARTICIPANTS
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE PARTICIPANTS_CODES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into PARTICIPANTS_CODES

---------------------------------------------------
--   END DATA FOR TABLE PARTICIPANTS_CODES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE PARTICIPANTS_TO_CODES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into PARTICIPANTS_TO_CODES

---------------------------------------------------
--   END DATA FOR TABLE PARTICIPANTS_TO_CODES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE PROVISIONAL_MESSAGES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into PROVISIONAL_MESSAGES

---------------------------------------------------
--   END DATA FOR TABLE PROVISIONAL_MESSAGES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE SOP_CLASSES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into SOP_CLASSES

---------------------------------------------------
--   END DATA FOR TABLE SOP_CLASSES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE SOURCES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into SOURCES

---------------------------------------------------
--   END DATA FOR TABLE SOURCES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE SOURCES_CODES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into SOURCES_CODES

---------------------------------------------------
--   END DATA FOR TABLE SOURCES_CODES
---------------------------------------------------


---------------------------------------------------
--   DATA FOR TABLE SOURCES_TO_CODES
--   FILTER = none used
---------------------------------------------------
REM INSERTING into SOURCES_TO_CODES

---------------------------------------------------
--   END DATA FOR TABLE SOURCES_TO_CODES
---------------------------------------------------

--------------------------------------------------------
--  Constraints for Table DETAIL_TYPES
--------------------------------------------------------

  ALTER TABLE "DETAIL_TYPES" ADD CONSTRAINT "DETAIL_TYPES_PKEY" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "DETAIL_TYPES" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SOURCES_CODES
--------------------------------------------------------

  ALTER TABLE "SOURCES_CODES" MODIFY ("SOURCES_ID" NOT NULL ENABLE);
 
  ALTER TABLE "SOURCES_CODES" MODIFY ("SOURCETYPECODES_ID" NOT NULL ENABLE);
 
  ALTER TABLE "SOURCES_CODES" ADD PRIMARY KEY ("SOURCES_ID", "SOURCETYPECODES_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table SOURCES_TO_CODES
--------------------------------------------------------

  ALTER TABLE "SOURCES_TO_CODES" ADD CONSTRAINT "SOURCES_TO_CODES_PKEY" PRIMARY KEY ("SOURCE", "CODE") ENABLE;
 
  ALTER TABLE "SOURCES_TO_CODES" MODIFY ("SOURCE" NOT NULL ENABLE);
 
  ALTER TABLE "SOURCES_TO_CODES" MODIFY ("CODE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MESSAGES_MESSAGE_OBJECTS
--------------------------------------------------------

  ALTER TABLE "MESSAGES_MESSAGE_OBJECTS" ADD CONSTRAINT "MESSAGES_MESSAGE_OBJECTS_ID" UNIQUE ("MESSAGEOBJECTS_ID") ENABLE;
 
  ALTER TABLE "MESSAGES_MESSAGE_OBJECTS" ADD CONSTRAINT "MESSAGES_MESSAGE_OBJECTS_PKEY" PRIMARY KEY ("MESSAGES_ID", "MESSAGEOBJECTS_ID") ENABLE;
 
  ALTER TABLE "MESSAGES_MESSAGE_OBJECTS" MODIFY ("MESSAGES_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MESSAGES_MESSAGE_OBJECTS" MODIFY ("MESSAGEOBJECTS_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ERRORS
--------------------------------------------------------

  ALTER TABLE "ERRORS" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "ERRORS" ADD PRIMARY KEY ("ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table PROVISIONAL_MESSAGES
--------------------------------------------------------

  ALTER TABLE "PROVISIONAL_MESSAGES" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "PROVISIONAL_MESSAGES" ADD PRIMARY KEY ("ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table MESSAGE_SOURCES
--------------------------------------------------------

  ALTER TABLE "MESSAGE_SOURCES" ADD CONSTRAINT "MESSAGE_SOURCES_PKEY" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "MESSAGE_SOURCES" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MESSAGE_OBJECTS
--------------------------------------------------------

  ALTER TABLE "MESSAGE_OBJECTS" ADD CONSTRAINT "MESSAGE_OBJECTS_PKEY" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "MESSAGE_OBJECTS" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table OBJECT_DETAILS
--------------------------------------------------------

  ALTER TABLE "OBJECT_DETAILS" ADD CONSTRAINT "OBJECT_DETAILS_PKEY" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "OBJECT_DETAILS" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table EVENT_TYPES_CODES
--------------------------------------------------------

  ALTER TABLE "EVENT_TYPES_CODES" MODIFY ("MESSAGES_ID" NOT NULL ENABLE);
 
  ALTER TABLE "EVENT_TYPES_CODES" MODIFY ("EVENTTYPECODES_ID" NOT NULL ENABLE);
 
  ALTER TABLE "EVENT_TYPES_CODES" ADD PRIMARY KEY ("MESSAGES_ID", "EVENTTYPECODES_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table OBJECTS
--------------------------------------------------------

  ALTER TABLE "OBJECTS" ADD CONSTRAINT "OBJECTS_PKEY" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "OBJECTS" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MOBJECTS_DETAILS
--------------------------------------------------------

  ALTER TABLE "MOBJECTS_DETAILS" MODIFY ("MESSAGE_OBJECTS_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MOBJECTS_DETAILS" MODIFY ("DETAILS_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MOBJECTS_DETAILS" ADD PRIMARY KEY ("MESSAGE_OBJECTS_ID", "DETAILS_ID") ENABLE;
 
  ALTER TABLE "MOBJECTS_DETAILS" ADD UNIQUE ("DETAILS_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table EVENT_TYPES_TO_CODES
--------------------------------------------------------

  ALTER TABLE "EVENT_TYPES_TO_CODES" ADD CONSTRAINT "EVENT_TYPES_TO_CODES_PKEY" PRIMARY KEY ("EVENT_TYPE", "CODE") ENABLE;
 
  ALTER TABLE "EVENT_TYPES_TO_CODES" MODIFY ("EVENT_TYPE" NOT NULL ENABLE);
 
  ALTER TABLE "EVENT_TYPES_TO_CODES" MODIFY ("CODE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table OBJECTS_DETAIL_TYPES
--------------------------------------------------------

  ALTER TABLE "OBJECTS_DETAIL_TYPES" ADD CONSTRAINT "OBJECTS_DETAIL_TYPES_ID" UNIQUE ("OBJECTDETAILTYPES_ID") ENABLE;
 
  ALTER TABLE "OBJECTS_DETAIL_TYPES" ADD CONSTRAINT "OBJECTS_DETAIL_TYPES_PKEY" PRIMARY KEY ("OBJECTS_ID", "OBJECTDETAILTYPES_ID") ENABLE;
 
  ALTER TABLE "OBJECTS_DETAIL_TYPES" MODIFY ("OBJECTS_ID" NOT NULL ENABLE);
 
  ALTER TABLE "OBJECTS_DETAIL_TYPES" MODIFY ("OBJECTDETAILTYPES_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MESSAGE_OBJECTS_OBJECT_DETAILS
--------------------------------------------------------

  ALTER TABLE "MESSAGE_OBJECTS_OBJECT_DETAILS" ADD CONSTRAINT "MESSAGE_OBJECT_DETAILS_ID_KEY" UNIQUE ("DETAILS_ID") ENABLE;
 
  ALTER TABLE "MESSAGE_OBJECTS_OBJECT_DETAILS" ADD CONSTRAINT "MESSAGE_OBJECT_DETAILS_PKEY" PRIMARY KEY ("MESSAGE_OBJECTS_ID", "DETAILS_ID") ENABLE;
 
  ALTER TABLE "MESSAGE_OBJECTS_OBJECT_DETAILS" MODIFY ("MESSAGE_OBJECTS_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MESSAGE_OBJECTS_OBJECT_DETAILS" MODIFY ("DETAILS_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PARTICIPANTS_CODES
--------------------------------------------------------

  ALTER TABLE "PARTICIPANTS_CODES" MODIFY ("PARTICIPANTS_ID" NOT NULL ENABLE);
 
  ALTER TABLE "PARTICIPANTS_CODES" MODIFY ("PARTICIPANTTYPECODES_ID" NOT NULL ENABLE);
 
  ALTER TABLE "PARTICIPANTS_CODES" ADD PRIMARY KEY ("PARTICIPANTS_ID", "PARTICIPANTTYPECODES_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table NETWORK_ACCESS_POINTS
--------------------------------------------------------

  ALTER TABLE "NETWORK_ACCESS_POINTS" ADD CONSTRAINT "NETWORK_ACCESS_POINTS_PKEY" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "NETWORK_ACCESS_POINTS" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SOP_CLASSES
--------------------------------------------------------

  ALTER TABLE "SOP_CLASSES" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "SOP_CLASSES" ADD PRIMARY KEY ("ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table MESSAGES_MOBJECTS
--------------------------------------------------------

  ALTER TABLE "MESSAGES_MOBJECTS" MODIFY ("MESSAGES_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MESSAGES_MOBJECTS" MODIFY ("MESSAGEOBJECTS_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MESSAGES_MOBJECTS" ADD PRIMARY KEY ("MESSAGES_ID", "MESSAGEOBJECTS_ID") ENABLE;
 
  ALTER TABLE "MESSAGES_MOBJECTS" ADD UNIQUE ("MESSAGEOBJECTS_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table MESSAGES_MESSAGE_PARTICIPANTS
--------------------------------------------------------

  ALTER TABLE "MESSAGES_MESSAGE_PARTICIPANTS" ADD CONSTRAINT "MESSAGES_PARTICIPANTS_ID_KEY" UNIQUE ("MESSAGEPARTICIPANTS_ID") ENABLE;
 
  ALTER TABLE "MESSAGES_MESSAGE_PARTICIPANTS" ADD CONSTRAINT "MESSAGES_PARTICIPANTS_PKEY" PRIMARY KEY ("MESSAGES_ID", "MESSAGEPARTICIPANTS_ID") ENABLE;
 
  ALTER TABLE "MESSAGES_MESSAGE_PARTICIPANTS" MODIFY ("MESSAGES_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MESSAGES_MESSAGE_PARTICIPANTS" MODIFY ("MESSAGEPARTICIPANTS_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MESSAGES
--------------------------------------------------------

  ALTER TABLE "MESSAGES" ADD CONSTRAINT "MESSAGES_PKEY" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "MESSAGES" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MESSAGES_MESSAGE_SOURCES
--------------------------------------------------------

  ALTER TABLE "MESSAGES_MESSAGE_SOURCES" ADD CONSTRAINT "MESSAGES_MESSAGE_SOURCES_ID" UNIQUE ("MESSAGESOURCES_ID") ENABLE;
 
  ALTER TABLE "MESSAGES_MESSAGE_SOURCES" ADD CONSTRAINT "MESSAGES_MESSAGE_SOURCES_PKEY" PRIMARY KEY ("MESSAGES_ID", "MESSAGESOURCES_ID") ENABLE;
 
  ALTER TABLE "MESSAGES_MESSAGE_SOURCES" MODIFY ("MESSAGES_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MESSAGES_MESSAGE_SOURCES" MODIFY ("MESSAGESOURCES_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CODES
--------------------------------------------------------

  ALTER TABLE "CODES" ADD CONSTRAINT "CODES_PKEY" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "CODES" MODIFY ("CODETYPE" NOT NULL ENABLE);
 
  ALTER TABLE "CODES" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MESSAGES_MSOURCES
--------------------------------------------------------

  ALTER TABLE "MESSAGES_MSOURCES" MODIFY ("MESSAGES_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MESSAGES_MSOURCES" MODIFY ("MESSAGESOURCES_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MESSAGES_MSOURCES" ADD PRIMARY KEY ("MESSAGES_ID", "MESSAGESOURCES_ID") ENABLE;
 
  ALTER TABLE "MESSAGES_MSOURCES" ADD UNIQUE ("MESSAGESOURCES_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table PARTICIPANTS_TO_CODES
--------------------------------------------------------

  ALTER TABLE "PARTICIPANTS_TO_CODES" ADD CONSTRAINT "PARTICIPANTS_TO_CODES_PKEY" PRIMARY KEY ("PARTICIPANT", "CODE") ENABLE;
 
  ALTER TABLE "PARTICIPANTS_TO_CODES" MODIFY ("PARTICIPANT" NOT NULL ENABLE);
 
  ALTER TABLE "PARTICIPANTS_TO_CODES" MODIFY ("CODE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PARTICIPANTS
--------------------------------------------------------

  ALTER TABLE "PARTICIPANTS" ADD CONSTRAINT "PARTICIPANTS_PKEY" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "PARTICIPANTS" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MESSAGE_PARTICIPANTS
--------------------------------------------------------

  ALTER TABLE "MESSAGE_PARTICIPANTS" ADD CONSTRAINT "MESSAGE_PARTICIPANTS_PKEY" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "MESSAGE_PARTICIPANTS" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table OBJECTS_DESCRIPTIONS
--------------------------------------------------------

  ALTER TABLE "OBJECTS_DESCRIPTIONS" MODIFY ("OBJECTS_ID" NOT NULL ENABLE);
 
  ALTER TABLE "OBJECTS_DESCRIPTIONS" MODIFY ("OBJECTDESCRIPTIONS_ID" NOT NULL ENABLE);
 
  ALTER TABLE "OBJECTS_DESCRIPTIONS" ADD PRIMARY KEY ("OBJECTS_ID", "OBJECTDESCRIPTIONS_ID") ENABLE;
 
  ALTER TABLE "OBJECTS_DESCRIPTIONS" ADD UNIQUE ("OBJECTDESCRIPTIONS_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table SOURCES
--------------------------------------------------------

  ALTER TABLE "SOURCES" ADD CONSTRAINT "SOURCES_PKEY" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "SOURCES" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table DESCRIPTIONS_SOP_CLASSES
--------------------------------------------------------

  ALTER TABLE "DESCRIPTIONS_SOP_CLASSES" MODIFY ("OBJECT_DESCRIPTIONS_ID" NOT NULL ENABLE);
 
  ALTER TABLE "DESCRIPTIONS_SOP_CLASSES" MODIFY ("SOPCLASSES_ID" NOT NULL ENABLE);
 
  ALTER TABLE "DESCRIPTIONS_SOP_CLASSES" ADD PRIMARY KEY ("OBJECT_DESCRIPTIONS_ID", "SOPCLASSES_ID") ENABLE;
 
  ALTER TABLE "DESCRIPTIONS_SOP_CLASSES" ADD UNIQUE ("SOPCLASSES_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table OBJECT_DESCRIPTIONS
--------------------------------------------------------

  ALTER TABLE "OBJECT_DESCRIPTIONS" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "OBJECT_DESCRIPTIONS" ADD PRIMARY KEY ("ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table MESSAGES_MPARTICIPANTS
--------------------------------------------------------

  ALTER TABLE "MESSAGES_MPARTICIPANTS" MODIFY ("MESSAGES_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MESSAGES_MPARTICIPANTS" MODIFY ("MESSAGEPARTICIPANTS_ID" NOT NULL ENABLE);
 
  ALTER TABLE "MESSAGES_MPARTICIPANTS" ADD PRIMARY KEY ("MESSAGES_ID", "MESSAGEPARTICIPANTS_ID") ENABLE;
 
  ALTER TABLE "MESSAGES_MPARTICIPANTS" ADD UNIQUE ("MESSAGEPARTICIPANTS_ID") ENABLE;
--------------------------------------------------------
--  DDL for Index IDX_PARTICIPANTS_TO_CODES
--------------------------------------------------------

  CREATE INDEX "IDX_PARTICIPANTS_TO_CODES" ON "PARTICIPANTS_TO_CODES" ("PARTICIPANT") 
  ;
--------------------------------------------------------
--  DDL for Index NETWORK_ACCESS_POINTS_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "NETWORK_ACCESS_POINTS_PKEY" ON "NETWORK_ACCESS_POINTS" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index MESSAGE_SOURCES_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "MESSAGE_SOURCES_PKEY" ON "MESSAGE_SOURCES" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index OBJECT_DETAILS_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "OBJECT_DETAILS_PKEY" ON "OBJECT_DETAILS" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SOURCES_TO_CODES_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "SOURCES_TO_CODES_PKEY" ON "SOURCES_TO_CODES" ("SOURCE", "CODE") 
  ;
--------------------------------------------------------
--  DDL for Index MESSAGE_OBJECTS_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "MESSAGE_OBJECTS_PKEY" ON "MESSAGE_OBJECTS" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index MESSAGES_MESSAGE_SOURCES_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "MESSAGES_MESSAGE_SOURCES_PKEY" ON "MESSAGES_MESSAGE_SOURCES" ("MESSAGES_ID", "MESSAGESOURCES_ID") 
  ;
--------------------------------------------------------
--  DDL for Index IDX_MESSAGE_OBJECTS
--------------------------------------------------------

  CREATE INDEX "IDX_MESSAGE_OBJECTS" ON "MESSAGE_OBJECTS" ("OBJECT_ID") 
  ;
--------------------------------------------------------
--  DDL for Index MESSAGES_MESSAGE_SOURCES_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX "MESSAGES_MESSAGE_SOURCES_ID" ON "MESSAGES_MESSAGE_SOURCES" ("MESSAGESOURCES_ID") 
  ;
--------------------------------------------------------
--  DDL for Index PARTICIPANTS_TO_CODES_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "PARTICIPANTS_TO_CODES_PKEY" ON "PARTICIPANTS_TO_CODES" ("PARTICIPANT", "CODE") 
  ;
--------------------------------------------------------
--  DDL for Index IDX_MESSAGE_PARTICIPANTS_0
--------------------------------------------------------

  CREATE INDEX "IDX_MESSAGE_PARTICIPANTS_0" ON "MESSAGE_PARTICIPANTS" ("NETWORKACCESSPOINT_ID") 
  ;
--------------------------------------------------------
--  DDL for Index OBJECTS_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "OBJECTS_PKEY" ON "OBJECTS" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index MESSAGES_MESSAGE_OBJECTS_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX "MESSAGES_MESSAGE_OBJECTS_ID" ON "MESSAGES_MESSAGE_OBJECTS" ("MESSAGEOBJECTS_ID") 
  ;
--------------------------------------------------------
--  DDL for Index IDX_MESSAGE_PARTICIPANTS
--------------------------------------------------------

  CREATE INDEX "IDX_MESSAGE_PARTICIPANTS" ON "MESSAGE_PARTICIPANTS" ("PARTICIPANT_ID") 
  ;
--------------------------------------------------------
--  DDL for Index IDX_EVENT_TYPES_TO_CODES
--------------------------------------------------------

  CREATE INDEX "IDX_EVENT_TYPES_TO_CODES" ON "EVENT_TYPES_TO_CODES" ("EVENT_TYPE") 
  ;
--------------------------------------------------------
--  DDL for Index PARTICIPANTS_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "PARTICIPANTS_PKEY" ON "PARTICIPANTS" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index DETAIL_TYPES_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "DETAIL_TYPES_PKEY" ON "DETAIL_TYPES" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index OBJECTS_DETAIL_TYPES_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX "OBJECTS_DETAIL_TYPES_ID" ON "OBJECTS_DETAIL_TYPES" ("OBJECTDETAILTYPES_ID") 
  ;
--------------------------------------------------------
--  DDL for Index MESSAGES_PARTICIPANTS_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "MESSAGES_PARTICIPANTS_PKEY" ON "MESSAGES_MESSAGE_PARTICIPANTS" ("MESSAGES_ID", "MESSAGEPARTICIPANTS_ID") 
  ;
--------------------------------------------------------
--  DDL for Index MESSAGE_OBJECT_DETAILS_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "MESSAGE_OBJECT_DETAILS_PKEY" ON "MESSAGE_OBJECTS_OBJECT_DETAILS" ("MESSAGE_OBJECTS_ID", "DETAILS_ID") 
  ;
--------------------------------------------------------
--  DDL for Index IDX_SOURCES_TO_CODES
--------------------------------------------------------

  CREATE INDEX "IDX_SOURCES_TO_CODES" ON "SOURCES_TO_CODES" ("SOURCE") 
  ;
--------------------------------------------------------
--  DDL for Index IDX_OBJECTS
--------------------------------------------------------

  CREATE INDEX "IDX_OBJECTS" ON "OBJECTS" ("OBJECTIDTYPECODE_ID") 
  ;
--------------------------------------------------------
--  DDL for Index IDX_SOURCES_TO_CODES_0
--------------------------------------------------------

  CREATE INDEX "IDX_SOURCES_TO_CODES_0" ON "SOURCES_TO_CODES" ("CODE") 
  ;
--------------------------------------------------------
--  DDL for Index IDX_MESSAGE_SOURCES
--------------------------------------------------------

  CREATE INDEX "IDX_MESSAGE_SOURCES" ON "MESSAGE_SOURCES" ("SOURCE_ID") 
  ;
--------------------------------------------------------
--  DDL for Index MESSAGES_MESSAGE_OBJECTS_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "MESSAGES_MESSAGE_OBJECTS_PKEY" ON "MESSAGES_MESSAGE_OBJECTS" ("MESSAGES_ID", "MESSAGEOBJECTS_ID") 
  ;
--------------------------------------------------------
--  DDL for Index EVENT_TYPES_TO_CODES_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "EVENT_TYPES_TO_CODES_PKEY" ON "EVENT_TYPES_TO_CODES" ("EVENT_TYPE", "CODE") 
  ;
--------------------------------------------------------
--  DDL for Index IDX_MESSAGES_MESSAGE_SOURCES
--------------------------------------------------------

  CREATE INDEX "IDX_MESSAGES_MESSAGE_SOURCES" ON "MESSAGES_MESSAGE_SOURCES" ("MESSAGES_ID") 
  ;
--------------------------------------------------------
--  DDL for Index MESSAGES_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "MESSAGES_PKEY" ON "MESSAGES" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index OBJECTS_DETAIL_TYPES_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "OBJECTS_DETAIL_TYPES_PKEY" ON "OBJECTS_DETAIL_TYPES" ("OBJECTS_ID", "OBJECTDETAILTYPES_ID") 
  ;
--------------------------------------------------------
--  DDL for Index SOURCES_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "SOURCES_PKEY" ON "SOURCES" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index CODES_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "CODES_PKEY" ON "CODES" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index IDX_EVENT_TYPES_TO_CODES_0
--------------------------------------------------------

  CREATE INDEX "IDX_EVENT_TYPES_TO_CODES_0" ON "EVENT_TYPES_TO_CODES" ("CODE") 
  ;
--------------------------------------------------------
--  DDL for Index MESSAGE_PARTICIPANTS_PKEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "MESSAGE_PARTICIPANTS_PKEY" ON "MESSAGE_PARTICIPANTS" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index IDX_PARTICIPANTS_TO_CODES_0
--------------------------------------------------------

  CREATE INDEX "IDX_PARTICIPANTS_TO_CODES_0" ON "PARTICIPANTS_TO_CODES" ("CODE") 
  ;
--------------------------------------------------------
--  DDL for Index IDX_MESSAGES
--------------------------------------------------------

  CREATE INDEX "IDX_MESSAGES" ON "MESSAGES" ("EVENTID_ID") 
  ;
--------------------------------------------------------
--  DDL for Index IDX_MESSAGES_MESSAGE_OBJECTS
--------------------------------------------------------

  CREATE INDEX "IDX_MESSAGES_MESSAGE_OBJECTS" ON "MESSAGES_MESSAGE_OBJECTS" ("MESSAGES_ID") 
  ;
--------------------------------------------------------
--  DDL for Index MESSAGES_PARTICIPANTS_ID_KEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "MESSAGES_PARTICIPANTS_ID_KEY" ON "MESSAGES_MESSAGE_PARTICIPANTS" ("MESSAGEPARTICIPANTS_ID") 
  ;
--------------------------------------------------------
--  DDL for Index MESSAGE_OBJECT_DETAILS_ID_KEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "MESSAGE_OBJECT_DETAILS_ID_KEY" ON "MESSAGE_OBJECTS_OBJECT_DETAILS" ("DETAILS_ID") 
  ;
--------------------------------------------------------
--  DDL for Index IDX_OBJECTS_DETAIL_TYPES
--------------------------------------------------------

  CREATE INDEX "IDX_OBJECTS_DETAIL_TYPES" ON "OBJECTS_DETAIL_TYPES" ("OBJECTS_ID") 
  ;

--------------------------------------------------------
--  Ref Constraints for Table DESCRIPTIONS_SOP_CLASSES
--------------------------------------------------------

  ALTER TABLE "DESCRIPTIONS_SOP_CLASSES" ADD CONSTRAINT "FK527E8333892E991" FOREIGN KEY ("SOPCLASSES_ID")
	  REFERENCES "SOP_CLASSES" ("ID") ENABLE;
 
  ALTER TABLE "DESCRIPTIONS_SOP_CLASSES" ADD CONSTRAINT "FK527E833E4FF9141" FOREIGN KEY ("OBJECT_DESCRIPTIONS_ID")
	  REFERENCES "OBJECT_DESCRIPTIONS" ("ID") ENABLE;


--------------------------------------------------------
--  Ref Constraints for Table EVENT_TYPES_CODES
--------------------------------------------------------

  ALTER TABLE "EVENT_TYPES_CODES" ADD CONSTRAINT "FK6EB886BB49858202" FOREIGN KEY ("EVENTTYPECODES_ID")
	  REFERENCES "CODES" ("ID") ENABLE;
 
  ALTER TABLE "EVENT_TYPES_CODES" ADD CONSTRAINT "FK6EB886BB723F9796" FOREIGN KEY ("MESSAGES_ID")
	  REFERENCES "MESSAGES" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table EVENT_TYPES_TO_CODES
--------------------------------------------------------

  ALTER TABLE "EVENT_TYPES_TO_CODES" ADD CONSTRAINT "FK_EVENT_TYPES_TO_CODES" FOREIGN KEY ("EVENT_TYPE")
	  REFERENCES "MESSAGES" ("ID") ENABLE;
 
  ALTER TABLE "EVENT_TYPES_TO_CODES" ADD CONSTRAINT "FK_EVENT_TYPES_TO_CODES_0" FOREIGN KEY ("CODE")
	  REFERENCES "CODES" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MESSAGES
--------------------------------------------------------

  ALTER TABLE "MESSAGES" ADD CONSTRAINT "FK_MESSAGES" FOREIGN KEY ("EVENTID_ID")
	  REFERENCES "CODES" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MESSAGES_MESSAGE_OBJECTS
--------------------------------------------------------

  ALTER TABLE "MESSAGES_MESSAGE_OBJECTS" ADD CONSTRAINT "FK_MESSAGES_MESSAGE_OBJECTS" FOREIGN KEY ("MESSAGEOBJECTS_ID")
	  REFERENCES "MESSAGE_OBJECTS" ("ID") ENABLE;
 
  ALTER TABLE "MESSAGES_MESSAGE_OBJECTS" ADD CONSTRAINT "FK_MESSAGES_MESSAGE_OBJECTS_0" FOREIGN KEY ("MESSAGES_ID")
	  REFERENCES "MESSAGES" ("ID") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table MESSAGES_MESSAGE_SOURCES
--------------------------------------------------------

  ALTER TABLE "MESSAGES_MESSAGE_SOURCES" ADD CONSTRAINT "FK_MESSAGES_MESSAGE_SOURCES" FOREIGN KEY ("MESSAGES_ID")
	  REFERENCES "MESSAGES" ("ID") ENABLE;
 
  ALTER TABLE "MESSAGES_MESSAGE_SOURCES" ADD CONSTRAINT "FK_MESSAGES_MESSAGE_SOURCES_0" FOREIGN KEY ("MESSAGESOURCES_ID")
	  REFERENCES "MESSAGE_SOURCES" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MESSAGES_MOBJECTS
--------------------------------------------------------

  ALTER TABLE "MESSAGES_MOBJECTS" ADD CONSTRAINT "FKFBC2C59A723F9796" FOREIGN KEY ("MESSAGES_ID")
	  REFERENCES "MESSAGES" ("ID") ENABLE;
 
  ALTER TABLE "MESSAGES_MOBJECTS" ADD CONSTRAINT "FKFBC2C59A8EA7BE74" FOREIGN KEY ("MESSAGEOBJECTS_ID")
	  REFERENCES "MESSAGE_OBJECTS" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MESSAGES_MPARTICIPANTS
--------------------------------------------------------

  ALTER TABLE "MESSAGES_MPARTICIPANTS" ADD CONSTRAINT "FKE55A5ADA723F9796" FOREIGN KEY ("MESSAGES_ID")
	  REFERENCES "MESSAGES" ("ID") ENABLE;
 
  ALTER TABLE "MESSAGES_MPARTICIPANTS" ADD CONSTRAINT "FKE55A5ADACAF86044" FOREIGN KEY ("MESSAGEPARTICIPANTS_ID")
	  REFERENCES "MESSAGE_PARTICIPANTS" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MESSAGES_MSOURCES
--------------------------------------------------------

  ALTER TABLE "MESSAGES_MSOURCES" ADD CONSTRAINT "FKE62B9C3E723F9796" FOREIGN KEY ("MESSAGES_ID")
	  REFERENCES "MESSAGES" ("ID") ENABLE;
 
  ALTER TABLE "MESSAGES_MSOURCES" ADD CONSTRAINT "FKE62B9C3EA859652C" FOREIGN KEY ("MESSAGESOURCES_ID")
	  REFERENCES "MESSAGE_SOURCES" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MESSAGE_OBJECTS
--------------------------------------------------------

  ALTER TABLE "MESSAGE_OBJECTS" ADD CONSTRAINT "FK_MESSAGE_OBJECTS" FOREIGN KEY ("OBJECT_ID")
	  REFERENCES "OBJECTS" ("ID") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table MESSAGE_PARTICIPANTS
--------------------------------------------------------

  ALTER TABLE "MESSAGE_PARTICIPANTS" ADD CONSTRAINT "FK_MESSAGE_PARTICIPANTS" FOREIGN KEY ("PARTICIPANT_ID")
	  REFERENCES "PARTICIPANTS" ("ID") ENABLE;
 
  ALTER TABLE "MESSAGE_PARTICIPANTS" ADD CONSTRAINT "FK_MESSAGE_PARTICIPANTS_0" FOREIGN KEY ("NETWORKACCESSPOINT_ID")
	  REFERENCES "NETWORK_ACCESS_POINTS" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MESSAGE_SOURCES
--------------------------------------------------------

  ALTER TABLE "MESSAGE_SOURCES" ADD CONSTRAINT "FK_MESSAGE_SOURCES" FOREIGN KEY ("SOURCE_ID")
	  REFERENCES "SOURCES" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MOBJECTS_DETAILS
--------------------------------------------------------

  ALTER TABLE "MOBJECTS_DETAILS" ADD CONSTRAINT "FKD5B820A3A2AB665" FOREIGN KEY ("MESSAGE_OBJECTS_ID")
	  REFERENCES "MESSAGE_OBJECTS" ("ID") ENABLE;
 
  ALTER TABLE "MOBJECTS_DETAILS" ADD CONSTRAINT "FKD5B820AB662E78D" FOREIGN KEY ("DETAILS_ID")
	  REFERENCES "OBJECT_DETAILS" ("ID") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table OBJECTS
--------------------------------------------------------

  ALTER TABLE "OBJECTS" ADD CONSTRAINT "FK_OBJECTS" FOREIGN KEY ("OBJECTIDTYPECODE_ID")
	  REFERENCES "CODES" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table OBJECTS_DESCRIPTIONS
--------------------------------------------------------

  ALTER TABLE "OBJECTS_DESCRIPTIONS" ADD CONSTRAINT "FKDB628142AAE08D22" FOREIGN KEY ("OBJECTDESCRIPTIONS_ID")
	  REFERENCES "OBJECT_DESCRIPTIONS" ("ID") ENABLE;
 
  ALTER TABLE "OBJECTS_DESCRIPTIONS" ADD CONSTRAINT "FKDB628142EC6FAC8A" FOREIGN KEY ("OBJECTS_ID")
	  REFERENCES "OBJECTS" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table OBJECTS_DETAIL_TYPES
--------------------------------------------------------

  ALTER TABLE "OBJECTS_DETAIL_TYPES" ADD CONSTRAINT "FK_OBJECTS_DETAIL_TYPES" FOREIGN KEY ("OBJECTDETAILTYPES_ID")
	  REFERENCES "DETAIL_TYPES" ("ID") ENABLE;
 
  ALTER TABLE "OBJECTS_DETAIL_TYPES" ADD CONSTRAINT "FK_OBJECTS_DETAIL_TYPES_0" FOREIGN KEY ("OBJECTS_ID")
	  REFERENCES "OBJECTS" ("ID") ENABLE;



--------------------------------------------------------
--  Ref Constraints for Table PARTICIPANTS_CODES
--------------------------------------------------------

  ALTER TABLE "PARTICIPANTS_CODES" ADD CONSTRAINT "FKA99332C7387FCFEE" FOREIGN KEY ("PARTICIPANTS_ID")
	  REFERENCES "PARTICIPANTS" ("ID") ENABLE;
 
  ALTER TABLE "PARTICIPANTS_CODES" ADD CONSTRAINT "FKA99332C7DAC1C71A" FOREIGN KEY ("PARTICIPANTTYPECODES_ID")
	  REFERENCES "CODES" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PARTICIPANTS_TO_CODES
--------------------------------------------------------

  ALTER TABLE "PARTICIPANTS_TO_CODES" ADD CONSTRAINT "FK_PARTICIPANTS_TO_CODES" FOREIGN KEY ("PARTICIPANT")
	  REFERENCES "PARTICIPANTS" ("ID") ENABLE;
 
  ALTER TABLE "PARTICIPANTS_TO_CODES" ADD CONSTRAINT "FK_PARTICIPANTS_TO_CODES_0" FOREIGN KEY ("CODE")
	  REFERENCES "CODES" ("ID") ENABLE;



--------------------------------------------------------
--  Ref Constraints for Table SOURCES_CODES
--------------------------------------------------------

  ALTER TABLE "SOURCES_CODES" ADD CONSTRAINT "FKD9A64DF6215342" FOREIGN KEY ("SOURCES_ID")
	  REFERENCES "SOURCES" ("ID") ENABLE;
 
  ALTER TABLE "SOURCES_CODES" ADD CONSTRAINT "FKD9A64DF8175D5FE" FOREIGN KEY ("SOURCETYPECODES_ID")
	  REFERENCES "CODES" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SOURCES_TO_CODES
--------------------------------------------------------

  ALTER TABLE "SOURCES_TO_CODES" ADD CONSTRAINT "FK_SOURCES_TO_CODES" FOREIGN KEY ("SOURCE")
	  REFERENCES "SOURCES" ("ID") ENABLE;
 
  ALTER TABLE "SOURCES_TO_CODES" ADD CONSTRAINT "FK_SOURCES_TO_CODES_0" FOREIGN KEY ("CODE")
	  REFERENCES "CODES" ("ID") ENABLE;
