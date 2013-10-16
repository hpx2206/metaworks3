var org_uengine_codi_mw3_knowledge_ProjectTitle = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);

	var object = mw3.objects[this.objectId];
	var faceHelper = this;
	
	if(object == null)
		return true;
//	
	var sqlFileDivId = mw3._getObjectDivId(object.sqlFile.__objectId);
	$('#' + sqlFileDivId ).parentsUntil('tr').parent().hide();
//	
	var warFileDivId = mw3._getObjectDivId(object.warFile.__objectId);
	$('#' + warFileDivId ).parentsUntil('tr').parent().hide();
	if(object.metaworksContext.when == mw3.WHEN_NEW){
		$("input[type=radio]:radio[value='2']").attr("checked",true);
		var value = mw3.objects[objectId];
		value.fileType = "none";
//		$('#' + sqlFileDivId ).parentsUntil('tr').parent().show();
//		$('#' + warFileDivId ).parentsUntil('tr').parent().show();
		
	}else{
		var value = mw3.objects[objectId];
		value.fileType = "war";
		$('#' + sqlFileDivId ).parentsUntil('tr').parent().show();
		$('#' + warFileDivId ).parentsUntil('tr').parent().show();
	}
	$('input[type=radio]').click(function(){
		var value = mw3.objects[objectId];
		var check = $(this).val();
		if(check == '1'){
			value.fileType = "war";
			$('#' + sqlFileDivId ).parentsUntil('tr').parent().show();
			$('#' + warFileDivId ).parentsUntil('tr').parent().show();
		}else if(check == '2'){
			$('#' + sqlFileDivId ).parentsUntil('tr').parent().hide();
			$('#' + warFileDivId ).parentsUntil('tr').parent().hide();
		
		}
		
		
			
	
	})

};

	
	
		

