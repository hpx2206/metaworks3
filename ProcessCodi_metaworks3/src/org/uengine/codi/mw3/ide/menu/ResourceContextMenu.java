package org.uengine.codi.mw3.ide.menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.ToOpener;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.MenuItem;
import org.metaworks.widget.ModalWindow;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.ide.CloudTab;
import org.uengine.codi.mw3.ide.CloudWindow;
import org.uengine.codi.mw3.ide.DockerService;
import org.uengine.codi.mw3.ide.IStorageService;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.AmazonService;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.editor.process.ProcessMergeEditor;
import org.uengine.codi.mw3.ide.libraries.ProcessNode;
import org.uengine.codi.mw3.knowledge.ProjectInfo;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.codi.util.CodiStringUtil;
import org.uengine.dbrepo.AppDbRepository;
import org.uengine.dbrepo.IAppDbRepository;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.webservice.ServiceClassGenerator;
import org.metaworks.dao.TransactionContext;

public class ResourceContextMenu extends CloudMenu {

	public final static String WHEN_COPY = "copy";
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	@AutowiredFromClient
	transient public ProjectInfo projectInfo;
	
	@AutowiredFromClient
	public Workspace workspace;
	
	public ResourceContextMenu(){
		
	}
	
	public ResourceContextMenu(ResourceNode resourceNode, Session session){		
		this.setResourceNode(resourceNode);
		
		this.setId("ResourceContext");
		this.setName("ResourceContext");
		this.setContext(true);
		
		this.add(new SubMenuItem(new NewMenu(this.getResourceNode())));
		
		if(ResourceNode.TYPE_FILE_JAVA.equals(this.getResourceNode().getType()) ||
		   ResourceNode.TYPE_FILE_PROCESS.equals(this.getResourceNode().getType())){
			if( this.getResourceNode() != null && this.getResourceNode() instanceof ProcessNode){
				this.add(new SubMenuItem(new OpenMenu((ProcessNode)this.getResourceNode())));
				
			}else{
				this.add(new SubMenuItem(new OpenMenu(this.getResourceNode())));
			}
		}
		
//		this.add(new MenuItem("open", "$resource.menu.open"));
		//this.add(new MenuItem("processMerge", "$processMergeCompare"));
		
		if(!ResourceNode.TYPE_PROJECT.equals(resourceNode.getType())){ 
			this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
			this.add(new MenuItem("copy", "$resource.menu.copy"));
		}else{
//			this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
//			this.add(new MenuItem("copy", "$resource.menu.copy"));
//			this.add(new MenuItem("paste", "$resource.menu.paste"));
//			this.add(new MenuItem("remove", "$resource.menu.remove"));
//			this.add(new MenuItem("move", "$resource.menu.move"));
//			this.add(new MenuItem("rename", "$resource.menu.rename"));
//			this.add(new MenuItem("deployee", "$resource.menu.deployee"));
//			this.add(new MenuItem("registerApp", "$resource.menu.registerApp"));	
		}
		
		if(session.getClipboard() != null){
			Object clipboard = session.getClipboard();
			
			if(clipboard instanceof ResourceNode){
				ResourceNode copyNode = (ResourceNode)clipboard;
				
				if(WHEN_COPY.equals(copyNode.getMetaworksContext().getWhen()))
					this.add(new MenuItem("paste", "$resource.menu.paste"));
			}
		}
		
		if(!ResourceNode.TYPE_PROJECT.equals(resourceNode.getType())){
			this.add(new MenuItem("remove", "$resource.menu.remove"));
			//this.add(new MenuItem("move", "$resource.menu.move"));
			//this.add(new MenuItem("rename", "$resource.menu.rename"));
		}else{
			this.add(new MenuItem(MenuItem.TYPE_DIVIDER));			
			this.add(new MenuItem("manageMetadata", "$resource.menu.manageMetadata"));
			this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
			this.add(new MenuItem("deployee", "$resource.menu.deployee"));
			this.add(new MenuItem("registerApp", "$resource.menu.registerApp"));
		}
		
		if( this.getResourceNode() != null && this.getResourceNode() instanceof ProcessNode){
			// 프로세스 노드일때 특별한 작업
			this.add(new MenuItem("webService", "$resource.menu.webService"));
		}
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object open(){
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode node = (ResourceNode)clipboard;
			return node.action();			
		}else{
			return null;
		}
	}
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object processMerge() throws Exception{
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode node = (ResourceNode)clipboard;
			ProcessMergeEditor processMergeEditor = new ProcessMergeEditor(node);
			processMergeEditor.setId("$processMergeCompare");
			processMergeEditor.setName("$processMergeCompare");
			
			return new ToAppend(new CloudWindow("editor"), processMergeEditor);
			
		}else{
			return null;
		}
	}
	
	@ServiceMethod(callByContent=true)
	public Object copy(){
		this.getResourceNode().getMetaworksContext().setWhen(WHEN_COPY);
		
		session.setClipboard(this.getResourceNode());
		
		return session;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] paste(){
		this.getResourceNode().session = session;
		
		return this.getResourceNode().drop();
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] remove(){
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode node = (ResourceNode)clipboard;
			
			File file = new File(node.getPath());
			if(file.exists()){
				file.delete();
			}
			
			return new Object[]{new Remover(new CloudTab(node.getId()), true), new Remover(node)};
		}else{
			return null;
		}
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object manageMetadata() throws Exception {

		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode node = (ResourceNode)clipboard;
			
			ResourceNode metadataFileNode = new ResourceNode();
			metadataFileNode.setProjectId(node.getProjectId());
			metadataFileNode.setId(node.getId() + File.separatorChar + Project.METADATA_FILENAME);
			metadataFileNode.setName(Project.METADATA_FILENAME);
			metadataFileNode.setPath(node.getPath() + File.separatorChar + metadataFileNode.getName());
			metadataFileNode.setParentId(node.getId());
			metadataFileNode.setType(ResourceNode.findNodeType(metadataFileNode.getName()));
			metadataFileNode.setMetaworksContext(node.getMetaworksContext());
			
			return metadataFileNode.action();
			
		}		
		
		return null;
		//return new ModalWindow(new ModalPanel(app), 0, 0, "$resource.menu.registerApp");
	}
	public void executeCommand(String cmd[]) throws IOException, InterruptedException{
	
		Runtime runTime = Runtime.getRuntime();
		Process process = runTime.exec(cmd);
		
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String line;
		while((line = br.readLine()) != null){
			System.out.println(line);
		}
		int execTime = process.waitFor();
		System.out.println("exe time: " + execTime);
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] deployee() throws Exception {
		
		Object clipboard = session.getClipboard();
		ResourceNode node = (ResourceNode)clipboard;
		String codebase = GlobalContext.getPropertyString("codebase");	
		String projectName = node.getName();
		String projectId   = node.getId();
		String cmd[] = new String[3];
		String osName = System.getProperty("os.name");
		String realPath = new HttpServletRequestWrapper(TransactionContext.getThreadLocalInstance().getRequest())
		 				.getRealPath("")+File.separatorChar+"resources"+File.separatorChar+"maven"+File.separatorChar;
		String command = null;	
		
		if(osName.toLowerCase().startsWith("window")){
			command = realPath + GlobalContext.getPropertyString("maven.depoly.bat", "depoly.bat");
			cmd[0] = "cmd.exe";
			cmd[1] = "/C";
			cmd[2] = command + " " + realPath +  " " + codebase+File.separatorChar+projectId + " " + projectName;

		}else{
			command = realPath + GlobalContext.getPropertyString("maven.depoly.sh", "depoly.sh");
			cmd[0] = "/bin/sh";
			cmd[1] = "-c";
			cmd[2] = command + " " + realPath +  " " + codebase+File.separatorChar+projectId + " " + projectName;
		}
		//command arg[1]=pom.xml,depoly.sh 파일 위치 arg[2]=해당 프로젝트 경로 arg[3]=프로젝트 이름
		System.out.println("command ==>" + cmd[2]);
		executeCommand(cmd);
		
		String reopsitoryService = GlobalContext.getPropertyString("file.repository.service");
		
		IStorageService storageService=null;
		
		if("amazon".equals(reopsitoryService)){
			storageService = new AmazonService();
		}else if("docker".equals(reopsitoryService)){
			storageService = new DockerService();
		}
		storageService.putObject(projectId, projectName,false);
		
		IFrame frame = new IFrame(GlobalContext.getPropertyString("app.url.dev")+projectName);
		
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		modalWindow.setWidth(1000);
		modalWindow.setHeight(600);
		//modalWindow.setTitle("$deployee.complete.title");
		modalWindow.setTitle("배포 완료 - 테스트 서버 " + frame.getSrc());
		modalWindow.setPanel(frame);		
		modalWindow.getButtons().put("$Confirm", new ToOpener(this));
		
		return new Object[]{modalWindow, new Remover(this)};
	}
	  
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object registerApp() throws Exception {
		 
		// 앱등록시 DB생성 
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode node = (ResourceNode)clipboard;
			
			AppDbRepository adr = new AppDbRepository();
			IAppDbRepository dao = adr.findDbRepo(1, node.getName());
			if(dao == null){
				AppDbRepository.addDbRepository(1, node.getName() , node.getId());
			}
		} 
		
		
//		AppDbRepository.addDbRepository(1, wfNode.getName(), wfNode.getId());
//		App app = new App();
//		app.load();
//		app.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
//		
//		Object clipboard = session.getClipboard();
//		if(clipboard instanceof ResourceNode){
//			ResourceNode node = (ResourceNode)clipboard;
//			
//			
//			for(int i = 0; i < app.getAttachProject().getOptionNames().size(); i++) {
//				
//				String value = app.getAttachProject().getOptionNames().get(i);
//				
//				if(value.equals(node.getProjectId()) == false) {
//					app.getAttachProject().remove(i);
//				}				
//			}			
//		}		
//		
//		return new ModalWindow(new ModalPanel(app), 0, 0, "$resource.menu.registerApp");
		

		return null;
	}
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] webService() throws Exception {
		
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ProcessNode){
			ProcessNode node = (ProcessNode)clipboard;		
			Editor editor = new Editor(node);
			String definitionString = editor.load();
			System.out.println(definitionString);
			ProcessDefinition def = (ProcessDefinition) GlobalContext.deserialize(definitionString);
			
			ServiceClassGenerator serviceClassGenerator = new ServiceClassGenerator();
			if( serviceClassGenerator.isWebServiceConvert(def) ){
				
			}else{
				throw new MetaworksException("웹서비스로 만들 엑티비티가 없습니다.");
			}
			
		}
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		modalWindow.setWidth(300);
		modalWindow.setHeight(150);
		modalWindow.setTitle("$webService.complete.title");
		modalWindow.setPanel("웹서비스등록이 완료 되었습니다.");//$deployee.complete.message		
		modalWindow.getButtons().put("$Confirm", new ToOpener(this));
		
		return new Object[]{modalWindow, new Remover(this)};
	}
	
	
	@ServiceMethod
	public Object showProperties(){		
		return null;
	}
	
}
