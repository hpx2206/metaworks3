<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<%@page import="org.uengine.kernel.ActivityInstanceContext"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%--
  - Author(s): Sungsoo Park
  - Copyright Notice:
Copyright 2001-2005 by HANWHA S&C Corp.,
All rights reserved.


This software is the confidential and proprietary information
of HANWHA S&C Corp. ("Confidential Information").  You
shall not disclose such Confidential Information and shall use
it only in accordance with the terms of the license agreement
you entered into with HANWHA S&C Corp.
  - @(#)
  - Description: 전자결재 화면
--%><%@ 
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ 
taglib
	prefix="bpm" uri="http://hsnc.hanwha.co.kr/taglibs/bpm"%><%@ 
include
	file="../../common/commons.jsp"%><%@ 
page import="java.util.Vector"%><%@ 
page
	import="org.uengine.kernel.ScopeActivity"%><%@ 
page
	import="org.uengine.kernel.EventHandler"%><%@ 
page
	import="org.uengine.kernel.UEngineException"%><%@ 
page
	import="org.uengine.kernel.SubProcessActivity"%><%@ 
page
	import="org.uengine.kernel.Role"%><%@ 
page
	import="org.uengine.kernel.RoleMapping"%><%@ 
page
	import="org.uengine.kernel.GlobalContext"%><%@ 
page
	import="hanwha.bpm.activitytypes.ApprovalActivity"%><%@ 
page
	import="hanwha.bpm.activitytypes.ApprovalLineActivity"%><%@ 
page
	import="org.uengine.kernel.HumanActivity"%><%@ 
page
	import="org.uengine.kernel.ActivityReference"%><%@ 
page
	import="hanwha.bpm.form.DocumentViewHelper"%><%@ 
page
	import="hanwha.bpm.form.wih.WihVO"%><%@ 
page
	import="hanwha.bpm.form.FormInstance"%><%@ 
page
	import="hanwha.bpm.approval.flow.Flow"%><%@ 
page
	import="hanwha.bpm.approval.flow.FlowManager"%><%@ 
page
	import="hanwha.bpm.approval.ApprConstants"%><%@ 
page
	import="hanwha.bpm.form.ProcessVariableTranslator"%><%@ 
page
	import="hanwha.bpm.BPMConstants"%><%@ 
page
	import="hanwha.bpm.list.worklist.business.WorkListBusinessFacade"%><%@ 
page
	import="hanwha.bpm.list.datamodel.DataMap"%><%@ 
page
	import="hanwha.bpm.list.datamodel.DataList"%><%@ 
page
	import="hanwha.bpm.list.datamodel.QueryCondition"%><%@ 
page
	import="hanwha.bpm.list.datamodel.util.HttpUtils"%><%@ 
page
	import="hanwha.bpm.list.datamodel.util.FormatUtil"%><%@ 
page
	import="hanwha.bpm.approval.ApprDocAuth"%><%@ 
page
	import="hanwha.bpm.exception.FVException"%><%@ 
page
	import="hanwha.bpm.approval.AttachedDocumentList"%><%@ 
page
	import="hanwha.bpm.approval.AttachedDocument"%><%@ 
page
	import="hanwha.bpm.approval.dao.DocFlowDAOFacade"%><%@ 
page
	import="hanwha.bpm.approval.business.DocFlowBusinessFacade"%><%@ 
page
	import="hanwha.bpm.approval.dao.ReceiveInfoDAO"%><%@ 
page
	import="hanwha.bpm.approval.dao.ReceiverListDAO"%><%@ 
page
	import="hanwha.bpm.approval.dao.DocFlowRegisterDAO"%><%@
page
	import="hanwha.commons.dao.IDAO"%><%@
page
	import="hanwha.commons.dao.ConnectiveDAO"%><%@
page
	import="hanwha.bpm.approval.dao.ReceiverConfirmDAO"%>
<%!//private static int threadCnt = 0;
	private static Logger apprLogger = Logger
			.getLogger("bpm.web_wih_approval_index");

	private static boolean isDisplay(String key, List list) {
		return (list != null && list.contains(key));
	}

	public static String escapeTitle(String str) {
		if (str == null)
			return "";
		StringBuffer buffer;
		char ch;
		String entity;

		buffer = null;
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			switch (ch) {
				case '\"' :
					entity = "&quot;";
					break;
				case '\r' :
					entity = "&#xD;";
					break;
				case '\t' :
					entity = "&#x9;";
					break;
				case '\n' :
					entity = "&#xA;";
					break;
				default :
					//	 if (strategy.shouldEscape(ch)) {
					//	 entity = "&#x" + Integer.toHexString(ch) + ";";
					//	 }
					//	 else {
					entity = null;
					//	 }
					break;
			}
			if (buffer == null) {
				if (entity != null) {
					// An entity occurred, so we'll have to use StringBuffer
					// (allocate room for it plus a few more entities).
					buffer = new StringBuffer(str.length() + 20);
					// Copy previous skipped characters and fall through
					// to pickup current character
					buffer.append(str.substring(0, i));
					buffer.append(entity);
				}
			} else {
				if (entity == null) {
					buffer.append(ch);
				} else {
					buffer.append(entity);
				}
			}
		}

		// If there were any entities, return the escaped characters
		// that we put in the StringBuffer. Otherwise, just return
		// the unmodified input string.
		return (buffer == null) ? str : buffer.toString();
	}%>
<%
	// 엡로직의 ThreadName이 너무 길어서 짧게 줄임. 
	// ExecuteThread: '94' for queue: 'weblogic.kernel.Default'
	//if ( threadCnt > 10000000 ) {
	//	threadCnt = 0;
	//}
	//int currentThreadId = threadCnt++;
	//String threadId = "AIX"+currentThreadId;
	//Thread.currentThread().setName(threadId);

	long totalTime = System.currentTimeMillis();
	apprLogger.info("[time check] start : "
			+ (System.currentTimeMillis() - totalTime));
	/***********************************************************************/
	/*                            Define                                   */
	/***********************************************************************/
	DataMap reqMap = HttpUtils.getDataMap(request, false);
	String filtering = reqMap.getString("FILTERING", "");
	String menuId = reqMap.getString("menuId", "");

	// Include Mode
	boolean isIncludeMode = ("true".equals(reqMap.getString(
			"ISINCLUDEMODE", "")));

	String menuItemId = reqMap.getString("MENU_ITEMID",
			BPMConstants.MENUITEM_KEY_BPM); // 메뉴아이템 아이디.
	String folderName = (String) MAP_FOLDER_NAME.get(filtering); // 폴더명.
	if (BPMConstants.MENUITEM_KEY_APP.equals(menuItemId)) {
		folderName = (String) MAP_FOLDER_NAME_APP.get(filtering); // 폴더명.
	} else if (BPMConstants.MENUITEM_KEY_SR.equals(menuItemId)) {
		folderName = (String) MAP_FOLDER_NAME_SR.get(filtering); // 폴더명.
	} else if (BPMConstants.MENUITEM_KEY_OS.equals(menuItemId)) {
		folderName = (String) MAP_FOLDER_NAME_ONE.get(filtering); // 폴더명.
	} else if (BPMConstants.MENUITEM_KEY_NP.equals(menuItemId)) {
		folderName = (String) MAP_FOLDER_NAME_NEW.get(filtering); // 폴더명.
	} else if (BPMConstants.MENUITEM_KEY_REQ.equals(menuItemId)) {
		folderName = (String) MAP_FOLDER_NAME_GWREQ.get(filtering); // 폴더명.
	}
%>
<%
	///////////////////////////
	// Start Transaction
	///////////////////////////
	RequestContext reqCtx = new RequestContext(pageContext);
	ProcessTransactionContext tc = new ProcessTransactionContext();
	WihVO wihVO = null;
	try {
		tc.setAutoCommit(false);
		//tc.setThreadId(threadId);
		tc.setRequestContext(reqCtx);
%>
<%
	apprLogger.info("[time check] 1 : "
				+ (System.currentTimeMillis() - totalTime));

		// InnoAP 사용 여부
		BPMUtil.setContextData("USE_INNOAP", "Y", pageContext);
		//BPMUtil.setContextData("USE_INNOAP", "N", pageContext);

		wihVO = new WihVO(); // Work Item Handler 에 대한 Value Object 생성
		wihVO.populate(pageContext);
		apprLogger.info("wihVO : " + wihVO);

		apprLogger.info("[time check] 2 : "
				+ (System.currentTimeMillis() - totalTime));

		String headerHTML = null;
		String docTypeId = null; // 문서형태

		boolean isMod = reqMap.getBoolean("_mod", false); // 문서수정 모드 
		BPMUtil.setContextData("isMod", isMod + "", pageContext);

		boolean hasInstance = false; // 프로세스 진행여부
		boolean isDone = false; // 결재완료여부
		boolean isRejected = false; // 반려문서여부

		boolean isEnabledTransfer = false;

		boolean isPrintInDraft = reqMap.getBoolean("isPrintInDraft",
				false); // 기안단계에서의 인쇄
		boolean isPrint = reqMap.getBoolean("isPrint", false); // 인쇄

		boolean display = false;

		int[] auth = null; // 권한

		String transferDocNumber = null; // 이관된 문서번호
		boolean beforeEDMTransfer = true; // EDM 이관전 (웹로직 taglib 버그로 인해 플래그를 두개로 분리)
		boolean completeEDMTransfer = false; // EDM 이관후 (웹로직 taglib 버그로 인해 플래그를 두개로 분리)
		String isPopup = "N";

		boolean formValidate = true; // 
		boolean isTransSihangDoc = false; // 시행문 발송 여부

		boolean isGianDoc = false; // 기안문(지출결의) 여부
		boolean isSihangDoc = false; // 시행문 여부
		boolean isOutDoc = false; // 대외발신문서
		boolean isSihangOrOutDoc = false; // 시행문 or 대외발신문서 여부
		boolean isAddSihangDoc = false; // 기안문 안에 포함된 시행문 or 대외발신문서
		boolean isPayDoc = false;

		// 나모 웹 에디터 (ActiveSquare 초기화 파일 적용)
		BPMUtil.setContextData("InitFileURL", "true", pageContext);

		// 프로세스관리자, 폼관리자, 폼 ViewHelper 생성
		ProcessManager pm = ProcessManagerFactory.create(tc);
		DocumentViewHelper docVH = DocumentViewHelper.create(tc);
		ProcessVariableTranslator pvt = ProcessVariableTranslator
				.getInstance(tc);
		ProcessDefinition definition = null;
		ProcessInstance instance = null;
		HumanActivity humanActivity;
%>
<%
	// 겸직일 경우 이전 화면에서 선택된 부서로 세션정보 변경
		if (wihVO.getDocumentMode() == null)
			return;
		if (MODE_WRITE.equals(wihVO.getDocumentMode())
				&& request.getParameter("changeCurrentDept") != null) {
			String changeDeptId = request
					.getParameter("changeCurrentDept");
			hanwha.bpm.util.SessionUtil.changeCurrentDept(request,
					changeDeptId);
			loggedUser = (hanwha.bpm.session.User) session
					.getAttribute("loggedUser");
			if (loggedUser != null) {
				loggedUserDeptName = loggedUser.getDeptName();
				loggedUserDeptId = loggedUser.getDeptId();
			}
		}

		//	 겸직일 경우 ㅇ미시저장에서 선택된 부서로 세션정보 변경 (2009.09)
		if (wihVO.getDocumentMode().equals("rewrite_preview")
				&& request.getParameter("changeCurrentDept") != null) {
			String changeDeptId = request
					.getParameter("changeCurrentDept");
			hanwha.bpm.util.SessionUtil.changeCurrentDept(request,
					changeDeptId);
			loggedUser = (hanwha.bpm.session.User) session
					.getAttribute("loggedUser");
			if (loggedUser != null) {
				loggedUserDeptName = loggedUser.getDeptName();
				loggedUserDeptId = loggedUser.getDeptId();
			}
		}
%>
<%
	apprLogger.info("[time check] 3 : "
				+ (System.currentTimeMillis() - totalTime));

		if (!wihVO.isInitiate()
				&& GWUtil.isNotEmpty(wihVO.getInstanceId())) {
			apprLogger.info("instanceId : " + wihVO.getInstanceId());
			instance = pm.getProcessInstance(wihVO.getInstanceId());

			if (instance != null) {
				wihVO.setRootInstanceId(instance
						.getRootProcessInstanceId());
				docTypeId = instance.getInstanceDAO().getDefTypeId();
				if (docTypeId == null)
					docTypeId = "";
				BPMUtil.setContextData("_docTypeId", docTypeId,
						pageContext);

				isEnabledTransfer = instance.isEnabledTransfer();

				hasInstance = true; // 프로세스 진힝여부 세팅
				isDone = (hasInstance && instance.isDone()); // 프로세스 완료여부 세팅
				isRejected = (hasInstance && instance.isRejected()); // 프로세스 반려여부 세팅
			}
		}

		apprLogger.info("[doc status] hasInstance : " + hasInstance
				+ ", isDone : " + isDone + ", isRejected : "
				+ isRejected);

		apprLogger.info("[time check] 4 : "
				+ (System.currentTimeMillis() - totalTime));
		if (hasInstance) {
			definition = instance.getProcessDefinition();
			wihVO.setDefinitionVersionId(definition.getId());
		} else {
			definition = pm.getProcessDefinition(wihVO
					.getDefinitionVersionId());
			definition = (ProcessDefinition) definition.clone();
		}
		wihVO.setDefinitionId(definition.getBelongingDefinitionId());
		//	apprLogger.info("definition code : " + definition.getAlias());
		apprLogger.info("[time check] 5 : "
				+ (System.currentTimeMillis() - totalTime));
		if (wihVO.isInitiate()) {

			/**
			 * @author 이용홍
			 * @version 2011.11.15
			 * ProcessVariableTranslator 의
			 * public void doProcess(ProcessDefinition definition, final ProcessInstance instance) throws Exception {}
			 * 에는 instance 가 null 이라면 폼의 in, out 맵퍼 자체를 타지 않는다. 따라서 fake process instance 를 발생시킨 후 rollback 을 해야한다.
			 * 또한 definition 이 프로세스의 첫 휴먼 액티비티가 존재하는 definition 을 넣어줘야 하는데,
			 * 현재의 경우 fake process instance 를 발생시키더라도 첫 휴먼 액티비티가 서브프로세스에 존재하는 경우 상위의 root 의 definition을 보게 된다.
			 *
			 * 그래서 결론적으로는  임의로 인스턴스를 발생시키고 실행한 다음, 서브프로세스를 포함한 running 중인 액티비티를 찾는다.
			 * 이 running 중인 액티비티는 전체 프로세스의 첫 휴먼 액티비티 시작점이라고 볼 수 있다.(여러개 일수도 있지만 모두가 첫 휴먼 액티비티라고 볼 수 있다.)
			 * anyway, 이 running 중인 첫 시작 휴먼 액티비티를 찾아서 humanActivity 에 넣어주고, instance 와 definition 도 재설정 해준다.
			 */
			instance = pm.initializeProcess(definition);
			instance = pm.getProcessInstance(instance.getInstanceId());
			instance.execute();
			Vector vector = instance
					.getCurrentRunningActivitiesDeeply();
			ActivityInstanceContext aic = (ActivityInstanceContext) vector
					.get(vector.size() - 1);
			humanActivity = (HumanActivity) aic.getActivity();
			instance = aic.getInstance();
			definition = instance.getProcessDefinition();

			//ActivityReference actRef = definition.getInitiatorHumanActivityReference(tc);
			//humanActivity = (HumanActivity)actRef.getActivity();
		} else {
			if (GWUtil.isNotEmpty(wihVO.getTracingTag())) {
				humanActivity = (HumanActivity) definition
						.getActivity(wihVO.getTracingTag());
			} else {
				ActivityReference actRef = definition
						.getInitiatorHumanActivityReference(tc);
				humanActivity = (HumanActivity) actRef.getActivity();
			}
		}
%>
<%
	// 문서형태 판별
		if (hasInstance) {
			isGianDoc = BPMUtil.hasDocType(docTypeId,
					BPMConstants.DOCTYPE_GIAN);
			isSihangDoc = BPMUtil.hasDocType(docTypeId,
					BPMConstants.DOCTYPE_SISHANG);
			isOutDoc = BPMUtil.hasDocType(docTypeId,
					BPMConstants.DOCTYPE_OUT);
			isSihangOrOutDoc = (isSihangDoc || isOutDoc);
			isPayDoc = BPMUtil.hasDocType(docTypeId,
					BPMConstants.DOCTYPE_PAY);
			apprLogger.info("docTypeId : " + docTypeId
					+ ", isGianDoc : " + isGianDoc
					+ ", isSihangOrOutDoc : " + isSihangOrOutDoc);
		}
		apprLogger.info("[time check] 6 : "
				+ (System.currentTimeMillis() - totalTime));

		// 문서 이관여부 판별
		if (isDone) {
			transferDocNumber = BPMUtil.getDocNumber(instance
					.getInstanceDAO().getInstanceId(), tc); // 이관이 정상적으로 완료되었을 경우에만 문서번호가 넘어옴.
			if (GWUtil.isNotEmpty(transferDocNumber)
					&& !wihVO.getDocumentMode().equals(
							BPMConstants.MODE_HISTORY)) {
				beforeEDMTransfer = false;
				completeEDMTransfer = true;
			}
		}

		// 2009.10.19 - 해당건 권한 있는지 체크 권한이 없으면 버튼 안보여지도록 처리
		boolean ownerShipCheck = true;
		/*
		 if( !wihVO.isInitiate() && GWUtil.isNotEmpty(wihVO.getInstanceId()) ) {
		 // 2009.11.10 권한체크 추가
		 // 2009.10.23 막음 결재쪽에 승인 올린 후에도 결재취소 등의 처리가 필요함.
		 // 2009.10.19 - 해당건 권한 있는지 체크 권한이 없으면 버튼 안보여지도록 처리
		 if (wihVO.getTaskId() != null && !wihVO.getTaskId().equals("")){
		 System.out.println("isGianDoc: "+isGianDoc + ", isSihangDoc: "+ isSihangDoc + ",isOutDoc : "+isOutDoc + ", isPayDoc:"+isPayDoc);
		 if (!isGianDoc && !isSihangDoc && !isOutDoc && !isPayDoc ){
		 // 2009.11.16 기안/시행문/대외발신문서/ 제외하고 오너쉽체크 
		 WorkListBusinessFacade workListBusinessFacade = new WorkListBusinessFacade();
		 QueryCondition condition = new QueryCondition();
		 reqMap.put("taskId", wihVO.getTaskId());
		 reqMap.put("userid", loggedUser.getUserId());
		 condition.setMap(reqMap); 
		 ownerShipCheck = workListBusinessFacade.getWorkListOwnerShipCheck(condition);
		 }
		 }
		 }
		 */
%>
<%
	///////////////////////////////////
		// 전자결재 권한 체크 및 DRM 적용
		///////////////////////////////////
		String defID = wihVO.getDefinitionId();
		boolean isApproval = false;
		//기안문 || 시행문 || 대내 시행문 내부결재 || 시행결과보고 || 지급기안 || 대외발신 || 대외문서 내부결재 || 회람서 
		if (defID.equals("238") || defID.equals("485")
				|| defID.equals("531") || defID.equals("2173")
				|| defID.equals("615") || defID.equals("829")
				|| defID.equals("627") || defID.equals("489")) {
			isApproval = true;
		}

		System.out.println("this defID is " + defID
				+ "::: isApproval is " + isApproval);
		//System.out.println("hasInstance is " + hasInstance + "::: BPMConstants.PRODUCTION_MODE is " + BPMConstants.PRODUCTION_MODE);
		System.out.println("filtering is " + filtering);

		//if ( hasInstance && BPMConstants.PRODUCTION_MODE ) {
		if (hasInstance) { //문서보안 적용 테스트 때문에 production mode 체크 해제.

			if (isApproval) { //전자결재
				ApprDocAuth docAuth = new ApprDocAuth();
				if (!isDone) { // 진행문서
					int apprAuth = docAuth.getApprAuth(
							loggedUserLoginName, wihVO.getInstanceId());
					String docType = "REG";
					if (isGianDoc || isPayDoc) {
						docType = "REG";
					} else if (isSihangOrOutDoc) {
						docType = "ENF";
					}
					if (filtering.equals("TEMPORARY_ARCHIVE")) { // 임시보관
						docType = "EXT";
					} else if (filtering.equals("PRIVATE_REJECTED")) { // 반려문서
						docType = "REF";
					}
					if (loggedUser.isDRMExt()) {
						BPMUtil.setContextData("isSecurity", "true",
								pageContext);
						BPMUtil.setContextData("docClass", "EXP",
								pageContext);
						BPMUtil.setContextData("docType", "NUL",
								pageContext);
						BPMUtil.setContextData("userCategory1", "NUL",
								pageContext);
						BPMUtil.setContextData("userCategory2", "NUL",
								pageContext);
					} else {
						BPMUtil.setContextData("isSecurity", "true",
								pageContext);
						BPMUtil.setContextData("docClass", "ING",
								pageContext);
						BPMUtil.setContextData("docType", docType,
								pageContext);
						BPMUtil.setContextData("userCategory1", "COO",
								pageContext);
						BPMUtil.setContextData("userCategory2", "NUL",
								pageContext);
					}
					System.out.println("this docType is " + docType);
				} else { // 완료문서
					apprLogger.info("loggedUserLoginName : "
							+ loggedUserLoginName);
					apprLogger.info("registerId : "
							+ wihVO.getRegisterId()
							+ ", receiveInfoId : "
							+ request.getParameter("receiveinfoId"));
					if ((isGianDoc || isPayDoc)
							&& GWUtil.isNotEmpty(wihVO.getRegisterId())) { // 기안문, 품의문
						auth = docAuth.getDocAuth(loggedUserLoginName,
								loggedUserDeptId,
								new Long(wihVO.getRegisterId()),
								ApprDocAuth.GIAN_DOC);
					} else if (isSihangOrOutDoc
							&& request.getParameter("receiveinfoId") != null) { // 시행문
						auth = docAuth
								.getDocAuth(
										loggedUserLoginName,
										loggedUserDeptId,
										new Long(
												request.getParameter("receiveinfoId")),
										ApprDocAuth.SIHANG_DOC);
					}
					if (auth != null) {
						apprLogger.info("auth[0] : " + auth[0]
								+ ", auth[1] : " + auth[1]);
						if (auth[0] < 0 && auth[1] < 0) {
							//out.println("<font color=red><b>보기 권한이 없습니다.</b></font>");
							//return;
						}
					}
					// 첨부파일 다운로드 DRM 적용
					String docType = "COM";
					if (isSihangOrOutDoc && docTypeId.equals("5")) {
						docType = "INT"; // 대외발신
						//} else if ( filtering.equals("PRIVATE_REJECTED") || filtering.equals("REJECTED") ) {	// 반려문서
						//	docType = "REF";
					} else if (filtering.indexOf("REJECTED") > -1) { // 반려문서
						docType = "REF";
					}

					String userCategory2 = "VEW";
					if (auth != null && auth[0] > -1 && auth[0] == 0) { // 작성자
						userCategory2 = "REG";
					}
					if (loggedUser.isDRMExt()) {
						BPMUtil.setContextData("isSecurity", "true",
								pageContext);
						BPMUtil.setContextData("docClass", "EXP",
								pageContext);
						BPMUtil.setContextData("docType", "NUL",
								pageContext);
						BPMUtil.setContextData("userCategory1", "NUL",
								pageContext);
						BPMUtil.setContextData("userCategory2", "NUL",
								pageContext);
					} else {
						BPMUtil.setContextData("isSecurity", "true",
								pageContext);
						BPMUtil.setContextData("docClass", "APP",
								pageContext);
						BPMUtil.setContextData("docType", docType,
								pageContext);
						BPMUtil.setContextData("userCategory1", "COO",
								pageContext);
						BPMUtil.setContextData("userCategory2",
								userCategory2, pageContext);
					}
					System.out.println("this docType is " + docType);
				}
			} else { //BPM
				BPMUtil.setContextData("isSecurity", "true",
						pageContext);
				BPMUtil.setContextData("docClass", "BPP", pageContext);
				BPMUtil.setContextData("docType", "NUL", pageContext);
				BPMUtil.setContextData("userCategory1", "NUL",
						pageContext);
				BPMUtil.setContextData("userCategory2", "NUL",
						pageContext);
			}
		} else {
			if (isApproval) { //전자결재
				BPMUtil.setContextData("isSecurity", "true",
						pageContext);
				BPMUtil.setContextData("docClass", "ING", pageContext);
				BPMUtil.setContextData("docType", "EXT", pageContext);
				BPMUtil.setContextData("userCategory1", "COO",
						pageContext);
				BPMUtil.setContextData("userCategory2", "NUL",
						pageContext);
			} else { // BPM
				BPMUtil.setContextData("isSecurity", "true",
						pageContext);
				BPMUtil.setContextData("docClass", "BPP", pageContext);
				BPMUtil.setContextData("docType", "NUL", pageContext);
				BPMUtil.setContextData("userCategory1", "NUL",
						pageContext);
				BPMUtil.setContextData("userCategory2", "NUL",
						pageContext);

			}
		}
		apprLogger.info("[time check] 7 : "
				+ (System.currentTimeMillis() - totalTime));
%>
<%
	// 시행문변환 판별
		if (isDone) {
			isTransSihangDoc = ((!isSihangDoc && !isOutDoc) && (filtering
					.equals("transAttachedDoc")
					|| filtering.equals("registerWaitDoc")
					|| filtering.equals("registerDoc")
					|| filtering.equals("assignedDoc")
					|| filtering.equals("receivedDoc")
					|| filtering.equals("receivedDocForPortlet")
					|| filtering.equals("outSendDoc")
					|| filtering.equals("indexedReg") || filtering
					.equals("KEEPDOC")));

			if (isTransSihangDoc) {
				beforeEDMTransfer = true;
				completeEDMTransfer = false;
			}
		}
%>
<%
	// 부서업무 여부 판단.
		int assignType = BPMConstants.WL_ASSIGNTYPE_USR; // 할당형태.	def : 사용자 직접 지정.
		if (instance != null) {
			RoleMapping roleMapping = humanActivity.getRole()
					.getMapping(instance);
			assignType = roleMapping.getAssignType();
		}

		// 경합여부.
		boolean isRacing = (assignType == BPMConstants.WL_ASSIGNTYPE_DPT || assignType == BPMConstants.WL_ASSIGNTYPE_GRP); // 부서업무, 그룹업무 인 경우.

		if (isRacing) {
			docVH.setDocumentMode(BPMConstants.MODE_READONLY);
		}

		// 부서업무일 경우 READ_ONLY.
		//	if( isRacing){// 부서업무, 그룹업무 인 경우.
		//	 wihVO.setDocumentMode(MODE_READONLY);
		//	 wihVO.setAssignType(String.valueOf(assignType));
		//	}
%>
<%
	//	 결재의 경우 HumanActivity가 ApprovalActivity를 상속받게 됨.
		ApprovalActivity approvalActivity = (ApprovalActivity) humanActivity;
		ApprovalLineActivity approvalLineActivity = approvalActivity
				.getApprovalLineActivity();
		wihVO.setFormId(approvalLineActivity.getFormId());
		if (wihVO.getDocumentMode().equals(MODE_PROCESS)
				|| wihVO.getDocumentMode().equals(MODE_WRITE)) {
			wihVO.setDraftActivity(approvalActivity.isDraftActivity());
		}

		// 프로세스 정의에 설정된 열람권한 체크 (by jjy)
		if (wihVO.getDocumentMode().equals(MODE_READONLY)
				&& instance instanceof org.uengine.kernel.BPMProcessInstance) {
			org.uengine.kernel.ProcessVariable fv = approvalLineActivity
					.getFormContextPV();
			if (fv != null) {
				Role openedRole = approvalLineActivity
						.getFormContextPV().getOpenRole();

				if (openedRole != null) {
					RoleMapping loggedRoleMapping = BPMUtil
							.createRoleMapping(loggedUser);

					if (!instance.isParticipant(loggedRoleMapping)) {

						boolean allowed = openedRole.containsMapping(
								instance, loggedRoleMapping);

						if (!allowed) {
%><font color=red><b>보기 권한이 없습니다.</b></font>
<%
	return;
						}
					}
				}
			}
		}
		apprLogger.info("[time check] 8 : "
				+ (System.currentTimeMillis() - totalTime));
%>
<%
	// 폼(문서) 초기화
		docVH.setApproval(true);
		docVH.initialize(wihVO, pageContext, instance, humanActivity);
		apprLogger.info("[time check] 9 : "
				+ (System.currentTimeMillis() - totalTime));
%>
<%
	if (!hasInstance) {
			isGianDoc = (docVH.getFormType().equals("approval2") || docVH
					.getFormType().startsWith("approval9"));
		}

		String authorTel = null;
		String authorDeptId = null;
		String authorDeptName = null;

		if (docVH.getFormInstance() != null) {
			authorTel = docVH.getFormInstance().getAuthor()
					.getPhoneNo();
			authorDeptId = docVH.getFormInstance().getAuthor()
					.getDeptId();
			authorDeptName = docVH.getFormInstance().getAuthor()
					.getDeptName();
		}
%>
<%
	if (wihVO.getDocumentMode().equals(MODE_WRITE)
				|| wihVO.getDocumentMode().equals(MODE_PROCESS)
				|| (isPrintInDraft && isPrint)) {
			// FormLink (XSLT)
			pvt.initialize(new Long(wihVO.getFormVerId()), false);
			//if( !docVH.isExistDocument() ){	// Document가 존재 하지 않을 경우 폼링크 수행.
			if (!isDone && wihVO.isDraftActivity()) {
				pvt.doProcess(definition, instance);
			}
		}

		if (wihVO.isDraftActivity()) {
			docVH.setTempFormInstance();
		}
		apprLogger.info("[time check] 10 : "
				+ (System.currentTimeMillis() - totalTime));
%>
<%
	// Event Trigger의 경우 가상으로 서브 프로세스를 발생시켜 doProcess효과를 얻은 후에 Rollback 해서 DB를 복구시킴.
		if (wihVO.isEventTrigger()) {
			apprLogger.info("EventTrigger 수행");
			ProcessTransactionContext willRollBackTC = new ProcessTransactionContext();
			String strInstanceId = null;
			try {
				willRollBackTC.setRequestContext(reqCtx);
				ProcessManager willRollBackPM = ProcessManagerFactory
						.create(willRollBackTC);

				//send message first
				ProcessInstance mainProcessInstance = willRollBackPM
						.getProcessInstance(wihVO.getMainInstanceId());
				mainProcessInstance.getProcessTransactionContext()
						.setWihVO(wihVO);
				ProcessDefinition mainProcessDefinition = mainProcessInstance
						.getProcessDefinition();
				ScopeActivity scopeActivity = (ScopeActivity) mainProcessDefinition
						.getActivity(wihVO.getMainTracingTag());
				scopeActivity.onMessage(mainProcessInstance,
						wihVO.getEventName());

				//get the initiated sub process instance
				EventHandler eventHandler = scopeActivity
						.getEventHandler(wihVO.getEventName());
				SubProcessActivity subProcessActivity = (SubProcessActivity) eventHandler
						.getHandlerActivity();
				Vector idVt = subProcessActivity
						.getSubprocessIds(mainProcessInstance);

				String subInstanceId = (String) idVt.get(0);
				wihVO.setInstanceId(subInstanceId);

				ProcessInstance virtualInstance = willRollBackPM
						.getProcessInstance(wihVO.getInstanceId());
				ProcessDefinition virtualDefinition = virtualInstance
						.getProcessDefinition();

				if (wihVO.getTracingTag().indexOf("@") > -1) { // 서브 프로세스 내에서 첫번째 워크아이템 시작인경우
					virtualInstance = virtualInstance
							.getSubProcessInstance(wihVO
									.getTracingTag());
					virtualDefinition = virtualInstance
							.getProcessDefinition();
				}

				pvt.initialize(new Long(wihVO.getFormVerId()), false);
				pvt.doProcess(virtualDefinition, virtualInstance);

			} catch (UEngineException ue) {
				if (ue.getErrorLevel() == UEngineException.FATAL) {
					throw ue;
				} else {
					apprLogger.error("Error in EventTrigger", ue);
					ue.printStackTrace();
				}
			} catch (Exception e) {
				apprLogger.error("Error in EventTrigger", e);
				e.printStackTrace();
			} finally {
				willRollBackTC.rollback();
			}
		}
		apprLogger.info("[time check] 11 : "
				+ (System.currentTimeMillis() - totalTime));
%>
<%
	////////////////////////////////////////////////////
		// 결재선 정보 조회
		////////////////////////////////////////////////////
		Flow flow = null;
		//String flowHTML = null;
		String flowXML = null;
		String multiDocXML = null;

		if (isPrintInDraft
				&& !wihVO.getDocumentMode()
						.equals(MODE_REWRITE_PREVIEW)) {
			flow = FlowManager.fromXml(
					BPMConstants.UTF8_XML_HEADER
							+ request.getParameter("flowXML"))
					.getFlow();
			flowXML = flow.toXml();
			multiDocXML = "<multi-doc/>";
			if (request.getParameter("multiDocument") != null) {
				multiDocXML = request.getParameter("multiDocument");
			}
		} else {
			if (wihVO.getDocumentMode().equals(MODE_REWRITE)
					|| wihVO.getDocumentMode().equals(
							MODE_REWRITE_PREVIEW)) { // 재작성
				flowXML = docVH.getFlowXML();
				flow = docVH.getFlow();
				multiDocXML = docVH.getFormInstance().getMultiDocXML();
			} else {
				// 문서수정 모드에서 인쇄 및 이력보기 에러 발생 수정
				if (isMod
						&& !isPrint
						&& !wihVO.getDocumentMode().equals(
								BPMConstants.MODE_HISTORY)) {
					flow = FlowManager.fromXml(
							BPMConstants.UTF8_XML_HEADER
									+ request.getParameter("flowXML"))
							.getFlow();
				}

				// 결재처리 업무 + 기안단계 + 요청저장 문서 존재 + 결재선 존재
				if (wihVO.getDocumentMode().equals(
						BPMConstants.MODE_PROCESS)
						&& hasInstance
						&& approvalActivity.isDraftActivity()
						&& docVH.getLocation() != null
						&& docVH.getFlow() != null) {
					flow = docVH.getFlow();
				}

				//flow = docVH.getUserWorkflow();	// Default 개인결재선
				if (flow == null) { // Default 개인결재선이 없는 경우 프로세스에서 결재선 계산
					flow = FlowManager.fromProcessDefinition(
							approvalLineActivity, instance, wihVO,
							reqCtx);
				}
				flowXML = flow.toXml();
				multiDocXML = "<multi-doc/>";
				if (docVH.getFormInstance() != null
						&& docVH.getFormInstance().getMultiDocXML() != null) {
					multiDocXML = docVH.getFormInstance()
							.getMultiDocXML();
				}
			}
		}
		apprLogger.info("[time check] 12 : "
				+ (System.currentTimeMillis() - totalTime));
%>
<%
	String delegateButtonText = "";
		String delegateType = "";
		if (wihVO.getDocumentMode().equals(MODE_PROCESS)) {
			if (approvalActivity instanceof hanwha.bpm.activitytypes.KLIApprovalSupervisorActivity) {
				delegateButtonText = "다른 감시자에게 전달";
				delegateType = ApprConstants.SIGNER_TYPE_KLI_SUPERVISOR
						+ "";
			}
			if (approvalActivity instanceof hanwha.bpm.activitytypes.KLIApprovalAuditActivity) {
				delegateButtonText = "다른 감사자에게 전달";
				delegateType = ApprConstants.SIGNER_TYPE_KLI_AUDIT + "";
			}
		}
%>
<%
	StringBuffer eventHandlerBtnHtml = new StringBuffer();
		boolean isEventHandlerEnabled = false;
		if (hasInstance) {

			Vector mls = instance.getMessageListeners("event");
			//	 ProcessDefinition definition = instance.getProcessDefinition();
			if (mls != null)
				for (int i = 0; i < mls.size(); i++) {
					ScopeActivity scopeAct = (ScopeActivity) definition
							.getActivity((String) mls.get(i));
					EventHandler[] ehs = scopeAct.getEventHandlers();
					if (ehs != null)

						for (int j = 0; j < ehs.length; j++) {
							EventHandler eventHandler = ehs[j];

							if (eventHandler.getHandlerActivity() instanceof SubProcessActivity) {
								//	 if( j == 0 ){
								//	 eventHandlerBtnHtml.append("")
								//	 .append("<td>") 
								//	 .append("<img src=\""+IMG+"/themes/default/groupware/images/list_bar_seroline.gif\" width=\"2\" height=\"17\" align=\"absmiddle\">")
								//	 .append("</td>");
								//	 }
								SubProcessActivity spAct = (SubProcessActivity) eventHandler
										.getHandlerActivity();
								String defId = spAct.getDefinitionId();

								isEventHandlerEnabled = true;

								// Event Handler Button.
								eventHandlerBtnHtml
										.append("<td>")
										.append("  <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">")
										.append("    <tr>")
										.append("      <td><img src=\""
												+ IMG
												+ "/themes/common/images/btn_01_blue.gif\"></td>")
										.append("      <td background=\""
												+ IMG
												+ "/themes/common/images/btn_02_blue.gif\" class=\"btn2_s\" nowrap><a href=\"#\" onclick=\"initiateEventHandlerWIH(\'"
												+ defId
												+ "\', \'"
												+ scopeAct
														.getTracingTag()
												+ "\',\'"
												+ eventHandler
														.getName()
												+ "\');\">"
												+ eventHandler
														.getDisplayName()
												+ "</a></td>")
										.append("      <td><img src=\""
												+ IMG
												+ "/themes/common/images/btn_03_blue.gif\"></td>")
										.append("    </tr>")
										.append("  </table>")
										.append("</td>");
								//eventHandlerBtnHtml.append("<input type=\"button\" name=\"urgeBtn\" value=\""+eventHandler.getDisplayName()+"\" onclick=\"initiateEventHandlerWIH(\'"+defId+"\', \'"+scopeAct.getTracingTag()+"\',\'"+eventHandler.getName()+"\');\" align=\"absmiddle\">");
							}
						}
				}
		}
		apprLogger.info("[time check] 13 : "
				+ (System.currentTimeMillis() - totalTime));
%>
<%
	request.setAttribute("pm", pm);
		request.setAttribute("definition", definition);
		request.setAttribute("instance", instance);
		request.setAttribute("wihVO", wihVO);
		request.setAttribute("inputConstantsMode",
				docVH.getInputConstantsMode() + "");
		request.setAttribute("tc", tc);
		request.setAttribute("drafter", flow.getDrafter());
		request.setAttribute("flow", flow);
		request.setAttribute("docVH", docVH);
		request.setAttribute("eventHandlerBtnHtml",
				eventHandlerBtnHtml.toString());
		request.setAttribute("menuItemId", menuItemId);
		request.setAttribute("folderName", folderName);
		request.setAttribute("delegateButtonText", delegateButtonText);
		request.setAttribute("menuId", menuId);
		request.setAttribute("approvalActivity", approvalActivity);
		if (completeEDMTransfer)
			request.setAttribute("completeEDMTransfer",
					completeEDMTransfer + "");
		if (transferDocNumber != null)
			request.setAttribute("transferDocNumber", transferDocNumber);
		request.setAttribute("isRacing", new Boolean(isRacing));
		request.setAttribute("isTempFI",
				new Boolean(docVH.getTempFormInstance() != null));
%>
<%
	/** 
		 * 문서유통 시작
		 * 시행문 및 안추가 처리
		 */
		String docIdx = null;
		int sealType = BPMConstants.DF_RECINFO_SEALTYPE_COMPANY; // default 값 (사인으로 날인)
		DocFlowDAOFacade docFlowDF = DocFlowDAOFacade.getInstance(tc);
		AttachedDocumentList attachedDocList = null;
		AttachedDocument attachedDoc = null;
		DocFlowRegisterDAO registerDAO = null; // 문서유통 MASTER 테이블 (BPM_DFREG)
		ReceiveInfoDAO currentReceiveInfoDAO = null; // 시행문 or 대외발신문서 (BPM_RECINFO)
		ReceiverConfirmDAO currentReceiverConfirmDAO = null; // 수신처 (BPM_RECEIVERCONFIRM)

		String isRcvUser = request.getParameter("isRcvUser");
		String confirmId = request.getParameter("confirmId");
		String receiveStatus = request.getParameter("receiveStatus");
		String receiverListId = request.getParameter("receiverlistId");
		if (confirmId != null && confirmId.equalsIgnoreCase("NAN")
				&& isRcvUser != null && isRcvUser.equals("1")) {
			currentReceiverConfirmDAO = docFlowDF.getConfirmDAO(request
					.getParameter("receiveInfoId"));
			confirmId = currentReceiverConfirmDAO.getConfirmId()
					.toString();
			receiveStatus = currentReceiverConfirmDAO
					.getReceiveStatus().toString();
			receiverListId = currentReceiverConfirmDAO
					.getReceiverListId().toString();
		}

		if (isDone && !isRejected
				&& (isSihangOrOutDoc || isGianDoc || isPayDoc)) {
			apprLogger.info("========시행문 및 안추가 처리==========");

			//모든화면을 같이 사용하므로 조회/수발자 모드를 Parameter로 받아
			//조회모드인 경우 수발자/기안자가 할 수 있는 버튼을 표시하지 않는다.
			//디폴트 모드는 조회모드.
			String docFlowViewActionMode = request
					.getParameter("docFlowViewActionMode");
			if (docFlowViewActionMode == null
					|| docFlowViewActionMode.equals("")) {
				docFlowViewActionMode = "VIEW";
			}
			BPMUtil.setContextData("docFlowViewActionMode",
					docFlowViewActionMode, pageContext);

			//감사참조시행문 처리
			String assignRefApprDoc = request
					.getParameter("assignRefApprDoc");
			if ("Y".equals(loggedUser.getRoleId("refApprDoc"))) {
				assignRefApprDoc = "true";
			}
			System.out.println("index - assignRefApprDoc : "
					+ assignRefApprDoc);
			BPMUtil.setContextData("assignRefApprDoc",
					assignRefApprDoc, pageContext);

			String assignMyself = request.getParameter("assignMyself");
			if (assignMyself == null || assignMyself.equals("")) {
				assignMyself = "false";
			}
			BPMUtil.setContextData("assignMyself", assignMyself,
					pageContext);
			String noAssignMyself = request
					.getParameter("noAssignMyself");
			if (noAssignMyself == null || noAssignMyself.equals("")) {
				noAssignMyself = "false";
			}
			BPMUtil.setContextData("noAssignMyself", noAssignMyself,
					pageContext);

			String viewYn = request.getParameter("readYn");
			if (viewYn == null)
				viewYn = "1";

			/**
			 *  사용자로 수신처 지정건은 조회시 바로 접수완료 처리.
			 */
			String docMngFlag = request.getParameter("docMngFlag");
			if (docMngFlag == null)
				docMngFlag = "1";

			BPMUtil.setContextData("receiveStatus", receiveStatus,
					pageContext);
			BPMUtil.setContextData("isRcvUser", isRcvUser, pageContext);
			BPMUtil.setContextData("docMngFlag", docMngFlag,
					pageContext);

			apprLogger.info("[time check] 14 : "
					+ (System.currentTimeMillis() - totalTime));

			if (isRcvUser != null && isRcvUser.equals("1")
					&& receiveStatus.equals("1")) {
				String receiverType = request
						.getParameter("receiverType");
				String userId = request.getParameter("userId");
				String userName = request.getParameter("userName");
				String deptId = request.getParameter("deptId");
				String deptName = request.getParameter("deptName");

				String receiverId = "";
				String receiverName = "";
				if (receiverType.equals("1")) { //개인
					receiverId = userId;
					receiverName = userName;
				} else {
					receiverId = deptId;
					receiverName = deptName;
				}
				if (confirmId == null)
					confirmId = "-1";
				docFlowDF
						.insertReceiverConfirm(
								new Integer(
										BPMConstants.DF_RCVLIST_STATUS_RCVDONE_ACCEPTDONE),
								new Long(receiverListId), new Integer(
										receiverType), receiverId,
								receiverName, new Long(confirmId), "",
								loggedUserName, "index.jsp");
			}

			apprLogger.info("[time check] 15 : "
					+ (System.currentTimeMillis() - totalTime));

			if (GWUtil.isNotEmpty(wihVO.getRegisterId())) {
				registerDAO = docFlowDF.getRegister(new Long(wihVO
						.getRegisterId()));
				apprLogger.info("[time check] 15-1 : "
						+ (System.currentTimeMillis() - totalTime));
			} else {
				registerDAO = docFlowDF
						.getRegisterByInstanceId(new Long(wihVO
								.getInstanceId()));
				apprLogger.info("[time check] 15-2 : "
						+ (System.currentTimeMillis() - totalTime));
			}

			BPMUtil.setContextData("DocFlowRegisterDAO", registerDAO,
					pageContext);

			if (registerDAO.size() > 0) {
				wihVO.setRegisterId(registerDAO.getRegisterId()
						.toString());
				if (isSihangOrOutDoc) { // 바로 시행문 작성인 경우
					currentReceiveInfoDAO = docFlowDF
							.getRecInfo(registerDAO.getRegisterId());
					BPMUtil.setContextData("currentReceiveInfoDAO",
							currentReceiveInfoDAO, pageContext);
					apprLogger.info("[time check] 15-3 : "
							+ (System.currentTimeMillis() - totalTime));
				} else { // 기안문 + 안추가
					apprLogger.info("========기안문 + 안추가==========");
					apprLogger.info(">>>wihVO.getRegisterId() : "
							+ wihVO.getRegisterId() + ", docIdx : "
							+ docIdx);
					isAddSihangDoc = true;
					docIdx = wihVO.getDocIdx();
					if (!GWUtil.isNotEmpty(docIdx)) {
						docIdx = "0";
						wihVO.setDocIdx("0");
					}
					currentReceiveInfoDAO = docFlowDF
							.getRecInfoByRegisterId(
									new Long(wihVO.getRegisterId()),
									new Long(docIdx));
					apprLogger.info("[time check] 15-4 : "
							+ (System.currentTimeMillis() - totalTime));
					try {
						if (currentReceiveInfoDAO.getInOutGubun() != null
								&& currentReceiveInfoDAO
										.getInOutGubun().intValue() == 1) {
							isOutDoc = true;
						}
					} catch (Exception e) {
					}
					try {
						sealType = currentReceiveInfoDAO.getSealType()
								.intValue();
						attachedDocList = docVH.getFormInstance()
								.getAttachDocList();
						if (attachedDocList != null) {
							attachedDoc = attachedDocList.get(Integer
									.parseInt(docIdx));
						}
						BPMUtil.setContextData("freeContents",
								attachedDoc.getFreeContentsURL(),
								pageContext);
						BPMUtil.setContextData("attachFile",
								attachedDoc.getAttachedFile(),
								pageContext);
					} catch (Exception e) {
					}
				}
				apprLogger.info("[time check] 15-5 : "
						+ (System.currentTimeMillis() - totalTime));
				if (currentReceiveInfoDAO != null
						&& currentReceiveInfoDAO.size() > 0) {
					//String confirmId = request.getParameter("confirmId");
					if (currentReceiverConfirmDAO == null) {
						if (GWUtil.isNotEmpty(confirmId)
								&& !confirmId.equalsIgnoreCase("NAN")) {
							currentReceiverConfirmDAO = docFlowDF
									.getReceverConfirmByConfirmId(new Long(
											confirmId));
							apprLogger
									.info("[time check] 15-5-1 : "
											+ (System
													.currentTimeMillis() - totalTime));
						} else {
							currentReceiverConfirmDAO = docFlowDF
									.getReceiverConfirm(
											currentReceiveInfoDAO
													.getReceiveInfoId(),
											loggedUserDeptId);
							apprLogger
									.info("[time check] 15-5-2 : "
											+ (System
													.currentTimeMillis() - totalTime));
						}
					}
				}
				BPMUtil.setContextData("currentReceiveInfoDAO",
						currentReceiveInfoDAO, pageContext);
				BPMUtil.setContextData("currentReceiverConfirmDAO",
						currentReceiverConfirmDAO, pageContext);
				BPMUtil.setContextData("docIdx", docIdx, pageContext);
				BPMUtil.setContextData("sealType", sealType + "",
						pageContext);
				request.setAttribute("attachedDoc", attachedDoc);
				apprLogger.info("[time check] 15-6 : "
						+ (System.currentTimeMillis() - totalTime));
			}
		}
%>
<%
	// 타이틀바, 버튼바 표시여부 설정
		String showTitleBar = request.getParameter("showTitleBar");
		if (showTitleBar == null)
			showTitleBar = "";
		String showBtnBar = request.getParameter("showBtnBar");
		if (showBtnBar == null)
			showBtnBar = "";

		if (!(showTitleBar.equals("N")))
			showTitleBar = "Y";
		if (!(showBtnBar.equals("N")))
			showBtnBar = "Y";

		//	// 2009.10.19 오너쉽이 없는 건이면 버튼을 보여주지 않는다.
		//	if (!ownerShipCheck){
		//	 showBtnBar = "N";
		//	}
%>
<%
	apprLogger.info("[time check] 16 : "
				+ (System.currentTimeMillis() - totalTime));
		List btnList = null;
		if (showBtnBar.equals("Y")) {
			hanwha.commons.filter.BufferedResponseWrapper bufferedResponse = new hanwha.commons.filter.BufferedResponseWrapper(
					response);

			request.setAttribute("ownerShipCheck", new Boolean(
					ownerShipCheck)); // 2009.11.10

			RequestDispatcher headerIncRD = request
					.getRequestDispatcher("index_header.jsp");
			out.flush();
			headerIncRD.include(request, bufferedResponse);
			if (bufferedResponse.getData() != null
					&& bufferedResponse.getData().get("btnList") != null) {
				btnList = (List) bufferedResponse.getData().get(
						"btnList");
			}
			out.flush();
			headerHTML = BPMUtil.trimLineFeed(bufferedResponse
					.toString());
		} else {
			headerHTML = "";
		}
		apprLogger.info("[time check] 17 : "
				+ (System.currentTimeMillis() - totalTime));
		String multiFormInstanceId = "";
		display = isDisplay("view_history", btnList);
		if (display && docVH.getMultiFormInstanceDAO() != null
				&& docVH.getMultiFormInstanceDAO().size() > 1) {
			docVH.getMultiFormInstanceDAO().beforeFirst();
			docVH.getMultiFormInstanceDAO().next();
			multiFormInstanceId = docVH.getMultiFormInstanceDAO()
					.getMultiFormInstanceId().toString();
			wihVO.setMultiFormInstanceId(docVH
					.getMultiFormInstanceDAO().getMultiFormInstanceId()
					.longValue());
		}
		apprLogger.info("[time check] 17-1 : "
				+ (System.currentTimeMillis() - totalTime));

		apprLogger.info("IncludeJSP : " + docVH.getIncludeJSP());

		//////////////////////////////////////
		// 헤더
		//////////////////////////////////////
		String headerIncludeFile = "index_header_"
				+ docVH.getFormType() + "_"
				+ docVH.getInputModeString() + ".jsp";
		apprLogger.info(headerIncludeFile);
		apprLogger.info("[time check] 18 : "
				+ (System.currentTimeMillis() - totalTime));
		String indexHeaderString = "";
		if (docVH.getInputConstantsMode() == InputConstants.PRINT
				&& !isPrintInDraft) {
			apprLogger.info("==================this=============");
			hanwha.bpm.form.mime.business.FormRequestWrapper printForFRW = new hanwha.bpm.form.mime.business.FormRequestWrapper(
					request);
			printForFRW.setFormInstance(docVH.getFormInstance());
			hanwha.commons.filter.BufferedResponseWrapper bufferedResponse = new hanwha.commons.filter.BufferedResponseWrapper(
					response);
			RequestDispatcher headerIncRD = request
					.getRequestDispatcher(headerIncludeFile);
			out.flush();
			headerIncRD.include(printForFRW, bufferedResponse);
			if (bufferedResponse.getException() != null)
				throw bufferedResponse.getException();
			out.flush();
			indexHeaderString = bufferedResponse.toString();
		} else {
			hanwha.commons.filter.BufferedResponseWrapper bufferedResponse = new hanwha.commons.filter.BufferedResponseWrapper(
					response);
			RequestDispatcher headerIncRD = request
					.getRequestDispatcher(headerIncludeFile);
			apprLogger.info("[time check] 18-001 : "
					+ (System.currentTimeMillis() - totalTime));
			out.flush();
			headerIncRD.include(request, bufferedResponse);
			apprLogger.info("[time check] 18-002 : "
					+ (System.currentTimeMillis() - totalTime));
			if (bufferedResponse.getException() != null)
				throw bufferedResponse.getException();
			apprLogger.info("[time check] 18-003 : "
					+ (System.currentTimeMillis() - totalTime));
			out.flush();
			apprLogger.info("[time check] 18-004 : "
					+ (System.currentTimeMillis() - totalTime));
			indexHeaderString = BPMUtil.trimLineFeed(bufferedResponse
					.toString());
			apprLogger.info("[time check] 18-end : "
					+ (System.currentTimeMillis() - totalTime));
		}
		apprLogger.info("[time check] 18-1 : "
				+ (System.currentTimeMillis() - totalTime));

		//////////////////////////////////////
		// 본문
		//////////////////////////////////////
		BPMUtil.setContextData("USE_FREE_CONTENTS", "true", pageContext);
		String bodyString = "";
		hanwha.commons.filter.BufferedResponseWrapper bodyBufferedResponse = new hanwha.commons.filter.BufferedResponseWrapper(
				response);
		if (!docVH.getFormType().equals("approval11")) {
			if (docVH.getInputConstantsMode() == InputConstants.PRINT
					&& !isPrintInDraft) {
				hanwha.bpm.form.mime.business.FormRequestWrapper printForFRW = new hanwha.bpm.form.mime.business.FormRequestWrapper(
						request);
				printForFRW.setFormInstance(docVH.getFormInstance());
				RequestDispatcher bodyIncRD = request
						.getRequestDispatcher(docVH.getIncludeJSP());
				out.flush();
				bodyIncRD.include(printForFRW, bodyBufferedResponse);
				out.flush();
			} else {
				RequestDispatcher bodyIncRD = request
						.getRequestDispatcher(docVH.getIncludeJSP());
				out.flush();
				bodyIncRD.include(request, bodyBufferedResponse);
				out.flush();
			}
			boolean trimLineFeed = false;
			if (instance == null) {
				trimLineFeed = (menuItemId != null && menuItemId
						.equalsIgnoreCase("item_app"));
			} else {
				trimLineFeed = !instance.isSubProcess();
			}
			if (wihVO.getDefinitionId() != null
					&& wihVO.getDefinitionId().equals("489")) {
				trimLineFeed = false;
			}
			//bodyString = BPMUtil.trimLineFeed( bodyBufferedResponse.toString(), trimLineFeed );
			bodyString = BPMUtil.trimLineFeed(bodyBufferedResponse
					.toString());
			apprLogger.info("[time check] 19-1 : "
					+ (System.currentTimeMillis() - totalTime));
		}

		Object fcObj = BPMUtil.getContextData("_FREE_CONTENTS",
				pageContext);
		Object fcsObj = BPMUtil.getContextData("_FREE_CONTENTS_SRC",
				pageContext);
		Map fcMap = null;
		if (fcObj != null) {
			fcMap = (Map) fcObj;
		}
		Map fcsMap = null;
		if (fcsObj != null) {
			fcsMap = (Map) fcsObj;
		}
		//boolean mdInit = ( wihVO.getDocumentMode().equals(MODE_WRITE) || wihVO.getDocumentMode().equals(MODE_REWRITE) || wihVO.getDocumentMode().equals(MODE_REWRITE_PREVIEW) || isMod );
		boolean mdInit = (wihVO.getDocumentMode().equals(MODE_WRITE)
				|| wihVO.getDocumentMode().equals(MODE_REWRITE) || isMod);
%>
<html>
<head>
<title>::: 대한생명 Enterprise Portal 시스템 :::</title>
<link rel="stylesheet" href="<%=ALTERNATIVE_COMMON_CSS%>"
	type="text/css">
<link rel="stylesheet" href="<%=ALTERNATIVE_DEFAULT_CSS%>"
	type="text/css">
<script language="javascript" src="<%=ALTERNATIVE_JS%>/popup.js"></script>
<script language="javascript"
	src='<%=GlobalContext.WEB_CONTEXT_ROOT%>/admin/lib/jquery/jquery-1.4.4.min.js'></script>
<script language="javascript"
	src="<%=ALTERNATIVE_JS%>/calendar/common.js"></script>
<script language="javascript"
	src="<%=ALTERNATIVE_JS%>/calendar/formUtil.js"></script>
<script language="javascript" src="<%=ALTERNATIVE_JS%>/receiver_edit.js"></script>
<script language="javascript" src="<%=ALTERNATIVE_JS%>/formuser_edit.js"></script>
<script language="javascript" src="<%=ALTERNATIVE_JS%>/bbs.js"></script>
<script language="javascript" src="<%=ALTERNATIVE_JS%>/mainmenu.js"></script>
<script language="javascript" src="<%=ALTERNATIVE_JS%>/dynamic_table.js"></script>
<script language="vbscript" src="<%=ALTERNATIVE_JS%>/grid_common.vbs"></script>
<script language="javascript"
	src="<%=ALTERNATIVE_JS%>/editableselect.js"></script>
<script language="javascript" src="<%=ALTERNATIVE_JS%>/org_edit.js"></script>
<script language="javascript" src="<%=ALTERNATIVE_JS%>/params.js"></script>
<script language="javascript" src="<%=ALTERNATIVE_JS%>/attach2.js"></script>
<script language="javascript" src="<%=ALTERNATIVE_JS%>/approval_util.js"></script>
<script language="javascript" src="<%=JS%>/approval_mdoc.js"></script>
<script language="javascript"
	src="<%=JS%>/approval.js.jsp?isMod=<%=isMod%>"></script>
<script type="text/javascript" src="<%=JS%>/objectInsert.js"></script>
<%
	if (showBtnBar.equals("Y")) { //페이지 오른쪽 하단에 이전, 다음 페이지 넘어가기 표시
%>
<script type="text/javascript" src="<%=ALTERNATIVE_JS%>/pageMove.js"></script>
<%
	}
%>
<%
	if (docVH.getInputConstantsMode() != InputConstants.PRINT) {
%>
<script language="javascript"
	src="<%=JS%>/approval_flow.js.jsp?da=<%=wihVO.isDraftActivity()%>&hi=<%=hasInstance%>&isPrint=<%=isPrint%>&dm=<%=wihVO.getDocumentMode()%>"></script>
<%--	 System.out.println("000000000000000000"	);
if (hasInstance) {
String mainDocTitle = instance.getInstanceDAO().getString("typealias");
if ( "기안".equals(mainDocTitle) ||  "지급".equals(mainDocTitle) || "품의".equals(mainDocTitle) ) {
//System.out.println("mainDocTitle : " + mainDocTitle	);
%><script language="javascript" src="<%=JS%>/approval2_flow.js.jsp?da=<%=wihVO.isDraftActivity()%>&hi=<%=hasInstance%>&isPrint=<%=isPrint%>&dm=<%=wihVO.getDocumentMode()%>"></script>
<%	 } else  {
//System.out.println("mainDocTitle : " + mainDocTitle	);
%><script language="javascript" src="<%=JS%>/approval_flow.js.jsp?da=<%=wihVO.isDraftActivity()%>&hi=<%=hasInstance%>&isPrint=<%=isPrint%>&dm=<%=wihVO.getDocumentMode()%>"></script>
<%	 }
} else {
if (reqMap.getInt("ROWNUM",0) == 2 || reqMap.getInt("ROWNUM",0) == 3 ){
//System.out.println("ROWNUM :" + reqMap.getInt("ROWNUM",0)	);
%><script language="javascript" src="<%=JS%>/approval2_flow.js.jsp?da=<%=wihVO.isDraftActivity()%>&hi=<%=hasInstance%>&isPrint=<%=isPrint%>&dm=<%=wihVO.getDocumentMode()%>"></script>
<%	 } else  {
//System.out.println("ROWNUM:" + reqMap.getInt("ROWNUM",0)	);
%><script language="javascript" src="<%=JS%>/approval_flow.js.jsp?da=<%=wihVO.isDraftActivity()%>&hi=<%=hasInstance%>&isPrint=<%=isPrint%>&dm=<%=wihVO.getDocumentMode()%>"></script>
<%	 }
}
--%>
<%
	}
%>
<%
	// InnoAP 스크립트 로딩 여부에 따라 스크립트 Import
		if (GWUtil.isExplicitTrue((String) BPMUtil.getContextData(
				"USE_INNOAP", pageContext))
				&& docVH.getMode().equals("write")) {
%>
<script language="javascript" src="/hwbpm/lib/js/InnoAP/InnoAP.js"></script>
<script language="javascript" src="/hwbpm/lib/js/InnoAP/InnoAttach.js"></script>
<%
	}
%>
<style type="text/css">
<!--
<%if (docVH.getInputModeString().equals("view")) {%> .alt {
	border-collapse: collapse;
	border: none;
	mso-border-alt: solid #95B4CF .5pt;
}

.alh {
	border: solid #95B4CF .5pt;
}

.alb {
	border-bottom: solid #95B4CF .5pt;
	border-right: solid #95B4CF .5pt;
	mso-border-top-alt: solid #95B4CF .5pt;
	mso-border-left-alt: solid #95B4CF .5pt
}

.td_color {
	border: solid #95B4CF .5pt;
	border-right: solid #95B4CF .5pt;
	font-size: 12px;
	font-weight: bold;
	color: #333333;
	background-color: #ffffff;
	text-align: center
}

.td_left {
	border: solid #95B4CF .5pt;
	border-right: solid #95B4CF .5pt;
	font-size: 12px;
	font-family: 굴림;
	color: #333333;
	line-heigh: 150%;
}

.td_center {
	border: solid #95B4CF .5pt;
	border-right: solid #95B4CF .5pt;
	font-size: 12px;
	font-family: 굴림;
	color: #333333;
	line-heigh: 150%;
	text-align: center
}

td {
	font-size: 12px;
	font-family: 굴림;
	color: #333333;
	line-heigh: 150%;
}

.hy_font10 {
	font-family: "HY견명조";
	font-size: 10pt;
	color: #000000
}

.hy_font13 {
	font-family: "HY신명조";
	font-size: 13pt;
	color: #000000
}

.hy_font18 {
	font-family: "HY견명조";
	font-size: 18pt;
	color: #000000
}

<%} else {%> .td_color { font-size:12px;
	font-weight: bold;
	color: #333333;
	background-color: #ffffff;
	text-align: center
}

td {
	font-size: 12px;
	font-family: 굴림;
	color: #333333;
	line-heigh: 150%;
}

.hy_font10 {
	font-family: "HY견명조";
	font-size: 10pt;
	color: #000000
}

.hy_font13 {
	font-family: "HY신명조";
	font-size: 13pt;
	color: #000000
}

.hy_font18 {
	font-family: "HY견명조";
	font-size: 18pt;
	color: #000000
}
<%}%>
-->
</style>
<%
	if (filtering != null && filtering.equals("payInfo")) {
%>
<script language="javascript">
function payInfoPrint() {
printDocument();
}
</script>
<%
	}
%>
<%
	if (isDone) {
%>
<script>
<%if (filtering.equals("receivedDoc")
							|| filtering.equals("receivedDocForPortlet")
							|| filtering.equals("assignedDoc")) {
						apprLogger.info("[time check] 17-1-1 : "
								+ (System.currentTimeMillis() - totalTime)
								+ ", " + currentReceiverConfirmDAO.size());
						String _schId = "";
						if (currentReceiverConfirmDAO != null
								&& currentReceiverConfirmDAO.size() > 0) {
							if (currentReceiverConfirmDAO.getRsrSchId() != null) {
								_schId = currentReceiverConfirmDAO
										.getRsrSchId().toString();
							}
						}
						apprLogger.info("[time check] 17-2 : "
								+ (System.currentTimeMillis() - totalTime));%>
function viewScheduleEx(scheduleId) {
var _schId = "<%=_schId%>";
if ( _schId != '' ) scheduleId = _schId;
    var urlStr = "/portal/GWController?beanid=hanwha.gw.schedule.business.ScheduleFacade&codeTp=1&scheduleId="+scheduleId+"&command=getInfoSchedule&nextpage=/groupware/modules/schedule/scheduleEditEx.jsp&updateMode=12";
    var title        = "<%=java.net.URLEncoder.encode("일정조회화면", "UTF-8")%>";
    var param = null;
    var rtnVal = _getOpenModalPopup('/portal',title, urlStr, param, 650, 585);
    <%-- 닫기버튼 클릭 --%>
    if((rtnVal == null) || (rtnVal == undefined)) {
        return;
    } 
    <%-- alert("mode:"+rtnVal[0]+" / newScheduleId:"+rtnVal[1]); --%>
}
<%}%>


<%-- 원기안문 조회 --%>
<%display = isDisplay("df_originalDocInfo", btnList);%>
<c:if test="<%=display%>">
function showOriginalDocInfo() {
var title = "원기안문";
var url = "<%=CONTEXT%>/wih/approval/index.jsp?documentMode=readonly&filtering=showOriginalDocInfo&instanceId=<%=wihVO.getInstanceId()%>&showTitleBar=N";
url += "&title="+title;
    var StrOption ;
    StrOption  = "dialogWidth:800px;dialogHeight:600px;";
    StrOption += "center:on;scroll:auto;status:off;resizable:off";
    var data = showModalDialog (url , null, StrOption);
}
</c:if>


<%-- 일괄발송 --%>
<%display = isDisplay("df_sendAll", btnList);%>
<c:if test="<%=display%>">
function sendAll() {
document.attachedDocForm.actionType.value = "all";
sendAttachedDocument();
}


<%-- 일괄발송 (대외 문서가 포함되어 있을 경우  --%>
function sendAllAfterConfirm(inCnt, outCnt) {
if ( parseInt(inCnt) == 0 ) {
alert("문서내에 대내문서가 포함되어 있지 않습니다.\r\n\r\n대외문서는 일괄발송을 하실 수 없습니다.\r\n\r\n[대내문서 : "+inCnt+"건 , 대외문서 : "+outCnt+"]");
return;
}
var reportInfo = "문서내에 대외 문서가 "+outCnt+"건 포함되어 있습니다.\r\n\r\n포함된 문서 중 대외 문서는 일괄발송에 포함되지 않습니다. 그래도 발송하시겠습니까?\r\n\r\n[대내문서 : "+inCnt+"건 , 대외문서 : "+outCnt+"]";
if(confirm(reportInfo)){
sendAll();
}
}
</c:if>


function sendAttachedDocument() {
document.attachedDocForm.registerId.value = document.documentBodyForm.registerId.value;
document.attachedDocForm.docIdx.value = document.documentBodyForm.docIdx.value;
document.attachedDocForm.action = "./send_attached_doc_submit.jsp";
document.attachedDocForm.submit();
}

<%-- 접수확인 --%>
<%display = isDisplay("df_confirmReceipt", btnList);%>
<c:if test="<%=display%>">
function confirmReceipt() {
if(confirm('접수 하시겠습니까?') == false ) return;
document.attachedDocForm.actionType.value = "confirmReceipt";
sendAttachedDocument();
}
</c:if>

<%-- 업무담당자 지정 --%>
<%display = isDisplay("df_assignMember", btnList);%>
<c:if test="<%=display%>">
function assignMember(){
var argsArray = new Array(4);
argsArray[0] = window;
var title	     = "업무담당자선택 팝업";
var url = "<%=CONTEXT%>/worklist/common/selectMemberPopup.jsp?selectPaneJSP=selectPane.jsp&receiveInfoId=<%=request.getParameter("receiveInfoId")%>&receiverListId=<%=receiverListId%>&receiverType=<%=request.getParameter("receiverType")%>&confirmId=<%=confirmId%>";
url += "&title="+title;
    var StrOption ;
    StrOption  = "dialogWidth:700px;dialogHeight:600px;";
    StrOption += "center:on;scroll:auto;status:off;resizable:off";
    var data = showModalDialog (url , argsArray, StrOption);
if( data != null && data == 'OK') {
alert('담당자를 지정 하였습니다!')
gotoList();
}
}
</c:if>


<%-- 본인의 보관문서지정 --%>
<%display = isDisplay("assignMyself", btnList);%>
<c:if test="<%=display%>">
function assignMyself() {
if (confirm("보관문서로 지정하시겠습니까?")) {
var beforeAction = document.listForm.action;
var beforeTarget = document.listForm.target;
document.listForm.action = "<%=CONTEXT%>/worklist/docflow/add_doc_keep_myself_submit.jsp?selectPaneJSP=selectPane.jsp&receiveInfoId=<%=request.getParameter("receiveInfoId")%>&receiverListId=<%=receiverListId%>&receiverType=<%=request.getParameter("receiverType")%>&confirmId=<%=confirmId%>";
document.listForm.target = "hiddenFrame";
document.listForm.submit();
document.listForm.action = beforeAction;
document.listForm.target = beforeTarget;
}
}
</c:if>


<%-- 본인의 보관문서지정해제 --%>
<%display = isDisplay("noAssignMyself", btnList);%>
<c:if test="<%=display%>">
function noAssignMyself() {
if (confirm("보관문서를 해제하시겠습니까?")) {
var beforeAction = document.listForm.action;
var beforeTarget = document.listForm.target;
document.listForm.action = "<%=CONTEXT%>/worklist/docflow/remove_doc_keep_myself_submit.jsp?selectPaneJSP=selectPane.jsp&receiveInfoId=<%=request.getParameter("receiveInfoId")%>&receiverListId=<%=receiverListId%>&receiverType=<%=request.getParameter("receiverType")%>&confirmId=<%=confirmId%>";
document.listForm.target = "hiddenFrame";
document.listForm.submit();
document.listForm.action = beforeAction;
document.listForm.target = beforeTarget;
}
}
</c:if>


<%-- 감사참조시행문 지정 2009.10.24 --%>
<%display = isDisplay("assignRefApprDoc", btnList);%>
<c:if test="<%=display%>">
function assignRefApprDoc() {
var title = "감사참조시행문 지정";
var argsArray = new Array(1);
argsArray[0] = window;
var url = "<%=CONTEXT%>/modules/refappr/refApprDoc_frame.jsp?instanceId=<%=wihVO.getInstanceId()%>";
    var strOption ;
    strOption  = "dialogWidth:760px;dialogHeight:270px;";
    strOption += "center:on;scroll:auto;status:off;resizable:off";
    var rtnVal = showModalDialog(url , argsArray, strOption);
if( rtnVal == "OK" ) {
//alert("지정 하였습니다");
gotoList();
}
}
</c:if>



<%-- 본인의 업무담당자지정 
<% display = isDisplay("assignMyself", btnList); %>
<c:if test="<%=display%>">
function assignMyself() {
if (confirm("본인을 담당자로 지정하시겠습니까?")) {
var beforeAction = document.listForm.action;
var beforeTarget = document.listForm.target;
document.listForm.action = "<%=CONTEXT%>/worklist/docflow/add_doc_acl_myself_submit.jsp?selectPaneJSP=selectPane.jsp&receiveInfoId=<%=request.getParameter("receiveInfoId")%>&receiverListId=<%=receiverListId%>&receiverType=<%=request.getParameter("receiverType")%>&confirmId=<%=confirmId%>";
document.listForm.target = "hiddenFrame";
document.listForm.submit();
document.listForm.action = beforeAction;
document.listForm.target = beforeTarget;
}
}
</c:if>
--%>


<%-- 내부결재 --%>
<%try {%>
<%display = (isDisplay("writeApprForSihang", btnList) || isDisplay(
								"df_rsrDraft", btnList));%>
<c:if test="<%=display%>">
function writeApprForSihang(apprType) {
<%-- 1. 본문에 함께 작성    2. 첨부로 등록 --%>
var url = "appr_confirm.jsp?apprType="+apprType;
var strOption  = "dialogWidth:300px;dialogHeight:240px;";
strOption += "center:on;scroll:off;status:off;resizable:off";

var result = showModalDialog (url , window, strOption);

if( result == null || result[1] == 'N' ){
return;
}

var mode;
if ( result[0] == 'Y' ) {
mode = "withBody";
} else {
mode = "attach";
}
<%if (isSihangOrOutDoc && docVH.getFormInstance() != null) {
							// callback
							docVH.getMultiFormInstanceDAO().beforeFirst();
							docVH.getMultiFormInstanceDAO().next();
							apprLogger.info("docVH.getFormInstance() : "
									+ docVH.getFormInstance());
							apprLogger
									.info("docVH.getFormInstance().getMultiFormInstanceDAO() : "
											+ docVH.getFormInstance()
													.getMultiFormInstanceDAO());
							//apprLogger.info("docVH.getFormInstance().getMultiFormInstanceDAO().getLocation() : " + docVH.getFormInstance().getMultiFormInstanceDAO().get("Location"));
							String xmlLoc = docVH.getFormInstance()
									.getMultiFormInstanceDAO().getLocation();%>
document.xmlComposerForm.action = "make_attached_xml.jsp"
document.xmlComposerForm.loc.value = "<%=xmlLoc%>";
document.xmlComposerForm.mode.value = mode;
document.xmlComposerForm.registerId.value = document.documentBodyForm.registerId.value;
document.xmlComposerForm.docIdx.value = document.documentBodyForm.docIdx.value;
document.xmlComposerForm.authorTel.value = "<%=authorTel%>";
document.xmlComposerForm.authorDeptId.value = "<%=authorDeptId%>";
document.xmlComposerForm.authorDeptName.value = "<%=authorDeptName%>";
document.xmlComposerForm.submit();
<%} else {%>
writeApprForMDocSubmit(mode);
<%}%>
}

<%-- 내부결재(안추가) --%>
function writeApprForMDocSubmit(mode) {
var lpc = new LParamComposer();
lpc.addItem("mode", mode);

<%-- 접수 확인을 위해 추가된 파라메타 --%>
var receiverType = document.attachedDocForm.receiverType.value;

var receiverId = "";
var receiverName = "";
var confirmId = document.attachedDocForm.confirmId.value;
if ( receiverType == '1' ) { <%-- 개인 --%>
receiverId = document.attachedDocForm.userId.value;
receiverName = document.attachedDocForm.userName.value;
} else {
receiverId = document.attachedDocForm.deptId.value;
receiverName = document.attachedDocForm.deptName.value;
}
if ( document.attachedDocForm.confirmId.value == null || document.attachedDocForm.confirmId.value == '' ) {
confirmId = "-1";
}
lpc.addItem("registerId", document.documentBodyForm.registerId.value); 
lpc.addItem("docIdx", document.documentBodyForm.docIdx.value);
lpc.addItem("receiveInfoId", document.attachedDocForm.receiveInfoId.value);
lpc.addItem("receiverListId", document.attachedDocForm.receiverListId.value);
lpc.addItem("receiverType", receiverType);
lpc.addItem("receiverId", receiverId);
lpc.addItem("receiverName", receiverName);
lpc.addItem("confirmId", confirmId);
lpc.addItem("authorTel", "<%=authorTel%>");
lpc.addItem("authorDeptId", "<%=authorDeptId%>");
lpc.addItem("authorDeptName", "<%=authorDeptName%>");


var docNode = mDocRootNode.selectSingleNode("//doc[@mIdx='<%=wihVO.getDocIdx()%>']");
if ( lpc.getXML() != null ) docNode.appendChild(lpc.getXML());
writeApprForSihangSubmit(docNode.xml);
}
</c:if>


<%} catch (Exception te) {
						te.printStackTrace();
					}%>

function writeApprForSihangSubmit(xml) {
document.writeApprForSihangForm.bodyXML.value = xml;
document.writeApprForSihangForm.appLineXML.value = flowDom.xml;
document.writeApprForSihangForm.submit();
}


<%-- 시행결과보고 --%>
<%display = isDisplay("df_rsrDraft", btnList);%>
<c:if test="<%=display%>">
function rsrDraft() {
document.writeApprForSihangForm.filtering.value = "rsrDraft";
document.writeApprForSihangForm.defId.value = "2173"; <%-- TODO defCode로 변경해야 함 --%>
writeApprForSihang("rsrDraft");	<%-- 내부결재와 통합. --%>
}
</c:if>

<%-- 대외문서발신요청 --%>
<%display = isDisplay("df_requestOutSendDoc", btnList);%>
<c:if test="<%=display%>">
function requestOutSendDoc(p_receiveInfoId) {
var title = "대외발신문서 날인옵션설정";
var argsArray = new Array(1);
argsArray[0] = window;
var url = "<%=CONTEXT%>/worklist/docflow/out_send_option_frame.jsp?receiveInfoId="+p_receiveInfoId;
    var strOption ;
    strOption  = "dialogWidth:650px;dialogHeight:400px;";
    strOption += "center:on;scroll:auto;status:off;resizable:off";
    var rtnVal = showModalDialog(url , argsArray, strOption);
if( rtnVal == "OK" ) {
df_requestOutSendDocTD[0].style.display="none"
df_requestOutSendDocTD[1].style.display="none"
<%-- 반려의견조회 버튼 숨김 --%>
if(document.all.df_view_rejectTD) {
df_view_rejectTD[0].style.display="none"
df_view_rejectTD[1].style.display="none"
}
if(document.all.df_view_requestTD) {
df_view_requestTD[0].style.display="block"
df_view_requestTD[1].style.display="block"
}
alert("발송의뢰 하였습니다");
gotoList();
}
}
</c:if>

<%-- 기안문에서 발송처리 팝업화면 호출 --%>
<%display = (isDisplay("df_sendDoc", btnList) || isDisplay(
							"df_sendIndividual", btnList));%>
<c:if test="<%=display%>">
function sendGianDoc(p_registeredId,docIdx) {
var title = "발송처리";
var argsArray = new Array(1);
argsArray[0] = window;
var url = "<%=CONTEXT%>/worklist/docflow/send_doc_gian_frame.jsp?registerId="+p_registeredId+"&docIdx="+docIdx;
    var strOption ;
    strOption  = "dialogWidth:650px;dialogHeight:360px;";
    strOption += "center:on;scroll:auto;status:off;resizable:off";
    var rtnVal = showModalDialog(url , argsArray, strOption);
if( rtnVal != null && rtnVal == 'OK' ) {
gotoList();
}
}
</c:if>

<%-- 대외문서발신날인처리 --%>
<%display = isDisplay("df_signOutSendDoc", btnList);%>
<c:if test="<%=display%>">
function signOutSendDoc(p_receiveInfoId,registerId,docIdx,receiverListId) {
var title = "대외발신문서 날인처리";
var argsArray = new Array(1);
argsArray[0] = window;
var url = "<%=CONTEXT%>/worklist/docflow/out_send_sign_frame.jsp?receiveInfoId="+p_receiveInfoId+"&registerId="+registerId+"&docIdx="+docIdx+"&receiverListId="+receiverListId;
    var strOption ;
    strOption  = "dialogWidth:650px;dialogHeight:430px;";
    strOption += "center:on;scroll:auto;status:off;resizable:off";
    var rtnVal = showModalDialog(url , argsArray, strOption);
if( rtnVal == "N" || rtnVal == "Y" ) {
df_signOutSendDocTD[0].style.display="none"
df_signOutSendDocTD[1].style.display="none"
if( rtnVal == "Y" ) {
alert("반려 하였습니다");
gotoList();
}
else {
alert("날인 하였습니다");
gotoList();
//	 refreshDocument();
}
}
}
</c:if>


<%-- 반려내용조회 --%>
<%display = isDisplay("df_view_reject", btnList);%>
<c:if test="<%=display%>">
function viewOutRegReject(registerId,docIdx) {
var title = "반려내용조회";
var argsArray = new Array(1);
argsArray[0] = window;
var url = "<%=CONTEXT%>/worklist/docflow/view_refuse_out_send_frame.jsp?registerId="+registerId+"&docIdx="+docIdx;
    var strOption ;
    strOption  = "dialogWidth:650px;dialogHeight:360px;";
    strOption += "center:on;scroll:auto;status:off;resizable:off";
    showModalDialog(url , argsArray, strOption);
}
</c:if>


<%-- 발송요청정보조회 --%>
<%display = isDisplay("df_view_request", btnList);%>
<c:if test="<%=display%>">
function viewOutRequestInfo(registerId,docIdx) {
var title = "발송요청정보조회";
var argsArray = new Array(1);
argsArray[0] = window;
var url = "<%=CONTEXT%>/worklist/docflow/view_request_out_send_frame.jsp?registerId="+registerId+"&docIdx="+docIdx;
    var strOption ;
    strOption  = "dialogWidth:500px;dialogHeight:300px;";
    strOption += "center:on;scroll:auto;status:off;resizable:off";
    showModalDialog(url , argsArray, strOption);
}
</c:if>

<%if (docVH.getFormInstance() != null) {
						FormInstance fi = docVH.getFormInstance();
						String intacciflag = fi.getParameter("intacciflag");
						if (GWUtil.isNotEmpty(intacciflag)
								&& intacciflag.equals("TRUE")) {
							BPMUtil.setContextData("view_acci", "true",
									pageContext);%>
<%-- 인터넷사고접수 결과값 입력 --%>
function openAcci() {
var argsArray = new Array(2);
argsArray[0] = "<%=fi.getParameter("juminno")%>"; <%-- 주민번호 --%>
argsArray[1] = "<%=fi.getParameter("intseq")%>";    	 <%-- 인터넷접수일련번호 --%> 
var result;
var url = "<%=CONTEXT%>/modules/acci/acci_result_frame.jsp";
var StrOption ;
StrOption  = "dialogWidth:650px;dialogHeight:600px;";
StrOption += "center:on;scroll:auto;status:off;resizable:off";
showModalDialog (url , argsArray, StrOption);
}
<%}
					}%>



</script>
<%
	} // end of if ( isDone ) {
%>









<script language="javascript">
function initApproval() {
<%if (fcsMap != null) {
					for (Iterator iter = fcsMap.keySet().iterator(); iter
							.hasNext();) {
						String tdObjectId = (String) iter.next();
						String td_src = (String) fcsMap.get(tdObjectId);%>
document.all("<%=tdObjectId%>").innerHTML = '<%=td_src%>';
<%}
				}%>
<%if (fcMap != null) {
					for (Iterator iter = fcMap.keySet().iterator(); iter
							.hasNext();) {
						String objectId = (String) iter.next();
						String downStr = (String) fcMap.get(objectId);%>
document.Wec<%=objectId%>_obj.OpenFile("<%=downStr%>");
<%}%>
<%if (isGianDoc || isPayDoc) {%>
loadMDocXML(<%=mdInit%>);
<%}
				}%>
<%if (hasInstance && !isDone && !isMod) { // 현재 진행중인 결재이고 수정모드가 아닌
					if (instance.getInstanceDAO().getHasComment()
							.booleanValue()) { // 결재의견이 있으면 결재의견 자동 조회 스크립트 실행%>
autoViewComment();
<%}
				}%>
<%List dtInitList = (List) BPMUtil.getContextData("_DT_INIT_SRC",
						pageContext);
				if (dtInitList != null) {
					for (Iterator dtInitIter = dtInitList.iterator(); dtInitIter
							.hasNext();) {%>
<%=dtInitIter.next()%>
<%}
				}%>
try {
afterComplete();
} catch (e) {
//alert(e.description);
}
}


function pageRefresh(){
document.listForm.workItemHandler.value='<%=wihVO.getWorkItemHandler()%>';
document.listForm.tracingTag.value='<%=wihVO.getTracingTag()%>';
document.listForm.action = "?";
document.listForm.submit();
}
</script>
<script>
<%-- 하위 결재자 의견 입력시 상위자 결재시 자동 조회 기능 (수정내용 by 남궁선) --%>
function autoViewComment() {
try {
if ((document.all.execute_doc!=null)&&(document.all.view_comment!=null)) { <%-- 결재, 결재의견 버튼 두가지가 모두 보이면 : 결재를 할 수 있고, 결재의견을 볼 수 있는 담당자이면 --%>
if (confirm("이 결재에 대하여 결재의견이 있습니다. 결재의견을 보시겠습니까?")) {
viewComments();
}
}
} catch(e) {}
}
</script>


</head>


<%
	String blockingEvent = "";
		if (docVH.getInputConstantsMode() != InputConstants.WRITE
				&& isDone
				&& (isOutDoc || docVH.getFormType()
						.equals("approval11"))) {
			blockingEvent = " onSelectStart = \"return false\" onDragStart = \"return false\" onContextMenu = \"return false\" ";
		}
		if (fcMap != null) {
%>
<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0"
	onload="initApproval();" <%=blockingEvent%>>
	<%
		} else {
	%>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0"
	onload="initApproval();" <%=blockingEvent%>>
	<%
		}
	%>
	<c:if test="<%=isPrint%>">


		<%--
<!-- MeadCo ScriptX Control -->
<object id="factory" style="display:none" viewastext
classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
codebase="/hwbpm/lib/scriptx/ScriptX.cab#Version=6,2,433,14">
</object>
--%>
		<!-- IEPageSetupX Control -->
		<OBJECT id="IEPageSetupX"
			classid="clsid:41C5BC45-1BE8-42C5-AD9F-495D6C8D7586"
			codebase="/hwbpm/lib/IEPageSetupX/IEPageSetupX.cab#version=1,0,20,4"
			width=0 height=0>
			<param name="copyright" value="http://isulnara.com">
		</OBJECT>
		<script defer>
function showPrintHelp2() {
var url = "<%=CONTEXT%>/help/printHelp2.jsp";
var StrOption ;
StrOption  = "dialogWidth:480px;dialogHeight:330px;";
StrOption += "center:on;scroll:auto;status:off;resizable:off";
showModalDialog (url , window, StrOption);
}

function Installed() {
if (typeof(document.all("IEPageSetupX"))!="undefined" && document.all("IEPageSetupX")!=null) 
return true;
else 
return false;
}

function window.onunload() {//컴포넌트를 설치했다면 창을 닫을때 설정값을 롤백시킴
if (Installed()) {
try {
//IEPageSetupX.RollBack();
} catch (exception) {
//Do Nothing
}
}
}

    function window.onload() {
if (Installed()) {
       try {
window.focus();	//포커스를 이 프레임으로
IEPageSetupX.Orientation = 1; // 가로 출력을 원하시면 0을 넣으면 됩니다. 세로출력은 1입니다.
IEPageSetupX.header = ""; // 헤더설정
IEPageSetupX.footer = ""; // 푸터설정
/*
IEPageSetupX.leftMargin = 5; // 왼쪽여백설정
IEPageSetupX.rightMargin = 5; // 오른쪽여백 설정
IEPageSetupX.topMargin = 3.0; // 윗쪽여백 설정
IEPageSetupX.bottomMargin = 2.0; // 아랫쪽 여백설정
*/
//IEPageSetupX.PrintBackground = true; // 배경색 및 이미지 인쇄
//IEPageSetupX.Print(true); // 인쇄대화상자 띄우기
       } catch (exception) {
           if(confirm("인쇄 프로그램이 오류가 발생하였습니다. \r\n 윈도우 기본인쇄로 출력하시겠습니까?")) {
               window.print();
               return;
           }
else {
return;
}
       }
}
else {
if(confirm("인쇄 프로그램이 설치 되지 않았습니다. \r\n 윈도우 기본인쇄로 출력하시겠습니까?")) {
           window.print();
           return;
       } else {
           return;
       }
}
    }
function window.onload_back() {
try {
if (!factory.object) {
return;
} else {
factory.printing.header = ""
factory.printing.footer = ""
factory.printing.portrait = true;	// false면 가로
<%if (completeEDMTransfer) {%>
factory.printing.leftMargin = 0.6;
<%} else {%>
factory.printing.leftMargin = 0.5;
<%}%>
factory.printing.topMargin = 0.0;
factory.printing.rightMargin = 0.0;
factory.printing.bottomMargin = 0.0;
//factory.printing.Print(true, _contents);

}
} catch (e) {}
}
<%if (isOutDoc) {%>
function displaySign(option) {
if ( option ) {
PO_SIGN_0.style.display = "block";
PO_SIGN_1.style.display = "block";
} else {
PO_SIGN_0.style.display = "none";
PO_SIGN_1.style.display = "none";
}
}


<%--
// 인식이 잘 안되는 경우가 있어서 페이지 하단으로 옮김.(2007.02.02)
// CI 변경으로 인하여 기존 이미지(44height)보다 높이가 커짐.(74height)-------
//var BASE_OUTDOC_HEIGHT = 690;
var BASE_OUTDOC_HEIGHT = 660;
var outDocLogoHeight = document.documentBodyForm.imgLogo.scrollHeight;
//alert("outDocLogoHeight:"+outDocLogoHeight);
if (outDocLogoHeight == 44){
BASE_OUTDOC_HEIGHT = 690;
}
//------------------------------------------------------------------
//var outDocHeight = outDocDiv.scrollHeight;
var outDocHeight = document.getElementById("outDocDiv").scrollHeight;
if ( outDocHeight < BASE_OUTDOC_HEIGHT ) document.getElementById("outDocSpace").height = BASE_OUTDOC_HEIGHT-outDocHeight;
--%>
<%}%>
<%if (docVH.getFormType().equals("approval11")) {%>
<%}%>
</script>
		<style media="print">
.noprint {
	display: none
}
</style>


		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			background="<%=IMG%>/back-printpop.gif" height="38">
			<tr>
				<td style="padding: 0 0 0 15"><img src="<%=IMG%>/i-pop.gif"
					border=0 align="abstop"> <a
					href="javascript:showPrintHelp2();">인쇄가 안될경우 여기를 클릭하세요.</a></td>
				<%
					if (isOutDoc) {
				%>
				<td align="right">프린트 옵션 : <%--
<input type="checkbox" value="" checked> 결재선&nbsp;&nbsp;
<input type="checkbox" value="" checked> 문서정보&nbsp;&nbsp;
<input type="checkbox" value="" checked> 수신처&nbsp;&nbsp;
--%> <input type="checkbox" name="signChk" checked
					onclick="if ( this.checked ) { displaySign(true); } else { displaySign(false); }" />
					인장포함
				</td>
				<%
					}
				%>
				<td align="right" style="padding: 4 12 0 0">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img
											src="<%=ALTERNATIVE_IMG%>/themes/common/images/btn_01.gif"></td>
										<%
											if (completeEDMTransfer) { // EDM으로 이관된 문서일 경우 IFRAME 내에 삽입된 문서내용만 인쇄
										%>
										<%--<td background="<%=ALTERNATIVE_IMG%>/themes/common/images/btn_02.gif" nowrap><a href="#" onclick="factory.printing.Print(true, edmViewer)">바로인쇄</a></td>--%>
										<td
											background="<%=ALTERNATIVE_IMG%>/themes/common/images/btn_02.gif"
											nowrap><a href="#" onclick="IEPageSetupX.print(true)">바로인쇄</a></td>
										<%
											} else {
										%>
										<td
											background="<%=ALTERNATIVE_IMG%>/themes/common/images/btn_02.gif"
											nowrap><a href="#" onclick="IEPageSetupX.print(true)">바로인쇄</a></td>
										<%
											}
										%>
										<td><img
											src="<%=ALTERNATIVE_IMG%>/themes/common/images/btn_03.gif"></td>
									</tr>
								</table>
							</td>
							<td width="5"></td>
							<td>
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img
											src="<%=ALTERNATIVE_IMG%>/themes/common/images/btn_01.gif"></td>
										<td
											background="<%=ALTERNATIVE_IMG%>/themes/common/images/btn_02.gif"
											nowrap><a href="#" onclick="IEPageSetupX.preview()">미리보기</a></td>
										<td><img
											src="<%=ALTERNATIVE_IMG%>/themes/common/images/btn_03.gif"></td>
									</tr>
								</table>
							</td>
							<td width="5"></td>
							<td>
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img
											src="<%=ALTERNATIVE_IMG%>/themes/common/images/btn_01.gif"></td>
										<td
											background="<%=ALTERNATIVE_IMG%>/themes/common/images/btn_02.gif"
											nowrap><a href="#" onclick="IEPageSetupX.SetupPage()">페이지설정</a></td>
										<td><img
											src="<%=ALTERNATIVE_IMG%>/themes/common/images/btn_03.gif"></td>
									</tr>
								</table>
							</td>
							<td width="5"></td>
							<td>
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img
											src="<%=ALTERNATIVE_IMG%>/themes/common/images/btn_01.gif"></td>
										<td
											background="<%=ALTERNATIVE_IMG%>/themes/common/images/btn_02.gif"
											nowrap><a href="#" onclick="closePrintWindow()">닫기</a></td>
										<td><img
											src="<%=ALTERNATIVE_IMG%>/themes/common/images/btn_03.gif"></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</c:if>
	<%--  end of if ( isPrint ) --%>


	<%
		if (!isPrint) {
	%>
	<form name="actionForm" action="./index.jsp" target="_self" method=POST>
		<%
			boolean hasFlowXML = false;
					for (Enumeration paramEnum = request.getParameterNames(); paramEnum
							.hasMoreElements();) {
						String paramKey = (String) paramEnum.nextElement();
						String paramValue = request.getParameter(paramKey);
						if (paramValue == null)
							continue;
						//if ( paramKey.equalsIgnoreCase("flowXML") && ( wihVO.isDraftActivity() || isPrint ) ) continue;
						if (paramKey.equalsIgnoreCase("appLineXML")
								&& (wihVO.isDraftActivity() || isPrint))
							continue;
						if (paramKey.equalsIgnoreCase("bodyXML")
								&& (wihVO.isDraftActivity() || isPrint))
							continue;
						if (paramKey.equalsIgnoreCase("refererList")
								&& (wihVO.isDraftActivity() || isPrint))
							continue;
						if (paramKey.equalsIgnoreCase("reviewerList")
								&& (wihVO.isDraftActivity() || isPrint))
							continue;
						if (paramKey.equalsIgnoreCase("apprFreeContents")
								&& (wihVO.isDraftActivity() || isPrint))
							continue;
						if (paramKey.equalsIgnoreCase("flowXML")) {
							paramValue = "";
							hasFlowXML = true;
						}
						if (paramKey.equalsIgnoreCase("flowChanged"))
							continue;
		%><input type="hidden" name="<%=paramKey%>" value="<%=paramValue%>">
		<%
			}
					if (!hasFlowXML) {
		%>
		<input type="hidden" name="flowXML"> <input type="hidden"
			name="flowChanged">
		<%
			}
		%>


	</form>
	<%
		}
	%>


	<%
		if (isEventHandlerEnabled) {
	%>
	<form name="eventHandlingWIHInitiation" action="../index.jsp"
		target="_new" method=POST>
		<input type=hidden name="eventTrigger" value="true"> <input
			type=hidden name="mainInstanceId" value="<%=wihVO.getInstanceId()%>">
		<input type=hidden name="mainTracingTag"> <input type=hidden
			name="eventName"> <input type=hidden name="defId"> <input
			type=hidden name="filtering" value="NEW"> <input type=hidden
			name="initiate" value="true">
	</form>
	<%
		}
			if (filtering.equals("registerWaitDoc")) {
	%>
	<form name="rsrSchForm" action="rsr_submit.jsp" target="rsrFrame"
		method=POST>
		<input type="hidden" name="actionType"> <input type="hidden"
			name="rsrSchId"> <input type="hidden" name="receiveInfoId"
			value="<%=request.getParameter("receiveInfoId")%>">
	</form>
	<%
		}
			if (isGianDoc
					|| isPayDoc
					|| (isSihangOrOutDoc && docVH.getFormInstance() != null)) {
	%>
	<form name="attachedDocForm" method="post" target="submitFrame">
		<input type="hidden" name="actionType"> <input type="hidden"
			name="registerId"> <input type="hidden" name="receiveInfoId"
			value="<%=request.getParameter("receiveInfoId")%>"> <input
			type="hidden" name="receiverListId" value="<%=receiverListId%>">
		<input type="hidden" name="receiverType"
			value="<%=request.getParameter("receiverType")%>"> <input
			type="hidden" name="confirmId" value="<%=confirmId%>"> <input
			type="hidden" name="userId" value="<%=loggedUserLoginName%>">
		<input type="hidden" name="userName" value="<%=loggedUserName%>">
		<input type="hidden" name="deptId" value="<%=loggedUserDeptId%>">
		<input type="hidden" name="deptName" value="<%=loggedUserDeptName%>">
		<input type="hidden" name="docIdx">
	</form>
	<%
		}
			if (isSihangOrOutDoc && docVH.getFormInstance() != null) {
	%>
	<form name="xmlComposerForm" method="post" target="xmlComposerFrame">
		<input type="hidden" name="loc"> <input type="hidden"
			name="mode"> <input type="hidden" name="registerId">
		<input type="hidden" name="docIdx"> <input type="hidden"
			name="receiveInfoId"
			value="<%=request.getParameter("receiveInfoId")%>"> <input
			type="hidden" name="receiverListId" value="<%=receiverListId%>">
		<input type="hidden" name="receiverType"
			value="<%=request.getParameter("receiverType")%>"> <input
			type="hidden" name="confirmId" value="<%=confirmId%>"> <input
			type="hidden" name="userId" value="<%=loggedUserLoginName%>">
		<input type="hidden" name="userName" value="<%=loggedUserName%>">
		<input type="hidden" name="deptId" value="<%=loggedUserDeptId%>">
		<input type="hidden" name="deptName" value="<%=loggedUserDeptName%>">
		<input type="hidden" name="authorTel"> <input type="hidden"
			name="authorDeptId"> <input type="hidden"
			name="authorDeptName">
	</form>
	<%
		}
			if (isGianDoc || isPayDoc) {
	%>
	<form name="mDocForm" method="post" action="add_doc_view.jsp">
		<input type="hidden" name="forInOut"> <input type="hidden"
			name="autoSend"> <input type="hidden" name="orgView">
		<input type="hidden" name="receiver"> <input type="hidden"
			name="referencer"> <input type="hidden" name="outReceiver">
		<input type="hidden" name="outReceiverList"> <input
			type="hidden" name="subject"> <input type="hidden"
			name="attachFile"> <input type="hidden" name="freeContents">
		<input type="hidden" name="sealType" value="<%=sealType%>"> <input
			type="hidden" name="mIdx"> <input type="hidden" name="tel">
		<input type="hidden" name="fax"> <input type="hidden"
			name="worker"> <input type="hidden" name="sendDate">
		<input type="hidden" name="rsrYN"> <input type="hidden"
			name="rsrEndDate"> <input type="hidden" name="rsrId">
		<input type="hidden" name="rsrSchId"> <input type="hidden"
			name="docSecret"> <input type="hidden" name="secretReader">
	</form>
	<%
		}
			if (isDisplay("writeApprForSihang", btnList)
					|| isDisplay("df_rsrDraft", btnList)) {
	%>
	<form name="writeApprForSihangForm" method="post"
		action="<%=CONTEXT%>/wih/index.jsp">
		<input type="hidden" name="documentMode"
			value="<%=BPMConstants.MODE_WRITE%>"> <input type="hidden"
			name="filtering" value="WAFS"> <input type="hidden"
			name="defId" value="531">
		<%-- TODO defCode로 변경해야 함 --%>
		<input type="hidden" name="parentInstanceId"
			value="<%=wihVO.getInstanceId()%>"> <input type="hidden"
			name="workItemHandler" value="approval"> <input type="hidden"
			name="bodyXML"> <input type="hidden" name="parameters">
		<input type="hidden" name="pmType"> <input type="hidden"
			name="appLineXML"> <input type="hidden" name="currentPage"
			value="<%=reqMap.getInt("CURRENTPAGE", 1)%>"> <input
			type="hidden" name="sort_column"
			value="<%=reqMap.getString("SORT_COLUMN", "")%>"> <input
			type="hidden" name="sort_cond"
			value="<%=reqMap.getString("SORT_COND", "")%>"> <input
			type="hidden" name="targetCond"
			value="<%=reqMap.getString("TARGETCOND", "")%>"> <input
			type="hidden" name="keywordCond"
			value="<%=reqMap.getString("KEYWORDCOND", "")%>"> <input
			type="hidden" name="SEARCH_CONDITION"
			value="<%=reqMap.getString("SEARCH_CONDITION", "")%>"> <input
			type="hidden" name="menuId"
			value="<%=reqMap.getString("menuId", "")%>"> <input
			type="hidden" name="listURL"
			value="<%=reqMap.getString("LISTURL", "?")%>"> <input
			type="hidden" name="rownum" value="<%=reqMap.getInt("ROWNUM", 0)%>">

		<input type="hidden" name="menu_itemid" value="item_app">
	</form>
	<%
		}
			if (isDisplay("df_rsrDraft", btnList)) {
	%>
	<form name="rsrDraftForm" method="post"
		action="<%=CONTEXT%>/wih/index.jsp">
		<input type="hidden" name="documentMode"
			value="<%=BPMConstants.MODE_WRITE%>"> <input type="hidden"
			name="filtering" value="rsrDraft"> <input type="hidden"
			name="defId" value="2173">
		<%-- TODO defCode로 변경해야 함 --%>
		<input type="hidden" name="parentInstanceId"
			value="<%=wihVO.getInstanceId()%>"> <input type="hidden"
			name="workItemHandler" value="approval"> <input type="hidden"
			name="parameters"> <input type="hidden" name="appLineXML">

		<input type="hidden" name="currentPage"
			value="<%=reqMap.getInt("CURRENTPAGE", 1)%>"> <input
			type="hidden" name="sort_column"
			value="<%=reqMap.getString("SORT_COLUMN", "")%>"> <input
			type="hidden" name="sort_cond"
			value="<%=reqMap.getString("SORT_COND", "")%>"> <input
			type="hidden" name="targetCond"
			value="<%=reqMap.getString("TARGETCOND", "")%>"> <input
			type="hidden" name="keywordCond"
			value="<%=reqMap.getString("KEYWORDCOND", "")%>"> <input
			type="hidden" name="SEARCH_CONDITION"
			value="<%=reqMap.getString("SEARCH_CONDITION", "")%>"> <input
			type="hidden" name="menuId"
			value="<%=reqMap.getString("menuId", "")%>"> <input
			type="hidden" name="listURL"
			value="<%=reqMap.getString("LISTURL", "?")%>"> <input
			type="hidden" name="rownum" value="<%=reqMap.getInt("ROWNUM", 0)%>">

		<input type="hidden" name="menu_itemid" value="item_app">
	</form>
	<%
		}
	%>
	<form name="changeCurrentUserForm" method="post"
		action="../common/changeCurrentUser.jsp">
		<input type="hidden" name="userId"> <input type="hidden"
			name="userName"> <input type="hidden" name="deptId">
		<input type="hidden" name="deptName"> <input type="hidden"
			name="processInstance"
			value="<%=(hasInstance) ? instance.getInstanceId() : ""%>"> <input
			type="hidden" name="definitionVersionId"
			value="<%=wihVO.getDefinitionVersionId()%>"> <input
			type="hidden" name="taskId"
			value="<%=reqMap.getString("TASKID", "")%>"> <input
			type="hidden" name="listURL"
			value="<%=reqMap.getString("LISTURL", "")%>"> <input
			type="hidden" name="MENU_ITEMID" value="<%=menuItemId%>">
		<%-- 메뉴 아이템 아이디 --%>
		<input type="hidden" name="gwtroublegubun"
			value="<%=reqMap.getString("GWTROUBLEGUBUN", "")%>">
		<%-- 장애 처리, 신청 처리 구분  --%>
		<input type="hidden" name="assignType">
		<%-- 할당형태  --%>
	</form>
	<form name="listForm" method="post"
		action="<%=reqMap.getString("LISTURL", "?")%>"
		onactivate="setListForm(document.listForm);">
		<input type="hidden" name="currentPage"
			value="<%=reqMap.getInt("CURRENTPAGE", 1)%>">
		<%-- Paging --%>
		<input type="hidden" name="sort_column"
			value="<%=reqMap.getString("SORT_COLUMN", "")%>">
		<%-- Sort --%>
		<input type="hidden" name="sort_cond"
			value="<%=reqMap.getString("SORT_COND", "")%>"> <input
			type="hidden" name="targetCond"
			value="<%=reqMap.getString("TARGETCOND", "")%>">
		<%-- Search	--%>
		<input type="hidden" name="keywordCond"
			value="<%=reqMap.getString("KEYWORDCOND", "")%>"> <input
			type="hidden" name="FILTERMETHOD"
			value="<%=reqMap.getString("FILTERMETHOD", "")%>"> <input
			type="hidden" name="SEARCH_CONDITION"
			value="<%=reqMap.getString("SEARCH_CONDITION", "")%>"> <input
			type="hidden" name="docFlowViewActionMode"
			value="<%=reqMap.getString("docFlowViewActionMode", "")%>"> <input
			type="hidden" name="assignMyself"
			value="<%=reqMap.getString("assignMyself", "")%>"> <input
			type="hidden" name="assignRefApprDoc"
			value="<%=reqMap.getString("assignRefApprDoc", "")%>">
		<%--	<input type="hidden" name="noAssignMyself" value="<%=reqMap.getString("noAssignMyself","")%>">  --%>
		<input type="hidden" name="filtering"
			value="<%=reqMap.getString("FILTERING", "")%>"> <input
			type="hidden" name="menuId"
			value="<%=reqMap.getString("menuId", "")%>"> <input
			type="hidden" name="listURL"
			value="<%=reqMap.getString("LISTURL", "?")%>"> <input
			type="hidden" name="returnFiltering"
			value="<%=reqMap.getString("returnFiltering", "")%>">
		<%-- 돌아올 업무함 Filtering --%>
		<input type="hidden" name="returnURL"
			value="<%=reqMap.getString("returnURL", "")%>">
		<%-- 돌아올 업무함 URL --%>
		<input type="hidden" name="returnLabel"
			value="<%=reqMap.getString("returnLabel", "")%>">
		<%-- 돌아올 업무함 Label --%>
		<input type="hidden" name="rownum"
			value="<%=reqMap.getInt("ROWNUM", 0)%>">
		<%-- Detail --%>
		<input type="hidden" name="defId"
			value="<%=reqMap.getString("DEFID", "")%>"> <input
			type="hidden" name="taskId"
			value="<%=reqMap.getString("TASKID", "")%>"> <input
			type="hidden" name="documentMode"
			value="<%=reqMap.getString("DOCUMENTMODE", "")%>"> <input
			type="hidden" name="totalCount"
			value="<%=reqMap.getString("TOTALCOUNT", "")%>"> <input
			type="hidden" name="workItemHandler" value="">
		<%
			if (hasInstance) {
		%>
		<input type="hidden" name="instanceId"
			value="<%=reqMap.getString("INSTANCEID", "")%>">
		<%
			}
		%>
		<input type="hidden" name="tracingTag" value=""> <input
			type="hidden" name="defcategoryid"
			value="<%=reqMap.getString("DEFCATEGORYID", "")%>"> <input
			type="hidden" name="deftypeid"
			value="<%=reqMap.getString("DEFTYPEID", "")%>"> <input
			type="hidden" name="MENU_ITEMID" value="<%=menuItemId%>">
		<%-- 메뉴 아이템 아이디 --%>
		<input type="hidden" name="gwtroublegubun"
			value="<%=reqMap.getString("GWTROUBLEGUBUN", "")%>">
		<%-- 장애 처리, 신청 처리 구분  --%>
		<%-- for 부서업무접수 --%>
		<input type="hidden" name="userId"> <input type="hidden"
			name="userName"> <input type="hidden" name="deptId">
		<input type="hidden" name="deptName"> <input type="hidden"
			name="processInstance"
			value="<%=(hasInstance) ? instance.getInstanceId() : ""%>"> <input
			type="hidden" name="definitionVersionId"
			value="<%=wihVO.getDefinitionVersionId()%>"> <input
			type="hidden" name="assignType">
		<%-- 할당형태 --%>

		<input type="hidden" name="ONEDAYCAL"
			value="<%=reqMap.getString("ONEDAYCAL", "")%>"> <input
			type="hidden" name="searchYear"
			value="<%=reqMap.getString("SEARCHYEAR", "")%>"> <input
			type="hidden" name="searchQuarter"
			value="<%=reqMap.getString("SEARCHQUARTER", "")%>"> <input
			type="hidden" name="itoAuth"
			value="<%=reqMap.getString("itoAuth", "0")%>"> <input
			type="hidden" name="STARTCAL"
			value="<%=reqMap.getString("STARTCAL", "")%>"> <input
			type="hidden" name="ENDCAL"
			value="<%=reqMap.getString("ENDCAL", "")%>">

	</form>
	<form name="downloadFileForm" method="post">
		<input type="hidden" name="module"> <input type="hidden"
			name="path"> <input type="hidden" name="name"> <input
			type="hidden" name="type"> <input type="hidden"
			name="isSecurity"> <input type="hidden" name="isExternal">
		<input type="hidden" name="docClass"> <input type="hidden"
			name="docType"> <input type="hidden" name="userCategory1">
		<input type="hidden" name="userCategory2"> <input
			type="hidden" name="isView">
	</form>
	<form name="documentBodyForm" method="post" target="_self">
		<%
			apprLogger.info("INDEX.JSP menuId ::" + menuId);
		%>
		<%-- HTML 내용 시작 --%>
		<table border="0" cellspacing="0" cellpadding="10" width="700">
			<tr>
				<td>
					<%
						/** 타이틀 */
							if (showTitleBar.equals("Y")) {
								boolean skipTitle = (!hasInstance
										&& wihVO.getDefinitionId() != null && wihVO
										.getDefinitionId().equals("486"));
								if (!skipTitle) {
									RequestDispatcher titleRD = request
											.getRequestDispatcher("index_title.jsp");
									out.flush();
									titleRD.include(request, response);
									out.flush();
								}
							}
					%>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<%-- 타이틀과 본문시작사이 여백 --%>
						<tr>
							<td></td>
						</tr>
					</table> <%=headerHTML%> <%-- 리스트 위 버튼 영역--%> <c:if
						test="<%=beforeEDMTransfer%>">
						<%-- EDM 이관 전 --%>
						<%
							if (showBtnBar.equals("Y")) {
						%>
						<table width="700" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td></td>
							</tr>
						</table>
						<%
							}
						%>
						<%
							boolean skipTitle = false;
									if (hasInstance
											&& instance.getInstanceDAO().getDefinitionId()
													.toString().equals("829")) {
										skipTitle = true;
									}
									if ("829".equals(wihVO.getDefinitionId())) {
										skipTitle = true;
									}
									//법인카드일경우에만  skipTitle이 true
									String docName = docVH.getHumanActivity()
											.getProcessDefinition().getName();

									if (!skipTitle
											&& (hasInstance || wihVO.getDocumentMode().equals(
													BPMConstants.MODE_REWRITE_PREVIEW))
											&& docVH.getInputConstantsMode() != InputConstants.PRINT) {
										if (!isTransSihangDoc) {
											String displayDocTitle = null;
											if (hasInstance) {
												String mainDocTitle = instance.getInstanceDAO()
														.getString("typealias");
												if ("기안".equals(mainDocTitle)) {
													displayDocTitle = "기 안";
												} else if ("시행".equals(mainDocTitle)) {
													displayDocTitle = "시 행 문";
												} else if ("지급".equals(mainDocTitle)
														|| "품의".equals(mainDocTitle)) {
													displayDocTitle = "기 안";
												} else if ("회람".equals(mainDocTitle)) {
													displayDocTitle = "회 람";
												} else if ("법인카드".equals(mainDocTitle)) {
													displayDocTitle = "법인카드신청";
												}
											} else {
												displayDocTitle = docVH.getDocTitle();
											}
											if (displayDocTitle != null) {
						%>
						<table width="700" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align='center'
									style="padding-top: 15px; padding-bottom: 0px">
									<h2 style='FONT-FAMILY: 굴림'><%=displayDocTitle%></h2>
								</td>
							</tr>
						</table>
						<%
							}
										}
									}
						%>
						<%
							apprLogger.info("filtering : " + filtering
											+ ", docTypeId : " + docTypeId);

									if (!isTransSihangDoc) { // 시행문 변환 아닌 경우 시작
						%>
						<%
							if (!skipTitle) {
						%>
						<table width="700" border="0" cellspacing="0" cellpadding="0">
							<%-- 결재선 --%>
							<tr>
								<td style="padding-top: 5px; padding-bottom: 21px">
									<div id="approvalLine"></div>
								</td>
							</tr>
						</table>
						<%
							} else {
						%>
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							id="header_space" style="display: block;">
							<tr>
								<td height=10></td>
							</tr>
						</table>
						<%
							}
						%>
						<%-- 본문헤더 시작 --%>
						<%%>
						<%=indexHeaderString%>
						<%-- 본문헤더 끝 --%>
						<%-- 본문내용 시작 --%>
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							style="padding-top: 2px">
							<tr>
								<td>
									<%-- 순수 양식 시작 --%> <%=bodyString%> <%-- 순수 양식 끝 --%>
								</td>
							</tr>
						</table>
						<%-- 본문내용 끝 --%>
						<%
							String attachMsg = (docVH.getInputConstantsMode() == InputConstants.WRITE)
												? "파일첨부"
												: "첨부파일";
						%>
						<%
							if (docVH.getInputModeString().equals("view")
												|| docVH.getInputModeString().equals("print")) {
						%>
						<table id="fileAndReceiver" width="700" border=1 cellspacing=0
							cellpadding=0 style='border-collapse: collapse; border: none'
							style="display:none">
							<%
								} else {
							%>
							<table id="fileAndReceiver" width="700" cellpadding=2
								cellspacing=0 border=1 bordercolor=#95B4CF bordercolordark=white
								style="display: none">
								<%
									}
								%>
								<%-- 파일첨부 영역 시작 --%>
								<%
									boolean hasAttachFile = (docVH.getInputConstantsMode() == InputConstants.WRITE || BPMUtil
														.hasAttachFile("approvalAttachFile",
																pageContext));
								%>
								<c:if test="<%=hasAttachFile %>">
									<tr id="apprFileContentsArea" bgcolor="#FFFFFF"
										style="display: none">
										<td height="25" witdh="110" nowrap><%=attachMsg%></td>
										<td style="padding-left: 10px" width="590" id="fileTD"><bpm:fileUpload
												name="approvalAttachFile"
												viewMode="<%=docVH.getInputConstantsMode()%>" width="550px"
												height="105px" edm="true" version="1" /></td>
									</tr>
								</c:if>
								<%-- 파일첨부 영역 끝 --%>
								<%-- 수신처 영역 시작 --%>
								<tr id="viewReceiverListPane" style="display: none">
									<td height="25" width="110">수 신 처</td>
									<td style="padding-left: 10px" width="590">
										<%
											if (docVH.getInputConstantsMode() == InputConstants.WRITE) {
										%> <INPUT id="viewReceiverList" NAME="viewReceiverList" VALUE=""
										style="border: solid 1px #9a9a9a; background-color: #ffffff; width: 520px; font-size: 12px; color: #333333; height: 19px; font-family: "
										굴림"; readOnly> <%
 	} else {
 %>
										<div id="viewReceiverList">&nbsp;</div> <%
 	}
 %>
									</td>
								</tr>
								<%-- 수신처 영역 끝 --%>
							</table>

							<%
								if (docVH.getInputConstantsMode() == InputConstants.WRITE) {
												hasAttachFile = true;
											} else {
												hasAttachFile = BPMUtil.hasAttachFile(
														"approvalAttachFile", pageContext);
											}
							%>
							<c:if test="<%=hasAttachFile%>">
								<script>
try {
if ( showApprFileContentsArea ) {
apprFileContentsArea.style.display = "block";
}
} catch(e) {}
</script>
							</c:if>
							<%
								// 추가된 안이 있을 경우 해당 안들을 보여줌.
											apprLogger.info("[time check] 20 : "
													+ (System.currentTimeMillis() - totalTime));
											if ((hasInstance
													|| wihVO.getDocumentMode().equals(
															MODE_REWRITE_PREVIEW) || wihVO
													.getDocumentMode().equals(
															BPMConstants.MODE_PRINT))
													&& !isMod
													&& !wihVO.getDocumentMode().equals(
															BPMConstants.MODE_REWRITE_FROM_INS)
													&& !wihVO.getDocumentMode().equals(
															BPMConstants.MODE_REWRITE)) {
												AttachedDocumentList attDocList = null;
												if (docVH.getFormInstance() != null
														&& docVH.getFormInstance()
																.getAttachDocList() != null) {
													attDocList = docVH.getFormInstance()
															.getAttachDocList();
												} else if (request.getParameter("multiDocument") != null) {
													String multiDocumentXML = request
															.getParameter("multiDocument");
													attDocList = new AttachedDocumentList(
															multiDocumentXML);
												}
												if (attDocList != null) {
													for (int di = 0; di < attDocList.size(); di++) {
														AttachedDocument attDoc = attDocList
																.get(di);
														if (GWUtil
																.isNotEmpty(wihVO.getRegisterId())) {
															try {
																ReceiveInfoDAO attdocReceiveInfoDAO = docFlowDF
																		.getRecInfoByRegisterId(
																				new Long(
																						wihVO.getRegisterId()),
																				new Long(di));
																BPMUtil.setContextData(
																		"attdocReceiveInfoDAO",
																		attdocReceiveInfoDAO,
																		pageContext);
																// 날인일자 세팅
																if (attdocReceiveInfoDAO
																		.getChangeDate() != null)
																	attDoc.setSealDate(hanwha.commons.util.DateUtil.getFormattedDate(
																			attdocReceiveInfoDAO
																					.getChangeDate(),
																			"yyyy.MM.dd"));
																// 결재완료일 세팅
																attDoc.setEndDate(hanwha.commons.util.DateUtil
																		.getFormattedDate(instance
																				.getInstanceDAO()
																				.getFinishedDate(),
																				"yyyy.MM.dd"));
															} catch (Exception e) {
																apprLogger
																		.error("안추가 문서에서 안추가된 문서를 조회하던 중 에러발생",
																				e);
															}
														}
														hanwha.bpm.approval.AttachedDocRequestWrapper adRW = new hanwha.bpm.approval.AttachedDocRequestWrapper(
																request, attDoc);
														adRW.setFormInstance(docVH
																.getFormInstance());
														BPMUtil.setContextData("freeContents",
																attDocList.get(di)
																		.getFreeContentsURL(),
																pageContext);
														String attachedDocJSP = "add_doc_view.jsp";
														RequestDispatcher attachedDocRD = request
																.getRequestDispatcher(attachedDocJSP);
														out.flush();
														attachedDocRD.include(adRW, response);
														out.flush();
													}
												}
											}
											apprLogger.info("[time check] 20-1 : "
													+ (System.currentTimeMillis() - totalTime));
							%>
							<%
								} else { // 시행문 변환 -> if ( !isTransSihangDoc ) 조건 끝
													// 시행문 바로 보여줌
											apprLogger.info("[time check] 21 : "
													+ (System.currentTimeMillis() - totalTime));
											// 시행문인 경우 시행문 보여주기 시작
											if (GWUtil.isNotEmpty(wihVO.getRegisterId())) {
												try {
													ReceiveInfoDAO attdocReceiveInfoDAO = docFlowDF
															.getRecInfoByRegisterId(
																	new Long(wihVO.getRegisterId()),
																	new Long(docIdx));
													BPMUtil.setContextData("attdocReceiveInfoDAO",
															attdocReceiveInfoDAO, pageContext);
													// 날인일자 세팅
													if (attdocReceiveInfoDAO.getChangeDate() != null)
														attachedDoc
																.setSealDate(hanwha.commons.util.DateUtil.getFormattedDate(
																		attdocReceiveInfoDAO
																				.getChangeDate(),
																		"yyyy.MM.dd"));
													// 결재완료일 세팅
													attachedDoc
															.setEndDate(hanwha.commons.util.DateUtil
																	.getFormattedDate(instance
																			.getInstanceDAO()
																			.getFinishedDate(),
																			"yyyy.MM.dd"));
												} catch (Exception e) {
													apprLogger.error(
															"안추가 문서에서 안추가된 문서 한건을 조회하던 중 에러발생", e);
												}
											}
											hanwha.bpm.approval.AttachedDocRequestWrapper adRW = new hanwha.bpm.approval.AttachedDocRequestWrapper(
													request, attachedDoc);
											adRW.setFormInstance(docVH.getFormInstance());

											request.setAttribute("incAppLine", "true");

											String attachedDocJSP = null;
											if (attachedDoc.getForInOut().equals("in")) {
												attachedDocJSP = "add_doc_view_in.jsp";
											} else {
												attachedDocJSP = "add_doc_view_out.jsp";
											}
											RequestDispatcher attachedDocRD = request
													.getRequestDispatcher(attachedDocJSP);
											out.flush();
											attachedDocRD.include(adRW, response);
											out.flush();
											apprLogger.info("[time check] 22 : "
													+ (System.currentTimeMillis() - totalTime));
							%>
							<%
								} // 시행문 변환 아닌 경우 끝
							%>


							<%
								//if (showBtnBar.equals("Y")) {
							%>
							<table width="700" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td></td>
								</tr>
							</table>
							<%
								//}
							%>


							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td>
										<div id="<%=hanwha.bpm.BPMConstants.DOC_AREA%>"></div>
									</td>
								</tr>
							</table>

							</c:if>


							<%-- EDM 이관 후 --%>
							<c:if test="<%=completeEDMTransfer%>">
								<%
									if (!isPrint) {
								%>
문서함으로 이관된 문서입니다. [문서번호 : <%=transferDocNumber%>] <br>
								<%
									}
								%>
								<%
									transferDocNumber = URLEncoder.encode(transferDocNumber,
													"KSC5601");
											isPopup = isPrint ? "Y" : "N";
											if (wihVO.getFiltering().equalsIgnoreCase(
													"showOriginalDocInfo")) {
												isPopup = "Y";
											}
								%>
								<script>
function fitFrameContentSize(id) {
try {
var contents = document.all( id );
if(contents.contentWindow.document.readyState == 'interactive' ||
contents.contentWindow.document.readyState == 'complete') {
var contentsBody = contents.contentWindow.document.body;
if(contentsBody != null) {
contents.contentWindow.document.body.leftMargin = "0";
contents.contentWindow.document.body.leftmargin = "0";
contents.contentWindow.document.body.marginheight = "0";
contents.contentWindow.document.body.marginwidth = "0";
var frameHeight = contents.contentWindow.document.body.scrollHeight;
contents.style.height = frameHeight;

var frameWidth = contents.contentWindow.document.body.scrollWidth;
//alert("문서 크기 디버깅 중입니다 " + frameWidth);
if ( parseInt(frameWidth) > 700 ) {
//alert("문서크기가 기준 크기를 벗어나  체크중 입니다 : " + frameWidth + ", " + frameHeight);
contents.style.width = frameWidth;
} else {
contents.style.width = frameWidth+20;
}
}
}
}catch(e){
<%-- alert("error 1 : " + e.description); --%>
}
}
</script>
								<iframe id="edmViewer"
									src="<%=BPMUtil.getHostURL(request)%>/page/KLI_OneDocInfo.jsp?txtUsrID=<%=loggedUserEmployeeNo%>&work=oneclick&docNumber=<%=transferDocNumber%>&popup=<%=isPopup%>"
									style="width: 100%; hegith: 100%" MARGINWIDTH="0"
									MARGINHEIGHT="0" frameBorder="0"
									style="marginLeft:0px;width:100%;height:100%;text-align:center;border:0px; solid"
									scrolling="no"
									onreadystatechange="fitFrameContentSize('edmViewer');"></iframe>
							</c:if>

							<%-- 리스트 아래 버튼 영역 시작 --%>
							<%=headerHTML%>
							<%-- 리스트 아래 버튼 영역 끝 --%>
							</td>
							</tr>
						</table>
						<%-- HTML 내용 끝 --%>
						<%
							if (!isPrint) {
						%>
						<%=wihVO.toHidden()%>
						<%
							}
						%>
						<%
							if (hasInstance) {
						%>
						<input type="hidden" name="instanceDocTitle"
							value="<%=escapeTitle(docVH.getDocTitle())%>">
						<%
							}
						%>
						<input type="hidden" name="hasInstance" value="<%=hasInstance%>">
						<input type="hidden" name="delegateType" value="<%=delegateType%>">
						<input type="hidden"
							name="<%=BPMConstants.PARAMETER_KEY_BODYDOCCOMMENTS%>">
						<input type="hidden" name="listURL"
							value="<%=reqMap.getString("LISTURL", "?")%>">
						<input type="hidden" name="returnFiltering"
							value="<%=reqMap.getString("returnFiltering", "")%>">
						<%-- 돌아올 업무함 Filtering --%>
						<input type="hidden" name="returnURL"
							value="<%=reqMap.getString("returnURL", "")%>">
						<%-- 돌아올 업무함 URL --%>
						<input type="hidden" name="returnLabel"
							value="<%=reqMap.getString("returnLabel", "")%>">
						<%-- 돌아올 업무함 Label --%>
						<input type="hidden" name="menuId"
							value="<%=reqMap.getString("menuId", "")%>">
						<%-- 메뉴 아이디 --%>
						<input type="hidden" name="MENU_ITEMID" value="<%=menuItemId%>">
						<%-- 메뉴 아이템 아이디 --%>
						<input type="hidden" name="gwtroublegubun"
							value="<%=reqMap.getString("GWTROUBLEGUBUN", "")%>">
						<%-- 장애 처리, 신청 처리 구분 --%>
						<input type="hidden" name="actionCode">
						<input type="hidden" name="userId">
						<input type="hidden" name="showTitleBar">
						<input type="hidden" name="showBtnBar">
						<c:if test="<%=isMod%>">
							<input type="hidden" name="_mod" value="true">
						</c:if>
						<input type="hidden" name="mode"
							value="<%=wihVO.getDocumentMode()%>">
						<input type="hidden" name="multiDocument">
						<input type="hidden" name="flowXML">
						<input type="hidden" name="comment">
						<c:if test="<%=isEnabledTransfer%>">
							<select style="display: none" name="receiverList" multiple></select>
							<%-- 피전달자 --%>
						</c:if>
						<input name="xmlLocation" type="hidden"
							value="<%=docVH.getLocation()%>">
						<script>
try {
if ( viewReceiverFlag == true ) {	<%-- 수신처 display 설정 --%>
viewReceiverListPane.style.display = "block";	<%-- 수신처 지정 결과 VIEW --%>
try {	<%-- 수신처 지정 버튼 처리--%>
var btnRObj = document.all("btnReceiver");	<%-- NOTE : 같은 아이디 값을 가진 수신처 지정 버튼이 위,아래 두개 존재하고 있어 배열로 넘어옴. --%>
btnRObj[0].style.display = "block";
btnRObj[1].style.display = "block";
} catch (e) {}
}
} catch(e) {}
<c:if test="<%=beforeEDMTransfer%>">
try {	<%-- 첨부파일 및 수신처를 1개의 테이블로 감싼 테이블의 표시 여부 --%>
if (viewReceiverFlag==true||showApprFileContentsArea==true) {
fileAndReceiver.style.display = "block";
}
} catch(e) {}
</c:if>
<%apprLogger.info("[time check] 23 : "
						+ (System.currentTimeMillis() - totalTime));
				RoleMapping referencerRole = null;
				RoleMapping reviewerRole = null;
				if (wihVO.getDocumentMode().equals(MODE_REWRITE)
						|| wihVO.getDocumentMode().equals(MODE_REWRITE_PREVIEW)) {
				} else {
					if (hasInstance) {
						referencerRole = instance.getRoleMapping("referer");
						reviewerRole = instance.getRoleMapping("reviewer");
					}
					apprLogger.info("referencerRole : " + referencerRole);
					apprLogger.info("reviewerRole : " + reviewerRole);
				}
				String refererXML = BPMUtil.createOrgUserXML(referencerRole);
				if (refererXML != null) {%>
refererEditor.addOrgFromString('<%=refererXML%>');
<%}
				String reviewerXML = BPMUtil.createOrgUserXML(reviewerRole);
				if (reviewerXML != null) {%>
reviewerEditor.addOrgFromString('<%=reviewerXML%>');
<%}
				String WASF = (String) BPMUtil.getContextData("WAFS",
						pageContext);
				apprLogger.info("............WASF : " + WASF);
				if (wihVO.getDocumentMode().equals(MODE_REWRITE)
						|| wihVO.getDocumentMode().equals(MODE_REWRITE_PREVIEW)
						|| (WASF != null && WASF.equals("true"))) {
					Object refererListObj = pageContext.getAttribute(
							"refererList", PageContext.REQUEST_SCOPE); // 열람자
					Object reviewerListObj = pageContext.getAttribute(
							"reviewerList", PageContext.REQUEST_SCOPE); // 수신처
					if (reviewerListObj == null) {
						reviewerListObj = BPMUtil.getContextData(
								"reviewerList", pageContext);
						apprLogger.info("............reviewerListObj : "
								+ reviewerListObj);
					}
					apprLogger.info("............reviewerListObj : "
							+ reviewerListObj);
					hanwha.bpm.form.attr.FormUserSerializer userSel = new hanwha.bpm.form.attr.FormUserSerializer();
					String refererListXML = BPMUtil.trimCDATA((String) userSel
							.serialize(refererListObj));
					String reviewerListXML = BPMUtil.trimCDATA((String) userSel
							.serialize(reviewerListObj));

					if (GWUtil.isNotEmpty(refererListXML)) {%>
refererChanged = true;
refererEditor.addOrgFromString('<%=refererListXML%>');
<%}

					if (GWUtil.isNotEmpty(reviewerListXML)) {%>
reviewerChanged = true;
reviewerEditor.addOrgFromString('<%=reviewerListXML%>');
<%}
				}%>
try {
renderReferer(refererEditor);
} catch (e) {}
try {
renderReviewer(reviewerEditor);
receiveListEditor.setXML(reviewerEditor.getXML());
} catch (e) {}
</script>
						</form>
						<%-- End of documentBodyForm --%>
						<%
							if (wihVO.getFiltering().equals("PROPOSAL")) { // 개인함-진행문서에서 결재선변경을 할 경우 사용
						%>
						<script>
var calInit = false;
function submitCALOK(changeAppLineFrame) {
if ( calInit ) {
var responseMsg = changeAppLineFrame.contentWindow.document.all("responseMsg");
if ( responseMsg == null) return;
var returnXml = responseMsg.xml;
var respMsgDoc = new ActiveXObject("Msxml2.DOMDocument");
try {
respMsgDoc.loadXML (returnXml);
} catch ( e ) {
alert ("결재선 변경 중 에러발생  : " + e.message);
}
var statusNode = respMsgDoc.selectSingleNode("//comm:response/comm:body/result");
var code = statusNode.getAttribute("code");
var rstMsg = statusNode.text;

<%-- 완료후 처리 --%>
if( code == "1" ) {
 //refreshDocument();
}
alert(rstMsg);
drawAppovalLine(flowDom);
}
calInit = true;
}
</script>
						<iframe name="changeAppLineFrame" onload="submitCALOK(this);"
							style="display: none"></iframe>
						<%
							}
						%>
						<%
							apprLogger.info("[time check] 24 : "
										+ (System.currentTimeMillis() - totalTime));
								if (isGianDoc
										|| isPayDoc
										|| (isSihangOrOutDoc && docVH.getFormInstance() != null)) {
						%>
						<script>
var sfInit = false;
function submitOK(submitFrame) {
if ( sfInit ) {
var responseMsg = submitFrame.contentWindow.document.all("responseMsg");
if ( responseMsg == null) return;
var returnXml = responseMsg.xml;
var respMsgDoc = new ActiveXObject("Msxml2.DOMDocument");
try {
respMsgDoc.loadXML (returnXml);
} catch ( e ) {
alert ("문서 발송 중 에러발생  : " + e.message);
}
var statusNode = respMsgDoc.selectSingleNode("//comm:response/comm:body/result");
var code = statusNode.getAttribute("code");
var rstMsg = statusNode.text;
statusNode = respMsgDoc.selectSingleNode("//comm:response/comm:body/actionType");
var actionType = statusNode.getAttribute("code");

<%-- 완료후 처리 --%>
if( code == "1" ) {
 gotoList();
}
alert(rstMsg);
}
sfInit = true;
}
</script>
						<iframe name="submitFrame" onload="submitOK(this);"
							style="display: none"></iframe>
						<%
							}
						%>
						<%
							if (filtering.equals("registerWaitDoc")) {
						%>
						<script>
var rsrInit = false;
function rsrSubmitOK(rsrFrame) {
if ( rsrInit ) {
var responseMsg = rsrFrame.contentWindow.document.all("responseMsg");
if ( responseMsg == null) return;
var returnXml = responseMsg.xml;
var respMsgDoc = new ActiveXObject("Msxml2.DOMDocument");
try {
respMsgDoc.loadXML (returnXml);
} catch ( e ) {
alert ("일정 등록시 에러발생  : " + e.message);
}
var statusNode = respMsgDoc.selectSingleNode("//comm:response/comm:body/result");
var code = statusNode.getAttribute("code");
var rstMsg = statusNode.text;

//처리 완료후 버튼 Display여부 설정
if( code == "1" ) {
 callbackScheduleEx();
}
alert(rstMsg);
}
rsrInit = true;
}
</script>
						<iframe name="rsrFrame" onload="rsrSubmitOK(this);"
							style="display: none"></iframe>
						<%
							}
								if (isSihangOrOutDoc && docVH.getFormInstance() != null) {
						%>
						<script>
var xcfInit = false;
function xcSubmitOK(xmlComposerFrame) {
if ( xcfInit ) {
var responseMsg = xmlComposerFrame.contentWindow.document.all("responseMsg");
if ( responseMsg == null) return;
var returnXml = responseMsg.xml;
var respMsgDoc = new ActiveXObject("Msxml2.DOMDocument");
try {
respMsgDoc.loadXML (returnXml);
//alert(respMsgDoc.documentElement.xml) 
writeApprForSihangSubmit(respMsgDoc.documentElement.xml);
} catch ( e ) {
alert ("xmlComposerFrame  : " + e.message);
}
<%--
//var statusNode = respMsgDoc.selectSingleNode("//comm:response/comm:body/result");
//var code = statusNode.getAttribute("code");
//var rstMsg = statusNode.text;
//alert(rstMsg);
--%>
}
xcfInit = true;
}
</script>
						<iframe name="xmlComposerFrame" onload="xcSubmitOK(this);"
							style="display: none"></iframe>
						<%
							}
						%>
						<iframe name="hiddenFrame" style="display: none"></iframe>
						<%
							if (wihVO.isDraftActivity() || isMod) {
						%>
						<iframe src="../../common/session_keeper.jsp"
							style="display: none; width: 0px; height: 0px;"></iframe>
						<%
							}
						%>
						<%
							//if ( !BPMConstants.PRODUCTION_MODE && hasInstance ) {
								if (false) {
									if (!(request.getParameter("showBtnDebug") != null && request
											.getParameter("showBtnDebug").equals("N"))) {
										String xmlURL = null;
										try {
											if (docVH.getFormInstance() != null
													&& docVH.getFormInstance()
															.getMultiFormInstanceDAO() != null
													&& docVH.getFormInstance()
															.getMultiFormInstanceDAO().size() > 0) {
												xmlURL = docVH.getFormInstance()
														.getMultiFormInstanceDAO()
														.getLocation();
											}
										} catch (Exception e) {
											apprLogger.error("디버깅을 위한 버튼 삽입 중 에러발생", e);
										}
										if (xmlURL != null) {
						%>
						<input style="display: none" type="button" name="DEBUG [XML]"
							value="DEBUG [XML]" title="DEBUG [XML]"
							onclick="javascript:viewSourceXML();" />
						<script>
function viewSourceXML() {
window.open("<%=CONTEXT%>/downloadSvc/file.xml?type=document&path=<%=xmlURL%>.xml&isView=false");
}
</script>
						<%
							}
									}
								}
								if (showBtnBar.equals("Y")) { //페이지 오른쪽 하단에 이전, 다음 페이지 넘어가기 표시
						%>
						<SPAN id=scrollpage style="position: absolute; height: 30;"><table
								border=0>
								<tr>
									<td><img
										src="<%=ALTERNATIVE_IMG%>/themes/common/images/up.gif"
										onclick="movePage(true); return false" style="cursor: hand"
										alt="PgUp"></td>
								</tr>
								<tr>
									<td><img
										src="<%=ALTERNATIVE_IMG%>/themes/common/images/down.gif"
										onclick="movePage(false); return false" style="cursor: hand"
										alt="PgDown"></td>
								</tr>
							</table></span>
						<%
							}
						%>
					
</body>
</html>
<div id="_FU_TEMP_DIV" style="display: none"></div>
<%
	//양식 오류 체크.
		Throwable cause = (Throwable) request
				.getAttribute("OBJ_SUBMIT_ERROR");
		if (cause != null) {
			throw new Exception("양식 처리시 오류 발생", cause);
		}
%>
<script type="text/javascript">
<%if (isOutDoc) {%>
// CI 변경으로 인하여 기존 이미지(44height)보다 높이가 커짐.(74height)-------
//var BASE_OUTDOC_HEIGHT = 690;
var BASE_OUTDOC_HEIGHT = 660;
if (document.getElementById("imgLogo")){
var outDocLogoHeight = document.documentBodyForm.imgLogo.scrollHeight;
} else {
var outDocLogoHeight = 44;
}
//alert("outDocLogoHeight:"+outDocLogoHeight);
if (outDocLogoHeight == 44){
BASE_OUTDOC_HEIGHT = 690;
}
//------------------------------------------------------------------
//var outDocHeight = outDocDiv.scrollHeight;
if (document.getElementById("outDocDiv")){
var outDocHeight = document.getElementById("outDocDiv").scrollHeight;
} else {
var outDocHeight = 0;
}
if ( outDocHeight < BASE_OUTDOC_HEIGHT ) {
if (document.getElementById("outDocSpace")){
document.getElementById("outDocSpace").height = BASE_OUTDOC_HEIGHT-outDocHeight;
}
}
<%}%>
</script>


<!--


//-->
</script>


<%-- 결재선 초기 정보를 자바스크립트 OBJECT 모델로 전달하는 부분 --%>
<XML id="flowXMLString"> <%=flowXML%> </XML>
<script>
try {
loadFlowXML(<%=(isMod && wihVO.isFlowChanged())%>);
} catch(e) {}
</script>
<%-- 다중기안 초기 정보를 자바스크립트 OBJECT 모델로 전달하는 부분 --%>
<%
	if (isGianDoc || isPayDoc) {
%>
<XML id="multiDocXMLString"> <%=BPMUtil.trimXML(multiDocXML)%> </XML>
<%
	if (fcMap == null) {
%>
<script>
loadMDocXML(<%=mdInit%>);
</script>
<%
	}
		}
%>
<%
	/** ##### 시행결과보고 알림창 시작 ##### */
		boolean rsrReturnYN = false;
		String strRsrEndDate = "";
		String strRsrEndTerm = "";
		int rsrEndTerm = 0;
		try {
			if (hasInstance
					&& (filtering.equals("receivedDoc")
							|| filtering
									.equals("receivedDocForPortlet")
							|| filtering.equals("registerWaitDoc") || filtering
								.equals("indexedReg"))
					&& currentReceiveInfoDAO != null
					&& currentReceiveInfoDAO.getRsrYN() != null
					&& currentReceiveInfoDAO.getRsrYN().intValue() == 1) {
				if (currentReceiverConfirmDAO != null
						&& currentReceiverConfirmDAO.size() > 0) {
					strRsrEndDate = hanwha.commons.util.DateUtil
							.changeToString(currentReceiveInfoDAO
									.getRsrEndDate());
					strRsrEndTerm = currentReceiveInfoDAO
							.getString("RsrEndTerm");
					if (strRsrEndTerm != null) {
						rsrEndTerm = (new java.math.BigDecimal(
								strRsrEndTerm)).intValue() + 1;
						strRsrEndTerm = rsrEndTerm + "";
					} else {
						strRsrEndTerm = "0";
					}
					if (currentReceiverConfirmDAO.getRsrReturnDate() == null) {
						rsrReturnYN = true;
					}
				}
			}
		} catch (Exception e) {
			apprLogger.error("시행결과 보고일 조회 중 에러발생", e);
		}

		if (rsrReturnYN) { // 시행결과보고가 필요하고 아직 결과보고를 하지않은 경우
%>
<script>
<%if (rsrEndTerm > 0) {%>
alert("본 시행문은 결과보고가 필요한 문서입니다. \r\n\r\n-결과보고 마감일 : <%=strRsrEndDate%> \r\n-남은기간 : <%=strRsrEndTerm%>일");
<%} else {%>
alert("결과보고 마감일이 지났습니다. \r\n\r\n-결과보고 마감일 : <%=strRsrEndDate%> \r\n-남은기간 : <%=strRsrEndTerm%>일");
<%}%>
</script>
<%
	} // 시행결과보고 알림 끝
%>
<%
	List fuInitList = (List) BPMUtil.getContextData("_FILE_UPLOAD",
				pageContext);
		if (fuInitList != null) {
%>
<script>
var onloadString = document.body.onload.toString();
function afterComplete() {
<%for (Iterator fuInitIter = fuInitList.iterator(); fuInitIter
							.hasNext();) {%>
<%=fuInitIter.next()%>
<%}%>
}
if ( onloadString.indexOf("initApproval") < 0 ) {
var execString = onloadString.substring(onloadString.indexOf('{')+1,onloadString.lastIndexOf('}'));
window.execScript(execString);
document.body.onload = initApproval;
}
</script>
<%
	}
%>
<%
	//tc.commit();
%>
<div style="display: none;">
	<param id="EAIRETCODE" type="STRING">
	EAIRETCODE : 0
	</param>
</div>
<%
	} catch (Exception e) {
		//System.out.println("에러 발생시 index.jsp를 타는지 테스트합니다");
		apprLogger.error("문서 조회 중 에러발생", e);
		e.printStackTrace();
		tc.rollback();
%>
<param id="EAIRETCODE" type="STRING">
EAIRETCODE : 1
</param>
<%
	StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);

		// 에러 출력
		out.print(sw);

		sw.close();
		pw.close();
	} finally {
		tc.rollback();
		apprLogger.info("Connection close : " + tc + ", hasDML : "
				+ tc.isHasDML());
		tc.close(); // Connection이 close되지 않는 문제점이 발견되어 강제 close()를 넣음.
		apprLogger.info("[time check] 25 : "
				+ (System.currentTimeMillis() - totalTime));
	}
	apprLogger.info("[time check] end [" + wihVO + "]"
			+ (System.currentTimeMillis() - totalTime));
	///////////////////////////
	// End Transaction
	///////////////////////////
%>