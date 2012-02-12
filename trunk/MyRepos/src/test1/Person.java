package test1;

import org.metaworks.annotation.*;
import java.io.Serializable;

public class Person implements Serializable{

	String name;
    @Id
		public String getName(){ return name; }
		public void setName(String name){ this.name = name; }

	int age;
		public int getAge(){ return age; }
		public void setAge(int age){ this.age = age; }
        
    @ServiceMethod(callByContent = true)
    public void happyNewYear(){
        age = age +1;
        System.out.println(">>>>>>>>>>>> name >>>>>"+ name);
    }

}