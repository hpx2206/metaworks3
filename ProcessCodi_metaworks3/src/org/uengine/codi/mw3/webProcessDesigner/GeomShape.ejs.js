var org_uengine_codi_mw3_webProcessDesigner_GeomShape = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;

	var object = mw3.objects[this.objectId];
	
	var element = document.getElementById(object.id);
	
	var canvasObject;
	if( object != null && object.viewType != null && "blockView" == object.viewType ){
		canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.InstanceMonitorPanel');
	}else{
		canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerWebContentPanel');
	}
	
	var canvasObjectFaceHelper = mw3.getFaceHelper(canvasObject.__objectId);
	var canvas = canvasObjectFaceHelper.icanvas;
	
	if( object && object.drawByObject){
		var initText = object.label;
		var shape = eval('new ' + object.shapeId + '(\''+initText+'\')');
		element = canvas.drawShape([
            object.x, object.y 
            ], 
            shape, [parseInt(object.width, 10), parseInt(object.height, 10)] , {} , object.id);
		
		$(element).attr('title', object.tooltip);
		$(element).hover(function(event, ui) {
			console.log('in');
			  $('body').append('<div id=\"shape_tooltip\" style=\"z-index: 1000; position: absolute; width: 200px; height: 30px; background-color: lightgray; text-align: center; padding: 15px 0px 0px; top: ' + event.pageY + 'px; left: ' + event.pageX + 'px\">' + object.tooltip + '</div>');
		}, function(){
			console.log('out');
			$('#shape_tooltip').remove();
		});
		//$(element).tooltip();
		
		
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