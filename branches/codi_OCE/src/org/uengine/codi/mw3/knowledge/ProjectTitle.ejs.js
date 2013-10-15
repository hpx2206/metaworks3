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
//	$('#' + sqlFileDivId ).hide();
//	
	var warFileDivId = mw3._getObjectDivId(object.warFile.__objectId);
//	$('#' + warFileDivId ).hide();
	$('#' + warFileDivId ).parentsUntil('tr').parent().hide();
	
//	$('#warFile_'+this.objectId).hide();
//	$('#sqlFile_'+this.objectId).hide();
//	$('#svn_'+this.objectId).hide();
	
	$('input[type=radio]').click(function(){
		var value = mw3.objects[objectId];
		var check = $(this).val();
		if(check == '1'){
			value.fileType = "war";
//			value.call();
//			$('#warFile_'+objectId).show();
//			$('#sqlFile_'+objectId).show();
//			$('#svn_'+objectId).show();
			$('#' + sqlFileDivId ).parentsUntil('tr').parent().show();
			$('#' + warFileDivId ).parentsUntil('tr').parent().show();
//			$('#' + sqlFileDivId ).show();
//			$('#' + warFileDivId ).show();
		}else if(check == '2'){
			value.fileType = "svn";
//			$('#' + sqlFileDivId ).hide();
//			$('#' + warFileDivId ).hide();
			$('#' + sqlFileDivId ).parentsUntil('tr').parent().hide();
			$('#' + warFileDivId ).parentsUntil('tr').parent().hide();
//			value.call();
//			$('#warFile_'+objectId).hide();
//			$('#sqlFile_'+objectId).hide();
//			$('#svn_'+objectId).show();	
		}
			
	
	})

};

	
	
		

