var org_uengine_codi_mw3_webProcessDesigner_ProcessVariablePanel = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	
	this.clickedVariable = '';
	$('.variableTr').dblclick(function(event) {
		var faceHelper = mw3.faceHelpers[objectId];
		if( faceHelper.clickedVariable != '' && faceHelper.clickedVariable != null && faceHelper.clickedVariable != 'undefined'){
			var dblclickedVarable = {
					__className : 'org.uengine.kernel.ProcessVariable',
					name : faceHelper.clickedVariable
			};
			var object = mw3.objects[objectId];
			object.editedProcessVariable = dblclickedVarable;
			object.editVariable();
		}
	});
	
};

org_uengine_codi_mw3_webProcessDesigner_ProcessVariablePanel.prototype = {
		clickedItem : function(variableName){
			this.clickedVariable = variableName;
			
			$('.variablePanel_td').css('background','none');
			$('.'+variableName+'_' +this.objectId).find('.variablePanel_td').css('background','#e5e5e5');
		},
		removeItem : function(){
			if( this.clickedVariable != '' ){
				var deleteVarable = {
						__className : 'org.uengine.kernel.ProcessVariable',
						name : this.clickedVariable
				};
				var object = mw3.objects[this.objectId];
				object.editedProcessVariable = deleteVarable;
				object.removeVariable();
				
			}else{
				alert('선택된 변수가 없습니다.');
			}
		}
};