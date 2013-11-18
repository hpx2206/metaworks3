package org.uengine.codi.mw3.knowledge;

import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.project.oce.KtProjectServers;
import org.uengine.codi.mw3.project.oce.NewServer;
import org.uengine.kernel.GlobalContext;


@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"fieldOrder"},values={"topicTitle,projectAlias,topicDescription,logoFile,projectSecuopt,fileType"} ,
ejsPathMappingByContext=	{
			"{how: 'html', face: 'dwr/metaworks/org/uengine/codi/mw3/model/ProjectTitle_HTML.ejs'}"
})
public class ProjectTitle implements ContextAware {
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	@AutowiredFromClient
	public PageNavigator pageNavigator; 
			
	public ProjectTitle(){
		setFileType("svn");
	}
	String topicId;
		@Hidden
		public String getTopicId() {
			return topicId;
		}
		public void setTopicId(String topicId) {
			this.topicId = topicId;
		}
		
	String topicTitle;
		@Face(displayName="$ProjectName", ejsPath="genericfaces/richText.ejs",options={"rows", "cols"}, values={"1", "50"})
		@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
		public String getTopicTitle() {
			return topicTitle;
		}
		public void setTopicTitle(String topicTitle) {
			this.topicTitle = topicTitle;
		}
	
	String topicDescription;
		@Face(displayName="$ProjectDescription", ejsPath="genericfaces/richText.ejs", options = { "rows", "cols" }, values = { "10", "50" })
		@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
		public String getTopicDescription() {
			return topicDescription;
		}
		public void setTopicDescription(String topicDescription) {
			this.topicDescription = topicDescription;
		}
	MetaworksFile logoFile;
		@Face(displayName="$LogoFile")
		public MetaworksFile getLogoFile() {
			return logoFile;
		}
		public void setLogoFile(MetaworksFile logoFile) {
			this.logoFile = logoFile;
		}

	String type;
		@Hidden
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

	String code;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}

	String fileType;
		@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
		@Face(displayName="$project.fileType.select", ejsPath="dwr/metaworks/genericfaces/RadioButton.ejs", options={"$SVN","$WAR"}, values={"svn","war"})
		public String getFileType() {
			return fileType;
		}
		public void setFileType(String fileType) {
			this.fileType = fileType;
		}

	String embeddedHtml;
		@Face(displayName="$topicEmbeddedHtml")
		@Available(how={"html"})
		public String getEmbeddedHtml() {
			return embeddedHtml;
		}
		public void setEmbeddedHtml(String embeddedHtml) {
			this.embeddedHtml = embeddedHtml;
		}
	String parentId;
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}
		
	boolean projectSecuopt;				
		@Face(displayName="$projectSecuopt")
		@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
		public boolean isProjectSecuopt() {
			return projectSecuopt;
		}
		public void setProjectSecuopt(boolean projectSecuopt) {
			this.projectSecuopt = projectSecuopt;
		}
	
	String projectAlias;
		@Face(displayName="$Alias", ejsPath="genericfaces/richText.ejs",options={"rows", "cols"}, values={"1", "50"})
		public String getProjectAlias() {
			return projectAlias;
		}
		public void setProjectAlias(String projectAlias) {
			this.projectAlias = projectAlias;
		}
		
//	@Face(displayName="$Next")
	@Available(when={MetaworksContext.WHEN_NEW})
//	@ServiceMethod(callByContent = true, target = ServiceMethodContext.TARGET_SELF)
	public Object createProjectStep1() throws Exception {
		NewServer newServer = new NewServer();
		
		if(this.getLogoFile().getFileTransfer() != null &&
				this.getLogoFile().getFilename() != null && 
				this.getLogoFile().getFilename().length() > 0){			
			this.getLogoFile().upload();
		}
		
		newServer.setProjectTitle(this);
		newServer.setServerGroup(KtProjectServers.SERVER_DEV);
		newServer.setMetaworksContext(new MetaworksContext());
		newServer.getMetaworksContext().setHow("projectCreate");
		newServer.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		return newServer;
	}
		
			
		
	@Face(displayName="$Create")
	@Available(when={MetaworksContext.WHEN_NEW})
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] save() throws Exception{
		this.saveMe();
		
		session.setLastPerspecteType("topic");
		session.setLastSelectedItem(this.getTopicId());

		
		ProjectNode projectNode = new ProjectNode();
		projectNode.setId(this.getTopicId());
		projectNode.setName(this.getTopicTitle());
		projectNode.setProjectAlias(this.getProjectAlias());
		projectNode.session = session;
		
		this.makeHtml();
		
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		this.getMetaworksContext().setHow("html");	

		if("svn".equals(this.getFileType())){
			String host = GlobalContext.getPropertyString("vm.manager.ip");
			String userId = GlobalContext.getPropertyString("vm.manager.user");
			String passwd = GlobalContext.getPropertyString("vm.manager.password");
			String command = null;
	
			/*JschCommand jschServerBehaviour = new JschCommand();
			jschServerBehaviour.sessionLogin(host, userId, passwd);
			
			// create SVN
			command = GlobalContext.getPropertyString("vm.svn.createProject") + " \"" + projectNode.getProjectAlias() + "\"";
			jschServerBehaviour.runCommand(command);
			
			// setting SVN
			command = GlobalContext.getPropertyString("vm.svn.setting") + " \"" +  projectNode.getProjectAlias() + "\"";
			jschServerBehaviour.runCommand(command);
			
			//SVN 유저 추가
			command = GlobalContext.getPropertyString("vm.svn.createUser") + " \"" +  projectNode.getProjectAlias() + "\" \"" + session.getEmployee().getEmpCode() + "\" \"" + session.getEmployee().getPassword() + "\"";
			jschServerBehaviour.runCommand(command);
			
			//Create Hudson
			command = GlobalContext.getPropertyString("vm.hudson.createJob") + " " +  projectNode.getProjectAlias();
			jschServerBehaviour.runCommand(command);
			
			//Setting Hudson
			command = GlobalContext.getPropertyString("vm.hudson.setting") + " " +  projectNode.getProjectAlias();
			jschServerBehaviour.runCommand(command);*/
			
		}
		
		
		Object[] returnObj = projectNode.loadTopic();
		Object[] returnObject = new Object[ returnObj.length + 3];
		for (int i = 0; i < returnObj.length; i++) {
			if( returnObj[i] instanceof InstanceListPanel){
				returnObject[i] = new Refresh(returnObj[i]);
			}else{
				returnObject[i] = new Refresh(returnObj[i]);
			}			
		}
		returnObject[returnObj.length ] = new ToAppend(new ProjectPanel(), projectNode);
		returnObject[returnObj.length + 1] = new Remover(new ModalWindow(), true);
		
		return returnObject;
		
	}
	
	public void saveMe() throws Exception {
		WfNode wfNode = new WfNode();
		
		if(this.getLogoFile().getFileTransfer() != null &&
				this.getLogoFile().getFilename() != null && 
				this.getLogoFile().getFilename().length() > 0){			
			this.getLogoFile().upload();
		}
		
		if(MetaworksContext.WHEN_NEW.equals(this.getMetaworksContext().getWhen())){
			wfNode.setName(this.getTopicTitle());
			wfNode.setProjectAlias(this.getProjectAlias());
			wfNode.setType("project");
			wfNode.setIsDistributed(false);
			wfNode.setIsReleased(false);
			wfNode.setSecuopt(projectSecuopt ? "1" : "0");
			wfNode.setParentId(session.getCompany().getComCode());	
			wfNode.setAuthorId(session.getUser().getUserId());
			
			if(this.getLogoFile().getUploadedPath() != null && this.getLogoFile().getFilename() != null){
				wfNode.setUrl(this.getLogoFile().getUploadedPath());
				wfNode.setThumbnail(this.getLogoFile().getFilename());
			}
			if(TenantContext.getThreadLocalInstance().getTenantId() != null)
				wfNode.setCompanyId(TenantContext.getThreadLocalInstance().getTenantId());
			else
				wfNode.setCompanyId(session.getCompany().getComCode());
				
			wfNode.setDescription(this.getTopicDescription());
			wfNode.setStartDate(new Date());
			wfNode.setVisType(this.getFileType());
			wfNode.createMe();
			
			TopicMapping tm = new TopicMapping();
			tm.setTopicId(wfNode.getId());
			tm.setUserId(session.getUser().getUserId());
			tm.setUserName(session.getUser().getName());
			tm.getMetaworksContext().setWhen(this.getMetaworksContext().getWhen());
			
			tm.saveMe();
			tm.flushDatabaseMe();
			
			this.setTopicId(wfNode.getId());
		}else{
			wfNode.setId(this.getTopicId());
			
			wfNode.copyFrom(wfNode.databaseMe());
			
			if(this.getLogoFile().getUploadedPath() != null && this.getLogoFile().getFilename() != null){
				wfNode.setUrl(this.getLogoFile().getUploadedPath());
				wfNode.setThumbnail(this.getLogoFile().getFilename());
			}
			
			wfNode.setName(this.getTopicTitle());
			wfNode.saveMe();
		}
	}
	
	@Face(displayName="$Close")
	@Available(how={"html"})
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object close() {
		return new Remover(new ModalWindow());
	}
	
	public void makeHtml() {
		try{
			HttpServletRequest request = TransactionContext.getThreadLocalInstance().getRequest();
			
	        String url = request.getRequestURL().toString();
	        String codebase = url.substring( 0, url.lastIndexOf( "/" ) );
	        URL urlURL = new java.net.URL(codebase);
	        
	       	String host = urlURL.getHost();
	       	int port = urlURL.getPort();
	       	String path = urlURL.getPath();
	       	String contextOnly = path.substring(0, path.substring(1).indexOf("/")+1);
			String protocol = urlURL.getProtocol();
	
			String defaultUrl = protocol + "://" + host + ":" + port + contextOnly + "/portlet_instanceList.html?type=Topic&id=" + this.getTopicId();
			String embeddedHtml = "<div style='padding:15px;border:1px solid #D7D7D7; font-size:14px; font-weight:bold;margin-bottom:10px;'>&lt;iframe id=\"portlet\" src=\"" + defaultUrl + "\" style=\"width: 500px; height: 500px; border-width:1px; border-color:red; border-style:solid;\"></iframe></div>";
			
			this.setEmbeddedHtml(embeddedHtml);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@AutowiredFromClient
	transient public Session session;
	

}
