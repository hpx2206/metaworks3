var org_uengine_codi_mw3_model_SelfTestChart = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
    this.divId = mw3._getObjectDivId(this.objectId);
    this.divObj = $('#' + this.divId);
    
    var object = mw3.objects[this.objectId];
    if(object){
    	mw3.importScript('scripts/highchart/highcharts.js');
    	mw3.importScript('scripts/highchart/highcharts-more.js');
    	mw3.importScript('scripts/highchart/modules/exporting.js');
    }
    
    var radarData = object.sumScore;
    
    $(function () {

    	$('#selfTest').highcharts({
    	            
    	    chart: {
    	        polar: true,
    	        type: 'line'
    	    },
    	    
    	    title: {
    	        text: '',
    	        x: -80
    	    },
    	    
    	    pane: {
    	    	size: '90%'
    	    },
    	    
    	    xAxis: {
    	        categories: ['제품', '해외정보', '해외마케팅 인력','해외마케팅 활동',
    		                    'IT 역량','브랜드마케팅','R&D','글로벌 전략','글로벌 네트워크'],
    	        tickmarkPlacement: 'on',
    	        lineWidth: 0
    	    },
    	        
    	    yAxis: {
    	        gridLineInterpolation: 'polygon',
    	        lineWidth: 0,
    	        min: 0
    	    },
    	    
    	    tooltip: {
    	    	shared: true,
    	        pointFormat: '<span style="color:{series.color}">{series.name}: <b>${point.y:,.0f}</b><br/>'
    	    },
    	    
    	    legend: {
                enabled: false
    	    },
    	    
    	    series: [{
    	        name: 'Self Test',
    	        data: radarData,
    	        pointPlacement: 'on'
    	    }]
    	
    	});
    });


				    
};

