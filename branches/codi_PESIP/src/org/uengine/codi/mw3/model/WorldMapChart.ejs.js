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
    var regionNumber = new Array();
    for(var i = 0; i < item.length; i++){
    	regionName[i] = item[i].regionName;
    	regionNumber[i] = item[i].number;
    }
    console.log(regionNumber);
    console.log(regionName[0]);
    console.log(regionName[1]);
    if(object){
    	mw3.importScript('scripts/rgraph/libraries/RGraph.common.core.js');
    	mw3.importScript('scripts/rgraph/libraries/RGraph.bar.js');
    	mw3.importScript('scripts/rgraph/libraries/RGraph.line.js');
    }
    
    var bar = new RGraph.Bar('scatter3', regionNumber);
    				bar.Set('labels', regionName);
    				bar.Set('colors', ['Gradient(#99f:#27afe9:#058DC7:#058DC7)', 'Gradient(#94f776:#50B332:#B1E59F)', 'Gradient(#fe783e:#EC561B:#F59F7D)']);
//				    bar.Set('colors', ['#494949'],['#35A0DA'])
    				bar.Set('strokestyle', 'white');
				    bar.Set('linewidth', 2);
				    bar.Set('shadow', true);
				    bar.Set('shadow.offsetx', 1);
				    bar.Set('shadow.offsety', 0);
				    bar.Set('shadow.blur', 1);
    var line = new RGraph.Line('scatter3',regionNumber)
					    .Set('spline', true)
					    .Set('tickmarks', 'circle')
					    .Set('colors', ['#CDE217'])
					    .Set('ymax', 10)
		                .Set('linewidth', 3)
					    .Set('shadow', true)
					    .Set('shadow.color', 'rgba(20,20,20,0.3)')
					    .Set('shadow.blur', 15)
					    .Set('shadow.offsetx', 0)
					    .Set('shadow.offsety', 0);
	var combo = new RGraph.CombinedChart(bar, line);
	combo.Draw();
      
//	 var item = {
//             __className : 'org.uengine.codi.mw3.model.PublicServiceIntroduceItem',
//             
//         };

  
};
  