package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;

public class ContactSearchBox extends SearchBox implements ContextAware{

	public ContactSearchBox(){	
		super();
	}

	@ServiceMethod(callByContent=true)
	public Object[] search() throws Exception{
	
		if (this.getMetaworksContext().getWhere().equals("contactList")) {
			
			String userId = session.getUser().getUserId();
			
			ContactListPanel contactListPanel = new ContactListPanel();
			contactListPanel.setMetaworksContext(new MetaworksContext());
			contactListPanel.getMetaworksContext().setWhen("contacts");
			contactListPanel.getMetaworksContext().setWhere(ContactList.LOCAL);
			
			contactListPanel.load(userId, getKeyword());
							
			return new Object[]{contactListPanel}; 
		}
		else {
		
			if(this.getKeyword().equals("")) {
				return new Object[]{ new AddLocalContactPanel(session)}; 
			}
			
			AddLocalContactPanel addLocalContactPanel = new AddLocalContactPanel();		
			
			IEmployee employee = new Employee();
			employee.getMetaworksContext().setWhere("addContact");
			employee.getMetaworksContext().setHow("tree");
			employee.setEmpName(getKeyword());
			
			EmployeeList employeeList = new EmployeeList();			
			employeeList.setEmployee(employee.findMeByEmpName());
			
			addLocalContactPanel.setDeptEmployee(employeeList);
		
			ContactSearchBox searchBox = new ContactSearchBox(); 
			searchBox.setMetaworksContext(new MetaworksContext());
			searchBox.getMetaworksContext().setWhere("addContact");
			searchBox.setKeyUpSearch(true);
			searchBox.setKeyEntetSearch(true);
			searchBox.setKeyword(getKeyword());
			
			addLocalContactPanel.setContactSearchBox(searchBox);			
			
			return new Object[]{addLocalContactPanel};
		}
	}
	
	MetaworksContext metaworksContext;	
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
}
