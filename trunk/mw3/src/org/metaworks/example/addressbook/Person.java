package org.metaworks.example.addressbook;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;

public class Person {

	String name;
	@Id
	@Name
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}	
		
	int age;
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
	
	String message;
	@NonEditable
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}

	public void sayHappyNewYear() throws InterruptedException{
		age = age + 1;
		setMessage("Happy New Year, " + getName() + ". and your age is " + age);
	}

	@ServiceMethod(callByContent=true)
	public Person sayHappyNewYearToHarry() throws InterruptedException{
		Person harry = addressbook.getHarry();
		harry.sayHappyNewYear();
		return harry;
	}

	@ServiceMethod(callByContent=true)
	public Person sayHappyNewYearToSally() throws InterruptedException{
		Person sally = addressbook.getSally();
		sally.sayHappyNewYear();
		return sally;
	}
		

	@ServiceMethod(callByContent=true)
	public Person sayHappyNewYearToDoogie() throws InterruptedException{
		Person doogie = addressbook.getDoogie();
		doogie.sayHappyNewYear();
		return doogie;
	}

	@AutowiredFromClient
	public Addressbook addressbook;

}
