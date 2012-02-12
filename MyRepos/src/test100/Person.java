package test100;

import org.metaworks.annotation.NonLoadable;


public class Person{

	java.lang.String name;
    @NonLoadable
        public java.lang.String getName(){ return name; }
		public void setName(java.lang.String name){ this.name = name; }

	java.lang.Long age3;
		public java.lang.Long getAge3(){ return age3; }
		public void setAge3(java.lang.Long age3){ this.age3 = age3; }

}