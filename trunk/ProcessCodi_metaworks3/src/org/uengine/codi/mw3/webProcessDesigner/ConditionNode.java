package org.uengine.codi.mw3.webProcessDesigner;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
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
	public ConditionNode(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
	}
	public void makeValiableChoice(String valiableString) throws Exception{
		if( valiableString != null){
			Choice choice = new Choice();
			Document doc = getDocument(valiableString);
//			Element rootElement = doc.getRootElement();
			XPath xpathSelector = null;
			xpathSelector = DocumentHelper.createXPath("//processValiable");
			List<Element> nodeList = xpathSelector.selectNodes(doc);
			if( nodeList.size() > 0 ){
				for (Iterator iterator = nodeList.iterator() ; iterator.hasNext(); ) {
					Element ele = (Element) iterator.next();
					String nameAttr = ele.attribute("name").getValue();
					String idAttr = ele.attribute("id").getValue();
					choice.add(nameAttr, idAttr);
				}
			}
			setValiableChoice(choice);
		}
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
		choice.add("Text", "Text");
		choice.add("Number", "Number");
		choice.add("Date", "Date");
		choice.add("Yes or No", "Yes or No");
		choice.add("File", "File");
		choice.add("Activity Selection", "Activity Selection");
		choice.add("Complex Type", "Complex Type");
		choice.add("Html Form", "Html Form");
		setExpressionChoice(choice);
	}
	public void makeOperandChoice() throws Exception{
		Choice choice = new Choice();
		choice.add("And", "And");
		choice.add("Or", "Or");
		setOperandChoice(choice);
	}

	public void init(String valiableString) throws Exception{
		makeValiableChoice(valiableString);
		makeSignChoice();
		makeExpressionChoice();
		makeOperandChoice();
	}
	
	@ServiceMethod
	public Object[] remove(){
		conditionPanel.conditionNodes.remove(this);
		return new Object[]{conditionPanel.conditionNodes};
	}
	
	private Document getDocument(String xmlDescription) throws DocumentException {
        if (xmlDescription == null) {
            return null;
        }
        return DocumentHelper.parseText(xmlDescription);
    }
	@AutowiredFromClient
	public Session session;
	@AutowiredFromClient
	transient public ConditionPanel conditionPanel;
}
