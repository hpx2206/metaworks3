/*
 * Created on 2004. 11. 3.
 */
package org.metaworks.dao;

import java.util.Calendar;
import java.util.Map;

/**
 * @author Jinyoung Jang
 * @author Jong-uk Jeong
 */

public abstract class DAOFactory{
	public static String USE_CLASS_NAME;
	
	ConnectionFactory connectionFactory;
		public ConnectionFactory getConnectionFactory() {
			return connectionFactory;
		}
		public void setConnectionFactory(ConnectionFactory factory) {
			connectionFactory = factory;
		}
		
	abstract public KeyGeneratorDAO createKeyGenerator(String forWhat, Map options) throws Exception;
	
	abstract public String getSequenceSql(String seqName) throws Exception;
	
	abstract public String getDBMSProductName() throws Exception;

	
	
	
	
	
	public static DAOFactory getInstance(ConnectionFactory tc){
		DAOFactory daoFactory;
		try{
			USE_CLASS_NAME = "org.metaworks.dao.MySQLDAOFactory";//GlobalContext.getPropertyString("daofactory.class");
			daoFactory = (DAOFactory)Thread.currentThread().getContextClassLoader().loadClass(USE_CLASS_NAME).newInstance();
		}catch(Exception e){
			e.printStackTrace();
			daoFactory = new MySQLDAOFactory();
		}
		
		daoFactory.setConnectionFactory(tc);
		
		return daoFactory;
	}
	
	
	
}
