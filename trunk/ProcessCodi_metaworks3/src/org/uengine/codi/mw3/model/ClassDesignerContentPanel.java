package org.uengine.codi.mw3.model;

import javax.servlet.http.HttpSession;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.ClassDefinition;
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
		//setting the facebook user Id into session attribute;
		HttpSession session = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		String userId = (String)session.getAttribute("userId");
		
		User user = new User();
		user.setUserId(userId);
		
		classDefinition = new ClassDefinition();
		classDefinition.setParentFolder(parentFoler);
		classDefinition.setAuthor(user);
	}
	
	public void load(String defId) throws Exception{
		classDefinition = new ClassDefinition();
		classDefinition.setAlias(defId);
		classDefinition.afterDeserialization();
		classDefinition.getMetaworksContext().setWhen("view");
		
	}

}
