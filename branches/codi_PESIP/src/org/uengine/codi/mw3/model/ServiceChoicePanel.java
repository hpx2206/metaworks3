package org.uengine.codi.mw3.model;

import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.TopicNode;

public class ServiceChoicePanel {
	
	String orderInfo;
		public String getOrderInfo() {
			return orderInfo;
		}
	
		public void setOrderInfo(String orderInfo) {
			this.orderInfo = orderInfo;
		}
	
	String koreaPublicService;
		public String getKoreaPublicService() {
			return koreaPublicService;
		}
	
		public void setKoreaPublicService(String koreaPublicService) {
			this.koreaPublicService = koreaPublicService;
		}
		
	String exportConsulting;
		public String getExportConsulting() {
			return exportConsulting;
		}
	
		public void setExportConsulting(String exportConsulting) {
			this.exportConsulting = exportConsulting;
		}
	String userFunctionImprovement;
		public String getUserFunctionImprovement() {
			return userFunctionImprovement;
		}
	
		public void setUserFunctionImprovement(String userFunctionImprovement) {
			this.userFunctionImprovement = userFunctionImprovement;
		}
	
	String exportEnterprisePool;
		public String getExportEnterprisePool() {
			return exportEnterprisePool;
		}
	
		public void setExportEnterprisePool(String exportEnterprisePool) {
			this.exportEnterprisePool = exportEnterprisePool;
		}
	
	String socialNetworking;
		public String getSocialNetworking() {
			return socialNetworking;
		}
	
		public void setSocialNetworking(String socialNetworking) {
			this.socialNetworking = socialNetworking;
		}
		
	String knolMarket;
		public String getKnolMarket() {
			return knolMarket;
		}
	
		public void setKnolMarket(String knolMarket) {
			this.knolMarket = knolMarket;
		}
		
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object[] loadTopic() throws Exception {
		TopicNode topicNode = new TopicNode();
		topicNode.setId("359");
		topicNode.setType("topic");
		topicNode.setName("기능 개선");
		Object[] returnObjs = topicNode.loadTopic();
		
		returnObjs[returnObjs.length + 1] = new Remover(new ModalWindow());
		return returnObjs;
	}

	@AutowiredFromClient
	public Session session;
	
	// 나중에 로드할 것이 잇다면 여기서 로드해야함
	public void load() {
		
	}
}
