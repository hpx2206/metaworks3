var Window = function(objectId, className){
	this.objectId = objectId;
	this.className = className;

	this.divId = '#objDiv_' + objectId;
	this.smallDivId = '#sm_' + objectId;
	
	$(this.divId).addClass('mw3_window').addClass('mw3_layout').attr('objectId', objectId);
	
	
	var faceHelper = this;
	
	faceHelper.load();
}

Window.prototype.load = function(){
	
	var object = mw3.objects[this.objectId];
	var options = {
			togglerLength_open:	0, 
			spacing_open:		0, 
			spacing_closed:		0,
			center__onresize:	'mw3.getFaceHelper('+this.objectId+').resizeChild()'
	}

	this.layout = $(this.divId).layout(options);
}

Window.prototype.destory = function(){
	$(this.divId).layout().destroy();
}

Window.prototype.resize = function(){
	if(this.layout){
		this.layout.resizeAll();
		
		this.resizeChild();
	}
}

Window.prototype.resizeChild = function(){
	
	$(this.divId).find('.mw3_layout:visible').each(function(index, value){
		var layoutId = value.getAttribute('objectId');
		
		if(layoutId)
			mw3.getFaceHelper(layoutId).resize();
	});
}

Window.prototype.minimize = function(layoutName){

	// 최초 입력을 위해 입력여부 확인
	if($('.mw3_windowpanel').find(this.smallDivId).length == 0)
		$(this.smallDivId).appendTo('.mw3_windowpanel');
	
	$(this.smallDivId).show();

	var layoutId = $(this.divId).parent().closest('.mw3_layout').attr('objectId');
	if(layoutId)
		mw3.getFaceHelper(layoutId).hide(layoutName);
}


Window.prototype.resume = function(layoutName){
	
	$(this.smallDivId).hide();
	
	var layoutId = $(this.divId).parent().closest('.mw3_layout').attr('objectId');
	if(layoutId)
		mw3.getFaceHelper(layoutId).show(layoutName);
}

Window.prototype.startLoading = function(){
	$('#loader_' + this.objectId).show();
}

Window.prototype.endLoading = function(){
	$('#loader_' + this.objectId).hide();

}
