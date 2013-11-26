package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.codi.mw3.processexplorer.ProcessExploreContent;
import org.uengine.codi.mw3.webProcessDesigner.IProcessTopicMapping;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContainer;
import org.uengine.codi.mw3.webProcessDesigner.ProcessTopicMapping;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewer;
import org.uengine.kernel.Activity;
import org.uengine.kernel.designer.web.ActivityView;

public class ActivityInfo extends PerspectiveInfo{
	
	ProcessViewer processViewer;
		public ProcessViewer getProcessViewer() {
			return processViewer;
		}
		public void setProcessViewer(ProcessViewer processViewer) {
			this.processViewer = processViewer;
		}
	Activity activity;	
		public Activity getActivity() {
			return activity;
		}
		public void setActivity(Activity activity) {
			this.activity = activity;
		}
	public ActivityInfo(){
		
	}
	public ActivityInfo(Session session){
		this.session = session;
		this.setProcessViewer(new ProcessViewer());
		this.setId(session.getLastSelectedItem());
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	

	@Override
	public void load() throws Exception {
		this.followersLoad();
		WfNode wfNode = new WfNode();
		wfNode.setId(this.getId());
		
		try {
			wfNode.copyFrom(wfNode.databaseMe());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setName(wfNode.getName());
		
		ProcessTopicMapping ptm = new ProcessTopicMapping();
		ptm.setTopicId(this.getId());
		IProcessTopicMapping findptm = ptm.findByTopicId();
		if(findptm==null){
			throw new Exception("wrong access");
		}
	
		processViewer.setDefId(this.getTitle());
		processViewer.setAlias(findptm.getProcessPath());
		processViewer.setViewType("definitionView");
		processViewer.load();
		
		ProcessDesignerContainer processDesignerContainer = processViewer.getProcessDesignerContainer();
		ArrayList<Activity> activityList = processDesignerContainer.getActivityList();
		for(Activity activity : activityList){
			if( activity.getDescription() != null && findptm.getProcessName().equals(activity.getDescription().getText())){
				activity.getActivityView().setBackgroundColor("#ff0000");
				this.setActivity(activity);
			}
		}
	}
	
	
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="$processinfo.showdetail")
	public ModalWindow action() throws Exception{
		
		ProcessTopicMapping ptm = new ProcessTopicMapping();
		ptm.setTopicId(this.getId());
		IProcessTopicMapping findptm = ptm.findByTopicId();
		if(findptm==null){
			throw new Exception("wrong access");
		}
		
		ProcessExploreContent processExploreContent = new ProcessExploreContent();
		processExploreContent.setAlias(findptm.getProcessPath());
		processExploreContent.setDefId(this.getTitle());
//		processExploreContent.setPath(this.getPath());
		processExploreContent.session = session;
		processExploreContent.load();
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setPanel(processExploreContent);
		modalWindow.setWidth(700);
		modalWindow.setHeight(900);
		modalWindow.setTitle(this.getTitle());
		
		return modalWindow;
		
	}
	
	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="$processinfo.cheklist")
	public ModalWindow showActivityDocument() throws Exception {
		ModalWindow modalWindow = new ModalWindow();
		if( this.getActivity() != null ){
			ActivityView activityView = new ActivityView();
			activityView.setActivity(getActivity());
			modalWindow.setPanel(activityView.formDetail());
		}
		modalWindow.setTitle("액티비티뷰");
		modalWindow.setWidth(600);
		modalWindow.setHeight(500);
		
		return modalWindow;
	}
	
}
