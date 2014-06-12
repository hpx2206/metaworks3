package org.uengine.codi.mw3.model;

import org.metaworks.Remover;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.annotation.Validator;
import org.metaworks.annotation.ValidatorContext;
import org.metaworks.annotation.ValidatorSet;
import org.metaworks.dao.IDAO;

@Table(name = "COMTABLE")
@Face(displayName="$Company", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
options={"fieldOrder"}, values={"repMail,repMlPwd"})
public interface ICompany extends IDAO {
	@Id
	@Hidden
	public String getComCode();
	public void setComCode(String comCode);

	@Face(displayName="$SALESEARLYSALES_BASECOMPANY_NAME")
	@Hidden
	public String getAlias();
	public void setAlias(String alias);
	
	@Name
	@Hidden
	public String getComName();
	public void setComName(String comName);

	@Hidden
	public String getDescription();
	public void setDescription(String description);

	@Hidden
	public String getIsDeleted();	
	public void setIsDeleted(String deleted);

	@ValidatorSet({
		@Validator(name=ValidatorContext.VALIDATE_NOTNULL, message="이메일을 입력하세요."),
		@Validator(name=ValidatorContext.VALIDATE_REGULAREXPRESSION, options={"/^([0-9a-zA-Z_\\.-]+)@([0-9a-zA-Z_-]+)(\\.[0-9a-zA-Z_-]+){1,2}$/"}, message="이메일 형식이 잘못되었습니다")
	})
	@Face(options={"width"}, values={"300"})
	public String getRepMail();
	public void setRepMail(String repMail);
	@Hidden
	public String getRepMlHst();
	public void setRepMlHst(String repMlHst);
	
	@Face(options={"width"}, values={"300"})
	public String getRepMlPwd();
	public void setRepMlPwd(String repMlPwd);
	
	//@ServiceMethod
	public ICompany load() throws Exception;
	
	@ServiceMethod(callByContent=true, validate=true)
	public Remover save() throws Exception;
	
	public ICompany findByAlias() throws Exception;
}
