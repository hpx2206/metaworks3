package examples;

import org.metaworks.Admin;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.CloudIDE;

public class TestProject implements ContextAware {

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	String userId;
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
	
	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}

	Admin admin;
		public Admin getAdmin() {
			return admin;
		}
		public void setAdmin(Admin admin) {
			this.admin = admin;
		}

	Object content;
		public Object getContent() {
			return content;
		}
		public void setContent(Object content) {
			this.content = content;
		}
		
	@ServiceMethod(callByContent=true)
	public void load(){
		this.setAdmin(new Admin());
		
		this.setUserId("somehow");
		this.setProjectId("codi");
		
		run();
	}
		
	
	@Face(displayName="Start Editing")
	@ServiceMethod(callByContent=true, except="content")
	public void run(){
		CloudIDE cloudIde = new CloudIDE();
		cloudIde.load("somehow", "codi");
		
		this.setContent(cloudIde);
	}
}
