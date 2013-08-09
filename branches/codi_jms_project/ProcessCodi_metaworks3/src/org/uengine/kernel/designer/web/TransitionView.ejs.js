var org_uengine_kernel_designer_web_TransitionView = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	
	var object = mw3.objects[this.objectId];
	var canvasObject;
	if( object != null && object.viewType != null && "blockView" == object.viewType ){
		canvasObject = mw3.getAutowiredObject('org.uengine.codi.mw3.webProcessDesigner.InstanceMonitorPanel');
	}else	if( object != null && object.viewType != null && ("definitionView" == object.viewType || "definitionEditor" == object.viewType  || "definitionDiff" == object.viewType)){
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

org_uengine_kernel_designer_web_TransitionView.prototype = {
		loaded : function(){
			var object = mw3.objects[this.objectId];
			var canvas = this.canvas;
			var initText = ( object.label == null || object.label == 'undefined' ) ? "" : unescape(object.label);
			var parent = object.parent;
			var style = object.style;
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
			$(element).on({
        		dblclick: function (event) {
        			
        		}
			});
		}
};