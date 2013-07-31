var org_uengine_codi_mw3_webProcessDesigner_LineShape = function(objectId, className){
	this.objectId = objectId;
	this.className = className;

	var object = mw3.objects[this.objectId];
	
	var element = null;
	if( object != null ){
		element = document.getElementById(object.id);
	}
	var canvasObject;
	if( object != null && object.viewType != null && "blockView" == object.viewType ){
		canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.InstanceMonitorPanel');
	}else{
		canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContentPanel');
	}
	
	var canvasObjectFaceHelper = mw3.getFaceHelper(canvasObject.__objectId);
	var canvas = canvasObjectFaceHelper.icanvas;
	
	if( object && object.drawByObject){
		var elementFrom = document.getElementById( object.from );
		var elementTo = document.getElementById( object.to );
		if( elementFrom && elementTo ){
			var edgeEle = canvas.connect(elementFrom, elementTo);
			canvas.drawLabel(edgeEle, object.label);
		}
	}
	if( element ){
		canvas.drawLabel(element, object.label);
		canvasObject.conditionMap[object.id] = object.lineCondition;
	}
};