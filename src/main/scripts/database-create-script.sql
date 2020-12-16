-- CREATE TABLE `news`.`news` (`ID` BIGINT NOT NULL AUTO_INCREMENT, `DATE` DATE, `BRIEF_CONTENT` VARCHAR(255), `CONTENT` LONGTEXT, `TITLE` VARCHAR(255), `AUTHOR_ID` BIGINT NOT NULL, PRIMARY KEY (`ID`));

-- INSERT INTO "SYSTEM"."NEWS" (ID, TITLE, BRIEF_CONTENT, CONTENT, NEWS_DATE) VALUES ('1', 'SpaceX rocket crashes', 'See this SpaceX rocket''s fiery crash landing', 'Things were going well, until a SpaceX rocket prototype made a fiery crash landing. Elon Musk says an issue with the rocket''s fuel system was the cause. The company hopes a version of this rocket will one day transport humans to Mars.', '2020-11-11')
-- INSERT INTO "SYSTEM"."NEWS" (ID, TITLE, BRIEF_CONTENT, CONTENT, NEWS_DATE) VALUES ('2', 'FDA''s Covid-19 vaccine authorization is a ''monumental moment''', 'Shipments of Pfizer and BioNTech''s Covid-19 vaccine will be distributed soon across the country after the FDA on Friday authorized the vaccine for emergency use, a landmark in the pandemic.', 'The emergency use authorization (EUA) is a "significant milestone" in the fight against the pandemic, FDA Commissioner Dr. Stephen Hahn said in a statement Friday. He said it comes after an "open and transparent review process that included input from independent scientific and public health experts and a thorough evaluation by the agency''s career scientists." An EUA stops short of a full approval. Pfizer would have to file a separate application for its vaccine to be fully licensed by the FDA.', '2020-11-10')
-- INSERT INTO "SYSTEM"."NEWS" (ID, TITLE, BRIEF_CONTENT, CONTENT, NEWS_DATE) VALUES ('3', 'Dinosaurs would have continued to thrive had it not been for the asteroid, researchers saying', 'Dinosaurs were doing well and could have continued to dominate Planet Earth if they had not been wiped out by an asteroid, new research has found.', 'After emerging during the Triassic period some 230 million years ago, dinosaurs occupied every continent and were dominant in most terrestrial ecosystems, until they were rendered extinct by the asteroid impact 66 million years ago.', '2019-10-10')

CREATE TABLE "SYSTEM"."NEWS"
   (	"ID" NUMBER NOT NULL ENABLE,
	"TITLE" VARCHAR2(100 BYTE),
	"BRIEF_CONTENT" VARCHAR2(500 BYTE),
	"CONTENT" VARCHAR2(2048 BYTE),
	"NEWS_DATE" DATE,
	 CONSTRAINT "NEWS_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

Insert into NEWS (ID,TITLE,BRIEF_CONTENT,CONTENT,NEWS_DATE) values (50,'SpaceX rocket crashes','See this SpaceX rocket''''s fiery crash landing','Things were going well, until a SpaceX rocket prototype made a fiery crash landing. Elon Musk says an issue with the rocket''s fuel system was the cause. The company hopes a version of this rocket will one day transport humans to Mars.',to_date('26-03-2020','dd-mm-yyyy'));
Insert into NEWS (ID,TITLE,BRIEF_CONTENT,CONTENT,NEWS_DATE) values (51,'FDA''s Covid-19 vaccine authorization is a ''monumental moment''','Shipments of Pfizer and BioNTech''s Covid-19 vaccine will be distributed soon across the country after the FDA on Friday authorized the vaccine for emergency use, a landmark in the pandemic.','The emergency use authorization (EUA) is a "significant milestone" in the fight against the pandemic, FDA Commissioner Dr. Stephen Hahn said in a statement Friday. He said it comes after an "open and transparent review process that included input from independent scientific and public health experts and a thorough evaluation by the agency''s career scientists." An EUA stops short of a full approval. Pfizer would have to file a separate application for its vaccine to be fully licensed by the FDA.',to_date('26-12-2020','dd-mm-yyyy'));
Insert into NEWS (ID,TITLE,BRIEF_CONTENT,CONTENT,NEWS_DATE) values (52,'Dinosaurs would have continued to thrive had it not been for the asteroid, researchers saying','Dinosaurs were doing well and could have continued to dominate Planet Earth if they had not been wiped out by an asteroid, new research has found.','After emerging during the Triassic period some 230 million years ago, dinosaurs occupied every continent and were dominant in most terrestrial ecosystems, until they were rendered extinct by the asteroid impact 66 million years ago.',to_date('26-10-2020','dd-mm-yyyy'));