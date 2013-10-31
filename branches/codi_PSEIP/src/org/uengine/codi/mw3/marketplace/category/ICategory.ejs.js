var org_uengine_codi_mw3_marketplace_category_ICategory = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	this.objectDivId = mw3._getObjectDivId(this.objectId);
	this.objectDiv = $('#' + this.objectDivId);
	
	this.object = mw3.objects[this.objectId];

	if(this.object == null)
		return true;	
	
	this.objectDiv
		.addClass('mp_menu_list' + this.object.categoryId)
		.hover(function(){
				$(this).css('cursor','pointer');
			}, function(){			
				$(this).css('cursor','auto');
		});
	
	$('#info_' + this.objectId).remove();
};

org_uengine_codi_mw3_marketplace_category_ICategory.prototype = {
	showStatus : function(status){
	},
	startLoading : function(status){
	},
	endLoading : function(status){
	},

};

/*

org_uengine_codi_mw3_marketplace_category_ICategory.prototype.showChildrenCategories = function(){
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
};
*/