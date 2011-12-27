package org.metaworks.example.addressbook;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;

public class Person{

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

	public void hearHappyNewYear() throws InterruptedException{
		age = age + 1;
		setMessage("Happy New Year, " + getName() + ". and your age is " + age);
	}

	public boolean tellHappyNewYear() throws InterruptedException{
		age = age - 1;
		setMessage("My age is " + age);
		
		if(age==0){
			addressbook.gameOver(this);
			return true;
		}
		
		return false;
			
	}
	

	@ServiceMethod(callByContent=true, when="view")
	public Object[] sayHappyNewYearToHarry() throws InterruptedException{
		Person otherPerson = addressbook.getHarry();
		otherPerson.hearHappyNewYear();
		
		if(tellHappyNewYear()){
			return new Object[]{addressbook};
		}
		
		return new Object[]{otherPerson, this};
	}

	@ServiceMethod(callByContent=true, when="view")
	public Object[] sayHappyNewYearToSally() throws InterruptedException{
		Person otherPerson = addressbook.getSally();
		otherPerson.hearHappyNewYear();
		
		if(tellHappyNewYear()){
			return new Object[]{addressbook};
		}
		
		return new Object[]{otherPerson, this};
	}
		

	@ServiceMethod(callByContent=true, when="view")
	public Object[] sayHappyNewYearToDoogie() throws InterruptedException{
		Person otherPerson = addressbook.getDoogie();
		otherPerson.hearHappyNewYear();
		
		if(tellHappyNewYear()){
			return new Object[]{addressbook};
		}
		
		return new Object[]{otherPerson, this};
	}

	@AutowiredFromClient
	public Addressbook addressbook;


	
}
