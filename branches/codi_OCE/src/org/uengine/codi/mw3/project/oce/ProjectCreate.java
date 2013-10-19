package org.uengine.codi.mw3.project.oce;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.ITool;
import org.uengine.processmanager.ProcessManagerRemote;

public class ProjectCreate implements ITool {
	
	String projectId;
		@Hidden
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
		
	String osSelect;
	@Face(displayName="운영체제 선택", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", options={"리눅스(LINUX)","유닉스(UNIX)","Window NT"}, values={"LINUX", "UNIX", "Window"})
		public String getOsSelect() {
			return osSelect;
		}
		public void setOsSelect(String osSelect) {
			this.osSelect = osSelect;
		}

	String wasSelect;
	@Face(displayName="서버엔진 선택", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", options={"서버엔진 사용안함","Jboss 6.0","Tomcat 7.0"}, values={"사용안함", "Jboss", "Tomcat"})
		public String getWasSelect() {
			return wasSelect;
		}
		public void setWasSelect(String wasSelect) {
			this.wasSelect = wasSelect;
		}

	String dbSelect;
	@Face(displayName="데이터베이스 선택", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", options={"데이터베이스 사용안함","Cubrid","Mysql","Oracle"}, values={"사용안함", "Cubrid", "Mysql","Oracle"})
		public String getDbSelect() {
			return dbSelect;
		}
		public void setDbSelect(String dbSelect) {
			this.dbSelect = dbSelect;
		}

	@Autowired
	public ProcessManagerRemote processManager ; 	
	
	@Override
	public void onLoad() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterComplete() throws Exception {
		
	}

}
