package org.metaworks.example;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Validator;
import org.metaworks.annotation.ValidatorContext;
import org.metaworks.annotation.ValidatorSet;
import org.metaworks.dao.IDAO;

/*@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
options={"fieldOrder"}, values={"empName","comName","password"})*/
public interface IEmployee extends IDAO {
    @Face(displayName="이름")
    @NonSavable
    @NonLoadable
    public String getEmpName();
    public void setEmpName(String empName);
 
    @Face(displayName="회사")
    @NonSavable
    @NonLoadable
    public String getComName();
    public void setComName(String comName);
    
	
	@Face(displayName="비밀번호")
	@NotNull(message="sss 이름을 입력하세요 ~")
	@NonSavable
    @NonLoadable
    public String getPassword();
    public void setPassword(String password);
    
    
    @ServiceMethod(callByContent=true, validate=true)
    public void complete();
}
