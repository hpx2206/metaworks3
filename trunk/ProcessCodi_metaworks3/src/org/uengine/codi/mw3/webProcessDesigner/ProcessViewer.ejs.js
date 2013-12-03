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
		
		this.object = object;
		
		this.icanvas = null;
		
		this.divObj.css('height','100%');
		this.divObj.parent().css('height','100%');
		var canvasDivObj = $('#canvas_' + objectId);
		
		OG.common.Constants.CANVAS_BACKGROUND = "#fff";
	    OG.Constants.ENABLE_CANVAS_OFFSET = true; // Layout 사용하지 않을 경우 true 로 지정
	    var canvas = new OG.Canvas('canvas_' + objectId);
		this.icanvas = canvas;	
		
		if( object != null && object.viewType != null && "definitionDiffEdit" == object.viewType ){
			canvas.initConfig({
				selectable      : true,
				dragSelectable  : true,
				movable         : true,
				resizable       : true,
				connectable     : true,
				selfConnectable : false,
				connectCloneable: false,
				connectRequired : true,
				labelEditable   : true,
				groupDropable   : true,
				collapsible     : true,
				enableHotKey    : false,
				enableContextMenu : true
			});
		}else{
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
		}
		// canvas size 조절
		var canvasWidth = canvasDivObj.width();
		var canvasHeight = canvasDivObj.height();
		var scaleSize = 0.50;
		var xScale = 1;
		var yScale = 1;
		var designerMaxX = object.designerMaxX;
		var designerMaxY = object.designerMaxY;
		// designerMaxX 가 현재 켄버스보다 작으면 스케일 적용 안함
		if( designerMaxY > canvasHeight){
			yScale = canvasHeight / designerMaxY;
		}
		if( designerMaxX > canvasWidth){
			xScale = canvasWidth / designerMaxX;
		}
		
		if( xScale != 1 || yScale != 1){
			scaleSize = xScale > yScale ? yScale : xScale;
			canvas._RENDERER.setScale(scaleSize);
		}
			
		this.icanvas.setCanvasSize([canvasWidth , canvasHeight]);
	},
	getValue : function(){
		// TODO processDesignercontainerPanel 에 있는 getValue부분을 활욜할수 있는지.. 없으면 복사해서 붙여넣기
	}
};