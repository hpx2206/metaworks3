package org.uengine.codi.mw3.knowledge;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.sql.RowSet;

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
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.Employee;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.vm.JschCommand;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;


@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
ejsPathMappingByContext=	{
			"{how: 'html', face: 'dwr/metaworks/org/uengine/codi/mw3/model/ProjectTitle.ejs'}"
})
public class ProjectTitle implements ContextAware {
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
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
		@Face(displayName="$ProjectName")
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
		
	String embeddedHtml;
		@Face(displayName="$topicEmbeddedHtml")
		@Available(how={"html"})
		public String getEmbeddedHtml() {
			return embeddedHtml;
		}
		public void setEmbeddedHtml(String embeddedHtml) {
			this.embeddedHtml = embeddedHtml;
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
		projectNode.session = session;
		
		this.makeHtml();
		
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		this.getMetaworksContext().setHow("html");	
	

		//String title = "프로젝트: " + this.getTopicTitle();
		
		/*
		String defId = "CreateProject.process";
		
		ProcessMap processMap = new ProcessMap();
		processMap.processManager = processManager;
		processMap.session = session;
		processMap.instanceView = instanceViewContent;
		processMap.setDefId(defId);
		
		String instId = processMap.initializeProcess();
				
		ProjectCreate projectCreate = new ProjectCreate();
		projectCreate.setName(this.getTopicTitle());
		projectCreate.setDescription(this.getTopicDescription());
		projectCreate.setProjectId(session.getLastSelectedItem());
		
		ResultPayload rp = new ResultPayload();
		rp.setProcessVariableChange(new KeyedParameter("ProjectCreate", projectCreate));
		
		RoleMappingPanel roleMappingPanel = new RoleMappingPanel(processManager, processMap.getDefId(), session);
		roleMappingPanel.putRoleMappings(processManager, instId);
		
		processManager.executeProcessByWorkitem(instId.toString(), rp);
		processManager.applyChanges();
		
		Instance instance = new Instance();
		
		instance.setInstId(new Long(instId));
		instance.databaseMe().setTopicId(this.getTopicId());
		instance.databaseMe().setName(instance.getDefName() + " : " + this.getTopicTitle());
		instance.flushDatabaseMe();
	
		IWfNode wfNode = (IWfNode)Database.sql(IWfNode.class, 
				"update bpm_knol set linkedInstId= ?InstId where id = ?id");
		wfNode.set("InstId", instId);
		wfNode.set("id", this.getTopicId());
		wfNode.update();
		
		
		String title = "프로젝트: " + this.getTopicTitle();
		Object[] returnObj = Perspective.loadInstanceListPanel(session, "topic", this.getTopicId(), title);	 //session, InstanceListPanel	
		Object[] returnObject = new Object[ returnObj.length + 3];	
		
		for (int i = 0; i < returnObj.length; i++) {
			if( returnObj[i] instanceof InstanceListPanel){
				returnObject[i] = new Refresh(returnObj[i]);
			}else{
				returnObject[i] = new Refresh(returnObj[i]);
			}			
		}
	
		InstanceView instanceView = instanceViewContent.instanceView;
		instanceView.session = session;		

		instanceView.load(instance);
		
		returnObject[returnObj.length] = new Refresh(instanceViewContent);
		returnObject[returnObj.length + 1] = new ToAppend(new ProjectPanel(), projectNode);
		returnObject[returnObj.length + 2] = new Remover(new ModalWindow(), true);
		*/
		
		

		String host = GlobalContext.getPropertyString("vm.manager.ip");
		String userId = GlobalContext.getPropertyString("vm.manager.user");
		String passwd = GlobalContext.getPropertyString("vm.manager.password");

		JschCommand jschServerBehaviour = new JschCommand();
		jschServerBehaviour.sessionLogin(host, userId, passwd);
		
		// create SVN
		String command = GlobalContext.getPropertyString("vm.svn.createProject") + " \"" + projectNode.getName()+ "\"";
		jschServerBehaviour.runCommand(command);
		
		// setting SVN
		command = GlobalContext.getPropertyString("vm.svn.setting") + " \"" +  projectNode.getName() + "\"";
		jschServerBehaviour.runCommand(command);
		
		//SVN 유저 추가
		command = GlobalContext.getPropertyString("vm.svn.createUser") + " \"" +  projectNode.getName() + "\" \"" + session.getEmployee().getEmpCode() + "\" \"" + session.getEmployee().getPassword() + "\"";
		jschServerBehaviour.runCommand(command);

		
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
		
		if(MetaworksContext.WHEN_NEW.equals(this.getMetaworksContext().getWhen())){
			wfNode.setName(this.getTopicTitle());
			wfNode.setType("project");
			wfNode.setParentId(session.getCompany().getComCode());	
			wfNode.setAuthorId(session.getUser().getUserId());		
			wfNode.setCompanyId(session.getCompany().getComCode());
			wfNode.setDescription(this.getTopicDescription());
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
	
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
	@AutowiredFromClient
	transient public Session session;

}
