package org.uengine.kernel.designer.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.common.MetaworksUtil;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.webProcessDesigner.ActivityPanel;
import org.uengine.codi.mw3.webProcessDesigner.ActivityWindow;
import org.uengine.contexts.ComplexType;
import org.uengine.kernel.Activity;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.IDrawDesigne;
import org.uengine.kernel.ParameterContext;
import org.uengine.kernel.ParameterContextPanel;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.ReceiveActivity;

public class HumanActivityView extends ActivityView{
	
	public static final String MAIN_CLASS = "org.uengine.kernel.HumanActivity"; 
	
	public HumanActivityView(){
		setActivityClass(HumanActivityView.MAIN_CLASS);
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	@Override
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object showProperties() throws Exception{
		ModalWindow popup = new ModalWindow();
		
		ActivityWindow activityWindow = new ActivityWindow();
		Activity activity = (Activity)propertiesWindow.getPanel();
		
		ParameterContext[] contexts = null;
		if( activity != null ){
			Class paramClass = activity.getClass();
			// 현재 클레스가 IDrawDesigne 인터페이스를 상속 받았는지 확인
			boolean isDesigner = IDrawDesigne.class.isAssignableFrom(paramClass);
			if( isDesigner ){
				((IDrawDesigne)activity).setParentEditorId(this.getEditorId());
				((IDrawDesigne)activity).drawInit();
			}
			
			boolean isReceiveActivity = ReceiveActivity.class.isAssignableFrom(paramClass);
			if( isReceiveActivity ){
				contexts = ((ReceiveActivity)activity).getParameters();
				if( contexts != null ){
					for(int i=0; i < contexts.length; i++){
						contexts[i].setMetaworksContext(new MetaworksContext());
						contexts[i].getMetaworksContext().setHow("list");
					}
				}
			}
			
		}
		activity.setActivityView(this);
		
		if( "definitionDiffView".equals(this.getViewType()) ){
			activity.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			activity.getDocumentation().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		}else{
			activity.setMetaworksContext(new MetaworksContext());
			activity.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			activity.getDocumentation().setMetaworksContext(new MetaworksContext());
			activity.getDocumentation().getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		}
		// 변수설정
		ParameterContextPanel parameterContextPanel = new ParameterContextPanel();
		parameterContextPanel.setParameterContext(contexts);
		parameterContextPanel.setEditorId(activity.getName() + "_" + activity.getTracingTag());
		parameterContextPanel.setParentEditorId(this.getEditorId());
		parameterContextPanel.load();
		
		activityWindow.getActivityPanel().setActivity(activity);
		activityWindow.getActivityPanel().setDocument(activity.getDocumentation());
		activityWindow.getActivityPanel().setParameterContextPanel(parameterContextPanel);
		if( contexts != null ){
			activityWindow.getActivityPanel().setMetaworksContext(new MetaworksContext());
			activityWindow.getActivityPanel().getMetaworksContext().setHow("humanActivity");
			settingPvValue(activityWindow.getActivityPanel() , activity);
		}
		popup.setTitle(activity.getDescription() != null ? activity.getDescription().getText() : activity.getName().getText() + "[" + activity.getTracingTag() + "]");
		popup.setPanel(activityWindow);
		popup.setWidth(700);
		popup.setHeight(500);
		
		return popup;
	}
	
	public void settingPvValue(ActivityPanel activityPanel , Activity activity){
		Class paramClass = activity.getClass();
		boolean isReceiveActivity = ReceiveActivity.class.isAssignableFrom(paramClass);
		if( isReceiveActivity ){
			ParameterContext[] contexts = ((ReceiveActivity)activity).getParameters();
			if( contexts != null && contexts.length > 0){
				for(int i=0; i < contexts.length; i++){
					ProcessVariable processVariable = contexts[i].getVariable();
					if( processVariable.getDefaultValue() != null && processVariable.getDefaultValue() instanceof ComplexType ){
						String typeId = ((ComplexType)processVariable.getDefaultValue()).getTypeId();
						String imgFilePath = CodiClassLoader.mySourceCodeBase(); 
						String imgFileName = (typeId.substring(1, typeId.lastIndexOf("]"))).replace('.', File.separatorChar) + ".html";
						System.out.println(imgFilePath + File.separatorChar + imgFileName);
						InputStream is = null;
						ByteArrayOutputStream bao = null;
						try{
							bao = new ByteArrayOutputStream();
							File file  = new File(imgFilePath + File.separatorChar + imgFileName);
							is = new FileInputStream(file);
							MetaworksUtil.copyStream(is, bao);
							
							activityPanel.setParameterValue(bao.toString(GlobalContext.ENCODING));
						    
						}catch(Exception e){
							e.printStackTrace();
						}finally {
							if(is != null){
								try {
									is.close();
									is = null;
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							if(bao != null){
								try {
									bao.close();
									bao = null;
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}
}
