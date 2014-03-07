var org_uengine_codi_mw3_model_SelfTestChart = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
    this.divId = mw3._getObjectDivId(this.objectId);
    this.divObj = $('#' + this.divId);
    
    var object = mw3.objects[this.objectId];
    if(object){
    	mw3.importScript('scripts/rgraph/libraries/RGraph.common.core.js');
//    	mw3.importScript('scripts/rgraph/libraries/RGraph.common.effects.js');
//    	mw3.importScript('scripts/rgraph/libraries/RGraph.common.dynamic.js');
//    	mw3.importScript('scripts/rgraph/libraries/RGraph.common.tooltips.js');
//    	mw3.importScript('scripts/rgraph/libraries/RGraph.drawing.rect.js');
    	mw3.importScript('scripts/rgraph/libraries/RGraph.radar.js');
    }
    
    var radarData1 = object.sumScore;
    
    var radar2 = new RGraph.Radar('selfTest', [radarData1,7,5,6,10,8,7,5,8,6])
	    .Set('labels', ['제품', '해외정보', '해외마케팅 인력','해외마케팅 활동','IT 역량','브랜드마케팅','R&D','글로벌 전략','글로벌 네트워크','시장규모'])
	    .Draw();
	    


				    
};

