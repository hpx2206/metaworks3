var org_uengine_kernel_designer_web_ActivityView = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	var canvasObject;
	if( object != null && object.viewType != null && "blockView" == object.viewType ){
		canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.InstanceMonitorPanel');
	}else	if( object != null && object.viewType != null && "definitionView" == object.viewType ){
		canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessViewer');
	}else	if( object.editorId ){
			canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContentPanel@'+object.editorId);
	}else{
			canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContentPanel');
	}
	var canvasObjectFaceHelper = mw3.getFaceHelper(canvasObject.__objectId);
	this.canvas = canvasObjectFaceHelper.icanvas;
	
};

org_uengine_kernel_designer_web_ActivityView.prototype = {
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
        	if( object.activity ){
        		$(element).data('activity', object.activity);
        	}else if( typeof $(element).attr("_classname") != 'undefined' &&  typeof $(element).data("activity") == 'undefined' ){
        		var activityData = {__className : $(element).attr("_classname"), tracingTag : $(element).attr("_tracingTag")};
        		$(element).data('activity', activityData);
        	}
        	// object.activity = null; 을 꼭 해주어야함.. activity가 activityView 를 들고있고, activityView가 activity를 들고있는 구조라서..
        	// view 단에서는 activity를 사용하는 부분은 $(element).data로 한정짖는다.
        	object.activity = null;
        	$(element).on({
        		dblclick: function (event) {
        			if(event.stopPropagation){
        				event.stopPropagation();
        			}
        			object.showDefinitionMonitor();
        		}
        	});
		}
};