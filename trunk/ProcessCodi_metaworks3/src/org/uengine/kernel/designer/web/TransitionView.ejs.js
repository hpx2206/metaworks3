var org_uengine_kernel_designer_web_TransitionView = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	var canvasObject;
	if( object != null && object.viewType != null && "blockView" == object.viewType ){
		canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.InstanceMonitorPanel');
	}else	if( object != null && object.viewType != null && "definitionView" == object.viewType ){
		canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessViewer');
	}else{
		canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContentPanel');
	}
	var canvasObjectFaceHelper = mw3.getFaceHelper(canvasObject.__objectId);
	this.canvas = canvasObjectFaceHelper.icanvas;
	
};

org_uengine_kernel_designer_web_TransitionView.prototype = {
		loaded : function(){
			var object = mw3.objects[this.objectId];
			var canvas = this.canvas;
			var element = null;
//			if( object != null ){
//				element = document.getElementById(object.id);
//			}
			var initText = ( object.label == null || object.label == 'undefined' ) ? "" : object.label;
			var shape = eval('new ' + object.shapeId + '(\''+initText +'\')');
			var id = object.id;
			var parent = object.parent;
			var style = object.style;
			if (object.shapeType === 'EDGE') {
				console.log(object);
				var fromTeminal = object.from;
				var toTeminal = object.to;
				var getShapeFromTerminal = function (terminal) {
					var terminalId = OG.Util.isElement(terminal) ? terminal.id : terminal;
					if (terminalId) {
						return canvas.getRenderer().getElementById(terminalId.substring(0, terminalId.indexOf(OG.Constants.TERMINAL_SUFFIX.GROUP)));
					} else {
						return null;
					}
				};
				var fromElement = getShapeFromTerminal(fromTeminal);
				var toElement = getShapeFromTerminal(toTeminal);
				element = canvas.connect(fromElement, toElement);
            } else {
            	element = canvas.drawShape([
            	                                 object.x, object.y 
            	                                 ], 
            	                                 shape, [parseInt(object.width, 10), parseInt(object.height, 10)] , OG.JSON.decode(unescape(style)), id, parent, false);
            	
            	$(element).attr("_classname", object.activityClass);
            	$(element).attr("_classType", object.classType);
            	$(element).attr("_tracingTag",object.tracingTag);
            	if( typeof $(element).attr("_classname") != 'undefined' &&  typeof $(element).data("activity") == 'undefined' ){
            		var activityData = {__className : $(element).attr("_classname"), tracingTag : $(element).attr("_tracingTag")};
            		$(element).data('activity', activityData);
            	}
            	
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
            					metaworksContext : metaworksContext,
            					id : $(this).attr('id')
            			};
            			
            			object['propertiesWindow'] = propertiesWindow;
            			object.showProperties();
            			
            		}
            	});
            }
			
				
			/*
			$(element).attr('title', object.tooltip);
			$(element).hover(function(event, ui) {
				  $('body').append('<div id=\"shape_tooltip\" style=\"z-index: 1000; position: absolute; width: 200px; height: 30px; background-color: lightgray; text-align: center; padding: 15px 0px 0px; top: ' + event.pageY + 'px; left: ' + event.pageX + 'px\">' + object.tooltip + '</div>');
			}, function(){
				$('#shape_tooltip').remove();
			});
			*/
		}
};