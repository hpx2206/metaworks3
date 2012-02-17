package org.uengine.codi.deployfilter;

import org.metaworks.dao.TransactionContext;
import org.uengine.kernel.DeployFilter;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.persistence.processdefinition.ProcessDefinitionDAO;
import org.uengine.processmanager.ProcessTransactionContext;

public class CodiDeployFilter implements DeployFilter {

	public void beforeDeploy(ProcessDefinition definition, ProcessTransactionContext tc, String folder, boolean isNew) throws Exception {
		
		ProcessDefinitionDAO procDef = (ProcessDefinitionDAO) tc.findSynchronizedDAO("bpm_procdef", "defid", definition.getBelongingDefinitionId(), ProcessDefinitionDAO.class);
		
		if (isNew) {
			String userId = (String) TransactionContext.getThreadLocalInstance().getRequest().getSession().getAttribute("userId");
			procDef.set("author", userId);
			
		} 
		
	}
}
