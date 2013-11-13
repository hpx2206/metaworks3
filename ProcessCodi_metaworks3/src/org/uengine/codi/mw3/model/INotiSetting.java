package org.uengine.codi.mw3.model;

import javax.persistence.Table;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

@Table(name="notiSetting")
public interface INotiSetting extends IDAO{
	
	@Id
	@Hidden
	public int getId();
	public void setId(int id);
	
	@Hidden
	public String getUserId();
	public void setUserId(String userId);
	
	public boolean isCheckLogin();
	public void setCheckLogin(boolean checkLogin);

	public boolean isWriteBookmark();
	public void setWriteBookmark(boolean writeBookmark);

	public boolean isNotiAdvice();
	public void setNotiAdvice(boolean notiAdvice);

	public boolean isModiUser();
	public void setModiUser(boolean modiUser);

	public boolean isModiTopic();
	public void setModiTopic(boolean modiTopic);

	public boolean isModiOrgan();
	public void setModiOrgan(boolean modiOrgan);

	public boolean isWriteTopic();
	public void setWriteTopic(boolean writeTopic);
	
	public boolean isWriteOrgan();
	public void setWriteOrgan(boolean writeOrgan);

	public boolean isWriteInstance();
	public void setWriteInstance(boolean writeInstance);
	
	public boolean isInviteTopic();
	public void setInviteTopic(boolean inviteTopic);

	public boolean isInviteOrgan();
	public void setInviteOrgan(boolean inviteOrgan);
	
	public boolean isAddFriend();
	public void setAddFriend(boolean addFriend);
	
	public boolean isBeforehandNoti();
	public void setBeforehandNoti(boolean beforehandNoti);

	public boolean isNotiTime();
	public void setNotiTime(boolean notiTime);

	public boolean isNotiEmail();
	public void setNotiEmail(boolean notiEmail);
	
	@ServiceMethod(callByContent=true)
	@Face(displayName = "$Save")
	public void save() throws Exception;
	
	public INotiSetting findByUserId(String userId) throws Exception;
}
