package org.uengine.codi.mw3.ide.form;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

public class MultipleChoiceOptionPanel {

	ArrayList<MultipleChoiceOption> choiceOptions;
		@Face(displayName="$multipleChoiceOption.displayname")
		@Available(when={MetaworksContext.WHEN_EDIT}, where={"properties"})
		public ArrayList<MultipleChoiceOption> getChoiceOptions() {
			return choiceOptions;
		}
		public void setChoiceOptions(ArrayList<MultipleChoiceOption> choiceOptions) {
			this.choiceOptions = choiceOptions;
		}
		
	public MultipleChoiceOptionPanel(){
		this.setChoiceOptions(new ArrayList<MultipleChoiceOption>());
	}
}
