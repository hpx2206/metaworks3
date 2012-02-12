var com_test_widget_Guage2 = function(objectId, className){
    var drawChart = function(){
		    
		var object = mw3.objects[objectId];
        
        if(!object){
            object = {
                label: "Label",
                value: 50
            }
        }
		
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
