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
            radioValue += parseInt($("input[name=preferred_color"+i+"]:checked").val());
        }
        
        var surveyIndex = 0;
        
        if(selfTest.surveyIndex == 2){
            surveyIndex = 3;
        } else if(selfTest.surveyIndex == 3){
            surveyIndex = 4;
        } else if(selfTest.surveyIndex == 4){
            surveyIndex = 5;
        } else if(selfTest.surveyIndex == 5){
            surveyIndex = 6;
        } else if(selfTest.surveyIndex == 6){
            surveyIndex = 7;
        } else if(selfTest.surveyIndex == 7){
            surveyIndex = 8;
        } else if(selfTest.surveyIndex == 8){
            surveyIndex = 9;
        } else{
            surveyIndex = 1;
        }
     
    selfTest.sumScore = radioValue;
    selfTest.surveyIndex = surveyIndex;
    selfTest.progressSurvey();
    
    });
};