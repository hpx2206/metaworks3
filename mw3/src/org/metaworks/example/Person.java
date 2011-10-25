package org.metaworks.example;

import org.metaworks.MetaworksObject;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;

public class Person extends MetaworksObject<IPerson> implements IPerson{

	String name;

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


	Contact contact;
	
		public Contact getContact() {
			return contact;
		}
		public void setContact(Contact contact) {
			this.contact = contact;
		}


	String portraitURL;
	@Face(ejsPath="faces/image.ejs")

		public String getPortraitURL() {
			return portraitURL;
		}
		public void setPortraitURL(String portraitURL) {
			this.portraitURL = portraitURL;
		}


	boolean isMyFried;

		public boolean isMyFried() {
			return isMyFried;
		}
		public void setMyFried(boolean isMyFried) {
			this.isMyFried = isMyFried;
		}
	

	@ServiceMethod
	public IPerson fill() throws Exception{
		return databaseMe();
	}
	
	@ServiceMethod(target=TARGET_AUTO)
	public PersonDetail wall() throws Exception{
		return new PersonDetail(this);
	}
	
	
}
