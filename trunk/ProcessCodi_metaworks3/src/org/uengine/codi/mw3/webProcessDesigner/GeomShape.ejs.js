var org_uengine_codi_mw3_webProcessDesigner_GeomShape = function(objectId, className){
	this.objectId = objectId;
	this.className = className;

	var object = mw3.objects[this.objectId];
	
	var element = document.getElementById(object.id);
	
	var canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerWebContentPanel');
	var canvasObjectFaceHelper = mw3.getFaceHelper(canvasObject.__objectId);
	var canvas = canvasObjectFaceHelper.icanvas;
	
	if( object && object.drawByObject){
		console.log('geomShape 호출!!!! obectID = ' + object.id);
		console.log('geomShape 호출!!!! label = ' + object.label);
		var initText = object.label;
		var shape = eval('new ' + object.shapeId + '(\''+initText+'\')');
		element = canvas.drawShape([
            object.x, object.y 
            ],
                shape, [parseInt(object.width, 10), parseInt(object.height, 10)] , {} , object.id);
	}
	
//	canvas.drawLabel(element, object.label);
	if(object.data != null && object.data != undefined){
//		var customData = [];
//		var jsonArray = eval(object.data);
//		for(i in jsonArray){
//		    customData.push(jsonArray[i]);
//		}
//		canvas.setCustomData(element, customData);
		canvas.setCustomData(element, object.data);
	}
	
};