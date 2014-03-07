var org_uengine_codi_mw3_model_WorldMapChart = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
    this.divId = mw3._getObjectDivId(this.objectId);
    this.divObj = $('#' + this.divId);
    
    var object = mw3.objects[this.objectId];
    
    var item = object.pastOrderInformationList;
    console.log(item.length);
    console.log(item);
    
    var regionName = new Array();
    for(var i = 0; i < item.length; i++){
    	regionName[i] = item[i].regionName;
    }
    
    console.log(regionName[0]);
    console.log(regionName[1]);
    if(object){
    	mw3.importScript('scripts/rgraph/libraries/RGraph.common.core.js');
    	mw3.importScript('scripts/rgraph/libraries/RGraph.bar.js');
    	mw3.importScript('scripts/rgraph/libraries/RGraph.line.js');
    }
    
    var bar = new RGraph.Bar('scatter3', [[item[0].number],[item[1].number]]);
    				bar.Set('labels', regionName);
    				bar.Set('colors', ['Gradient(#99f:#27afe9:#058DC7:#058DC7)', 'Gradient(#94f776:#50B332:#B1E59F)', 'Gradient(#fe783e:#EC561B:#F59F7D)'])
				    bar.Set('strokestyle', 'white')
				    bar.Set('linewidth', 2)
				    bar.Set('shadow', true)
				    bar.Set('shadow.offsetx', 1)
				    bar.Set('shadow.offsety', 0)
				    bar.Set('shadow.blur', 1)

    var line = new RGraph.Line('scatter3', [item[0].number,item[1].number])
					    .Set('spline', true)
					    .Set('tickmarks', 'filledcircle')
					    .Set('colors', ['blue'])
					    .Set('ymax', 10);
	
	var combo = new RGraph.CombinedChart(bar, line);
	combo.Draw();
      
//	 var item = {
//             __className : 'org.uengine.codi.mw3.model.PublicServiceIntroduceItem',
//             
//         };

  
};
  