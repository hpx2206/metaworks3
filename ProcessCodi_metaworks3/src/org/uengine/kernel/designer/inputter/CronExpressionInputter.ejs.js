var org_uengine_kernel_designer_inputter_CronExpressionInputter = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
	
	var object = mw3.objects[this.objectId];
    if(object != null && !object.preLoaded){
        object.load();
    }
	
};

org_uengine_kernel_designer_inputter_CronExpressionInputter.prototype = {
}