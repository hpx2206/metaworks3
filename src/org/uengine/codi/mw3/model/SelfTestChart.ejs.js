var org_uengine_codi_mw3_model_SelfTestChart = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
    this.divId = mw3._getObjectDivId(this.objectId);
    this.divObj = $('#' + this.divId);
    
    var object = mw3.objects[this.objectId];
    if(object){
    	mw3.importScript('scripts/rgraph/libraries/RGraph.common.core.js');
    	mw3.importScript('scripts/rgraph/libraries/RGraph.common.effects.js');
    	mw3.importScript('scripts/rgraph/libraries/RGraph.radar.js');
    }
    
    var radarData = object.sumScore;
    
    var radar = new RGraph.Radar('selfTest',radarData)
     .Set('labels', ['제품', '해외정보', '해외마케팅 인력','해외마케팅 활동','IT 역량','브랜드마케팅','R&D','글로벌 전략','글로벌 네트워크'])
     .Set('labels.axes', 'n')
     .Set('labels.offset', 20)
     .Set('gutter.top',35)
     .Set('strokestyle', 'black')
     .Set('colors.alpha', 0.3)
     .Set('accumulative', true)
     .Set('colors', ['Gradient(white:green:green)'])
     .Set('labels.axes.boxed', false)
     .Set('labels.axes.boxed.zero', false)
     .Draw();
				    
};

