package org.uengine.kernel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.uengine.util.ActivityForLoop;

public class StartActivity extends DefaultActivity {
	@Override
	protected void executeActivity(ProcessInstance instance) throws Exception {
//		String ss = instance.getProcessDefinition().getInitiatorHumanActivityReference(instance.getProcessTransactionContext()).getActivity().getTracingTag();
//		System.out.println();
		// TODO flowActivity 의 getInitiatorHumanActivityReference 에서 휴먼을 찾아서 롤 셋팅을 해줘야함
		super.executeActivity(instance);
	}
}
