package org.essencia.ide;

import org.essencia.mini.view.TreeNodeView;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class Templete implements ContextAware {
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	TreeNodeView componentTreeNode;
		@Hidden
		public TreeNodeView getComponentTreeNode() {
			return componentTreeNode;
		}
		public void setComponentTreeNode(TreeNodeView componentTreeNode) {
			this.componentTreeNode = componentTreeNode;
		}
		
	public Templete(){
		MetaworksContext metaworksContext = new MetaworksContext();
		metaworksContext.setWhen(MetaworksContext.WHEN_EDIT);
		
		this.setMetaworksContext(metaworksContext);
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Remover cancel(){
		return new Remover(new ModalWindow());		
	}
}
