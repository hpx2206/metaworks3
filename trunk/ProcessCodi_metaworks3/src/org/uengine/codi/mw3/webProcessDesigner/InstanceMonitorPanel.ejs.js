var org_uengine_codi_mw3_webProcessDesigner_InstanceMonitorPanel = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
//	this.divId = mw3._getObjectDivId(this.objectId);
	var object = mw3.objects[this.objectId];
	var faceHelper = this;
	var canvas = null;
	
	canvas = new OG.Canvas('canvasView');
	
	// TODO 위쪽 세개를 false로 놓으면 화면에 그려지질 않는다.. 왜그럴까?
	canvas.initConfig({
        selectable      : false,
        dragSelectable  : false,
        movable         : false,
        resizable       : false,
        connectable     : false,
        selfConnectable : false,
        connectCloneable: false,
        connectRequired : false,
        labelEditable   : false,
        groupDropable   : false,
        collapsible     : false,
        enableHotKey    : false
    });
	
	canvas.onDrawShape(function (event, shapeElement) {
		faceHelper.addEvent(objectId, canvas, shapeElement);
    });
	
	if( object != null && object.graphString != null ){
		var canvassizeObject = canvas.loadJSON($.parseJSON(object.graphString));
		// 약간의 여유를 두기 위하여 30px 만큼 더해줌
		canvas.setCanvasSize([canvassizeObject.x2 + 30 , canvassizeObject.y2 + 30]);
	}
	if( object != null && object.cell != null ){
		var cells = object.cell;
		for(var i=0; i < cells.length; i++){
			var ele = $('#'+cells[i].id);
			if( cells[i].instStatus != null && cells[i].backgroundColor != null){
//				canvas.setShapeStyle(ele[0], {"stroke": cells[i].backgroundColor });
				canvas.setShapeStyle(ele[0], {"fill": cells[i].backgroundColor , "fill-opacity": 0.5});
			}
			var cellTracing = cells[i].tracingTag;
			if( cellTracing != null ){
				ele.attr("tracingTag",cellTracing);
			}
		}
	}
};
org_uengine_codi_mw3_webProcessDesigner_InstanceMonitorPanel.prototype = {
		addEvent : function(objectId, canvas, element){
			var shape = $(element).attr("_shape");
			if( shape == "GEOM"){
				$(element).unbind('click').bind({
					click: function (event) {
						var value = mw3.objects[objectId]; 
						value.tempTracingTag = $(this).attr('tracingTag');
						value.showActivityInfo();
					}
				});
			}
		}
};