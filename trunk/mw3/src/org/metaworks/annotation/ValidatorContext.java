package org.metaworks.annotation;

public class ValidatorContext {

	public final static String EVENT_KEYUP = "keyup";

	public final static String VALIDATE_NULL = "isnull";
	public final static String VALIDATE_MAXBYTE = "maxbyte";
	public final static String VALIDATE_MAXLENGTH = "maxlength";
	public final static String VALIDATE_MINLENGTH = "minlength";
	public final static String VALIDATE_NUMBERZERO = "numberzero";
	public final static String VALIDATE_HTMLTAG = "htmltag";
	public final static String VALIDATE_SCRIPT = "script";
	
	public String name;
		public String getName() {
			return this.name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
	public String message;		
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}

	public String[] events;
		public String[] getEvents() {
			return events;
		}
		public void setEvents(String[] events) {
			this.events = events;
		}
		
	public String[] options;
		public String[] getOptions() {
			return this.options;
		}
		public void setOptions(String[] options) {
			this.options = options;
		}	
}