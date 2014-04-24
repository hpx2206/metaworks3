var org_uengine_jms_mw3_model_ProcessExploreContent = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;	
	
	this.objectDiv.css({position: 'relative', height: '100%'});
};

org_uengine_jms_mw3_model_ProcessExploreContent.prototype = {
		downloadPdf : function(){
			 var data = (new XMLSerializer).serializeToString(document.body.getElementsByTagName("svg")[0]);
			 var object = mw3.objects[this.objectId];
			 object.svgData=data;
			 object.downloadPdf();
		}
};