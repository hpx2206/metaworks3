
var java_util_Date = function(objectId, objectType){
	this.objectId = objectId;
	this.objectType = objectType;
	
	var value = mw3.objects[objectId];   
	
	$("#"+this.objectId+"_datepicker").datepicker("setDate",value);
	
} 

java_util_Date.prototype.getValue = function(){
	var selDate = new Date($("#" + this.objectId + "_datepicker").datepicker("getDate"));
	
	return selDate.getTime();
}