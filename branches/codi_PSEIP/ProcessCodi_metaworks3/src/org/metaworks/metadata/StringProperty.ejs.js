var org_metaworks_metadata_StringProperty = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.object = mw3.objects[this.objectId];
	
	
	this.accordionList = $("#sp_accordion_list_" + this.objectId);
	
	this.accordionList.click(function(){
		mw3.call(objectId, 'showPropertyDetail');
	});
	
	this.accordionList.hover(function(){
			$(this).css('cursor','pointer');
		}, function(){			
			$(this).css('cursor','auto');
	});
	
	
	this.editBtn = $("#sp_editBtn_" + this.objectId);
	
	this.editBtn.hover(function(){
			$(this).css('cursor','pointer');
		}, function(){			
			$(this).css('cursor','auto');
	});
	
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	this.objectDiv.find(".fb_form_box").click(function(){
		$('.fb_form_box').css({'background':'#f3f7fb'});
		$(this).css({'background':'#A8D0F9'});
	});
	
	this.objectDiv.hover(
		function(){
			$(this).find('.hover_div').show();
		},
		function(){
			$(this).find('.hover_div').hide();
		}
	);
};