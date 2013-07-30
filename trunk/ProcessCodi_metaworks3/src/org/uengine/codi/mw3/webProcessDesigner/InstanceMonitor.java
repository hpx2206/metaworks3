package org.uengine.codi.mw3.webProcessDesigner;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.Activity;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.viewer.DefaultActivityViewer;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;

public class InstanceMonitor {
	static Properties statusColors = new Properties();
	static{
		statusColors.setProperty(Activity.STATUS_READY, 	"#5F6061");
		statusColors.setProperty(Activity.STATUS_COMPLETED, "#888888");
		statusColors.setProperty(Activity.STATUS_RUNNING, 	"#027dbc");
		statusColors.setProperty(Activity.STATUS_FAULT, 	"#ee2020");
		statusColors.setProperty(Activity.STATUS_SKIPPED, 	"#2020aa");
		statusColors.setProperty(Activity.STATUS_SUSPENDED, "#20cccc");
		statusColors.setProperty(Activity.STATUS_TIMEOUT, 	"#aaaa20");
		statusColors.setProperty(Activity.STATUS_RETRYING, 	"#ee2020");
	}
	public static String getStatusColor(String status){		
		return statusColors.getProperty(status);
	}
	
	CanvasDTO cell[];
	public CanvasDTO[] getCell() {
		return cell;
	}
	public void setCell(CanvasDTO[] cell) {
		this.cell = cell;
	}
	String graphString;
	public String getGraphString() {
		return graphString;
	}
	public void setGraphString(String graphString) {
		this.graphString = graphString;
	}
	String instanceId;
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	ProcessInstance instance;
	public ProcessInstance getInstance() {
		return instance;
	}
	public void setInstance(ProcessInstance instance) {
		this.instance = instance;
	}
	ProcessDefinition procDef;
	public ProcessDefinition getProcDef() {
		return procDef;
	}
	public void setProcDef(ProcessDefinition procDef) {
		this.procDef = procDef;
	}
	String tempTracingTag;
	public String getTempTracingTag() {
		return tempTracingTag;
	}
	public void setTempTracingTag(String tempTracingTag) {
		this.tempTracingTag = tempTracingTag;
	}
	public void load(String instanceId) throws Exception {
		this.setInstanceId(instanceId);
		ProcessInstance instance = processManager.getProcessInstance(instanceId);
		ProcessDefinition procDef = instance.getProcessDefinition();
		if( procDef.getExtendedAttributes() == null ) return;
		/*
		ArrayList<CanvasDTO> cellsList = (ArrayList<CanvasDTO>) procDef.getExtendedAttributes().get("cells");
		DefaultActivityViewer dav = new DefaultActivityViewer();
		if( cellsList != null){
			CanvasDTO []cells = new CanvasDTO[cellsList.size()];
			for(int i = 0; i < cellsList.size(); i++){
				cells[i] = (CanvasDTO)cellsList.get(i);
				if( cells[i] != null && cells[i].getJsonString() != null){
					this.setGraphString(cells[i].getJsonString());
				}
				String tracingTag = cells[i].getTracingTag();
				String status = null;
				if( tracingTag != null && cells[i].getShapeType().equals("GEOM")){	// TODO GEOM 으로 체크하는 로직 삭제
					//				Activity activity = procDef.getActivity(tracingTag);
					//				instance.getStatus(activity.getTracingTag());
					status = instance.getStatus(tracingTag);
					cells[i].setInstStatus(status);
					cells[i].setBackgroundColor( getStatusColor(status) );
				}
			}
			// canvas setting
			this.setCell(cells);
		}
		 */

	}

	public void loadProcess(String path) throws Exception {
		System.out.println("path = " + path);

		/*
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			UEngineUtil.copyStream(is, bao);

			ProcessDefinition def = (ProcessDefinition) GlobalContext.deserialize(bao.toString("UTF-8"));
			if( def.getExtendedAttributes() == null ) return;

			ArrayList<CanvasDTO> cellsList = (ArrayList<CanvasDTO>) def.getExtendedAttributes().get("cells");
			DefaultActivityViewer dav = new DefaultActivityViewer();
			if( cellsList != null){
				CanvasDTO []cells = new CanvasDTO[cellsList.size()];
				for(int i = 0; i < cellsList.size(); i++){
					cells[i] = (CanvasDTO)cellsList.get(i);
					if( cells[i] != null && cells[i].getJsonString() != null){
						this.setGraphString(cells[i].getJsonString());
					}
				}
				// canvas setting
				this.setCell(cells);
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(is != null){
				try { is.close(); is = null; } catch (IOException e) {		e.printStackTrace();}
			}
			if(bao != null){
				try { bao.close(); bao = null; } catch (IOException e) {		e.printStackTrace();}
			}
		}
		*/
	}

	@ServiceMethod(callByContent=true, target="popup")
	public Popup showActivityInfo() throws Exception{
		if( instanceId == null ) return null;
		Popup popup = new Popup(400,275);
		TaskInfoPanel taskInfo = new TaskInfoPanel();

		ProcessInstance instance = processManager.getProcessInstance(instanceId);
		ProcessDefinition procDef = instance.getProcessDefinition();

		Activity activity = procDef.getActivity(getTempTracingTag());
		taskInfo.load(getInstanceId(), activity, instance);
		popup.setPanel(taskInfo);
		return popup;
	}
	@AutowiredFromClient
	public Session session;

	@Autowired
	public ProcessManagerRemote processManager;

}
