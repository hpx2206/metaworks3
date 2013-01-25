package org.metaworks.example.ide;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

public class ProcessDesigner {

	
	ERDEditor erdEditor;
	
		public ERDEditor getErdEditor() {
			return erdEditor;
		}
	
		public void setErdEditor(ERDEditor erdEditor) {
			this.erdEditor = erdEditor;
		}

	ProcessDefinitionChartEditor chartEditor;
		
		public ProcessDefinitionChartEditor getChartEditor() {
			return chartEditor;
		}
	
		public void setChartEditor(ProcessDefinitionChartEditor chartEditor) {
			this.chartEditor = chartEditor;
		}

	FormEditor formEditor;
		public FormEditor getFormEditor() {
			return formEditor;
		}
	
		public void setFormEditor(FormEditor formEditor) {
			this.formEditor = formEditor;
		}

	JavaActivity activity;
		@Face(ejsPath="genericfaces/Window.ejs")
	
		public JavaActivity getActivity() {
			return activity;
		}
	
		public void setActivity(JavaActivity activity) {
			this.activity = activity;
		}

		
	@ServiceMethod
	public void load(){
		setErdEditor(new ERDEditor());
		setFormEditor(new FormEditor());
		setChartEditor(new ProcessDefinitionChartEditor());
		setActivity(new JavaActivity());
	}
	
}
