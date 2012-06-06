package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;

public class Sales_ActivityCheck implements ITool , Serializable {
	String instanceId;
	@Hidden
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}
		
    MetaworksContext metaworksContext;
        public MetaworksContext getMetaworksContext(){
            return this.metaworksContext;
        }
        public void setMetaworksContext(MetaworksContext metaworksContext){
            this.metaworksContext = metaworksContext;
        }
     
        
        
    @Override
	public void onLoad() {
        this.metaworksContext = new MetaworksContext();
        metaworksContext.setWhen(MetaworksContext.WHEN_NEW);
		// TODO Auto-generated method stub
        
	}

	@Override
	public void beforeComplete() {
		// TODO Auto-generated method stub
		//setCompYnVar(compYn == true ? "Y" : "N");.ㅡ
	}

	@Override
	public void afterComplete() {
		// TODO Auto-generated method stub	
		
	}
	
	String check_request;
	
	String activity_contents;
	
	String sugession_file;
	
	String estimate_file;
	
	String favorite_file;
	
	String contact_file;
	
	String spent_expenses;
	
	String request_check;
	
	String special_contents;
	
	
	@Face(displayName="*내용 검토 후 승인/반려 선택(1:승인, 2:반려)")
	public String getCheck_request() {
		return check_request;
	}
	public void setCheck_request(String check_request) {
		this.check_request = check_request;
	}
	
	@Face(displayName="영업활동 중 제안/견적/발주/계약에 관련된 내용")
	public String getActivity_contents() {
		return activity_contents;
	}
	public void setActivity_contents(String activity_contents) {
		this.activity_contents = activity_contents;
	}
	
	@Face(displayName="제안 관련 문서")
	public String getSugession_file() {
		return sugession_file;
	}
	public void setSugession_file(String sugession_file) {
		this.sugession_file = sugession_file;
	}
	
	@Face(displayName="견적 관련 문서")
	public String getEstimate_file() {
		return estimate_file;
	}
	public void setEstimate_file(String estimate_file) {
		this.estimate_file = estimate_file;
	}
	
	@Face(displayName="발주서 문서")
	public String getFavorite_file() {
		return favorite_file;
	}
	public void setFavorite_file(String favorite_file) {
		this.favorite_file = favorite_file;
	}
		
	@Face(displayName="계약 관련 문서")
	public String getContact_file() {
		return contact_file;
	}
	public void setContact_file(String contact_file) {
		this.contact_file = contact_file;
	}
	
	@Face(displayName="영업활동 소요경비")
	public String getSpent_expenses() {
		return spent_expenses;
	}
	public void setSpent_expenses(String spent_expenses) {
		this.spent_expenses = spent_expenses;
	}
	
	@Face(displayName="검토 요청")
	public String getRequest_check() {
		return request_check;
	}
	public void setRequest_check(String request_check) {
		this.request_check = request_check;
	}
	
	@Face(displayName="기타 특이사항")
	public String getSpecial_contents() {
		return special_contents;
	}
	public void setSpecial_contents(String special_contents) {
		this.special_contents = special_contents;
	}
	
}
