package org.metaworks.example;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;

public class PersonDetail {
	
	public PersonDetail(){
	}

	public PersonDetail(IPerson person) throws Exception{
		setPerson(person);
		
		wall = (IPosting) Database.sql(IPosting.class, "select * from Posting where writer=?writer");
		wall.setWriter(person);
		wall.select();
		
		friends = (IPerson) Database.sql(IPerson.class, "select * from Friends where friendName=?name");
		friends.setName(person.getName());
		friends.select();
		
		
		
	}
	
	IPerson person;
		@Id
		public IPerson getPerson() {
			return person;
		}
	
		public void setPerson(IPerson person) {
			this.person = person;
		}

	IPosting wall;

		public IPosting getWall() {
			return wall;
		}
	
		public void setWall(IPosting wall) {
			this.wall = wall;
		}
		
	IPerson friends;

		public IPerson getFriends() {
			return friends;
		}
	
		public void setFriends(IPerson friends) {
			this.friends = friends;
		}
	
	@ServiceMethod
	public void requestFriend(){
		
	}
	
	
	
	
		
}
