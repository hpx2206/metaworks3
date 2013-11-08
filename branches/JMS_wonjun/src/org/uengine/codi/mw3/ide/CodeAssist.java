package org.uengine.codi.mw3.ide;

import java.util.List;
import java.util.Map;

public class CodeAssist {

	String editorId;
		public String getEditorId() {
			return editorId;
		}
		public void setEditorId(String editorId) {
			this.editorId = editorId;
		}
		
	Map<String, List<String>> assistMap;
		public Map<String, List<String>> getAssistMap() {
			return assistMap;
		}
		public void setAssistMap(Map<String, List<String>> assistMap) {
			this.assistMap = assistMap;
		}
}
