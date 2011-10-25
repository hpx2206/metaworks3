package org.metaworks.example;

import java.util.ArrayList;
import java.util.Hashtable;

//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;


public class AddressBook implements IAddressBook{
	
	static Hashtable<String, IPerson> book = new Hashtable<String, IPerson>();
	
	public IPerson findPerson(String name){
		return book.get(name);
	}
	
	public IPerson[] listPersonsInSameAge(int age){
		ArrayList<IPerson> list = new ArrayList();
		
		for(String key : book.keySet()){
			IPerson p = book.get(key);
			if(p.getAge()==age){
				list.add(p);
			}
		}
		
		IPerson[] plist = new IPerson[list.size()];
		list.toArray(plist);
		return plist;
	}
	
	public void putPerson(IPerson person){
		book.put(person.getName(), person);
	}
	
	public PersonAndContact findPersonAndContact(String name){
		IPerson p = findPerson(name);
		PersonAndContact personAndContact = new PersonAndContact();
		personAndContact.setContact(p.getContact());
		personAndContact.setPerson(p);
		
		personAndContact.setId("#ID001");
		personAndContact.setOrder(5);
		
		return personAndContact;
	}
	
}

	