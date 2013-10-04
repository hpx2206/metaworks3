package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.calendar.ScheduleCalendar;


public class ProcessMapList implements ContextAware {

	public ProcessMapList(){
		setMetaworksContext(new MetaworksContext());
	}
	
	public void load(Session session) throws Exception {
		IProcessMap processMap = ProcessMap.loadList(session);
		processMap.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		setProcessMapList(processMap);		
	}
	
	@AutowiredToClient
	ScheduleCalendar scheduleCalendar;
	
	String title;
		
		public String getTitle() {
			return title;
		}
	
		public void setTitle(String title) {
			this.title = title;
		}

	IProcessMap processMapList;
		public IProcessMap getProcessMapList() {
			return processMapList;
		}	
		public void setProcessMapList(IProcessMap processMapList) {
			this.processMapList = processMapList;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

		
	@AutowiredFromClient
	public Session session;
		
	@ServiceMethod(callByContent=true)
	public void save() throws Exception {
		if(processMapList.size() > 0){
			processMapList.first();
			
			do{
				ProcessMap processMap = new ProcessMap();
				processMap.copyFrom(processMapList);
				processMap.session = session;
				processMap.syncToDatabaseMe();
				processMap.flushDatabaseMe();
				
			} while (processMapList.next());
		}
		
		IProcessMap processMap = ProcessMap.loadList(session);
		processMap.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		setProcessMapList(processMap);		
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP, callByContent=true)
	public ModalWindow append() {
		AddProcessMapPanel addProcessMapPanel = new AddProcessMapPanel();
		addProcessMapPanel.session = session;
		addProcessMapPanel.load();

		ModalWindow modalWindow = new ModalWindow(addProcessMapPanel, 800, 600, "프로세스 맵 등록");
		
		return modalWindow;
	}		
	
	
	Long parentInstanceId;
		public Long getParentInstanceId() {
			return parentInstanceId;
		}
	
		public void setParentInstanceId(Long parentInstanceId) {
			this.parentInstanceId = parentInstanceId;
		}
		
}
