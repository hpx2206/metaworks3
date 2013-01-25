var org_uengine_contexts_MappingContext = function(objectId, className){
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

	OG.Constants.DEFAULT_STYLE.EDGE["edge-type"] = "straight";
	
	OG.common.Constants.CANVAS_BACKGROUND = "#fff";
    OG.Constants.ENABLE_CANVAS_OFFSET = true; // Layout 사용하지 않을 경우 true 로 지정
    canvas = new OG.Canvas('canvas2');
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
    var leftTreeObj = this.divObj.find("#tree1");
    
    leftTreeObj.bind('loaded', {align : 'left'}, function(event){
		faceHelper.drawTerminals(this.id, true, canvas , null);
    }).bind('expanded', function(){
    	faceHelper.drawTerminals(this.id, true, canvas , null);
    }).bind('collapsed', function(){
    	faceHelper.drawTerminals(this.id, true, canvas , null);
    });
    
    var rightTreeObj = this.divObj.find("#tree2");
    rightTreeObj.bind('loaded', {align : 'right'}, function(event){
		faceHelper.drawTerminals(this.id, false, canvas , null);
    }).bind('expanded', function(){
    	faceHelper.drawTerminals(this.id, false, canvas , null);
    }).bind('collapsed', function(){
    	faceHelper.drawTerminals(this.id, false, canvas , null);
    });
    
    canvas.onConnectShape(function (event, edgeElement, fromElement, toElement) {
        console.log('connected!', fromElement.id, '--->', toElement.id);
        var linkedInfoStr = fromElement.id + "," + toElement.id;
        linekedInfo.add(linkedInfoStr);
        faceHelper.linekedInfo = linekedInfo;
    });

    canvas.onDisconnectShape(function (event, edgeElement, fromElement, toElement) {
        console.log('disconnected!', fromElement.id, '-/->', toElement.id);
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

org_uengine_contexts_MappingContext.prototype = {
		drawTerminals : function(treeDivId, isLeft , canvas, callback) {
			var treeObjectId = null;
			if(isLeft){
				treeObjectId = this.divObj.find("#tree1 .filemgr-tree").attr('objectId');
			}else{
				treeObjectId = this.divObj.find("#tree2 .filemgr-tree").attr('objectId');
			}
			var leftTreeLoding = this.leftTreeLoaded;
			var rightTreeLoding = this.rightTreeLoaded;
			$('#' + treeDivId+' .item-fix').each(function(idx, item) {
				if(!($(this).hasClass('root'))){
					var objectId = $(this).attr('objectId');
					var object = mw3.objects[objectId];
					var id = object.id;
					var shapeId = (isLeft ? 'FROM_' : 'TO_') + id;
					var isView = true;
					var closedParentObjectId = mw3.getFaceHelper(treeObjectId).getClosedParentNodes(objectId);
					var closedParentObject = mw3.objects[closedParentObjectId];
					if( object.type != 'folder' && closedParentObject != null ){
						isView = false;
					}
					
					if( isView ){
						var shapeElement = canvas.drawShape(
		                    [(isLeft ? 5 : 295), ( $(this).offset().top - $('#canvas2').offset().top ) + item.offsetHeight / 2],
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
									[(isLeft ? 5 : 295), ( parentNode.offset().top - $('#canvas2').offset().top ) + parentNode[0].offsetHeight / 2],
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
		drawLine : function(){
			this.loadDrawed = true;
			var elements = this.object.mappingElements;
			if(typeof elements == 'undefined'){
				return;
			}
			for(var i = 0; i < elements.length; i++){
				var toId = elements[i].argument.text;
				var fromId = elements[i].variable.name;
				console.log('fromId ============ ' +fromId );
				console.log('toId ========== ' +toId );
				var fromShape = this.icanvas.getElementById(fromId);
				var toShape = this.icanvas.getElementById(toId);
				this.icanvas.connect(fromShape, toShape);
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
						console.log('fromId = ' +fromId );
						console.log('toId = ' +toId );
						// argument : 받은 변수정보 => toId
						// variable : 준 변수 정보 => formId
						
						var processValiable = {
								__className : 'org.uengine.kernel.ProcessVariable',
								name : fromId
						};
						var argumentText = {
								__className : 'org.uengine.contexts.TextContext',
								text : toId
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