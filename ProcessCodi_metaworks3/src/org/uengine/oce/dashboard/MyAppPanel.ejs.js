var org_uengine_oce_dashboard_MyAppPanel = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	$(".process_map2 li").hover(
		
		function(){
		console.log("11111");
		
			$(this).find(".editicon").css("display","block");
			
		},
		function(){
		console.log("22222");
			$(this).find(".editicon").css("display","none");
		}			
	);
};

org_uengine_oce_dashboard_MyAppPanel.prototype = {
	startLoading : function(){
		//Do Something...
	},
	
	endLoading : function(){
		//Do Something...
	},
	
	showStatus : function(message){
		//Do Something...
	}
	
	
	
};