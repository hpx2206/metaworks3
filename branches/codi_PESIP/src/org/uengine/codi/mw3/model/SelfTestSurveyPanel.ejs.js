var org_uengine_codi_mw3_model_SelfTestSurveyPanel = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
    this.objectDivId = mw3._getObjectDivId(this.objectId);
    
    this.object = mw3.objects[this.objectId];
    var selfTest = mw3.objects[this.objectId];
    var contentLength = this.object.surveyContent;
    var radioValue = parseInt(0);
    
    //메타웍스 null처리
    $('#wBtn #radio_save').click(function(){
	    for(var i = 0 ; i < contentLength.length; i++){
	    	console.log("preferred_color"+i);
	    		radioValue += parseInt($("input[name=preferred_color"+i+"]:checked").val());
	    		console.log(radioValue);
	    }
	    var SurveyIndex = 0;
	    var metaworksHow ='';

	    var metaworksContext = {
	    		__className : 'org.metaworks.MetaworksContext',
	    		how :metaworksHow
	    };
	    
	   if(selfTest.surveyIndex == 2){
	    	SurveyIndex = 3;
	    }else if(selfTest.surveyIndex == 3){
	    	SurveyIndex = 4;
	    }else if(selfTest.surveyIndex == 4){
	    	SurveyIndex = 5;
	    }else if(selfTest.surveyIndex == 5){
	    	SurveyIndex = 6;
	    }else if(selfTest.surveyIndex == 6){
	    	SurveyIndex = 7;
	    }else if(selfTest.surveyIndex == 7){
	    	SurveyIndex = 8;
	    }else if(selfTest.surveyIndex == 8){
	    	SurveyIndex = 9;
	    }else{
	    	SurveyIndex = 1;
	    }
	    metaworksContext = {
	    		__className : 'org.metaworks.MetaworksContext',
	    		how :metaworksHow
	    };
	    
//	    this.object.sumScore.push(radioValue);
//	    var surveyPanel = {
//	    		__className : 'org.uengine.codi.mw3.model.SelfTestSurveyPanel',
//	    		sumScore : radioValue,
//	    		metaworksContext : metaworksContext,
//	    		surveyIndex : SurveyIndex
//	    };
	    
//	    console.log(surveyPanel);
//	    mw3.locateObject(surveyPanel);
	    selfTest.sumScore= radioValue;
	    selfTest.surveyIndex =SurveyIndex;
	    selfTest.saveSurvey();
    })
    
   
};

