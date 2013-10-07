package org.uengine.codi.mw3.ide.libraries;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.webProcessDesigner.Documentation;
import org.uengine.codi.mw3.webProcessDesigner.ProcessAttributePanel;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.Role;

public class LibraryActivity {
	
	public static final String TYPE_ACTIVITY = "activity";

	HumanActivity humanActivity;
		public HumanActivity getHumanActivity() {
			return humanActivity;
		}
		public void setHumanActivity(HumanActivity humanActivity) {
			this.humanActivity = humanActivity;
		}
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
	public void load() {
		humanActivity = new HumanActivity();
		
		TextContext textContext = new TextContext();
		textContext.setText("임시 액티비티");
		
		Documentation documentation = new Documentation();
		documentation.setAlias("D:/codi/codebase/codi/root/GG.process");
		documentation.setDefId("defId");
		documentation.setDocument("임시 문서");
		documentation.setTitle("임시 문서 제목");
		
		humanActivity.setId("0");
		humanActivity.setName(textContext);
		humanActivity.setDescription(textContext);
		humanActivity.setDocumentation(documentation);
		
		this.setType(TYPE_ACTIVITY);
		
	}
	
	@ServiceMethod(callByContent = true)
	public Object showProperties() {
		ProcessAttributePanel processAttributePanel = new ProcessAttributePanel();
		
		return null;
	}
}
