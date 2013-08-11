package org.uengine.codi.mw3.ide.compare;

import java.util.ArrayList;
import java.util.HashMap;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.Choice;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContainer;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContentPanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewer;
import org.uengine.kernel.Activity;

public class CompareOriginFilePanel {
	
	static final String FILE_LOCATION = "source";   
	
	String selectedProcessAlias;
		public String getSelectedProcessAlias() {
			return selectedProcessAlias;
		}
		public void setSelectedProcessAlias(String selectedProcessAlias) {
			this.selectedProcessAlias = selectedProcessAlias;
		}
	CompareFileNavigator compareFileNavigator; 
		public CompareFileNavigator getCompareFileNavigator() {
			return compareFileNavigator;
		}
		public void setCompareFileNavigator(CompareFileNavigator compareFileNavigator) {
			this.compareFileNavigator = compareFileNavigator;
		}
	CompareOriginFile compareOriginFile;
		public CompareOriginFile getCompareOriginFile() {
			return compareOriginFile;
		}
		public void setCompareOriginFile(CompareOriginFile compareOriginFile) {
			this.compareOriginFile = compareOriginFile;
		}
	Choice nodeSearch;
		@Face(ejsPath="dwr/metaworks/org/metaworks/widget/ChoiceCombo.ejs")
		public Choice getNodeSearch() {
			return nodeSearch;
		}
		public void setNodeSearch(Choice nodeSearch) {
			this.nodeSearch = nodeSearch;
		}
	
	public CompareOriginFilePanel(){
	}
	
	public void load() throws Exception{
		
		Choice nodeSearch = new Choice();
		nodeSearch.add("Origin Process", "0");
		nodeSearch.add("Import Process", "1");
		nodeSearch.setSelected("0");
		
		setNodeSearch(nodeSearch);
		
		compareFileNavigator = new CompareFileNavigator();
		compareFileNavigator.setId(CompareOriginFilePanel.FILE_LOCATION);
		compareFileNavigator.load();
		
		compareOriginFile = new CompareOriginFile();
		compareOriginFile.setSelectedProcessAlias(selectedProcessAlias);
		compareOriginFile.load();
		
	}
	
	@AutowiredFromClient
	public CompareImportFile compareImportFile;
	
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_AUTO)
	public Object[] processMerge() throws Exception{
		
		HashMap<String , Activity> activityMap1 = new HashMap<String , Activity>(); 
		HashMap<String , Activity> activityMap2 = new HashMap<String , Activity>(); 
		ProcessViewer originProcessViewer = compareOriginFile.getProcessViewer();
		originProcessViewer.setProcessDesignerContainer(new ProcessDesignerContainer());
		originProcessViewer.getProcessDesignerContainer().setEditorId(originProcessViewer.getAlias());
		originProcessViewer.load();
		ProcessDesignerContainer designerContainer1 = originProcessViewer.getProcessDesignerContainer();
		ArrayList<Activity> activityList1 = designerContainer1.getActivityList();
		for( Activity act : activityList1 ){
			activityMap1.put(act.getTracingTag() , act);
		}
		
		ProcessViewer compareProcessViewer = compareImportFile.getProcessViewer();
		compareProcessViewer.setProcessDesignerContainer(new ProcessDesignerContainer());
		compareProcessViewer.getProcessDesignerContainer().setEditorId(compareProcessViewer.getAlias());
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
		
		compareOriginFile.getProcessViewer().setProcessDesignerContainer(designerContainer1); 
		compareImportFile.getProcessViewer().setProcessDesignerContainer(designerContainer2);
		
		return new Object[]{compareOriginFile, compareImportFile};
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
