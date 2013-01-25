package org.metaworks.example.addressbook;

import org.metaworks.annotation.Range;

public class Contact {

	int type;
	
		@Range(
				options={"전화번호", "이메일","주소"},
				values={"1", "2", "3"}
				)
		public int getType() {
			return type;
		}
	
		public void setType(int type) {
			this.type = type;
		}
	
	String address;

	
		public String getAddress() {
			return address;
		}
	
		public void setAddress(String address) {
			this.address = address;
		}
	
	
	
}
