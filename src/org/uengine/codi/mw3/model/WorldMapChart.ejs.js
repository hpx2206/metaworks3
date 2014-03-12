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
    };
    
    
    if(object){
    	mw3.importScript('scripts/highchart/highcharts.js');
    	mw3.importScript('scripts/highchart/modules/exporting.js');
    }
    
    $(function () {
        $('#scatter3').highcharts({
            chart: {
            },
            title: {
                text: ''
            },
            xAxis: {
                categories:regionName
            },
            tooltip: {
                formatter: function() {
                    var s;
                    if (this.point.name) { // the pie chart
                        s = ''+
                            this.point.name +': '+ this.y +' 건수';
                    } else {
                        s = ''+
                            this.x  +': '+ this.y;
                    }
                    return s;
                }
            },
            labels: {
                items: [{
                    html: '전체 건수',
                    style: {
                        left: '600px',
                        top: '8px',
                        color: 'black'
                    }
                }]
            },
            series: [{
    			type: 'column',
    			name : regionName,
    			data : regionNumber
            }, {
                type: 'spline',
                name: 'Average',
                data:regionNumber,
                marker: {
                	lineWidth: 2,
                	lineColor: Highcharts.getOptions().colors[3],
                	fillColor: 'white'
                }
            }, {
                type: 'pie',
                name: '전체건수 ',
                data: regionNumber,
                center: [650, 80],
                size: 100,
                showInLegend: false,
                dataLabels: {
                    enabled: false
                }
            }]
        });
    });
};
  