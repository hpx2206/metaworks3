package org.uengine.codi.activitytypes.wih;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import org.metaworks.FieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.dwr.TransactionalDwrServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.uengine.codi.mw3.model.ClassDesignerContentPanel;
import org.uengine.kernel.NeedArrangementToSerialize;

public class ClassDevelopmentWorkItemHandler implements Serializable, NeedArrangementToSerialize{

	public ClassDevelopmentWorkItemHandler(){
		setClassDesigner(new ClassDesignerContentPanel());
	}
	
	String resourceName;
		
		public String getResourceName() {
			return resourceName;
		}
	
		public void setResourceName(String resourceName) {
			this.resourceName = resourceName;
		}

	@Autowired
	public transient ClassDesignerContentPanel classDesigner;
	
		public ClassDesignerContentPanel getClassDesigner() {
			return classDesigner;
		}
	
		public void setClassDesigner(ClassDesignerContentPanel classDesigner) {
			this.classDesigner = classDesigner;
		}

	@Override
	public void beforeSerialization() {
		if(getResourceName()==null)
			throw new RuntimeException("리소스명이 입력되어야 완료할 수 있습니다.");
	}

	@Override
	public void afterDeserialization() {
		try {
			
			classDesigner.load("["+getResourceName()+"]");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
}
