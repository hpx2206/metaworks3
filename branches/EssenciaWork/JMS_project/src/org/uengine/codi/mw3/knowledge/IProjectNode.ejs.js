var org_uengine_codi_mw3_knowledge_IProjectNode = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];
	
	
};

org_uengine_codi_mw3_knowledge_IProjectNode.prototype = {
	startLoading : function(status){ },
	endLoading : function(status){ },
	showStatus : function(message){
		if(message){
			
			var message_split = message.split(' ');

			if(message_split[0] == 'loadTopic'){
				$('#navigator .depth1').removeClass('selected');
				$('#navigator .depth2 .fist_menu li').removeClass('selected_navi');
				$('#objDiv_'  + this.objectId + ' li').addClass('selected_navi');
			}
		}
	}
};
