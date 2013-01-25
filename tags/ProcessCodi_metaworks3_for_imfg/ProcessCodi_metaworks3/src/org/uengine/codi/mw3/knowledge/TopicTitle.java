package org.uengine.codi.mw3.knowledge;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.example.ide.SourceCode;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
	  ejsPathMappingByContext=	{
				"{how: 'html', face: 'dwr/metaworks/org/uengine/codi/mw3/knowledge/TopicTitle.ejs'}"
})
public class TopicTitle  implements ContextAware{
	public TopicTitle(){
		setMetaworksContext(new MetaworksContext());
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	String topicId;
		@Hidden
		public String getTopicId() {
			return topicId;
		}
		public void setTopicId(String topicId) {
			this.topicId = topicId;
		}

	String topicTitle;
		@Face(displayName="$topicTitle")
		@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
		public String getTopicTitle() {
			return topicTitle;
		}
		public void setTopicTitle(String topicTitle) {
			this.topicTitle = topicTitle;
		}
	boolean topicSecuopt;				
		@Face(displayName="$topicSecuopt")
		@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
		public boolean isTopicSecuopt() {
			return topicSecuopt;
		}
		public void setTopicSecuopt(boolean topicSecuopt) {
			this.topicSecuopt = topicSecuopt;
		}
	String url;
		@Face(displayName="$topicUrl")
		@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
	String embeddedHtml;
		@Face(displayName="$topicEmbeddedHtml")
		@Available(how={"html"})
		public String getEmbeddedHtml() {
			return embeddedHtml;
		}
		public void setEmbeddedHtml(String embeddedHtml) {
			this.embeddedHtml = embeddedHtml;
		}
	
	public void makeHtml() {
		try{
			HttpServletRequest request = TransactionContext.getThreadLocalInstance().getRequest();
			
	        String url = request.getRequestURL().toString();
	        String codebase = url.substring( 0, url.lastIndexOf( "/" ) );
	        URL urlURL = new java.net.URL(codebase);
	        
	       	String host = urlURL.getHost();
	       	int port = urlURL.getPort();
	       	String path = urlURL.getPath();
	       	String contextOnly = path.substring(0, path.substring(1).indexOf("/")+1);
			String protocol = urlURL.getProtocol();
	
			String defaultUrl = protocol + "://" + host + ":" + port + contextOnly + "/portlet_instanceList.html?type=Topic&id=" + this.getTopicId();
			String embeddedHtml = "<div style='padding:15px;border:1px solid #D7D7D7; font-size:14px; font-weight:bold;margin-bottom:10px;'>&lt;iframe id=\"portlet\" src=\"" + defaultUrl + "\" style=\"width: 500px; height: 500px; border-width:1px; border-color:red; border-style:solid;\"></iframe></div>";
			
			this.setEmbeddedHtml(embeddedHtml);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void saveMe() throws Exception {
		WfNode wfNode = new WfNode();
		
		if(MetaworksContext.WHEN_NEW.equals(this.getMetaworksContext().getWhen())){
			wfNode.setName(this.getTopicTitle());
			wfNode.setType("topic");
			wfNode.setSecuopt(topicSecuopt ? "1" : "0");
			wfNode.setParentId(session.getCompany().getComCode());	
			wfNode.setAuthorId(session.getUser().getUserId());		
			wfNode.setCompanyId(session.getCompany().getComCode());
			wfNode.createMe();
			
			TopicMapping tm = new TopicMapping();
			tm.setTopicId(wfNode.getId());
			tm.setUserId(session.getUser().getUserId());
			tm.setUserName(session.getUser().getName());
			tm.getMetaworksContext().setWhen(this.getMetaworksContext().getWhen());
			
			tm.saveMe();
			
			this.setTopicId(wfNode.getId());
		}else{
			wfNode.setId(this.getTopicId());
			
			wfNode.copyFrom(wfNode.databaseMe());
			
			wfNode.setName(this.getTopicTitle());
			wfNode.saveMe();
		}
	}
	
	@Face(displayName="$Create")
	@Available(when={MetaworksContext.WHEN_NEW})
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] save() throws Exception{
		this.saveMe();
		
		TopicNode topicNode = new TopicNode();
		topicNode.setId(this.getTopicId());
		topicNode.setName(this.getTopicTitle());
		
		this.makeHtml();
		
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		this.getMetaworksContext().setHow("html");	
		
		return new Object[]{new ToAppend(new TopicPanel(), topicNode), new Refresh(this)};
	}
	
	@Face(displayName="$Save")
	@Available(when={MetaworksContext.WHEN_EDIT})
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] modify() throws Exception{
		this.saveMe();
		
		TopicNode topicNode = new TopicNode();
		topicNode.setId(this.getTopicId());
		topicNode.setName(this.getTopicTitle());

		return new Object[]{new Refresh(topicNode), new Remover(new ModalWindow())};		
	}
	
	@Face(displayName="$Close")
	@Available(how={"html"})
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object close() {
		return new Remover(new ModalWindow());
	}
	
	@AutowiredFromClient
	transient public Session session;
	
}
