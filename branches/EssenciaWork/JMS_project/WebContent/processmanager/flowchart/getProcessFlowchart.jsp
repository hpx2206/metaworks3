<%@page import="org.uengine.ui.list.datamodel.DataMap"%>
<%@page import="org.uengine.ui.list.util.HttpUtils"%>
<%@page import="org.uengine.kernel.viewer.ViewerOptions"%>
<%@page import="org.uengine.util.*"%>
<%@page import="org.uengine.web.chart.MakeFlowChart"%>

<%@include file="../../common/header.jsp"%>
<%@include file="../../common/getLoggedUser.jsp"%>

<jsp:useBean id="processManagerFactory" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<%
ProcessManagerRemote pm = processManagerFactory.getProcessManagerForReadOnly();
MakeFlowChart mfc = new MakeFlowChart();

String imgFolderPath = GlobalContext.WEB_CONTEXT_ROOT + "/processmanager/images";
String wihDefaultTemplatePath = GlobalContext.WEB_CONTEXT_ROOT + "/wih/wihDefaultTemplate";

String instanceId = reqMap.getString("instanceId", "");
String processDefinition = reqMap.getString("processDefinition", "");
String definitionVersionId = reqMap.getString("definitionVersionId", "");
String initiate = reqMap.getString("initiate", "");
String parentPdver = reqMap.getString("parentPdver", "");

try {
    if (!mfc.initFlowChart(pm, reqMap, loggedUserLocale, loggedUserId)) return;
%>
<div style="width: 95%;">
<input type="hidden" id="flowchartVariable_instanceId" value="<%=instanceId%>" />
<input type="hidden" id="flowchartVariable_processDefinition" value="<%=processDefinition%>" />
<input type="hidden" id="flowchartVariable_definitionVersionId" value="<%=definitionVersionId%>" />
<input type="hidden" id="flowchartVariable_viewType" value="<%=mfc.getViewType()%>" />
<input type="hidden" id="flowchartVariable_viewOption" value="<%=mfc.getViewOption()%>" />
<input type="hidden" id="flowchartVariable_lastInstance" value="<%=mfc.getLastInstanceId()%>" />
<input type="hidden" id="flowchartVariable_initiate" value="<%=initiate%>" />
<input type="hidden" id="flowchartVariable_parentPdver" value="<%=parentPdver%>" />

<%
    StringBuilder tmpParentPdVers = new StringBuilder(0);
    int chartCount = 0;
    for(int i = mfc.getVtMainPi().size() - 1; i >= 0 ; i-- ) {
        chartCount++;
        String barTitle = mfc.getVtTitle().get(i);
        String strInstanceId = mfc.getVtMainPi().get(i);
        boolean isProcessDefinition = mfc.getVtIsProcessDefinition().get(i);

        String rowId = null;

        if (isProcessDefinition) {
            mfc.setScriptFncFlowChart("", "", strInstanceId, tmpParentPdVers.toString(), mfc.getViewType(), mfc.getViewOption(), mfc.getLastInstanceId(), initiate, "");
            
            if (tmpParentPdVers.length() > 0) {
                tmpParentPdVers.append(";");
            }

            rowId = "definition_" + strInstanceId;
            tmpParentPdVers.append(strInstanceId);
        } else {
            mfc.setScriptFncFlowChart(strInstanceId, "", "", "", mfc.getViewType(), mfc.getViewOption(), "", initiate, "");
            
            rowId = "instance_" + strInstanceId;
        }
%>
<!-- Parent table -->
<table id="tableParantTitle_<%=chartCount %>" border="0" cellspacing="0" cellpadding="0" width="100%" style="display:none;">
    <%=mfc.getBeginningRow(rowId, barTitle, mfc.getScriptFncFlowChart(), false) %>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableFlowchartCanvas_<%=rowId%>">
    <tr>
        <td rowspan="2" width="50" valign="top"><div style="display:none;" id="divImgLine_<%=chartCount%>"><img src="<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Common/fc_line.gif"></div></td>
        <td height="15"></td>
    </tr>
    <tr>
        <td align="left">
<%
    }

    if (mfc.getArrayParentId() != null) {
        for (int iCount = 0; iCount < mfc.getArrayParentId().length; iCount++) {
            String strFlowchartId = mfc.getArrayParentId()[iCount];
            
            String strBarTitle = pm.getProcessDefinitionRemoteWithInstanceId(strFlowchartId).getName().toString();
            mfc.setScriptFncFlowChart(instanceId, "", definitionVersionId, "", mfc.getViewType(), mfc.getViewOption(), "", initiate, strFlowchartId);
%>
            <table border="0" cellspacing="0" cellpadding="0" align="left" width="100%">
                <%=mfc.getBeginningRow("instance_" + strFlowchartId, strBarTitle, mfc.getScriptFncFlowChart(), false) %>
            </table>
<%
        }
    }
%>
            <!-- Flowchart -->
            <table id="tableFlowchartCanvas_<%=mfc.getChartType() %>_<%=mfc.getViewInstDefVerId()%>" border="0" cellspacing="0" cellpadding="0" align="left" width="100%">
                <%=mfc.getBeginningRow(mfc.getChartType() + "_" + mfc.getViewInstDefVerId(), mfc.getTitle(), "", false) %>
                <tr id="rowFlowchartCanvas_<%=mfc.getChartType() %>_<%=mfc.getViewInstDefVerId()%>">
                    <td id="cellFlowchartCanvas_<%=mfc.getChartType() %>_<%=mfc.getViewInstDefVerId()%>" height="80" align="center">
                        <div id="divTitleBar" style="background:#FAFAFA; border:1px solid #CACACA; border-left:none; border-right:none; padding:6px;  width:100%; height:46px;">
                        <table width="100%" cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td align="left">
                                    <img src="<%=imgFolderPath %>/view_option_title.gif" border="0"
                                    /><img src="<%=imgFolderPath %>/view_option_vertical.gif" border="0" style="cursor: pointer;" onclick="swapViewType('vertical', 'vertical', '<%=instanceId%>', '<%=definitionVersionId %>', '<%=mfc.getViewInstDefVerId() %>');"
                                    /><img src="<%=imgFolderPath %>/view_option_horizontal.gif" border="0" style="cursor: pointer;" onclick="swapViewType('horizontal', 'horizontal', '<%=instanceId%>', '<%=definitionVersionId %>', '<%=mfc.getViewInstDefVerId() %>');"
                                    /><img src="<%=imgFolderPath %>/view_option_swimlane_virtical.gif" border="0" style="cursor: pointer;" onclick="swapViewType('swimlane', 'vertical', '<%=instanceId%>', '<%=definitionVersionId %>', '<%=mfc.getViewInstDefVerId() %>');"
                                    /><img src="<%=imgFolderPath %>/view_option_swimlane_horizontal.gif" border="0" style="cursor: pointer;" onclick="swapViewType('swimlane', 'horizontal', '<%=instanceId%>', '<%=definitionVersionId %>', '<%=mfc.getViewInstDefVerId() %>');"
                                    /><img src="<%=imgFolderPath %>/view_option_ganttchart.gif" border="0" style="cursor: pointer;" onclick="swapViewType('ganttchart', 'ganttchart', '<%=instanceId%>', '<%=definitionVersionId %>', '<%=mfc.getViewInstDefVerId() %>');"
                                    />
                                    SubProcess view type
                                    <input type="radio" name="subProcessViewType" value="multiple" onclick="flowchart.setSubProcessViewType(this.value)" /> Multiple
                                    <input type="radio" name="subProcessViewType" value="cascade" onclick="flowchart.setSubProcessViewType(this.value)" /> Cascade
                                </td>
                                <td align="right">
<%
    if (
            !UEngineUtil.isTrue(request.getParameter("initiate"))
            && !UEngineUtil.isNotEmpty(instanceId)
            && (
	            mfc.getPermission().containsKey(AclManager.PERMISSION_INITIATE)
	            || mfc.getPermission().containsKey(AclManager.PERMISSION_EDIT)
	            || mfc.getPermission().containsKey(AclManager.PERMISSION_MANAGEMENT)
            )
    ) {
%>
                                    <a href="javascript:initateProcess('<%=mfc.getPdrId()%>');"><img src="<%=imgFolderPath %>/run_<%=loggedUserLocale%>.gif" align="middle" border="0"></a>
<%
    } 
%>
                                </td>
                                <td width="10"></td>
                            </tr>
                        </table>
                        </div>
                        <div id="oZoom">
<%
    if (
            mfc.getPermission().containsKey(AclManager.PERMISSION_VIEW)
            || mfc.getPermission().containsKey(AclManager.PERMISSION_INITIATE)
            || mfc.getPermission().containsKey(AclManager.PERMISSION_EDIT)
            || mfc.getPermission().containsKey(AclManager.PERMISSION_MANAGEMENT)
    ) {
        out.println("<br />" + mfc.getChart());
    }
    else
    {
        out.println(GlobalContext.getMessageForWeb("You have not permission", loggedUserLocale));
    }
%>
                            <br /><br />
                        </div>
                        <!-- End flowchart -->
                    </td>
                </tr>
            </table>

<!-- Child Flowchart -->
<%
    if (mfc.getArrayChildId() != null) {
        for (int iCount = 0; iCount < mfc.getArrayChildId().length; iCount++) {
            String strFlowchartId = mfc.getArrayChildId()[iCount];
            
            String strBarTitle = pm.getProcessDefinitionRemoteWithInstanceId(strFlowchartId).getName().toString();
            mfc.setScriptFncFlowChart(instanceId, "", definitionVersionId, "", mfc.getViewType(), mfc.getViewOption(), "", initiate, strFlowchartId);
%>
            <table border="0" cellspacing="0" cellpadding="0" align="left" width="100%">
                <%=mfc.getBeginningRow("instance_" + strFlowchartId, strBarTitle, mfc.getScriptFncFlowChart(), false) %>
            </table>
<%
        }
    }
    
    for (int i = 0; i < mfc.getVtMainPi().size(); i++) { 
%>
        <!-- End parent table -->
        </td>
    </tr>
</table>
<%
    }
%>

</div>
<br /><br />
<%
} finally {
    try{pm.remove();} catch(Exception e){}
}
%>