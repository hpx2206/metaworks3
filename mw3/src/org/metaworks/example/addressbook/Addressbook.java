package org.metaworks.example.addressbook;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

@Face(ejsPath="genericfaces/Window.ejs")
public class Addressbook {
	
	@ServiceMethod
	public void load(){
		harry = new Person();
		harry.setName("Harry");
		harry.setAge(10);
		
		sally = new Person();
		sally.setName("Sally");
		sally.setAge(9);
		
		doogie = new Person();
		doogie.setName("Doogie");
		doogie.setAge(3);
	}

	Person harry;
		public Person getHarry() {
			return harry;
		}
		public void setHarry(Person harry) {
			this.harry = harry;
		}

	Person sally;
		public Person getSally() {
			return sally;
		}
		public void setSally(Person sally) {
			this.sally = sally;
		}

	Person doogie;
		public Person getDoogie() {
			return doogie;
		}
		public void setDoogie(Person doogie) {
			this.doogie = doogie;
		}
		
}
