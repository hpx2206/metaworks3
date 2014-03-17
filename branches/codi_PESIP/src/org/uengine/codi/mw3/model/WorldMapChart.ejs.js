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
    var piedata = new Array();
    var columnData = new Array();
    var colors = new Array();
    for(var i=0; i<item.length*2; i+=2){
    	colors[i] = '#'+i+'23456';
    }

    for(var i = 0; i < item.length; i++){
    	regionName[i] = item[i].regionName;
    	regionNumber[i] = item[i].number;
    	piedata[i] = {
    			name : regionName[i],
        		y: regionNumber[i],
        		color : colors[i]
    	}
    	columnData[i] = {
                type: 'column',
                name: regionName[i],
                data: regionNumber[i]
    	}
    };
    
    
    console.log("칼러"+colors);
    console.log("파이데이타"+regionNumber[0]);
    console.log("파이데이타"+regionNumber[1]);
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
                name:regionName[0],
                data: regionNumber
            },{
                type: 'spline',
                name: 'Average',
                data:regionNumber,
                marker: {
                	lineWidth:3,
                	lineColor: 'black',
                	fillColor: 'white'
                }
            },{
                type: 'pie',
                name: '전체건수 ',
                data: piedata,
                center: [650, 80],
                size: 100,
                showInLegend: true,
                dataLabels: {
                    enabled: true
                }
            }]
        });
    });
};
  