package org.uengine.kernel.descriptor;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import javax.swing.JTextField;

import org.uengine.kernel.Activity;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ParameterContext;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.Role;
import org.uengine.kernel.RoleParameterContext;
import org.uengine.kernel.SubProcessActivity;
import org.uengine.kernel.SubProcessParameterContext;
import org.uengine.processdesigner.*;
import org.uengine.processdesigner.inputters.org_uengine_kernel_ParameterContextArrayInput;
import org.uengine.ui.XMLValueInput;
import org.uengine.util.ClientProxy;
import org.uengine.util.UEngineUtil;
import org.metaworks.*;
import org.metaworks.inputter.Inputter;
import org.metaworks.inputter.RadioInput;


/**
 * 
 * @author Jinyoung Jang
 * @author  <a href="mailto:ghbpark@hanwha.co.kr">Sungsoo Park</a>
 * @version    $Id: SubProcessActivityDescriptor.java,v 1.1 2012/02/13 05:29:17 sleepphoenix4 Exp $
 */
public class RuleProcessActivityDescriptor extends SubProcessActivityDescriptor{
	
	public RuleProcessActivityDescriptor() throws Exception{
		super();
	}
	
	public void initialize(final ProcessDesigner pd, Activity activity){
		super.initialize(pd, activity);
		
			
		FieldDescriptor fd;

		
		removeFieldDescriptor("RoleBindings");
		
		
		fd = getFieldDescriptor("DefinitionId");
		XMLValueInput inputter = new XMLValueInput("/processmanager/processDefinitionListXML.jsp?omitVersion=false&objectType=rule_dt"){
			public void onValueChanged() {
				changeBindingArguments((String)getValue());
			}
		};
		

	}
	
	
}