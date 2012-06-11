var org_uengine_codi_mw3_model_Locale = function(objectId, className){
	
	this.objectId = objectId;
	
};

function getMessage(key, defaultValue){
	var message = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Locale").resourceBundle[key];
	
	if(message)
		return message;
	
	if(defaultValue)
		return defaultValue;
	
	return key;
}