package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;

public class PrcsVariable implements ContextAware , Cloneable {
	
	String name;
	@Id
	@Name
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	SelectBox  variableType;
		public SelectBox getVariableType() {
			return variableType;
		}
		public void setVariableType(SelectBox variableType) {
			this.variableType = variableType;
		}
	String initValue;
		public String getInitValue() {
			return initValue;
		}
		public void setInitValue(String initValue) {
			this.initValue = initValue;
		}
	String typeId;
		public String getTypeId() {
			return typeId;
		}
		public void setTypeId(String typeId) {
			this.typeId = typeId;
		}
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		 public void load() throws Exception{
             this.makeDataTypeChoice();
     }
     @ServiceMethod(when="edit", callByContent=true)
     public PrcsVariablePanel add() throws Exception{
             
             PrcsVariable added = (PrcsVariable) this.clone();
             added.setMetaworksContext(new MetaworksContext());
             added.getMetaworksContext().setWhen("view");
             prcsVariablePanel.getPrcsValiables().add(added);
             
             setName("");
             setInitValue("");
             makeDataTypeChoice();
             getMetaworksContext().setWhen("edit");
     
             prcsVariablePanel.setNewPrcsValiable(this);
             
             return prcsVariablePanel;
     }
     
     @ServiceMethod(when="view", callByContent=true)
     public PrcsVariablePanel remove(){
             prcsVariablePanel.getPrcsValiables().remove(this);
             return prcsVariablePanel;
     }
     
     public void makeDataTypeChoice() throws Exception{
             SelectBox choice = new SelectBox();
             choice.add("Text", "text");
             choice.add("Number", "number");
             choice.add("Date", "date");
             choice.add("Yes or No", "Yes or No");
             choice.add("File", "File");
             choice.add("Activity Selection", "Activity Selection");
             choice.add("Knowledge Type" ,"knowledgelType");
             choice.add("Complex Type" ,"complexType");
             choice.add("Html Form" ,"htmlType" );
             setVariableType(choice);
     }
		
	public PrcsVariable() throws Exception{
		this.makeDataTypeChoice();
	}
	@AutowiredFromClient
    public PrcsVariablePanel prcsVariablePanel;

	@Override
	public boolean equals(Object obj) {
		return obj!=null && obj instanceof PrcsVariable && ((PrcsVariable)obj).getName().equals(getName());
	}
}