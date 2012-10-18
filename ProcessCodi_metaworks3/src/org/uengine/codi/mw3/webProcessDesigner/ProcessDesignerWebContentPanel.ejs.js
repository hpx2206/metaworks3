var org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	var object = mw3.objects[this.objectId];
	var faceHelper = this;
	var canvas = null;
	$('#objDiv_' + this.objectId).css('height','100%');
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
                    
                    if (shapeInfo._shape_type === 'GEOM') {
	                    // 그리고 난 후의 엘리먼트에 이벤트 등록
	                    faceHelper.addEventGeom(objectId, canvas, element);
                    }
                }
                this.icanvas = canvas;
                $('#canvas').removeData('DRAG_SHAPE');
            }
        }
    });
    $("#canvas").mouseup(function(){
    	var session = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Session");
 		var clipboardNode = session.clipboard;
 		// 지식노드를 캔버스에 떨구었을때 
 		if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.knowledge.WfNode"){
 			var newNodeWidth = 70 ;
 			var newNodeHeight = 50 ;
 			var knolElement = canvas.drawShape([
                 event.pageX - $('#canvas')[0].offsetLeft + $('#canvas')[0].scrollLeft	- $('#canvas').offsetParent().offset().left,
                 event.pageY - $('#canvas')[0].offsetTop + $('#canvas')[0].scrollTop	- $('#canvas').offsetParent().offset().top ],
                 new OG.shape.bpmn.A_Task(clipboardNode.name) , [parseInt(newNodeWidth, 10), parseInt(newNodeHeight, 10)]);
 			var customData = [];
 			customData.push( {"customId": clipboardNode.id , "customName" : clipboardNode.name , "customType" : "wfNode"});
 			canvas.setCustomData(knolElement, customData);
 			session.clipboard = null;
 		}		
    });
    
    // Shape 이 Connect 되었을 때의 이벤트 리스너
    canvas.onConnectShape(function (event, edgeElement, fromElement, toElement) {
    	// TODO 연결되는 시점에만 호출 되니 그려진 로직은 다른걸 태우던지 해야함
    	$(edgeElement).bind({
			dblclick: function (event) {
				var value = mw3.getObject(objectId);
				value.gateCondition();
			}
		});
    });
    
    // Role.ejs 파일쪽에 있는 스윔레인추가 버튼 클릭시 동작
    $(".horizontalLaneShapeCall").click(function(){
    	var text = $("#horizontalLaneShapeText").val();
    	if( text == null || text == '' || text == undefined ){
    		alert('Role 명을 입력해주세요');	// TODO 다국어 처리~
    	}else{
    		var newNodeWidth = 300 ;
			var newNodeHeight = 100 ;
			var positionWidth = 300;
			var positionHeight = 300;
			var eleLength = $("g[_shape_id='OG.shape.HorizontalLaneShape']").length;
			if( eleLength == 0 ){
				positionWidth = $('#canvas').width() - $('#canvas')[0].offsetLeft + $('#canvas')[0].scrollLeft	- $('#canvas').offsetParent().offset().left;
				positionHeight = $('#canvas').height() - $('#canvas')[0].offsetTop + $('#canvas')[0].scrollTop	- $('#canvas').offsetParent().offset().top;
			}else{
				$("g[_shape_id='OG.shape.HorizontalLaneShape']").each(function (index, element) {
					if (index === eleLength - 1) {
						positionWidth = $(this).position().left  + (element.getBBox().width / 2 ) ; 
						positionHeight = $(this).position().top + (element.getBBox().height /2 ) + element.getBBox().height ;
						newNodeWidth = element.getBBox().width;
						newNodeHeight = element.getBBox().height;
					}
				});
			}
			canvas.drawShape([ positionWidth , positionHeight ],
				 new OG.shape.HorizontalLaneShape(text) , [parseInt(newNodeWidth, 10), parseInt(newNodeHeight, 10)]);
			$("#horizontalLaneShapeText").val("");
    	}
    });
    // load 에서 데이터가 넘어왔을 경우 데이터를 셋팅하여 그림
	if( object != null && object.graphString != null ){
		var canvassizeObject = this.icanvas.loadJSON($.parseJSON(object.graphString));
		// 캔버스 사이즈 조정
		var canvasWidth = $('#canvas').width();
		var canvasHeight = $('#canvas').height();
		if(canvasWidth < canvassizeObject.x2){
			canvasWidth = canvassizeObject.x2;
		}
		if(canvasHeight < canvassizeObject.y2){
			canvasHeight = canvassizeObject.y2;
		}
		this.icanvas.setCanvasSize([canvasWidth, canvasHeight]);
		$("g[_shape=" + OG.Constants.SHAPE_TYPE.GEOM + "]").each(function (n, element) {
			faceHelper.addEventGeom(objectId ,canvas, element);
		});
		$("g[_shape=" + OG.Constants.SHAPE_TYPE.EDGE + "]").each(function (n, element) {
			faceHelper.addEventEdge(objectId ,canvas, element);
		});
	}
};

org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.addEventGeom = function(objectId, canvas, element){
	$(element).on({
    	mouseup: function (event, ui) {
    		var session = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Session");
    		var clipboardNode = session.clipboard;
    		var customData = canvas.getCustomData(element);
    		if( customData == undefined || customData == null || customData == "" ){
    			customData = [];
    		}
    		if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.knowledge.WfNode"){
    			canvas.drawLabel(element, clipboardNode.name);
    			customData.push( {"customId": clipboardNode.id , "customName" : clipboardNode.name , "customType" : "wfNode"});
    			session.clipboard = null;
    		}
    		if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.webProcessDesigner.Role"){
    			var wfText = $(element).children('[id$=_LABEL]').text();
    			wfText = wfText + '(' + clipboardNode.name + ')';
    			canvas.drawLabel(element, wfText);
    			customData.push( {"customId": "" , "customName" : clipboardNode.name , "customType" : "role"});
    			session.clipboard = null;
    		}
    		if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.model.ResourceFile"){
    			var wfText = $(element).children('[id$=_LABEL]').text();
    			wfText = wfText + '\n(' + clipboardNode.name + ')';
    			canvas.drawLabel(element, wfText);
    			session.clipboard = null;
    		}
    		if(customData.length > 0){
    			canvas.setCustomData(element, customData);
    		}
//    	},
//    	"contextmenu": function (event) {
//    		if( $(this).attr("_shape_id") == "OG.shape.bpmn.G_Gateway" ){
//    			
//    		}
    	}
    });
};

org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.addEventEdge = function(objectId, canvas, element){
	$(element).bind({
		dblclick: function (event) {
			var value = mw3.getObject(objectId);
			value.gateCondition();
		}
	});
};
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
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.getValue = function(){
	var graphJson = this.icanvas.toJSON();
	var ogObj = eval(graphJson.opengraph);
	var ogArr = ogObj.cell;
	var cellsForDwr = [];
	for(var i=0; i<ogArr.length; i++){
		var og = ogArr[i];
		var cellForDwr = {};
		for(var key in og){
			// 추가로 필요한 정보 관리
			if(key == '@id'){
				cellForDwr['id'] = og[key];
			}
			if(key == '@shapeId'){
				cellForDwr['shapeId'] = og[key];
			}
			if(key == '@label'){
				cellForDwr['label'] = og[key];
			}
			if(key == '@data'){
				cellForDwr['data'] = og[key];
			}
			if(key == '@from'){
				cellForDwr['from'] = og[key];
			}
			if(key == '@fromEdge'){
				cellForDwr['fromEdge'] = og[key];
			}
			if(key == '@to'){
				cellForDwr['to'] = og[key];
			}
			if(key == '@toEdge'){
				cellForDwr['toEdge'] = og[key];
			}
			if(key == '@shapeType'){
				cellForDwr['shapeType'] = og[key];
			}
			if(key == '@parent'){
				cellForDwr['parent'] = og[key];
			}
		}
		cellsForDwr[cellsForDwr.length] = cellForDwr;
	}
	var object = mw3.objects[this.objectId];
	object.cell = cellsForDwr;
	object.graphString = JSON.stringify(graphJson);
	
	return object;
};
