<%@page import="org.uengine.ui.list.datamodel.DataMap"%>
<%@page import="org.uengine.ui.list.util.HttpUtils"%>
<%@page import="org.uengine.kernel.viewer.ViewerOptions"%>
<%@page import="org.uengine.util.*"%>
<%@page import="org.uengine.web.chart.MakeFlowChart"%>

<%@include file="../../common/header.jsp"%>
<%@include file="../../common/getLoggedUser.jsp"%>

<jsp:useBean id="pmfBean" scope="application" class="org.uengine.processmanager.ProcessManagerFactoryBean" />
<%
MakeFlowChart mfc = new MakeFlowChart();

String chart = mfc.getMultipleSubProcess(pmfBean, reqMap, loggedUserId, loggedUserLocale);

if (chart == null) {
    return;
} else {
    out.println(chart);
}
%>

