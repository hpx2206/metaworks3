package org.uengine.codi.mw3;

import javax.servlet.http.HttpSession;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToOpener;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.model.Company;
import org.uengine.codi.mw3.model.Employee;
import org.uengine.codi.mw3.model.ICompany;
import org.uengine.codi.mw3.model.Main;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.GlobalContext;
public class StartCodi {

	@ServiceMethod(target=ServiceMethodContext.TARGET_SELF)
	public Object load() throws Exception{
		
		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession();		
		String loggedUserId = (String)httpSession.getAttribute("loggedUserId");

		System.out.println("loggedUserId : " + loggedUserId);
		
		String tenantId = TenantContext.getThreadLocalInstance().getTenantId();
		
		//등록 없음  - 회원가입 화면.
		if(tenantId == null){
			return new SignUp();
		}
		
		// 등록되어 있는테넌트 불러오기.
		Company company = new Company();
		company.setAlias(tenantId);
		ICompany findTenant = company.findByAlias();

		/*
		 * if is not null
		 * true  : 존재하는 태넌트
		 * false : 존재하지 않으므로 경고처리
		 */
		if(findTenant != null){
			
			/*
			 * if is not null
			 * true : 로그인된 사용자 -> 바로 메인화면으로
			 * false : 로그인 안되었으므로 로그인 화면
			 */
			if(loggedUserId != null){
				
				
				Employee employee = new Employee();
				employee.setEmpCode(loggedUserId);
				employee.copyFrom(employee.findMe());
				
				if(employee.getGlobalCom().equals(findTenant.getComCode())){
					Session session = new Session();
					session.setEmployee(employee);
					session.fillSession();
	
					// TODO : guided tour 는 실행여부를 database 로 관리하여 진행하게 한다.
			
					ModalWindow removeWindow = new ModalWindow();
					removeWindow.setId("subscribe");
					
					
					String pageNavigatorPropertyName;
					String className;
					PageNavigator pageNavigator;
					MainPanel mainPanel;
					if("1".equals(GlobalContext.getPropertyString("oce.use", "1"))){
						pageNavigatorPropertyName = "oce.pagenavigator.class";
						session.setUx("oce");
					}
					else{
						pageNavigatorPropertyName = "codi.pagenavigator.class";
					}
					
					className = GlobalContext.getPropertyString(pageNavigatorPropertyName);
					
					Class c = Thread.currentThread().getContextClassLoader().loadClass(GlobalContext.getPropertyString(pageNavigatorPropertyName));
					Object object = c.newInstance();
					
					if(object instanceof PageNavigator){
						pageNavigator = (PageNavigator)object;
					}
					else{
						throw new Exception("pageNavigator가 잘못 지정되었습니다. uengine.properties의 pagenavigatorClassName을 수정해주세요.");
					}
					
					pageNavigator.session = session;
					
					if("oce".equals(session.getUx())){
						mainPanel = pageNavigator.goDashBoard();
						return new Object[]{new Refresh(mainPanel, false, true)};
					}
					
				
				return new MainPanel(new Main(session));	
				}
			}
			
			Login login = new Login();
			login.setStatus("login");
			
			login.setMetaworksContext(new MetaworksContext());
			login.getMetaworksContext().setHow("logout");
			login.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
			return login;
			//return new Login();
		}else{
			// 잘못 입력되었을때. - ex) asdf.processcodi.com:8080/uengine-web
			//return new ErrorPage();
			return new Login();
		}		
	}
}
