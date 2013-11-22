var org_uengine_codi_mw3_webProcessDesigner_RoleSettingPanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.object = mw3.objects[this.objectId];
	
	if(this.object == null)
		return true;
	
	this.selectedRoleCode = null;
	this.selectedGroupCode = null;
	var faceHelperObj = this;
	
	var groupTreeObj = this.object.groupTree;
	var treeObjectDiv = $('#' + mw3._getObjectDivId(groupTreeObj.__objectId));
	$(treeObjectDiv).bind('change', function(event, objectId){
		var childObj = mw3.objects[objectId];
		$('input[name=selectedGroup]').val(childObj.name);
		faceHelperObj.selectedGroupCode = childObj.id;
	});

	var selectObjectDiv = $('#' + mw3._getObjectDivId(this.object.roleSelect.__objectId));
	$(selectObjectDiv).find('select').bind('change', function(event, objectId){
		$('input[name=selectedRole]').val($(this).find('option:selected').text());
		faceHelperObj.selectedRoleCode = $(this).find('option:selected').val();
	});
	
	this.selectedRoleCode = this.object.selectedRoleCode;
	this.selectedGroupCode = this.object.selectedGroupCode;
	faceHelperObj.init();
};

org_uengine_codi_mw3_webProcessDesigner_RoleSettingPanel.prototype.init = function(){
	if( this.selectedRoleCode != null &&  this.selectedGroupCode != null){
		
	}else{
		$("#group_"+this.objectId).hide();
		$('input[name=selectedGroup]').hide();
		$("#role_"+this.objectId).hide();
		$('input[name=selectedRole]').hide();
	}
};
org_uengine_codi_mw3_webProcessDesigner_RoleSettingPanel.prototype.radioChecked = function(){
	var groupSelect = $("input:radio[name=groupSelectRadio]:checked").val();
	if( groupSelect == '0'){
		// All
		$("#group_"+this.objectId).hide();
		$('input[name=selectedGroup]').val("");
		$('input[name=selectedGroup]').hide();
	}else if( groupSelect == '1'){
		// The specified group
		$("#group_"+this.objectId).show();
		$('input[name=selectedGroup]').show();
	}else if( groupSelect == '2'){
		// The group that
		$("#group_"+this.objectId).hide();
		$('input[name=selectedGroup]').val("");
		$('input[name=selectedGroup]').hide();
	}
	
	var roleSelect = $("input:radio[name=roleSelectRadio]:checked").val();
	if( roleSelect == '0'){
		// All
		$("#role_"+this.objectId).hide();
		$('input[name=selectedRole]').val("");
		$('input[name=selectedRole]').hide();
	}else if( roleSelect == '1'){
		// The specified role
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
//org_uengine_codi_mw3_webProcessDesigner_RoleSettingPanel.prototype.destroy = function(){
//	
//};