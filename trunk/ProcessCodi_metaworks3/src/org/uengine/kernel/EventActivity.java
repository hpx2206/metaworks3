package org.uengine.kernel;

public class EventActivity extends Activity{
	
	String definitionId;
	    public String getDefinitionId() {
	      return definitionId;
	    }
	    public void setDefinitionId(String l) {
	      definitionId = l;
	    }
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	@Override
	protected void executeActivity(ProcessInstance instance) throws Exception {
		// TODO Auto-generated method stub
	}

}
