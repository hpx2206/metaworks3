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
        selectable      : true,
        dragSelectable  : true,
        movable         : true,
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
		canvas.loadJSON($.parseJSON(object.graphString));
	}
	if( object != null && object.cell != null ){
		var cells = object.cell;
		for(var i=0; i < cells.length; i++){
			if( cells[i].instStatus != null && cells[i].backgroundColor != null){
				var ele = $('#'+cells[i].id);
//				canvas.setShapeStyle(ele[0], {"stroke": cells[i].backgroundColor });
				canvas.setShapeStyle(ele[0], {"fill": cells[i].backgroundColor , "fill-opacity": 0.5});
			}
		}
	}
};
org_uengine_codi_mw3_webProcessDesigner_InstanceMonitorPanel.prototype = {
		addEvent : function(objectId, canvas, element){
			$(element).unbind('click').bind({
				click: function (event) {
					var value = mw3.objects[objectId]; 
					value.tempElementId = $(this).attr('id');
					value.tempElementName = $(this).children('[id$=_LABEL]').text();
					value.gateCondition();
				}
			});
		}
};