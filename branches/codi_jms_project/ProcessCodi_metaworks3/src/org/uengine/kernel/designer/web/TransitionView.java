package org.uengine.kernel.designer.web;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.webProcessDesigner.CanvasDTO;
import org.uengine.codi.mw3.webProcessDesigner.ConditionPanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContainer;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.Role;
import org.uengine.kernel.graph.Transition;

public class TransitionView extends CanvasDTO{
	transient Transition transition;
		public Transition getTransition() {
			return transition;
		}
		public void setTransition(Transition transition) {
			this.transition = transition;
		}
	boolean drawByCanvas;
		public boolean isDrawByCanvas() {
			return drawByCanvas;
		}
		public void setDrawByCanvas(boolean drawByCanvas) {
			this.drawByCanvas = drawByCanvas;
		}
		
	Object element;
		public Object getElement() {
			return element;
		}
		public void setElement(Object element) {
			this.element = element;
		}
	
	@AutowiredFromClient
	public ProcessDesignerContainer processDesignerContainer;
		
	@ServiceMethod(callByContent=true, target="popup")
	public ModalWindow gateCondition() throws Exception{
		
		ArrayList<Role>	 roleList = processDesignerContainer.getRolePanel().getRoleList();
        ArrayList<ProcessVariable> variableList = processDesignerContainer.getProcessVariablePanel().getVariableList();
        
		ConditionPanel conditionPanel = new ConditionPanel();
		conditionPanel.setMetaworksContext(new MetaworksContext());
		conditionPanel.getMetaworksContext().setWhen("edit");
		
		conditionPanel.setCondition(transition.getCondition());
		conditionPanel.setRoleList(roleList);
		conditionPanel.setVariableList(variableList);
		conditionPanel.load();
		return new ModalWindow(conditionPanel , 800, 550,  "조건편집" );
	}
}
