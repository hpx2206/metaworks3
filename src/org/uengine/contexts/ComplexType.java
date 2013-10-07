package org.uengine.contexts;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.metaworks.FieldDescriptor;
import org.metaworks.ObjectType;
import org.metaworks.Type;
import org.metaworks.annotation.Hidden;
import org.metaworks.metadata.MetadataBundle;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.PropertyListable;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.ui.XMLValueInput;

public class ComplexType implements Serializable, PropertyListable{
	
	private static final long serialVersionUID = GlobalContext.SERIALIZATION_UID;

	public static void metaworksCallback_changeMetadata(Type type){
		FieldDescriptor fd;
		
		fd = type.getFieldDescriptor("TypeId");
		XMLValueInput inputter = new XMLValueInput("/dwr/xstr-rpc?className=org.uengine.codi.mw3.model.ResourceFile&methodName=drillDownDeeply&object=" + URLEncoder.encode("<org.uengine.codi.mw3.model.ResourceFile><alias></alias><filter>java</filter></org.uengine.codi.mw3.model.ResourceFile>"))/* {
			public void onValueChanged() { 
				changeBindingArguments((String) getValue());
			}
		}*/;
		
		inputter.setEditable(true);

		fd.setInputter(inputter);
	}

	ClassLoader classLoader;
	
	String typeId;
		public String getTypeId() {
			return typeId;
		}
		public void setTypeId(String typeId) {
			this.typeId = typeId;
		}
	Object value;
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
	transient boolean designerMode;
	@Hidden
		public boolean isDesignerMode() {
			return designerMode;
		}
		public void setDesignerMode(boolean designerMode) {
			this.designerMode = designerMode;
		}
		
	transient Class typeClass;
	public Class getTypeClass() throws Exception{
		return getTypeClass(null);
	}
	public Class getTypeClass(ProcessManagerRemote pm) throws Exception{
		if(typeClass!=null) return typeClass;
		
		if( designerMode ){
			return null;
		}
		
		String typeId = getTypeId();
		String className = null;
		if( typeId != null  && typeId.startsWith("[@") && MetadataBundle.projectBundle != null){
			String key = typeId.substring(2, typeId.lastIndexOf("]"));
			String value = MetadataBundle.projectBundle.getProperty(key);
			className = value.substring(1, value.lastIndexOf(".")).replace('/', '.');
		}else{
			if(typeId.lastIndexOf(".java") > -1)
				className = typeId.substring(1, typeId.lastIndexOf(".")).replace('/', '.');
			else
				className = typeId.substring(1, typeId.length() -1);
		}
		
		typeClass = Thread.currentThread().getContextClassLoader().loadClass(className);
		
		return typeClass;
	}
	
	
	@Override
	public ArrayList<String> listProperties() {
		try {
			Class clazz = getTypeClass();
			
			ObjectType type = new ObjectType(clazz);
			ArrayList<String> fieldNames = new ArrayList<String>();
			for(FieldDescriptor fd: type.getFieldDescriptors()){
				fieldNames.add(fd.getName());
			}
			
			return fieldNames;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);//.printStackTrace();
		}
		
	}

}
