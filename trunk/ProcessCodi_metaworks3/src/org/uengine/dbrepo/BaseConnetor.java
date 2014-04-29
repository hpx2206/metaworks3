package org.uengine.dbrepo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.uengine.kernel.GlobalContext;


public abstract class BaseConnetor implements DatabaseManager{
	
	public static String devUser = GlobalContext.getPropertyString("prod.dbrepo.jdbc.username", "u1234");
	public static String devPasswd = GlobalContext.getPropertyString("prod.dbrepo.jdbc.password", "u1234");
	public static String devIp = GlobalContext.getPropertyString("prod.dbrepo.jdbc.ip", "localhost");
	public static String devPort = GlobalContext.getPropertyString("prod.dbrepo.jdbc.port", "3306");
	
	public static String prodUser = GlobalContext.getPropertyString("prod.dbrepo.jdbc.username", "u1234");
	public static String prodPasswd = GlobalContext.getPropertyString("prod.dbrepo.jdbc.password", "u1234");
	public static String prodIp = GlobalContext.getPropertyString("prod.dbrepo.jdbc.ip", "localhost");
	public static String prodPort = GlobalContext.getPropertyString("prod.dbrepo.jdbc.port", "3306");
	
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	protected abstract String getCreateDatabase(String databaseName);
	protected abstract String getCreateUser(String user, String passwd);
	protected abstract String getGrantUser(String databaseName, String user, String passwd);

	protected abstract String getDeleteDatabase(String databaseName);
	protected abstract String getDeleteUser(String user);
	protected abstract String getDeleteGrantUser(String user);
	

	protected QueryResult queryResult(int mode, String querySql){
		
		String user = mode == 0 ? devUser : prodUser;
		String passwd = mode == 0 ? devPasswd : prodPasswd;
		String ip = mode == 0 ? devIp : prodIp;
		String port = mode == 0 ? devPort : prodPort;
		
		QueryResult result = null;
		String connectDB = "jdbc:mysql://localhost:3306/mysql"; 
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(connectDB, user, passwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(querySql);
			result = new QueryResult(rs);
		} catch (SQLException e) {
			if(null == result){
				result = new QueryResult();
			}
			System.out.println("***********"+e.getMessage()+"***************");
			result.errorCode = 1;
			result.errorMsg = e.getMessage();
		} finally {
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
		return result;
	}


	protected QueryResult queryDDLResult(int mode, String querySql){
		
		String user = mode == 0 ? devUser : prodUser;
		String passwd = mode == 0 ? devPasswd : prodPasswd;
		String ip = mode == 0 ? devIp : prodIp;
		String port = mode == 0 ? devPort : prodPort;

		QueryResult result = new QueryResult();
		String connectDB = "jdbc:mysql://localhost:3306/mysql"; 
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int ret = 0;
		try {
			con = DriverManager.getConnection(connectDB, user, passwd);
			stmt = con.createStatement();
			ret = stmt.executeUpdate(querySql);
			if(ret == 1){
				result.errorCode = 0;
			}else{
				result.errorCode = 1;
			}


		} catch (SQLException e) {
			System.out.println("***********"+e.getMessage()+"***************");
			result.errorCode = 1;
			result.errorMsg = e.getMessage();
		} finally {
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
		return result;
	}

	@Override
	public QueryResult addDbRepository(int mode, String proId) {
		long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyymmddhhmmss");
		QueryResult result = null;
		String uuid = UUID.randomUUID().toString();
		String[] userInfo = uuid.split("-");
		String dbName   = "Codi" + uuid.replaceAll("-", "");
		String dbUser   = userInfo[1].toString() + proId;
		String dbPasswd = userInfo[2].toString() + userInfo[3].toString();

		String sql = this.getCreateDatabase(dbName);
		result = this.queryDDLResult(mode, sql);

//		if(result.errorCode != 0){
//			result.errorMsg = String.format("sql error %s ", sql);
//			return result;
//		}
		
		
		sql = this.getCreateUser(dbUser, dbPasswd);
		result = this.queryDDLResult(mode, sql);

		sql = this.getGrantUser(dbName, dbUser, dbPasswd);
		result = this.queryResult(mode, sql);

		System.out.println("username : " + dbUser);
		System.out.println("password : " + dbPasswd);
		System.out.println("db name  : " + dbName);

		List<Map<String, String>> listRet = new ArrayList<Map<String, String>>();
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("dbname", dbName);
		resultMap.put("user", dbUser);
		resultMap.put("password", dbPasswd);
		listRet.add(resultMap);
		
		result.listResult = listRet;

		return result;
	}


	@Override
	public QueryResult removeDbRepository(int mode, String dbName, String dbUser) {
		QueryResult result = null;

		String sql = this.getDeleteDatabase(dbName);
		result = this.queryDDLResult(mode, sql);

		//		if(result.errorCode != 0){
		//			result.errorMsg = String.format("sql error %s ", sql);
		//			return result;
		//		}

		sql = this.getDeleteUser(dbUser);
		System.out.println("sql del user : " + sql);

		result = this.queryDDLResult(mode, sql);

		sql = this.getDeleteGrantUser(dbUser);
		System.out.println("sql del Grantuser : " + sql);
		result = this.queryDDLResult(mode, sql);


		System.out.println("db name  : " + dbName);

		return result;
	}



//	@Override
//	public QueryResult GetQueryResult(String querySql) {
//		QueryResult result = this.queryResult(querySql);
//		return result;
//	}



}
