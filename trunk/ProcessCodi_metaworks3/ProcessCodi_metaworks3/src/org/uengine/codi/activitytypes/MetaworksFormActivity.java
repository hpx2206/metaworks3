package org.uengine.codi.activitytypes;

import java.util.Map;

import org.uengine.kernel.FormActivity;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.ResultPayload;

public class MetaworksFormActivity extends FormActivity{

	@Override
	public void saveWorkItem(ProcessInstance instance, ResultPayload payload)
			throws Exception {
		
		if(isMappingWhenSave()){
			mappingOut(instance);
		}
		
		super.saveWorkItem(instance, payload);

	}

	@Override
	public String getTool() {
		// TODO Auto-generated method stub
		return "mw3.org.uengine.codi.activitytypes.wih.FormWorkItemHandler";
	}

	@Override
	protected void onSave(ProcessInstance instance, Map parameterMap_)
			throws Exception {
		// TODO Auto-generated method stub
		//super.onSave(instance, parameterMap_);
	}

	@Override
	protected void saveSnapshotHTML(ProcessInstance instance) throws Exception {
		// TODO Auto-generated method stub
		//super.saveSnapshotHTML(instance);
	}

	
	
	
}
