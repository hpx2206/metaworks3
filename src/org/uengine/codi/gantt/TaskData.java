package org.uengine.codi.gantt;

import org.metaworks.annotation.Face;

public class TaskData {
	
	private String uid;
		@Face(options={"prefix"}, values={"@"})
		public String getUid() {
			return uid;
		}
	
		public void setUid(String uid) {
			this.uid = uid;
		}
	private String seqNo;
		@Face(options={"prefix"}, values={"@"})
		public String getSeqNo() {
			return seqNo;
		}
	
		public void setSeqNo(String seqNo) {
			this.seqNo = seqNo;
		}
	private String parent;
		@Face(options={"prefix"}, values={"@"})
		public String getParent() {
			return parent;
		}
	
		public void setParent(String parent) {
			this.parent = parent;
		}
	private String from;
		@Face(options={"prefix"}, values={"@"})
		public String getFrom() {
			return from;
		}
	
		public void setFrom(String from) {
			this.from = from;
		}
	private String to;
		@Face(options={"prefix"}, values={"@"})
		public String getTo() {
			return to;
		}
	
		public void setTo(String to) {
			this.to = to;
		}
	private int wbsLevel;
		@Face(options={"prefix"}, values={"@"})
		public int getWbsLevel() {
			return wbsLevel;
		}
	
		public void setWbsLevel(int wbsLevel) {
			this.wbsLevel = wbsLevel;
		}
	private String isLeaf;
		@Face(options={"prefix"}, values={"@"})
		public String getisLeaf() {
			return isLeaf;
		}
	
		public void setisLeaf(String isLeaf) {
			this.isLeaf = isLeaf;
		}
	private String title;
		public String getTitle() {
			return title;
		}
	
		public void setTitle(String title) {
			this.title = title;
		}
	private String type;
		public String getType() {
			return type;
		}
	
		public void setType(String type) {
			this.type = type;
		}
	private String startDate;
		public String getStartDate() {
			return startDate;
		}
	
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
	private String endDate;
		public String getEndDate() {
			return endDate;
		}
	
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
	private int duration;
		public int getDuration() {
			return duration;
		}
	
		public void setDuration(int duration) {
			this.duration = duration;
		}
	private String startDate_run;
		public String getStartDate_run() {
			return startDate_run;
		}
	
		public void setStartDate_run(String startDate_run) {
			this.startDate_run = startDate_run;
		}
	private String endDate_run;
		public String getEndDate_run() {
			return endDate_run;
		}
	
		public void setEndDate_run(String endDate_run) {
			this.endDate_run = endDate_run;
		}
	private int duration_run;
		public int getDuration_run() {
			return duration_run;
		}
	
		public void setDuration_run(int duration_run) {
			this.duration_run = duration_run;
		}
	private String description;
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	private String status;
		public String getStatus() {
			return status;
		}
	
		public void setStatus(String status) {
			this.status = status;
		}
	private int priority;
		public int getPriority() {
			return priority;
		}
	
		public void setPriority(int priority) {
			this.priority = priority;
		}
	private String resources;
		public String getResources() {
			return resources;
		}
	
		public void setResources(String resources) {
			this.resources = resources;
		}
	private int completeRate;
		public int getCompleteRate() {
			return completeRate;
		}
	
		public void setCompleteRate(int completeRate) {
			this.completeRate = completeRate;
		}
	private int completeRate_run;
		public int getCompleteRate_run() {
			return completeRate_run;
		}
	
		public void setCompleteRate_run(int completeRate_run) {
			this.completeRate_run = completeRate_run;
		}
	
	public TaskData(){

	}

}
