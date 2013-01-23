var org_uengine_codi_mw3_webProcessDesigner_LineShape = function(objectId, className){
	this.objectId = objectId;
	this.className = className;

	var object = mw3.objects[this.objectId];
	
	var element = document.getElementById(object.id);
	
	var canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerWebContentPanel');
	var canvasObjectFaceHelper = mw3.getFaceHelper(canvasObject.__objectId);
	var canvas = canvasObjectFaceHelper.icanvas;
	
	canvas.drawLabel(element, object.label);
//	if(object.customData != null && object.customData != undefined){
//		var customData = [];
//		var jsonArray = eval(object.customData);
//		for(i in jsonArray){
//		    customData.push(jsonArray[i]);
//		}
//		canvas.setCustomData(element, customData);
//	}
	canvasObject.conditionMap[object.id] = object.lineCondition;
};