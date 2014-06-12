package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.FieldDescriptor;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.codi.mw3.model.Session;
import org.uengine.contexts.ComplexType;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.Role;

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
	ConditionInput conditionInput;
		public ConditionInput getConditionInput() {
			return conditionInput;
		}
		public void setConditionInput(ConditionInput conditionInput) {
			this.conditionInput = conditionInput;
		}
	
	String conditionType;
		public String getConditionType() {
			return conditionType;
		}
		public void setConditionType(String conditionType) {
			this.conditionType = conditionType;
		}
		
	ConditionTreeNode parentTreeNode;
		public ConditionTreeNode getParentTreeNode() {
			return parentTreeNode;
		}
		public void setParentTreeNode(ConditionTreeNode parentTreeNode) {
			this.parentTreeNode = parentTreeNode;
		}

	public ArrayList<Role>	 roleList;
		public ArrayList<Role> getRoleList() {
			return roleList;
		}
		public void setRoleList(ArrayList<Role> roleList) {
			this.roleList = roleList;
		}	
	public ArrayList<ProcessVariable> variableList;
		public ArrayList<ProcessVariable> getVariableList() {
			return variableList;
		}
		public void setVariableList(ArrayList<ProcessVariable> variableList) {
			this.variableList = variableList;
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
				choice.add("[ROLE]"+role.getName(), role.getName());
			}
		}
		if( this.getVariableList() != null){
			for(int i = 0; i < variableList.size(); i++){
				ProcessVariable processVariable = variableList.get(i);
				String nameAttr = processVariable.getName();
				choice.add(nameAttr, nameAttr);
//				String typeAttr = processVariable.getDefaultValue();
				Object typeAttr = processVariable.getDefaultValue();
				if( typeAttr instanceof ComplexType){
//					WebObjectType wot = MetaworksRemoteService.getInstance().getMetaworksType( typeIdAttr.substring(0, typeIdAttr.lastIndexOf(".")).replaceAll("/", ".") ); 
					ComplexType complexType = (ComplexType)typeAttr;
					String typeIdAttr = complexType.getTypeId();
					if( typeIdAttr != null && !"".equals(typeIdAttr) ){
						String formName = typeIdAttr.substring(1, typeIdAttr.length() -1); 
						WebObjectType wot = MetaworksRemoteService.getInstance().getMetaworksType( formName ); 
						WebFieldDescriptor wfields[] = wot.getFieldDescriptors();
						FieldDescriptor fields[] = wot.metaworks2Type().getFieldDescriptors();
						for(int j=0; j<fields.length; j++){
							WebFieldDescriptor wfd = wfields[j];
//						FieldDescriptor fd = fields[i];
							choice.add("["+nameAttr+"]"+wfd.getName(), nameAttr + "." + wfd.getName());
							if( wfd.getClassName().startsWith("org.uengine.codi.mw3")){
								WebObjectType wot2 = MetaworksRemoteService.getInstance().getMetaworksType(wfd.getClassName() ); 
								WebFieldDescriptor wfields2[] = wot2.getFieldDescriptors();
								FieldDescriptor fields2[] = wot2.metaworks2Type().getFieldDescriptors();
								for(int k=0; k<fields2.length; k++){
									WebFieldDescriptor wfd2 = wfields2[k];
									choice.add("["+nameAttr+"]"+wfd.getName()+ "." + wfd2.getName(), nameAttr + "." + wfd.getName() + "." + wfd2.getName());
								}
							}
						}
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
		choice.add("NULL", "null");
		choice.add("Text", "text");
		choice.add("Number", "number");
		choice.add("Date", "date");
		choice.add("Yes or No", "Yes or No");
//		choice.add("File", "File");
//		choice.add("Activity Selection", "Activity Selection");
//		choice.add("Knowledge Type" ,"knowledgelType");
		choice.add("Process Variable" ,"variable");
//		choice.add("Html Form" ,"htmlType" );
		setExpressionChoice(choice);
	}
	
	@ServiceMethod( callByContent=true ,target=ServiceMethodContext.TARGET_AUTO)
	public Object[] saveCondition() throws Exception{
//		this.getMetaworksContext().setHow("tree");
		String nodeName = "";
		if( conditionType != null && ( conditionType.equals(ConditionTreeNode.CONDITION_AND) || conditionType.equals(ConditionTreeNode.CONDITION_OR) )){
			String val1 = this.getValiableChoice().getSelected();
			String val2 = this.getSignChoice().getSelected();
			String val3 = this.getExpressionChoice().getSelected();
			
			ConditionInput expVal = this.getConditionInput();
			if( val3 != null && ( val3.equalsIgnoreCase("text") || val3.equalsIgnoreCase("number")) ){
				val3 = expVal.getExpressionText();
			}else if( val3 != null && val3.equalsIgnoreCase("Yes or No") ){
				val3 = expVal.getYesNo();
			}else if( val3 != null && val3.equalsIgnoreCase("date") ){
				val3 = expVal.getExpressionDate().toString();
			}else if( val3 != null && val3.equalsIgnoreCase("File") ){
				// TODO
			}else if( val3 != null && val3.equalsIgnoreCase("variable") ){
				val3 = expVal.getValiableChoice().getSelectedText();
			}
			nodeName = "[" +conditionType + "]" + val1 + " " +val2 + " " + val3; 
		}else if( conditionType != null && conditionType.equals(ConditionTreeNode.CONDITION_OTHERWISE) ){
			nodeName = "otherwise";
		}else{
			nodeName = "오류";
		}
		// 왼쪽 트리 변경
		ConditionTreeNode node = getParentTreeNode();
		node.setName(nodeName);
		node.setConditionNode(this);
		
		// 오른쪽 패널 화면 아무것도 안나오도록 설정
		ConditionNode conditionNode = new ConditionNode();
		conditionNode.getMetaworksContext().setWhen("view");
		
		return new Object[]{node , conditionNode};
	}

	public void init() throws Exception{
		makeValiableChoice();
		makeSignChoice();
		makeExpressionChoice();

		conditionInput = new ConditionInput();
		conditionInput.init();
		// 두개를 동시에 쓰는 경우가 생기기 때문에 객체를 분리하여 주었다
		SelectBox selectBox = new SelectBox();
		selectBox.setOptionNames(valiableChoice.getOptionNames());
		selectBox.setOptionValues(valiableChoice.getOptionValues());
		conditionInput.setValiableChoice(selectBox);
	}
	
	@AutowiredFromClient
	public Session session;
	
}
