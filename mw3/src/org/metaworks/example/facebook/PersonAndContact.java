package org.metaworks.example.facebook;

public class PersonAndContact {

	IPerson person;
		public IPerson getPerson() {
			return person;
		}
		public void setPerson(IPerson person) {
			this.person = person;
		}
		
	Contact contact;
		public Contact getContact() {
			return contact;
		}
		public void setContact(Contact contact) {
			this.contact = contact;
		}
	
	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	int order;
		public int getOrder() {
			return order;
		}
		public void setOrder(int order) {
			this.order = order;
		}
	
	
}
