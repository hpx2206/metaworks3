var org_uengine_kernel_ParameterContextPanel = function(objectId, className){
	// default setting
	this.objectId = objectId;
	this.className = className;
	
	this.clickedIdx = -1;
	
};

org_uengine_kernel_ParameterContextPanel.prototype = {
		clickedItem : function(index){
			this.clickedIdx = index;
		},
		removeItem : function(){
			if( this.clickedIdx >= 0 ){
				var object = mw3.objects[this.objectId];
				object.selectedIndex = this.clickedIdx;
				object.removeActivityVariable();
			}else{
				alert('선택된 변수가 없습니다.');
			}
		}
};