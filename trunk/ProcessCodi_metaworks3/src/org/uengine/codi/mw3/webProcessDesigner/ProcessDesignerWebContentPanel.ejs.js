var org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	var object = mw3.objects[this.objectId];
	
	var canvas = null;
	$('#objDiv_' + this.objectId).css('height','100%')
    // Canvas
    OG.common.Constants.CANVAS_BACKGROUND = "#fff";
    OG.Constants.ENABLE_CANVAS_OFFSET = true; // Layout 사용하지 않을 경우 true 로 지정
    
    canvas = new OG.Canvas('canvas');
    this.icanvas = canvas;
    // Shape drag & drop
    $(".icon_shape").draggable({
        start   : function () {
        	$('#canvas').data('DRAG_SHAPE', {
                '_shape_type': $(this).attr('_shape_type'),
                '_shape_id'  : $(this).attr('_shape_id'),
                '_width'     : $(this).attr('_width'),
                '_height'    : $(this).attr('_height')
            });
        },
        helper  : 'clone',
        appendTo: "#canvas"
    });
    $("#canvas").droppable({
        drop: function (event, ui) {
        	var shapeInfo = $('#canvas').data('DRAG_SHAPE'), shape, element;
            if (shapeInfo) {
                if (shapeInfo._shape_type === 'EDGE') {
                    shape = eval('new ' + shapeInfo._shape_id + '()');
                    element = canvas.drawShape(null, shape, null);
                    canvas.RENDERER.move(element, [
                        event.pageX - $('#canvas')[0].offsetLeft + $('#canvas')[0].scrollLeft - $('#canvas').offsetParent().offset().left,
                        event.pageY - $('#canvas')[0].offsetTop + + $('#canvas')[0].scrollTop	- $('#canvas').offsetParent().offset().top ]);
                } else {
                	// 초기 텍스트 값 셋팅
                	var initText = "";
                	if( shapeInfo._shape_id == 'OG.shape.bpmn.E_Start' ){
                		initText = "start";
                	}else if(shapeInfo._shape_id == 'OG.shape.bpmn.E_End' ){
                		initText = "end";
                	}
                    shape = eval('new ' + shapeInfo._shape_id + '(\''+initText+'\')');
                    element = canvas.drawShape([
                        event.pageX - $('#canvas')[0].offsetLeft + $('#canvas')[0].scrollLeft	- $('#canvas').offsetParent().offset().left,
                        event.pageY - $('#canvas')[0].offsetTop + $('#canvas')[0].scrollTop	- $('#canvas').offsetParent().offset().top ],
                            shape, [parseInt(shapeInfo._width, 10), parseInt(shapeInfo._height, 10)]);
                    
                    // 그리고 난 후의 엘리먼트에 이벤트 등록
                    addEventAfterDraw(canvas, element);
                }
                this.icanvas = canvas;
                $('#canvas').removeData('DRAG_SHAPE');
            }
        }
    });
    
    // load 에서 데이터가 넘어왔을 경우 데이터를 셋팅하여 그림
	if( object != null && object.graphString != null ){
		this.icanvas.loadJSON($.parseJSON(object.graphString));
		$("g[_shape=" + OG.Constants.SHAPE_TYPE.GEOM + "]").each(function (n, element) {
			addEventAfterDraw(canvas , element);
			// 이벤트 핸들
//			mw3.getFaceHelper(objectId).addEventAfterDraw(element);
		});
	}
};

function addEventAfterDraw(canvas, element){
	$(element).on({
    	mouseup: function (event, ui) {
    		var session = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Session");
    		var node = session.clipboard;
    		var customData = canvas.getCustomData(element);
    		if( customData == undefined || customData == null || customData == "" ){
    			customData = [];
    		}
    		if(node && node.__className=="org.uengine.codi.mw3.knowledge.WfNode"){
    			canvas.drawLabel(element, node.name);
    			customData.push( {"customId": node.id , "customName" : node.name , "customType" : "wfNode"});
//    			canvas.setCustomData(element, {"customId": node.id , "customName" : node.name , "customType" : "wfNode"});
    			session.clipboard = null;
    		}
    		if(node && node.__className=="org.uengine.codi.mw3.webProcessDesigner.Role"){
    			var wfText = $(element).children('[id$=_LABEL]').text();
    			wfText = wfText + '(' + node.name + ')';
    			canvas.drawLabel(element, wfText);
    			customData.push( {"customId": "" , "customName" : node.name , "customType" : "role"});
//    			canvas.setCustomData(element, {"customId": "" , "customName" : node.name , "customType" : "role"});
    			session.clipboard = null;
    		}
    		if(customData.length > 0){
    			canvas.setCustomData(element, customData);
    		}
//    	},
//    	"contextmenu": function (event) {
//    		alert(element.id);
    	}
    });
}

//org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.addEventAfterDraw = function(element){
//	$(element).on({
//    	mouseup: function (event, ui) {
//    		var session = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Session");
//    		var node = session.clipboard;
//    		if(node && node.__className=="org.uengine.codi.mw3.knowledge.WfNode"){
//    			canvas.setCustomData(this, {"customId": node.id , "customType" : "wfNode"});
//    			session.clipboard = null;
//    		}
//    	}
//    });
//};
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.clear = function(){
	this.icanvas.clear();
};
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.jsonobject = function(){
//	var object = mw3.getObject(this.objectId);
//	object.jsonObj = this.icanvas.toJSON();
//	object.jsonObj = JSON.stringify(this.icanvas.toJSON());
	alert(JSON.stringify(this.icanvas.toJSON() ));
//	object.jsonObj = $.parseXML(this.icanvas.toXML());
};
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.loadForJson = function(cells){
	var opengraphJSON = null;
	if( cells != null){
		for(var i=0; i<cells.length; i++){
			if( cells.jsonString != null ){
				opengraphJSON = cells.jsonString;
			}
		}
	}
	
	return opengraphJSON;
};
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.getValue = function(){
	var graphJson = this.icanvas.toJSON();
	var ogObj = eval(graphJson.opengraph);
	var ogArr = ogObj.cell;
	var cellsForDwr = [];
	for(var i=0; i<ogArr.length; i++){
		var og = ogArr[i];
		var cellForDwr = {};
		for(var key in og){
			// id 와 data 만 따로 관리 - 추가로 필요한 정보 관리
			if(key == '@id'){
				cellForDwr['id'] = og[key];
			}
			if(key == '@data'){
				cellForDwr['data'] = og[key];
			}
			if(key == '@shapeType'){
				cellForDwr['shapeType'] = og[key];
			}
		}
		cellsForDwr[cellsForDwr.length] = cellForDwr;
	}
	var object = mw3.objects[this.objectId];
	object.cell = cellsForDwr;
	object.graphString = JSON.stringify(graphJson);
	
	return object;
};
