var org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	this.icanvas = null;
	
	var object = mw3.objects[this.objectId];
	
	var canvas = null;
	// layout
    //$('body').layout({ west__size: 200 });
    $("#accordion").accordion({
        fillSpace : false,
        autoHeight: false
    });

    // Canvas
    OG.common.Constants.CANVAS_BACKGROUND = "#ffffff";
    canvas = new OG.Canvas('canvas');
    this.icanvas = canvas;
    // Shape drag & drop
    $(".icon_shape").draggable({
        start   : function () {
            $('#canvas').data('DRAG_SHAPE_ID', $(this).attr('_shape_id'));
        },
        helper  : 'clone',
        appendTo: "#canvas"
    });
    $("#canvas").droppable({
        drop: function (event, ui) {
            var shapeId = $('#canvas').data('DRAG_SHAPE_ID');
            if (shapeId) {
                var shape = eval('new ' + shapeId + '()');
                canvas.drawShape([event.pageX - $('#canvas')[0].offsetLeft + 5, event.pageY - $('#canvas')[0].offsetTop + 5], shape, [30, 30]);
                this.icanvas = canvas;
                $('#canvas').removeData('DRAG_SHAPE_ID');
            }
        }
    });
    
}

org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.clear = function(){
	this.icanvas.RENDERER.clear();
}
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.jsonobject = function(){
	var object = mw3.getObject(this.objectId);
	object.jsonObj = this.icanvas.toJSON();

	object.getJSONObj();
}
