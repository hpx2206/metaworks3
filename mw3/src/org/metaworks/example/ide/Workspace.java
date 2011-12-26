package org.metaworks.example.ide;

public class Workspace {

	ProjectNavigator projectNavigator;

		public ProjectNavigator getProjectNavigator() {
			return projectNavigator;
		}
	
		public void setProjectNavigator(ProjectNavigator projectNavigator) {
			this.projectNavigator = projectNavigator;
		}
		
	SourceCodeEditor sourceCodeEditor;
	
		public SourceCodeEditor getSourceCodeEditor() {
			return sourceCodeEditor;
		}
	
		public void setSourceCodeEditor(SourceCodeEditor sourceCodeEditor) {
			this.sourceCodeEditor = sourceCodeEditor;
		} 
}
