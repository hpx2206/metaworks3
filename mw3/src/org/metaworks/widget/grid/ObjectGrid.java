package org.metaworks.widget.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.metaworks.FieldDescriptor;
import org.metaworks.ObjectInstance;
import org.metaworks.ObjectType;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;


public class ObjectGrid extends Grid{

	public ObjectGrid() throws Exception{
		
	}

	public void setObjectData(List list) throws Exception{
		Class dataClass = list.get(0).getClass();
		
		ObjectType type = (ObjectType) MetaworksRemoteService.getInstance().getMetaworksType(dataClass.getName()).metaworks2Type();
		columnModel = new HashMap<String, Column>();
		
		
		List<String> columnNamesInList = new ArrayList<String>();
//		Map<String, Method> getters = new HashMap<String, Method>();
		for(FieldDescriptor fd : type.getFieldDescriptors()){
			
			//if(fd.getAttribute("hidden"))
			columnNamesInList.add(fd.getName());
			
			Column column = new Column();
			column.setName(fd.getDisplayName());
			column.setIndex(fd.getName());
			column.setEditable(fd.isUpdatable());
//			column.setWidth(fd.getAttribute("size"));
			column.setIndex(fd.getName());
			
			columnModel.put(fd.getName(), column);
//			getters.put(fd.getName(), dataClass.getMethod("get" + fd.getName(), parameterTypes))
		}
		
		columnNames = new String[columnNamesInList.size()];
		columnNamesInList.toArray(columnNames); 
		
		data = new ArrayList<Map<String, String>>();
		for(int i=0; i<list.size(); i++){
			Map<String, String> column = new HashMap<String, String>();

			Object row = list.get(i);
			ObjectInstance instance = (ObjectInstance) type.createInstance();
			instance.setObject(row);
			
			for(FieldDescriptor fd : type.getFieldDescriptors()){
				column.put(fd.getName(), instance.getFieldValue(fd.getName()).toString());
			}
			
			data.add(column);
		}

	}

	@ServiceMethod
	public void init() {
		
		ArrayList list = new ArrayList();
		
//		Person p = new Person();
//		p.setAge(10);
//		p.setName("Jinyoung Jang");
//		list.add(p);
//		list.add(p);
//		list.add(p);
//		
		try {
			setObjectData(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}
