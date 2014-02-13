package org.uengine.codi.mw3.ide.editor;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.ResourceNode;

@Face(options={"tabClass"}, values={"org.uengine.codi.mw3.ide.MultiTab"})
public class MultiPageEditor extends Editor {
	
	String[] pageText;
		public String[] getPageText() {
			return pageText;
		}
		public void setPageText(String[] pageText) {
			this.pageText = pageText;
		}
	Object[] pagePanel;
		public Object[] getPagePanel() {
			return pagePanel;
		}
		public void setPagePanel(Object[] pagePanel) {
			this.pagePanel = pagePanel;
		}
		
	boolean callServiceFunction;
		public boolean isCallServiceFunction() {
			return callServiceFunction;
		}
		public void setCallServiceFunction(boolean callServiceFunction) {
			this.callServiceFunction = callServiceFunction;
		}
	String callSelectFunctionName;
		public String getCallSelectFunctionName() {
			return callSelectFunctionName;
		}
		public void setCallSelectFunctionName(String callSelectFunctionName) {
			this.callSelectFunctionName = callSelectFunctionName;
		}
		
	public MultiPageEditor(){
		super();
	}
	public MultiPageEditor(ResourceNode resourceNode){
		super(resourceNode);
	}

	public void createPage(Object page, String pageTitle){
		
		int index = addPage(page);
		addPageText(index, pageTitle);
	}
	
	private int addPage(Object page){
		int index = 0;
		if( pagePanel == null ){
			pagePanel = new Object[1];
		}else{
			index = pagePanel.length ;
			Object[] pagePanel2 = new Object[pagePanel.length+1]; 
			System.arraycopy(pagePanel, 0, pagePanel2, 0, pagePanel.length);
			pagePanel = pagePanel2;
		}
		pagePanel[index] = page;
		
		return index ;
	}
	private void addPageText(int index, String pageTitle){
		if( pageText == null ){
			pageText = new String[1];
		}else{
			String[] pageText2 = new String[pageText.length+1]; 
			System.arraycopy(pageText, 0, pageText2, 0, pageText.length);
			pageText = pageText2;
		}
		pageText[index] = pageTitle;
	}
	
	@ServiceMethod(callByContent=true, except="content", target=ServiceMethodContext.TARGET_NONE)
	public String load() {
		return null;
	}
	
	public Object save() throws Exception{
		return super.save();
	}
}
