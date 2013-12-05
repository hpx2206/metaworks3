var org_uengine_codi_mw3_model_WorldMap = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
    this.divId = mw3._getObjectDivId(this.objectId);
    this.divObj = $('#' + this.divId);
    
    
    // import 부분
    var object = mw3.objects[this.objectId];
    if(object){
        mw3.importScript('scripts/jVectorMap/data.js');
        mw3.importScript('scripts/jVectorMap/jquery-jvectormap-1.2.2.min.js');
        mw3.importScript('scripts/jVectorMap/jquery-jvectormap-world-mill-en.js');
        mw3.importStyle('scripts/jVectorMap/jquery-jvectormap-1.2.2.css');
    }
    
    // object 가져와서 현재 지역별 안에 있는 나라를 regionNodeList로 저장
    var regionNodeList = object.regionNode;
    
    var countryData = []; 
    //for each country, set the code and value
    $.each(data.countries, function() {
        countryData[this.code] = this.pGdp;
    });
     
    // 지도 담을 객체 및 마커 스타일 셋팅
    var map;
    // 지도 뿌리기.
    $(document).ready(function(){
        map = $('#jvm_worldMap').vectorMap({
            map : 'world_mill_en',
            markerStyle: {
                 initial: {
                    fill: 'red'
                 }
            },
            series : {
                regions : [ {
                    values : countryData,
                    scale : [ '#C8EEFF', '#0071A4' ],   // two colors: for minimum and maximum values 
                    normalizeFunction : 'polynomial'
                } ]
            },
            
            onRegionLabelShow: function(e, el, code) {
                var country = $.grep(data.countries, function(obj, index) {
                    return obj.code == code;
                })[0]; 
               
                if (country != undefined) {
                    $(function() {
                        // z-index가 없어서 가려진다. jvectormap-labe class css 수정
                        $('.jvectormap-label').css('z-index', 2000);
                        
                    });
                }
            }
            
        });
          
    });
    
    
    // map을 그렸다. 마커를 그려야겠지?
    // regions에서 node를 뽑아내기 위한 작업.
    var mapObject = map.vectorMap('get', 'mapObject');
    var regions = [];
    regions = mapObject.regions;
    
    var isElements = [];
    // regionNodeList 만큼 for문 돌면서 regionNodeList가 가지고 있는 국가코드(즉, 코디의 주제에 있는 나라 코드)를 key 값으로 하여
    // regions에서 검색하여 가져온다.(regions에는 180개국이 있지만 이 곳 때문에 3가지로 걸러진다.(현재 주제3개)
    for(var i = 0; i < regionNodeList.length; i++) {
        isElements[i] = regions[regionNodeList[i].vistype];
    }
    
    var nodes = [];
    var point = [];
    var regionName = [];
    var message = "발주정보를 확인하려면 클릭하세요";
    
    // 해쉬 맵에 있는 elements의 node를 가져와서 centroid 함수를 작성하여야 한다. (중간 값 계산)
    // nodes는 svg정보를 가지고 있는 bbox를 가지고 있으므로 이 부분이 가장 중요하다.
    // 국가 이름도 저장한다..따로..
    for(var i = 0; i < isElements.length; i++) {
        nodes[i] = isElements[i].element.node;
        regionName[i] = isElements[i].config.name;
        //console.log(isElements[i], regionName[i]);
        
        //bbox를 배열로 받아야 한다...(주제는 하나 하나가 객체니까...regionNode)
        var bbox = [];
        bbox[i] = nodes[i].getBBox();
        
        // bbox[i] 번째의 x, y 값 가져와서 중간 값을 구하는 과정. 대륙의 가로와 세로를 반으로 나누면 중간점.
        
        point[i] = [bbox[i].x + bbox[i].width, bbox[i].y + bbox[i].width];
        point[i] = mapObject.pointToLatLng(point[i][0], point[i][1]);
        //console.log(point[i]);
        
        mapObject.addMarker(i, {latLng: [point[i].lat, point[i].lng], name : regionName[i] +"의 "+ message } );
    }
    
};


