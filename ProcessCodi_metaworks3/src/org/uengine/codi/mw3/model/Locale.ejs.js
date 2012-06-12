var org_uengine_codi_mw3_model_Locale = function(objectId, className){
	this.objectId = objectId;
	
	jQuery.timeago.settings.strings = {
			  suffixAgo: getMessage("suffixAgo"),
			  suffixFromNow: getMessage("suffixFromNow"),
			  seconds: getMessage("seconds"),
			  minute: getMessage("minute"),
			  minutes: getMessage("minutes"),
			  hour: getMessage("hour"),
			  hours: getMessage("hours"),
			  day: getMessage("day"),
			  days: getMessage("days"),
			  month: getMessage("month"),
			  months: getMessage("months"),
			  year: getMessage("year"),
			  years: getMessage("years"),
			  wordSeparator: " ",
			  number: []
			};
	
	console.debug(jQuery.timeago.settings.strings);
	
};

function getMessage(key, defaultValue){
	var message = mw3.getAutowiredObject("org.uengine.codi.mw3.model.Locale").resourceBundle[key];
	
	if(message)
		return message;
	
	if(defaultValue)
		return defaultValue;
	
	return key;
}