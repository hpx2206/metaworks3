
var org_uengine_kernel_designer_web_ActivityView = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	var canvasObject;
	if( object != null && object.viewType != null && "blockView" == object.viewType ){
		canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.InstanceMonitorPanel');
	}else	if( object != null && object.viewType != null && ("definitionView" == object.viewType || "definitionEditor" == object.viewType  || "definitionDiffEdit" == object.viewType  || "definitionDiffView" == object.viewType)){
		if( object.editorId ){
			canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessViewer@'+object.editorId);
		}else{
			canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessViewer');
		}
	}else{
		if( object.editorId ){
			canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContentPanel@'+object.editorId);
		}else{
			canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContentPanel');
		}
	}
	this.canvasObjectId = canvasObject.__objectId;
	var canvasObjectFaceHelper = mw3.getFaceHelper(canvasObject.__objectId);
	this.canvas = canvasObjectFaceHelper.icanvas;
	
};

org_uengine_kernel_designer_web_ActivityView.prototype = {
		loaded : function(){
			var object = mw3.objects[this.objectId];
			var element = null;
			var canvas = this.canvas;
			if( object && !object.drawByCanvas){
				var initText;
				if( object.activity ){
					initText = ( object.activity.description != null && object.activity.description.text != null ) ? object.activity.description.text :  "";
				}else{
					initText = ( object.label == null || object.label == 'undefined' ) ? "" :  unescape(object.label);
				}
				var shape = null;
				if( initText && initText != "" && initText != null){
					shape = eval('new ' + object.shapeId + '(\''+initText +'\')');
				}else{
					shape = eval('new ' + object.shapeId + '()');
				}
				var id = object.id;
				var parent = object.parent;
				var style = object.style;
	
				element = canvas.drawShape([
	        	                                 object.x, object.y 
	        	                                 ], 
	        	                                 shape, [parseInt(object.width, 10), parseInt(object.height, 10)] , OG.JSON.decode(unescape(style)), id, parent, false);
	        	
	        	// object.activityClass : Activity , object.__className : ActivityView
				if( object.backgroundColor ){
					canvas.setShapeStyle(element, {"fill": object.backgroundColor , "fill-opacity": 0.5});
				}
				
	        	$(element).attr("_classname", object.activityClass);
	        	$(element).attr("_viewClass", object.__className);
	        	$(element).attr("_classType", object.classType);
	        	$(element).attr("_tracingTag",object.tracingTag);
	        	if( object.activity ){
	        		$(element).data('activity', object.activity);
	        		// object.activity.activityView = null; 을 꼭 해주어야함.. activity가 activityView 를 들고있고, activityView가 activity를 들고있는 구조라서..
	        		object.activity.activityView = null;
	        	}else if( typeof $(element).attr("_classname") != 'undefined' &&  typeof $(element).data("activity") == 'undefined' ){
	        		var activityData = {__className : $(element).attr("_classname"), tracingTag : $(element).attr("_tracingTag")};
	        		$(element).data('activity', activityData);
	        	}
	        	
			}else{
				element = object.element;
				
				var activityData = {__className : $(element).attr("_classname"), tracingTag : $(element).attr("_tracingTag")};
        		$(element).data('activity', activityData);
				
				object.element = null;
			}
			
        	if( typeof object.activityClass != 'undefined'){
	        	$(element).unbind('dblclick');
	        	$(element).on({
	        		dblclick: function (event) {	
	        			if(event.stopPropagation){
	        				event.stopPropagation();
	        			}
	        			var divId = 'properties_' + this.objectId;
	        			$('body').append("<div id='" + divId + "'></div>");
	        			var metaworksContext = {
	        					__className : 'org.metaworks.MetaworksContext',
	        					when : 'edit'
	        			};
	        			
	        			var propertiesWindow = {
	        					__className : 'org.uengine.codi.mw3.webProcessDesigner.PropertiesWindow',
	        					open : true,
	        					width : 860,
	        					height : 600,
	        					panel : $(this).data('activity'),
	        					metaworksContext : metaworksContext
	        			};
	        			
	        			object['propertiesWindow'] = propertiesWindow;
	        			object.id = $(this).attr('id');
	        			
	    				var processVariablePanel = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessVariablePanel');
	    				if( processVariablePanel ){
	        				object.wholeVariableList = processVariablePanel.variableList;
	        			}
	        			
	        			object.showProperties();
	        			
	        		},
	        		btnclick : function(event) {
	        			object.id = $(this).attr('id');
	        			object.activity = $(this).data('activity');
	        			object.showDefinitionMonitor();
	        		}
	        	});
			}
        	$(element).droppable({
        		greedy: true,		
        		tolerance: 'geom',
            	over: function(event, ui){
            	},
            	out: function(event, ui){
            	},
        		drop: function(event, ui){
        			var session = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Session");
        	 		var clipboardNode = session.clipboard;
//					console.log(clipboardNode);
        			if(clipboardNode){
        				switch (clipboardNode.__className){
        				case 'org.uengine.codi.mw3.knowledge.WfNode':
        					$(element).attr("_classname", "org.uengine.codi.activitytypes.KnowledgeActivity");
        		        	$(element).attr("_viewClass", "org.uengine.kernel.designer.web.ActivityView");
        		        	
        		        	var activityData = {__className : $(element).attr("_classname"), tracingTag : $(element).attr("_tracingTag") , knolNodeId : clipboardNode.id};
        	        		$(element).data('activity', activityData);
        	        		canvas.drawLabel(element, clipboardNode.name);
        				case 'org.uengine.codi.mw3.ide.ResourceNode':
        					switch (clipboardNode.type) {
        					case 'java':
        						var id = clipboardNode.id;
        						var projectId = clipboardNode.projectId;
								
//								console.log(id.indexOf(projectId));
//								console.log(id.);
        						var dragObjMetadata = mw3.getMetadata(clipboardNode.name);
								console.log(dragObjMetadata);
        						canvas.drawLabel(element, dragObjMetadata.displayName);
    	    		    		
    	    		    		var complexType = {
    	    		    				__className : 'org.uengine.contexts.ComplexType',
    	    		    				designerMode : true,
    	    		    				typeId : '[' + clipboardNode.alias + ']'
    	    		    		};
    	    		    		var variable = {
    									__className : 'org.uengine.kernel.ProcessVariable',
    									name : dragObjMetadata.displayName ,
    									typeInputter : complexType.__className,
    									defaultValue : complexType
    							};
    	    		    		
    	    		    		var parameterContexts = [{
    	    		    			__className : 'org.uengine.kernel.ParameterContext',
    	    		    			variable : variable
    	    		    			
    	    		    		}];
    	    		    		
    	    		    		object.id = $(this).attr('id');
    	            			object.activity = $(this).data('activity');
    	    		    		// 엑티비티에 파라미터 추가
    	    		    		if( object.activity ){
    	    		    			object.activity.parameters = parameterContexts;
    	    		    			var text = {
	    		    					__className : 'org.uengine.contexts.TextContext',
	    		    					text : dragObjMetadata.displayName
    	    		    			};
    	    		    			object.activity.description = text;
    	    		    		}
    	    		    		
    	    		    		// 전체 변수 리스트에 추가
    	    		    		var processVariablePanel = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessVariablePanel@'+object.editorId);
    	    		    		if( processVariablePanel ){
    	    		    			processVariablePanel.editedProcessVariable = variable;
    	    		    			processVariablePanel.addWholeVariable();
    	            			}
    	    		    		
    	    		    		var eleClassName = $(this).attr("_classname");
    	    		    		if( eleClassName == 'org.uengine.kernel.InvocationActivity'){
    	    		    			var activityData = $(this).data('activity');
    	    		    			activityData.resourceClass = clipboardNode.alias;
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
        			session.clipboard = null;
        		}
        	});
			// jms 조회 화면
			if( object != null && object.viewType != null && "definitionView" == object.viewType ){
				$(element).unbind('dblclick').bind('dblclick' , function(event){
				});
				$(element).unbind('click').bind('click' , function(event){
					object.showActivityDocument();
				});
			}
			// 프로세스 비교
			if( object != null && object.viewType != null && "definitionDiffView" == object.viewType ){
				var eleId = $(element).attr('id');
				$.contextMenu({
					selector: '#' + eleId,
					build   : function ($trigger, e) {
						return {
							items: {
								'copyRightToLeft'     : {
									name: 'copyRightToLeft', callback: function () {
										object.copyRightToLeft();
									}
								},
								'sep1'       : '---------',
								'compare'     : {
									name: 'compare', callback: function () {
									}
								}
							}
						};
					}
			    });
			}
		}
};