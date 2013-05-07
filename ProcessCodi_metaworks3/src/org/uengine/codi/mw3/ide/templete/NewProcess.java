package org.uengine.codi.mw3.ide.templete;

import java.io.File;

import org.metaworks.MetaworksException;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.CloudWindow;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Templete;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.editor.process.ProcessEditor;
import org.uengine.codi.mw3.model.Session;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"fieldOrder"}, values={"packageName,name"})
public class NewProcess extends Templete {

	@AutowiredFromClient
	public Session session;
	
	String name;
		@Face(displayName="Name")
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] finish() throws Exception {
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode targetNode = (ResourceNode)clipboard;
			
			ResourceNode node = new ResourceNode();
			node.setName(this.getName() + ".wpd");
			node.setId(targetNode.getId() + File.separatorChar + node.getName());			
			node.setType(TreeNode.TYPE_FILE_PROCESS);
			
			ProcessEditor editor = new ProcessEditor(node.getId());
			editor.jbPath = jbPath;
			editor.save();
			
			return new Object[]{new ToAppend(targetNode, node), new ToAppend(new CloudWindow("editor"), editor), new Remover(new ModalWindow())};
		}else{
			throw new MetaworksException("finish error");
		}
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Remover cancel(){
		return new Remover(new ModalWindow());		
	}
}
