package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;
import java.util.HashMap;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Id;
import org.uengine.kernel.Activity;

public class ProcessSummaryPanel implements ContextAware {

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
		
	ArrayList<Activity> detailList;
		public ArrayList<Activity> getDetailList() {
			return detailList;
		}
		public void setDetailList(ArrayList<Activity> detailList) {
			this.detailList = detailList;
		}
	
	public ProcessSummaryPanel(){
		setMetaworksContext(new MetaworksContext());
		detailList = new ArrayList<Activity>();
		
	}
	
	public void addDetailList(Activity activity){
		detailList.add(activity);
	}
}
