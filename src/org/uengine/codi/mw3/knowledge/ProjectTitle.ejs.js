var org_uengine_codi_mw3_knowledge_ProjectTitle = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);

	this.object = mw3.objects[this.objectId];
	var faceHelper = this;
	
	if(this.object == null)
		return true;
	
	
	
	$('#warFile_'+this.objectId).hide();
	$('#sqlFile_'+this.objectId).hide();
	$('#svn_'+this.objectId).hide();
	
	$('input[type=radio]').click(function(){
		var value = mw3.objects[objectId];
		var check = $(this).val();
		if(check == '1'){
			value.fileType = "war";
//			value.call();
			$('#warFile_'+objectId).show();
			$('#sqlFile_'+objectId).show();
			$('#svn_'+objectId).hide();
		}else if(check == '2'){
			value.fileType = "svn";
//			value.call();
			$('#warFile_'+objectId).hide();
			$('#sqlFile_'+objectId).hide();
			$('#svn_'+objectId).show();	
		}
			
	
	})

};

	
	
		

