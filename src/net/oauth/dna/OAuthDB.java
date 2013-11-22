package net.oauth.dna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.metaworks.dao.TransactionContext;

public class OAuthDB {
	
	public void update(String user_id, String access_token) throws Exception {
			
		String sql = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;		
		  
		try {
			
			sql = "select * from oauth_token where user_id='" + user_id + "'";
			
			conn = TransactionContext.getThreadLocalInstance().getConnection();
			
			pstmt = conn.prepareStatement(sql);			
			rs = pstmt.executeQuery(sql);			
			
			if(rs.next()) {
				//update
				
				sql = "update oauth_token set access_token='" + access_token + "' where user_id='" + user_id + "'";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				
			}
			else {
				//insert
				
				sql = "insert into oauth_token (user_id, access_token) values ('" + user_id +"', '" + access_token +  "')";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			}
			
			conn.commit();
				
			if(rs != null)
				rs.close();	
			
		} catch (Exception e) {
			
			conn.rollback();
			
			e.printStackTrace();
			
			String message = e.getMessage();			
			throw new Exception(message);
		}		
		finally{
			
			try{
				if(conn != null){
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle){			
			}
			
			try {
				if(stmt != null){
					stmt.close();
					stmt = null;
				}
			} catch (SQLException sqle){						
			}
			
			try {
				if(pstmt != null){
					pstmt.close();
					pstmt = null;
				}
			} catch (SQLException sqle){						
			}	
			
		}
		
	}
	
}
