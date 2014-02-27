var org_uengine_kernel_designer_web_TransitionView = function(objectId, className){
	
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
	this.element;
};

org_uengine_kernel_designer_web_TransitionView.prototype = {
		loaded : function(){
			var objectId = this.objectId;
			var object = mw3.objects[this.objectId];
			var element;
			var canvas = this.canvas;
			var getShapeFromTerminal = function (terminal) {
				var terminalId = OG.Util.isElement(terminal) ? terminal.id : terminal;
				if (terminalId) {
					return canvas.getRenderer().getElementById(terminalId.substring(0, terminalId.indexOf(OG.Constants.TERMINAL_SUFFIX.GROUP)));
				} else {
					return null;
				}
			};
			if( object && !object.drawByCanvas ){
				var initText = ( object.label == null || object.label == 'undefined' ) ? "" : unescape(object.label);
				var style = object.style;
				var fromTeminal = object.from;
				var toTeminal = object.to;
				var fromElement = getShapeFromTerminal(fromTeminal);
				var toElement = getShapeFromTerminal(toTeminal);
				var fromElementId = $(fromElement).attr('id');
				var toElementId = $(toElement).attr('id');
				// 이 부분을 두번 안태우도록 ProcessDesignerContentPanel.ejs.js 에서 해당 attr로 비교를 함
				$(fromElement).attr('_conneted'+fromElementId , fromElementId +'_'+ toElementId);
				$(toElement).attr('_conneted'+toElementId , fromElementId +'_'+ toElementId);
				//element = canvas.connect(fromElement, toElement , OG.JSON.decode(unescape(style)) , initText);
				element = canvas.connectWithTerminalId(fromTeminal, toTeminal , OG.JSON.decode(unescape(style)) , initText);
				
				if( object.transition ){
					$(element).data('transition', object.transition);
					object.transition.transitionView = null;
				}
			}else{
				element = object.element;
				var fromElement = getShapeFromTerminal($(element).attr("_from"));
				var toElement = getShapeFromTerminal($(element).attr("_to"));
//				
				var fromTracingTag = $(fromElement).attr('_tracingTag');
				var toTracingTag = $(toElement).attr('_tracingTag');
				if( fromTracingTag && toTracingTag){
					var transitionData = {
							__className : 'org.uengine.kernel.graph.Transition', 
							source : fromTracingTag,
							target : toTracingTag
					};
					$(element).data('transition', transitionData);
					object.transition = transitionData;
				}
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
			
			$(element).unbind('dblclick');
        	$(element).on({
        		dblclick: function (event) {
        			if(event.stopPropagation){
        				event.stopPropagation();
        			}
        			object.transition = $(this).data('transition');
        			object.gateCondition();
        		}
        	});
			
			// jms 조회 화면
            if( object != null && object.viewType != null && "definitionView" == object.viewType ){
                $(element).unbind('dblclick').bind('dblclick' , function(event){
                });
            }
		},
		validation : function(message){
//          this.canvas.setErrorType(this.element, 'error');
//          mw3.alert(message);
        }
};