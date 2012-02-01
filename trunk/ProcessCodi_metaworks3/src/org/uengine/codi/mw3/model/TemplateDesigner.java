package org.uengine.codi.mw3.model;

import java.io.FileOutputStream;
import java.util.Collection;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.kernel.GlobalContext;

public class TemplateDesigner {
	
	public TemplateDesigner(){}
	
	public TemplateDesigner(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		designee = Thread.currentThread().getContextClassLoader().loadClass(className).newInstance();
	}
	
	Object designee;
		public Object getDesignee() {
			return designee;
		}
		public void setDesignee(Object designee) {
			this.designee = designee;
		}
	
	@ServiceMethod(callByContent=true)
	public void save() throws Exception{
		
		String className = designee.getClass().getName();
		//WebObjectType webObjectType = MetaworksRemoteService.getInstance().getMetaworksType(designee.getClass().getName());
		
		GlobalContext.serialize(designee, new FileOutputStream("/Users/jyjang/javasources/" + className + ".xml"), String.class);
		
		MetaworksRemoteService.getInstance().clearMetaworksType(className);


	}
	
	
}
