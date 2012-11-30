var org_uengine_codi_mw3_webProcessDesigner_RoleSettingPanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.object = mw3.objects[this.objectId];
	this.selectedGroupId = null;
	this.selectedGroupName = null;
	var faceHelperObj = this;
	
	var groupTreeObj = this.object.groupTree;
	var treeObjectDiv = $('#' + mw3._getObjectDivId(groupTreeObj.__objectId));
	$(treeObjectDiv).bind('change', function(event, objectId){
		var childObj = mw3.objects[objectId];
		$('input[name=selectedGroup]').val(childObj.name);
		faceHelperObj.selectedGroupId = childObj.id;
		faceHelperObj.selectedGroupName = childObj.name;
	});
	
};

org_uengine_codi_mw3_webProcessDesigner_RoleSettingPanel.prototype.radioChecked = function(){
	var groupSelect = $("input:radio[name=groupSelectRadio]:checked").val();
	if( groupSelect == '0'){
		$("#group_"+this.objectId).hide();
		$('input[name=selectedGroup]').hide();
		$('input[name=selectedGroup]').val("");
	}else if( groupSelect == '1'){
		$("#group_"+this.objectId).show();
		$('input[name=selectedGroup]').show();
	}else if( groupSelect == '2'){
		$("#group_"+this.objectId).hide();
		$('input[name=selectedGroup]').hide();
		$('input[name=selectedGroup]').val("");
	}
	
	var roleSelect = $("input:radio[name=roleSelectRadio]:checked").val();
	if( roleSelect == '0'){
		$("#role_"+this.objectId).hide();
		$('input[name=selectedRole]').hide();
		$('input[name=selectedRole]').val("");
	}else if( roleSelect == '1'){
		$("#role_"+this.objectId).show();
		$('input[name=selectedRole]').show();
	}
};
org_uengine_codi_mw3_webProcessDesigner_RoleSettingPanel.prototype.saveForm = function(){
	var groupSelect = $("input:radio[name=groupSelect]:checked").val();
	var roleSelect = $("input:radio[name=roleSelect]:checked").val();
	if( groupSelect == undefined || groupSelect == '' ){
		alert('please choose group radio box');
		return;
	}
	if( roleSelect == undefined || roleSelect == '' ){
		alert('please choose role radio box');
		return;
	}
};