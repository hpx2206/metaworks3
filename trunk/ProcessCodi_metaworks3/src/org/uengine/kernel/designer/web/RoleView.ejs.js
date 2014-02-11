var org_uengine_kernel_designer_web_RoleView = function(objectId, className){
	
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

org_uengine_kernel_designer_web_RoleView.prototype = {
		loaded : function(){
			var object = mw3.objects[this.objectId];
			var objectId = this.objectId;
			var canvas = this.canvas;
			var element = null;
			var initText;
			if( object.role ){
				initText = ( object.role.displayName != null && object.role.displayName.text != null ) ? object.role.displayName.text :  "";
			}else{
				initText = ( object.label == null || object.label == 'undefined' ) ? "" :  unescape(object.label);
			}
			var shape = eval('new ' + object.shapeId + '(\''+initText +'\')');
			var id = object.id;
			var style = object.style;
			
        	element = canvas.drawShape([
        	                                 object.x, object.y 
        	                                 ], 
        	                                 shape, [parseInt(object.width, 10), parseInt(object.height, 10)] , OG.JSON.decode(unescape(style)), id, null, false);
        	
        	// object.activityClass : Activity , object.__className : ActivityView
        	$(element).attr("_classname", object.activityClass);
        	$(element).attr("_viewClass", object.__className);
        	$(element).attr("_classType", object.classType);
        	
        	if( object.role ){
        		$(element).data('role', object.role);
        		// object.activity.activityView = null; 을 꼭 해주어야함.. activity가 activityView 를 들고있고, activityView가 activity를 들고있는 구조라서..
        		object.role.roleView = null;
        	}else if( typeof $(element).attr("_classname") != 'undefined' &&  typeof $(element).data("role") == 'undefined' ){
        		var activityData = {__className : $(element).attr("_classname")};
        		$(element).data('role', activityData);
        	}
        	$(element).unbind('dblclick');
        	$(element).on({
        		dblclick: function (event) {
        			if(event.stopPropagation){
        				event.stopPropagation();
        			}
        			var divId = 'properties_' + objectId;
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
        					panel : $(this).data('role'),
        					metaworksContext : metaworksContext
        			};
        			
        			object['propertiesWindow'] = propertiesWindow;
        			object.id = $(this).attr('id');
        			object.showProperties();
        			
        		}
        	});
		}
};