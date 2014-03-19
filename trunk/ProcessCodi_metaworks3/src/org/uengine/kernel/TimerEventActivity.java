package org.uengine.kernel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Employee;
import org.uengine.kernel.designer.inputter.CronExpressionInputter;
import org.uengine.scheduler.SchedulerUtil;

public class TimerEventActivity extends EventActivity implements IDrawDesigner {

	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;
	
	public static final String WAITING_TYPE_PERIOD 				= "WAIT_PERIOD";
	public static final String WAITING_TYPE_UNTIL 					= "WAIT_UNTIL";
	public static final String WAITING_TYPE_UNTIL_WITH_DATE 	= "WAIT_UNTIL_WITH_DATE";
	public static final String STOP_INSTANCE = "STOP_INSTANCE";
	public static final String PASS_INSTANCE = "PASS_INSTANCE";
	
	CronExpressionInputter expression;
	@Face(displayName="시간설정")
		public CronExpressionInputter getExpression() {
			return expression;
		}
		public void setExpression(CronExpressionInputter expression) {
			this.expression = expression;
		}

	String duration;
	@Face(displayName="기간설정", 
			ejsPath="dwr/metaworks/genericfaces/RadioButton.ejs", 
			options={"for the next occurrance set by 'Cron Expression'","until given 'WaitUntil'","until given 'variable date + WaitUntil'"}, 
			values={WAITING_TYPE_PERIOD, WAITING_TYPE_UNTIL, WAITING_TYPE_UNTIL_WITH_DATE})
		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}

	String instanceStop;	
	@Face(displayName="인스턴스 종료", ejsPath="dwr/metaworks/genericfaces/RadioButton.ejs", 
			options={"stop Instance after wait give 'Date'", "no"}, 
			values={STOP_INSTANCE, PASS_INSTANCE})
		public String getInstanceStop() {
			return instanceStop;
		}
		public void setInstanceStop(String instanceStop) {
			this.instanceStop = instanceStop;
		}
		
	public TimerEventActivity(){
		setName("timerEvent");
	}
	
	transient String parentEditorId;
		@Hidden
		public String getParentEditorId() {
			return parentEditorId;
		}
		public void setParentEditorId(String parentEditorId) {
			this.parentEditorId = parentEditorId;
		}
	@Override
	public void drawInit() throws Exception {
		if( expression == null ){
			expression = new CronExpressionInputter();
		}
		if( duration == null ){
			duration = WAITING_TYPE_PERIOD;
		}
		if( instanceStop == null ){
			instanceStop = PASS_INSTANCE;
		}
		expression.setParentEditorId(parentEditorId);
		expression.setDuration(duration);
	}
	
	@ServiceMethod( callByContent=true ,target=ServiceMethodContext.TARGET_APPEND, eventBinding="change", bindingFor={"duration"})
	public Object[] changeDuration() throws Exception{
		expression.setPreLoaded(false);
		expression.setDuration(duration);
		return new Object[]{new Refresh(expression)};
	}
		
	@Override
	protected void executeActivity(ProcessInstance instance) throws Exception {
		//start listens...
		String resultExpression = expression.getResultExpression();
		if( WAITING_TYPE_PERIOD.equals(duration)){
			Calendar modifyCal = SchedulerUtil.getCalendarByCronExpression(resultExpression);
			this.addSchedule(instance, this.getTracingTag(), modifyCal, resultExpression);
		}else if( WAITING_TYPE_UNTIL.equals(duration) || WAITING_TYPE_UNTIL_WITH_DATE.equals(duration)){
			Calendar modifyCal = Calendar.getInstance();
			
			String[] exp = resultExpression.split(" ");
			if( WAITING_TYPE_UNTIL_WITH_DATE.equals(duration) ){
				// 프로세스 변수
				String srcVariableName = exp[4];
				Object val = instance.getBeanProperty(srcVariableName);
				if( val != null && val instanceof Calendar){
					modifyCal = (Calendar)val;
				}
			}
			int minutes = Integer.parseInt(exp[0]) ; 
			int hours = Integer.parseInt(exp[1]) ;
			int days = 0;
			if(!exp[2].contentEquals("-")){
				days = Integer.parseInt(exp[2]) ;
			}
			int months = 0;
			if(!exp[3].contentEquals("-")){
				months = Integer.parseInt(exp[3]) ;
			}
			
			if( minutes != 0 ){
				modifyCal.add(Calendar.MINUTE, minutes);
			}
			if( hours != 0 ){
				modifyCal.add(Calendar.HOUR_OF_DAY, hours);
			}
			if( days != 0 ){
				modifyCal.add(Calendar.DATE, days);
			}
			if( months != 0 ){
				modifyCal.add(Calendar.MONTH, months-1);
			}
			
			this.addSchedule(instance, this.getTracingTag(), modifyCal, null);
		}else{
			fireComplete(instance);
		}
		
	}
	
	protected void addSchedule(ProcessInstance instance, String tracingTag, Calendar modifyCal, String expression) throws Exception { 		
		Connection conn = null;
		PreparedStatement  pstmt = null;
		ResultSet rs = null;
		String sqlOldSchDelete =  "DELETE FROM SCHEDULE_TABLE WHERE INSTID = ? AND TRCTAG = ? ";
		String sqlSEQ = "SELECT MAX(SCHE_IDX) FROM SCHEDULE_TABLE";
		String sql = " INSERT INTO SCHEDULE_TABLE(SCHE_IDX, INSTID, TRCTAG, STARTDATE, expression, newInstance,defId,GLOBALCOM) VALUES(?, ?, ?, ?, ?, ?, ?, ?) "; 
        
		try {
			conn = instance.getProcessTransactionContext().getConnection();
			pstmt = conn.prepareStatement(sqlSEQ);
			rs = pstmt.executeQuery();

			int max = 0;
			if (rs.next()) {
				max = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			
			pstmt = conn.prepareStatement(sqlOldSchDelete);
			pstmt.setString(1, instance.getInstanceId());
			pstmt.setString(2, tracingTag);
			pstmt.executeUpdate();
			pstmt.close();
			
			int newInstance = 0;
			if( this.getIncomingTransitions().size() == 0 ){
				// TODO intermidediate 일 경우에는 incomming이 없고, 엑티비티안쪽에서 작용하는 것이라 새로운 인스턴스를 발행하는게 아니라 다른 방식이 필요함 
				newInstance = 1;
			}
			String globalcom = null;
			ActivityReference ref =instance.getProcessDefinition().getInitiatorHumanActivityReference(instance.getProcessTransactionContext());
			Activity humanActivity = ref.getActivity();
			if( humanActivity != null ){
				RoleMapping rm = instance.getRoleMapping(((HumanActivity)humanActivity).getRole().getName());
				Employee emp = new Employee();
				emp.setEmpCode(rm.getEndpoint());
				globalcom = emp.databaseMe().getGlobalCom();
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, max == 0 ? 1 : max + 1);
			pstmt.setString(2, instance.getInstanceId());
			pstmt.setString(3, tracingTag);
			pstmt.setTimestamp(4, new Timestamp(modifyCal.getTimeInMillis()));
			pstmt.setString(5, expression);
			pstmt.setInt(6, newInstance);
			pstmt.setString(7, instance.getProcessDefinition().getId());
			pstmt.setString(8, globalcom);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		}  finally {
        	if (rs!= null) try { rs.close(); } catch (Exception e) { }
            if (pstmt != null) try { pstmt.close(); } catch (Exception e) { }
        }

       
	}
}
