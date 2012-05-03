package com.defaultcompany.activities;

import org.uengine.kernel.ProcessInstance;

public interface PrecedenceExecutable {
	/*************** For Kitech Approval ***************/
	public void precede(ProcessInstance instance) throws Exception;
}
