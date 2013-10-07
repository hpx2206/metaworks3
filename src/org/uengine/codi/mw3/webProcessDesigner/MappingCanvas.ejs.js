var org_uengine_codi_mw3_webProcessDesigner_MappingCanvas= function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	var linekedInfo = new ArrayList();
	this.linekedInfo = linekedInfo;
	var faceHelper = this;
	var canvas = null;
	
	var object = mw3.objects[this.objectId];
	if( object == null || typeof object == 'undefined'){
		this.object = {
				__objectId : this.objectId,
				__className : this.className
		};
	}else{
		this.object = object;
	}
	
	OG.shape.From = function (label) {
        OG.shape.From.superclass.call(this, label);
        this.SHAPE_ID = 'OG.shape.From';
    };
    OG.shape.From.prototype = new OG.shape.CircleShape();
    OG.shape.From.superclass = OG.shape.CircleShape;
    OG.shape.From.prototype.constructor = OG.shape.From;
    OG.From = OG.shape.From;

    OG.shape.From.prototype.createTerminal = function () {
        if (!this.geom) {
            return [];
        }

        var envelope = this.geom.getBoundary();

        return [
            new OG.Terminal(envelope.getCentroid(), OG.Constants.TERMINAL_TYPE.C, OG.Constants.TERMINAL_TYPE.OUT)
        ];
    };
    OG.shape.From.prototype.clone = function () {
        return new OG.shape.From(this.label);
    };

    // define To Shape
    OG.shape.To = function (label) {
        OG.shape.To.superclass.call(this, label);
        this.SHAPE_ID = 'OG.shape.To';
    };
    OG.shape.To.prototype = new OG.shape.CircleShape();
    OG.shape.To.superclass = OG.shape.CircleShape;
    OG.shape.To.prototype.constructor = OG.shape.To;
    OG.To = OG.shape.To;

    OG.shape.To.prototype.createTerminal = function () {
        if (!this.geom) {
            return [];
        }

        var envelope = this.geom.getBoundary();

        return [
            new OG.Terminal(envelope.getCentroid(), OG.Constants.TERMINAL_TYPE.C, OG.Constants.TERMINAL_TYPE.IN)
        ];
    };
    OG.shape.To.prototype.clone = function () {
        return new OG.shape.To(this.label);
    };

//	OG.Constants.DEFAULT_STYLE.EDGE["edge-type"] = "straight";
	
	OG.common.Constants.CANVAS_BACKGROUND = "#fff";
    OG.Constants.ENABLE_CANVAS_OFFSET = true; // Layout 사용하지 않을 경우 true 로 지정
    this.canvasId = object.canvasId;
    canvas = new OG.Canvas(this.canvasId);
    canvas._CONFIG.DEFAULT_STYLE.EDGE["edge-type"] = "straight";
    canvas.initConfig({
        selectable      : true,
        dragSelectable  : false,
        movable         : false,
        resizable       : false,
        connectable     : true,
        connectCloneable: false,
        connectRequired : true,
        labelEditable   : false,
        groupDropable   : false,
        collapsible     : false,
        enableHotKey    : true
    });
    
    this.leftTreeLoaded = false;
    this.rightTreeLoaded = false;
    this.loadDrawed = false;
    this.divId = mw3._getObjectDivId(this.objectId);
	this.divObj = $('#' + this.divId);
	// canvas는 단독으로 동작하는게 아니고 상위 화면을 가지고있기때문에 parent 로 구하여 작업한다.
	this.parentDivObj = this.divObj.parent();
	this.leftTreeId = object.leftTreeId;
	this.rightTreeId = object.rightTreeId;
    var leftTreeObj = this.parentDivObj.find("#" + this.leftTreeId );
    
    leftTreeObj.bind('loaded', {align : 'left'}, function(event){
		faceHelper.drawTerminals(this.id, true, canvas , null , false);
    }).bind('expanded', function(){
    	faceHelper.drawTerminals(this.id, true, canvas , null , false);
    }).bind('collapsed', function(){
    	faceHelper.drawTerminals(this.id, true, canvas , null , false);
    }).bind('toAppended', function(){
    	faceHelper.drawTerminals(this.id, true, canvas , null , true);
    });
    
    var rightTreeObj = this.parentDivObj.find("#"+ this.rightTreeId);
    rightTreeObj.bind('loaded', {align : 'right'}, function(event){
		faceHelper.drawTerminals(this.id, false, canvas , null , false);
    }).bind('expanded', function(){
    	faceHelper.drawTerminals(this.id, false, canvas , null , false);
    }).bind('collapsed', function(){
    	faceHelper.drawTerminals(this.id, false, canvas , null , false);
    }).bind('toAppended', function(){
    	faceHelper.drawTerminals(this.id, false, canvas , null , true);
    });
    
    canvas.onConnectShape(function (event, edgeElement, fromElement, toElement) {
//        if (console && console.log) console.log('connected!', fromElement.id, '--->', toElement.id);
        var linkedInfoStr = fromElement.id + "," + toElement.id;
        linekedInfo.add(linkedInfoStr);
        faceHelper.linekedInfo = linekedInfo;
    });

    canvas.onDisconnectShape(function (event, edgeElement, fromElement, toElement) {
//        if (console && console.log) console.log('disconnected!', fromElement.id, '-/->', toElement.id);
        var linkedInfoStr = fromElement.id + "," + toElement.id;
        for(var i = 0; i < linekedInfo.size(); i++){
        	if( linekedInfo.get(i) == linkedInfoStr){
        		linekedInfo.remove(i);
        	}
        }
        faceHelper.linekedInfo = linekedInfo;
    });
    
    this.icanvas = canvas;	
    // TODO 추후 삭제 - 임시로직 ( getValue 가 여러번 호출되기때문에 한번만 호출을 해주도록 임시변수 셋팅함
    this.callCount = 1;
};

org_uengine_codi_mw3_webProcessDesigner_MappingCanvas.prototype = {
		drawTerminals : function(treeDivId, isLeft , canvas, callback, isAppend) {
			var treeObjectId = null;
			if(isLeft){
				treeObjectId = this.parentDivObj.find("#"+this.leftTreeId +" .filemgr-tree").attr('objectId');
			}else{
				treeObjectId = this.parentDivObj.find("#"+this.rightTreeId +" .filemgr-tree").attr('objectId');
			}
			var leftTreeLoding = this.leftTreeLoaded;
			var rightTreeLoding = this.rightTreeLoaded;
			var canvasId = this.canvasId;
			$('#' + treeDivId+' .item-fix').each(function(idx, item) {
				if(!($(this).hasClass('root'))){
					var objectId = $(this).attr('objectId');
					var object = mw3.objects[objectId];
					var id = object.id;
					var shapeId = (isLeft ? 'FROM_' : 'TO_') + id;
					var isView = true;
					var closedParentObjectId = mw3.getFaceHelper(treeObjectId).getClosedParentNodes(objectId);
					var closedParentObject = mw3.objects[closedParentObjectId];
					// 동적으로 새롭게 붙은 노드들은 무조건 도형을 그려야 하기때문에 isAppend 를 체크하여줌
					if( object.type != 'folder' && closedParentObject != null && !isAppend){
						isView = false;
					}
					
					if( isView ){
						var shapeElement = canvas.drawShape(
		                    [(isLeft ? 5 : 275), ( $(this).offset().top - $('#'+canvasId).offset().top ) + item.offsetHeight / 2],
		                    (isLeft ? new OG.From() : new OG.To()),
		                    [5, 5],
		                    {},
		                    shapeId
						);

						edgeIds = $(shapeElement).attr(isLeft ? "_toedge" : "_fromedge");
						if (edgeIds) {
							$.each(edgeIds.split(","), function (indx, edgeId) {
								edge = canvas.getElementById(edgeId);
								edge.shape.geom.style.map['stroke-dasharray'] = '';
							});
						}
						$(shapeElement).click("destroy");
						canvas.removeAllGuide();
						canvas.redrawConnectedEdge(shapeElement);
						canvas.show(shapeElement);
					}else{
						var shapeElement = canvas.getElementById(shapeId);
						if (shapeElement) {
							var parentNode = $('.item-fix[objectId='+ closedParentObjectId+']');
							shapeElement = canvas.drawShape(
									[(isLeft ? 5 : 275), ( parentNode.offset().top - $('#'+canvasId).offset().top ) + parentNode[0].offsetHeight / 2],
									(isLeft ? new OG.From() : new OG.To()),
									[5, 5],
									{},
									shapeId
							);

							edgeIds = $(shapeElement).attr(isLeft ? "_toedge" : "_fromedge");
							if (edgeIds) {
								$.each(edgeIds.split(","), function (indx, edgeId) {
									edge = canvas.getElementById(edgeId);
									edge.shape.geom.style.map['stroke-dasharray'] = '--';
								});
							}
							$(shapeElement).click("destroy");
							canvas.removeAllGuide();
							canvas.redrawConnectedEdge(shapeElement);
							canvas.hide(shapeElement);
						}
					}
					// load triger 가 트리를 처음 그릴때 한번 타고, faceHelper를 모두 태운뒤에 한번더 이벤트가 온다
					// 두번째 이벤트에서 모두 트리를 그릴때 이 each문을 타기때문에 트리를 모두 그린후에 loaded를 체크해준다.
					if(isLeft && !leftTreeLoding){
						leftTreeLoding = true;
					}else if(!isLeft && !rightTreeLoding) {
						rightTreeLoding = true;
					}
				}
			});
			this.leftTreeLoaded = leftTreeLoding;
			this.rightTreeLoaded = rightTreeLoding;
			if(!this.loadDrawed){
				if(leftTreeLoding && rightTreeLoding){
					this.drawLine();
				}
			}
			// sended data role : " fromElementId , toElementId "//TODO, (in|out|inOut) " 
		},
		drawUnExtendedTerminal : function(shapeId , isLeft , parentElement){
			var canvas = this.icanvas;
			var shapeElement = canvas.drawShape(
					[(isLeft ? 5 : 275), ($(parentElement).offset().top - $('#'+this.canvasId).offset().top ) + 4 ],
					(isLeft ? new OG.From() : new OG.To()),
					[5, 5],
					{},
					shapeId
			);

			$(shapeElement).click("destroy");
			canvas.removeAllGuide();
			canvas.hide(shapeElement);
		},
		drawLine : function(){
			this.loadDrawed = true;
			var elements = this.object.mappingElements;
			if(typeof elements == 'undefined'){
				return;
			}
			if( elements != null ){
				for(var i = 0; i < elements.length; i++){
					var toId = 'TO_' + elements[i].argument.text;
					var fromId = 'FROM_' + elements[i].variable.name;
					// "." 을 "-" 로 변경
					toId = toId.replace(/\./gi, "-");
					fromId = fromId.replace(/\./gi, "-");
					
//					console.log('fromId = ' + fromId);
//					console.log('toId = ' + toId);
					
					var fromShape = this.icanvas.getElementById(fromId);
					var toShape = this.icanvas.getElementById(toId);
					// 동적으로 생성되는 노드는 로드 시점에 아직 안그려져 있을수 있다.
					if( typeof(fromShape) == "undefined" || fromShape == null){
						var parentElement = this.findParentNode(fromId);
						// 선이 이어진 도형만 그린 후에 선을 연결시키고 숨겨준다. 
						this.drawUnExtendedTerminal(fromId, true, parentElement);
						fromShape = this.icanvas.getElementById(fromId);
					}
					if( typeof(toShape) == "undefined" || toShape == null){
						var parentElement = this.findParentNode(toId);
						this.drawUnExtendedTerminal(toId, false, parentElement);
						toShape = this.icanvas.getElementById(toId);
					}
					// 선을 그린다.
					this.icanvas.connect(fromShape, toShape);
				}
			}
		},
		findParentNode : function(nodeId){
			var parentNodeId = nodeId.substring(0, nodeId.lastIndexOf("-") );
			var shapeNode = this.icanvas.getElementById(parentNodeId);
			if( shapeNode == null ){
				return this.findParentNode(parentNodeId);
			}else{
				return shapeNode;
			}
		},
		getValue : function(){
			if( this.callCount == 1 ){
				var returnObject = this.object;
				returnObject.mappingElements = [];
				for(var i = 0; i < this.linekedInfo.size(); i++){
					var linkedInfoStr = this.linekedInfo.get(i);
					var fromId = '';
					var toId = '';
					if( linkedInfoStr != null ){
						var tempStr = linkedInfoStr.split(","); 
						fromId 	= tempStr[0];
						toId 		= tempStr[1];
						if (console && console.log) console.log('fromId = ' +fromId );
						if (console && console.log) console.log('toId = ' +toId );
						// argument : 받은 변수정보 => toId
						// variable : 준 변수 정보 => formId
						var fromText = fromId.substring(5);
						var toText = toId.substring(3);
						if (console && console.log) console.log('fromText = ' +fromText );
						if (console && console.log) console.log('toText = ' +toText );
						fromText = fromText.replace(/-/gi, ".");
						toText = toText.replace(/-/gi, ".");
						var processValiable = {
								__className : 'org.uengine.kernel.ProcessVariable',
								name : fromText
						};
						var argumentText = {
								__className : 'org.uengine.contexts.TextContext',
								text : toText
						};
						var mappingElement = {
							__className : 'org.uengine.kernel.MappingElement',
							direction : 'in'	,
							argument : argumentText,
							variable : processValiable
						};
						
						returnObject.mappingElements.push(mappingElement);
					}
				}
				this.object = returnObject;
				this.callCount = -1;
			}
			return this.object;
		}
};