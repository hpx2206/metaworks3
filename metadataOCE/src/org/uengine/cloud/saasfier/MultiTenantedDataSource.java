package org.uengine.cloud.saasfier;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.AbandonedConfig;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

public class MultiTenantedDataSource implements DataSource{

	public MultiTenantedDataSource(){
		defaultAutoCommit = true;
		defaultReadOnly = null;
		defaultTransactionIsolation = -1;
		defaultCatalog = null;
		driverClassName = null;
		maxActive = 8;
		maxIdle = 8;
		minIdle = 0;
		initialSize = 0;
		maxWait = -1L;
		poolPreparedStatements = false;
		maxOpenPreparedStatements = -1;
		testOnBorrow = true;
		testOnReturn = false;
		timeBetweenEvictionRunsMillis = -1L;
		numTestsPerEvictionRun = 3;
		minEvictableIdleTimeMillis = 1800000L;
		testWhileIdle = false;
		password = null;
		url = null;
		username = null;
		validationQuery = null;
		accessToUnderlyingConnectionAllowed = false;
		restartNeeded = false;
		connectionPool = null;
		connectionProperties = new Properties();
		dataSource = null;
		logWriter = new PrintWriter(System.out);
	}

	public synchronized String getPassword()
	{
		return password;
	}

	public synchronized void setPassword(String password)
	{
		this.password = password;
		restartNeeded = true;
	}

	public synchronized String getUrl()
	{
		return url;
	}

	public synchronized void setUrl(String url)
	{
		this.url = url;
		restartNeeded = true;
	}

	public synchronized String getUsername()
	{
		return username;
	}

	public synchronized void setUsername(String username)
	{
		this.username = username;
		restartNeeded = true;
	}

	public synchronized String getValidationQuery()
	{
		return validationQuery;
	}

	public synchronized void setValidationQuery(String validationQuery)
	{
		if(validationQuery != null && validationQuery.trim().length() > 0)
			this.validationQuery = validationQuery;
		else
			this.validationQuery = null;
		restartNeeded = true;
	}

	public synchronized String getDriverClassName()
	{
		return driverClassName;
	}

	public synchronized void setDriverClassName(String driverClassName)
	{
		if(driverClassName != null && driverClassName.trim().length() > 0)
			this.driverClassName = driverClassName;
		else
			this.driverClassName = null;
		restartNeeded = true;
	}

	HashMap<String, BasicDataSource> baseDataSources = new HashMap<String, BasicDataSource>();

	public Connection getConnection() throws SQLException {

		String tenantId = null;
		
		if(TenantContext.getThreadLocalInstance() != null)
			tenantId = TenantContext.getThreadLocalInstance().getTenantId();
		
		if(tenantId == null)
			tenantId = "default";
		
		BasicDataSource dataSourceForTenant = null;

		if(!baseDataSources.containsKey(tenantId)){

			dataSourceForTenant = new BasicDataSource();
			dataSourceForTenant.setUrl(getUrl().replace("$tenantId", "default".equals(tenantId)?"":"_"+tenantId));
			dataSourceForTenant.setUsername(getUsername());
			dataSourceForTenant.setPassword(getPassword());
			dataSourceForTenant.setValidationQuery(getValidationQuery());
			dataSourceForTenant.setDriverClassName(getDriverClassName());

			System.out.println("==============================================");
			System.out.println("dataSourceForTenant create : " + tenantId);
			
			baseDataSources.put(tenantId, dataSourceForTenant);
		}else{
			// System.out.println("dataSourceForTenant get : " + tenantId);
			
			dataSourceForTenant = baseDataSources.get(tenantId);
		}

		return dataSourceForTenant.getConnection();
	}

	public synchronized void close() throws SQLException {
		Iterator<String> iterator = baseDataSources.keySet().iterator();
	    while (iterator.hasNext()) {
	        String key = (String) iterator.next();
	        
	        System.out.println("dataSourceForTenant close : " + key);
	        
	        BasicDataSource dataSourceForTenant = baseDataSources.get(key);
	        dataSourceForTenant.close();
	    }
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		throw new UnsupportedOperationException("Not supported by MultiTenantedDataSource");
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}
	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}
	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		
	public boolean isDefaultAutoCommit() {
		return defaultAutoCommit;
	}

	public void setDefaultAutoCommit(boolean defaultAutoCommit) {
		this.defaultAutoCommit = defaultAutoCommit;
	}

	public int getDefaultTransactionIsolation() {
		return defaultTransactionIsolation;
	}

	public void setDefaultTransactionIsolation(int defaultTransactionIsolation) {
		this.defaultTransactionIsolation = defaultTransactionIsolation;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	public long getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}

	public boolean isPoolPreparedStatements() {
		return poolPreparedStatements;
	}

	public void setPoolPreparedStatements(boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}

	public int getMaxOpenPreparedStatements() {
		return maxOpenPreparedStatements;
	}

	public void setMaxOpenPreparedStatements(int maxOpenPreparedStatements) {
		this.maxOpenPreparedStatements = maxOpenPreparedStatements;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public int getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}

	public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	public long getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	protected boolean defaultAutoCommit;
	protected Boolean defaultReadOnly;
	protected int defaultTransactionIsolation;
	protected String defaultCatalog;
	protected String driverClassName;
	protected int maxActive;
	protected int maxIdle;
	protected int minIdle;
	protected int initialSize;
	protected long maxWait;
	protected boolean poolPreparedStatements;
	protected int maxOpenPreparedStatements;
	protected boolean testOnBorrow;
	protected boolean testOnReturn;
	protected long timeBetweenEvictionRunsMillis;
	protected int numTestsPerEvictionRun;
	protected long minEvictableIdleTimeMillis;
	protected boolean testWhileIdle;
	protected String password;
	protected String url;
	protected String username;
	protected String validationQuery;
	private boolean accessToUnderlyingConnectionAllowed;
	private boolean restartNeeded;
	protected GenericObjectPool connectionPool;
	protected Properties connectionProperties;
	protected DataSource dataSource;
	protected PrintWriter logWriter;
	private AbandonedConfig abandonedConfig;

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean getRemoveAbandoned(){
		if(abandonedConfig != null)
			return abandonedConfig.getRemoveAbandoned();
		else
			return false;
	}
	
	public void setRemoveAbandoned(boolean removeAbandoned){
		if(abandonedConfig == null)
			abandonedConfig = new AbandonedConfig();
		
		abandonedConfig.setRemoveAbandoned(removeAbandoned);
		restartNeeded = true;
	}
	
	public int getRemoveAbandonedTimeout(){
		if(abandonedConfig != null)
			return abandonedConfig.getRemoveAbandonedTimeout();
		else
			return 300;
	}
	
	public void setRemoveAbandonedTimeout(int removeAbandonedTimeout){
		if(abandonedConfig == null)
			abandonedConfig = new AbandonedConfig();
		
		abandonedConfig.setRemoveAbandonedTimeout(removeAbandonedTimeout);
		restartNeeded = true;
	}	
	
	public boolean getLogAbandoned(){
		if(abandonedConfig != null)
			return abandonedConfig.getLogAbandoned();
		else
			return false;
	}
	
	public void setLogAbandoned(boolean logAbandoned){
		if(abandonedConfig == null)
			abandonedConfig = new AbandonedConfig();
		
		abandonedConfig.setLogAbandoned(logAbandoned);
		restartNeeded = true;
	}	
	
}
