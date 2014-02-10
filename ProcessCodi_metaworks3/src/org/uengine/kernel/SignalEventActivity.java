package org.uengine.kernel;

public class SignalEventActivity  extends EventActivity implements MessageListener{

	@Override
	protected void executeActivity(ProcessInstance instance) throws Exception {
		
		//start listens...
		
	}
	@Override
	public boolean onMessage(ProcessInstance instance, Object payload)
			throws Exception {
		
//		..... 여기서 다음 액티비티를 실행할 수 있도록 해주거나, 그냥 이 액티비티를 실행완료 이벤트를 주면 상위에 전달되어, 다음 트랜지션에 연결된 액티비티가 실행될까?
//		fireCompleted(payload); //??? 될까... 이렇게만 해도??
				
		return false;
	}

	@Override
	public String getMessage() {
		return getClass().getName();  //just simply return the event name as it's classname.
	}

}
