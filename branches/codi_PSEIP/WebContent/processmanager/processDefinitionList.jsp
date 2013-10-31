<%@page import="org.uengine.persistence.dao.*"%>
<%
    String target = request.getParameter("target");
%>

<jsp:include page="../scripts/formActivity.js.jsp" flush="false">
    <jsp:param name="rmClsName" value="<%=rmClsName%>" />
</jsp:include>
<script type="text/javascript">
$(document).ready(function () {
    setCalender("<%=loggedUserLocale%>", {buttonImage:"<%=GlobalContext.WEB_CONTEXT_ROOT%>/images/Icon/btn_calendar.gif"});
});

function viewObjectType(objType ,id, folder){
    if(objType=="process"){
        document.viewProcessDefinition.processDefinition.value=id;
        document.viewProcessDefinition.folder.value=folder;     
        document.viewProcessDefinition.submit();
    }else if(objType=="rule"){
        document.viewRuleDefinition.objectDefinitionId.value=id;
        document.viewRuleDefinition.folder.value=folder;        
        document.viewRuleDefinition.submit();
    }else if(objType=="class"){
        document.viewClassDefinition.objectDefinitionId.value=id;
        document.viewClassDefinition.folder.value=folder;       
        document.viewClassDefinition.submit();
    }else if(objType=="form"){
        document.viewFormDefinition.objectDefinitionId.value=id;
        document.viewFormDefinition.folder.value=folder;        
        document.viewFormDefinition.submit();
    }else if(objType=="excel"){
        document.viewCalcDefinition.objectDefinitionId.value=id;
        document.viewCalcDefinition.folder.value=folder;        
        document.viewCalcDefinition.submit();
    }
}

    
function newObject(objType,defId){
    if(defId==undefined || defId=="continentRoot") defId=-1;

    if(objType=="process"){
        location='ProcessDesigner.jnlp?folderId='+defId;
    }else if (objType=="mxProcess") {
		location='../mxgraph/examples/editors/workfloweditor.html?folderId='+defId;
    }else if(objType=="rule"){
        document.newRuleDefinition.folderId.value=defId;    
        document.newRuleDefinition.submit();
    }else if(objType=="class"){
        document.newClassDefinition.folderId.value=defId;       
        document.newClassDefinition.submit();
    }else if(objType=="form"){
        document.newFormDefinition.folderId.value=defId;        
        document.newFormDefinition.submit();
    }else if(objType=="crud_app"){
        document.newDatabaseSet.folderId.value=defId;       
        document.newDatabaseSet.submit();
    }
}

function addFolder(parent){
    if(parent==undefined || parent=="continentRoot") parent=-1;
    document.addFolderForm.parentFolder.value=parent;
    dijit.byId('addFolderDialog').show();
}

var definitionId = null;
function moveFolder(tempDefinitionId, definitionName){
    var url = "../processparticipant/commonfilter/selectProcessDefinition_frame.jsp?moveToDef=Y";
    var StrOption = "width=500,height=300,scroll=auto,resizable=false";

    definitionId = tempDefinitionId;
    window.open (url , "", StrOption);
}

function actionMoveFolder(parentFolder) {
    document.location.href = WEB_CONTEXT_ROOT + "/processmanager/moveProcessDefinition.jsp?processDefinition=" + definitionId
            + "&parentFolder=" + parentFolder;
}

function rename(processDefinition, definitionName){
    document.actionForm.processDefinition.value=processDefinition;
    dijit.byId('reNameDialog').show();

    var tempTest = document.getElementsByName("newname");
    for (var i = 0, il = tempTest.length; i < il; i++)
    {
        tempTest[i].value = definitionName;
    }
}

function remove(processDefinition) {
    if (confirm("Are you sure to delete this processdefinition?")) {
        document.removeForm.folder.value=processDefinition;
        document.removeForm.submit();
    }
}
        
function exportDef(processDefinition){
    if(processDefinition==undefined || processDefinition=="continentRoot") processDefinition=-1;
    document.exportDef.processDefinition.value=processDefinition;
    document.exportDef.submit();
}

function importDefDialog(processDefinition){
    if(processDefinition==undefined || processDefinition=="continentRoot") processDefinition=-1;
    document.importDef.definitionId.value=processDefinition;
    
    var dialogOpts = {
            autoOpen: false,
            width: 300,
            modal : true,
            resizable: true,
            buttons: [{
                text: "<%=GlobalContext.getMessageForWeb("OK", loggedUserLocale) %>",
                click: function() { fnImportDef() }
            }]
    };
    
    jQuery("#importDefDialog").dialog(dialogOpts);
    jQuery("#importDefDialog").dialog("open");
    $("#importDefDialog").css("height", 40);
}

function fnImportDef() {
    $("#divImport").append($("#importDefPath"));
    document.importDef.submit();
    $("#divInputFile").append($("#importDefPath"));
    jQuery("#importDefDialog").dialog("close");
}

/* 
 * Authority management
 */
function accessibility(item){
    document.accessibility.defId.value = item.id;
    document.accessibility.defName.value = item.name;
    document.accessibility.submit();
    //alert("You can't use this function, In standalone version");
}
      
function toggleVisible(defId, isVisible){
    if(defId==undefined || defId=="continentRoot") defId=-1;

    document.toggleVisibleForm.definitionId.value=defId;
    document.toggleVisibleForm.visible.value=isVisible;
    document.toggleVisibleForm.submit();
}

// Serach instance
function search() {
    //document.searchForm.target = "right";
    document.searchForm.submit();       
}
function callback(cal) {
    //checkDate();
}

var userType = 'initiator';

function selectUser(id, name, loginName) {
    if ( userType == 'initiator' ) {
        document.searchForm.initiatorId.value = id;
        document.searchForm.initiatorName.value = name;
        document.searchForm.initiatorLoginName.value = loginName;
    } else if ( userType == 'currentUser' ) {
        document.searchForm.currentUserId.value = id;
        document.searchForm.currentUserName.value = name;
        document.searchForm.currentUserLoginName.value = loginName;
    }
}

function initInputBox() {
    var sForm = document.searchForm;
    sForm.initiatorId.value = "";
    sForm.initiatorName.value = "";
    sForm.currentUserId.value = "";
    sForm.currentUserName.value = "";
    sForm.init_start_date.value = "";
    sForm.init_end_date.value = "";
    sForm.due_start_date.value = "";
    sForm.due_end_date.value = "";
    sForm.complete_start_date.value = "";
    sForm.complete_end_date.value = "";
    sForm.docTitle.value = "";
    sForm.workName.value = "";
}

function reNameDialog(name){
    if(name !=null && name !="" && name!="null"){
        document.actionForm.newName.value = name;
        document.actionForm.submit();
    }
}

function addFolderDialog(name){
    if(name !=null && name !="" && name!="null"){
        document.addFolderForm.folderName.value=name;
        document.addFolderForm.submit();
    }
}

function getChildNodesForAjax(item) {
	var firstChild = item.children[0];
	if(firstChild.type == "temp") {
		continentStore.deleteItem(firstChild);
		$.ajax({
			url : "makeProcessDefinitionListJson.jsp",
			data : "parentDefId=" + item.id,
			type : "POST",
			dataType : "json",
			success :function (data, textStatus, jqXHR) {
				$(data.items).each(function(index, defItem) {
					var newItem = continentStore.newItem(defItem, {parent : item, attribute : "children"});
					if (defItem.obj == "folder" && parseInt(defItem.childCnt) > 0) {
						var tempChild = {id : "temp_" + index + defItem.obj + "_" + defItem.id, name : "tempChildrenForItem", type : "temp"};
						continentStore.newItem(tempChild, {parent : newItem, attribute : "children"});
					}
				});
			} 
		});
	}
}

function addChildNodeData(definitionStore, data) {
	var tempChild = {id : "temp_" + 0 + data.obj + "_" + data.id, name : "tempChildrenForItem", type : "folder"};
	definitionStore.newItem(tempChild, {parent : data, attribute : "children"});
}
        
</script>

<div class="soria">
<div dojoType="dijit.Dialog" id="reNameDialog" title="insert newname" execute="reNameDialog(arguments[0].newname);">
    <table>
        <tr>
            <td><label for="newname"><%=GlobalContext.getMessageForWeb("Name", loggedUserLocale) %> : </label></td>
            <td><input dojoType="dijit.form.TextBox" type="text" name="newname" id="newname"></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><button dojoType="dijit.form.Button" type="submit">OK</button></td>
        </tr>
    </table>
</div>
<div dojoType="dijit.Dialog" id="addFolderDialog" title="insert name" execute="addFolderDialog(arguments[0].name);">
    <table>
        <tr>
            <td><label for="name"><%=GlobalContext.getMessageForWeb("Name", loggedUserLocale) %> : </label></td>
            <td><input dojoType="dijit.form.TextBox" type="text" name="name" id="name"></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><button dojoType="dijit.form.Button" type="submit"><%=GlobalContext.getMessageForWeb("OK", loggedUserLocale) %></button></td>
        </tr>
    </table>
</div>

<form name="importDef" action="importDefinition.jsp" enctype="multipart/form-data" method="post" target="innerworkarea">
    <input type="hidden" name="definitionId">
    <div id="divImport"></div>
    <div id="importDefDialog" title="Select Definition" style="display: none; height:100%;">
        <table>
            <tr>
                <td>
                    <div id="divInputFile"><input type="file" name="importDefPath" id="importDefPath" /></div>
                </td>
            </tr>
        </table>
    </div>
</form>

<div dojoType="dojo.data.ItemFileWriteStore" jsId="continentStore" url="json/jsonConfig.json"></div>
<div dojoType="dijit.tree.ForestStoreModel" jsId="continentModel" store="continentStore" rootId="continentRoot" rootLabel="Definitions" childrenAttrs="children"></div>

<!-- ########## changed menu code here ########## -->
<ul dojoType="dijit.Menu" id="folder_menu" style="display: none;">
    <li dojoType="dijit.MenuItem" onclick="viewObjectType(global_tn.item.obj,global_tn.item.id,global_tn.item.parent);"><%=GlobalContext.getMessageForWeb("View", loggedUserLocale) %></li>
<%
if (!(GlobalContext.getPropertyString("aclmanager.class", "").equals("") || GlobalContext.getPropertyString("aclmanager.class", "").indexOf("DefaultNonAclUtil") > -1)) {
%>
    <li dojoType="dijit.MenuItem" onclick="accessibility(global_tn.item)"><%=GlobalContext.getMessageForWeb("Set Authority", loggedUserLocale) %></li>
<%
}
%>
    <li dojoType="dijit.MenuItem" onclick="rename(global_tn.item.id, global_tn.item.name)"><%=GlobalContext.getMessageForWeb("Rename", loggedUserLocale) %></li>
    <li dojoType="dijit.MenuItem" onclick="remove(global_tn.item.id)"><%=GlobalContext.getMessageForWeb("Remove", loggedUserLocale) %></li>
    <li dojoType="dijit.MenuItem" onclick="moveFolder(global_tn.item.id,global_tn.item.name)"><%=GlobalContext.getMessageForWeb("Move", loggedUserLocale) %></li>
    <li dojoType="dijit.MenuItem" onclick="document.location.reload()"><%=GlobalContext.getMessageForWeb("Refresh", loggedUserLocale) %></li>
    <!--li dojoType="dijit.PopupMenuItem">
            <span>Visible</span>
            <ul dojoType="dijit.Menu" id="submenu1" >
                <li dojoType="dijit.MenuItem" onClick="toggleVisible(global_tn.item.id,"true")">visible</li>
                <li dojoType="dijit.MenuItem" onClick="toggleVisible(global_tn.item.id,"false")">invisible</li>
            </ul>
        </li-->
    <li dojoType="dijit.PopupMenuItem">
        <span><%=GlobalContext.getMessageForWeb("New", loggedUserLocale) %></span>
        <ul dojoType="dijit.Menu" id="submenu2">
            <li dojoType="dijit.MenuItem" onclick="newObject('process',global_tn.item.id)" ><%=GlobalContext.getMessageForWeb("Process", loggedUserLocale) %></li>
            <li dojoType="dijit.MenuItem" onclick="newObject('mxProcess',global_tn.item.id)" ><%=GlobalContext.getMessageForWeb("mxProcess", loggedUserLocale) %></li>
            <!-- li dojoType="dijit.MenuItem" onClick="newObject('rule',global_tn.item.id)" ><%=GlobalContext.getMessageForWeb("Rule", loggedUserLocale) %></li-->
            <li dojoType="dijit.MenuItem" onclick="newObject('form',global_tn.item.id)" ><%=GlobalContext.getMessageForWeb("Form", loggedUserLocale) %></li>
            <!-- li dojoType="dijit.MenuItem" onClick="newObject('class',global_tn.item.id)" ><%=GlobalContext.getMessageForWeb("Class", loggedUserLocale) %></li-->
            <li dojoType="dijit.MenuItem" onclick="addFolder(global_tn.item.id)" ><%=GlobalContext.getMessageForWeb("Child Folder", loggedUserLocale) %></li>
            <!-- li dojoType="dijit.MenuItem" onClick="newObject('excel',global_tn.item.id)" ><%=GlobalContext.getMessageForWeb("JCalc", loggedUserLocale) %></li-->
            <!--li dojoType="dijit.MenuItem" onClick="newObject('crud_app',global_tn.item.id)" ><%=GlobalContext.getMessageForWeb("Crud App", loggedUserLocale) %></li-->
        </ul>
    </li>
    <li dojoType="dijit.PopupMenuItem">
        <span><%=GlobalContext.getMessageForWeb("Im/Export", loggedUserLocale) %></span>
        <ul dojoType="dijit.Menu" id="submenu3" >
            <li dojoType="dijit.MenuItem" onclick="importDefDialog(global_tn.item.id)"><%=GlobalContext.getMessageForWeb("Import", loggedUserLocale) %></li>
            <li dojoType="dijit.MenuItem" onclick="exportDef(global_tn.item.id)"><%=GlobalContext.getMessageForWeb("Export", loggedUserLocale) %></li>
        </ul>
    </li>
</ul>
<!-- ############################################ -->

<div dojoType="dijit.layout.LayoutContainer" style="width: 100%; height: 100%; padding: 0; margin: 0; border: 0;">
    <div dojoType="dijit.layout.AccordionContainer" layoutAlign="client">
        <!-- First accordion : Process -->
        <div dojoType="dijit.layout.AccordionPane" title="<%=GlobalContext.getMessageForWeb("Definition List", loggedUserLocale) %>">
            <div dojoType="dijit.Tree" id="definitionTree" model="continentModel" childrenAttr="children"  showRoot="false" openOnClick="false" >
                <script type="dojo/connect">
                        var menuEmpty = dijit.byId("folder_menu");
                
                        menuEmpty.bindDomNode(this.domNode);
                
                        dojo.connect(menuEmpty, "_openMyself", this, function(e) {
                            global_tn = dijit.getEnclosingWidget(e.target);

                            if (global_tn.item.id == "continentRoot") {
                                menuEmpty.getChildren().forEach(function(i) { 
                                    if ( 
                                        i.id=="dijit_MenuItem_0" ||
                                        i.id=="dijit_MenuItem_1" ||
                                        i.id=="dijit_MenuItem_2" ||
                                        i.id=="dijit_MenuItem_3" ||
                                        i.id=="dijit_MenuItem_4"  
                                    ) {
                                        i.setDisabled(true); 
                                    } else {
                                        i.setDisabled(false); 
                                    }
                                });
                            } else if (global_tn.item.obj == "folder") {
                                menuEmpty.getChildren().forEach(function(i) {
                                    if (i.id == "dijit_MenuItem_0") {
                                        i.setDisabled(true); 
                                    } else {
                                        i.setDisabled(false); 
                                    }
                                });                 
                            } else {
                                menuEmpty.getChildren().forEach(function(i) {
                                    if (i.id == "dijit_PopupMenuItem_0" || i.id == "dijit_PopupMenuItem_1") {
                                        i.setDisabled(true); 
                                    } else {
                                        i.setDisabled(false); 
                                    }
                                });
                            }
                        });
                    </script>
                    <script type="dojo/method" event="getIconClass" args="item, opened">
                        if (item.type == "root") {
                            ObjType = "definition";
                        } else if(item.obj == "folder") {
                            ObjType = "folder";
                        } else {
                            ObjType = item.obj;
                        }
           
                        if (ObjType == "folder") {
                            ObjType = opened ? "folderOpenIcon" : "folderClosedIcon";
                        } else {
                            ObjType += "Icon";
                        }

                        return ObjType;
                    </script>
                    <script type="dojo/method" event="onClick" args="item">
                        if (item.obj != "folder") {
                            viewObjectType(item.obj, item.id, item.parent);
                        }
                    </script>
                    <script type="dojo/method" event="onOpen" args="item, node">
                        getChildNodesForAjax(item);
                    </script>
            </div>
        </div>
        <!-- Second accordion : Instances -->
        <div dojoType="dijit.layout.AccordionPane" title="<%=GlobalContext.getMessageForWeb("Search Instance", loggedUserLocale) %>">
            <form name="searchForm" method="post" action="processInstanceListDetail.jsp" target="innerworkarea">
                <input type="hidden" name="initiatorId" />
                <input type="hidden" name="initiatorLoginName" />
                <input type="hidden" name="currentUserId" />
                <input type="hidden" name="currentUserLoginName" />
                <table border="0" width="100%" border="0" cellspacing="0" cellpadding="0">
                    <col span="1" width="50px" align="left">
                    <col span="1" width="10px">
                    <col span="1" width="*" align="left">

                    <tr height="30" >
                        <td bgcolor="f4f4f4">&nbsp;<font size="-2"><%=GlobalContext.getMessageForWeb("Status", loggedUserLocale) %></font></td>
                        <td></td>
                        <td><select name="status" size="1" class="black" >
                                <option value="All"><%=GlobalContext.getMessageForWeb("All", loggedUserLocale) %></option>
                                <option value="Running"><%=GlobalContext.getMessageForWeb("Running", loggedUserLocale) %></option>
                                <option value="Ready"><%=GlobalContext.getMessageForWeb("Ready", loggedUserLocale) %></option>
                                <option value="Completed"><%=GlobalContext.getMessageForWeb("Completed", loggedUserLocale) %></option>
                                <option value="Stopped"><%=GlobalContext.getMessageForWeb("Stopped", loggedUserLocale) %></option>
                                <option value="Skipped"><%=GlobalContext.getMessageForWeb("Skipped", loggedUserLocale) %></option>
                                <option value="Suspended"><%=GlobalContext.getMessageForWeb("Suspended", loggedUserLocale) %></option>
                                <option value="Failed"><%=GlobalContext.getMessageForWeb("Failed", loggedUserLocale) %></option>
                            </select></td>
                    </tr>
                    <tr>
                        <td class="dotline" colspan="3"></td>
                    </tr>
                    <!-- def id start-->
                    <tr height="30">
                        <td bgcolor="f4f4f4">&nbsp;<font size="-2"><%=GlobalContext.getMessageForWeb("instance_id", loggedUserLocale) %></font></td>
                        <td></td>
                        <td><input name="Instance" type="text"  class="gray" size="20" maxlength="20" value="" onBlur="validate_Number(this);"></td>
                    </tr>
                    <tr>
                        <td class="dotline" colspan="3"></td>
                    </tr>
                    <!-- def id 2009-08-04 -->
                    <tr height="30">
                        <td bgcolor="f4f4f4">&nbsp;<font size="-2"><%=GlobalContext.getMessageForWeb("Name", loggedUserLocale) %></font></td>
                        <td></td>
                        <td><input name="docTitle" type="text"  class="gray" size="20" maxlength="20" value=""></td>
                    </tr>
                    <tr>
                        <td class="dotline" colspan="3"></td>
                    </tr>
                    <!-- starter start -->
                    <tr height="30">
                        <td bgcolor="f4f4f4">&nbsp;<font size="-2"><%=GlobalContext.getMessageForWeb("Initiator", loggedUserLocale) %></font></td>
                        <td></td>
                        <td>
                            <input type='hidden' name='Initiator__which_is_xml_value' id="Initiator__which_is_xml_value" value="" size='1' >
                            <input type="hidden" name="Initiator" id="Initiator" size='20' value=""/>
                            <input type="text" name="Initiator_display" id="Initiator_display" size='20' value="" readonly="readonly"/>
                            <img name="Initiator" align="middle" onclick='searchPeopleObj(this,true)' src='<%=GlobalContext.WEB_CONTEXT_ROOT%>/processmanager/images/Toolbar-toblock.gif' style="cursor:pointer;" alt="Initiator Search" title="Initiator search" />
                        </td>
                    </tr>
                    <tr>
                        <td class="dotline" colspan="3"></td>
                    </tr>
                    <!-- starter 2009-08-04 -->
                    <!-- getter start -->
                    <tr height="30">
                        <td bgcolor="f4f4f4">&nbsp;<font size="-2"><%=GlobalContext.getMessageForWeb("current_participant", loggedUserLocale) %></font></td>
                        <td></td>
                        <td><input type='hidden' name='Nowperson__which_is_xml_value' id="Nowperson__which_is_xml_value" value="" size='1' >
                            <input type="hidden" name="Nowperson" id="Nowperson" size='20' value=""/>
                            <input type="text" name="Nowperson_display" id="Nowperson_display" size='20' value="" readonly="readonly"/>
                            <img name="Nowperson" align="middle" onclick='searchPeopleObj(this,true)'   src='<%=GlobalContext.WEB_CONTEXT_ROOT%>/processmanager/images/Toolbar-toblock.gif' style="cursor:pointer;" alt="now person Search" title="Now person search" /></td>
                    </tr>
                    <tr>
                        <td class="dotline" colspan="3"></td>
                    </tr>
                    <!-- getter 2009-08-04 -->
                    <tr height="30" >
                        <td bgcolor="f4f4f4">&nbsp;<font size="-2"><%=GlobalContext.getMessageForWeb("start_date", loggedUserLocale) %></font></td>
                        <td></td>
                        <td><table>
                                <tr>
                                    <td align="right" width="38" style="font-size:11px;"><%=GlobalContext.getMessageForWeb("From", loggedUserLocale) %>:</td>
                                    <td>
                                        <input type="text" name="init_start_date" id="init_start_date" size="12" value="" class="j_calendar"/>
                                        <!-- <img src="../images/icon_dayselect.gif" style="cursor:hand" name="init_start_date" onClick="showFullcalendar('month', this.name);" />-->
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right"  style="font-size:11px;"><%=GlobalContext.getMessageForWeb("To", loggedUserLocale) %>:</td>
                                    <td>
                                        <input type="text" name="init_end_date" id="init_end_date" size="12" value="" class="j_calendar" />
                                        <!--<img src="../images/icon_dayselect.gif" style="cursor:hand" name="init_end_date" onClick="showFullcalendar('month', this.name);" />-->
                                    </td>
                                </tr>
                            </table></td>
                    </tr>
                    <tr>
                        <td class="dotline" colspan="3"></td>
                    </tr>
                    <tr height="30">
                        <td bgcolor="f4f4f4">&nbsp;<font size="-2"><%=GlobalContext.getMessageForWeb("end_date", loggedUserLocale) %></font></td>
                        <td></td>
                        <td><table>
                                <tr>
                                    <td align="right"  width="38"  style="font-size:11px;"><%=GlobalContext.getMessageForWeb("From", loggedUserLocale) %>:</td>
                                    <td>
                                        <input type="text" name="complete_start_date" id="complete_start_date" size="12" value="" class="j_calendar" />
                                        <!--<img src="../images/icon_dayselect.gif" style="cursor:hand" name="complete_start_date" onClick="showFullcalendar('month', this.name);" /> -->
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right"  style="font-size:11px;"><%=GlobalContext.getMessageForWeb("To", loggedUserLocale) %>:</td>
                                    <td>
                                        <input type="text" name="complete_end_date" id="complete_end_date" size="12" value="" class="j_calendar" />
                                        <!--<img src="../images/icon_dayselect.gif" style="cursor:hand" name="complete_end_date" onClick="showFullcalendar('month', this.name);" /> -->
                                    </td>
                                </tr>
                            </table></td>
                    </tr>
                    <tr>
                        <td class="dotline" colspan="3"></td>
                    </tr>
                    <tr>
                        <td colspan="3"  height="10"></td>
                    </tr>
                    <tr>
                        <td colspan="3" align="right" ><img src="../images/Common/b_bigsearch.gif" onClick="search()" style="cursor: pointer;" alt="search"> <img src="../images/Common/b_refresh.gif" onClick="searchForm.reset()" style="cursor: pointer;" alt="reset"></td>
                    </tr>
                </table>
            </form>
        </div>
        <div dojoType="dijit.layout.AccordionPane" title="<%=GlobalContext.getMessageForWeb("Search Definition", loggedUserLocale) %>">
            <form name="formSearchDefinition" action="searchDefinition.jsp" target="innerworkarea" method="post">
                <table border="0" width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr height="30">
                        <td width="50" align="left" bgcolor="f4f4f4">&nbsp;<font size="-2"><%=GlobalContext.getMessageForWeb("TYPE", loggedUserLocale) %></font></td>
                        <td width="10"></td>
                        <td width="*" align="left">
                            <select name="inputObjType" id="inputObjType" style="width:100%;" >
                                <option value="all"><%=GlobalContext.getMessageForWeb("ALL", loggedUserLocale) %></option>
                                <option value="process"><%=GlobalContext.getMessageForWeb("PROCESS", loggedUserLocale) %></option>
                                <option value="form"><%=GlobalContext.getMessageForWeb("FORM", loggedUserLocale) %></option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="dotline" colspan="3"></td>
                    </tr>
                    <tr height="30">
                        <td width="50" align="left" bgcolor="f4f4f4">&nbsp;<font size="-2"><%=GlobalContext.getMessageForWeb("NAME", loggedUserLocale) %></font></td>
                        <td width="10"></td>
                        <td width="*" align="left"><input name="inputSearchWord" type="text" style="width:100%;" /></td>
                    </tr>
                    <tr>
                        <td class="dotline" colspan="3"></td>
                    </tr>
                    <tr height="30">
                        <td width="50" align="left" bgcolor="f4f4f4">&nbsp;<font size="-2"><%=GlobalContext.getMessageForWeb("ALIAS", loggedUserLocale) %></font></td>
                        <td width="10"></td>
                        <td width="*" align="left"><input name="inputSearchAlias" type="text" style="width:100%;" /></td>
                    </tr>
                    <tr>
                        <td class="dotline" colspan="3"></td>
                    </tr>
                    <tr>
                        <td colspan="3"  height="10"></td>
                    </tr>
                </table><table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td align="right"><img src="../images/Common/b_bigsearch.gif" onClick="document.formSearchDefinition.submit()" style="cursor: pointer;" alt="search" /> 
                        <img src="../images/Common/b_refresh.gif" onClick="document.formSearchDefinition.reset()" style="cursor: pointer;" alt="reset" /></td>
    </tr>
</table>

            </form>
        </div>
    </div>
</div>
<form name="newRuleDefinition" action="newRuleDefinition.jsp" method="get" target="innerworkarea">
    <input type="hidden" name="folderId">
</form>
<form name="newClassDefinition" action="newClassDefinition.jsp" method="get" target="innerworkarea">
    <input type="hidden" name="folderId">
</form>
<form name="newFormDefinition" action="newFormDefinitionUSFeditor.jsp" method="get" target="innerworkarea">
    <input type="hidden" name="folderId">
</form>
<form name="newDatabaseSet" action="../crud/CRUDSetIndex.jsp" method="get" target="innerworkarea">
    <input type="hidden" name="folderId">
</form>
<form name="viewProcessDefinition" action="viewProcessFlowChart.jsp" method="get" target="innerworkarea">
    <input type="hidden" name="processDefinition">
    <input type="hidden" name="folder">
</form>
<form name="viewRuleDefinition" action="viewRuleDefinition.jsp" method="get" target="innerworkarea">
    <input type="hidden" name="objectDefinitionId">
    <input type="hidden" name="folder">
</form>
<form name="viewClassDefinition" action="viewClassDefinition.jsp" method="get" target="innerworkarea">
    <input type="hidden" name="objectDefinitionId">
    <input type="hidden" name="folder">
</form>
<form name="viewCalcDefinition" action="viewCalcDefinition.jsp" method="get" target="innerworkarea">
    <input type="hidden" name="objectDefinitionId">
    <input type="hidden" name="folder">
</form>
<form name="viewFormDefinition" action="viewFormDefinitionUSFeditor.jsp" method="get" target="innerworkarea">
    <input type="hidden" name="objectDefinitionId">
    <input type="hidden" name="folder">
</form>
<form name="viewDatabaseSet" action="../crud/CRUDSetIndex.jsp" method="get" target="innerworkarea">
    <input type="hidden" name="objectDefinitionId">
    <input type="hidden" name="folder">
</form>
<form name="addFolderForm" action="addFolder.jsp" method="POST" >
    <input type="hidden" name="folderName">
    <input type="hidden" name="parentFolder">
</form>
<form name="toggleVisibleForm" action="toggleVisible.jsp" method="get" >
    <input type="hidden" name="definitionId">
    <input type="hidden" name="visible">
</form>
<form name="actionForm" action="renameProcessDefinition.jsp" method="POST">
    <input type="hidden" name="processDefinition">
    <input type="hidden" name="newName">
</form>
<form name="removeForm" action="removeFolder.jsp" method="POST">
    <input type="hidden" name="folder">
</form>
<form name="exportDef" action="exportDefinition.jsp" method="POST">
    <input type="hidden" name="processDefinition">
    <input type="hidden" name="filePath">
</form>
<form name="accessibility" action="../authority/authority.jsp" method="POST" target="innerworkarea">
    <input type="hidden" name="defId">
    <input type="hidden" name="defName">
</form>
</div>
