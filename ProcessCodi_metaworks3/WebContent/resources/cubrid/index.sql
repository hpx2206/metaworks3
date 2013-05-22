ALTER TABLE "app" ADD PRIMARY KEY("appid");
ALTER TABLE "appkeys" ADD PRIMARY KEY("appkey");
ALTER TABLE "appmapping" ADD PRIMARY KEY("appid","comcode");
ALTER TABLE "bizrate" ADD PRIMARY KEY("empcode","yearmm");
ALTER TABLE "bpm_acltable" ADD PRIMARY KEY("acltableid");
ALTER TABLE "bpm_deadln_dim" ADD PRIMARY KEY("deadln_id");
ALTER TABLE "bpm_knol" ADD PRIMARY KEY("id");
ALTER TABLE "bpm_procdef" ADD PRIMARY KEY("defid");
ALTER TABLE "bpm_procdefver" ADD PRIMARY KEY("defverid");
ALTER TABLE "bpm_procinst" ADD PRIMARY KEY("instid");
ALTER TABLE "bpm_procvar" ADD PRIMARY KEY("varid");
ALTER TABLE "bpm_rolemapping" ADD PRIMARY KEY("rolemappingid");
ALTER TABLE "bpm_strtg" ADD PRIMARY KEY("strtgid");
ALTER TABLE "bpm_tag" ADD PRIMARY KEY("tagid");
ALTER TABLE "bpm_topicmapping" ADD PRIMARY KEY("topicmappingid");
ALTER TABLE "bpm_worklist" ADD PRIMARY KEY("taskid");
ALTER TABLE "category" ADD PRIMARY KEY("categoryid");
ALTER TABLE "comtable" ADD PRIMARY KEY("comcode");
ALTER TABLE "emptable" ADD PRIMARY KEY("empcode");
ALTER TABLE "oauth_token" ADD PRIMARY KEY("user_id");
ALTER TABLE "parttable" ADD PRIMARY KEY("partcode");
ALTER TABLE "processmap" ADD PRIMARY KEY("mapid");
ALTER TABLE "roletable" ADD PRIMARY KEY("rolecode");
ALTER TABLE "roleusertable" ADD PRIMARY KEY("rolecode","empcode","comcode");

CREATE INDEX "parentid" ON "bpm_knol"("parentid","no");
CREATE INDEX "fkf57f151c46f158c1" ON "bpm_procinst"("defid");

CREATE INDEX "fkf57f151c78eb68e6" ON "bpm_procinst"("rootinstid");

CREATE INDEX "test1" ON "bpm_procinst"("instid","rootinstid","secuopt");
CREATE INDEX "test1" ON "bpm_rolemapping"("rootinstid","assigntype","endpoint");

CREATE INDEX "test2" ON "bpm_rolemapping"("instid","rolename");

CREATE INDEX "test3" ON "bpm_rolemapping"("rolename");
CREATE INDEX "test1" ON "bpm_worklist"("instid","rolename");

CREATE INDEX "test2" ON "bpm_worklist"("instid","refrolename");

ALTER TABLE "bpm_procdefver" ADD  CONSTRAINT  "fk_defid" FOREIGN KEY ("defid") REFERENCES "bpm_procdef"("defid") ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE "parttable" ADD  CONSTRAINT  "fk_comcode" FOREIGN KEY ("comcode") REFERENCES "comtable"("comcode") ON DELETE RESTRICT ON UPDATE RESTRICT;
