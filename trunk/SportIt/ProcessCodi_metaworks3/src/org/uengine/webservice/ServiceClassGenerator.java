package org.uengine.webservice;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import org.uengine.kernel.Activity;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ReceiveMessageEventActivity;

public class ServiceClassGenerator {
	
	public boolean isWebServiceConvert(ProcessDefinition proc) throws Exception{
		ArrayList<Activity> activityList = this.getPossibleActivities(proc);
		if( activityList.size() > 0 ){
			return true;
		}else{
			return false;
		}
	}
	
	private ArrayList<Activity> getPossibleActivities(ProcessDefinition proc) throws Exception{
		ArrayList<Activity> activityList = new ArrayList<Activity>();
		for(Activity childActivity: proc.getChildActivities()){
			// ReceiveMessageEventActivity 가 있을때만 true
			if(childActivity instanceof ReceiveMessageEventActivity 
					&& (childActivity.getIncomingTransitions() == null || childActivity.getIncomingTransitions().size() == 0)){
				// incomming 을 체크를 하였는데.. 중간에 있는 엑티비티도 포함을 시켜야 할지 고민임
				activityList.add(childActivity);
			}
		}
		return activityList;
	}
			
	
	public String generateSource(ProcessDefinition proc, Map options) throws Exception{	
		
		String procName;
		
		if( options == null ){
			options = new Properties();
		}
		
		if(options.containsKey("className"))
			procName = options.get("className").toString();
		else{
			procName = proc.getName().getText().replace(' ', '_');
		}
		
		this.makeCallClass(proc);
		
		return procName;
	}
	
	private String makeCallClass(ProcessDefinition proc) throws Exception{	
		StringBuffer sb = new StringBuffer();
		StringBuffer importBuffer 		= new StringBuffer();		// import 생성 버퍼
		StringBuffer methodBuffer 		= new StringBuffer();		// 메서드 생성 버퍼
		StringBuffer constructorBuffer 	= new StringBuffer();		// 생성자 생성 버퍼 
		
		importBuffer.append("import javax.ws.rs.Consumes; \n");
		importBuffer.append("import javax.ws.rs.DefaultValue; \n");
		importBuffer.append("import javax.ws.rs.FormParam; \n");
		importBuffer.append("import javax.ws.rs.GET; \n");
		importBuffer.append("import javax.ws.rs.POST; \n");
		importBuffer.append("import javax.ws.rs.Path; \n");
		importBuffer.append("import javax.ws.rs.PathParam; \n");
		importBuffer.append("import javax.ws.rs.Produces; \n");
		importBuffer.append("import javax.ws.rs.core.Context; \n");
		importBuffer.append("import javax.ws.rs.core.MediaType; \n");

		ArrayList<Activity> activityList = getPossibleActivities(proc);
		for(Activity messageActivity : activityList){
			
		}
		
		return sb.toString();
	}
	private String makeActivator(ProcessDefinition proc) throws Exception{	
		StringBuffer sb = new StringBuffer();
		
		return sb.toString();
	}

}
