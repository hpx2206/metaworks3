package org.uengine.webservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import net.sf.json.JSONObject;

import org.uengine.kernel.GlobalContext;

@Path("dbrepo")
public class JaxRsDbrepoWebService {

	@GET
	@Path("/getDbrepo/{mode}/{projectName}")
	@Produces("application/json")
	public String _sendMessage(@Context HttpServletRequest req 
			, @Context HttpServletResponse res
			, @PathParam("mode") String mode
			, @PathParam("projectName") String projectName) {
		
		JSONObject obj = null;
		try{
			String className = GlobalContext.getPropertyString("jdbc.driverClassName", "com.mysql.jdbc.Driver");
			String url = GlobalContext.getPropertyString("jdbc.url", "jdbc:mysql://localhost:3306/uengine?useUnicode=true&amp;characterEncoding=UTF8");
			String user = GlobalContext.getPropertyString("jdbc.username", "root");
			String passwd = GlobalContext.getPropertyString("jdbc.password", "u1234");

			
			Class.forName(className);
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			try{
				String query = "select dbUrl, user, password from appdatabase where appid='" + projectName + "' and mode='" + mode + "'";
				con = DriverManager.getConnection(url, user, passwd);
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				
				ResultSetMetaData md = rs.getMetaData();
				int columnCount = md.getColumnCount();
				obj = new JSONObject();
				while (rs.next()) {
					for (int i = 1; i <= columnCount; i++) {
						obj.element(md.getColumnName(i), rs.getObject(i).toString());
					}
				}
			}catch(Exception e){
				
			}finally{
				if (null != stmt) {
					try {
						stmt.cancel();
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (null != con) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return obj.toString();
	}
}
