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
    	mw3.importScript('scripts/rgraph/libraries/RGraph.drawing.xaxis.js');
    	mw3.importScript('scripts/rgraph/libraries/RGraph.scatter.js');
    }
    
    
      var scatter2 = new RGraph.Scatter('scatter2',  [[50,31, 'red', '<b>Fred</b><br />Fred is at the start'], 
                                                      [80,49, 'blue', '<b>Juan</b><br />Juan is in the middle'], 
                                                      [180,45, 'red', '<b>Hoolio</b><br />Hoolio is at the end']]);
//      scatter2.Set('chart.title', 'Sample Chart');
   //   scatter2.Set('chart.labels', ['1', '2', '3', '4']);
      scatter2.Set('chart.defaultcolor', 'black'); // Optional
      scatter2.Set('chart.gutter', 40); //margins
      scatter2.Set('chart.tickmarks', 'circle');
      scatter2.Set('chart.ticksize', 12);
      scatter2.Set('chart.xmax', 200);
      scatter2.Set('chart.ymax', 200);
      scatter2.Set('tooltips.event','mousemove');
      scatter2.Set('tooltips',['AAA','BBB','CCC']);
//      scatter2.Set('chart.units.pre', '$');
//    scatter2.Set('chart.contextmenu', [['Clear', function () {RGraph.Clear(scatter2.canvas);
      scatter2.Draw();
     
      var xaxis = new RGraph.Drawing.XAxis('scatter2', scatter2.canvas.height - 25)
		.Set('max',200)
		.Set('xaxispos','center')
		.Draw();

  
};
  