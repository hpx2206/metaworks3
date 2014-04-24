var com_defaultcompany_organization_DefaultCompanyRoleResolutionContextInputter = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	var objectDiv = $('#' + this.objectDivId);
	
	objectDiv.find('input:button').click(function(){
		var buttonId = $(this).attr('id');
		if( buttonId != null && buttonId.length > 7){ // button_{radioname}
			var buttonObjectId = buttonId.substring(7);
			var buttonDivId = mw3._getObjectDivId(buttonObjectId);
			var radioName = $('#' + buttonDivId).attr('name');
			if( radioName == 'userSelector') {
				objectDiv.find('input:radio[name=userContext_'+objectId+'][value=0]').attr("checked", true);
				objectDiv.find('input:radio[name=groupContext_'+objectId+']').attr("checked", false);
				objectDiv.find('input:radio[name=roleContext_'+objectId+']').attr("checked", false);
			}else if( radioName == 'groupSelector' ){
				objectDiv.find('input:radio[name=userContext_'+objectId+'][value=0]').attr("checked", false);
				objectDiv.find('input:radio[name=groupContext_'+objectId+'][value=2]').attr("checked", true);
			}else if( radioName == 'roleSelector' ){
				objectDiv.find('input:radio[name=userContext_'+objectId+'][value=0]').attr("checked", false);
				objectDiv.find('input:radio[name=roleContext_'+objectId+'][value=4]').attr("checked", true);
			}
		}
    });
	objectDiv.find('input:radio[name=userContext_'+objectId+']').click(function(){
		objectDiv.find('input:radio[name=groupContext_'+objectId+']').attr("checked", false);
        objectDiv.find('input:radio[name=roleContext_'+objectId+']').attr("checked", false);
	});	
	objectDiv.find('input:radio[name=groupContext_'+objectId+']').click(function(){
		objectDiv.find('input:radio[name=userContext_'+objectId+']').attr("checked", false);
	});	
	objectDiv.find('input:radio[name=roleContext_'+objectId+']').click(function(){
		objectDiv.find('input:radio[name=userContext_'+objectId+']').attr("checked", false);
	});	
};
com_defaultcompany_organization_DefaultCompanyRoleResolutionContextInputter.prototype = {
	getValue : function(){
		var object = mw3.objects[this.objectId];
		var returnFlag = true;
		$('#' + this.objectDivId).find('input:radio:checked').each(function(index){
			var assignType = $(this).val();
			if( assignType == '0'){
				if( object.userSelector.target == null ){
                    mw3.alert('유저를 선택해 주세요');					
					returnFlag = false;
				}else{
					object.groupSelector.name = null;
					object.groupSelector.target = null;
					object.roleSelector.name = null;
					object.roleSelector.target = null;
					object.groupId = null;
					object.roleId = null;
					object.userId = object.userSelector.target.userId;
					object.userName = object.userSelector.target.name;
				}
			}else if( assignType == '2'){
				if( object.groupSelector.target == null ){
                    mw3.alert('그룹을 선택해 주세요');              
					returnFlag = false;     
                }else{
					object.userSelector.name = null;
					object.userSelector.target == null
					object.userId = null;
					object.userName = null;
                    object.groupId = object.groupSelector.target.partCode;
					object.groupName = object.groupSelector.target.partName;
                }
			}else if( assignType == '4'){
				if( object.roleSelector.target == null ){
                    mw3.alert('역할을 선택해 주세요');                   
					returnFlag = false;
                }else{
					object.userSelector.name = null;
					object.userSelector.target == null
                    object.userId = null;
					object.userName = null;
                    object.roleId = object.roleSelector.target.roleCode;
					object.roleName = object.roleSelector.target.roleName;
                }
			}else if( assignType == 'groupAll'){
				object.userSelector.name = null;
                object.userSelector.target == null
                object.userId = null;
                object.userName = null;
					
				object.groupSelector.name = null;
				object.groupSelector.target = null;
				object.groupId = null;
				object.groupName = null;
			}else if( assignType == 'roleAll'){
				object.userSelector.name = null;
                object.userSelector.target == null
                object.userId = null;
                object.userName = null;
				
				object.roleSelector.name = null;
				object.roleSelector.target = null;
				object.roleId = null;
				object.roleName = null;
			}
		});
		if( !returnFlag ){
			object.displayName = 'error';
		}
		return object;
	}
};