var org_uengine_codi_mw3_model_WorldMap = function(objectId, className){
    this.objectId = objectId;
    this.className = className;
    this.divId = mw3._getObjectDivId(this.objectId);
    this.divObj = $('#' + this.divId);
    
    
    var object = mw3.objects[this.objectId];
    // import 부분
    if(object){
        mw3.importScript('scripts/jVectorMap/data.js');
        mw3.importScript('scripts/jVectorMap/jquery-jvectormap-1.2.2.min.js');
        mw3.importScript('scripts/jVectorMap/jquery-jvectormap-world-mill-en.js');
        mw3.importStyle('scripts/jVectorMap/jquery-jvectormap-1.2.2.css');
    }
    
    // db를 통해 최소한의 국가와 좌표값만 가져온다.
    var distinctOrderInformationList = object.distinctOrderInformation;
    
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
            },
            
            onMarkerClick: function(e, code){
                // 현재 그려진 마커에서 countrycode를 가져와 디비를 재검색..(방법이 없다. click 메서드에 제공되는 정보가 없다 어쩔수 없이 디비를 바라봐야 한다.)
                object.countrycode = distinctOrderInformationList[code].countrycode;
                object.countryname = distinctOrderInformationList[code].countryname;
                object.loadViewPanel();
                
            }
            
        });
          
    });
    
    
    // map을 그렸다. 마커를 그려야겠지?
    // distinctOrderInformationList 에서 좌표를 빼온다. 중복을 제거했기 때문에 이를 사용하면 한 국가씩만 마커가 칠해진다.
    var mapObject = map.vectorMap('get', 'mapObject');
    var regions = [];
    var message = "발주정보를 확인하려면 클릭하세요";
    regions = mapObject.regions;
    
    
    // marker 추가
    var markers = [];
    for(var i = 0; i < distinctOrderInformationList.length; i++) {
        var style = null;
        
        if (distinctOrderInformationList[i].count < 2) { // 3보다 작으면 파란색.
            style = {fill: '#1462FF', stroke : '#000000', "stroke-width": 1}
        } else if (2 <= distinctOrderInformationList[i].count && distinctOrderInformationList[i].count <= 10) { // 3보다 크고 10보다 작으면 노란색.
            style = {fill: '#FFFF00', stroke : '#000000', "stroke-width": 1}
        } else if (distinctOrderInformationList[i] > 10) { // 10보다 크면 빨강
            style = {fill: '#FF0000', stroke : '#000000', "stroke-width": 1}
        }
        
        markers.push({latLng: [distinctOrderInformationList[i].lat, distinctOrderInformationList[i].lng], name: distinctOrderInformationList[i].countryname + "의 " + message, style : style});
        mapObject.addMarkers(markers, null);
    }
    
    
    
    
    
    
    
    //////////////// 이 아래로 주석 //////////////////////
    
    
    
    // 아래는 해당 마커를 동적으로 그려준다..하지만 위도와 경도가 올바른 곳에 찍히지 않는다. 
    // 문제점을 해결하지 못해 주석처리..
    /*
    var nodes = [];
    var point = [];
    var regionName = [];
    var message = "발주정보를 확인하려면 클릭하세요";
    
    // 해쉬 맵에 있는 elements의 node를 가져와서 centroid 함수를 작성하여야 한다. (중간 값 계산)
    // nodes는 svg정보를 가지고 있는 bbox를 가지고 있으므로 이 부분이 가장 중요하다.
    // 국가 이름도 저장한다..따로..
    for(var i = 0; i < isElements.length; i++) {
        console.log(isElements);
    
       
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
       
        
        //mapObject.addMarker(i, {latLng: [point[i].lat, point[i].lng], name : regionName[i] +"의 "+ message } );
    }
    
    */
    
};
