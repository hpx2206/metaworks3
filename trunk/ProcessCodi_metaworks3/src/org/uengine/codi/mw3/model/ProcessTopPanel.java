package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.admin.TopPanel;
import org.uengine.codi.mw3.admin.WindowPanel;

public class ProcessTopPanel extends TopPanel {

	public ProcessTopPanel(){
	}

	public ProcessTopPanel(Session session) throws Exception {
		super(session);
		
		tray = new Tray();
		tray.session = session;
		tray.load();
	}
	
	WindowPanel windowPanel;
		public WindowPanel getWindowPanel() {
			return windowPanel;
		}	
		public void setWindowPanel(WindowPanel windowPanel) {
			this.windowPanel = windowPanel;
		}
	boolean isAdmin;
		public boolean isAdmin() {
			return isAdmin;
		}
		public void setAdmin(boolean isAdmin) {
			this.isAdmin = isAdmin;
		}

	Tray tray;
		public Tray getTray() {
			return tray;
		}
		public void setTray(Tray tray) {
			this.tray = tray;
		}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow  companyRepInfo() throws Exception{
		Company company = new Company();
		company.setComCode(this.getSession().getEmployee().getGlobalCom());
		ICompany findCompany = company.findByCode();
		if(findCompany!=null){
			ModalWindow modalWindow = new ModalWindow();
			modalWindow.setPanel(findCompany);
			modalWindow.setWidth(400);
			modalWindow.setHeight(200);
			modalWindow.setTitle("$setComapnyRep");
			modalWindow.setMetaworksContext(new MetaworksContext());
			modalWindow.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			return modalWindow;
		}
		return null;
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object feedBackInfo() throws Exception{
		ModalWindow modalWindow = new ModalWindow();
		
		ContactUs contactUs = new ContactUs();
		contactUs.session = this.getSession();
		modalWindow.setPanel(contactUs);
		modalWindow.setTitle("Contact Us");
		modalWindow.setWidth(700);
		modalWindow.setHeight(700);
		modalWindow.setMetaworksContext(new MetaworksContext());
		modalWindow.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		return modalWindow;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	public Popup showMenu() throws Exception {
		
		Employee employee = new Employee();
		
		employee.setEmpCode(this.getSession().getEmployee().getEmpCode());
		
		employee.setMetaworksContext(new MetaworksContext());
		employee.getMetaworksContext().setWhere("user_menu_option");
		
		return new Popup(200, 89, employee);
	}
	
}
