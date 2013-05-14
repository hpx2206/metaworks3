package net.oauth.dna;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.uengine.kernel.GlobalContext;

public class OAuthDB {
	
	public void update(String user_id, String access_token) throws Exception {
			
		String sql = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		Class.forName("cubrid.jdbc.driver.CUBRIDDriver");
		conn = DriverManager.getConnection(
				"jdbc:" + GlobalContext.getPropertyString("cubrid.run.server.db") + ":"
						+ GlobalContext.getPropertyString("cubrid.run.server.ip") + ":" 
						+ GlobalContext.getPropertyString("cubrid.run.server.port") + ":"
						+ GlobalContext.getPropertyString("cubrid.run.server.database") + ":::?charset=utf-8"
		,GlobalContext.getPropertyString("cubrid.run.server.user"), GlobalContext.getPropertyString("cubrid.run.server.password"));
//		conn = DriverManager.getConnection(
//				"jdbc:cubrid:192.168.212.108:33000:uengine:::?charset=utf-8", "dba", "");
		
		sql = "select * from oauth_token where user_id='" + user_id + "'";
		
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			//update
			
			sql = "update oauth_token set access_token= ? where user_id= ?";
			
			pstmt = conn.prepareStatement(sql);
			
            pstmt.setString(1, access_token);
            pstmt.setString(2, user_id);
            pstmt.executeUpdate();
			
		}
		else {
			//insert
			
			sql = "INSERT INTO oauth_token (user_id, access_token) VALUES (?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
            pstmt.setString(1, user_id);
            pstmt.setString(2, access_token);
            pstmt.executeUpdate();
		}
		
		stmt.close();
		pstmt.close();
		rs.close();
        conn.commit();
        conn.close();		
	}
	
//	try {
//
//
//	} catch (SQLException e) {
//		System.err.println(e.getMessage());
//	} catch (Exception e) {
//		System.err.println(e.getMessage());
//	} finally {
//		if (conn != null)
//			conn.close();
//	}
}
