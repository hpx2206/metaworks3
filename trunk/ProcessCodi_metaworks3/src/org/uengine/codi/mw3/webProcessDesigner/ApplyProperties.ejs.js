var org_uengine_codi_mw3_webProcessDesigner_ApplyProperties = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.object = mw3.objects[this.objectId];
	
//	console.log('org_uengine_codi_mw3_webProcessDesigner_ApplyProperties');
//	console.log($('#' + this.object.id));
	
	var canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerWebContentPanel');
	var canvasObjectFaceHelper = mw3.getFaceHelper(canvasObject.__objectId);
	var canvas = canvasObjectFaceHelper.icanvas;
//	console.log(this.object.content);
	
	var contentValue = this.object.content;
	var element = document.getElementById(this.object.id);
	
	if(contentValue && contentValue.__className=="org.uengine.kernel.Role"){
//		canvasObject.roleMap[this.object.id] = contentValue;
		canvas.drawLabel(element, contentValue.displayName.text);
	}else if(contentValue && contentValue.__className=="org.uengine.kernel.HumanActivity"){
		// TODO 엑티비티별로..??? 이름주기
		var wfText = $(element).children('[id$=_LABEL]').text();
		wfText = contentValue.description.text + wfText ;
		canvas.drawLabel(element, wfText);
	}
	
	$('#' + this.object.id).data('activity', contentValue);
	$('#' + this.object.id).trigger('apply');
};