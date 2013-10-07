package org.uengine.codi.mw3.admin;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.ClassDesignerContentPanel;

@Face(ejsPath="genericfaces/Window.ejs",
      displayName="New Java Class")

public class NewClass implements ContextAware {
	
	public  NewClass() {
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);		
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	String sourceFolder;
		@Hidden
		public String getSourceFolder() {
			return sourceFolder;
		}
		public void setSourceFolder(String sourceFolder) {
			this.sourceFolder = sourceFolder;
		}
	
	String packageName;	
		public String getPackageName() {
			return packageName;
		}
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}
	
	String className;
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}

	@ServiceMethod(callByContent=true)
	public Object[] finish() throws Exception {
		ClassDesignerContentPanel classDesignerContentPanel = new ClassDesignerContentPanel();
		
		classDesignerContentPanel.newClass(getSourceFolder());
				
		classDesignerContentPanel.getClassDefinition().setPackageName(getPackageName());
		classDesignerContentPanel.getClassDefinition().setClassName(getClassName());
		classDesignerContentPanel.getClassDefinition().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		return new Object[]{classDesignerContentPanel, new Remover(this)};
	}
	
	@ServiceMethod
	public Object cancel() throws Exception {
		return new Remover(this);
	}
}
