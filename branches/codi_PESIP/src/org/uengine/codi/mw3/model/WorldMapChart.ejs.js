var org_uengine_codi_mw3_model_WorldMapChart = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
    this.divId = mw3._getObjectDivId(this.objectId);
    this.divObj = $('#' + this.divId);
    this.object = mw3.objects[this.objectId];
    
    var object = mw3.objects[this.objectId];
    if(object){
    	mw3.importScript('scripts/rgraph/libraries/RGraph.common.core.js');
    	mw3.importScript('scripts/rgraph/libraries/RGraph.common.key.js');
    	mw3.importScript('scripts/rgraph/libraries/RGraph.bar.js');
    }
    
    
    var regionList = this.object.pastOrderInformationList;
    
    for(var i = 0; i < regionList.length; i++) {
    
    
    
       var bar = new RGraph.Bar('cvs', barData)            
                .Set('hmargin', 1)
                .Set('colors', colors)
                .Set('colors.sequential', true)
                .Set('xaxispos', 'center')
                .Set('background.grid.autofit.numhlines', 10)
                .Set('ymax', 100)
                .Set('gutter.bottom', 50)
                .Set('labels', ['1997', '1998','1999','2000','2001','2002','2003','2004','2005','2006','2007','2008','2009','2010','2011'])
       
    } 
  
};
  