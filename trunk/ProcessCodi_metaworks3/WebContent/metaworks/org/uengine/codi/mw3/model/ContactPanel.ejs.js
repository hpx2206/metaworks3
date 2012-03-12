var Window = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;


}

function addContact(objectId, e){
	var object = mw3.getObject(objectId);	
	
	object.pageX = e.pageX;
	object.pageY = e.pageY;			

	mw3.call(objectId, "addContact");	
}


var org_uengine_codi_mw3_model_ContactPanel = function(objectId, className) {
	this.objectId = objectId;
	this.className = className;

	this.divId = "objDiv_" + objectId;

	this.divId = "objDiv_" + objectId;	
	$("#" + this.divId).css("height","100%");

	
	
}