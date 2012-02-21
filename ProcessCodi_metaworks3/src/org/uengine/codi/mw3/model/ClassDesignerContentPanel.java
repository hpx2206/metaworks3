package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath="genericfaces/Window.ejs", displayName="Class Designer", options={"hideLabels"}, values={"true"})
public class ClassDesignerContentPanel extends ContentWindow {

	@Autowired
	public ProcessManagerRemote processManager;
	
	ClassDefinition classDefinition;
		public ClassDefinition getClassDefinition() {
			return classDefinition;
		}
		public void setClassDefinition(ClassDefinition classDefinition) {
			this.classDefinition = classDefinition;
		}

	public void newClass(String parentFoler){
		classDefinition = new ClassDefinition();
		classDefinition.setParentFolder(parentFoler);
	}

	public void load(String defId) throws Exception{
		
		try{
			String defVerId = processManager.getProcessDefinitionProductionVersion(defId);
			String resource = processManager.getResource(defVerId);
			classDefinition = (ClassDefinition) GlobalContext.deserialize(resource, ClassDefinition.class);
			classDefinition.setDefId(defId);
			classDefinition.setFacebookComments(new Facebook());
			classDefinition.getFacebookComments().setDefId(defId);
			classDefinition.setFacebookLike(new Facebook());
			classDefinition.getFacebookLike().setDefId(defId);
			
			try {
				ProcessDefinition def = new ProcessDefinition();
				def.setDefId(new Long(defId));
				String authorId = def.databaseMe().getAuthor();
				
				User author = new User();
				author.setUserId(authorId);
				classDefinition.setAuthor(author);
				
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}finally{
			processManager.remove();
		}
		
		
	}

}
