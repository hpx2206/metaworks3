var org_uengine_codi_mw3_admin_ClassField = function(objectId, className){
	this.objectId = objectId;
	this.classname = className;
	
	this.object = mw3.objects[this.objectId];
	if(this.object.metaworksContext.when == 'edit'){
		// 콤보박스를 재로딩 하였을때 type 이 선택되어있다면 옵션과 value를 보여줌
		if( this.object.type != null && this.object.type == 'org.metaworks.component.SelectBox' ){
			$("#selectOption_"+this.objectId).show();
		}
		
		$('#' + mw3._getObjectDivId(this.objectId) ).find('select').bind('change', {objectId : this.objectId},function(event){
			var change = $(this).find('option:selected');
			var selectedText = change.text();
			if( selectedText.indexOf('Select') > 0){
				$("#selectOption_"+objectId).show();
			}else{
				$("#selectOption_"+objectId).hide();
			}
		});
	}
};