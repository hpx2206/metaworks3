package org.uengine.codi.mw3.model;

import org.metaworks.dao.ORMappingListener;
import org.uengine.codi.ITool;

public class GenericWorkItemHandler implements ORMappingListener{

	String serializedTool;

		public String getSerializedTool() {
			return serializedTool;
		}
	
		public void setSerializedTool(String serializedTool) {
			this.serializedTool = serializedTool;
		}

	@Override
	public void onRelation2Object() {

		try {
			setTool( org.uengine.kernel.GlobalContext.deserialize(getSerializedTool()));
			
			if(getTool() instanceof ITool){
				ITool tool = (ITool)getTool();
				tool.onLoad();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onObject2Relation() {
		try {
			setSerializedTool(org.uengine.kernel.GlobalContext.serialize(getTool(), Object.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

	Object tool;

		public Object getTool() {
			return tool;
		}
	
		public void setTool(Object tool) {
			this.tool = tool;
		}
	
	
}
