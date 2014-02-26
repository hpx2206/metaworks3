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
	
	this.divObj.addClass('mw3_resize');
	
	var object = mw3.objects[this.objectId];
	
	if(object){
		mw3.importScript('scripts/jquery/jquery.contextMenu.js');
		mw3.importStyle('style/jquery/jquery.contextMenu.css');
//		mw3.importScript('scripts/jquery/jquery-tooltip.js');
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
	            	if( typeof activityclass == 'undefined' ){
	            		viewclass = "org.uengine.kernel.designer.web.GraphicView";
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
				var fromTrcingTag = $(fromElement).attr('_tracingTag');
				var toTrcingTag = $(toElement).attr('_tracingTag');
				var transitionView;
				if( fromTrcingTag && toTrcingTag ){
					// from과 to 가 모두 엑티비티인 경우만 transition 으로 데이터를 넣는다.
		    		transitionView = {
							__className : 'org.uengine.kernel.designer.web.TransitionView',
							drawByCanvas : true,
							editorId : object.alias,
							element : edgeElement
		    		};
				}else{
					transitionView = {
                            __className : 'org.uengine.kernel.designer.web.GraphicView',
                            editorId : object.alias,
							drawByCanvas : true,
							shapeType : 'EDGE',
							element : edgeElement
                    };
				}
	    		var html = mw3.locateObject(transitionView , transitionView.____className);
            	canvasDivObj.append(html);
            	
            	mw3.onLoadFaceHelperScript();
	    	}
	    });
	    
	    canvas.onDrawShape(function (event, shapeElement) {
	    	// TODO 여기서 Shape 객체를 바로 만드려고 하였으나 연결정보는 가지고 있지 않다.
	    	if($(shapeElement).attr('auto_draw') && $(shapeElement).attr('auto_draw') == 'yes'){
	    		if( shapeElement.shape instanceof OG.shape.bpmn.A_Task ){
	    			$(shapeElement).attr('_width', '120');
	    			$(shapeElement).attr('_height', '50');
	    			$(shapeElement).attr('_classname', 'org.uengine.kernel.HumanActivity');
	    			$(shapeElement).attr('_viewclass', 'org.uengine.kernel.designer.web.HumanActivityView');
	    		}else if( shapeElement.shape instanceof OG.shape.bpmn.E_End ){
	    			$(shapeElement).attr('_width', '30');
	    			$(shapeElement).attr('_height', '30');
	    			$(shapeElement).attr('_classname', 'org.uengine.kernel.EndActivity');
	    			$(shapeElement).attr('_viewclass', 'org.uengine.kernel.designer.web.ActivityView');
	    		}
	    		$(shapeElement).attr('_classType', 'Activity');
		    	$(shapeElement).attr('_shape_type', 'GEOM');
		    	$(shapeElement).attr('_tracingTag', ++faceHelper.tracingTag);
		    	
		    	var activityView = {
						__className : $(shapeElement).attr('_viewclass'),
						element : shapeElement,
						editorId : object.alias,
						activityClass : $(shapeElement).attr('_classname'),
						drawByCanvas : true
		    	};
		    	
		    	var html = mw3.locateObject(activityView , activityView.__className);
		    	canvasDivObj.append(html);
	        	
	        	mw3.onLoadFaceHelperScript();
	    	}
        });
	    canvas.onRemoveShape(function (event, shapeElement) {
//			console.log($(shapeElement));
	    });
	    canvas.onBeforeRemoveShape(function (event, shapeElement) {
			if( shapeElement.shape &&  shapeElement.shape.TYPE == 'EDGE' ){
				var getShapeFromTerminal = function (terminal) {
	                var terminalId = OG.Util.isElement(terminal) ? terminal.id : terminal;
	                if (terminalId) {
	                    return canvas.getRenderer().getElementById(terminalId.substring(0, terminalId.indexOf(OG.Constants.TERMINAL_SUFFIX.GROUP)));
	                } else {
	                    return null;
	                }
	            };
				var fromElement = getShapeFromTerminal($(shapeElement).attr("_from"));
	            var toElement = getShapeFromTerminal($(shapeElement).attr("_to"));
	            var fromElementId = $(fromElement).attr('id');
	            var toElementId = $(toElement).attr('id');
				$(fromElement).attr('_conneted'+fromElementId,'');
				$(toElement).attr('_conneted'+toElementId,'');
			}
	    });
	    canvas.onLabelChanged(function (event, shapeElement, afterText, beforeText) {
	    	// TODO 스윔레인에 text가 써졌을때, 바로 role 추가하는 로직 생성
	    });
	    var canvasWidth = document.getElementById('canvas_' + objectId).getBoundingClientRect().width;		// defualt
	    var canvasHeight = document.getElementById('canvas_' + objectId).getBoundingClientRect().height;		// defualt
	    
	    if( object != null ){
	    	this.tracingTag = object.processDesignerContainer.lastTracingTag;
	    	var maxX = object.processDesignerContainer.maxX;
	    	var maxY = object.processDesignerContainer.maxY;
	    	if( maxX && maxY ){
	    		if( maxX > canvasWidth ) canvasWidth = maxX + 30;
	    		if( maxY > canvasHeight ) canvasHeight = maxY + 30;
	    	}
	    }
		
		this.icanvas.setCanvasSize([canvasWidth, canvasHeight]);		
		
		$('.leftMenuTab_variable_'+objectId).dblclick(function(){
			var processVariablePanel = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessVariablePanel@'+object.alias);
			processVariablePanel.showWholeVariable();
		});
		$('.leftMenuTab_role_'+objectId).dblclick(function(){
			var rolePanel = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.RolePanel@'+object.alias);
			rolePanel.showWholeRole();
		});
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

org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.canvasDropEvent = function(objectId , canvas , canvasDivObj){
	var session = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Session");
	var clipboardNode = session.clipboard;
	var object = mw3.objects[objectId];
	// 지식노드를 캔버스에 떨구었을때 
	if(clipboardNode && clipboardNode.__className=="org.uengine.codi.mw3.knowledge.WfNode"){
		var newNodeWidth = 70 ;
		var newNodeHeight = 50 ;
    	var activityclass = "org.uengine.codi.activitytypes.KnowledgeActivity";
		var	viewclass = "org.uengine.kernel.designer.web.ActivityView";
		
    	var pageX = event.pageX - $("#canvas_" + objectId)[0].offsetLeft + $("#canvas_" + objectId)[0].scrollLeft - $("#canvas_" + objectId).offsetParent().offset().left;
    	var pageY = event.pageY - $('#canvas_' + objectId)[0].offsetTop + $('#canvas_' + objectId)[0].scrollTop	- $('#canvas_' + objectId).offsetParent().offset().top;
    	var activityView = {
				__className : viewclass,
				x : pageX,
				y : pageY,
				shapeId : 'OG.shape.bpmn.A_Task',
				shapeType : 'GEOM',
				classType : activityclass,
				activityClass : activityclass,
				height : newNodeHeight,
				width : newNodeWidth,
				tracingTag : ++faceHelper.tracingTag,
				editorId : object.alias
		};
    	
    	var html = mw3.locateObject(activityView , activityView.____className);
    	canvasDivObj.append(html);
    	
    	mw3.onLoadFaceHelperScript();
		
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
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.findSwimlane = function(parentId){
	var $parentId = $('#'+parentId);
	if( $parentId ){
		var classType = $parentId.attr('_classType');
		if( classType == 'Role'){
			return parentId;
		}else{
			// parent를 찾아가는 로직이 필요함...
			//return this.findSwimlane();
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
	var graphicList = [];
	var graphicIdx = 0;
	
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
			if(key == '@swimlane'){
				cellForDwr['swimlane'] = og[key];
			}
			if(key == '@childs'){
				cellForDwr['childs'] = og[key];
			}
		}
		var $id = $('#'+og['@id']);
		var viewClass = $id.attr('_viewClass');
		// 그래픽 정보만 저장을 할 경우
		if( viewClass == 'org.uengine.kernel.designer.web.GraphicView' ){
			graphicList[graphicIdx++] = cellForDwr;
        }else{
			cellForDwr['__className'] = viewClass;
			if( og['@shapeType'] != 'EDGE'){
				var tracingTag = $id.attr('_tracingTag');
				cellForDwr['tracingTag'] = tracingTag;
				cellForDwr['activityClass'] = $id.attr('_classname');
				cellForDwr['classType'] = $id.attr('_classType');
				// set Activity
				activityList = this.activitySetting($id, cellForDwr, activityList);
				// set ValueChain
				valueChainList = this.valueChainSetting($id, cellForDwr, valueChainList);
				// set Role
				roleList     = this.roleSetting($id, cellForDwr, roleList);
			}
			if( og['@shapeType'] == 'EDGE'){
				transitionList = this.transitionSetting($id, cellForDwr, transitionList);
			}
		}
	}
	
	this.activityValidate(activityList, transitionList);
	
	var object = mw3.objects[this.objectId];
	var processVariablePanel = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessVariablePanel@'+object.alias);
	
	var container = {
			__className : 'org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContainer',
			activityList : activityList,
			transitionList : transitionList,
			valueChainList : valueChainList,
			roleList : roleList,
			graphicList : graphicList,
			processVariablePanel : processVariablePanel
	};
	
	object.processDesignerContainer = container;
	if( object.processNameView ){
		object.processName = $('#processName_' + object.processNameView.__objectId).val();
	}
	return object;
};

org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.activitySetting = function(idDiv, cellForDwr, list){
	var activity = idDiv.data('activity');
    if( activity ){
		var classname = idDiv.attr('_classname');
        var classType = idDiv.attr('_classType');
        activity.activityView = cellForDwr;
        if(classType == 'Activity' ){
            if (classname == 'org.uengine.kernel.HumanActivity' || classname == 'org.uengine.codi.activitytypes.KnowledgeActivity') {
				activity.role = null;       // role 정보를 초기화
				if (cellForDwr.swimlane) {
					var parentRoleId = this.findSwimlane(cellForDwr.swimlane);
					if (parentRoleId != null) {
						var role = $('#' + parentRoleId).data('role');
						activity.role = role;
					}
				}
			}else if (classname == 'org.uengine.kernel.ScopeActivity') {
				activity.childActivities = [];     // child를 초기화
                activity.transitions = [];         // transition 을 초기화
			}
            if(cellForDwr.parent){
                // 스콥에만 해당시키고 definition 에는 포함안시키기 위하여 
                // 음.. 이렇게 하면 scope 안쪽의 humanActivity 에 롤이 셋팅 안되는 경우가 발생할수 있을듯한데..
				return list;
            }
            if(cellForDwr.childs){
                if (classname == 'org.uengine.kernel.ScopeActivity') {
                    var childLen = 0;
					var childTransitionIdx = 0;
                    for(var k = 0; k < cellForDwr.childs.length; k++){
                        var $childId = $('#'+cellForDwr.childs[k]);
                        var childClassType = $childId.attr('_classType');
						var childShapType = $childId.attr('_shape');
                        if(childShapType != 'EDGE' && childClassType == 'Activity') {
                            activity.childActivities[childLen++] = $childId.data('activity');
                        }
						if(childShapType == 'EDGE') {
							if( $childId.data('transition') ){
	                            activity.transitions[childTransitionIdx++] = $childId.data('transition');
							}
						}
                    }
                }
            }
            list[list.length] = activity;
        }
    }
	return list;
};
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.roleSetting = function(idDiv, cellForDwr, list){
	var role = idDiv.data('role');
    if(role){
        role.roleView = cellForDwr;
		var classType = idDiv.attr('_classType');
        if(classType == 'Role'){
            list[list.length] = role;
        }
    }
	return list;
};
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.valueChainSetting = function(idDiv, cellForDwr, list){
	var valuechain = idDiv.data('valuechain');
    if(valuechain){
        valuechain.valueChainView = cellForDwr;
		var classType = idDiv.attr('_classType');
        if(classType == 'ValueChain'){
            list[list.length] = valuechain;
        }
    }
	return list;
};
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.transitionSetting = function(idDiv, cellForDwr, list){
	var transition = idDiv.data('transition');
    if( transition ){
        transition.transitionView = cellForDwr;
		if (!cellForDwr.parent) {
			// parent가 있는 경우는 parent 에서 transition을 넣어준다.
            list[list.length] = transition;		
		}
    }
	return list;
};
/**
 * 1. 휴먼엑티비티에는 무조건 롤이 포함되어야 한다.
 * 2. 스콥엑티비티에는 inCommingTransition 이 무조건 있어야 한다.
 * 3. 서브프로세스는 서브프로세스가 들어있어야한다.
 * 4. invocationActivity에는 resourceClass가 있어야 한다.
 * @param {Object} activityList
 * @param {Object} transitionList
 */
org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.activityValidate = function(activityList, transitionList){
	var scopeActivity = null;
	var subprocessActivity = null;
	var invocationActivity = null;
	
	var transitionSource = new HashMap();
	var transitionTarget = new HashMap();
	for (var o = 0; o < transitionList.length; o++) {
	   transitionSource.put(transitionList[o].source , transitionList[o]);
	   transitionTarget.put(transitionList[o].target , transitionList[o]);
	}
	
	for(var i=0; i < activityList.length; i++){
		var activity = activityList[i];
//		console.log(activity);
		var classname = activity.__className;
		if (classname == 'org.uengine.kernel.HumanActivity' || classname == 'org.uengine.codi.activitytypes.KnowledgeActivity') {
			if( activity.role == null ){
				var actViewObj = mw3.getAutowiredObject(activity.activityView.__className +'@'+activity.activityView.id);
				 actViewObj.__faceHelper.validation('emptyRole');
			}
		}
		if( classname == 'org.uengine.kernel.ScopeActivity'){
			if( !transitionTarget.containsKey(activity.tracingTag) ){
				var actViewObj = mw3.getAutowiredObject(activity.activityView.__className +'@'+activity.activityView.id);
                actViewObj.__faceHelper.validation(' scopeActivity can not execute! ');
			}
			// 스콥안쪽에 차일드가 있는지 재귀호출로 체크를 하여준다.
			this.activityValidate(activity.childActivities, activity.transitions);
		}
		if (classname == 'org.uengine.kernel.SubProcessActivity') {
		    if( !activity.definitionId || activity.definitionId == "" || activity.definitionId == null ){
				var actViewObj = mw3.getAutowiredObject(activity.activityView.__className +'@'+activity.activityView.id);
                actViewObj.__faceHelper.validation(' SubProcess definitionId is null ');
			}
		}
		if (classname == 'org.uengine.kernel.InvocationActivity') {
		    if( !activity.resourceClass || activity.resourceClass == "" || activity.resourceClass == null ){
                var actViewObj = mw3.getAutowiredObject(activity.activityView.__className +'@'+activity.activityView.id);
                actViewObj.__faceHelper.validation(' InvocationActivity resourceClass is null ');
            }
		}
	}
	
};

org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.startLoading = function(){
	this.divObj.trigger('startLoading');
};

org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.endLoading = function(){
	this.divObj.trigger('endLoading');
};

org_uengine_codi_mw3_webProcessDesigner_ProcessDesignerContentPanel.prototype.resize = function(){

	var isChange = false;
	var tempWidth = mw3.canvas.getRootBBox().width;
	var tempHeight = mw3.canvas.getRootBBox().height;
	
	var canvasWidth = document.getElementById('canvas_' + this.objectId).getBoundingClientRect().width;
    var canvasHeight = document.getElementById('canvas_' + this.objectId).getBoundingClientRect().height;
    
    if(tempWidth < canvasWidth){
    	isChange = true;
    	tempWidth = canvasWidth;
	}
    if(tempHeight < canvasHeight){
    	isChange = true;
    	tempHeight = canvasHeight;
	}
    			
    if(isChange){
		mw3.canvas.setCanvasSize([tempWidth, tempHeight]);
	}
 
};
