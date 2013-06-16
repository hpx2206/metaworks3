package org.uengine.codi.mw3.ide.form;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
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
		@Available(when={MetaworksContext.WHEN_EDIT}, where={"properties"})
		public ArrayList<MultipleChoiceOption> getChoiceOptions() {
			return choiceOptions;
		}
		public void setChoiceOptions(ArrayList<MultipleChoiceOption> choiceOptions) {
			this.choiceOptions = choiceOptions;
		}
		
	public MultipleChoiceOptionPanel(){
	 
		this.setChoiceOptions(new ArrayList<MultipleChoiceOption>());
		
		MultipleChoiceOption option = new MultipleChoiceOption();
		option.setParentId(this.getParentId());
		option.add();
	}
}
