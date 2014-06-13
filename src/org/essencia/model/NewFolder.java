package org.essencia.model;

import java.io.File;

import org.essencia.ide.ResourceNode;
import org.essencia.ide.Templete;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;

@Face(displayName="Folder", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class NewFolder extends Templete{

	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	public NewFolder(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
	}
	
	String folderName;
		@Face(displayName="FolderName")
		public String getFolderName() {
			return folderName;
		}
	
		public void setFolderName(String folderName) {
			this.folderName = folderName;
		}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] create() throws Exception{
		
		
		ResourceNode node = new ResourceNode();
		node.setName(this.getFolderName());
		node.setId("id"+node.getName());
		node.setPath("D:/codi/codebase/\\codi\\uengine.org"+ File.separatorChar + node.getName());
		node.setType(TreeNode.TYPE_FOLDER);
		node.setFolder(true);
		
		File file = new File(node.getPath());
		
		if(!file.exists())
			file.mkdirs();
		
		return new Object[]{new Remover(new ModalWindow()), new ToAppend(this,node), new Refresh(node)};
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Remover cancel(){
		return new Remover(new ModalWindow());		
	}
}
