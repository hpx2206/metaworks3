var org_uengine_codi_mw3_webProcessDesigner_ApplyProperties = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.object = mw3.objects[this.objectId];
	var object = this.object;
	
	var canvasObject;
	if( object != null && object.viewType != null && "blockView" == object.viewType ){
		canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.InstanceMonitorPanel');
	}else	if( object != null && object.viewType != null && ("definitionView" == object.viewType || "definitionEditor" == object.viewType  || "definitionDiffEdit" == object.viewType  || "definitionDiffView" == object.viewType)){
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
		canvas.drawLabel(element, contentValue.displayName.text);
		$('#' + this.object.id).data('role', contentValue);
	}else if(contentValue && contentValue.__className=="org.uengine.kernel.ValueChain"){
		canvas.drawLabel(element, contentValue.name.text);
		$('#' + this.object.id).data('valuechain', contentValue);
	}else{
		// activity
		canvas.drawLabel(element, contentValue.description.text);
		$('#' + this.object.id).data('activity', contentValue);
	}
	
	$('#' + this.object.id).trigger('apply');
};