package org.uengine.codi.activitytypes.wih;

import java.io.Serializable;
import java.nio.charset.CodingErrorAction;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.metaworks.MetaworksContext;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.activitytypes.MetaworksFormActivity;
import org.uengine.codi.mw3.admin.FormDefinition;
import org.uengine.codi.mw3.admin.FormField;
import org.uengine.codi.mw3.admin.FormInstance;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.WorkItemHandler;
import org.uengine.contexts.HtmlFormContext;
import org.uengine.kernel.Activity;
import org.uengine.kernel.FormActivity;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.KeyedParameter;
import org.uengine.kernel.ParameterContext;
import org.uengine.kernel.ResultPayload;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;

public class FormWorkItemHandler extends WorkItemHandler{
	
	public FormWorkItemHandler(){} //to be spring bean without argument, this is required.
	
	
	
	
	@Override
	public void load() throws Exception {
		// TODO Auto-generated method stub
		super.load();
		
		MetaworksFormActivity formActivity = (MetaworksFormActivity) humanActivity;
		HtmlFormContext htmlFormContext = (HtmlFormContext) formActivity.getVariableForHtmlFormContext().get(instance, "");
		
		String prodVerId = processManager.getProcessDefinitionProductionVersion(htmlFormContext.getFormDefId());
		
		FormDefinition formDef = (FormDefinition) GlobalContext.deserialize(processManager.getResource(prodVerId));
		FormInstance formInstance = formDef.preview();
		
		
		
//		for(FormField formField: formInstance.getFormFields()){
//			Object fieldValue = htmlFormContext.getFieldValue(formField.getFieldName());
//			formField.setValueString((String) fieldValue);
//		}
		
		setFormInstance(formInstance);
		
	}




	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_VIEW)
	public InstanceViewContent complete() throws Exception {

		Long instanceId = new Long(getInstanceId());
		Long taskId = getTaskId();
		String tracingTag = getTracingTag();
		
		
		instance = processManager.getProcessInstance(instanceId.toString());

		if(humanActivity==null && instanceId!=null && tracingTag!=null){
			humanActivity = (HumanActivity) instance.getProcessDefinition().getActivity(tracingTag);
		}

		MetaworksFormActivity formActivity = (MetaworksFormActivity) humanActivity;
		
		Map valueMap = new HashMap();
		for(FormField field : getFormInstance().getFormFields()){
			if("java.lang.String".equals(field.getType())){
				valueMap.put(field.getFieldName(), field.getValueString());
				
			}
			
			field.setMetaworksContext(new MetaworksContext());
			field.getMetaworksContext().setWhen("view");
		}
		
		
		String filePath = FormActivity.FILE_SYSTEM_DIR + UEngineUtil.getCalendarDir();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS", Locale.KOREA);
		String fileName = filePath + "/" + instance.getInstanceId() + "_" + sdf.format(new Date());
		
		//Form data save to xml 
		formActivity.saveFormVariableXML(instance, valueMap, fileName + ".xml");
		

		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("completed");
		//formActivity.saveWorkItem(instance, payload)
		
		return super.complete();
	}




	FormInstance formInstance;
		public FormInstance getFormInstance() {
			return formInstance;
		}
		public void setFormInstance(FormInstance formInstance) {
			this.formInstance = formInstance;
		} 
		
		
	
			
}
