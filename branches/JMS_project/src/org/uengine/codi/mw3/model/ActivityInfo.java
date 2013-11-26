package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.codi.mw3.processexplorer.ProcessExploreContent;
import org.uengine.codi.mw3.webProcessDesigner.IProcessTopicMapping;
import org.uengine.codi.mw3.webProcessDesigner.ProcessTopicMapping;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewer;
import org.uengine.kernel.designer.web.ActivityView;

public class ActivityInfo extends PerspectiveInfo{
	
	ProcessViewer processViewer;
		public ProcessViewer getProcessViewer() {
			return processViewer;
		}
		public void setProcessViewer(ProcessViewer processViewer) {
			this.processViewer = processViewer;
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
		
		MetaworksFile logoFile = new MetaworksFile();
		logoFile.setUploadedPath(wfNode.getUrl());
		logoFile.setFilename(wfNode.getThumbnail());
		this.setLogoFile(logoFile);
		
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
//		ActivityView activityView = new ActivityView();
//		modalWindow.setPanel(activityView.formDetail());
		modalWindow.setTitle("액티비티뷰");
		modalWindow.setWidth(400);
		modalWindow.setHeight(400);
		
		return modalWindow;
	}
	
}
