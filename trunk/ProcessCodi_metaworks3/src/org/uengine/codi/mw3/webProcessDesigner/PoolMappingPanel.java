package org.uengine.codi.mw3.webProcessDesigner;


import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.uengine.contexts.MappingContext;
import org.uengine.kernel.RestWebServiceActivity;
import org.uengine.kernel.graph.PoolTransition;

@Face(ejsPath="genericfaces/ActivityFace.ejs", options={"fieldOrder"},values={"mappingName,mappingContext"})
public class PoolMappingPanel implements ContextAware{
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	String mappingId;
	@Hidden
		public String getMappingId() {
			return mappingId;
		}
		public void setMappingId(String mappingId) {
			this.mappingId = mappingId;
		}
	String mappingName;
	@Face(displayName="매핑이름")
		public String getMappingName() {
			return mappingName;
		}
		public void setMappingName(String mappingName) {
			this.mappingName = mappingName;
		}
	PoolTransition poolTransition;
	@Hidden
		public PoolTransition getPoolTransition() {
			return poolTransition;
		}
		public void setPoolTransition(PoolTransition poolTransition) {
			this.poolTransition = poolTransition;
		}	
	MappingContext mappingContext;
	@Face(displayName="$dataMapping")
		public MappingContext getMappingContext() {
			return mappingContext;
		}
		public void setMappingContext(MappingContext mappingContext) {
			this.mappingContext = mappingContext;
		}
	transient String parentEditorId;
	@Hidden
		public String getParentEditorId() {
			return parentEditorId;
		}
		public void setParentEditorId(String parentEditorId) {
			this.parentEditorId = parentEditorId;
		}	
		
	public PoolMappingPanel(){
		this.setMetaworksContext(new MetaworksContext());
	}
	public void load() throws Exception{
		Tree leftTree;
		Tree rightTree;
		if( poolTransition.getSourceActivity() instanceof RestWebServiceActivity){
			RestWebServiceActivity activity = (RestWebServiceActivity)poolTransition.getSourceActivity();
			leftTree = new PoolMappingTree();
			((PoolMappingTree) leftTree).setActivity(activity);
		}else{
			leftTree = new MappingTree();
			((MappingTree) leftTree).setParentEditorId(this.getParentEditorId());
		}
		leftTree.setId(TreeNode.ALIGN_LEFT);
		leftTree.setAlign(TreeNode.ALIGN_LEFT);
		
		if( poolTransition.getTargetActivity() instanceof RestWebServiceActivity){
			RestWebServiceActivity activity = (RestWebServiceActivity)poolTransition.getTargetActivity();
			rightTree = new PoolMappingTree();
			((PoolMappingTree) rightTree).setActivity(activity);
		}else{
			rightTree = new MappingTree();
			((MappingTree) rightTree).setParentEditorId(this.getParentEditorId());
		}
		rightTree.setId(TreeNode.ALIGN_RIGHT);
		rightTree.setAlign(TreeNode.ALIGN_RIGHT);
		
		mappingContext = poolTransition.getMappingContext();
		if( mappingContext == null ){
			mappingContext = new MappingContext();
			MappingCanvas canvas = new MappingCanvas();
			canvas.setCanvasId("mappingCanvas");
			canvas.setLeftTreeId(leftTree.getId());
			canvas.setRightTreeId(rightTree.getId());
			mappingContext.setMappingCanvas(canvas);
		}
		mappingContext.setMappingTreeLeft(leftTree);
		mappingContext.setMappingTreeRight(rightTree);
		
	}
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] apply(){
		
		poolTransition.setTransitionName(mappingName);
		poolTransition.setMappingContext(mappingContext);
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setId(this.getMappingId());
		
		return new Object[]{new ApplyProperties(this.getMappingId() , poolTransition), new Remover(modalWindow, true) };
	}

	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] cancel(){
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setId(this.getMappingId());
		return new Object[]{new Remover(modalWindow , true)};
		
	}
}
