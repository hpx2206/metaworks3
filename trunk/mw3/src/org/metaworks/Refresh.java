package org.metaworks;

public class Refresh {
	
	Object target;
		public Object getTarget() {
			return target;
		}
		public void setTarget(Object target) {
			this.target = target;
		}

	boolean match;
		public boolean isMatch() {
			return match;
		}
		public void setMatch(boolean match) {
			this.match = match;
		}
		
	public Refresh(Object target){
		this(target, false);
	}
	
	public Refresh(Object target, boolean match){
		this.setTarget(target); //TODO: need remove detail properties except the key value or clone a new key object containing the only key parts for network optimization
		this.setMatch(match);
	}
}
