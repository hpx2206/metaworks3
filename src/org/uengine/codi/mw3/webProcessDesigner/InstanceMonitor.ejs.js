var org_uengine_codi_mw3_webProcessDesigner_InstanceMonitor = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
//	this.divId = mw3._getObjectDivId(this.objectId);
	var object = mw3.objects[this.objectId];
	
	if(object == null)
		return true;
	
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	this.divObj = $('#' + this.divId);
	this.icanvas = null;
	this.canvasDivObj = $('#canvasView_'+objectId);
	
	if(object){
		if(mw3.importScript('scripts/opengraph/OpenGraph-0.1-SNAPSHOT.js')){
			mw3.importScript('scripts/jquery/jquery.contextMenu.js');
			mw3.importStyle('style/jquery/jquery.contextMenu.css');
			mw3.importStyle('dwr/metaworks/org/uengine/codi/mw3/model/PureWebProcessDesigner.ejs.css');
		}
	}
	
	var faceHelper = this;
	var canvas = null;
	
	canvas = new OG.Canvas('canvasView_'+objectId);
	this.icanvas = canvas;
	canvas._CONFIG.DEFAULT_STYLE.EDGE["edge-type"] = "bezier";
	// TODO 위쪽 세개를 false로 놓으면 화면에 그려지질 않는다.. 왜그럴까?
	canvas.initConfig({
        selectable      : true,
        dragSelectable  : true,
        movable         : false,
        resizable       : false,
        connectable     : false,
        selfConnectable : false,
        connectCloneable: false,
        connectRequired : false,
        labelEditable   : false,
        groupDropable   : false,
        collapsible     : false,
        enableHotKey    : false,
        enableContextMenu : false
    });
	
	canvas.onDrawShape(function (event, shapeElement) {
		faceHelper.addEvent(objectId, canvas, shapeElement);
    });
	
	var minX = Number.MAX_VALUE, minY = Number.MAX_VALUE, maxX = Number.MIN_VALUE, maxY = Number.MIN_VALUE;
	if( object != null && object.graphString != null ){
		var canvassizeObject = canvas.loadJSON($.parseJSON(object.graphString));
		// 약간의 여유를 두기 위하여 30px 만큼 더해줌
		maxX = canvassizeObject.x2 + 30;
		maxY = canvassizeObject.y2 + 30;
		this.canvasDivObj.width(maxX + 30);
		this.canvasDivObj.height(maxY + 30);
		canvas.setCanvasSize([maxX , maxY]);
	}else{
		if( object != null && object.cell != null ){
			var cells = object.cell;
			// 도형을 모두 그린후에 선을 그린다
			for(var i=0; i < cells.length; i++){
				if( cells[i].drawByObject && cells[i].shapeType != 'EDGE' ){
					
					var x = parseInt(cells[i].x, 10);
					var y = parseInt(cells[i].y, 10);
					var width = parseInt(cells[i].width, 10);
					var height = parseInt(cells[i].height, 10);
					
					minX = (minX > (x - width / 2)) ? (x - width / 2) : minX;
					minY = (minY > (y - height / 2)) ? (y - height / 2) : minY;
					maxX = (maxX < (x + width / 2)) ? (x + width / 2) : maxX;
					maxY = (maxY < (y + height / 2)) ? (y + height / 2) : maxY;
				}
			}
			this.canvasDivObj.width(maxX + 30);
			this.canvasDivObj.height(maxY + 30);
			canvas.setCanvasSize([maxX , maxY]);
		}
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
org_uengine_codi_mw3_webProcessDesigner_InstanceMonitor.prototype = {
		loaded : function(){
			var object = mw3.objects[this.objectId];
			if( object != null && object.cell != null ){
				if(object.graphString == null ){
					var graphJson = this.icanvas.toJSON();
					object.graphString = JSON.stringify(graphJson);
				}
			}
			/*
			var object = mw3.objects[this.objectId];
			if( object != null && object.cell != null ){
				var cells = object.cell;
				for(var i=0; i < cells.length; i++){
					if( cells[i].drawByObject && cells[i].shapeType != 'EDGE' ){
						var html = mw3.locateObject(cells[i]);
						this.canvasDivObj.append(html);
					}
				}
				for( i=0; i < cells.length; i++){
					if( cells[i].drawByObject && cells[i].shapeType == 'EDGE' ){
						var html = mw3.locateObject(cells[i]);
						this.canvasDivObj.append(html);
					}
				}
				if(object.graphString == null ){
					var graphJson = this.icanvas.toJSON();
					object.graphString = JSON.stringify(graphJson);
				}
			}
			*/
		},
		addEvent : function(objectId, canvas, element){
			var shape = $(element).attr("_shape");
			if( shape == "GEOM"){
				$(element).unbind('click').bind({
					click: function (event) {
						var value = mw3.objects[objectId]; 
						value.tempTracingTag = $(this).attr('tracingTag');
						//value.showActivityInfo();
					}
				});
			}
		}
};