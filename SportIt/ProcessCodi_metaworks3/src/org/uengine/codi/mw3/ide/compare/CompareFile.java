package org.uengine.codi.mw3.ide.compare;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.process.ProcessMergeEditor;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContainer;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContentPanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewer;
import org.uengine.kernel.Activity;
import org.uengine.util.UEngineUtil;


public class CompareFile {
	
	@AutowiredFromClient
	public Locale localeManager;
	
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
		
	public CompareFile() throws Exception{
		processViewer = new ProcessViewer();
	}
	public void load(){
		if(UEngineUtil.isNotEmpty(selectedProcessAlias) ){
			processViewer.setEditorId(this.getEditorId());
			processViewer.setAlias(selectedProcessAlias);
			processViewer.setViewType(this.getViewType());
			processViewer.getProcessDesignerContainer().setEditorId(selectedProcessAlias);
			
			if( this.getFileName() == null ){
				File file = new File(selectedProcessAlias);
				this.setFileName(file.getName());
			}
			
			processViewer.load();
		}
	}
	public Object saveMe() throws Exception{
		if( this.getFileName() != null && TreeNode.TYPE_FILE_PROCESS.equals(ResourceNode.findNodeType(this.getFileName()))){
			/* TODO ejs.js 를 업데이트 받고나서 주석 풀기.. 그리고 저장되는지 확인
			 * TODO 현재 processMerge를 호출을 하고나면 style 이 저장이 되기때문에,.. 우선적으론 스타일을 제거해야함.. 추후 도형으로 추가가 되면 그 부분적용
			ProcessDesignerContainer processDesignerContainer = this.processViewer.getProcessDesignerContainer();
			String title = this.fileName.replace('.','@').split("@")[0];
			ProcessDefinition def = processDesignerContainer.containerToDefinition(processDesignerContainer);
			def.setName(title);
			if( processViewer.getProcessDesignerInstanceId() != null ){
				def.setProcessDesignerInstanceId(processViewer.getProcessDesignerInstanceId());
			}
			if( processViewer.getProcessDesignerSize() != null ){
				def.setProcessDesignerSize(processViewer.getProcessDesignerSize());
			}
			
			FileOutputStream fos = null;
			try{
				File file = new File(this.processViewer.getAlias());
				fos = new FileOutputStream(file);
				String definitionInString = (String)GlobalContext.serialize(def, ProcessDefinition.class);
				ByteArrayInputStream bai = new ByteArrayInputStream(definitionInString.getBytes(GlobalContext.ENCODING));
				UEngineUtil.copyStream(bai, fos);
			} catch (Exception e) {
				throw e;//e.printStackTrace();
			} finally{
				if(fos!=null)
					fos.close();
			}
			*/
		}
		return null;
	}	
	public void save(ProcessMergeEditor processMergeEditor) throws Exception{
		if( !CompareOriginFilePanel.FILE_LOCATION.equals(this.getEditorId())){
			CompareOriginFile compareOriginFile = processMergeEditor.getFileComparePanel().getCompareOriginFilePanel().getCompareOriginFile();
			this.processViewer = compareOriginFile.processViewer;
			this.editorId = compareOriginFile.editorId;
			this.selectedProcessAlias = compareOriginFile.selectedProcessAlias;
			this.viewType = compareOriginFile.viewType;
			this.fileName = compareOriginFile.fileName;
		}
		this.saveMe();
	}	
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_APPEND)
	public Object save() throws Exception{
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		modalWindow.setWidth(300);
		modalWindow.setHeight(150);
						
		modalWindow.setTitle("$SaveCompleteTitle");
		modalWindow.setPanel(localeManager.getString("$SaveCompleteMessage"));
		modalWindow.getButtons().put("$Confirm", new Refresh(this.saveMe()));		
		
		return modalWindow;
	}
	
	@AutowiredFromClient
	public CompareImportFile compareImportFile;
	
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_AUTO)
	public Object[] processMerge() throws Exception{
		
		HashMap<String , Activity> activityMap1 = new HashMap<String , Activity>(); 
		HashMap<String , Activity> activityMap2 = new HashMap<String , Activity>(); 
		processViewer.setProcessDesignerContainer(new ProcessDesignerContainer());
		processViewer.getProcessDesignerContainer().setEditorId(this.getEditorId());
		processViewer.load();
		ProcessDesignerContainer designerContainer1 = processViewer.getProcessDesignerContainer();
		ArrayList<Activity> activityList1 = designerContainer1.getActivityList();
		for( Activity act : activityList1 ){
			activityMap1.put(act.getTracingTag() , act);
		}
		
		ProcessViewer compareProcessViewer = compareImportFile.getProcessViewer();
		compareProcessViewer.setProcessDesignerContainer(new ProcessDesignerContainer());
		compareProcessViewer.getProcessDesignerContainer().setEditorId(compareProcessViewer.getEditorId());
		compareProcessViewer.load();
		ProcessDesignerContainer designerContainer2 = compareProcessViewer.getProcessDesignerContainer();
		ArrayList<Activity> activityList2 = designerContainer2.getActivityList();
		for( Activity act : activityList2 ){
			activityMap2.put(act.getTracingTag() , act);
		}
		
		/*
		 * mergeOriginProcess 안쪽에 있는 프로세스를 기준으로 mergeImportProcess 에 그려진 프로세스가 수정, 삭제, 추가 되었는지 체크를 하여 
		 * mergeImportProcess 만 리턴을 시켜서 새롭게 그리는 방법
		 * 삭제된건 왼쪽에 표시를 하고, 추가된건 오른쪽에 표시, 녹색은 둘다
		 * 파랑 : 추가 , 빨강 : 제거 , 녹색 : 수정
		 */
		for( int i = 0 ; i < activityList1.size(); i++ ){
			Activity act = activityList1.get(i);
			if( activityMap2.containsKey(act.getTracingTag()) ){
				Activity compareActivity = activityMap2.get(act.getTracingTag());
				boolean changeFlag = false;
				if( act.getDescription() != null && compareActivity.getDescription() != null && act.getDescription().getText() != null
					&&	!act.getDescription().getText().equals(compareActivity.getDescription().getText()) ){
					changeFlag = true;
				}
				
				if( !act.getDocumentation().equals(compareActivity.getDocumentation())){
					changeFlag = true;
				}
				
				if( changeFlag ){
					// TODO 프로퍼티 비교 - 프로퍼티 변경시 도형위에 펜 마크 표시
					String style = "{\"stroke\":\"green\",\"stroke-width\":\"5\"}";
					act.getActivityView().setStyle( ProcessDesignerContentPanel.escape(style));
					activityList1.set(i, act);
					
					addActivityStyle(activityList2 , compareActivity , style);
				}
			}else{
				// TODO 삭제 도형시에 빨간색 마크위에 x 표시
				String style = "{\"stroke\":\"red\",\"stroke-width\":\"5\"}";
				act.getActivityView().setStyle( ProcessDesignerContentPanel.escape(style));
				activityList1.set(i, act);
			}
		}
		
		for( int i = 0 ; i < activityList2.size(); i++ ){
			Activity act = activityList2.get(i);
			if( !activityMap1.containsKey(act.getTracingTag()) ){
				String style = "{\"stroke\":\"blue\",\"stroke-width\":\"5\"}";
				act.getActivityView().setStyle( ProcessDesignerContentPanel.escape(style));
				activityList2.set(i, act);
			}
		}
		designerContainer1.setActivityList(activityList1);
		designerContainer2.setActivityList(activityList2);
		
		processViewer.setProcessDesignerContainer(designerContainer1); 
		compareImportFile.getProcessViewer().setProcessDesignerContainer(designerContainer2);
		
		return new Object[]{processViewer, compareImportFile};
	}
	
	public void addActivityStyle(ArrayList<Activity> activityList , Activity act , String style){
		act.getActivityView().setStyle( ProcessDesignerContentPanel.escape(style));
		for( int j = 0 ; j < activityList.size(); j++ ){
			if( act.equals(activityList.get(j)) ){
				activityList.set(j, act);
			}
		}
	}
}
