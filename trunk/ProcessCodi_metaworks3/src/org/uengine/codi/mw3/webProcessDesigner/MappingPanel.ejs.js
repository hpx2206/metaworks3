var org_uengine_codi_mw3_webProcessDesigner_MappingPanel = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	var faceHelper = this;
	var canvas = null;
	
	OG.shape.From = function (label) {

	    this.SHAPE_ID = 'OG.shape.From';

	    this.label = label;

	    /**
	     * Shape 간의 연결을 위한 Terminal 을 반환한다.
	     *
	     * @return {OG.Terminal[]} Terminal
	     * @override
	     */
	    this.createTerminal = function () {
	        if (!this.geom) {
	            return [];
	        }

	        var envelope = this.geom.getBoundary();

	        return [
	            new OG.Terminal(envelope.getCentroid(), OG.Constants.TERMINAL_TYPE.C, OG.Constants.TERMINAL_TYPE.OUT)
	        ];
	    };

	    /**
	     * 드로잉할 Shape 을 생성하여 반환한다.
	     *
	     * @return {OG.geometry.Geometry} Shape 정보
	     * @override
	     */
	    this.createShape = function () {
	        if (this.geom) {
	            return this.geom;
	        }

	        this.geom = new OG.geometry.Circle([50, 50], 50);
	        return this.geom;
	    };

	    /**
	     * Shape 을 복사하여 새로인 인스턴스로 반환한다.
	     *
	     * @return {OG.shape.IShape} 복사된 인스턴스
	     * @override
	     */
	    this.clone = function () {
	        return new OG.shape.From(this.label);
	    };
	};
	OG.shape.From.prototype = new OG.shape.CircleShape();
	OG.shape.From.prototype.constructor = OG.shape.From;
	OG.From = OG.shape.From;

	OG.shape.To = function (label) {

	    this.SHAPE_ID = 'OG.shape.To';

	    this.label = label;

	    /**
	     * Shape 간의 연결을 위한 Terminal 을 반환한다.
	     *
	     * @return {OG.Terminal[]} Terminal
	     * @override
	     */
	    this.createTerminal = function () {
	        if (!this.geom) {
	            return [];
	        }

	        var envelope = this.geom.getBoundary();

	        return [
	            new OG.Terminal(envelope.getCentroid(), OG.Constants.TERMINAL_TYPE.C, OG.Constants.TERMINAL_TYPE.IN)
	        ];
	    };

	    /**
	     * 드로잉할 Shape 을 생성하여 반환한다.
	     *
	     * @return {OG.geometry.Geometry} Shape 정보
	     * @override
	     */
	    this.createShape = function () {
	        if (this.geom) {
	            return this.geom;
	        }

	        this.geom = new OG.geometry.Circle([50, 50], 50);
	        return this.geom;
	    };

	    /**
	     * Shape 을 복사하여 새로인 인스턴스로 반환한다.
	     *
	     * @return {OG.shape.IShape} 복사된 인스턴스
	     * @override
	     */
	    this.clone = function () {
	        return new OG.shape.To(this.label);
	    };
	};
	OG.shape.To.prototype = new OG.shape.CircleShape();
	OG.shape.To.prototype.constructor = OG.shape.To;
	OG.To = OG.shape.To;

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
    /*
    $("#tree1").jstree({
        "core"     : {
            "animation": 0
        },
        "themes"   : {
            "theme": "classic",
            "dots" : false,
            "icons": true
        },
        "plugins"  : [ "themes", "json_data" ],
        "json_data": {
            "data": [
                {
                    "data"    : "getSchemaLocation",
                    "attr"    : { "id": "tree1_1" },
                    "state"   : "open",
                    "children": [
                        {
                            "data"    : "Variables",
                            "attr"    : { "id": "tree1_2" },
//                            "state"   : "open",
                            "children": [
                                {
                                    "data": "Duration",
                                    "attr": { "id": "tree1_21" }
                                },
                                {
                                    "data": "notes",
                                    "attr": { "id": "tree1_22" }
                                },
                                {
                                    "data": "startDate",
                                    "attr": { "id": "tree1_23" }
                                }
                            ]
                        },
                        {
                            "data"    : "Roles",
                            "attr"    : { "id": "tree1_3" },
                            "state"   : "open",
                            "children": [
                                {
                                    "data": "Initiator",
                                    "attr": { "id": "tree1_31" }
                                }
                            ]
                        },
                        {
                            "data"    : "Instance",
                            "attr"    : { "id": "tree1_4" },
                            "state"   : "open",
                            "children": [
                                {
                                    "data": "instanceId",
                                    "attr": { "id": "tree1_41" }
                                },
                                {
                                    "data": "name",
                                    "attr": { "id": "tree1_42" }
                                },
                                {
                                    "data": "locale",
                                    "attr": { "id": "tree1_43" }
                                }
                            ]
                        },
                        {
                            "data"    : "Activities",
                            "attr"    : { "id": "tree1_5" },
                            "state"   : "open",
                            "children": [
                                {
                                    "data"    : "일정",
                                    "attr"    : { "id": "tree1_51" },
                                    "state"   : "open",
                                    "children": [
                                        {
                                            "data": "startedTime",
                                            "attr": { "id": "tree1_511" }
                                        },
                                        {
                                            "data": "endTime",
                                            "attr": { "id": "tree1_512" }
                                        },
                                        {
                                            "data": "dueDate",
                                            "attr": { "id": "tree1_513" }
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    }).bind('loaded.jstree',function (event, data) {
    			faceHelper.drawTerminal('tree1', true, canvas, function(){faceHelper.drawLine();});
            }).bind('after_open.jstree',function (event, data) {
            	faceHelper.drawTerminal('tree1', true, canvas);
            }).bind('after_close.jstree', function (event, data) {
            	faceHelper.drawTerminal('tree1', true, canvas);
            });

    $("#tree2").jstree({
        "core"     : {
            "rtl"      : true,
            "animation": 0
        },
        "themes"   : {
            "theme": "classic",
            "dots" : false,
            "icons": true
        },
        "plugins"  : [ "themes", "json_data" ],
        "json_data": {
            "data": [
                {
                    "data"    : "getSchemaLocation",
                    "attr"    : { "id": "tree2_1" },
                    "state"   : "open",
                    "children": [
                        {
                            "data"    : "Variables",
                            "attr"    : { "id": "tree2_2" },
                            "state"   : "open",
                            "children": [
                                {
                                    "data": "Duration",
                                    "attr": { "id": "tree2_21" }
                                },
                                {
                                    "data": "notes",
                                    "attr": { "id": "tree2_22" }
                                },
                                {
                                    "data": "startDate",
                                    "attr": { "id": "tree2_23" }
                                }
                            ]
                        },
                        {
                            "data"    : "Roles",
                            "attr"    : { "id": "tree2_3" },
                            "state"   : "open",
                            "children": [
                                {
                                    "data": "Initiator",
                                    "attr": { "id": "tree2_31" }
                                }
                            ]
                        },
                        {
                            "data"    : "Instance",
                            "attr"    : { "id": "tree2_4" },
                            "state"   : "open",
                            "children": [
                                {
                                    "data": "instanceId",
                                    "attr": { "id": "tree2_41" }
                                },
                                {
                                    "data": "name",
                                    "attr": { "id": "tree2_42" }
                                },
                                {
                                    "data": "locale",
                                    "attr": { "id": "tree2_43" }
                                }
                            ]
                        },
                        {
                            "data"    : "Activities",
                            "attr"    : { "id": "tree2_5" },
                            "state"   : "open",
                            "children": [
                                {
                                    "data"    : "일정",
                                    "attr"    : { "id": "tree2_51" },
                                    "state"   : "open",
                                    "children": [
                                        {
                                            "data": "startedTime",
                                            "attr": { "id": "tree2_511" }
                                        },
                                        {
                                            "data": "endTime",
                                            "attr": { "id": "tree2_512" }
                                        },
                                        {
                                            "data": "dueDate",
                                            "attr": { "id": "tree2_513" }
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    }).bind('loaded.jstree',function (event, data) {
    			faceHelper.drawTerminal('tree2', false, canvas, function(){faceHelper.drawLine();});
            }).bind('after_open.jstree',function (event, data) {
            	faceHelper.drawTerminal('tree2', false, canvas , null);
            }).bind('after_close.jstree', function (event, data) {
            	faceHelper.drawTerminal('tree2', false, canvas , null );
            });
	*/
    
    
    this.leftTreeLoaded = false;
    this.rightTreeLoaded = false;
    
    var leftTreeId = mw3.getChildObjectId(this.objectId, 'leftTree');
    
    console.log( $('#' + mw3._getObjectDivId(leftTreeId)) );
    
    $('#' + mw3._getObjectDivId(leftTreeId)).bind('loaded', function(align){
//    	faceHelper.drawLine();
    }).bind('expanded', function(){
    	console.log('expanded');
    	//faceHelper.drawTerminals('tree2', false, canvas , null);
    }).bind('collapsed', function(){
    	console.log('collapsed');
    });
    
    drawLine = function(align){
    	if(align == 'left')
    		this.leftTreeLoaded = true;
    	else if(align == 'right')
    		this.rightTreeLoaded = true;
    	
    	if(this.leftTreeLoaded && this.rightTreeLoaded){
    		
    	}
    };
    /*
     * 
    $('.filemgr-tree').bind('expanded', function(){
    	console.log('expanded');
    	faceHelper.drawTerminals('tree2', false, canvas , null);
    }).bind('collapsed', function(){
    	console.log('collapsed');
    });
    */
    
    canvas.onConnectShape(function (event, edgeElement, fromElement, toElement) {
        console.log('connected!', fromElement.id, '--->', toElement.id);
    });

    canvas.onDisconnectShape(function (event, edgeElement, fromElement, toElement) {
        console.log('disconnected!', fromElement.id, '-/->', toElement.id);
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
		drawTerminals : function(treeId, isLeft , canvas, callback) {
			$('.filemgr-tree.left .item-fix').each(function(event, ui){
	        	console.log(this);
	        	
	        	// draw
	        });
		},
		drawTerminal : function(treeId, isLeft , canvas, callback) {
		    var tree = $('#' + treeId), id, text, shapeId, shapeElement, parentNode, edgeIds, edge, i,
		            isParentOpen = function (node) {
		                var parents = $(node).parents('li');
		                for (i = parents.length - 1; i >= 0; i--) {
		                    if (tree.jstree('is_closed', parents[i])) {
		                        return false;
		                    }
		                }
		                return true;
		            },
		            getParentClosedNode = function (node) {
		                var parents = $(node).parents('li');
		                for (i = parents.length - 1; i >= 0; i--) {
		                    if (tree.jstree('is_closed', parents[i])) {
		                        return parents[i];
		                    }
		                }
		                return null;
		            };
		    
		    $('#' + treeId + ' .jstree-leaf').each(function (idx, item) {
		        id = $(item).attr('id');
		        text = $(item).children('a').text();
		        shapeId = (isLeft ? 'FROM_' : 'TO_') + id;
		        if (isParentOpen(item) && tree.jstree('is_leaf', item)) {
		            shapeElement = canvas.drawShape(
		                    [(isLeft ? 5 : 295), item.offsetTop + item.offsetHeight / 2],
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
		        } else {
		            shapeElement = canvas.getElementById(shapeId);
		            if (shapeElement) {
		                parentNode = getParentClosedNode(item);
		                shapeElement = canvas.drawShape(
		                        [(isLeft ? 5 : 295), parentNode.offsetTop + parentNode.offsetHeight / 2],
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
		    });
		    
		    
		    if(typeof callback == 'function')
		    	callback();
		},
		drawLine : function(){
			var object = mw3.objects[this.objectId];
		    if( object != null && object.mapperData != null ){
				this.icanvas.loadJSON($.parseJSON(object.mapperData));
		    }
		},
		getValue : function(){
			var object = mw3.objects[this.objectId];
			object.mapperData = JSON.stringify(this.icanvas.toJSON());
			
			return object;
		}
};

