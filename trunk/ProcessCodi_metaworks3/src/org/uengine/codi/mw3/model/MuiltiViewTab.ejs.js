var org_uengine_codi_mw3_model_MuiltiViewTab = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;
	
	this.objectDiv
		.css({
			position: 'relative',
			height:   '100%'
		})
		.addClass('mw3_tab');
	
	$('#tabs_' + objectId).tabs({
		show: function(event, ui){
			if(mw3.getFaceHelper(objectId) && mw3.getFaceHelper(objectId).resize)
				mw3.getFaceHelper(objectId).resize();
			
			if( ui.index == 1){
				var calObjectId = $('#tab_'+objectId+'_2').find('.mw3_layout').attr('objectId');
				
				if(mw3.getFaceHelper(calObjectId) && mw3.getFaceHelper(calObjectId).resize)
					mw3.getFaceHelper(calObjectId).resize();
			}
		}
	});
	
	/*
	if(objectMetadata.faceOptions['tabsBottom'] == 'true'){
		$('#objDiv_' + objectId).addClass('tabs-bottom');
	}	
	var objectMetadata = mw3.getMetadata(className);
	$('#objDiv_' + objectId).removeClass( "ui-corner-all");
	$( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" ).removeClass( "ui-corner-all ui-corner-top" ).addClass( "ui-corner-bottom" );
	*/
};

org_uengine_codi_mw3_model_MuiltiViewTab.prototype = {
	// 탭 선택
	selectTab : function(tabIndex){
		$('#tabs_' + this.objectId).tabs({selected: tabIndex});
	}
};