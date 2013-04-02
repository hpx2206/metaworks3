var org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	this.divObj = $('#' + this.divId);
	
	var object = mw3.objects[this.objectId];
	
	if(object){
		if(mw3.importScript('scripts/opengraph/OpenGraph-0.1-SNAPSHOT.js', function(){mw3.getFaceHelper(objectId).load();})){
			mw3.importScript('scripts/jquery/jquery.contextMenu.js');
			
			mw3.importStyle('style/jquery/jquery.contextMenu.css');
			mw3.importStyle('dwr/metaworks/org/uengine/codi/mw3/model/PureWebProcessDesigner.ejs.css');
		}else{
			var faceHelper = this;
			
			faceHelper.load();
		}
	}
	
	$('.leftMenuTab_ide').click(function(){
		if($(this).hasClass('closed')){
			$(this).next('.leftMenuTabInner_ide').show();
			$(this).removeClass('closed');
		}else{
			$(this).next('.leftMenuTabInner_ide').hide();
			$(this).addClass('closed');
		}
	})
};

org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype = {
	load : function(){
		var objectId = this.objectId;
		var object = mw3.objects[this.objectId];
		var faceHelper = this;
		
		this.object = object;
		
		var canvas = null;
		this.icanvas = null;
		this.tracingTag = 0;
		
		this.divObj.css('height','100%');
		var canvasDivObj = $('#canvas');
		
//		if(canvasDivObj.length > 0){
//			canvasDivObj.show().appendTo('#' + this.divId + '>div:first-child');
//		}else{
//			$('#' + this.divId + '>div:first-child').append('<div id=\"canvas\" style=\"height:100%; margin-left:80px; cursor: default; overflow:auto;  background:#F1FCF1; \"></div>');
//			canvasDivObj = $('#canvas');
//		    // Canvas
//		    OG.common.Constants.CANVAS_BACKGROUND = "#fff";
//		    OG.Constants.ENABLE_CANVAS_OFFSET = true; // Layout 사용하지 않을 경우 true 로 지정
//		    
//		    mw3.canvas = new OG.Canvas('canvas');
//		}
//		this.icanvas = mw3.canvas;
//		canvas = this.icanvas;
		
		OG.common.Constants.CANVAS_BACKGROUND = "#fff";
	    OG.Constants.ENABLE_CANVAS_OFFSET = true; // Layout 사용하지 않을 경우 true 로 지정
	    mw3.canvas = new OG.Canvas('canvas');
		this.icanvas = mw3.canvas;	
		canvas = mw3.canvas;
		
		canvas.initConfig({
	        selectable       : true,
	        dragSelectable   : true,
	        movable          : true,
	        resizable        : true,
	        connectable      : true,
	        selfConnectable  : false,
	        connectCloneable : false,
	        connectRequired  : true,
	        labelEditable    : true,
	        groupDropable    : true,
	        collapsible      : true,
	        enableHotKey     : true,
	        enableContextMenu: true
	    });
		
	    // Shape drag & drop
	    $(".icon_shape").draggable({
	        start   : function () {
	        	$('#canvas').data('DRAG_SHAPE', {
	                '_shape_type': $(this).attr('_shape_type'),
	                '_shape_id'  : $(this).attr('_shape_id'),
	                '_width'     : $(this).attr('_width'),
	                '_height'    : $(this).attr('_height'),
	                '_classname' : $(this).attr('_classname'),
	                '_classType' : $(this).attr('_classType')
	            });
	        },
	        helper  : 'clone',
	        appendTo: "#canvas"
	    });
	    canvasDivObj.droppable({
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
	                    
	                    if (shapeInfo._shape_type === 'GEOM' || shapeInfo._shape_type === 'GROUP') {
	                    	
	                    	if( shapeInfo._shape_id == 'OG.shape.bpmn.E_Start' || shapeInfo._shape_id == 'OG.shape.bpmn.E_End'){
	                    		// do nothing
	                    	}else{
	                    		$(element).attr("_classname", shapeInfo._classname);
	                    		$(element).attr("_classType", shapeInfo._classType);
	                    		$(element).attr("_tracingTag",++faceHelper.tracingTag);
	                    		// 그리고 난 후의 엘리먼트에 이벤트 등록
	                    		faceHelper.addEventGeom(objectId, canvas, element);
	                    	}
	                    }
	                }
	                this.icanvas = canvas;
	                $('#canvas').removeData('DRAG_SHAPE');
	            }
	        }
	    });
	    canvasDivObj.mouseup(function(){
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
			faceHelper.addEventEdge(objectId, canvas, edgeElement);
	    });
	    canvas.onDrawShape(function (event, shapeElement) {
	    	// TODO 여기서 Shape 객체를 바로 만드려고 하였으나 연결정보는 가지고 있지 않다.
	    });
	    canvas.onRemoveShape(function (shapeElement) {
//	    	console.log($(shapeElement));
	    });
	    canvas.onLabelChanged(function (event, shapeElement, afterText, beforeText) {
	    	// TODO 스윔레인에 text가 써졌을때, 바로 role 추가하는 로직 생성
	    });
	    // Role.ejs 파일쪽에 있는 스윔레인추가 버튼 클릭시 동작
//	    $(".horizontalLaneShapeCall").click(function(){
//	    	var text = $("#horizontalLaneShapeText").val();
//	    	if( text == null || text == '' || text == undefined ){
//	    		alert('Role 명을 입력해주세요');	// TODO 다국어 처리~
//	    	}else{
//	    		var newNodeWidth = 300 ;
//				var newNodeHeight = 100 ;
//				var positionWidth = 300;
//				var positionHeight = 300;
//				var eleLength = $("g[_shape_id='OG.shape.HorizontalLaneShape']").length;
//				if( eleLength == 0 ){
//					positionWidth = $('#canvas').width() - $('#canvas')[0].offsetLeft + $('#canvas')[0].scrollLeft	- $('#canvas').offsetParent().offset().left;
//					positionHeight = $('#canvas').height() - $('#canvas')[0].offsetTop + $('#canvas')[0].scrollTop	- $('#canvas').offsetParent().offset().top;
//				}else{
//					$("g[_shape_id='OG.shape.HorizontalLaneShape']").each(function (index, element) {
//						if (index === eleLength - 1) {
//							positionWidth = $(this).position().left  + (element.getBBox().width / 2 ) ; 
//							positionHeight = $(this).position().top + (element.getBBox().height /2 ) + element.getBBox().height ;
//							newNodeWidth = element.getBBox().width;
//							newNodeHeight = element.getBBox().height;
//						}
//					});
//				}
//				canvas.drawShape([ positionWidth , positionHeight ],
//					 new OG.shape.HorizontalLaneShape(text) , [parseInt(newNodeWidth, 10), parseInt(newNodeHeight, 10)]);
//				$("#horizontalLaneShapeText").val("");
//	    	}
//	    });
	    var canvasWidth = 1024;		// defualt
	    var canvasHeight = 768;		// defualt
	    
	    // load 에서 데이터가 넘어왔을 경우 데이터를 셋팅하여 그림
		if( object != null && object.graphString != null ){
			var canvassizeObject = this.icanvas.loadJSON($.parseJSON(object.graphString));
			
			this.tracingTag = object.lastTracingTag;
			// tracingTag 달아주기
			if( object != null && object.cell != null ){
				var cells = object.cell;
				for(var i=0; i < cells.length; i++){
					var cellId = cells[i].id;
					var cellTracing = cells[i].tracingTag;
					var cellClassname = cells[i].classname;
					var cellClassType = cells[i].classType;
					
					if( cellTracing != null ){
						$('#'+cellId).attr("_tracingTag",cellTracing);					
					}
					
					if( cellClassname != null ){
						$('#'+cellId).attr("_classname",cellClassname);
						$('#'+cellId).attr("_classType",cellClassType);
						var activityData = null;
						if( cellClassType == 'Activity'){
							activityData = object.activityMap[cellId];
						}else if( cellClassType == 'Role'){
							activityData = object.roleMap[cellId];
						}
						$('#'+cellId).data('activity', activityData);
					}
				}
			}
			// 캔버스 사이즈 조정
			if(canvasWidth < canvassizeObject.x2){
				canvasWidth = canvassizeObject.x2;
			}
			if(canvasHeight < canvassizeObject.y2){
				canvasHeight = canvassizeObject.y2;
			}
			$("g[_shape=" + OG.Constants.SHAPE_TYPE.GEOM + "]").each(function (n, element) {
				faceHelper.addEventGeom(objectId ,canvas, element);
			});
			$("g[_shape=" + OG.Constants.SHAPE_TYPE.GROUP + "]").each(function (n, element) {
				faceHelper.addEventGeom(objectId ,canvas, element);
			});		
			$("g[_shape=" + OG.Constants.SHAPE_TYPE.EDGE + "]").each(function (n, element) {
				faceHelper.addEventEdge(objectId ,canvas, element);
			});
			
			
		}
		this.icanvas.setCanvasSize([canvasWidth, canvasHeight]);		
	},
	findActivityData : function(tracingTag){
		var object = mw3.objects[this.objectId];
		var activityList = object.activityList;
		if( activityList ){
			for( var i = 0; i < activityList.length; i++){
				activity = activityList[i];
			}
		}
	}
};

//org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype = {
//		destroy : function(){
//			$('#canvas').hide().appendTo('body');
//			this.icanvas.clear();
//		}
//};
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.addEventGeom = function(objectId, canvas, element){
	
	var shape_id = $(element).attr("_shape_id");
	if( typeof $(element).attr("_classname") != 'undefined' &&  typeof $(element).data("activity") == 'undefined' ){
		var activityData = {__className : $(element).attr("_classname"), tracingTag : $(element).attr("_tracingTag")};
		$(element).data('activity', activityData);
	}
	$(element).on({
    	mouseup: function (event, ui) {
    		var session = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Session");
    		var clipboardNode = session.clipboard;
    		var customData = canvas.getCustomData(element);
    		if( customData == undefined || customData == null || customData == "" ){
    			customData = [];
    		}
    		var shapeType = $(this).attr("_shape");
    		if( shapeType == 'GEOM' ){	
	    		if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.knowledge.WfNode"){
	    			canvas.drawLabel(element, clipboardNode.name);
//	    			customData.push( {"customId": clipboardNode.id , "customName" : clipboardNode.name , "customType" : "wfNode"});
	    			var value = mw3.objects[objectId];
	    			value.tempElementId = $(this).attr('id');
	    			value.tempElementName = clipboardNode.name;
	    			value.tempElementTypeId = clipboardNode.id;
	    			value.tempElementType = "wfNode";
	    			value.addValiable();
	    		}
//	    		if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.webProcessDesigner.Role"){
//	    			var wfText = $(element).children('[id$=_LABEL]').text();
//	    			wfText = wfText + '(' + clipboardNode.name + ')';
//	    			canvas.drawLabel(element, wfText);
//	    			customData.push( {"customId": "" , "customName" : clipboardNode.name , "customType" : "role"});
//	    		}
	    		if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.model.ResourceFile"){
	    			var javaFileName = clipboardNode.name;
	    			if( javaFileName != '' && javaFileName.length > 5){
	    				var tokens  = javaFileName.split(".");
	    				var text = null;
	    		    	if(tokens.length>1)
	    		    		text = tokens[tokens.length-1];
	    		    	if( text != null && text == 'java'){
	    		    		var eleClassName = $(this).attr("_classname");
	    		    		if( eleClassName == 'org.uengine.kernel.InvocationActivity'){
	    		    			var activityData = $(this).data('activity');
	    		    			activityData.resourceClass = clipboardNode.alias;
	    		    			$(this).data('activity', activityData);
	    		    		}
	    		    		
	    		    		
	    		    		var wfText = $(element).children('[id$=_LABEL]').text();
	    		    		wfText = wfText + '(' + clipboardNode.name + ')';
	    		    		canvas.drawLabel(element, wfText);
//	    		    		customData.push( {"customId": "" , "customName" : tokens[tokens.length-2] , "customType" : "class"});
	    		    		
	    		    		var value = mw3.objects[objectId];
	    		    		var variableName = tokens[tokens.length-2];
	    		    		var contentValue = {
									__className : 'org.uengine.codi.mw3.webProcessDesigner.PrcsVariable',
									name : variableName ,
									typeId : clipboardNode.alias ,
									variableType : 'complexType'
							};
	    		    		value.variableMap[variableName] = contentValue;
//	    	    			value.tempElementId = $(this).attr('id');
//	    	    			value.tempElementName = tokens[tokens.length-2];
//	    	    			value.tempElementTypeId = clipboardNode.alias;
//	    	    			value.tempElementType = "class";
//	    	    			value.addValiable();
	    		    	}
	    			}
	    		}
    		}else if( shapeType == 'GROUP' ){
    			if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.model.ResourceFile"){
	    			var javaFileName = clipboardNode.name;
	    			if( javaFileName != '' && javaFileName.length > 9){
	    				var tokens  = javaFileName.split(".");
	    				var text = null;
	    		    	if(tokens.length>1)
	    		    		text = tokens[tokens.length-1];
	    		    	if( text != null && text == 'process2'){
	    		    		var eleClassName = $(this).attr("_classname");
	    		    		if( eleClassName == 'org.uengine.kernel.SubProcessActivity'){
	    		    			var activityData = $(this).data('activity');
	    		    			// defId 셋팅
	    		    			activityData.definitionId = clipboardNode.alias;
	    		    			$(this).data('activity', activityData);
	    		    		}
	    		    		
	    		    		var wfText = clipboardNode.name;
	    		    		canvas.drawLabel(element, wfText);
	    		    	}
	    			}
    			}
    		}
    		session.clipboard = null;
    		if(customData.length > 0){
    			canvas.setCustomData(element, customData);
    		}
    	},
    	dblclick: function (event) {
    		/*
    		var value = mw3.getOobject(objectId);
			value.tempElementId = $(this).attr('id');
			value.geomInfo();
			*/
    		var divId = 'properties_' + objectId;
			$('body').append("<div id='" + divId + "'></div>");
			var metaworksContext = {
				__className : 'org.metaworks.MetaworksContext',
				when : 'edit'
			};
			
			if(shape_id == 'OG.shape.HorizontalLaneShape' || shape_id == 'OG.shape.VerticalLaneShape')
				metaworksContext['how'] = 'lane';
				
			var propertiesWindow = {
				__className : 'org.uengine.codi.mw3.webProcessDesigner.PropertiesWindow',
				open : true,
				width : 860,
				height : 600,
				panel : $(this).data('activity'),
				metaworksContext : metaworksContext,
				id : $(this).attr('id')
			};
			
			
			var object = mw3.getObject(objectId);
			object['propertiesWindow'] = propertiesWindow;
			object.showProperties();
			//var metadata = mw3.getMetadata(object.__className);				
			
			//mw3.locateObject(object, null, '#' + divId, metadata);
			//mw3.onLoadFaceHelperScript();	    		
    	}
    });
	$(element).bind('apply', function(){
//		alert('11');
	});
};

org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.addEventEdge = function(objectId, canvas, element){
	$(element).unbind('dblclick').bind({
		dblclick: function (event) {
			var value = mw3.objects[objectId]; 
			
			value.tempElementId = $(this).attr('id');
			value.tempElementName = $(this).children('[id$=_LABEL]').text();
			value.gateCondition();
		}
	});
};
// TODO delete test code 
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.showValiables = function(){
	alert( mw3.pcsValiable.toXML(mw3.pcsValiable.rootElement)  );
};
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.clear = function(){
	this.icanvas.clear();
};
//TODO delete test code 
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
	var activityMap = {};
	var roleMap = {};
	for(var i=0; i<ogArr.length; i++){
		var og = ogArr[i];
		var cellForDwr = {};
		
		if( og['@shapeType'] != 'EDGE'){
			$id = $('#'+og['@id']);
			cellForDwr['tracingTag'] = $id.attr('_tracingTag');
			cellForDwr['classname'] = $id.attr('_classname');
			var classType = $id.attr('_classType');
			cellForDwr['classType'] = classType;
			var activity = $id.data('activity');
			if(typeof activity != 'undefined' && classType == 'Activity'){
				activityMap[og['@id']] = activity;
			}else if(typeof activity != 'undefined' && classType == 'Role'){
				roleMap[og['@id']] = activity;
			}
		}
		
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
	object.activityMap = activityMap;
	object.roleMap = roleMap;
	
	return object;
};


org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.startLoading = function(){
	this.divObj.trigger('startLoading');
};

org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerWebContentPanel.prototype.endLoading = function(){
	this.divObj.trigger('endLoading');
};