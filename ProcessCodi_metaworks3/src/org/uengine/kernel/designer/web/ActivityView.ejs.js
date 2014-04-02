
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
	this.element;
};

org_uengine_kernel_designer_web_ActivityView.prototype = {
		loaded : function(){
			var object = mw3.objects[this.objectId];
			var element = null;
			var canvas = this.canvas;
			if( object && !object.drawByCanvas){
				var initText = "";
				if( !(object.label == null || object.label == 'undefined') ){
					initText = unescape(object.label);
				}else if( object.activity && object.activity.description != null && object.activity.description.text != null ){
					initText = object.activity.description.text;
				}
				
				var shape = null;
				if( initText && initText != "" && initText != null){
					shape = eval('new ' + object.shapeId + '(\''+initText +'\')');
				}else{
					shape = eval('new ' + object.shapeId + '()');
				}
				var id = object.id;
				var style = object.style;
				if( object.instStatus ){
					if ("Completed" == object.instStatus || "Running" == object.instStatus) {
						shape.status = object.instStatus;
					}
				}
				if (object.exceptionType && object.exceptionType != 'None') {
					shape.exceptionType = object.exceptionType;
				}
	
				element = canvas.drawShape([
	        	                                 object.x, object.y 
	        	                                 ], 
	        	                                 shape, [parseInt(object.width, 10), parseInt(object.height, 10)] , OG.JSON.decode(unescape(style)), id, null, false);
	        	// object.activityClass : Activity , object.__className : ActivityView
	        	$(element).attr("_classname", object.activityClass);
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
			
			if( !object.id ){
				// 새롭게 그려진 경우 ID를 부여하여 keymapping 시켜줌
				object.id = $(element).attr('id');
				var objKeys = mw3._createObjectKey(object, true);
	            if(objKeys && objKeys.length){
	                for(var i=0; i<objKeys.length; i++){
	                    mw3.objectId_KeyMapping[objKeys[i]] = Number(this.objectId);
	                }
	            }
			}
			
			$(element).attr("_viewClass", object.__className);
			this.element = element;
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
					var eleClassName = $(this).attr("_classname");
					//console.log(clipboardNode);
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
        						var dragObjMetadata = null;
								try{
									dragObjMetadata = mw3.getMetadata(clipboardNode.alias);
								}catch(e){
									dragObjMetadata = null;
								}
								
								if( dragObjMetadata == null ){
									var message = '[' + clipboardNode.alias + '.java] 파일이 온전하지 않습니다. ';
									mw3.alert(message);
									break;
								}
								// 이미 label 이 그려진 경우라면 새롭게 label을 그리지 않는다.
								if( !($(this).find('#'+this.id+'_LABEL') && $(this).find('#'+this.id+'_LABEL').length > 0 && $(this).find('#'+this.id+'_LABEL').text() != "" )){
	        						canvas.drawLabel(element, dragObjMetadata.displayName);
								}
    	    		    		
    	    		    		var complexType = {
    	    		    				__className : 'org.uengine.contexts.ComplexType',
    	    		    				designerMode : true,
    	    		    				typeId : '[' + clipboardNode.alias + ']'
    	    		    		};
								var variableName = clipboardNode.name.substring(0, clipboardNode.name.length-5);
								var variableDisplayName = {
                                        __className : 'org.uengine.contexts.TextContext',
                                        text : dragObjMetadata.displayName
                                    };
    	    		    		var variable = {
    									__className : 'org.uengine.kernel.ProcessVariable',
    									name :  variableName,
										displayName : variableDisplayName ,
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
									if( !(object.activity.description && object.activity.description.text != null && object.activity.description.text != '') ){
	    	    		    			object.activity.description = variableDisplayName;
									}
    	    		    		}
    	    		    		
    	    		    		// 전체 변수 리스트에 추가
    	    		    		var processVariablePanel = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessVariablePanel@'+object.editorId);
    	    		    		if( processVariablePanel ){
    	    		    			processVariablePanel.editedProcessVariable = variable;
    	    		    			processVariablePanel.addWholeVariable();
    	            			}
    	    		    		
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
						case 'org.uengine.codi.mw3.ide.libraries.ProcessNode':
							switch (clipboardNode.type) {
								case 'process':
									if( eleClassName == 'org.uengine.kernel.SubProcessActivity' ){  // 서브프로세스 에서만 프로세스를 받을수 있음
									    console.log(clipboardNode);
										
										var descName = clipboardNode.name.substring(0, clipboardNode.name.length - 8);
		                                // 이미 label 이 그려진 경우라면 새롭게 label을 그리지 않는다.
		                                if( !($(this).find('#'+this.id+'_LABEL') && $(this).find('#'+this.id+'_LABEL').length > 0 && $(this).find('#'+this.id+'_LABEL').text() != "" )){
		                                    canvas.drawLabel(element, descName);
		                                }
										
										object.id = $(this).attr('id');
		                                object.activity = $(this).data('activity');
										
										var subprocessDisplayName = {
	                                        __className : 'org.uengine.contexts.TextContext',
	                                        text : descName
	                                    };
									
		                                // 엑티비티에 파라미터 추가
	                                    if (object.activity) {
											  if( !(object.activity.description && object.activity.description.text != null && object.activity.description.text != '') ){
		                                          object.activity.description = subprocessDisplayName;
		                                      }
										      object.activity.alias = clipboardNode.alias;
											  object.activity.definitionId = clipboardNode.alias;
										}
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
                    object.id = $(this).attr('id');
                    object.activity = $(this).data('activity');
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
		},
		validation : function(message){
			if('release' == message){
				this.canvas.setExceptionType(this.element, '');
			}else{
				this.canvas.setExceptionType(this.element, 'error');
			}
//			mw3.alert(message);
		}
};