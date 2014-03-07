var org_uengine_codi_mw3_model_SelfTestSurveyPanel = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
    this.objectDivId = mw3._getObjectDivId(this.objectId);
    
    this.object = mw3.objects[this.objectId];
    
    var contentLength = this.object.surveyContent;
    var radioValue = parseInt(0);
    var i;
    
    
    $('#wBtn #finish').click(function(){
	    for(i = 0 ; i < contentLength.length; i++){
	    	console.log("preferred_color"+i);
	    		radioValue += parseInt($("input[name=preferred_color"+i+"]:checked").val());
	    		console.log(radioValue);
	    }
	    
	    var sumScore = {
	    		__className : 'org.uengine.codi.mw3.model.SelfTestSurveyPanel',
	    		sumScore : radioValue
	    };
	    
	    
	    mw3.locateObject(sumScore);
	    sumScore.showResult();
    })
    
   
};

