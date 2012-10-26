var Window = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.divId = '#objDiv_' + this.objectId;
	this.smallDivId = '#sm_' + this.objectId;
	
	var layout = $(this.divId).parent();	
	if(!layout.hasClass('pane')){
		layout = layout.parent();
	}	
	this.layoutDiv = layout;
	this.layoutName = layout.attr('layoutName');
	
	$(this.divId).addClass('mw3_window').addClass('mw3_layout').attr('objectId', objectId);
	
	if(layout.css('display') != 'none'){
		var faceHelper = this;
		faceHelper.load();		
	}
}

Window.prototype.setTitle = function(title){
	$(this.divId + " .mw3_window_title span").html(title);
}

Window.prototype.load = function(){
	
	var object = mw3.objects[this.objectId];
	var options = {
			togglerLength_open:	0, 
			spacing_open:		0, 
			spacing_closed:		0,
			west__size: 3,
			east__size: 7,
			center__onresize:	'mw3.getFaceHelper('+this.objectId+').resizeChild()'
	}

	this.layout = $(this.divId).layout(options);
}

Window.prototype.destroy = function(){
	if(this.layout)
		this.layout.destroy();
	
	$('.mw3_windowpanel').find(this.smallDivId).remove();
}

Window.prototype.resize = function(){
	
	if(this.layout){
		this.layout.resizeAll();
		
		this.resizeChild();
	}
}

Window.prototype.resizeChild = function(){
	
	$(this.divId).find('.mw3_layout, .mw3_resize').each(function(index, value){
		var layoutId = value.getAttribute('objectId');
		
		if(layoutId)
			mw3.getFaceHelper(layoutId).resize();
	});
}

Window.prototype.maximize = function(){
	var miximizeBtn = $('#window_miximize_' + this.objectId);
	
	var layout = $(this.divId).parent().closest('.mw3_layout');
	var layoutId = layout.attr('objectId');
	
	if(miximizeBtn.hasClass('togglebtnexp')){
		
		// 최대화
		miximizeBtn.removeClass('togglebtnexp').addClass('togglebtnmini');
		
		mw3.getFaceHelper(layoutId).maximize(this.objectId);
		
	}else{
		// 이전크기로 복원
		miximizeBtn.removeClass('togglebtnmini').addClass('togglebtnexp');

		mw3.getFaceHelper(layoutId).resume();
	}
}

Window.prototype.minimize = function(){

	this.showSmallWindow();
	
	var layoutId = $(this.divId).parent().closest('.mw3_layout').attr('objectId');
	if(layoutId)
		mw3.getFaceHelper(layoutId).hide(this.layoutName);
	
}

Window.prototype.resume = function(){
		
	this.hideSmallWindow();
	
	var layoutId = $(this.divId).parent().closest('.mw3_layout').attr('objectId');
	if(layoutId)
		mw3.getFaceHelper(layoutId).show(this.layoutName);
	
	if(typeof this.layout == 'undefined'){
		this.load();
		this.resizeChild();
	}
		
	
}

Window.prototype.showSmallWindow = function(){
	
	// 최초 입력을 위해 입력여부 확인
	if($('.mw3_windowpanel').find(this.smallDivId).length == 0)
		$(this.smallDivId).appendTo('.mw3_windowpanel');
	
	$(this.smallDivId).show();
	
}

Window.prototype.hideSmallWindow = function(){
	$(this.smallDivId).hide();
}

Window.prototype.startLoading = function(){
	$('#loader_' + this.objectId).show();
}

Window.prototype.endLoading = function(){
	$('#loader_' + this.objectId).hide();

}
