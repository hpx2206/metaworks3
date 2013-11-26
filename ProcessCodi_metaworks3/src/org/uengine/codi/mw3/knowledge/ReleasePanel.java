package org.uengine.codi.mw3.knowledge;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.metadata.MetadataFile;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.Session;

@Face(ejsPath="", options={"fieldOrder"},values={"reflectVersion,check,warFile,sqlFile,comment"})
public class ReleasePanel {
	
	SelectBox reflectVersion;
		@Face(displayName = "$ReflectVersion")
		@Available(when={MetaworksContext.WHEN_EDIT, MetaworksContext.WHEN_NEW})
		public SelectBox getReflectVersion() {
			return reflectVersion;
		}
		public void setReflectVersion(SelectBox reflectVersion) {
			this.reflectVersion = reflectVersion;
		}
	
	Boolean check;
		@Face(displayName="$newFile")
		@Available(when=MetaworksContext.WHEN_EDIT)
		public Boolean getCheck() {
			return check;
		}
		public void setCheck(Boolean check) {
			this.check = check;
		}
		
	MetaworksFile warFile;
		@Face(displayName="$WarFile")
		@Available(when=MetaworksContext.WHEN_EDIT)
		public MetaworksFile getWarFile() {
			return warFile;
		}
		public void setWarFile(MetaworksFile warFile) {
			this.warFile = warFile;
		}
		
		MetaworksFile sqlFile;
		@Face(displayName="$SqlFile")
		@Available(when={MetaworksContext.WHEN_EDIT})
		public MetaworksFile getSqlFile() {
			return sqlFile;
		}
		public void setSqlFile(MetaworksFile sqlFile) {
			this.sqlFile = sqlFile;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
		
	String comment;
		@Face(displayName = "$Comment", ejsPath = "dwr/metaworks/genericfaces/richText.ejs", options={"rows", "cols"}, values = {"5", "50"})
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		
	@Face(displayName = "$release")
	@ServiceMethod(callByContent = true)
	public Object release() throws Exception{
		ModalWindow modalWindow = new ModalWindow();
		FilepathInfo filepathinfo = new FilepathInfo();
		filepathinfo.setId(Integer.parseInt(this.getReflectVersion().toString()));
		filepathinfo.copyFrom(filepathinfo.databaseMe());
		
		if(filepathinfo.findReleaseVersion(filepathinfo.getProjectId()) == 0){
			filepathinfo.setReleaseVer(1);
		}
		else{
			filepathinfo.setReleaseVer(filepathinfo.findReleaseVersion(filepathinfo.getProjectId()) + 1);
		}
		
		WfNode wfNode = new WfNode();
		wfNode.setId(this.getProjectId());
		wfNode.copyFrom(wfNode.databaseMe());
		
		if("war".equals(wfNode.getVisType())){
			if (this.getWarFile().getFileTransfer() != null
					&& this.getWarFile().getFilename() != null
					&& this.getWarFile().getFilename().length() > 0)
				this.getWarFile().upload();
			
			if (this.getSqlFile().getFileTransfer() != null
					&& this.getSqlFile().getFilename() != null
					&& this.getSqlFile().getFilename().length() > 0)
				this.getSqlFile().upload();
			
			if(!this.getCheck()){	//반영된 버전 미 사용
				filepathinfo.setId(filepathinfo.createNewId());
				filepathinfo.setComment(this.getComment());
				filepathinfo.setDistributor(session.getEmployee().getEmpName());
				filepathinfo.setModdate(new Date());
				filepathinfo.setReflectVer(0);
				
				filepathinfo.createDatabaseMe();
			}
			else{	//반영된 버전 사용 ---?????? 반영된버전은 디비에 저장 안함?
				filepathinfo.setId(Integer.parseInt(this.getReflectVersion().getSelected()));
				filepathinfo.copyFrom(filepathinfo.databaseMe());
			}
		}
		else if("svn".equals(wfNode.getVisType())){
			filepathinfo.setComment(this.getComment());
			filepathinfo.setDistributor(session.getEmployee().getEmpName());
			filepathinfo.setModdate(new Date());
			filepathinfo.setId(filepathinfo.createNewId());
			
			filepathinfo.createDatabaseMe();
			filepathinfo.flushDatabaseMe();
		}
		
		modalWindow.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		modalWindow.setWidth(300);
		modalWindow.setHeight(150);
		modalWindow.setTitle("$Release");
		modalWindow.setPanel(filepathinfo.getReleaseVer() + localeManager.getString(".0 버전으로 release 되었습니다."));
		modalWindow.getButtons().put("$Confirm", "");
		
		return new Remover(new ModalWindow());
	}
	
	public String command(String cmd){
		String result = null;
		StringBuffer strbuf = new StringBuffer ( );
		BufferedReader br = null;
		try{ 
			Process proc = Runtime.getRuntime ( ).exec ( cmd );
			br = new BufferedReader ( new InputStreamReader ( proc.getInputStream ( ) ) );
			String line;
			while ( ( line = br.readLine ( ) ) != null )
				strbuf.append ( line );
			br.close ( );
		}catch ( Exception e ) { result = "command file : " + e.toString(); }
		  
		result = strbuf.toString ( );
		return result;
	 }
	
	@AutowiredFromClient
	public Locale localeManager;
	
	@AutowiredFromClient
	public Session session;
}
