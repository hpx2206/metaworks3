package org.metaworks;

import java.util.Map;

public class ServiceMethodContext{
	

	public final static String TARGET_AUTO 		= "auto"; 
	public final static String TARGET_SELF 		= "self"; 
	public final static String TARGET_APPEND 	= "append"; 
	public final static String TARGET_PREPEND 	= "prepend"; 
	public final static String TARGET_STICK 	= "stick";
	public final static String TARGET_POPUP 	= "popup";
//	public final static String TARGET_WINDOW 	= "window";
	public final static String TARGET_NONE 		= "none";
	public final static String TARGET_PAGEMOVE 	= "pagemove";
	
	public boolean clientSide;
		public boolean isClientSide() {
			return clientSide;
		}
		public void setClientSide(boolean clientSide) {
			this.clientSide = clientSide;
		}

	public boolean needToConfirm;
		public boolean isNeedToConfirm() {
			return needToConfirm;
		}
		public void setNeedToConfirm(boolean needToConfirm) {
			this.needToConfirm = needToConfirm;
		}

	public boolean callByContent;
		public boolean isCallByContent() {
			return callByContent;
		}
		public void setCallByContent(boolean callByContent) {
			this.callByContent = callByContent;
		}

	String methodName;
		public String getMethodName() {
			return methodName;
		}
		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}
		
	String when;
		public String getWhen() {
			return when;
		}
		public void setWhen(String when) {
			this.when = when;
		}

	String where;
		public String getWhere() {
			return where;
		}
		public void setWhere(String where) {
			this.where = where;
		}

	String target;
		public String getTarget() {
			return target;
		}
		public void setTarget(String target) {
			this.target = target;
		}
	
	String displayName;
		public String getDisplayName() {
			return displayName;
		}
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
		
	boolean childrenGetter;
		public boolean isChildrenGetter() {
			return childrenGetter;
		}
		public void setChildrenGetter(boolean childrenGetter) {
			this.childrenGetter = childrenGetter;
		}

	boolean nameGetter;
		public boolean isNameGetter() {
			return nameGetter;
		}
		public void setNameGetter(boolean nameGetter) {
			this.nameGetter = nameGetter;
		}
		
	
	Map<String, Object> attributes;
		public Map<String, Object> getAttributes() {
			return attributes;
		}
		public void setAttributes(Map<String, Object> attributes) {
			this.attributes = attributes;
		}

		
}
