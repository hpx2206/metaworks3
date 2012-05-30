package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;

public class TrayItem {
	String title;
	@Name
		public String getTitle() {
			return title;
		}
	
		public void setTitle(String title) {
			this.title = title;
		}
	
	String instId;
	@Id
		public String getInstId() {
			return instId;
		}
	
		public void setInstId(String instId) {
			this.instId = instId;
		}
		
	InstanceView instance;
		public InstanceView getInstance() {
			return instance;
		}
		public void setInstance(InstanceView instance) {
			this.instance = instance;
		}

		//@ServiceMethod(target="popup", callByContent=true, inContextMenu=true)
		@ServiceMethod(callByContent=true, inContextMenu=true)
	public void open() throws Exception{
		Instance instance = new Instance();
		instance.setInstId(new Long(getInstId()));

		instanceView.load(instance);
		
		setInstance(instanceView);
	}
	
	@ServiceMethod(callByContent=true, inContextMenu=true)
	public Tray close() throws Exception{
		tray.getTrayItems().remove(this);
		
		return tray;
	}
	
	@ServiceMethod(callByContent=true, inContextMenu=true)
	public ContentWindow maximize() throws Exception{
		
		Instance instance = new Instance();
		instance.setInstId(new Long(getInstId()));
		instanceViewContent.load(instance);
		
		return instanceViewContent;
	}

	@AutowiredFromClient //TODO: not good for performance. some except options should work for the autowired fields.
	public Tray tray;
	
	@Autowired
	public InstanceView instanceView;
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
	
}
