var org_uengine_codi_mw3_ide_libraries_SearchResultPanel = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	this.divObj = $('#' + this.divId);
	
	var object = mw3.objects[this.objectId];
	
	if(object){
		mw3.importScript('scripts/emberjs/ember-1.0.0.js');
		mw3.importScript('scripts/emberjs/handlebars-1.0.0.js');
		mw3.importScript('scripts/emberjs/jquery-1.9.1.js');
		
		mw3.importStyle('style/emberjs/normalize.css');
		mw3.importStyle('style/emberjs/style.css');
		var faceHelper = this;
		faceHelper.load();
	}
};