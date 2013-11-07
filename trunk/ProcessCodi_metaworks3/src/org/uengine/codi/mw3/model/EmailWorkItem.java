package org.uengine.codi.mw3.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.metaworks.annotation.Hidden;
import org.uengine.codi.mw3.admin.WebEditor;
import org.uengine.kernel.FormActivity;
import org.uengine.util.UEngineUtil;


public class EmailWorkItem extends MemoWorkItem{
	
	public EmailWorkItem(){
		setType("email");		
		setMemo(new WebEditor());
	}
	
	@Hidden(on=false)
	public WebEditor getMemo() {
		// TODO Auto-generated method stub
		return super.getMemo();
	}
	ArrayList<User> ccUsers;
		public ArrayList<User> getCcUsers() {
			return ccUsers;
		}
		public void setCcUsers(ArrayList<User> ccUsers) {
			this.ccUsers = ccUsers;
		}
	ArrayList<User> bccUsers;
		public ArrayList<User> getBccUsers() {
			return bccUsers;
		}
		public void setBccUsers(ArrayList<User> bccUsers) {
			this.bccUsers = bccUsers;
		}

	@Override
	public Object[] add() throws Exception {
		
		if(getMemo()!=null && getMemo().getContents()!=null){
			
			//if there is no title has been entered, use the first line of content:
			if(title==null || title.trim().length()==0) 
				try{
					//TODO: since the contents contains some multiple lines of html tags, so it cannot parses real next line of the text.
					title = getMemo().getContents().substring(0, getMemo().getContents().indexOf('\n'));
				}
			catch(Exception e){}
			if( getWriter() != null){
				IUser fromUser = getWriter();
				setExt1(fromUser.getUserId());
			}
			
			if( ccUsers != null && ccUsers.size() > 0 ){
				String ext2 = "";
				for( int i = 0; i < ccUsers.size(); i++){
					User ccUser = ccUsers.get(i);
					if( i != 0){
						ext2 = ext2 + ",";
					}
					ext2 +=  ccUser.getUserId();
				}
				setExt2(ext2);
			}
			if( bccUsers != null && bccUsers.size() > 0 ){
				String ext3 = "";
				for( int i = 0; i < bccUsers.size(); i++){
					User bccUser = bccUsers.get(i);
					if( i != 0){
						ext3 = ext3 + ",";
					}
					ext3 +=  bccUser.getUserId();
				}
				setExt3(ext3);
			}
			
			if(getMemo().getContents().length() > 2990){
				
				String relativeFilePath = UEngineUtil.getCalendarDir() + "/email" + getInstId() + "_" + System.currentTimeMillis() + ".html";
				String absoluteFilePath = FormActivity.FILE_SYSTEM_DIR + relativeFilePath;
				
				File contentFile = new File(absoluteFilePath);
				contentFile.getParentFile().mkdirs();
				
				PrintWriter fos = new PrintWriter(contentFile);
				fos.write(getMemo().getContents());
				fos.close();
				
				setExtFile(relativeFilePath);

				getMemo().setContents("...loading...");
					
			}

		}
		
		return super.add();
	}

	@Override
	public void loadContents() throws Exception {
		if(getExtFile()!=null){
			
			ByteArrayOutputStream bao = new ByteArrayOutputStream();

			String absoluteFilePath = FormActivity.FILE_SYSTEM_DIR + getExtFile();

			UEngineUtil.copyStream(new FileInputStream(absoluteFilePath), bao);
			
//			this.setContent(bao.toString());
			this.getMemo().setContents(bao.toString());
			setContentLoaded(true);

		}else{
			this.getMemo().setContents(this.getContent());
			setContentLoaded(true);
		}
	}
}
