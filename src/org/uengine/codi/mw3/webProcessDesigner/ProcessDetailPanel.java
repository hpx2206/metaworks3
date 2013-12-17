package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class ProcessDetailPanel implements ContextAware {

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	String editorId;
	@Id
		public String getEditorId() {
			return editorId;
		}
		public void setEditorId(String editorId) {
			this.editorId = editorId;
		}	
	Documentation documentation;
		public Documentation getDocumentation() {
			return documentation;
		}
		public void setDocumentation(Documentation documentation) {
			this.documentation = documentation;
		}
	public ProcessDetailPanel(){
		setMetaworksContext(new MetaworksContext());
		documentation = new Documentation();
		documentation.init();
	}
	
	public void load(Documentation doc){
		documentation = doc;
		documentation.getMetaworksContext().setHow("process");
		documentation.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object showProcessDetail() throws Exception{
		ProcessDetailPanel processDetailPanel = new ProcessDetailPanel();
		processDetailPanel.setEditorId(this.getEditorId() + "dummy");
		processDetailPanel.getMetaworksContext().setHow("ide");
		processDetailPanel.documentation = this.getDocumentation();
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setPanel(processDetailPanel);
		modalWindow.setWidth(800);
		modalWindow.setHeight(700);
		modalWindow.setTitle("프로세스 상세내용");
		
		return modalWindow;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] save() throws Exception{
		this.getMetaworksContext().setHow(null);
		String tempEditorId = this.getEditorId();
		this.setEditorId(tempEditorId.substring(0, tempEditorId.indexOf("dummy")));
		return new Object[]{new Remover(new ModalWindow(),true) , new Refresh(this)};
	}
}
