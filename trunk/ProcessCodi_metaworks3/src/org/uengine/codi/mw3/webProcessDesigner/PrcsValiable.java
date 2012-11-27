package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.Choice;

public class PrcsValiable implements ContextAware , Cloneable {
	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	Choice dataType;
		@Face(ejsPath="dwr/metaworks/org/metaworks/widget/ChoiceCombo.ejs")	
		public Choice getDataType() {
			return dataType;
		}
		public void setDataType(Choice dataType) {
			this.dataType = dataType;
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
	public PrcsValiable() throws Exception{
		this.makeDataTypeChoice();
	}
	@ServiceMethod(when="edit", callByContent=true)
	public PrcsValiablePanel add() throws Exception{
		
		PrcsValiable added = (PrcsValiable) this.clone();
		added.setMetaworksContext(new MetaworksContext());
		added.getMetaworksContext().setWhen("view");
		prcsValiablePanal.getPrcsValiables().add(added);
		
		setName("");
		setInitValue("");
		makeDataTypeChoice();
		getMetaworksContext().setWhen("edit");
	
		prcsValiablePanal.setNewPrcsValiable(this);
		
		return prcsValiablePanal;
	}
	
	@ServiceMethod(when="view", callByContent=true)
	public PrcsValiablePanel remove(){
		prcsValiablePanal.getPrcsValiables().remove(this);
		return prcsValiablePanal;
	}
	
	public void makeDataTypeChoice() throws Exception{
		Choice choice = new Choice();
		choice.add("Text", "string");
		choice.add("Number", "number");
		choice.add("Date", "date");
		choice.add("Yes or No", "Yes or No");
		choice.add("File", "File");
		choice.add("Activity Selection", "Activity Selection");
		choice.add("Knowledge Type" ,"knowledgelType");
		choice.add("Complex Type" ,"complexType");
		choice.add("Html Form" ,"htmlType" );
		setDataType(choice);
	}
	
	@AutowiredFromClient
	public PrcsValiablePanel prcsValiablePanal;
	

	@Override
	public boolean equals(Object obj) {
		return obj!=null && obj instanceof PrcsValiable && ((PrcsValiable)obj).getName().equals(getName());
	}
}
