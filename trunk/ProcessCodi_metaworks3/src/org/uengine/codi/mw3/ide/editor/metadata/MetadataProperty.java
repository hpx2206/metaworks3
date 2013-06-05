package org.uengine.codi.mw3.ide.editor.metadata;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.ResourceTree;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.view.Navigator;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.kernel.GlobalContext;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


@XStreamAlias("MetadataProperty")
public class MetadataProperty {
	
	public MetadataProperty() {
		setFile(new MetaworksFile()); 
	}
	
	@XStreamAsAttribute
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	
	@XStreamOmitField
	SelectBox selectedType;
		public SelectBox getSelectedType() {
			return selectedType;
		}
		public void setSelectedType(SelectBox selectedType) {
			this.selectedType = selectedType;
		}

	@XStreamAsAttribute
	boolean isKeyEditable;
		public boolean isKeyEditable() {
			return isKeyEditable;
		}
		public void setKeyEditable(boolean isKeyEditable) {
			this.isKeyEditable = isKeyEditable;
		}
	@XStreamAsAttribute
	boolean isRemote;
		public boolean isRemote() {
			return isRemote;
		}
		public void setRemote(boolean isRemote) {
			this.isRemote = isRemote;
		}

	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	String value;
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	
	@XStreamOmitField
	MetaworksFile file;
		public MetaworksFile getFile() {
			return file;
		}
		public void setFile(MetaworksFile file) {
			this.file = file;
		}
	
	@XStreamOmitField
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	@XStreamAsAttribute
	int index;
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}

		
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
