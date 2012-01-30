//mw3.importScript('https://www.google.com/jsapi');

    
var org_metaworks_widget_chart_Guage = function(objectId, className){
	var drawChart = function(){
		    
		var object = mw3.objects[objectId];
		
	    var data = new google.visualization.DataTable();
	    data.addColumn('string', 'Label');
	    data.addColumn('number', 'Value');
	    data.addRows([
	      [object.label, object.value]
	    ]);
	
	    var options = {
	      width: 400, height: 120,
	      redFrom: 90, redTo: 100,
	      yellowFrom:75, yellowTo: 90,
	      minorTicks: 5
	    };
	
	    var chart = new google.visualization.Gauge(document.getElementById('chart_'+objectId));
	    chart.draw(data, options);
	};
	
	google.setOnLoadCallback(drawChart);
	
	drawChart();
}

