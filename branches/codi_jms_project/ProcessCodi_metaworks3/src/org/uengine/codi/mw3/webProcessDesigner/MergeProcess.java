package org.uengine.codi.mw3.webProcessDesigner;

public class MergeProcess {
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
		
	public void load(){
		// TODO selectedProcessAlias 가 없으면 프로세스 선택화면이 뜬다.
		if( selectedProcessAlias == null ){
			
		}else{
			processViewer = new ProcessViewer();
			processViewer.setAlias(selectedProcessAlias);
			processViewer.setViewType("definitionView");
			processViewer.load();
		}
	}
}
