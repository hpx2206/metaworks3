var org_uengine_codi_mw3_model_SearchBox = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;

	$("#search_" + this.objectId).focus();
}

org_uengine_codi_mw3_model_SearchBox.prototype.getValue = function() {
	var object = mw3.objects[this.objectId];

	object.keyword = $("#search_" + this.objectId).val();

	return object;
}

var threadSearch = null;

function keyEventProcess(objectId) {
	if (this.threadSearch != null) {
		clearTimeout(this.threadSearch);
	}

	this.threadSearch = setTimeout(function() {
		//$("#search_" + objectId).blur();

		var value = mw3.getObject(objectId);
		value.search();
	}, 500);
}