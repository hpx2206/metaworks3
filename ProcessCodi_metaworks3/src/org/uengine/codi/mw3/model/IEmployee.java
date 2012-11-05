package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.annotation.Validator;
import org.metaworks.annotation.ValidatorContext;
import org.metaworks.annotation.ValidatorSet;
import org.metaworks.dao.IDAO;
import org.uengine.codi.mw3.Login;

@Table(name = "EMPTABLE")
public interface IEmployee extends IDAO {

/*	
    @Id
	@ORMapping(databaseFields={"empCode", "empName"}, objectFields={"userId", "name"})
	public IUser getUser();
	public void setUser(IUser user);
*/	
	@Id
	@ValidatorSet({
		@Validator(name=ValidatorContext.VALIDATE_NOTNULL),
		@Validator(name=ValidatorContext.VALIDATE_MIN, options={"3"}),
	})
	public String getEmpCode();
	public void setEmpCode(String empCode);

	@Name
	@ValidatorSet({
		@Validator(name=ValidatorContext.VALIDATE_NOTNULL)
	})	
	public String getEmpName();
	public void setEmpName(String empName);

	//@Hidden
	public String getFacebookId();
	public void setFacebookId(String facebookId);
	
	@Hidden(when = "view")
	@ValidatorSet({
		@Validator(name=ValidatorContext.VALIDATE_NOTNULL),
		@Validator(name=ValidatorContext.VALIDATE_MIN, options={"4"}),
	})
	@Face(options="type", values="password")
	public String getPassword();
	public void setPassword(String password);
	
	@Hidden(when = "view")
	@Face(options="type", values="password")
	@NonLoadable
	@NonSavable
	@ValidatorSet({
		@Validator(name=ValidatorContext.VALIDATE_NOTNULL),
		@Validator(name=ValidatorContext.VALIDATE_MIN, options={"4"}),
	})
	public String getConfirmPassword();
	public void setConfirmPassword(String confirmPassword);

	public String getJikName();
	public void setJikName(String jikName);

	@ORMapping(databaseFields={"partCode", "partName"}, objectFields={"partCode", "partName"})
	@NonSavable
	public IDept getDept();
	public void setDept(IDept dept);
	
	public String getPartCode();	
	public void setPartCode(String partCode);
	
	@NonSavable
	public String getPartName();	
	public void setPartName(String partName);
	
	public String getGlobalCom();	
	public void setGlobalCom(String comCode);
	
	@Hidden
	public String getIsDeleted();	
	public void setIsDeleted(String deleted);

	public String getMobileNo();
	public void setMobileNo(String mobileNo);
	
	public String getEmail();
	public void setEmail(String email);

	@Range(
			values={"en", "ko", "jp", "cn", "joodle"},
			options={"English", "Korean", "Japanese", "Chinese", "joodle"}			
			)
	public String getLocale();
	public void setLocale(String locale);
	
	
	@Range(
			values={"tw", "fb", "sns", "outlook", "asana", "wave"},
			options={"I love twitter", "I love facebook", "I Love SNS" ,"I love Outlook", "I love Asana", "I love Google Wave"}			
			)
	public String getPreferUX();
	public void setPreferUX(String preferUX);

	@Range(
			values={"auto", "phone", "pad", "desktop"},
			options={"Auto Detection", "Phone (1-column)", "Pad (2-column)", "Desktop version"}			
			)
	public String getPreferMob();
	public void setPreferMob(String preferMob);

	
	@NonLoadable
	@NonSavable
	@Available(when={WHEN_EDIT, WHEN_NEW})
	public PortraitImageFile getImageFile();
	public void setImageFile(PortraitImageFile imageFile);
	
	@ServiceMethod(callByContent = true)
	public IEmployee load() throws Exception;

	public abstract boolean getIsAdmin();
	public abstract void setIsAdmin(boolean value);
	
	public String getMood();
	public void setMood(String mood);

		
	public IEmployee findMe() throws Exception;	
	public IEmployee findByDept(Dept dept) throws Exception;
	public IEmployee findByRole(Role role) throws Exception;
	public IEmployee findByDeptOther() throws Exception;
	
	@ServiceMethod(where="navigator")
	public Object[] loadOrganization() throws Exception;
	
	@ServiceMethod(target="append", where="picker")
	public Object pickup() throws Exception;
	
	@ServiceMethod(target="popup")
	public Popup openPicker();
	
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_VIEW)
	public Object editEmployeeInfo() throws Exception;
	
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_NEW)
	public Object[] subscribeStep1() throws Exception;
	
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_NEW, validate=true)
	public Object subscribeStep2() throws Exception;
	
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_NEW, validate=true)
	public Object subscribeStep3() throws Exception;
	
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_NEW, validate=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] saveEmployeeInfo() throws Exception;
	
	@ServiceMethod(target="popup", callByContent=true)
	public Object showDetail() throws Exception;
	
	@ServiceMethod(callByContent=true, target="popup")
	public Refresh addContact() throws Exception;
	
	@ServiceMethod(callByContent=true, target="popup")
	public void addTopicUser() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public void checkEmpCode() throws Exception ;
	
	@ServiceMethod(needToConfirm=true, target=TARGET_TOP, inContextMenu=true)
	@Face(displayName="$Unsubscribe")
	public Login unsubscribe() throws Exception;
	
	@ServiceMethod
	public Object[] logout() throws Exception;
}
