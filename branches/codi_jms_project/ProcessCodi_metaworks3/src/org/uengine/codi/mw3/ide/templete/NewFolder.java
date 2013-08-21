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
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Templete;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.MajorProcessDefinitionNode;

@Face(displayName="$templete.folder", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class NewFolder extends Templete {

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
		if(clipboard instanceof ResourceNode){
			ResourceNode targetNode = (ResourceNode)clipboard;
			
			ResourceNode node = new ResourceNode();
			node.setName(this.getName());
			node.setId(targetNode.getId() + File.separatorChar + node.getName());
			node.setPath(targetNode.getPath() + File.separatorChar + node.getName());
			node.setProjectId(targetNode.getProjectId());
			node.setType(TreeNode.TYPE_FOLDER);
			node.setFolder(true);
			
			File file = new File(node.getPath());
			if(!file.exists())
				file.mkdirs();
			
			return new Object[]{new Remover(new ModalWindow()), new ToAppend(targetNode, node)};
		}else if(clipboard instanceof MajorProcessDefinitionNode){
			if( this.getName() != null && !"".equals(this.getName().trim())){
				MajorProcessDefinitionNode targetNode = (MajorProcessDefinitionNode)clipboard;
				targetNode.setExpanded(true);
				if(targetNode.getChild() != null){
					// 중복 폴더 체크
					for(int i=0; i < targetNode.getChild().size(); i++){
						MajorProcessDefinitionNode childNode = (MajorProcessDefinitionNode) targetNode.getChild().get(i);
						if( this.getName().equals(childNode.getName()) ){
							throw new MetaworksException("same process remain!!");
						}
					}
				}
				
				MajorProcessDefinitionNode node = new MajorProcessDefinitionNode();
				node.setName(this.getName());
				node.setId(targetNode.getId() + File.separatorChar + node.getName());
				node.setPath(targetNode.getId() + File.separatorChar + node.getName());
				node.setType(TreeNode.TYPE_FOLDER);
				node.setFolder(true);
				node.setExpanded(true);
				
				return new Object[]{new Remover(new ModalWindow()), new ToAppend(targetNode, node)};
			}else{
				return new Object[]{new Remover(new ModalWindow())};
			}
		}else{
			throw new MetaworksException("finish error");
		}
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Remover cancel(){
		return new Remover(new ModalWindow());		
	}
}
