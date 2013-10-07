<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="org.uengine.util.dao.*"%>
<%@page import="org.uengine.persistence.dao.*"%>
<%@page import="org.uengine.persistence.processinstance.*"%>
<%@page import="org.uengine.ui.list.datamodel.DataMap"%>
<%@page import="org.uengine.ui.list.datamodel.DataList"%>
<%@page import="org.uengine.ui.list.datamodel.QueryCondition"%>
<%@page import="org.uengine.ui.list.util.HttpUtils"%>
<%@page import="org.uengine.ui.list.util.DAOListCursorUtil"%>
<%@page import="org.uengine.util.dao.DefaultConnectionFactory"%>

<%@include file="../common/header.jsp"%>
<%@include file="../common/getLoggedUser.jsp"%>
<%@ taglib uri="http://www.uengine.org/taglibs/bpm" prefix="bpm"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	/***********************************************************************/
	/*                            Define                                   */
	/***********************************************************************/
	QueryCondition condition = new QueryCondition();
	DataList dl = null;

	// Work List BF.
	//	WorkListBusinessFacade workListBF = null;

	int intPageCnt = 20;
	int nPagesetSize = 10;
	int currentPage = reqMap.getInt("CURRENTPAGE", 1);
	int totalCount = 0;
	int totalPageCount = 0;

	String strTarget = reqMap.getString("TARGETCOND", "procins.instancename");
	String strKeyword = reqMap.getString("KEYWORDCOND", "");
	String strDateKeyStart = reqMap.getString("INIT_START_DATE", "");
	String strDateKeyEnd = reqMap.getString("INIT_END_DATE", "");
	String strDefCategoryId = reqMap.getString("DEFCATEGORYID", "");
	String strDefTypeId = reqMap.getString("DEFTYPEID", "");

	String strSortColumn = reqMap.getString("SORT_COLUMN", "");
	String strSortCond = reqMap.getString("SORT_COND", "");
	String menuItemId = reqMap.getString("MENU_ITEMID", "item_bpm");
	//	String filtering = reqMap.getString("FILTERING","");
	//	RequestContext reqCtx = new RequestContext(request);
	//	User logdUser = reqCtx.getUser();
	//	loggedUserCompanyId=   logdUser.getCompanyId();

	/***********************************************************************/
	/*                            Check & Set Parameter                    */
	/***********************************************************************/
	condition.setMap(reqMap);
	condition.setOnePageCount(intPageCnt);
	condition.setPageNo(currentPage);

	HashMap colors = new HashMap(10);
	colors.put("Failed", "red");
	colors.put("Suspended", "yellow");
	colors.put("Skipped", "blue");
	colors.put("Ready", "green");
	colors.put("Running", "green");
	colors.put("Complete", "gray");
	colors.put("Stopped", "black");

	StringBuffer condStr = new StringBuffer();

	String _status = request.getParameter("status");
	String _Instance = request.getParameter("Instance");
	String complete_end_date = request.getParameter("complete_end_date");
	String simpleKeyWord = reqMap.getString("simpleKeyWord", "");
	String docTitle = request.getParameter("docTitle");
	String _Initiator = request.getParameter("Initiator");
	String _Initiator_display = request.getParameter("Initiator_display");
	String _Initiator__which_is_xml_value = request.getParameter("Initiator__which_is_xml_value");
	String _Nowperson = request.getParameter("Nowperson");
	String _Nowperson_display = request.getParameter("Nowperson_display");
	String _Nowperson__which_is_xml_value = request.getParameter("Nowperson__which_is_xml_value");
	String complete_start_date = request.getParameter("complete_start_date");
	String init_start_date = request.getParameter("init_start_date");
	String init_end_date = request.getParameter("init_end_date");
	
	
	if (UEngineUtil.isNotEmpty(_status) && !_status.equals("All")) {
		condStr.append("AND a.status = '" + _status + "' ");
	} else {
		_status = "";
	}
	
	String _defId = request.getParameter("defId");
	if (UEngineUtil.isNotEmpty(_defId)) {
		condStr.append(" AND a.defid = " + UEngineUtil.searchStringFilter(_defId) + " ");
	} else {
		_defId = "";
	}
	
	if (UEngineUtil.isNotEmpty(simpleKeyWord)) {

		String typeOfDBMS = DAOFactory.getInstance(null).getDBMSProductName().toUpperCase();
		String lowerCaseFunctionName = "LCASE";
		if ("ORACLE".equals(typeOfDBMS) || "POSTGRES".equals(typeOfDBMS)) {
		    lowerCaseFunctionName = "LOWER";
		}
		
		String simpleKeyWordLowerCase = UEngineUtil.searchStringFilter(simpleKeyWord).toLowerCase(); 
		
		condStr.append(" AND ( ");
		condStr.append("	").append(lowerCaseFunctionName).append("(a.DEFNAME) 		LIKE '%").append(simpleKeyWordLowerCase).append("%' ").append(" OR ");
		condStr.append("	").append(lowerCaseFunctionName).append("(a.INFO)		 	LIKE '%").append(simpleKeyWordLowerCase).append("%' ").append(" OR "); 
		condStr.append("	").append(lowerCaseFunctionName).append("(a.NAME)		 	LIKE '%").append(simpleKeyWordLowerCase).append("%' ").append(" OR "); 
		condStr.append("	").append(lowerCaseFunctionName).append("(a.INITEP)	 		LIKE '%").append(simpleKeyWordLowerCase).append("%' ").append(" OR ");
		condStr.append("	").append(lowerCaseFunctionName).append("(a.INITRSNM)	 	LIKE '%").append(simpleKeyWordLowerCase).append("%' ").append(" OR "); 
		condStr.append("	").append(lowerCaseFunctionName).append("(a.CURREP)		 	LIKE '%").append(simpleKeyWordLowerCase).append("%' ").append(" OR ");
		condStr.append("	").append(lowerCaseFunctionName).append("(a.CURRRSNM)		LIKE '%").append(simpleKeyWordLowerCase).append("%' ");
		try {
			Integer.parseInt(simpleKeyWordLowerCase);
			condStr.append(" OR ").append(" 	instid = ").append(simpleKeyWordLowerCase);
		} catch(Exception e) { }
		condStr.append(" ) ");
		
	} else {
		if (UEngineUtil.isNotEmpty(docTitle)) {
			//docTitle = decode(docTitle);
			condStr.append("AND a.name like '%" + UEngineUtil.searchStringFilter(docTitle) + "%' ");
		} else {
			docTitle = "";
		}
		
		//2009-08-04 InitiatorName start
		if (UEngineUtil.isNotEmpty(_Initiator)) {
			/*
			condStr.append(" AND a.initep in ( ");
			StringBuffer searchInitiatorString = new StringBuffer();
			for (String initiator : _Initiator.split(";")) {
				if (searchInitiatorString.length() > 0) searchInitiatorString.append(",");
				searchInitiatorString.append("'").append(initiator).append("'");
			}
			condStr.append(searchInitiatorString).append(" ) ");
			*/
			condStr.append(" AND a.initep LIKE '%").append(UEngineUtil.searchStringFilter(_Initiator)).append("%' ");
		} else {
			_Initiator = "";
		}
	
		if (!UEngineUtil.isNotEmpty(_Initiator_display)) {
			_Initiator_display = "";
		} else {
			condStr.append(" AND a.initrsnm LIKE '%").append(UEngineUtil.searchStringFilter(_Initiator_display)).append("%' ");
		}
	
		if (!UEngineUtil.isNotEmpty(_Initiator__which_is_xml_value)) {
			_Initiator__which_is_xml_value = "";
		}
		//2009-08-04 InitiatorName end
		
		
		//2009-08-04 NowPersonName start
		if(UEngineUtil.isNotEmpty(_Nowperson)){
			/*			
			condStr.append(" AND a.currep in ( ");
			StringBuffer searchNowpersonString = new StringBuffer();
			for (String nowperson : _Nowperson.split(";")) {
				if (searchNowpersonString.length() > 0) searchNowpersonString.append(",");
				searchNowpersonString.append("'").append(nowperson).append("'");
			}
			condStr.append(searchNowpersonString).append(" ) ");
			*/
			condStr.append(" AND a.currep LIKE '%").append(UEngineUtil.searchStringFilter(_Nowperson)).append("%' ");
		} else {
			_Nowperson = "";
		}
	
		if(!UEngineUtil.isNotEmpty(_Nowperson_display)){
			_Nowperson_display = "";
		} else {
			condStr.append(" AND a.currrsnm LIKE '%").append(UEngineUtil.searchStringFilter(_Nowperson_display)).append("%' ");
		}
	
		if(!UEngineUtil.isNotEmpty(_Nowperson__which_is_xml_value)){
			_Nowperson__which_is_xml_value = "";
		}
		//2009-08-04 NowPersonName end
	
		if (UEngineUtil.isNotEmpty(init_start_date)) {
			condStr.append("AND a.StartedDATE >= '" + UEngineUtil.searchStringFilter(init_start_date) + "' ");
		} else {
			init_start_date = "";
		}
	
		if (UEngineUtil.isNotEmpty(init_end_date)) {
			condStr.append("AND a.StartedDATE <= '" + UEngineUtil.searchStringFilter(init_end_date) + "' ");
		} else {
			init_end_date = "";
		}
	
		if (UEngineUtil.isNotEmpty(complete_start_date)) {
			condStr.append("AND a.finishedDATE >= '" + UEngineUtil.searchStringFilter(complete_start_date) + "' ");
		} else {
			complete_start_date = "";
		}
	
		if (UEngineUtil.isNotEmpty(complete_end_date)) {
			condStr.append("AND a.finishedDATE <= '" + UEngineUtil.searchStringFilter(complete_end_date) + "' ");
		} else {
			complete_end_date = "";
		}
	
		// 2009-08-05 add
		if(UEngineUtil.isNotEmpty(_Instance)){
			condStr.append(" AND instid = " + UEngineUtil.searchStringFilter(_Instance) + " ");
		} else {
			_Instance = "";
		}
	}
	
	String sqlFrom = null;

	if(!loggedUserIsMaster)
	{
		String dBMSProductName = null;
		try {
			dBMSProductName = DAOFactory.getInstance().getDBMSProductName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if ("MySQL".equals(dBMSProductName)) {
			sqlFrom = " FROM bpm_procinst a, bpm_procdef b WHERE a.defid = b.defid AND b.comcode = '" + loggedUserGlobalCom + "' AND ";
		} else {
			sqlFrom = " FROM bpm_procinst a LEFT JOIN bpm_procdef b ON a.defid = b.defid WHERE b.comcode = '" + loggedUserGlobalCom + "' AND ";
		}
	}
	else
	{
		sqlFrom = " FROM bpm_procinst a WHERE ";
	}

	String sql = "SELECT a.instid, a.defname, a.startedDate, a.finishedDate, a.status, a.info, a.name, a.isDeleted, a.ext1, a.defid, "
			+ " a.initep, a.initrsnm, a.currep, a.currrsnm " // add view column
			+ sqlFrom + " a.instid = rootinstid "
			+ " AND a.isDeleted = 0 "
			+ condStr
			+ " ORDER BY a.starteddate DESC";

	System.out.println("[ SQL : "+ sql +" ]");
%>
<%@include file="../lib/jquery/importJquery.jsp" %>
<%@include file="../common/styleHeader.jsp"%>

<style type="text/css">
th {
	font-size: 9pt;
}
td {
	font-size: 8pt;
	
}

</style>
	<jsp:include page="../scripts/formActivity.js.jsp" flush="false">
		<jsp:param name="rmClsName" value="<%=rmClsName%>" />
	</jsp:include>
<script type="text/javascript" src="<%=GlobalContext.WEB_CONTEXT_ROOT %>/scripts/instanceList.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	checkItem();
	setCalender("<%=loggedUserLocale%>", {buttonImage:"<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Icon/btn_calendar.gif"});
});

function checkItem() {
	var options = document.getElementById("status").options;
	for (var i = 0; i < options.length; i++) {
		option = options[i];
		if (option.value == "<%=_status%>") {
			option.selected = true;
		}
	}
}

function resetSubmit() {
	var inputs = document.refreshForm.elements;
	for (var i = 0; i < inputs.length; i++) {
		inputs[i].value = "";
	}

	document.refreshForm.submit();
}

function searchDetail() {
	var mainForm = document.refreshForm;

	mainForm.Nowperson_display.value = $("#Nowperson_display").val();
	mainForm.Nowperson.value = $("#Nowperson").val();
	mainForm.Initiator.value = $("#Initiator").val();
	mainForm.Initiator_display.value = $("#Initiator_display").val();
	mainForm.Instance.value = $("#Instance").val();
	mainForm.docTitle.value = $("#docTitle").val();
	mainForm.init_start_date.value = $("#init_start_date").val();
	mainForm.init_end_date.value = $("#init_end_date").val();
	mainForm.complete_start_date.value = $("#complete_start_date").val();
	mainForm.complete_end_date.value = $("#complete_end_date").val();

	mainForm.simpleKeyWord.value = "";
	mainForm.submit();
}

</script>

<title><%=GlobalContext.getMessageForWeb("Instance List", loggedUserLocale)%></title>
<style type="text/css">
	html body {
		margin: 10px;
	}
</style>
</head>
<body>

<!-- Start Detail Search Layer -->
<div id="divSubSearch" class="divSubSearchStyle" style="display: none;" title="<%=GlobalContext.getMessageForWeb("Advanced Search", loggedUserLocale) %>">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" >
	<colgroup>
	    <col span="1" width="110" style="font-weight: bold; font-size: 12pt;">
        <col span="1" width="220">
        <col span="1" width="110">
        <col span="1" width="*">
	</colgroup>
	    <tr>
	        <td class="formtitle"><%=GlobalContext.getMessageForWeb("Instance Name", loggedUserLocale)%></td>
	        <td class="formcon"><input type="text" id="docTitle" value="<%=docTitle%>" size='28' /></td>
	        <!-- 2009-08-05 update start -->
	        <td class="formtitle"><%=GlobalContext.getMessageForWeb("Instance_Id", loggedUserLocale)%></td>
	        <td class="formcon"><input type="text" id="Instance" value="<%=_Instance%>" onBlur="validate_Number(this);" size='28'/></td>
	        <!-- 2009-08-05 update end -->
	    </tr>
	    <!-- 2009-08-05 start -->
	    <tr bgcolor="#b9cae3">
	        <td colspan="5"  height="1"></td>
	    </tr>
	    <tr>
	        <td class="formtitle"><%= GlobalContext.getMessageForWeb("Initiator Name", loggedUserLocale)%></td>
	        <td class="formcon">
	        	<input type="hidden" id='Initiator__which_is_xml_value' name="Initiator__which_is_xml_value" value="<%=_Initiator__which_is_xml_value %>" />
	            <input type="text" name="Initiator_display" id="Initiator_display" size='28' value="<%=_Initiator_display%>" />
	        <td class="formtitle"><%= GlobalContext.getMessageForWeb("Initiator Id", loggedUserLocale)%></td>
	        <td class="formcon"><input type="text" name="Initiator"  id="Initiator" size='28' value="<%=_Initiator%>" /></td>
	    </tr>
	    <tr bgcolor="#b9cae3">
	        <td colspan="5"  height="1"></td>
	    </tr>
	    <tr>
	        <td class="formtitle"><%=GlobalContext.getMessageForWeb("Current Participant Name", loggedUserLocale) %></td>
	        <td class="formcon">
	        	<input type="hidden" id='Nowperson__which_is_xml_value' name="Nowperson__which_is_xml_value" value="<%=_Nowperson__which_is_xml_value %>" />
	            <input type="text" name="Nowperson_display" id="Nowperson_display" size='28' value="<%=_Nowperson_display%>"/>
	        <td class="formtitle"><%=GlobalContext.getMessageForWeb("Current Participant Id", loggedUserLocale) %></td>
	        <td class="formcon"><input type="text" name="Nowperson" id="Nowperson" size='28' value="<%=_Nowperson%>"/></td>
	    </tr>
	    <tr bgcolor="#b9cae3">
	        <td colspan="5"  height="1"></td>
	    </tr>
	    <!-- 2009-08-05 end -->
	    <tr>
	        <td class="formtitle"><%=GlobalContext.getMessageForWeb("Start Date", loggedUserLocale)%></td>
	        <td class="formcon">
	        	<input type="text" id="init_start_date" value="<%=init_start_date%>" class='j_calendar' size="12"/>
	        	~ <input type="text" id="init_end_date" value="<%=init_end_date%>" class='j_calendar' size="12"/>
	        </td>
	        <td class="formtitle"><%=GlobalContext.getMessageForWeb("End Date", loggedUserLocale)%></td>
	        <td class="formcon">
	        	<input type="text" id="complete_start_date" value="<%=complete_start_date%>" class='j_calendar' size="12"/>
	        	~ <input type="text" id="complete_end_date" value="<%=complete_end_date%>" class='j_calendar' size="12"/>
	        </td>
	    </tr>
	</table>
</div>
<!-- End Detail Search Layer -->

<form name="refreshForm" method="post" action="processInstanceList.jsp" onSubmit="document.refreshForm.currentPage.value=1;">
<fieldset class='block-labels' >
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td align="right" style="padding:0 0 10px 0;">
        	<table width="*" border="0" cellspacing="0" cellpadding="0">
                <tr height="25" valign="middle">
                    <td><img src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/searchTitle.gif" width="70" height="25"></td>
	                <td background="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/searchTitleCenter.gif" valign="middle">
                    	<select name="status" id="status" style="width: 120px">
                            <option value="All"><%=GlobalContext.getMessageForWeb("Status", loggedUserLocale)%> : <%=GlobalContext.getMessageForWeb("All", loggedUserLocale)%></option>
                            <option value="Running"><%=GlobalContext.getMessageForWeb("Status", loggedUserLocale)%> : <%=GlobalContext.getMessageForWeb("Running", loggedUserLocale)%></option>
                            <option value="Completed"><%=GlobalContext.getMessageForWeb("Status", loggedUserLocale)%> : <%=GlobalContext.getMessageForWeb("Completed", loggedUserLocale)%></option>
                            <option value="Stopped"><%=GlobalContext.getMessageForWeb("Status", loggedUserLocale)%> : <%=GlobalContext.getMessageForWeb("Stopped", loggedUserLocale)%></option>
                            <option value="Failed"><%=GlobalContext.getMessageForWeb("Status", loggedUserLocale)%> : <%=GlobalContext.getMessageForWeb("Failed", loggedUserLocale)%></option>
                        </select>
	                    <input type="text" name="simpleKeyWord" value="<%=simpleKeyWord%>" size='15'  style="background:#FFF;"/>
	                </td>
	                <td width="5" background="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/searchTitleCenter.gif"></td>
	                <td width="*" background="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/searchTitleCenter.gif" valign="middle">
	                    <img src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/searchGo.gif" 
	                    alt="Search" align="middle" onclick="searchSimple();" style="cursor: pointer;" />
	                    <a href="processInstanceListDetail.jsp"><img src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/searchRefresh.gif" 
	                    alt="reset" align="middle" /></a>
	                </td>
	                <td width="5" background="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/searchTitleCenter.gif"></td>
	                <td background="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/searchTitleCenter.gif">
	                    <a href="javascript: showDetailSearch('ProcessManager', '750', '140', '<%=GlobalContext.getMessageForWeb("Search Button", loggedUserLocale)%>', '<%=GlobalContext.getMessageForWeb("Close Button", loggedUserLocale)%>');" style="text-decoration:underline;"><%=GlobalContext.getMessageForWeb("Advanced Search", loggedUserLocale) %></a></td>
	                <td><img src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/searchTitleRight.gif"></td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<input type="hidden" name="currentPage" value="<%=currentPage%>">
<!-- Sort -->
<input type="hidden" name="sort_column" value="<%=strSortColumn%>">
<input type="hidden" name="sort_cond" value="<%=strSortCond%>">
<!-- Search -->
<input type="hidden" name="TARGETCOND" value="<%=strTarget%>">
<input type="hidden" name="defId" value="<%=_defId%>">

<input type="hidden" name="Nowperson" value="<%=_Nowperson%>">
<input type="hidden" name="Nowperson_display" value="<%=_Nowperson_display%>">
<input type="hidden" name="Initiator" value="<%=_Initiator%>">
<input type="hidden" name="Initiator_display" value="<%=_Initiator_display%>">
<input type="hidden" name="Instance" value="<%=_Instance%>">
<input type="hidden" name="docTitle" value="<%=docTitle%>">
<input type="hidden" name="init_start_date" value="<%=init_start_date%>">
<input type="hidden" name="init_end_date" value="<%=init_end_date%>">
<input type="hidden" name="complete_start_date" value="<%=complete_start_date%>">
<input type="hidden" name="complete_end_date" value="<%=complete_end_date%>">

<input type='hidden' name='Nowperson__which_is_xml_value' id="Nowperson__which_is_xml_value" value="<%=_Nowperson__which_is_xml_value%>" />
<input type='hidden' name='Initiator__which_is_xml_value' id="Initiator__which_is_xml_value" value="<%=_Initiator__which_is_xml_value%>" />

</fieldset>

<%
	if (UEngineUtil.isNotEmpty(_defId)) {
%>
	Search for process definition : <%=_defId %>
<%
	}
%>	
	
    <table width="100%">
        <tr>
            <td>
            	<table border="0" width="100%" cellpadding="0" cellspacing="0" class="tableborder">
                    <colgroup>
	                    <col width="50px" height="27px">
	                    <col width="2px">
	                    <col width="50px">
	                    <col width="2px">
	                    <col width="*" align="left">
	                    <col width="2px">
	                    <col width="155px" align="left">
	                    <col width="2px">
	                    <col width="60px">
	                    <col width="2px">
	                    <col width="70px">
	                    <col width="2px">
	                    <col width="130px">
	                    <col width="2px">
	                    <col width="100px">
	                    <col width="2px">
	                    <col width="100px">
	                    <col width="2px">
	                    <col width="30px">
	                    <col width="2px">
	                    <col width="50px">
                    </colgroup>
                    <tr class="tabletitle" align="center" height="26">
                    	<th><%=GlobalContext.getMessageForWeb("Status", loggedUserLocale)%></th>
                        <th><img src="../images/Common/tabletitleline.gif" width="2"></th>
                        <th><%=GlobalContext.getMessageForWeb("ID", loggedUserLocale)%></th>
                        <th><img src="../images/Common/tabletitleline.gif" width="2"></th>
                        <th><%=GlobalContext.getMessageForWeb("Instance Name", loggedUserLocale)%></th>
                        <th><img src="../images/Common/tabletitleline.gif" width="2"></th>
                        <th><%=GlobalContext.getMessageForWeb("Definition Name", loggedUserLocale)%></th>
                        <th><img src="../images/Common/tabletitleline.gif" width="2"></th>
                        <!-- --------------------------add view column---------------------- -->
                        <th><%=GlobalContext.getMessageForWeb("Initiator", loggedUserLocale)%></th>
                        <th><img src="../images/Common/tabletitleline.gif" width="2"></th>
                        <th><%=GlobalContext.getMessageForWeb("Current Participant", loggedUserLocale)%></th>
                        <th><img src="../images/Common/tabletitleline.gif" width="2"></th>
                        <!-- --------------------------------------------------------------- -->
                        <th><%=GlobalContext.getMessageForWeb("Info", loggedUserLocale)%></th>
                        <th><img src="../images/Common/tabletitleline.gif" width="2"></th>
                        <th><%=GlobalContext.getMessageForWeb("Start Date", loggedUserLocale)%></th>
                        <th><img src="../images/Common/tabletitleline.gif" width="2"></th>
                        <th><%=GlobalContext.getMessageForWeb("End Date", loggedUserLocale)%></th>
                        <th><img src="../images/Common/tabletitleline.gif" width="2"></th>
                        <th><%=GlobalContext.getMessageForWeb("Ext1", loggedUserLocale)%></th>
                        <th><img src="../images/Common/tabletitleline.gif" width="2"></th>
                        <th><%=GlobalContext.getMessageForWeb("Remove", loggedUserLocale)%></th>
                    </tr>
<%
				//	ProcessInstanceDAO procInst = (ProcessInstanceDAO)GenericDAO.createDAOImpl("java:/uEngineDS", sql, ProcessInstanceDAO.class);
				//	procInst.select();
				java.sql.Connection con = DefaultConnectionFactory.create().getConnection();
				try {
					dl = DAOListCursorUtil.executeList(sql, condition, new ArrayList(), con);
					totalCount = (int) dl.getTotalCount();
					totalPageCount = dl.getTotalPageNo();
				} catch (Exception e) {
					throw e;
				} finally {
					if (con != null) {
						con.close();
					}
				}
				//int aclTotalCount = 0;
				//	while(procInst.next()){
				if (totalCount > 0) {
					boolean isGray = false;
					String bgcolor = " onmouseover=\"this.style.backgroundColor='#e7effa';\" onmouseout=\"this.style.backgroundColor = '';\" ";

					String strGrayStyle = " bgcolor=\"#F2F2F2\" ";

					String strNotGrayStyle = " bgcolor=\"#FFFFFF\" ";
					
					AclManager acl = AclManager.getInstance();
					
					for (int i = 0; i < dl.size(); i++) {
						DataMap tmpMap = (DataMap) dl.get(i);
						String pid = tmpMap.getString("instid", "-");
						String status = tmpMap.getString("status", "-");
						String instName = tmpMap.getString("name", "-");
						String initrsnm = tmpMap.getString("initrsnm", "-");
						String currrsnm = tmpMap.getString("currrsnm", "-");
						String defName = tmpMap.getString("defname", "-");
						String info = tmpMap.getString("info", "-");
						String startedDate = tmpMap.getString("startedDate", "-");
						if (startedDate.length() > 10) {
							startedDate = startedDate.substring(0,16);
						}
						String finishedDate = tmpMap.getString("finishedDate", "-");
						if (finishedDate.length() > 10) {
							finishedDate = finishedDate.substring(0,16);
						}
						String ext1 = tmpMap.getString("ext1", "-");
						String defId = tmpMap.getString("defid", "-");

						HashMap permission = null;
						if (loggedUserIsAdmin) {
                            permission = new HashMap();
							permission.put(AclManager.PERMISSION_MANAGEMENT, true);
						} else {
						    permission = acl.getPermission(Integer.parseInt(defId), loggedUserId);
						}
						
						String backGroundColor = bgcolor + (isGray ? strGrayStyle : strNotGrayStyle);
						isGray = !isGray;
						
						if (permission.containsKey(AclManager.PERMISSION_MANAGEMENT) || permission.containsKey(AclManager.PERMISSION_EDIT)) {
%>
                    <tr align="center" style="font-size: 9pt; height: 24px;" <%=backGroundColor%>>
                        <td><font color="<%=colors.get(status)%>"><%=status%></font></td>
                        <td></td>
                        <td><%=pid%></td>
                        <td></td>
                        <td><a href='viewProcessFlowChart.jsp?instanceId=<%=pid%>'><strong><%=instName%></strong></a></td>
                        <td></td>
                        <td><%=defName%></td>
                        <td></td>
                        <td><%=initrsnm%></td>
                        <td></td>
                        <td><%=currrsnm%></td>
                        <td></td>
                        <td><%=info%></td>
                        <td></td>
                        <td><%=startedDate%></td>
                        <td></td>
                        <td><%=finishedDate%></td>
                        <td></td>
                        <td><%=ext1%></td>
                        <td></td>
                        <td><a href='removeProcessInstance.jsp?instanceId=<%=pid%>'><img
					src="../images/Common/b_delete02.gif" alt="remove instance"></a></td>
                    </tr>
                    <tr>
                        <td colspan="121" height="1" bgcolor="#c7d3e4"></td>
                    </tr>
<%
						}
					}
				}
%>
                </table></td>
        </tr>
        <tr>
            <td align="center">
<%
			if (totalCount > 0) {
%>
                <!--	#####	Navigation start		#####	-->
                <br style="line-height: 15px;">
                <bpm:page link="" totalcount="<%=totalCount%>"
				pagecount="<%=intPageCnt%>" pagelimit="<%=nPagesetSize%>"
				currentpage="<%=currentPage%>" locale="<%=loggedUserLocale%>" />
                <br>
<%
			}
%>
			</td>
        </tr>
    </table>
</form>
</body>
</html>