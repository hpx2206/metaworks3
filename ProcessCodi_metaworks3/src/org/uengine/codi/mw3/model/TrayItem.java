package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;

public class TrayItem implements ContextAware {
	
	public TrayItem(){
		setMetaworksContext(new MetaworksContext());
	}
	
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
		
	transient InstanceView instance;
		public InstanceView getInstance() {
			return instance;
		}
		public void setInstance(InstanceView instance) {
			this.instance = instance;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	@ServiceMethod(callByContent=true, inContextMenu=true)
	public void slideDown() throws Exception{
		getMetaworksContext().setHow("down");
		
		Instance instance = new Instance();
		instance.setInstId(new Long(getInstId()));
		
		instanceView.session = session;
		instanceView.load(instance);
		
		setInstance(instanceView);
	}
	
	@ServiceMethod(callByContent=true, inContextMenu=true)
	public void slideUp() throws Exception{
		getMetaworksContext().setHow("up");
	}
		
	@ServiceMethod(callByContent=true, inContextMenu=true)
	public Tray close() throws Exception{
		tray.getTrayItems().remove(this);
		tray.session = session;
		tray.save();
		
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
	
	@AutowiredFromClient
	public Session session;
	
}
