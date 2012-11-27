package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;
import org.metaworks.ContextAware;
import org.metaworks.FieldDescriptor;
import org.metaworks.MetaworksContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.widget.Choice;
import org.uengine.codi.mw3.model.Session;

public class ConditionNode  implements Cloneable, ContextAware{
	
	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
	
	int idx;
		@Id
		public int getIdx() {
			return idx;
		}
		public void setIdx(int idx) {
			this.idx = idx;
		}

	Choice valiableChoice;
		@Face(ejsPath="dwr/metaworks/org/metaworks/widget/ChoiceCombo.ejs")
		public Choice getValiableChoice() {
			return valiableChoice;
		}
		public void setValiableChoice(Choice valiableChoice) {
			this.valiableChoice = valiableChoice;
		}
	Choice signChoice;
		@Face(ejsPath="dwr/metaworks/org/metaworks/widget/ChoiceCombo.ejs")
		public Choice getSignChoice() {
			return signChoice;
		}
		public void setSignChoice(Choice signChoice) {
			this.signChoice = signChoice;
		}
	Choice expressionChoice;
		@Face(ejsPath="dwr/metaworks/org/metaworks/widget/ChoiceCombo.ejs")
		public Choice getExpressionChoice() {
			return expressionChoice;
		}
		public void setExpressionChoice(Choice expressionChoice) {
			this.expressionChoice = expressionChoice;
		}
	Choice operandChoice;
		@Face(ejsPath="dwr/metaworks/org/metaworks/widget/ChoiceCombo.ejs")
		public Choice getOperandChoice() {
			return operandChoice;
		}
		public void setOperandChoice(Choice operandChoice) {
			this.operandChoice = operandChoice;
		}
	String expressionText;
		public String getExpressionText() {
			return expressionText;
		}
		public void setExpressionText(String expressionText) {
			this.expressionText = expressionText;
		}
	public ArrayList<Role>	 roleList;
		public ArrayList<Role> getRoleList() {
			return roleList;
		}
		public void setRoleList(ArrayList<Role> roleList) {
			this.roleList = roleList;
		}	
	public ArrayList<PrcsValiable>	 prcsValiableList;
		public ArrayList<PrcsValiable> getPrcsValiableList() {
			return prcsValiableList;
		}
		public void setPrcsValiableList(ArrayList<PrcsValiable> prcsValiableList) {
			this.prcsValiableList = prcsValiableList;
		}	
		
	public ConditionNode(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
	}
	public void makeValiableChoice() throws Exception{
		Choice choice = new Choice();
		if( this.getRoleList() != null){
			for(int i = 0; i < roleList.size(); i++){
				Role role = roleList.get(i);
				choice.add("[ROLE]"+role.name, role.name);
			}
		}
		if( this.getPrcsValiableList() != null){
			for(int i = 0; i < prcsValiableList.size(); i++){
				PrcsValiable prcsValiable = prcsValiableList.get(i);
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
						choice.add("["+nameAttr+"]"+wfd.getName(), wfd.getName());
					}
				}
			}
		}
		
		setValiableChoice(choice);
	}
	public void makeSignChoice() throws Exception{
		Choice choice = new Choice();
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
		setExpressionChoice(choice);
	}
	public void makeOperandChoice() throws Exception{
		Choice choice = new Choice();
		choice.add("And", "And");
		choice.add("Or", "Or");
		setOperandChoice(choice);
	}

	public void init() throws Exception{
		makeValiableChoice();
		makeSignChoice();
		makeExpressionChoice();
		makeOperandChoice();
	}
	
	@ServiceMethod
	public Object[] remove(){
		conditionPanel.conditionNodes.remove(this);
		return new Object[]{conditionPanel.conditionNodes};
	}
	@AutowiredFromClient
	public Session session;
	@AutowiredFromClient
	transient public ConditionPanel conditionPanel;
	
	@Override
	public boolean equals(Object obj) {
		if( obj instanceof ConditionNode){
			return this.idx == ((ConditionNode)obj).idx;
		}else{
			return false;
		}
	}
}
