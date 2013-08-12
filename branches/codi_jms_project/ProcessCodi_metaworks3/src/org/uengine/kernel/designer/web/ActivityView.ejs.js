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
	var canvasObjectFaceHelper = mw3.getFaceHelper(canvasObject.__objectId);
	this.canvas = canvasObjectFaceHelper.icanvas;
	
};

org_uengine_kernel_designer_web_ActivityView.prototype = {
		loaded : function(){
			var object = mw3.objects[this.objectId];
			var canvas = this.canvas;
			var element = null;
			var initText;
			if( object.activity ){
				initText = ( object.activity.description != null && object.activity.description.text != null ) ? object.activity.description.text :  "";
			}else{
				initText = ( object.label == null || object.label == 'undefined' ) ? "" :  unescape(object.label);
			}
			var shape = eval('new ' + object.shapeId + '(\''+initText +'\')');
			var id = object.id;
			var parent = object.parent;
			var style = object.style;
			
        	element = canvas.drawShape([
        	                                 object.x, object.y 
        	                                 ], 
        	                                 shape, [parseInt(object.width, 10), parseInt(object.height, 10)] , OG.JSON.decode(unescape(style)), id, parent, false);
        	if( object.activityClass != null && object.activityClass == 'org.uengine.kernel.SubProcessActivity' ){
        		canvas.setShapeStyle(element, {fill: '#FFFFFF-#000000'});
//        		canvas.setShapeStyle(element, {fill: '#FFFFFF-#000000' , "stroke": 'red' , "stroke-width" : 5 });
        	}
        	
        	// object.activityClass : Activity , object.__className : ActivityView
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
        			object.showProperties();
        			
        		},
        		btnclick : function(event) {
        			object.id = $(this).attr('id');
        			object.activity = $(this).data('activity');
        			object.showDefinitionMonitor();
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