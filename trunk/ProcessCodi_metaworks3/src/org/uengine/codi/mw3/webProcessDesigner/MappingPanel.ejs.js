var org_uengine_codi_mw3_webProcessDesigner_MappingPanel = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	this.linekdInfo;
	var linekdInfo = new ArrayList();
	var faceHelper = this;
	var canvas = null;
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
    
    var leftTreeId = mw3.getChildObjectId(this.objectId, 'leftTree');
    var leftTreeObj = $('#' + mw3._getObjectDivId(leftTreeId));
    
    leftTreeObj.bind('loaded', {align : 'left'}, function(event){
    	faceHelper.leftTreeLoaded = true;    	
    	faceHelper.drawTerminals(this.id, true, canvas , null);
    }).bind('expanded', function(){
    	faceHelper.drawTerminals(this.id, true, canvas , null);
    }).bind('collapsed', function(){
    	faceHelper.drawTerminals(this.id, true, canvas , null);
    });
    
    var rightTreeId = mw3.getChildObjectId(this.objectId, 'rightTree');
    var rightTreeObj = $('#' + mw3._getObjectDivId(rightTreeId));
    rightTreeObj.bind('loaded', {align : 'right'}, function(event){
    	faceHelper.rightTreeLoaded = true;
		faceHelper.drawTerminals(this.id, false, canvas , null);
    }).bind('expanded', function(){
    	faceHelper.drawTerminals(this.id, false, canvas , null);
    }).bind('collapsed', function(){
    	faceHelper.drawTerminals(this.id, false, canvas , null);
    });
    
    canvas.onConnectShape(function (event, edgeElement, fromElement, toElement) {
        console.log('connected!', fromElement.id, '--->', toElement.id);
        var linkedInfoStr = fromElement.id + "," + toElement.id;
        linekdInfo.add(linkedInfoStr);
        this.linekdInfo = linekdInfo;
    });

    canvas.onDisconnectShape(function (event, edgeElement, fromElement, toElement) {
        console.log('disconnected!', fromElement.id, '-/->', toElement.id);
        var linkedInfoStr = fromElement.id + "," + toElement.id;
        for(var i = 0; i < linekdInfo.size(); i++){
        	if( linekdInfo.get(i) == linkedInfoStr){
        		linekdInfo.remove(i);
        	}
        }
        this.linekdInfo = linekdInfo;
    });

    $(canvas.getRootElement()).bind('contextmenu', function (event) {
        var shapeElement, htmlShape = new OG.HtmlShape('<table width="70px" height="18px" border="1" cellspacing="0"><tr><td align="center">To Number</td></tr></table>');
        shapeElement = canvas.drawShape(
                [event.offsetX, event.offsetY],
                htmlShape,
                [70, 18],
                {'font-size': 10}
        );

        canvas.getEventHandler().setMovable(shapeElement, true);
    });
    
    this.icanvas = canvas;	
};

org_uengine_codi_mw3_webProcessDesigner_MappingPanel.prototype= {
		drawTerminals : function(treeDivId, isLeft , canvas, callback) {
			var treeId = null;
			if(isLeft){
				treeId = mw3.getChildObjectId(this.objectId, 'leftTree');
			}else{
				treeId = mw3.getChildObjectId(this.objectId, 'rightTree');
			}
			
			$('#' + treeDivId+' .item-fix').each(function(idx, item) {
				if(!($(this).hasClass('root'))){
					var objectId = $(this).attr('objectId');
					var object = mw3.getObject(objectId);
					var id = object.id;
					var text = object.name;
					var shapeId = (isLeft ? 'FROM_' : 'TO_') + id;
					var isView = true;
					var closedParentObjectId = mw3.getFaceHelper(treeId).getClosedParentNodes(objectId);
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
				}
			});
			
//			if(!this.loadDrawed){
//				if(this.leftTreeLoaded && this.rightTreeLoaded)
//					this.drawLine();
//			}
			
			
		},
		drawLine : function(){
			this.loadDrawed = true;
			
			var object = mw3.objects[this.objectId];
		    if( object != null && object.mapperData != null ){
		    	console.log(object.mapperData);
		    	
		    	this.icanvas.clear();
				this.icanvas.loadJSON($.parseJSON(object.mapperData));
		    }		    
		},
		drawSavedData : function(){
			// sended data role : " fromElementId , toElementId "//TODO, (in|out|inOut) " 
		},
		getValue : function(){
			var object = mw3.objects[this.objectId];
			object.mapperData = JSON.stringify(this.icanvas.toJSON());
			
			return object;
		}
};

