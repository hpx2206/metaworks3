var org_uengine_codi_mw3_model_PureWebProcessDesigner = function(objectId, className){
	new mxApplication('scripts/mxgraph/examples/editors/config/workfloweditor.xml','1');
}


		//mxClient.basePath = "/uengine-web/scripts/mxgraph/examples/editors/";
		
		mxConstants.DEFAULT_HOTSPOT = 1;
		
		// Enables guides
		mxGraphHandler.prototype.guidesEnabled = true;
		
	    // Alt disables guides
	    mxGuide.prototype.isEnabledForEvent = function(evt)
	    {
	    	return !mxEvent.isAltDown(evt);
	    };
		
		// Enables snapping waypoints to terminals
		mxEdgeHandler.prototype.snapToTerminals = true;
		
		window.onbeforeunload = function() { return mxResources.get('changesLost'); };