package com.defaultcompany.activities;

import org.metaworks.Type;
import org.uengine.kernel.DefaultActivity;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.Role;

public class YouTubeActivity extends DefaultActivity {
	
	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;
	
	public static void metaworksCallback_changeMetadata(Type type) {
		type.setFieldOrder(new String[] { "Auth", "ApplicationName", "Media", "VideoFile" });
		
		type.removeFieldDescriptor("Cost");
		type.removeFieldDescriptor("StatusCode");
	}
	
	public YouTubeActivity() {
		super();
		setName("YouTubeActivity");
		setApplicationName("uEngine-Demo-1");
	}

//	private YouTubeAuth auth;
//		public YouTubeAuth getAuth() {
//			return auth;
//		}
//		public void setAuth(YouTubeAuth auth) {
//			this.auth = auth;
//		}
	
	/**
	 * 롤맵핑 하였을 때 ExtendedProperty 에
	 * youtube_username
	 * youtube_password
	 * youtube_developerKey
	 * 3 가지의 값이 존재하도록 해야 한다.
	 */
	private Role auth;
		public Role getAuth() {
			return auth;
		}
		public void setAuth(Role auth) {
			this.auth = auth;
		}

	private String applicationName;
		public String getApplicationName() {
			return applicationName;
		}
		public void setApplicationName(String applicationName) {
			this.applicationName = applicationName;
		}
		
	private YouTubeMedia media;
		public YouTubeMedia getMedia() {
			return media;
		}	
		public void setMedia(YouTubeMedia media) {
			this.media = media;
		}
		
	private ProcessVariable videoFile;
		public ProcessVariable getVideoFile() {
			return videoFile;
		}
		public void setVideoFile(ProcessVariable videoFile) {
			this.videoFile = videoFile;
		}

	@Override
	protected void executeActivity(ProcessInstance instance) throws Exception {
		
		YouTubeActivityExecuter client = new YouTubeActivityExecuter(System.out);
		client.execute(instance, this);
		
		super.executeActivity(instance);
	}

}
