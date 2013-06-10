package org.uengine.codi.mw3.ide.editor.metadata;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.metadata.MetadataProperty;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.ResourceTree;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.view.Navigator;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.kernel.GlobalContext;

import com.thoughtworks.xstream.XStream;

public class MetadataPropertyImpl extends MetadataProperty {

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_NONE)
	public String toXmlXStream(){
		XStream stream = new XStream();
		stream.autodetectAnnotations(true);
		
		return stream.toXML(this);
	}
	
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object findResource(){
		
		String codebase = GlobalContext.getPropertyString("codebase", "codebase");
		String companyId = "uEngine";
		// make workspace
		Workspace workspace = new Workspace();
		workspace.load(codebase, companyId);
		
		Navigator navigator = new Navigator();		
		ResourceNode workspaceNode = new ResourceNode();
		workspaceNode.setId(workspace.getId());
		workspaceNode.setRoot(true);
		workspaceNode.setHidden(true);
		
		for(Project project : workspace.getProjects()){
			ResourceNode node = new ResourceNode(project);
			node.getMetaworksContext().setWhere("resource");
			workspaceNode.add(node);
		}
		
		ResourceTree resourceTree = new ResourceTree();
		resourceTree.setId(workspace.getId());
		resourceTree.setNode(workspaceNode);
		
		navigator.setResourceTree(resourceTree);
		navigator.setId("popupTree");
		
		Popup popup = new Popup();
		popup.setPanel(navigator);
		return popup;
	}
	@ServiceMethod(callByContent=true)
	public void remove(){
		
		XStream stream = new XStream();
		stream.autodetectAnnotations(true);
		
		System.out.println("remove herer");
		
	}
	
	@ServiceMethod(payload="selectedType", eventBinding="change", bindingFor="selectedType", bindingHidden=true, target=ServiceMethodContext.TARGET_SELF)
	public void selectType() {}
}
