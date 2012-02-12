package test2;

import org.metaworks.annotation.ServiceMethod;

public class Addressbook{

	java.lang.String name;
		public java.lang.String getName(){ return name; }
		public void setName(java.lang.String name){ this.name = name; }

    String telNo;
      public String getTelNo(){return telNo;}
      public void setTelNo(String telNo){this.telNo = telNo;}
      
    @ServiceMethod
    public void test(){
        setTelNo("11111111-222222");
    }
}

