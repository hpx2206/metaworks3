package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.viewer.DefaultActivityViewer;
import org.uengine.kernel.viewer.ViewerOptions;

public class TaskInfoPanel  implements ContextAware{
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	String makedHtml;
		public String getMakedHtml() {
			return makedHtml;
		}
		public void setMakedHtml(String makedHtml) {
			this.makedHtml = makedHtml;
		}
	public void load(String instanceId , Activity activity, ProcessInstance instance) throws Exception{
		
		ViewerOptions options = new ViewerOptions();
	    
	    options.setViewType(options.VERTICAL, options.VERTICAL);
	    options.put("flowControl", new Boolean(true));
	//  options.put("dontCollapseScopes", new Boolean(true));
	    options.put("decorated", new Boolean(true));
	    options.put("show hidden activity", new Boolean(true));
	    options.put("ShowAllComplexActivities", new Boolean(true));
	//  options.put("enableEvent_onActivityClicked", new Boolean(true));
	    options.put("align", "center");
	    options.put("locale", "ko");
	    
	    if (instanceId != null) {
	        options.put("enableUserEvent_compensateTo", "Back to here");
	        options.put("enableUserEvent_refreshMultipleInstances_org.uengine.kernel.SubProcessActivity", "Refresh Multiple Instances");
	        options.put("enableUserEvent_showLog", "See Execution Log");
	        //options.put("enableUserEvent_locateWorkItem", "Work Item Handler");
	        options.put("enableUserEvent_locateWorkItem_org.uengine.kernel.ReceiveActivity", "Work Item Handler");
	    }
	    
	    options.put("enableUserEvent_viewFormDefinition_org.uengine.kernel.FormActivity", "View Form Definition");
	    options.put("enableUserEvent_drillInto_org.uengine.kernel.SubProcessActivity", "Drill Into");
	    
	    DefaultActivityViewer dav = new DefaultActivityViewer();
	    StringBuilder sb = dav.render(activity, instance, options);
	    setMakedHtml(sb.toString());
	}
}
