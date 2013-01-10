package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.Refresh;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.component.SelectBox;
import org.metaworks.dao.Database;
import org.metaworks.dao.ORMappingListener;
import org.metaworks.website.MetaworksFile;
import org.uengine.util.UEngineUtil;

public class WorkItemVersionChooser implements ORMappingListener{
	
	
	public WorkItemVersionChooser(){
		
	}
	
	
	
	
	Long instId;
		
		public Long getInstId() {
			return instId;
		}
	
	
		public void setInstId(Long instId) {
			this.instId = instId;
		}



	Long taskId;
	
		public Long getTaskId() {
			return taskId;
		}
	
	
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}




	Long grpTaskId;
	
	
	
		public Long getGrpTaskId() {
			return grpTaskId;
		}
	
	
		public void setGrpTaskId(Long grpTaskId) {
			this.grpTaskId = grpTaskId;
		}
	



	ArrayList<Long> taskIdsPerVersion;
		
			
			
		public ArrayList<Long> getTaskIdsPerVersion() {
			return taskIdsPerVersion;
		}
	
	
		public void setTaskIdsPerVersion(ArrayList<Long> taskIdsPerVersion) {
			this.taskIdsPerVersion = taskIdsPerVersion;
		}




	SelectBox versionSelector;


	
		
		
			public SelectBox getVersionSelector() {
				return versionSelector;
			}
		
		
			public void setVersionSelector(SelectBox versionSelector) {
				this.versionSelector = versionSelector;
			}



	String selectedVersion;
	

	public String getSelectedVersion() {
		return selectedVersion;
	}


	public void setSelectedVersion(String selectedVersion) {
		this.selectedVersion = selectedVersion;
	}




	@ServiceMethod(callByContent=true)
	public IWorkItem choose() throws Exception{
		System.out.println(getVersionSelector().getSelected());
		
		int i=0;
		for(String value : getVersionSelector().getOptionValues()){
			if(value.equals(getVersionSelector().getSelected())){
				WorkItem workItem = new WorkItem();
				workItem.setTaskId(getTaskIdsPerVersion().get(i));
				
				return workItem.databaseMe();
				
			}
			i++;
		}
		
		
		return null;

	}

	@Override
	public void onRelation2Object() {
	
		taskIdsPerVersion = new ArrayList<Long>();
		
		IWorkItem workitem;
		try {
			workitem = (IWorkItem) Database.sql(IWorkItem.class, "select * from bpm_worklist where instid=?instId and grptaskid=?grpTaskId");
			
			workitem.set("instId", getInstId());
			workitem.set("grpTaskId", getGrpTaskId());
			
			workitem.select();
			
			versionSelector = new SelectBox();
			
			if(workitem.size() > 0){
				while(workitem.next()){
					String version = String.valueOf(workitem.getMajorVer()+ "." + workitem.getMinorVer());
					versionSelector.add(version, version);
					taskIdsPerVersion.add(workitem.getTaskId());	
					
					
					if(workitem.getTaskId().equals(getTaskId())){
						versionSelector.setSelectedValue(version);
						
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		

		
	
		
	}



	@Override
	public void onObject2Relation() {
		// TODO Auto-generated method stub
		
	}
	
}
