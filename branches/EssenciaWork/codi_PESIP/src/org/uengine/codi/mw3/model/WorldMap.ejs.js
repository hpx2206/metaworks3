var org_uengine_codi_mw3_model_WorldMap = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
    this.divId = mw3._getObjectDivId(this.objectId);
    this.divObj = $('#' + this.divId);
    
    
    var object = mw3.objects[this.objectId];
    // import 부분
    if(object){
        mw3.importScript('scripts/jVectorMap/jquery-jvectormap-1.2.2.min.js');
        mw3.importScript('scripts/jVectorMap/jquery-jvectormap-world-mill-en.js');
        mw3.importStyle('scripts/jVectorMap/jquery-jvectormap-1.2.2.css');
    }
    
    // 객체에 db에서 불러온 orderInfo, codeInfo, koNameInfo 자료가 있다. 이를 토대로 커스터마이징
    var orderInfo = object.countryOrderInfo;
    var codeInfo = object.countryCodeInfo;
    
    // 지도 뿌리기.
    $(document).ready(function(){
        $('#jvm_worldMap').vectorMap({
            map: 'world_mill_en',
            backgroundColor: '#ffffff',
            regionStyle: {
            	initial: {
            	    "stroke": '#a9a9a9',
            	    "stroke-width": '0.5px',
            	    "stroke-opacity": '1'
        	    }
            },
            series: {
                regions: [{
                    values: orderInfo,
                    scale: ['#C8EEFF', '#0071A4'],
                    normalizeFunction: 'polynomial'
                        
                }]
            },
            
            onRegionClick: function(event, code) {
                // codeInfo hash에서 code를 key로 value를 찾아 selectRegion 에 setting
                var countryCode = codeInfo[code];
                
                object.selectedRegion = countryCode;
                object.selectRegion();
            },
            
            onRegionLabelShow: function() {
                $(function() {
                    // z-index가 없어서 가려진다. jvectormap-labe class css 수정
                    $('.jvectormap-label').css('z-index', 2000);
                      
                });
                    
            }
            
        });
       
    });
};
