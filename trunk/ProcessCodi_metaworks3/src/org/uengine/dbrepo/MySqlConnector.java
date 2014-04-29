package org.uengine.dbrepo;


public class MySqlConnector extends BaseConnetor{

	@Override
	protected String getCreateDatabase(String databaseName) {
		return "create database " + databaseName + "";
	}

	
	@Override
	protected String getCreateUser(String user, String passwd) {
		// TODO Auto-generated method stub
		return "create user '" + user + "'@'%' identified by '" + passwd + "'";
	}

	@Override
	protected String getGrantUser(String databaseName, String user, String passwd) {
		// TODO Auto-generated method stub
		return "grant all privileges on " + databaseName + ".* to '" + user + "'@'%' identified by '" + passwd + "'";
	}


	@Override
	protected String getDeleteDatabase(String databaseName) {
		// TODO Auto-generated method stub
		return "drop database " + databaseName + "";
	}


	@Override
	protected String getDeleteUser(String user) {
		// TODO Auto-generated method stub
		return "delete from user where user='" + user + "' and host='%'";
	}


	@Override
	protected String getDeleteGrantUser(String user) {
		// TODO Auto-generated method stub
		return "delete from db where user='" + user + "' and host='%'";
	}

	

}
