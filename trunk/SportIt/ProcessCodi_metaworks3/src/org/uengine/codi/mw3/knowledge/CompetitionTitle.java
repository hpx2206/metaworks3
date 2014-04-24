package org.uengine.codi.mw3.knowledge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToEvent;
import org.metaworks.ToPrepend;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Validator;
import org.metaworks.annotation.ValidatorContext;
import org.metaworks.annotation.ValidatorSet;
import org.metaworks.component.CheckBox;
import org.metaworks.dao.Database;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.CodiProcessManagerBean;
import org.uengine.codi.MetaworksUEngineSpringConnectionAdapter;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.calendar.ScheduleCalendar;
import org.uengine.codi.mw3.calendar.ScheduleCalendarEvent;
import org.uengine.codi.mw3.filter.OtherSessionFilter;
import org.uengine.codi.mw3.marketplace.App;
import org.uengine.codi.mw3.marketplace.AppMapping;
import org.uengine.codi.mw3.marketplace.IApp;
import org.uengine.codi.mw3.model.CommentWorkItem;
import org.uengine.codi.mw3.model.CompetitionData;
import org.uengine.codi.mw3.model.Employee;
import org.uengine.codi.mw3.model.IRoleMapping;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceList;
import org.uengine.codi.mw3.model.InstanceListener;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.CompetitionPerspective;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.ListPanel;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.ProcessMap;
import org.uengine.codi.mw3.model.RecentItem;
import org.uengine.codi.mw3.model.RoleMappingPanel;
import org.uengine.codi.mw3.model.TodoBadge;
import org.uengine.codi.mw3.model.TopicInfo;
import org.uengine.codi.mw3.model.UpcommingTodoPerspective;
import org.uengine.kernel.KeyedParameter;
import org.uengine.kernel.ResultPayload;
import org.uengine.kernel.RoleMapping;
import org.uengine.persistence.dao.UniqueKeyGenerator;
import org.uengine.processmanager.ProcessManagerBean;

import competition.form.competitionRegister;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", 
options={"fieldOrder"}, 
values={"topicTitle,topicSecuopt,startDate,endDate,serviceLists"})
public class CompetitionTitle extends TopicTitle{

	@Override
	@Face(displayName="$competitionTitle")
	@ValidatorSet({
		@Validator(name=ValidatorContext.VALIDATE_NOTNULL, message="대회 이름을 입력해주세요."),
		@Validator(name=ValidatorContext.VALIDATE_MAX , options={"20"}, message="20자 이내로 입력해주세요."),
		@Validator(name=ValidatorContext.VALIDATE_REGULAREXPRESSION, options={"/^[^~!@\\\\#$%^&*\\()\\-=+_\'\"]+$/"}, message="다음과 같은 문자는 입력 할 수 없습니다. ~!@#$%^&*()\\-=+_\'\"")
	})
	@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
	public String getTopicTitle() {
		return super.getTopicTitle();
	}
	@Override
	public void setTopicTitle(String topicTitle) {
		super.setTopicTitle(topicTitle);
	}
	
	Date startDate;
		@Face(displayName="시작일")
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

	Date endDate;
		@Face(displayName="종료일")
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
	
	@Override
	@Face(displayName="대회로고")
	@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
	public MetaworksFile getLogoFile() {
		return super.getLogoFile();
	}
	@Override
	public void setLogoFile(MetaworksFile logoFile) {
		super.setLogoFile(logoFile);
	}


	CheckBox serviceLists;
	@Face(displayName="서비스")
		public CheckBox getServiceLists() {
			return serviceLists;
		}
		public void setServiceLists(CheckBox serviceLists) {
			this.serviceLists = serviceLists;
		}
		
		
	@Override
	public void saveMe() throws Exception {
		WfNode wfNode = new WfNode();

		if(this.getLogoFile().getFileTransfer() != null &&
				this.getLogoFile().getFilename() != null && 
				this.getLogoFile().getFilename().length() > 0){			
			
			if( this.getLogoFile().getFileTransfer().getMimeType() != null  && !this.getLogoFile().getFileTransfer().getMimeType().startsWith("image")){
				throw new MetaworksException("$OnlyImageFileCanUpload");
			}else{
				this.getLogoFile().upload();
			}
			
		}
		
		if(MetaworksContext.WHEN_NEW.equals(this.getMetaworksContext().getWhen())){
			
			// TODO: 이름으로 바로 검색하여 중복 값 체크 할 수 있게 수정해야함
			ICompetitionNode competitionNodeList = CompetitionNode.findCompetition(session);
			while(competitionNodeList.next()){
				if(this.getTopicTitle().equals(competitionNodeList.getName())){
					throw new Exception("$DuplicateName");
				}
			}
			
			wfNode.setName(this.getTopicTitle());
			wfNode.setType(CompetitionNode.COMPETITION);
			wfNode.setSecuopt(topicSecuopt ? "1" : "0");
			wfNode.setParentId(session.getCompany().getComCode());	
			wfNode.setAuthorId(session.getUser().getUserId());		
			wfNode.setCompanyId(session.getCompany().getComCode());
			if(this.getLogoFile().getUploadedPath() != null && this.getLogoFile().getFilename() != null){
				wfNode.setUrl(this.getLogoFile().getUploadedPath());
				wfNode.setThumbnail(this.getLogoFile().getFilename());
			}
			wfNode.createMe();
			
			TopicMapping tm = new TopicMapping();
			tm.setTopicId(wfNode.getId());
			tm.setUserId(session.getUser().getUserId());
			tm.setUserName(session.getUser().getName());
			tm.getMetaworksContext().setWhen(this.getMetaworksContext().getWhen());
			
			tm.saveMe();
			tm.flushDatabaseMe();
			this.setTopicId(wfNode.getId());
		}else{
			wfNode.setId(this.getTopicId());
			
			wfNode.copyFrom(wfNode.databaseMe());
			wfNode.setSecuopt(topicSecuopt ? "1" : "0");
			
			if(this.getLogoFile().getUploadedPath() != null && this.getLogoFile().getFilename() != null){
				wfNode.setUrl(this.getLogoFile().getUploadedPath());
				wfNode.setThumbnail(this.getLogoFile().getFilename());
			}
			
			wfNode.setName(this.getTopicTitle());
			wfNode.saveMe();
		}
	}

	

	@Override
	public Object[] save() throws Exception {
		this.saveMe();
		
		String defId = "competition/process/registerCompetition.process";
		
		ProcessMap processMap = new ProcessMap();
		processMap.processManager = processManager;
		processMap.session = session;
		processMap.setDefId(defId);
		
		String instId = processMap.initializeProcess();
		String serviceName="";
		String serviceId[]=this.getServiceLists().getSelected().split(",");
		ArrayList<String> serviceNames = this.getServiceLists().getOptionNames();
		ArrayList<String> serviceValues = this.getServiceLists().getOptionValues();

		for(int i=0; i<serviceId.length; i++){
			for(int j=0; j<serviceValues.size(); j++){
				if(serviceId[i].equals(serviceValues.get(j))){
					if( i > 0 ){
						serviceName += ",";
					}
					serviceName += serviceNames.get(j);
					break;
				}
			}
		}
		Employee user = new Employee();
		user.setEmpCode(session.getEmployee().getEmpCode());
		user.copyFrom(user.databaseMe());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String startForm = dateFormat.format(startDate);
		String endForm = dateFormat.format(endDate);
		
		competitionRegister data = new competitionRegister(); 
		data.setCompetitionId(this.getTopicId());
		data.setGameName(this.getTopicTitle());
		data.setUserId(user.getEmpCode());
		data.setUserName(user.getEmpName());
		data.setUserPw(user.getPassword());
		data.setTitle("관리자 승인");
		data.setComName(session.getCompany().getComName());
		data.setComCode(session.getCompany().getComCode());
		data.setServiceId(this.getServiceLists().getSelected());
		data.setServiceName(serviceName);
		data.setStartDate(startForm);
		data.setEndDate(endForm);
		data.setRmk("description");
		
		
		RoleMappingPanel roleMappingPanel = new RoleMappingPanel(processManager, defId, session);
		roleMappingPanel.putRoleMappings(processManager, instId);
		
		processManager.getProcessInstance(instId.toString()).setBeanProperty("competitionRegister", data);
		processManager.executeProcess(instId);
		processManager.applyChanges();
		
		Instance instance = new Instance();
		instance.setInstId(Long.valueOf(instId));
		instance.databaseMe().setTopicId(this.getTopicId());
		instance.copyFrom(instance.databaseMe());

		CompetitionNode competitionNode = new CompetitionNode();
		competitionNode.setId(this.getTopicId());
		competitionNode.setName(this.getTopicTitle());
		competitionNode.setType(CompetitionNode.COMPETITION);
		competitionNode.setSecuopt(this.isTopicSecuopt()?"1":"0");
		competitionNode.session = session;
		
//		this.notiToCompany();
		
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		this.getMetaworksContext().setHow("html");
		
		Object[] returnObj = competitionNode.loadCompetition();
		Object[] returnObject = new Object[ returnObj.length + 3];
		for (int i = 0; i < returnObj.length; i++) {
			returnObject[i] = new Refresh(returnObj[i]);
		}
		
		returnObject[returnObj.length ] = new ToEvent(ServiceMethodContext.TARGET_OPENER, EventContext.EVENT_CHANGE);
		returnObject[returnObj.length + 1] = new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CLOSE);
		returnObject[returnObj.length + 2] = new ToPrepend(new InstanceList(), instance);
		return returnObject;
			
	}
	
	
	public void loadService() throws Exception{
		  StringBuffer result= new StringBuffer();
		 try {
			 	//URL url = new URL("http://202.68.237.16:8080/ict/cloud/getServiceList.json");
		 		URL url = new URL("http://175.207.44.155:9090/ict/cloud/getServiceList.json");
		 		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}
		 
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
				
				String output;
				while ((output = br.readLine()) != null) {
					System.out.println(output);
					result.append(output);
				}
		 
				conn.disconnect();
				
			  } catch (MalformedURLException e) {
		 
				e.printStackTrace();
		 
			  } catch (IOException e) {
		 
				e.printStackTrace();
		 
			  }
		 
			JSONObject json =  (JSONObject)new JSONParser().parse(result.toString());
			if(!"00".equals(json.get("resultCode"))){
				throw new Exception(json.get("resultCode").toString());
			}
			
			serviceLists = new CheckBox();
			
			JSONArray array = (JSONArray) json.get("ResultData");
			for(int i=0; i<array.size(); i++){
				JSONObject tmpJson =  (JSONObject)new JSONParser().parse(array.get(i).toString());
				Boolean service = new Boolean(String.valueOf(tmpJson.get("sstmNm")));
				serviceLists.add(i, String.valueOf(tmpJson.get("sstmNm")), String.valueOf(tmpJson.get("sstmId")));
			}
	
	}
	
	@Override
	public Object[] modify() throws Exception {
		this.saveMe();
		
		Locale locale = new Locale(session);
		locale.load();
		
		InstanceListPanel instanceListPanel = Perspective.loadInstanceList(session, Perspective.MODE_TOPIC, Perspective.TYPE_NEWSFEED, getTopicId());

		String title = locale.getString("$Competition") + " - " + this.getTopicTitle();
		session.setWindowTitle(title);
		
		ListPanel listPanel = new ListPanel(instanceListPanel, new TopicInfo(session, this.getTopicId()));
		
		TopicInfo topicInfo = new TopicInfo(session, session.getLastPerspecteType());
		topicInfo.load();
		
		return new Object[]{new Refresh(session),
							new Refresh(listPanel),
				 			new ToEvent(new CompetitionPerspective(), EventContext.EVENT_CHANGE),
				 			new ToEvent(ServiceMethodContext.TARGET_SELF, EventContext.EVENT_CLOSE)};
	}
	
	

	
}
