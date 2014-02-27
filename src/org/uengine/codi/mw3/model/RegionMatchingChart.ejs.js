var org_uengine_codi_mw3_model_RegionMatchingChart = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
    this.divId = mw3._getObjectDivId(this.objectId);
    this.divObj = $('#' + this.divId);
    
    var object = mw3.objects[this.objectId];
    if(object){
    	mw3.importScript('scripts/rgraph/libraries/RGraph.common.core.js');
    	mw3.importScript('scripts/rgraph/libraries/RGraph.common.context.js');
    	mw3.importScript('scripts/rgraph/libraries/RGraph.common.annotate.js');
    	mw3.importScript('scripts/rgraph/libraries/RGraph.common.tooltips.js');
    	mw3.importScript('scripts/rgraph/libraries/RGraph.common.dynamic.js');
    	mw3.importScript('scripts/rgraph/libraries/RGraph.scatter.js');
    }
    this.chartId = 'highChart_'+ this.objectId;
    
    
    
    // chart data
    var data2 = [[10,15],[13,16],[12,11],[15,16],[20,19],[19,16]];
    
	var objectId = this.objectId;
	$(document).ready(function() {
      var scatter2 = new RGraph.Scatter('scatter2',  [[50,31, 'red', '<b>Fred</b><br />Fred is at the start'], [80,49, 'blue', '<b>Juan</b><br />Juan is in the middle'], [280,45, 'red', '<b>Hoolio</b><br />Hoolio is at the end']],
              [[25,21,'red'], [67,22,'red'], [289,35,'red']]);
      scatter2.Set('chart.title', 'Sample Chart');
      scatter2.Set('chart.labels', ['1', '2', '3', '4']);
      scatter2.Set('chart.defaultcolor', 'black'); // Optional
      scatter2.Set('chart.gutter', 40); //margins
      scatter2.Set('chart.tickmarks', 'circle');
      scatter2.Set('chart.ticksize', 12);
      scatter2.Set('chart.xmax', 365);
//      scatter2.Set('chart.units.pre', '$');
//    scatter2.Set('chart.contextmenu', [['Clear', function () {RGraph.Clear(scatter2.canvas);
      scatter2.Draw();
     

  });
  
};
  