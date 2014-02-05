package org.metaworks;

public class ToEvent {
	
	Object target;
		public Object getTarget() {
			return target;
		}
		public void setTarget(Object target) {
			this.target = target;
		}
	
	String event;
		public String getEvent() {
			return event;
		}
		public void setEvent(String event) {
			this.event = event;
		}
	
	boolean match;
		public boolean isMatch() {
			return match;
		}
		public void setMatch(boolean match) {
			this.match = match;
		}
	
	public ToEvent(Object target, String event){
		this(target, event, false);
	}
	
	public ToEvent(Object target, String event, boolean match){
		this.setTarget(target);
		this.setEvent(event);
		this.setMatch(match);
	}
	
}
