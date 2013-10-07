package org.uengine.facebook.activities.descriptor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.metaworks.FieldDescriptor;
import org.metaworks.inputter.Inputter;
import org.metaworks.inputter.SelectInput;
import org.uengine.kernel.Activity;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.descriptor.ActivityDescriptor;
import org.uengine.processdesigner.ProcessDesigner;
import org.uengine.util.UEngineUtil;

public class WriteFacebookActivityDescriptor extends ActivityDescriptor {
	
	Inputter mygroupInputter; 
	Inputter myappkeyInputter;
	Inputter accessTokenInputter;
	Inputter friendlistsInputter; //add friendlist
	
	public WriteFacebookActivityDescriptor() throws Exception {
		super();
	}

	@Override
	public void initialize(ProcessDesigner value, Activity activity) {
		super.initialize(value, activity);
		
		FieldDescriptor mygroupFd = this.getFieldDescriptor("Mygroup");
		mygroupInputter = mygroupFd.getInputter();
		
		//add friendlist
		FieldDescriptor friendlistsFd = this.getFieldDescriptor("Friendlists");
		friendlistsInputter = friendlistsFd.getInputter();
		
		FieldDescriptor myappkeyFd = this.getFieldDescriptor("Myappkey");
		myappkeyInputter = myappkeyFd.getInputter();
		
		FieldDescriptor accessTokenFd = this.getFieldDescriptor("Access_token");
		accessTokenInputter = accessTokenFd.getInputter();
		
		myappkeyInputter.addActionListener(new FacebookGroupListActionListener());
		accessTokenInputter.addActionListener(new FacebookGroupListActionListener());
		
	}
	
	class FacebookGroupListActionListener implements ActionListener {
		
		public FacebookGroupListActionListener() {
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String myappkey = (String) myappkeyInputter.getValue();
			String accessToken = (String) accessTokenInputter.getValue();
			
			Map<String, String> formValueMap = new HashMap<String, String>();
			formValueMap.put("myappkey", myappkey);
			formValueMap.put("accessToken", accessToken);
			
			if (UEngineUtil.isNotEmpty(myappkey) && UEngineUtil.isNotEmpty(accessToken)) {
				
				
				try {
					InputStream is = ProcessDesigner.getClientProxy().postStream("/processmanager/facebook/getGroupList.jsp", formValueMap);
					
					Map<String, String> data = (Map<String, String>) GlobalContext.deserialize(is, (Class) null);
					
					Object[] selections = new Object[data.size()];
					Object[] values = new Object[data.size()];
					
					Iterator<String> itr = data.keySet().iterator();
					int i=0;
					while (itr.hasNext()) {
						String key = itr.next();
						String value = data.get(key);
						
						selections[i] = value;
						values[i] = key;
						
						i++;
					}
					
					((SelectInput) mygroupInputter).setSelections(selections);
					((SelectInput) mygroupInputter).setValues(values);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			if(UEngineUtil.isNotEmpty(myappkey) && UEngineUtil.isNotEmpty(accessToken)){
			 
				
				try{
					InputStream is = ProcessDesigner.getClientProxy().postStream("/processmanager/facebook/getFriendList.jsp", formValueMap);
					Map<String, String> data = (Map<String, String>) GlobalContext.deserialize(is, (Class) null);
					
					Object[] selections = new Object[data.size()];
					Object[] values = new Object[data.size()];
					
					Iterator<String> itr = data.keySet().iterator();
					int i=0;
					while (itr.hasNext()) {
						String key = itr.next();
						String value = data.get(key);
						
						selections[i] = value;
						values[i] = key;
						
						i++;
					}
					
					((SelectInput) friendlistsInputter).setSelections(selections);
					((SelectInput) friendlistsInputter).setValues(values);
				}catch (Exception e2) {
					e2.printStackTrace();
				}
				
				
			}
		}
		
	}

}
