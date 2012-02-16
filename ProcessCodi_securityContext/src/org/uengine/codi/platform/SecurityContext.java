package org.uengine.codi.platform;

/**
 * This class should not have any dependencies, so, this class is separated from other project.
 * @author jyjang
 *
 */
public class SecurityContext {
    static ThreadLocal<SecurityContext> local = new ThreadLocal<SecurityContext>();

	boolean needSecurityCheck;
	
		public boolean isNeedSecurityCheck() {
			return needSecurityCheck;
		}
		public void setNeedSecurityCheck(boolean needSecurityCheck) {
			this.needSecurityCheck = needSecurityCheck;
		}

		
	public static SecurityContext getThreadLocalInstance(){
		SecurityContext sc = local.get();
		
		if(sc==null){
			sc = new SecurityContext();
			local.set(sc);
		}
		
		return sc;
	}

}
