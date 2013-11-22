package com.defaultcompany.activity;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.uengine.kernel.DefaultActivity;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ProcessInstance;

public class MakeTTSFileActivity extends DefaultActivity {

	private static final long serialVersionUID = GlobalContext.SERIALIZATION_UID;
	
	private String soundUrl;
	private String filePath;
	private String message;
	
	public String getSoundUrl() {
		return soundUrl;
	}

	public void setSoundUrl(String soundUrl) {
		this.soundUrl = soundUrl;
	}
	
	public String getFilePath() {
		return this.filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public MakeTTSFileActivity() {
		super("Save Sound File");
	}
	
	public void saveSoundFile(ProcessInstance instance) throws Exception {
//		URL url = new URL(evaluateContent(instance, this.getSoundUrl()).toString());
//		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
//		File file = new File(this.getFilePath());
//		
//		AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, file);
		
		InputStream is = null;
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			String data = "message=" + URLEncoder.encode(evaluateContent(instance, getMessage()).toString(), GlobalContext.ENCODING);  
			URL url = new URL(getSoundUrl());
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			
			urlConn.setDoOutput(true);
			osw = new OutputStreamWriter(urlConn.getOutputStream());
			osw.write(data);
			osw.flush();
			
			is = urlConn.getInputStream();
			byte[] buff = new byte[1];
			fos = new FileOutputStream(getFilePath());
			
			while(is.read(buff) != -1) {
				fos.write(buff);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) fos.close();
			if (is != null) is.close();
			if (osw != null) osw.close();
		}
	}
	
	protected void executeActivity(ProcessInstance instance) throws Exception {
		saveSoundFile(instance);

		fireComplete(instance);		
    }
	
	public static void main(String[] args) throws Exception{
		MakeTTSFileActivity mtfa = new MakeTTSFileActivity();
		
		mtfa.setSoundUrl("http://localhost:8080/uengine-tts/ttsServer.jsp");
		mtfa.setMessage("닐니리아");
		mtfa.setFilePath("d:\\finalResult.wav");
		mtfa.executeActivity(null);
	}
}
