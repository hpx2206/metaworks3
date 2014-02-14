package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.processmanager.ProcessManagerRemote;

public class InstanceTooltip implements ContextAware {

	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;	

	@AutowiredFromClient
	public Locale localeManager;

	Long instanceId;
		@Id
		public Long getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(Long instanceId) {
			this.instanceId = instanceId;
		}
	
	String secuopt;
		public String getSecuopt() {
			return secuopt;
		}
		public void setSecuopt(String secuopt) {
			this.secuopt = secuopt;
		}
		
	String status;
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	Date dueDate;
		public Date getDueDate() {
			return dueDate;
		}
		public void setDueDate(Date dueDate) {
			this.dueDate = dueDate;
		}
		
	EventTrigger[] eventTriggers;
		public EventTrigger[] getEventTriggers() {
			return eventTriggers;
		}
		public void setEventTriggers(EventTrigger[] eventTriggers) {
			this.eventTriggers = eventTriggers;
		}
		
	public InstanceTooltip() throws Exception{
		setMetaworksContext(new MetaworksContext());
	}
	
	public void load(IInstance instance) throws Exception{
		this.setInstanceId(instance.getInstId());
		this.setStatus(instance.getStatus());
		this.setSecuopt(instance.getSecuopt());
		this.setDueDate(instance.getDueDate());
		
		/*
		if(instance.getDefVerId()!=null){ //process exists!
//			EventHandler[] eventHandlers = processManager.getEventHandlersInAction(instance.getInstId().toString());
			ProcessInstance processInstance = processManager.getProcessInstance(instance.getInstId().toString());
			Vector mls = processInstance.getMessageListeners("event");

			ProcessDefinition definition = processInstance.getProcessDefinition();
			EventTrigger[] eventTriggers = new EventTrigger[mls.size()];
			if(mls!=null){
				for(int i=0; i<mls.size(); i++){
					EventActivity scopeAct = (EventActivity)definition.getActivity((String)mls.get(i));
					
					EventTrigger eventTrigger = new EventTrigger();
					eventTrigger.setInstanceId(instance.getInstId().toString());
					eventTrigger.setDisplayName(scopeAct.getDescription().getText());
					eventTrigger.setEventName(scopeAct.getName().getText());
					eventTriggers[i] = eventTrigger;
					
				}
			}
			
			instanceTooltip.setEventTriggers(eventTriggers);
		}
		*/
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow monitor() throws Exception{
		Instance instance = new Instance();
		instance.processManager = processManager;
		instance.session = session;
		instance.localeManager = localeManager;
		instance.setInstId(this.getInstanceId());
		
		return instance.monitor();
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP, loader="auto")
	public Popup schedule() throws Exception{
		
		Instance instance = new Instance();
		instance.processManager = processManager;
		instance.session = session;
		instance.localeManager = localeManager;
		instance.setInstId(this.getInstanceId());
		
		return instance.schedule();
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP, loader="auto")
	public Popup newSubInstance() throws Exception{
		Instance instance = new Instance();
		instance.processManager = processManager;
		instance.session = session;
		instance.localeManager = localeManager;
		instance.setInstId(this.getInstanceId());
		
		return instance.newSubInstance();
	}

	@Face(displayName="$Remove")
	@ServiceMethod(callByContent=true, needToConfirm=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] remove() throws Exception{
		Instance instance = new Instance();
		instance.processManager = processManager;
		instance.session = session;
		instance.localeManager = localeManager;
		instance.setInstId(this.getInstanceId());
		
		return instance.remove();
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
	public void toggleSecurityConversation() throws Exception{
		Instance instance = new Instance();
		instance.processManager = processManager;
		instance.session = session;
		instance.localeManager = localeManager;
		instance.setInstId(this.getInstanceId());		
		instance.setSecuopt(this.getSecuopt());
		instance.toggleSecurityConversation();
		
		this.setSecuopt(instance.getSecuopt());
	}
	
	@Available(condition="(dueDate)")
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
	public void complete() throws Exception{
		Instance instance = new Instance();
		instance.processManager = processManager;
		instance.session = session;
		instance.localeManager = localeManager;		
		instance.setInstId(this.getInstanceId());
		instance.setStatus(this.getStatus());
		instance.complete();
		
		this.setStatus(instance.getStatus());
	}
}
