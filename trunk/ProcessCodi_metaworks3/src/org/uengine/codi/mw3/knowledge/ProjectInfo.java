
package org.uengine.codi.mw3.knowledge;
import org.metaworks.EventContext;
import org.metaworks.ToEvent;
import org.metaworks.common.MetaworksUtil;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.TopicInfo;
import org.uengine.dbrepo.AppDbRepository;
import org.uengine.dbrepo.DatabaseManager;
import org.uengine.dbrepo.IAppDbRepository;
import org.uengine.dbrepo.MySqlConnector;

public class ProjectInfo extends TopicInfo {

	public ProjectInfo(){
		
	}
	
	public ProjectInfo(Session session, String topicId, String topicName) throws Exception {
		super(session, topicId);
		this.setTitle(topicName);
	}
	
	@Override
	public Object[] remove() throws Exception {
		// 2014.04.24 AppDb work 
		// 프로젝트 삭제시에는 개발만 삭제함 
		AppDbRepository adr = new AppDbRepository();
		adr.setAppid(this.getTitle());
		try{
			IAppDbRepository appDbRepo = adr.findDbRepo(0, this.getTitle());//adr.findDbRepo(0, this.getTitle());
			String dbUser = appDbRepo.getUser();
			String dbName = appDbRepo.getDbname();
			adr.deleteDatabaseMe();
			
			DatabaseManager dbm = new MySqlConnector();
			dbm.removeDbRepository(0, dbName, dbUser);
		}catch(Exception e){
		}
		
		return MetaworksUtil.putObjectArray(MetaworksUtil.makeRefreshObjectArray(super.remove()), new ToEvent(new ProjectPerspective(), EventContext.EVENT_CHANGE));
	}	

	
}
