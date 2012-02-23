package org.metaworks.widget.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.metaworks.annotation.ServiceMethod;

public class Grid {
	
	String[] columnNames;
	
	String columnModel;
	
	List<Map<String, String>> data;

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public String getColumnModel() {
		return columnModel;
	}

	public void setColumnModel(String columnModel) {
		this.columnModel = columnModel;
	}

	public List<Map<String, String>> getData() {
		return data;
	}

	public void setData(List<Map<String, String>> data) {
		this.data = data;
	}

	@ServiceMethod
	public void init(){
		setColumnNames(new String[]{"Inv No","Date", "Client", "Amount","Tax","Total","Notes"});
	//	setColumnModel("");
		
		data = new ArrayList<Map<String, String>>();
		Map column = new HashMap();
		
		column.put("id", "1");
		column.put("invdate", "2007-10-02");
		column.put("name", "11111");
		column.put("note", "11111");
		column.put("tax", "11111");
		column.put("total", "11111");

		data.add(column);
		column = new HashMap();
		column.put("id", "2");
		column.put("invdate", "2007-10-02");
		column.put("name", "22222");
		column.put("note", "22222");
		column.put("tax", "22222");
		column.put("total", "22222");
		data.add(column);

		column = new HashMap();
		column.put("id", "3");
		column.put("invdate", "2007-10-02");
		column.put("name", "33333");
		column.put("note", "33333");
		column.put("tax", "33333");
		column.put("total", "33333");
		data.add(column);

		column = new HashMap();
		column.put("id", "4");
		column.put("invdate", "2007-10-02");
		column.put("name", "4444");
		column.put("note", "44444");
		column.put("tax", "44444");
		column.put("total", "4444");
		data.add(column);
	}
	
	
	
}
