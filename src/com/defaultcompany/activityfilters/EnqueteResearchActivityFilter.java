package com.defaultcompany.activityfilters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.*;

import net.sf.json.JSONArray;

import org.uengine.contexts.HtmlFormContext;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ActivityFilter;
import org.uengine.kernel.EJBProcessInstance;
import org.uengine.kernel.FormActivity;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessDefinitionFactory;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.RoleMapping;
import org.uengine.persistence.dao.WorkListDAO;
import org.uengine.processdesigner.SimulatorProcessInstance;

/**
 * @author Jinyoung Jang
 */
public class EnqueteResearchActivityFilter implements ActivityFilter, Serializable{

	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;

	public final static String FILE_SYSTEM_DIR = GlobalContext.getPropertyString("filesystem.path", ProcessDefinitionFactory.DEFINITION_ROOT);

	public void afterExecute(Activity activity, final ProcessInstance instance)
		throws Exception {
 
		}
	
	public void afterComplete(Activity activity, ProcessInstance instance) throws Exception {
				 
		try{
			if(activity instanceof FormActivity){
				FormActivity formActivity = (FormActivity)activity;
				WorkListDAO wlDAO = (WorkListDAO) instance.getProcessTransactionContext().findSynchronizedDAO("BPM_WORKLIST", "TASKID", formActivity.getTaskIds(instance)[0], WorkListDAO.class);
				
				ProcessVariable formVariable = formActivity.getVariableForHtmlFormContext();
				HtmlFormContext formContext = (HtmlFormContext) formVariable.get(instance, "");
	
				InputStream is = formContext.openValueXMLStream();
				HashMap valueMap = (HashMap)GlobalContext.deserialize(is,HashMap.class);
				
				String selectitem = (String)valueMap.get("selectitem");
				
				String rdoCheckedItem = (String)valueMap.get("rdo_item");
				
				String[] selectArray = selectitem.split(";");

				LinkedHashMap<String, String> rValues = new LinkedHashMap<String, String>();
				for (int i=1; i <= selectArray.length; i++) {
					if (rdoCheckedItem.equals(String.valueOf(i))) {
						rValues.put(String.valueOf(i), "1");
						rValues.put(i + "-name", (String)valueMap.get("text_item_"+i));
					} else {
						rValues.put(String.valueOf(i), "0");
						rValues.put(i + "-name", (String)valueMap.get("text_item_"+i));
					}
				}
				
				String rootInstanceId = instance.getRootProcessInstanceId();
				
				String DEFINITION_ROOT = GlobalContext.getPropertyString(
						"server.definition.path",
						"./uengine/definition/"
					);
				
				String path = "C:\\uengine_standalone\\was\\bin\\uengine\\definition\\enquete\\" + rootInstanceId + ".dat";
				
				try {
					InputStream isx = new FileInputStream(path);
			        HashMap<String, String> lhm = (HashMap<String, String>)GlobalContext.deserialize(isx, HashMap.class);

			        for (int i=1; i <= 10; i++) {
			        	if (rValues.containsKey(String.valueOf(i))) {
			        		int before = Integer.parseInt(lhm.get(String.valueOf(i)));
			        		int after  = Integer.parseInt(rValues.get(String.valueOf(i)));
			        		
			        		rValues.put(String.valueOf(i), String.valueOf(before + after));
			        	}
			        }
			        
			        FileOutputStream fos = new FileOutputStream(path);
					GlobalContext.serialize(rValues, fos, LinkedHashMap.class);
					fos.close();
				} catch(FileNotFoundException fnfe) {
					FileOutputStream fos = new FileOutputStream(path);
					GlobalContext.serialize(rValues, fos, LinkedHashMap.class);
					fos.close();
				}
			}
			
		}catch(Exception e){
			   	
		}
 
	}

	public void beforeExecute(Activity activity, ProcessInstance instance)
		throws Exception {
	}

	public void onDeploy(ProcessDefinition definition) throws Exception {
	}

	public void onPropertyChange(Activity activity, ProcessInstance instance, String propertyName, Object changedValue) throws Exception {
 
	}
}
