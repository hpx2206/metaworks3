package org.uengine.codi.mw3.ide.templete;

import org.metaworks.MetaworksException;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.Templete;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.MajorProcessDefinitionNode;
import org.uengine.codi.mw3.webProcessDesigner.MinorProcessDefinitionNode;

@Face(displayName="Rename...", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class FileRenamer extends Templete {

	@AutowiredFromClient
	public Session session;
	
	String name;
		@Face(displayName="$templete.folder.name")
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] finish() throws Exception {
		Object clipboard = session.getClipboard();
		if(clipboard instanceof MajorProcessDefinitionNode){
			if( this.getName() != null && !"".equals(this.getName().trim())){
				MajorProcessDefinitionNode targetNode = (MajorProcessDefinitionNode)clipboard;
				targetNode.setName(this.getName());
				return new Object[]{new Remover(new ModalWindow()), new Refresh(targetNode)};
			}else{
				return new Object[]{new Remover(new ModalWindow())};
			}
		}else if(clipboard instanceof MinorProcessDefinitionNode){
			if( this.getName() != null && !"".equals(this.getName().trim())){
				MinorProcessDefinitionNode targetNode = (MinorProcessDefinitionNode)clipboard;
				targetNode.setName(this.getName());
				return new Object[]{new Remover(new ModalWindow()), new Refresh(targetNode)};
			}else{
				return new Object[]{new Remover(new ModalWindow())};
			}
		}else{
			throw new MetaworksException("finish error");
		}
	}
}
