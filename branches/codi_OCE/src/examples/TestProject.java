package examples;

import org.metaworks.Admin;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.ide.CloudIDE;
import org.uengine.codi.mw3.model.Session;

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
	public void load() throws Exception{
		this.setAdmin(new Admin());
		
		this.setUserId("test");
		this.setProjectId("codi");
		
		run();
	}
		
	
	@Face(displayName="Start Editing")
	@ServiceMethod(callByContent=true, except="content")
	public void run() throws Exception{
		Login login = new Login();
		login.setUserId("CJW@kiat.or.kr");
//		login.setUserId(this.getUserId());
		login.setPassword("test");

		Session session = new Session();

		try {
			session = login.loginService();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CloudIDE cloudIde = new CloudIDE();
		cloudIde.load(session);
//		cloudIde.load(session, "");
		
		this.setContent(new MainPanel(cloudIde));
	}
}
