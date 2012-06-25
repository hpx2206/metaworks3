var org_uengine_codi_mw3_model_InstanceListPanel_pinterest = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	
    var $container = $('#pinterest_container');
    
   
    $container.masonry({
      itemSelector: '.box',
      columnWidth: 100,
//      isAnimated: !Modernizr.csstransitions,
      cornerStampSelector: '.corner-stamp'
    });
   
}

