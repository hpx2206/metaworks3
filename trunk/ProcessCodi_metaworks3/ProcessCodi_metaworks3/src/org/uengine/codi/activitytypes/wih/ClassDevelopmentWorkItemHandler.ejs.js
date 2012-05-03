var org_uengine_codi_activitytypes_wih_ClassDevelopmentWorkItemHandler = function(objectId, className){
	
	mw3.getInputElement(objectId, "resourceName").addEventListener('keydown', function(e){
		
		if(e.keyCode=13){
			
			mw3.getInputElement(objectId, "nextField").focus();
			
		}
		
	})


}