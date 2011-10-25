package org.metaworks.example;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

	@Table(name="Person")
	public interface IPerson extends IDAO{
		
			@Id
			public String getName();
			public void setName(String name);
	
			public int getAge();
			public void setAge(int age);
			
			@NonLoadable
			public Contact getContact();
			public void setContact(Contact contact);
			
			@Face(ejsPath="faces/image.ejs")
			public String getPortraitURL();
			public void setPortraitURL(String portraitURL);
			
			
			public boolean isMyFried();
			public void setMyFried(boolean isMyFried);
			
			///method
			
			public IPerson fill() throws Exception;
	}