package org.uengine.codi.mw3.knowledge;

import java.net.URL;
import java.util.Locale;

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
import org.metaworks.component.SelectBox;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
	  ejsPathMappingByContext=	{
				"{how: 'html', face: 'dwr/metaworks/org/uengine/codi/mw3/knowledge/TopicTitle.ejs'}"
}, options={"fieldOrder"}, values={"selectBox,regionSecuopt,url"})
public class RegionTitle  implements ContextAware{
	public RegionTitle(){
		setMetaworksContext(new MetaworksContext());
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	String regionId;
		@Hidden
		public String getRegionId() {
			return regionId;
		}
		public void setRegionId(String regionId) {
			this.regionId = regionId;
		}
			
	String regionTitle;
		@Face(displayName="$RegionTitle")
		@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
		public String getRegionTitle() {
			return regionTitle;
		}
		public void setRegionTitle(String regionTitle) {
			this.regionTitle = regionTitle;
		}

	SelectBox selectBox;
		@Face(displayName="$RegionSelect")
		@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
		public SelectBox getSelectBox() {
			return selectBox;
		}
		public void setSelectBox(SelectBox selectBox) {
			this.selectBox = selectBox;
		}
		
	boolean regionSecuopt;				
		@Face(displayName="$topicSecuopt")
		@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
		public boolean isRegionSecuopt() {
			return regionSecuopt;
		}
		public void setRegionSecuopt(boolean regionSecuopt) {
			this.regionSecuopt = regionSecuopt;
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
	
			String defaultUrl = protocol + "://" + host + ":" + port + contextOnly + "/portlet_instanceList.html?type=Topic&id=" + this.getRegionId();
			String embeddedHtml = "<div style='padding:15px;border:1px solid #D7D7D7; font-size:14px; font-weight:bold;margin-bottom:10px;'>&lt;iframe id=\"portlet\" src=\"" + defaultUrl + "\" style=\"width: 500px; height: 500px; border-width:1px; border-color:red; border-style:solid;\"></iframe></div>";
			
			this.setEmbeddedHtml(embeddedHtml);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void saveMe() throws Exception {
		WfNode wfNode = new WfNode();
		
		if(MetaworksContext.WHEN_NEW.equals(this.getMetaworksContext().getWhen())){
			// 기존과 다름. 이거는 셀렉트 된 key 값을 이름으로 가져야함.
			wfNode.setName(this.getSelectBox().getSelectedText());
			wfNode.setType("region");
			wfNode.setSecuopt(regionSecuopt ? "1" : "0");
			wfNode.setParentId(session.getCompany().getComCode());	
			wfNode.setAuthorId(session.getUser().getUserId());		
			wfNode.setCompanyId(session.getCompany().getComCode());
			// 국가 코드 임시로 넣음
			wfNode.setVisType(this.getSelectBox().getSelected());
			wfNode.createMe();
			
			TopicMapping tm = new TopicMapping();
			tm.setTopicId(wfNode.getId());
			tm.setUserId(session.getUser().getUserId());
			tm.setUserName(session.getUser().getName());
			tm.getMetaworksContext().setWhen(this.getMetaworksContext().getWhen());
			
			tm.saveMe();
			
			this.setRegionId(wfNode.getId());
		}else{
			wfNode.setId(this.getRegionId());
			
			wfNode.copyFrom(wfNode.databaseMe());
			
			wfNode.setName(this.getSelectBox().getSelectedText());
			wfNode.saveMe();
		}
	}
	
	@Face(displayName="$Create")
	@Available(when={MetaworksContext.WHEN_NEW})
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] save() throws Exception{
		if("".equals(this.getSelectBox().getSelectedText())){
			throw new Exception("지역이름을 입력해주세요");
		}
		
		IRegionNode regionNodeList = RegionNode.moreView(session);
		while(regionNodeList.next()){
			if(regionNodeList.getName().equals(this.getSelectBox().getSelectedText())){
				throw new Exception("지역이름이 중복 됩니다.");
			}
		}
		
		this.saveMe();
		
		RegionNode regionNode = new RegionNode();
		regionNode.setId(this.getRegionId());
		regionNode.setName(this.getSelectBox().getSelectedText());
		regionNode.setType("region");
		
		this.makeHtml();
		
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		this.getMetaworksContext().setHow("html");	
		
		return new Object[]{new ToAppend(new RegionPanel(), regionNode), new Refresh(this)};
	}
	
	@Face(displayName="$Save")
	@Available(when={MetaworksContext.WHEN_EDIT})
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] modify() throws Exception{
		this.saveMe();
		
		RegionNode regionNode = new RegionNode();
		regionNode.setId(this.getRegionId());
		regionNode.setName(this.getSelectBox().getSelectedText());

		return new Object[]{new Refresh(regionNode), new Remover(new ModalWindow())};		
	}
	
	@Face(displayName="$Close")
	@Available(how={"html"})
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object close() {
		return new Remover(new ModalWindow());
	}
	
	@AutowiredFromClient
	transient public Session session;
	
	public void setRegionCode() {
		if(selectBox == null) 
			selectBox = new SelectBox();
		
		String[] regions = Locale.getISOCountries();
		for(String countryCode : regions) {
			Locale locale = new Locale("", countryCode);
			selectBox.add("("+ locale.getCountry() + ")" + " " + locale.getDisplayCountry(), locale.getCountry());
		}
	}
}
