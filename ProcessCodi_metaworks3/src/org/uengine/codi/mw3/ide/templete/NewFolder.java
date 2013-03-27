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
import org.uengine.codi.mw3.ide.JavaBuildPath;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Templete;
import org.uengine.codi.mw3.model.Session;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"fieldOrder"}, values={"packageName,name"})
public class NewFolder extends Templete {

	@AutowiredFromClient
	public Session session;
	
	@AutowiredFromClient
	public JavaBuildPath jbPath;
	
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
			node.setName(this.getName());
			node.setId(targetNode.getId() + File.separatorChar + node.getName());			
			node.setType(TreeNode.TYPE_FOLDER);
			node.setFolder(true);
			
			File file = new File(jbPath.getBasePath() + node.getId());
			if(!file.exists())
				file.mkdirs();
			
			return new Object[]{new Remover(new ModalWindow()), new ToAppend(targetNode, node)};
		}else{
			throw new MetaworksException("finish error");
		}
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Remover cancel(){
		return new Remover(new ModalWindow());		
	}
}
