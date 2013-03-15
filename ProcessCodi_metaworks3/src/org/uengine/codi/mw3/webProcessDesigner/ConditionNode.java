package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;
import java.util.Date;

import org.metaworks.ContextAware;
import org.metaworks.FieldDescriptor;
import org.metaworks.MetaworksContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.codi.mw3.model.Session;

//@Face(ejsPath="dwr/metaworks/genericfaces/GridFace.ejs")
public class ConditionNode  implements Cloneable, ContextAware{
	
	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}

	SelectBox valiableChoice;
		public SelectBox getValiableChoice() {
			return valiableChoice;
		}
		public void setValiableChoice(SelectBox valiableChoice) {
			this.valiableChoice = valiableChoice;
		}
	SelectBox signChoice;
		public SelectBox getSignChoice() {
			return signChoice;
		}
		public void setSignChoice(SelectBox signChoice) {
			this.signChoice = signChoice;
		}
	SelectBox expressionChoice;
		public SelectBox getExpressionChoice() {
			return expressionChoice;
		}
		public void setExpressionChoice(SelectBox expressionChoice) {
			this.expressionChoice = expressionChoice;
		}
	SelectBox operandChoice;
		public SelectBox getOperandChoice() {
			return operandChoice;
		}
		public void setOperandChoice(SelectBox operandChoice) {
			this.operandChoice = operandChoice;
		}
	ConditionInput conditionInput;
		public ConditionInput getConditionInput() {
			return conditionInput;
		}
		public void setConditionInput(ConditionInput conditionInput) {
			this.conditionInput = conditionInput;
		}

	public ArrayList<Role>	 roleList;
		public ArrayList<Role> getRoleList() {
			return roleList;
		}
		public void setRoleList(ArrayList<Role> roleList) {
			this.roleList = roleList;
		}	
	public ArrayList<PrcsVariable>	 prcsValiableList;
		public ArrayList<PrcsVariable> getPrcsValiableList() {
			return prcsValiableList;
		}
		public void setPrcsValiableList(ArrayList<PrcsVariable> prcsValiableList) {
			this.prcsValiableList = prcsValiableList;
		}	
		
	public ConditionNode(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
	}
	public void makeValiableChoice() throws Exception{
		SelectBox choice = new SelectBox();
		choice.setId("makeKey");
		choice.add("", "null");
		if( this.getRoleList() != null){
			for(int i = 0; i < roleList.size(); i++){
				Role role = roleList.get(i);
				choice.add("[ROLE]"+role.name, role.name);
			}
		}
		if( this.getPrcsValiableList() != null){
			for(int i = 0; i < prcsValiableList.size(); i++){
				PrcsVariable prcsValiable = prcsValiableList.get(i);
				String nameAttr = prcsValiable.getName();
				choice.add(nameAttr, nameAttr);
				String typeIdAttr = prcsValiable.getTypeId();
				String typeAttr = prcsValiable.getDataType().getSelected();
				if( "complexType".equals(typeAttr)){
					WebObjectType wot = MetaworksRemoteService.getInstance().getMetaworksType( typeIdAttr.substring(0, typeIdAttr.lastIndexOf(".")).replaceAll("/", ".") ); 
					WebFieldDescriptor wfields[] = wot.getFieldDescriptors();
					FieldDescriptor fields[] = wot.metaworks2Type().getFieldDescriptors();
					for(int j=0; j<fields.length; j++){
						WebFieldDescriptor wfd = wfields[j];
//						FieldDescriptor fd = fields[i];
						choice.add("["+nameAttr+"]"+wfd.getName(), nameAttr + "." + wfd.getName());
					}
				}
			}
		}
//		choice.setSelectStyle("font-size:10px;");
		setValiableChoice(choice);
	}
	public void makeSignChoice() throws Exception{
		SelectBox choice = new SelectBox();
		choice.setId("sign");
		choice.add("==", "==");
		choice.add("!=", "!=");
		choice.add(">=", ">=");
		choice.add(">", ">");
		choice.add("<", "<");
		choice.add("<=", "<=");
		choice.add("contains", "contains");
		choice.add("not contains", "not contains");
		setSignChoice(choice);
	}
	public void makeExpressionChoice() throws Exception{
		SelectBox choice = new SelectBox();
		choice.setId("expression");
		choice.add("", "null");
		choice.add("Text", "text");
		choice.add("Number", "number");
		choice.add("Date", "date");
		choice.add("Yes or No", "Yes or No");
		choice.add("File", "File");
		choice.add("Activity Selection", "Activity Selection");
		choice.add("Knowledge Type" ,"knowledgelType");
		choice.add("Complex Type" ,"complexType");
		choice.add("Html Form" ,"htmlType" );
		setExpressionChoice(choice);
	}
	public void makeOperandChoice() throws Exception{
		SelectBox choice = new SelectBox();
		choice.setId("operand");
		choice.add("And", "And");
		choice.add("Or", "Or");
		setOperandChoice(choice);
	}

	public void init() throws Exception{
		makeValiableChoice();
		makeSignChoice();
		makeExpressionChoice();
		makeOperandChoice();

		conditionInput = new ConditionInput();
		conditionInput.init();
	}
	
//	@ServiceMethod
//	public Object[] remove(){
//		conditionPanel.conditionNodes.remove(this);
//		return new Object[]{conditionPanel.conditionNodes};
//	}
	@AutowiredFromClient
	public Session session;
//	@AutowiredFromClient
//	transient public ConditionPanel conditionPanel;
	
//	@Override
//	public boolean equals(Object obj) {
//		if( obj instanceof ConditionNode){
//			return this.idx == ((ConditionNode)obj).idx;
//		}else{
//			return false;
//		}
//	}
}
