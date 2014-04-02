package org.uengine.webservice;

import org.metaworks.annotation.Hidden;

import net.sf.json.JSONArray;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("method")
public class MethodProperty {
	
	@XStreamAsAttribute
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	@XStreamAsAttribute
	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	String description;
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	String responseClass;	
		public String getResponseClass() {
			return responseClass;
		}
		public void setResponseClass(String responseClass) {
			this.responseClass = responseClass;
		}
	ParameterProperty[] request;	
		public ParameterProperty[] getRequest() {
			return request;
		}
		public void setRequest(ParameterProperty[] request) {
			this.request = request;
		}
		
	JSONArray responseMessages;	
	@Hidden
		public JSONArray getResponseMessages() {
			return responseMessages;
		}
		public void setResponseMessages(JSONArray responseMessages) {
			this.responseMessages = responseMessages;
		}
	JSONArray consumes;
	@Hidden
		public JSONArray getConsumes() {
			return consumes;
		}
		public void setConsumes(JSONArray consumes) {
			this.consumes = consumes;
		}
	JSONArray produces;
	@Hidden
		public JSONArray getProduces() {
			return produces;
		}
		public void setProduces(JSONArray produces) {
			this.produces = produces;
		}
	String summary;
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}	

}
