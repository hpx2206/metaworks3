package org.metaworks.example.roomnine;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;

@Face(ejsPath="genericfaces/Window.ejs")
public class Person {

	Contact contact;
	String name;
	int age;
	
		public Contact getContact() {
			return contact;
		}
		public void setContact(Contact contact) {
			this.contact = contact;
		}
	@Id
	@Face(displayName="성명")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@ServiceMethod(callByContent=true)
	public void saveToAddressbook() throws InterruptedException
	{
		Thread.sleep(3000);
		System.out.println("Hello, " + getName() + " and your age is " + getAge());
	}
	
}
