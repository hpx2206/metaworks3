package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.ValueChain;

public class MajorProcessListPanel {
	
	@AutowiredFromClient
	public Session session;
	
	@AutowiredFromClient
	public ValueChainNavigatorPanel valueChainNavigatorPanel;
	
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	String viewType;
		public String getViewType() {
			return viewType;
		}
		public void setViewType(String viewType) {
			this.viewType = viewType;
		}
		
	ArrayList<ProcessDefinitionHolder> majorProcessItems;
		public ArrayList<ProcessDefinitionHolder> getMajorProcessItems() {
			return majorProcessItems;
		}
		public void setMajorProcessItems(ArrayList<ProcessDefinitionHolder> majorProcessItems) {
			this.majorProcessItems = majorProcessItems;
		}
		
		public void addMajorProcessItem(ProcessDefinitionHolder item){
			this.majorProcessItems.add(item);
		}
	
	@AutowiredFromClient
	ValueChain valueChain;
		public ValueChain getValueChain() {
			return valueChain;
		}
		public void setValueChain(ValueChain valueChain) {
			this.valueChain = valueChain;
		}
	
	public MajorProcessListPanel() {
		
	}
		
	public void load(){
		if(valueChain != null && valueChain.getMajorProcessItems() != null){
			majorProcessItems = (ArrayList<ProcessDefinitionHolder>) valueChain.getMajorProcessItems().clone();
		}
	}
	
	@ServiceMethod(callByContent=true)
	public Object[] save(){
		valueChain.setMajorProcessItems(majorProcessItems);
		return null;
	}
	
}
