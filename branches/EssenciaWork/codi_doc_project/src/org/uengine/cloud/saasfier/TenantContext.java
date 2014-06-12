package org.uengine.cloud.saasfier;



public class TenantContext{
	
    static ThreadLocal<TenantContext> local = new ThreadLocal<TenantContext>();

    String tenantId;
		public String getTenantId() {
			return tenantId;
		}
		public void setTenantId(String tenantId) {
			this.tenantId = tenantId;
		}


	public TenantContext(String tenantId){
//		if(getThreadLocalInstance()!=null)
//			throw new RuntimeException("There's uncommitted transactionContext remains.");
		
		this.tenantId = tenantId;
		
//new Throwable("TenantId is recognized: " + tenantId).printStackTrace();
		
		local.set(this);

	}
	
	
	public static TenantContext getThreadLocalInstance(){
		TenantContext tc = local.get();
		
		return tc;
	}

	
}