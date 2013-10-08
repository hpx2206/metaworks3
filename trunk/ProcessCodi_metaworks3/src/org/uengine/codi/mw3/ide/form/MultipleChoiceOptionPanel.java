package org.uengine.codi.mw3.ide.form;

import java.util.ArrayList;

import org.metaworks.annotation.Face;

@Face(ejsPath="dwr/metaworks/org/uengine/codi/mw3/ide/form/MultipleChoiceOptionPanel.ejs")
public class MultipleChoiceOptionPanel {
	
	String parentId;
//	@Hidden
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	ArrayList<MultipleChoiceOption> choiceOptions;
		public ArrayList<MultipleChoiceOption> getChoiceOptions() {
			return choiceOptions;
		}
		public void setChoiceOptions(ArrayList<MultipleChoiceOption> choiceOptions) {
			this.choiceOptions = choiceOptions;
		}
		
	public void init(){
	
		//TODO : 기본 입력 값 처리
		
		ArrayList<MultipleChoiceOption> options = new ArrayList<MultipleChoiceOption>();
		
		MultipleChoiceOption option1 = new MultipleChoiceOption();
		option1.setFieldId(MultipleChoiceOption.OPTION_FIELD_ID_PREFIX + "0");
		option1.setOption("First option");
		option1.setValue("first");
		
		MultipleChoiceOption option2 = new MultipleChoiceOption();
		option2.setFieldId(MultipleChoiceOption.OPTION_FIELD_ID_PREFIX + "1");
		option2.setOption("Second option");
		option2.setValue("second");
		
		MultipleChoiceOption option3 = new MultipleChoiceOption();
		option3.setFieldId(MultipleChoiceOption.OPTION_FIELD_ID_PREFIX + "2");
		option3.setOption("Third option");
		option3.setValue("third");
	
		options.add(option1);
		options.add(option2);
		options.add(option3);
		
		this.setChoiceOptions(options);
	}
}