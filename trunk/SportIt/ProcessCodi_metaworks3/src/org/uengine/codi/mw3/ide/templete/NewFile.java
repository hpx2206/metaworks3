package org.uengine.codi.mw3.ide.templete;

import java.io.File;

import org.metaworks.MetaworksException;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.ide.CloudWindow;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Templete;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.util.CodiFileUtil;
import org.uengine.codi.util.CodiStringUtil;

@Face(displayName="$templete.file", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class NewFile extends Templete {
	
	String name;
		@Face(displayName="$templete.file.name")
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
			node.setName(this.getName());
			node.setId(targetNode.getId() + File.separatorChar + node.getName());
			node.setPath(targetNode.getPath() + File.separatorChar + node.getName());
			
			if(CodiFileUtil.exists(node.getPath()))
				throw new Exception("$file.already.exists");

			Editor editor = (Editor)node.beforeAction();
			editor.save();
			
			return new Object[]{new Remover(new ModalWindow()), new ToAppend(targetNode, node), new ToAppend(new CloudWindow("editor"), editor)};
		}else{
			throw new MetaworksException("finish error");
		}
	}

}
