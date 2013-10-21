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
	if(object.metaworksContext.when == mw3.WHEN_NEW){
		$("input[type=radio]:radio[value='1']").attr("checked",true);
		var value = mw3.objects[objectId];
		value.fileType = "svn";
	}
	
	$('input[type=radio]').click(function(){
		var value = mw3.objects[objectId];
		var check = $(this).val();
		if(check == '1'){
			value.fileType = "war";
		}else if(check == '2'){
			value.fileType = "svn";
		}
	});

};
