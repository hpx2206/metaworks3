package org.metaworks.example.ide;

import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;

public class CodeAssist {

	ArrayList<String> assistances;
		public ArrayList<String> getAssistances() {
			return assistances;
		}
		public void setAssistances(ArrayList<String> assistances) {
			this.assistances = assistances;
		}
		
	String srcCodeObjectId;
		public String getSrcCodeObjectId() {
			return srcCodeObjectId;
		}
		public void setSrcCodeObjectId(String srcCodeObjectId) {
			this.srcCodeObjectId = srcCodeObjectId;
		}
	
}
