package org.metaworks.example.roomnine;

import org.metaworks.annotation.Face;


public class AddressBook {

	Person newPerson;

		public Person getNewPerson() {
			return newPerson;
		}
	
		public void setNewPerson(Person newPerson) {
			this.newPerson = newPerson;
		}
		
	public void load() throws Exception 
	{
		System.out.println("AddressBook.load()");
	}
	
}
