package org.essencia.model;

import java.io.Serializable;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;

@Face(displayName="¼Ó¼º", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"fieldOrder"},values={"accountableCompetency,alphaInput,concern,name,order,rCompetencies,multiplicity"})
public class Element implements Serializable , ContextAware{
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	String accountableCompetency;
		@Face(displayName="AccountableCompetency")
		public String getAccountableCompetency() {
			return accountableCompetency;
		}
		public void setAccountableCompetency(String accountableCompetency) {
			this.accountableCompetency = accountableCompetency;
		}
	
	String alphaInput;
		@Face(displayName="AlphaInput")
		public String getAlphaInput() {
			return alphaInput;
		}
		public void setAlphaInput(String alphaInput) {
			this.alphaInput = alphaInput;
		}
		
	String concern;
		@Face(displayName="Concern")
		public String getConcern() {
			return concern;
		}
		public void setConcern(String concern) {
			this.concern = concern;
		}
	
	String name;
		@Face(displayName="Name")
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
	String order;
		@Face(displayName="Order")
		public String getOrder() {
			return order;
		}
		public void setOrder(String order) {
			this.order = order;
		}
	
	String rCompetencies;
		@Face(displayName="Required Competencies")
		public String getrCompetencies() {
			return rCompetencies;
		}
		public void setrCompetencies(String rCompetencies) {
			this.rCompetencies = rCompetencies;
		}
		
	String multiplicity;
		@Face(displayName="multiplicity")
		public String getMultiplicity() {
			return multiplicity;
		}
		public void setMultiplicity(String multiplicity) {
			this.multiplicity = multiplicity;
		}
	
		

	public Element(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
	}
}
