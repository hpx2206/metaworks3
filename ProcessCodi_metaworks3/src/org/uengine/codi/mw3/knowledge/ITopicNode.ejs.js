var org_uengine_codi_mw3_knowledge_ITopicNode = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;
};

org_uengine_codi_mw3_knowledge_ITopicNode.prototype = {
	startLoading : function(){},
	endLoading : function(){},
	showStatus : function(message){
//		if(message){
//			if(window.console)
//				mw3.log('ITopicNode(' + this.objectId + ') : ' + message);
//			
//			var message_split = message.split(' ');
//			
//			//주제를 선택시 선택블록 효과주기
//			if(message_split[0] == 'loadTopic'){
//				$('#navigator .depth2 .fist_menu li').removeClass('selected_navi');
//				$('.idept').removeClass('selected_navi2');
//				$('.irole').removeClass('selected_navi2');
//				$('.iemployee').removeClass('selected_navi2');
//				this.objectDiv.addClass('selected_navi');
//			}
//		}
	}
};
