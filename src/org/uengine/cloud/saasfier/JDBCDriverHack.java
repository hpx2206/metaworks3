package org.uengine.cloud.saasfier;

public class JDBCDriverHack{

	public static String changeConnectionString(String original){
		
		if(org.uengine.cloud.saasfier.TenantContext.getThreadLocalInstance()==null){
			new Throwable(" Thread local tenant context is null").printStackTrace();
			
			return original;
		}
		
		original = "jdbc:mysql://localhost:3306/"+ TenantContext.getThreadLocalInstance().getTenantId() +"?useUnicode=true&amp;characterEncoding=UTF8&amp;useOldAliasMetadataBehavior=true";
		
		new Throwable("Conn. info = " + original).printStackTrace();
		
		return original;
	}
	
	
	
	
}