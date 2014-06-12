var org_uengine_codi_mw3_selfservice_SelfServiceControlPanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;
		
	this.objectDiv.css({
		height:   '100%'
	}).addClass('mw3_resize');
	
	var accordionDiv = $("#sp_accordion");
	
	accordionDiv.accordion({
		fillSpace:	true ,
		active: 3,
		collapsible: true,
		heightStyle: "fill"
		});
	

	setCookie("codi.lastVisit", "selfservice", 10, "/", "", "");
};

org_uengine_codi_mw3_selfservice_SelfServiceControlPanel.prototype = {
		resize : function(){
			$('#sp_accordion').accordion('resize');
		},
		showStatus : function(status){
		},
		startLoading : function(status){
		},
		endLoading : function(status){
		}
};