var org_uengine_processmarket_IMarketItem = function(objectId, className){
	this.objectId = objectId;
	this.className = className;
	
	this.divId = "#objDiv_" + objectId;
	
	$(this.divId).hover(function(){
		$("#objDiv_"+ objectId + " .marketItemDetail").show('clip',200);
	},function(){
		$("#objDiv_"+ objectId + " .marketItemDetail").hide('clip',200);
	})
	
	
	
}
