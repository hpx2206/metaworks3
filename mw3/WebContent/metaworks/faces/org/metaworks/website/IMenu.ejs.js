var org_metaworks_website_IMenu = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
}

org_metaworks_website_IMenu.prototype.showStatus = function(status){
}

org_metaworks_website_IMenu.prototype.startLoading = function(){
	
}

org_metaworks_website_IMenu.prototype.endLoading = function(){

}

org_metaworks_website_IMenu.prototype.showSubMenu = function(){
	var currMenu = mw3.getObject(this.objectId);
	currMenu.selectMenu();
	var subMenu = currMenu.subMenu;
	
	if(subMenu!=null){

		var subMenuDiv = "#objDiv_" + this.__objectId;
		
		if(subMenuDiv){
			$(subMenuDiv).slideDown(500, function(){
			//	setTimeout(function() {
			//		$( subMenuDiv ).slideUp(500);
			//	}, 5000 );
			});
		}
	}
}

