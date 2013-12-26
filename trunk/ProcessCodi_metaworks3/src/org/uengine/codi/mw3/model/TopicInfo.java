package org.uengine.codi.mw3.model;

import java.util.Calendar;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.knowledge.ITopicMapping;
import org.uengine.codi.mw3.knowledge.TopicMapping;
import org.uengine.codi.mw3.knowledge.TopicNode;
import org.uengine.codi.mw3.knowledge.TopicTitle;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.kernel.GlobalContext;

public class TopicInfo extends GroupInfo{

	
	public final static String HTML = "html";
	
	
	public TopicInfo(){
	}
	
	public TopicInfo(Session session) throws Exception{
		this.session = session;
		this.setId(session.getLastSelectedItem());
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
	}
	
	
	

	@Override
	public ModalWindow modify() throws Exception {
		TopicTitle topicTitle = new TopicTitle();
		topicTitle.setTopicId(this.getId());
		topicTitle.setTopicTitle(this.getName());
		topicTitle.setLogoFile(new MetaworksFile());
		topicTitle.setMetaworksContext(new MetaworksContext());
		topicTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		topicTitle.session = session;
		return new ModalWindow(topicTitle, MODIFY_POPUP_WIDTH, MODIFY_POPUP_HEIGHT, "$EditTopic");
	}

	@Override
	public Object[] delete() throws Exception {
		TopicNode deletedNode = new TopicNode();
		deletedNode.setId(this.getId());
		deletedNode.copyFrom(deletedNode.databaseMe());
		deletedNode.deleteDatabaseMe();
		TopicPerspective topicPerspective = new TopicPerspective();
		
		//this가 아닌 Node지우기.
		return new Object[]{new Refresh(new InstanceListPanel()), new Remover(deletedNode), new Refresh(topicPerspective)};
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
		this.setSecuopt(wfNode.getSecuopt());
		
		MetaworksFile logoFile = new MetaworksFile();
		logoFile.setUploadedPath(wfNode.getUrl());
		logoFile.setFilename(wfNode.getThumbnail());
		this.setLogoFile(logoFile);
		this.setIsJoinME();
		
	}
	
	
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	@Face(displayName="$ExportHtml")
	public ModalWindow exportHtml() throws Exception {
		TopicTitle topicTitle = new TopicTitle();
		topicTitle.setTopicId(this.getId());
		topicTitle.setTopicTitle(this.getName());
		topicTitle.getMetaworksContext().setHow(HTML);
		topicTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		topicTitle.makeHtml();
				
		return new ModalWindow(topicTitle, MODIFY_POPUP_WIDTH, MODIFY_POPUP_HEIGHT, "$ExportHtml");
	}
}
