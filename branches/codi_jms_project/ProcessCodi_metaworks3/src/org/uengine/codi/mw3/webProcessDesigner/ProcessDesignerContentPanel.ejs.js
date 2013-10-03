$.ui.intersect = function(draggable, droppable, toleranceMode) {
	if (!droppable.offset) return false;

	var x1 = (draggable.positionAbs || draggable.position.absolute).left, x2 = x1 + draggable.helperProportions.width,
		y1 = (draggable.positionAbs || draggable.position.absolute).top, y2 = y1 + draggable.helperProportions.height;
	var l = droppable.offset.left, r = l + droppable.proportions.width,
		t = droppable.offset.top, b = t + droppable.proportions.height;
	switch (toleranceMode) {
		case 'fit':
			return (l <= x1 && x2 <= r
				&& t <= y1 && y2 <= b);
			break;
		case 'intersect':
			return (l < x1 + (draggable.helperProportions.width / 2) // Right Half
				&& x2 - (draggable.helperProportions.width / 2) < r // Left Half
				&& t < y1 + (draggable.helperProportions.height / 2) // Bottom Half
				&& y2 - (draggable.helperProportions.height / 2) < b ); // Top Half
			break;
		case 'pointer':
			var draggableLeft = ((draggable.positionAbs || draggable.position.absolute).left + (draggable.clickOffset || draggable.offset.click).left),
				draggableTop = ((draggable.positionAbs || draggable.position.absolute).top + (draggable.clickOffset || draggable.offset.click).top),
				isOver = $.ui.isOver(draggableTop, draggableLeft, t, l, droppable.proportions.height, droppable.proportions.width);
			return isOver;
			break;
		case 'touch':
			return (
					(y1 >= t && y1 <= b) ||	// Top edge touching
					(y2 >= t && y2 <= b) ||	// Bottom edge touching
					(y1 < t && y2 > b)		// Surrounded vertically
				) && (
					(x1 >= l && x1 <= r) ||	// Left edge touching
					(x2 >= l && x2 <= r) ||	// Right edge touching
					(x1 < l && x2 > r)		// Surrounded horizontally
				);
			break;
		case 'geom':
			var boundary = droppable.element[0].shape.geom.getBoundary();
			
			var draggableLeft = ((draggable.positionAbs || draggable.position.absolute).left + (draggable.clickOffset || draggable.offset.click).left),
			draggableTop = ((draggable.positionAbs || draggable.position.absolute).top + (draggable.clickOffset || draggable.offset.click).top),
			isOver = $.ui.isOver(draggableTop, draggableLeft, t, l, boundary._height, boundary._width);
			
			return isOver;
			
			break;
		default:
			return false;
			break;
		}

};

var org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	this.divObj = $('#' + this.divId);
	
	var object = mw3.objects[this.objectId];
	
	if(object){
		mw3.importScript('scripts/opengraph/OpenGraph-0.1-SNAPSHOT.js');
		mw3.importScript('scripts/jquery/jquery.contextMenu.js');
		mw3.importStyle('style/jquery/jquery.contextMenu.css');
		mw3.importScript('scripts/jquery/jquery-tooltip.js');
		mw3.importStyle('style/jquery/jquery-tooltip.css');
		mw3.importStyle('dwr/metaworks/org/uengine/codi/mw3/model/PureWebProcessDesigner.ejs.css');
		
		mw3.importScript('scripts/jquery.jqGrid-4.3.1/jquery.jqGrid.js');
		mw3.importStyle('scripts/jquery.jqGrid-4.3.1/css/ui.jqgrid.css');
		
		var faceHelper = this;
		faceHelper.load();
	}
	
	$('.leftMenuTab_ide').click(function(){
		if($(this).hasClass('closed')){
			$(this).next('.leftMenuTabInner_ide').show();
			$(this).removeClass('closed');
		}else{
			$(this).next('.leftMenuTabInner_ide').hide();
			$(this).addClass('closed');
		}
	});
};

org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype = {
	load : function(){
		var objectId = this.objectId;
		var object = mw3.objects[this.objectId];
		var faceHelper = this;
		this.object = object;
		
		var canvas = null;
		this.icanvas = null;
		this.tracingTag = 0;
		
		this.divObj.css('height','100%');
		this.divObj.parent().css('height','100%');
		var canvasDivObj = $('#canvas_' + objectId);
		
//		if(canvasDivObj.length > 0){
//			canvasDivObj.show().appendTo('#' + this.divId + '>div:first-child');
//		}else{
//			$('#' + this.divId + '>div:first-child').append('<div id=\"canvas\" style=\"height:100%; margin-left:80px; cursor: default; overflow:auto;  background:#F1FCF1; \"></div>');
//			canvasDivObj = $('#canvas_' + objectId);
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
	    mw3.canvas = new OG.Canvas('canvas_' + objectId);
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
		
		canvas.setCurrentCanvas(canvas);
	    // Shape drag & drop
	    $(".icon_shape_"+objectId).draggable({
	        helper  : 'clone',
	        appendTo: "#canvas_" + objectId
	    });
	    canvasDivObj.droppable({
	    	
	        drop: function (event, ui) {
	        	faceHelper.canvasDropEvent(objectId , canvas , canvasDivObj);
	        	if(!$(ui.draggable).hasClass('icon_shape_'+objectId))
	        		return true;
	        	var shapeInfo = {
		                '_shape_type': $(ui.draggable).attr('_shape_type'),
		                '_shape_id'  : $(ui.draggable).attr('_shape_id'),
		                '_width'     : $(ui.draggable).attr('_width'),
		                '_height'    : $(ui.draggable).attr('_height'),
		                '_classname' : $(ui.draggable).attr('_classname'),
		                '_viewclass' : $(ui.draggable).attr('_viewclass'),
		                '_classType' : $(ui.draggable).attr('_classType')
	            }, shape, element;

	        	if (shapeInfo) {
	            	var viewclass = shapeInfo._viewclass;
	            	var activityclass = shapeInfo._classname;
	            	if( typeof viewclass == 'undefined' ){
	            		viewclass = "org.uengine.kernel.designer.web.ActivityView";
	            	}
	            	var pageX = event.pageX - $("#canvas_" + objectId)[0].offsetLeft + $("#canvas_" + objectId)[0].scrollLeft - $("#canvas_" + objectId).offsetParent().offset().left;
	            	var pageY = event.pageY - $('#canvas_' + objectId)[0].offsetTop + $('#canvas_' + objectId)[0].scrollTop	- $('#canvas_' + objectId).offsetParent().offset().top;
	            	var activityView = {
							__className : viewclass,
							x : pageX,
							y : pageY,
							shapeId : shapeInfo._shape_id,
							shapeType : shapeInfo._shape_type,
							classType : shapeInfo._classType,
							activityClass : activityclass,
							height : shapeInfo._height,
							width : shapeInfo._width,
							tracingTag : ++faceHelper.tracingTag,
							editorId : object.alias
					};
	            	
	            	var html = mw3.locateObject(activityView , activityView.____className);
	            	canvasDivObj.append(html);
	            	
	            	mw3.onLoadFaceHelperScript();
	            	
	            	this.icanvas = canvas;
	            	
	            } 
	            	/*
	                if (shapeInfo._shape_type === 'EDGE') {
	                    shape = eval('new ' + shapeInfo._shape_id + '()');
	                    element = canvas.drawShape(null, shape, null);
	                    canvas.RENDERER.move(element, [
	                        event.pageX - $("#canvas_" + objectId)[0].offsetLeft + $("#canvas_" + objectId)[0].scrollLeft - $("#canvas_" + objectId).offsetParent().offset().left,
	                        event.pageY - $("#canvas_" + objectId)[0].offsetTop + + $("#canvas_" + objectId)[0].scrollTop	- $("#canvas_" + objectId).offsetParent().offset().top ]);
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
	                        event.pageX - $('#canvas_' + objectId)[0].offsetLeft + $('#canvas_' + objectId)[0].scrollLeft	- $('#canvas_' + objectId).offsetParent().offset().left,
	                        event.pageY - $('#canvas_' + objectId)[0].offsetTop + $('#canvas_' + objectId)[0].scrollTop	- $('#canvas_' + objectId).offsetParent().offset().top ],
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
	                */
	        }  
	         
	        
	    });
	    // Shape 이 Connect 되었을 때의 이벤트 리스너
	    canvas.onConnectShape(function (event, edgeElement, fromElement, toElement) {
	    	var connected = false;
	    	var fromElementId = $(fromElement).attr('id');
			var toElementId = $(toElement).attr('id');
			var connectedText = fromElementId +'_'+ toElementId;
			var fromFlag = $(fromElement).attr('_conneted'+fromElementId);
			var toflag = $(toElement).attr('_conneted'+toElementId);
			if( fromFlag == connectedText && toflag == connectedText ){
				connected = true;
			}
			
	    	if( $(edgeElement).data('transition') == undefined && !connected ){
	    		var transitionView = {
						__className : 'org.uengine.kernel.designer.web.TransitionView',
						drawByCanvas : true,
						element : edgeElement
	    		};
	    		var html = mw3.locateObject(transitionView , transitionView.____className);
            	canvasDivObj.append(html);
            	
            	mw3.onLoadFaceHelperScript();
	    	}
//	    	var fromTracingTag = $(fromElement).attr('_tracingTag');
//	    	var toTracingTag = $(toElement).attr('_tracingTag');
//	    	var transitionData = {
//	    			__className : 'org.uengine.kernel.graph.Transition', 
//	    			source : fromTracingTag,
//	    			target : toTracingTag
//	    	};
//	    	$(edgeElement).data('transition', transitionData);
//			faceHelper.addEventEdge(objectId, canvas, edgeElement);
	    });
	    
	    canvas.onDrawShape(function (event, shapeElement) {
	    	// TODO 여기서 Shape 객체를 바로 만드려고 하였으나 연결정보는 가지고 있지 않다.
	    	if($(shapeElement).attr('auto_draw') && $(shapeElement).attr('auto_draw') == 'yes'){
		    	$(shapeElement).attr('_shape_type', 'GEOM');
		    	$(shapeElement).attr('_width', '70');
		    	$(shapeElement).attr('_height', '50');
		    	$(shapeElement).attr('_classname', 'org.uengine.kernel.HumanActivity');
		    	$(shapeElement).attr('_viewclass', 'org.uengine.kernel.designer.web.HumanActivityView');
		    	$(shapeElement).attr('_classType', 'Activity');
		    	$(shapeElement).attr('_tracingTag', ++faceHelper.tracingTag);
		    	
		    	var activityView = {
						__className : $(shapeElement).attr('_viewclass'),
						element : shapeElement,
						drawByCanvas : true
		    	};
		    	
		    	var html = mw3.locateObject(activityView , activityView.____className);
	        	canvasDivObj.append(html);
	        	
	        	mw3.onLoadFaceHelperScript();
	    	}
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
//					positionWidth = $('#canvas_' + objectId).width() - $('#canvas_' + objectId)[0].offsetLeft + $('#canvas_' + objectId)[0].scrollLeft	- $('#canvas_' + objectId).offsetParent().offset().left;
//					positionHeight = $('#canvas_' + objectId).height() - $('#canvas_' + objectId)[0].offsetTop + $('#canvas_' + objectId)[0].scrollTop	- $('#canvas_' + objectId).offsetParent().offset().top;
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
	    
	    if( object != null ){
	    	this.tracingTag = object.processDesignerContainer.lastTracingTag;
	    	var maxX = object.processDesignerContainer.maxX;
	    	var maxY = object.processDesignerContainer.maxY;
	    	if( maxX && maxY ){
	    		if( maxX > canvasWidth ) canvasWidth = maxX + 30;
	    		if( maxY > canvasHeight ) canvasHeight = maxY + 30;
	    	}
	    }
	    /*
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
		*/
		
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

//org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype = {
//		destroy : function(){
//			$('#canvas_' + objectId).hide().appendTo('body');
//			this.icanvas.clear();
//		}
//};
/*
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.addEventGeom = function(objectId, canvas, element){
	
	var shape_id = $(element).attr("_shape_id");
	if( typeof $(element).attr("_classname") != 'undefined' &&  typeof $(element).data("activity") == 'undefined' ){
		var activityData = {__className : $(element).attr("_classname"), tracingTag : $(element).attr("_tracingTag")};
		$(element).data('activity', activityData);
	}
	$(element).droppable({
		greedy: true,		
		tolerance: 'geom',
    	over: function(event, ui){
    		console.log('over');
    	},
    	out: function(event, ui){
    		console.log('out');
    	},
		    	
		drop: function(event, ui){
    		var customData = canvas.getCustomData(element);
    		if( customData == undefined || customData == null || customData == "" ){
    			customData = [];
    		}
    		
    		var shapeType = $(this).attr("_shape");
    		if( shapeType == 'GEOM' ){	
    			var dragObj = ui.draggable.data('dragObj');
    			
    			if(dragObj){
    				switch (dragObj.__className){
    				case 'org.uengine.codi.mw3.ide.ResourceNode':
    					switch (dragObj.type) {
    					case 'java':
    						var dragObjMetadata = mw3.getMetadata(dragObj.alias);
    						canvas.drawLabel(element, dragObjMetadata.displayName);
	    		    		
	    		    		var value = mw3.objects[objectId];
	    		    		var contentValue = {
									__className : 'org.uengine.codi.mw3.webProcessDesigner.PrcsVariable',
									name : dragObjMetadata.displayName ,
									typeId : dragObj.alias ,
									variableType : 'complexType'
							};
	    		    		value.variableMap[dragObj.alias] = contentValue;	    		    			    		
	    		    		customData.push( {"customId": "" , "customName" : dragObjMetadata.displayName , "customType" : "class"});
	    		    		
	    		    		var eleClassName = $(this).attr("_classname");
	    		    		if( eleClassName == 'org.uengine.kernel.InvocationActivity'){
	    		    			var activityData = $(this).data('activity');
	    		    			activityData.resourceClass = dragObj.alias;
	    		    			$(this).data('activity', activityData);
	    		    		}
	    		    		
    						break;
    					default:
    						break;
    					} 
    					
    					break;
    				default:
    					break;
    				
    				}
    			}
    		}
    		
    		if(customData.length > 0){
    			canvas.setCustomData(element, customData);
    		}
		}
	});
	
	
	$(element).on({
		/*
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
	    			customData.push( {"customId": clipboardNode.id , "customName" : clipboardNode.name , "customType" : "wfNode"});
	    			var value = mw3.objects[objectId];
		    		var contentValue = {
							__className : 'org.uengine.codi.mw3.webProcessDesigner.PrcsVariable',
							name : clipboardNode.name ,
//							typeId : 'org/uengine/codi/mw3/knowledge/KnowledgeTool.java' ,
							typeId : clipboardNode.id ,
							variableType : 'wfNode'
					};
		    		value.variableMap[clipboardNode.name] = contentValue;
		    		var activityClass = "org.uengine.codi.activitytypes.KnowledgeActivity";
		    		$(this).attr("_classname", activityClass);
		    		var activityData = $(this).data('activity');
	    			activityData.__className = activityClass;
	    			$(this).data('activity', activityData);
	    			
//	    			value.tempElementId = $(this).attr('id');
//	    			value.tempElementName = clipboardNode.name;
//	    			value.tempElementTypeId = clipboardNode.id;
//	    			value.tempElementType = "wfNode";
//	    			value.addValiable();
	    		}else if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.model.ResourceFile"){	    			
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
//	    		    		var wfText = $(element).children('[id$=_LABEL]').text();
//	    		    		wfText = wfText + '(' + clipboardNode.name + ')';
//	    		    		canvas.drawLabel(element, wfText);
	    		    		var value = mw3.objects[objectId];
	    		    		var variableName = tokens[tokens.length-2];
	    		    		
	    		    		console.log(clipboardNode);
	    		    		
	    		    		var contentValue = {
									__className : 'org.uengine.codi.mw3.webProcessDesigner.PrcsVariable',
									name : variableName,
									typeId : clipboardNode.alias ,
									variableType : 'complexType'
							};
	    		    		value.variableMap[variableName] = contentValue;
	    		    		
//	    		    		canvas.drawLabel(element, variableName);
	    		    		
	    		    		customData.push( {"customId": "" , "customName" : variableName , "customType" : "class"});
	    		    		
//	    	    			value.tempElementId = $(this).attr('id');
//	    	    			value.tempElementName = tokens[tokens.length-2];
//	    	    			value.tempElementTypeId = clipboardNode.alias;
//	    	    			value.tempElementType = "class";
//	    	    			value.addValiable();
	    		    	}
	    			}
	    		} else if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.ide.ResourceNode"){
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
//	    		    		var wfText = $(element).children('[id$=_LABEL]').text();
//	    		    		wfText = wfText + '(' + clipboardNode.name + ')';
//	    		    		canvas.drawLabel(element, wfText);
	    		    		var value = mw3.objects[objectId];
	    		    		var variableName = tokens[tokens.length-2];
	    		    		
	    		    		console.log(clipboardNode);
	    		    		
	    		    		var contentValue = {
									__className : 'org.uengine.codi.mw3.webProcessDesigner.PrcsVariable',
									name : variableName,
									typeId : clipboardNode.alias ,
									variableType : 'complexType'
							};
	    		    		value.variableMap[variableName] = contentValue;
	    		    		
	    		    		canvas.drawLabel(element, variableName);
	    		    		
	    		    		customData.push( {"customId": "" , "customName" : variableName , "customType" : "class"});
	    		    		
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
    		if(event.stopPropagation){
    			event.stopPropagation();
    		}
    		
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
*/
/*
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.addEventEdge = function(objectId, canvas, element){
//	$(element).unbind('dblclick').bind({
//		dblclick: function (event) {
//			var value = mw3.objects[objectId]; 
//			console.log($(this).data('transition'));
//			value.tempElementId = $(this).attr('id');
//			value.tempElementName = $(this).children('[id$=_LABEL]').text();
//			value.gateCondition();
//		}
//	});
};
*/
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.canvasDropEvent = function(objectId , canvas , canvasDivObj){
	var session = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Session");
	var clipboardNode = session.clipboard;
	var object = mw3.objects[objectId];
	// 지식노드를 캔버스에 떨구었을때 
	if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.knowledge.WfNode"){
		var newNodeWidth = 70 ;
		var newNodeHeight = 50 ;
		var knolElement = canvas.drawShape([
         event.pageX - $('#canvas_' + objectId)[0].offsetLeft + $('#canvas_' + objectId)[0].scrollLeft	- $('#canvas_' + objectId).offsetParent().offset().left,
         event.pageY - $('#canvas_' + objectId)[0].offsetTop + $('#canvas_' + objectId)[0].scrollTop	- $('#canvas_' + objectId).offsetParent().offset().top ],
         new OG.shape.bpmn.A_Task(clipboardNode.name) , [parseInt(newNodeWidth, 10), parseInt(newNodeHeight, 10)]);
		var customData = [];
		customData.push( {"customId": clipboardNode.id , "customName" : clipboardNode.name , "customType" : "wfNode"});
		canvas.setCustomData(knolElement, customData);
		
		var contentValue = {
				__className : 'org.uengine.codi.mw3.webProcessDesigner.PrcsVariable',
				name : clipboardNode.name ,
				typeId : clipboardNode.id ,
				variableType : 'wfNode'
		};
		object.variableMap[clipboardNode.name] = contentValue;
			
		$(knolElement).attr("_classname", "org.uengine.codi.activitytypes.KnowledgeActivity");
		$(knolElement).attr("_classType", "Activity");
		$(knolElement).attr("_tracingTag",++faceHelper.tracingTag);
		// 그리고 난 후의 엘리먼트에 이벤트 등록
		faceHelper.addEventGeom(objectId, canvas, knolElement);
		
	}else if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.ide.libraries.OrganizationRole"){
		var shapeWidth = "300";
		var shapeHeight = "100";
		var roleclass = "org.uengine.kernel.Role";
		var	viewclass = "org.uengine.kernel.designer.web.RoleView";
		var pageX = event.pageX - $("#canvas_" + objectId)[0].offsetLeft + $("#canvas_" + objectId)[0].scrollLeft - $("#canvas_" + objectId).offsetParent().offset().left;
		var pageY = event.pageY - $('#canvas_' + objectId)[0].offsetTop + $('#canvas_' + objectId)[0].scrollTop	- $('#canvas_' + objectId).offsetParent().offset().top;
		var metaworksContext = {
			__className : "org.metaworks.MetaworksContext",
			when : "edit"
		};
		
		var role = {
				__className : roleclass,
				name : clipboardNode.name,
				metaworksContext : metaworksContext
		};
		
		var roleView = {
				__className : viewclass,
				x : pageX,
				y : pageY,
				shapeId : "OG.shape.HorizontalLaneShape",
				shapeType : "GROUP",
				classType : "Role",
				roleClass : roleclass,
				height : shapeHeight,
				width : shapeWidth,
				editorId : object.alias,
				role : role
		};
		
		var html = mw3.locateObject(roleView , roleView.____className);
		canvasDivObj.append(html);
		
		mw3.onLoadFaceHelperScript();
	
	}else if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.ide.libraries.ActivityNode"){
		var shapeWidth = "70";
		var shapeHeight = "50";
		var activityclass = clipboardNode.activity.__className;
		var	viewclass = "org.uengine.kernel.designer.web.HumanActivityView";
		var pageX = event.pageX - $("#canvas_" + objectId)[0].offsetLeft + $("#canvas_" + objectId)[0].scrollLeft - $("#canvas_" + objectId).offsetParent().offset().left;
		var pageY = event.pageY - $('#canvas_' + objectId)[0].offsetTop + $('#canvas_' + objectId)[0].scrollTop	- $('#canvas_' + objectId).offsetParent().offset().top;
		
		var activityView = {
				__className : viewclass,
				x : pageX,
				y : pageY,
				shapeId : "OG.shape.bpmn.A_Task",
				shapeType : "GEOM",
				classType : "Activity",
				activityClass : activityclass,
				height : shapeHeight,
				width : shapeWidth,
				editorId : object.alias,
				activity : clipboardNode.activity
		};
		
		var html = mw3.locateObject(activityView , activityView.____className);
		canvasDivObj.append(html);
		
		mw3.onLoadFaceHelperScript();
	}
	session.clipboard = null;
};
// TODO delete test code 
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.showValiables = function(){
	alert( mw3.pcsValiable.toXML(mw3.pcsValiable.rootElement)  );
};
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.clear = function(){
	this.icanvas.clear();
};
//TODO delete test code 
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.findSwimlane = function(parentId){
	var $parentId = $('#'+parentId);
	if( $parentId ){
		var classType = $parentId.attr('_classType');
		if( classType == 'Role'){
			return parentId;
		}else{
			return this.findSwimlane();
		}
	}
	return null;
};
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.getValue = function(){
	var graphJson = this.icanvas.toJSON();
	var ogObj = eval(graphJson.opengraph);
	var ogArr = ogObj.cell;
	var activityList = [];
	var roleList = [];
	var transitionList = [];
	var valueChainList = [];
	
	var activityIdx = 0;
	var roleIdx = 0;
	var transitionIdx = 0;
	var valueChainIdx = 0;
	
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
			if(key == '@x'){
				cellForDwr['x'] = og[key];
			}
			if(key == '@y'){
				cellForDwr['y'] = og[key];
			}
			if(key == '@width'){
				cellForDwr['width'] = og[key];
			}
			if(key == '@height'){
				cellForDwr['height'] = og[key];
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
			if(key == '@value'){
				cellForDwr['value'] = og[key];
			}
			if(key == '@style'){
				cellForDwr['style'] = og[key];
			}
		}
//		cellsForDwr[cellsForDwr.length] = cellForDwr;
		
		var $id = $('#'+og['@id']);
		if( og['@shapeType'] != 'EDGE'){
			cellForDwr['tracingTag'] = $id.attr('_tracingTag');
			cellForDwr['__className'] = $id.attr('_viewClass');
			var classname = $id.attr('_classname');
			var classType = $id.attr('_classType');
			cellForDwr['activityClass'] = classname;
			cellForDwr['classType'] = classType;
			//set Activity, Role
			var activity = $id.data('activity');
			if( activity ){
				activity.activityView = cellForDwr;
				if(classType == 'Activity'){
					// TODO 저장하는 시점에.. 휴먼엑티비티이고, parent가 없다면... 경고창을 띄워도 괜찮을듯하다.
					if( classname == 'org.uengine.kernel.HumanActivity' && cellForDwr.parent){
						var parentRoleId = this.findSwimlane(cellForDwr.parent);
						if( parentRoleId != null ){
							var role = $('#'+parentRoleId).data('role');
							activity.role = role;
						}
					}
					activityList[activityIdx++] = activity;
				}
			}
			
			//set Role
			var role = $id.data('role');
			if(role){
				role.roleView = cellForDwr;
				if(classType == 'Role'){
					roleList[roleIdx++] = role;
				}
			}
			
			//set ValueChain
			var valuechain = $id.data('valuechain');
			if(valuechain){
				valuechain.valueChainView = cellForDwr;
				if(classType == 'ValueChain'){
					valueChainList[valueChainIdx++] = valuechain;
				}
			}
		}
		if( og['@shapeType'] == 'EDGE'){
			var transition = $id.data('transition');
			if( transition ){
				transitionList[transitionIdx++] = transition;
				transition.transitionView = cellForDwr;
			}
		}
	}
	var object = mw3.objects[this.objectId];
	
	var processVariablePanel = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessVariablePanel');
	
	var container = {
			__className : 'org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContainer',
			activityList : activityList,
			transitionList : transitionList,
			valueChainList : valueChainList,
			roleList : roleList,
			processVariablePanel : processVariablePanel
	};
	object.processDesignerContainer = container;
	if( object.processNameView ){
		object.processName = $('#processName_' + object.processNameView.__objectId).val();
	}
	return object;
};


org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.startLoading = function(){
	this.divObj.trigger('startLoading');
};

org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.endLoading = function(){
	this.divObj.trigger('endLoading');
};
