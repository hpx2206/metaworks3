package org.uengine.codi.mw3.ide.menu;

import java.io.File;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.MenuItem;
import org.metaworks.widget.ModalPanel;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.ide.CloudTab;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.knowledge.ProjectServer;
import org.uengine.codi.mw3.knowledge.ProjectServers;
import org.uengine.codi.mw3.marketplace.App;
import org.uengine.processmanager.ProcessManagerRemote;

public class ResourceContextMenu extends CloudMenu {

	@Autowired
	public ProcessManagerRemote processManager;
	
	public ResourceContextMenu(){
		this(null);
	}
	
	public ResourceContextMenu(ResourceNode resourceNode){
		this.setResourceNode(resourceNode);
		
		this.setId("ResourceContext");
		this.setName("ResourceContext");
		this.setContext(true);
		
		this.add(new SubMenuItem(new NewMenu(this.getResourceNode())));
		this.add(new MenuItem("open", "$resource.menu.open"));
		this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
		this.add(new MenuItem("copy", "$resource.menu.copy"));
		this.add(new MenuItem("paste", "$resource.menu.paste"));
		this.add(new MenuItem("remove", "$resource.menu.remove"));
		this.add(new MenuItem("move", "$resource.menu.move"));
		this.add(new MenuItem("rename", "$resource.menu.rename"));
		this.add(new MenuItem("deployee", "$resource.menu.deployee"));
		this.add(new MenuItem("registerApp", "$resource.menu.registerApp"));
						
		
/*		this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
		this.add(new SubMenuItem(new TeamMenu()));
		this.add(new MenuItem(MenuItem.TYPE_DIVIDER));
		this.add(new MenuItem("showProperties", "Properties"));*/

	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
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
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object deployee() throws Exception {
		
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode node = (ResourceNode)clipboard;			
			
			ProjectServers devServers = new ProjectServers(node.getProjectId(), "dev"); //prod
			devServers.processManager = processManager;
			devServers.load();
			
			for(ProjectServer projectServer : devServers.getServerList()) {
				
				if(projectServer.getType().indexOf("WAS") > 0)
					projectServer.deploy();
			}
		}	
		
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		modalWindow.setWidth(300);
		modalWindow.setHeight(150);
		modalWindow.setTitle("$deployee.complete.title");
		modalWindow.setPanel("배포가 완료 되었습니다.");//$deployee.complete.message		
		modalWindow.getButtons().put("$Confirm", this);
		
		return modalWindow;
	}
	
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object registerApp() throws Exception {

		App app = new App();
		app.load();
		app.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode node = (ResourceNode)clipboard;
			
			
			for(int i = 0; i < app.getAttachProject().getOptionNames().size(); i++) {
				
				String value = app.getAttachProject().getOptionNames().get(i);
				
				if(value.equals(node.getProjectId()) == false) {
					app.getAttachProject().remove(i);
				}				
			}			
		}		
		
		return new ModalWindow(new ModalPanel(app), 0, 0, "$resource.menu.registerApp");
	}
	
	
	
	
	@ServiceMethod
	public Object showProperties(){		
		return null;
	}
	
}
