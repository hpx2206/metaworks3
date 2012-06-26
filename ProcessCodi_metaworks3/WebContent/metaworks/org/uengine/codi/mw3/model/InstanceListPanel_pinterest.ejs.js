var org_uengine_codi_mw3_model_InstanceListPanel_pinterest = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;
	
    var $container = $('#pinterest_container');
    
//    $container.imagesLoaded( function(){
//	    $container.masonry({
//	      itemSelector: '.box',
//	      columnWidth: 100,
//	      isAnimated: !Modernizr.csstransitions
//	//      cornerStampSelector: '.corner-stamp'
//	    });
//    });

    var reload = function(){
	    $container.masonry({
		      itemSelector: '.box',
		      columnWidth: 100,
		      isAnimated: !Modernizr.csstransitions
		//      cornerStampSelector: '.corner-stamp'
		    })};
		    
	reload();

    setTimeout(reload, 2000);
    

}

