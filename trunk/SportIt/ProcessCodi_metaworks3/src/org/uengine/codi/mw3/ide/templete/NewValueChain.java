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
import org.uengine.codi.mw3.ide.editor.valuechain.ValueChainEditor;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.util.CodiFileUtil;

@Face(displayName="$templete.valuechain", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class NewValueChain extends Templete {

	@AutowiredFromClient
	public Session session;
	
	String name;
		@Face(displayName="$templete.valuechain.name")
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	@Override
	public Object[] finish() throws Exception {
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode targetNode = (ResourceNode)clipboard;
			
			ResourceNode node = new ResourceNode();
			node.setName(this.getName() + ".valuechain");
			node.setId(targetNode.getId() + File.separatorChar + node.getName());
			node.setPath(targetNode.getPath() + File.separatorChar + node.getName());
			node.setProjectId(targetNode.getProjectId());
			node.setType(TreeNode.TYPE_FILE_VALUECHAIN);
			
			if(CodiFileUtil.exists(node.getPath()))
				throw new Exception("$file.already.exists");

			ValueChainEditor editor = new ValueChainEditor(node);
			editor.getValueChainDesigner().getProcessNameView().setAlias(this.getName());
			editor.getValueChainDesigner().getRolePanel().setEditorId(node.getPath());
			editor.getValueChainDesigner().getProcessVariablePanel().setEditorId(node.getPath());
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
