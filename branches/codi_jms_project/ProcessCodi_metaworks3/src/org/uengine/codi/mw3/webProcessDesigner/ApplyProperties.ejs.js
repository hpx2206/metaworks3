var org_uengine_codi_mw3_webProcessDesigner_ApplyProperties = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.object = mw3.objects[this.objectId];
	var object = this.object;
//	console.log('org_uengine_codi_mw3_webProcessDesigner_ApplyProperties');
//	console.log($('#' + this.object.id));
	
	var canvasObject;
	if( object != null && object.viewType != null && "blockView" == object.viewType ){
		canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.InstanceMonitorPanel');
	}else	if( object != null && object.viewType != null && ("definitionView" == object.viewType || "definitionEditor" == object.viewType  || "definitionDiff" == object.viewType)){
		if( object.editorId ){
			canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessViewer@'+object.editorId);
		}else{
			canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessViewer');
		}
	}else{
		if( object.editorId ){
			canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContentPanel@'+object.editorId);
		}else{
			canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContentPanel');
		}
	}
	var canvasObjectFaceHelper = mw3.getFaceHelper(canvasObject.__objectId);
	var canvas = canvasObjectFaceHelper.icanvas;
	
	var contentValue = this.object.content;
	var element = document.getElementById(this.object.id);
	if(contentValue && contentValue.__className=="org.uengine.kernel.Role"){
//		canvasObject.roleMap[this.object.id] = contentValue;
		canvas.drawLabel(element, contentValue.displayName.text);
//	}else if(contentValue && contentValue.__className=="org.uengine.kernel.HumanActivity"){
	}else if(contentValue && contentValue.activityView && contentValue.activityView.classType == 'Activity'){
		canvas.drawLabel(element, contentValue.description.text);
	}
	
	$('#' + this.object.id).data('activity', contentValue);
	$('#' + this.object.id).trigger('apply');
};