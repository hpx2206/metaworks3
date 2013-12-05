package org.uengine.codi.mw3.ide.menu;

import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.MenuItem;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.ide.CloudWindow;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.editor.java.JavaCodeEditor;
import org.uengine.codi.mw3.ide.editor.metadata.MetadataXmlEditor;
import org.uengine.codi.mw3.ide.editor.metadata.ProcessXmlEditor;
import org.uengine.codi.mw3.ide.libraries.ProcessNode;

public class OpenMenu extends CloudMenu{

	@AutowiredFromClient
	public Workspace workspace;
	
	@AutowiredFromClient
	public ProcessNode processNode;
		public ProcessNode getProcessNode() {
			return processNode;
		}
		public void setProcessNode(ProcessNode processNode) {
			this.processNode = processNode;
		}

	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}


	public OpenMenu(){
		this(null);
	}
	
	
	public OpenMenu(ResourceNode resourceNode){
		this.setResourceNode(resourceNode);
		
		this.setId("new");
		this.setName("$resource.menu.open");
		
		this.add(new MenuItem("openWithUI","UI"));
//		this.add(new MenuItem("openWithJava","OpenWithJava"));
//		this.add(new MenuItem("openWithXML","OpenWithXML"));
		this.add(new MenuItem("code","$resource.menu.code"));
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object openWithUI() throws Exception{
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode node = (ResourceNode)clipboard;
			node.workspace = workspace;
			return node.action();			
		}else{
			return null;
		}
	}
	
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object code() throws Exception{
		String type = null;
		if("valuechain".equals(this.getResourceNode().getType())){
			type = ResourceNode.findNodeType(this.getResourceNode().getName());
		}else if("metadata".equals(this.getResourceNode().getType())){
			type = ResourceNode.findNodeType(this.getResourceNode().getName());
		}else if("java".equals(this.getResourceNode().getType())){
			type = ResourceNode.findNodeType(this.getResourceNode().getName());
		}else{
			type = ResourceNode.findNodeType(this.getProcessNode().getName());
		}
		
		if(type.equals(TreeNode.TYPE_FILE_JAVA)){
			Editor editor = new JavaCodeEditor(this.getResourceNode());
//			e?ditor.load();
//			EditorWindow editorWindow = new EditorWindow();
//			editorWindow.setType(type);
//			editorWindow.load();
			return new ToAppend(new CloudWindow("java"), new JavaCodeEditor(this.getResourceNode()));
		}else if(type.equals(TreeNode.TYPE_FILE_PROCESS)){
			Editor editor =  new ProcessXmlEditor(this.getProcessNode());
//			editor.load();
			return new ToAppend(new CloudWindow("process"), new ProcessXmlEditor(this.getProcessNode()));
		}else if(type.equals(TreeNode.TYPE_FILE_METADATA)){
			Editor editor = new MetadataXmlEditor(this.getResourceNode());
//			editor.load();
			return new ToAppend(new CloudWindow("xml"), new MetadataXmlEditor(this.getResourceNode()));
		}else if(type.equals(TreeNode.TYPE_FILE_VALUECHAIN)){
			Editor editor = new MetadataXmlEditor(this.getResourceNode());
////			editor.load();
			return new ToAppend(new CloudWindow("valuechain"), new MetadataXmlEditor(this.getResourceNode()));
		}
		return null;
	}
	
	
	
	
}
