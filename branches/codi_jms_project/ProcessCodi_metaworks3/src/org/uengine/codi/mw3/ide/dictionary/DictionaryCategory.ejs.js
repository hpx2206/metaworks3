var org_uengine_codi_mw3_ide_dictionary = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;
	
	$(dictionaryItem).hover( 
		function() {
			$(this).addClass('dictionaryType');
		},
		function() {
			$(this).removeClass('dictionaryType');
		}
	);
};