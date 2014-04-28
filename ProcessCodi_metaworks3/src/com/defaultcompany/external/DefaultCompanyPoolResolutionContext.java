package com.defaultcompany.external;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.marketplace.AppMapping;
import org.uengine.codi.mw3.model.AllAppList;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.PoolResolutionContext;
import org.uengine.kernel.designer.web.DynamicDrawGeom;
import org.uengine.webservice.JaxRSWebServiceConnector;
import org.uengine.webservice.WebServiceConnector;

public class DefaultCompanyPoolResolutionContext extends PoolResolutionContext implements ContextAware {

	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;

	@AutowiredFromClient
	transient public Session session;

	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	public DefaultCompanyPoolResolutionContext(){
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		String webServiceApiType = GlobalContext.getPropertyString("webServiceApiType", WebServiceConnector.WEBSERVICE_API_JAXRS);
		if( WebServiceConnector.WEBSERVICE_API_JAXRS.equals(webServiceApiType)){
			webServiceConnector = new JaxRSWebServiceConnector();
		}else{
			// default
			webServiceConnector = new JaxRSWebServiceConnector();
		}
	}
	
	String linkedId;
		public String getLinkedId() {
			return linkedId;
		}
		public void setLinkedId(String linkedId) {
			this.linkedId = linkedId;
		}

	String displayName;
		public String getDisplayName() {
			return displayName;
		}
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
	String webserviceUrl;
		public String getWebserviceUrl() {
			return webserviceUrl;
		}
		public void setWebserviceUrl(String webserviceUrl) {
			this.webserviceUrl = webserviceUrl;
		}

	WebServiceConnector webServiceConnector;
		public WebServiceConnector getWebServiceConnector() {
			return webServiceConnector;
		}
		public void setWebServiceConnector(WebServiceConnector webServiceConnector) {
			this.webServiceConnector = webServiceConnector;
		}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	public Object openPicker() throws Exception{
		
		AllAppList allAppList = new AllAppList();
		allAppList.session = session;
		allAppList.load();
		
		ArrayList<AppMapping> myAppsList = allAppList.getMyAppsList();
		
		for(AppMapping app : myAppsList ){
			app.getMetaworksContext().setWhere(IUser.WHERE_PICKERLIST);
			app.getMetaworksContext().setHow(IUser.HOW_PICKER);
		}
		
		Popup popup = new Popup();
		popup.setPanel(allAppList);
		
		return popup;
		
	}
	@ServiceMethod(callByContent=true)
	public void refresh() throws Exception{
		webServiceConnector.setLinkedId(this.getLinkedId());
		webServiceConnector.setWebserviceUrl(this.getWebserviceUrl());
		webServiceConnector.load();
		
		this.setDisplayName(webServiceConnector.getWebServiceName());
		
	}	
	
	public DynamicDrawGeom drawActivitysOnDesigner() throws Exception{
		return this.webServiceConnector.drawActivitysOnDesigner();
	}
}
