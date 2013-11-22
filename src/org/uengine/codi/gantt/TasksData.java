package org.uengine.codi.gantt;

import java.util.ArrayList;

import org.metaworks.annotation.Face;

public class TasksData {
	
	ArrayList<TaskData> taskList;
		@Face(options = { "upperCase" }, values = { "first" })
		public ArrayList<TaskData> getTask() {
			return taskList;
		}
	
		public void setTask(ArrayList<TaskData> taskList) {
			this.taskList = taskList;
		}
		
	public TasksData() {
		
		taskList = new ArrayList<TaskData>();
		
	}
	
}
