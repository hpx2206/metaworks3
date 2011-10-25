package org.metaworks.example;


public interface IAddressBook {
	public IPerson findPerson(String name);
	
	public IPerson[] listPersonsInSameAge(int age);
	
	public void putPerson(IPerson person);
	
	public PersonAndContact findPersonAndContact(String name);
}
