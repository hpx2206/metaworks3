package org.uengine.jms.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.webProcessDesigner.Documentation;

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
		documentation.getMetaworksContext().setHow("process");
		documentation.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	public void load(Documentation doc){
		
		if( !"".equals(doc.getPurpose()) ){
			documentation.setPurpose(doc.getPurpose());
		}
		if( !"".equals(doc.getRange()) ){
			documentation.setRange(doc.getRange());
		}
		if( !"".equals(doc.getReference()) ){
			documentation.setReference(doc.getReference());
		}
		if( !"".equals(doc.getDefine()) ){
			documentation.setDefine(doc.getDefine());
		}
		if( !"".equals(doc.getResponsibility()) ){
			documentation.setResponsibility(doc.getResponsibility());
		}
		if( !"".equals(doc.getEquipment()) ){
			documentation.setEquipment(doc.getEquipment());
		}
		if( !"".equals(doc.getInitialCondition()) ){
			documentation.setInitialCondition(doc.getInitialCondition());
		}
		if( !"".equals(doc.getNotandum()) ){
			documentation.setNotandum(doc.getNotandum());
		}
		if( !"".equals(doc.getStep()) ){
			documentation.setStep(doc.getStep());
		}
		if( !"".equals(doc.getIndicationStandard()) ){
			documentation.setIndicationStandard(doc.getIndicationStandard());
		}
		if( !"".equals(doc.getActivityDetail()) ){
			documentation.setActivityDetail(doc.getActivityDetail());
		}
		
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
