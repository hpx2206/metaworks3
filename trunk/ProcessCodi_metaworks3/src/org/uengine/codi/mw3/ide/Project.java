package org.uengine.codi.mw3.ide;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.knowledge.IProjectNode;

@Face(ejsPath="genericfaces/EmptyEjs.ejs")
public class Project {

	public final static String METADATA_FILENAME = "uengine.metadata";
	
	String id;
		@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
	
	String projectAlias;
		public String getProjectAlias() {
			return projectAlias;
		}
		public void setProjectAlias(String projectAlias) {
			this.projectAlias = projectAlias;
		}

	boolean useDefault;
		public boolean isUseDefault() {
			return useDefault;
		}
		public void setUseDefault(boolean useDefault) {
			this.useDefault = useDefault;
		}
	
	public Project(){
		
	}
	
	public Project(IProjectNode node) throws Exception {
		this.setId(node.getId());
		this.setName(node.getName());
		this.setUseDefault(true);
		this.setProjectAlias(node.getProjectAlias());
		this.load();
	}
	
	public void load() throws Exception{

		StringBuffer path = new StringBuffer();
		path.append(CodiClassLoader.getCodeBaseRoot() + this.getId() + CodiClassLoader.PATH_SEPARATOR);
		
		if(this.isUseDefault())
			path.append(CodiClassLoader.DEFAULT_NAME);
		else{
			String tenantId = null;
			
			HttpServletRequest request = TransactionContext.getThreadLocalInstance().getRequest();
			HttpSession session = request.getSession();
			if(session!=null)
				 tenantId = (String) session.getAttribute("tenantId");

			if(tenantId == null)
				throw new Exception("tenant is not recognized. It is the wrong approach.");
			
			path.append(tenantId);
		}
		
		path.append(CodiClassLoader.PATH_SEPARATOR);
		
		this.setPath(path.toString());
	}
}
