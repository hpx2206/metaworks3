package org.uengine.codi.mw3.project;

import java.io.Serializable;

import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.NonSavable;
import org.metaworks.dao.Database;
import org.uengine.processmanager.ProcessManagerFactoryBean;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.knowledge.IWfNode;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.codi.mw3.model.IInstance;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.contexts.ComplexType;


public class SVNEdit implements ITool {
	
	String svnUrl;
		public String getSvnUrl() {
			return svnUrl;
		}	
		public void setSvnUrl(String svnUrl) {
			this.svnUrl = svnUrl;
		}
	
	String instId;
		@Hidden
		public String getInstId() {
			return instId;
		}
		public void setInstId(String instId) {
			this.instId = instId;
		}
		
	String mainInstId;
		@Hidden
		@NonSavable
		public String getMainInstId() {
			return mainInstId;
		}
		public void setMainInstId(String mainInstId) {
			this.mainInstId = mainInstId;
		}
		
	
	@Override
	public void onLoad() throws Exception {
		// TODO Auto-generated method stub		
		
		ProcessManagerFactoryBean factoryBean = new ProcessManagerFactoryBean();
		ProcessManagerRemote processManager = factoryBean.getProcessManager();
		
		if(this.svnUrl != null && this.mainInstId != null) {
			
			SVNSetting svn = new SVNSetting();
			svn.setSvnUrl(this.getSvnUrl());
			
			processManager.setProcessVariable(this.getMainInstId(),"", "SVNSetting", svn);
			
		}else{
			IInstance instance = (IInstance)Database.sql(IInstance.class,"select * from bpm_procinst where instid = ?instid");
			instance.set("instid", this.getInstId());
			instance.select();

			if (instance.size() > 0) {
				
				instance.next();
				
				Instance inst = new Instance();
				inst.copyFrom(instance);
				
				String topicId = inst.getTopicId();
				
				
				IWfNode wfNode = (IWfNode)Database.sql(IWfNode.class, 
						"select * from bpm_knol where id = ?id");
				wfNode.set("id", topicId);
				wfNode.select();
				
				if(wfNode.size() > 0) {
					wfNode.next();
					
					WfNode wf = new WfNode(); 
					wf.copyFrom(wfNode);
					
					this.setMainInstId(String.valueOf(wf.getLinkedInstId()));
							
					Serializable value = processManager.getProcessVariable(this.getMainInstId(), "", "SVNSetting");
					
					if (value instanceof ComplexType) {
						return;
						
					}else{
						SVNSetting svn = (SVNSetting)value;
						
						this.svnUrl = svn.getSvnUrl();
					}
				}
			}
		}
 		
	}

	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
	}
	
//	@Autowired
//	public ProcessManagerRemote processManager;
}
