
package org.uengine.codi.mw3.knowledge;
import org.metaworks.EventContext;
import org.metaworks.ToEvent;
import org.metaworks.common.MetaworksUtil;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.TopicInfo;

public class ProjectInfo extends TopicInfo {

	public ProjectInfo(){
		
	}
	
	public ProjectInfo(Session session, String topicId) throws Exception {
		super(session, topicId);
	}
	
	@Override
	public Object[] remove() throws Exception {
		return MetaworksUtil.putObjectArray(MetaworksUtil.makeRefreshObjectArray(super.remove()), new ToEvent(new ProjectPerspective(), EventContext.EVENT_CHANGE));
	}	

	
}
