package org.uengine.kernel.designer.inputter;

import java.io.Serializable;
import java.util.Calendar;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.uengine.kernel.TimerEventActivity;
import org.uengine.scheduler.SchedulerUtil;

public class CronExpressionInputter implements Serializable, ContextAware {
	
	public CronExpressionInputter(){
		
	}
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	public void load(String duration){
		metaworksContext = new MetaworksContext();
		metaworksContext.setWhen(MetaworksContext.WHEN_EDIT);
		
		minute = new SelectBox();
		hour = new SelectBox();
		dayOfMonth = new SelectBox();
		month = new SelectBox();
		this.setDuration(duration);
		
		this.loadMinute();
		this.loadHour();
		this.loadDayOfMonth();
		this.loadMonth();
		
		
		if( TimerEventActivity.WAITING_TYPE_PERIOD.equals(duration)){
			
			dayOfWeek = new SelectBox();
			year = new SelectBox();
			
			
			this.loadDayOfWeek();
			this.loadYear();
			
			if( resultExpression != null && resultExpression.length() > 0){
				String[] exp = resultExpression.split(" ");
				if( exp.length >= 7 ){
					minute.setSelected(exp[1]);
					hour.setSelected(exp[2]);
					dayOfMonth.setSelected(exp[3]);
					month.setSelected(exp[4]);
					dayOfWeek.setSelected(exp[5]);
					year.setSelected(exp[6]);
				}
			}
		}else if( TimerEventActivity.WAITING_TYPE_UNTIL.equals(duration)){
			if( resultExpression != null && resultExpression.length() > 0){
				String[] exp = resultExpression.split(" ");
				if( exp.length >= 4 ){
					minute.setSelected(exp[0]);
					hour.setSelected(exp[1]);
					dayOfMonth.setSelected(exp[2]);
					month.setSelected(exp[3]);
				}
			}else{
				// default
				minute.setSelected("0");
				hour.setSelected("0");
				dayOfMonth.setSelected("-");
				month.setSelected("-");
			}
		}
	}
	
	public void loadMinute(){
		int maxVal = 60;
		for(int i =0 ; i < maxVal; i++){
			minute.add(String.valueOf(i), String.valueOf(i));
		}
		if( TimerEventActivity.WAITING_TYPE_PERIOD.equals(duration)){
			minute.add("*", "*");
		}
	}
	public void loadHour(){
		int maxVal = 24;
		for(int i =0 ; i < maxVal; i++){
			hour.add(String.valueOf(i), String.valueOf(i));
		}
		if( TimerEventActivity.WAITING_TYPE_PERIOD.equals(duration)){
			hour.add("*", "*");
		}
	}
	public void loadDayOfMonth(){
		if( TimerEventActivity.WAITING_TYPE_PERIOD.equals(duration)){
			dayOfMonth.add("*", "*");
		}else{
			dayOfMonth.add("-", "-");
		}
		int maxVal = 32;
		for(int i =1 ; i < maxVal; i++){
			dayOfMonth.add(String.valueOf(i), String.valueOf(i));
		}
		if( TimerEventActivity.WAITING_TYPE_PERIOD.equals(duration)){
			dayOfMonth.add("L", "L");		// L 지정할 수 있는 범위의 마지막 값
			dayOfMonth.add("?", "?");
		}
	}
	public void loadMonth(){
		if( TimerEventActivity.WAITING_TYPE_PERIOD.equals(duration)){
			month.add("*", "*");
		}else{
			month.add("-", "-");
		}
		int maxVal = 13;
		for(int i =1 ; i < maxVal; i++){
			month.add(String.valueOf(i), String.valueOf(i));
		}
	}
	public void loadDayOfWeek(){
		dayOfWeek.add("?", "?");
		dayOfWeek.add("*", "*");
		dayOfWeek.add("MON", "MON");
		dayOfWeek.add("TUE", "TUE");
		dayOfWeek.add("WED", "WED");
		dayOfWeek.add("THU", "THU");
		dayOfWeek.add("FRI", "FRI");
		dayOfWeek.add("SAT", "SAT");
		dayOfWeek.add("SUN", "SUN");
		dayOfWeek.add("MON-FRI", "MON-FRI");
	}
	public void loadYear(){
		year.add("*", "*");
		Calendar c = Calendar.getInstance();
		for (int i = c.get(Calendar.YEAR); i < c.get(Calendar.YEAR) + 50; i++) {
			year.add(String.valueOf(i), String.valueOf(i));
		}
	}

	public String getCronExpression() {		
		StringBuilder sb = new StringBuilder("0 ");
		sb.append(this.getMinute()).append(" ");
		sb.append(this.getHour()).append(" ");
		sb.append(this.getDayOfMonth()).append(" ");		
		sb.append(this.getMonth()).append(" ");
		sb.append(this.getDayOfWeek()).append(" ");
		sb.append(this.getYear());

		return sb.toString();
	}
	
	public String getDateExpression(){
		StringBuilder sb = new StringBuilder("");
		sb.append(this.getMinute()).append(" ");
		sb.append(this.getHour()).append(" ");
		sb.append(this.getDayOfMonth()).append(" ");		
		sb.append(this.getMonth()).append(" ");

		return sb.toString();
	}
	
	transient String duration;
		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}

	transient SelectBox minute;
		public SelectBox getMinute() {
			return minute;
		}
		public void setMinute(SelectBox minute) {
			this.minute = minute;
		}

	transient SelectBox hour;
		public SelectBox getHour() {
			return hour;
		}
		public void setHour(SelectBox hour) {
			this.hour = hour;
		}

	transient SelectBox dayOfMonth;
		public SelectBox getDayOfMonth() {
			return dayOfMonth;
		}
		public void setDayOfMonth(SelectBox dayOfMonth) {
			this.dayOfMonth = dayOfMonth;
		}

	transient SelectBox month;
		public SelectBox getMonth() {
			return month;
		}
		public void setMonth(SelectBox month) {
			this.month = month;
		}

	transient SelectBox dayOfWeek;
		public SelectBox getDayOfWeek() {
			return dayOfWeek;
		}
		public void setDayOfWeek(SelectBox dayOfWeek) {
			this.dayOfWeek = dayOfWeek;
		}

	transient SelectBox year;
		public SelectBox getYear() {
			return year;
		}
		public void setYear(SelectBox year) {
			this.year = year;
		}
		
	transient String resultExpressionStr;
		public String getResultExpressionStr() {
			return resultExpressionStr;
		}
		public void setResultExpressionStr(String resultExpressionStr) {
			this.resultExpressionStr = resultExpressionStr;
		}

	String resultExpression;
		@Hidden
		public String getResultExpression() {
			if( TimerEventActivity.WAITING_TYPE_PERIOD.equals(duration) && this.getMinute() != null){
				resultExpression = getCronExpression();
			}else if( TimerEventActivity.WAITING_TYPE_UNTIL.equals(duration) && this.getMinute() != null){
				resultExpression = getDateExpression();
			}
			return resultExpression;
		}
		public void setResultExpression(String resultExpression) {
			this.resultExpression = resultExpression;
		}
	
	@ServiceMethod(callByContent=true)
	public void expression() throws Exception{
//		System.out.println(getCronExpression());
		// expression 검증
		Calendar modifyCal = SchedulerUtil.getCalendarByCronExpression(getCronExpression());
		
		StringBuilder sb = new StringBuilder("");
		if( !getYear().toString().contentEquals("*")){
			sb.append(getYear() + "년 ");
		}
		
		if(getDayOfWeek().toString().contentEquals("?")){
			sb.append("아무 요일 ");
		}else if(getDayOfWeek().toString().contentEquals("*")){
			sb.append("모든 요일 ");
		}else{
			sb.append(getDayOfWeek() + " ");
		}
		
		if(getMonth().toString().contentEquals("*")){
			sb.append("매월 ");
		}else{
			sb.append(getMonth() + "월 ");
		}
		
		if(getDayOfMonth().toString().contentEquals("?")){
			sb.append("아무 날이나 ");
		}else if(getDayOfMonth().toString().contentEquals("*")){
			sb.append("매일 ");
		}else if(getDayOfMonth().toString().contentEquals("L")){
			sb.append("마지막날 ");
		}else{
			sb.append(getDayOfMonth() + "일 ");
		}
		
		if(getHour().toString().contentEquals("*")){
			sb.append("매시간 ");
		}else{
			sb.append(getHour() + "시 ");
		}
		
		if(getMinute().toString().contentEquals("*")){
			sb.append("매분 ");
		}else{
			sb.append(getMinute() + "분 ");
		}
//		System.out.println(sb.toString());
		setResultExpressionStr(sb.toString());
	}
}
