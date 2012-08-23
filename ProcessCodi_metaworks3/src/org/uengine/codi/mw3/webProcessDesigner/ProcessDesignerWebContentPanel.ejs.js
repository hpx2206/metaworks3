var org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	this.icanvas = null;
	
	var object = mw3.objects[this.objectId];
	
	var canvas = null , opengraphJSON;
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

    // load 에서 데이터가 넘어왔을 경우 데이터를 셋팅
	if( object.cell != null ){
		opengraphJSON = this.loadForJson(object.cell);
		
		if( opengraphJSON != null){
			this.icanvas.loadJSON(opengraphJSON);
		}
	}
}

org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.clear = function(){
	this.icanvas.RENDERER.clear();
}
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.jsonobject = function(){
//	var object = mw3.getObject(this.objectId);
//	object.jsonObj = this.icanvas.toJSON();
//	object.jsonObj = JSON.stringify(this.icanvas.toJSON());
	alert(JSON.stringify(this.icanvas.toJSON() ));
//	object.jsonObj = $.parseXML(this.icanvas.toXML());
}
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.loadForJson = function(cells){
	var opengraphJSON = { opengraph: {
										cell: []
									}};
	for(var i=0; i<cells.length; i++){
		var cellForOri = cells[i];
		var cell = {};
		cell['@id'] = cellForOri.id;
		cell['@shapeType'] = cellForOri.shapeType;
		cell['@shapeId'] = cellForOri.shapeId;
		cell['@x'] = cellForOri.x;
		cell['@y'] = cellForOri.y;
		cell['@width'] = cellForOri.width;
		cell['@height'] = cellForOri.height;
		cell['@style'] = cellForOri.style;
		if (cellForOri.from) {
			cell['@from'] = cellForOri.from;
		}
		if (cellForOri.to) {
			cell['@to'] = cellForOri.to;
		}
		if (cellForOri.fromedge) {
			cell['@fromEdge'] = cellForOri.fromedge;
		}
		if (cellForOri.toedge) {
			cell['@toEdge'] = cellForOri.toedge;
		}
		if (cellForOri.label) {
			cell['@label'] = cellForOri.label;
		}
		if (cellForOri.angle && cellForOri.angle !== 0) {
			cell['@angle'] = cellForOri.angle;
		}
		/* shape 와 item 이 확인이 안되서 일단 보류
		if (shape instanceof OG.shape.ImageShape) {
			cell['@value'] = shape.image;
		} else if (shape instanceof OG.shape.TextShape) {
			cell['@value'] = shape.text;
		} else if (shape instanceof OG.shape.EdgeShape) {
			vertices = geom.getVertices();
			from = vertices[0];
			to = vertices[vertices.length - 1];
			cell['@value'] = from + ',' + to;
		}
		if (item.data) {
			cell['@data'] = escape(OG.JSON.encode(item.data));
		}
		*/
		opengraphJSON.opengraph.cell.push(cell);
	}
	
	return opengraphJSON;
}
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.getValue = function(){
	var ogObj = eval(this.icanvas.toJSON().opengraph);
	var ogArr = ogObj.cell;
	var cellsForDwr = [];
	
	for(var i=0; i<ogArr.length; i++){
		var og = ogArr[i];
		var cellForDwr = {};
		for(var key in og){
			var newKey = "" + key;
			if(key.indexOf("@")==0){
				newKey = key.substring(1);
			}
			cellForDwr[newKey] = og[key];
		}
		
		cellsForDwr[cellsForDwr.length] = cellForDwr;
	}
	
	var object = mw3.objects[this.objectId];
	object.cell = cellsForDwr;
	
	return object;
};
