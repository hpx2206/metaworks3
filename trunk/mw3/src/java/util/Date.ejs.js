
var java_util_Date = function(objectId, objectType){
	this.objectId = objectId;
	this.objectType = objectType;
	
	var value = mw3.objects[objectId];   
	
	$("#"+this.objectId+"_datepicker").datepicker("setDate",value);
	
} 

java_util_Date.prototype.getValue = function(){
	var dateObj = mw3.getObject(this.objectId);
	if(dateObj.metaworksContext) {
		if(dateObj.metaworksContext.when == 'edit' || dateObj.metaworksContext.when == 'new') {
			var selDate = new Date($("#" + this.objectId + "_datepicker").datepicker("getDate"));
			return selDate.getTime();
		} else if(dateObj.metaworksContext.when == 'view') {
			return dateObj;
		} else {
			return dateObj;
		}
	} else {
		return dateObj;
	}
}