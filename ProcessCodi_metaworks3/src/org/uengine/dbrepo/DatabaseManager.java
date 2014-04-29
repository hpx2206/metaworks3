package org.uengine.dbrepo;

public interface DatabaseManager {
	
//	QueryResult GetQueryResult(String querySql); 
	
	QueryResult addDbRepository(int mode, String wfNode);
	
	QueryResult removeDbRepository(int mode, String dbName, String dbUser);
	
} 
