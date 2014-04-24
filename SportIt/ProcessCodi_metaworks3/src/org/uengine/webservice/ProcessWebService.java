package org.uengine.webservice;

import org.uengine.codi.CodiProcessManagerBean;
import org.uengine.codi.MetaworksUEngineSpringConnectionAdapter;

public class ProcessWebService {

	protected String _start(String definitionName) throws Exception{
		
		MetaworksUEngineSpringConnectionAdapter connectionAdapter = new MetaworksUEngineSpringConnectionAdapter();
		
		CodiProcessManagerBean pm = new CodiProcessManagerBean();
		pm.setConnectionFactory(connectionAdapter);
		pm.setAutoCloseConnection(false);
		pm.setManagedTransaction(true);
		
		String instId = pm.initializeProcess(definitionName);
		pm.executeProcess(instId);
		pm.applyChanges();
		
		return instId;
	}
}
