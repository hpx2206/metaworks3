var org_uengine_codi_mw3_webProcessDesigner_MappingPanel = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
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
    	//console.log('loaded');
//    	faceHelper.drawLine();
//    }).bind('expanded', function(){
//    	console.log('expanded');
//    	//faceHelper.drawTerminals('tree2', false, canvas , null);
//    }).bind('collapsed', function(){
//    	console.log('collapsed');
    //});
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
							console.log(shapeElement);
							$(shapeElement).click("destroy");
							canvas.removeAllGuide();
							canvas.redrawConnectedEdge(shapeElement);
							canvas.hide(shapeElement);
						}
					}
				}
			});
			
			if(!this.loadDrawed){
				if(this.leftTreeLoaded && this.rightTreeLoaded)
					this.drawLine();
			}
			
			
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
			this.loadDrawed = true;
			
			var object = mw3.objects[this.objectId];
		    if( object != null && object.mapperData != null ){
		    	console.log(object.mapperData);
		    	
		    	this.icanvas.clear();
				this.icanvas.loadJSON($.parseJSON(object.mapperData));
		    }		    
		},
		getValue : function(){
			var object = mw3.objects[this.objectId];
			object.mapperData = JSON.stringify(this.icanvas.toJSON());
			
			return object;
		}
};

