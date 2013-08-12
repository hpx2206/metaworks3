package org.uengine.codi.mw3.ide.compare;

import org.metaworks.annotation.Id;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewer;


public class CompareFile {
	String editorId;
		@Id
		public String getEditorId() {
			return editorId;
		}
		public void setEditorId(String editorId) {
			this.editorId = editorId;
		}
	String selectedProcessAlias;
		public String getSelectedProcessAlias() {
			return selectedProcessAlias;
		}
		public void setSelectedProcessAlias(String selectedProcessAlias) {
			this.selectedProcessAlias = selectedProcessAlias;
		}
	ProcessViewer processViewer;
		public ProcessViewer getProcessViewer() {
			return processViewer;
		}
		public void setProcessViewer(ProcessViewer processViewer) {
			this.processViewer = processViewer;
		}
	String viewType;
		public String getViewType() {
			return viewType;
		}
		public void setViewType(String viewType) {
			this.viewType = viewType;
		}
	String fileName;
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
	public void load(){
		
		// TODO selectedProcessAlias 가 없으면 프로세스 선택화면이 뜬다.
		if( selectedProcessAlias == null ){
			
			
		}else{
			processViewer = new ProcessViewer();
			processViewer.setEditorId(this.getEditorId());
			processViewer.setAlias(selectedProcessAlias);
			processViewer.setViewType(this.getViewType());
			processViewer.getProcessDesignerContainer().setEditorId(selectedProcessAlias);
			processViewer.load();
		}
	}
}
