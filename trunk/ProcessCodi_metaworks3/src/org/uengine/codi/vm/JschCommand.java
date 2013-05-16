package org.uengine.codi.vm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.ProgressMonitor;

import org.uengine.kernel.GlobalContext;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpProgressMonitor;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

public class JschCommand {

	Session jschSession;
		public Session getJschSession() {
			return jschSession;
		}
		public void setJschSession(Session jschSession) {
			this.jschSession = jschSession;
		}
	
	public Session sessionLogin() throws JSchException{
		JSch jsch=new JSch();
		
		String host = GlobalContext.getPropertyString("vm.manager.ip");
		String userId = GlobalContext.getPropertyString("vm.manager.user");
		String passwd = GlobalContext.getPropertyString("vm.manager.password");
		
		Session session=jsch.getSession(userId, host, 22);
		session.setPassword(passwd);
		
		// username and password will be given via UserInfo interface.
		UserInfo ui = new MyUserInfo(){
		      public void showMessage(String message){
		      }
		      public boolean promptYesNo(String message){
		      	return true;
		      }
		};
		
		session.setUserInfo(ui);
		session.connect();
		setJschSession(session);
		return session;
	}
	
	public String runCommand(String command){
		String output = "";
		try{
			if( getJschSession() == null ){
				System.out.println("try connect");
				this.sessionLogin();
			}
			System.out.println("connected");
			ChannelExec channel = (ChannelExec)getJschSession().openChannel("exec");
			
			((ChannelExec)channel).setCommand(command);
			channel.setInputStream(null);
			System.out.println("run command");
			channel.connect();
			System.out.println("finish");
			InputStream in=channel.getInputStream();
			output = setInAndOutStream(channel, in);
			
			channel.disconnect();
		}catch(Exception e){
			if( getJschSession() != null )	getJschSession().disconnect();
		}
		
//		getJschSession().disconnect();
	
		return output;
	}
	
	public void copySettingFileToVM(String host){
		try{
			
			JSch jsch=new JSch();
			
//			String host = "192.168.212.60";
//			//나중에 뺼 코드(if문)
//			if(host == null)
//				host = "192.168.212.60";
//			String userId = "root";
//			String passwd = "redhatxen";
			
			String userId = GlobalContext.getPropertyString("vm.manager.user");
			String passwd = GlobalContext.getPropertyString("vm.manager.password");
			
			
			Session session=jsch.getSession(userId, host, 22);
			session.setPassword(passwd);
			
			// username and password will be given via UserInfo interface.
			UserInfo ui = new MyUserInfo(){
			      public void showMessage(String message){
			      }
			      public boolean promptYesNo(String message){
			      	return true;
			      }
			};
			session.setUserInfo(ui);
			session.connect();
			
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			
			SftpProgressMonitor monitor=new MyProgressMonitor();
			int mode = ChannelSftp.APPEND;
			sftpChannel.put(GlobalContext.getPropertyString("vm.local.filepath") + GlobalContext.getPropertyString("vm.filename.adjustEnv"), GlobalContext.getPropertyString("vm.remote.filepath") + GlobalContext.getPropertyString("vm.filename.adjustEnv"), monitor, mode);
			sftpChannel.put(GlobalContext.getPropertyString("vm.local.filepath") + GlobalContext.getPropertyString("vm.filename.adjustHosts"), GlobalContext.getPropertyString("vm.remote.filepath") + GlobalContext.getPropertyString("vm.filename.adjustHosts"), monitor, mode);
			sftpChannel.exit();
			
			channel.disconnect();
		}catch (Exception e) {
			// TODO: handle exception
		}
//		getJschSession().disconnect();
	}
	
	private static String setInAndOutStream(Channel channel, InputStream in) throws IOException, JSchException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder outPutResult = new StringBuilder("");
        int exitStatus = -100;
        String output = null;
        while (true) {
        	while (true) {
                try {
                    String result = br.readLine();
                    if (result == null || "".equals(result))
                        break;
                    outPutResult.append(result);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    break;
                }
            }
            output = outPutResult.toString();
            System.out.println(output);
            if (channel.isClosed()) { 
                exitStatus = channel.getExitStatus();
                break;
            }
            try{Thread.sleep(1000);}catch(Exception ee){}
        }
        return output;
    }
	
	public static abstract class MyUserInfo implements UserInfo, UIKeyboardInteractive{
		public String getPassword(){ return null; }
		public boolean promptYesNo(String str){ return false; }
		public String getPassphrase(){ return null; }
		public boolean promptPassphrase(String message){ return false; } 
		public boolean promptPassword(String message){ return false; }
		public void showMessage(String message){ }
		public String[] promptKeyboardInteractive(String destination,
		                        String name,
		                        String instruction,
		                        String[] prompt,
		                        boolean[] echo){
		return null;
		}
	}
	
	public static class MyProgressMonitor implements SftpProgressMonitor{
		ProgressMonitor monitor;
		long count=0;
		long max=0;
		public void init(int op, String src, String dest, long max){
			this.max=max;
			monitor=new ProgressMonitor(null, 
											((op==SftpProgressMonitor.PUT)? "put" : "get")+": "+src, "",  0, (int)max
										);
			count=0;
			percent=-1;
			monitor.setProgress((int)this.count);
			monitor.setMillisToDecideToPopup(1000);
		}
		private long percent=-1;
		public boolean count(long count){
			this.count+=count;
			
			if(percent>=this.count*100/max){ return true; }
			percent=this.count*100/max;
			
			monitor.setNote("Completed "+this.count+"("+percent+"%) out of "+max+".");     
			monitor.setProgress((int)this.count);
			
			return !(monitor.isCanceled());
		}
		public void end(){
			monitor.close();
		}
	}
	
}
