function addContact(objectId, e){
	var object = mw3.getObject(objectId);	
	
	object.pageX = e.pageX;
	object.pageY = e.pageY;			

	mw3.call(objectId, "addContact");	
}