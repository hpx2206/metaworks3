package org.uengine.kernel.descriptor;

import org.uengine.kernel.Activity;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ReceiveActivity;
import org.uengine.processdesigner.*;
import org.uengine.processdesigner.inputters.*;
import org.metaworks.*;

/**
 * @author Jinyoung Jang
 */

public class ReceiveActivityDescriptor extends ActivityDescriptor{

	static{
		fieldOrder.insertElementAt("MessageDefinition", 0);
	}

	public ReceiveActivityDescriptor() throws Exception{
		super();
	}
	
	public void initialize(ProcessDesigner pd, Activity activity){
		
	}
	
}