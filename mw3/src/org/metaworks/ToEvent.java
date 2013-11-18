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
	
	public ToEvent(Object target, String event){
		this.setTarget(target);
		this.setEvent(event);
	}
	
}
