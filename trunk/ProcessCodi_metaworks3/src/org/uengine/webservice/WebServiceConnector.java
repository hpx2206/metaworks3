package org.uengine.webservice;

import org.metaworks.annotation.Id;
import org.uengine.kernel.designer.web.DynamicDrawGeom;

public interface WebServiceConnector {
	
	public static String WEBSERVICE_API_JAXRS = "jaxrs";
	
	@Id
	public String getLinkedId();
	public void setLinkedId(String linkedId);
	
	public String getApiType();
	public void setApiType(String apiType);
	
	public String getWebServiceName();
	public void setWebServiceName(String webServiceName);
	
	public WebServiceDefinition getWebServiceDefinition();
	public void setWebServiceDefinition(WebServiceDefinition webServiceDefinition);
	
	public void load() throws Exception;
	
	public DynamicDrawGeom drawActivitysOnDesigner() throws Exception;
	
}
