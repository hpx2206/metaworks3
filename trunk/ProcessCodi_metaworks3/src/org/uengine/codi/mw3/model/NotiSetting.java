package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.component.SelectBox;
import org.metaworks.dao.Database;
import org.metaworks.widget.ModalWindow;

public class NotiSetting extends Database<INotiSetting> implements INotiSetting{
	
	int id;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
	String userId;
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		
	boolean checkLogin;
		public boolean isCheckLogin() {
			return checkLogin;
		}
		public void setCheckLogin(boolean checkLogin) {
			this.checkLogin = checkLogin;
		}
	
	boolean writeBookmark;
		public boolean isWriteBookmark() {
			return writeBookmark;
		}
		public void setWriteBookmark(boolean writeBookmark) {
			this.writeBookmark = writeBookmark;
		}
	
	boolean notiAdvice;
		public boolean isNotiAdvice() {
			return notiAdvice;
		}
		public void setNotiAdvice(boolean notiAdvice) {
			this.notiAdvice = notiAdvice;
		}
	
	boolean modiUser;
		public boolean isModiUser() {
			return modiUser;
		}
		public void setModiUser(boolean modiUser) {
			this.modiUser = modiUser;
		}
	
	boolean modiTopic;
		public boolean isModiTopic() {
			return modiTopic;
		}
		public void setModiTopic(boolean modiTopic) {
			this.modiTopic = modiTopic;
		}
	
	boolean modiOrgan;
		public boolean isModiOrgan() {
			return modiOrgan;
		}
		public void setModiOrgan(boolean modiOrgan) {
			this.modiOrgan = modiOrgan;
		}
	
	boolean writeTopic;
		public boolean isWriteTopic() {
			return writeTopic;
		}
		public void setWriteTopic(boolean writeTopic) {
			this.writeTopic = writeTopic;
		}
		
	boolean writeOrgan;
		public boolean isWriteOrgan() {
			return writeOrgan;
		}
		public void setWriteOrgan(boolean writeOrgan) {
			this.writeOrgan = writeOrgan;
		}
	
	boolean writeInstance;
		public boolean isWriteInstance() {
			return writeInstance;
		}
		public void setWriteInstance(boolean writeInstance) {
			this.writeInstance = writeInstance;
		}
	
	boolean inviteTopic;
		public boolean isInviteTopic() {
			return inviteTopic;
		}
		public void setInviteTopic(boolean inviteTopic) {
			this.inviteTopic = inviteTopic;
		}
	
	boolean inviteOrgan;
		public boolean isInviteOrgan() {
			return inviteOrgan;
		}
		public void setInviteOrgan(boolean inviteOrgan) {
			this.inviteOrgan = inviteOrgan;
		}
	
	boolean addFriend;
		public boolean isAddFriend() {
			return addFriend;
		}
		public void setAddFriend(boolean addFriend) {
			this.addFriend = addFriend;
		}
	
	boolean beforehandNoti;
		public boolean isBeforehandNoti() {
			return beforehandNoti;
		}
		public void setBeforehandNoti(boolean beforehandNoti) {
			this.beforehandNoti = beforehandNoti;
		}
	
	boolean notiTime;
		public boolean isNotiTime() {
			return notiTime;
		}
		public void setNotiTime(boolean notiTime) {
			this.notiTime = notiTime;
		}
	
	boolean notiEmail;
		public boolean isNotiEmail() {
			return notiEmail;
		}
		public void setNotiEmail(boolean notiEmail) {
			this.notiEmail = notiEmail;
		}
		
	PortraitImageFile imageFile;		
		public PortraitImageFile getImageFile() {
			return imageFile;
		}
		public void setImageFile(PortraitImageFile imageFile) {
			this.imageFile = imageFile;
		}
	
	String empCode;
		public String getEmpCode() {
			return empCode;
		}
		public void setEmpCode(String empCode) {
			this.empCode = empCode;
		}
		
	SelectBox selectTime;
		public SelectBox getSelectTime() {
			return selectTime;
		}
		public void setSelectTime(SelectBox selectTime) {
			this.selectTime = selectTime;
		}
	
	String defaultNotiTime;
		public String getDefaultNotiTime() {
			return defaultNotiTime;
		}
		public void setDefaultNotiTime(String defaultNotiTime) {
			this.defaultNotiTime = defaultNotiTime;
		}
		
		
	public Remover save() throws Exception{
		this.setDefaultNotiTime(this.getSelectTime().getSelected());
		this.syncToDatabaseMe();
		
		return new Remover(new ModalWindow());
	}
	
	public INotiSetting findByUserId(String userId) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ");
		sb.append("notisetting ");
		sb.append("where userId=?userId");
		
		INotiSetting findNotiSetting = (INotiSetting) sql(sb.toString());
		findNotiSetting.set("userId", userId);
		findNotiSetting.select();
		
		findNotiSetting.setMetaworksContext(this.getMetaworksContext());
		
		return findNotiSetting;
	}
	
	public INotiSetting findByEmpcode(String Empcode) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ");
		sb.append("emptable ");
		sb.append("where userId=?userId");
		
		INotiSetting findNotiSetting = (INotiSetting) sql(sb.toString());
		findNotiSetting.set("userId", userId);
		findNotiSetting.select();
		
		findNotiSetting.setMetaworksContext(this.getMetaworksContext());
		
		return findNotiSetting;
	}
}
