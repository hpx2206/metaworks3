var org_uengine_codi_mw3_webProcessDesigner_ProcessViewer = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	this.divId = mw3._getObjectDivId(this.objectId);
	this.divObj = $('#' + this.divId);
	
	var object = mw3.objects[this.objectId];
	
	if(object){
		mw3.importScript('scripts/opengraph/OpenGraph-0.1-SNAPSHOT.js');
		mw3.importScript('scripts/jquery/jquery.contextMenu.js');
		mw3.importStyle('style/jquery/jquery.contextMenu.css');
		mw3.importStyle('dwr/metaworks/org/uengine/codi/mw3/model/PureWebProcessDesigner.ejs.css');
		
		var faceHelper = this;
		faceHelper.load();
	}
};

org_uengine_codi_mw3_webProcessDesigner_ProcessViewer.prototype = {
	load : function(){
		var objectId = this.objectId;
		var object = mw3.objects[this.objectId];
		var faceHelper = this;
		
		this.object = object;
		
		this.icanvas = null;
		
		this.divObj.css('height','100%');
		this.divObj.parent().css('height','100%');
		var canvasDivObj = $('#canvas_' + objectId);
		
		OG.common.Constants.CANVAS_BACKGROUND = "#fff";
	    OG.Constants.ENABLE_CANVAS_OFFSET = true; // Layout 사용하지 않을 경우 true 로 지정
	    var canvas = new OG.Canvas('canvas_' + objectId);
		this.icanvas = canvas;	
		
		canvas.initConfig({
	        selectable      : false,
	        dragSelectable  : false,
	        movable         : false,
	        resizable       : false,
	        connectable     : false,
	        selfConnectable : false,
	        connectCloneable: false,
	        connectRequired : false,
	        labelEditable   : false,
	        groupDropable   : false,
	        collapsible     : false,
	        enableHotKey    : false,
	        enableContextMenu : false
	    });
		// scale 조절 50%
		canvas._RENDERER.setScale(0.50);
		// canvas size 조절
	    this.icanvas.setCanvasSize([canvasDivObj.width() -10 , canvasDivObj.height()]);
	    
	    
	}
};