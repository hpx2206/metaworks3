var org_uengine_processmarket_ICategory = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
}

org_uengine_processmarket_ICategory.prototype.showStatus = function(status){
}

org_uengine_processmarket_ICategory.prototype.startLoading = function(){
	
}

org_uengine_processmarket_ICategory.prototype.endLoading = function(){

}

org_uengine_processmarket_ICategory.prototype.showChildrenCategories = function(){
	var currCategory = mw3.getObject(this.objectId);
	currCategory.selectCategory();
	var children = currCategory.childrenCategories;
	
	if(children!=null){

		var childrenCategoriesDiv = "#objDiv_" + this.objectId;
		
		if(childrenCategoriesDiv){
			$(childrenCategoriesDiv).slideDown(500, function(){
			//	setTimeout(function() {
			//		$( subMenuDiv ).slideUp(500);
			//	}, 5000 );
			});
		}
	}
}

