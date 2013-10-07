package com.defaultcompany.web.comment;

import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.uengine.processmanager.SimpleTransactionContext;
import org.uengine.ui.list.exception.UEngineException;
import org.uengine.util.dao.ConnectionFactory;
import org.uengine.util.dao.GenericDAO;
import org.uengine.util.dao.IDAO;

public class CommentDAO {
    
	ConnectionFactory con;
	
	public CommentDAO(ConnectionFactory con){
		this.con = con;
	}
	/**
	 * Insert Comment
	 * @param instid
	 * @param userid
	 * @param bpm_comment
	 * @param loggedUserGlobalCom
	 * @return
	 */
    public JSONArray insertCommentList(String instid, String userid, String bpm_comment, String loggedUserGlobalCom) {
    	
    	StringBuffer selectMaxidxSQL = new StringBuffer();
    	selectMaxidxSQL.append(" SELECT (MAX(IDX)+1) AS MAXCNT FROM BPM_COMMENT ");
    	
    	StringBuffer insertCommentSQL = new StringBuffer();
    	
    	// HSQL - TIMESTAMP
    	insertCommentSQL.append("INSERT INTO BPM_COMMENT(IDX, INSTID, ENDPOINT, COMCODE, COMMENT, CREATED_DATE) VALUES(?idx, ?instid, ?endpoint, ?comcode, ?comment, CURRENT_TIMESTAMP)");
        
    	// MYSQL - TIMESTAMP
//    	insertCommentSQL.append("INSERT INTO BPM_COMMENT(IDX, INSTID, ENDPOINT, COMCODE, COMMENT, CREATED_DATE) VALUES(?idx, ?instid, ?endpoint, ?comcode, ?comment, NOW())");
    	
        try {
        	IDAO sdao = (IDAO)GenericDAO.createDAOImpl(con, selectMaxidxSQL.toString(), IDAO.class);
        	sdao.select();
        	String idx = "1";
        	while(sdao.next())
    			idx = sdao.getString("MAXCNT");
        	
            IDAO idao = (IDAO)GenericDAO.createDAOImpl(con, insertCommentSQL.toString(), IDAO.class);
            idao.set("idx"		, (idx == null||"null".equals(idx))?"1":idx);
            idao.set("instid"	, instid);
            idao.set("endpoint"	, userid);
            idao.set("comcode"	, loggedUserGlobalCom);
            idao.set("comment"	, bpm_comment);
            idao.insert();
            
        } catch (Exception ex) {
            // TODO Auto-generated catch block
            new UEngineException(ex, "insertComment Exception");
        }finally{
        	
        }
    	        
        return selectCommentList(instid, userid, loggedUserGlobalCom, new StringBuffer());
    }
    
    /**
     * Delete Comment
     * @param instid
     * @param userid
     * @param loggedUserGlobalCom
     * @return
     */
    public JSONArray deleteCommentList(String idx, String instid, String userid, String loggedUserGlobalCom) {
    
    	StringBuffer deleteCommentSQL = new StringBuffer();
    	deleteCommentSQL.append("DELETE FROM BPM_COMMENT WHERE IDX=?idx ");
        
        try {
            IDAO idao = (IDAO)GenericDAO.createDAOImpl(con, deleteCommentSQL.toString(), IDAO.class);
            idao.set("idx"	, idx);
            idao.update();
            
        } catch (Exception ex) {
            // TODO Auto-generated catch block
            new UEngineException(ex, "deleteComment Exception");
        }finally{
        	
        }
    	
    	return selectCommentList(instid, userid, loggedUserGlobalCom, new StringBuffer());
    }
    
    /**
     * Select Comment List
     * @param instid
     * @param userid
     * @param loggedUserGlobalCom
     * @param table
     * @return
     */
    public JSONArray selectCommentList(String instid, String userid, String loggedUserGlobalCom, StringBuffer table){
    	JSONArray jsonArray = new JSONArray();
        
        StringBuffer selectCommentSQL = new StringBuffer();
        selectCommentSQL.append(" SELECT IDX, INSTID, ENDPOINT, COMMENT, TO_CHAR(CREATED_DATE, 'YYYY-MM-DD HH:mm:SS') CREATED_DATE ")
		      			.append(" FROM BPM_COMMENT 			")
		      			.append(" WHERE INSTID  = ?instid 	")
		      			.append("   AND COMCODE = ?comcode	")
		      			.append(" ORDER BY CREATED_DATE DESC");
        SimpleTransactionContext stc = new SimpleTransactionContext();
        try {
            IDAO taskDao = (IDAO)GenericDAO.createDAOImpl(stc, selectCommentSQL.toString(), IDAO.class);
            taskDao.set("instid" , instid);
            taskDao.set("comcode", loggedUserGlobalCom);
            taskDao.select();
            
            if (taskDao.size() > 0) {            	
            	JSONObject json = null;
            	while(taskDao.next()) {
                    json = new JSONObject();
                    json.put("idx"			, taskDao.getString("IDX"));
                    json.put("instid"		, taskDao.getString("INSTID"));
                    json.put("endpoint"		, taskDao.getString("ENDPOINT"));
                    json.put("comment"		, taskDao.getString("COMMENT"));
                    json.put("created_date"	, taskDao.getString("CREATED_DATE"));
                    jsonArray.add(json);
                }
            	
            }
            
        } catch (Exception ex) {
            new UEngineException(ex, "getCommentList Exception");
        }finally{
        	if(con !=null) {
        		try {
					if(con.getConnection() != null)
						con.getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
        
        return jsonArray;
    }
}
