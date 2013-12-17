var org_uengine_codi_mw3_marketplace_IAppMapping = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];
	
	this.session = mw3.getAutowiredObject('org.uengine.codi.mw3.model.Session');
	
	if(this.object == null)
		return true;
	
	this.objectDiv
		.hover(function(){
				$(this).css('cursor','pointer');
			}, function(){			
				$(this).css('cursor','auto');
		});
	
	if( this.object && this.object.selected ){
		$("#mp_menu_list_"+ this.objectId).addClass('current');
	}

	$('#info_' + this.objectId).remove();
	
	$("#mp_menu_list_"+ this.objectId).click(function(){
		
		var object = mw3.objects[objectId];
		
		var selfServiceControlPanel = mw3.getAutowiredObject('org.uengine.codi.mw3.selfservice.SelfServiceControlPanel');
		
		selfServiceControlPanel.appId = object.appId;
		selfServiceControlPanel.appName = object.appName;
		
		if( selfServiceControlPanel.appMapping != null ){
			for(var i=0; i < selfServiceControlPanel.appMapping.length ; i++){
				var appMap = selfServiceControlPanel.appMapping[i];
				appMap.selected = false;
				if( appMap.appName == object.appName){
					appMap.selected = true;
				}
			}
		}
		mw3.call(selfServiceControlPanel.__objectId, 'selectedApp');
		
	});
	
	this.objectDiv.trigger('loaded_app', [ this.object.appId, this.objectDivId ]);
};

org_uengine_codi_mw3_marketplace_IAppMapping.prototype = {
	startLoading : function(status){
	},
	endLoading : function(status){
	},
	showStatus : function(message){
		if(message){
			var message_split = message.split(' ');
			
			//주제를 선택시 선택블록 효과주기
			if(message_split[0] == 'clickAppList'){
				$('#navigator .depth1').removeClass('selected');
				$('#navigator .depth2 .fist_menu li').removeClass('selected_navi');
				$('#objDiv_'  + this.objectId + ' li').addClass('selected_navi');
			}
		}
	},
	clickAppList : function(){
		this.object.empCode = this.session.employee.empCode;
		
		//자주 찾는 앱 등록
		this.object.clickAppList();
		
	},
	openAppBrowser : function(){
		/*
		var object = mw3.objects[this.objectId];
		window.open(object.url);
		this.object.empCode = this.session.employee.empCode;
		
		//자주 찾는 앱 등록
		this.object.updateFavoriteApp();
		*/
		
		
		mw3.call(this.objectId, 'openAppBrowser', null, null, function(){
			var window = mw3.getAutowiredObject('org.uengine.codi.mw3.model.PerspectiveWindow');
			
			mw3.getFaceHelper(window.__objectId).minimize();
		});
	}
};

