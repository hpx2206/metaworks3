var org_uengine_kernel_designer_web_ValueChainView = function(objectId, className){
	
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
	var canvasObjectFaceHelper = mw3.getFaceHelper(canvasObject.__objectId);
	this.canvas = canvasObjectFaceHelper.icanvas;
	
};

org_uengine_kernel_designer_web_ValueChainView.prototype = {
		loaded : function(){
			var object = mw3.objects[this.objectId];
			var canvas = this.canvas;
			var element = null;
			var initText = ( object.label == null || object.label == 'undefined' ) ? "" : object.label;
			var shape = eval('new ' + object.shapeId + '(\''+initText +'\')');
			var id = object.id;
			var parent = object.parent;
			var style = object.style;
        	element = canvas.drawShape([
        	                                 object.x, object.y 
        	                                 ], 
        	                                 shape, [parseInt(object.width, 10), parseInt(object.height, 10)] , OG.JSON.decode(unescape(style)), id, parent, false);
        	
        	$(element).attr("_classname", object.activityClass);
        	$(element).attr("_viewClass", object.__className);
        	$(element).attr("_classType", object.classType);
        	$(element).attr("_tracingTag",object.tracingTag);
        	if( object.valuechain ){
        		$(element).data('valuechain',object.valuechain);
        	}else if( typeof $(element).attr("_classname") != 'undefined' &&  typeof $(element).data("activity") == 'undefined' ){
        		var activityData = {__className : $(element).attr("_classname"), tracingTag : $(element).attr("_tracingTag")};
        		$(element).data('valuechain', activityData);
        	}
        	
        	$(element).on({
        		btnclick : function(event) {
        			object.showDefinitionMonitor();
        		}
        	});
			
				
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