var Window = function(objectId, className){
	
	this.objectId = objectId;
	this.className = className;



	this.divId = "objDiv_" + objectId;


	
	
	$("#" + this.divId).css("height","100%");

	

}

function addContact(objectId, e){
	var object = mw3.getObject(objectId);	
	
	object.pageX = e.pageX;
	object.pageY = e.pageY;			

	mw3.call(objectId, "addContact");	
}