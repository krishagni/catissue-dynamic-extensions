alter table DYEXTN_ENTITY_GROUP add (IS_CACORE_GENERATED number(1,0) default 0);

UPDATE DYEXTN_ENTITY_GROUP SET DYEXTN_ENTITY_GROUP.LONG_NAME=(Select DYEXTN_ABSTRACT_METADATA.NAME from DYEXTN_ABSTRACT_METADATA WHERE DYEXTN_ENTITY_GROUP.IDENTIFIER=DYEXTN_ABSTRACT_METADATA.IDENTIFIER AND DYEXTN_ENTITY_GROUP.LONG_NAME IS NULL),DYEXTN_ENTITY_GROUP.SHORT_NAME=DYEXTN_ENTITY_GROUP.LONG_NAME WHERE DYEXTN_ENTITY_GROUP.LONG_NAME IS NULL;

commit;