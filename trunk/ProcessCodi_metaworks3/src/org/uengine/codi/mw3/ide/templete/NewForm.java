package org.uengine.codi.mw3.ide.templete;

import java.io.File;

import org.metaworks.MetaworksException;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.CloudWindow;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Templete;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.editor.Editor;

@Face(displayName="$templete.form", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"fieldOrder"}, values={"packageName,name"})
public class NewForm extends Templete {

	@AutowiredFromClient
	public Workspace workspace;
	
	String packageName;
		@Face(displayName="$templete.form.package")
		@NonEditable
		public String getPackageName() {
			return packageName;
		}
	
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}
	
	String name;
		@Face(displayName="$templete.form.name")
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
	public void load(){
		Project project = workspace.findProject(this.getResourceNode().getProjectId());
		
		String packageName = ResourceNode.makePackageName(this.getResourceNode().getId());
		
		this.setPackageName(packageName);
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND, keyBinding="enter")
	public Object[] finish() throws Exception {
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode targetNode = (ResourceNode)clipboard;
			
			ResourceNode node = new ResourceNode();
			node.setName(this.getName() + ".java");
			node.setId(targetNode.getId() + File.separatorChar + node.getName());
			node.setPath(targetNode.getPath() + File.separatorChar + node.getName());
			node.setProjectId(targetNode.getProjectId());
			node.setParentId(targetNode.getParentId());
			node.setType(targetNode.getType());
			node.workspace = workspace;
			
			Editor editor = null;
			try {
				editor = (Editor)node.beforeAction();
			} catch (Exception e) {
			}
			
			editor.save();
			
			return new Object[]{new ToAppend(targetNode, node), new ToAppend(new CloudWindow("editor"), editor) , new Remover(new ModalWindow())};
		}else{
			throw new MetaworksException("finish error");
		}
	}		
}
