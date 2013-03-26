var org_uengine_codi_mw3_marketplace_model_AquisitionApprovalForm = function(objectId, className) {
	
	this.objectId = objectId;
	this.className = className;

	
	var object = mw3.objects[this.objectId];
	
	var divId = "objDiv_" + objectId;
	var isApproval = object.approval;
	console.log(isApproval);
	
	$("#"+ divId).click(function(){
		if(!isApproval){
			
			$("#textArea_"+ objectId).css('display', 'none');
			
			isApproval = true;
			
		}else{
			
			$("#textArea_"+ objectId).css('display', 'block');
			
			isApproval = false;
		}
		
	});
	
	
};

